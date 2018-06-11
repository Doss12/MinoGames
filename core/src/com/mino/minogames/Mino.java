package com.mino.minogames;

enum orientation {VERTICALE, HORIZONTALE;}

public class Mino {
	
	private int nb_cote;
	private int[] cote;
	private Mino[] dispo;
	private orientation ori;
	private boolean est_visible;
	
	public Mino(int nb_cote)
	{
		this.nb_cote = nb_cote;
		cote = new int[nb_cote];
		dispo = new Mino[nb_cote];
		for(int i = 0; i < nb_cote; i++)
			dispo[i] = null;
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
	
	public Mino get_dispo(int id_dispo)
	{
		try {
			return dispo[id_dispo];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return dispo[id_dispo];
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
	
	public void set_dispo(int id_dispo, Mino m)
	{
		dispo[id_dispo] = m;
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
	
	//fonction qui prend un mino m en paramètre, et vérifie que l'on peut poser m à côté de notre mino
	public boolean compare(Mino m)
	{
		boolean is_compatible = false;
		
		for(int i=0;i<this.nb_cote;i++)
		{
			if(this.dispo[i]==null)
			{
				for(int j=0;j<m.nb_cote;j++)
				{
					if(dispo[j]==null)
					{
						//si on traite un domino
						if(this.nb_cote == 2)
						{
							if(this.cote[i]==m.cote[j])
							{
								System.out.println("COTE COMPATIBLES : " + this.cote[i] +  " & " + m.cote[j]);
								return true;
							}
						}
						//si on traite un triomino
						else
						{
							if(this.dispo[i] == m.dispo[j])
							{
								if((this.cote[i] == m.cote[(j+1)%3]) && (this.cote[(i+1)%3] == m.cote[j]))
								{
									System.out.println("COTE COMPATIBLES : " + this.cote[i]+ "," + this.cote[(i+1)%3] +  " & "
												+  m.cote[(j+1)%3] + "," + m.cote[j]);
									return true;
								}
							}
						}
					}
					else
					{
						System.out.println("PLUS DE PLACE SUR " + j);
					}
				}
			}
			else
			{
				System.out.println("PLUS DE PLACE SUR " + i);
			}	
		}
		
		return is_compatible;
	}
}
