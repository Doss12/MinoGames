package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MinoIMG extends Actor{
	protected Mino M;
	private int posX, posY;
	private boolean est_libre;
	
	protected boolean action_en_cours = false;
	
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
	
    @Override
    public void act(float delta){
    	int pozInitX=posX;
    	int pozInitY=posY;
        if(action_en_cours){
        	//this.addAction(Actions.moveTo(640, 360, 10));
            //posX=Gdx.input.getX() - 15;
            //posY=Gdx.graphics.getHeight() - Gdx.input.getY() - 30;
        	posX=((Gdx.input.getX()-15)*1280)/Gdx.graphics.getWidth();
        	posY=((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight();
            this.setPosition(posX, posY);
            addListener(new ClickListener(){
            	
                public void clicked(InputEvent event, float x, float y)  {
                	System.out.println("le x="+Gdx.input.getX()+" le y ="+Gdx.input.getY());
                	if (Gdx.input.getY()>110 && Gdx.input.getY()<610 && Gdx.input.getX()>250 && Gdx.input.getX()<830)
                	{
                		((DominoIMG)event.getTarget()).action_en_cours = false;
                	}
                	
                };
            });
        }
    }
    

}