/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;
import org.linphone.core.PayloadType;
import org.linphone.core.PayloadTypeImpl;
import org.linphone.core.VideoSize;

public class LinphoneCallParamsImpl
implements LinphoneCallParams {
    protected final long nativePtr;

    public LinphoneCallParamsImpl(long nativePtr) {
        this.nativePtr = nativePtr;
    }

    private native void enableVideo(long var1, boolean var3);

    private native boolean getVideoEnabled(long var1);

    private native void audioBandwidth(long var1, int var3);

    private native void setMediaEncryption(long var1, int var3);

    private native int getMediaEncryption(long var1);

    private native long getUsedAudioCodec(long var1);

    private native long getUsedVideoCodec(long var1);

    private native void destroy(long var1);

    private native void enableLowBandwidth(long var1, boolean var3);

    private native boolean isLowBandwidthEnabled(long var1);

    public boolean getVideoEnabled() {
        return this.getVideoEnabled(this.nativePtr);
    }

    public void setVideoEnabled(boolean b) {
        this.enableVideo(this.nativePtr, b);
    }

    protected void finalize() throws Throwable {
        this.destroy(this.nativePtr);
        super.finalize();
    }

    public void setAudioBandwidth(int value) {
        this.audioBandwidth(this.nativePtr, value);
    }

    public LinphoneCore.MediaEncryption getMediaEncryption() {
        return LinphoneCore.MediaEncryption.fromInt(this.getMediaEncryption(this.nativePtr));
    }

    public void setMediaEnctyption(LinphoneCore.MediaEncryption menc) {
        this.setMediaEncryption(this.nativePtr, menc.mValue);
    }

    public PayloadType getUsedAudioCodec() {
        long ptr = this.getUsedAudioCodec(this.nativePtr);
        if (ptr == 0) {
            return null;
        }
        return new PayloadTypeImpl(ptr);
    }

    public PayloadType getUsedVideoCodec() {
        long ptr = this.getUsedVideoCodec(this.nativePtr);
        if (ptr == 0) {
            return null;
        }
        return new PayloadTypeImpl(ptr);
    }

    private native boolean localConferenceMode(long var1);

    public boolean localConferenceMode() {
        return this.localConferenceMode(this.nativePtr);
    }

    public void enableLowBandwidth(boolean enable) {
        this.enableLowBandwidth(this.nativePtr, enable);
    }

    public boolean isLowBandwidthEnabled() {
        return this.isLowBandwidthEnabled(this.nativePtr);
    }

    private native void setRecordFile(long var1, String var3);

    public void setRecordFile(String path) {
        this.setRecordFile(this.nativePtr, path);
    }

    private native void addCustomHeader(long var1, String var3, String var4);

    public void addCustomHeader(String name, String value) {
        this.addCustomHeader(this.nativePtr, name, value);
    }

    private native String getCustomHeader(long var1, String var3);

    public String getCustomHeader(String name) {
        return this.getCustomHeader(this.nativePtr, name);
    }

    private native void setPrivacy(long var1, int var3);

    public void setPrivacy(int privacy_mask) {
        this.setPrivacy(this.nativePtr, privacy_mask);
    }

    private native int getPrivacy(long var1);

    public int getPrivacy() {
        return this.getPrivacy(this.nativePtr);
    }

    private native void setSessionName(long var1, String var3);

    public void setSessionName(String name) {
        this.setSessionName(this.nativePtr, name);
    }

    private native String getSessionName(long var1);

    public String getSessionName() {
        return this.getSessionName(this.nativePtr);
    }

    private native int[] getSentVideoSize(long var1);

    public VideoSize getSentVideoSize() {
        int[] nativeSize = this.getSentVideoSize(this.nativePtr);
        VideoSize vSize = new VideoSize();
        vSize.width = nativeSize[0];
        vSize.height = nativeSize[1];
        return vSize;
    }

    private native int[] getReceivedVideoSize(long var1);

    public VideoSize getReceivedVideoSize() {
        int[] nativeSize = this.getReceivedVideoSize(this.nativePtr);
        VideoSize vSize = new VideoSize();
        vSize.width = nativeSize[0];
        vSize.height = nativeSize[1];
        return vSize;
    }

    private native void enableAudioMulticast(long var1, boolean var3);

    public void enableAudioMulticast(boolean yesno) {
        this.enableAudioMulticast(this.nativePtr, yesno);
    }

    private native boolean audioMulticastEnabled(long var1);

    public boolean audioMulticastEnabled() {
        return this.audioMulticastEnabled(this.nativePtr);
    }

    private native void enableVideoMulticast(long var1, boolean var3);

    public void enableVideoMulticast(boolean yesno) {
        this.enableVideoMulticast(this.nativePtr, yesno);
    }

    private native boolean videoMulticastEnabled(long var1);

    public boolean videoMulticastEnabled() {
        return this.videoMulticastEnabled(this.nativePtr);
    }
}

