package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.File;

public class ActivityResultUtil {
	/**
	 * 当使用IntentUtil.intentToAlbum时在onActivityResult中调用获取结果
	 * <ul>
	 * <strong>ActivityResult.result返回值</strong>
	 * <li><b>ERROR</b>: RequestCode不等于IntentUtil.INTENT_REQUEST_CODE_ALBUM时返回</li>
	 * <li><b>NO_DATA</b>: 参数Intent data
	 * 为空或者data.getData()为空或者Cursor为空或者Cursor未找到相关值时返回</li>
	 * <li><b>CANCEL</b>:ResultCode不等于Activity.RESULT_OK时返回</li>
	 * <li><b>SD_UNAVAILABLE</b>:SD卡不可用时返回</li>
	 * <li><b>NO_PERMISSION</b>:没有SD卡读写权限时返回</li>
	 * <li><b>SUCCESS</b>:成功获取时返回</li>
	 * </ul>
	 * 
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@SuppressWarnings("deprecation")
	public static ActivityResult onAlbumResult(Activity activity,
			int requestCode, int resultCode, Intent data) {
		if (requestCode == IntentUtil.INTENT_REQUEST_CODE_ALBUM) {
			if (resultCode == Activity.RESULT_OK) {
				if (!FileUtil.isSDCardExist()) {
					return new ActivityResult(Result.SD_UNAVAILABLE, null);
				}
				if (!FileUtil.isHasSDCardPermission((Context) activity)) {
					return new ActivityResult(Result.NO_PERMISSION, null);
				}
				if (data != null && data.getData() != null) {
					Uri uri = data.getData();
					String[] proj = { MediaStore.Images.Media.DATA };
					Cursor cursor = activity.managedQuery(uri, proj, null,
							null, null);
					if (cursor != null) {
						int column_index = cursor
								.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
						if (cursor.getCount() > 0 && cursor.moveToFirst()) {
							String path = cursor.getString(column_index);
							Bitmap image = ImageUtil.getBitmapByFile(
									(Context) activity, path);
							Bundle dataBundle = new Bundle();
							dataBundle
									.putString(ActivityResult.DATA_PATH, path);
							dataBundle.putParcelable(ActivityResult.DATA_IMAGE,
									image);
							return new ActivityResult(Result.SUCCESS,
									dataBundle);
						}
					}
				}
				return new ActivityResult(Result.NO_DATA, null);

			} else {
				return new ActivityResult(Result.CANCEL, null);
			}
		}
		return new ActivityResult(Result.ERROR, null);
	}

	/**
	 * 当使用IntentUtil.intentToCamera时在onActivityResult中调用获取结果
	 * <ul>
	 * <strong>ActivityResult.result返回值</strong>
	 * <li><b>ERROR</b>: RequestCode不等于IntentUtil.INTENT_REQUEST_CODE_CAMERA时返回</li>
	 * <li><b>NO_DATA</b>: 参数path为空或者path所对应的文件不存在时返回</li>
	 * <li><b>CANCEL</b>:ResultCode不等于Activity.RESULT_OK时返回</li>
	 * <li><b>SD_UNAVAILABLE</b>:SD卡不可用时返回</li>
	 * <li><b>NO_PERMISSION</b>:没有SD卡读写权限时返回</li>
	 * <li><b>SUCCESS</b>:成功获取时返回</li>
	 * </ul>
	 * 
	 * @param activity
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 * @return
	 */
	public static ActivityResult onCameraResult(Activity activity,
			int requestCode, int resultCode, Intent data, String path) {
		if (requestCode == IntentUtil.INTENT_REQUEST_CODE_CAMERA) {
			if (resultCode == Activity.RESULT_OK) {
				if (!FileUtil.isSDCardExist()) {
					return new ActivityResult(Result.SD_UNAVAILABLE, null);
				}
				if (!FileUtil.isHasSDCardPermission((Context) activity)) {
					return new ActivityResult(Result.NO_PERMISSION, null);
				}
				if (!StringUtil.isEmpty(path)) {
					if (new File(path).exists()) {
						Bitmap image = ImageUtil.getBitmapByFile(
								(Context) activity, path);
						Bundle dataBundle = new Bundle();
						dataBundle.putString(ActivityResult.DATA_PATH, path);
						dataBundle.putParcelable(ActivityResult.DATA_IMAGE,
								image);
						return new ActivityResult(Result.SUCCESS, dataBundle);
					}
				}
				return new ActivityResult(Result.NO_DATA, null);
			} else {
				return new ActivityResult(Result.CANCEL, null);
			}
		}
		return new ActivityResult(Result.ERROR, null);
	}

	public static class ActivityResult {
		public static final String DATA_PATH = "_path";
		public static final String DATA_IMAGE = "_image";
		private Result result;
		private Bundle data;

		public ActivityResult(Result result, Bundle data) {
			super();
			this.result = result;
			this.data = data;
		}

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		}

		public Bundle getData() {
			return data;
		}

		public void setData(Bundle data) {
			this.data = data;
		}

	}

	public enum Result {
		ERROR, NO_DATA, CANCEL, SD_UNAVAILABLE, NO_PERMISSION, SUCCESS;
	}
}
