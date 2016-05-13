/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public interface LinphonePlayer {
    public int open(String var1, Listener var2);

    public int start();

    public int pause();

    public int seek(int var1);

    public State getState();

    public int getDuration();

    public int getCurrentPosition();

    public void close();

    public static interface Listener {
        public void endOfFile(LinphonePlayer var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum State {
        closed,
        paused,
        playing;
        

        private State() {
        }

        public static State fromValue(int value) {
            if (value == 0) {
                return closed;
            }
            if (value == 1) {
                return paused;
            }
            if (value == 2) {
                return playing;
            }
            return null;
        }
    }

}

