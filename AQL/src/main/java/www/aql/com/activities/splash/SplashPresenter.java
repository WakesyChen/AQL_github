package www.aql.com.activities.splash;

import android.util.Log;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.ConnectionResponse;
import www.aql.com.enums.Configs;
import www.aql.com.urls.BaseUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.NetWorkUtils;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/17.
 */
public class SplashPresenter implements ISplashPresenter {
    private ISplashView view;

    public SplashPresenter(ISplashView view) {
        this.view = view;
    }

    @Override
    public void loadToken() {
        RequestParams requestParams = RequestParamsHelper.getRequestParams(BaseUrls.createConnection);
        requestParams.addBodyParameter("appid", Configs.APP_ID);
        requestParams.addBodyParameter("secret", Configs.SECRET);
        NetWorkUtils.getInstance().get(requestParams, new
                NetRequestCallBack<ConnectionResponse>() {
                    @Override
                    protected void onSuccess(ConnectionResponse connectionResponse) {
                        Log.i("jason", "获取的token：" + connectionResponse + "");
                        if (connectionResponse != null) {
                            RequestParamsHelper.response = connectionResponse;
                        }
                        view.finishedLoadToken();
                    }

                    @Override
                    protected void onFailed(String errorMsg) {
                        Log.i("jason", errorMsg);
                        view.loadFail();
                    }
                }, ConnectionResponse.class);
    }
}
