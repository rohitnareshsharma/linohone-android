/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAuthInfo;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallLog;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneChatRoom;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneEvent;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphonePlayer;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.LpConfig;
import org.linphone.core.OnlineStatus;
import org.linphone.core.PayloadType;
import org.linphone.core.PresenceModel;
import org.linphone.core.Reason;
import org.linphone.core.ToneID;
import org.linphone.core.TunnelConfig;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.video.AndroidVideoWindowImpl;

public interface LinphoneCore {
    public void setContext(Object var1);

    public void clearProxyConfigs();

    public void addProxyConfig(LinphoneProxyConfig var1) throws LinphoneCoreException;

    public void removeProxyConfig(LinphoneProxyConfig var1);

    public void setDefaultProxyConfig(LinphoneProxyConfig var1);

    public LinphoneProxyConfig getDefaultProxyConfig();

    public LinphoneAuthInfo[] getAuthInfosList();

    public LinphoneAuthInfo findAuthInfo(String var1, String var2, String var3);

    public void removeAuthInfo(LinphoneAuthInfo var1);

    public void clearAuthInfos();

    public void addAuthInfo(LinphoneAuthInfo var1);

    public LinphoneAddress interpretUrl(String var1) throws LinphoneCoreException;

    public LinphoneCall invite(String var1) throws LinphoneCoreException;

    public LinphoneCall invite(LinphoneAddress var1) throws LinphoneCoreException;

    public void terminateCall(LinphoneCall var1);

    public void declineCall(LinphoneCall var1, Reason var2);

    public LinphoneCall getCurrentCall();

    public LinphoneAddress getRemoteAddress();

    public boolean isIncall();

    public boolean isInComingInvitePending();

    public void iterate();

    public void acceptCall(LinphoneCall var1) throws LinphoneCoreException;

    public void acceptCallWithParams(LinphoneCall var1, LinphoneCallParams var2) throws LinphoneCoreException;

    public void acceptCallUpdate(LinphoneCall var1, LinphoneCallParams var2) throws LinphoneCoreException;

    public void deferCallUpdate(LinphoneCall var1) throws LinphoneCoreException;

    public LinphoneCallLog[] getCallLogs();

    public void setNetworkReachable(boolean var1);

    public boolean isNetworkReachable();

    public void destroy();

    public void setPlaybackGain(float var1);

    public float getPlaybackGain();

    public void setPlayLevel(int var1);

    public int getPlayLevel();

    public void muteMic(boolean var1);

    public boolean isMicMuted();

    public void sendDtmf(char var1);

    public void playDtmf(char var1, int var2);

    public void stopDtmf();

    public void clearCallLogs();

    public PayloadType findPayloadType(String var1, int var2, int var3);

    public PayloadType findPayloadType(String var1, int var2);

    public PayloadType findPayloadType(String var1);

    public void enablePayloadType(PayloadType var1, boolean var2) throws LinphoneCoreException;

    public boolean isPayloadTypeEnabled(PayloadType var1);

    public boolean payloadTypeIsVbr(PayloadType var1);

    public void setPayloadTypeBitrate(PayloadType var1, int var2);

    public int getPayloadTypeBitrate(PayloadType var1);

    public void setPayloadTypeNumber(PayloadType var1, int var2);

    public int getPayloadTypeNumber(PayloadType var1);

    public void enableAdaptiveRateControl(boolean var1);

    public boolean isAdaptiveRateControlEnabled();

    public void setAdaptiveRateAlgorithm(AdaptiveRateAlgorithm var1);

    public AdaptiveRateAlgorithm getAdaptiveRateAlgorithm();

    public void enableEchoCancellation(boolean var1);

    public boolean isEchoCancellationEnabled();

    public boolean isEchoLimiterEnabled();

    public void setSignalingTransportPorts(Transports var1);

    public Transports getSignalingTransportPorts();

    public void setSipDscp(int var1);

    public int getSipDscp();

    public void enableSpeaker(boolean var1);

    public boolean isSpeakerEnabled();

    public void addFriend(LinphoneFriend var1) throws LinphoneCoreException;

    public LinphoneFriend[] getFriendList();

    public void setPresenceInfo(int var1, String var2, OnlineStatus var3);

    public OnlineStatus getPresenceInfo();

    public void setPresenceModel(PresenceModel var1);

