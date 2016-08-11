package www.aql.com.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MobileDisplayHelper {
    /**
     * 获取当前手机的分辨率 width = size.x; height = size.y;
     *
     * @param context
     * @return
     */
    public static Point getMobileWidthHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }


    public static void setBannerHeight(Context context, View view) {
        int width = getMobileWidthHeight(context).x;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = 593 * width / 640;
        view.setLayoutParams(params);
    }

    public static void setRouteListHeight(Context context, View view) {
        int width = getMobileWidthHeight(context).x;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = 314 * width / 606;
        view.setLayoutParams(params);
    }
}
