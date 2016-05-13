/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.media.AudioManager
 *  android.os.Build
 *  android.os.Build$VERSION
 */
package org.linphone.mediastream;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.MediastreamException;

public class MediastreamerAndroidContext {
    private static final int DEVICE_CHOICE = 0;
    private static final int DEVICE_HAS_BUILTIN_AEC = 1;
    private static final int DEVICE_HAS_BUILTIN_AEC_CRAPPY = 2;
    private static final int DEVICE_USE_ANDROID_MIC = 4;
    private static final int DEVICE_HAS_BUILTIN_OPENSLES_AEC = 8;
    private static MediastreamerAndroidContext instance;

    private native void setDeviceFavoriteSampleRate(int var1);

    private native void setDeviceFavoriteBufferSize(int var1);

    private native void addSoundDeviceDescription(String var1, String var2, String var3, int var4, int var5, int var6);

    private MediastreamerAndroidContext() {
    }

    private static MediastreamerAndroidContext getInstance() {
        if (instance == null) {
            instance = new MediastreamerAndroidContext();
        }
        return instance;
    }

    public static void addSoundDeviceDesc(String manufacturer, String model, String platform, int flags, int delay, int recommended_rate) {
        MediastreamerAndroidContext.getInstance().addSoundDeviceDescription(manufacturer, model, platform, flags, delay, recommended_rate);
    }

    @TargetApi(value=19)
    public static void setContext(Object c) {
        if (c == null) {
            return;
        }
        Context context = (Context)c;
        int bufferSize = 64;
        int sampleRate = 44100;
        MediastreamerAndroidContext mac = MediastreamerAndroidContext.getInstance();
        if (Build.VERSION.SDK_INT >= 19) {
            AudioManager audiomanager = (AudioManager)context.getSystemService("audio");
            String bufferProperty = audiomanager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
            bufferSize = MediastreamerAndroidContext.parseInt(bufferProperty, bufferSize);
            String sampleRateProperty = audiomanager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            sampleRate = MediastreamerAndroidContext.parseInt(sampleRateProperty, sampleRate);
            Log.i("Setting buffer size to " + bufferSize + " and sample rate to " + sampleRate + " for OpenSLES MS sound card.");
            mac.setDeviceFavoriteSampleRate(sampleRate);
            mac.setDeviceFavoriteBufferSize(bufferSize);
        } else {
            Log.i("Android < 4.4 detected, android context not used.");
        }
    }

    private static int parseInt(String value, int defaultValue) {
        int returnedValue = defaultValue;
        try {
            returnedValue = Integer.parseInt(value);
        }
        catch (NumberFormatException nfe) {
            Log.e("Can't parse " + value + " to integer ; using default value " + defaultValue);
        }
        return returnedValue;
    }

    private native int enableFilterFromNameImpl(String var1, boolean var2);

    private native boolean filterFromNameEnabledImpl(String var1);

    public static void enableFilterFromName(String name, boolean enable) throws MediastreamException {
        if (MediastreamerAndroidContext.getInstance().enableFilterFromNameImpl(name, enable) != 0) {
            throw new MediastreamException("Cannot " + (enable ? "enable" : "disable") + " filter  name [" + name + "]");
        }
    }

    public static boolean filterFromNameEnabled(String name) {
        return MediastreamerAndroidContext.getInstance().filterFromNameEnabledImpl(name);
    }
}

