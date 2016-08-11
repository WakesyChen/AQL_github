package www.aql.com.activities.UserCenterActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.applyforcompanyuser.ApplyForCompanyUserActivity;
import www.aql.com.base.BaseActivity;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;

public class UserCenterActivity extends BaseActivity {

    @BindView(R.id.img_scan_code)
    ImageView imgScanCode;
    @BindView(R.id.tv_apply_company_user)
    TextView tvApplyCompanyUser;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_user_type)
    TextView tvUserType;
    @BindView(R.id.tv_user_location)
    TextView tvUserLocation;
    @BindView(R.id.tv_evaluationCount)
    TextView tvEvaluationCount;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.tv_collection)
    TextView tvCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvUsername.setText(SPUtils.getString(this, SPConfig.NICKNAME, SPConfig.DEFAULT_NICKNAME));
        Glide.with(this).load(SPUtils.getString(this, SPConfig.HEADER_URL, "")).error(R.drawable.default_avatar).into
                (imgAvatar);
    }

    @OnClick({R.id.rl_evaluation, R.id.rl_praise, R.id.rl_collection, R.id.rl_shoppingcart, R.id.rl_my_order, R.id
            .rl_luck_record, R.id.rl_my_medal, R.id.rl_upload_credential, R.id.rl_my_red_packet, R.id.rl_message, R
            .id.rl_activity_competition, R.id.rl_extend_vip, R.id.rl_my_fans, R.id.rl_my_wallet, R.id.rl_kefu, R.id
            .rl_setting, R.id.rl_userBaseInfo, R.id.tv_apply_company_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_userBaseInfo:
                break;
            case R.id.rl_evaluation:
                break;
            case R.id.rl_praise:
                break;
            case R.id.rl_collection:
                break;
            case R.id.rl_shoppingcart:
                Toast.makeText(this, "技术人员正在调试", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_my_order:
                Toast.makeText(this, "技术人员正在调试", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_luck_record:
                break;
            case R.id.rl_my_medal:
                break;
            case R.id.rl_upload_credential:
                break;
            case R.id.rl_my_red_packet:
                break;
            case R.id.rl_message:
                break;
            case R.id.rl_activity_competition:
                break;
            case R.id.rl_extend_vip:
                break;
            case R.id.rl_my_fans:
                break;
            case R.id.rl_my_wallet:
                break;
            case R.id.rl_kefu:
                break;
            case R.id.rl_setting:
                break;
            case R.id.tv_apply_company_user:
                //申请成为企业账户
                ActivitySkipHelper.skipToActivity(UserCenterActivity.this,ApplyForCompanyUserActivity.class);
                break;

        }
    }
}
