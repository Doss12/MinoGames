package com.mino.minogames;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class JeuMinoGUI extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Stage stage;
	private JeuMino game;
	ArrayList<ArrayList<MinoIMG>> mino_joueur;
	private ArrayList<MinoIMG> plateau;
		
	@Override
	public void create () {
		game = new JeuMino(3,4);
		
		batch = new SpriteBatch();
		stage = new Stage();
		camera = new OrthographicCamera();
		
		Gdx.input.setInputProcessor(stage);
		mino_joueur = new ArrayList<ArrayList<MinoIMG>>();
		for(int i = 0; i < game.get_nbJoueur(); i++)
			mino_joueur.add(new ArrayList<MinoIMG>());
		plateau = new ArrayList<MinoIMG>();
        init_HUD();
        affiche_joueur();
	}
	
	public void init_HUD() {
		HUD hud = new HUD(0, 0, game.get_list_Joueur().size());
        stage.addActor(hud);
	}
	
	public void affiche_joueur() {
		if (game.get_nbCote() == 2)
			affiche_joueur_domino();
		else if (game.get_nbCote() == 3)
			affiche_joueur_triomino();
	}
	
	public void affiche_joueur_domino() {
		int X = 450;	int Y = 10;
		ArrayList<Mino> list_mino;
		for(int i = 0; i < game.get_nbJoueur(); i++){
			list_mino = game.get_list_Joueur().get(i).get_main();
			if(i < 2) {X = 450;}
			else {Y = 500;}
			for (int j=0 ; j<list_mino.size(); j++) {
				if(i == 0) {X+=50; Y = 10;}
				if(i == 1) {X+=50; Y = 650;}
				if(i == 2) {Y-=50; X = 10;}
				if(i == 3) {Y-=50; X = 1210;}
				mino_joueur.get(i).add(new DominoIMG(list_mino.get(j),X,Y));
				mino_joueur.get(i).get(j).setTouchable(Touchable.enabled);
				stage.addActor(mino_joueur.get(i).get(j));
			}
		}
	}
	
	public void affiche_joueur_triomino() {
		int X = 400;	int Y = 10;
		ArrayList<Mino> list_mino;
		for(int i = 0; i < game.get_nbJoueur(); i++){
			list_mino = game.get_list_Joueur().get(i).get_main();
			if(i < 2) {X = 400;}
			else {Y = 500;}
			for (int j=0 ; j<list_mino.size(); j++) {
				if(i == 0) {X+=65; Y = 10;}
				if(i == 1) {X+=65; Y = 650;}
				if(i == 2) {Y-=65; X = 10;}
				if(i == 3) {Y-=65; X = 1210;}
				mino_joueur.get(i).add(new TriominoIMG(list_mino.get(j),X,Y));
				mino_joueur.get(i).get(j).setTouchable(Touchable.enabled);
				stage.addActor(mino_joueur.get(i).get(j));
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(60/255f, 68/255f, 79/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
		batch.begin();
		batch.end();
	}
		
	@Override
	public void dispose () {
		batch.dispose();
	}
	
    @Override
    public void resize(int width, int height) {
    	camera.setToOrtho(false, width, height);
        camera.update();
    	stage.getViewport().setScreenSize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
