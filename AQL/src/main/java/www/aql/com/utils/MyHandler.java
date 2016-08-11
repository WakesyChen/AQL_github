package www.aql.com.utils;

import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * T为当前Activity
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class MyHandler<T> extends Handler {
	WeakReference<T> weakReference;

	public MyHandler(T activity) {
		weakReference = new WeakReference<T>(activity);
	}

	public WeakReference<T> getWeakReference() {
		return weakReference;
	}

	public void setWeakReference(WeakReference<T> weakReference) {
		this.weakReference = weakReference;
	}
}
