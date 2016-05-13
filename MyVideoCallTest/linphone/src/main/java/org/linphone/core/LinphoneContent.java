/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public interface LinphoneContent {
    public String getType();

    public String getSubtype();

    public String getEncoding();

    public String getDataAsString();

    public byte[] getData();

    public int getExpectedSize();

    public void setExpectedSize(int var1);

    public int getRealSize();

    public void setType(String var1);

    public void setSubtype(String var1);

    public void setEncoding(String var1);

    public void setStringData(String var1);

    public void setData(byte[] var1);

    public void setName(String var1);

    public String getName();
}

