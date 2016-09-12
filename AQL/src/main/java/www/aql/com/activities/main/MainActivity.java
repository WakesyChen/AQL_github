package www.aql.com.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.activities.around.AroundActivity;
import www.aql.com.activities.innercountry.InnerCountryActivity;
import www.aql.com.activities.internation.InternationalActivity;
import www.aql.com.activities.login.LoginActivity;
import www.aql.com.activities.routedetail.RouteDetailActivity;
import www.aql.com.activities.usercenter.UserCenterActivity;
import www.aql.com.adapter.LVRouteAdapter;
import www.aql.com.adapter.VPBannerAdapter;
import www.aql.com.applicaton.MyApplication;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.ColumnInfo;
import www.aql.com.entity.response.Route;
import www.aql.com.enums.Keys;
import www.aql.com.enums.RequestCode;
import www.aql.com.enums.ResultCode;
import www.aql.com.enums.Values;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.MobileDisplayHelper;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.RadioButtonHelper;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;
import www.aql.com.utils.ScreenUtils;

public class MainActivity extends BaseActivity implements MainContact.IMainView, View.OnClickListener {
    @BindView(R.id.lv1)
    ListView lv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private List<ColumnInfo> totallist_gv = new ArrayList<>();
    private MainPresenter presenter;
    List<Banner> totallist_vp;
    private ViewPager header_vp;
    private ImageView header_img_search;
    private RadioGroup header_rg_dots;
    private ImageView header_img_userCenter;
    private List<Route> totallist_lv;
    private LVRouteAdapter lvHomeAdapter;
    private VPBannerAdapter vpHomeHeaderAdapter;

