package com.uy.ygsb.Util;

import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class StoreUtil {

	private SharedPreferences sp;
	private Editor editor;
	private String name = "YGSB";
	private int mode = Context.MODE_PRIVATE;

	public StoreUtil(Context context) {
		this.sp = context.getSharedPreferences(name, mode);
		this.editor = sp.edit();
	}

	/**
	 * 添加信息到SharedPreferences
	 * 
	 * @param name
	 * @param map
	 * @throws Exception
	 */
	public void set(Map<String, String> map) {
		Set<String> set = map.keySet();
		for (String key : set) {
			editor.putString(key, map.get(key));
		}
		editor.commit();
	}

	public void set(String key, String val) {
		editor.putString(key, val);
		editor.commit();
	}

	public void set(String key, int val) {
		editor.putInt(key, val);
		editor.commit();
	}

	/**
	 * 删除所有信息
	 * 
	 * @throws Exception
	 */
	public void deleteAll() throws Exception {
		editor.clear();
		editor.commit();
	}

	/**
	 * 删除一条信息
	 */
	public void delete(String key) throws Exception {
		editor.remove(key);
		editor.commit();
	}

	/**
	 * 获取字符信息
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String get(String key) {
		if (sp != null) {
			return sp.getString(key, "");
		}
		return "";
	}

	/**
	 * 获取整数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int getInt(String key) {
		if (sp != null) {
			return sp.getInt(key, 0);
		}
		return 0;
	}

}
