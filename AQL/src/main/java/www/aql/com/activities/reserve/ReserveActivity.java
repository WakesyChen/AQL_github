package www.aql.com.activities.reserve;

import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.base.BaseActivity;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.WebViewUtils;

public class ReserveActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        ButterKnife.bind(this);


        String url = getIntent().getExtras().getString(Keys.WEBVIEW_URL);
        WebViewUtils.setWebView(this, webView);
        webView.loadUrl(MyUrls.service_Url + url);
    }
}
