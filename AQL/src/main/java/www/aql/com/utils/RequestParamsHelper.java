package www.aql.com.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import org.xutils.http.RequestParams;

import www.aql.com.entity.response.ConnectionResponse;

public class RequestParamsHelper {
    public static ConnectionResponse response;

    /**
     * 获取配置好的RequestParams对象
     *
     * @param context
     * @param url
     * @return
     */
    public static RequestParams getCacheRequestParams(Context context, String url) {
        RequestParams params = getBaseRequestParam(url);
        long maxMemory = Runtime.getRuntime().maxMemory();
        int cacheSize = (int) (maxMemory / 8);
        String cacheDir = SDCardHelper.getSDCardPrivateCacheDir(context);
        params.setCacheSize(cacheSize);
        params.setCacheDirName(cacheDir);
        params.setCacheMaxAge(30000);
        if (response != null)
            params.addBodyParameter("token", response.token);
        return params;
    }


    /**
     * 获取配置好的RequestParams对象
     *
     * @param url
     * @return
     */
    @NonNull
    public static RequestParams getBaseRequestParam(String url) {
        RequestParams params = new RequestParams(url);
        params.addHeader("Connection", "keep-alive");
        params.setConnectTimeout(7000);
        if (response != null)
            params.addBodyParameter("token", response.token);
        return params;
    }
}
