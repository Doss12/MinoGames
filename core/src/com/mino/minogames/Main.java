package com.mino.minogames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Main extends ApplicationAdapter {
	
    public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("domino_6.png"));
        float actorX, actorY;
        public boolean started = false;

        public MyActor(int X, int Y){
            setBounds(X,Y,texture.getWidth(),texture.getHeight());
            this.actorX = X;
            this.actorY = Y;
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    ((MyActor)event.getTarget()).started = true;
                    return true;
                }
            });
        }
        
        
        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,actorX,actorY);
        }
        
        @Override
        public void act(float delta){
            if(started){
                actorX+=10;
            }
        }
    }
	
	SpriteBatch batch;
	Texture HUD;
	private Stage stage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		HUD = new Texture("HUD_Deux_Joueurs.png");
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        MyActor myActor = new MyActor(500, 500);
        myActor.setTouchable(Touchable.enabled);
        stage.addActor(myActor);
        
        MyActor domino_6 = new MyActor(300, 300);
        domino_6.setTouchable(Touchable.enabled);
        stage.addActor(domino_6);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(60/255f, 68/255f, 79/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
		batch.begin();
		batch.draw(HUD, 0, 0);
		batch.end();
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
		HUD.dispose();
	}
	
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}