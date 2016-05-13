/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.hardware.Camera
 *  android.hardware.Camera$Size
 */
package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfigurationReader5;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfigurationReader9;

public class AndroidCameraConfiguration {
    private static AndroidCamera[] camerasCache;

    public static AndroidCamera[] retrieveCameras() {
        AndroidCameraConfiguration.initCamerasCache();
        return camerasCache;
    }

    public static boolean hasSeveralCameras() {
        AndroidCameraConfiguration.initCamerasCache();
        return camerasCache.length > 1;
    }

    public static boolean hasFrontCamera() {
        AndroidCameraConfiguration.initCamerasCache();
        for (AndroidCamera cam : camerasCache) {
            if (!cam.frontFacing) continue;
            return true;
        }
        return false;
    }

    private static void initCamerasCache() {
        if (camerasCache != null && camerasCache.length != 0) {
            return;
        }
        try {
            camerasCache = Version.sdk() < 9 ? AndroidCameraConfiguration.probeCamerasSDK5() : AndroidCameraConfiguration.probeCamerasSDK9();
        }
        catch (Exception exc) {
            Log.e("Error: cannot retrieve cameras information (busy ?)", exc);
            exc.printStackTrace();
            camerasCache = new AndroidCamera[0];
        }
    }

    static AndroidCamera[] probeCamerasSDK5() {
        return AndroidCameraConfigurationReader5.probeCameras();
    }

    static AndroidCamera[] probeCamerasSDK9() {
        return AndroidCameraConfigurationReader9.probeCameras();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class AndroidCamera {
        public int id;
        public boolean frontFacing;
        public int orientation;
        public List<Camera.Size> resolutions;

        public AndroidCamera(int i, boolean f, int o, List<Camera.Size> r) {
            this.id = i;
            this.frontFacing = f;
            this.orientation = o;
            this.resolutions = r;
        }
    }

}

