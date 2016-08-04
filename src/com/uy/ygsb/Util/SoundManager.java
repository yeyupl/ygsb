package com.uy.ygsb.Util;

import com.uy.ygsb.R;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundManager {
	private Context context;
	private MediaPlayer mediaPlayer;
	private static SoundManager soundManager;

	public SoundManager(Context context) {
		this.context = context;
	}

	public static SoundManager getInstance(Context context) {
		if (soundManager == null) {
			soundManager = new SoundManager(context);
		}
		return soundManager;
	}

	public void playMusic() {
		try {
			this.mediaPlayer = MediaPlayer.create(this.context, R.raw.music);
			this.mediaPlayer.setLooping(true);
			this.mediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopMusic() {
		try {
			this.mediaPlayer.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
