package com.uy.ygsb.Pojo;

public class Auth {
	private String serverUrl, authKey;
	private int authId;

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public int getAuthId() {
		return authId;
	}
}
