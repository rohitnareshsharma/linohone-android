/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum SubscriptionDir {
    Incoming(0),
    Outgoing(1),
    Invalid(2);
    
    protected final int mValue;

    private SubscriptionDir(int value) {
        this.mValue = value;
    }

    protected static SubscriptionDir fromInt(int value) {
        switch (value) {
            case 0: {
                return Incoming;
            }
            case 1: {
                return Outgoing;
            }
        }
        return Invalid;
    }
}

