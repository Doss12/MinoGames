package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Domino extends Mino {

	Texture texture = new Texture(Gdx.files.internal("domino_6.png"));
    
    public Domino(int X, int Y){
        setBounds(X,Y,texture.getWidth(),texture.getHeight());
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
        batch.draw(texture,get_posX(),get_posY());
    }
   
}