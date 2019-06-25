package Helper;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class NewSize implements Serializable{

	public static BufferedImage getSize(BufferedImage image, int w, int h){
		BufferedImage newImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		newImage.getGraphics().drawImage(image,0 , 0, w,h, null);
		return newImage;
	}

}
