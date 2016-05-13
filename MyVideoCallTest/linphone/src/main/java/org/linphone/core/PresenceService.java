/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceBasicStatus;
import org.linphone.core.PresenceNote;

public interface PresenceService {
    public String getId();

    public int setId(String var1);

    public PresenceBasicStatus getBasicStatus();

    public int setBasicStatus(PresenceBasicStatus var1);

    public String getContact();

    public int setContact(String var1);

    public long getNbNotes();

    public PresenceNote getNthNote(long var1);

    public int addNote(PresenceNote var1);

    public int clearNotes();

    public long getNativePtr();
}

