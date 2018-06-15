package com.mino.minogames;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class JeuMinoGUI extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Stage stage;
	private JeuMino game;
	private int nb_cote; // 2 = domino, 3 = triomino
	private int nb_joueur;
	private int tour;
	private HUD hud;
	private ArrayList<ArrayList<MinoIMG>> mino_joueur;
	private ArrayList<MinoIMG> plateau;
	private int nb_domino_droite = 0;
	private int nb_domino_gauche = 0;
	private DominoIMG domino_jouee = null;
	private TriominoIMG triomino_jouee = null;
	
	
	@Override
	public void create () {
		nb_cote = 3;
		nb_joueur = 4;
		game = new JeuMino(nb_cote, nb_joueur);
		batch = new SpriteBatch();
		stage = new Stage();
		camera = new OrthographicCamera();
		Gdx.input.setInputProcessor(stage);
		
		init_texture_mino();
		/*************************************************************************
		*********************************** TEST ********************************/
		game.get_list_Joueur().get(0).set_pseudo("Glenn Rhee");
		game.get_list_Joueur().get(1).set_pseudo("Maggie Rhee");
		game.get_list_Joueur().get(2).set_pseudo("Rick Grimes");
		game.get_list_Joueur().get(3).set_pseudo("Daryl Dixon");
		/*************************************************************************
		*************************************************************************/
        init_HUD();
        init_premier_mino();
        set_tour(game.qui_commence()+1);
        affiche_joueur();
        hud.piocher.toFront();
        mino_to_front(plateau.get(0));
        update_mino();
	}
	
	public void mino_to_front(MinoIMG M)
	{
		M.toFront();
        for(int i = 0; i < game.get_nbCote(); i++)
        	M.get_halo(i).toFront();
	}
	
	public void init_premier_mino()
	{
		plateau = new ArrayList<MinoIMG>();
		if(game.get_nbCote() == 2)
			plateau.add(new DominoIMG(game.pose_mino_max(), 640, 230));
		else
			plateau.add(new TriominoIMG(game.pose_mino_max(), 640, 360));
		plateau.get(0).setTouchable(Touchable.enabled);
		plateau.get(0).set_orientation(orientation.HORIZONTALE);
		plateau.get(0).posable();
		plateau.get(0).set_pose(true);
		this.ajout_mino_stage(plateau.get(0));
	}
	
	public void init_texture_mino() {
		mino_joueur = new ArrayList<ArrayList<MinoIMG>>();
		for(int i = 0; i < game.get_nbJoueur(); i++)
			mino_joueur.add(new ArrayList<MinoIMG>());
		plateau = new ArrayList<MinoIMG>();
	}
	
	public void init_HUD() {
		hud = new HUD(0, 0, game.get_list_Joueur(),game.get_nbCote());
		hud.piocher.addListener(new ClickListener() {
        	public void clicked(InputEvent event, float x, float y)  {
        		System.out.println("CLIC Piocher");
        		if(game.get_pioche().isEmpty() && besoin_pioche())
        		{
        			//FIN DE PARTIE MATCH NUL
        			stage.addActor(new FIN(game.get_list_Joueur().get(tour), true , game.get_nbCote()));
        		}
        		else
        		{
	        		if(!game.get_pioche().isEmpty())
	        			action_piocher();
	        		else
	        			System.out.println("Pioche vide");
        		}
        	}
        });
        stage.addActor(hud);
        stage.addActor(hud.piocher);
	}
	
	public void action_piocher()
	{
		if(!(besoin_pioche()))
			return;
		Mino M = game.joueur_piocher(tour);
		for(int i = 0; i < mino_joueur.get(tour).size(); i++)
		{
			if(!(mino_joueur.get(tour).get(i).isVisible()))
			{
				mino_joueur.get(tour).get(i).set_mino(M);
				if(game.get_nbCote() == 2)
					((DominoIMG) mino_joueur.get(tour).get(i)).update_texture();
				else
					((TriominoIMG) mino_joueur.get(tour).get(i)).update_texture();
				int posX = mino_joueur.get(tour).get(i).get_posX();
				int posY = mino_joueur.get(tour).get(i).get_posY();
				mino_joueur.get(tour).get(i).deplacer(posX, posY);
				mino_joueur.get(tour).get(i).setVisible(true);
				i = mino_joueur.get(tour).size();
			}
		}
		
	}
	
	public boolean besoin_pioche()
	{
		for(int i = 0; i < game.get_list_Joueur().get(tour).get_main().size(); i++)
		{
			for(int j = 0; j < plateau.size(); j++)
			{
				int[] cote_dispo = game.get_list_Joueur().get(tour).get_main().get(i).tester_tous_cotes(plateau.get(j).get_mino());
				for(int k = 0; k < game.get_nbCote(); k++)
				{
					if(cote_dispo[k] != -1)
						return false;
				}
			}
			
		}
		return true;
	}
	
	public void update_main_joueur(int id_joueur)
	{
		int X = 450;	int Y = 10;
		if(game.get_nbCote() == 2)
		{
			if(id_joueur < 2) {X = 385;}
			else {Y = 540;}
		}
		else
		{
			if(id_joueur < 2) {X = 360;}
			else {Y = 530;}
		}
		
		for(int i = 0; i < mino_joueur.get(id_joueur).size(); i++)
		{
			if(!(mino_joueur.get(id_joueur).get(i).est_pose()))
			{
				if(game.get_nbCote() == 2)
				{
					if(id_joueur == 0) {X+=42; Y = 10;}
					if(id_joueur == 1) {X+=42; Y = 650;}
					if(id_joueur == 2) {Y-=42; X = 10;}
					if(id_joueur == 3) {Y-=42; X = 1210;}
				}
				else
				{
					if(id_joueur == 0) {X+=65; Y = 10; if(i == 9) X = 360;		if(i > 9) Y = 70;}
					if(id_joueur == 1) {X+=65; Y = 650; if(i == 9) X = 360;		if(i > 9) Y = 590;}
					if(id_joueur == 2) {Y-=65; X = 10; if(i == 9) Y = 530;		if(i > 9) X = 70;}
					if(id_joueur == 3) {Y-=65; X = 1210; if(i == 9) Y = 530;	if(i > 9) X = 1150;}
				}
				
				mino_joueur.get(id_joueur).get(i).setAncienX(X);
				mino_joueur.get(id_joueur).get(i).setAncienY(Y);
				mino_joueur.get(id_joueur).get(i).deplacer(X, Y);
			}
		}
		hud.piocher.toFront();
	}
	
	public void affiche_joueur() {
		if (game.get_nbCote() == 2)
			affiche_joueur_domino();
		else if (game.get_nbCote() == 3)
			affiche_joueur_triomino();
	}
	
	
	public void affiche_joueur_domino() {
		//Pour Ajout domino invisible à piocher
		int nb_ajout = 0;
		if(game.get_nbJoueur() == 4)
			nb_ajout = 8;
		if(game.get_nbJoueur() == 3)
			nb_ajout = 13;
		if(game.get_nbJoueur() == 2)
			nb_ajout = 14;
			
		int X = 450;	int Y = 10;
		ArrayList<Mino> list_mino;
		for(int i = 0; i < game.get_nbJoueur(); i++){
			list_mino = game.get_list_Joueur().get(i).get_main();
			if(i < 2) {X = 385;}
			else {Y = 540;}
			for (int j=0 ; j<list_mino.size()+nb_ajout; j++) {
				if(i == 0) {X+=42; Y = 10;}
				if(i == 1) {X+=42; Y = 650;}
				if(i == 2) {Y-=42; X = 10;}
				if(i == 3) {Y-=42; X = 1210;}
				
				
				if(j < list_mino.size())
					mino_joueur.get(i).add(new DominoIMG(list_mino.get(j),X,Y));
				else
				{
					mino_joueur.get(i).add(new DominoIMG(new Mino(2),X,Y));
					mino_joueur.get(i).get(j).setVisible(false);
				}
				mino_joueur.get(i).get(j).setTouchable(Touchable.enabled);
                //LE PREMIER CLIC POUR SELECTIONNER LE DOMINO
				final int clic1_i = i, clic1_j = j;
				
                mino_joueur.get(i).get(j).addListener(new ClickListener() {
                	public void clicked(InputEvent event, float x, float y)  {
                		System.out.println("CLIC 1");
                		if(MinoIMG.est_libre == true && mino_joueur.get(clic1_i).get(clic1_j).est_jouable() && tour == clic1_i)
                		{
                			MinoIMG.est_libre = false;
	                		mino_joueur.get(clic1_i).get(clic1_j).set_select(true);
	                		domino_jouee = (DominoIMG)mino_joueur.get(clic1_i).get(clic1_j);
	                		set_visible_all_halo(false);
	                		//plateau.add(mino_joueur.get(clic1_i).get(clic1_j));
	                		Mino main = mino_joueur.get(clic1_i).get(clic1_j).get_mino();
	                		Mino select;
	                		for(int i = 0; i < plateau.size(); i++) {
	                			select = plateau.get(i).get_mino(); 
	                    		int[] cote_dispo = main.tester_tous_cotes(select);
	                    		if (cote_dispo[0] != -1)
	                    			plateau.get(i).set_halo_visible(0, true);
	                    		if (cote_dispo[1] != -1)
	                    			plateau.get(i).set_halo_visible(1, true);
	                		}
                		}
                	}
                });
                this.ajout_mino_stage(mino_joueur.get(i).get(j));
			}
		}
	}
	
	
	
	public void affiche_joueur_triomino() {
		//Pour Ajout domino invisible à piocher
		int nb_ajout = 0;
		if(game.get_nbJoueur() == 4)
			nb_ajout = 32;
		if(game.get_nbJoueur() == 3)
			nb_ajout = 39;
		if(game.get_nbJoueur() == 2)
			nb_ajout = 38;
		int X = 400;	int Y = 10;
		ArrayList<Mino> list_mino;
		for(int i = 0; i < game.get_nbJoueur(); i++){
			list_mino = game.get_list_Joueur().get(i).get_main();
			if(i < 2) {X = 360;}
			else {Y = 530;}
			for (int j=0 ; j<list_mino.size()+nb_ajout; j++) {
				if(i == 0) {X+=65; Y = 10; if(j == 9) X = 360;		if(j > 9) Y = 70;}
				if(i == 1) {X+=65; Y = 650; if(j == 9) X = 360;		if(j > 9) Y = 590;}
				if(i == 2) {Y-=65; X = 10; if(j == 9) Y = 530;		if(j > 9) X = 70;}
				if(i == 3) {Y-=65; X = 1210; if(j == 9) Y = 530;	if(j > 9) X = 1150;}
				
				
				if(j < list_mino.size())
					mino_joueur.get(i).add(new TriominoIMG(list_mino.get(j),X,Y));
				else
				{
					mino_joueur.get(i).add(new TriominoIMG(new Mino(3),X,Y));
					mino_joueur.get(i).get(j).setVisible(false);
				}
				mino_joueur.get(i).get(j).setTouchable(Touchable.enabled);
				mino_joueur.get(i).get(j).set_orientation(orientation.HORIZONTALE);
				//LE PREMIER CLIC POUR SELECTIONNER LE TRIOMINO
				final int clic1_i = i, clic1_j = j;
                mino_joueur.get(i).get(j).addListener(new ClickListener() {
                	public void clicked(InputEvent event, float x, float y)  {
                		System.out.println("CLIC 1");
                		if(MinoIMG.est_libre == true && mino_joueur.get(clic1_i).get(clic1_j).est_jouable() && tour == clic1_i)
                		{
                			MinoIMG.est_libre = false;
	                		mino_joueur.get(clic1_i).get(clic1_j).set_select(true);
	                		triomino_jouee = (TriominoIMG)mino_joueur.get(clic1_i).get(clic1_j);
	                		set_visible_all_halo(false);
	                		//plateau.add(mino_joueur.get(clic1_i).get(clic1_j));
	                		Mino main = mino_joueur.get(clic1_i).get(clic1_j).get_mino();
	                		Mino select;
	                		for(int i = 0; i < plateau.size(); i++) {
	                			select = plateau.get(i).get_mino(); 
	                    		int[] cote_dispo = main.tester_tous_cotes(select);
	                    		if (cote_dispo[0] != -1)
	                    			plateau.get(i).set_halo_visible(0, true);
	                    		if (cote_dispo[1] != -1)
	                    			plateau.get(i).set_halo_visible(1, true);
	                    		if (cote_dispo[2] != -1)
	                    			plateau.get(i).set_halo_visible(2, true);
	                		}
                		}
                	}
                });
				this.ajout_mino_stage(mino_joueur.get(i).get(j));
				mino_joueur.get(i).get(j).toFront();
				mino_joueur.get(i).get(j).get_halo(0).toBack();
				mino_joueur.get(i).get(j).get_halo(1).toBack();
				mino_joueur.get(i).get(j).get_halo(2).toBack();
			}
		}
	}
	
	
	
	public void set_visible_all_halo(boolean val)
	{
		for(int i = 0; i < plateau.size(); i++)
		{
			for(int j = 0; j < plateau.get(i).get_mino().get_nbcote(); j++)
			{
				plateau.get(i).set_halo_visible(j, val);
			}
		}
			
	}
	
	public ArrayList<MinoIMG> get_plateau_distinct(){
		Set<MinoIMG> hs = new HashSet<MinoIMG>();
        hs.addAll(plateau);
        plateau.clear();
        plateau.addAll(hs);
        
        return plateau;
	}
	
	public void ajout_mino_stage(MinoIMG M)
	{
		stage.addActor(M);
		for(int i = 0; i < M.get_mino().get_nbcote(); i++)
			stage.addActor(M.get_halo(i));
	}
	
	// Met Ã  jour les minos dans la liste Plateau
	public void update_mino() {
		plateau = get_plateau_distinct();
		for (int i=0 ; i<nb_joueur ; i++) {
			for (int j=0 ; j<mino_joueur.get(i).size() ; j++) {
				if((mino_joueur.get(i).get(j).get_posX() < 230
					|| mino_joueur.get(i).get(j).get_posX() > 860
					|| mino_joueur.get(i).get(j).get_posY() < 140
					|| mino_joueur.get(i).get(j).get_posY() > 640)
						&& plateau.contains(mino_joueur.get(i).get(j))) {
					plateau.remove(mino_joueur.get(i).get(j));
				}
			}
		}
	}
	
	public void interact_domino_halo()
	{
		System.out.println("nb_domino_droite : " + nb_domino_droite);
		System.out.println("nb_domino_gauche : " + nb_domino_gauche);
		if(domino_jouee == null)
			return;
		for(int i = 0; i < plateau.size(); i++)
		{
			for(int j = 0; j < game.get_nbCote(); j++)
			{
				if(plateau.get(i).est_pose() == true && plateau.get(i).get_halo(j).est_select() == true)
				{
					plateau.get(i).get_halo(j).set_select(false);
					int place_x = plateau.get(i).get_halo(j).get_posX();
					int place_y = plateau.get(i).get_halo(j).get_posY();
					orientation orient = plateau.get(i).get_halo(j).getOri();
					if(j == 0)
					{
						domino_jouee.get_mino().set_Mino_dispo(1, plateau.get(i).get_mino());
						plateau.get(i).get_mino().set_Mino_dispo(0, domino_jouee.get_mino());
						if(this.nb_domino_gauche == 7)
						{
							orient = orientation.VERTICALE;
							place_x += 30;
							place_y -= 30;
						}
						if(this.nb_domino_gauche == 9)
						{
							orient = orientation.HORIZONTALE;
							place_y += 30;
						}
						if(domino_jouee.get_mino().doit_inverse_gauche_domino(plateau.get(i).get_mino()))
							domino_jouee.inverser_cote();
						this.nb_domino_gauche ++;
					}
					if(j == 1)
					{
						domino_jouee.get_mino().set_Mino_dispo(0, plateau.get(i).get_mino());
						plateau.get(i).get_mino().set_Mino_dispo(1, domino_jouee.get_mino());
						if(this.nb_domino_droite== 6)
							orient = orientation.VERTICALE;
						if(this.nb_domino_droite== 12)
							orient = orientation.HORIZONTALE;
						
						if(domino_jouee.get_mino().doit_inverse_droite_domino(plateau.get(i).get_mino()))
							domino_jouee.inverser_cote();
						this.nb_domino_droite ++;
					}
					domino_jouee.poser(place_x, place_y, orient);
					mino_to_front(domino_jouee);
					plateau.add(domino_jouee);
					game.get_list_Joueur().get(tour).jouer(domino_jouee.get_mino());
					
					
					//mino_joueur.get(tour).remove(domino_jouee);
					//update_main_joueur(tour);
					update_main_joueur(tour);
					domino_jouee = null;
					//FIN DE PARTIE
					if(game.get_list_Joueur().get(tour).get_main().isEmpty())
	        			stage.addActor(new FIN(game.get_list_Joueur().get(tour), true , game.get_nbCote()));
					set_tour(tour+1);
				}
			}
		}
	}
	
	public void interact_triomino_halo()
	{
		if(triomino_jouee == null)
			return;
		for(int i = 0; i < plateau.size(); i++)
		{
			for(int j = 0; j < game.get_nbCote(); j++)
			{
				if(plateau.get(i).est_pose() == true && plateau.get(i).get_halo(j).est_select() == true)
				{
					plateau.get(i).get_halo(j).set_select(false);
					int place_x = plateau.get(i).get_halo(j).get_posX();
					int place_y = plateau.get(i).get_halo(j).get_posY();
					orientation orient = plateau.get(i).get_halo(j).getOri();
					triomino_jouee.get_mino().pose_triomino(plateau.get(i).get_mino(), j);
					triomino_jouee.poser(place_x, place_y, orient);
					mino_to_front(triomino_jouee);
					plateau.add(triomino_jouee);
					int ancien_score = game.get_list_Joueur().get(tour).get_score();
					game.get_list_Joueur().get(tour).set_score(ancien_score + triomino_jouee.get_mino().somme_cote());
					update_main_joueur(tour);
					triomino_jouee = null;
					//FIN DE PARTIE
					if(game.get_list_Joueur().get(tour).get_main().isEmpty())
					{
	        			stage.addActor(new FIN(game.get_joueur_gagnant(), true , game.get_nbCote()));
					}
	        		set_tour(tour+1);
				}
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
		//update_mino();
		if(game.get_nbCote() == 2)
			interact_domino_halo();
		else if(game.get_nbCote() == 3)
			interact_triomino_halo();
		
		//CONTENU PLATEAU
		/*System.out.println("Contenue plateau :");
		for(int i = 0; i < plateau.size(); i++)
        	plateau.get(i).get_mino().affiche();*/
		
		/*if(triomino_jouee != null)
			triomino_jouee.get_mino().affiche();*/
		//Si aucun mino n'est selectionné
		if(MinoIMG.est_libre == true)
			set_visible_all_halo(false);
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
	
	public void set_tour(int val)
	{
		tour = val;
		tour %= game.get_nbJoueur();
		hud.setTour(tour);
	}
	
}
