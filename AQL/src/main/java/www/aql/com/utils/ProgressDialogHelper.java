package www.aql.com.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogHelper {
	/**
	 * 初始化可取消的ProgressDialog
	 * 
	 * @param context
	 * @return
	 */
	public static ProgressDialog getCancleableDialog(Context context) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setMessage("请稍等...");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		return dialog;
	}

	/**
	 * 初始化不可取消的ProgressDialog
	 * 
	 * @param context
	 * @return
	 */
	public static ProgressDialog getUncancleableDialog(Context context) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setMessage("请稍等...");
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		return dialog;
	}
}
