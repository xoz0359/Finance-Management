package finance_su;

import java.awt.*;
import javax.swing.*;

public class NorthMenuBar extends JFrame {

	JPanel northFirstP;
	JButton b_menusave, b_menufavorites, b_menuclose;

	public NorthMenuBar() {
		super();

		BorderLayout mainLayout = new BorderLayout(10, 10);
		this.setLayout(mainLayout);

		northFirstP = new JPanel();
		FlowLayout northLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
		northFirstP.setLayout(northLayout);
		northFirstP.setBackground(Color.black);

		b_menusave = new JButton("Áñ°ÜÃ£±â");
		this.l_menu_design(b_menusave);
		this.jb_color_change(b_menusave);

		b_menufavorites = new JButton("µÚ·Î°¡±â");
		this.l_menu_design(b_menufavorites);
		this.jb_color_change(b_menufavorites);

		b_menuclose = new JButton("´Ý±â");
		this.l_menu_design(b_menuclose);
		this.jb_color_change(b_menuclose);

		northFirstP.add(b_menusave);
		northFirstP.add(b_menufavorites);
		northFirstP.add(b_menuclose);

		this.add(northFirstP, "North");

		this.setSize(500, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public void jb_color_change(JButton jb) {
		jb.setBackground(Color.black);
		jb.setForeground(Color.white);
	}

	public void l_menu_design(JButton jl) {
		jl.setForeground(Color.white);
		jl.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	}

	public static void main(String[] args) {

		new NorthMenuBar();

	}

}
