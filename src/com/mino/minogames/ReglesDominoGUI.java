package com.mino.minogames;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class ReglesDominoGUI implements Screen {
	private SpriteBatch batch;
	private Texture BackgroundTexture;
	private ImageButton Double_SixButton,DeroulementButton,RetourButton;
    private Stage stage;
	private Game game;
	
	public ReglesDominoGUI(Game game) {
		 this.game=game;
    	 batch=new SpriteBatch();
    	 stage=new Stage();
    	 Gdx.input.setInputProcessor(stage);
    	 BackgroundTexture = new Texture(Gdx.files.internal("regle_domino.png"));
    	 Skin skin = new Skin();
    	 skin.add("bouton_doubleSix", Assets.manager.get(Assets.bouton_doubleSix));
    	 Drawable bouton_doubleSix = skin.getDrawable("bouton_doubleSix");
    	 Double_SixButton = new ImageButton(bouton_doubleSix);
    	 Double_SixButton.setX(850);
    	 Double_SixButton.setY(530);
    	 Double_SixButton.addListener( new ClickListener() {
 			@Override
   		    public void clicked(InputEvent event, float x, float y) {
 				redirectDoubleSix();
 			}
 	       });
    	 
    	 
    	 skin.add("bouton_deroulement", Assets.manager.get(Assets.bouton_deroulement));
    	 Drawable bouton_deroulement = skin.getDrawable("bouton_deroulement");
    	 DeroulementButton = new ImageButton(bouton_deroulement);
    	 DeroulementButton.setX(1050);
    	 DeroulementButton.setY(530);
    	 DeroulementButton.addListener( new ClickListener() {

  			@Override
  		    public void clicked(InputEvent event, float x, float y) {
  		        redirectDeroulement();
  			}
  		    } );
    
    	 
    	 
    	 skin.add("bouton_retour", Assets.manager.get(Assets.bouton_retour));
    	 Drawable bouton_retour = skin.getDrawable("bouton_retour");
    	 RetourButton = new ImageButton(bouton_retour);
    	 RetourButton.setX(50);
    	 RetourButton.setY(600);
    	 RetourButton.addListener( new ClickListener() {
  			@Override
  		    public void clicked(InputEvent event, float x, float y) {
  		        System.out.println("retour");
  		        redirect();
  		    }
  		} );
    	 
    	 
    	 stage.addActor(Double_SixButton);
    	 stage.addActor(DeroulementButton);
    	 stage.addActor(RetourButton);
    	 
		
	}
	void redirect() {
		game.setScreen(new JoueurGUI(game));
	}
	void redirectDeroulement() {
		this.BackgroundTexture =Assets.manager.get(Assets.deroulement);
	}
	void redirectDoubleSix() {
		this.BackgroundTexture =Assets.manager.get(Assets.double_Six);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		

		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		batch.begin();
		batch.draw(BackgroundTexture, 0, 0);
		batch.end();
		stage.act(delta);
		stage.draw();
		
		
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
