package utils.tools;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

	static Toast toast;

	public static void showToast(Context context, int resId) {
		if (toast == null) {
			toast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
		} else {
			toast.setText(resId);
		}
		toast.show();
	}

	public static void showToast(Context context, String str) {
		if (toast == null) {
			toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
		} else {
			toast.setText(str);
		}
		toast.show();
	}
}
