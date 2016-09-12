package www.aql.com.activities.rideactivitydetail.routedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.buynow.BuyNowActivity;
import www.aql.com.activities.login.LoginActivity;
import www.aql.com.activities.reserve.ReserveActivity;
import www.aql.com.activities.showshare.ShareDialogActivity;
import www.aql.com.activities.visa.VisaActivity;
import www.aql.com.adapter.GVSelectIsueAdapter;
import www.aql.com.base.BaseFragmentActivity;
import www.aql.com.base.MyFragmentPagerAdapter;
import www.aql.com.entity.response.Issue;
import www.aql.com.entity.response.Route;
import www.aql.com.enums.Keys;
import www.aql.com.enums.RequestCode;
import www.aql.com.enums.Values;
import www.aql.com.fragments.CustomerEvaluationFragment;
import www.aql.com.fragments.RouteSpecialFragment;
import www.aql.com.fragments.SchedulaFragment;
import www.aql.com.fragments.ServiceOptionFragment;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.MobileDisplayHelper;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;

public class RideActivityDetailActivity extends BaseFragmentActivity implements RideActivityDetailContact
        .IRideActivityDetaillView {

    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    @BindView(R.id.tv_shoppingcart)
    TextView tvShoppingcart;
    @BindView(R.id.tv_one_yuan_purchase)
    TextView tvOneYuanPurchase;
    @BindView(R.id.tv_prebuy_now)
    TextView tvPrebuyNow;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.tv_routeName)
    TextView tvRouteName;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.tv_evaluationCount)
    TextView tvEvaluationCount;
    @BindView(R.id.tv_collectionCount)
    TextView tvCollectionCount;
    @BindView(R.id.tv_addCollection)
    TextView tvAddCollection;
    @BindView(R.id.tv_currentPrice)
    TextView tvCurrentPrice;
    @BindView(R.id.img_isDisCount)
    ImageView imgIsDisCount;
    @BindView(R.id.tv_marketPrice)
    TextView tvMarketPrice;
    @BindView(R.id.tv_selectIssue)
    TextView tvSelectIssue;
    @BindView(R.id.gv_selectIssue)
    GridView gvSelectIssue;
    @BindView(R.id.tv_selectIssue_describtion)
    TextView tvSelectIssueDescribtion;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_visaInfo)
    TextView tvVisaInfo;
    @BindView(R.id.tv_reserveInfo)
    TextView tvReserveInfo;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_prepay_money)
    TextView tvPrepayMoney;
    private Route route;
    private RideActivityDetailContact.IRideActivityDetailPresenter presenter;
    private List<Fragment> totallist_vp;
    private Issue currentIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_activity_detail);
        ButterKnife.bind(this);

        presenter = new RideActivityDetailPresenter(this);

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            route = bundle.getParcelable(Route.class.getName());
            bindViewWithData();
        } else {
            Log.i("jason", "bundle为空");
        }
    }

    private void bindViewWithData() {
        MobileDisplayHelper.setBannerHeight(this, imgTitle);
        Glide.with(this).load(MyUrls.service_Url + route.routeimage).error(R.drawable.default_banner).into(imgTitle);
        tvRouteName.setText(route.routename + "");
        tvCollectionCount.setText(route.liknum + "");
        tvEvaluationCount.setText(route.reviewnum + "");
        tvCurrentPrice.setText("￥" + route.currentprice);
        tvMarketPrice.setText("市场价￥" + route.marketprice);
        tvPrepayMoney.setText(route.prepaidmoney);

        gvSelectIssue.setAdapter(new GVSelectIsueAdapter(this, route.issues));
        gvSelectIssue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选择的团期
                currentIssue = route.issues.get(position);
            }
        });

        totallist_vp = new ArrayList<>();
        totallist_vp.add(SchedulaFragment.getInstance(route.urlinfo.schedulingurl));
        totallist_vp.add(RouteSpecialFragment.getInstance(route.urlinfo.lineurl));
        totallist_vp.add(ServiceOptionFragment.getInstance(route.urlinfo.servicesurl));
        totallist_vp.add(CustomerEvaluationFragment.getInstance(route.routeid));
        List<String> list_tabName = new ArrayList<>();
        list_tabName.add("行程安排");
        list_tabName.add("线路特色");
        list_tabName.add("服务项目");
        list_tabName.add("客户评价");
        viewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), totallist_vp, list_tabName));
        viewpager.setOffscreenPageLimit(3);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewpager);
    }

    @OnClick({R.id.tv_visaInfo, R.id.tv_reserveInfo, R.id.tv_kefu, R.id.tv_shoppingcart, R.id.tv_one_yuan_purchase, R
            .id.tv_prebuy_now, R.id.img_share})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_visaInfo:
                //跳转到签证信息
                bundle.putString(Keys.WEBVIEW_URL, route.urlinfo.visainfourl);
                ActivitySkipHelper.skipToActivityWithData(RideActivityDetailActivity.this, VisaActivity.class, bundle);
                break;
            case R.id.tv_reserveInfo:
                //跳转到预订须知
                bundle.putString(Keys.WEBVIEW_URL, route.urlinfo.reserveinfourl);
                ActivitySkipHelper.skipToActivityWithData(RideActivityDetailActivity.this, ReserveActivity.class,
                        bundle);
                break;
            case R.id.tv_kefu:
                break;
            case R.id.tv_shoppingcart:
                break;
            case R.id.tv_one_yuan_purchase:
                break;
            case R.id.tv_prebuy_now:
                String userid = SPUtils.getString(this, SPConfig.USER_ID, "");
                if (userid.equals("")) {
                    bundle.putString(Keys.FROM_WHERE, Values.REQUEST_LOGIN_FOR_BUY_NOW);
                    ActivitySkipHelper.skipToActivityForResultWithData(RideActivityDetailActivity.this, LoginActivity
                            .class,
                            bundle, RequestCode.LOGIN_TO_PURCHASE);
                    return;
                }
                if (route.issues == null || route.issues.size() == 0) {
                    Log.i("jason", "没有团期");
                } else {
                    if (currentIssue == null) {
                        MyUtils.showToast(this, "请先选择团期");
                        break;
                    }
                }

                bundle.putParcelable(Route.class.getName(), route);
                bundle.putString(Keys.FROM_WHERE, Values.FROM_ROUTE_DETAIL);
                bundle.putParcelable(Keys.CURRENT_ISSUE, currentIssue);
                ActivitySkipHelper.skipToActivityWithData(RideActivityDetailActivity.this, BuyNowActivity.class,
                        bundle);
                break;
            case R.id.img_share:
                Intent intent = new Intent(RideActivityDetailActivity.this, ShareDialogActivity.class);
                intent.putExtra(Keys.ROUTE, route);
                startActivity(intent);
                overridePendingTransition(R.anim.translate_in_bottom, R.anim.translate_out_bottom);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //        if (resultCode == ResultCode.LOGIN_TO_PURCHASE) {
        //            Bundle bundle = new Bundle();
        //            if (route.issues == null || route.issues.size() == 0) {
        //                Log.i("jason", "没有团期");
        //            } else {
        //                if (currentIssue == null) {
        //                    MyUtils.showToast(this, "请先选择团期");
        //                }
        //            }
        //
        //            bundle.putParcelable(Route.class.getName(), route);
        //            bundle.putString(Keys.FROM_WHERE, Values.FROM_ROUTE_DETAIL);
        //            bundle.putParcelable(Keys.CURRENT_ISSUE, currentIssue);
        //            ActivitySkipHelper.skipToActivityWithData(RideActivityDetailActivity.this, BuyNowActivity
        // .class, bundle);
        //        }
    }
}
