package utils.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

public class Utils {

	public static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd");

	// 设定时间的模板
	public static final SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static boolean isConnect(Context context) {

		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {

				NetworkInfo info = connectivity.getActiveNetworkInfo();

				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		MyToast.showToast(context, "当前网络不可用，请检查网络.");
		return false;
	}

	/***
	 * 返回格式化好的当前时间
	 * 
	 * @return Date 返回一个格式化好的当前系统时间
	 * **/
	public static Date formatCurrentDate() {
		try {
			return format.parse(format.format(new Date(System
					.currentTimeMillis())));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/****
	 * 对一个字符串格式化 并返回一个date对象
	 * 
	 * @param date
	 *            需要格式化的时间
	 * @return Date 返回的格式化的时间
	 * **/
	public static Date formatDate(String date) {
		try {
			return format.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 判断两个时间差
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return 两个时间的时间差
	 * **/
	public static long twoDateDistance(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return -1;
		}
		long timeLong = endDate.getTime() - startDate.getTime();

		return timeLong;
	}

	/***
	 * 判断两个时间差
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return 两个时间的时间差 并进行格式化 1小时30分
	 * **/
	public static String twoDateDistanceFormat(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return "";
		}

		long fen = Utils.twoDateDistance(startDate, endDate) / 1000;
		if (fen < 0) // 如果是跨天的两个时间 需要特殊处理一下
			fen += 24 * 60;
		return fen / 60 + "小时" + fen % 60 + "分";
	}

	public static long dateDistanceCurrent(String start) {
		Date startDate = formatDate(start);
		Date endDate = formatCurrentDate();

		return twoDateDistance(startDate, endDate);
	}

	public static Bitmap getIconFile(String fileName) {
		File iconFile = new File(Environment.getExternalStorageDirectory()
				+ "/lanren/icon");
		if (!iconFile.exists()) {
			return null;
		}
		FileInputStream fis = null;
		Bitmap bitmap = null;
		try {
			fis = new FileInputStream(iconFile);
			bitmap = BitmapFactory.decodeStream(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static File createIconFile(String fileName, Bitmap bitmap) {
		File file = null;
		file = new File(Environment.getExternalStorageDirectory()
				+ "/lanren/icon");
		// 如果文件夹不存在则创建
		if (file != null && !file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		File iconFile = new File(file, fileName);
		if (iconFile.exists() && iconFile.isFile()) {
			iconFile.delete();
		}
		try {
			iconFile.createNewFile();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.PNG, 0, bos);
			byte[] bitmapdata = bos.toByteArray();
			FileOutputStream fos = new FileOutputStream(iconFile);
			fos.write(bitmapdata);
			fos.flush();
			fos.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return iconFile;
	}
	public static Bitmap getScaleBitmap(Bitmap bitmap,int bitmapWidth,int iconWidth){
		Matrix matrix = new Matrix();
		float mscale =  ((float)iconWidth)/bitmapWidth;
		matrix.postScale(mscale,mscale); //长和宽放大缩小的比例
		Bitmap resizeBmp = null;
		resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return resizeBmp;
	}

	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	/**
	 * 验证输入密码条件(只能输入由数字和26个英文字母组成的字符串 6~20位)
	 * 
	 * @param str 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean IsPassword(String str) {
		String regex = "^[A-Za-z0-9]{6,20}+$";
		return match(regex, str);
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
