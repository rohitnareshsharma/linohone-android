/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCoreException;

public class LinphoneAddressImpl
implements LinphoneAddress {
    protected final long nativePtr;

    private native long newLinphoneAddressImpl(String var1, String var2);

    private native long ref(long var1);

    private native void unref(long var1);

    private native long clone(long var1);

    private native String getDisplayName(long var1);

    private native String getUserName(long var1);

    private native String getDomain(long var1);

    private native int getTransport(long var1);

    private native int getPort(long var1);

    private native String toUri(long var1);

    private native void setDisplayName(long var1, String var3);

    private native void setDomain(long var1, String var3);

    private native void setUserName(long var1, String var3);

    private native void setTransport(long var1, int var3);

    private native void setPort(long var1, int var3);

    private native String toString(long var1);

    protected LinphoneAddressImpl(String identity) throws LinphoneCoreException {
        this.nativePtr = this.newLinphoneAddressImpl(identity, null);
        if (this.nativePtr == 0) {
            throw new LinphoneCoreException("Cannot create LinphoneAdress from [" + identity + "]");
        }
    }

    protected LinphoneAddressImpl(String username, String domain, String displayName) {
        this.nativePtr = this.newLinphoneAddressImpl(null, displayName);
        this.setUserName(username);
        this.setDomain(domain);
    }

    private LinphoneAddressImpl(long aNativeptr) {
        this(aNativeptr, WrapMode.FromConst);
    }

    protected LinphoneAddressImpl(long aNativePtr, WrapMode mode) {
        switch (mode) {
            case FromNew: {
                this.nativePtr = aNativePtr;
                break;
            }
            case FromConst: {
                this.nativePtr = this.clone(aNativePtr);
                break;
            }
            case FromExisting: {
                this.nativePtr = this.ref(aNativePtr);
                break;
            }
            default: {
                this.nativePtr = 0;
            }
        }
    }

    protected void finalize() throws Throwable {
        if (this.nativePtr != 0) {
            this.unref(this.nativePtr);
        }
    }

    public String getDisplayName() {
        return this.getDisplayName(this.nativePtr);
    }

    public String getDomain() {
        return this.getDomain(this.nativePtr);
    }

    public String getUserName() {
        return this.getUserName(this.nativePtr);
    }

    public LinphoneAddress.TransportType getTransport() {
        return LinphoneAddress.TransportType.fromInt(this.getTransport(this.nativePtr));
    }

    public int getPort() {
        return this.getPort(this.nativePtr);
    }

    public String toString() {
        return this.toString(this.nativePtr);
    }

    public String toUri() {
        return this.toUri(this.nativePtr);
    }

    public String asString() {
        return this.toString();
    }

    public String asStringUriOnly() {
        return this.toUri(this.nativePtr);
    }

    public void clean() {
        throw new RuntimeException("Not implemented");
    }

    public void setDisplayName(String name) {
        this.setDisplayName(this.nativePtr, name);
    }

    public void setDomain(String domain) {
        this.setDomain(this.nativePtr, domain);
    }

    public void setPort(int port) {
        this.setPort(this.nativePtr, port);
    }

    public void setUserName(String username) {
        this.setUserName(this.nativePtr, username);
    }

    public void setTransport(LinphoneAddress.TransportType transport) {
        this.setTransport(this.nativePtr, transport.toInt());
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum WrapMode {
        FromNew,
        FromConst,
        FromExisting;
        

        private WrapMode() {
        }
    }

}

