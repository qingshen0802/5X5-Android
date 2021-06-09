package com.zenlabs.z5x5.Utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by admin on 6/20/16.
 */
public class DeviceSize {

    public static Point deviceSize;
    public static Point getDeviceSize(Context context) {
        if ( deviceSize != null )
            return deviceSize;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deviceSize = size;
        return size;
    }

    public static int getDeviceWidth(Context context) {
        if (deviceSize != null) {
            return deviceSize.x;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deviceSize = size;
        return size.x;
    }

}
