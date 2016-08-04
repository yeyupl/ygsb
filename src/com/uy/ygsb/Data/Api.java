package com.uy.ygsb.Data;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.uy.ygsb.Game.Constant;
import com.uy.ygsb.Util.BaseHttp;

/**
 * 接口请求类
 * 
 */
public class Api {
	private String[] paramsName;
	private String[] paramsValue;
	private List<NameValuePair> params;
	private BaseHttp http;
	private static Api _api;
	private String server_url = Constant.server_url;

	private final String charset = HTTP.UTF_8; // 请求数据的编码
	private long timeStamp; // 时间戳

	public Api(String server_url) {
		this.timeStamp = System.currentTimeMillis() / 1000;
		this.server_url = server_url;
	}

	public static Api getApi() {
		return getApi(Constant.server_url);
	}

	public static Api getApi(String server_url) {
		if (_api == null) {
			_api = new Api(server_url);
		}
		return _api;
	}

	public void setServerUrl(String url) {
		this.server_url = url;
	}

	/**
	 * 发送请求,加载数据
	 * 
	 * @return
	 */
	protected String query() throws Exception {
		params = new ArrayList<NameValuePair>();
		for (int i = 0, j = paramsName.length; i < j; i++) {
			setParam(paramsName[i], paramsValue[i]);
		}
		setParam("ts", String.valueOf(timeStamp));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, charset);
		http = new BaseHttp(this.server_url, entity);
		return http.post();
	}

	/**
	 * 设置参数
	 * 
	 * @param key
	 * @param value
	 */
	private void setParam(String key, String value) {
		params.add(new BasicNameValuePair(key, value));
	}

	public void setParamsName(String[] paramsName) {
		this.paramsName = paramsName;
	}

	public void setParamsValue(String[] paramsValue) {
		this.paramsValue = paramsValue;
	}

	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public long save() throws Exception {
		JSONObject obj = new JSONObject(query());
		if (obj.optLong("code") == 1)
			return obj.optLong("data");
		else
			return 0;
	}

	/**
	 * 更新
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean update() throws Exception {
		JSONObject obj = new JSONObject(query());
		if (obj.optLong("code") == 1)
			return true;
		else
			return false;
	}
}
