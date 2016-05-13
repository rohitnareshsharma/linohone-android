/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddressImpl;
import org.linphone.core.LinphoneChatMessage;
import org.linphone.core.LinphoneChatMessageImpl;
import org.linphone.core.LinphoneChatRoom;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore;

class LinphoneChatRoomImpl
implements LinphoneChatRoom {
    protected final long nativePtr;

    private native long createLinphoneChatMessage(long var1, String var3);

    private native long getPeerAddress(long var1);

    private native void sendMessage(long var1, String var3);

    private native void sendMessage2(long var1, Object var3, long var4, LinphoneChatMessage.StateListener var6);

    private native long[] getHistoryRange(long var1, int var3, int var4);

    private native long[] getHistory(long var1, int var3);

    private native void destroy(long var1);

    private native int getUnreadMessagesCount(long var1);

    private native int getHistorySize(long var1);

    private native void deleteHistory(long var1);

    private native void compose(long var1);

    private native boolean isRemoteComposing(long var1);

    private native void markAsRead(long var1);

    private native void deleteMessage(long var1, long var3);

    private native long createLinphoneChatMessage2(long var1, String var3, String var4, int var5, long var6, boolean var8, boolean var9);

    private native void sendChatMessage(long var1, Object var3, long var4);

    protected LinphoneChatRoomImpl(long aNativePtr) {
        this.nativePtr = aNativePtr;
    }

    public LinphoneAddress getPeerAddress() {
        return new LinphoneAddressImpl(this.getPeerAddress(this.nativePtr), LinphoneAddressImpl.WrapMode.FromConst);
    }

    public void sendMessage(String message) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.sendMessage(this.nativePtr, message);
        }
    }

    public void sendMessage(LinphoneChatMessage message, LinphoneChatMessage.StateListener listener) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.sendMessage2(this.nativePtr, message, ((LinphoneChatMessageImpl)message).getNativePtr(), listener);
        }
    }

    public LinphoneChatMessage createLinphoneChatMessage(String message) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            return new LinphoneChatMessageImpl(this.createLinphoneChatMessage(this.nativePtr, message));
        }
    }

    public LinphoneChatMessage[] getHistory() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            return this.getHistory(0);
        }
    }

    public LinphoneChatMessage[] getHistoryRange(int begin, int end) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            long[] typesPtr = this.getHistoryRange(this.nativePtr, begin, end);
            return this.getHistoryPrivate(typesPtr);
        }
    }

    public LinphoneChatMessage[] getHistory(int limit) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            long[] typesPtr = this.getHistory(this.nativePtr, limit);
            return this.getHistoryPrivate(typesPtr);
        }
    }

    public void destroy() {
        this.destroy(this.nativePtr);
    }

    public int getUnreadMessagesCount() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            return this.getUnreadMessagesCount(this.nativePtr);
        }
    }

    public int getHistorySize() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            return this.getHistorySize(this.nativePtr);
        }
    }

    public void deleteHistory() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.deleteHistory(this.nativePtr);
        }
    }

    public void compose() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.compose(this.nativePtr);
        }
    }

    public boolean isRemoteComposing() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            return this.isRemoteComposing(this.nativePtr);
        }
    }

    public void markAsRead() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.markAsRead(this.nativePtr);
        }
    }

    public void deleteMessage(LinphoneChatMessage message) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            if (message != null) {
                this.deleteMessage(this.nativePtr, ((LinphoneChatMessageImpl)message).getNativePtr());
            }
        }
    }

    public LinphoneChatMessage createLinphoneChatMessage(String message, String url, LinphoneChatMessage.State state, long timestamp, boolean isRead, boolean isIncoming) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            return new LinphoneChatMessageImpl(this.createLinphoneChatMessage2(this.nativePtr, message, url, state.value(), timestamp / 1000, isRead, isIncoming));
        }
    }

    private native Object getCore(long var1);

    public synchronized LinphoneCore getCore() {
        return (LinphoneCore)this.getCore(this.nativePtr);
    }

    private LinphoneChatMessage[] getHistoryPrivate(long[] typesPtr) {
        if (typesPtr == null) {
            return null;
        }
        LinphoneChatMessage[] messages = new LinphoneChatMessage[typesPtr.length];
        for (int i = 0; i < messages.length; ++i) {
            messages[i] = new LinphoneChatMessageImpl(typesPtr[i]);
        }
        return messages;
    }

    private native long createFileTransferMessage(long var1, String var3, String var4, String var5, int var6);

    public LinphoneChatMessage createFileTransferMessage(LinphoneContent content) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            return new LinphoneChatMessageImpl(this.createFileTransferMessage(this.nativePtr, content.getName(), content.getType(), content.getSubtype(), content.getRealSize()));
        }
    }

    public void sendChatMessage(LinphoneChatMessage message) {
        this.sendChatMessage(this.nativePtr, message, ((LinphoneChatMessageImpl)message).getNativePtr());
    }
}

