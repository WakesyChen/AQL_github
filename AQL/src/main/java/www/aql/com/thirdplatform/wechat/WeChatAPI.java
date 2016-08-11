package www.aql.com.thirdplatform.wechat;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup.LayoutParams;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import www.aql.com.utils.BitmapBytesDrawableHelper;

public class WeChatAPI {
    /**
     * 微信接口secret(微信开放平台申请获得)
     */
    public static final String APP_SECRET = "99face25bdfc4375a8e7b9c5967691f6";
    /**
     * 微信接口ID(微信开放平台申请获得)
     */
    public static final String APP_ID = "wx0ad2fbbeabd50a8a";

    /**
     * 商户ID
     */
    public static final String MCH_ID = "1306096401";

    /**
     * key
     */
    public static final String KEY = "1DWSD1S11sfda61F65WS1FaF165ewdf1";

    /**
     * 通过code获取access_token的接口。(get请求)
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=
     * SECRET&code=CODE&grant_type=authorization_code
     */
    public static final String GET_ACCESS_TOKEN_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 刷新或续期access_token使用
     * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type
     * =refresh_token&refresh_token=REFRESH_TOKEN
     */
    public static final String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    /**
     * 检验授权凭证（access_token）是否有效
     * https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=
     * OPENID
     */
    public static final String IS_ACCESS_TOKEN_VALID = "https://api.weixin.qq.com/sns/auth";

    /**
     * 获取用户个人信息（UnionID机制）
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=
     * OPENID
     */
    public static final String GET_USER_INFROMATION = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 统一下单接口
     */
    public static final String UNIFORM_CREATE_ORDER = "https://api.mch.weixin.qq.com/aliPay/unifiedorder";

    /**
     * 分享到聊天框
     */
    public static final int SESSION = SendMessageToWX.Req.WXSceneSession;
    /**
     * 添加到收藏
     */
    public static final int FAVORITE = SendMessageToWX.Req.WXSceneFavorite;
    /**
     * 分享到朋友圈
     */
    public static final int TIMELINE = SendMessageToWX.Req.WXSceneTimeline;

    /**
     * 分享文字
     *
     * @param context
     * @param text    文本内容
     * @param where   分享到哪儿
     */
    public static void shareText(Context context, String text, int where) {
        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.description = text;
        msg.mediaObject = textObj;

        // 构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());// 唯一标识一个请求
        req.message = msg;
        switch (where) {
            case SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;// 分享到聊天框
                break;
            case FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;// 添加到收藏
                break;
            case TIMELINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;// 分享到朋友圈
                break;
        }

        // 调用API发送数据到微信
        WXAPIFactory.createWXAPI(context, WeChatAPI.APP_ID, true).sendReq(req);
    }

    /**
     * 分享图片
     *
     * @param context
     * @param bitmap
     * @param where   分享到哪儿
     */
    public static void shareImage(Context context, Bitmap bitmap, int where) {

        // 初始化一个WXImageObject对象和一个WXMediaMessage对象
        WXImageObject imgObj = new WXImageObject();
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        // 设置缩略图
        Bitmap.createScaledBitmap(bitmap, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        bitmap.recycle();
        msg.thumbData = BitmapBytesDrawableHelper.Bitmap2Bytes(bitmap);

        // 构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());// 唯一标识一个请求
        req.message = msg;
        switch (where) {
            case SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;// 分享到聊天框
                break;
            case FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;// 添加到收藏
                break;
            case TIMELINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;// 分享到朋友圈
                break;
        }

        // 调用API发送数据到微信
        WXAPIFactory.createWXAPI(context, WeChatAPI.APP_ID, true).sendReq(req);
    }

    /**
     * 分享网页
     *
     * @param context
     * @param url         网页地址
     * @param title       网页标题
     * @param description 网页描述
     * @param bitmap
     * @param where       分享到哪儿
     */
    public static void shareWebPage(Context context, String url, String title, String description, Bitmap bitmap, int where) {

        // 初始化一个WXImageObject对象和一个WXMediaMessage对象
        WXWebpageObject webPageObj = new WXWebpageObject();
        webPageObj.webpageUrl = url;

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(webPageObj);
        msg.title = title;
        msg.description = description;
        Bitmap.createScaledBitmap(bitmap, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        bitmap.recycle();
        msg.thumbData = BitmapBytesDrawableHelper.Bitmap2Bytes(bitmap);

        // 构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());// 唯一标识一个请求
        req.message = msg;
        switch (where) {
            case SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;// 分享到聊天框
                break;
            case FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;// 添加到收藏
                break;
            case TIMELINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;// 分享到朋友圈
                break;
        }

        // 调用API发送数据到微信
        WXAPIFactory.createWXAPI(context, WeChatAPI.APP_ID, true).sendReq(req);
    }

    /**
     * 分享音乐(分享至微信的音乐，直接点击好友会话或朋友圈下的分享内容会跳转至第三方
     * APP，点击会话列表顶部的音乐分享内容将跳转至微信原生音乐播放器播放。)
     *
     * @param context
     * @param url         音乐地址
     * @param title       音乐标题
     * @param description 音乐描述
     * @param bitmap
     * @param where       分享到哪儿
     */
    public static void shareMusic(Context context, String url, String title, String description, Bitmap bitmap, int where) {

        // 初始化一个WXImageObject对象和一个WXMediaMessage对象
        WXMusicObject musicObj = new WXMusicObject();
        musicObj.musicUrl = url;

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(musicObj);
        msg.title = title;
        msg.description = description;
        Bitmap.createScaledBitmap(bitmap, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        bitmap.recycle();
        msg.thumbData = BitmapBytesDrawableHelper.Bitmap2Bytes(bitmap);

        // 构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());// 唯一标识一个请求
        req.message = msg;
        switch (where) {
            case SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;// 分享到聊天框
                break;
            case FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;// 添加到收藏
                break;
            case TIMELINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;// 分享到朋友圈
                break;
        }

        // 调用API发送数据到微信
        WXAPIFactory.createWXAPI(context, WeChatAPI.APP_ID, true).sendReq(req);
    }

    /**
     * 分享视频
     *
     * @param context
     * @param url         视频地址
     * @param title       视频标题
     * @param description 视频描述
     * @param bitmap
     * @param where       分享到哪儿
     */
    public static void shareVideo(Context context, String url, String title, String description, Bitmap bitmap, int where) {

        // 初始化一个WXImageObject对象和一个WXMediaMessage对象
        WXVideoObject videoObj = new WXVideoObject();
        videoObj.videoUrl = url;

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(videoObj);
        msg.title = title;
        msg.description = description;
        Bitmap.createScaledBitmap(bitmap, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        bitmap.recycle();
        msg.thumbData = BitmapBytesDrawableHelper.Bitmap2Bytes(bitmap);

        // 构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());// 唯一标识一个请求
        req.message = msg;
        switch (where) {
            case SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;// 分享到聊天框
                break;
            case FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;// 添加到收藏
                break;
            case TIMELINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;// 分享到朋友圈
                break;
        }

        // 调用API发送数据到微信
        WXAPIFactory.createWXAPI(context, WeChatAPI.APP_ID, true).sendReq(req);
    }
}
