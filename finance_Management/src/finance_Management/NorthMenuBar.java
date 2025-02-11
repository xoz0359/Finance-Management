package finance_Management;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class NorthMenuBar extends JPanel {

	JPanel northFirstP;
	JButton b_menufavorites, b_forward, b_backward, b_menuclose;

	public NorthMenuBar() {
		super();

		BorderLayout mainLayout = new BorderLayout(10, 10);
		this.setLayout(mainLayout);

		northFirstP = new JPanel();
		FlowLayout northLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
		northFirstP.setLayout(northLayout);
		northFirstP.setBackground(Color.decode("#1879C9"));
		
		//¾ÆÀÌÄÜ»ý¼º
		String backwardPath = "images\\backward.png";
		String forwardPath = "images\\forward.png";
		String starPath = "images\\star.png";
		String closePath = "images\\close.png";
		

		ImageIconAdd backwardIcon = new ImageIconAdd(backwardPath, 20, 20);
		ImageIconAdd forwardIcon = new ImageIconAdd(forwardPath, 20, 20);
		ImageIconAdd starIcon = new ImageIconAdd(starPath, 20, 20);
		ImageIconAdd closeIcon = new ImageIconAdd(closePath, 20, 20);
		
		
		
		b_backward = new JButton(backwardIcon.icon);
		this.l_menu_design(b_backward);
		this.jb_color_change(b_backward);
		
		b_forward = new JButton(forwardIcon.icon);
		this.l_menu_design(b_forward);
		this.jb_color_change(b_forward);
		
		b_menufavorites = new JButton("Áñ°ÜÃ£±â",starIcon.icon);
		this.l_menu_design(b_menufavorites);
		this.jb_color_change(b_menufavorites);

		b_menuclose = new JButton("´Ý±â",closeIcon.icon);
		this.l_menu_design(b_menuclose);
		this.jb_color_change(b_menuclose);

		northFirstP.add(b_backward);
		northFirstP.add(b_forward);
		northFirstP.add(b_menufavorites);
		northFirstP.add(b_menuclose);

		this.add(northFirstP, "Center");
		String iconPath = "src\\img\\setting4.png";
		ImageIconAdd iconAdd = new ImageIconAdd(iconPath, 50, 50);
		JButton jb_icon = new JButton(iconAdd.icon);
		this.add(jb_icon, "East");
		jb_icon.setBackground(Color.decode("#1879C9"));
		this.setBackground(Color.decode("#1879C9"));

	}

	public void jb_color_change(JButton jb) {
		jb.setBackground(Color.decode("#1879C9"));
		jb.setForeground(Color.white);
	}

	public void l_menu_design(JButton jl) {
		jl.setForeground(Color.white);
		jl.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	}


}