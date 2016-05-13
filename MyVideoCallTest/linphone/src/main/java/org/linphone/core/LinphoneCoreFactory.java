/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.io.PrintStream;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAuthInfo;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneLogHandler;
import org.linphone.core.LpConfig;
import org.linphone.core.PresenceActivity;
import org.linphone.core.PresenceActivityType;
import org.linphone.core.PresenceBasicStatus;
import org.linphone.core.PresenceModel;
import org.linphone.core.PresenceService;

public abstract class LinphoneCoreFactory {
    private static String factoryName = "org.linphone.core.LinphoneCoreFactoryImpl";
    static LinphoneCoreFactory theLinphoneCoreFactory;

    public static void setFactoryClassName(String className) {
        factoryName = className;
    }

    public static final synchronized LinphoneCoreFactory instance() {
        try {
            if (theLinphoneCoreFactory == null) {
                Class lFactoryClass = Class.forName(factoryName);
                theLinphoneCoreFactory = (LinphoneCoreFactory)lFactoryClass.newInstance();
            }
        }
        catch (Exception e) {
            System.err.println("Cannot instanciate factory [" + factoryName + "]");
        }
        return theLinphoneCoreFactory;
    }

    public abstract LinphoneAuthInfo createAuthInfo(String var1, String var2, String var3, String var4);

    public abstract LinphoneAuthInfo createAuthInfo(String var1, String var2, String var3, String var4, String var5, String var6);

    public abstract LinphoneCore createLinphoneCore(LinphoneCoreListener var1, String var2, String var3, Object var4, Object var5) throws LinphoneCoreException;

    public abstract LinphoneCore createLinphoneCore(LinphoneCoreListener var1, Object var2) throws LinphoneCoreException;

    public abstract LinphoneAddress createLinphoneAddress(String var1, String var2, String var3);

    public abstract LinphoneAddress createLinphoneAddress(String var1) throws LinphoneCoreException;

    public abstract LpConfig createLpConfig(String var1);

    public abstract void setDebugMode(boolean var1, String var2);

    public abstract void enableLogCollection(boolean var1);

    public abstract void setLogCollectionPath(String var1);

    public abstract void setLogHandler(LinphoneLogHandler var1);

    public abstract LinphoneFriend createLinphoneFriend(String var1);

    public abstract LinphoneFriend createLinphoneFriend();

    public abstract LinphoneContent createLinphoneContent(String var1, String var2, String var3);

    public abstract LinphoneContent createLinphoneContent(String var1, String var2, byte[] var3, String var4);

    public abstract PresenceActivity createPresenceActivity(PresenceActivityType var1, String var2);

    public abstract PresenceService createPresenceService(String var1, PresenceBasicStatus var2, String var3);

    public abstract PresenceModel createPresenceModel();

    public abstract PresenceModel createPresenceModel(PresenceActivityType var1, String var2);

    public abstract PresenceModel createPresenceModel(PresenceActivityType var1, String var2, String var3, String var4);
}

