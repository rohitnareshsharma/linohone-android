/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.ErrorInfo;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore;
import org.linphone.core.Reason;
import org.linphone.core.SubscriptionDir;
import org.linphone.core.SubscriptionState;

public interface LinphoneEvent {
    public String getEventName();

    public SubscriptionDir getSubscriptionDir();

    public SubscriptionState getSubscriptionState();

    public void acceptSubscription();

    public void denySubscription(Reason var1);

    public void notify(LinphoneContent var1);

    public void updateSubscribe(LinphoneContent var1);

    public void updatePublish(LinphoneContent var1);

    public void terminate();

    public Reason getReason();

    public ErrorInfo getErrorInfo();

    public void setUserContext(Object var1);

    public Object getUserContext();

    public void addCustomHeader(String var1, String var2);

    public String getCustomHeader(String var1);

    public void sendSubscribe(LinphoneContent var1);

    public void sendPublish(LinphoneContent var1);

    public LinphoneCore getCore();
}

