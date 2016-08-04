package com.uy.ygsb.Activity;

import com.uy.ygsb.R;
import com.uy.ygsb.Game.GameBag;
import com.uy.ygsb.Game.GameBigMap;
import com.uy.ygsb.Game.GameChat;
import com.uy.ygsb.Game.GameMap;
import com.uy.ygsb.Game.GameRole;
import com.uy.ygsb.Game.GameShop;
import com.uy.ygsb.Game.GameSkill;
import com.uy.ygsb.Ui.Dialog.GameDialog;
import com.uy.ygsb.Ui.Dialog.GameDialogEventListener;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends BaseActivity implements OnClickListener {
	private ImageButton toolbarMap, toolbarRole, toolbarBag, toolbarSkill,
			toolbarFriend, toolbarFamily, toolbarTask, toolbarPay, toolbarShop;
	private ImageView roleAvatar, roleCamp;
	private TextView roleLevel, roleName, roleHpVal, roleMpVal, roleGold,
			roleSilver, roleCopper;
	private ProgressBar roleHp, roleMp;
	private GameMap gameMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		this.roleAvatar = (ImageView) findViewById(R.id.game_role_avatar);
		this.roleCamp = (ImageView) findViewById(R.id.game_role_camp);

		this.roleLevel = (TextView) findViewById(R.id.game_role_level);
		this.roleName = (TextView) findViewById(R.id.game_role_name);
		this.roleHpVal = (TextView) findViewById(R.id.game_role_hp_val);
		this.roleMpVal = (TextView) findViewById(R.id.game_role_mp_val);
		this.roleGold = (TextView) findViewById(R.id.game_role_gold);
		this.roleSilver = (TextView) findViewById(R.id.game_role_silver);
		this.roleCopper = (TextView) findViewById(R.id.game_role_copper);

		this.roleHp = (ProgressBar) findViewById(R.id.game_role_hp);
		this.roleMp = (ProgressBar) findViewById(R.id.game_role_mp);

		this.toolbarMap = (ImageButton) findViewById(R.id.toolbar_map);
		this.toolbarMap.setOnClickListener(this);

		this.toolbarRole = (ImageButton) findViewById(R.id.toolbar_role);
		this.toolbarRole.setOnClickListener(this);

		this.toolbarBag = (ImageButton) findViewById(R.id.toolbar_bag);
		this.toolbarBag.setOnClickListener(this);

		this.toolbarSkill = (ImageButton) findViewById(R.id.toolbar_skill);
		this.toolbarSkill.setOnClickListener(this);

		this.toolbarFriend = (ImageButton) findViewById(R.id.toolbar_friend);
		this.toolbarFriend.setOnClickListener(this);

		this.toolbarFamily = (ImageButton) findViewById(R.id.toolbar_family);
		this.toolbarFamily.setOnClickListener(this);

		this.toolbarTask = (ImageButton) findViewById(R.id.toolbar_task);
		this.toolbarTask.setOnClickListener(this);

		this.toolbarPay = (ImageButton) findViewById(R.id.toolbar_pay);
		this.toolbarPay.setOnClickListener(this);

		this.toolbarShop = (ImageButton) findViewById(R.id.toolbar_shop);
		this.toolbarShop.setOnClickListener(this);

		this.setRoleInfo();

		this.gameMap = new GameMap(this);
		this.gameMap.render();

		new GameChat(this);
	}

	private void setRoleInfo() {
		this.roleName.setText(gameApp.getRoleInfo().getName());
		this.roleLevel.setText(gameApp.getRoleInfo().getLevel() + "");
		this.roleHpVal.setText(gameApp.getRoleInfo().getHp() + "/"
				+ gameApp.getRoleInfo().getMaxHp());
		this.roleMpVal.setText(gameApp.getRoleInfo().getMp() + "/"
				+ gameApp.getRoleInfo().getMaxMp());
		this.roleGold.setText(gameApp.getRoleInfo().getGold() + "");
		this.roleSilver.setText(gameApp.getRoleInfo().getSilver() + "");
		this.roleCopper.setText(gameApp.getRoleInfo().getCopper() + "");

		this.roleHp.setMax(gameApp.getRoleInfo().getMaxHp());
		this.roleHp.setProgress(gameApp.getRoleInfo().getHp());

		this.roleMp.setMax(gameApp.getRoleInfo().getMaxMp());
		this.roleMp.setProgress(gameApp.getRoleInfo().getMp());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.toolbar_map:
			GameBigMap.getInstance(this);
			break;
		case R.id.toolbar_role:
			GameRole.getInstance(this);
			break;
		case R.id.toolbar_bag:
			GameBag.getInstance(this);
			break;
		case R.id.toolbar_skill:
			GameSkill.getInstance(this);
			break;
		case R.id.toolbar_friend:
			break;
		case R.id.toolbar_family:
			break;
		case R.id.toolbar_task:
			break;
		case R.id.toolbar_pay:
			break;
		case R.id.toolbar_shop:
			GameShop.getInstance(this);
			break;
		}

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			final GameDialog exitDialog = new GameDialog(this);
			exitDialog.setTitle(R.string.text_exit);
			exitDialog.setMessage(R.string.text_exit_msg);
			exitDialog.setListener(new GameDialogEventListener() {
				@Override
				public void onDialogOk() {
					gameApp.closeSocket();
					exitDialog.dismiss();
					GameActivity.this.finish();
				}

				@Override
				public void onDialogCancel() {
					exitDialog.dismiss();
				}
			});
			exitDialog.setWidth(0.7);
			exitDialog.show();
		}
		return false;
	}
}