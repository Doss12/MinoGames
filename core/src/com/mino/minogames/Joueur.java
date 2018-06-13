package com.mino.minogames;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Orientation;


public class Joueur {
	
	private int id;
	private String pseudo;
	private int score;
	private boolean est_IA;
	private ArrayList<Mino> main;
	
	public Joueur(int id)
	{
		this.id = id;
		this.pseudo = "";
		this.est_IA = false;
		main = new ArrayList<Mino>();
	}
	
	public int get_ID()
	{
		return id;
	}
	
	public String get_pseudo()
	{
		return pseudo;
	}
	
	public int get_score()
	{
		return score;
	}
	
	public boolean get_IA()
	{
		return est_IA;
	}
	
	public ArrayList<Mino> get_main()
	{
		return main;
	}
	
	public void set_id(int val)
	{
		id = val;
	}
	
	public void set_pseudo(String chaine)
	{
		pseudo = chaine;
	}
	
	public void set_score(int val)
	{
		score = val;
	}
	
	public void set_IA(boolean val)
	{
		est_IA = val;
	}
	
	public void piocher(Mino m)
	{
		if(id > 2)
			m.set_orientation(Mino.orientation.HORIZONTALE);
		main.add(m);
	}
	
	public void affiche()
	{
		System.out.println("J"+id+" "+pseudo+" score : "+score+" est_IA : "+est_IA);
		for(int i = 0; i < main.size(); i++)
			main.get(i).affiche();
	}
	
}
