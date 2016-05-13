/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.OnlineStatus;
import org.linphone.core.PresenceModel;

public interface LinphoneFriend {
    public void setAddress(LinphoneAddress var1);

    public LinphoneAddress getAddress();

    public void setIncSubscribePolicy(SubscribePolicy var1);

    public SubscribePolicy getIncSubscribePolicy();

    public void enableSubscribes(boolean var1);

    public boolean isSubscribesEnabled();

    public OnlineStatus getStatus();

    public PresenceModel getPresenceModel();

    public void edit();

    public void done();

    public String toString();

    public long getNativePtr();

    public void setRefKey(String var1);

    public String getRefKey();

    public static class SubscribePolicy {
        private static Vector<SubscribePolicy> values = new Vector();
        protected final int mValue;
        private final String mStringValue;
        public static final SubscribePolicy SPWait = new SubscribePolicy(0, "SPWait");
        public static final SubscribePolicy SPDeny = new SubscribePolicy(1, "SPDeny");
        public static final SubscribePolicy SPAccept = new SubscribePolicy(2, "SPAccept");

        private SubscribePolicy(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static SubscribePolicy fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                SubscribePolicy policy = values.elementAt(i);
                if (policy.mValue != value) continue;
                return policy;
            }
            throw new RuntimeException("Policy not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

}

