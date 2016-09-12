package www.aql.com.activities.usercenter;

import www.aql.com.entity.response.UserInfo;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/19.
 */
public class UserCenterContact {
    interface IUserCenterView extends IBaseView {
        void successLoadUserInfo(UserInfo userInfo);
    }

    interface IUserCenterPresenter extends IBasePresenter {

        void loadUserInfo(String userid);
    }
}
