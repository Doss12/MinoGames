package com.mino.minogames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HaloIMG extends Actor {
	private Texture texture;
	private orientation ori;
	private int X,Y;
	private int id_cote;
	private int nb_cote;
	private boolean est_visible;
	private boolean est_select;
    
    public HaloIMG(int nb_cote, int cote_dispo, orientation ori){
    	this.ori = ori;
    	this.nb_cote = nb_cote;
    	est_select = false;
    	est_visible = false;
    	id_cote = cote_dispo;
    	switch (nb_cote) {
    		case 2:
    			texture = new Texture(Gdx.files.internal("halo_2.png"));
    			if(ori == orientation.VERTICALE)
    				texture = new Texture(Gdx.files.internal("halo_2_verticale.png"));
    			break;
    		case 3:
    			texture = new Texture(Gdx.files.internal("halo_3.png"));
    			if(ori == orientation.VERTICALE)
    				texture = new Texture(Gdx.files.internal("halo_3_reverse.png"));
    			break;
    		default:
    			break;
    	}
    	X = 0;
    	Y = 0;
    	posable();
    	/*if (cote_dispo == 0) { // halo à gauche
    		X = M.get_posX() - 60;
    		Y = M.get_posY();
    	}
    	else { // halo à droite
    		X = M.get_posX() + 60;
    		Y = M.get_posY();
    	}*/
    	/*addListener(new ClickListener() {
        	public void clicked(InputEvent event, float x, float y)  {
        		System.out.println("Clic sur premier mino");
		}});*/
    }
    
    public int get_posX() {
		return X;
	}

	public void set_posX(int x) {
		X = x;
	}

	public int get_posY() {
		return Y;
	}

	public void set_posY(int y) {
		Y = y;
	}
    
    public boolean get_visible()
    {
    	return est_visible;
    }
    
    public void set_visible(boolean val)
    {
    	est_visible = val;
    }
    
    public boolean est_select()
    {
    	return est_select;
    }
    
    public void set_select(boolean val)
    {
    	est_select = val;
    }
    
    public orientation getOri() {
		return ori;
	}

	public void setOri(orientation ori) {
		this.ori = ori;
	}
    
	
    public void update_halo()
    {
    	switch (nb_cote) {
		case 2:
			texture = new Texture(Gdx.files.internal("halo_2.png"));
			if(ori == orientation.VERTICALE)
				texture = new Texture(Gdx.files.internal("halo_2_verticale.png"));
			break;
		case 3:
			texture = new Texture(Gdx.files.internal("halo_3.png"));
			if(ori == orientation.VERTICALE)
				texture = new Texture(Gdx.files.internal("halo_3_reverse.png"));
			break;
		default:
			break;
    	}
    	this.toBack();
    }
    
    public void posable()
    {
    	addListener(new ClickListener() {
        	public void clicked(InputEvent event, float x, float y)  {
        		if(est_visible == true && est_select == false)
        		{
        			((HaloIMG)event.getTarget()).set_visible(false);
        			((HaloIMG)event.getTarget()).set_select(true);
        			System.out.println("Coté selectionné "+id_cote+" !");
        		}
        		else
        			System.out.println("CLICK SUR COTE"+id_cote+" INVISIBLE");
        	}
		});
    }
    
    public void selectionne()
    {
    	if(est_visible == true)
    		est_select = true;
    	System.out.println("Coté selectionné !");
    }
    
    public int getId_cote() {
		return id_cote;
	}

    
    @Override
    public void draw(Batch batch, float alpha){
    	if(est_visible == true)
    		batch.draw(texture,X,Y);
    }

}