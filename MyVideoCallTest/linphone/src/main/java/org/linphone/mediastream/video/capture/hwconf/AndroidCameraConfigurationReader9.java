/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.hardware.Camera
 *  android.hardware.Camera$CameraInfo
 *  android.hardware.Camera$Parameters
 *  android.hardware.Camera$Size
 */
package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera;
import java.util.ArrayList;
import java.util.List;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration;

class AndroidCameraConfigurationReader9 {
    AndroidCameraConfigurationReader9() {
    }

    public static AndroidCameraConfiguration.AndroidCamera[] probeCameras() {
        ArrayList<AndroidCameraConfiguration.AndroidCamera> cam = new ArrayList<AndroidCameraConfiguration.AndroidCamera>(Camera.getNumberOfCameras());
        for (int i = 0; i < Camera.getNumberOfCameras(); ++i) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo((int)i, (Camera.CameraInfo)info);
            Camera c = Camera.open((int)i);
            cam.add(new AndroidCameraConfiguration.AndroidCamera(i, info.facing == 1, info.orientation, c.getParameters().getSupportedPreviewSizes()));
            c.release();
        }
        AndroidCameraConfiguration.AndroidCamera[] result = new AndroidCameraConfiguration.AndroidCamera[cam.size()];
        result = cam.toArray(result);
        return result;
    }
}

