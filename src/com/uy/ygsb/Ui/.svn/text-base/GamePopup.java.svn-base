package com.uy.ygsb.Ui;

import com.uy.ygsb.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class GamePopup {
	private View view;
	private LinearLayout actionView;
	private PopupWindow pw;
	private ImageView arrowUp, arrowDown;
	private Context context;

	public GamePopup(Context context) {
		this.context = context;
		this.view = LayoutInflater.from(context).inflate(R.layout.popup, null);
		this.pw = new PopupWindow(this.view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		this.pw.setTouchable(true);
		this.pw.setFocusable(true);
		this.pw.setBackgroundDrawable(new BitmapDrawable());
		this.arrowUp = (ImageView) this.view.findViewById(R.id.popup_arrow_up);
		this.arrowDown = (ImageView) this.view
				.findViewById(R.id.popup_arrow_down);
		this.actionView = (LinearLayout) this.view
				.findViewById(R.id.popup_action);
	}

	public GamePopup setContentView(int resId) {
		this.actionView.removeAllViews();
		View v = LayoutInflater.from(this.context).inflate(resId, null);
		this.actionView.addView(v, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		return this;
	}

	public GamePopup setContentView(View v) {
		this.actionView.removeAllViews();
		this.actionView.addView(v, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		return this;
	}

	public GamePopup setMessage(int resId) {
		this.actionView.removeAllViews();
		TextView tv = new TextView(this.context);
		tv.setText(resId);
		tv.setTextColor(Color.WHITE);
		this.actionView.addView(tv, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		return this;
	}

	public GamePopup setMessage(CharSequence str) {
		this.actionView.removeAllViews();
		TextView tv = new TextView(this.context);
		tv.setText(str);
		tv.setTextColor(Color.WHITE);
		this.actionView.addView(tv, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		return this;
	}

	public View findViewById(int id) {
		return this.actionView.findViewById(id);
	}

	public void show(View v) {
		int height = this.view.getMeasuredHeight();
		int offsetX = v.getWidth() / 2 - this.view.getWidth() / 2;
		int offsetY = 0;
		if (height > 0 && v.getTop() >= height) {
			this.arrowUp.setVisibility(View.GONE);
			this.arrowDown.setVisibility(View.VISIBLE);
			offsetY = (height + v.getHeight()) * -1;
		} else {
			this.arrowUp.setVisibility(View.VISIBLE);
			this.arrowDown.setVisibility(View.GONE);
		}
		this.pw.showAsDropDown(v, offsetX, offsetY);
	}

	public void dismiss() {
		this.pw.dismiss();
	}

	public void update() {
		this.pw.update();
	}
}