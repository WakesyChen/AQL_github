package www.aql.com.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import www.aql.com.R;
import www.aql.com.applicaton.MyApplication;
import www.aql.com.broadcast.NetState;
import www.aql.com.interfaces.IBaseView;
import www.aql.com.utils.MyUtils;

/**
 * Created by Jason on 2016/7/5.
 */
public class BaseActivity extends Activity implements IBaseView {

    private NetState receiver;
    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加入到Activity栈中
        MyApplication.getInstance().addActivity(this);

        //强制设置竖屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //网络状态监听
        receiver = MyUtils.monitorNetState(this);

        initDialog();
    }

    private void initDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("加载中...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminate(true);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void loadFail(String errMsg) {
        MyUtils.showToast(this, "请求失败:" + errMsg);
    }

    @Override
    public void netException() {
        MyUtils.showToast(this, "服务器无响应");
    }
}


