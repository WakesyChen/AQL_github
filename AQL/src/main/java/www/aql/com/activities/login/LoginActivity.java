package www.aql.com.activities.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import www.aql.com.R;
import www.aql.com.activities.regist.RegistActivity;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.LoginResponse;
import www.aql.com.entity.response.User;
import www.aql.com.enums.Keys;
import www.aql.com.enums.ResponseErrorCode;
import www.aql.com.enums.ResultCode;
import www.aql.com.enums.Values;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;

public class LoginActivity extends BaseActivity implements LoginContact.ILoginView, PlatformActionListener {

    @BindView(R.id.et_userphone)
    EditText etUserphone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    @BindView(R.id.tv_forgetPassword)
    TextView tvForgetPassword;
    @BindView(R.id.img_qq_login)
    ImageView imgQqLogin;
    @BindView(R.id.img_wechat_login)
    ImageView imgWechatLogin;
    @BindView(R.id.img_sina_login)
    ImageView imgSinaLogin;
    private LoginContact.ILoginPresenter presenter;
    private String fromWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromWhere = bundle.getString(Keys.FROM_WHERE);
        }

        presenter = new LoginPresenter(this);

    }

    @OnClick({R.id.tv_login, R.id.tv_regist, R.id.tv_forgetPassword, R.id.img_qq_login, R.id.img_wechat_login, R.id
            .img_sina_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String userphone = etUserphone.getText() + "";
                String password = etPassword.getText() + "";
                if (userphone.equals("")) {
                    MyUtils.showToast(this, "请输入手机号");
                    return;
                }
                if (!MyUtils.isPhoneNumber(userphone)) {
                    MyUtils.showToast(this, "请填写正确的手机号码");
                    return;
                }
                if (password.equals("")) {
                    MyUtils.showToast(this, "请输入密码");
                    return;
                }
                presenter.login(userphone, password);
                break;
            case R.id.tv_regist:
                ActivitySkipHelper.skipToActivity(LoginActivity.this, RegistActivity.class);
                break;
            case R.id.tv_forgetPassword:
                break;
            case R.id.img_qq_login:
                oauthLogin(QQ.NAME);
                break;
            case R.id.img_wechat_login:
                //TODO 微信登录
                //                oauthLogin(Wechat.NAME);
                break;
            case R.id.img_sina_login:
                //TODO 微博登录
                //                oauthLogin(SinaWeibo.NAME);
                break;
        }
    }

    private void oauthLogin(String platformName) {
        Platform platform = ShareSDK.getPlatform(this, platformName);
        platform.setPlatformActionListener(this);
        platform.SSOSetting(false);
        platform.showUser(null);
    }

    @Override
    public void finishLogin(LoginResponse response) {
        if (response.errcode == BaseResponse.SUCCESS_OK) {
            User user = response.data;
            SPUtils.putString(this, SPConfig.USER_ID, user.userid);
            SPUtils.putString(this, SPConfig.NICKNAME, user.nickname);
            SPUtils.putString(this, SPConfig.HEADER_URL, user.userheader);
            SPUtils.putString(this, SPConfig.USERPHONE, user.userphone);

            if (fromWhere != null) {
                if (fromWhere.equals(Values.FROM_ROUTE_DETAIL))
                    setResult(ResultCode.LOGIN_TO_PURCHASE);
                if (fromWhere.equals(Values.FROM_MAIN))
                    setResult(ResultCode.LOGIN_TO_ENTER_USERCENTER);
                if (fromWhere.equals(Values.FROM_SHOPPINGCART))
                    setResult(ResultCode.LOGIN_TO_ENTER_SHOPPINGCART);
                finish();
            }
        }
        if (response.errcode == ResponseErrorCode.USER_NOT_Exist) {
            MyUtils.showToast(this, "该手机用户不存在");
        }

        if (response.errcode == ResponseErrorCode.WRONG_TEL_OR_PASSWORD) {
            MyUtils.showToast(this, "手机号或密码错误");
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        PlatformDb db = platform.getDb();
        SPUtils.putString(this, SPConfig.USER_ID, db.getUserId());
        SPUtils.putString(this, SPConfig.NICKNAME, hashMap.get("nickname").toString());
        SPUtils.putString(this, SPConfig.HEADER_URL, hashMap.get("figureurl_qq_1").toString());
        SPUtils.putString(this, SPConfig.GENDER, hashMap.get("gender").toString());
        //TODO 注册
        LoginActivity.this.finish();

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        MyUtils.showToast(LoginActivity.this, "登录失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        MyUtils.showToast(LoginActivity.this, "授权失败");
    }
}
