/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneContent;

public interface LinphoneInfoMessage {
    public void setContent(LinphoneContent var1);

    public LinphoneContent getContent();

    public void addHeader(String var1, String var2);

    public String getHeader(String var1);
}

