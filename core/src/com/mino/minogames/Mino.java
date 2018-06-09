package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mino extends Actor {
	private int cote1, cote2, posX, posY;
	private boolean est_libre;
	
	public boolean action_en_cours = false;
	
	public Mino() {
		cote1 = 0;
		cote2 = 0;
		est_libre = true;
	}
	
	public int get_cote1() {
		return cote1;
	}
	
	public int get_cote2() {
		return cote2;
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
	
	public void set_posX(int X) {
		posX = X;
	}
	
	public void set_posY(int Y) {
		posY = Y;
	}
	
	public void set_cote1(int val) {
		cote1 = val;
	}
	
	public void set_cote2(int val) {
		cote2 = val;
	}
	
    @Override
    public void act(float delta){
        if(action_en_cours){
            posX=Gdx.input.getX() - 15;
            posY=Gdx.graphics.getHeight() - Gdx.input.getY() - 30;

            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            	action_en_cours = false;
            }
        }
    }
}