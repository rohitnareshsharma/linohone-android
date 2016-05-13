/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public class TunnelConfig {
    private String host = null;
    private int port = 443;
    private int remoteUdpMirrorPort = 12345;
    private int delay = 1000;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getRemoteUdpMirrorPort() {
        return this.remoteUdpMirrorPort;
    }

    public void setRemoteUdpMirrorPort(int remoteUdpMirrorPort) {
        this.remoteUdpMirrorPort = remoteUdpMirrorPort;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}

