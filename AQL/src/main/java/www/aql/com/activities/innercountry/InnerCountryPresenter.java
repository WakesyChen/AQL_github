package www.aql.com.activities.innercountry;

import android.util.Log;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.BannerResponse;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.RouteResponse;
import www.aql.com.entity.response.request.ReqRoutes;
import www.aql.com.enums.Keys;
import www.aql.com.enums.Values;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.NetWorkUtils;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/19.
 */
public class InnerCountryPresenter implements InnerCountryContact.IInnerCountryPresenter {
    private InnerCountryContact.IInnerCountryView view;

    public InnerCountryPresenter(InnerCountryContact.IInnerCountryView view) {
        this.view = view;
    }

    @Override
    public void firstLoadRoutes(ReqRoutes routes) {
        RequestParams param = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        param.addBodyParameter("columnid", routes.columnid + "");
        param.addBodyParameter("routename", routes.routename + "");
        param.addBodyParameter("cityname", routes.cityname + "");
        param.addBodyParameter("cityid", routes.cityid + "");
        param.addBodyParameter("page", routes.page + "");
        param.addBodyParameter("rows", routes.rows + "");
        NetWorkUtils.getInstance().get(param, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                Log.i("jason", "第一次加载国内数据：" + response);
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successFirstLoadRoutes(response.data);
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
        }, RouteResponse.class);
    }

    @Override
    public void refreshLoadRoutes(ReqRoutes routes) {
        RequestParams param = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        param.addBodyParameter("columnid", routes.columnid + "");
        param.addBodyParameter("routename", routes.routename + "");
        param.addBodyParameter("cityname", routes.cityname + "");
        param.addBodyParameter("cityid", routes.cityid + "");
        param.addBodyParameter("page", routes.page + "");
        param.addBodyParameter("rows", routes.rows + "");
        NetWorkUtils.getInstance().get(param, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successRefreshLoadRoutes(response.data);
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
        }, RouteResponse.class);
    }

    @Override
    public void firstLoadBanners(long columnId) {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getBannersByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, columnId + "");
        NetWorkUtils.getInstance().get(requestParams, new NetRequestCallBack<BannerResponse>() {
            @Override
            protected void onSuccess(BannerResponse response) {
                Log.i("jason", "获取首页banner图：" + response);
                if (response.errcode == 0 && response.errmsg.equals("ok")) {
                    view.successFirstLoadBanners(response.data);
                } else {
                    if (response.errmsg != null) {
                        view.loadFail(response.errmsg);
                    } else {
                        view.netException();
                    }
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
        }, BannerResponse.class);
    }

    @Override
    public void refreshLoadBanners(long columnId) {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getBannersByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, columnId + "");
        NetWorkUtils.getInstance().get(requestParams, new NetRequestCallBack<BannerResponse>() {
            @Override
            protected void onSuccess(BannerResponse response) {
                Log.i("jason", "获取首页banner图：" + response);
                if (response.errcode == 0 && response.errmsg.equals("ok")) {
                    view.successRefreshLoadBanners(response.data);
                } else {
                    if (response.errmsg != null) {
                        view.loadFail(response.errmsg);
                    } else {
                        view.netException();
                    }
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
        }, BannerResponse.class);
    }

    @Override
    public void loadMore(ReqRoutes routes) {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, Values.HOME_COLUMN_ID + "");
        requestParams.addBodyParameter(Keys.ROUTE_NAME, routes.routename);
        requestParams.addBodyParameter(Keys.CITY_NAME, routes.cityname);
        requestParams.addBodyParameter(Keys.CIT_ID, routes.cityid);
        requestParams.addBodyParameter(Keys.PAGE, routes.page + "");
        requestParams.addBodyParameter(Keys.ROWS, routes.rows + "");
        NetWorkUtils.getInstance().get(requestParams, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successLoadMore(response.data);
                } else {
                    if (response.errmsg != null) {
                        view.loadFail(response.errmsg);
                    } else {
                        view.netException();
                    }
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
        }, RouteResponse.class);
    }
}
