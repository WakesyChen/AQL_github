package www.aql.com.activities.customerevaluationmore;

import android.util.Log;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.EvaluationReponse;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.XUtilsHelper;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/17.
 */
public class CustomerEvaluationMorePresenter implements CustomerEvaluationMoreContact.ICustomerEvaluationMorePresenter {

    private CustomerEvaluationMoreContact.ICustomerEvaluationMoreView view;
    private RequestParams requestParams;

    public CustomerEvaluationMorePresenter(CustomerEvaluationMoreContact.ICustomerEvaluationMoreView view) {
        this.view = view;
    }

    @Override
    public void refreshLoadEvaluation(String routeid, int page, int rows) {
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.getCommentsByRouteID);
        params.addBodyParameter(Keys.ROUTE_ID, routeid);
        params.addBodyParameter(Keys.PAGE, page + "");
        params.addBodyParameter(Keys.ROWS, rows + "");
        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<EvaluationReponse>() {
            @Override
            protected void onSuccess(EvaluationReponse response) {
                Log.i("jason", "请求评论数据：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successRefreshLoadEvaluation(response.data);
                } else {
                    view.loadFail(response.errmsg);
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    view.loadFail(errorMsg);
                } else {
                    view.netException();
                }
            }
        }, EvaluationReponse.class);
    }

    @Override
    public void firstLoadEvaluation(String routeid, int page, int rows) {
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.getCommentsByRouteID);
        params.addBodyParameter(Keys.ROUTE_ID, routeid);
        params.addBodyParameter(Keys.PAGE, page + "");
        params.addBodyParameter(Keys.ROWS, rows + "");
        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<EvaluationReponse>() {
            @Override
            protected void onSuccess(EvaluationReponse response) {
                Log.i("jason", "请求评论数据：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successFirstLoadEvaluation(response.data);
                } else {
                    view.loadFail(response.errmsg);
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    view.loadFail(errorMsg);
                } else {
                    view.netException();
                }
            }
        }, EvaluationReponse.class);
    }

    @Override
    public void loadMore(String routeid, int page, int rows) {
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.getCommentsByRouteID);
        params.addBodyParameter(Keys.ROUTE_ID, routeid);
        params.addBodyParameter(Keys.PAGE, page + "");
        params.addBodyParameter(Keys.ROWS, rows + "");
        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<EvaluationReponse>() {
            @Override
            protected void onSuccess(EvaluationReponse response) {
                Log.i("jason", "请求评论数据：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successLoadMore(response.data);
                } else {
                    view.loadFail(response.errmsg);
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    view.loadFail(errorMsg);
                } else {
                    view.netException();
                }
            }
        }, EvaluationReponse.class);

    }
}
