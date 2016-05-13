/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceBasicStatus;
import org.linphone.core.PresenceNote;
import org.linphone.core.PresenceService;

public class PresenceServiceImpl
implements PresenceService {
    private long mNativePtr;

    protected PresenceServiceImpl(long nativePtr) {
        this.mNativePtr = nativePtr;
    }

    private native long newPresenceServiceImpl(String var1, int var2, String var3);

    protected PresenceServiceImpl(String id2, PresenceBasicStatus status, String contact) {
        this.mNativePtr = this.newPresenceServiceImpl(id2, status.toInt(), contact);
    }

    private native void unref(long var1);

    protected void finalize() {
        this.unref(this.mNativePtr);
    }

    private native String getId(long var1);

    public String getId() {
        return this.getId(this.mNativePtr);
    }

    private native int setId(long var1, String var3);

    public int setId(String id2) {
        return this.setId(this.mNativePtr, id2);
    }

    private native int getBasicStatus(long var1);

    public PresenceBasicStatus getBasicStatus() {
        return PresenceBasicStatus.fromInt(this.getBasicStatus(this.mNativePtr));
    }

    private native int setBasicStatus(long var1, int var3);

    public int setBasicStatus(PresenceBasicStatus status) {
        return this.setBasicStatus(this.mNativePtr, status.toInt());
    }

    private native String getContact(long var1);

    public String getContact() {
        return this.getContact(this.mNativePtr);
    }

    private native int setContact(long var1, String var3);

    public int setContact(String contact) {
        return this.setContact(this.mNativePtr, contact);
    }

    private native long getNbNotes(long var1);

    public long getNbNotes() {
        return this.getNbNotes(this.mNativePtr);
    }

    private native Object getNthNote(long var1, long var3);

    public PresenceNote getNthNote(long idx) {
        return (PresenceNote)this.getNthNote(this.mNativePtr, idx);
    }

    private native int addNote(long var1, long var3);

    public int addNote(PresenceNote note) {
        return this.addNote(this.mNativePtr, note.getNativePtr());
    }

    private native int clearNotes(long var1);

    public int clearNotes() {
        return this.clearNotes(this.mNativePtr);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }
}

