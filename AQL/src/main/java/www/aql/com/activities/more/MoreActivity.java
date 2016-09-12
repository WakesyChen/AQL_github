package www.aql.com.activities.more;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.base.BaseActivity;

public class MoreActivity extends BaseActivity {

    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_award)
    TextView tvAward;
    @BindView(R.id.gv_more)
    GridView gvMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        loadData();
    }

    private void loadData() {
//        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.getCitiesByProvince);
//        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<Object>() {
//            @Override
//            protected void onSuccess(Object o) {
//
//            }
//
//            @Override
//            protected void onFailed(String errorMsg) {
//            }
//
//        }, Object.class);
    }
}
