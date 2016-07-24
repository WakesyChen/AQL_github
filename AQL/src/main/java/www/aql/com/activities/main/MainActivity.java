package www.aql.com.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.activities.BaseActivity;
import www.aql.com.activities.UserCenterActivity.UserCenterActivity;
import www.aql.com.activities.around.AroundActivity;
import www.aql.com.activities.innercountry.InnerCountryActivity;
import www.aql.com.activities.internation.InternationalActivity;
import www.aql.com.activities.more.MoreActivity;
import www.aql.com.activities.routedetail.RouteDetailActivity;
import www.aql.com.activities.selectcity.SelectCityActivity;
import www.aql.com.adapter.GVHomeSortAdapter;
import www.aql.com.adapter.LVRouteAdapter;
import www.aql.com.adapter.VPBannerAdapter;
import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.ColumnInfo;
import www.aql.com.entity.response.Route;
import www.aql.com.enums.ColumnID;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.DisplayHelper;
import www.aql.com.utils.MyUtils;
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
    private GridView header_gv;
    private ImageView header_img_search;
    private RadioGroup header_rg_dots;
    private ImageView header_img_userCenter;
    private List<Route> totallist_lv;
    private LVRouteAdapter lvHomeAdapter;
    private GVHomeSortAdapter gvHomeHeaderAdapter;
    private VPBannerAdapter vpHomeHeaderAdapter;
    private int[] img_columnInfo;


    private int lastVisibleItemPosition;
    private boolean scrollFlag;
    private int currentRouteSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);

        //初始化ListView的header
        initHeader();

        initSwipe();

        img_columnInfo = new int[]{R.drawable.international, R.drawable.innercountry, R.drawable.outercountry, R
                .drawable.more};

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
        header_rg_dots = (RadioGroup) header.findViewById(R.id.rg_dots);
        header_img_userCenter = (ImageView) header.findViewById(R.id.img_user_center);
        header_img_search = (ImageView) header.findViewById(R.id.img_search);
        header_gv = (GridView) header.findViewById(R.id.gv);
        lv.addHeaderView(header);

        header_img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入选择城市
                ActivitySkipHelper.skipToActivity(MainActivity.this, SelectCityActivity.class);
            }
        });
        header_img_userCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入用户中心
                ActivitySkipHelper.skipToActivity(MainActivity.this, UserCenterActivity.class);
            }
        });
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
            RadioButton rb = new RadioButton(this);
            rb.setButtonDrawable(android.R.color.transparent);
            int width_height = DisplayHelper.dp2px(this, 5);
            int margin = DisplayHelper.dp2px(this, 5);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width_height, width_height);
            params.setMargins(margin, margin, margin, margin);
            rb.setLayoutParams(params);
            rb.setBackgroundResource(R.drawable.selector_dot);

            header_rg_dots.addView(rb);
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
        gvHomeHeaderAdapter.notifyDataSetChanged();
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
            lv.setSelection(currentRouteSize);
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
                ActivitySkipHelper.skipToActivityWithData(MainActivity.this, RouteDetailActivity.class, bundle);
            }
        });

        lv.setOnScrollListener(new MyOnScrollListener());
    }

    private void initGridView() {
        gvHomeHeaderAdapter = new GVHomeSortAdapter(this, totallist_gv, img_columnInfo);
        header_gv.setAdapter(gvHomeHeaderAdapter);
        header_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ColumnInfo.class.getName(), totallist_gv.get(i));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                int columnid = (int) totallist_gv.get(i).columnid;
                switch (columnid) {
                    case ColumnID.INTERNATIONAL://国际
                        intent.setClass(MainActivity.this, InternationalActivity.class);
                        break;
                    case ColumnID.INNTER_COUNTRY://国内
                        intent.setClass(MainActivity.this, InnerCountryActivity.class);
                        break;
                    case ColumnID.AROUND://周边
                        intent.setClass(MainActivity.this, AroundActivity.class);
                        break;
                    case ColumnID.MORE://更多
                        intent.setClass(MainActivity.this, MoreActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
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
                lastVisibleItemPosition = firstVisibleItem;
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
}
