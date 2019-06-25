package Main;
import java.awt.Graphics2D;
import java.io.Serializable;

import Graphics.Sprite;
import Graphics.TextureAtlas;
import Helper.Input;

public class Bullet extends Entity implements Serializable{
	private static int mashtab=1;
	
	//----------------------
	private int speed;
	private boolean fly;
	private Sprite[] 	sprite;
	//----------------------
	
	public Bullet(int x, int y, int w, int h, TextureAtlas atlas, int speed){
		super(x, y);
		this.speed=speed;
		fly=false;
		sprite=new Sprite[2];
		sprite[0]=new Sprite(atlas.cut(0, 16*2, w, h), mashtab);
		sprite[1]=new Sprite(atlas.cut(0, 16*2,1, 1), 1);
	}
	public void render(Graphics2D g){
		if(fly)	sprite[0].render(g, x, y);
		else sprite[1].render(g, x, y);
	}
	public void updateMovement(Input input) {	
	}
	
	
	public boolean getFly(){
		return fly;
	}
	public void setFly(boolean fly){
		this.fly=fly;
	}
	
	public int getSpeed(){
		return speed;
	}

}