    public PresenceModel getPresenceModel();

    public LinphoneChatRoom getOrCreateChatRoom(String var1);

    public void setVideoWindow(Object var1);

    public void setPreviewWindow(Object var1);

    public void setDeviceRotation(int var1);

    public void setVideoDevice(int var1);

    public int getVideoDevice();

    public boolean isVideoSupported();

    public void enableVideo(boolean var1, boolean var2);

    public boolean isVideoEnabled();

    public void setStunServer(String var1);

    public String getStunServer();

    public void setFirewallPolicy(FirewallPolicy var1);

    public FirewallPolicy getFirewallPolicy();

    public LinphoneCall inviteAddressWithParams(LinphoneAddress var1, LinphoneCallParams var2) throws LinphoneCoreException;

    public int updateCall(LinphoneCall var1, LinphoneCallParams var2);

    public LinphoneCallParams createDefaultCallParameters();

    public void setRing(String var1);

    public String getRing();

    public void setRootCA(String var1);

    public void setRingback(String var1);

    public int getUploadBandwidth();

    public void setUploadBandwidth(int var1);

    public int getDownloadBandwidth();

    public void setDownloadBandwidth(int var1);

    public void setDownloadPtime(int var1);

    public void setUploadPtime(int var1);

    public void setPreferredVideoSize(VideoSize var1);

    public void setPreferredVideoSizeByName(String var1);

    public VideoSize getPreferredVideoSize();

    public void setPreferredFramerate(float var1);

    public float getPreferredFramerate();

    public PayloadType[] getAudioCodecs();

    public void setAudioCodecs(PayloadType[] var1);

    public PayloadType[] getVideoCodecs();

    public void setVideoCodecs(PayloadType[] var1);

    public void enableKeepAlive(boolean var1);

    public boolean isKeepAliveEnabled();

    public void startEchoCalibration(LinphoneCoreListener var1) throws LinphoneCoreException;

    public boolean needsEchoCalibration();

    public boolean hasBuiltInEchoCanceler();

    public void enableIpv6(boolean var1);

    public boolean isIpv6Enabled();

    public void adjustSoftwareVolume(int var1);

    public boolean pauseCall(LinphoneCall var1);

    public boolean resumeCall(LinphoneCall var1);

    public boolean pauseAllCalls();

    public void setZrtpSecretsCache(String var1);

    public void enableEchoLimiter(boolean var1);

    public boolean isInConference();

    public boolean enterConference();

    public void leaveConference();

    public void addToConference(LinphoneCall var1);

    public void removeFromConference(LinphoneCall var1);

    public void addAllToConference();

    public void terminateConference();

    public int getConferenceSize();

    public void startConferenceRecording(String var1);

    public void stopConferenceRecording();

    public void terminateAllCalls();

    public LinphoneCall[] getCalls();

    public int getCallsNb();

    public void transferCall(LinphoneCall var1, String var2);

    public void transferCallToAnother(LinphoneCall var1, LinphoneCall var2);

    public LinphoneCall startReferedCall(LinphoneCall var1, LinphoneCallParams var2);

    public LinphoneCall findCallFromUri(String var1);

    public int getMaxCalls();

    public void setMaxCalls(int var1);

    public boolean isMyself(String var1);

    public boolean soundResourcesLocked();

    public boolean mediaEncryptionSupported(MediaEncryption var1);

    public void setMediaEncryption(MediaEncryption var1);

    public MediaEncryption getMediaEncryption();

    public void setMediaEncryptionMandatory(boolean var1);

    public boolean isMediaEncryptionMandatory();

    public void setPlayFile(String var1);

    public void tunnelEnable(boolean var1);

    public void tunnelSetMode(TunnelMode var1);

    public TunnelMode tunnelGetMode();

    public void tunnelEnableSip(boolean var1);

    public boolean tunnelSipEnabled();

    public void tunnelAutoDetect();

    public void tunnelCleanServers();

    public void tunnelSetHttpProxy(String var1, int var2, String var3, String var4);

    public void tunnelAddServerAndMirror(String var1, int var2, int var3, int var4);

    public void tunnelAddServer(TunnelConfig var1);

    public TunnelConfig[] tunnelGetServers();

    public boolean isTunnelAvailable();

    public LinphoneProxyConfig[] getProxyConfigList();

