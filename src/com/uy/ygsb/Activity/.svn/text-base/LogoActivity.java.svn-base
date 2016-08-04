package com.uy.ygsb.Activity;

import com.uy.ygsb.R;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Util.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoActivity extends BaseActivity implements Runnable {
	private Handler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);

		if (!Utils.isNetwork(this)) {
			GameToast.makeText(this, R.string.text_network_fail_msg,
					GameToast.LENGTH_SHORT).show();
		}
		this.handler = new Handler();
		this.handler.postDelayed(this, 3000L);

		this.setMusic(false);
	}

	@Override
	public void run() {
		this.finish();
		Intent intent = new Intent(this, MainActivity.class);
		this.startActivity(intent);
	}
}