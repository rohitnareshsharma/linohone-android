/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.util.Vector;

public interface LinphoneCallStats {
    public MediaType getMediaType();

    public IceState getIceState();

    public float getDownloadBandwidth();

    public float getUploadBandwidth();

    public float getSenderLossRate();

    public float getReceiverLossRate();

    public float getSenderInterarrivalJitter();

    public float getReceiverInterarrivalJitter();

    public float getRoundTripDelay();

    public long getLatePacketsCumulativeNumber();

    public float getJitterBufferSize();

    public float getLocalLossRate();

    public float getLocalLateRate();

    public static class IceState {
        private static Vector<IceState> values = new Vector();
        public static IceState NotActivated = new IceState(0, "Not activated");
        public static IceState Failed = new IceState(1, "Failed");
        public static IceState InProgress = new IceState(2, "In progress");
        public static IceState HostConnection = new IceState(3, "Host connection");
        public static IceState ReflexiveConnection = new IceState(4, "Reflexive connection");
        public static IceState RelayConnection = new IceState(5, "Relay connection");
        protected final int mValue;
        private final String mStringValue;

        private IceState(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static IceState fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                IceState mstate = values.elementAt(i);
                if (mstate.mValue != value) continue;
                return mstate;
            }
            throw new RuntimeException("IceState not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public static class MediaType {
        private static Vector<MediaType> values = new Vector();
        public static MediaType Audio = new MediaType(0, "Audio");
        public static MediaType Video = new MediaType(1, "Video");
        protected final int mValue;
        private final String mStringValue;

        private MediaType(int value, String stringValue) {
            this.mValue = value;
            values.addElement(this);
            this.mStringValue = stringValue;
        }

        public static MediaType fromInt(int value) {
            for (int i = 0; i < values.size(); ++i) {
                MediaType mtype = values.elementAt(i);
                if (mtype.mValue != value) continue;
                return mtype;
            }
            throw new RuntimeException("MediaType not found [" + value + "]");
        }

        public String toString() {
            return this.mStringValue;
        }
    }

}

