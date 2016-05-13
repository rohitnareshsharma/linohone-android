/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallImpl;
import org.linphone.core.LinphoneCallStats;

class LinphoneCallStatsImpl
implements LinphoneCallStats {
    private int mediaType;
    private int iceState;
    private float downloadBandwidth;
    private float uploadBandwidth;
    private float senderLossRate;
    private float receiverLossRate;
    private float senderInterarrivalJitter;
    private float receiverInterarrivalJitter;
    private float roundTripDelay;
    private long latePacketsCumulativeNumber;
    private float jitterBufferSize;
    private float localLossRate;
    private float localLateRate;
    private long nativePtr;

    private native int getMediaType(long var1);

    private native int getIceState(long var1);

    private native float getDownloadBandwidth(long var1);

    private native float getUploadBandwidth(long var1);

    private native float getSenderLossRate(long var1);

    private native float getReceiverLossRate(long var1);

    private native float getSenderInterarrivalJitter(long var1, long var3);

    private native float getReceiverInterarrivalJitter(long var1, long var3);

    private native float getRoundTripDelay(long var1);

    private native long getLatePacketsCumulativeNumber(long var1, long var3);

    private native float getJitterBufferSize(long var1);

    private native float getLocalLossRate(long var1);

    private native float getLocalLateRate(long var1);

    private native void updateStats(long var1, int var3);

    protected LinphoneCallStatsImpl(long nativeCallPtr, long nativeStatsPtr) {
        this.nativePtr = nativeStatsPtr;
        this.mediaType = this.getMediaType(nativeStatsPtr);
        this.iceState = this.getIceState(nativeStatsPtr);
        this.downloadBandwidth = this.getDownloadBandwidth(nativeStatsPtr);
        this.uploadBandwidth = this.getUploadBandwidth(nativeStatsPtr);
        this.senderLossRate = this.getSenderLossRate(nativeStatsPtr);
        this.receiverLossRate = this.getReceiverLossRate(nativeStatsPtr);
        this.senderInterarrivalJitter = this.getSenderInterarrivalJitter(nativeStatsPtr, nativeCallPtr);
        this.receiverInterarrivalJitter = this.getReceiverInterarrivalJitter(nativeStatsPtr, nativeCallPtr);
        this.roundTripDelay = this.getRoundTripDelay(nativeStatsPtr);
        this.latePacketsCumulativeNumber = this.getLatePacketsCumulativeNumber(nativeStatsPtr, nativeCallPtr);
        this.jitterBufferSize = this.getJitterBufferSize(nativeStatsPtr);
    }

    protected void updateRealTimeStats(LinphoneCall call) {
        this.updateStats(((LinphoneCallImpl)call).nativePtr, this.mediaType);
        this.localLossRate = this.getLocalLossRate(this.nativePtr);
        this.localLateRate = this.getLocalLateRate(this.nativePtr);
    }

    public LinphoneCallStats.MediaType getMediaType() {
        return LinphoneCallStats.MediaType.fromInt(this.mediaType);
    }

    public LinphoneCallStats.IceState getIceState() {
        return LinphoneCallStats.IceState.fromInt(this.iceState);
    }

    public float getDownloadBandwidth() {
        return this.downloadBandwidth;
    }

    public float getUploadBandwidth() {
        return this.uploadBandwidth;
    }

    public float getSenderLossRate() {
        return this.senderLossRate;
    }

    public float getReceiverLossRate() {
        return this.receiverLossRate;
    }

    public float getSenderInterarrivalJitter() {
        return this.senderInterarrivalJitter;
    }

    public float getReceiverInterarrivalJitter() {
        return this.receiverInterarrivalJitter;
    }

    public float getRoundTripDelay() {
        return this.roundTripDelay;
    }

    public long getLatePacketsCumulativeNumber() {
        return this.latePacketsCumulativeNumber;
    }

    public float getJitterBufferSize() {
        return this.jitterBufferSize;
    }

    public float getLocalLossRate() {
        return this.localLossRate;
    }

    public float getLocalLateRate() {
        return this.localLateRate;
    }
}

