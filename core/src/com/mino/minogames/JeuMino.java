package com.mino.minogames;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class JeuMino {
	private int nb_cote, nb_joueur;
	private ArrayList<Joueur> list_Joueur;
	private ArrayList<Mino> pioche;
	private ArrayList<Mino> plateau;

	
	public JeuMino(int nb_cote, int nb_joueur) {
		this.nb_cote = nb_cote;
		this.nb_joueur = nb_joueur;
		pioche = get_pioche();
		//Initialisation des joueurs et de leurs minos
		list_Joueur = new ArrayList<Joueur>();
		int nb_piece_par_joueur = get_nbpiece_distrib();
		for(int i = 0 ; i < nb_joueur ; i++) {
			list_Joueur.add(new Joueur(i+1));
			
			for(int j = 0; j < nb_piece_par_joueur; j++) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, pioche.size());
				Mino m = pioche.get(randomNum);
				list_Joueur.get(i).piocher(m);
				pioche.remove(randomNum);
			}
		}
	}
	
	public ArrayList<Mino> get_pioche() {
		ArrayList<Mino> pioche = new ArrayList<Mino>();
		if(nb_cote == 2) {
			int k = 0;
			for (int i = 0 ; i < 7 ; i++) {
				for (int j = i ; j < 7 ; j++) {
					pioche.add(new Mino(nb_cote));
					pioche.get(k).set_cote(0, i);
					pioche.get(k).set_cote(1, j);
					k++;
				}
			}
		}
		else {
			int x = 0;
			for (int k = 0 ; k < 6 ; k++) {
				for (int i = k ; i < 6 ; i++) {
					for (int j = i; j < 6; j++) {
						pioche.add(new Mino(nb_cote));
						pioche.get(x).set_cote(0, k);
						pioche.get(x).set_cote(1, i);
						pioche.get(x).set_cote(2, j);
						x++;
					}
				}
			}
		}
		return pioche;
	}
	
	public int get_nbpiece_distrib()
	{
		int res = 0;
		if(nb_cote == 2) {
			if(nb_joueur == 2)
				res = 7;
			else
				res = 5;
		}
		else
			res = 7;
		return res;
	}
	
	public ArrayList<Joueur> get_list_Joueur() {
		return list_Joueur;
	}
	
	public int get_nbCote() {
		return nb_cote;
	}
	
	public int get_nbJoueur() {
		return nb_joueur;
	}
	
	public Mino premier_Mino() {
		int i = 0;
		Mino plusGrand = list_Joueur.get(i).get_mino_max();
		for (i = 1; i < list_Joueur.size(); i++) {
			if (list_Joueur.get(i).get_mino_max().somme_cote() > plusGrand.somme_cote())
				plusGrand = list_Joueur.get(i).get_mino_max();
			}
		return plusGrand;
	}
	
	
	
	public Mino pose_mino_max()
	{
		Mino m = premier_Mino();
		for(int i = 0; i < list_Joueur.size(); i++)
		{
			if(list_Joueur.get(i).get_main().contains(m))
			{
				list_Joueur.get(i).jouer(m);
				//plateau.add(m);
				return m;
			}
		}
		/*for(int i = 0; i < list_Joueur.size(); i++)
			list_Joueur.get(i).affiche();*/
		return null;
	}
	
	
	public void add_mino_plateau(Mino M) {
		plateau.add(M);
	}
}
