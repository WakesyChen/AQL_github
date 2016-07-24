package www.aql.com.activities.routedetail;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.activities.BaseActivity;
import www.aql.com.entity.response.Route;

public class RouteDetailActivity extends BaseActivity {

    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    @BindView(R.id.tv_shoppingcart)
    TextView tvShoppingcart;
    @BindView(R.id.tv_one_yuan_purchase)
    TextView tvOneYuanPurchase;
    @BindView(R.id.tv_prebuy_now)
    TextView tvPrebuyNow;
    @BindView(R.id.webView)
    WebView webView;
    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            route = bundle.getParcelable(Route.class.getName());
        } else {
            Log.i("jason", "bundle为空");
        }
    }
}
