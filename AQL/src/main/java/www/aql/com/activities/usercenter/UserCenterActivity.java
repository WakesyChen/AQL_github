package www.aql.com.activities.usercenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.applyforcompanyuser.ApplyForCompanyUserActivity;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.UserInfo;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;

public class UserCenterActivity extends BaseActivity implements UserCenterContact.IUserCenterView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_scan_code)
    ImageView imgScanCode;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
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
    @BindView(R.id.rl_userBaseInfo)
    RelativeLayout rlUserBaseInfo;
    @BindView(R.id.tv_seperator1)
    TextView tvSeperator1;
    @BindView(R.id.tv_evaluationCount)
    TextView tvEvaluationCount;
    @BindView(R.id.rl_evaluation)
    RelativeLayout rlEvaluation;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.rl_praise)
    RelativeLayout rlPraise;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.ll_evaluation_praise_collection)
    LinearLayout llEvaluationPraiseCollection;
    @BindView(R.id.img_shoppingcart)
    ImageView imgShoppingcart;
    @BindView(R.id.tv_shoppingcart)
    TextView tvShoppingcart;
    @BindView(R.id.rl_shoppingcart)
    RelativeLayout rlShoppingcart;
    @BindView(R.id.tv_seperator2)
    TextView tvSeperator2;
    @BindView(R.id.img_my_order)
    ImageView imgMyOrder;
    @BindView(R.id.tv_my_order)
    TextView tvMyOrder;
    @BindView(R.id.rl_my_order)
    RelativeLayout rlMyOrder;
    @BindView(R.id.tv_seperator3)
    TextView tvSeperator3;
    @BindView(R.id.img_luck_record)
    ImageView imgLuckRecord;
    @BindView(R.id.tv_luck_record)
    TextView tvLuckRecord;
    @BindView(R.id.rl_luck_record)
    RelativeLayout rlLuckRecord;
    @BindView(R.id.tv_seperator4)
    TextView tvSeperator4;
    @BindView(R.id.img_my_medal)
    ImageView imgMyMedal;
    @BindView(R.id.tv_my_medal)
    TextView tvMyMedal;
    @BindView(R.id.rl_my_medal)
    RelativeLayout rlMyMedal;
    @BindView(R.id.tv_seperator5)
    TextView tvSeperator5;
    @BindView(R.id.img_upload_credential)
    ImageView imgUploadCredential;
    @BindView(R.id.tv_upload_credential)
    TextView tvUploadCredential;
    @BindView(R.id.rl_upload_credential)
    RelativeLayout rlUploadCredential;
    @BindView(R.id.tv_seperator6)
    TextView tvSeperator6;
    @BindView(R.id.img_my_red_packet)
    ImageView imgMyRedPacket;
    @BindView(R.id.tv_my_red_packet)
    TextView tvMyRedPacket;
    @BindView(R.id.rl_my_red_packet)
    RelativeLayout rlMyRedPacket;
    @BindView(R.id.tv_seperator7)
    TextView tvSeperator7;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;
    @BindView(R.id.tv_seperator8)
    TextView tvSeperator8;
    @BindView(R.id.img_activity_competition)
    ImageView imgActivityCompetition;
    @BindView(R.id.tv_my_activity_competitiont)
    TextView tvMyActivityCompetitiont;
    @BindView(R.id.rl_activity_competition)
    RelativeLayout rlActivityCompetition;
    @BindView(R.id.tv_seperator9)
    TextView tvSeperator9;
    @BindView(R.id.img_extend_vip)
    ImageView imgExtendVip;
    @BindView(R.id.tv_extend_vip)
    TextView tvExtendVip;
    @BindView(R.id.rl_extend_vip)
    RelativeLayout rlExtendVip;
    @BindView(R.id.tv_seperator10)
    TextView tvSeperator10;
    @BindView(R.id.img_my_fans)
    ImageView imgMyFans;
    @BindView(R.id.tv_my_fans)
    TextView tvMyFans;
    @BindView(R.id.rl_my_fans)
    RelativeLayout rlMyFans;
    @BindView(R.id.tv_seperator11)
    TextView tvSeperator11;
    @BindView(R.id.img_my_wallet)
    ImageView imgMyWallet;
    @BindView(R.id.tv_my_wallet)
    TextView tvMyWallet;
    @BindView(R.id.rl_my_wallet)
    RelativeLayout rlMyWallet;
    @BindView(R.id.tv_seperator12)
    TextView tvSeperator12;
    @BindView(R.id.img_kefu)
    ImageView imgKefu;
    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    @BindView(R.id.rl_kefu)
    RelativeLayout rlKefu;
    @BindView(R.id.tv_seperator13)
    TextView tvSeperator13;
    @BindView(R.id.img_setting)
    ImageView imgSetting;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private UserCenterPresenter presenter;

    private final int CompanyUser = 1;
    private final int normalUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        ButterKnife.bind(this);

        initData();

        presenter = new UserCenterPresenter(this);
        presenter.loadUserInfo(SPUtils.getString(this, SPConfig.USER_ID, ""));

    }

    private void bindData(UserInfo userInfo) {
        //        initData();
        if (userInfo.isenterpriseuser == CompanyUser) {
            tvApplyCompanyUser.setVisibility(View.GONE);
        } else if (userInfo.isenterpriseuser == normalUser) {
            tvApplyCompanyUser.setVisibility(View.VISIBLE);
            rlExtendVip.setVisibility(View.GONE);
            rlMyFans.setVisibility(View.GONE);
            rlMyRedPacket.setVisibility(View.GONE);
            rlActivityCompetition.setVisibility(View.GONE);
        }
        tvEvaluationCount.setText(userInfo.routereviewnum);
        tvPraise.setText(userInfo.routelikenum);
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
                ActivitySkipHelper.skipToActivity(UserCenterActivity.this, ApplyForCompanyUserActivity.class);
                break;

        }
    }

    @Override
    public void successLoadUserInfo(UserInfo userInfo) {
        bindData(userInfo);
    }
}
