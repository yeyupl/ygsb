package com.uy.ygsb.Data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Pojo.Item;
import com.uy.ygsb.Pojo.Response;

public class Bags {

	/**
	 * 获取背包中的物品
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response getBag(Auth auth, int bagId) throws Exception {
		String[] paramsName = new String[] { "c", "m", "bag_id", "auth_id",
				"auth_key" };
		String[] paramsValue = new String[] { "bag", "get_bag", bagId + "",
				auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());
		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			List<Item> itemList = new ArrayList<Item>();
			JSONArray array = obj.optJSONArray("data");
			for (int i = 0, j = array.length(); i < j; i++) {
				JSONObject o = array.optJSONObject(i);
				Item item = new Item();
				item.setId(o.optInt("id"));
				item.setName(o.optString("name"));
				item.setCategory(o.optInt("category"));
				item.setAddNum(o.optInt("add_num"));
				item.setNum(o.optInt("num"));
				item.setType(o.optInt("type"));
				item.setTypeId(o.optInt("type_id"));
				item.setSubType(o.optInt("sub_type"));
				item.setDesc(o.optString("desc"));
				item.setSellCopper(o.optInt("sell_copper"));
				item.setSellAble(o.optBoolean("sell_able"));
				item.setDropAble(o.optBoolean("drop_able"));
				item.setTimeLimit(o.optInt("tilelimit"));
				item.setBuyAble(o.optBoolean("buy_able"));
				item.setBuyCopper(o.optInt("buy_copper"));
				item.setBuySilver(o.optInt("buy_silver"));
				item.setBuyGold(o.optInt("buy_gold"));
				itemList.add(item);
			}
			respone.setData(itemList);
		}
		return respone;
	}

	/**
	 * 出售物品
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response sell(Auth auth, int bag_id, int id, int num)
			throws Exception {
		String[] paramsName = new String[] { "c", "m", "bag_id", "id", "num",
				"auth_id", "auth_key" };
		String[] paramsValue = new String[] { "bag", "sell", bag_id + "",
				id + "", num + "", auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());
		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		return respone;
	}

	/**
	 * 丢弃物品
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response drop(Auth auth, int bag_id, int id, int num)
			throws Exception {
		String[] paramsName = new String[] { "c", "m", "bag_id", "id", "num",
				"auth_id", "auth_key" };
		String[] paramsValue = new String[] { "bag", "drop", bag_id + "",
				id + "", num + "", auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());
		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		return respone;
	}
}