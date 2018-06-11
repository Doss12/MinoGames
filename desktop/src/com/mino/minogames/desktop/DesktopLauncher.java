package com.mino.minogames.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
// import com.mino.minogames.Main;
import com.mino.minogames.JeuMinoGUI;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 1280;
		config.height = 720;
		config.title = "MinoGames";
		config.addIcon("icon.png", Files.FileType.Internal);
		// new LwjglApplication(new Main(), config);
		new LwjglApplication(new JeuMinoGUI(), config);
	}
}
