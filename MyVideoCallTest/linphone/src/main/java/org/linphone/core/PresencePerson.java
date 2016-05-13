/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceActivity;
import org.linphone.core.PresenceNote;

public interface PresencePerson {
    public String getId();

    public int setId(String var1);

    public long getNbActivities();

    public PresenceActivity getNthActivity(long var1);

    public int addActivity(PresenceActivity var1);

    public int clearActivities();

    public long getNbNotes();

    public PresenceNote getNthNote(long var1);

    public int addNote(PresenceNote var1);

    public int clearNotes();

    public long getNbActivitiesNotes();

    public PresenceNote getNthActivitiesNote(long var1);

    public int addActivitiesNote(PresenceNote var1);

    public int clearActivitesNotes();

    public long getNativePtr();
}

