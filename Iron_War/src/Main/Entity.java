package Main;

import java.awt.Graphics2D;
import java.io.Serializable;

import Helper.Input;

public abstract class Entity implements Serializable{
	public int x,y;
	public Entity(int x, int y){
		this.x=x;
		this.y=y;
	}
	abstract public void render(Graphics2D g); 
	abstract public void updateMovement(Input input);
}
