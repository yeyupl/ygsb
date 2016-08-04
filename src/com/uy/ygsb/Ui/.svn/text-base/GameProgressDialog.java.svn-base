package com.uy.ygsb.Ui;

import com.uy.ygsb.R;

import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;

public class GameProgressDialog extends Dialog {
	private ProgressBar loadingView;
	private static GameProgressDialog gameProgressDialog;

	public GameProgressDialog(Context context) {
		super(context, R.style.dialog);
		this.loadingView = new ProgressBar(context);
		this.setContentView(this.loadingView);
		this.setCancelable(false);
	}

	public static GameProgressDialog getInstance(Context context) {
		if (gameProgressDialog == null) {
			gameProgressDialog = new GameProgressDialog(context);
		}
		return gameProgressDialog;
	}

}