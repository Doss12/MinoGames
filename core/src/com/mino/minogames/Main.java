package com.mino.minogames;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;


public class Main extends Game {

		@Override
		public void create() {
			Assets.load();
			Assets.manager.finishLoading();
			setScreen(new MenuGUI(this));

		}

	@Override
	public void dispose() {
		Assets.dispose();
	}
}


