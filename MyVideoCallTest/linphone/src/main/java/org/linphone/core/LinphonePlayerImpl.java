/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphonePlayer;

public class LinphonePlayerImpl
implements LinphonePlayer {
    private long nativePtr = 0;

    LinphonePlayerImpl(long nativePtr) {
        this.nativePtr = nativePtr;
    }

    private native int open(long var1, String var3, LinphonePlayer.Listener var4);

    public synchronized int open(String filename, LinphonePlayer.Listener listener) {
        return this.open(this.nativePtr, filename, listener);
    }

    private native int start(long var1);

    public synchronized int start() {
        return this.start(this.nativePtr);
    }

    private native int pause(long var1);

    public synchronized int pause() {
        return this.pause(this.nativePtr);
    }

    private native int seek(long var1, int var3);

    public synchronized int seek(int timeMs) {
        return this.seek(this.nativePtr, timeMs);
    }

    private native int getState(long var1);

    public synchronized LinphonePlayer.State getState() {
        return LinphonePlayer.State.fromValue(this.getState(this.nativePtr));
    }

    private native int getDuration(long var1);

    public synchronized int getDuration() {
        return this.getDuration(this.nativePtr);
    }

    private native int getCurrentPosition(long var1);

    public synchronized int getCurrentPosition() {
        return this.getCurrentPosition(this.nativePtr);
    }

    private native void close(long var1);

    public synchronized void close() {
        this.close(this.nativePtr);
    }

    private native void destroy(long var1);

    protected void finalize() {
        this.destroy(this.nativePtr);
    }
}

