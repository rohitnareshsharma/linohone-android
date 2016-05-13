/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.hardware.Camera
 *  android.hardware.Camera$AutoFocusCallback
 *  android.hardware.Camera$Parameters
 *  android.hardware.Camera$PreviewCallback
 *  android.hardware.Camera$Size
 *  android.view.SurfaceHolder
 *  android.view.SurfaceView
 */
package org.linphone.mediastream.video.capture;

import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration;

public class AndroidVideoApi5JniWrapper {
    public static boolean isRecording = false;

    public static native void putImage(long var0, byte[] var2);

    public static int detectCameras(int[] indexes, int[] frontFacing, int[] orientation) {
        Log.d("detectCameras\n");
        AndroidCameraConfiguration.AndroidCamera[] cameras = AndroidCameraConfiguration.retrieveCameras();
        int nextIndex = 0;
        for (AndroidCameraConfiguration.AndroidCamera androidCamera : cameras) {
            if (nextIndex == 2) {
                Log.w("Returning only the 2 first cameras (increase buffer size to retrieve all)");
                break;
            }
            indexes[nextIndex] = androidCamera.id;
            frontFacing[nextIndex] = androidCamera.frontFacing ? 1 : 0;
            orientation[nextIndex] = androidCamera.orientation;
            ++nextIndex;
        }
        return nextIndex;
    }

    public static int[] selectNearestResolutionAvailable(int cameraId, int requestedW, int requestedH) {
        Log.d("mediastreamer", "selectNearestResolutionAvailable: " + cameraId + ", " + requestedW + "x" + requestedH);
        return AndroidVideoApi5JniWrapper.selectNearestResolutionAvailableForCamera(cameraId, requestedW, requestedH);
    }

    public static void activateAutoFocus(Object cam) {
        Log.d("mediastreamer", "Turning on autofocus on camera " + cam);
        Camera camera = (Camera)cam;
        if (camera != null && (camera.getParameters().getFocusMode() == "auto" || camera.getParameters().getFocusMode() == "macro")) {
            camera.autoFocus(null);
        }
    }

    public static Object startRecording(int cameraId, int width, int height, int fps, int rotation, final long nativePtr) {
        Log.d("mediastreamer", "startRecording(" + cameraId + ", " + width + ", " + height + ", " + fps + ", " + rotation + ", " + nativePtr + ")");
        Camera camera = Camera.open();
        AndroidVideoApi5JniWrapper.applyCameraParameters(camera, width, height, fps);
        camera.setPreviewCallback(new Camera.PreviewCallback(){

            public void onPreviewFrame(byte[] data, Camera camera) {
                if (AndroidVideoApi5JniWrapper.isRecording) {
                    AndroidVideoApi5JniWrapper.putImage(nativePtr, data);
                }
            }
        });
        camera.startPreview();
        isRecording = true;
        Log.d("mediastreamer", "Returning camera object: " + (Object)camera);
        return camera;
    }

    public static void stopRecording(Object cam) {
        isRecording = false;
        Log.d("mediastreamer", "stopRecording(" + cam + ")");
        Camera camera = (Camera)cam;
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
        } else {
            Log.i("mediastreamer", "Cannot stop recording ('camera' is null)");
        }
    }

    public static void setPreviewDisplaySurface(Object cam, Object surf) {
        Log.d("mediastreamer", "setPreviewDisplaySurface(" + cam + ", " + surf + ")");
        Camera camera = (Camera)cam;
        SurfaceView surface = (SurfaceView)surf;
        try {
            camera.setPreviewDisplay(surface.getHolder());
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    protected static int[] selectNearestResolutionAvailableForCamera(int id2, int requestedW, int requestedH) {
        if (requestedH > requestedW) {
            int t = requestedH;
            requestedH = requestedW;
            requestedW = t;
        }
        AndroidCameraConfiguration.AndroidCamera[] cameras = AndroidCameraConfiguration.retrieveCameras();
        List<Camera.Size> supportedSizes = null;
        for (AndroidCameraConfiguration.AndroidCamera c : cameras) {
            if (c.id != id2) continue;
            supportedSizes = c.resolutions;
        }
        if (supportedSizes == null) {
            Log.e("mediastreamer", "Failed to retrieve supported resolutions.");
            return null;
        }
        Log.d("mediastreamer", "" + supportedSizes.size() + " supported resolutions :");
        for (Camera.Size s : supportedSizes) {
            Log.d("mediastreamer", "\t" + s.width + "x" + s.height);
        }
        int[] r = null;
        int rW = Math.max(requestedW, requestedH);
        int rH = Math.min(requestedW, requestedH);
        try {
            Camera.Size result = supportedSizes.get(0);
            int req = rW * rH;
            int minDist = Integer.MAX_VALUE;
            int useDownscale = 0;
            for (Camera.Size s2 : supportedSizes) {
                int dist = -1 * (req - s2.width * s2.height);
                if ((s2.width >= rW && s2.height >= rH || s2.width >= rH && s2.height >= rW) && dist < minDist) {
                    minDist = dist;
                    result = s2;
                    useDownscale = 0;
                }
                int downScaleDist = -1 * (req - s2.width * s2.height / 4);
                if ((s2.width / 2 >= rW && s2.height / 2 >= rH || s2.width / 2 >= rH && s2.height / 2 >= rW) && downScaleDist < minDist) {
                    if (Version.hasFastCpuWithAsmOptim()) {
                        minDist = downScaleDist;
                        result = s2;
                        useDownscale = 1;
                    } else {
                        result = s2;
                        useDownscale = 0;
                    }
                }
                if (s2.width != rW || s2.height != rH) continue;
                result = s2;
                useDownscale = 0;
                break;
            }
            r = new int[]{result.width, result.height, useDownscale};
            Log.d("mediastreamer", "resolution selection done (" + r[0] + ", " + r[1] + ", " + r[2] + ")");
            return r;
        }
        catch (Exception exc) {
            Log.e(exc, "mediastreamer", " resolution selection failed");
            return null;
        }
    }

    protected static void applyCameraParameters(Camera camera, int width, int height, int requestedFps) {
        Camera.Parameters params = camera.getParameters();
        params.setPreviewSize(width, height);
        List supported = params.getSupportedPreviewFrameRates();
        if (supported != null) {
            int nearest = Integer.MAX_VALUE;
            for (Object from : supported) {
                Integer fr = (Integer) from;
                int diff = Math.abs(fr - requestedFps);
                if (diff >= nearest) continue;
                nearest = diff;
                params.setPreviewFrameRate(fr.intValue());
            }
            Log.d("mediastreamer", "Preview framerate set:" + params.getPreviewFrameRate());
        }
        camera.setParameters(params);
    }

}

