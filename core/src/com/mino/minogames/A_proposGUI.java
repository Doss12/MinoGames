package com.mino.minogames;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class A_proposGUI implements Screen {
	private SpriteBatch batch;
	private Texture BackgroundTexture;
	private ImageButton RetourButton;

	private Stage stage;
	private Game game;
	
	public A_proposGUI(Game game) {
		this.game=game;
		stage=new Stage();
    	batch=new SpriteBatch();
    	stage=new Stage();
    	 Gdx.input.setInputProcessor(stage);
    	
    	 Skin skin = new Skin();
 		 //bouton Retour
 		 skin.add("RetourButton", Assets.manager.get(Assets.bouton_retour));
 		 Drawable RetourDrawable = skin.getDrawable("RetourButton");
    	 RetourButton = new ImageButton(RetourDrawable);
    	 RetourButton.setX(50);
    	 RetourButton.setY(600);
    	 RetourButton.addListener( new ClickListener() {
 			@Override
 		    public void clicked(InputEvent event, float x, float y) {
 		        System.out.println("retour");
 		        rediret();
 		    }
 		} );
    	 
    	 
    	 
 	    stage.addActor(RetourButton);
		
	}
	
	
	@Override
	public void render(float delta) {
		BackgroundTexture = Assets.manager.get(Assets.menu_a_propos);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		batch.begin();
		batch.draw(BackgroundTexture, 0, 0);
		batch.end();
		stage.act(delta);
		stage.draw();
		
	}
	
	private void rediret() {
		game.setScreen(new MenuGUI(game));
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {	
	}

}
