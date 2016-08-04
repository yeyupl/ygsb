package com.uy.ygsb.Ui;

import com.uy.ygsb.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.widget.ImageView;

public class GameCellView extends ImageView {
	private Context context;
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Bitmap mBitmap;
	private String name;
	private float[] colorArray = new float[] { 1, 0, 0, 0, 100, 0, 1, 0, 0,
			100, 0, 0, 1, 0, 100, 0, 0, 0, 1, 0 };

	public GameCellView(Context context, Bitmap bitmap, String name) {
		super(context);
		this.context = context;
		this.mBitmap = bitmap;
		this.name = name;
		this.setMinimumHeight(this.mBitmap.getHeight() + 20);
		this.setMinimumWidth(this.mBitmap.getWidth());
		this.setClickable(true);
		this.setFocusable(true);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		this.mPaint.setColorFilter(null);
		canvas.drawBitmap(this.mBitmap, 0, 20, this.mPaint);
		if (this.isPressed() || this.isFocused()) {
			ColorMatrix cm = new ColorMatrix(); // 设置颜色矩阵
			cm.set(this.colorArray); // 颜色滤镜，将颜色矩阵应用于图片
			this.mPaint.setColorFilter(new ColorMatrixColorFilter(cm)); // 绘图
			canvas.drawBitmap(this.mBitmap, 0, 20, this.mPaint);
		}
		this.mPaint.setColorFilter(null);
		this.mPaint.setTextSize(14);
		this.mPaint.setFakeBoldText(true);
		drawText(this.name, canvas, this.mPaint,
				(int) (this.getMeasuredWidth() - this.mPaint
						.measureText(this.name)) / 2, 20, this.context
						.getResources().getColor(R.color.orange), Color.WHITE);
	}

	/***
	 * 绘制带有边框的文字
	 * 
	 * @param strMsg
	 *            ：绘制内容
	 * @param canvas
	 *            ：画布
	 * @param paint
	 *            ：画笔
	 * @param setx
	 *            ：X轴起始坐标
	 * @param sety
	 *            ：Y轴的起始坐标
	 * @param fg
	 *            ：前景色
	 * @param bg
	 *            ：背景色
	 */
	public void drawText(String strMsg, Canvas canvas, Paint paint, int setx,
			int sety, int fg, int bg) {
		paint.setColor(bg);
		canvas.drawText(strMsg, setx + 1, sety, paint);
		canvas.drawText(strMsg, setx, sety - 1, paint);
		canvas.drawText(strMsg, setx, sety + 1, paint);
		canvas.drawText(strMsg, setx - 1, sety, paint);
		paint.setColor(fg);
		canvas.drawText(strMsg, setx, sety, paint);
		canvas.restore();
	}

}