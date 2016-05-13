/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.ErrorInfo;
import org.linphone.core.ErrorInfoImpl;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneEvent;
import org.linphone.core.Reason;
import org.linphone.core.SubscriptionDir;
import org.linphone.core.SubscriptionState;

public class LinphoneEventImpl
implements LinphoneEvent {
    private Object mUserContext;
    private long mNativePtr;

    protected LinphoneEventImpl(long nativePtr) {
        this.mNativePtr = nativePtr;
    }

    private native String getEventName(long var1);

    public synchronized String getEventName() {
        return this.getEventName(this.mNativePtr);
    }

    private native int acceptSubscription(long var1);

    public synchronized void acceptSubscription() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.acceptSubscription(this.mNativePtr);
        }
    }

    private native int denySubscription(long var1, int var3);

    public synchronized void denySubscription(Reason reason) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.denySubscription(this.mNativePtr, reason.mValue);
        }
    }

    private native int notify(long var1, String var3, String var4, byte[] var5, String var6);

    public void notify(LinphoneContent content) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.notify(this.mNativePtr, content.getType(), content.getSubtype(), content.getData(), content.getEncoding());
        }
    }

    private native int updateSubscribe(long var1, String var3, String var4, byte[] var5, String var6);

    public void updateSubscribe(LinphoneContent content) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.updateSubscribe(this.mNativePtr, content.getType(), content.getSubtype(), content.getData(), content.getEncoding());
        }
    }

    private native int updatePublish(long var1, String var3, String var4, byte[] var5, String var6);

    public void updatePublish(LinphoneContent content) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.updatePublish(this.mNativePtr, content.getType(), content.getSubtype(), content.getData(), content.getEncoding());
        }
    }

    private native int terminate(long var1);

    public void terminate() {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            this.terminate(this.mNativePtr);
        }
    }

    private native int getReason(long var1);

    public synchronized Reason getReason() {
        return Reason.fromInt(this.getReason(this.mNativePtr));
    }

    public synchronized void setUserContext(Object obj) {
        this.mUserContext = obj;
    }

    public synchronized Object getUserContext() {
        return this.mUserContext;
    }

    private native int getSubscriptionDir(long var1);

    public synchronized SubscriptionDir getSubscriptionDir() {
        return SubscriptionDir.fromInt(this.getSubscriptionDir(this.mNativePtr));
    }

    private native int getSubscriptionState(long var1);

    public synchronized SubscriptionState getSubscriptionState() {
        try {
            return SubscriptionState.fromInt(this.getSubscriptionState(this.mNativePtr));
        }
        catch (LinphoneCoreException e) {
            e.printStackTrace();
            return SubscriptionState.Error;
        }
    }

    private native void unref(long var1);

    protected void finalize() {
        this.unref(this.mNativePtr);
    }

    private native void addCustomHeader(long var1, String var3, String var4);

    public synchronized void addCustomHeader(String name, String value) {
        this.addCustomHeader(this.mNativePtr, name, value);
    }

    private native String getCustomHeader(long var1, String var3);

    public synchronized String getCustomHeader(String name) {
        return this.getCustomHeader(this.mNativePtr, name);
    }

    private native void sendSubscribe(long var1, String var3, String var4, byte[] var5, String var6);

    public void sendSubscribe(LinphoneContent body) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            if (body != null) {
                this.sendSubscribe(this.mNativePtr, body.getType(), body.getSubtype(), body.getData(), body.getEncoding());
            } else {
                this.sendSubscribe(this.mNativePtr, null, null, null, null);
            }
        }
    }

    private native void sendPublish(long var1, String var3, String var4, byte[] var5, String var6);

    public void sendPublish(LinphoneContent body) {
        LinphoneCore linphoneCore = this.getCore();
        synchronized (linphoneCore) {
            if (body != null) {
                this.sendPublish(this.mNativePtr, body.getType(), body.getSubtype(), body.getData(), body.getEncoding());
            } else {
                this.sendPublish(this.mNativePtr, null, null, null, null);
            }
        }
    }

    private native long getErrorInfo(long var1);

    public synchronized ErrorInfo getErrorInfo() {
        return new ErrorInfoImpl(this.getErrorInfo(this.mNativePtr));
    }

    private native Object getCore(long var1);

    public synchronized LinphoneCore getCore() {
        return (LinphoneCore)this.getCore(this.mNativePtr);
    }
}

