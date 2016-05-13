/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PayloadType;

class PayloadTypeImpl
implements PayloadType {
    public final long nativePtr;

    private native String toString(long var1);

    private native String getMime(long var1);

    private native int getRate(long var1);

    protected PayloadTypeImpl(long aNativePtr) {
        this.nativePtr = aNativePtr;
    }

    public int getRate() {
        return this.getRate(this.nativePtr);
    }

    public String getMime() {
        return this.getMime(this.nativePtr);
    }

    public String toString() {
        return this.toString(this.nativePtr);
    }

    private native void setRecvFmtp(long var1, String var3);

    public void setRecvFmtp(String fmtp) {
        this.setRecvFmtp(this.nativePtr, fmtp);
    }

    private native String getRecvFmtp(long var1);

    public String getRecvFmtp() {
        return this.getRecvFmtp(this.nativePtr);
    }

    private native void setSendFmtp(long var1, String var3);

    public void setSendFmtp(String fmtp) {
        this.setSendFmtp(this.nativePtr, fmtp);
    }

    private native String getSendFmtp(long var1);

    public String getSendFmtp() {
        return this.getSendFmtp(this.nativePtr);
    }
}

