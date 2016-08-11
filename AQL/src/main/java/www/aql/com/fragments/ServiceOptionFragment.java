package www.aql.com.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.serviceoptionmore.ServiceOptionMoreActivity;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.ActivitySkipHelper;
import www.aql.com.utils.WebViewUtils;

/**
 * Created by Jason on 2016/7/25.
 */
public class ServiceOptionFragment extends Fragment {

    @BindView(R.id.webView)
    WebView webView;
    private String url;

    public static ServiceOptionFragment getInstance(String url) {
        ServiceOptionFragment fragment = new ServiceOptionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Keys.WEBVIEW_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getArguments().getString(Keys.WEBVIEW_URL);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_webview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebViewUtils.setWebView(getActivity(), webView);
        String webViewUrl = MyUrls.service_Url + this.url;
        webView.loadUrl(webViewUrl);
    }

    @OnClick(R.id.tv_lookMore)
    public void onClick() {
        //查看跟多
        Bundle bundle = new Bundle();
        bundle.putString(Keys.WEBVIEW_URL, url);
        ActivitySkipHelper.skipToActivityWithData(getActivity(), ServiceOptionMoreActivity.class, bundle);
    }

}
