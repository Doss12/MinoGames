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
	private ArrayList<ArrayList<MinoIMG>> mino_joueur;
	private ArrayList<MinoIMG> plateau;
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
        affiche_joueur();
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
			plateau.add(new DominoIMG(game.pose_mino_max(), 640, 360));
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
		HUD hud = new HUD(0, 0, game.get_list_Joueur());
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
				
                //LE PREMIER CLIC POUR SELECTIONNER LE DOMINO
				final int clic1_i = i, clic1_j = j;
                mino_joueur.get(i).get(j).addListener(new ClickListener() {
                	public void clicked(InputEvent event, float x, float y)  {
                		System.out.println("CLIC 1");
                		if(MinoIMG.est_libre == true && mino_joueur.get(clic1_i).get(clic1_j).est_jouable())
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
				mino_joueur.get(i).get(j).set_orientation(orientation.HORIZONTALE);
				//LE PREMIER CLIC POUR SELECTIONNER LE TRIOMINO
				final int clic1_i = i, clic1_j = j;
                mino_joueur.get(i).get(j).addListener(new ClickListener() {
                	public void clicked(InputEvent event, float x, float y)  {
                		System.out.println("CLIC 1");
                		if(MinoIMG.est_libre == true && mino_joueur.get(clic1_i).get(clic1_j).est_jouable())
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
                mino_joueur.get(i).get(j).toFront();
				this.ajout_mino_stage(mino_joueur.get(i).get(j));
			}
		}
	}
	
	public void set_visible_all_halo(boolean val)
	{
		for(int i = 0; i < plateau.size(); i++)
		{
			for(int j = 0; j < plateau.get(i).get_mino().get_nbcote(); j++)
				plateau.get(i).set_halo_visible(j, val);
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
		
		/*System.out.println("Plateau contient :");
		for(int i = 0; i < plateau.size(); i++)
        	plateau.get(i).get_mino().affiche();*/
		//game.get_list_Joueur().get(0).affiche();
        
	}
	
	public void interact_domino_halo()
	{
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
					if(j == 1 && game.get_nbCote() == 2)
					{
						if(domino_jouee.get_mino().doit_inverse_droite_domino(plateau.get(i).get_mino()))
							domino_jouee.inverser_cote();
					}
					if(j == 0 && game.get_nbCote() == 2)
					{
						if(domino_jouee.get_mino().doit_inverse_gauche_domino(plateau.get(i).get_mino()))
							domino_jouee.inverser_cote();
					}
					domino_jouee.poser(place_x, place_y, orient);
					mino_to_front(domino_jouee);
					plateau.add(domino_jouee);
					domino_jouee = null;
					//System.out.println("POSE ? "+ plateau.get(i).est_pose());
				}
			}
		}
	}
	
	public void interact_triomino_halo()
	{
		
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
		
		/*if(mino_jouee != null)
			mino_jouee.get_mino().affiche();*/
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
	
	
	public void test_compatibilite_mino_2cote()
    {
    	Mino m1 = new Mino(2);
		m1.set_cote(0,3);
		m1.set_cote(1,1);
		
		Mino m2 = new Mino(2);
		m2.set_cote(0,1);
		m2.set_cote(1,3);
		
		if(m1.compare(m2)== true)
		{
			System.out.println("COMPATIBLES\n");
		}
		else
		{
			System.out.println("PAS COMPATIBLES\n");
		}
    }
    
	public void test_compatibilite_mino_3cote()
    {
    	Mino m1 = new Mino(3);
		m1.set_cote(0,3);
		m1.set_cote(1,5);
		m1.set_cote(2,4);
		Mino m2 = new Mino(3);
		m2.set_cote(0,5);
		m2.set_cote(1,3);
		m2.set_cote(2,4);
		
		if(m1.compare(m2)== true)
		{
			System.out.println("COMPATIBLES\n");
		}
		else
		{
			System.out.println("PAS COMPATIBLES\n");
		}
    }

    
	public void test_association_Minos_2cote()
    {
    	Mino m1 = new Mino(2);
		m1.set_cote(0,3);
		m1.set_cote(1,1);
		
		Mino m2 = new Mino(2);
		m2.set_cote(0,3);
		m2.set_cote(1,2);
		
		int[] cotes_dispo = new int[2];
		
		for(int i =0;i<2;i++)
		{
			System.out.println(m2.get_Mino_dispo(i));
		}
		
		cotes_dispo = m1.tester_tous_cotes(m2);
		
		for(int i =0;i<2;i++)
		{
			System.out.println(i + " : " + cotes_dispo[i]);
		}
		
		for(int i= 0;i<2;i++)
		{
			if(cotes_dispo[i]!=-1)
			{
				m1.associer_mino(m2, cotes_dispo[i], i);
			}
		}
		
		System.out.println("M1 IS : " + m1.get_cote(0) + " , " + m1.get_cote(1));
		System.out.println("M2 IS : " + m2.get_cote(0) + " , " + m2.get_cote(1));
    }
    
	public void test_association_Minos_3cote()
    {
    	Mino m1 = new Mino(3);
		m1.set_cote(0,5);
		m1.set_cote(1,0);
		m1.set_cote(2,4);
		
		Mino m2 = new Mino(3);
		m2.set_cote(0,5);
		m2.set_cote(1,3);
		m2.set_cote(2,0);
		
		int[] cotes_dispo = new int[3];
		
		for(int i =0;i<3;i++)
		{
			System.out.println(m2.get_Mino_dispo(i));
		}
		
		cotes_dispo = m1.tester_tous_cotes(m2);
				
		for(int i =0;i<3;i++)
		{
			System.out.println(i + " : " + cotes_dispo[i]);
		}
		
		for(int i =0;i<3;i++)
		{
			if(m1.get_Mino_dispo(i)!= null)
				System.out.println("M1 DISPO " + i + " OCCUPIED");
			else
				System.out.println("M2 DISPO " + i + " FREE");
			if(m2.get_Mino_dispo(i)!= null)
				System.out.println("M2 DISPO " + i + " OCCUPIED");
			else
				System.out.println("M2 DISPO " + i + " FREE");
		}
    	
    }
}
