package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

/**
 * NetWorkUtil
 * 
 * @author wei2bei132
 * 
 */
public class NetWorkUtil {
	private static State mWifiState = null;
	private static State mMobileState = null;

	public enum NetWorkState {
		WIFI, MOBILE, NONE;

	}

	public static NetWorkState getConnectState(Context context) {
		mWifiState = null;
		mMobileState = null;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		manager.getActiveNetworkInfo();
		mWifiState = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		mMobileState = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		if (mWifiState != null && mMobileState != null
				&& State.CONNECTED != mWifiState
				&& State.CONNECTED == mMobileState) {
			return NetWorkState.MOBILE;
		} else if (mWifiState != null && mMobileState != null
				&& State.CONNECTED != mWifiState
				&& State.CONNECTED != mMobileState) {
			return NetWorkState.NONE;
		} else if (mWifiState != null && State.CONNECTED == mWifiState) {
			return NetWorkState.WIFI;
		}
		return NetWorkState.NONE;
	}
}
