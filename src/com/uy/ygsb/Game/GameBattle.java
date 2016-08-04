package com.uy.ygsb.Game;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.uy.ygsb.R;
import com.uy.ygsb.Activity.GameActivity;
import com.uy.ygsb.Data.Battles;
import com.uy.ygsb.Pojo.Battle;
import com.uy.ygsb.Pojo.Monster;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Pojo.Role;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Ui.Dialog.GameDialog;

public class GameBattle {
	private Context context;
	private static GameBattle gameBattle;
	private GameDialog battleDialog;
	private Response response;
	private int attackType =1 ; //1是打人，2是打怪
	private Monster monster; //攻击的怪物
	private Role role;//攻击的人
	private TextView battleInfo;
	private List<Battle> battleList = new ArrayList<Battle>();
	
	public GameBattle(Context context) {
		this.context = context;
		this.battleDialog = new GameDialog(this.context);
		this.battleDialog.setTitle("战斗");
		this.battleDialog.setOkVisibility(View.GONE);
		this.battleDialog.setCancelVisibility(View.GONE);
		
		this.battleInfo = new TextView(this.context);
		this.battleInfo.setGravity(Gravity.CENTER);
		this.battleInfo.setPadding(10, 10, 10, 10);
		this.battleInfo.setTextColor(this.context.getResources().getColor(R.color.orange));
		this.battleDialog.setContentView(this.battleInfo);
		
		this.battleDialog.show();
	}

	public static GameBattle getInstance(Context context,Monster monster) {
		if (gameBattle == null) {
			gameBattle = new GameBattle(context);
		}
		gameBattle.setAttackType(2);
		gameBattle.setMonster(monster);
		gameBattle.fetchData();
		if (!gameBattle.battleDialog.isShowing()) {
			gameBattle.battleDialog.show();
		}
		return gameBattle;
	}
	
	public static GameBattle getInstance(Context context,Role role) {
		if (gameBattle == null) {
			gameBattle = new GameBattle(context);
		}
		gameBattle.setAttackType(1);
		gameBattle.setRole(role);
		gameBattle.fetchData();
		if (!gameBattle.battleDialog.isShowing()) {
			gameBattle.battleDialog.show();
		}
		return gameBattle;
	}

	// 处理返回数据
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_FAIL:
				((GameActivity) context).showErr();
				break;
			case Constant.MSG_SUCCESS:
				if (response.getCode() == 1) {
					battleList = (List<Battle>) response.getData();
					for(Battle battle:battleList){
						battleInfo.append(Html.fromHtml(battle.getInfo()+"<br />"));
					}
				}else{
					GameToast.makeText(context, response.getMsg(),GameToast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};

	public void fetchData() {
		new Thread(new Runnable() {
			public void run() {
				try {
					if(attackType==2){
						response= Battles.attack(((GameActivity)context).auth,monster.getId(),attackType);
					}else{
						response= Battles.attack(((GameActivity)context).auth,role.getId(),attackType);
					}
					mHandler.sendEmptyMessage(Constant.MSG_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void setAttackType(int attackType) {
		this.attackType = attackType;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}


	public void setRole(Role role) {
		this.role = role;
	}


}