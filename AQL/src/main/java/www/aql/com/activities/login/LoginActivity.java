package www.aql.com.activities.login;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.activities.BaseActivity;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_describe)
    LinearLayout llDescribe;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.seperator)
    TextView seperator;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    @BindView(R.id.tv_forgetPassword)
    TextView tvForgetPassword;
    @BindView(R.id.tv_fastLogin)
    TextView tvFastLogin;
    @BindView(R.id.ll_fastLogin)
    LinearLayout llFastLogin;
    @BindView(R.id.img_qq_login)
    ImageView imgQqLogin;
    @BindView(R.id.img_wechat_login)
    ImageView imgWechatLogin;
    @BindView(R.id.img_sina_login)
    ImageView imgSinaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ImageView img = (ImageView) findViewById(R.id.img_splash);
    }
}
