/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceNote;

public class PresenceNoteImpl
implements PresenceNote {
    private long mNativePtr;

    protected PresenceNoteImpl(long nativePtr) {
        this.mNativePtr = nativePtr;
    }

    private native long newPresenceNoteImpl(String var1, String var2);

    protected PresenceNoteImpl(String content, String lang) {
        this.mNativePtr = this.newPresenceNoteImpl(content, lang);
    }

    private native void unref(long var1);

    protected void finalize() {
        this.unref(this.mNativePtr);
    }

    private native String getContent(long var1);

    public String getContent() {
        return this.getContent(this.mNativePtr);
    }

    private native int setContent(long var1, String var3);

    public int setContent(String content) {
        return this.setContent(this.mNativePtr, content);
    }

    private native String getLang(long var1);

    public String getLang() {
        return this.getLang(this.mNativePtr);
    }

    private native int setLang(long var1, String var3);

    public int setLang(String lang) {
        return this.setLang(this.mNativePtr, lang);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }
}

