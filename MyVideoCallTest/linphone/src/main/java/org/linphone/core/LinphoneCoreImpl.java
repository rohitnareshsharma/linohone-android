/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.AudioManager
 *  android.net.wifi.WifiManager
 *  android.net.wifi.WifiManager$MulticastLock
 *  android.net.wifi.WifiManager$WifiLock
 */
package org.linphone.core;

import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import java.io.File;
import java.io.IOException;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddressImpl;
import org.linphone.core.LinphoneAuthInfo;
import org.linphone.core.LinphoneAuthInfoImpl;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallImpl;
import org.linphone.core.LinphoneCallLog;
import org.linphone.core.LinphoneCallLogImpl;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCallParamsImpl;
import org.linphone.core.LinphoneChatRoom;
import org.linphone.core.LinphoneChatRoomImpl;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneEvent;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneFriendImpl;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphoneInfoMessageImpl;
import org.linphone.core.LinphonePlayer;
import org.linphone.core.LinphonePlayerImpl;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.LinphoneProxyConfigImpl;
import org.linphone.core.LpConfig;
import org.linphone.core.LpConfigImpl;
import org.linphone.core.OnlineStatus;
import org.linphone.core.PayloadType;
import org.linphone.core.PayloadTypeImpl;
import org.linphone.core.PresenceModel;
import org.linphone.core.PresenceModelImpl;
import org.linphone.core.Reason;
import org.linphone.core.ToneID;
import org.linphone.core.TunnelConfig;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;
import org.linphone.mediastream.video.AndroidVideoWindowImpl;
import org.linphone.mediastream.video.capture.hwconf.Hacks;

