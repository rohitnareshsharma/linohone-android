/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneCore;
import org.linphone.core.PayloadType;
import org.linphone.core.VideoSize;

public interface LinphoneCallParams {
    public void setVideoEnabled(boolean var1);

    public boolean getVideoEnabled();

    public void setAudioBandwidth(int var1);

    public LinphoneCore.MediaEncryption getMediaEncryption();

    public void setMediaEnctyption(LinphoneCore.MediaEncryption var1);

    public PayloadType getUsedAudioCodec();

    public PayloadType getUsedVideoCodec();

    public void enableLowBandwidth(boolean var1);

    public boolean isLowBandwidthEnabled();

    public void setRecordFile(String var1);

    public void addCustomHeader(String var1, String var2);

    public String getCustomHeader(String var1);

    public void setPrivacy(int var1);

    public int getPrivacy();

    public void setSessionName(String var1);

    public String getSessionName();

    public VideoSize getSentVideoSize();

    public VideoSize getReceivedVideoSize();

    public void enableAudioMulticast(boolean var1);

    public boolean audioMulticastEnabled();

    public void enableVideoMulticast(boolean var1);

    public boolean videoMulticastEnabled();
}

