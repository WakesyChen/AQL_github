package www.aql.com.utils;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import www.aql.com.R;

public class ImageLoaderHelper {

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(480, 800)
                // 设置缓存的详细信息，最好不要设置这个
                //                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.PNG, 100, null)
                // 线程池内加载的数量
                .threadPoolSize(5)
                // 你可以通过自己的内存缓存实现
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(4 * 1024 * 1024)).memoryCacheSize(4 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                // 将保存的时候的URI名称用MD5 加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // 缓存的文件数量
                .tasksProcessingOrder(QueueProcessingType.LIFO).diskCacheFileCount(100)
                // 自定义缓存路径
                .diskCache(new UnlimitedDiskCache(context.getExternalCacheDir()))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)).writeDebugLogs().build();// 开始构建
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions getDisplayImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.img_loading)
                .showImageForEmptyUri(R.drawable.img_loading).showImageOnFail(R.drawable.img_loading)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
        return options;
    }

    public static DisplayImageOptions getDisplayImageOptionsWithRounded() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.img_loading)
                .showImageForEmptyUri(R.drawable.img_loading).showImageOnFail(R.drawable.img_loading)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(90)).build();
        return options;
    }
}
