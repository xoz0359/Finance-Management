package finance_Management;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageIconAdd {
	ImageIcon icon;
	
	public ImageIconAdd(String path,int width, int height) {
		
		URL imageUrl = getClass().getClassLoader().getResource(path);
		ImageIcon rawicon = new ImageIcon(imageUrl);
		Image img = rawicon.getImage();
		Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); 
		icon = new ImageIcon(resizedImg);		
	}

}
