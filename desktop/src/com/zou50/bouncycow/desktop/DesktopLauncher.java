package com.zou50.bouncycow.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zou50.bouncycow.BCGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = BCGame.TITLE;
		config.width = BCGame.WIDTH;
		config.height = BCGame.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new BCGame(), config);
	}
}
