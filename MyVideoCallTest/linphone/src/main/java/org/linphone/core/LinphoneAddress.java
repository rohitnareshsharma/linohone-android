/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;

public interface LinphoneAddress {
    public String getDisplayName();

    public String getUserName();

    public String getDomain();

    public int getPort();

    public void setDisplayName(String var1);

    public void setUserName(String var1);

    public void setDomain(String var1);

    public void setPort(int var1);

    public void clean();

    public String asString();

    public String asStringUriOnly();

    public String toString();

    public TransportType getTransport();

    public void setTransport(TransportType var1);

    public static class TransportType {
        private static Vector<TransportType> values = new Vector();
        public static TransportType LinphoneTransportUdp = new TransportType(0, "LinphoneTransportUdp");
        public static TransportType LinphoneTransportTcp = new TransportType(1, "LinphoneTransportTcp");
        public static TransportType LinphoneTransportTls = new TransportType(2, "LinphoneTransportTls");
        private final int mValue;
        private final String mStringValue;

        private TransportType(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static TransportType fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                TransportType type = values.elementAt(i);
                if (type.mValue != value) continue;
                return type;
            }
            throw new RuntimeException("state not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }

        public int toInt() {
            return this.mValue;
        }
    }

}

