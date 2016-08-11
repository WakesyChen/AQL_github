package www.aql.com.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import www.aql.com.broadcast.NetState;

public class MyUtils {

    public static void showLog(String log) {
        Log.i("jason", log);
    }

    /**
     * 弹出短时间吐司
     *
     * @param context 上下文对象
     * @param text    吐司内容
     */
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * @return 当前时间(yyyy-MM-dd HH:mm:ss)
     */
    public static String getCurrentTime() {
        // 获取系统当前时间
        // Date date = new Date(System.currentTimeMillis());
        Date date = new Date();
        // 格式化时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formater.format(date);
        return dateString;
    }

    /**
     * @return 当前时间(yyyyMMddHHmmss)
     */
    public static String getCurrentTimeForWechatPay() {
        // 获取系统当前时间
        // Date date = new Date(System.currentTimeMillis());
        Date date = new Date();
        // 格式化时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formater.format(date);
        return dateString;
    }

    /**
     * 格式化json数据中的createDate时间字段,将'T'替换为' '
     *
     * @param createDate
     * @return
     */
    public static String subStringCreateDate(String createDate) {
        return createDate.replace('T', ' ');
    }

    /**
     * 正则表达式判断是否为手机号码
     *
     * @param input 要匹配的字符串
     * @return
     */
    public static boolean isPhoneNumber(String input) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * md5加密
     *
     * @param string 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * scrollview与listview合用会出现listview只显示一行多点。此方法是为了定死listview的高度就不会出现以上状况
     * 算出listview的高度
     */
    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(1, 1);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + listView
                .getPaddingTop() +
                listView.getPaddingBottom();
        listView.setLayoutParams(params);
    }

    /**
     * 判断字符串是否是Integer类型
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str != null && !"".equals(str.trim())) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            Long number = 0l;
            if (isNum.matches()) {
                number = Long.parseLong(str);
            } else {
                return false;
            }
            if (number > 2147483647) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 随机生成32位字符串
     *
     * @return string
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * 实时检测网络状态
     *
     * @param context
     */
    public static NetState monitorNetState(Context context) {
        NetState receiver = new NetState();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, filter);
        return receiver;
    }

    /**
     * 顶部刷新的样式
     *
     * @param swipe
     */
    public static void setSwipeStyle(SwipeRefreshLayout swipe) {
        swipe.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light, android.R
                .color.holo_blue_bright, android.R.color.holo_orange_light);
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

}
