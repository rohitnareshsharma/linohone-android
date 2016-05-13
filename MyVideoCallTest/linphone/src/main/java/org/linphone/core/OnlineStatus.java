/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;

public class OnlineStatus {
    private static Vector<OnlineStatus> values = new Vector();
    public static OnlineStatus Offline = new OnlineStatus(0, "Offline");
    public static OnlineStatus Online = new OnlineStatus(1, "Online");
    public static OnlineStatus Busy = new OnlineStatus(2, "Busy");
    public static OnlineStatus BeRightBack = new OnlineStatus(3, "BeRightBack");
    public static OnlineStatus Away = new OnlineStatus(4, "Away");
    public static OnlineStatus OnThePhone = new OnlineStatus(5, "OnThePhone");
    public static OnlineStatus OutToLunch = new OnlineStatus(6, "OutToLunch ");
    public static OnlineStatus DoNotDisturb = new OnlineStatus(7, "DoNotDisturb");
    public static OnlineStatus StatusMoved = new OnlineStatus(8, "StatusMoved");
    public static OnlineStatus StatusAltService = new OnlineStatus(9, "StatusAltService");
    public static OnlineStatus Pending = new OnlineStatus(10, "Pending");
    protected final int mValue;
    private final String mStringValue;

    private OnlineStatus(int value, String stringValue) {
        this.mValue = value;
        values.addElement(this);
        this.mStringValue = stringValue;
    }

    public static OnlineStatus fromInt(int value) {
        for (int i = 0; i < values.size(); ++i) {
            OnlineStatus state = values.elementAt(i);
            if (state.mValue != value) continue;
            return state;
        }
        throw new RuntimeException("state not found [" + value + "]");
    }

    public String toString() {
        return this.mStringValue;
    }
}

