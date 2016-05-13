/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceActivity;
import org.linphone.core.PresenceActivityType;

public class PresenceActivityImpl
implements PresenceActivity {
    private long mNativePtr;

    protected PresenceActivityImpl(long nativePtr) {
        this.mNativePtr = nativePtr;
    }

    private native long newPresenceActivityImpl(int var1, String var2);

    protected PresenceActivityImpl(PresenceActivityType type, String description) {
        this.mNativePtr = this.newPresenceActivityImpl(type.toInt(), description);
    }

    private native void unref(long var1);

    protected void finalize() {
        this.unref(this.mNativePtr);
    }

    private native String toString(long var1);

    public String toString() {
        return this.toString(this.mNativePtr);
    }

    private native int getType(long var1);

    public PresenceActivityType getType() {
        return PresenceActivityType.fromInt(this.getType(this.mNativePtr));
    }

    private native int setType(long var1, int var3);

    public int setType(PresenceActivityType type) {
        return this.setType(this.mNativePtr, type.toInt());
    }

    private native String getDescription(long var1);

    public String getDescription() {
        return this.getDescription(this.mNativePtr);
    }

    private native int setDescription(long var1, String var3);

    public int setDescription(String description) {
        return this.setDescription(this.mNativePtr, description);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }
}

