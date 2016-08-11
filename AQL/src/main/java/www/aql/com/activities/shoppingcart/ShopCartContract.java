package www.aql.com.activities.shoppingcart;

import java.util.List;

import www.aql.com.entity.response.Shoppingcart;
import www.aql.com.entity.response.request.ReqShoppingcart;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by xyh on 2016/6/12.
 */
public class ShopCartContract {
    interface IShopCartView extends IBaseView {
        void successFirstLoadShoppingcart(List<Shoppingcart> list);

        void successRefreshLoadShoppingcart(List<Shoppingcart> list);
    }

    interface IShopPresenter extends IBasePresenter {
        void firstLoadShopingcart(ReqShoppingcart reqShoppingcart);

        void refreshLoadShopingcart(ReqShoppingcart reqShoppingcart);
    }
}
