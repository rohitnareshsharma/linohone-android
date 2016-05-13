/*
 * Decompiled with CFR 0_114.
 */
package org.linphone.core;

public interface LinphoneLogHandler {
    public static final int Fatal = 16;
    public static final int Error = 8;
    public static final int Warn = 4;
    public static final int Info = 2;
    public static final int Debug = 1;

    public void log(String var1, int var2, String var3, String var4, Throwable var5);
}

