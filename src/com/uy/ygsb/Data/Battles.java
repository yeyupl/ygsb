package com.uy.ygsb.Data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.uy.ygsb.Pojo.Attack;
import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Pojo.Battle;
import com.uy.ygsb.Pojo.Response;

public class Battles {

	/**
	 * 战斗
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response attack(Auth auth, int id, int type)throws Exception {
		String[] paramsName = new String[] { "c", "m", "id", "type", "auth_id","auth_key" };
		String[] paramsValue = new String[] { "battle", "attack", "1","2", auth.getAuthId() + "", auth.getAuthKey()};
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());

		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			List<Battle> battleList = new ArrayList<Battle>();
			JSONArray array = obj.optJSONArray("data");
			for (int i = 0, j = array.length(); i < j; i++) {
				JSONObject o = array.optJSONObject(i);

				Battle battle = new Battle();
				battle.setInfo(o.optString("info"));
				
				JSONObject attackObj = o.getJSONObject("attack_actor");
				Attack attack = new Attack();
				attack.setId(attackObj.optInt("id"));
				attack.setType(attackObj.optInt("actor_type"));
				attack.setLoseHp(attackObj.optInt("lose_hp"));
				attack.setLoseMp(attackObj.optInt("lose_mp"));
				battle.setAttack(attack);

				JSONObject attackedObj = o.getJSONObject("be_attack_actor");
				Attack attacked = new Attack();
				attacked.setId(attackedObj.optInt("id"));
				attacked.setType(attackedObj.optInt("actor_type"));
				attacked.setLoseHp(attackedObj.optInt("lose_hp"));
				attacked.setLoseMp(attackedObj.optInt("lose_mp"));
				battle.setAttacked(attacked);

				battleList.add(battle);
			}
			respone.setData(battleList);
		}
		return respone;
	}
}