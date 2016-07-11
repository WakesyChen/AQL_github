package www.aql.com.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import java.util.ArrayList;
import java.util.List;

/**
 * Activity栈追踪工具类
 *
 * @author 陈小默
 */
public class ActivityUtils {
    private static List<Activity> ACTIVITY_STACK = new ArrayList<Activity>();

    public ActivityUtils() {
    }

    /**
     * 添加一个Activity
     *
     * @param activity
     */
    public synchronized static void addActivity(Activity activity) {
        ACTIVITY_STACK.add(activity);
    }

    /**
     * 移除一个Activity
     *
     * @param activity
     */
    public synchronized static void removeActivity(Activity activity) {
        ACTIVITY_STACK.remove(activity);
    }

    /**
     * 销毁全部Activity
     *
     * @param runnable 用于在全部Activity销毁后执行的方法，打开新Activity的方法可以放在其中执行，以免被一起销毁
     */
    public synchronized static void killAllActivity(Runnable runnable) {
        Object[] array = ACTIVITY_STACK.toArray();
        for (Object object : array) {
            Activity activity = (Activity) object;
            activity.finish();
        }
        if (runnable != null)
            runnable.run();
    }

    /**
     * 在启动此activity之前销毁所有activity
     *
     * @param activity
     */
    public synchronized static void startActivity(final Context context, final Class<?> start, final Activity activity) {
        killAllActivity(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(context, start);
                activity.startActivity(intent);
            }
        });
    }
}
