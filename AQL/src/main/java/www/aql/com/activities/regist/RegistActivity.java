package www.aql.com.activities.regist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.RegistResponse;
import www.aql.com.entity.response.VerificationResponse;
import www.aql.com.enums.ResponseErrorCode;
import www.aql.com.enums.VerificationType;
import www.aql.com.utils.MyUtils;

public class RegistActivity extends BaseActivity implements RegistContact.IRegistView {

    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.et_userphone)
    EditText etUserphone;
    @BindView(R.id.et_verificationCode)
    EditText etVerificationCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    private RegistPresenter presenter;
    private String verificationCode;
    private int counter = 60;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (counter < 0) {
                        scheduledExecutorService.shutdown();
                        tvVerify.setEnabled(true);
                    } else {
                        tvVerify.setText(counter-- + "s");
                    }
                    break;
                case 1:
                    break;
            }
        }
    };
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        presenter = new RegistPresenter(this);
    }

    @Override
    public void finishRegist(RegistResponse response) {
        if (response.errcode == ResponseErrorCode.FAIL_MSG_SEND) {
            MyUtils.showToast(this, "服务器短信发送失败");
        } else if (response.errcode == ResponseErrorCode.TEL_HAS_BEEN_REGISTED) {
            MyUtils.showToast(this, "该手机号已被注册");
        } else if (response.errcode == BaseResponse.SUCCESS_OK) {
            MyUtils.showToast(this, "注册成功");
            finish();
        } else {
            MyUtils.showToast(this, "注册失败");
            tvRegist.setEnabled(true);
        }
    }

    @Override
    public void finishGetVerification(VerificationResponse response) {
        if (response.errcode == BaseResponse.SUCCESS_OK) {
            verificationCode = response.data.code;
        }
        tvVerify.setEnabled(true);
    }

    @OnClick({R.id.tv_verify, R.id.tv_regist, R.id.tv_protocal})
    public void onClick(View view) {
        String userphone = "";
        switch (view.getId()) {
            case R.id.tv_verify:
                userphone = etUserphone.getText() + "";
                if (userphone.equals("")) {
                    MyUtils.showToast(this, "请填写手机号码");
                    return;
                }
                if (!MyUtils.isPhoneNumber(userphone)) {
                    MyUtils.showToast(this, "请填写正确的手机号码");
                    return;
                }
                presenter.getVerification(userphone, VerificationType.REGIST, "");
                tvVerify.setEnabled(false);
                excuteTimeTask();
                break;
            case R.id.tv_regist:
                userphone = etUserphone.getText() + "";
                if (userphone.equals("")) {
                    MyUtils.showToast(this, "请填写手机号码");
                    return;
                }
                if (!MyUtils.isPhoneNumber(userphone)) {
                    MyUtils.showToast(this, "请填写正确的手机号码");
                    return;
                }
                String password = etPassword.getText() + "";
                if (password.equals("")) {
                    MyUtils.showToast(this, "请设置密码");
                    return;
                }
                String code = etVerificationCode.getText() + "";
                if (code.equals("")) {
                    MyUtils.showToast(this, "请填写验证码");
                    return;
                }
                if (!code.equals(verificationCode)) {
                    MyUtils.showToast(this, "验证码不正确");
                    return;
                }
                presenter.regist(userphone, password, "");
                tvRegist.setEnabled(false);
                break;
            case R.id.tv_protocal:
                break;
        }
    }

    private void excuteTimeTask() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
}
