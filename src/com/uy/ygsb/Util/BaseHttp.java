package com.uy.ygsb.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;


public class BaseHttp {

	private String URL = "";
	private UrlEncodedFormEntity entity = null;
	private HttpClient httpClient = null;
	private HttpPost httpPost = null;
	private HttpGet httpGet = null;
	private HttpResponse response = null;
	private BufferedReader in = null;
	private final int CONNECTION_TIMEOUT = 15000;
	private final int SO_TIMEOUT = 15000;

	public BaseHttp(String URL) {
		this.URL = URL;
	}

	public BaseHttp(String URL, UrlEncodedFormEntity entity) {
		this.URL = URL;
		this.entity = entity;
	}

	/**
	 * 初始化HttpClient，设置请求超时和读取超时
	 * 
	 * @throws Exception
	 */
	private void initHttpClient() throws Exception {
		httpClient = new DefaultHttpClient();
		// 请求超时
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
		// 读取超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				SO_TIMEOUT);
	}

	/**
	 * Get请求并读取返回数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String get() throws Exception {
		httpGet = new HttpGet(URL);
		initHttpClient();
		response = httpClient.execute(httpGet);
		return this.getData();
	}

	/**
	 * Post请求并读取返回数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String post() throws Exception {
		initHttpClient();
		httpPost = new HttpPost(URL);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		response = httpClient.execute(httpPost);
		return this.getData();
	}

	/**
	 * 读取返回数据
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getData() throws Exception {
		InputStream is = null;
		Header header = response.getFirstHeader("Content-Encoding");
		String acceptEncoding = "";
		if (header != null)
			acceptEncoding = header.getValue();

		if (acceptEncoding.toLowerCase().indexOf("gzip") > -1) {
			// 建立gzip解压工作流
			is = new GZIPInputStream(response.getEntity().getContent());
		} else {
			is = response.getEntity().getContent();
		}

		in = new BufferedReader(new InputStreamReader(is));
		String line = "";
		StringBuilder sb = new StringBuilder();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		is.close();
		return sb.toString();
	}
}
