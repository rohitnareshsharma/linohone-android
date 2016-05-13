/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LpConfig;

class LpConfigImpl
implements LpConfig {
    private final long nativePtr;
    boolean ownPtr = false;

    public LpConfigImpl(long ptr) {
        this.nativePtr = ptr;
    }

    private native long newLpConfigImpl(String var1);

    private native void delete(long var1);

    public LpConfigImpl(String file) {
        this.nativePtr = this.newLpConfigImpl(file);
        this.ownPtr = true;
    }

    protected void finalize() throws Throwable {
        if (this.ownPtr) {
            this.delete(this.nativePtr);
        }
    }

    private native void sync(long var1);

    public void sync() {
        this.sync(this.nativePtr);
    }

    private native void setInt(long var1, String var3, String var4, int var5);

    public void setInt(String section, String key, int value) {
        this.setInt(this.nativePtr, section, key, value);
    }

    private native void setFloat(long var1, String var3, String var4, float var5);

    public void setFloat(String section, String key, float value) {
        this.setFloat(this.nativePtr, section, key, value);
    }

    private native void setBool(long var1, String var3, String var4, boolean var5);

    public void setBool(String section, String key, boolean value) {
        this.setBool(this.nativePtr, section, key, value);
    }

    private native void setString(long var1, String var3, String var4, String var5);

    public void setString(String section, String key, String value) {
        this.setString(this.nativePtr, section, key, value);
    }

    private native void setIntRange(long var1, String var3, String var4, int var5, int var6);

    public void setIntRange(String section, String key, int min, int max) {
        this.setIntRange(this.nativePtr, section, key, min, max);
    }

    private native int getInt(long var1, String var3, String var4, int var5);

    public int getInt(String section, String key, int defaultValue) {
        return this.getInt(this.nativePtr, section, key, defaultValue);
    }

    private native float getFloat(long var1, String var3, String var4, float var5);

    public float getFloat(String section, String key, float defaultValue) {
        return this.getFloat(this.nativePtr, section, key, defaultValue);
    }

    private native boolean getBool(long var1, String var3, String var4, boolean var5);

    public boolean getBool(String section, String key, boolean defaultValue) {
        return this.getBool(this.nativePtr, section, key, defaultValue);
    }

    private native String getString(long var1, String var3, String var4, String var5);

    public String getString(String section, String key, String defaultValue) {
        return this.getString(this.nativePtr, section, key, defaultValue);
    }

    private native int[] getIntRange(long var1, String var3, String var4, int var5, int var6);

    public int[] getIntRange(String section, String key, int defaultMin, int defaultMax) {
        return this.getIntRange(this.nativePtr, section, key, defaultMin, defaultMax);
    }
}

