/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneInfoMessage;

public class LinphoneInfoMessageImpl
implements LinphoneInfoMessage {
    protected long nativePtr;
    private LinphoneContent mContent;

    private native Object getContent(long var1);

    public LinphoneInfoMessageImpl(long ptr) {
        this.nativePtr = ptr;
        this.mContent = (LinphoneContent)this.getContent(this.nativePtr);
    }

    private native void setContent(long var1, String var3, String var4, String var5);

    public void setContent(LinphoneContent content) {
        this.mContent = content;
        this.setContent(this.nativePtr, this.mContent.getType(), this.mContent.getSubtype(), this.mContent.getDataAsString());
    }

    public LinphoneContent getContent() {
        return this.mContent;
    }

    private native void addHeader(long var1, String var3, String var4);

    public void addHeader(String name, String value) {
        this.addHeader(this.nativePtr, name, value);
    }

    private native String getHeader(long var1, String var3);

    public String getHeader(String name) {
        return this.getHeader(this.nativePtr, name);
    }

    private native void delete(long var1);

    protected void finalize() {
        this.delete(this.nativePtr);
    }
}

