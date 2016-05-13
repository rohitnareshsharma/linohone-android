/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneCoreException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum ToneID {
    Undefined(0),
    Busy(1),
    CallWaiting(2),
    CallOnHold(3),
    CallLost(4);
    
    protected final int mValue;

    private ToneID(int value) {
        this.mValue = value;
    }

    protected static ToneID fromInt(int value) throws LinphoneCoreException {
        switch (value) {
            case 0: {
                return Undefined;
            }
            case 1: {
                return Busy;
            }
            case 2: {
                return CallWaiting;
            }
            case 3: {
                return CallOnHold;
            }
            case 4: {
                return CallLost;
            }
        }
        throw new LinphoneCoreException("Unhandled enum value " + value + " for LinphoneToneID");
    }
}

