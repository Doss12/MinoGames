package com.mino.minogames;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class JeuDomino extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage_domino;
	Domino[] main_joueur, main_ordinateur;
	HUD hud;
	Random aleatoire = new Random();
	
	@Override
	public void create () {
		int alea1, alea2;
		int X = 500;
		batch = new SpriteBatch();
		stage_domino = new Stage();
		Gdx.input.setInputProcessor(stage_domino);
        hud = new HUD(0, 0, 2);
        stage_domino.addActor(hud);
        
		main_joueur = new Domino[7];
        for (int i=0 ; i<7 ; i++) {
        	alea1 = aleatoire.nextInt(6);
    		alea2 = aleatoire.nextInt(6);
    		main_joueur[i] = new Domino(X, 10, alea1, alea2);
        	main_joueur[i].set_cote1(alea1);
        	main_joueur[i].set_cote2(alea2);
        	X = X + 50;
        	main_joueur[i].setTouchable(Touchable.enabled);
        	stage_domino.addActor(main_joueur[i]);
        }
        X = 500;
        
		main_ordinateur = new Domino[7];
        for (int i=0 ; i<7 ; i++) {
        	alea1 = aleatoire.nextInt(6);
    		alea2 = aleatoire.nextInt(6);
        	main_ordinateur[i] = new Domino(X, 650, alea1, alea2);
        	main_ordinateur[i].set_cote1(alea1);
        	main_ordinateur[i].set_cote2(alea2);
        	X = X + 50;
        	main_ordinateur[i].setTouchable(Touchable.enabled);
        	stage_domino.addActor(main_ordinateur[i]);
        }
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(60/255f, 68/255f, 79/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage_domino.act(Gdx.graphics.getDeltaTime());
	    stage_domino.draw();
		batch.begin();
		batch.end();
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
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
