/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.tools;

import org.linphone.core.LpConfig;
import org.linphone.mediastream.Log;

public class Xml2Lpc {
    private static boolean mAvailable;
    private long internalPtr = 0;

    private native void init();

    private native void destroy();

    public Xml2Lpc() {
        this.init();
    }

    public void finalize() {
        this.destroy();
    }

    public native int setXmlFile(String var1);

    public native int setXmlString(String var1);

    public native int setXsdFile(String var1);

    public native int setXsdString(String var1);

    public native int validate();

    public native int convert(LpConfig var1);

    public void printLog(int level, String message) {
        if (level > 0 && level < LogLevel.values().length) {
            switch (LogLevel.values()[level]) {
                case DEBUG: {
                    Log.d(message);
                    break;
                }
                case MESSAGE: {
                    Log.i(message);
                    break;
                }
                case WARNING: {
                    Log.w(message);
                    break;
                }
                case ERROR: {
                    Log.e(message);
                }
            }
        }
    }

    public static boolean isAvailable() {
        return mAvailable;
    }

    static {
        try {
            new Xml2Lpc();
            mAvailable = true;
        }
        catch (Throwable e) {
            mAvailable = false;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static enum LogLevel {
        DEBUG,
        MESSAGE,
        WARNING,
        ERROR;
        

        private LogLevel() {
        }
    }

}

