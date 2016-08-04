package com.uy.ygsb.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uy.ygsb.R;
import com.uy.ygsb.Data.Maps;
import com.uy.ygsb.Data.Roles;
import com.uy.ygsb.Game.Constant;
import com.uy.ygsb.Pojo.Map;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Pojo.Role;
import com.uy.ygsb.Util.Utils;

public class LoadingActivity extends BaseActivity {
	private LinearLayout loading_layout;
	private Response response;
	private TextView tips;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);

		this.loading_layout = (LinearLayout) findViewById(R.id.loading_layout);
		this.loading_layout.setBackgroundResource(Utils.getImgResId(this, "bg_"
				+ Utils.getRandom(1, 4)));

		this.tips = (TextView) findViewById(R.id.loading_tips);

		this.setMusic(false);

		this.getRoleData();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_FAIL:
				showErr();
				break;
			case Constant.MSG_SUCCESS:
				if (response.getCode() == 1) {
					tips.setText("角色信息加载成功......");
					gameApp.setRoleInfo((Role) response.getData());
					getMapData();
				} else {
					tips.setText(response.getMsg());
				}
				break;
			case Constant.MSG_MAP_SUCCESS:
				if (response.getCode() == 1) {
					tips.setText("地图信息加载成功......");
					gameApp.setMapInfo((Map) response.getData());
					tips.setText("加载完毕，正在进入游戏......");
					startGame();
				} else {
					tips.setText(response.getMsg());
				}
				break;

			}
		}
	};

	private void getRoleData() {
		this.tips.setText("正在加载角色信息......");
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Roles.getRole(gameApp.getAuth(), gameApp
							.getRoleInfo().getId());
					mHandler.sendEmptyMessage(Constant.MSG_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void getMapData() {
		this.tips.setText("正在加载地图信息......");
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Maps.getMapInfo(gameApp.getAuth(), gameApp
							.getRoleInfo().getMapId());
					mHandler.sendEmptyMessage(Constant.MSG_MAP_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void startGame() {
		Intent intent = new Intent(this, GameActivity.class);
		this.startActivity(intent);
		this.finish();
	}
}