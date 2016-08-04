package com.uy.ygsb.Ui;

import com.uy.ygsb.R;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameToast extends Toast {
	private View view;
	private TextView toast_msg;

	public GameToast(Context context) {
		super(context);
		this.view = LayoutInflater.from(context).inflate(R.layout.toast, null);
		this.toast_msg = (TextView) this.view.findViewById(R.id.toast_msg);
		this.setView(view);
	}

	public static GameToast makeText(Context context, int resId, int duration) {
		GameToast toast = new GameToast(context);
		toast.setText(resId);
		toast.setDuration(duration);
		return toast;
	}

	public static GameToast makeText(Context context, CharSequence text,
			int duration) {
		GameToast toast = new GameToast(context);
		toast.setText(text);
		toast.setDuration(duration);
		return toast;
	}

	@Override
	public void setText(int resId) {
		this.toast_msg.setText(resId);
	}

	@Override
	public void setText(CharSequence text) {
		this.toast_msg.setText(text);
	}
}