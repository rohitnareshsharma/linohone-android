/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceActivity;
import org.linphone.core.PresenceNote;
import org.linphone.core.PresencePerson;

public class PresencePersonImpl
implements PresencePerson {
    private long mNativePtr;

    protected PresencePersonImpl(long nativePtr) {
        this.mNativePtr = nativePtr;
    }

    private native long newPresencePersonImpl(String var1);

    protected PresencePersonImpl(String id2) {
        this.mNativePtr = this.newPresencePersonImpl(id2);
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

    private native long getNbActivities(long var1);

    public long getNbActivities() {
        return this.getNbActivities(this.mNativePtr);
    }

    private native Object getNthActivity(long var1, long var3);

    public PresenceActivity getNthActivity(long idx) {
        return (PresenceActivity)this.getNthActivity(this.mNativePtr, idx);
    }

    private native int addActivity(long var1, long var3);

    public int addActivity(PresenceActivity activity) {
        return this.addActivity(this.mNativePtr, activity.getNativePtr());
    }

    private native int clearActivities(long var1);

    public int clearActivities() {
        return this.clearActivities(this.mNativePtr);
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

    private native long getNbActivitiesNotes(long var1);

    public long getNbActivitiesNotes() {
        return this.getNbActivitiesNotes(this.mNativePtr);
    }

    private native Object getNthActivitiesNote(long var1, long var3);

    public PresenceNote getNthActivitiesNote(long idx) {
        return (PresenceNote)this.getNthActivitiesNote(this.mNativePtr, idx);
    }

    private native int addActivitiesNote(long var1, long var3);

    public int addActivitiesNote(PresenceNote note) {
        return this.addActivitiesNote(this.mNativePtr, note.getNativePtr());
    }

    private native int clearActivitesNotes(long var1);

    public int clearActivitesNotes() {
        return this.clearActivitesNotes(this.mNativePtr);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }
}

