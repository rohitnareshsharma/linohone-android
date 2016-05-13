/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.PresenceActivityType;

public interface PresenceActivity {
    public String toString();

    public PresenceActivityType getType();

    public int setType(PresenceActivityType var1);

    public String getDescription();

    public int setDescription(String var1);

    public long getNativePtr();
}

