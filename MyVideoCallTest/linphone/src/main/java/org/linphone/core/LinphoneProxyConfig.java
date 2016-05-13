/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.ErrorInfo;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.Reason;

public interface LinphoneProxyConfig {
    public LinphoneProxyConfig edit();

    public void done();

    public void setIdentity(String var1) throws LinphoneCoreException;

    public String getIdentity();

    public void setProxy(String var1) throws LinphoneCoreException;

    public String getProxy();

    public LinphoneProxyConfig enableRegister(boolean var1);

    public boolean registerEnabled();

    public String normalizePhoneNumber(String var1);

    public void setDialPrefix(String var1);

    public String getDialPrefix();

    public void setDialEscapePlus(boolean var1);

    public boolean getDialEscapePlus();

    public String getDomain();

    public boolean isRegistered();

    public void setRoute(String var1) throws LinphoneCoreException;

    public String getRoute();

    public void enablePublish(boolean var1);

    public boolean publishEnabled();

    public LinphoneCore.RegistrationState getState();

    public void setExpires(int var1);

    public int getExpires();

    public void setPrivacy(int var1);

    public int getPrivacy();

    public void enableAvpf(boolean var1);

    public boolean avpfEnabled();

    public void setAvpfRRInterval(int var1);

    public int getAvpfRRInterval();

    public void enableQualityReporting(boolean var1);

    public boolean qualityReportingEnabled();

    public void setQualityReportingInterval(int var1);

    public int getQualityReportingInterval();

    public void setQualityReportingCollector(String var1);

    public String getQualityReportingCollector();

    public void setRealm(String var1);

    public String getRealm();

    public void setContactParameters(String var1);

    public String getContactParameters();

    public void setContactUriParameters(String var1);

    public String getContactUriParameters();

    public int lookupCCCFromIso(String var1);

    public int lookupCCCFromE164(String var1);

    public Reason getError();

    public ErrorInfo getErrorInfo();

    public void setPublishExpires(int var1);

    public int getPublishExpires();

    public void setUserData(Object var1);

    public boolean isPhoneNumber(String var1);

    public Object getUserData();
}

