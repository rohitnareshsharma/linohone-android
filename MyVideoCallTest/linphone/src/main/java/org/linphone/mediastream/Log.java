/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.util.Log
 */
package org.linphone.mediastream;

public final class Log {
    public static String TAG = "Linphone";
    private static final boolean useIsLoggable = false;
    private static boolean isLogEnabled = true;

    public Log(String tag, boolean enable) {
        TAG = tag;
        isLogEnabled = enable;
    }

    private static boolean isLoggable(int level) {
        return isLogEnabled;
    }

    public static /* varargs */ void i(Object ... objects) {
        if (Log.isLoggable(4)) {
            android.util.Log.i((String)TAG, (String)Log.toString(objects));
        }
    }

    public static /* varargs */ void i(Throwable t, Object ... objects) {
        if (Log.isLoggable(4)) {
            android.util.Log.i((String)TAG, (String)Log.toString(objects), (Throwable)t);
        }
    }

    public static /* varargs */ void d(Object ... objects) {
        if (Log.isLoggable(3)) {
            android.util.Log.d((String)TAG, (String)Log.toString(objects));
        }
    }

    public static /* varargs */ void d(Throwable t, Object ... objects) {
        if (Log.isLoggable(3)) {
            android.util.Log.d((String)TAG, (String)Log.toString(objects), (Throwable)t);
        }
    }

    public static /* varargs */ void w(Object ... objects) {
        if (Log.isLoggable(5)) {
            android.util.Log.w((String)TAG, (String)Log.toString(objects));
        }
    }

    public static /* varargs */ void w(Throwable t, Object ... objects) {
        if (Log.isLoggable(5)) {
            android.util.Log.w((String)TAG, (String)Log.toString(objects), (Throwable)t);
        }
    }

    public static /* varargs */ void e(Object ... objects) {
        if (Log.isLoggable(6)) {
            android.util.Log.e((String)TAG, (String)Log.toString(objects));
        }
    }

    public static /* varargs */ void e(Throwable t, Object ... objects) {
        if (Log.isLoggable(6)) {
            android.util.Log.e((String)TAG, (String)Log.toString(objects), (Throwable)t);
        }
    }

    public static /* varargs */ void f(Object ... objects) {
        if (Log.isLoggable(6)) {
            android.util.Log.e((String)TAG, (String)Log.toString(objects));
            throw new RuntimeException("Fatal error : " + Log.toString(objects));
        }
    }

    public static /* varargs */ void f(Throwable t, Object ... objects) {
        if (Log.isLoggable(6)) {
            android.util.Log.e((String)TAG, (String)Log.toString(objects), (Throwable)t);
            throw new RuntimeException("Fatal error : " + Log.toString(objects), t);
        }
    }

    private static /* varargs */ String toString(Object ... objects) {
        StringBuilder sb = new StringBuilder();
        for (Object o : objects) {
            sb.append(o);
        }
        return sb.toString();
    }
}

