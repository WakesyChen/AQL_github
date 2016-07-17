package www.aql.com.activities.main;

import android.util.Log;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.ListBanner;
import www.aql.com.entity.response.ListColumnInfo;
import www.aql.com.enums.Configs;
import www.aql.com.urls.BaseUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.NetWorkUtils;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/17.
 */
public class MainPresenter implements IMainPresenter {

    private IMainView view;

    public MainPresenter(IMainView view) {
        this.view = view;
    }

    /**
     * 加载首页banner图
     */
    @Override
    public void loadBanners() {
        final RequestParams requestParams = RequestParamsHelper.getRequestParams(BaseUrls.getBannersByColumns);
        requestParams.addBodyParameter(Configs.COLUMN_ID, Configs.HOME_COLUMN_ID + "");
        NetWorkUtils.getInstance().get(requestParams, new
                NetRequestCallBack<ListBanner>() {
                    @Override
                    protected void onSuccess(ListBanner response) {
                        Log.i("jason", "获取首页banner图：" + response);
                        if (response.errcode == 0) {
                            view.finishedLoadBanner(response.data);
                        } else {
                            if (response.errmsg != null) {
                                view.loadFail();
                            } else {
                                view.netException();
                            }
                        }
                    }

                    @Override
                    protected void onFailed(String errorMsg) {
                        if (errorMsg != null) {
                            view.loadFail();
                        } else {
                            view.netException();
                        }
                    }
                }, ListBanner.class);
    }

    /**
     * 加载首页数据
     */
    @Override
    public void loadNetData() {
        RequestParams requestParams = RequestParamsHelper.getRequestParams(BaseUrls.getColumnsInfo);
        requestParams.addBodyParameter(Configs.PCOLUMN_ID, Configs.HOME_COLUMN_ID + "");
        NetWorkUtils.getInstance().get(requestParams, new
                NetRequestCallBack<ListColumnInfo>() {
                    @Override
                    protected void onSuccess(ListColumnInfo response) {
                        Log.i("jason", "获取首页信息：" + response);
                        if (response.errcode == 0) {
                            view.finishedLoadColumnInfo(response.data);
                        } else {
                            if (response.errmsg != null) {
                                view.loadFail();
                            } else {
                                view.netException();
                            }
                        }
                    }

                    @Override
                    protected void onFailed(String errorMsg) {
                        if (errorMsg != null) {
                            view.loadFail();
                        } else {
                            view.netException();
                        }
                    }
                }, ListColumnInfo.class);
    }
}
