/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import java.io.Serializable;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddressImpl;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.OnlineStatus;
import org.linphone.core.PresenceModel;

class LinphoneFriendImpl
implements LinphoneFriend,
Serializable {
    protected final long nativePtr;

    private native void finalize(long var1);

    private native long newLinphoneFriend(String var1);

    private native void setAddress(long var1, long var3);

    private native long getAddress(long var1);

    private native void setIncSubscribePolicy(long var1, int var3);

    private native int getIncSubscribePolicy(long var1);

    private native void enableSubscribes(long var1, boolean var3);

    private native boolean isSubscribesEnabled(long var1);

    private native int getStatus(long var1);

    private native Object getPresenceModel(long var1);

    private native void setPresenceModel(long var1, long var3);

    private native void edit(long var1);

    private native void done(long var1);

    private native Object getCore(long var1);

    private native void setRefKey(long var1, String var3);

    private native String getRefKey(long var1);

    protected LinphoneFriendImpl() {
        this.nativePtr = this.newLinphoneFriend(null);
    }

    protected LinphoneFriendImpl(String friendUri) {
        this.nativePtr = this.newLinphoneFriend(friendUri);
    }

    protected LinphoneFriendImpl(long aNativePtr) {
        this.nativePtr = aNativePtr;
    }

    protected void finalize() throws Throwable {
        if (this.nativePtr != 0) {
            this.finalize(this.nativePtr);
        }
        super.finalize();
    }

    public void setAddress(LinphoneAddress anAddress) {
        this.setAddress(this.nativePtr, ((LinphoneAddressImpl)anAddress).nativePtr);
    }

    public LinphoneAddress getAddress() {
        return new LinphoneAddressImpl(this.getAddress(this.nativePtr), LinphoneAddressImpl.WrapMode.FromConst);
    }

    public void setIncSubscribePolicy(LinphoneFriend.SubscribePolicy policy) {
        Object object = this.getSyncObject();
        synchronized (object) {
            this.setIncSubscribePolicy(this.nativePtr, policy.mValue);
        }
    }

    public LinphoneFriend.SubscribePolicy getIncSubscribePolicy() {
        return LinphoneFriend.SubscribePolicy.fromInt(this.getIncSubscribePolicy(this.nativePtr));
    }

    public void enableSubscribes(boolean enable) {
        Object object = this.getSyncObject();
        synchronized (object) {
            this.enableSubscribes(this.nativePtr, enable);
        }
    }

    public boolean isSubscribesEnabled() {
        return this.isSubscribesEnabled(this.nativePtr);
    }

    public OnlineStatus getStatus() {
        return OnlineStatus.fromInt(this.getStatus(this.nativePtr));
    }

    public PresenceModel getPresenceModel() {
        return (PresenceModel)this.getPresenceModel(this.nativePtr);
    }

    public void edit() {
        Object object = this.getSyncObject();
        synchronized (object) {
            this.edit(this.nativePtr);
        }
    }

    public void done() {
        Object object = this.getSyncObject();
        synchronized (object) {
            this.done(this.nativePtr);
        }
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    private Object getSyncObject() {
        Object core = this.getCore(this.nativePtr);
        if (core != null) {
            return core;
        }
        return this;
    }

    public void setRefKey(String key) {
        Object object = this.getSyncObject();
        synchronized (object) {
            this.setRefKey(this.nativePtr, key);
        }
    }

    public String getRefKey() {
        return this.getRefKey(this.nativePtr);
    }
}

