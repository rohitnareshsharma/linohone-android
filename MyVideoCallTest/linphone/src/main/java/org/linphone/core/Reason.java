/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;

public class Reason {
    private static Vector<Reason> values = new Vector();
    public static Reason None = new Reason(0, "None");
    public static Reason NoResponse = new Reason(1, "NoResponse");
    public static Reason BadCredentials = new Reason(2, "BadCredentials");
    public static Reason Declined = new Reason(3, "Declined");
    public static Reason NotFound = new Reason(4, "NotFound");
    public static Reason NotAnswered = new Reason(5, "NotAnswered");
    public static Reason Busy = new Reason(6, "Busy");
    public static Reason Media = new Reason(7, "Media");
    public static Reason IOError = new Reason(8, "IOError");
    public static Reason DoNotDisturb = new Reason(9, "DoNotDisturb");
    public static Reason Unauthorized = new Reason(10, "Unauthorized");
    public static Reason NotAcceptable = new Reason(11, "NotAcceptable");
    public static Reason NoMatch = new Reason(12, "NoMatch");
    public static Reason MovedPermanently = new Reason(13, "MovedPermanently");
    public static Reason Gone = new Reason(14, "Gone");
    public static Reason TemporarilyUnavailable = new Reason(15, "TemporarilyUnavailable");
    public static Reason AddressIncomplete = new Reason(16, "AddressIncomplete");
    public static Reason NotImplemented = new Reason(17, "NotImplemented");
    public static Reason BadGateway = new Reason(18, "BadGateway");
    public static Reason ServerTimeout = new Reason(19, "ServerTimeout");
    public static Reason Unknown = new Reason(20, "Unknown");
    protected final int mValue;
    private final String mStringValue;

    private Reason(int value, String stringValue) {
        this.mValue = value;
        values.addElement(this);
        this.mStringValue = stringValue;
    }

    public static Reason fromInt(int value) {
        for (int i = 0; i < values.size(); ++i) {
            Reason state = values.elementAt(i);
            if (state.mValue != value) continue;
            return state;
        }
        throw new RuntimeException("Reason not found [" + value + "]");
    }

    public String toString() {
        return this.mStringValue;
    }
}

