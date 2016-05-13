/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.ErrorInfo;
import org.linphone.core.Reason;

public class ErrorInfoImpl
implements ErrorInfo {
    private Reason mReason;
    private int mCode;
    private String mPhrase;
    private String mDetails;

    private native int getReason(long var1);

    private native int getProtocolCode(long var1);

    private native String getPhrase(long var1);

    private native String getDetails(long var1);

    public ErrorInfoImpl(long nativePtr) {
        this.mReason = Reason.fromInt(this.getReason(nativePtr));
        this.mCode = this.getProtocolCode(nativePtr);
        this.mPhrase = this.getPhrase(nativePtr);
        this.mDetails = this.getDetails(nativePtr);
    }

    public Reason getReason() {
        return this.mReason;
    }

    public int getProtocolCode() {
        return this.mCode;
    }

    public String getPhrase() {
        return this.mPhrase;
    }

    public String getDetails() {
        return this.mDetails;
    }
}

