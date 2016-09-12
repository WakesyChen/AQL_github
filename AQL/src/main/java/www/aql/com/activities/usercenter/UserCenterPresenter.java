package www.aql.com.activities.usercenter;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.UserInfoResponse;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.XUtilsHelper;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/17.
 */
public class UserCenterPresenter implements UserCenterContact.IUserCenterPresenter {
    private UserCenterContact.IUserCenterView view;

    public UserCenterPresenter(UserCenterContact.IUserCenterView view) {
        this.view = view;
    }

    @Override
    public void loadUserInfo(String userid) {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.createConnection);
        requestParams.addBodyParameter("userid", userid);
        XUtilsHelper.getInstance().get(requestParams, new
                NetRequestCallBack<UserInfoResponse>() {
                    @Override
                    protected void onSuccess(UserInfoResponse response) {
                        if (response.errcode == BaseResponse.SUCCESS_OK) {
                            view.successLoadUserInfo(response.data);
                        } else {
                            view.loadFail(response.errmsg);
                        }
                    }

                    @Override
                    protected void onFailed(String errorMsg) {
                        if (errorMsg != null)
                            view.loadFail(errorMsg);
                        else
                            view.netException();
                    }
                }, UserInfoResponse.class);
    }
}
