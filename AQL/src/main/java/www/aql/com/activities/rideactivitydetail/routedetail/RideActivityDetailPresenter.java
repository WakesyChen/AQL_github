package www.aql.com.activities.rideactivitydetail.routedetail;

/**
 * Created by Jason on 2016/7/24.
 */
public class RideActivityDetailPresenter implements RideActivityDetailContact.IRideActivityDetailPresenter {
    private RideActivityDetailContact.IRideActivityDetaillView view;

    public RideActivityDetailPresenter(RideActivityDetailContact.IRideActivityDetaillView view) {
        this.view = view;
    }


}
