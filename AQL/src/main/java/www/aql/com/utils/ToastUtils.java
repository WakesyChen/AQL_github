package www.aql.com.utils;

import android.content.Context;
import android.widget.Toast;

import www.aql.com.applicaton.MyApplication;

/**
 * Created by Jason on 2016/8/31.
 */
public class ToastUtils {
    /**
     * 弹出短时间吐司
     *
     * @param context 上下文对象
     * @param text    吐司内容
     */
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String text) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
