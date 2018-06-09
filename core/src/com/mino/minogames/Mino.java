package com.mino.minogames;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mino extends Actor {
	private int cote;
	private boolean est_libre;
	private float posX, posY;
	
	public boolean action_en_cours = false;
	
	public Mino() {
		cote = 0;
		est_libre = true;
	}
	
	public int get_cote() {
		return cote;
	}
	
	public boolean est_libre() {
		return est_libre;
	}
	
	public float get_posX() {
		return posX;
	}
	
	public float get_posY() {
		return posY;
	}
	
	public void set_posX(float X) {
		posX = X;
	}
	
	public void set_posY(float Y) {
		posY = Y;
	}
	
    @Override
    public void act(float delta){
        if(action_en_cours){
            posX+=10;
        }
    }
}