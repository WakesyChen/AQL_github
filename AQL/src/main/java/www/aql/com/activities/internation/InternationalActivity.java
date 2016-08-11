package www.aql.com.activities.internation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.routedetail.RouteDetailActivity;
import www.aql.com.adapter.LVRouteAdapter;
import www.aql.com.adapter.VPBannerAdapter;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.ColumnInfo;
import www.aql.com.entity.response.Route;
import www.aql.com.entity.response.request.ReqRoutes;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.DisplayHelper;
import www.aql.com.utils.MobileDisplayHelper;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.ScreenUtils;

public class InternationalActivity extends BaseActivity implements InternaltionalContact.IInternaltionalView {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private InternaltionalPresenter presenter;
    private ColumnInfo columnInfo;
    private ViewPager header_vp;
    private RadioGroup header_rg_dots;
    private List<Route> totallist_lv;
    private ArrayList<Banner> totallist_vp;
    private VPBannerAdapter vpBannerAdapter;
    private LVRouteAdapter lvRouteAdapter;

    private int page = 1;
    private int rows = 7;
    private int currentRouteSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international);
        ButterKnife.bind(this);

        presenter = new InternaltionalPresenter(this);

        initData();
        initHeader();
        initSwipe();

    }

    private void initSwipe() {
        MyUtils.setSwipeStyle(swipe);
        //下拉刷新
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ReqRoutes reqRoutes = new ReqRoutes();
                reqRoutes.columnid = columnInfo.columnid;
                reqRoutes.cityid = "";
                reqRoutes.page = page;
                reqRoutes.rows = rows;
                reqRoutes.routename = "";
                reqRoutes.cityname = "";
                presenter.refreshLoadRoutes(reqRoutes);
            }
        });
    }

    /**
     * 初始化ListView的header
     */
    private void initHeader() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_lv_international_innercountry, null);
        header_vp = (ViewPager) header.findViewById(R.id.vp);
        MobileDisplayHelper.setBannerHeight(this, header_vp);
        header_rg_dots = (RadioGroup) header.findViewById(R.id.rg_dots);
        lv.addHeaderView(header);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            columnInfo = bundle.getParcelable(ColumnInfo.class.getName());
            ReqRoutes reqRoutes = new ReqRoutes();
            reqRoutes.columnid = columnInfo.columnid;
            reqRoutes.cityid = "";
            reqRoutes.page = page;
            reqRoutes.rows = rows;
            reqRoutes.routename = "";
            reqRoutes.cityname = "";
            presenter.firstLoadRoutes(reqRoutes);
            presenter.firstLoadBanners(columnInfo.columnid);
        } else {
            Log.i("jason", "bundle为空");
        }
    }

    @Override
    public void successFirstLoadRoutes(List<Route> list) {
        totallist_lv = new ArrayList<>();
        if (list != null) {
            totallist_lv.addAll(list);
            initListView();
        } else {
            MyUtils.showToast(this, "暂无路线数据");
        }
    }


    private void initListView() {
        lvRouteAdapter = new LVRouteAdapter(this, totallist_lv);
        lv.setAdapter(lvRouteAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到路线详情
                int headerViewsCount = lv.getHeaderViewsCount();
                Route route = totallist_lv.get(position - headerViewsCount);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Route.class.getName(), route);
                Log.i("jason", "点击的route：" + route);
                ActivitySkipHelper.skipToActivityWithData(InternationalActivity.this, RouteDetailActivity.class,
                        bundle);
            }
        });
        lv.setOnScrollListener(new MyOnScrollListener());
    }

    @Override
    public void successRefreshLoadRoutes(List<Route> list) {
        presenter.refreshLoadBanners(columnInfo.columnid);
    }

    @Override
    public void successFirstLoadBanners(List<Banner> list) {
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
    public void successRefreshLoadBanners(List<Banner> list) {
        swipe.setRefreshing(false);
        vpBannerAdapter.notifyDataSetChanged();
        lvRouteAdapter.notifyDataSetChanged();
    }

    @Override
    public void successLoadMore(List<Route> list) {
        currentRouteSize = totallist_lv.size();
        if (list != null && list.size() > 0) {
            totallist_lv.addAll(list);
            lvRouteAdapter.notifyDataSetChanged();
            lv.setSelection(currentRouteSize + lv.getHeaderViewsCount());
        } else {
            MyUtils.showToast(this, "暂无更多数据");
        }
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

    private void initViewPager() {
        vpBannerAdapter = new VPBannerAdapter(this, totallist_vp);
        header_vp.setAdapter(vpBannerAdapter);
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

    @OnClick(R.id.img_user_center)
    public void onClick() {
        //进入到用户中心
        MyUtils.showToast(InternationalActivity.this, "技术人员正在开发中");
    }


    //滑动到最后一个条目的时候自己去加载更多的数据
    private boolean scrollFlag;

    private class MyOnScrollListener implements AbsListView.OnScrollListener {
        public boolean isBottom;
        public int firstVisibleItem;

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (scrollFlag && ScreenUtils.getScreenViewBottomHeight(lv) >= ScreenUtils
                    .getScreenHeight(InternationalActivity.this) / 20) {
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
                        ReqRoutes reqRoutes = new ReqRoutes();
                        reqRoutes.columnid = columnInfo.columnid;
                        reqRoutes.cityid = "";
                        reqRoutes.page = page + 1;
                        reqRoutes.rows = rows;
                        reqRoutes.routename = "";
                        reqRoutes.cityname = "";
                        presenter.loadMore(reqRoutes);
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
