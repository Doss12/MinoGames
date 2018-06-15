package com.mino.minogames;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;



public class MenuGUI implements Screen {
	private SpriteBatch batch;
	private Stage stage;
    private Texture BackgroundTexture;
	private ImageButton dominoButton,triominoButton,a_propos;
	private Game game;
	private Sprite sprite;


	public MenuGUI(Game game) {
		this.game = game;
		stage = new Stage();
		batch=new SpriteBatch();
		Gdx.input.setInputProcessor(stage);
		//le Titre "MinoGames"
		sprite=new Sprite(Assets.manager.get(Assets.titre));
		sprite.setX(150);
		sprite.setY(400);
		Skin skin = new Skin();
		//bouton jouer domino
		skin.add("domino", Assets.manager.get(Assets.domino));
		Drawable drawableDomino = skin.getDrawable("domino");
	    dominoButton = new ImageButton(drawableDomino);
	    dominoButton.setX(400);
	    dominoButton.setY(400);
	    stage.addActor(dominoButton);
		dominoButton.addListener( new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("domino");
		        rediretJoueur();
		    }
		} );

		//bouton jouer triomino
		skin.add("triomino", Assets.manager.get(Assets.triomino));
	    Drawable drawableTriomino= skin.getDrawable("triomino");
	    triominoButton = new ImageButton(drawableTriomino);
	    triominoButton.setX(400);
	    triominoButton.setY(200);
	    triominoButton.addListener( new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("non disponible");
		    }
		} );
	    stage.addActor(triominoButton);

	    //A propos bouton
	    skin.add("propos", Assets.manager.get(Assets.propos));
        Drawable drawablePropos = skin.getDrawable("propos");
	    a_propos = new ImageButton(drawablePropos);
		a_propos.setX(750);
		a_propos.setY(50);
		a_propos.addListener( new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("propos");
		        rediretPropos();
		    }
		} );


		stage.addActor(a_propos);
	}


	@Override
	public void render(float delta) {
		BackgroundTexture=Assets.manager.get(Assets.menu);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
		batch.begin();
		batch.draw(BackgroundTexture, 0, 0);
		sprite.draw(batch);
		batch.end();
		stage.act(delta);
		stage.draw();
	}

	public void rediretJoueur() {
		this.game.setScreen(new JoueurGUI(this.game));
	}

	public void rediretPropos() {
		this.game.setScreen(new A_proposGUI(this.game));
	}

	@Override
	public void dispose () {

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
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}





