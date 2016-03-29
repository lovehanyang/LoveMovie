package utils.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

	private final static String PROP_FILE = "GpsCard";

	public synchronized static boolean putString(Context context, String key,
			String value) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.edit().putString(key, value).commit();
	}

	public synchronized static String getString(Context context, String key,
			String defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}
	
	public synchronized static boolean putLong(Context context, String key, long value) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.edit().putLong(key, value).commit();
	}
	
	public synchronized static long getLong(Context context, String key, long defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.getLong(key, defaultValue);
	}

	public synchronized static boolean putInt(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.edit().putInt(key, value).commit();
	}
	
	public synchronized static int getInt(Context context, String key, int defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}

	public synchronized static boolean putBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.edit().putBoolean(key, value).commit();
	}
	
	public synchronized static boolean getBoolean(Context context, String key, boolean defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(PROP_FILE,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}
}
