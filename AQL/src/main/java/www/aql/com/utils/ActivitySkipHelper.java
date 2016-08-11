package www.aql.com.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivitySkipHelper {
    /**
     * Activity跳转
     *
     * @param <T>
     * @param from 当前Activity
     * @param to   要跳转的Activity
     */
    public static <T> void skipToActivity(Context from, Class<T> to) {
        Intent intent = new Intent();
        intent.setClass(from, to);
        from.startActivity(intent);
    }

    public static <T> void skipToActivityForResult(Activity from, Class<T> to, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(from, to);
        from.startActivityForResult(intent, requestCode);
    }

    /**
     * Activity带ArrayList<T>跳转
     *
     * @param <T>
     * @param from   当前Activity
     * @param class1 要跳转的Activity
     * @param bundle 要传的ArrayList<T>值
     */
    public static <T> void skipToActivityWithData(Context from, Class<T> class1, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(from, class1);
        intent.putExtras(bundle);
        from.startActivity(intent);
    }

    public static <T> void skipToActivityForResultWithData(Activity from, Class<T> class1, Bundle bundle, int
            requestCode) {
        Intent intent = new Intent();
        intent.setClass(from, class1);
        intent.putExtras(bundle);
        from.startActivityForResult(intent, requestCode);
    }
}
