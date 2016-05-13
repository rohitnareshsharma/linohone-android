/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceActivity;
import org.linphone.core.PresenceActivityType;
import org.linphone.core.PresenceBasicStatus;
import org.linphone.core.PresenceNote;
import org.linphone.core.PresencePerson;
import org.linphone.core.PresenceService;

public interface PresenceModel {
    public PresenceBasicStatus getBasicStatus();

    public int setBasicStatus(PresenceBasicStatus var1);

    public long getTimestamp();

    public String getContact();

    public void setContact(String var1);

    public PresenceActivity getActivity();

    public int setActivity(PresenceActivityType var1, String var2);

    public long getNbActivities();

    public PresenceActivity getNthActivity(long var1);

    public int addActivity(PresenceActivity var1);

    public int clearActivities();

    public PresenceNote getNote(String var1);

    public int addNote(String var1, String var2);

    public int clearNotes();

    public long getNbServices();

    public PresenceService getNthService(long var1);

    public int addService(PresenceService var1);

    public int clearServices();

    public long getNbPersons();

    public PresencePerson getNthPerson(long var1);

    public int addPerson(PresencePerson var1);

    public int clearPersons();
}

