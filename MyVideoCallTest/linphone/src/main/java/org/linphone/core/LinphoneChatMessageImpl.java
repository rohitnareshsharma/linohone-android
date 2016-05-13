/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.io.UnsupportedEncodingException;
import org.linphone.core.ErrorInfo;
import org.linphone.core.ErrorInfoImpl;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddressImpl;
import org.linphone.core.LinphoneChatMessage;
import org.linphone.core.LinphoneContent;
import org.linphone.core.Reason;

public class LinphoneChatMessageImpl
implements LinphoneChatMessage {
    protected final long nativePtr;

    private native byte[] getText(long var1);

    private native long getPeerAddress(long var1);

    private native String getExternalBodyUrl(long var1);

    private native void setExternalBodyUrl(long var1, String var3);

    private native long getFrom(long var1);

    private native long getTime(long var1);

    private native int getStatus(long var1);

    private native boolean isRead(long var1);

    private native boolean isOutgoing(long var1);

    private native void store(long var1);

    private native int getStorageId(long var1);

    private native void setFileTransferFilepath(long var1, String var3);

    private native void downloadFile(long var1);

    private native void setListener(long var1, LinphoneChatMessage.LinphoneChatMessageListener var3);

    private native void unref(long var1);

    protected LinphoneChatMessageImpl(long aNativePtr) {
        this.nativePtr = aNativePtr;
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public String getText() {
        try {
            byte[] rawText = this.getText(this.nativePtr);
            if (rawText != null) {
                return new String(rawText, "UTF-8");
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinphoneAddress getPeerAddress() {
        return new LinphoneAddressImpl(this.getPeerAddress(this.nativePtr), LinphoneAddressImpl.WrapMode.FromConst);
    }

    public String getExternalBodyUrl() {
        return this.getExternalBodyUrl(this.nativePtr);
    }

    public void setExternalBodyUrl(String url) {
        this.setExternalBodyUrl(this.nativePtr, url);
    }

    public LinphoneAddress getFrom() {
        return new LinphoneAddressImpl(this.getFrom(this.nativePtr), LinphoneAddressImpl.WrapMode.FromConst);
    }

    private native long getTo(long var1);

    public LinphoneAddress getTo() {
        return new LinphoneAddressImpl(this.getTo(this.nativePtr), LinphoneAddressImpl.WrapMode.FromConst);
    }

    private native void addCustomHeader(long var1, String var3, String var4);

    public void addCustomHeader(String name, String value) {
        this.addCustomHeader(this.nativePtr, name, value);
    }

    private native String getCustomHeader(long var1, String var3);

    public String getCustomHeader(String name) {
        return this.getCustomHeader(this.nativePtr, name);
    }

    public long getTime() {
        return this.getTime(this.nativePtr) * 1000;
    }

    public LinphoneChatMessage.State getStatus() {
        return LinphoneChatMessage.State.fromInt(this.getStatus(this.nativePtr));
    }

    public boolean isRead() {
        return this.isRead(this.nativePtr);
    }

    public boolean isOutgoing() {
        return this.isOutgoing(this.nativePtr);
    }

    public void store() {
        this.store(this.nativePtr);
    }

    public int getStorageId() {
        return this.getStorageId(this.nativePtr);
    }

    private native int getReason(long var1);

    public Reason getReason() {
        return Reason.fromInt(this.getReason(this.nativePtr));
    }

    private native long getErrorInfo(long var1);

    public ErrorInfo getErrorInfo() {
        return new ErrorInfoImpl(this.getErrorInfo(this.nativePtr));
    }

    protected void finalize() throws Throwable {
        this.unref(this.nativePtr);
        super.finalize();
    }

    private native Object getFileTransferInformation(long var1);

    public LinphoneContent getFileTransferInformation() {
        return (LinphoneContent)this.getFileTransferInformation(this.nativePtr);
    }

    private native void setAppData(long var1, String var3);

    public void setAppData(String data) {
        this.setAppData(this.nativePtr, data);
    }

    private native String getAppData(long var1);

    public String getAppData() {
        return this.getAppData(this.nativePtr);
    }

    private native void cancelFileTransfer(long var1);

    public void cancelFileTransfer() {
        this.cancelFileTransfer(this.nativePtr);
    }

    public void setFileTransferFilepath(String path) {
        this.setFileTransferFilepath(this.nativePtr, path);
    }

    public void downloadFile() {
        this.downloadFile(this.nativePtr);
    }

    public void setListener(LinphoneChatMessage.LinphoneChatMessageListener listener) {
        this.setListener(this.nativePtr, listener);
    }
}

