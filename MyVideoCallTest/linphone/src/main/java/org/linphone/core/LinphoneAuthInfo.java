/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public interface LinphoneAuthInfo {
    public String getUsername();

    public void setUsername(String var1);

    public String getPassword();

    public void setPassword(String var1);

    public String getRealm();

    public void setRealm(String var1);

    public String getUserId();

    public void setUserId(String var1);

    public String getHa1();

    public void setHa1(String var1);

    public void setDomain(String var1);

    public String getDomain();

    public LinphoneAuthInfo clone();
}

