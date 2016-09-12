package www.aql.com.activities.main;

import android.util.Log;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.BannerResponse;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.ColumnInfoResponse;
import www.aql.com.entity.response.RouteResponse;
import www.aql.com.enums.Keys;
import www.aql.com.enums.Values;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.XUtilsHelper;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/17.
 */
public class MainPresenter implements MainContact.IMainPresenter {

    private MainContact.IMainView view;
    private RequestParams requestParams;

    public MainPresenter(MainContact.IMainView view) {
        this.view = view;
    }

    private long page = 1;
    private long rows = 8;

    /**
     * 加载首页banner图
     */
    @Override
    public void refreshLoadBanners() {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getBannersByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, Values.HOME_COLUMN_ID + "");
        XUtilsHelper.getInstance().get(requestParams, new NetRequestCallBack<BannerResponse>() {
            @Override
            protected void onSuccess(BannerResponse response) {
                Log.i("jason", "获取首页banner图：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == 0 && response.errmsg.equals("ok")) {
                    view.successRefreshLoadBanner(response.data);
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

    /**
     * 加载首页数据
     */
    @Override
    public void refreshLoadColumnInfo() {
        requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getColumnsInfo);
        requestParams.addBodyParameter(Keys.PCOLUMN_ID, Values.HOME_COLUMN_ID + "");
        XUtilsHelper.getInstance().get(requestParams, new NetRequestCallBack<ColumnInfoResponse>() {
            @Override
            protected void onSuccess(ColumnInfoResponse response) {
                Log.i("jason", "获取首页分类模块信息：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successRefreshLoadColumnInfo(response.data);
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
        }, ColumnInfoResponse.class);
    }

    @Override
    public void refreshLoadRoutes() {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, Values.HOME_COLUMN_ID + "");
        requestParams.addBodyParameter(Keys.ROUTE_NAME, "");
        requestParams.addBodyParameter(Keys.CITY_NAME, "");
        requestParams.addBodyParameter(Keys.CIT_ID, "");
        requestParams.addBodyParameter(Keys.PAGE, page + "");
        requestParams.addBodyParameter(Keys.ROWS, rows + "");
        XUtilsHelper.getInstance().get(requestParams, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                Log.i("jason", "获取首页路线列表信息：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successRefreshLoadRoute(response.data);
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

    @Override
    public void firstLoadBanners() {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getBannersByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, Values.HOME_COLUMN_ID + "");
        XUtilsHelper.getInstance().get(requestParams, new NetRequestCallBack<BannerResponse>() {
            @Override
            protected void onSuccess(BannerResponse response) {
                Log.i("jason", "获取首页banner图：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == 0 && response.errmsg.equals("ok")) {
                    view.successFirstLoadBanner(response.data);
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
    public void firstLoadColumnInfo() {
        requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getColumnsInfo);
        requestParams.addBodyParameter(Keys.PCOLUMN_ID, Values.HOME_COLUMN_ID + "");
        XUtilsHelper.getInstance().get(requestParams, new NetRequestCallBack<ColumnInfoResponse>() {
            @Override
            protected void onSuccess(ColumnInfoResponse response) {
                Log.i("jason", "获取首页分类模块信息：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successFirstLoadColumnInfo(response.data);
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
        }, ColumnInfoResponse.class);
    }

    @Override
    public void firstLoadRoutes() {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, Values.HOME_COLUMN_ID + "");
        requestParams.addBodyParameter(Keys.ROUTE_NAME, "");
        requestParams.addBodyParameter(Keys.CITY_NAME, "");
        requestParams.addBodyParameter(Keys.CIT_ID, "");
        requestParams.addBodyParameter(Keys.PAGE, page + "");
        requestParams.addBodyParameter(Keys.ROWS, rows + "");
        XUtilsHelper.getInstance().get(requestParams, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                Log.i("jason", "获取首页路线列表信息：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successFirstLoadRoute(response.data);
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

    @Override
    public void loadMore() {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        requestParams.addBodyParameter(Keys.COLUMN_ID, Values.HOME_COLUMN_ID + "");
        requestParams.addBodyParameter(Keys.ROUTE_NAME, "");
        requestParams.addBodyParameter(Keys.CITY_NAME, "");
        requestParams.addBodyParameter(Keys.CIT_ID, "");
        requestParams.addBodyParameter(Keys.PAGE, (page++) + "");
        requestParams.addBodyParameter(Keys.ROWS, rows + "");
        XUtilsHelper.getInstance().get(requestParams, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                Log.i("jason", "获取首页路线列表信息：" + response);
                if (response == null) {
                    view.netException();
                    return;
                }
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
