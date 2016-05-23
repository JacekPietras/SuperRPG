package com.ph.rpg.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ph.rpg.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = "RPG";
		cfg.width = 800;
		cfg.height = 600;
		cfg.useGL30 = false;
		cfg.resizable = false;
		new LwjglApplication(new Game(), cfg);
	}
}