    private boolean scrollFlag;
    private int currentRouteSize;
    private ImageView img_international;
    private ImageView img_innerCountry;
    private ImageView img_surround;
    private ImageView img_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);

        //初始化ListView的header
        initHeader();

        initSwipe();

        //请求banner图
        presenter.firstLoadBanners();
        //请求四大分类模块
        presenter.firstLoadColumnInfo();
        //请求线路列表
        presenter.firstLoadRoutes();
    }

    private void initSwipe() {
        MyUtils.setSwipeStyle(swipe);
        //下拉刷新
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshLoadRoutes();
            }
        });
    }

    /**
     * 初始化ListView的header
     */
    private void initHeader() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_lv_home, null);
        header_vp = (ViewPager) header.findViewById(R.id.vp);
        MobileDisplayHelper.setBannerHeight(this, header_vp);
        header_rg_dots = (RadioGroup) header.findViewById(R.id.rg_dots);
        header_img_userCenter = (ImageView) header.findViewById(R.id.img_user_center);
        header_img_search = (ImageView) header.findViewById(R.id.img_search);
        img_international = (ImageView) header.findViewById(R.id.img_international);
        img_innerCountry = (ImageView) header.findViewById(R.id.img_innerCountry);
        img_surround = (ImageView) header.findViewById(R.id.img_surrround);
        img_more = (ImageView) header.findViewById(R.id.img_more);
        lv.addHeaderView(header);

        header_img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.showToast(MainActivity.this, "技术人员正在调试中");
                //进入选择城市
                //                ActivitySkipHelper.skipToActivity(MainActivity.this, SelectCityActivity.class);
            }
        });
        header_img_userCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入用户中心
                String userid = SPUtils.getString(MainActivity.this, SPConfig.USER_ID, "");
                if (userid.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Keys.FROM_WHERE, Values.FROM_MAIN);
                    ActivitySkipHelper.skipToActivityForResultWithData(MainActivity.this, LoginActivity.class,
                            bundle, RequestCode.LOGIN_TO_ENTER_USERCENTER);
                    return;
                }
                ActivitySkipHelper.skipToActivity(MainActivity.this, UserCenterActivity.class);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ResultCode.LOGIN_TO_ENTER_USERCENTER) {
            ActivitySkipHelper.skipToActivity(MainActivity.this, UserCenterActivity.class);
        }
    }

    private void initViewPager() {
        vpHomeHeaderAdapter = new VPBannerAdapter(this, totallist_vp);
        header_vp.setAdapter(vpHomeHeaderAdapter);
        header_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) header_rg_dots.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //动态绘制RadioButton
    private void drawRadioButton() {
        for (int i = 0; i < totallist_vp.size(); i++) {
            header_rg_dots.addView(RadioButtonHelper.drawRadioButton(this));
        }
        ((RadioButton) header_rg_dots.getChildAt(0)).setChecked(true);
    }

    @Override
    public void successRefreshLoadColumnInfo(List<ColumnInfo> list) {
        if (list != null && list.size() > 0) {
            totallist_gv.clear();
            totallist_gv.addAll(list);
        } else {
            MyUtils.showToast(this, "暂无新数据");
        }
        presenter.refreshLoadBanners();
    }

    @Override
    public void successRefreshLoadRoute(List<Route> list) {
        if (list != null && list.size() > 0) {
            totallist_lv.clear();
            totallist_lv.addAll(list);
        } else {
            MyUtils.showToast(this, "暂无新数据");
        }
        presenter.refreshLoadColumnInfo();
    }

    @Override
    public void successRefreshLoadBanner(List<Banner> list) {
        if (list != null && list.size() > 0) {
            totallist_vp.clear();
            totallist_vp.addAll(list);
        } else {
            MyUtils.showToast(this, "暂无新数据");
        }
        swipe.setRefreshing(false);
        lvHomeAdapter.notifyDataSetChanged();
        vpHomeHeaderAdapter.notifyDataSetChanged();
    }

    @Override
    public void successFirstLoadBanner(List<Banner> list) {
        totallist_vp = new ArrayList<>();
        if (list != null && list.size() != 0) {
            this.totallist_vp.addAll(list);
            //动态绘制RadioButton
            drawRadioButton();
            initViewPager();
        } else {
            MyUtils.showToast(this, "暂无banner图");
        }
    }

    @Override
    public void successFirstLoadColumnInfo(List<ColumnInfo> list) {
        totallist_gv = new ArrayList<>();
        if (list != null) {
            totallist_gv.addAll(list);
            initGridView();
        } else {
            MyUtils.showToast(this, "暂无分类信息");
        }
    }

    @Override
    public void successFirstLoadRoute(List<Route> list) {
        totallist_lv = new ArrayList<>();
        if (list != null && list.size() != 0) {
            totallist_lv.addAll(list);
            initListView();
        } else {
            MyUtils.showToast(this, "暂无路线数据");
        }
    }

    @Override
    public void successLoadMore(List<Route> list) {
        currentRouteSize = totallist_lv.size();
        if (list != null && list.size() > 0) {
            totallist_lv.addAll(list);
            lvHomeAdapter.notifyDataSetChanged();
            lv.setSelection(currentRouteSize + lv.getHeaderViewsCount());
        } else {
            MyUtils.showToast(this, "暂无更多数据");
        }
    }

    private void initListView() {
        lvHomeAdapter = new LVRouteAdapter(this, totallist_lv);
        lv.setAdapter(lvHomeAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int headerViewsCount = lv.getHeaderViewsCount();
                Route route = totallist_lv.get(i - headerViewsCount);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Route.class.getName(), route);
                Log.i("jason", "点击的route:" + route);
                ActivitySkipHelper.skipToActivityWithData(MainActivity.this, RouteDetailActivity.class, bundle);
            }
        });

        lv.setOnScrollListener(new MyOnScrollListener());
    }

    private void initGridView() {
        img_international.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ColumnInfo.class.getName(), totallist_gv.get(0));
                ActivitySkipHelper.skipToActivityWithData(MainActivity.this, InternationalActivity.class, bundle);
            }
        });
        img_innerCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ColumnInfo.class.getName(), totallist_gv.get(1));
                ActivitySkipHelper.skipToActivityWithData(MainActivity.this, InnerCountryActivity.class, bundle);
            }
        });
        img_surround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                MyUtils.showToast(MainActivity.this, "技术人员正在开发中");
                Bundle bundle = new Bundle();
                bundle.putParcelable(ColumnInfo.class.getName(), totallist_gv.get(2));
                ActivitySkipHelper.skipToActivityWithData(MainActivity.this, AroundActivity.class,
                        bundle);
            }
        });
        img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.showToast(MainActivity.this, "技术人员正在开发中");
                Bundle bundle = new Bundle();
                bundle.putParcelable(ColumnInfo.class.getName(), totallist_gv.get(3));
                //                ActivitySkipHelper.skipToActivityWithData(MainActivity.this, MoreActivity.class,
                // bundle);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    //滑动到最后一个条目的时候自己去加载更多的数据
    private class MyOnScrollListener implements AbsListView.OnScrollListener {
        public boolean isBottom;
        public int firstVisibleItem;

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (scrollFlag && ScreenUtils.getScreenViewBottomHeight(lv) >= ScreenUtils
                    .getScreenHeight(MainActivity.this) / 20) {
            }

            this.firstVisibleItem = firstVisibleItem;
            if (firstVisibleItem + visibleItemCount == totalItemCount
                    && visibleItemCount < totalItemCount) {
                isBottom = true;
            }
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    if (isBottom && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                        isBottom = false;
                        //加载更多
                        presenter.loadMore();
                    }
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    scrollFlag = true;
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                    scrollFlag = false;
                    break;
            }
        }

    }

    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                MyUtils.showToast(getBaseContext(), "再按一次退出爱骑旅");
                exitTime = System.currentTimeMillis();
            } else {
                MyApplication.getInstance().AppExit();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
