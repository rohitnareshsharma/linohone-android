/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneContent;

public class LinphoneContentImpl
implements LinphoneContent {
    private String mType;
    private String mSubtype;
    private String mEncoding;
    private String mName;
    private byte[] mData;
    private int mExpectedSize;

    public LinphoneContentImpl(String type, String subtype, byte[] data, String encoding) {
        this.mType = type;
        this.mSubtype = subtype;
        this.mData = data;
        this.mEncoding = encoding;
        this.mName = null;
        this.mExpectedSize = 0;
    }

    public LinphoneContentImpl(String name, String type, String subtype, byte[] data, String encoding, int expectedSize) {
        this.mType = type;
        this.mSubtype = subtype;
        this.mData = data;
        this.mEncoding = encoding;
        this.mName = name;
        this.mExpectedSize = expectedSize;
    }

    public String getType() {
        return this.mType;
    }

    public String getSubtype() {
        return this.mSubtype;
    }

    public String getDataAsString() {
        if (this.mData != null) {
            return new String(this.mData);
        }
        return null;
    }

    public void setExpectedSize(int size) {
        this.mExpectedSize = size;
    }

    public int getExpectedSize() {
        return this.mExpectedSize;
    }

    public int getRealSize() {
        if (this.mData != null) {
            return this.mData.length;
        }
        return 0;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public void setSubtype(String subtype) {
        this.mSubtype = subtype;
    }

    public void setStringData(String data) {
        this.mData = data != null ? data.getBytes() : null;
    }

    public void setData(byte[] data) {
        this.mData = data;
    }

    public String getEncoding() {
        return this.mEncoding;
    }

    public byte[] getData() {
        return this.mData;
    }

    public void setEncoding(String encoding) {
        this.mEncoding = encoding;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }
}

