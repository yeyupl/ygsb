package com.uy.ygsb.Data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Pojo.Role;

public class Roles {

	/**
	 * 获取服务器角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response getRoleList(Auth auth) throws Exception {
		String[] paramsName = new String[] { "c", "m", "auth_id", "auth_key" };
		String[] paramsValue = new String[] { "role", "get_all_role",
				auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());

		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			List<Role> roleList = new ArrayList<Role>();
			JSONArray array = obj.optJSONArray("data");
			for (int i = 0, j = array.length(); i < j; i++) {
				JSONObject o = array.optJSONObject(i);
				Role role = new Role();
				role.setId(o.optInt("id"));
				role.setName(o.optString("name"));
				role.setCampName(o.optString("camp_name"));
				role.setJobName(o.optString("job_name"));
				role.setCamp(o.optInt("camp"));
				role.setEnergy(o.optInt("energy"));
				role.setJob(o.optInt("job"));
				role.setLevel(o.optInt("level"));
				role.setMaxEnergy(o.optInt("max_energy"));
				role.setUid(o.optInt("uid"));
				roleList.add(role);
			}
			respone.setData(roleList);
		}
		return respone;
	}

	/**
	 * 获取角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response getRole(Auth auth, int id) throws Exception {
		String[] paramsName = new String[] { "c", "m", "id", "auth_id",
				"auth_key" };
		String[] paramsValue = new String[] { "role", "get_role_info", id + "",
				auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());

		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			JSONObject o = obj.getJSONObject("data");

			Role role = new Role();
			role.setId(o.optInt("id"));
			role.setName(o.optString("name"));
			role.setCampName(o.optString("camp_name"));
			role.setJobName(o.optString("job_name"));
			role.setAgile(o.optInt("agile"));
			role.setAttrPoints(o.optInt("attr_points"));
			role.setCamp(o.optInt("camp"));
			role.setCopper(o.optInt("copper"));
			role.setCreateTime(o.optLong("time_create"));
			role.setEnergy(o.optInt("energy"));
			role.setExp(o.optInt("exp"));
			role.setGold(o.optInt("gold"));
			role.setHp(o.optInt("hp"));
			role.setJob(o.optInt("job"));
			role.setLastLoginTime(o.optLong("time_lastlogin"));
			role.setLevel(o.optInt("level"));
			role.setMapId(o.optInt("map"));
			role.setMaxEnergy(o.optInt("max_energy"));
			role.setMaxHp(o.optInt("max_hp"));
			role.setMaxMp(o.optInt("max_mp"));
			role.setMind(o.optInt("mide"));
			role.setMp(o.optInt("mp"));
			role.setPower(o.optInt("power"));
			role.setSilver(o.optInt("silver"));
			role.setUid(o.optInt("uid"));
			role.setGender(o.optInt("gender"));
			respone.setData(role);
		}
		return respone;
	}

	/**
	 * 创建角色
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response createRole(Auth auth, int job, String name,
			int gender) throws Exception {
		String[] paramsName = new String[] { "c", "m", "job", "name", "gender",
				"auth_id", "auth_key" };
		String[] paramsValue = new String[] { "role", "create", job + "", name,
				gender + "", auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());
		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			JSONObject o = obj.getJSONObject("data");
			Role role = new Role();
			role.setId(o.optInt("id"));
			respone.setData(role);
		}
		return respone;
	}

}