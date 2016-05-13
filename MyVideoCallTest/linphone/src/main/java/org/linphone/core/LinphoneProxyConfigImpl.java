/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.ErrorInfo;
import org.linphone.core.ErrorInfoImpl;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreImpl;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.Reason;

class LinphoneProxyConfigImpl
implements LinphoneProxyConfig {
    protected final long nativePtr;
    protected LinphoneCoreImpl mCore;
    Object userData;

    private native void finalize(long var1);

    private native int getState(long var1);

    private native void setExpires(long var1, int var3);

    private native int getExpires(long var1);

    private native long createProxyConfig(long var1);

    protected LinphoneProxyConfigImpl(LinphoneCoreImpl core, String identity, String proxy, String route, boolean enableRegister) throws LinphoneCoreException {
        this.mCore = core;
        this.nativePtr = this.createProxyConfig(core.nativePtr);
        this.setIdentity(identity);
        this.setProxy(proxy);
        this.setRoute(route);
        this.enableRegister(enableRegister);
    }

    protected LinphoneProxyConfigImpl(LinphoneCoreImpl core) {
        this.mCore = core;
        this.nativePtr = this.createProxyConfig(core.nativePtr);
    }

    protected LinphoneProxyConfigImpl(LinphoneCoreImpl core, long aNativePtr) {
        this.mCore = core;
        this.nativePtr = aNativePtr;
    }

    private void isValid() {
        if (this.nativePtr == 0) {
            throw new RuntimeException("proxy config removed");
        }
    }

    protected void finalize() throws Throwable {
        if (this.nativePtr != 0) {
            this.finalize(this.nativePtr);
        }
        super.finalize();
    }

    private native long newLinphoneProxyConfig();

    private native void edit(long var1);

    private native void done(long var1);

    private native void setIdentity(long var1, String var3);

    private native String getIdentity(long var1);

    private native int setProxy(long var1, String var3);

    private native String getProxy(long var1);

    private native void enableRegister(long var1, boolean var3);

    private native boolean isRegisterEnabled(long var1);

    private native boolean isRegistered(long var1);

    private native void setDialPrefix(long var1, String var3);

    private native String getDialPrefix(long var1);

    private native String normalizePhoneNumber(long var1, String var3);

    private native String getDomain(long var1);

    private native void setDialEscapePlus(long var1, boolean var3);

    private native boolean getDialEscapePlus(long var1);

    private native String getRoute(long var1);

    private native int setRoute(long var1, String var3);

    private native void enablePublish(long var1, boolean var3);

    private native boolean publishEnabled(long var1);

    private native void setContactParameters(long var1, String var3);

    private native int lookupCCCFromIso(long var1, String var3);

    private native int lookupCCCFromE164(long var1, String var3);

    public LinphoneProxyConfig enableRegister(boolean value) {
        this.isValid();
        this.enableRegister(this.nativePtr, value);
        return this;
    }

    public void done() {
        Object mutex;
        this.isValid();
        Object object = mutex = this.mCore != null ? this.mCore : this;
        synchronized (object) {
            this.done(this.nativePtr);
        }
    }

    public LinphoneProxyConfig edit() {
        Object mutex;
        this.isValid();
        Object object = mutex = this.mCore != null ? this.mCore : this;
        synchronized (object) {
            this.edit(this.nativePtr);
        }
        return this;
    }

    public void setIdentity(String identity) throws LinphoneCoreException {
        this.isValid();
        this.setIdentity(this.nativePtr, identity);
    }

    public void setProxy(String proxyUri) throws LinphoneCoreException {
        this.isValid();
        if (this.setProxy(this.nativePtr, proxyUri) != 0) {
            throw new LinphoneCoreException("Bad proxy address [" + proxyUri + "]");
        }
    }

    public String normalizePhoneNumber(String number) {
        this.isValid();
        return this.normalizePhoneNumber(this.nativePtr, number);
    }

    public void setDialPrefix(String prefix) {
        this.isValid();
        this.setDialPrefix(this.nativePtr, prefix);
    }

    public String getDialPrefix() {
        this.isValid();
        return this.getDialPrefix(this.nativePtr);
    }

    public String getDomain() {
        this.isValid();
        return this.getDomain(this.nativePtr);
    }

    public void setDialEscapePlus(boolean value) {
        this.isValid();
        this.setDialEscapePlus(this.nativePtr, value);
    }

    public boolean getDialEscapePlus() {
        this.isValid();
        return this.getDialEscapePlus(this.nativePtr);
    }

    public String getIdentity() {
        this.isValid();
        return this.getIdentity(this.nativePtr);
    }

    public String getProxy() {
        this.isValid();
        return this.getProxy(this.nativePtr);
    }

    public boolean isRegistered() {
        this.isValid();
        return this.isRegistered(this.nativePtr);
    }

    public boolean registerEnabled() {
        this.isValid();
        return this.isRegisterEnabled(this.nativePtr);
    }

    public String getRoute() {
        this.isValid();
        return this.getRoute(this.nativePtr);
    }

    public void setRoute(String routeUri) throws LinphoneCoreException {
        this.isValid();
        if (this.setRoute(this.nativePtr, routeUri) != 0) {
            throw new LinphoneCoreException("cannot set route [" + routeUri + "]");
        }
    }

    public void enablePublish(boolean enable) {
        this.isValid();
        this.enablePublish(this.nativePtr, enable);
    }

    public LinphoneCore.RegistrationState getState() {
        this.isValid();
        return LinphoneCore.RegistrationState.fromInt(this.getState(this.nativePtr));
    }

    public void setExpires(int delay) {
        this.isValid();
        this.setExpires(this.nativePtr, delay);
    }

    public int getExpires() {
        this.isValid();
        return this.getExpires(this.nativePtr);
    }

    public boolean publishEnabled() {
        this.isValid();
        return this.publishEnabled(this.nativePtr);
    }

    public void setContactParameters(String params) {
        this.isValid();
        this.setContactParameters(this.nativePtr, params);
    }

    public int lookupCCCFromIso(String iso) {
        this.isValid();
        return this.lookupCCCFromIso(this.nativePtr, iso);
    }

    public int lookupCCCFromE164(String e164) {
        this.isValid();
        return this.lookupCCCFromE164(this.nativePtr, e164);
    }

    private native int getError(long var1);

    public Reason getError() {
        this.isValid();
        return Reason.fromInt(this.getError(this.nativePtr));
    }

    private native void setPrivacy(long var1, int var3);

    public void setPrivacy(int privacy_mask) {
        this.isValid();
        this.setPrivacy(this.nativePtr, privacy_mask);
    }

    private native int getPrivacy(long var1);

    public int getPrivacy() {
        this.isValid();
        return this.getPrivacy(this.nativePtr);
    }

    private native void enableAvpf(long var1, boolean var3);

    public void enableAvpf(boolean enable) {
        this.isValid();
        this.enableAvpf(this.nativePtr, enable);
    }

    private native boolean avpfEnabled(long var1);

    public boolean avpfEnabled() {
        this.isValid();
        return this.avpfEnabled(this.nativePtr);
    }

    private native void setAvpfRRInterval(long var1, int var3);

    public void setAvpfRRInterval(int interval) {
        this.isValid();
        this.setAvpfRRInterval(this.nativePtr, interval);
    }

    private native int getAvpfRRInterval(long var1);

    public int getAvpfRRInterval() {
        this.isValid();
        return this.getAvpfRRInterval(this.nativePtr);
    }

    private native String getContactParameters(long var1);

    public String getContactParameters() {
        this.isValid();
        return this.getContactParameters(this.nativePtr);
    }

    private native void setContactUriParameters(long var1, String var3);

    public void setContactUriParameters(String params) {
        this.isValid();
        this.setContactUriParameters(this.nativePtr, params);
    }

    private native String getContactUriParameters(long var1);

    public String getContactUriParameters() {
        this.isValid();
        return this.getContactUriParameters(this.nativePtr);
    }

    private native long getErrorInfo(long var1);

    public ErrorInfo getErrorInfo() {
        return new ErrorInfoImpl(this.getErrorInfo(this.nativePtr));
    }

    private native void enableQualityReporting(long var1, boolean var3);

    public void enableQualityReporting(boolean enable) {
        this.isValid();
        this.enableQualityReporting(this.nativePtr, enable);
    }

    private native boolean qualityReportingEnabled(long var1);

    public boolean qualityReportingEnabled() {
        this.isValid();
        return this.avpfEnabled(this.nativePtr);
    }

    private native void setQualityReportingInterval(long var1, int var3);

    public void setQualityReportingInterval(int interval) {
        this.isValid();
        this.setQualityReportingInterval(this.nativePtr, interval);
    }

    private native int getQualityReportingInterval(long var1);

    public int getQualityReportingInterval() {
        this.isValid();
        return this.getQualityReportingInterval(this.nativePtr);
    }

    private native void setQualityReportingCollector(long var1, String var3);

    public void setQualityReportingCollector(String collector) {
        this.isValid();
        this.setQualityReportingCollector(this.nativePtr, collector);
    }

    private native String getQualityReportingCollector(long var1);

    public String getQualityReportingCollector() {
        this.isValid();
        return this.getQualityReportingCollector(this.nativePtr);
    }

    private native void setRealm(long var1, String var3);

    public void setRealm(String realm) {
        this.isValid();
        this.setRealm(this.nativePtr, realm);
    }

    private native String getRealm(long var1);

    public String getRealm() {
        this.isValid();
        return this.getRealm(this.nativePtr);
    }

    private native void setPublishExpires(long var1, int var3);

    public void setPublishExpires(int expires) {
        this.isValid();
        this.setPublishExpires(this.nativePtr, expires);
    }

    private native int getPublishExpires(long var1);

    public int getPublishExpires() {
        this.isValid();
        return this.getPublishExpires(this.nativePtr);
    }

    private native boolean isPhoneNumber(long var1, String var3);

    public boolean isPhoneNumber(String username) {
        return this.isPhoneNumber(this.nativePtr, username);
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }
}