    public void setVideoPolicy(boolean var1, boolean var2);

    public boolean getVideoAutoInitiatePolicy();

    public boolean getVideoAutoAcceptPolicy();

    public void setStaticPicture(String var1);

    public void setUserAgent(String var1, String var2);

    public void setCpuCount(int var1);

    public void removeCallLog(LinphoneCallLog var1);

    public int getMissedCallsCount();

    public void resetMissedCallsCount();

    public void refreshRegisters();

    public String getVersion();

    public void removeFriend(LinphoneFriend var1);

    public LinphoneFriend findFriendByAddress(String var1);

    public void setAudioPort(int var1);

    public void setAudioPortRange(int var1, int var2);

    public void setAudioDscp(int var1);

    public int getAudioDscp();

    public void setVideoPort(int var1);

    public void setVideoPortRange(int var1, int var2);

    public void setVideoDscp(int var1);

    public int getVideoDscp();

    public void setIncomingTimeout(int var1);

    public void setInCallTimeout(int var1);

    public void setMicrophoneGain(float var1);

    public void setPrimaryContact(String var1);

    public String getPrimaryContact();

    public void setPrimaryContact(String var1, String var2);

    public String getPrimaryContactUsername();

    public String getPrimaryContactDisplayName();

    public void setUseSipInfoForDtmfs(boolean var1);

    public boolean getUseSipInfoForDtmfs();

    public void setUseRfc2833ForDtmfs(boolean var1);

    public boolean getUseRfc2833ForDtmfs();

    public LpConfig getConfig();

    public boolean upnpAvailable();

    public UpnpState getUpnpState();

    public String getUpnpExternalIpaddress();

    public LinphoneInfoMessage createInfoMessage();

    public LinphoneEvent subscribe(LinphoneAddress var1, String var2, int var3, LinphoneContent var4);

    public LinphoneEvent createSubscribe(LinphoneAddress var1, String var2, int var3);

    public LinphoneEvent createPublish(LinphoneAddress var1, String var2, int var3);

    public LinphoneEvent publish(LinphoneAddress var1, String var2, int var3, LinphoneContent var4);

    public void setChatDatabasePath(String var1);

    public LinphoneChatRoom[] getChatRooms();

    public String[] getSupportedVideoSizes();

    public int migrateToMultiTransport();

    public boolean acceptEarlyMedia(LinphoneCall var1);

    public boolean acceptEarlyMediaWithParams(LinphoneCall var1, LinphoneCallParams var2);

    public LinphoneProxyConfig createProxyConfig();

    public LinphoneProxyConfig createProxyConfig(String var1, String var2, String var3, boolean var4) throws LinphoneCoreException;

    public void setCallErrorTone(Reason var1, String var2);

    public void setTone(ToneID var1, String var2);

    public void setMtu(int var1);

    public int getMtu();

    public void enableSdp200Ack(boolean var1);

    public boolean isSdp200AckEnabled();

    public void disableChat(Reason var1);

    public void enableChat();

    public boolean chatEnabled();

    public void stopRinging();

    public void setAudioJittcomp(int var1);

    public void setVideoJittcomp(int var1);

    public void setFileTransferServer(String var1);

    public String getFileTransferServer();

    public LinphonePlayer createLocalPlayer(AndroidVideoWindowImpl var1);

    public void addListener(LinphoneCoreListener var1);

    public void removeListener(LinphoneCoreListener var1);

    public void setRemoteRingbackTone(String var1);

    public String getRemoteRingbackTone();

    public void uploadLogCollection();

    public void resetLogCollection();

    public void setAudioMulticastAddr(String var1) throws LinphoneCoreException;

    public void setVideoMulticastAddr(String var1) throws LinphoneCoreException;

    public String getAudioMulticastAddr();

    public String getVideoMulticastAddr();

    public void setAudioMulticastTtl(int var1) throws LinphoneCoreException;

    public void setVideoMulticastTtl(int var1) throws LinphoneCoreException;

    public int getAudioMulticastTtl();

    public int getVideoMulticastTtl();

    public void enableAudioMulticast(boolean var1);

    public boolean audioMulticastEnabled();

    public void enableVideoMulticast(boolean var1);

    public boolean videoMulticastEnabled();

    public void enableDnsSrv(boolean var1);

