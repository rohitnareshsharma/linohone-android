/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.os.Build
 *  android.os.Build$VERSION
 */
package org.linphone.mediastream;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.video.capture.hwconf.Hacks;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Version {
    public static final int API03_CUPCAKE_15 = 3;
    public static final int API04_DONUT_16 = 4;
    public static final int API05_ECLAIR_20 = 5;
    public static final int API06_ECLAIR_201 = 6;
    public static final int API07_ECLAIR_21 = 7;
    public static final int API08_FROYO_22 = 8;
    public static final int API09_GINGERBREAD_23 = 9;
    public static final int API10_GINGERBREAD_MR1_233 = 10;
    public static final int API11_HONEYCOMB_30 = 11;
    public static final int API12_HONEYCOMB_MR1_31X = 12;
    public static final int API13_HONEYCOMB_MR2_32 = 13;
    public static final int API14_ICE_CREAM_SANDWICH_40 = 14;
    public static final int API15_ICE_CREAM_SANDWICH_403 = 15;
    public static final int API16_JELLY_BEAN_41 = 16;
    public static final int API17_JELLY_BEAN_42 = 17;
    public static final int API18_JELLY_BEAN_43 = 18;
    public static final int API19_KITKAT_44 = 19;
    public static final int API21_LOLLIPOP_50 = 21;
    public static final int API22_LOLLIPOP_51 = 22;
    private static Boolean hasNeon;
    private static final int buildVersion;
    private static Boolean sCacheHasZrtp;

    private static native boolean nativeHasZrtp();

    private static native boolean nativeHasNeon();

    public static boolean isXLargeScreen(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 4;
    }

    public static final boolean sdkAboveOrEqual(int value) {
        return buildVersion >= value;
    }

    public static final boolean sdkStrictlyBelow(int value) {
        return buildVersion < value;
    }

    public static int sdk() {
        return buildVersion;
    }

    public static List<String> getCpuAbis() {
        ArrayList<String> cpuabis = new ArrayList<String>();
        if (Version.sdkAboveOrEqual(21)) {
            try {
                String[] abis;
                for (String abi : abis = (String[])Build.class.getField("SUPPORTED_ABIS").get(null)) {
                    cpuabis.add(abi);
                }
            }
            catch (Throwable e) {
                Log.e(e, new Object[0]);
            }
        } else {
            cpuabis.add(Build.CPU_ABI);
            cpuabis.add(Build.CPU_ABI2);
        }
        return cpuabis;
    }

    private static boolean isArmv7() {
        try {
            return Version.getCpuAbis().get(0).startsWith("armeabi-v7");
        }
        catch (Throwable e) {
            Log.e(e, new Object[0]);
            return false;
        }
    }

    private static boolean isX86() {
        try {
            return Version.getCpuAbis().get(0).startsWith("x86");
        }
        catch (Throwable e) {
            Log.e(e, new Object[0]);
            return false;
        }
    }

    private static boolean isArmv5() {
        try {
            return Version.getCpuAbis().get(0).equals("armeabi");
        }
        catch (Throwable e) {
            Log.e(e, new Object[0]);
            return false;
        }
    }

    public static boolean hasNeon() {
        if (hasNeon == null) {
            hasNeon = Version.nativeHasNeon();
        }
        return hasNeon;
    }

    public static boolean hasFastCpu() {
        return !Version.isArmv5();
    }

    public static boolean hasFastCpuWithAsmOptim() {
        return !Version.isX86() && !Version.isArmv5() && Version.hasNeon() || Version.isX86();
    }

    public static boolean isVideoCapable() {
        return !Version.sdkStrictlyBelow(5) && Version.hasFastCpu() && Hacks.hasCamera();
    }

    public static boolean isHDVideoCapable() {
        int availableCores = Runtime.getRuntime().availableProcessors();
        return Version.isVideoCapable() && Version.hasFastCpuWithAsmOptim() && availableCores > 1;
    }

    public static boolean hasZrtp() {
        if (sCacheHasZrtp == null) {
            sCacheHasZrtp = Version.nativeHasZrtp();
        }
        return sCacheHasZrtp;
    }

    public static void dumpCapabilities() {
        StringBuilder sb = new StringBuilder(" ==== Capabilities dump ====\n");
        if (Version.isArmv7()) {
            sb.append("Has neon: ").append(Boolean.toString(Version.hasNeon())).append("\n");
        }
        sb.append("Has ZRTP: ").append(Boolean.toString(Version.hasZrtp())).append("\n");
        Log.i(sb.toString());
    }

    static {
        buildVersion = Build.VERSION.SDK_INT;
    }
}

