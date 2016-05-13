/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public class CallDirection {
    public static CallDirection Outgoing = new CallDirection("CallOutgoing");
    public static CallDirection Incoming = new CallDirection("Callincoming");
    private String mStringValue;

    private CallDirection(String aStringValue) {
        this.mStringValue = aStringValue;
    }

    public String toString() {
        return this.mStringValue;
    }
}

