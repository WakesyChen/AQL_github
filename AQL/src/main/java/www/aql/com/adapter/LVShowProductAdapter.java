package www.aql.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.base.MyBaseAdapter;
import www.aql.com.entity.response.ShowProduct;
import www.aql.com.urls.MyUrls;

/**
 * Created by Jason on 2016/7/20.
 */
public class LVShowProductAdapter extends MyBaseAdapter<ShowProduct> {

    public LVShowProductAdapter(Context context, List<ShowProduct> list) {
        super(context);
        this.list = list;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lv_commit_order, viewGroup, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        ShowProduct product = list.get(i);
        vh.tvProName.setText(product.proName);
        vh.tvPrice.setText(String.format(context.getString(R.string.price), product.price));
        vh.tvCount.setText("x" + product.proNum);

        loader.displayImage(MyUrls.service_Url + product.imgUrl, vh.img, options);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_proName)
        TextView tvProName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
