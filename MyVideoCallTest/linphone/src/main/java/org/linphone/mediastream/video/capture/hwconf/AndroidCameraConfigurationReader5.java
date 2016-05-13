/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.hardware.Camera
 *  android.hardware.Camera$Parameters
 *  android.hardware.Camera$Size
 */
package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera;
import java.util.ArrayList;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration;
import org.linphone.mediastream.video.capture.hwconf.Hacks;

class AndroidCameraConfigurationReader5 {
    AndroidCameraConfigurationReader5() {
    }

    public static AndroidCameraConfiguration.AndroidCamera[] probeCameras() {
        ArrayList<AndroidCameraConfiguration.AndroidCamera> cam = new ArrayList<AndroidCameraConfiguration.AndroidCamera>(1);
        Camera camera = Camera.open();
        List r = camera.getParameters().getSupportedPreviewSizes();
        camera.release();
        if (Hacks.isGalaxySOrTab()) {
            Log.d("Hack Galaxy S : has one or more cameras");
            if (Hacks.isGalaxySOrTabWithFrontCamera()) {
                Log.d("Hack Galaxy S : HAS a front camera with id=2");
                cam.add(new AndroidCameraConfiguration.AndroidCamera(2, true, 90, r));
            } else {
                Log.d("Hack Galaxy S : NO front camera");
            }
            Log.d("Hack Galaxy S : HAS a rear camera with id=1");
            cam.add(new AndroidCameraConfiguration.AndroidCamera(1, false, 90, r));
        } else {
            cam.add(new AndroidCameraConfiguration.AndroidCamera(0, false, 90, r));
            if (Hacks.hasTwoCamerasRear0Front1()) {
                Log.d("Hack SPHD700 has 2 cameras a rear with id=0 and a front with id=1");
                cam.add(new AndroidCameraConfiguration.AndroidCamera(1, true, 90, r));
            }
        }
        AndroidCameraConfiguration.AndroidCamera[] result = new AndroidCameraConfiguration.AndroidCamera[cam.size()];
        result = cam.toArray(result);
        return result;
    }
}

