package www.aql.com.activities.regist;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import www.aql.com.entity.response.RegistResponse;
import www.aql.com.entity.response.VerificationResponse;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.XUtilsHelper;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/29.
 */
public class RegistPresenter implements RegistContact.IRegistPresenter {
    private RegistContact.IRegistView view;

    public RegistPresenter(RegistContact.IRegistView view) {
        this.view = view;
    }

    @Override
    public void regist(String userphone, String password, String nickname) {
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.regist);
        params.addBodyParameter(Keys.USER_PHONE, userphone);
        params.addBodyParameter(Keys.PASSWORD, password);
        params.addBodyParameter(Keys.NICKNAME, nickname);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        int errcode = jsonObject.optInt("errcode");
                        String data = jsonObject.optString("data");
                        String errmsg = jsonObject.optString("errmsg");
                        RegistResponse response = null;
                        if (data.equals("") || errcode != 0) {
                            response = new RegistResponse();
                            response.errmsg = errmsg;
                            response.errcode = errcode;
                            response.data = null;
                            view.finishRegist(response);
                        } else {
                            try {
                                response = new Gson().fromJson(result, RegistResponse.class);
                                view.finishRegist(response);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                Log.i("result", "gson解析异常:" + e.getMessage());
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        //        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<RegistResponse>() {
        //            @Override
        //            protected void onSuccess(RegistResponse response) {
        //                if (response == null) {
        //                    view.netException();
        //                    return;
        //                }
        //                view.finishRegist(response);
        //            }
        //
        //            @Override
        //            protected void onFailed(String errorMsg) {
        //                view.loadFail(errorMsg);
        //            }
        //        }, RegistResponse.class);
    }

    @Override
    public void getVerification(String phone, int type, String activity) {
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.getSMSCode);
        params.addBodyParameter(Keys.PHONE, phone);
        params.addBodyParameter(Keys.TYPE, type + "");
        params.addBodyParameter(Keys.ACTIVITY, activity);
        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<VerificationResponse>() {
            @Override
            protected void onSuccess(VerificationResponse response) {
                if (response == null) {
                    view.netException();
                    return;
                }
                view.finishGetVerification(response);
            }

            @Override
            protected void onFailed(String errorMsg) {
                view.loadFail(errorMsg);
            }
        }, VerificationResponse.class);

    }
}
