package www.aql.com.activities.around;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.adapter.VPBannerAdapter;
import www.aql.com.base.BaseFragmentActivity;
import www.aql.com.base.MyFragmentPagerAdapter;
import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.ColumnInfo;
import www.aql.com.entity.response.Route;
import www.aql.com.enums.ColumnID;
import www.aql.com.fragments.AroundCompetitionFragment;
import www.aql.com.fragments.RideActivityFragment;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.RadioButtonHelper;

public class AroundActivity extends BaseFragmentActivity implements AroundContact.IAroundView {
    @BindView(R.id.vp_banner)
    ViewPager vp_banner;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rg_dots)
    RadioGroup rgDots;
    private ColumnInfo columnInfo;
    private List<Fragment> totallist_vp;
    private List<Banner> totallist_vp_banner;
    private Context context = this;
    private AroundPresenter presenter;

    private VPBannerAdapter vpHomeHeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_around);
        ButterKnife.bind(this);

        presenter = new AroundPresenter(this);

        initData();
        initView();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            columnInfo = bundle.getParcelable(ColumnInfo.class.getName());
            presenter.firstLoadBanners(columnInfo.columnid);
        } else {
            Log.i("jason", "bundle为空");
        }
    }

    private void initView() {
        totallist_vp = new ArrayList<>();
        totallist_vp.add(RideActivityFragment.getInstance(ColumnID.RIDE_ACTIVITY + ""));
        totallist_vp.add(AroundCompetitionFragment.getInstance(ColumnID.AROUND_COMPETITION + ""));
        List<String> list_tabName = new ArrayList<>();
        list_tabName.add("骑行活动");
        list_tabName.add("周边赛事");
        viewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), totallist_vp, list_tabName));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewpager);
    }

    //动态绘制RadioButton
    private void drawRadioButton() {
        for (int i = 0; i < totallist_vp.size(); i++) {
            rgDots.addView(RadioButtonHelper.drawRadioButton(this));
        }
        ((RadioButton) rgDots.getChildAt(0)).setChecked(true);
    }

    private void initViewPager() {
        vpHomeHeaderAdapter = new VPBannerAdapter(this, totallist_vp_banner);
        vp_banner.setAdapter(vpHomeHeaderAdapter);
        vp_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) rgDots.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void successFirstLoadRoutes(List<Route> list) {

    }

    @Override
    public void successRefreshLoadRoutes(List<Route> list) {

    }

    @Override
    public void successFirstLoadBanners(List<Banner> list) {
        totallist_vp_banner = new ArrayList<>();
        if (list != null && list.size() != 0) {
            this.totallist_vp_banner.addAll(list);
            //动态绘制RadioButton
            drawRadioButton();
            initViewPager();
        } else {
            MyUtils.showToast(this, "暂无banner图");
        }
    }

    @Override
    public void successRefreshLoadBanners(List<Banner> list) {

    }

    @Override
    public void successLoadMore(List<Route> list) {

    }
}
