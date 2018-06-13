
package View;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mino.minogames.HUD;
import com.mino.minogames.JeuMino;
import com.mino.minogames.Mino;

public class JeuMinoGUI implements Screen {

	private Stage stage;
	private JeuMino jeu;
	ArrayList<ArrayList<MinoIMG>> mino_joueur;
	private ArrayList<DominoIMG> plateau;
	Game game;
	
	
		
	public JeuMinoGUI(Game game) {
		super();
		this.game=game;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		jeu = new JeuMino(2,4);
		mino_joueur = new ArrayList<ArrayList<MinoIMG>>();
		for(int i = 0; i < jeu.get_nbJoueur(); i++)
			mino_joueur.add(new ArrayList<MinoIMG>());
		plateau = new ArrayList<DominoIMG>();
        init_HUD();
        affiche_joueur();
		
	}




	

	
	public void init_HUD() {
		HUD hud = new HUD(0, 0, jeu.get_list_Joueur().size());
        stage.addActor(hud);
	}
	
	public void affiche_joueur() {
		if (jeu.get_nbCote() == 2)
			affiche_joueur_domino();
	else if (jeu.get_nbCote() == 3)
			affiche_joueur_triomino();
	}
	
	public void affiche_joueur_domino() {
		int X = 450;	int Y = 10;
		ArrayList<Mino> list_mino;
		for(int i = 0; i < jeu.get_nbJoueur(); i++){
			list_mino = jeu.get_list_Joueur().get(i).get_main();
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
		for(int i = 0; i < jeu.get_nbJoueur(); i++){
			list_mino = jeu.get_list_Joueur().get(i).get_main();
			if(i < 2) {X = 400;}
			else {Y = 500;}
			for (int j=0 ; j<list_mino.size(); j++) {
				if(i == 0) {X+=65; Y = 10;}
				if(i == 1) {X+=65; Y = 650;}
				if(i == 2) {Y-=65; X = 10;}
				if(i == 3) {Y-=65; X = 1210;}
			//	mino_joueur.get(i).add(new TriominoIMG(list_mino.get(j),X,Y));
				mino_joueur.get(i).get(j).setTouchable(Touchable.enabled);
				stage.addActor(mino_joueur.get(i).get(j));
			}
		}
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
	
	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(60/255f, 68/255f, 79/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
	    stage.draw();
	
		
	}



	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}





		
	
}
