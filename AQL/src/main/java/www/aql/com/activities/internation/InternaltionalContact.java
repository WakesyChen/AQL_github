package www.aql.com.activities.internation;

import java.util.List;

import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.Route;
import www.aql.com.entity.response.request.ReqRoutes;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/19.
 */
public class InternaltionalContact {
    interface IInternaltionalView extends IBaseView {
        void successFirstLoadRoutes(List<Route> list);

        void successRefreshLoadRoutes(List<Route> list);

        void successFirstLoadBanners(List<Banner> list);

        void successRefreshLoadBanners(List<Banner> list);

        void successLoadMore(List<Route> list);
    }

    interface IInternaltionalPresenter extends IBasePresenter {
        void firstLoadRoutes(ReqRoutes routes);

        void refreshLoadRoutes(ReqRoutes routes);

        void firstLoadBanners(long columnId);

        void refreshLoadBanners(long columnId);

        void loadMore(ReqRoutes routes);
    }
}
