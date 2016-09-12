package www.aql.com.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.customerevaluationmore.CustomerEvaluationMoreActivity;
import www.aql.com.enums.Keys;
import www.aql.com.utils.ActivitySkipHelper;

/**
 * Created by Jason on 2016/7/25.
 */
public class AroundCompetitionFragment extends Fragment {

    @BindView(R.id.lv)
    ListView lv;

    String routeid;
    int page = 1;
    int rows = 7;

    public static AroundCompetitionFragment getInstance(String routeid) {
        AroundCompetitionFragment fragment = new AroundCompetitionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Keys.ROUTE_ID, routeid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        routeid = getArguments().getString(Keys.ROUTE_ID);

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

    @OnClick(R.id.tv_lookMore)
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Keys.ROUTE_ID, routeid);
        ActivitySkipHelper.skipToActivityWithData(getActivity(), CustomerEvaluationMoreActivity.class, bundle);
    }
}
