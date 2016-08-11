package www.aql.com.activities.routedetail;

/**
 * Created by Jason on 2016/7/24.
 */
public class RouteDetailPresenter implements RouteDetailContact.IRouteDetailPresenter {
    private RouteDetailContact.IRouteDetailView view;

    public RouteDetailPresenter(RouteDetailContact.IRouteDetailView view) {
        this.view = view;
    }


}
