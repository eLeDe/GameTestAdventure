package com.elede.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elede.game.GameTestMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		// Screen ratio 16:9 = 1.7778
		config.width = 640;
		config.height = 360;
		
		new LwjglApplication(new GameTestMain(), config);
		
		
	}
}
