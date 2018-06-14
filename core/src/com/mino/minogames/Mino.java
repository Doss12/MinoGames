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

	public Mino get_Mino_dispo(int id_dispo)
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
	
	public boolean doit_inverse_droite_domino(Mino plateau)
	{
		boolean res = false;
		if(this.cote[0] == plateau.cote[1])
			res = false;
		else
			res = true;
		
		return res;
	}
	
	public boolean doit_inverse_gauche_domino(Mino plateau)
	{
		boolean res = false;
		if(this.cote[1] == plateau.cote[0])
			res = false;
		else
			res = true;
		
		return res;
	}
	
	public boolean associer_mino(Mino m, int indice_dispo_this, int indice_dispo_m)
	{
		this.set_Mino_dispo(indice_dispo_this,m);
		m.set_Mino_dispo(indice_dispo_m,this);
		if(indice_dispo_this == indice_dispo_m)
		{
			this.inverser_domino();
			return true;
		}
		return false;
	}
	
	
        
	public void set_Mino_dispo(int id_dispo, Mino M)
	{
		dispo[id_dispo] = M;
	}
        
	public void set_orientation(orientation val)
	{
		ori = val;
	}
        
	public void set_visible(boolean val)
	{
		est_visible = val;
	}
	
	public int somme_cote()
	{
		int res = 0;
		for(int i = 0; i < nb_cote; i++)
			res += cote[i];
		
		return res;
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
						System.out.println("PLUS DE PLACE SUR " + j);
				}
			}
			else
				System.out.println("PLUS DE PLACE SUR " + i);
		}
                        
		return is_compatible;
	}
                
	//Utilisée dans tester_tous_cotes: prend un mino m en paramètre et un numero de dispo, renvoie -1 si pas de disponibilité, renvoie i si dispo[i] de this est compatible
	public int tester_un_cote(Mino m,int num_dispo)
	{
		for(int i=0;i<nb_cote;i++)
		{
			if(this.dispo[i] == null)
			{
				if(this.nb_cote == 2)
				{
					if(this.cote[i] == m.cote[num_dispo])
						return i;
				}
				else
				{
					if((this.cote[i] == m.cote[(num_dispo+1)%3]) && (this.cote[(i+1)%3] == m.cote[num_dispo]))
						return i;
				}
			}
		}
		return -1;
	}
                
	//Test la compatibilité de tous les cotés d'un mino m
	//Renvoie un tableau de int[nb_cote] cotes_compaibles, tel que cotes_compatibles[i] = -1 si m.dispo[i] n'a aucune dispo
	//Sinon, cotes_compatibles[i] = j si m.dispo[i] et this.dispo[j] sont "collables" ensembles 
	//Dans tous les cas, si cotes_compatibles[i] >= 0, on peut mettre une "aura" devant
	public int[] tester_tous_cotes(Mino m)
	{
		int [] cotes_compatibles = new int[this.nb_cote];
		for(int i=0;i<this.nb_cote;i++)
			cotes_compatibles[i] = -1;
                
		if(!this.compare(m))
			System.out.println("AUCUN EMPLACEMENT DISPO");
		else
		{
			for(int i=0;i<this.nb_cote;i++)
			{
				if(m.dispo[i] == null)
				{
					System.out.println("Cote " + i + " du deuxième mino est libre");
					cotes_compatibles[i] = tester_un_cote(m,i);
				}
				else
					System.out.println("Cote " + i + " du deuxième mino n'est pas libre");
			}
		}
                
		return cotes_compatibles;
	}
	public void inverser_domino()
	{
		int temp = this.cote[0];
		this.cote[0] = this.cote[1];
		this.cote[1] = temp;
		
		Mino Mino_temp = this.dispo[0];
		this.dispo[0] = this.dispo[1];
		this.dispo[1] = Mino_temp;
	}
}
