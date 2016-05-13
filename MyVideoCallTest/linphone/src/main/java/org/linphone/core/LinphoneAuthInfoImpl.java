/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneAuthInfo;
import org.linphone.core.LinphoneCoreFactory;

class LinphoneAuthInfoImpl
implements LinphoneAuthInfo {
    protected final long nativePtr;
    boolean ownPtr = false;

    private native long newLinphoneAuthInfo();

    private native void delete(long var1);

    private native String getPassword(long var1);

    private native String getRealm(long var1);

    private native String getUsername(long var1);

    private native void setPassword(long var1, String var3);

    private native void setRealm(long var1, String var3);

    private native void setUsername(long var1, String var3);

    private native void setUserId(long var1, String var3);

    private native void setHa1(long var1, String var3);

    private native String getUserId(long var1);

    private native String getHa1(long var1);

    private native String getDomain(long var1);

    private native void setDomain(long var1, String var3);

    protected LinphoneAuthInfoImpl(String username, String password, String realm, String domain) {
        this(username, null, password, null, realm, domain);
    }

    protected LinphoneAuthInfoImpl(String username, String userid, String passwd, String ha1, String realm, String domain) {
        this.nativePtr = this.newLinphoneAuthInfo();
        this.setUsername(username);
        this.setUserId(userid);
        this.setPassword(passwd);
        this.setHa1(ha1);
        this.setDomain(domain);
        this.setRealm(realm);
        this.ownPtr = true;
    }

    protected LinphoneAuthInfoImpl(long aNativePtr) {
        this.nativePtr = aNativePtr;
        this.ownPtr = false;
    }

    protected void finalize() throws Throwable {
        if (this.ownPtr) {
            this.delete(this.nativePtr);
        }
    }

    public String getPassword() {
        return this.getPassword(this.nativePtr);
    }

    public String getRealm() {
        return this.getRealm(this.nativePtr);
    }

    public String getUsername() {
        return this.getUsername(this.nativePtr);
    }

    public void setPassword(String password) {
        this.setPassword(this.nativePtr, password);
    }

    public void setRealm(String realm) {
        this.setRealm(this.nativePtr, realm);
    }

    public void setUsername(String username) {
        this.setUsername(this.nativePtr, username);
    }

    public String getUserId() {
        return this.getUserId(this.nativePtr);
    }

    public void setUserId(String userid) {
        this.setUserId(this.nativePtr, userid);
    }

    public String getHa1() {
        return this.getHa1(this.nativePtr);
    }

    public void setHa1(String ha1) {
        this.setHa1(this.nativePtr, ha1);
    }

    public void setDomain(String domain) {
        this.setDomain(this.nativePtr, domain);
    }

    public String getDomain() {
        return this.getDomain(this.nativePtr);
    }

    public LinphoneAuthInfo clone() {
        LinphoneAuthInfo clone = LinphoneCoreFactory.instance().createAuthInfo(this.getUsername(), this.getUserId(), this.getPassword(), this.getHa1(), this.getRealm(), this.getDomain());
        return clone;
    }
}

