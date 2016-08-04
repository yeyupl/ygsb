package com.uy.ygsb.Activity;

import com.uy.ygsb.GameApp;
import com.uy.ygsb.R;
import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Util.SoundManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class BaseActivity extends Activity {
	public GameApp gameApp;
	public Auth auth;
	public boolean isMusic = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		this.gameApp = (GameApp) this.getApplication();
		// 获取服务器地址和验证信息
		this.auth = this.gameApp.getAuth();
	}

	public void showErr() {
		GameToast.makeText(this, R.string.text_server_response_fail,
				GameToast.LENGTH_SHORT).show();
	}

	public void setMusic(boolean is) {
		this.isMusic = is;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (this.isMusic) {
			SoundManager.getInstance(this).playMusic();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (this.isMusic) {
			SoundManager.getInstance(this).stopMusic();
		}
	}

	public void onDestroy() {
		super.onDestroy();
	}

}