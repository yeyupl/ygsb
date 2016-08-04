package com.uy.ygsb.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.uy.ygsb.R;
import com.uy.ygsb.Game.Constant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class ImageUtil {
	private static String resUrl = Constant.res_url;
	private String filePath;
	private Context context;
	private static ImageUtil imageUtil;

	public ImageUtil(Context context) {
		this.context = context;
		this.filePath = "/sdcard/99uy/";
		if (Utils.isSDCard()) {
			File dir = new File(this.filePath);
			if (!dir.exists() || !dir.isDirectory()) {
				dir.mkdir();
			}
		}
	}

	public static ImageUtil getInstance(Context context) {
		if (imageUtil == null) {
			imageUtil = new ImageUtil(context);
		}
		return imageUtil;
	}

	/*
	 * 获取图片 并缓存
	 */
	public Bitmap getImage(String name) {
		if (name == null) {
			name = "DEFAULT_IMG";
		}
		String fileName = Utils.md5(name);
		Bitmap bitmap = null;
		// 判断SDCard文件存不存在
		File file = new File(this.filePath + fileName);
		if (file.exists() && file.isFile()) {
			// 存在，从SDCard读取
			bitmap = this.getImageFromSDCard(fileName);
		} else {
			// 从网络读取并缓存到SDCard
			bitmap = this.getImageFromNetwork(resUrl + name);
			if (bitmap == null) {
				// 如果不存在，用默认的图片代替 不缓存
				bitmap = BitmapFactory.decodeResource(
						this.context.getResources(), R.drawable.item);
			} else {
				this.saveImageToSdCard(bitmap, fileName);
			}
		}
		return bitmap;
	}

	/*
	 * 保存图片到SDCard上
	 */
	private Bitmap saveImageToSdCard(Bitmap bitmap, String fileName) {
		try {
			File file = new File(this.filePath + fileName);
			bitmap.compress(CompressFormat.PNG, 100, new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/*
	 * 从网络获取图片
	 */
	private Bitmap getImageFromNetwork(String fileUrl) {
		Bitmap bitmap = null;
		try {
			URL url = new URL(fileUrl);
			bitmap = BitmapFactory.decodeStream(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/*
	 * 从SDCard读取图片
	 */
	private Bitmap getImageFromSDCard(String fileName) {
		return BitmapFactory.decodeFile(this.filePath + fileName);
	}

}
