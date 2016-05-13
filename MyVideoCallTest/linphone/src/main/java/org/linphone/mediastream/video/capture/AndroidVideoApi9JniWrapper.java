/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.graphics.ImageFormat
 *  android.hardware.Camera
 *  android.hardware.Camera$CameraInfo
 *  android.hardware.Camera$Parameters
 *  android.hardware.Camera$PreviewCallback
 *  android.hardware.Camera$Size
 */
package org.linphone.mediastream.video.capture;

import android.annotation.TargetApi;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.video.capture.AndroidVideoApi5JniWrapper;
import org.linphone.mediastream.video.capture.AndroidVideoApi8JniWrapper;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@TargetApi(value=9)
public class AndroidVideoApi9JniWrapper {
    public static int detectCameras(int[] indexes, int[] frontFacing, int[] orientation) {
        return AndroidVideoApi5JniWrapper.detectCameras(indexes, frontFacing, orientation);
    }

    public static int[] selectNearestResolutionAvailable(int cameraId, int requestedW, int requestedH) {
        Log.d("selectNearestResolutionAvailable: " + cameraId + ", " + requestedW + "x" + requestedH);
        return AndroidVideoApi5JniWrapper.selectNearestResolutionAvailableForCamera(cameraId, requestedW, requestedH);
    }

    public static Object startRecording(int cameraId, int width, int height, int fps, int rotation, final long nativePtr) {
        Log.d("startRecording(" + cameraId + ", " + width + ", " + height + ", " + fps + ", " + rotation + ", " + nativePtr + ")");
        try {
            Camera camera = Camera.open((int)cameraId);
            Camera.Parameters params = camera.getParameters();
            params.setPreviewSize(width, height);
            int[] chosenFps = AndroidVideoApi9JniWrapper.findClosestEnclosingFpsRange(fps * 1000, params.getSupportedPreviewFpsRange());
            params.setPreviewFpsRange(chosenFps[0], chosenFps[1]);
            camera.setParameters(params);
            int bufferSize = width * height * ImageFormat.getBitsPerPixel((int)params.getPreviewFormat()) / 8;
            camera.addCallbackBuffer(new byte[bufferSize]);
            camera.addCallbackBuffer(new byte[bufferSize]);
            camera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback(){

                public void onPreviewFrame(byte[] data, Camera camera) {
                    if (data == null) {
                        Camera.Parameters params = camera.getParameters();
                        Camera.Size size = params.getPreviewSize();
                        int bufferSize = size.width * size.height * ImageFormat.getBitsPerPixel((int)params.getPreviewFormat()) / 8;
                        bufferSize += bufferSize / 20;
                        camera.addCallbackBuffer(new byte[bufferSize]);
                    } else if (AndroidVideoApi5JniWrapper.isRecording) {
                        AndroidVideoApi5JniWrapper.putImage(nativePtr, data);
                        camera.addCallbackBuffer(data);
                    }
                }
            });
            AndroidVideoApi9JniWrapper.setCameraDisplayOrientation(rotation, cameraId, camera);
            camera.startPreview();
            AndroidVideoApi5JniWrapper.isRecording = true;
            Log.d("Returning camera object: " + (Object)camera);
            return camera;
        }
        catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }

    public static void stopRecording(Object cam) {
        AndroidVideoApi5JniWrapper.isRecording = false;
        AndroidVideoApi8JniWrapper.stopRecording(cam);
    }

    public static void setPreviewDisplaySurface(Object cam, Object surf) {
        AndroidVideoApi5JniWrapper.setPreviewDisplaySurface(cam, surf);
    }

    private static void setCameraDisplayOrientation(int rotationDegrees, int cameraId, Camera camera) {
        int result;
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo((int)cameraId, (Camera.CameraInfo)info);
        if (info.facing == 1) {
            result = (info.orientation + rotationDegrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - rotationDegrees + 360) % 360;
        }
        Log.w("Camera preview orientation: " + result);
        try {
            camera.setDisplayOrientation(result);
        }
        catch (Exception exc) {
            Log.e("Failed to execute: camera[" + (Object)camera + "].setDisplayOrientation(" + result + ")");
            exc.printStackTrace();
        }
    }

    private static int[] findClosestEnclosingFpsRange(int expectedFps, List<int[]> fpsRanges) {
        Log.d("Searching for closest fps range from " + expectedFps);
        if (fpsRanges == null || fpsRanges.size() == 0) {
            return new int[]{0, 0};
        }
        int[] closestRange = fpsRanges.get(0);
        int measure = Math.abs(closestRange[0] - expectedFps) + Math.abs(closestRange[1] - expectedFps);
        for (int[] curRange : fpsRanges) {
            int curMeasure;
            if (curRange[0] > expectedFps || curRange[1] < expectedFps || (curMeasure = Math.abs(curRange[0] - expectedFps) + Math.abs(curRange[1] - expectedFps)) >= measure) continue;
            closestRange = curRange;
            measure = curMeasure;
            Log.d("a better range has been found: w=" + closestRange[0] + ",h=" + closestRange[1]);
        }
        Log.d("The closest fps range is w=" + closestRange[0] + ",h=" + closestRange[1]);
        return closestRange;
    }

}

