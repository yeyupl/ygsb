package com.uy.ygsb.Activity;

import java.util.ArrayList;
import java.util.List;

import com.uy.ygsb.R;
import com.uy.ygsb.Data.Roles;
import com.uy.ygsb.Data.Servers;
import com.uy.ygsb.Data.Users;
import com.uy.ygsb.Game.Constant;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Pojo.Role;
import com.uy.ygsb.Pojo.Server;
import com.uy.ygsb.Pojo.User;
import com.uy.ygsb.Ui.GameProgressDialog;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Ui.Dialog.GameDialog;
import com.uy.ygsb.Ui.Dialog.GameDialogEventListener;
import com.uy.ygsb.Util.Utils;
import com.uy.ygsb.View.RoleListAdapter;
import com.uy.ygsb.View.ServerListAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnClickListener {
	private ImageButton btnReg, btnLogin, btnSetting, btnHelp, btnExit;
	private Response response;
	private GameDialog regDialog, loginDialog, serverDialog, createRoleDialog;
	private GridView serverListView, roleListView;
	private ServerListAdapter serverListAdapter;
	private RoleListAdapter roleListAdapter;
	private List<Server> serverList = new ArrayList<Server>();
	private List<Role> roleList = new ArrayList<Role>();
	private int gender = 2, job = 1002;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.btnReg = (ImageButton) findViewById(R.id.btn_reg);
		this.btnReg.setOnClickListener(this);
		this.btnLogin = (ImageButton) findViewById(R.id.btn_login);
		this.btnLogin.setOnClickListener(this);
		this.btnSetting = (ImageButton) findViewById(R.id.btn_setting);
		this.btnSetting.setOnClickListener(this);
		this.btnHelp = (ImageButton) findViewById(R.id.btn_help);
		this.btnHelp.setOnClickListener(this);
		this.btnExit = (ImageButton) findViewById(R.id.btn_exit);
		this.btnExit.setOnClickListener(this);

	}

	// 处理返回数据
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_FAIL:
				showErr();
				break;
			case Constant.MSG_REG_SUCCESS:
				GameToast.makeText(MainActivity.this, response.getMsg(),
						GameToast.LENGTH_SHORT).show();
				if (response.getCode() == 1) {
					regDialog.dismiss();
					gameApp.setUserInfo((User) response.getData());
					onLoginCheck();
				}
				break;
			case Constant.MSG_LOGIN_SUCCESS:
				if (response.getCode() == 1) {
					loginDialog.dismiss();
					gameApp.setUserInfo((User) response.getData());
					onLoginCheck();
				} else {
					GameToast.makeText(MainActivity.this, response.getMsg(),
							GameToast.LENGTH_SHORT).show();
				}
				break;
			case Constant.MSG_SERVER_LIST_SUCCESS:
				if (response.getCode() == 1) {
					serverList = (List<Server>) response.getData();
					if (serverList.size() > 0) {
						serverListAdapter.setServerList(serverList);
						serverListAdapter.notifyDataSetChanged();
					} else {
						serverDialog.setCancelable(true);
					}
				} else {
					serverDialog.setCancelable(true);
				}
				break;

			case Constant.MSG_ROLE_LIST_SUCCESS:
				if (response.getCode() == 1) {
					roleList = (List<Role>) response.getData();
					int roleNum = roleList.size();
					if (roleNum > 0) {
						ImageView noRole = (ImageView) serverDialog
								.findViewById(R.id.no_role);
						noRole.setVisibility(View.GONE);
						roleListView.setVisibility(View.VISIBLE);
						roleListAdapter.setRoleList(roleList);
						roleListAdapter.notifyDataSetChanged();
					}
					if (roleNum < Constant.MAX_ROLE_NUM) {
						serverDialog.setOkText(R.string.btn_create);
						serverDialog.setOkVisibility(View.VISIBLE);
						serverDialog.setListener(new GameDialogEventListener() {
							@Override
							public void onDialogOk() {
								onCreateRolesShow();
							}

							@Override
							public void onDialogCancel() {

							}

						});
					} else {
						serverDialog.setOkVisibility(View.GONE);
					}
				} else {
					GameToast.makeText(MainActivity.this, response.getMsg(),
							GameToast.LENGTH_SHORT).show();
				}
				break;
			case Constant.MSG_ROLE_CREATE_SUCCESS:
				GameToast.makeText(MainActivity.this, response.getMsg(),
						GameToast.LENGTH_SHORT).show();
				if (response.getCode() == 1) {
					createRoleDialog.dismiss();
					// 重新加载角色列表
					onShowRoleList();
				}
				break;

			}
			GameProgressDialog.getInstance(MainActivity.this).dismiss();
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg:
			this.onRegAgreementShow();
			break;
		case R.id.btn_login:
			this.onLoginShow();
			break;
		case R.id.btn_setting:
			final GameDialog settingDialog = new GameDialog(this);
			settingDialog.setTitle(R.string.text_setting);
			settingDialog.setMessage(R.string.text_setting);
			settingDialog.setWidth(0.7);
			settingDialog.show();
			break;
		case R.id.btn_help:
			final GameDialog helpDialog = new GameDialog(this);
			helpDialog.setTitle(R.string.text_help);
			helpDialog.setMessage(R.string.text_help);
			helpDialog.setWidth(0.7);
			helpDialog.show();
			break;
		case R.id.btn_exit:
			this.onExit();
			break;
		}

	}

	private void onExit() {
		final GameDialog exitDialog = new GameDialog(this);
		exitDialog.setTitle(R.string.text_exit);
		exitDialog.setMessage(R.string.text_exit_msg);
		exitDialog.setListener(new GameDialogEventListener() {
			@Override
			public void onDialogOk() {
				exitDialog.dismiss();
				MainActivity.this.finish();
			}

			@Override
			public void onDialogCancel() {
				exitDialog.dismiss();
			}
		});
		exitDialog.setWidth(0.7);
		exitDialog.show();
	}

	// 用户协议
	private void onRegAgreementShow() {
		final GameDialog agreementDialog = new GameDialog(this);
		agreementDialog.setTitle(R.string.text_reg_agreement);
		agreementDialog.setMessage(R.string.text_reg_agreement_content);
		agreementDialog.setCancelText(R.string.btn_refuse);
		agreementDialog.setOkText(R.string.btn_accept);
		agreementDialog.setListener(new GameDialogEventListener() {
			@Override
			public void onDialogOk() {
				agreementDialog.dismiss();
				onRegShow();
			}

			@Override
			public void onDialogCancel() {
				agreementDialog.dismiss();
			}
		});
		agreementDialog.show();
	}

	// 登陆界面
	private void onLoginShow() {
		this.loginDialog = new GameDialog(this);
		this.loginDialog.setTitle(R.string.text_login);
		this.loginDialog.setOkText(R.string.btn_login);
		this.loginDialog.setCancelText(R.string.btn_reg);
		this.loginDialog.setContentView(R.layout.login);
		this.loginDialog.setListener(new GameDialogEventListener() {
			@Override
			public void onDialogOk() {
				onLoginSubmit();
			}

			@Override
			public void onDialogCancel() {
				loginDialog.dismiss();
				onRegAgreementShow();
			}
		});
		this.loginDialog.show();
	}

	// 登陆
	private void onLoginCheck() {
		if (this.gameApp.isLogin()) {
			this.onSelectServerShow();
		} else {
			this.onLoginShow();
		}
	}

	// 选服
	private void onSelectServerShow() {
		this.serverDialog = new GameDialog(this);
		this.serverDialog.setTitle(R.string.text_select_server);
		this.serverDialog.setOkVisibility(View.GONE);
		this.serverDialog.setCancelVisibility(View.GONE);
		this.serverDialog.setWidth(1.0);
		this.serverDialog.setContentView(R.layout.select_server);
		this.serverDialog.setCancelable(false);
		this.serverDialog.show();

		this.serverListView = (GridView) serverDialog
				.findViewById(R.id.server_list);
		this.serverListAdapter = new ServerListAdapter(this, this.serverList);
		this.serverListView.setAdapter(this.serverListAdapter);
		this.serverListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Server server = serverListAdapter.getServerList().get(position);
				if (server.getId() == 0 || server.getUrl().equals("")) {
					return;
				}
				if (server.getStatus() == 1) {
					GameToast.makeText(MainActivity.this,
							R.string.text_server_unopened,
							GameToast.LENGTH_SHORT).show();
					return;
				}

				// 把当前服务器信息存到全局变量里，用作后续请求
				MainActivity.this.gameApp.setServerInfo(server);
				onShowRoleList();
			}

		});

		// 请求服务器列表
		GameProgressDialog.getInstance(this).show();
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Servers.getServerList(gameApp.getAuth());
					mHandler.sendEmptyMessage(Constant.MSG_SERVER_LIST_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 加载角色列表
	private void onShowRoleList() {
		this.roleListView = (GridView) this.serverDialog
				.findViewById(R.id.role_list);
		this.roleListAdapter = new RoleListAdapter(this, this.roleList);
		this.roleListView.setAdapter(this.roleListAdapter);
		this.roleListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Role role = roleListAdapter.getRoleList().get(position);
				if (role.getId() == 0) {
					return;
				}

				// 把当前角色信息存到全局变量里求
				MainActivity.this.gameApp.setRoleInfo(role);

				// 进入游戏
				MainActivity.this.serverDialog.dismiss();
				Intent intent = new Intent(MainActivity.this,
						LoadingActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				MainActivity.this.finish();
			}

		});

		// 请求服务器角色列表
		GameProgressDialog.getInstance(this).show();
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Roles.getRoleList(gameApp.getAuth());
					mHandler.sendEmptyMessage(Constant.MSG_ROLE_LIST_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 创建角色
	private void onCreateRolesShow() {
		this.createRoleDialog = new GameDialog(this);
		this.createRoleDialog.setTitle(R.string.text_create_role);
		this.createRoleDialog.setOkVisibility(View.GONE);
		this.createRoleDialog.setCancelVisibility(View.GONE);
		this.createRoleDialog.setWidth(0.9);
		this.createRoleDialog.setContentView(R.layout.create_role);
		this.createRoleDialog.setBotomText(R.string.job_mage);
		this.createRoleDialog.show();

		final ImageView jobAvatar = (ImageView) this.createRoleDialog
				.findViewById(R.id.job_avatar);
		final TextView jobIntro = (TextView) this.createRoleDialog
				.findViewById(R.id.job_intro);
		final EditText roleName = (EditText) this.createRoleDialog
				.findViewById(R.id.role_name);

		final ImageButton btnJobMars = (ImageButton) this.createRoleDialog
				.findViewById(R.id.btn_job_mars);
		final ImageButton btnJobMage = (ImageButton) this.createRoleDialog
				.findViewById(R.id.btn_job_mage);
		final ImageButton btnJobBeast = (ImageButton) this.createRoleDialog
				.findViewById(R.id.btn_job_beast);
		final ImageButton btnJobFairy = (ImageButton) this.createRoleDialog
				.findViewById(R.id.btn_job_fairy);
		btnJobMars.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				job = 1001;
				jobIntro.setText(getResources().getString(
						R.string.job_mars_intro));
				createRoleDialog.setBotomText(R.string.job_mars);
			}

		});
		btnJobMage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				job = 1002;
				jobIntro.setText(getResources().getString(
						R.string.job_mage_intro));
				createRoleDialog.setBotomText(R.string.job_mage);
			}

		});
		btnJobBeast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				job = 2001;
				jobIntro.setText(getResources().getString(
						R.string.job_beast_intro));
				createRoleDialog.setBotomText(R.string.job_beast);
			}

		});
		btnJobFairy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				job = 2002;
				jobIntro.setText(getResources().getString(
						R.string.job_fairy_intro));
				createRoleDialog.setBotomText(R.string.job_fairy);
			}

		});

		final Button btnRoleGenderMale = (Button) this.createRoleDialog
				.findViewById(R.id.btn_role_gender_male);
		final Button btnRoleGenderFemale = (Button) this.createRoleDialog
				.findViewById(R.id.btn_role_gender_female);

		btnRoleGenderMale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gender = 1;
				btnRoleGenderMale.setBackgroundResource(R.drawable.btn_pressed);
				btnRoleGenderFemale.setBackgroundResource(R.drawable.btn);
			}
		});

		btnRoleGenderFemale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gender = 2;
				btnRoleGenderFemale
						.setBackgroundResource(R.drawable.btn_pressed);
				btnRoleGenderMale.setBackgroundResource(R.drawable.btn);
			}
		});
		btnRoleGenderFemale.setBackgroundResource(R.drawable.btn_pressed);

		final Button btnRoleCreate = (Button) this.createRoleDialog
				.findViewById(R.id.btn_role_create);
		btnRoleCreate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String name = roleName.getText().toString();
				if (name.length() < 3 || name.length() > 15) {
					GameToast
							.makeText(MainActivity.this,
									R.string.text_role_name_msg,
									GameToast.LENGTH_SHORT).show();
					return;
				}
				GameProgressDialog.getInstance(MainActivity.this).show();
				new Thread(new Runnable() {
					public void run() {
						try {
							response = Roles.createRole(gameApp.getAuth(), job,
									name, gender);
							mHandler.sendEmptyMessage(Constant.MSG_ROLE_CREATE_SUCCESS);
						} catch (Exception e) {
							mHandler.sendEmptyMessage(Constant.MSG_FAIL);
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
	}

	// 提交登陆
	private void onLoginSubmit() {
		EditText login_account = (EditText) this.loginDialog
				.findViewById(R.id.login_account);
		EditText login_password = (EditText) this.loginDialog
				.findViewById(R.id.login_password);

		final String account = login_account.getText().toString();
		final String password = login_password.getText().toString();
		if (account.length() < 3) {
			GameToast.makeText(this, R.string.text_account_toast,
					GameToast.LENGTH_SHORT).show();
			return;
		}
		if (password.length() < 6) {
			GameToast.makeText(this, R.string.text_password_toast,
					GameToast.LENGTH_SHORT).show();
			return;
		}
		// 提交登陆请求
		GameProgressDialog.getInstance(this).show();
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Users.login(account, password);
					mHandler.sendEmptyMessage(Constant.MSG_LOGIN_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 注册
	private void onRegShow() {
		this.regDialog = new GameDialog(this);
		this.regDialog.setTitle(R.string.text_reg);
		this.regDialog.setOkText(R.string.btn_reg);
		this.regDialog.setCancelVisibility(View.GONE);
		this.regDialog.setContentView(R.layout.reg);
		this.regDialog.setListener(new GameDialogEventListener() {
			@Override
			public void onDialogOk() {
				MainActivity.this.onRegSubmit();
			}

			@Override
			public void onDialogCancel() {
			}
		});
		regDialog.show();
	}

	// 提交注册
	private void onRegSubmit() {
		EditText reg_account = (EditText) this.regDialog
				.findViewById(R.id.reg_account);
		EditText reg_password = (EditText) this.regDialog
				.findViewById(R.id.reg_password);
		EditText reg_confirm_password = (EditText) this.regDialog
				.findViewById(R.id.reg_confirm_password);

		final String account = reg_account.getText().toString();
		final String password = reg_password.getText().toString();
		String confirm_password = reg_confirm_password.getText().toString();

		if (account.length() < 3) {
			GameToast.makeText(this, R.string.text_account_toast,
					GameToast.LENGTH_SHORT).show();
			return;
		}
		if (password.length() < 6) {
			GameToast.makeText(this, R.string.text_password_toast,
					GameToast.LENGTH_SHORT).show();
			return;
		}
		if (!password.equals(confirm_password)) {
			GameToast.makeText(this, R.string.text_confirm_password_toast,
					GameToast.LENGTH_SHORT).show();
			return;
		}

		// 提交注册请求
		GameProgressDialog.getInstance(this).show();
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Users.reg(account, password, Utils.getModel(),
							Utils.getOs(),
							Utils.getDeviceCode(MainActivity.this),
							Utils.getResolution(MainActivity.this),
							Utils.getVerCode(MainActivity.this),
							Utils.getVerName(MainActivity.this));
					mHandler.sendEmptyMessage(Constant.MSG_REG_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onExit();
		}
		return false;
	}
}