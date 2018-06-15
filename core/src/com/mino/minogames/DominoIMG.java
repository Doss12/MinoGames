package com.mino.minogames;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DominoIMG extends MinoIMG {
	private Texture texture_cote1, texture_cote2;
	private boolean est_inverse = false;
   

	public DominoIMG(Mino M, int X, int Y){
    	this.M = M;
        this.set_posX(X);
        this.set_posY(Y);
        this.setAncienX(X);
        this.setAncienY(Y);
        
        update_texture();
    	
    	if(M.get_orientation() == orientation.VERTICALE)
    		setBounds(X,Y,texture_cote1.getWidth(),texture_cote1.getHeight()*2);
    	else
    		setBounds(X,Y,texture_cote1.getWidth()*2,texture_cote1.getHeight());
    	
    	list_halo = new ArrayList<HaloIMG>();
		int nb_cote = M.get_nbcote();
		for(int i = 0; i < nb_cote; i++)
			list_halo.add(new HaloIMG(nb_cote,i,M.get_orientation()));
		
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
	
    public void update_texture()
    {
    	switch (M.get_cote(0)) {
    		case 0:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_0.png"));
    			break;
    		case 1:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_1.png"));
    			break;
    		case 2:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_2.png"));
    			break;
    		case 3:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_3.png"));
    			break;
    		case 4:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_4.png"));
    			break;
    		case 5:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_5.png"));
    			break;    
    		case 6:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_6.png"));
    			break;
    		default:
    			texture_cote1 = new Texture(Gdx.files.internal("domino_0.png"));
    			break;
    	}
    	
    	switch (M.get_cote(1)) {
			case 0:
				texture_cote2 = new Texture(Gdx.files.internal("domino_0.png"));
				break;
			case 1:
				texture_cote2 = new Texture(Gdx.files.internal("domino_1.png"));
				break;
			case 2:
				texture_cote2 = new Texture(Gdx.files.internal("domino_2.png"));
				break;
			case 3:
				texture_cote2 = new Texture(Gdx.files.internal("domino_3.png"));
				break;
			case 4:
				texture_cote2 = new Texture(Gdx.files.internal("domino_4.png"));
				break;
			case 5:
				texture_cote2 = new Texture(Gdx.files.internal("domino_5.png"));
				break;    
			case 6:
				texture_cote2 = new Texture(Gdx.files.internal("domino_6.png"));
				break;
			default:
				texture_cote2 = new Texture(Gdx.files.internal("domino_0.png"));
				break;
    	}
    }
    
    public void inverser_cote()
    {
    	if(est_inverse == false)
    	{
	    	Texture tmp = texture_cote2;
	    	texture_cote1 = texture_cote2;
	    	texture_cote2 = tmp;
	    	M.inverser_domino();
	    	update_texture();
	    	est_inverse = true;
    	}
    }
    
    public void deplacer(int x, int y)
    {
    	super.deplacer(x, y);
    	this.set_posX(x);
		this.set_posY(y);
    	if(M.get_orientation() == orientation.VERTICALE)
    	{
        	this.list_halo.get(0).set_posX(x);
        	this.list_halo.get(0).set_posY(y-60);
        	this.list_halo.get(1).set_posX(x);
        	this.list_halo.get(1).set_posY(y+60);
    		setBounds(x,y,texture_cote1.getWidth(),texture_cote1.getHeight()*2);
    		int halo_x = this.list_halo.get(0).get_posX();
        	int halo_y = this.list_halo.get(0).get_posY();
        	this.list_halo.get(0).setBounds(halo_x, halo_y, texture_cote1.getWidth(), texture_cote1.getHeight()*2);
        	halo_x = this.list_halo.get(1).get_posX();
        	halo_y = this.list_halo.get(1).get_posY();
        	this.list_halo.get(1).setBounds(halo_x, halo_y, texture_cote1.getWidth(), texture_cote1.getHeight()*2);
    	}
    	else
    	{
        	this.list_halo.get(0).set_posX(x - 60);
        	this.list_halo.get(0).set_posY(y);
        	this.list_halo.get(1).set_posX(x + 60);
        	this.list_halo.get(1).set_posY(y);
    		setBounds(x,y,texture_cote1.getWidth()*2,texture_cote1.getHeight());
    		int halo_x = this.list_halo.get(0).get_posX();
        	int halo_y = this.list_halo.get(0).get_posY();
        	this.list_halo.get(0).setBounds(halo_x, halo_y, texture_cote1.getWidth()*2, texture_cote1.getHeight());
        	halo_x = this.list_halo.get(1).get_posX();
        	halo_y = this.list_halo.get(1).get_posY();
        	this.list_halo.get(1).setBounds(halo_x, halo_y, texture_cote1.getWidth()*2, texture_cote1.getHeight());
    	}
    	this.list_halo.get(0).update_halo();
    	this.list_halo.get(1).update_halo();
    }
    
    @Override
    public void draw(Batch batch, float alpha){
    	if(M.get_orientation() == orientation.VERTICALE) {
	        batch.draw(texture_cote1,get_posX(),get_posY());
	        batch.draw(texture_cote2,get_posX(),get_posY()+30);
    	}
    	else {
    		batch.draw(texture_cote1,get_posX(),get_posY());
	        batch.draw(texture_cote2,get_posX()+30,get_posY());
    	}
    }

	@Override
	public void act(float delta){
		if(est_select()){
        	if(M.get_orientation() == orientation.VERTICALE) {
        		super.set_posX(((Gdx.input.getX() - 15)*1280)/Gdx.graphics.getWidth());
        		super.set_posY(((Gdx.graphics.getHeight() - Gdx.input.getY() - 30)*720)/Gdx.graphics.getHeight());
        	}
        	else {
        		super.set_posX(((Gdx.input.getX() - 30)*1280)/Gdx.graphics.getWidth());
        		super.set_posY(((Gdx.graphics.getHeight() - Gdx.input.getY() - 15)*720)/Gdx.graphics.getHeight());
        	}
        	deplacer(super.get_posX(), super.get_posY());
        }
	}
}