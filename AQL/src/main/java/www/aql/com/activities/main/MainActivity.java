package www.aql.com.activities.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.activities.BaseActivity;
import www.aql.com.adapter.VPHomeAdapter;
import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.ColumnInfo;
import www.aql.com.utils.CommonUtils;
import www.aql.com.utils.DisplayHelper;

public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener {

    @BindView(R.id.lv)
    ListView lv;
    private MainPresenter presenter;
    List<Banner> totallist_vp;
    private ViewPager vp;
    private RadioGroup rg_dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initHeader();

        presenter = new MainPresenter(this);
        presenter.loadBanners();
        presenter.loadNetData();
    }

    private void initHeader() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_lv_main, null);
        vp = (ViewPager) header.findViewById(R.id.vp);
        header.findViewById(R.id.img_international).setOnClickListener(this);
        header.findViewById(R.id.img_innerOfCountry).setOnClickListener(this);
        header.findViewById(R.id.img_outerOfCountry).setOnClickListener(this);
        header.findViewById(R.id.img_more).setOnClickListener(this);
        rg_dots = (RadioGroup) header.findViewById(R.id.rg_dots);
        lv.addHeaderView(header);
    }

    @Override
    public void loadFail() {
        CommonUtils.showToast(this, "请求失败");
    }

    @Override
    public void finishedLoadBanner(List<Banner> list) {
        if (list != null && list.size() != 0) {
            this.totallist_vp = list;
            //动态绘制RadioButton
            drawRadioButton();
            setViewPagerAdapter();
        } else {
            CommonUtils.showToast(this, "未请求到数据");
        }
    }

    private void drawRadioButton() {
        RadioButton rb = new RadioButton(this);
        rb.setButtonDrawable(android.R.color.transparent);
        int width_height = DisplayHelper.dp2px(this, 5);
        rb.setLayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
        rb.setBackgroundResource(R.drawable.selector_dot);
        rg_dots.addView(rb);
    }

    private void setViewPagerAdapter() {
        vp.setAdapter(new VPHomeAdapter(this, totallist_vp));
    }

    @Override
    public void finishedLoadColumnInfo(List<ColumnInfo> list) {
        if (list != null) {
            this.totallist = list;
        }
    }

    private List<ColumnInfo> totallist;

    @Override
    public void netException() {
        CommonUtils.showToast(this, "服务器异常");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_international:
                break;
            case R.id.img_innerOfCountry:
                break;
            case R.id.img_outerOfCountry:
                break;
            case R.id.img_more:
                break;
        }
    }
}
