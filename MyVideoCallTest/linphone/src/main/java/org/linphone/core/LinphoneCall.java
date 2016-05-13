/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;
import org.linphone.core.CallDirection;
import org.linphone.core.ErrorInfo;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCallLog;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCallStats;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphonePlayer;
import org.linphone.core.Reason;

public interface LinphoneCall {
    public State getState();

    public LinphoneAddress getRemoteAddress();

    public CallDirection getDirection();

    public LinphoneCallLog getCallLog();

    public LinphoneCallStats getAudioStats();

    public LinphoneCallStats getVideoStats();

    public LinphoneCallParams getRemoteParams();

    public LinphoneCallParams getCurrentParamsCopy();

    public void enableCamera(boolean var1);

    public boolean cameraEnabled();

    public void enableEchoCancellation(boolean var1);

    public boolean isEchoCancellationEnabled();

    public void enableEchoLimiter(boolean var1);

    public boolean isEchoLimiterEnabled();

    public LinphoneCall getReplacedCall();

    public int getDuration();

    public float getCurrentQuality();

    public float getAverageQuality();

    public String getAuthenticationToken();

    public boolean isAuthenticationTokenVerified();

    public void setAuthenticationTokenVerified(boolean var1);

    public boolean isInConference();

    public boolean mediaInProgress();

    public float getPlayVolume();

    public String getRemoteUserAgent();

    public String getRemoteContact();

    public void takeSnapshot(String var1);

    public void zoomVideo(float var1, float var2, float var3);

    public void startRecording();

    public void stopRecording();

    public State getTransferState();

    public void sendInfoMessage(LinphoneInfoMessage var1);

    public LinphoneCall getTransfererCall();

    public LinphoneCall getTransferTargetCall();

    public Reason getReason();

    public ErrorInfo getErrorInfo();

    public void setUserData(Object var1);

    public Object getUserData();

    public LinphonePlayer getPlayer();

    public static class State {
        private static Vector<State> values = new Vector();
        private final int mValue;
        private final String mStringValue;
        public static final State Idle = new State(0, "Idle");
        public static final State IncomingReceived = new State(1, "IncomingReceived");
        public static final State OutgoingInit = new State(2, "OutgoingInit");
        public static final State OutgoingProgress = new State(3, "OutgoingProgress");
        public static final State OutgoingRinging = new State(4, "OutgoingRinging");
        public static final State OutgoingEarlyMedia = new State(5, "OutgoingEarlyMedia");
        public static final State Connected = new State(6, "Connected");
        public static final State StreamsRunning = new State(7, "StreamsRunning");
        public static final State Pausing = new State(8, "Pausing");
        public static final State Paused = new State(9, "Paused");
        public static final State Resuming = new State(10, "Resuming");
        public static final State Refered = new State(11, "Refered");
        public static final State Error = new State(12, "Error");
        public static final State CallEnd = new State(13, "CallEnd");
        public static final State PausedByRemote = new State(14, "PausedByRemote");
        public static final State CallUpdatedByRemote = new State(15, "UpdatedByRemote");
        public static final State CallIncomingEarlyMedia = new State(16, "IncomingEarlyMedia");
        public static final State CallUpdating = new State(17, "Updating");
        public static final State CallReleased = new State(18, "Released");
        public static final State CallEarlyUpdatedByRemote = new State(19, "EarlyUpdatedByRemote");
        public static final State CallEarlyUpdating = new State(20, "EarlyUpdating");

        public final int value() {
            return this.mValue;
        }

        private State(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static State fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                State state = values.elementAt(i);
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

