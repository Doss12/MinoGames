package View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class A_proposGUI implements Screen {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture BackgroundTexture,TextureRetour;
	private TextureRegion TextureRegionRetour;
	private TextureRegionDrawable RetourDrawable;
	private ImageButton RetourButton;

	private Stage stage;
	private Game game;
	
	public A_proposGUI(Game game) {
		this.game=game;
		stage=new Stage();
    	batch=new SpriteBatch();
    	stage=new Stage();
    	 Gdx.input.setInputProcessor(stage);
    	 TextureRetour = new Texture(Gdx.files.internal("bouton_retour.png"));
    	 TextureRegionRetour = new TextureRegion(TextureRetour);
    	 RetourDrawable = new TextureRegionDrawable(TextureRegionRetour);
    	 RetourButton = new ImageButton(RetourDrawable);
    	 RetourButton.setX(50);
    	 RetourButton.setY(600);
    	 RetourButton.addListener( new ClickListener() {
 			@Override
 		    public void clicked(InputEvent event, float x, float y) {
 		        System.out.println("ajouuut");
 		        rediret();
 		    }
 		} );
    	 
    	 
    	 
 	    stage.addActor(RetourButton);
		
	}
	private void rediret() {
		game.setScreen(new MenuGUI(game));
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void render(float delta) {
		BackgroundTexture = new Texture(Gdx.files.internal("menu_apropos.png"));
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
		batch.begin();
		batch.draw(BackgroundTexture, 0, 0);
		batch.end();
		stage.act(delta);
	
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		 batch.dispose();
		 BackgroundTexture.dispose();
		 TextureRetour.dispose();
		
		
	}

}
