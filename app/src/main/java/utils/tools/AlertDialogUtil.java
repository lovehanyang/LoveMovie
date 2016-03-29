package utils.tools;


import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

public class AlertDialogUtil {

	private static AlertDialogUtil instance = null;
	
	private Builder builder;
	
	private AlertDialogUtil(){
	}
	public static AlertDialogUtil getInstance(){
		if(instance==null){
			instance = new AlertDialogUtil();
		}
		return instance;
	}
	
	public void show(Context context,String title,String message,
			String negativeButtonTxt,OnClickListener negativeButtonListener,
			String positiveButtonTxt,OnClickListener positiveButtonListener){
		builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setCancelable(true);
		if (negativeButtonTxt != null && negativeButtonTxt.endsWith("")) {
			builder.setNegativeButton(negativeButtonTxt, negativeButtonListener);
		}
		builder.setPositiveButton(positiveButtonTxt, positiveButtonListener);
		builder.create();
		builder.show();
	}
	
	public void show(Context context,int titleRes,int messageRes,
			int negativeButtonTxtRes,OnClickListener negativeButtonListener,
			int positiveButtonTxtRes,OnClickListener positiveButtonListener){
		builder = new Builder(context);
		builder.setTitle(titleRes);
		builder.setMessage(messageRes);
		builder.setCancelable(true);
		builder.setNegativeButton(negativeButtonTxtRes, negativeButtonListener);
		builder.setPositiveButton(positiveButtonTxtRes, positiveButtonListener);
		builder.create();
		builder.show();
	}
	
}
