package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TriominoIMG extends MinoIMG {
	Texture texture;
    
    public TriominoIMG(Mino M, int X, int Y){
    	this.M = M;
        this.set_posX(X);
        this.set_posY(Y);
        
        texture = new Texture(Gdx.files.internal("triomino_0.png"));
        setBounds(X,Y,texture.getWidth(),texture.getHeight());
        
        
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	((TriominoIMG)event.getTarget()).action_en_cours = true;
                return true;
            }
        });
    }
    
    @Override
    public void draw(Batch batch, float alpha){
    	batch.draw(texture,get_posX(),get_posY());
    }
    
	@Override
	public void act(float delta){
    	if(action_en_cours){
        	super.set_posX(((Gdx.input.getX() - 30)*1280)/Gdx.graphics.getWidth());
        	super.set_posY(((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight());
        	this.setPosition(super.get_posX(), super.get_posY());
        	if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
        		action_en_cours = false;
        	}
        }
	}
}