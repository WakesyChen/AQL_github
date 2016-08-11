package www.aql.com.activities.login;

import www.aql.com.entity.response.LoginResponse;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/29.
 */
public class LoginContact {
    interface ILoginView extends IBaseView {

        void finishLogin(LoginResponse response);
    }

    interface ILoginPresenter extends IBasePresenter {
        void login(String userphone, String password);

    }
}
