package www.aql.com.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xutils.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.customerevaluationmore.CustomerEvaluationMoreActivity;
import www.aql.com.adapter.LVEvaluationAdapter;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.EvaluationReponse;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.XUtilsHelper;
import www.aql.com.utils.RequestParamsHelper;

/**
 * Created by Jason on 2016/7/25.
 */
public class CustomerEvaluationFragment extends Fragment {

    @BindView(R.id.lv)
    ListView lv;

    String routeid;
    int page = 1;
    int rows = 7;

    public static CustomerEvaluationFragment getInstance(String routeid) {
        CustomerEvaluationFragment fragment = new CustomerEvaluationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Keys.ROUTE_ID, routeid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        routeid = getArguments().getString(Keys.ROUTE_ID);

        loadEvaluation(routeid, page, rows);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_customer_evaluation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void loadEvaluation(String routeid, int page, int rows) {
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.getCommentsByRouteID);
        params.addBodyParameter(Keys.ROUTE_ID, routeid);
        params.addBodyParameter(Keys.PAGE, page + "");
        params.addBodyParameter(Keys.ROWS, rows + "");
        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<EvaluationReponse>() {
            @Override
            protected void onSuccess(EvaluationReponse response) {
                Log.i("jason", "请求评论数据：" + response);
                if (response == null) {
                    MyUtils.showToast(getActivity(), "服务器异常");
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    lv.setAdapter(new LVEvaluationAdapter(getActivity(), response.data));
                } else {
                    MyUtils.showToast(getActivity(), response.errmsg);
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    MyUtils.showToast(getActivity(), errorMsg);
                } else {
                    MyUtils.showToast(getActivity(), "请求失败");
                }
            }
        }, EvaluationReponse.class);
    }

    @OnClick(R.id.tv_lookMore)
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Keys.ROUTE_ID, routeid);
        ActivitySkipHelper.skipToActivityWithData(getActivity(), CustomerEvaluationMoreActivity.class, bundle);
    }
}
