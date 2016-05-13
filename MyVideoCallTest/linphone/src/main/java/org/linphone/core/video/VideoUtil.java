/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.hardware.Camera
 *  android.hardware.Camera$Size
 */
package org.linphone.core.video;

import android.hardware.Camera;
import java.util.ArrayList;
import java.util.List;
import org.linphone.core.VideoSize;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
final class VideoUtil {
    private VideoUtil() {
    }

    public static List<VideoSize> createList(List<Camera.Size> supportedVideoSizes) {
        ArrayList<VideoSize> converted = new ArrayList<VideoSize>(supportedVideoSizes.size());
        for (Camera.Size s : supportedVideoSizes) {
            converted.add(new VideoSize(s.width, s.height));
        }
        return converted;
    }
}

