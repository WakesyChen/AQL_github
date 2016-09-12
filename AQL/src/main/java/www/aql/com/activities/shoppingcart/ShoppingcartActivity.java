package www.aql.com.activities.shoppingcart;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.Shoppingcart;
import www.aql.com.entity.response.request.ReqShoppingcart;
import www.aql.com.enums.Keys;
import www.aql.com.utils.SPUtils;

public class ShoppingcartActivity extends BaseActivity implements ShopCartContract.IShopCartView {

    @BindView(R.id.cb_selectAll)
    CheckBox cbSelectAll;
    @BindView(R.id.tv_selectAll)
    TextView tvSelectAll;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_gotoPurchase)
    TextView tvGotoPurchase;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private ShopCartPresenter presenter;

    int page = 1;
    int rows = 7;
    String userid;
    String shoppingcartid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        ButterKnife.bind(this);

        presenter = new ShopCartPresenter(this);

        userid = SPUtils.getString(this, Keys.USER_ID, "");
        if (userid.equals("")) {
            //跳转到登录页面
            //            Bundle bundle = new Bundle();
            //            bundle.putString(Keys.REQUEST_LOGIN_FOR_SHOPPINGCART, Values.REQUEST_LOGIN_FOR_SHOPPINGCART);
            //            ActivitySkipHelper.skipToActivityWithData(this, LoginActivity.class, bundle);
        }

        ReqShoppingcart req = new ReqShoppingcart();
        req.userid = userid;
        req.shopingcartid = shoppingcartid;
        req.page = page;
        req.rows = rows;
        presenter.firstLoadShopingcart(req);


    }

    @Override
    public void successFirstLoadShoppingcart(List<Shoppingcart> list) {
        //设置适配器


    }

    @Override
    public void successRefreshLoadShoppingcart(List<Shoppingcart> list) {

    }

    @OnClick({R.id.cb_selectAll, R.id.tv_gotoPurchase})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_selectAll:
                break;
            case R.id.tv_gotoPurchase:
                break;
        }
    }
}
