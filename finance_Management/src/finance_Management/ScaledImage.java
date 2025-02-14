package finance_Management;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;

public class ScaledImage {

	public ImageIcon givemeImage(String path, int width, int height) {
		
		URL imageUrl = getClass().getClassLoader().getResource(path);
		ImageIcon icon = new ImageIcon(imageUrl);
		Image img = icon.getImage();

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();

		// 고품질 렌더링 설정
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.drawImage(img, 0, 0, width, height, null);
		g2d.dispose();

		return new ImageIcon(bufferedImage);

	}
}
