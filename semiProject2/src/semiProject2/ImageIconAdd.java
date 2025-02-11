package finance_Management;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageIconAdd {
	ImageIcon icon;
	
	public ImageIconAdd(String path,int width, int height) {
		ImageIcon rawicon = new ImageIcon(path);
		Image img = rawicon.getImage();
		Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); 
		icon = new ImageIcon(resizedImg);		
	}

}
