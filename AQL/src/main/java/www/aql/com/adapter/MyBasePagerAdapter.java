package www.aql.com.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import www.aql.com.utils.ImageLoaderHelper;

/**
 * Created by Jason on 2016/7/19.
 */
public abstract class MyBasePagerAdapter<T> extends PagerAdapter {
    public List<T> list;
    public DisplayImageOptions options;
    public ImageLoader loader;

    public MyBasePagerAdapter() {
        options = ImageLoaderHelper.getDisplayImageOptions();
        loader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
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
