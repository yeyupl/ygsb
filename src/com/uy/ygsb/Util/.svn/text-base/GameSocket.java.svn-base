package com.uy.ygsb.Util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.uy.ygsb.Game.Constant;
import com.uy.ygsb.Pojo.ChatMsg;

import android.util.Log;

public class GameSocket {

	private Socket socket;
	private DataInputStream in;
	private OutputStream out;

	public GameSocket() {

	}

	public void connect(String host, int port) {
		try {
			this.socket = new Socket(InetAddress.getByName(host), port);
			this.socket.setKeepAlive(true);
			this.in = new DataInputStream(this.socket.getInputStream());
			this.out = this.socket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isAlive() {
		return this.socket != null && this.socket.isConnected();
	}

	public void close() {
		try {
			if (this.socket != null) {
				this.socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(ChatMsg chatMsg) {
		try {
			if (this.isAlive() && !this.socket.isOutputShutdown()) {
				byte[] actionId, roleId, channelId, key, packLen, msg;
				short lenInt;
				switch (chatMsg.getActionId()) {
				case Constant.CHAT_ACTION_AUTH: // 验证
					actionId = InttoByteArray(chatMsg.getActionId());
					roleId = InttoByteArray(chatMsg.getRoleId());
					key = stringtoByteArray(chatMsg.getKey());
					lenInt = (short) (actionId.length + roleId.length + key.length);
					packLen = ShorttoByteArray(lenInt);
					this.out.write(packLen);
					this.out.write(actionId);
					this.out.write(roleId);
					this.out.write(key);
					this.out.flush();
					Log.d("++++sendAuthSucc+++++", chatMsg.getKey());
					break;
				case Constant.CHAT_ACTION_WORLD: // 世界聊天
					actionId = InttoByteArray(chatMsg.getActionId());
					channelId = InttoByteArray(chatMsg.getChannelId());
					msg = stringtoByteArray(chatMsg.getMsg());
					lenInt = (short) (actionId.length + channelId.length + msg.length);
					packLen = ShorttoByteArray(lenInt);
					this.out.write(packLen);
					this.out.write(actionId);
					this.out.write(channelId);
					this.out.write(msg);
					this.out.flush();
					Log.d("++++sendMsgSucc+++++", chatMsg.getMsg());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ChatMsg read() {
		ChatMsg chatMsg = new ChatMsg();
		try {
			if (this.isAlive() && !this.socket.isInputShutdown()) {
				if (this.in.readShort() > 0) {
					int actionId = this.in.readInt();
					chatMsg.setActionId(actionId);
					switch (actionId) {
					case Constant.CHAT_ACTION_AUTH: // 验证
						chatMsg.setCode(this.in.readInt());
						break;
					case Constant.CHAT_ACTION_WORLD: // 世界聊天
						chatMsg.setChannelId(this.in.readInt());
						chatMsg.setRoleId(this.in.readInt());
						chatMsg.setRoleName(this.in.readUTF());
						chatMsg.setMsg(this.in.readUTF());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chatMsg;
	}

	public static byte[] InttoByteArray(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n >> 24 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[3] = (byte) (n & 0xff);
		return b;
	}

	public static byte[] ShorttoByteArray(short n) {
		byte[] b = new byte[2];
		b[0] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n & 0xff);
		return b;
	}

	public static int ByteArraytoInt(byte[] b) {
		int iOutcome = 0;
		byte bLoop;
		for (int i = 0; i < 4; i++) {
			bLoop = b[i];
			iOutcome += (bLoop & 0xff) << (8 * i);
		}
		return iOutcome;
	}

	public static short ByteArraytoShort(byte[] b) {
		short iOutcome = 0;
		byte bLoop;
		for (int i = 0; i < 2; i++) {
			bLoop = b[i];
			iOutcome += (bLoop & 0xff) << (8 * i);
		}
		return iOutcome;
	}

	public static String byteArraytoString(byte[] b) {
		String retStr = "";
		try {
			retStr = new String(b, "UTF8");
		} catch (Exception e) {
		}
		return retStr.trim();
	}

	public static byte[] stringtoByteArray(String str) {
		byte[] retBytes = null;
		try {
			retBytes = str.getBytes("UTF8");
		} catch (Exception ex) {
		}
		return retBytes;
	}

}
