package www.aql.com.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import www.aql.com.entity.response.Banner;
import www.aql.com.utils.ImageLoaderHelper;

/**
 * Created by Jason on 2016/7/17.
 */
public class VPHomeAdapter extends PagerAdapter {
    private List<Banner> list;
    private Context context;
    private DisplayImageOptions options;
    private ImageLoader loader;

    public VPHomeAdapter(Context context, List<Banner> list) {
        this.list = list;
        this.context = context;
        options = ImageLoaderHelper.getDisplayImageOptions();
        loader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        loader.displayImage(list.get(position).image, imageView, options);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
