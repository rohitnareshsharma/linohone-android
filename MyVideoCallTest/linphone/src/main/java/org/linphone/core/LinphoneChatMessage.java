/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;
import org.linphone.core.ErrorInfo;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneBuffer;
import org.linphone.core.LinphoneContent;
import org.linphone.core.Reason;

public interface LinphoneChatMessage {
    public String getText();

    public LinphoneAddress getPeerAddress();

    public LinphoneAddress getFrom();

    public LinphoneAddress getTo();

    public String getExternalBodyUrl();

    public void setExternalBodyUrl(String var1);

    public void addCustomHeader(String var1, String var2);

    public String getCustomHeader(String var1);

    public long getTime();

    public State getStatus();

    public boolean isRead();

    public boolean isOutgoing();

    public void store();

    public int getStorageId();

    public Reason getReason();

    public ErrorInfo getErrorInfo();

    public void cancelFileTransfer();

    public LinphoneContent getFileTransferInformation();

    public void setAppData(String var1);

    public String getAppData();

    public void setFileTransferFilepath(String var1);

    public void downloadFile();

    public void setListener(LinphoneChatMessageListener var1);

    public static class State {
        private static Vector<State> values = new Vector();
        private final int mValue;
        private final String mStringValue;
        public static final State Idle = new State(0, "Idle");
        public static final State InProgress = new State(1, "InProgress");
        public static final State Delivered = new State(2, "Delivered");
        public static final State NotDelivered = new State(3, "NotDelivered");
        public static final State FileTransferError = new State(4, "FileTransferError");
        public static final State FileTransferDone = new State(5, "FileTransferDone");

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

        public int toInt() {
            return this.mValue;
        }
    }

    public static interface LinphoneChatMessageListener {
        public void onLinphoneChatMessageStateChanged(LinphoneChatMessage var1, State var2);

        public void onLinphoneChatMessageFileTransferReceived(LinphoneChatMessage var1, LinphoneContent var2, LinphoneBuffer var3);

        public void onLinphoneChatMessageFileTransferSent(LinphoneChatMessage var1, LinphoneContent var2, int var3, int var4, LinphoneBuffer var5);

        public void onLinphoneChatMessageFileTransferProgressChanged(LinphoneChatMessage var1, LinphoneContent var2, int var3, int var4);
    }

    @Deprecated
    public static interface StateListener {
        public void onLinphoneChatMessageStateChanged(LinphoneChatMessage var1, State var2);
    }

}

