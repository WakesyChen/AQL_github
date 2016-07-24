package www.aql.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import www.aql.com.entity.response.ColumnInfo;

/**
 * Created by Jason on 2016/7/19.
 */
public class GVHomeSortAdapter extends MyBaseAdapter {
    private int[] imgs;

    public GVHomeSortAdapter(Context context, List<ColumnInfo> list) {
        super(context);
        this.list = list;
    }

    public GVHomeSortAdapter(Context context, List<ColumnInfo> list, int[] imgs) {
        super(context);
        this.list = list;
        this.imgs = imgs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(imgs[position]);

        return imageView;
    }
}