    public boolean dnsSrvEnabled();

    public void setVideoPreset(String var1);

    public String getVideoPreset();

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum TunnelMode {
        disable(0),
        enable(1),
        auto(2);
        
        private final int value;

        private TunnelMode(int value) {
            this.value = value;
        }

        public static int enumToInt(TunnelMode enum_mode) {
            return enum_mode.value;
        }

        public static TunnelMode intToEnum(int value) {
            switch (value) {
                case 0: {
                    return disable;
                }
                case 1: {
                    return enable;
                }
                case 2: {
                    return auto;
                }
            }
            return disable;
        }
    }

    public static class LogCollectionUploadState {
        private static Vector<LogCollectionUploadState> values = new Vector();
        public static LogCollectionUploadState LogCollectionUploadStateInProgress = new LogCollectionUploadState(0, "LinphoneCoreLogCollectionUploadStateInProgress");
        public static LogCollectionUploadState LogCollectionUploadStateDelivered = new LogCollectionUploadState(1, "LinphoneCoreLogCollectionUploadStateDelivered");
        public static LogCollectionUploadState LogCollectionUploadStateNotDelivered = new LogCollectionUploadState(2, "LinphoneCoreLogCollectionUploadStateNotDelivered");
        private final int mValue;
        private final String mStringValue;

