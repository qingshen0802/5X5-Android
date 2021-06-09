package com.zenlabs.z5x5.Utils;

import android.content.res.Resources;

/**
 * Created by admin on 7/22/16.
 */
public class Utils {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
