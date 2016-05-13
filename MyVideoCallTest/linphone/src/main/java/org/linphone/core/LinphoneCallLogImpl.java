/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.CallDirection;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddressImpl;
import org.linphone.core.LinphoneCallLog;

class LinphoneCallLogImpl
implements LinphoneCallLog {
    protected final long nativePtr;

    private native long getFrom(long var1);

    private native long getTo(long var1);

    private native boolean isIncoming(long var1);

    private native int getStatus(long var1);

    private native String getStartDate(long var1);

    private native int getCallDuration(long var1);

    private native int getCallId(long var1);

    private native long getTimestamp(long var1);

    LinphoneCallLogImpl(long aNativePtr) {
        this.nativePtr = aNativePtr;
    }

    public CallDirection getDirection() {
        return this.isIncoming(this.nativePtr) ? CallDirection.Incoming : CallDirection.Outgoing;
    }

    public LinphoneAddress getFrom() {
        return new LinphoneAddressImpl(this.getFrom(this.nativePtr), LinphoneAddressImpl.WrapMode.FromExisting);
    }

    public LinphoneAddress getTo() {
        return new LinphoneAddressImpl(this.getTo(this.nativePtr), LinphoneAddressImpl.WrapMode.FromExisting);
    }

    public LinphoneCallLog.CallStatus getStatus() {
        return LinphoneCallLog.CallStatus.fromInt(this.getStatus(this.nativePtr));
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public String getStartDate() {
        return this.getStartDate(this.nativePtr);
    }

    public int getCallDuration() {
        return this.getCallDuration(this.nativePtr);
    }

    public int getCallId() {
        return this.getCallId(this.nativePtr);
    }

    public long getTimestamp() {
        return this.getTimestamp(this.nativePtr) * 1000;
    }
}

