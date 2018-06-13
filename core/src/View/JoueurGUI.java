package View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener.FocusEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class JoueurGUI implements Screen  {
	private SpriteBatch batch;
	private OrthographicCamera camera;

	private Stage stage;
	private Game game;
	private Texture myTexture,addTexture,BackgroundTexture;
	ImageButton jouer,ajout_joueur1,ajout_joueur2,ajout_joueur3,ajout_joueur4;

    String [] username;
    int i=0;//incrementation du tableau des users
    public static Sprite backgroundSprite;
    String user1;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    TextField joueur1,joueur2,joueur3,joueur4;

	
		
    public JoueurGUI(Game game) {
    	this.game=game;
    	batch=new SpriteBatch();
    	stage=new Stage();
    	 Gdx.input.setInputProcessor(stage);
    	
    	myTexture = new Texture(Gdx.files.internal("play.png"));
	    myTextureRegion = new TextureRegion(myTexture);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    jouer = new ImageButton(myTexRegionDrawable); 
	    jouer.setX(400);
	    jouer.setY(100);
	    stage.addActor(jouer);
	    
	    jouer.addListener( new ClickListener() {

			@Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("ajouuut");
		        rediret();
		        
		     
		    }
		} );
	    
	    addTexture = new Texture(Gdx.files.internal("add.png"));
	    myTextureRegion = new TextureRegion(addTexture);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
	   
	   joueur1=new TextField("joueur 1 ",uiSkin);
	   joueur1.setPosition(500, 600);
	   joueur1.setSize(300, 40);
	   stage.addActor(joueur1);
	   ajout_joueur(stage);
	  
	  
	 /*  joueur2=new TextField("joueur 2",uiSkin);
	   joueur2.setPosition(500,500);
	   joueur2.setSize(300, 40);
	  
	   stage.addActor(joueur2);
	   
	 
	   
	  /* joueur3=new TextField("joueur 3 ",uiSkin);
	   joueur3.setPosition(500,400);
	   joueur3.setSize(300, 40);
	   stage.addActor(joueur3);
	  
	   joueur4=new TextField("joueur 3",uiSkin);
	   joueur4.setPosition(500,300);
	   joueur4.setSize(300, 40);
	   stage.addActor(joueur4);
	   */
	  
	    
}
	



		
	public void rediret() {
		game.setScreen(new ReglesDominoGUI(game));
	}
	
	@Override
	public void dispose() {
	
	}
	 @Override
	    public void resize(int width, int height) {
	    	
	    }

	@Override
	public void show() {
	
		
	} 



	@Override
	public void render(float delta) {
		BackgroundTexture = new Texture(Gdx.files.internal("menu.png"));
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
		batch.begin();
		batch.draw(BackgroundTexture, 0, 0);
		batch.end();
		stage.act(delta);
	
		stage.draw();
		
	}
	
	
	void ajout_joueur(final Stage stage) {
		
	 	 ajout_joueur1=new ImageButton(myTexRegionDrawable);
		   ajout_joueur1.setX(800);
		   ajout_joueur1.setY(600);
		   ajout_joueur1.setSize(50, 50);
		   
		   
		   ajout_joueur1.addListener( new ClickListener() {

					@Override
				    public void clicked(InputEvent event, float x, float y) {
				        System.out.println(joueur1.getText());
				        joueur1.setText("");
				        Gdx.input.setInputProcessor(stage);
				    }
				} );
		  
		   stage.addActor(ajout_joueur1);
		   
		   
		   ajout_joueur2=new ImageButton(myTexRegionDrawable);
		   ajout_joueur2.setX(800);
		   ajout_joueur2.setY(500);
		   ajout_joueur2.setSize(50, 50);
		   
		   
		   ajout_joueur2.addListener( new ClickListener() {

					@Override
				    public void clicked(InputEvent event, float x, float y) {
				        System.out.println(joueur2.getText());
				        joueur2.setText("");
				    }
				} );
		  
		   stage.addActor(ajout_joueur2);
		   
		/*   ajout_joueur3=new ImageButton(myTexRegionDrawable);
		   ajout_joueur3.setX(800);
		   ajout_joueur3.setY(400);
		   ajout_joueur3.setSize(50, 50);
		   
		   
		   ajout_joueur3.addListener( new ClickListener() {

					@Override
				    public void clicked(InputEvent event, float x, float y) {
				        System.out.println(joueur3.getText());
				        joueur3.setText("");
				    }
				} );
		  
		   stage.addActor(ajout_joueur3);
		   
		   
		   ajout_joueur4=new ImageButton(myTexRegionDrawable);
		   ajout_joueur4.setX(800);
		   ajout_joueur4.setY(300);
		   ajout_joueur4.setSize(50, 50);
		   
		   
		   ajout_joueur4.addListener( new ClickListener() {

					@Override
				    public void clicked(InputEvent event, float x, float y) {
				        System.out.println(joueur4.getText());
				        joueur4.setText("");
				    }
				} );
		  
		   stage.addActor(ajout_joueur4);*/
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


}
