package www.aql.com.activities.splash;

import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/19.
 */
public class SplashContact {
    interface ISplashView extends IBaseView {
        void successLoadToken();
    }

    interface ISplashPresenter extends IBasePresenter {

        void loadToken();
    }
}
