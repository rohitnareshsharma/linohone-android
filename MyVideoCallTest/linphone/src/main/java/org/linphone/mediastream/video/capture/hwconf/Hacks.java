/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.hardware.Camera
 *  android.os.Build
 */
package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera;
import android.os.Build;
import java.lang.reflect.Method;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;

public final class Hacks {
    private static BuiltInEchoCancellerModel[] mBuiltInEchoCancellerModels = new BuiltInEchoCancellerModel[]{new BuiltInEchoCancellerModel("samsung", "GT-I9100"), new BuiltInEchoCancellerModel("samsung", "GT-I9300")};

    private Hacks() {
    }

    public static boolean isGalaxySOrTabWithFrontCamera() {
        return Hacks.isGalaxySOrTab() && !Hacks.isGalaxySOrTabWithoutFrontCamera();
    }

    private static boolean isGalaxySOrTabWithoutFrontCamera() {
        return Hacks.isSC02B() || Hacks.isSGHI896();
    }

    public static boolean isGalaxySOrTab() {
        return Hacks.isGalaxyS() || Hacks.isGalaxyTab();
    }

    public static boolean isGalaxyTab() {
        return Hacks.isGTP1000();
    }

    private static boolean isGalaxyS() {
        return Hacks.isGT9000() || Hacks.isSC02B() || Hacks.isSGHI896() || Hacks.isSPHD700();
    }

    public static final boolean hasTwoCamerasRear0Front1() {
        return Hacks.isLGP970() || Hacks.isSPHD700() || Hacks.isADR6400();
    }

    private static final boolean isADR6400() {
        return Build.MODEL.startsWith("ADR6400") || Build.DEVICE.startsWith("ADR6400");
    }

    private static final boolean isSPHD700() {
        return Build.DEVICE.startsWith("SPH-D700");
    }

    private static boolean isSGHI896() {
        return Build.DEVICE.startsWith("SGH-I896");
    }

    private static boolean isGT9000() {
        return Build.DEVICE.startsWith("GT-I9000");
    }

    private static boolean isSC02B() {
        return Build.DEVICE.startsWith("SC-02B");
    }

    private static boolean isGTP1000() {
        return Build.DEVICE.startsWith("GT-P1000");
    }

    private static final boolean isLGP970() {
        return Build.DEVICE.startsWith("LG-P970");
    }

    public static final void sleep(int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException ie) {
            // empty catch block
        }
    }

    public static boolean needSoftvolume() {
        return Hacks.isGalaxySOrTab() && Version.sdkStrictlyBelow(9);
    }

    public static boolean needGalaxySAudioHack() {
        return Hacks.isGalaxySOrTab() && !Hacks.isSC02B();
    }

    public static boolean needPausingCallForSpeakers() {
        return Hacks.needGalaxySAudioHack();
    }

    public static boolean hasCamera() {
        if (Version.sdkAboveOrEqual(9)) {
            int nb = 0;
            try {
                nb = (Integer)Camera.class.getMethod("getNumberOfCameras", null).invoke(null, new Object[0]);
            }
            catch (Exception e) {
                Log.e("Error getting number of cameras");
            }
            return nb > 0;
        }
        Log.i("Hack: considering there IS a camera.\nIf it is not the case, report DEVICE and MODEL to linphone-users@nongnu.org");
        return true;
    }

    public static boolean hasBuiltInEchoCanceller() {
        for (BuiltInEchoCancellerModel model : mBuiltInEchoCancellerModels) {
            if (!Build.MANUFACTURER.equals(model.manufacturer) || !Build.MODEL.startsWith(model.model)) continue;
            Log.i(Build.MANUFACTURER + " " + Build.MODEL + " has a built-in echo canceller");
            return true;
        }
        Log.i(Build.MANUFACTURER + " " + Build.MODEL + " doesn't have a built-in echo canceller");
        return false;
    }

    private static class BuiltInEchoCancellerModel {
        public String manufacturer;
        public String model;

        public BuiltInEchoCancellerModel(String manufacturer, String model) {
            this.manufacturer = manufacturer;
            this.model = model;
        }
    }

}

