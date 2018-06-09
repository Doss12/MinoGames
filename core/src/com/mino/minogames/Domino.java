package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Domino extends Mino {
	Texture texture_cote1, texture_cote2;
    
    public Domino(int X, int Y, int cote1, int cote2){
    	switch (cote1) {
    		case 0:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_0.png"));
    			break;
    		case 1:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_1.png"));
    			break;
    		case 2:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_2.png"));
    			break;
    		case 3:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_3.png"));
    			break;
    		case 4:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_4.png"));
    			break;
    		case 5:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_5.png"));
    			break;    
    		case 6:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_6.png"));
    			break;
    		default:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_0.png"));
    			break;
    	}
    	
    	switch (cote2) {
			case 0:
				texture_cote2 = new Texture(Gdx.files.internal("domino_0.png"));
				break;
			case 1:
				texture_cote2 = new Texture(Gdx.files.internal("domino_1.png"));
				break;
			case 2:
				texture_cote2 = new Texture(Gdx.files.internal("domino_2.png"));
				break;
			case 3:
				texture_cote2 = new Texture(Gdx.files.internal("domino_3.png"));
				break;
			case 4:
				texture_cote2 = new Texture(Gdx.files.internal("domino_4.png"));
				break;
			case 5:
				texture_cote2 = new Texture(Gdx.files.internal("domino_5.png"));
				break;    
			case 6:
				texture_cote2 = new Texture(Gdx.files.internal("domino_6.png"));
				break;
			default:
				texture_cote2 = new Texture(Gdx.files.internal("domino_0.png"));
				break;
    	}
    	
        setBounds(X,Y,texture_cote1.getWidth(),texture_cote1.getHeight());
        setBounds(X,Y,texture_cote2.getWidth(),texture_cote2.getHeight());
        this.set_posX(X);
        this.set_posY(Y);
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	((Domino)event.getTarget()).action_en_cours = true;
                return true;
            }
        });
    }
    
    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(texture_cote1,get_posX(),get_posY());
        batch.draw(texture_cote2,get_posX(),get_posY()+30);
    }
}