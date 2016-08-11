package www.aql.com.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 应用程序Activity的基类
 *
 * @author 陈小默
 * @version 1.0.0
 */
public abstract class BaseActivity extends Activity {

    public BaseActivity() {
        ActivityUtils.addActivity(this);
    }

    /**
     * 获得当前Activity的根布局
     *
     * @return
     */
    public View getRootView() {
        return ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * 获得当前Activity的高（像素）
     *
     * @return
     */
    public int getHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获得当前Activity的宽（像素）
     *
     * @return
     */
    public int getWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 启动Activity
     *
     * @param activity
     * @return
     */
    public Intent startActivity(Class<? extends Activity> activity) {
        return startActivity(activity, null);
    }

    /**
     * 启动带参数的activity
     *
     * @param activity
     * @param extra
     * @return
     */
    public Intent startActivity(Class<? extends Activity> activity, Bundle extra) {
        Intent intent = new Intent(this, activity);
        if (extra != null)
            intent.putExtras(extra);
        startActivity(intent);
        return intent;
    }

    /**
     * 使用请求参数启动Activity
     *
     * @param activity
     * @param requestCode
     * @return
     */
    public Intent startActivityForResult(Class<? extends Activity> activity, int requestCode) {
        Intent intent = new Intent(this, activity);
        startActivityForResult(intent, requestCode);
        return intent;
    }

    /**
     * dp单位数据转换为px单位
     *
     * @param dpValue dp单位
     * @return
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 吐司方法
     *
     * @param context
     */
    public void toast(Object context) {
        Toast.makeText(getApplicationContext(), context + "", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {

    }

    public void loadData() {

    }

    /**
     * 获取屏幕像素密度
     *
     * @return
     */
    public float getDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    /**
     * 更换或者显示Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    public void showFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}