/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.Reason;

public interface ErrorInfo {
    public Reason getReason();

    public int getProtocolCode();

    public String getPhrase();

    public String getDetails();
}

