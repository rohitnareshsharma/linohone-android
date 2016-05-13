/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceActivity;
import org.linphone.core.PresenceActivityType;
import org.linphone.core.PresenceBasicStatus;
import org.linphone.core.PresenceModel;
import org.linphone.core.PresenceNote;
import org.linphone.core.PresencePerson;
import org.linphone.core.PresenceService;

public class PresenceModelImpl
implements PresenceModel {
    private long mNativePtr;

    protected PresenceModelImpl(long nativePtr) {
        this.mNativePtr = nativePtr;
    }

    private native long newPresenceModelImpl();

    protected PresenceModelImpl() {
        this.mNativePtr = this.newPresenceModelImpl();
    }

    private native long newPresenceModelImpl(int var1, String var2);

    protected PresenceModelImpl(PresenceActivityType type, String description) {
        this.mNativePtr = this.newPresenceModelImpl(type.toInt(), description);
    }

    private native long newPresenceModelImpl(int var1, String var2, String var3, String var4);

    protected PresenceModelImpl(PresenceActivityType type, String description, String note, String lang) {
        this.mNativePtr = this.newPresenceModelImpl(type.toInt(), description, note, lang);
    }

    private native void unref(long var1);

    protected void finalize() {
        this.unref(this.mNativePtr);
    }

    private native int getBasicStatus(long var1);

    public PresenceBasicStatus getBasicStatus() {
        return PresenceBasicStatus.fromInt(this.getBasicStatus(this.mNativePtr));
    }

    private native int setBasicStatus(long var1, int var3);

    public int setBasicStatus(PresenceBasicStatus basic_status) {
        return this.setBasicStatus(this.mNativePtr, basic_status.toInt());
    }

    private native long getTimestamp(long var1);

    public long getTimestamp() {
        return this.getTimestamp(this.mNativePtr);
    }

    private native String getContact(long var1);

    public String getContact() {
        return this.getContact(this.mNativePtr);
    }

    private native void setContact(long var1, String var3);

    public void setContact(String contact) {
        this.setContact(this.mNativePtr, contact);
    }

    private native Object getActivity(long var1);

    public PresenceActivity getActivity() {
        return (PresenceActivity)this.getActivity(this.mNativePtr);
    }

    private native int setActivity(long var1, int var3, String var4);

    public int setActivity(PresenceActivityType activity, String description) {
        return this.setActivity(this.mNativePtr, activity.toInt(), description);
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

    private native Object getNote(long var1, String var3);

    public PresenceNote getNote(String lang) {
        return (PresenceNote)this.getNote(this.mNativePtr, lang);
    }

    private native int addNote(long var1, String var3, String var4);

    public int addNote(String note_content, String lang) {
        return this.addNote(this.mNativePtr, note_content, lang);
    }

    private native int clearNotes(long var1);

    public int clearNotes() {
        return this.clearNotes(this.mNativePtr);
    }

    private native long getNbServices(long var1);

    public long getNbServices() {
        return this.getNbServices(this.mNativePtr);
    }

    private native Object getNthService(long var1, long var3);

    public PresenceService getNthService(long idx) {
        return (PresenceService)this.getNthService(this.mNativePtr, idx);
    }

    private native int addService(long var1, long var3);

    public int addService(PresenceService service) {
        return this.addService(this.mNativePtr, service.getNativePtr());
    }

    private native int clearServices(long var1);

    public int clearServices() {
        return this.clearServices(this.mNativePtr);
    }

    private native long getNbPersons(long var1);

    public long getNbPersons() {
        return this.getNbPersons(this.mNativePtr);
    }

    private native Object getNthPerson(long var1, long var3);

    public PresencePerson getNthPerson(long idx) {
        return (PresencePerson)this.getNthPerson(this.mNativePtr, idx);
    }

    private native int addPerson(long var1, long var3);

    public int addPerson(PresencePerson person) {
        return this.addPerson(this.mNativePtr, person.getNativePtr());
    }

    private native int clearPersons(long var1);

    public int clearPersons() {
        return this.clearPersons(this.mNativePtr);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }
}

