package com.mino.minogames;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HUD extends Actor {
	int posX, posY;
	Texture texture;
    
    public HUD(int X, int Y, int nb_joueur){
    	switch (nb_joueur) {
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
    			texture = new Texture(Gdx.files.internal("HUD_2.png"));
    			break;
    	}
    	
        setBounds(X,Y,texture.getWidth(),texture.getHeight());
        this.posX = X;
        this.posY = Y;
    }
    
    
    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture,posX,posY);
    }
   
}