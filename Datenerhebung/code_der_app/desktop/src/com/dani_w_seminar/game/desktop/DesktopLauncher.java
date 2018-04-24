package com.dani_w_seminar.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dani_w_seminar.game.Dani_w_seminar;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Dani_w_seminar(new DesktopPlayVideo(), new Desktop_File_Manager()), config);
	}
}
