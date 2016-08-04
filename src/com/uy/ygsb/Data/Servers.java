package com.uy.ygsb.Data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Pojo.Server;

public class Servers {

	/**
	 * 获取服务器列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response getServerList(Auth auth) throws Exception {
		String[] paramsName = new String[] { "c", "m", "auth_id", "auth_key" };
		String[] paramsValue = new String[] { "server", "server_list",
				auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi().setParamsName(paramsName);
		Api.getApi().setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi().query());

		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			List<Server> serverList = new ArrayList<Server>();
			JSONArray array = obj.optJSONArray("data");
			for (int i = 0, j = array.length(); i < j; i++) {
				JSONObject o = array.optJSONObject(i);
				Server server = new Server();
				server.setId(o.optInt("id"));
				server.setStatus(o.optInt("status"));
				server.setName(o.optString("name"));
				server.setOpenTime(o.optLong("time_open"));
				server.setUnum(o.optInt("unum"));
				server.setUrl(o.optString("url"));
				serverList.add(server);
			}
			respone.setData(serverList);
		}
		return respone;
	}

}