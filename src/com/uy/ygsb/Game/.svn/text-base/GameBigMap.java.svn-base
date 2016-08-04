package com.uy.ygsb.Game;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.uy.ygsb.R;
import com.uy.ygsb.Ui.Dialog.GameBaseDialog;

public class GameBigMap implements OnClickListener {
	private Context context;
	private static GameBigMap gameBitMap;
	private GameBaseDialog bigMapDialog;
	private ImageButton btnClose;

	public GameBigMap(final Context context) {
		this.context = context;
		this.bigMapDialog = new GameBaseDialog(this.context);
		this.bigMapDialog.setContentView(R.layout.bigmap);
		this.bigMapDialog.setWidth(1.0);
		this.bigMapDialog.show();

		this.btnClose = (ImageButton) this.bigMapDialog
				.findViewById(R.id.btn_bigmap_close);
		this.btnClose.setOnClickListener(this);
	}

	public static GameBigMap getInstance(Context context) {
		if (gameBitMap == null) {
			gameBitMap = new GameBigMap(context);
		}
		if (!gameBitMap.bigMapDialog.isShowing()) {
			gameBitMap.bigMapDialog.show();
		}
		return gameBitMap;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_bigmap_close:
			this.bigMapDialog.dismiss();
			break;
		}
	}

}