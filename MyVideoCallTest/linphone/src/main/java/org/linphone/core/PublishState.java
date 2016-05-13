/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneCoreException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum PublishState {
    None(0),
    Progress(1),
    Ok(2),
    Error(3),
    Expiring(4),
    Cleared(5);
    
    protected final int mValue;

    private PublishState(int value) {
        this.mValue = value;
    }

    protected static PublishState fromInt(int value) throws LinphoneCoreException {
        switch (value) {
            case 0: {
                return None;
            }
            case 1: {
                return Progress;
            }
            case 2: {
                return Ok;
            }
            case 3: {
                return Error;
            }
            case 4: {
                return Expiring;
            }
            case 5: {
                return Cleared;
            }
        }
        throw new LinphoneCoreException("Unhandled enum value " + value + " for PublishState");
    }
}

