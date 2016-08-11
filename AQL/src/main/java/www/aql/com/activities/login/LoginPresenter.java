package www.aql.com.activities.login;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.LoginResponse;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.NetWorkUtils;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/29.
 */
public class LoginPresenter implements LoginContact.ILoginPresenter {
    private LoginContact.ILoginView view;

    public LoginPresenter(LoginContact.ILoginView view) {
        this.view = view;
    }

    @Override
    public void login(String userphone, String password) {
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.loginCheck);
        params.addBodyParameter(Keys.USER_PHONE, userphone);
        params.addBodyParameter(Keys.PASSWORD, password);
        NetWorkUtils.getInstance().get(params, new NetRequestCallBack<LoginResponse>() {
            @Override
            protected void onSuccess(LoginResponse response) {
                if (response == null) {
                    view.netException();
                    return;
                }
                view.finishLogin(response);
            }

            @Override
            protected void onFailed(String errorMsg) {
                view.loadFail(errorMsg);
            }
        }, LoginResponse.class);
    }
}
