package www.aql.com.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
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
}
