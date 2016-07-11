package www.aql.com.activities;

import android.os.Bundle;
import android.widget.ImageView;

import www.aql.com.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView img = (ImageView) findViewById(R.id.img_splash);
    }
}
