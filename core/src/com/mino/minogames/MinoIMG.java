package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MinoIMG extends Actor{
	protected Mino M;
	private int posX, posY;
	private boolean est_libre;
	
	protected boolean action_en_cours = false;
	protected boolean action = true;
	
	public MinoIMG() {
		posX = 0;
		posY = 0;
		est_libre = true;
	}
	
	public boolean est_libre() {
		return est_libre;
	}
	
	public Mino get_mino() {
		return M;
	}
	
	public int get_posX() {
		return posX;
	}
	
	public int get_posY() {
		return posY;
	}
	
	public void set_mino(Mino val) {
		M = val;
	}
	
	public void set_posX(int X) {
		posX = X;
	}
	
	public void set_posY(int Y) {
		posY = Y;
	}
	
 /*   @Override
    public void act(float delta){
        if(action_en_cours){
        	//this.addAction(Actions.moveTo(640, 360, 10));
            //posX=Gdx.input.getX() - 15;
            //posY=Gdx.graphics.getHeight() - Gdx.input.getY() - 30;
        	posX=((Gdx.input.getX()-15)*1280)/Gdx.graphics.getWidth();
        	posY=((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight();
            this.setPosition(posX, posY);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            	action_en_cours = false;
            }
        }
    }*/

	/*public boolean addListener(boolean action) {
		// TODO Auto-generated method stub
		if(action_en_cours){
        	//this.addAction(Actions.moveTo(640, 360, 10));
            //posX=Gdx.input.getX() - 15;
            //posY=Gdx.graphics.getHeight() - Gdx.input.getY() - 30;
        	posX=((Gdx.input.getX()-15)*1280)/Gdx.graphics.getWidth();
        	posY=((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight();
            this.setPosition(posX, posY);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            	action_en_cours = false;
            	action=false;
            	return action;
            }
            
        }
		return action;
	}*/
	
	public int addListener(int i) {
		// TODO Auto-generated method stub
		if(action_en_cours){
        	posX=((Gdx.input.getX()-15)*1280)/Gdx.graphics.getWidth();
        	posY=((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight();
            this.setPosition(posX, posY);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            	action_en_cours = false;
            	if (i==3)
            		i=0;
            	else 
            		i=i+1;
            	return i;
            }
            
        }
		return i;
	}
    
    

}