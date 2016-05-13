/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.CallDirection;
import org.linphone.core.ErrorInfo;
import org.linphone.core.ErrorInfoImpl;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddressImpl;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallLog;
import org.linphone.core.LinphoneCallLogImpl;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCallParamsImpl;
import org.linphone.core.LinphoneCallStats;
import org.linphone.core.LinphoneCallStatsImpl;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphoneInfoMessageImpl;
import org.linphone.core.LinphonePlayer;
import org.linphone.core.LinphonePlayerImpl;
import org.linphone.core.Reason;

class LinphoneCallImpl
implements LinphoneCall {
    protected final long nativePtr;
    boolean ownPtr = false;
    Object userData;
    private LinphoneCallStats audioStats;
    private LinphoneCallStats videoStats;

    private native void finalize(long var1);

    private native long getCallLog(long var1);

    private native boolean isIncoming(long var1);

    private native long getRemoteAddress(long var1);

    private native int getState(long var1);

    private native long getCurrentParamsCopy(long var1);

    private native long getRemoteParams(long var1);

    private native void enableCamera(long var1, boolean var3);

    private native boolean cameraEnabled(long var1);

    private native void enableEchoCancellation(long var1, boolean var3);

    private native boolean isEchoCancellationEnabled(long var1);

    private native void enableEchoLimiter(long var1, boolean var3);

    private native boolean isEchoLimiterEnabled(long var1);

    private native Object getReplacedCall(long var1);

    private native int getDuration(long var1);

    private native float getCurrentQuality(long var1);

    private native float getAverageQuality(long var1);

    private native boolean mediaInProgress(long var1);

    private LinphoneCallImpl(long aNativePtr) {
        this.nativePtr = aNativePtr;
    }

    protected void finalize() throws Throwable {
        this.finalize(this.nativePtr);
    }

    public LinphoneCallLog getCallLog() {
        long lNativePtr = this.getCallLog(this.nativePtr);
        if (lNativePtr != 0) {
            return new LinphoneCallLogImpl(lNativePtr);
        }
        return null;
    }

    public void setAudioStats(LinphoneCallStats stats) {
        this.audioStats = stats;
    }

    public void setVideoStats(LinphoneCallStats stats) {
        this.videoStats = stats;
    }

    public LinphoneCallStats getAudioStats() {
        if (this.audioStats != null) {
            ((LinphoneCallStatsImpl)this.audioStats).updateRealTimeStats(this);
        }
        return this.audioStats;
    }

    public LinphoneCallStats getVideoStats() {
        if (this.videoStats != null) {
            ((LinphoneCallStatsImpl)this.videoStats).updateRealTimeStats(this);
        }
        return this.videoStats;
    }

    public CallDirection getDirection() {
        return this.isIncoming(this.nativePtr) ? CallDirection.Incoming : CallDirection.Outgoing;
    }

    public LinphoneAddress getRemoteAddress() {
        long lNativePtr = this.getRemoteAddress(this.nativePtr);
        if (lNativePtr != 0) {
            return new LinphoneAddressImpl(lNativePtr, LinphoneAddressImpl.WrapMode.FromConst);
        }
        return null;
    }

    public LinphoneCall.State getState() {
        return LinphoneCall.State.fromInt(this.getState(this.nativePtr));
    }

    public LinphoneCallParams getCurrentParamsCopy() {
        return new LinphoneCallParamsImpl(this.getCurrentParamsCopy(this.nativePtr));
    }

    public LinphoneCallParams getRemoteParams() {
        long remoteParamsPtr = this.getRemoteParams(this.nativePtr);
        if (remoteParamsPtr == 0) {
            return null;
        }
        return new LinphoneCallParamsImpl(remoteParamsPtr);
    }

    public void enableCamera(boolean enabled) {
        this.enableCamera(this.nativePtr, enabled);
    }

    public boolean cameraEnabled() {
        return this.cameraEnabled(this.nativePtr);
    }

    public boolean equals(Object call) {
        if (this == call) {
            return true;
        }
        if (call == null) {
            return false;
        }
        if (!(call instanceof LinphoneCallImpl)) {
            return false;
        }
        return this.nativePtr == ((LinphoneCallImpl)call).nativePtr;
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + (int)(this.nativePtr ^ this.nativePtr >>> 32);
        return result;
    }

    public void enableEchoCancellation(boolean enable) {
        this.enableEchoCancellation(this.nativePtr, enable);
    }

    public boolean isEchoCancellationEnabled() {
        return this.isEchoCancellationEnabled(this.nativePtr);
    }

    public void enableEchoLimiter(boolean enable) {
        this.enableEchoLimiter(this.nativePtr, enable);
    }

    public boolean isEchoLimiterEnabled() {
        return this.isEchoLimiterEnabled(this.nativePtr);
    }

    public LinphoneCall getReplacedCall() {
        return (LinphoneCall)this.getReplacedCall(this.nativePtr);
    }

    public int getDuration() {
        return this.getDuration(this.nativePtr);
    }

    public float getAverageQuality() {
        return this.getAverageQuality(this.nativePtr);
    }

    public float getCurrentQuality() {
        return this.getCurrentQuality(this.nativePtr);
    }

    private native String getAuthenticationToken(long var1);

    public String getAuthenticationToken() {
        return this.getAuthenticationToken(this.nativePtr);
    }

    private native boolean isAuthenticationTokenVerified(long var1);

    public boolean isAuthenticationTokenVerified() {
        return this.isAuthenticationTokenVerified(this.nativePtr);
    }

    private native void setAuthenticationTokenVerified(long var1, boolean var3);

    public void setAuthenticationTokenVerified(boolean verified) {
        this.setAuthenticationTokenVerified(this.nativePtr, verified);
    }

    public boolean isInConference() {
        LinphoneCallParamsImpl params = new LinphoneCallParamsImpl(this.getCurrentParamsCopy(this.nativePtr));
        return params.localConferenceMode();
    }

    public boolean mediaInProgress() {
        return this.mediaInProgress(this.nativePtr);
    }

    public String toString() {
        return "Call " + this.nativePtr;
    }

    private native float getPlayVolume(long var1);

    public float getPlayVolume() {
        return this.getPlayVolume(this.nativePtr);
    }

    private native String getRemoteUserAgent(long var1);

    public String getRemoteUserAgent() {
        return this.getRemoteUserAgent(this.nativePtr);
    }

    private native String getRemoteContact(long var1);

    public String getRemoteContact() {
        return this.getRemoteContact(this.nativePtr);
    }

    private native void takeSnapshot(long var1, String var3);

    public void takeSnapshot(String path) {
        this.takeSnapshot(this.nativePtr, path);
    }

    private native void zoomVideo(long var1, float var3, float var4, float var5);

    public void zoomVideo(float factor, float cx, float cy) {
        this.zoomVideo(this.nativePtr, factor, cx, cy);
    }

    private native void startRecording(long var1);

    public void startRecording() {
        this.startRecording(this.nativePtr);
    }

    private native void stopRecording(long var1);

    public void stopRecording() {
        this.stopRecording(this.nativePtr);
    }

    private native int getTransferState(long var1);

    public LinphoneCall.State getTransferState() {
        return LinphoneCall.State.fromInt(this.getTransferState(this.nativePtr));
    }

    private native int sendInfoMessage(long var1, long var3);

    public void sendInfoMessage(LinphoneInfoMessage msg) {
        this.sendInfoMessage(this.nativePtr, ((LinphoneInfoMessageImpl)msg).nativePtr);
    }

    private native Object getTransfererCall(long var1);

    public LinphoneCall getTransfererCall() {
        return (LinphoneCall)this.getTransfererCall(this.nativePtr);
    }

    private native Object getTransferTargetCall(long var1);

    public LinphoneCall getTransferTargetCall() {
        return (LinphoneCall)this.getTransferTargetCall(this.nativePtr);
    }

    public Reason getReason() {
        return null;
    }

    private native long getErrorInfo(long var1);

    public ErrorInfo getErrorInfo() {
        return new ErrorInfoImpl(this.getErrorInfo(this.nativePtr));
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }

    private native long getPlayer(long var1);

    public LinphonePlayer getPlayer() {
        return new LinphonePlayerImpl(this.getPlayer(this.nativePtr));
    }
}

