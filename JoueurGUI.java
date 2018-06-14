package com.mino.minogames;
import com.badlogic.gdx.Files;
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


public class JoueurGUI implements Screen {
	private SpriteBatch batch;
	private Stage stage;
	private Game game;
	private Texture myTexture, addIcone, BackgroundTexture, joueurEntrer;
	ImageButton jouer, ajout_joueur;
	BitmapFont font;

	int i = 0;// incrementation du tableau des users
	public static Sprite backgroundSprite;
	private TextureRegion myTextureRegion;
	private TextureRegionDrawable myTexRegionDrawable;
	TextField joueur;
	String[] pseudoJoueur = {" "," "," "," "};
	String pseudo = "Nom Joueur";
	int x = 30, y = 500;

	public JoueurGUI(Game game) {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// Boutton Ajouter Joeur
		addIcone = new Texture(Gdx.files.internal("add.png"));
		myTextureRegion = new TextureRegion(addIcone);
		myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

		// TextField
		joueur = new TextField("", uiSkin);
		joueur.setPosition(30, 550);
		joueur.setSize(300, 40);
		stage.addActor(joueur);
		ajout_joueur(stage);

		// Nom des joueurs
		font = new BitmapFont(Gdx.files.internal("calibri.fnt"), false);

		// Boutton PLAY
		myTexture = new Texture(Gdx.files.internal("play.png"));
		myTextureRegion = new TextureRegion(myTexture);
		myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		jouer = new ImageButton(myTexRegionDrawable);
		jouer.setX(400);
		jouer.setY(100);
		stage.addActor(jouer);

		jouer.addListener(new ClickListener() {// Click sur Play Joueur

			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				System.out.println("Play");
			}
		});

	}

		public void rediret() {
		game.setScreen(new ReglesDominoGUI(game));
	}


	@Override
	public void dispose() {
		myTexture.dispose();
		addIcone.dispose();
		BackgroundTexture.dispose();
		joueurEntrer.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

		// Background
		BackgroundTexture = new Texture(Gdx.files.internal("menu.png"));
		joueurEntrer = new Texture(Gdx.files.internal("EntrerJoueur.png"));
		batch.begin();
		batch.draw(BackgroundTexture, 0, 0);

		// Titre
		batch.draw(joueurEntrer, 20, 600);

		// Nom
		switch (i) {
		case 0:
			font.draw(batch, " J1 : " + pseudoJoueur[0], 30, 500);
			break;
		case 1:
			font.draw(batch, " J2 : " + pseudoJoueur[1], 30, 450);
			font.draw(batch, " J1 : " + pseudoJoueur[0], 30, 500);
			break;
		case 2:
			font.draw(batch, " J3 : " + pseudoJoueur[2], 30, 400);
			font.draw(batch, " J2 : " + pseudoJoueur[1], 30, 450);
			font.draw(batch, " J1 : " + pseudoJoueur[0], 30, 500);
			break;
		case 3:
			font.draw(batch, " J3 : " + pseudoJoueur[2], 30, 400);
			font.draw(batch, " J2 : " + pseudoJoueur[1], 30, 450);
			font.draw(batch, " J1 : " + pseudoJoueur[0], 30, 500);
			break;
		case 4:
			font.draw(batch, " J4 : " + pseudoJoueur[3], 30, 350);
			font.draw(batch, " J3 : " + pseudoJoueur[2], 30, 400);
			font.draw(batch, " J2 : " + pseudoJoueur[1], 30, 450);
			font.draw(batch, " J1 : " + pseudoJoueur[0], 30, 500);
			break;
		default:
			;

		}

		batch.end();
		stage.act(delta);
		stage.draw();

	}

	void ajout_joueur(final Stage stage) {

		ajout_joueur = new ImageButton(myTexRegionDrawable);
		ajout_joueur.setX(300);
		ajout_joueur.setY(550);
		ajout_joueur.setSize(50, 50);

		ajout_joueur.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				pseudoJoueur[i] = joueur.getText();
				joueur.setText("");
				y -= 50;
				i++;
			}
		});

		stage.addActor(ajout_joueur);

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
