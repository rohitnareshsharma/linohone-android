/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;
import org.linphone.core.CallDirection;
import org.linphone.core.LinphoneAddress;

public interface LinphoneCallLog {
    public LinphoneAddress getFrom();

    public LinphoneAddress getTo();

    public CallDirection getDirection();

    public CallStatus getStatus();

    public String getStartDate();

    public long getTimestamp();

    public int getCallDuration();

    public int getCallId();

    public static class CallStatus {
        private static Vector<CallStatus> values = new Vector();
        private final int mValue;
        private final String mStringValue;
        public static final CallStatus Success = new CallStatus(0, "Sucess");
        public static final CallStatus Aborted = new CallStatus(1, "Aborted");
        public static final CallStatus Missed = new CallStatus(2, "Missed");
        public static final CallStatus Declined = new CallStatus(3, "Declined");

        private CallStatus(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static CallStatus fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                CallStatus state = values.elementAt(i);
                if (state.mValue != value) continue;
                return state;
            }
            throw new RuntimeException("CallStatus not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }

        public int toInt() {
            return this.mValue;
        }
    }

}

