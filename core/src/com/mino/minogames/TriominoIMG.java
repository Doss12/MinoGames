package com.mino.minogames;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TriominoIMG extends MinoIMG {
	private Texture texture;
	private BitmapFont font;
	private boolean est_inverse = false;
	
    public TriominoIMG(Mino M, int X, int Y){
    	this.M = M;
        this.set_posX(X);
        this.set_posY(Y);
        this.setAncienX(X);
        this.setAncienY(Y);
        
        update_texture();
        
        setBounds(X,Y,texture.getWidth(),texture.getHeight());
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        
        list_halo = new ArrayList<HaloIMG>();
		int nb_cote = M.get_nbcote();
		for(int i = 0; i < nb_cote; i++)
		{
			if(M.get_orientation() == orientation.HORIZONTALE)
				list_halo.add(new HaloIMG(nb_cote,i,orientation.VERTICALE));
			else
				list_halo.add(new HaloIMG(nb_cote,i,orientation.HORIZONTALE));
		}
        
		deplacer(X,Y);
		
		//SI ON CLIC EN DEHORS DUN HALO
    	clic_vide = new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if(est_select() == true && MinoIMG.est_libre == false){
					set_libre(true);
				    set_select(false);
				    deplacer(getAncienX(),getAncienY());
				}
			}
		};
    	this.addListener(clic_vide);
    }
    
    public boolean est_inverse(){
		return est_inverse;
	}
	
	public void set_inverse(boolean est_inverse) {
		this.est_inverse = est_inverse;
	}
    
    public void set_orientation(orientation val)
    {
    	super.set_orientation(val);
    	update_texture();
    }
    
    public void update_texture()
    {
    	if(M.get_orientation() == orientation.HORIZONTALE)
        	texture = new Texture(Gdx.files.internal("triomino_0.png"));
        else
        	texture = new Texture(Gdx.files.internal("triomino_1.png"));
    }
    
    
    public void deplacer(int x, int y)
    {
    	super.deplacer(x, y);
    	this.set_posX(x);
		this.set_posY(y);
		if(M.get_orientation() == orientation.HORIZONTALE)
		{
			this.list_halo.get(0).set_posX(x + 30);
	    	this.list_halo.get(0).set_posY(y);
	    	this.list_halo.get(1).set_posX(x);
	    	this.list_halo.get(1).set_posY(y-60);
	    	this.list_halo.get(2).set_posX(x-30);
	    	this.list_halo.get(2).set_posY(y);
		}
		else
		{
			this.list_halo.get(0).set_posX(x-30);
	    	this.list_halo.get(0).set_posY(y);
	    	this.list_halo.get(1).set_posX(x);
	    	this.list_halo.get(1).set_posY(y+59);
	    	this.list_halo.get(2).set_posX(x+30);
	    	this.list_halo.get(2).set_posY(y);
		}
    	setBounds(x,y,texture.getWidth(),texture.getHeight());
    	
		int halo_x = this.list_halo.get(0).get_posX();
    	int halo_y = this.list_halo.get(0).get_posY();
    	this.list_halo.get(0).setBounds(halo_x,halo_y,texture.getWidth(),texture.getHeight());
    	halo_x = this.list_halo.get(1).get_posX();
    	halo_y = this.list_halo.get(1).get_posY();
    	this.list_halo.get(1).setBounds(halo_x,halo_y,texture.getWidth(),texture.getHeight());
    	halo_x = this.list_halo.get(2).get_posX();
    	halo_y = this.list_halo.get(2).get_posY();
    	this.list_halo.get(2).setBounds(halo_x,halo_y,texture.getWidth(),texture.getHeight());
    	
    	this.list_halo.get(0).update_halo();
    	this.list_halo.get(1).update_halo();
    	this.list_halo.get(2).update_halo();
    	
    }
    
    @Override
    public void draw(Batch batch, float alpha){
    	batch.draw(texture, get_posX(), get_posY());
    	if(M.get_orientation() == orientation.HORIZONTALE)
    	{
	    	font.draw(batch, String.valueOf(M.get_cote(0)), get_posX() + 26, get_posY() + 53);
	    	font.draw(batch, String.valueOf(M.get_cote(1)), get_posX() + 45, get_posY() + 14);
	    	font.draw(batch, String.valueOf(M.get_cote(2)), get_posX() + 7, get_posY() + 14);
    	}
    	else
    	{
    		font.draw(batch, String.valueOf(M.get_cote(0)), get_posX() + 26, get_posY() + 21);
	    	font.draw(batch, String.valueOf(M.get_cote(1)), get_posX() + 7, get_posY() + 56);
	    	font.draw(batch, String.valueOf(M.get_cote(2)), get_posX() + 45, get_posY() + 56);
    	}
    }
    
	@Override
	public void act(float delta){
		if(est_select()){
        	super.set_posX(((Gdx.input.getX() - 30)*1280)/Gdx.graphics.getWidth());
        	super.set_posY(((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight());
        	//this.setPosition(super.get_posX(), super.get_posY());
        	deplacer(super.get_posX(), super.get_posY());
        }
		
	}

	public void set_mino(Mino val) {
		super.set_mino(val);
		
	}
}