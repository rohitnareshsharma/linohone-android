/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneCoreException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum SubscriptionState {
    None(0),
    OutoingProgress(1),
    IncomingReceived(2),
    Pending(3),
    Active(4),
    Terminated(5),
    Error(6),
    Expiring(7);
    
    protected final int mValue;

    private SubscriptionState(int value) {
        this.mValue = value;
    }

    protected static SubscriptionState fromInt(int value) throws LinphoneCoreException {
        switch (value) {
            case 0: {
                return None;
            }
            case 1: {
                return OutoingProgress;
            }
            case 2: {
                return IncomingReceived;
            }
            case 3: {
                return Pending;
            }
            case 4: {
                return Active;
            }
            case 5: {
                return Terminated;
            }
            case 6: {
                return Error;
            }
            case 7: {
                return Expiring;
            }
        }
        throw new LinphoneCoreException("Unhandled enum value " + value + " for SubscriptionState");
    }
}

