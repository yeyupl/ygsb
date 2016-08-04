package com.uy.ygsb.Data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Pojo.Item;
import com.uy.ygsb.Pojo.Response;

public class Shops {

	/**
	 * 获取商店中的物品
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response getShop(Auth auth, int shopId)
			throws Exception {
		String[] paramsName = new String[] { "c", "m", "id", "auth_id",
				"auth_key" };
		String[] paramsValue = new String[] { "shop", "get_shop", shopId + "",
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
	 * 购买道具
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response buy(Auth auth, int type_id, int num)
			throws Exception {
		String[] paramsName = new String[] { "c", "m", "type_id", "num",
				"auth_id", "auth_key" };
		String[] paramsValue = new String[] { "shop", "buy", type_id + "",
				num + "", auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());
		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		return respone;
	}
}