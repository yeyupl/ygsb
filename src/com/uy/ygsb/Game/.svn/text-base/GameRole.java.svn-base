package com.uy.ygsb.Game;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.uy.ygsb.R;
import com.uy.ygsb.Activity.GameActivity;
import com.uy.ygsb.Data.Bags;
import com.uy.ygsb.Pojo.Item;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Pojo.Role;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Ui.Dialog.GameDialog;

public class GameRole implements OnClickListener {
	private Context context;
	private static GameRole gameRole;
	private List<Item> itemList = new ArrayList<Item>();
	private GameDialog roleDialog;
	private Response response;
	private int bagId = 0;
	private TextView roleName, roleLevel, roleCamp, roleJob, rolePoints,
			rolePower, roleMind, roleAgile, roleEnergy;
	private Button btnReset;
	private ImageButton btnPower, btnMind, btnAgile;

	public GameRole(Context context) {
		this.context = context;

		this.roleDialog = new GameDialog(this.context);
		this.roleDialog.setTitle(R.string.text_role);
		this.roleDialog.setOkText(R.string.btn_close);
		this.roleDialog.setCancelVisibility(View.GONE);
		this.roleDialog.setWidth(1.0);
		this.roleDialog.setContentView(R.layout.role);
		this.roleDialog.show();

		this.roleName = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_name);
		this.roleLevel = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_level);
		this.roleCamp = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_camp);
		this.roleJob = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_job);
		this.rolePoints = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_points);
		this.rolePower = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_power);
		this.roleMind = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_mind);
		this.roleAgile = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_agile);
		this.roleEnergy = (TextView) this.roleDialog
				.findViewById(R.id.role_detail_energy);

		this.btnReset = (Button) this.roleDialog
				.findViewById(R.id.btn_points_reset);
		this.btnReset.setOnClickListener(this);

		this.btnPower = (ImageButton) this.roleDialog
				.findViewById(R.id.btn_add_power);
		this.btnPower.setOnClickListener(this);

		this.btnMind = (ImageButton) this.roleDialog
				.findViewById(R.id.btn_add_mind);
		this.btnMind.setOnClickListener(this);

		this.btnAgile = (ImageButton) this.roleDialog
				.findViewById(R.id.btn_add_agile);
		this.btnAgile.setOnClickListener(this);

		this.initRoleInfo();

		this.fetchData();
	}

	public static GameRole getInstance(Context context) {
		if (gameRole == null) {
			gameRole = new GameRole(context);
		}
		if (!gameRole.roleDialog.isShowing()) {
			gameRole.roleDialog.show();
		}
		return gameRole;
	}

	private void initRoleInfo() {
		Role role = ((GameActivity) context).gameApp.getRoleInfo();
		this.roleName.setText(role.getName());
		this.roleLevel.setText("Lv " + role.getLevel());
		this.roleCamp.setText(role.getCampName());
		this.roleJob.setText(role.getJobName());
		this.rolePoints.setText(role.getAttrPoints() + "");
		this.rolePower.setText(role.getPower() + "");
		this.roleMind.setText(role.getMind() + "");
		this.roleAgile.setText(role.getAgile() + "");
		this.roleEnergy.setText(role.getEnergy() + "");
	}

	@Override
	public void onClick(View v) {

	}

	// 处理返回数据
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_FAIL:
				((GameActivity) context).showErr();
				break;
			case Constant.MSG_BAG_GET_SUCCESS:
				if (response.getCode() == 1) {
					itemList = (List<Item>) response.getData();
				} else {
					GameToast.makeText(context, response.getMsg(),
							GameToast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};

	public void fetchData() {
		// 请求装备数据
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Bags
							.getBag(((GameActivity) context).auth, bagId);
					mHandler.sendEmptyMessage(Constant.MSG_BAG_GET_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}
}