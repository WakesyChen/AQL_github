package www.aql.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import www.aql.com.utils.ImageLoaderHelper;

/**
 * Created by Jason on 2016/7/19.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public ImageLoader loader;
    public DisplayImageOptions options;
    public List<T> list;
    public LayoutInflater inflater;
    public Context context;

    public MyBaseAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        loader = ImageLoader.getInstance();
        options = ImageLoaderHelper.getDisplayImageOptions();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
