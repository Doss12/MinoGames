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
	private ArrayList<Joueur> liste_joueur;
    
    public HUD(int X, int Y, ArrayList<Joueur> liste_joueur){
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
    	
        setBounds(X,Y,texture.getWidth(),texture.getHeight());
        this.posX = X;
        this.posY = Y;
    }
    
    
    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture, posX, posY);
    	switch (liste_joueur.size()) {
			case 2:
				font.draw(batch, liste_joueur.get(0).get_pseudo(), 320, 27);
		    	font.draw(batch, liste_joueur.get(1).get_pseudo(), 320, 667);
				break;
			case 3:
				font.draw(batch, liste_joueur.get(0).get_pseudo(), 320, 27);
		    	font.draw(batch, liste_joueur.get(1).get_pseudo(), 320, 667);
		    	font.draw(batch, liste_joueur.get(2).get_pseudo(), 17, 548);
				break;
			case 4:
				font.draw(batch, liste_joueur.get(0).get_pseudo(), 320, 27);
		    	font.draw(batch, liste_joueur.get(1).get_pseudo(), 320, 667);
				font.draw(batch, liste_joueur.get(2).get_pseudo(), 17, 548);
		    	font.draw(batch, liste_joueur.get(3).get_pseudo(), 1167, 548);
				break;
			default:
				break;
    	}
    }
   
}