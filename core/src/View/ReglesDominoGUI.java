package View;

import java.lang.ProcessBuilder.Redirect;

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

public class ReglesDominoGUI implements Screen {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture BackgroundTexture,Double_SixTexture,DeroulementTexture,TextureRetour;
	private TextureRegion TextureRegionDouble_Six,TextureRegionDeroulement,TextureRegionRetour;
	private TextureRegionDrawable TextureRegionDrawableDouble_Six,TextureRegionDrawableDeroulement,RetourDrawable;
	private ImageButton Double_SixButton,DeroulementButton,RetourButton;
    private Stage stage;
	private Game game;
	
	public ReglesDominoGUI(Game game) {
		 this.game=game;
    	 batch=new SpriteBatch();
    	 stage=new Stage();
    	 Gdx.input.setInputProcessor(stage);
    	 
    	 BackgroundTexture = new Texture(Gdx.files.internal("regle_domino.png"));
    	 Double_SixTexture = new Texture(Gdx.files.internal("bouton_domino_double_six.png"));
    	 TextureRegionDouble_Six = new TextureRegion(Double_SixTexture);
    	 TextureRegionDrawableDouble_Six = new TextureRegionDrawable(TextureRegionDouble_Six);
    	 Double_SixButton = new ImageButton(TextureRegionDrawableDouble_Six);
    	 Double_SixButton.setX(850);
    	 Double_SixButton.setY(530);
    	 DeroulementTexture = new Texture(Gdx.files.internal("bouton_domino_double_six.png"));
    	 TextureRegionDeroulement = new TextureRegion(DeroulementTexture);
    	 TextureRegionDrawableDeroulement = new TextureRegionDrawable(TextureRegionDeroulement);
    	 DeroulementButton = new ImageButton(TextureRegionDrawableDeroulement);
    	 DeroulementButton.setX(1050);
    	 DeroulementButton.setY(530);
    	 
    	 DeroulementButton.addListener( new ClickListener() {

  			@Override
  		    public void clicked(InputEvent event, float x, float y) {
  		        redirectDeroulement();
  			}
  		} );
    	 Double_SixButton.addListener( new ClickListener() {
    			@Override
      		    public void clicked(InputEvent event, float x, float y) {
    				redirectDoubleSix();
    			}
    	 });
    	 
    	 
    	
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
  		        redirect();
  		    }
  		} );
    	 
    	 
    	 stage.addActor(Double_SixButton);
    	 stage.addActor(DeroulementButton);
    	 stage.addActor(RetourButton);
    	 
		
	}
	void redirect() {
		game.setScreen(new JoueurGUI(game));
	}
	void redirectDeroulement() {
		BackgroundTexture = new Texture(Gdx.files.internal("deroulement.png"));
	}
	void redirectDoubleSix() {
		BackgroundTexture = new Texture(Gdx.files.internal("regle_domino.png"));
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		
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
		BackgroundTexture.dispose();Double_SixTexture.dispose();
		DeroulementTexture.dispose();
		TextureRetour.dispose();
	
		
	}

}
