package com.uy.ygsb.Ui.Dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uy.ygsb.R;

public class GameDialog extends GameBaseDialog implements OnClickListener {
	private Context context;
	private Button btnOk, btnCancel;
	private TextView dialogTitle, dialogMsg, dialogBottomText;
	private LinearLayout dialogView;
	private GameDialogEventListener listener;
	private double widthScale = 0.8;

	public GameDialog(Context context) {
		super(context);
		super.setContentView(R.layout.dialog);
		this.context = context;

		this.setWidth(this.widthScale);

		this.dialogTitle = (TextView) findViewById(R.id.dialog_title);
		this.dialogTitle.getPaint().setFakeBoldText(true);

		this.dialogMsg = (TextView) findViewById(R.id.dialog_msg);

		this.dialogBottomText = (TextView) findViewById(R.id.dialog_bottom_text);
		this.dialogBottomText.getPaint().setFakeBoldText(true);

		this.dialogView = (LinearLayout) findViewById(R.id.dialog_view);

		this.btnOk = (Button) findViewById(R.id.btn_ok);
		this.btnOk.setOnClickListener(this);
		this.btnCancel = (Button) findViewById(R.id.btn_cancel);
		this.btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			if (this.listener == null) {
				this.dismiss();
			} else {
				this.listener.onDialogOk();
			}
			break;
		case R.id.btn_cancel:
			if (this.listener == null) {
				this.dismiss();
			} else {
				this.listener.onDialogCancel();
			}
			break;
		}
	}

	public void setListener(GameDialogEventListener gameDialogEventListener) {
		this.listener = gameDialogEventListener;
	}

	public void setMessage(int resId) {
		this.dialogMsg.setText(resId);
	}

	public void setMessage(CharSequence title) {
		this.dialogMsg.setText(title);
	}

	@Override
	public void setTitle(int resId) {
		this.dialogTitle.setText(resId);
	}

	@Override
	public void setTitle(CharSequence title) {
		this.dialogTitle.setText(title);
	}

	@Override
	public void setContentView(int layoutResID) {
		this.dialogView.removeAllViews();
		View view = LayoutInflater.from(this.context)
				.inflate(layoutResID, null);
		this.dialogView.addView(view);
	}

	@Override
	public void setContentView(View view) {
		this.dialogView.removeAllViews();
		this.dialogView.addView(view);
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		this.dialogView.removeAllViews();
		this.dialogView.addView(view, params);
	}

	public void setOkEnabled(boolean isEnabled) {
		this.btnOk.setEnabled(isEnabled);
	}

	public void setCancelEnabled(boolean isEnabled) {
		this.btnCancel.setEnabled(isEnabled);
	}

	public void setOkVisibility(int visibility) {
		this.btnOk.setVisibility(visibility);
	}

	public void setCancelVisibility(int visibility) {
		this.btnCancel.setVisibility(visibility);
	}

	public void setOkText(int resId) {
		this.btnOk.setText(resId);
	}

	public void setOkText(CharSequence text) {
		this.btnOk.setText(text);
	}

	public void setCancelText(int resId) {
		this.btnCancel.setText(resId);
	}

	public void setCancelText(CharSequence text) {
		this.btnCancel.setText(text);
	}

	public void setBotomText(int resId) {
		this.dialogBottomText.setText(resId);
	}

	public void setBotomText(CharSequence text) {
		this.dialogBottomText.setText(text);
	}
}