package www.aql.com.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import www.aql.com.R;
import www.aql.com.activities.main.MainActivity;
import www.aql.com.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //设置窗体全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_splash_entering:
                startActivity(MainActivity.class);
                finish();
                break;
        }
    }
}