class LinphoneCoreImpl
implements LinphoneCore {
    private final LinphoneCoreListener mListener;
    protected long nativePtr = 0;
    private Context mContext = null;
    private AudioManager mAudioManager = null;
    private boolean mSpeakerEnabled = false;
    static int FIND_PAYLOAD_IGNORE_RATE = -1;
    static int FIND_PAYLOAD_IGNORE_CHANNELS = -1;

    private native long newLinphoneCore(LinphoneCoreListener var1, String var2, String var3, Object var4);

    private native void iterate(long var1);

    private native LinphoneProxyConfig getDefaultProxyConfig(long var1);

    private native void setDefaultProxyConfig(long var1, long var3);

    private native int addProxyConfig(LinphoneProxyConfig var1, long var2, long var4);

    private native void removeProxyConfig(long var1, long var3);

    private native void clearAuthInfos(long var1);

    private native void clearProxyConfigs(long var1);

    private native void addAuthInfo(long var1, long var3);

    private native void removeAuthInfo(long var1, long var3);

    private native Object invite(long var1, String var3);

    private native void terminateCall(long var1, long var3);

    private native long getRemoteAddress(long var1);

    private native boolean isInCall(long var1);

    private native boolean isInComingInvitePending(long var1);

    private native void acceptCall(long var1, long var3);

    private native long getCallLog(long var1, int var3);

    private native int getNumberOfCallLogs(long var1);

    private native void delete(long var1);

    private native void setNetworkStateReachable(long var1, boolean var3);

    private native boolean isNetworkStateReachable(long var1);

    private native void setPlaybackGain(long var1, float var3);

    private native float getPlaybackGain(long var1);

    private native void muteMic(long var1, boolean var3);

    private native long interpretUrl(long var1, String var3);

    private native Object inviteAddress(long var1, long var3);

    private native Object inviteAddressWithParams(long var1, long var3, long var5);

    private native void sendDtmf(long var1, char var3);

    private native void clearCallLogs(long var1);

    private native boolean isMicMuted(long var1);

    private native long findPayloadType(long var1, String var3, int var4, int var5);

    private native int enablePayloadType(long var1, long var3, boolean var5);

    private native boolean isPayloadTypeEnabled(long var1, long var3);

    private native boolean payloadTypeIsVbr(long var1, long var3);

    private native void enableAdaptiveRateControl(long var1, boolean var3);

    private native boolean isAdaptiveRateControlEnabled(long var1);

    private native String getAdaptiveRateAlgorithm(long var1);

    private native void setAdaptiveRateAlgorithm(long var1, String var3);

    private native void enableEchoCancellation(long var1, boolean var3);

    private native boolean isEchoCancellationEnabled(long var1);

    private native Object getCurrentCall(long var1);

    private native void playDtmf(long var1, char var3, int var4);

    private native void stopDtmf(long var1);

    private native void setVideoWindowId(long var1, Object var3);

    private native void setPreviewWindowId(long var1, Object var3);

    private native void setDeviceRotation(long var1, int var3);

    private native void addFriend(long var1, long var3);

    private native LinphoneFriend[] getFriendList(long var1);

    private native void setPresenceInfo(long var1, int var3, String var4, int var5);

    private native int getPresenceInfo(long var1);

    private native void setPresenceModel(long var1, long var3);

    private native Object getPresenceModel(long var1);

    private native long getOrCreateChatRoom(long var1, String var3);

    private native void enableVideo(long var1, boolean var3, boolean var4);

    private native boolean isVideoEnabled(long var1);

    private native boolean isVideoSupported(long var1);

    private native void setFirewallPolicy(long var1, int var3);

    private native int getFirewallPolicy(long var1);

    private native void setStunServer(long var1, String var3);

    private native String getStunServer(long var1);

    private native long createDefaultCallParams(long var1);

    private native int updateCall(long var1, long var3, long var5);

    private native int getUploadBandwidth(long var1);

    private native void setUploadBandwidth(long var1, int var3);

    private native int getDownloadBandwidth(long var1);

    private native void setDownloadBandwidth(long var1, int var3);

    private native void setPreferredVideoSize(long var1, int var3, int var4);

    private native void setPreferredVideoSizeByName(long var1, String var3);

    private native int[] getPreferredVideoSize(long var1);

    private native void setRing(long var1, String var3);

    private native String getRing(long var1);

    private native void setRootCA(long var1, String var3);

    private native void setRingback(long var1, String var3);

    private native long[] listVideoPayloadTypes(long var1);

    private native void setVideoCodecs(long var1, long[] var3);

    private native LinphoneProxyConfig[] getProxyConfigList(long var1);

    private native long[] getAuthInfosList(long var1);

    private native long findAuthInfos(long var1, String var3, String var4, String var5);

    private native long[] listAudioPayloadTypes(long var1);

    private native void setAudioCodecs(long var1, long[] var3);

    private native void enableKeepAlive(long var1, boolean var3);

    private native boolean isKeepAliveEnabled(long var1);

    private native int startEchoCalibration(long var1, Object var3);

    private native int getSignalingTransportPort(long var1, int var3);

    private native void setSignalingTransportPorts(long var1, int var3, int var4, int var5);

    private native void enableIpv6(long var1, boolean var3);

    private native boolean isIpv6Enabled(long var1);

    private native int pauseCall(long var1, long var3);

    private native int pauseAllCalls(long var1);

    private native int resumeCall(long var1, long var3);

    private native void setUploadPtime(long var1, int var3);

    private native void setDownloadPtime(long var1, int var3);

    private native void setZrtpSecretsCache(long var1, String var3);

    private native void enableEchoLimiter(long var1, boolean var3);

    private native int setVideoDevice(long var1, int var3);

    private native int getVideoDevice(long var1);

    private native int getMediaEncryption(long var1);

    private native void setMediaEncryption(long var1, int var3);

    private native boolean isMediaEncryptionMandatory(long var1);

    private native void setMediaEncryptionMandatory(long var1, boolean var3);

    private native void removeCallLog(long var1, long var3);

    private native int getMissedCallsCount(long var1);

    private native void resetMissedCallsCount(long var1);

    private native String getVersion(long var1);

    private native void setAudioPort(long var1, int var3);

    private native void setVideoPort(long var1, int var3);

    private native void setAudioPortRange(long var1, int var3, int var4);

    private native void setVideoPortRange(long var1, int var3, int var4);

    private native void setIncomingTimeout(long var1, int var3);

    private native void setInCallTimeout(long var1, int var3);

    private native void setPrimaryContact2(long var1, String var3);

    private native String getPrimaryContact(long var1);

    private native void setPrimaryContact(long var1, String var3, String var4);

    private native String getPrimaryContactUsername(long var1);

    private native String getPrimaryContactDisplayName(long var1);

    private native void setChatDatabasePath(long var1, String var3);

    private native long[] getChatRooms(long var1);

    private native int migrateToMultiTransport(long var1);

    private native void setCallErrorTone(long var1, int var3, String var4);

    private native void enableSdp200Ack(long var1, boolean var3);

    private native boolean isSdp200AckEnabled(long var1);

    private native void stopRinging(long var1);

    private static native void setAndroidPowerManager(Object var0);

    private native void setAndroidWifiLock(long var1, Object var3);

    private native void setAndroidMulticastLock(long var1, Object var3);

    LinphoneCoreImpl(LinphoneCoreListener listener, File userConfig, File factoryConfig, Object userdata) throws IOException {
        this.mListener = listener;
        String user = userConfig == null ? null : userConfig.getCanonicalPath();
        String factory = factoryConfig == null ? null : factoryConfig.getCanonicalPath();
        this.nativePtr = this.newLinphoneCore(listener, user, factory, userdata);
    }

    LinphoneCoreImpl(LinphoneCoreListener listener) throws IOException {
        this.mListener = listener;
        this.nativePtr = this.newLinphoneCore(listener, null, null, null);
    }

    protected void finalize() throws Throwable {
        if (this.nativePtr != 0) {
            this.destroy();
        }
    }

    private boolean contextInitialized() {
        if (this.mContext == null) {
            Log.e("Context of LinphoneCore has not been initialized, call setContext() after creating LinphoneCore.");
            return false;
        }
        return true;
    }

    public void setContext(Object context) {
        WifiManager.WifiLock lock;
        WifiManager.MulticastLock multicastLock;
        WifiManager wifiManager;
        this.mContext = (Context)context;
        this.mAudioManager = (AudioManager)this.mContext.getSystemService(Context.AUDIO_SERVICE);
        LinphoneCoreImpl.setAndroidPowerManager(this.mContext.getSystemService(Context.POWER_SERVICE));
        if (Version.sdkAboveOrEqual(12)) {
            wifiManager = (WifiManager)this.mContext.getSystemService(Context.WIFI_SERVICE);
            lock = wifiManager.createWifiLock(3, "linphonecore [" + this.nativePtr + "] wifi-lock");
            lock.setReferenceCounted(true);
            this.setAndroidWifiLock(this.nativePtr, (Object)lock);
        }
        if (Version.sdkAboveOrEqual(14)) {
            wifiManager = (WifiManager)this.mContext.getSystemService(Context.WIFI_SERVICE);
            multicastLock = wifiManager.createMulticastLock("linphonecore [" + this.nativePtr + "] multicast-lock");
            multicastLock.setReferenceCounted(true);
            this.setAndroidMulticastLock(this.nativePtr, (Object)multicastLock);
        }
    }

    public synchronized void addAuthInfo(LinphoneAuthInfo info) {
        this.isValid();
        this.addAuthInfo(this.nativePtr, ((LinphoneAuthInfoImpl)info).nativePtr);
    }

    public synchronized void removeAuthInfo(LinphoneAuthInfo info) {
        this.isValid();
        this.removeAuthInfo(this.nativePtr, ((LinphoneAuthInfoImpl)info).nativePtr);
    }

    public synchronized LinphoneProxyConfig getDefaultProxyConfig() {
        this.isValid();
        return this.getDefaultProxyConfig(this.nativePtr);
    }

    public synchronized LinphoneCall invite(String uri) {
        this.isValid();
        return (LinphoneCall)this.invite(this.nativePtr, uri);
    }

    public synchronized void iterate() {
        this.isValid();
        this.iterate(this.nativePtr);
    }

    public synchronized void setDefaultProxyConfig(LinphoneProxyConfig proxyCfg) {
        this.isValid();
        long proxyPtr = proxyCfg != null ? ((LinphoneProxyConfigImpl)proxyCfg).nativePtr : 0;
        this.setDefaultProxyConfig(this.nativePtr, proxyPtr);
    }

    public synchronized void addProxyConfig(LinphoneProxyConfig proxyCfg) throws LinphoneCoreException {
        this.isValid();
        if (this.addProxyConfig(proxyCfg, this.nativePtr, ((LinphoneProxyConfigImpl)proxyCfg).nativePtr) != 0) {
            throw new LinphoneCoreException("bad proxy config");
        }
        ((LinphoneProxyConfigImpl)proxyCfg).mCore = this;
    }

    public synchronized void removeProxyConfig(LinphoneProxyConfig proxyCfg) {
        this.isValid();
        this.removeProxyConfig(this.nativePtr, ((LinphoneProxyConfigImpl)proxyCfg).nativePtr);
    }

    public synchronized void clearAuthInfos() {
        this.isValid();
        this.clearAuthInfos(this.nativePtr);
    }

    public synchronized void clearProxyConfigs() {
        this.isValid();
        this.clearProxyConfigs(this.nativePtr);
    }

    public synchronized void terminateCall(LinphoneCall aCall) {
        this.isValid();
        if (aCall != null) {
            this.terminateCall(this.nativePtr, ((LinphoneCallImpl)aCall).nativePtr);
        }
    }

    public synchronized LinphoneAddress getRemoteAddress() {
        this.isValid();
        long ptr = this.getRemoteAddress(this.nativePtr);
        if (ptr == 0) {
            return null;
        }
        return new LinphoneAddressImpl(ptr, LinphoneAddressImpl.WrapMode.FromConst);
    }

    public synchronized boolean isIncall() {
        this.isValid();
        return this.isInCall(this.nativePtr);
    }

    public synchronized boolean isInComingInvitePending() {
        this.isValid();
        return this.isInComingInvitePending(this.nativePtr);
    }

    public synchronized void acceptCall(LinphoneCall aCall) {
        this.isValid();
        this.acceptCall(this.nativePtr, ((LinphoneCallImpl)aCall).nativePtr);
    }

    public synchronized LinphoneCallLog[] getCallLogs() {
        this.isValid();
        LinphoneCallLog[] logs = new LinphoneCallLog[this.getNumberOfCallLogs(this.nativePtr)];
        for (int i = 0; i < this.getNumberOfCallLogs(this.nativePtr); ++i) {
            logs[i] = new LinphoneCallLogImpl(this.getCallLog(this.nativePtr, i));
        }
        return logs;
    }

    public synchronized void destroy() {
        LinphoneCoreImpl.setAndroidPowerManager(null);
        this.delete(this.nativePtr);
        this.nativePtr = 0;
    }

    private void isValid() {
        if (this.nativePtr == 0) {
            throw new RuntimeException("object already destroyed");
        }
    }

    public synchronized void setNetworkReachable(boolean isReachable) {
        this.setNetworkStateReachable(this.nativePtr, isReachable);
    }

    public synchronized void setPlaybackGain(float gain) {
        this.setPlaybackGain(this.nativePtr, gain);
    }

    public synchronized float getPlaybackGain() {
        return this.getPlaybackGain(this.nativePtr);
    }

    public synchronized void muteMic(boolean isMuted) {
        this.muteMic(this.nativePtr, isMuted);
    }

    public synchronized LinphoneAddress interpretUrl(String destination) throws LinphoneCoreException {
        long lAddress = this.interpretUrl(this.nativePtr, destination);
        if (lAddress != 0) {
            return new LinphoneAddressImpl(lAddress, LinphoneAddressImpl.WrapMode.FromNew);
        }
        throw new LinphoneCoreException("Cannot interpret [" + destination + "]");
    }

    public synchronized LinphoneCall invite(LinphoneAddress to) throws LinphoneCoreException {
        LinphoneCall call = (LinphoneCall)this.inviteAddress(this.nativePtr, ((LinphoneAddressImpl)to).nativePtr);
        if (call != null) {
            return call;
        }
        throw new LinphoneCoreException("Unable to invite address " + to.asString());
    }

    public synchronized void sendDtmf(char number) {
        this.sendDtmf(this.nativePtr, number);
    }

    public synchronized void clearCallLogs() {
        this.clearCallLogs(this.nativePtr);
    }

    public synchronized boolean isMicMuted() {
        return this.isMicMuted(this.nativePtr);
    }

    public synchronized PayloadType findPayloadType(String mime, int clockRate, int channels) {
        this.isValid();
        long playLoadType = this.findPayloadType(this.nativePtr, mime, clockRate, channels);
        if (playLoadType == 0) {
            return null;
        }
        return new PayloadTypeImpl(playLoadType);
    }

    public synchronized void enablePayloadType(PayloadType pt, boolean enable) throws LinphoneCoreException {
        this.isValid();
        if (this.enablePayloadType(this.nativePtr, ((PayloadTypeImpl)pt).nativePtr, enable) != 0) {
            throw new LinphoneCoreException("cannot enable payload type [" + pt + "]");
        }
    }

    public synchronized boolean isPayloadTypeEnabled(PayloadType pt) {
        this.isValid();
        return this.isPayloadTypeEnabled(this.nativePtr, ((PayloadTypeImpl)pt).nativePtr);
    }

    public synchronized boolean payloadTypeIsVbr(PayloadType pt) {
        this.isValid();
        return this.payloadTypeIsVbr(this.nativePtr, ((PayloadTypeImpl)pt).nativePtr);
    }

    public synchronized void enableEchoCancellation(boolean enable) {
        this.isValid();
        this.enableEchoCancellation(this.nativePtr, enable);
    }

    public synchronized boolean isEchoCancellationEnabled() {
        this.isValid();
        return this.isEchoCancellationEnabled(this.nativePtr);
    }

    public synchronized LinphoneCall getCurrentCall() {
        this.isValid();
        return (LinphoneCall)this.getCurrentCall(this.nativePtr);
    }

    public int getPlayLevel() {
        return 0;
    }

    public void setPlayLevel(int level) {
    }

    private void applyAudioHacks() {
        if (Hacks.needGalaxySAudioHack()) {
            this.setMicrophoneGain(-9.0f);
        }
    }

    private void setAudioModeIncallForGalaxyS() {
        if (!this.contextInitialized()) {
            return;
        }
        this.mAudioManager.setMode(2);
    }

    public void routeAudioToSpeakerHelper(boolean speakerOn) {
        if (!this.contextInitialized()) {
            return;
        }
        if (Hacks.needGalaxySAudioHack()) {
            this.setAudioModeIncallForGalaxyS();
        }
        this.mAudioManager.setSpeakerphoneOn(speakerOn);
    }

    private native void forceSpeakerState(long var1, boolean var3);

    public void enableSpeaker(boolean value) {
        LinphoneCall call = this.getCurrentCall();
        this.mSpeakerEnabled = value;
        this.applyAudioHacks();
        if (call != null && call.getState() == LinphoneCall.State.StreamsRunning && Hacks.needGalaxySAudioHack()) {
            Log.d("Hack to have speaker=", value, " while on call");
            this.forceSpeakerState(this.nativePtr, value);
        } else {
            this.routeAudioToSpeakerHelper(value);
        }
    }

    public boolean isSpeakerEnabled() {
        return this.mSpeakerEnabled;
    }

    public synchronized void playDtmf(char number, int duration) {
        this.playDtmf(this.nativePtr, number, duration);
    }

    public synchronized void stopDtmf() {
        this.stopDtmf(this.nativePtr);
    }

    public synchronized void addFriend(LinphoneFriend lf) throws LinphoneCoreException {
        this.addFriend(this.nativePtr, ((LinphoneFriendImpl)lf).nativePtr);
    }

    public synchronized LinphoneFriend[] getFriendList() {
        return this.getFriendList(this.nativePtr);
    }

    public synchronized void setPresenceInfo(int minutes_away, String alternative_contact, OnlineStatus status) {
        this.setPresenceInfo(this.nativePtr, minutes_away, alternative_contact, status.mValue);
    }

    public synchronized OnlineStatus getPresenceInfo() {
        return OnlineStatus.fromInt(this.getPresenceInfo(this.nativePtr));
    }

    public synchronized void setPresenceModel(PresenceModel presence) {
        this.setPresenceModel(this.nativePtr, ((PresenceModelImpl)presence).getNativePtr());
    }

    public synchronized PresenceModel getPresenceModel() {
        return (PresenceModel)this.getPresenceModel(this.nativePtr);
    }

    public synchronized LinphoneChatRoom getOrCreateChatRoom(String to) {
        return new LinphoneChatRoomImpl(this.getOrCreateChatRoom(this.nativePtr, to));
    }

    public synchronized void setPreviewWindow(Object w) {
        this.setPreviewWindowId(this.nativePtr, w);
    }

    public synchronized void setVideoWindow(Object w) {
        this.setVideoWindowId(this.nativePtr, w);
    }

    public synchronized void setDeviceRotation(int rotation) {
        this.setDeviceRotation(this.nativePtr, rotation);
    }

    public synchronized void enableVideo(boolean vcap_enabled, boolean display_enabled) {
        this.enableVideo(this.nativePtr, vcap_enabled, display_enabled);
    }

    public synchronized boolean isVideoEnabled() {
        return this.isVideoEnabled(this.nativePtr);
    }

    public synchronized boolean isVideoSupported() {
        return this.isVideoSupported(this.nativePtr);
    }

    public synchronized LinphoneCore.FirewallPolicy getFirewallPolicy() {
        return LinphoneCore.FirewallPolicy.fromInt(this.getFirewallPolicy(this.nativePtr));
    }

    public synchronized String getStunServer() {
        return this.getStunServer(this.nativePtr);
    }

    public synchronized void setFirewallPolicy(LinphoneCore.FirewallPolicy pol) {
        this.setFirewallPolicy(this.nativePtr, pol.value());
    }

    public synchronized void setStunServer(String stunServer) {
        this.setStunServer(this.nativePtr, stunServer);
    }

    public synchronized LinphoneCallParams createDefaultCallParameters() {
        return new LinphoneCallParamsImpl(this.createDefaultCallParams(this.nativePtr));
    }

    public synchronized LinphoneCall inviteAddressWithParams(LinphoneAddress to, LinphoneCallParams params) throws LinphoneCoreException {
        long ptrDestination = ((LinphoneAddressImpl)to).nativePtr;
        long ptrParams = ((LinphoneCallParamsImpl)params).nativePtr;
        LinphoneCall call = (LinphoneCall)this.inviteAddressWithParams(this.nativePtr, ptrDestination, ptrParams);
        if (call != null) {
            return call;
        }
        throw new LinphoneCoreException("Unable to invite with params " + to.asString());
    }

    public synchronized int updateCall(LinphoneCall call, LinphoneCallParams params) {
        long ptrCall = ((LinphoneCallImpl)call).nativePtr;
        long ptrParams = params != null ? ((LinphoneCallParamsImpl)params).nativePtr : 0;
        return this.updateCall(this.nativePtr, ptrCall, ptrParams);
    }

    public synchronized int getUploadBandwidth() {
        return this.getUploadBandwidth(this.nativePtr);
    }

    public synchronized void setUploadBandwidth(int bw) {
        this.setUploadBandwidth(this.nativePtr, bw);
    }

    public synchronized int getDownloadBandwidth() {
        return this.getDownloadBandwidth(this.nativePtr);
    }

    public synchronized void setDownloadBandwidth(int bw) {
        this.setDownloadBandwidth(this.nativePtr, bw);
    }

    public synchronized void setPreferredVideoSize(VideoSize vSize) {
        this.setPreferredVideoSize(this.nativePtr, vSize.width, vSize.height);
    }

    public synchronized void setPreferredVideoSizeByName(String name) {
        this.setPreferredVideoSizeByName(this.nativePtr, name);
    }

    public synchronized VideoSize getPreferredVideoSize() {
        int[] nativeSize = this.getPreferredVideoSize(this.nativePtr);
        VideoSize vSize = new VideoSize();
        vSize.width = nativeSize[0];
        vSize.height = nativeSize[1];
        return vSize;
    }

    public synchronized void setRing(String path) {
        this.setRing(this.nativePtr, path);
    }

    public synchronized String getRing() {
        return this.getRing(this.nativePtr);
    }

    public synchronized void setRootCA(String path) {
        this.setRootCA(this.nativePtr, path);
    }

    public synchronized void setRingback(String path) {
        this.setRingback(this.nativePtr, path);
    }

    public synchronized LinphoneProxyConfig[] getProxyConfigList() {
        return this.getProxyConfigList(this.nativePtr);
    }

    public synchronized PayloadType[] getVideoCodecs() {
        long[] typesPtr = this.listVideoPayloadTypes(this.nativePtr);
        if (typesPtr == null) {
            return null;
        }
        PayloadType[] codecs = new PayloadType[typesPtr.length];
        for (int i = 0; i < codecs.length; ++i) {
            codecs[i] = new PayloadTypeImpl(typesPtr[i]);
        }
        return codecs;
    }

    public synchronized void setVideoCodecs(PayloadType[] codecs) {
        long[] typesPtr = new long[codecs.length];
        for (int i = 0; i < codecs.length; ++i) {
            typesPtr[i] = ((PayloadTypeImpl)codecs[i]).nativePtr;
        }
        this.setVideoCodecs(this.nativePtr, typesPtr);
    }

    public synchronized PayloadType[] getAudioCodecs() {
        long[] typesPtr = this.listAudioPayloadTypes(this.nativePtr);
        if (typesPtr == null) {
            return null;
        }
        PayloadType[] codecs = new PayloadType[typesPtr.length];
        for (int i = 0; i < codecs.length; ++i) {
            codecs[i] = new PayloadTypeImpl(typesPtr[i]);
        }
        return codecs;
    }

    public synchronized void setAudioCodecs(PayloadType[] codecs) {
        long[] typesPtr = new long[codecs.length];
        for (int i = 0; i < codecs.length; ++i) {
            typesPtr[i] = ((PayloadTypeImpl)codecs[i]).nativePtr;
        }
        this.setAudioCodecs(this.nativePtr, typesPtr);
    }

    public synchronized boolean isNetworkReachable() {
        return this.isNetworkStateReachable(this.nativePtr);
    }

    public synchronized void enableKeepAlive(boolean enable) {
        this.enableKeepAlive(this.nativePtr, enable);
    }

    public synchronized boolean isKeepAliveEnabled() {
        return this.isKeepAliveEnabled(this.nativePtr);
    }

    public synchronized void startEchoCalibration(LinphoneCoreListener listener) throws LinphoneCoreException {
        this.startEchoCalibration(this.nativePtr, listener);
    }

    public synchronized LinphoneCore.Transports getSignalingTransportPorts() {
        LinphoneCore.Transports transports = new LinphoneCore.Transports();
        transports.udp = this.getSignalingTransportPort(this.nativePtr, 0);
        transports.tcp = this.getSignalingTransportPort(this.nativePtr, 1);
        transports.tls = this.getSignalingTransportPort(this.nativePtr, 3);
        return transports;
    }

    public synchronized void setSignalingTransportPorts(LinphoneCore.Transports transports) {
        this.setSignalingTransportPorts(this.nativePtr, transports.udp, transports.tcp, transports.tls);
    }

    public synchronized void enableIpv6(boolean enable) {
        this.enableIpv6(this.nativePtr, enable);
    }

    public synchronized boolean isIpv6Enabled() {
        return this.isIpv6Enabled(this.nativePtr);
    }

    public synchronized void adjustSoftwareVolume(int i) {
    }

    public synchronized boolean pauseCall(LinphoneCall call) {
        return 0 == this.pauseCall(this.nativePtr, ((LinphoneCallImpl)call).nativePtr);
    }

    public synchronized boolean resumeCall(LinphoneCall call) {
        return 0 == this.resumeCall(this.nativePtr, ((LinphoneCallImpl)call).nativePtr);
    }

    public synchronized boolean pauseAllCalls() {
        return 0 == this.pauseAllCalls(this.nativePtr);
    }

    public synchronized void setDownloadPtime(int ptime) {
        this.setDownloadPtime(this.nativePtr, ptime);
    }

    public synchronized void setUploadPtime(int ptime) {
        this.setUploadPtime(this.nativePtr, ptime);
    }

    public synchronized void setZrtpSecretsCache(String file) {
        this.setZrtpSecretsCache(this.nativePtr, file);
    }

    public synchronized void enableEchoLimiter(boolean val) {
        this.enableEchoLimiter(this.nativePtr, val);
    }

    public synchronized void setVideoDevice(int id2) {
        Log.i("Setting camera id :", id2);
        if (this.setVideoDevice(this.nativePtr, id2) != 0) {
            Log.e("Failed to set video device to id:", id2);
        }
    }

    public synchronized int getVideoDevice() {
        return this.getVideoDevice(this.nativePtr);
    }

    private native void leaveConference(long var1);

    public synchronized void leaveConference() {
        this.leaveConference(this.nativePtr);
    }

    private native boolean enterConference(long var1);

    public synchronized boolean enterConference() {
        return this.enterConference(this.nativePtr);
    }

    private native boolean isInConference(long var1);

    public synchronized boolean isInConference() {
        return this.isInConference(this.nativePtr);
    }

    private native void terminateConference(long var1);

    public synchronized void terminateConference() {
        this.terminateConference(this.nativePtr);
    }

    private native int getConferenceSize(long var1);

    public synchronized int getConferenceSize() {
        return this.getConferenceSize(this.nativePtr);
    }

    private native int getCallsNb(long var1);

    public synchronized int getCallsNb() {
        return this.getCallsNb(this.nativePtr);
    }

    private native void terminateAllCalls(long var1);

    public synchronized void terminateAllCalls() {
        this.terminateAllCalls(this.nativePtr);
    }

    private native Object getCall(long var1, int var3);

    public synchronized LinphoneCall[] getCalls() {
        int size = this.getCallsNb(this.nativePtr);
        LinphoneCall[] calls = new LinphoneCall[size];
        for (int i = 0; i < size; ++i) {
            calls[i] = (LinphoneCall)this.getCall(this.nativePtr, i);
        }
        return calls;
    }

    private native void addAllToConference(long var1);

    public synchronized void addAllToConference() {
        this.addAllToConference(this.nativePtr);
    }

    private native void addToConference(long var1, long var3);

    public synchronized void addToConference(LinphoneCall call) {
        this.addToConference(this.nativePtr, this.getCallPtr(call));
    }

    private native void removeFromConference(long var1, long var3);

    public synchronized void removeFromConference(LinphoneCall call) {
        this.removeFromConference(this.nativePtr, this.getCallPtr(call));
    }

    private long getCallPtr(LinphoneCall call) {
        return ((LinphoneCallImpl)call).nativePtr;
    }

    private long getCallParamsPtr(LinphoneCallParams callParams) {
        return ((LinphoneCallParamsImpl)callParams).nativePtr;
    }

    private native int transferCall(long var1, long var3, String var5);

    public synchronized void transferCall(LinphoneCall call, String referTo) {
        this.transferCall(this.nativePtr, this.getCallPtr(call), referTo);
    }

    private native int transferCallToAnother(long var1, long var3, long var5);

    public synchronized void transferCallToAnother(LinphoneCall call, LinphoneCall dest) {
        this.transferCallToAnother(this.nativePtr, this.getCallPtr(call), this.getCallPtr(dest));
    }

    private native Object findCallFromUri(long var1, String var3);

    public synchronized LinphoneCall findCallFromUri(String uri) {
        return (LinphoneCall)this.findCallFromUri(this.nativePtr, uri);
    }

    public synchronized LinphoneCore.MediaEncryption getMediaEncryption() {
        return LinphoneCore.MediaEncryption.fromInt(this.getMediaEncryption(this.nativePtr));
    }

    public synchronized boolean isMediaEncryptionMandatory() {
        return this.isMediaEncryptionMandatory(this.nativePtr);
    }

    public synchronized void setMediaEncryption(LinphoneCore.MediaEncryption menc) {
        this.setMediaEncryption(this.nativePtr, menc.mValue);
    }

    public synchronized void setMediaEncryptionMandatory(boolean yesno) {
        this.setMediaEncryptionMandatory(this.nativePtr, yesno);
    }

    private native int getMaxCalls(long var1);

    public synchronized int getMaxCalls() {
        return this.getMaxCalls(this.nativePtr);
    }

    public boolean isMyself(String uri) {
        LinphoneProxyConfig lpc = this.getDefaultProxyConfig();
        if (lpc == null) {
            return false;
        }
        return uri.equals(lpc.getIdentity());
    }

    private native boolean soundResourcesLocked(long var1);

    public synchronized boolean soundResourcesLocked() {
        return this.soundResourcesLocked(this.nativePtr);
    }

    private native void setMaxCalls(long var1, int var3);

    public synchronized void setMaxCalls(int max) {
        this.setMaxCalls(this.nativePtr, max);
    }

    private native boolean isEchoLimiterEnabled(long var1);

    public synchronized boolean isEchoLimiterEnabled() {
        return this.isEchoLimiterEnabled(this.nativePtr);
    }

    private native boolean mediaEncryptionSupported(long var1, int var3);

    public synchronized boolean mediaEncryptionSupported(LinphoneCore.MediaEncryption menc) {
        return this.mediaEncryptionSupported(this.nativePtr, menc.mValue);
    }

    private native void setPlayFile(long var1, String var3);

    public synchronized void setPlayFile(String path) {
        this.setPlayFile(this.nativePtr, path);
    }

    private native void tunnelAddServerAndMirror(long var1, String var3, int var4, int var5, int var6);

    public synchronized void tunnelAddServerAndMirror(String host, int port, int mirror, int ms) {
        this.tunnelAddServerAndMirror(this.nativePtr, host, port, mirror, ms);
    }

    private native void tunnelAddServer(long var1, TunnelConfig var3);

    public synchronized void tunnelAddServer(TunnelConfig config) {
        this.tunnelAddServer(this.nativePtr, config);
    }

    private final native TunnelConfig[] tunnelGetServers(long var1);

    public final synchronized TunnelConfig[] tunnelGetServers() {
        return this.tunnelGetServers(this.nativePtr);
    }

    private native void tunnelAutoDetect(long var1);

    public synchronized void tunnelAutoDetect() {
        this.tunnelAutoDetect(this.nativePtr);
    }

    private native void tunnelCleanServers(long var1);

    public synchronized void tunnelCleanServers() {
        this.tunnelCleanServers(this.nativePtr);
    }

    private native void tunnelEnable(long var1, boolean var3);

    public synchronized void tunnelEnable(boolean enable) {
        this.tunnelEnable(this.nativePtr, enable);
    }

    private native void tunnelSetMode(long var1, int var3);

    public synchronized void tunnelSetMode(LinphoneCore.TunnelMode mode) {
        this.tunnelSetMode(this.nativePtr, LinphoneCore.TunnelMode.enumToInt(mode));
    }

    private native int tunnelGetMode(long var1);

    public synchronized LinphoneCore.TunnelMode tunnelGetMode() {
        return LinphoneCore.TunnelMode.intToEnum(this.tunnelGetMode(this.nativePtr));
    }

    private native void tunnelEnableSip(long var1, boolean var3);

    public void tunnelEnableSip(boolean enable) {
        this.tunnelEnableSip(this.nativePtr, enable);
    }

    private native boolean tunnelSipEnabled(long var1);

    public boolean tunnelSipEnabled() {
        return this.tunnelSipEnabled(this.nativePtr);
    }

    public native boolean isTunnelAvailable();

    private native void acceptCallWithParams(long var1, long var3, long var5);

    public synchronized void acceptCallWithParams(LinphoneCall aCall, LinphoneCallParams params) throws LinphoneCoreException {
        this.acceptCallWithParams(this.nativePtr, this.getCallPtr(aCall), this.getCallParamsPtr(params));
    }

    private native void acceptCallUpdate(long var1, long var3, long var5);

    public synchronized void acceptCallUpdate(LinphoneCall aCall, LinphoneCallParams params) throws LinphoneCoreException {
        this.acceptCallUpdate(this.nativePtr, this.getCallPtr(aCall), this.getCallParamsPtr(params));
    }

    private native void deferCallUpdate(long var1, long var3);

    public synchronized void deferCallUpdate(LinphoneCall aCall) throws LinphoneCoreException {
        this.deferCallUpdate(this.nativePtr, this.getCallPtr(aCall));
    }

    private native void setVideoPolicy(long var1, boolean var3, boolean var4);

    public synchronized void setVideoPolicy(boolean autoInitiate, boolean autoAccept) {
        this.setVideoPolicy(this.nativePtr, autoInitiate, autoAccept);
    }

    private native boolean getVideoAutoInitiatePolicy(long var1);

    public synchronized boolean getVideoAutoInitiatePolicy() {
        return this.getVideoAutoInitiatePolicy(this.nativePtr);
    }

    private native boolean getVideoAutoAcceptPolicy(long var1);

    public synchronized boolean getVideoAutoAcceptPolicy() {
        return this.getVideoAutoAcceptPolicy(this.nativePtr);
    }

    private native void setStaticPicture(long var1, String var3);

    public synchronized void setStaticPicture(String path) {
        this.setStaticPicture(this.nativePtr, path);
    }

    private native void setUserAgent(long var1, String var3, String var4);

    public synchronized void setUserAgent(String name, String version) {
        this.setUserAgent(this.nativePtr, name, version);
    }

    private native void setCpuCountNative(int var1);

    public synchronized void setCpuCount(int count) {
        this.setCpuCountNative(count);
    }

    public synchronized int getMissedCallsCount() {
        return this.getMissedCallsCount(this.nativePtr);
    }

    public synchronized void removeCallLog(LinphoneCallLog log) {
        this.removeCallLog(this.nativePtr, ((LinphoneCallLogImpl)log).getNativePtr());
    }

    public synchronized void resetMissedCallsCount() {
        this.resetMissedCallsCount(this.nativePtr);
    }

    private native void tunnelSetHttpProxy(long var1, String var3, int var4, String var5, String var6);

    public synchronized void tunnelSetHttpProxy(String proxy_host, int port, String username, String password) {
        this.tunnelSetHttpProxy(this.nativePtr, proxy_host, port, username, password);
    }

    private native void refreshRegisters(long var1);

    public synchronized void refreshRegisters() {
        this.refreshRegisters(this.nativePtr);
    }

    public String getVersion() {
        return this.getVersion(this.nativePtr);
    }

    public synchronized PayloadType findPayloadType(String mime, int clockRate) {
        return this.findPayloadType(mime, clockRate, FIND_PAYLOAD_IGNORE_CHANNELS);
    }

    private native void removeFriend(long var1, long var3);

    public synchronized void removeFriend(LinphoneFriend lf) {
        this.removeFriend(this.nativePtr, lf.getNativePtr());
    }

    private native LinphoneFriend getFriendByAddress(long var1, String var3);

    public synchronized LinphoneFriend findFriendByAddress(String sipUri) {
        return this.getFriendByAddress(this.nativePtr, sipUri);
    }

    public synchronized void setAudioPort(int port) {
        this.setAudioPort(this.nativePtr, port);
    }

    public synchronized void setVideoPort(int port) {
        this.setVideoPort(this.nativePtr, port);
    }

    public synchronized void setAudioPortRange(int minPort, int maxPort) {
        this.setAudioPortRange(this.nativePtr, minPort, maxPort);
    }

    public synchronized void setVideoPortRange(int minPort, int maxPort) {
        this.setVideoPortRange(this.nativePtr, minPort, maxPort);
    }

    public synchronized void setIncomingTimeout(int timeout) {
        this.setIncomingTimeout(this.nativePtr, timeout);
    }

    public synchronized void setInCallTimeout(int timeout) {
        this.setInCallTimeout(this.nativePtr, timeout);
    }

    private native void setMicrophoneGain(long var1, float var3);

    public synchronized void setMicrophoneGain(float gain) {
        this.setMicrophoneGain(this.nativePtr, gain);
    }

    public synchronized void setPrimaryContact(String address) {
        this.setPrimaryContact2(this.nativePtr, address);
    }

    public synchronized String getPrimaryContact() {
        return this.getPrimaryContact(this.nativePtr);
    }

    public synchronized void setPrimaryContact(String displayName, String username) {
        this.setPrimaryContact(this.nativePtr, displayName, username);
    }

    public synchronized String getPrimaryContactUsername() {
        return this.getPrimaryContactUsername(this.nativePtr);
    }

    public synchronized String getPrimaryContactDisplayName() {
        return this.getPrimaryContactDisplayName(this.nativePtr);
    }

    private native void setUseSipInfoForDtmfs(long var1, boolean var3);

    public synchronized void setUseSipInfoForDtmfs(boolean use) {
        this.setUseSipInfoForDtmfs(this.nativePtr, use);
    }

    private native boolean getUseSipInfoForDtmfs(long var1);

    public synchronized boolean getUseSipInfoForDtmfs() {
        return this.getUseSipInfoForDtmfs(this.nativePtr);
    }

    private native void setUseRfc2833ForDtmfs(long var1, boolean var3);

    public synchronized void setUseRfc2833ForDtmfs(boolean use) {
        this.setUseRfc2833ForDtmfs(this.nativePtr, use);
    }

    private native boolean getUseRfc2833ForDtmfs(long var1);

    public synchronized boolean getUseRfc2833ForDtmfs() {
        return this.getUseRfc2833ForDtmfs(this.nativePtr);
    }

    private native long getConfig(long var1);

    public synchronized LpConfig getConfig() {
        long configPtr = this.getConfig(this.nativePtr);
        return new LpConfigImpl(configPtr);
    }

    private native boolean needsEchoCalibration(long var1);

    public synchronized boolean needsEchoCalibration() {
        return this.needsEchoCalibration(this.nativePtr);
    }

    private native boolean hasBuiltInEchoCanceler(long var1);

    public synchronized boolean hasBuiltInEchoCanceler() {
        return this.hasBuiltInEchoCanceler(this.nativePtr);
    }

    private native void declineCall(long var1, long var3, int var5);

    public synchronized void declineCall(LinphoneCall aCall, Reason reason) {
        this.declineCall(this.nativePtr, ((LinphoneCallImpl)aCall).nativePtr, reason.mValue);
    }

    private native boolean upnpAvailable(long var1);

    public synchronized boolean upnpAvailable() {
        return this.upnpAvailable(this.nativePtr);
    }

    private native int getUpnpState(long var1);

    public synchronized LinphoneCore.UpnpState getUpnpState() {
        return LinphoneCore.UpnpState.fromInt(this.getUpnpState(this.nativePtr));
    }

    private native String getUpnpExternalIpaddress(long var1);

    public synchronized String getUpnpExternalIpaddress() {
        return this.getUpnpExternalIpaddress(this.nativePtr);
    }

    private native int startConferenceRecording(long var1, String var3);

    public synchronized void startConferenceRecording(String path) {
        this.startConferenceRecording(this.nativePtr, path);
    }

    private native int stopConferenceRecording(long var1);

    public synchronized void stopConferenceRecording() {
        this.stopConferenceRecording(this.nativePtr);
    }

    public synchronized PayloadType findPayloadType(String mime) {
        return this.findPayloadType(mime, FIND_PAYLOAD_IGNORE_RATE);
    }

    private native void setSipDscp(long var1, int var3);

    public synchronized void setSipDscp(int dscp) {
        this.setSipDscp(this.nativePtr, dscp);
    }

    private native int getSipDscp(long var1);

    public synchronized int getSipDscp() {
        return this.getSipDscp(this.nativePtr);
    }

    private native void setAudioDscp(long var1, int var3);

    public synchronized void setAudioDscp(int dscp) {
        this.setAudioDscp(this.nativePtr, dscp);
    }

    private native int getAudioDscp(long var1);

    public synchronized int getAudioDscp() {
        return this.getAudioDscp(this.nativePtr);
    }

    private native void setVideoDscp(long var1, int var3);

    public synchronized void setVideoDscp(int dscp) {
        this.setVideoDscp(this.nativePtr, dscp);
    }

    private native int getVideoDscp(long var1);

    public synchronized int getVideoDscp() {
        return this.getVideoDscp(this.nativePtr);
    }

    private native long createInfoMessage(long var1);

    public synchronized LinphoneInfoMessage createInfoMessage() {
        return new LinphoneInfoMessageImpl(this.createInfoMessage(this.nativePtr));
    }

    private native Object subscribe(long var1, long var3, String var5, int var6, String var7, String var8, byte[] var9, String var10);

    public synchronized LinphoneEvent subscribe(LinphoneAddress resource, String eventname, int expires, LinphoneContent content) {
        return (LinphoneEvent)this.subscribe(this.nativePtr, ((LinphoneAddressImpl)resource).nativePtr, eventname, expires, content != null ? content.getType() : null, content != null ? content.getSubtype() : null, content != null ? content.getData() : null, content != null ? content.getEncoding() : null);
    }

    private native Object publish(long var1, long var3, String var5, int var6, String var7, String var8, byte[] var9, String var10);

    public synchronized LinphoneEvent publish(LinphoneAddress resource, String eventname, int expires, LinphoneContent content) {
        return (LinphoneEvent)this.publish(this.nativePtr, ((LinphoneAddressImpl)resource).nativePtr, eventname, expires, content != null ? content.getType() : null, content != null ? content.getSubtype() : null, content != null ? content.getData() : null, content != null ? content.getEncoding() : null);
    }

    private native Object createSubscribe(long var1, long var3, String var5, int var6);

    public synchronized LinphoneEvent createSubscribe(LinphoneAddress resource, String event, int expires) {
        return (LinphoneEvent)this.createSubscribe(this.nativePtr, ((LinphoneAddressImpl)resource).nativePtr, event, expires);
    }

    private native Object createPublish(long var1, long var3, String var5, int var6);

    public synchronized LinphoneEvent createPublish(LinphoneAddress resource, String event, int expires) {
        return (LinphoneEvent)this.createPublish(this.nativePtr, ((LinphoneAddressImpl)resource).nativePtr, event, expires);
    }

    public synchronized void setChatDatabasePath(String path) {
        this.setChatDatabasePath(this.nativePtr, path);
    }

    public synchronized LinphoneChatRoom[] getChatRooms() {
        long[] typesPtr = this.getChatRooms(this.nativePtr);
        if (typesPtr == null) {
            return null;
        }
        LinphoneChatRoom[] proxies = new LinphoneChatRoom[typesPtr.length];
        for (int i = 0; i < proxies.length; ++i) {
            proxies[i] = new LinphoneChatRoomImpl(typesPtr[i]);
        }
        return proxies;
    }

    public synchronized LinphoneAuthInfo[] getAuthInfosList() {
        long[] typesPtr = this.getAuthInfosList(this.nativePtr);
        if (typesPtr == null) {
            return null;
        }
        LinphoneAuthInfo[] authInfos = new LinphoneAuthInfo[typesPtr.length];
        for (int i = 0; i < authInfos.length; ++i) {
            authInfos[i] = new LinphoneAuthInfoImpl(typesPtr[i]);
        }
        return authInfos;
    }

    public synchronized LinphoneAuthInfo findAuthInfo(String username, String realm, String domain) {
        long ptr = this.findAuthInfos(this.nativePtr, username, realm, domain);
        if (ptr == 0) {
            return null;
        }
        return new LinphoneAuthInfoImpl(ptr);
    }

    private native LinphoneCall startReferedCall(long var1, long var3, long var5);

    public synchronized LinphoneCall startReferedCall(LinphoneCall call, LinphoneCallParams params) {
        long ptrParams = ((LinphoneCallParamsImpl)params).nativePtr;
        return this.startReferedCall(this.nativePtr, this.getCallPtr(call), ptrParams);
    }

    private native String[] listSupportedVideoResolutions(long var1);

    public synchronized String[] getSupportedVideoSizes() {
        return this.listSupportedVideoResolutions(this.nativePtr);
    }

    public synchronized int migrateToMultiTransport() {
        return this.migrateToMultiTransport(this.nativePtr);
    }

    private native boolean acceptEarlyMedia(long var1, long var3);

    public synchronized boolean acceptEarlyMedia(LinphoneCall call) {
        return this.acceptEarlyMedia(this.nativePtr, this.getCallPtr(call));
    }

    private native boolean acceptEarlyMediaWithParams(long var1, long var3, long var5);

    public synchronized boolean acceptEarlyMediaWithParams(LinphoneCall call, LinphoneCallParams params) {
        long ptrParams = params != null ? ((LinphoneCallParamsImpl)params).nativePtr : 0;
        return this.acceptEarlyMediaWithParams(this.nativePtr, this.getCallPtr(call), ptrParams);
    }

    public synchronized LinphoneProxyConfig createProxyConfig() {
        return new LinphoneProxyConfigImpl(this);
    }

    public synchronized LinphoneProxyConfig createProxyConfig(String identity, String proxy, String route, boolean enableRegister) throws LinphoneCoreException {
        this.isValid();
        try {
            return new LinphoneProxyConfigImpl(this, identity, proxy, route, enableRegister);
        }
        catch (LinphoneCoreException e) {
            return null;
        }
    }

    public synchronized void setCallErrorTone(Reason reason, String path) {
        this.setCallErrorTone(this.nativePtr, reason.mValue, path);
    }

    private native void setMtu(long var1, int var3);

    public synchronized void setMtu(int mtu) {
        this.setMtu(this.nativePtr, mtu);
    }

    private native int getMtu(long var1);

    public synchronized int getMtu() {
        return this.getMtu(this.nativePtr);
    }

    public synchronized void enableSdp200Ack(boolean enable) {
        this.enableSdp200Ack(this.nativePtr, enable);
    }

    public synchronized boolean isSdp200AckEnabled() {
        return this.isSdp200AckEnabled(this.nativePtr);
    }

    private native void setTone(long var1, int var3, String var4);

    public synchronized void setTone(ToneID id2, String wavfile) {
        this.setTone(this.nativePtr, id2.mValue, wavfile);
    }

    private native void disableChat(long var1, int var3);

    public synchronized void disableChat(Reason denycode) {
        this.disableChat(this.nativePtr, denycode.mValue);
    }

    private native void enableChat(long var1);

    public synchronized void enableChat() {
        this.enableChat(this.nativePtr);
    }

    private native boolean chatEnabled(long var1);

    public synchronized boolean chatEnabled() {
        return this.chatEnabled(this.nativePtr);
    }

    public synchronized void stopRinging() {
        this.stopRinging(this.nativePtr);
    }

    private native void setPayloadTypeBitrate(long var1, long var3, int var5);

    public synchronized void setPayloadTypeBitrate(PayloadType pt, int bitrate) {
        this.setPayloadTypeBitrate(this.nativePtr, ((PayloadTypeImpl)pt).nativePtr, bitrate);
    }

    private native int getPayloadTypeBitrate(long var1, long var3);

    public synchronized int getPayloadTypeBitrate(PayloadType pt) {
        return this.getPayloadTypeBitrate(this.nativePtr, ((PayloadTypeImpl)pt).nativePtr);
    }

    private native void setPayloadTypeNumber(long var1, long var3, int var5);

    public synchronized void setPayloadTypeNumber(PayloadType pt, int number) {
        this.setPayloadTypeNumber(this.nativePtr, ((PayloadTypeImpl)pt).nativePtr, number);
    }

    private native int getPayloadTypeNumber(long var1, long var3);

    public synchronized int getPayloadTypeNumber(PayloadType pt) {
        return this.getPayloadTypeNumber(this.nativePtr, ((PayloadTypeImpl)pt).nativePtr);
    }

    public synchronized void enableAdaptiveRateControl(boolean enable) {
        this.enableAdaptiveRateControl(this.nativePtr, enable);
    }

    public synchronized boolean isAdaptiveRateControlEnabled() {
        return this.isAdaptiveRateControlEnabled(this.nativePtr);
    }

    public synchronized LinphoneCore.AdaptiveRateAlgorithm getAdaptiveRateAlgorithm() {
        return LinphoneCore.AdaptiveRateAlgorithm.fromString(this.getAdaptiveRateAlgorithm(this.nativePtr));
    }

    public synchronized void setAdaptiveRateAlgorithm(LinphoneCore.AdaptiveRateAlgorithm alg) {
        this.setAdaptiveRateAlgorithm(this.nativePtr, alg.toString());
    }

    private native void setAudioJittcomp(long var1, int var3);

    public synchronized void setAudioJittcomp(int value) {
        this.setAudioJittcomp(this.nativePtr, value);
    }

    private native void setVideoJittcomp(long var1, int var3);

    public synchronized void setVideoJittcomp(int value) {
        this.setVideoJittcomp(this.nativePtr, value);
    }

    private native void setFileTransferServer(long var1, String var3);

    public synchronized void setFileTransferServer(String serverUrl) {
        this.setFileTransferServer(this.nativePtr, serverUrl);
    }

    private native String getFileTransferServer(long var1);

    public synchronized String getFileTransferServer() {
        return this.getFileTransferServer(this.nativePtr);
    }

    private native long createLocalPlayer(long var1, AndroidVideoWindowImpl var3);

    public synchronized LinphonePlayer createLocalPlayer(AndroidVideoWindowImpl window) {
        long playerPtr = this.createLocalPlayer(this.nativePtr, window);
        if (playerPtr != 0) {
            return new LinphonePlayerImpl(playerPtr);
        }
        return null;
    }

    private native void addListener(long var1, LinphoneCoreListener var3);

    public void addListener(LinphoneCoreListener listener) {
        this.addListener(this.nativePtr, listener);
    }

    private native void removeListener(long var1, LinphoneCoreListener var3);

    public void removeListener(LinphoneCoreListener listener) {
        this.removeListener(this.nativePtr, listener);
    }

    private native void setRemoteRingbackTone(long var1, String var3);

    public void setRemoteRingbackTone(String file) {
        this.setRemoteRingbackTone(this.nativePtr, file);
    }

    private native String getRemoteRingbackTone(long var1);

    public String getRemoteRingbackTone() {
        return this.getRemoteRingbackTone(this.nativePtr);
    }

    private native void uploadLogCollection(long var1);

    public void uploadLogCollection() {
        this.uploadLogCollection(this.nativePtr);
    }

    public native void resetLogCollection();

    private native void setPreferredFramerate(long var1, float var3);

    public void setPreferredFramerate(float fps) {
        this.setPreferredFramerate(this.nativePtr, fps);
    }

    private native float getPreferredFramerate(long var1);

    public float getPreferredFramerate() {
        return this.getPreferredFramerate(this.nativePtr);
    }

    private native int setAudioMulticastAddr(long var1, String var3);

    public void setAudioMulticastAddr(String ip) throws LinphoneCoreException {
        if (this.setAudioMulticastAddr(this.nativePtr, ip) != 0) {
            throw new LinphoneCoreException("bad ip address [" + ip + "]");
        }
    }

    private native int setVideoMulticastAddr(long var1, String var3);

    public void setVideoMulticastAddr(String ip) throws LinphoneCoreException {
        if (this.setVideoMulticastAddr(this.nativePtr, ip) != 0) {
            throw new LinphoneCoreException("bad ip address [" + ip + "]");
        }
    }

    private native String getAudioMulticastAddr(long var1);

    public String getAudioMulticastAddr() {
        return this.getAudioMulticastAddr(this.nativePtr);
    }

    private native String getVideoMulticastAddr(long var1);

    public String getVideoMulticastAddr() {
        return this.getVideoMulticastAddr(this.nativePtr);
    }

    private native int setAudioMulticastTtl(long var1, int var3);

    public void setAudioMulticastTtl(int ttl) throws LinphoneCoreException {
        if (this.setAudioMulticastTtl(this.nativePtr, ttl) != 0) {
            throw new LinphoneCoreException("bad ttl value [" + ttl + "]");
        }
    }

    private native int setVideoMulticastTtl(long var1, int var3);

    public void setVideoMulticastTtl(int ttl) throws LinphoneCoreException {
        if (this.setVideoMulticastTtl(this.nativePtr, ttl) != 0) {
            throw new LinphoneCoreException("bad ttl value [" + ttl + "]");
        }
    }

    private native int getAudioMulticastTtl(long var1);

    public int getAudioMulticastTtl() {
        return this.getAudioMulticastTtl(this.nativePtr);
    }

    private native int getVideoMulticastTtl(long var1);

    public int getVideoMulticastTtl() {
        return this.getVideoMulticastTtl(this.nativePtr);
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

    private native void enableDnsSrv(long var1, boolean var3);

    public void enableDnsSrv(boolean yesno) {
        this.enableDnsSrv(this.nativePtr, yesno);
    }

    private native boolean dnsSrvEnabled(long var1);

    public boolean dnsSrvEnabled() {
        return this.dnsSrvEnabled(this.nativePtr);
    }

    private native void setVideoPreset(long var1, String var3);

    public void setVideoPreset(String preset) {
        this.setVideoPreset(this.nativePtr, preset);
    }

    private native String getVideoPreset(long var1);

    public String getVideoPreset() {
        return this.getVideoPreset(this.nativePtr);
    }
}

