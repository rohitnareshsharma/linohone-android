/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.nio.ByteBuffer;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallStats;
import org.linphone.core.LinphoneChatMessage;
import org.linphone.core.LinphoneChatRoom;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneEvent;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.PublishState;
import org.linphone.core.SubscriptionState;

public class LinphoneCoreListenerBase
implements LinphoneCoreListener {
    public void authInfoRequested(LinphoneCore lc, String realm, String username, String Domain) {
    }

    public void callStatsUpdated(LinphoneCore lc, LinphoneCall call, LinphoneCallStats stats) {
    }

    public void newSubscriptionRequest(LinphoneCore lc, LinphoneFriend lf, String url) {
    }

    public void notifyPresenceReceived(LinphoneCore lc, LinphoneFriend lf) {
    }

    public void dtmfReceived(LinphoneCore lc, LinphoneCall call, int dtmf) {
    }

    public void notifyReceived(LinphoneCore lc, LinphoneCall call, LinphoneAddress from, byte[] event) {
    }

    public void transferState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State new_call_state) {
    }

    public void infoReceived(LinphoneCore lc, LinphoneCall call, LinphoneInfoMessage info) {
    }

    public void subscriptionStateChanged(LinphoneCore lc, LinphoneEvent ev, SubscriptionState state) {
    }

    public void publishStateChanged(LinphoneCore lc, LinphoneEvent ev, PublishState state) {
    }

    public void show(LinphoneCore lc) {
    }

    public void displayStatus(LinphoneCore lc, String message) {
    }

    public void displayMessage(LinphoneCore lc, String message) {
    }

    public void displayWarning(LinphoneCore lc, String message) {
    }

    public void fileTransferProgressIndication(LinphoneCore lc, LinphoneChatMessage message, LinphoneContent content, int progress) {
    }

    public void fileTransferRecv(LinphoneCore lc, LinphoneChatMessage message, LinphoneContent content, byte[] buffer, int size) {
    }

    public int fileTransferSend(LinphoneCore lc, LinphoneChatMessage message, LinphoneContent content, ByteBuffer buffer, int size) {
        return 0;
    }

    public void globalState(LinphoneCore lc, LinphoneCore.GlobalState state, String message) {
    }

    public void registrationState(LinphoneCore lc, LinphoneProxyConfig cfg, LinphoneCore.RegistrationState state, String smessage) {
    }

    public void configuringStatus(LinphoneCore lc, LinphoneCore.RemoteProvisioningState state, String message) {
    }

    public void messageReceived(LinphoneCore lc, LinphoneChatRoom cr, LinphoneChatMessage message) {
    }

    public void callState(LinphoneCore lc, LinphoneCall call, LinphoneCall.State state, String message) {
    }

    public void callEncryptionChanged(LinphoneCore lc, LinphoneCall call, boolean encrypted, String authenticationToken) {
    }

    public void notifyReceived(LinphoneCore lc, LinphoneEvent ev, String eventName, LinphoneContent content) {
    }

    public void isComposingReceived(LinphoneCore lc, LinphoneChatRoom cr) {
    }

    public void ecCalibrationStatus(LinphoneCore lc, LinphoneCore.EcCalibratorStatus status, int delay_ms, Object data) {
    }

    public void uploadProgressIndication(LinphoneCore lc, int offset, int total) {
    }

    public void uploadStateChanged(LinphoneCore lc, LinphoneCore.LogCollectionUploadState state, String info) {
    }
}

