package com.uy.ygsb;

import com.uy.ygsb.Game.Constant;
import com.uy.ygsb.Pojo.Auth;
import com.uy.ygsb.Pojo.ChatMsg;
import com.uy.ygsb.Pojo.Map;
import com.uy.ygsb.Pojo.Role;
import com.uy.ygsb.Pojo.Server;
import com.uy.ygsb.Pojo.User;
import com.uy.ygsb.Util.GameSocket;

import android.app.Application;
import android.util.Log;

public class GameApp extends Application {

	private User user;
	private Role role;
	private Server server;
	private Auth auth;
	private Map map;
	private GameSocket gameSocket;
	private boolean isSocketClose = false;

	public User getUserInfo() {
		return this.user;
	}

	public void setUserInfo(User user) {
		this.user = user;
	}

	public boolean isLogin() {
		return this.user.getId() > 0;
	}

	public Role getRoleInfo() {
		return this.role;
	}

	public void setRoleInfo(Role role) {
		this.role = role;
	}

	public Server getServerInfo() {
		return this.server;
	}

	public void setServerInfo(Server server) {
		this.server = server;
	}

	public String getServerUrl() {
		if (this.server != null && !this.server.getUrl().equals("")) {
			return this.server.getUrl();
		}
		return Constant.server_url;
	}

	public int getAuthId() {
		if (this.role != null && this.getRoleInfo().getId() > 0) {
			return this.getRoleInfo().getId();
		}
		if (this.user != null) {
			return this.getUserInfo().getId();
		}
		return 0;
	}

	public String getAuthKey() {
		if (this.user != null) {
			return this.getUserInfo().getAuthKey();
		}
		return "";
	}

	public GameSocket getSocket() {
		if (this.gameSocket == null) {
			Log.d("++++gameSocket+++", "getInstance");
			this.gameSocket = new GameSocket();
		}
		if (!this.isSocketClose && !this.gameSocket.isAlive()) {
			Log.d("++++gameSocket+++", "connecting");

			this.gameSocket.connect(Constant.host, Constant.port);

			Log.d("++++gameSocket+++", this.gameSocket.toString() + "");

			// 验证
			ChatMsg chatMsg = new ChatMsg();
			chatMsg.setActionId(Constant.CHAT_ACTION_AUTH);
			chatMsg.setRoleId(this.getRoleInfo().getId());
			chatMsg.setKey(this.getAuthKey());
			this.gameSocket.send(chatMsg);

		}
		return this.gameSocket;
	}

	public void closeSocket() {
		this.isSocketClose = true;
		this.gameSocket.close();
	}

	public Auth getAuth() {
		if (this.auth == null) {
			this.auth = new Auth();
		}
		this.auth.setServerUrl(this.getServerUrl());
		this.auth.setAuthId(this.getAuthId());
		this.auth.setAuthKey(this.getAuthKey());
		return this.auth;
	}

	public void setMapInfo(Map map) {
		this.map = map;
	}

	public Map getMapInfo() {
		return map;
	}
}
