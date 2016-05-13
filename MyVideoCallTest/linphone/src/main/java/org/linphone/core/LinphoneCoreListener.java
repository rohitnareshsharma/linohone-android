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
import org.linphone.core.LinphoneEvent;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.PublishState;
import org.linphone.core.SubscriptionState;

public interface LinphoneCoreListener {
    public void authInfoRequested(LinphoneCore var1, String var2, String var3, String var4);

    public void callStatsUpdated(LinphoneCore var1, LinphoneCall var2, LinphoneCallStats var3);

    public void newSubscriptionRequest(LinphoneCore var1, LinphoneFriend var2, String var3);

    public void notifyPresenceReceived(LinphoneCore var1, LinphoneFriend var2);

    public void dtmfReceived(LinphoneCore var1, LinphoneCall var2, int var3);

    public void notifyReceived(LinphoneCore var1, LinphoneCall var2, LinphoneAddress var3, byte[] var4);

    public void transferState(LinphoneCore var1, LinphoneCall var2, LinphoneCall.State var3);

    public void infoReceived(LinphoneCore var1, LinphoneCall var2, LinphoneInfoMessage var3);

    public void subscriptionStateChanged(LinphoneCore var1, LinphoneEvent var2, SubscriptionState var3);

    public void publishStateChanged(LinphoneCore var1, LinphoneEvent var2, PublishState var3);

    public void show(LinphoneCore var1);

    public void displayStatus(LinphoneCore var1, String var2);

    public void displayMessage(LinphoneCore var1, String var2);

    public void displayWarning(LinphoneCore var1, String var2);

    public void fileTransferProgressIndication(LinphoneCore var1, LinphoneChatMessage var2, LinphoneContent var3, int var4);

    public void fileTransferRecv(LinphoneCore var1, LinphoneChatMessage var2, LinphoneContent var3, byte[] var4, int var5);

    public int fileTransferSend(LinphoneCore var1, LinphoneChatMessage var2, LinphoneContent var3, ByteBuffer var4, int var5);

    public void globalState(LinphoneCore var1, LinphoneCore.GlobalState var2, String var3);

    public void registrationState(LinphoneCore var1, LinphoneProxyConfig var2, LinphoneCore.RegistrationState var3, String var4);

    public void configuringStatus(LinphoneCore var1, LinphoneCore.RemoteProvisioningState var2, String var3);

    public void messageReceived(LinphoneCore var1, LinphoneChatRoom var2, LinphoneChatMessage var3);

    public void callState(LinphoneCore var1, LinphoneCall var2, LinphoneCall.State var3, String var4);

    public void callEncryptionChanged(LinphoneCore var1, LinphoneCall var2, boolean var3, String var4);

    public void notifyReceived(LinphoneCore var1, LinphoneEvent var2, String var3, LinphoneContent var4);

    public void isComposingReceived(LinphoneCore var1, LinphoneChatRoom var2);

    public void ecCalibrationStatus(LinphoneCore var1, LinphoneCore.EcCalibratorStatus var2, int var3, Object var4);

    public void uploadProgressIndication(LinphoneCore var1, int var2, int var3);

    public void uploadStateChanged(LinphoneCore var1, LinphoneCore.LogCollectionUploadState var2, String var3);
}

