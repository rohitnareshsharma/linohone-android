/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public interface PayloadType {
    public String getMime();

    public int getRate();

    public void setRecvFmtp(String var1);

    public String getRecvFmtp();

    public void setSendFmtp(String var1);

    public String getSendFmtp();
}

