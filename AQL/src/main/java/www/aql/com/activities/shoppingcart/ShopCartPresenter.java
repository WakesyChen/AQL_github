package www.aql.com.activities.shoppingcart;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.ShoppingcartResponse;
import www.aql.com.entity.response.request.ReqShoppingcart;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.NetWorkUtils;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by xyh on 2016/6/12.
 */
public class ShopCartPresenter implements ShopCartContract.IShopPresenter {
    private ShopCartContract.IShopCartView view;

    public ShopCartPresenter(ShopCartContract.IShopCartView shopCartView) {
        this.view = shopCartView;
    }

    @Override
    public void firstLoadShopingcart(ReqShoppingcart req) {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getShoppingcart);
        requestParams.addBodyParameter(Keys.USER_ID, req.userid + "");
        requestParams.addBodyParameter(Keys.SHOPPINGCART_ID, req.shopingcartid + "");
        requestParams.addBodyParameter(Keys.PAGE, req.page + "");
        requestParams.addBodyParameter(Keys.ROWS, req.rows + "");
        NetWorkUtils.getInstance().get(requestParams, new NetRequestCallBack<ShoppingcartResponse>() {
            @Override
            protected void onSuccess(ShoppingcartResponse response) {
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successFirstLoadShoppingcart(response.data);
                } else {
                    if (response.errmsg != null) {
                        view.loadFail(response.errmsg);
                    } else {
                        view.netException();
                    }
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    view.loadFail(errorMsg);
                } else {
                    view.netException();
                }
            }
        }, ShoppingcartResponse.class);

    }

    @Override
    public void refreshLoadShopingcart(ReqShoppingcart req) {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getShoppingcart);
        requestParams.addBodyParameter(Keys.USER_ID, req.userid + "");
        requestParams.addBodyParameter(Keys.SHOPPINGCART_ID, req.shopingcartid + "");
        requestParams.addBodyParameter(Keys.PAGE, req.page + "");
        requestParams.addBodyParameter(Keys.ROWS, req.rows + "");
        NetWorkUtils.getInstance().get(requestParams, new NetRequestCallBack<ShoppingcartResponse>() {
            @Override
            protected void onSuccess(ShoppingcartResponse response) {
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successRefreshLoadShoppingcart(response.data);
                } else {
                    if (response.errmsg != null) {
                        view.loadFail(response.errmsg);
                    } else {
                        view.netException();
                    }
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    view.loadFail(errorMsg);
                } else {
                    view.netException();
                }
            }
        }, ShoppingcartResponse.class);
    }
}
