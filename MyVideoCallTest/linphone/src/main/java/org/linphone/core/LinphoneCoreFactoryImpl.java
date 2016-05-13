/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.util.Log
 */
package org.linphone.core;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddressImpl;
import org.linphone.core.LinphoneAuthInfo;
import org.linphone.core.LinphoneAuthInfoImpl;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneContentImpl;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreFactory;
import org.linphone.core.LinphoneCoreImpl;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneFriendImpl;
import org.linphone.core.LinphoneLogHandler;
import org.linphone.core.LpConfig;
import org.linphone.core.LpConfigImpl;
import org.linphone.core.PresenceActivity;
import org.linphone.core.PresenceActivityImpl;
import org.linphone.core.PresenceActivityType;
import org.linphone.core.PresenceBasicStatus;
import org.linphone.core.PresenceModel;
import org.linphone.core.PresenceModelImpl;
import org.linphone.core.PresenceService;
import org.linphone.core.PresenceServiceImpl;
import org.linphone.mediastream.MediastreamerAndroidContext;
import org.linphone.mediastream.Version;

public class LinphoneCoreFactoryImpl
extends LinphoneCoreFactory {
    private static boolean loadOptionalLibrary(String s) {
        try {
            System.loadLibrary(s);
            return true;
        }
        catch (Throwable e) {
            Log.w((String)"LinphoneCoreFactoryImpl", (String)("Unable to load optional library lib" + s));
            return false;
        }
    }

    public LinphoneAuthInfo createAuthInfo(String username, String password, String realm, String domain) {
        return new LinphoneAuthInfoImpl(username, password, realm, domain);
    }

    public LinphoneAddress createLinphoneAddress(String username, String domain, String displayName) {
        return new LinphoneAddressImpl(username, domain, displayName);
    }

    public LinphoneAddress createLinphoneAddress(String identity) throws LinphoneCoreException {
        return new LinphoneAddressImpl(identity);
    }

    public LpConfig createLpConfig(String file) {
        return new LpConfigImpl(file);
    }

    public LinphoneCore createLinphoneCore(LinphoneCoreListener listener, String userConfig, String factoryConfig, Object userdata, Object context) throws LinphoneCoreException {
        try {
            MediastreamerAndroidContext.setContext(context);
            File user = userConfig == null ? null : new File(userConfig);
            File factory = factoryConfig == null ? null : new File(factoryConfig);
            LinphoneCoreImpl lc = new LinphoneCoreImpl(listener, user, factory, userdata);
            if (context != null) {
                lc.setContext(context);
            }
            return lc;
        }
        catch (IOException e) {
            throw new LinphoneCoreException("Cannot create LinphoneCore", e);
        }
    }

    public LinphoneCore createLinphoneCore(LinphoneCoreListener listener, Object context) throws LinphoneCoreException {
        try {
            MediastreamerAndroidContext.setContext(context);
            LinphoneCoreImpl lc = new LinphoneCoreImpl(listener);
            if (context != null) {
                lc.setContext(context);
            }
            return lc;
        }
        catch (IOException e) {
            throw new LinphoneCoreException("Cannot create LinphoneCore", e);
        }
    }

    public native void setDebugMode(boolean var1, String var2);

    private native void _setLogHandler(Object var1);

    public void setLogHandler(LinphoneLogHandler handler) {
        this._setLogHandler(handler);
    }

    public LinphoneFriend createLinphoneFriend(String friendUri) {
        return new LinphoneFriendImpl(friendUri);
    }

    public LinphoneFriend createLinphoneFriend() {
        return this.createLinphoneFriend(null);
    }

    public native void enableLogCollection(boolean var1);

    public native void setLogCollectionPath(String var1);

    public static boolean isArmv7() {
        return System.getProperty("os.arch").contains("armv7");
    }

    public LinphoneAuthInfo createAuthInfo(String username, String userid, String passwd, String ha1, String realm, String domain) {
        return new LinphoneAuthInfoImpl(username, userid, passwd, ha1, realm, domain);
    }

    public LinphoneContent createLinphoneContent(String type, String subType, byte[] data, String encoding) {
        return new LinphoneContentImpl(type, subType, data, encoding);
    }

    public LinphoneContent createLinphoneContent(String type, String subType, String data) {
        return new LinphoneContentImpl(type, subType, data == null ? null : data.getBytes(), null);
    }

    public PresenceActivity createPresenceActivity(PresenceActivityType type, String description) {
        return new PresenceActivityImpl(type, description);
    }

    public PresenceService createPresenceService(String id2, PresenceBasicStatus status, String contact) {
        return new PresenceServiceImpl(id2, status, contact);
    }

    public PresenceModel createPresenceModel() {
        return new PresenceModelImpl();
    }

    public PresenceModel createPresenceModel(PresenceActivityType type, String description) {
        return new PresenceModelImpl(type, description);
    }

    public PresenceModel createPresenceModel(PresenceActivityType type, String description, String note, String lang) {
        return new PresenceModelImpl(type, description, note, lang);
    }

    static {
        List<String> cpuabis = Version.getCpuAbis();
        boolean libLoaded = false;
        Throwable firstException = null;
        for (String abi : cpuabis) {
            Log.i((String)"LinphoneCoreFactoryImpl", (String)("Trying to load liblinphone for " + abi));
            String ffmpegAbi = abi;
            if (abi.startsWith("armeabi")) {
                ffmpegAbi = "arm";
            }
            LinphoneCoreFactoryImpl.loadOptionalLibrary("ffmpeg-linphone-" + ffmpegAbi);
            try {
                System.loadLibrary("linphone-" + abi);
                Log.i((String)"LinphoneCoreFactoryImpl", (String)("Loading done with " + abi));
                libLoaded = true;
                break;
            }
            catch (Throwable e) {
                if (firstException != null) continue;
                firstException = e;
                continue;
            }
        }
        if (!libLoaded) {
            throw new RuntimeException(firstException);
        }
        Version.dumpCapabilities();
    }
}

