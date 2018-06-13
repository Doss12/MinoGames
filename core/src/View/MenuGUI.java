package View;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class MenuGUI implements Screen {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Stage stage;
	private Game game;
	private Texture trioTexture,domTexture,BackgroundTexture,a_proposTexture;
	private TextureRegion TextureDomRegion,TextureTrioRegion,TextureProposRegion;
	private TextureRegionDrawable TrioDrawable,DominoDrawable,ProposDrawable;
	private ImageButton dominoButton,triominoButton,a_propos;
	BitmapFont font;
	
	
	public MenuGUI(Game game) {
		super();
		this.game = game;
		stage = new Stage();
		batch=new SpriteBatch();
		Gdx.input.setInputProcessor(stage);

		font=new BitmapFont(Gdx.files.internal("calibri.fnt"));
		font.getData().setScale(2.0f);
		
		domTexture = new Texture(Gdx.files.internal("domino.png"));
		TextureDomRegion = new TextureRegion(domTexture);
		DominoDrawable = new TextureRegionDrawable(TextureDomRegion);
	    dominoButton = new ImageButton(DominoDrawable);
	    dominoButton.setX(400);
	    dominoButton.setY(400);
	    stage.addActor(dominoButton);
		dominoButton.addListener( new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("hiii");
		        rediretJoueur();
		    }
		} );
	    
	    trioTexture = new Texture(Gdx.files.internal("triomino.png"));
	    TextureTrioRegion = new TextureRegion(trioTexture);
	    TrioDrawable = new TextureRegionDrawable(TextureTrioRegion);
	    triominoButton = new ImageButton(TrioDrawable);
	    triominoButton.setX(400);
	    triominoButton.setY(200);
	    stage.addActor(triominoButton);
	    
	    a_proposTexture = new Texture(Gdx.files.internal("bouton_a_propos.png"));
	    TextureProposRegion = new TextureRegion(a_proposTexture);
	    ProposDrawable = new TextureRegionDrawable(TextureProposRegion);
	    a_propos = new ImageButton(ProposDrawable);
		a_propos.setX(750);
		a_propos.setY(50);
		a_propos.addListener( new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("propos");
		        rediretPropos();
		    }
		} );
		
		
		stage.addActor(a_propos);
	}
	public void rediretJoueur() {
		game.setScreen(new JoueurGUI(game));
	}
	
	public void rediretPropos() {
		game.setScreen(new A_proposGUI(game));
	}
		
	@Override
	public void dispose () {
	
		batch.dispose();
		trioTexture.dispose();
		domTexture.dispose();
		BackgroundTexture.dispose();
		a_proposTexture.dispose();
		font.dispose();
		
		
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
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		BackgroundTexture = new Texture(Gdx.files.internal("menu.png"));
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
		batch.begin();
		
		batch.draw(BackgroundTexture, 0, 0);
		font.draw(batch,"MinoGames", Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2+250);
		
		batch.end();
		stage.act(delta);
	
		stage.draw();
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


	


	
}





