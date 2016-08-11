package www.aql.com.activities.buynow;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.activities.PayResultActivity;
import www.aql.com.adapter.LVShowProductAdapter;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.AddOrderResponse;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.Issue;
import www.aql.com.entity.response.Route;
import www.aql.com.entity.response.Shoppingcart;
import www.aql.com.entity.response.ShowProduct;
import www.aql.com.entity.response.UpdateOrderResponse;
import www.aql.com.entity.response.request.ReqAddOrder;
import www.aql.com.entity.response.request.ReqUpdateOrder;
import www.aql.com.entity.response.request.RouteData;
import www.aql.com.enums.Keys;
import www.aql.com.enums.OrderState;
import www.aql.com.enums.PayType;
import www.aql.com.enums.Values;
import www.aql.com.fastpayconfig.AlipayConfig;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.MathExtend;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.NetWorkUtils;
import www.aql.com.utils.RequestParamsHelper;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;
import www.aql.com.thirdplatform.alipay.PayResult;
import www.aql.com.thirdplatform.alipay.SignUtils;

public class BuyNowActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_payNow)
    TextView tvPayNow;
    private CheckBox cb_alipay;
    private CheckBox cb_wechat;
    private TextView tv_totalCountAndPrice;
    private String totalCount;
    private String totalPrice;
    private ArrayList<Shoppingcart> list_sc;
    private List<ShowProduct> totallist;

    private static final int SDK_PAY_FLAG = 1;

    private String orderNumber = "";//订单号 用来传递到支付结果页面的

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    Log.i("jason", "支付返回结果：" + payResult);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0
                     * .xdvAU6&treeId=59&articleId=103665&docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(BuyNowActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finishedPay(orderNumber, 1);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(BuyNowActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                            finishedPay(orderNumber, 3);
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(BuyNowActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            finishedPay(orderNumber, 2);

                        }
                    }
                    break;
                }
            }
        }

        ;
    };
    private Route route;
    private Issue issue;
    private ProgressDialog commitOrderDialog;
    private int orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);
        ButterKnife.bind(this);

        initData();
        initFooter();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        String where = bundle.getString(Keys.FROM_WHERE);
        totallist = new ArrayList<>();
        if (where.equals(Values.FROM_ROUTE_DETAIL)) {
            issue = bundle.getParcelable(Keys.CURRENT_ISSUE);
            route = bundle.getParcelable(Route.class.getName());
            ShowProduct product = new ShowProduct();
            product.proName = route.routename;
            product.imgUrl = route.routeimage;
            product.price = route.prepaidmoney;
            product.proNum = 1;
            product.issue = "";
            totallist.add(product);
            totalCount = 1 + "";
            totalPrice = route.prepaidmoney;
        }
        if (where.equals(Values.FROM_SHOPPINGCART)) {
            list_sc = bundle.getParcelableArrayList(Route.class.getName());
            if (list_sc == null) {
                return;
            }
            totalCount = list_sc.size() + "";
            for (Shoppingcart shoppingcart : list_sc) {
                ShowProduct product = new ShowProduct();
                Route route = shoppingcart.routeinfo;
                product.proName = route.routename;
                product.imgUrl = route.routeimage;
                product.price = route.prepaidmoney;
                product.proNum = 1;
                product.issue = "";
                totallist.add(product);
                String currentPrice = MathExtend.multiply(shoppingcart.paycopynum + "", shoppingcart.routeinfo
                        .prepaidmoney);
                totalPrice = MathExtend.add(totalPrice, currentPrice);
                totalCount += shoppingcart.paycopynum;
            }

        }
    }

    private void initFooter() {
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_lv_pay_now, null);
        tv_totalCountAndPrice = (TextView) footer.findViewById(R.id.tv_totalCountAndPrice);
        tv_totalCountAndPrice.setText("共" + totalCount + "件商品   合计：" + totalPrice + "元");
        lv.addFooterView(footer);
        lv.setAdapter(new LVShowProductAdapter(this, totallist));
    }

    @OnClick(R.id.tv_payNow)
    public void onClick() {
        commitOrderDialog = ProgressDialog.show(this, null, "正在提交订单...", true, false);
        //向本地服务器提交订单
        ReqAddOrder order = new ReqAddOrder();
        order.paymoney = totalPrice;
        order.paytype = PayType.alipay;
        order.state = OrderState.PREPARE_PAY;
        order.userid = SPUtils.getString(this, SPConfig.USER_ID, "10086");
        List<RouteData> list_routeData = new ArrayList<>();
        if (route != null) {
            RouteData routeData = new RouteData();
            if (issue == null) {
                routeData.issuesid = "";
            } else {
                routeData.issuesid = issue.issuesid;
            }
            routeData.routeid = route.routeid;
            routeData.paycopynum = 1;
            list_routeData.add(routeData);
        }
        if (list_sc != null) {

        }
        order.data = new Gson().toJson(list_routeData);
        Log.i("jason", "请求添加订单请求体：" + new Gson().toJson(order));
        NetWorkUtils.getInstance().post(MyUrls.addOrder + "?token=" + RequestParamsHelper.response.token, order, new
                NetRequestCallBack<AddOrderResponse>() {
                    @Override
                    protected void onSuccess(AddOrderResponse response) {
                        commitOrderDialog.dismiss();
                        if (response.errcode == BaseResponse.SUCCESS_OK) {
                            tvPayNow.setEnabled(false);
                            tvPayNow.setClickable(false);
                            orderid = response.data.orderid;
                            //调起支付宝支付
                            aliPay();
                        } else {
                            MyUtils.showToast(BuyNowActivity.this, response.errmsg);
                        }
                    }

                    @Override
                    protected void onFailed(String errorMsg) {
                        commitOrderDialog.dismiss();
                        MyUtils.showToast(BuyNowActivity.this, "提交订单失败" + errorMsg);
                    }
                }, AddOrderResponse.class);
    }


    /**
     * call alipay sdk aliPay. 调用SDK支付
     */
    public void aliPay() {
        if (TextUtils.isEmpty(AlipayConfig.PARTNER) || TextUtils.isEmpty(AlipayConfig.RSA_PRIVATE) || TextUtils
                .isEmpty(AlipayConfig.SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }
        String orderInfo = getOrderInfo("来自爱骑旅的商品", "该测试商品的详细描述", "0.01");

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(BuyNowActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + AlipayConfig.PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + AlipayConfig.SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderid + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    //    /**
    //     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
    //     */
    //    private String getOutTradeNo() {
    //        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
    //        Date date = new Date();
    //        String key = format.format(date);
    //
    //        Random r = new Random();
    //        key = key + r.nextInt();
    //        key = key.substring(0, 15);
    //        return key;
    //    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, AlipayConfig.RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    //处理三种支付状态  state 1成功  2 失败  3待确认
    public void finishedPay(String orderNumber, int state) {
        updateOrderState();
        Intent intent = new Intent(this, PayResultActivity.class);
        intent.putExtra("orderNumber", orderNumber);
        intent.putExtra("state", state);
        startActivity(intent);
        finish();
    }

    /**
     * 更新订单状态
     */
    private void updateOrderState() {
        ReqUpdateOrder updateOrder = new ReqUpdateOrder();
        updateOrder.thirdorderid = orderNumber;
        updateOrder.state = OrderState.FINISHED_PAY;
        updateOrder.orderid = orderid;
        NetWorkUtils.getInstance().post(MyUrls.updateOrder + "?token=" + RequestParamsHelper
                .response.token, updateOrder, new NetRequestCallBack<UpdateOrderResponse>() {
            @Override
            protected void onSuccess(UpdateOrderResponse response) {
                Log.i("jason", "更新订单状态结果：" + response);
                if (response.errcode == BaseResponse.SUCCESS_OK) {

                }
            }

            @Override
            protected void onFailed(String errorMsg) {

            }
        }, UpdateOrderResponse.class);
    }
}
