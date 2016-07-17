package www.aql.com.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import www.aql.com.R;
import www.aql.com.applicaton.BaseApplication;
import www.aql.com.broadcast.NetState;
import www.aql.com.utils.CommonUtils;

/**
 * Created by Jason on 2016/7/5.
 */
public class BaseActivity extends Activity {

    private NetState receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加入到Activity栈中
        BaseApplication.getInstance().addActivity(this);

        //强制设置竖屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //网络状态监听
        receiver = CommonUtils.monitorNetState(this);
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
}


