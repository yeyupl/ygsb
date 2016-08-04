package com.uy.ygsb.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

public class Utils {

	// 随机数
	public static int getRandom(int max) {
		return getRandom(0, max);
	}

	public static int getRandom(int min, int max) {
		max++;
		int temp = 0;
		try {
			if (min > max) {
				temp = new Random().nextInt(min - max);
				return temp + max;
			} else {
				temp = new Random().nextInt(max - min);
				return temp + min;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp + min;
	}

	// 根据名称获取图片资源ID
	public static int getImgResId(Context context, String name) {
		int resId = context.getResources().getIdentifier(name, "drawable",
				context.getPackageName());
		return resId;
	}

	// 根据名称获取资源ID
	public static int getResId(Context context, String name, String type) {
		int resId = context.getResources().getIdentifier(name, type,
				context.getPackageName());
		return resId;
	}

	// MD5加密
	public static String md5(String encodeStr) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(encodeStr.getBytes());

			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException ex) {

		}
		return null;
	}

	/**
	 * 将dip转换成px
	 * 
	 * @param dip
	 * @return
	 */
	public static int dipToPx(Context context, int dip) {
		return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
	}

	// 字符串截取
	public static String substr(String str, int length) {
		if (str == null || str.equals("")) {
			return "";
		}
		byte[] bytes;
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		String dots = "";
		try {
			bytes = str.getBytes("Unicode");
			if (bytes.length >= length)
				dots = "...";
			for (; i < bytes.length && n < length; i++) {
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1) {
					n++; // 在UCS2第二个字节时n加1
				} else {
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0) {
						n++;
					}
				}
			}
			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1) {
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0)
					i = i - 1;
				// 该UCS2字符是字母或数字，则保留该字符
				else
					i = i + 1;
			}
			return new String(bytes, 0, i, "Unicode") + dots;
		} catch (Exception e) {

		}
		return str;
	}

	// 网络是否可用
	public static boolean isNetwork(Context context) {
		// 判断接入方式
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		// 如果联网
		if (info != null && info.isAvailable()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将新的List加入到原来的List中
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List appendList(List sourceList, List appendList) {
		for (Object o : appendList) {
			sourceList.add(o);
		}
		return sourceList;
	}

	// 获取手机IMEI
	public static String getDeviceCode(Context context) {
		return ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}

	// 获取程序版本序号
	public static int getVerCode(Context context) {
		int versionCode = 0;
		try {
			PackageInfo info = context.getPackageManager()
					.getPackageInfo(context.getPackageName(),
							PackageManager.GET_CONFIGURATIONS);
			versionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	// 获取程序版本名称
	public static String getVerName(Context context) {
		String versionName = null;
		try {
			PackageInfo info = context.getPackageManager()
					.getPackageInfo(context.getPackageName(),
							PackageManager.GET_CONFIGURATIONS);
			versionName = info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	// 获取手机型号
	public static String getModel() {
		return android.os.Build.MODEL;
	}

	// 获取手机操作系统版本
	public static String getOs() {
		return android.os.Build.VERSION.RELEASE;
	}

	// 获取手机操作系统SDK版本
	public static String getSdk() {
		return android.os.Build.VERSION.SDK;
	}

	// 获取屏幕分辨率
	public static String getResolution(Context context) {
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		return width + "x" + height;
	}

	// SDCard 是否可用
	public static boolean isSDCard() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			return true;
		}
		return false;
	}

}