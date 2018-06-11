package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HaloIMG extends Actor {
	private Texture texture;
	private int X;
	private int Y;
    
    public HaloIMG(MinoIMG M){
    	switch (M.get_mino().get_nbcote()) {
    		case 2:
    			texture = new Texture(Gdx.files.internal("halo_2.png"));
    			break;
    		case 3:
    			texture = new Texture(Gdx.files.internal("halo_3.png"));
    			break;
    		default:
    			break;
    	}
    	
    	X = M.get_posX() + 60;
    	Y = M.get_posY();
    }
    
    @Override
    public void draw(Batch batch, float alpha){
    	batch.draw(texture,X,Y);
    }

}