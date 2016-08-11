package www.aql.com.activities.customerevaluationmore;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.adapter.LVEvaluationAdapter;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.Evaluation;
import www.aql.com.enums.Keys;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.ScreenUtils;

public class CustomerEvaluationMoreActivity extends BaseActivity implements CustomerEvaluationMoreContact
        .ICustomerEvaluationMoreView {

    @BindView(R.id.lv1)
    ListView lv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private String routeid;
    private CustomerEvaluationMorePresenter presenter;

    private int page = 1;
    private int rows = 10;
    private List<Evaluation> totallist;
    private LVEvaluationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_evaluation_more);
        ButterKnife.bind(this);

        presenter = new CustomerEvaluationMorePresenter(this);

        routeid = getIntent().getExtras().getString(Keys.ROUTE_ID);

        presenter.firstLoadEvaluation(routeid, page, rows);

        MyUtils.setSwipeStyle(swipe);
        //下拉刷新
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.refreshLoadEvaluation(routeid, page, rows);
            }
        });
    }

    @Override
    public void successRefreshLoadEvaluation(List<Evaluation> list) {
        swipe.setRefreshing(false);
        if (list != null && list.size() > 0) {
            totallist.clear();
            totallist.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            MyUtils.showToast(this, "获取评论失败");
        }
    }

    @Override
    public void successFirstLoadEvaluation(List<Evaluation> list) {
        if (list != null && list.size() > 0) {
            totallist = new ArrayList<>();
            totallist.addAll(list);
            initListView();
        } else {
            MyUtils.showToast(this, "获取评论失败");
        }
    }

    private void initListView() {
        adapter = new LVEvaluationAdapter(this, totallist);
        lv.setAdapter(adapter);
        lv.setOnScrollListener(new MyOnScrollListener());
    }

    @Override
    public void successLoadMore(List<Evaluation> list) {
        int position = totallist.size();
        if (list != null && list.size() > 0) {
            totallist.addAll(list);
            adapter.notifyDataSetChanged();
            lv.setSelection(position);
        } else {
            MyUtils.showToast(this, "获取评论失败");
        }
    }

    private boolean scrollFlag;

    @Override
    public void loadFail(String errMsg) {

    }

    @Override
    public void netException() {

    }

    //滑动到最后一个条目的时候自己去加载更多的数据
    private class MyOnScrollListener implements AbsListView.OnScrollListener {
        public boolean isBottom;
        public int firstVisibleItem;

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (scrollFlag && ScreenUtils.getScreenViewBottomHeight(lv) >= ScreenUtils
                    .getScreenHeight(CustomerEvaluationMoreActivity.this) / 20) {
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
                        presenter.loadMore(routeid, ++page, rows);
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
