package www.aql.com.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import www.aql.com.base.MyBaseAdapter;
import www.aql.com.entity.response.ColumnInfo;

/**
 * Created by Jason on 2016/7/19.
 */
public class GVMoreAdapter extends MyBaseAdapter {
    private int[] imgs;

    public GVMoreAdapter(Context context, List<ColumnInfo> list) {
        super(context);
        this.list = list;
    }

    public GVMoreAdapter(Context context, List<ColumnInfo> list, int[] imgs) {
        super(context);
        this.list = list;
        this.imgs = imgs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout
                        .LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(imgs[position]);

        return imageView;
    }
}