        private LogCollectionUploadState(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static LogCollectionUploadState fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                LogCollectionUploadState state = values.elementAt(i);
                if (state.mValue != value) continue;
                return state;
            }
            throw new RuntimeException("state not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public static class UpnpState {
        private static Vector<UpnpState> values = new Vector();
        public static UpnpState Idle = new UpnpState(0, "Idle");
        public static UpnpState Pending = new UpnpState(1, "Pending");
        public static UpnpState Adding = new UpnpState(2, "Adding");
        public static UpnpState Removing = new UpnpState(3, "Removing");
        public static UpnpState NotAvailable = new UpnpState(4, "Not available");
        public static UpnpState Ok = new UpnpState(5, "Ok");
        public static UpnpState Ko = new UpnpState(6, "Ko");
        public static UpnpState Blacklisted = new UpnpState(7, "Blacklisted");
        protected final int mValue;
        private final String mStringValue;

        private UpnpState(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static UpnpState fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                UpnpState mstate = values.elementAt(i);
                if (mstate.mValue != value) continue;
                return mstate;
            }
            throw new RuntimeException("UpnpState not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public static class EcCalibratorStatus {
        private static Vector<EcCalibratorStatus> values = new Vector();
        public static final int IN_PROGRESS_STATUS = 0;
        public static final int DONE_STATUS = 1;
        public static final int FAILED_STATUS = 2;
        public static final int DONE_NO_ECHO_STATUS = 3;
        public static EcCalibratorStatus InProgress = new EcCalibratorStatus(0, "InProgress");
        public static EcCalibratorStatus Done = new EcCalibratorStatus(1, "Done");
        public static EcCalibratorStatus Failed = new EcCalibratorStatus(2, "Failed");
        public static EcCalibratorStatus DoneNoEcho = new EcCalibratorStatus(3, "DoneNoEcho");
        private final int mValue;
        private final String mStringValue;

        private EcCalibratorStatus(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static EcCalibratorStatus fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                EcCalibratorStatus status = values.elementAt(i);
                if (status.mValue != value) continue;
                return status;
            }
            throw new RuntimeException("status not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }

        public int value() {
            return this.mValue;
        }
    }

    public static final class AdaptiveRateAlgorithm {
        private static Vector<AdaptiveRateAlgorithm> values = new Vector();
        public static final AdaptiveRateAlgorithm Simple = new AdaptiveRateAlgorithm(0, "Simple");
        public static final AdaptiveRateAlgorithm Stateful = new AdaptiveRateAlgorithm(1, "Stateful");
        protected final int mValue;
        private final String mStringValue;

        private AdaptiveRateAlgorithm(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static AdaptiveRateAlgorithm fromString(String value) {
            for (int i = 0; i < values.size(); ++i) {
                AdaptiveRateAlgorithm alg = values.elementAt(i);
                if (!alg.mStringValue.equalsIgnoreCase(value)) continue;
                return alg;
            }
            throw new RuntimeException("AdaptiveRateAlgorithm not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public static final class MediaEncryption {
        private static Vector<MediaEncryption> values = new Vector();
        public static final MediaEncryption None = new MediaEncryption(0, "None");
        public static final MediaEncryption SRTP = new MediaEncryption(1, "SRTP");
        public static final MediaEncryption ZRTP = new MediaEncryption(2, "ZRTP");
        public static final MediaEncryption DTLS = new MediaEncryption(3, "DTLS");
        protected final int mValue;
        private final String mStringValue;

        private MediaEncryption(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static MediaEncryption fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                MediaEncryption menc = values.elementAt(i);
                if (menc.mValue != value) continue;
                return menc;
            }
            throw new RuntimeException("MediaEncryption not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public static class Transports {
        public int udp;
        public int tcp;
        public int tls;

        public Transports() {
        }

        public Transports(Transports t) {
            this.udp = t.udp;
            this.tcp = t.tcp;
            this.tls = t.tls;
        }

        public String toString() {
            return "udp[" + this.udp + "] tcp[" + this.tcp + "] tls[" + this.tls + "]";
        }
    }

    public static class FirewallPolicy {
        private static Vector<FirewallPolicy> values = new Vector();
        public static FirewallPolicy NoFirewall = new FirewallPolicy(0, "NoFirewall");
        public static FirewallPolicy UseNatAddress = new FirewallPolicy(1, "UseNatAddress");
        public static FirewallPolicy UseStun = new FirewallPolicy(2, "UseStun");
        public static FirewallPolicy UseIce = new FirewallPolicy(3, "UseIce");
        public static FirewallPolicy UseUpnp = new FirewallPolicy(4, "UseUpnp");
        private final int mValue;
        private final String mStringValue;

        private FirewallPolicy(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static FirewallPolicy fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                FirewallPolicy state = values.elementAt(i);
                if (state.mValue != value) continue;
                return state;
            }
            throw new RuntimeException("state not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }

        public int value() {
            return this.mValue;
        }
    }

    public static class RegistrationState {
        private static Vector<RegistrationState> values = new Vector();
        public static RegistrationState RegistrationNone = new RegistrationState(0, "RegistrationNone");
        public static RegistrationState RegistrationProgress = new RegistrationState(1, "RegistrationProgress");
        public static RegistrationState RegistrationOk = new RegistrationState(2, "RegistrationOk");
        public static RegistrationState RegistrationCleared = new RegistrationState(3, "RegistrationCleared");
        public static RegistrationState RegistrationFailed = new RegistrationState(4, "RegistrationFailed");
        private final int mValue;
        private final String mStringValue;

        private RegistrationState(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static RegistrationState fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                RegistrationState state = values.elementAt(i);
                if (state.mValue != value) continue;
                return state;
            }
            throw new RuntimeException("state not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public static class RemoteProvisioningState {
        private static Vector<RemoteProvisioningState> values = new Vector();
        public static RemoteProvisioningState ConfiguringSuccessful = new RemoteProvisioningState(0, "ConfiguringSuccessful");
        public static RemoteProvisioningState ConfiguringFailed = new RemoteProvisioningState(1, "ConfiguringFailed");
        public static RemoteProvisioningState ConfiguringSkipped = new RemoteProvisioningState(2, "ConfiguringSkipped");
        private final int mValue;
        private final String mStringValue;

        private RemoteProvisioningState(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static RemoteProvisioningState fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                RemoteProvisioningState state = values.elementAt(i);
                if (state.mValue != value) continue;
                return state;
            }
            throw new RuntimeException("state not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public static class GlobalState {
        private static Vector<GlobalState> values = new Vector();
        public static GlobalState GlobalOff = new GlobalState(0, "GlobalOff");
        public static GlobalState GlobalStartup = new GlobalState(1, "GlobalStartup");
        public static GlobalState GlobalOn = new GlobalState(2, "GlobalOn");
        public static GlobalState GlobalShutdown = new GlobalState(3, "GlobalShutdown");
        public static GlobalState GlobalConfiguring = new GlobalState(4, "GlobalConfiguring");
        private final int mValue;
        private final String mStringValue;

        private GlobalState(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static GlobalState fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                GlobalState state = values.elementAt(i);
                if (state.mValue != value) continue;
                return state;
            }
            throw new RuntimeException("state not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

}

