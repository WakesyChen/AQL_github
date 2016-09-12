package www.aql.com.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.rideactivitydetail.routedetail.RideActivityDetailActivity;
import www.aql.com.adapter.LVRouteAdapter;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.Route;
import www.aql.com.entity.response.RouteResponse;
import www.aql.com.entity.response.request.ReqRoutes;
import www.aql.com.enums.ColumnID;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.XUtilsHelper;
import www.aql.com.utils.RequestParamsHelper;
import www.aql.com.utils.ToastUtils;

/**
 * Created by Jason on 2016/7/25.
 */
public class RideActivityFragment extends Fragment {

    @BindView(R.id.lv)
    ListView lv;
    private List<Route> totallist_lv;
    private LVRouteAdapter adapter;

    String routeid;
    int page = 1;
    int rows = 7;
    private int currentSelection;

    public static RideActivityFragment getInstance(String routeid) {
        RideActivityFragment fragment = new RideActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Keys.ROUTE_ID, routeid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        routeid = getArguments().getString(Keys.ROUTE_ID);
        ReqRoutes reqRoutes = new ReqRoutes();
        reqRoutes.cityname = "";
        reqRoutes.cityid = "";
        reqRoutes.routename = "";
        reqRoutes.page = page;
        reqRoutes.rows = rows;
        reqRoutes.columnid = ColumnID.RIDE_ACTIVITY;
        firstLoadRoute(reqRoutes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_activity, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initListView() {
        adapter = new LVRouteAdapter(getActivity(), totallist_lv);
        lv.setAdapter(adapter);
        MyUtils.setListViewHeight(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到路线详情
                int headerViewsCount = lv.getHeaderViewsCount();
                Route route = totallist_lv.get(position - headerViewsCount);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Route.class.getName(), route);
                Log.i("jason", "点击的route：" + route);
                ActivitySkipHelper.skipToActivityWithData(getActivity(), RideActivityDetailActivity.class, bundle);
            }
        });
    }

    private void firstLoadRoute(ReqRoutes routes) {
        RequestParams param = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        param.addBodyParameter("columnid", routes.columnid + "");
        param.addBodyParameter("routename", routes.routename + "");
        param.addBodyParameter("cityname", routes.cityname + "");
        param.addBodyParameter("cityid", routes.cityid + "");
        param.addBodyParameter("page", routes.page + "");
        param.addBodyParameter("rows", routes.rows + "");
        XUtilsHelper.getInstance().get(param, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                if (response == null && response.errcode == BaseResponse.SUCCESS_OK) {
                    totallist_lv = new ArrayList();
                    totallist_lv.addAll(response.data);
                    initListView();
                } else {
                    ToastUtils.showToast("请求失败");
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    MyUtils.showToast(getActivity(), errorMsg);
                }
            }
        }, RouteResponse.class);
    }

    private void LoadMoreRoute(ReqRoutes routes) {
        RequestParams param = RequestParamsHelper.getBaseRequestParam(MyUrls.getRouteByColumns);
        param.addBodyParameter("columnid", routes.columnid + "");
        param.addBodyParameter("routename", routes.routename + "");
        param.addBodyParameter("cityname", routes.cityname + "");
        param.addBodyParameter("cityid", routes.cityid + "");
        param.addBodyParameter("page", routes.page + "");
        param.addBodyParameter("rows", routes.rows + "");
        XUtilsHelper.getInstance().get(param, new NetRequestCallBack<RouteResponse>() {
            @Override
            protected void onSuccess(RouteResponse response) {
                if (response == null && response.errcode == BaseResponse.SUCCESS_OK) {
                    if (totallist_lv != null) {
                        totallist_lv.addAll(response.data);
                        adapter.notifyDataSetChanged();
                        lv.setSelection(currentSelection);
                        MyUtils.setListViewHeight(lv);
                    }
                } else {
                    ToastUtils.showToast("请求失败");
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    MyUtils.showToast(getActivity(), errorMsg);
                }
            }
        }, RouteResponse.class);
    }

    @OnClick(R.id.tv_lookMore)
    public void onClick() {
        //加载更多
        currentSelection = rows - 1;
        rows = rows * 2;
        ReqRoutes reqRoutes = new ReqRoutes();
        reqRoutes.cityname = "";
        reqRoutes.cityid = "";
        reqRoutes.routename = "";
        reqRoutes.page = page;
        reqRoutes.rows = rows;
        reqRoutes.columnid = ColumnID.RIDE_ACTIVITY;
        LoadMoreRoute(reqRoutes);
    }
}
