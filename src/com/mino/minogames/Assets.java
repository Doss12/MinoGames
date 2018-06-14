package com.mino.minogames;



import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public static final AssetManager manager=new AssetManager();
	public static final String domino="domino.png";
	public static final String triomino="triomino.png";
	public static final String propos="bouton_a_propos.png";
	public static final String double_Six="regle_domino.png";
	public static final String bouton_retour="bouton_retour.png";
	public static final String deroulement="deroulement.png";
	public static final String bouton_doubleSix="bouton_domino_double_six.png";
	public static final String bouton_deroulement="bouton_deroulement.png";
	public static final String menu="menu.png";
	public static final String menu_a_propos="menu_apropos.png";
	public static final AssetDescriptor<Texture> titre=new AssetDescriptor<Texture>("titre.png",Texture.class);
	
	public static void load() {
		manager.load(domino,Texture.class);
		manager.load(triomino,Texture.class);
		manager.load(propos,Texture.class);
		manager.load(double_Six,Texture.class);
		manager.load(bouton_retour,Texture.class);
		manager.load(deroulement,Texture.class);
		manager.load(bouton_doubleSix,Texture.class);
		manager.load(bouton_deroulement,Texture.class);
		manager.load(menu,Texture.class);
		manager.load(menu_a_propos,Texture.class);
		manager.load(titre);
	}
	
	public static void dispose() {
		manager.dispose();
		
	}
	

}
