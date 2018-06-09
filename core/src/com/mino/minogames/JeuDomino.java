package com.mino.minogames;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import java.lang.Math;

public class JeuDomino extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage_domino;
	Mino[] pioche;
	Domino[] main_joueur, main_ordinateur;
	int indice_max = 27;
		
	@Override
	public void create () {
		batch = new SpriteBatch();
		stage_domino = new Stage();
		Gdx.input.setInputProcessor(stage_domino);
		
        init_HUD();
        pioche=creation_pioche();
        distribution_domino();
	}
	
	public void init_HUD() {
		HUD hud = new HUD(0, 0, 2);
        stage_domino.addActor(hud);
	}
	
	public Mino[] creation_pioche() {
		Mino[] pioche = new Mino[28];
		int k=0;
		for (int i=0; i<7; i++) {
			for (int j=i; j<7; j++) {
				Mino mino_test = new Mino();
				mino_test.set_cote1(i);
				mino_test.set_cote2(j);
				pioche[k]=mino_test;
				k=k+1;
			}
		}
		return pioche;
	}
	
	public Mino domino_aleatoire(Mino[] pioche) {
		int indice;
		indice = (int) (Math.random() * (indice_max+1));
		indice_max--;
		Mino domino_aleatoire=pioche[indice];
		pioche[indice]=pioche[indice_max];
		return domino_aleatoire;
	}
	
	public void distribution_domino() {
		int X = 500;
		
		main_joueur = new Domino[7];
		
        for (int i=0 ; i<7 ; i++) {
        	Mino domino_joueur=domino_aleatoire(pioche);
    		main_joueur[i] = new Domino(X, 10, domino_joueur.get_cote1(), domino_joueur.get_cote2());
        	X = X + 50;
        	main_joueur[i].setTouchable(Touchable.enabled);
        	stage_domino.addActor(main_joueur[i]);
        	
        }
        
        X = 500;
        
		main_ordinateur = new Domino[7];
        for (int i=0 ; i<7 ; i++) {
        	Mino domino_ordi=domino_aleatoire(pioche);
        	main_ordinateur[i] = new Domino(X, 650, domino_ordi.get_cote1(), domino_ordi.get_cote2());
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
