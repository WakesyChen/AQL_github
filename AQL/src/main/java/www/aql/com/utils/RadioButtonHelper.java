package www.aql.com.utils;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import www.aql.com.R;

/**
 * Created by Jason on 2016/8/31.
 */
public class RadioButtonHelper {

    //动态绘制RadioButton
    public static RadioButton drawRadioButton(Context context) {
        RadioButton rb = new RadioButton(context);
        rb.setButtonDrawable(android.R.color.transparent);
        int width_height = DisplayHelper.dp2px(context, 5);
        int margin = DisplayHelper.dp2px(context, 10);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width_height, width_height);
        params.setMargins(margin, margin, margin, margin);
        rb.setLayoutParams(params);
        rb.setBackgroundResource(R.drawable.selector_dot);
        return rb;
    }
}
