package com.mino.minogames;

enum orientation {VERTICALE, HORIZONTALE;}

public class Mino {
	
	private int nb_cote;
	private int[] cote;
	private boolean[] dispo;
	private orientation ori;
	private boolean est_visible;
	
	public Mino(int nb_cote)
	{
		this.nb_cote = nb_cote;
		cote = new int[nb_cote];
		dispo = new boolean[nb_cote];
		for(int i = 0; i < nb_cote; i++)
			dispo[i] = true;
		ori = orientation.VERTICALE;
		est_visible = true;
	}
	
	public int get_nbcote()
	{
		return nb_cote;
	}
	
	public int get_cote(int id_cote)
	{
		try {
			return cote[id_cote];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean get_dispo(int id_dispo)
	{
		try {
			return dispo[id_dispo];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public orientation get_orientation()
	{
		return ori;
	}
	
	public boolean get_visible()
	{
		return est_visible;
	}
	
	public void set_cote(int id_cote, int val)
	{
		cote[id_cote] = val;
	}
	
	public void set_dispo(int id_dispo, boolean val)
	{
		dispo[id_dispo] = val;
	}
	
	public void set_orientation(orientation val)
	{
		ori = val;
	}
	
	public void set_visible(boolean val)
	{
		est_visible = val;
	}
	
	public void affiche()
	{
		System.out.printf("(");
		for(int i = 0; i < nb_cote; i++)
			System.out.printf(cote[i] + ",");
		System.out.print(")\n");
	}
}
