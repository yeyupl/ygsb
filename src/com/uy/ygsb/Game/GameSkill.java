package com.uy.ygsb.Game;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.uy.ygsb.R;
import com.uy.ygsb.Activity.GameActivity;
import com.uy.ygsb.Ui.Dialog.GameDialog;

public class GameSkill implements OnClickListener {
	private Context context;
	private static GameSkill gameSkill;
	private GameDialog skillDialog, skillDetailDialog;
	private ImageView skill_1, skill_2, skill_3, skill_4, skill_5, skill_6;

	public GameSkill(Context context) {
		this.context = context;

		this.skillDialog = new GameDialog(this.context);
		this.skillDialog.setTitle(R.string.text_skill);
		this.skillDialog.setOkText(R.string.btn_close);
		this.skillDialog.setCancelVisibility(View.GONE);
		this.skillDialog.setWidth(1.0);
		this.skillDialog.setContentView(R.layout.skill);
		this.skillDialog.show();

		this.skill_1 = (ImageView) this.skillDialog
				.findViewById(R.id.role_skill_1);
		this.skill_1.setOnClickListener(this);
		this.skill_2 = (ImageView) this.skillDialog
				.findViewById(R.id.role_skill_2);
		this.skill_2.setOnClickListener(this);
		this.skill_3 = (ImageView) this.skillDialog
				.findViewById(R.id.role_skill_3);
		this.skill_3.setOnClickListener(this);
		this.skill_4 = (ImageView) this.skillDialog
				.findViewById(R.id.role_skill_4);
		this.skill_4.setOnClickListener(this);
		this.skill_5 = (ImageView) this.skillDialog
				.findViewById(R.id.role_skill_5);
		this.skill_5.setOnClickListener(this);
		this.skill_6 = (ImageView) this.skillDialog
				.findViewById(R.id.role_skill_6);
		this.skill_6.setOnClickListener(this);
	}

	public static GameSkill getInstance(Context context) {
		if (gameSkill == null) {
			gameSkill = new GameSkill(context);
		}
		if (!gameSkill.skillDialog.isShowing()) {
			gameSkill.skillDialog.show();
		}
		return gameSkill;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.role_skill_1:
			break;
		case R.id.role_skill_2:
			break;
		case R.id.role_skill_3:
			break;
		case R.id.role_skill_4:
			break;
		case R.id.role_skill_5:
			break;
		case R.id.role_skill_6:
			break;
		}
		this.skillDetailDialog = new GameDialog(this.context);
		this.skillDetailDialog.setTitle(R.string.text_skill_detail);
		this.skillDetailDialog.setContentView(R.layout.skill_detail);
		this.skillDetailDialog.setCancelVisibility(View.GONE);
		this.skillDetailDialog.setWidth(0.7);
		this.skillDetailDialog.show();

	}

}