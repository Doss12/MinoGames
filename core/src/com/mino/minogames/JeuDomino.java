package com.mino.minogames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class JeuDomino extends ApplicationAdapter {
	SpriteBatch batch;
	Texture HUD;
	private Stage stage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		HUD = new Texture("HUD_Deux_Joueurs.png");
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Domino domino_6 = new Domino(400,400);
        domino_6.setTouchable(Touchable.enabled);
        stage.addActor(domino_6);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(60/255f, 68/255f, 79/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
		batch.begin();
		batch.draw(HUD, 0, 0);
		batch.end();
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
		HUD.dispose();
	}
	
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}
