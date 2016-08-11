package www.aql.com.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import www.aql.com.base.MyBasePagerAdapter;
import www.aql.com.entity.response.Banner;
import www.aql.com.urls.MyUrls;

/**
 * Created by Jason on 2016/7/17.
 */
public class VPBannerAdapter extends MyBasePagerAdapter {
    private Context context;

    public VPBannerAdapter(Context context, List<Banner> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Banner banner = (Banner) list.get(position);
        String imgUrl = MyUrls.service_Url + banner.image;
        loader.displayImage(imgUrl, imageView, options);
        //        MyUtils.showLog("图片url:" + imgUrl);
        container.addView(imageView);
        return imageView;
    }
}
