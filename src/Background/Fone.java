package Background;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import Helper.NewSize;

public class Fone implements Serializable{
	private BufferedImage image;
	private FoneType type;
	
	public Fone(BufferedImage image, int mashtab,FoneType type){
		this.image=NewSize.getSize(image, image.getWidth()*mashtab, image.getHeight()*mashtab);
		this.type=type;
	}
	public void render(Graphics2D g, int x, int y){
	g.drawImage(image, x,y,null);
	}
}
