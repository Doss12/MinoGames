package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DominoIMG extends MinoIMG {
	private Texture texture_cote1, texture_cote2;
	private HaloIMG halo_cote1, halo_cote2;
    
    public DominoIMG(Mino M, int X, int Y){
    	this.M = M;
        this.set_posX(X);
        this.set_posY(Y);
        this.setAncienX(X);
        this.setAncienY(Y);
        
    	switch (M.get_cote(0)) {
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
    	
    	switch (M.get_cote(1)) {
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
    	
    	if(M.get_orientation() == orientation.VERTICALE)
    		setBounds(X,Y,texture_cote1.getWidth(),texture_cote1.getHeight()*2);
    	else
    		setBounds(X,Y,texture_cote1.getWidth()*2,texture_cote1.getHeight());
    	
        addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y)  {
        		((DominoIMG)event.getTarget()).action_en_cours = true;
        	}
        });
    }
    
    @Override
    public void draw(Batch batch, float alpha){
    	if(M.get_orientation() == orientation.VERTICALE) {
	        batch.draw(texture_cote1,get_posX(),get_posY());
	        batch.draw(texture_cote2,get_posX(),get_posY()+30);
	        
    	}
    	else {
    		batch.draw(texture_cote1,get_posX(),get_posY());
	        batch.draw(texture_cote2,get_posX()+30,get_posY());
    	}
    }

	@Override
	public void act(float delta){
    	if(action_en_cours){
        	if(M.get_orientation() == orientation.VERTICALE) {
        		super.set_posX(((Gdx.input.getX() - 15)*1280)/Gdx.graphics.getWidth());
        		super.set_posY(((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight());
        	}
        	else {
        		super.set_posX(((Gdx.input.getX() - 30)*1280)/Gdx.graphics.getWidth());
        		super.set_posY(((Gdx.graphics.getHeight() - Gdx.input.getY() - 15)*720)/Gdx.graphics.getHeight());
        	}
        	this.setPosition(super.get_posX(), super.get_posY());
        	
			addListener(new ClickListener() {
				
				public void clicked(InputEvent event, float x, float y) {
					if (Gdx.input.getY() > 110 && Gdx.input.getY() < 610 && Gdx.input.getX() > 250
							&& Gdx.input.getX() < 830) {
						action_en_cours = false;
					}
					else {
						set_posX(getAncienX());
						set_posY(getAncienY());
				    	if(M.get_orientation() == orientation.VERTICALE)
				    		setBounds(getAncienX(),getAncienY(),texture_cote1.getWidth(),texture_cote1.getHeight()*2);
				    	else
				    		setBounds(getAncienX(),getAncienY(),texture_cote1.getWidth()*2,texture_cote1.getHeight());
						action_en_cours = false;
					}
				};
			});
        }
	}
}