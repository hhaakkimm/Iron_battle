package Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;  
public class TextureAtlas implements Serializable{
	public BufferedImage image;
	public static final String PATH="resource/";
	
	public TextureAtlas(String imageName){
		try{
		image=ImageIO.read(new File(PATH+imageName));
		}catch(IOException e){}
	}
	public BufferedImage cut(int x,int y, int w, int h){
		return image.getSubimage(x, y, w, h);
	}

}
