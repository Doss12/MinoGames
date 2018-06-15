package com.mino.minogames;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HUD extends Actor {
	int posX, posY;
	private Texture texture;
	private BitmapFont font;
	private BitmapFont info_tour;
	private ArrayList<Joueur> liste_joueur;
	private int tour;
	private int nb_cote;
	public Actor piocher;
	
	public HUD(int X, int Y, ArrayList<Joueur> liste_joueur, int nb_cote){
		this.nb_cote = nb_cote;
    	font = new BitmapFont();
        font.setColor(Color.WHITE);
        this.liste_joueur = liste_joueur;
        
    	switch (liste_joueur.size()) {
    		case 2:
    			texture = new Texture(Gdx.files.internal("HUD_2.png"));
    			break;
    		case 3:
    			texture = new Texture(Gdx.files.internal("HUD_3.png"));
    			break;
    		case 4:
    			texture = new Texture(Gdx.files.internal("HUD_4.png"));
    			break;
    		default:
    			break;
    	}
    	info_tour = new BitmapFont();
    	info_tour.setColor(Color.WHITE);
    	info_tour.getData().setScale(2);
    	
    	piocher = new Actor();
    	piocher.setX(1100);
    	piocher.setY(650);
    	piocher.setBounds(piocher.getX(), piocher.getY(), 106, 46);
    	
    	
        //setBounds(X,Y,texture.getWidth(),texture.getHeight());
        this.posX = X;
        this.posY = Y;
        
    }
    public int getTour() {
		return tour;
	}


	public void setTour(int tour) {
		this.tour = tour;
	}
    
	@Override
    public void draw(Batch batch, float alpha){
		batch.draw(new Texture(Gdx.files.internal("bouton_piocher.png")), piocher.getX(), piocher.getY());
        batch.draw(texture, posX, posY);
        info_tour.draw(batch, "Tour de J"+(tour+1), 20, 700);
    	switch (liste_joueur.size()) {
			case 2:
				font.draw(batch, liste_joueur.get(0).get_pseudo(), 320, 27);
		    	font.draw(batch, liste_joueur.get(1).get_pseudo(), 320, 667);
		    	if(nb_cote == 3)
		    	{
		    		
		    	}
				break;
			case 3:
				font.draw(batch, liste_joueur.get(0).get_pseudo(), 320, 27);
		    	font.draw(batch, liste_joueur.get(1).get_pseudo(), 320, 667);
		    	font.draw(batch, liste_joueur.get(2).get_pseudo(), 17, 548);
		    	if(nb_cote == 3)
		    	{
		    		
		    	}
				break;
			case 4:
				font.draw(batch, liste_joueur.get(0).get_pseudo(), 320, 27);
		    	font.draw(batch, liste_joueur.get(1).get_pseudo(), 320, 667);
				font.draw(batch, liste_joueur.get(2).get_pseudo(), 17, 548);
		    	font.draw(batch, liste_joueur.get(3).get_pseudo(), 1167, 548);
		    	if(nb_cote == 3)
		    	{
		    		font.draw(batch,"Score : " + liste_joueur.get(0).get_score(), 345, 68);
		    		font.draw(batch,"Score : " + liste_joueur.get(1).get_score(), 345, 708);
		    		font.draw(batch,"Score : " + liste_joueur.get(2).get_score(), 42, 589);
		    		font.draw(batch,"Score : " + liste_joueur.get(3).get_score(), 1190, 589);
		    	}
				break;
			default:
				break;
    	}
    }
   
}