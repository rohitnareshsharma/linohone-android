/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneChatMessage;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore;

public interface LinphoneChatRoom {
    public LinphoneAddress getPeerAddress();

    public void sendMessage(String var1);

    @Deprecated
    public void sendMessage(LinphoneChatMessage var1, LinphoneChatMessage.StateListener var2);

    public LinphoneChatMessage createLinphoneChatMessage(String var1);

    public LinphoneChatMessage[] getHistory();

    public LinphoneChatMessage[] getHistory(int var1);

    public LinphoneChatMessage[] getHistoryRange(int var1, int var2);

    public void destroy();

    public int getUnreadMessagesCount();

    public int getHistorySize();

    public void deleteHistory();

    public void compose();

    public boolean isRemoteComposing();

    public void markAsRead();

    public void deleteMessage(LinphoneChatMessage var1);

    public LinphoneChatMessage createLinphoneChatMessage(String var1, String var2, LinphoneChatMessage.State var3, long var4, boolean var6, boolean var7);

    public LinphoneCore getCore();

    public LinphoneChatMessage createFileTransferMessage(LinphoneContent var1);

    public void sendChatMessage(LinphoneChatMessage var1);
}

