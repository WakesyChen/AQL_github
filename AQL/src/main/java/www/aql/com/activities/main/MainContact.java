package www.aql.com.activities.main;

import java.util.List;

import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.ColumnInfo;
import www.aql.com.entity.response.Route;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/19.
 */
public class MainContact {
    interface IMainView extends IBaseView {
        void successRefreshLoadBanner(List<Banner> list);

        void successRefreshLoadColumnInfo(List<ColumnInfo> list);

        void successRefreshLoadRoute(List<Route> list);

        void successFirstLoadBanner(List<Banner> list);

        void successFirstLoadColumnInfo(List<ColumnInfo> list);

        void successFirstLoadRoute(List<Route> list);

        void successLoadMore(List<Route> list);

    }

    interface IMainPresenter extends IBasePresenter {
        void refreshLoadBanners();

        void refreshLoadColumnInfo();

        void refreshLoadRoutes();

        void firstLoadBanners();

        void firstLoadColumnInfo();

        void firstLoadRoutes();

        void loadMore();
    }
}
