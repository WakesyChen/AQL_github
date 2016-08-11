package www.aql.com.activities.splash;

import android.util.Log;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.ConnectionResponse;
import www.aql.com.enums.Values;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.NetWorkUtils;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/17.
 */
public class SplashPresenter implements SplashContact.ISplashPresenter {
    private SplashContact.ISplashView view;

    public SplashPresenter(SplashContact.ISplashView view) {
        this.view = view;
    }

    @Override
    public void loadToken() {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.createConnection);
        requestParams.addBodyParameter("appid", Values.APP_ID);
        requestParams.addBodyParameter("secret", Values.SECRET);
        NetWorkUtils.getInstance().get(requestParams, new
                NetRequestCallBack<ConnectionResponse>() {
                    @Override
                    protected void onSuccess(ConnectionResponse response) {
                        Log.i("jason", "获取的token：" + response + "");
                        if (response == null) {
                            view.netException();
                            return;
                        }
                        RequestParamsHelper.response = response;
                        view.successLoadToken();
                    }

                    @Override
                    protected void onFailed(String errorMsg) {
                        if (errorMsg != null)
                            view.loadFail(errorMsg);
                        else
                            view.netException();
                    }
                }, ConnectionResponse.class);
    }
}
