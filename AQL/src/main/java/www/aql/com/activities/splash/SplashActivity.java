package www.aql.com.activities.splash;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import www.aql.com.R;
import www.aql.com.activities.BaseActivity;
import www.aql.com.activities.main.MainActivity;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;

public class SplashActivity extends BaseActivity implements SplashContact.ISplashView {
    Context context = this;
    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置窗体全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        presenter = new SplashPresenter(this);
        presenter.loadToken();

    }

    private void run() {
        //判断是否第一次进入app
        boolean isFirstEnter = SPUtils.getBoolean(context, SPConfig.IS_FIRST_ENTER, false);
        if (!isFirstEnter) {
            SPUtils.putBoolean(context, SPConfig.IS_FIRST_ENTER, true);
            setContentView(R.layout.activity_splash);
        } else {
            ActivitySkipHelper.skipToActivity(SplashActivity.this, MainActivity.class);
            finish();
        }
    }

    public void click(View view) {
        super.click(view);
        switch (view.getId()) {
            case R.id.btn_splash_entering:
                ActivitySkipHelper.skipToActivity(SplashActivity.this, MainActivity.class);
                finish();
                break;
        }
    }

    @Override
    public void successLoadToken() {
        run();
    }

    @Override
    public void loadFail(String errMsg) {
        super.loadFail(errMsg);
        run();
    }

    @Override
    public void netException() {
        super.netException();
        run();
    }
}
