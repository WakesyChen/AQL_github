package www.aql.com.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class XUtilsHelper {


    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2Ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2Ip(i);
        } catch (Exception ex) {
            return " 获取IP出错了,请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
    }

    private static XUtilsHelper instance;
    private static Gson gson = new Gson();

    /**
     *
     */
    public static XUtilsHelper getInstance() {
        if (null == instance) {
            instance = new XUtilsHelper();
        }
        return instance;
    }


    //异步Get调用的方法
    public <T> void get(final RequestParams params, final NetRequestCallBack<T> callBack, final Class<T> clazz) {
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("result", result);
                if (callBack != null) {
                    if (!TextUtils.isEmpty(result)) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(result);
                            if (jsonObject.optInt("errcode") != 0) {
                                callBack.onSuccess(null);
                            } else {
                                try {
                                    T t = new Gson().fromJson(result, clazz);
                                    callBack.onSuccess(t);
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    Log.i("result", "gson解析异常:" + e.getMessage());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        callBack.onSuccess(null);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                failure(callBack, ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });
    }

    //异步Post调用的方法
    public <T> void post(final String url, Object req, final NetRequestCallBack<T> callBack, final Class<T> clazz) {
        RequestParams requestParams = getRequestParams(req, url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("result", "onSuccess" + result);
                if (callBack != null) {
                    if (!TextUtils.isEmpty(result)) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.optInt("errcode") != 0) {
                                callBack.onSuccess(null);
                            } else {
                                T t = null;
                                try {
                                    t = new Gson().fromJson(result, clazz);
                                    callBack.onSuccess(t);
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    Log.i("result", "gson解析错误：" + e.getMessage());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        callBack.onSuccess(null);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("result", "onError:" + ex.getMessage());
                failure(callBack, ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    //同步Get调用的方法
    public <T> T syncGet(RequestParams params, Class<T> clazz) {
        try {
            String result = null;
            try {
                result = x.http().getSync(params, String.class);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            return new Gson().fromJson(result, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //同步Post调用的方法
    public <T> T syncPost(String method, Object req, Class<T> clazz) {
        RequestParams requestParams = getRequestParams(req, method);
        try {
            String result = null;
            try {
                result = x.http().postSync(requestParams, String.class);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            return new Gson().fromJson(result, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //对参数进行封装格式，为了方便以后的维护，在这里可以统一处理头部信息以及一些上传下载的配置
    private RequestParams getRequestParams(Object req, String url) {
        RequestParams requestParams = RequestParamsHelper.getBaseRequestParam(url);
        requestParams.setConnectTimeout(15000);
        if (req instanceof String) {
            requestParams.setBodyContent(String.valueOf(req));
        } else if (req instanceof File)
            requestParams.addBodyParameter("file", (File) req);
        else {
            requestParams.setBodyContent(gson.toJson(req));
        }
        return requestParams;
    }

    /**
     * 失败处理
     */
    public void failure(NetRequestCallBack callBack, Throwable ex) {
        String s = ex.getMessage();
        if (ex instanceof HttpException) { // 网络错误
            HttpException exception = (HttpException) ex;
            if (exception.getCode() == 900 || exception.getCode() == 901) {

            } else if (exception.getCode() >= 500 && exception.getCode() < 600) {

            } else {

            }
        } else {

        }
        if (callBack != null) {
            callBack.onFailed(s);
        }
    }

}
