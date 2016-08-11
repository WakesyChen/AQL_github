package www.aql.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import www.aql.com.R;
import www.aql.com.base.BaseActivity;

/**
 * 支付成功的界面 支付成功，失败或者其他情况都需要统一跳转到这个界面
 */
public class PayResultActivity extends BaseActivity implements View.OnClickListener {

    //支付结果
    private TextView tv_pay_result;
    //查看订单详情
    private Button btn_order_detail;
    //回主页
    private Button btn_main_page;
    private String orderNumber;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);

        initData();
        initView();
        setData();
    }


    //初始化数据  得到从支付界面传过来的参数
    private void initData() {
        Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNumber");
        state = intent.getIntExtra("state", -1);
    }

    //需要的参数：
    private void initView() {
        tv_pay_result = (TextView) findViewById(R.id.tv_pay_result);
        btn_order_detail = (Button) findViewById(R.id.btn_order_detail);
        btn_main_page = (Button) findViewById(R.id.btn_main_page);
        btn_order_detail.setOnClickListener(this);
        btn_main_page.setOnClickListener(this);
    }

    private void setData() {
        if (state == 1) {
            tv_pay_result.setText("支付成功");
        } else if (state == 2) {
            tv_pay_result.setText("支付失败");
        } else if (state == 3) {
            tv_pay_result.setText("支付确认中");
        } else {
            tv_pay_result.setText("支付确认中");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_page:
                finish();
                break;
            case R.id.btn_order_detail:
                finish();
                break;
        }
    }
}
