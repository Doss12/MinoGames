package com.mino.minogames;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mino.minogames.Mino;

public class MinoIMG extends Actor{
	protected Mino M;
	private int posX, posY;
	private int ancienX, ancienY;
	private boolean est_libre;

	protected boolean action_en_cours = false;

	public MinoIMG() {
		posX = 0;
		posY = 0;
		est_libre = true;
	}

	public boolean est_libre() {
		return est_libre;
	}

	public Mino get_mino() {
		return M;
	}

	public int get_posX() {
		return posX;
	}

	public int get_posY() {
		return posY;
	}

	public void set_mino(Mino val) {
		M = val;
	}

	public void set_posX(int X) {
		posX = X;
	}

	public void set_posY(int Y) {
		posY = Y;
	}

	public int getAncienX() {
		return ancienX;
	}

	public void setAncienX(int ancienX) {
		this.ancienX = ancienX;
	}

	public int getAncienY() {
		return ancienY;
	}

	public void setAncienY(int ancienY) {
		this.ancienY = ancienY;
	}
}
