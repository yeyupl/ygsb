package com.uy.ygsb.Data;

import org.json.JSONObject;

import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Pojo.User;

public class Users {

	/**
	 * 用户注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response reg(String name, String password,
			String model, String os, String imei, String resolution,
			int verCode, String verName) throws Exception {
		String[] paramsName = new String[] { "c", "m", "name", "password",
				"equip_model", "equip_os", "equip_id", "equip_screen",
				"version_code", "version_name" };
		String[] paramsValue = new String[] { "user", "create", name, password,
				model, os, imei, resolution, verCode + "", verName };
		Api.getApi().setParamsName(paramsName);
		Api.getApi().setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi().query());

		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			User user = new User();
			JSONObject o = obj.getJSONObject("data");
			user.setId(o.optInt("id"));
			user.setName(name);
			user.setAuthKey(o.optString("key"));
			respone.setData(user);
		}
		return respone;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response login(String name, String password)
			throws Exception {
		String[] paramsName = new String[] { "c", "m", "name", "password" };
		String[] paramsValue = new String[] { "user", "login", name, password };
		Api.getApi().setParamsName(paramsName);
		Api.getApi().setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi().query());

		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			User user = new User();
			JSONObject o = obj.getJSONObject("data");
			user.setId(o.optInt("id"));
			user.setName(o.optString("name"));
			user.setAuthKey(o.optString("key"));
			respone.setData(user);
		}
		return respone;
	}

}