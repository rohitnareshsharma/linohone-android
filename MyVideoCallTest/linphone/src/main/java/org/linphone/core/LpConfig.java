/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public interface LpConfig {
    public void setInt(String var1, String var2, int var3);

    public void setFloat(String var1, String var2, float var3);

    public void setBool(String var1, String var2, boolean var3);

    public void setString(String var1, String var2, String var3);

    public void setIntRange(String var1, String var2, int var3, int var4);

    public int getInt(String var1, String var2, int var3);

    public float getFloat(String var1, String var2, float var3);

    public boolean getBool(String var1, String var2, boolean var3);

    public String getString(String var1, String var2, String var3);

    public int[] getIntRange(String var1, String var2, int var3, int var4);

    public void sync();
}

