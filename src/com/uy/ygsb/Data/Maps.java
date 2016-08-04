package com.uy.ygsb.Data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Pojo.Building;
import com.uy.ygsb.Pojo.Map;
import com.uy.ygsb.Pojo.Monster;
import com.uy.ygsb.Pojo.Npc;
import com.uy.ygsb.Pojo.Response;

public class Maps {

	/**
	 * 获取地图信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static final Response getMapInfo(Auth auth, int mapId)
			throws Exception {
		String[] paramsName = new String[] { "c", "m", "id", "auth_id",
				"auth_key" };
		String[] paramsValue = new String[] { "map", "enter", mapId + "",
				auth.getAuthId() + "", auth.getAuthKey() };
		Api.getApi(auth.getServerUrl()).setParamsName(paramsName);
		Api.getApi(auth.getServerUrl()).setParamsValue(paramsValue);
		JSONObject obj = new JSONObject(Api.getApi(auth.getServerUrl()).query());

		Response respone = new Response();
		respone.setCode(obj.optInt("code"));
		respone.setMsg(obj.optString("msg"));
		if (obj.optInt("code") == 1) {
			JSONObject oMap = obj.getJSONObject("data");
			// 地图信息
			Map map = new Map();
			map.setCamp(oMap.optInt("camp"));
			map.setDesc(oMap.optString("desc"));
			map.setId(oMap.optInt("id"));
			map.setMaxLevel(oMap.optInt("max_level"));
			map.setMinLevel(oMap.optInt("min_level"));
			map.setName(oMap.optString("name"));
			map.setNextMap(oMap.optInt("next_map"));
			map.setPreMap(oMap.optInt("pre_map"));
			map.setSkin(oMap.optString("skin"));

			// 建筑信息
			JSONArray buildings = oMap.optJSONArray("buildings");
			List<Building> buildingList = new ArrayList<Building>();
			for (int i = 0, j = buildings.length(); i < j; i++) {
				JSONObject o = buildings.optJSONObject(i);
				Building building = new Building();
				building.setId(o.optInt("id"));
				building.setMapId(o.optInt("map"));
				building.setName(o.optString("name"));
				building.setPosX(o.optInt("posx"));
				building.setPosY(o.optInt("posy"));
				building.setSkin(o.optString("skin"));
				building.setType(o.optInt("type"));
				buildingList.add(building);
			}
			map.setBuildings(buildingList);

			// NPC信息
			JSONArray npcs = oMap.optJSONArray("npcs");
			List<Npc> npcList = new ArrayList<Npc>();
			for (int i = 0, j = npcs.length(); i < j; i++) {
				JSONObject o = npcs.optJSONObject(i);
				Npc npc = new Npc();
				npc.setDialogue(o.optString("dialogue"));
				npc.setId(o.optInt("id"));
				npc.setAvatar(o.optString("avatar"));
				npc.setMapId(o.optInt("map"));
				npc.setName(o.optString("name"));
				npc.setPosX(o.optInt("posx"));
				npc.setPosY(o.optInt("posy"));
				npc.setSkin(o.optString("skin"));
				npcList.add(npc);
			}
			map.setNpcs(npcList);

			// 怪物信息
			JSONArray monsters = oMap.optJSONArray("monsters");
			List<Monster> monsterList = new ArrayList<Monster>();
			for (int i = 0, j = monsters.length(); i < j; i++) {
				JSONObject o = monsters.optJSONObject(i);
				Monster monster = new Monster();
				monster.setId(o.optInt("id"));
				monster.setMapId(o.optInt("map"));
				monster.setName(o.optString("name"));
				monster.setPosX(o.optInt("posx"));
				monster.setPosY(o.optInt("posy"));
				monster.setSkin(o.optString("skin"));
				monster.setAvoid(o.optInt("avoid"));
				monster.setHp(o.optInt("hp"));
				monster.setLevel(o.optInt("level"));
				monster.setMagicDefence(o.optInt("magic_defence"));
				monster.setMaxAttack(o.optInt("max_attack"));
				monster.setMinAttack(o.optInt("min_attack"));
				monster.setMp(o.optInt("mp"));
				monster.setPhyDefence(o.optInt("phy_defence"));
				monster.setAvatar(o.optString("avatar"));
				monsterList.add(monster);
			}
			map.setMonsters(monsterList);

			respone.setData(map);
		}
		return respone;
	}
}