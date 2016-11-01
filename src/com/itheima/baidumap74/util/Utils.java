package com.itheima.baidumap74.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Utils {

	/**
	 * ����Ļ�м���ʾһ��Toast
	 * 
	 * @param text
	 */
	public static void showToast(Context context, CharSequence text) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}
