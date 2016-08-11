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
import www.aql.com.entity.response.Route;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.MobileDisplayHelper;

/**
 * Created by Jason on 2016/7/20.
 */
public class LVRouteAdapter extends MyBaseAdapter<Route> {

    public LVRouteAdapter(Context context, List<Route> list) {
        super(context);
        this.list = list;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lv_route, viewGroup, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Route route = list.get(i);
        vh.tvRouteName.setText(route.routename);
        vh.tvPrice.setText(String.format(context.getString(R.string.price), route.currentprice));
        vh.tvCollectionCount.setText(route.liknum + "");
        vh.tvEvaluationCount.setText(route.joinnum + "");

        MobileDisplayHelper.setRouteListHeight(context, vh.img);

        loader.displayImage(MyUrls.service_Url + route.routeimage, vh.img, options);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_routeName)
        TextView tvRouteName;
        @BindView(R.id.tv_evaluationCount)
        TextView tvEvaluationCount;
        @BindView(R.id.tv_collectionCount)
        TextView tvCollectionCount;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
