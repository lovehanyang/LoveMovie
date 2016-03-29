package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Base64;

/**
 * ImageUtil
 * 
 * @author wei2bei132
 * 
 */
public class ImageUtil {

	/**
	 * 通过资源id获取位图
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap getBitmapById(Context context, int resId) {
		if (context != null && resId > 0) {
			return BitmapFactory.decodeResource(context.getResources(), resId);
		}
		return null;
	}

	/**
	 * 通过资源id获取图片
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Drawable getDrawableById(Context context, int resId) {
		if (context != null && resId > 0) {
			return context.getResources().getDrawable(resId);
		}
		return null;
	}

	/**
	 * 通过文件获取图片
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static Bitmap getBitmapByFile(Context context, String path) {
		if (FileUtil.isSDCardExist() && FileUtil.isHasSDCardPermission(context)
				&& context != null && !StringUtil.isBlank(path)) {
			return BitmapFactory.decodeFile(path);
		}
		return null;
	}

	/**
	 * 通过Assets获取图片
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static Bitmap getBitmapByAssets(Context context, String path) {
		if (context != null && !StringUtil.isBlank(path)) {
			InputStream is = null;
			Bitmap bitmap = null;
			try {
				is = context.getAssets().open(path);
				if (is != null) {
					bitmap = BitmapFactory.decodeStream(is);
					return bitmap;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (is != null) {
					try {
						is.close();
						is = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return null;
	}

	/**
	 * 通过Uri获取图片
	 * 
	 * @param cr
	 * @param uri
	 * @return
	 */
	public static Bitmap getBitmapByUri(ContentResolver cr, Uri uri) {
		if (cr != null && uri != null) {
			try {
				return BitmapFactory.decodeStream(cr.openInputStream(uri));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将Bitmap转化为字节数组
	 * 
	 * @param bitmap
	 * @return
	 */

	public static byte[] bitmap2byte(Bitmap bitmap) {
		if (null == bitmap) {
			return null;
		}
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.PNG, 100, baos);
			byte[] array = baos.toByteArray();
			baos.flush();
			baos.close();
			return array;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 将byte数组转化为bitmap
	 * 
	 * @param data
	 * @return
	 */

	public static Bitmap byte2bitmap(byte[] data) {
		if (null == data) {
			return null;
		}
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

	/**
	 * 将Drawable转化为Bitmap
	 * 
	 * @param drawable
	 * @return
	 */

	public static Bitmap drawable2bitmap(Drawable drawable) {
		if (null == drawable) {
			return null;
		}
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
				: Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * 将bitmap转化为drawable
	 * 
	 * @param bitmap
	 * @return
	 */

	@SuppressWarnings("deprecation")
	public static Drawable bitmap2Drawable(Bitmap bitmap) {
		if (null == bitmap) {
			return null;
		}
		return new BitmapDrawable(bitmap);
	}

	/**
	 * 根据长度宽度缩放图片
	 * 
	 * @param context
	 * @param path
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap createBitmap(Context context, String path, int w, int h) {
		if (FileUtil.isSDCardExist() && FileUtil.isHasSDCardPermission(context)
				&& !StringUtil.isBlank(path) && w > 0 && h > 0) {
			try {
				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(path, opts);
				int srcWidth = opts.outWidth;
				int srcHeight = opts.outHeight;
				int destWidth = 0;
				int destHeight = 0;

				double ratio = 0.0;
				if (srcWidth < w || srcHeight < h) {
					ratio = 0.0;
					destWidth = srcWidth;
					destHeight = srcHeight;
				} else if (srcWidth > srcHeight) {
					ratio = (double) srcWidth / w;
					destWidth = w;
					destHeight = (int) (srcHeight / ratio);
				} else {
					ratio = (double) srcHeight / h;
					destHeight = h;
					destWidth = (int) (srcWidth / ratio);
				}
				BitmapFactory.Options newOpts = new BitmapFactory.Options();

				newOpts.inSampleSize = (int) ratio + 1;

				newOpts.inJustDecodeBounds = false;

				newOpts.outHeight = destHeight;
				newOpts.outWidth = destWidth;

				return BitmapFactory.decodeFile(path, newOpts);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据屏幕大小以及缩放比例压缩图片
	 * 
	 * @param screenWidth
	 * @param filePath
	 * @param ratio
	 * @return
	 */
	public static Bitmap compressionPhoto(Context context, float screenWidth,
			String path, int ratio) {
		if (FileUtil.isSDCardExist() && FileUtil.isHasSDCardPermission(context)
				&& screenWidth > 0 && !StringUtil.isBlank(path) && ratio > 0) {
			Bitmap bitmap = ImageUtil.getBitmapByFile(context, path);
			return compressionPhoto(context, screenWidth, bitmap, ratio);
		}
		return null;
	}

	/**
	 * 根据屏幕大小以及缩放比例压缩图片
	 * 
	 * @param context
	 * @param screenWidth
	 * @param bitmap
	 * @param ratio
	 * @return
	 */
	public static Bitmap compressionPhoto(Context context, float screenWidth,
			Bitmap bitmap, int ratio) {
		if (FileUtil.isSDCardExist() && FileUtil.isHasSDCardPermission(context)
				&& screenWidth > 0 && bitmap != null && ratio > 0) {
			Bitmap compressionBitmap = null;
			float scaleWidth = screenWidth / (bitmap.getWidth() * ratio);
			float scaleHeight = screenWidth / (bitmap.getHeight() * ratio);
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			try {
				compressionBitmap = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			} catch (Exception e) {
				return bitmap;
			}
			return compressionBitmap;
		}
		return null;
	}

	/**
	 * 将bitmap位图保存到path路径下
	 * 
	 * @param bitmap
	 * @param path
	 *            保存路径
	 * @param format
	 *            格式-Bitmap.CompressFormat.PNG或Bitmap.CompressFormat.JPEG.PNG
	 * @param quality
	 *            Hint to the compressor, 0-100. 0 meaning compress for small
	 *            size, 100 meaning compress for max quality. Some formats, like
	 *            PNG which is lossless, will ignore the quality setting
	 * @return
	 */

	public static boolean saveBitmapToLocation(Context context, Bitmap bitmap,
			String path, CompressFormat format, int quality) {
		if (FileUtil.isSDCardExist() && FileUtil.isHasSDCardPermission(context)
				&& FileUtil.isHasAvailableMemory() && bitmap != null
				&& !StringUtil.isBlank(path) && format != null
				&& (quality >= 0 && quality <= 100)) {
			try {
				File file = new File(path);
				File parent = file.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				boolean b = bitmap.compress(format, quality, fos);
				fos.flush();
				fos.close();
				return b;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取图片路径下的图片的64byte数组字符串
	 * 
	 * @param path
	 *            图片的路径
	 * @return 64byte数组字符串
	 */
	public static String getImageByte64ToString(Context context, String path) {
		if (!FileUtil.isSDCardExist()
				|| !FileUtil.isHasSDCardPermission(context)
				|| StringUtil.isEmpty(path)) {
			return null;

		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int count = 0;
		try {
			while ((count = fis.read(buffer)) >= 0) {
				baos.write(buffer, 0, count);
			}
		} catch (IOException e) {
			return null;
		}
		String uploadBuffer = new String(Base64.encode(baos.toByteArray(),
				Base64.DEFAULT));
		try {
			fis.close();
		} catch (IOException e) {
			return null;
		}
		return uploadBuffer;
	}

	/**
	 * 获取圆角图片
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		if (null == bitmap) {
			return null;
		}
		pixels = pixels > 0 ? pixels : 10;
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 获取灰色图片
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap toGrayscale(Bitmap bitmap) {
		if (null == bitmap) {
			return null;
		}
		int width, height;
		height = bitmap.getHeight();
		width = bitmap.getWidth();

		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bitmap, 0, 0, paint);
		return bmpGrayscale;
	}

	/**
	 * 获取倒影图片
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		if (null == bitmap) {
			return null;
		}
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);
		return bitmapWithReflection;
	}

	/**
	 * 获取LOMO特效图片
	 *
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getLomoFilter(Bitmap bitmap) {
		if (null == bitmap) {
			return null;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int dst[] = new int[width * height];
		bitmap.getPixels(dst, 0, width, 0, 0, width, height);

		int ratio = width > height ? height * 32768 / width : width * 32768
				/ height;
		int cx = width >> 1;
		int cy = height >> 1;
		int max = cx * cx + cy * cy;
		int min = (int) (max * (1 - 0.8f));
		int diff = max - min;

		int ri, gi, bi;
		int dx, dy, distSq, v;

		int R, G, B;

		int value;
		int pos, pixColor;
		int newR, newG, newB;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pos = y * width + x;
				pixColor = dst[pos];
				R = Color.red(pixColor);
				G = Color.green(pixColor);
				B = Color.blue(pixColor);

				value = R < 128 ? R : 256 - R;
				newR = (value * value * value) / 64 / 256;
				newR = (R < 128 ? newR : 255 - newR);

				value = G < 128 ? G : 256 - G;
				newG = (value * value) / 128;
				newG = (G < 128 ? newG : 255 - newG);

				newB = B / 2 + 0x25;

				dx = cx - x;
				dy = cy - y;
				if (width > height)
					dx = (dx * ratio) >> 15;
				else
					dy = (dy * ratio) >> 15;

				distSq = dx * dx + dy * dy;
				if (distSq > min) {
					v = ((max - distSq) << 8) / diff;
					v *= v;
					ri = (int) (newR * v) >> 16;
					gi = (int) (newG * v) >> 16;
					bi = (int) (newB * v) >> 16;
					newR = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
					newG = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
					newB = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
				}
				dst[pos] = Color.rgb(newR, newG, newB);
			}
		}
		Bitmap acrossFlushBitmap = Bitmap.createBitmap(width, height,
				Config.RGB_565);
		acrossFlushBitmap.setPixels(dst, 0, width, 0, 0, width, height);
		return acrossFlushBitmap;
	}

	/**
	 * 获取旧时光特效图片
	 * 
	 * @param bmp
	 * @return
	 */
	public static Bitmap getOldTimeFilter(Bitmap bmp) {
		if (null == bmp) {
			return null;
		}
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Config.RGB_565);
		int pixColor = 0;
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				pixColor = pixels[width * i + k];
				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);
				newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
				newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
				newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
				int newColor = Color.argb(255, newR > 255 ? 255 : newR,
						newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
				pixels[width * i + k] = newColor;
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 获取暖意特效图片
	 * 
	 * @param bmp
	 * @param centerX
	 * @param centerY
	 * @return
	 */
	public static Bitmap getWarmthFilter(Bitmap bmp, int centerX, int centerY) {
		if (null == bmp) {
			return null;
		}
		centerX = centerX > 0 && centerX < bmp.getWidth() ? centerX : bmp
				.getWidth() / 2;
		centerY = centerY > 0 && centerY < bmp.getHeight() ? centerY : bmp
				.getHeight() / 2;
		final int width = bmp.getWidth();
		final int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Config.RGB_565);
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;
		int radius = Math.min(centerX, centerY);

		final float strength = 150F;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		int pos = 0;
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				pos = i * width + k;
				pixColor = pixels[pos];

				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);

				newR = pixR;
				newG = pixG;
				newB = pixB;

				int distance = (int) (Math.pow((centerY - i), 2) + Math.pow(
						centerX - k, 2));
				if (distance < radius * radius) {
					int result = (int) (strength * (1.0 - Math.sqrt(distance)
							/ radius));
					newR = pixR + result;
					newG = pixG + result;
					newB = pixB + result;
				}

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));

				pixels[pos] = Color.argb(255, newR, newG, newB);
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 根据饱和度、色相、亮度调整图片
	 * 
	 * @param bm
	 * @param saturation
	 * @param hue
	 * @param lum
	 * @return
	 */
	public static Bitmap getHandleImage(Bitmap bm, int saturation, int hue,
			int lum) {
		if (null == bm) {
			return null;
		}
		Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		ColorMatrix mLightnessMatrix = new ColorMatrix();
		ColorMatrix mSaturationMatrix = new ColorMatrix();
		ColorMatrix mHueMatrix = new ColorMatrix();
		ColorMatrix mAllMatrix = new ColorMatrix();
		float mSaturationValue = saturation * 1.0F / 127;
		float mHueValue = hue * 1.0F / 127;
		float mLumValue = (lum - 127) * 1.0F / 127 * 180;
		mHueMatrix.reset();
		mHueMatrix.setScale(mHueValue, mHueValue, mHueValue, 1);

		mSaturationMatrix.reset();
		mSaturationMatrix.setSaturation(mSaturationValue);
		mLightnessMatrix.reset();

		mLightnessMatrix.setRotate(0, mLumValue);
		mLightnessMatrix.setRotate(1, mLumValue);
		mLightnessMatrix.setRotate(2, mLumValue);

		mAllMatrix.reset();
		mAllMatrix.postConcat(mHueMatrix);
		mAllMatrix.postConcat(mSaturationMatrix);
		mAllMatrix.postConcat(mLightnessMatrix);

		paint.setColorFilter(new ColorMatrixColorFilter(mAllMatrix));
		canvas.drawBitmap(bm, 0, 0, paint);
		return bmp;
	}
}
