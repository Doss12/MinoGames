package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FIN extends Actor {
	private int nb_cote;
	private Joueur gagnant;
	private boolean match_nul;
	private BitmapFont info;
	
	public FIN(Joueur J, boolean est_nul, int nb_cote){
		this.gagnant = J;
		this.match_nul = est_nul;
		this.nb_cote = nb_cote;
		info = new BitmapFont();
		info.setColor(Color.WHITE);
		info.getData().setScale(4);
		this.setBounds(0, 0, 1280, 720);
	}
	
	 public void draw(Batch batch, float alpha){
		 batch.draw(new Texture(Gdx.files.internal("Fin.png")), 0, 0);
		 if(match_nul == true)
			 info.draw(batch, "Match Nul !",440, 300);
		 else
		 {
			 info.draw(batch, "J" + gagnant.get_ID() + " " + gagnant.get_pseudo() + " gagne la partie.",100,300);
			 if(nb_cote == 3)
				 info.draw(batch, "Son score : " + gagnant.get_score(), 100, 200);
		 }
	 }
}
