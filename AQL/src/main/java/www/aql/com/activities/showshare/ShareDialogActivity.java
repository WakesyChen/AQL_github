package www.aql.com.activities.showshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.entity.response.Route;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.ShareHelper;


/**
 * author:wamcs
 * date:2016/3/12
 * email:kaili@hustunique.com
 */
public class ShareDialogActivity extends AppCompatActivity {

    private String mTitle;
    private String mText = "我在爱骑旅，一起来玩儿吧";
    private String mImageUrl;
    private String mShareUrl;
    private Route route;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_share);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        route = intent.getParcelableExtra(Keys.ROUTE);
        mTitle = route.routename;
        mImageUrl = MyUrls.service_Url + route.routeimage;
        mShareUrl = MyUrls.service_Url + route.urlinfo.lineurl;
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
    }

    @OnClick({R.id.share_to_friend_group, R.id.share_to_weixin, R.id.share_to_sina, R.id.share_to_qzone, R.id
            .share_to_qq, R.id.share_to_other, R.id.dialog_btn_orange})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_to_friend_group:
                ShareHelper.share(ShareHelper.SHARE_WECHAT_CIRCLE, mTitle, mText, mImageUrl, mShareUrl);
                finish();
                break;
            case R.id.share_to_weixin:
                ShareHelper.share(ShareHelper.SHARE_WECHAT_FRIENDS, mTitle, mText, mImageUrl, mShareUrl);
                finish();
                break;
            case R.id.share_to_sina:
                break;
            case R.id.share_to_qzone:
                break;
            case R.id.share_to_qq:
                ShareHelper.share(ShareHelper.SHARE_QQ, mTitle, mText, mImageUrl, mShareUrl);
                finish();
                break;
            case R.id.share_to_other:
                break;
            case R.id.dialog_btn_orange:
                finish();
                break;
        }
    }
}
