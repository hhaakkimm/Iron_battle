package Graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import Helper.NewSize;

public class Sprite implements Serializable{
	private int mashtab;
	private BufferedImage image;
		
	public Sprite(BufferedImage image,int  mashtab){
		this.mashtab=mashtab;
		this.image=NewSize.getSize(image, image.getWidth()*mashtab, image.getHeight()*mashtab);
	}
	
	public void render(Graphics2D g, int x, int y){
      g.drawImage(image, x, y,null);
	}

}
