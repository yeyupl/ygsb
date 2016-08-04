package com.uy.ygsb.Game;

import java.util.LinkedList;

import com.uy.ygsb.R;
import com.uy.ygsb.Activity.GameActivity;
import com.uy.ygsb.Pojo.ChatMsg;
import com.uy.ygsb.Util.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class GameChat implements OnClickListener {
	public Context context;
	private LinkedList<String> msgArr = new LinkedList<String>();
	private int maxMsg = 25;
	private TextView chatView;
	private ScrollView chatScrollView;
	private ImageView chatScale;
	private Button btnChatSend;
	private int chatScaleStatus = 1;

	public GameChat(Context context) {
		this.context = context;
		this.chatView = (TextView) ((GameActivity) context)
				.findViewById(R.id.chat_view);
		this.chatScrollView = (ScrollView) ((GameActivity) context)
				.findViewById(R.id.chat_scrollView);

		this.chatScale = (ImageView) ((GameActivity) context)
				.findViewById(R.id.chat_scale);
		this.chatScale.setOnClickListener(this);

		this.btnChatSend = (Button) ((GameActivity) context)
				.findViewById(R.id.btn_chat_send);
		this.btnChatSend.setOnClickListener(this);

		this.readMsg();
	}

	private Runnable mScrollToBottom = new Runnable() {
		public void run() {
			chatScrollView.fullScroll(View.FOCUS_DOWN);
		}
	};

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_SUCCESS:
				chatView.setText(Html.fromHtml(formatMsg()));
				this.post(mScrollToBottom);
				break;
			}
		}
	};

	private String formatMsg() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.msgArr.size(); i++) {
			sb.append(this.msgArr.get(i));
			if (i < this.msgArr.size() - 1) {
				sb.append("<br />");
			}
		}
		return sb.toString();

	}

	private void readMsg() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					String msg = null;
					ChatMsg chatMsg = ((GameActivity) context).gameApp
							.getSocket().read();
					switch (chatMsg.getActionId()) {
					case Constant.CHAT_ACTION_AUTH:
						if (chatMsg.getCode() == 1) {
							msg = "<font color='#FF6600'>[系]</font><font color='#008000'>连接聊天服务器成功</font>";
						} else {
							msg = "<font color='#FF6600'>[系]</font><font color='#FF0000'>连接聊天服务器失败</font>";
						}
						break;
					case Constant.CHAT_ACTION_WORLD:
						msg = "<font color='#285EAB'>[世]</font><font color='#FEB500'>"
								+ chatMsg.getRoleName()
								+ "</font>："
								+ chatMsg.getMsg();
						break;
					}
					if (msg != null && !msg.equals("")) {
						if (msgArr.size() >= maxMsg) {
							msgArr.removeFirst();
						}
						msgArr.addLast(msg);
						mHandler.sendEmptyMessage(Constant.MSG_SUCCESS);
					}
				}
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chat_scale:
			if (this.chatScaleStatus == 1) {
				this.chatScrollView
						.setLayoutParams(new LinearLayout.LayoutParams(Utils
								.dipToPx(context, 150), Utils.dipToPx(context,
								150)));
				this.chatScaleStatus = 2;
			} else if (this.chatScaleStatus == 2) {
				this.chatScrollView
						.setLayoutParams(new LinearLayout.LayoutParams(Utils
								.dipToPx(context, 150), Utils.dipToPx(context,
								60)));
				this.chatScaleStatus = 0;
			} else {
				this.chatScrollView
						.setLayoutParams(new LinearLayout.LayoutParams(Utils
								.dipToPx(context, 150), Utils.dipToPx(context,
								100)));
				this.chatScaleStatus = 1;
			}
			break;
		case R.id.btn_chat_send:
			new Thread(new Runnable() {
				public void run() {
					ChatMsg chatMsg = new ChatMsg();
					chatMsg.setActionId(Constant.CHAT_ACTION_WORLD);
					chatMsg.setChannelId(1);
					chatMsg.setMsg("<font color='#FF6600'>没有椰子妹，坑爹啊</font>_"
							+ Utils.getRandom(100));
					((GameActivity) context).gameApp.getSocket().send(chatMsg);
				}
			}).start();
			break;
		}
	}

}