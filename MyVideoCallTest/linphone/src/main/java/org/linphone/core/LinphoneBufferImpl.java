/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

import org.linphone.core.LinphoneBuffer;

public class LinphoneBufferImpl
implements LinphoneBuffer {
    private byte[] mData;
    private int mSize;

    public LinphoneBufferImpl(byte[] data, int size) {
        this.mData = data;
        this.mSize = size;
    }

    public byte[] getContent() {
        return this.mData;
    }

    public void setContent(byte[] data) {
        this.mData = data;
    }

    public int getSize() {
        return this.mSize;
    }

    public void setSize(int size) {
        this.mSize = size;
    }
}

