/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum PresenceBasicStatus {
    Open(0),
    Closed(1),
    Invalid(2);
    
    protected final int mValue;

    private PresenceBasicStatus(int value) {
        this.mValue = value;
    }

    public int toInt() {
        return this.mValue;
    }

    protected static PresenceBasicStatus fromInt(int value) {
        switch (value) {
            case 0: {
                return Open;
            }
            case 1: {
                return Closed;
            }
        }
        return Invalid;
    }
}

