package com.uy.ygsb.Ui.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import com.uy.ygsb.R;

public class GameBaseDialog extends Dialog {
	private Context context;
	private double widthScale = 0.8;

	public GameBaseDialog(Context context) {
		super(context, R.style.dialog);
		this.context = context;
		this.setWidth(this.widthScale);
	}

	public void setWidth(double widthScale) {
		Display disp = ((WindowManager) this.context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		LayoutParams params = getWindow().getAttributes();
		params.width = (int) (disp.getWidth() * widthScale);
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);
	}

	public void setHeight(double heightScale) {
		Display disp = ((WindowManager) this.context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		LayoutParams params = getWindow().getAttributes();
		params.height = (int) (disp.getHeight() * heightScale);
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);
	}

}