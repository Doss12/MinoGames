package com.mino.minogames;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class MinoIMG extends Actor{
	protected Mino M;
	private int posX, posY;
	private int ancienX, ancienY;
	public static boolean est_libre = true;
	private boolean est_select, est_pose, est_jouable;
	protected ArrayList<HaloIMG> list_halo;
	EventListener clic_main;
	
	public MinoIMG() {
		
		posX = 0;
		posY = 0;
		est_select = false;
		est_pose = false;
		//Pour les tours
		est_jouable = true;
	}
	
	public boolean est_libre() {
		return est_libre;
	}
	
	public boolean est_select() {
		return est_select;
	}
	
	public boolean est_pose() {
		return est_pose;
	}
	
	public boolean est_jouable() {
		return est_jouable;
	}
	
	public void set_jouable(boolean val) {
		est_jouable = val;
	}
	
	public void set_pose(boolean val) {
		est_pose = val;
	}
	
	public void poser(int x, int y, orientation ori){
		set_libre(true);
	    set_select(false);
		est_pose = true;
		set_posX(x);
		set_posY(y);
		set_orientation(ori);
		this.removeListener(clic_main);
		deplacer(x,y);
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
	
	public void set_libre(boolean val) {
		est_libre = val;
	}
	
	public void set_select(boolean val) {
		this.est_select = val;
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
	
	public HaloIMG get_halo(int id_cote) {
		return list_halo.get(id_cote);
	}
	
	public void set_halo_visible(int id_cote, boolean val)
	{
		if(est_select == false)
			list_halo.get(id_cote).set_visible(val);
	}
	
	public void deplacer(int x, int y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	public void posable()
	{
		for(int i = 0; i < M.get_nbcote(); i++)
			list_halo.get(i).posable();
	}
	
	public void set_orientation(orientation val)
	{
		M.set_orientation(val);
		for(int i = 0; i < M.get_nbcote(); i++)
		{
			if(M.get_nbcote() == 3)
			{
				if(M.get_orientation() == orientation.VERTICALE)
					list_halo.get(i).setOri(orientation.HORIZONTALE);
				else
					list_halo.get(i).setOri(orientation.VERTICALE);
			}
			else
				list_halo.get(i).setOri(val);
			list_halo.get(i).update_halo();
		}
		deplacer(posX,posY);
	}
}