package com.uy.ygsb.Game;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.uy.ygsb.R;
import com.uy.ygsb.Activity.GameActivity;
import com.uy.ygsb.Data.Maps;
import com.uy.ygsb.Pojo.Building;
import com.uy.ygsb.Pojo.Map;
import com.uy.ygsb.Pojo.Monster;
import com.uy.ygsb.Pojo.Npc;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Ui.GameCellView;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Util.ImageUtil;

public class GameMap implements OnClickListener {
	public Context context;
	public FrameLayout gameMap;
	private Map map;
	private Response response;
	private ArrayList<GameCellView> cells = new ArrayList<GameCellView>() {
	};

	public GameMap(Context context) {
		this.context = context;
		this.gameMap = (FrameLayout) ((GameActivity) context)
				.findViewById(R.id.game_map);
	}

	public void setMap(Map map) {
		this.map = map;
		this.gameMap.setBackgroundDrawable(new BitmapDrawable(ImageUtil
				.getInstance(context).getImage(map.getSkin())));
	}

	public Map getMap() {
		return this.map;
	}

	public void addCell(Monster monster) {
		GameCellView cell = new GameCellView(this.context, ImageUtil
				.getInstance(context).getImage(monster.getSkin()),
				monster.getName());
		cell.setTag(monster);
		this.addCell(cell, monster.getPosY(), monster.getPosX());
	}

	public void addCell(Npc npc) {
		GameCellView cell = new GameCellView(this.context, ImageUtil
				.getInstance(context).getImage(npc.getSkin()), npc.getName());
		cell.setTag(npc);
		this.addCell(cell, npc.getPosY(), npc.getPosX());
	}

	public void addCell(Building building) {
		GameCellView cell = new GameCellView(this.context, ImageUtil
				.getInstance(context).getImage(building.getSkin()),
				building.getName());
		cell.setTag(building);
		this.addCell(cell, building.getPosY(), building.getPosX());
	}

	private void addCell(GameCellView cell, int posY, int posX) {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.LEFT;
		params.topMargin = posY;
		params.leftMargin = posX;
		cell.setOnClickListener(this);
		this.gameMap.addView(cell, params);
		this.cells.add(cell);
	}

	private void removeCells() {
		for (GameCellView cell : cells) {
			this.gameMap.removeView(cell);
		}
	}

	@Override
	public void onClick(View v) {
		v.invalidate();
		if (v.getTag() instanceof Monster) {
			GameBattle.getInstance(context, (Monster) v.getTag());
		} else if (v.getTag() instanceof Npc) {
			if (this.map.getId() == 1) {
				this.enter(2);
			} else {
				this.enter(1);
			}
		} else {
			if (this.map.getId() == 1) {
				this.enter(2);
			} else {
				this.enter(1);
			}
		}	
	}

	// 渲染地图
	public void render() {
		Map map = ((GameActivity) context).gameApp.getMapInfo();
		this.setMap(map);
		this.removeCells();
		if (map != null) {
			// 建筑
			if (map.getBuildings().size() > 0) {
				for (Building building : map.getBuildings()) {
					this.addCell(building);
				}
			}
			// NPC
			if (map.getNpcs().size() > 0) {
				for (Npc npc : map.getNpcs()) {
					this.addCell(npc);
				}
			}
			// 怪物
			if (map.getMonsters().size() > 0) {
				for (Monster monster : map.getMonsters()) {
					this.addCell(monster);
				}
			}
		}
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_FAIL:
				((GameActivity) context).showErr();
				break;
			case Constant.MSG_MAP_SUCCESS:
				if (response.getCode() == 1) {
					((GameActivity) context).gameApp.setMapInfo((Map) response
							.getData());
					render();
				} else {
					GameToast.makeText(context, response.getMsg(),
							GameToast.LENGTH_SHORT).show();
				}
				break;

			}
		}
	};

	// 地图跳转
	public void enter(final int mapId) {
		GameToast.makeText(context, "正在跳转地图" + mapId + "......",
				GameToast.LENGTH_SHORT).show();
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Maps.getMapInfo(
							((GameActivity) context).gameApp.getAuth(), mapId);
					mHandler.sendEmptyMessage(Constant.MSG_MAP_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}
}