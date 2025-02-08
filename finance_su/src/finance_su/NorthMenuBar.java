package finance_su;

import java.awt.*;
import javax.swing.*;

public class NorthMenuBar extends JFrame {

	JPanel northFirstP;
	JButton b_menufavorites, b_forward, b_backward, b_menuclose;

	public NorthMenuBar() {
		super();

		BorderLayout mainLayout = new BorderLayout(10, 10);
		this.setLayout(mainLayout);

		northFirstP = new JPanel();
		FlowLayout northLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
		northFirstP.setLayout(northLayout);
		northFirstP.setBackground(Color.black);

		b_backward = new JButton("°Á");
		this.l_menu_design(b_backward);
		this.jb_color_change(b_backward);
		
		b_forward = new JButton("°Ê");
		this.l_menu_design(b_forward);
		this.jb_color_change(b_forward);
		
		b_menufavorites = new JButton("¡Ò∞‹√£±‚");
		this.l_menu_design(b_menufavorites);
		this.jb_color_change(b_menufavorites);

		b_menuclose = new JButton("¥›±‚");
		this.l_menu_design(b_menuclose);
		this.jb_color_change(b_menuclose);

		northFirstP.add(b_backward);
		northFirstP.add(b_forward);
		northFirstP.add(b_menufavorites);
		northFirstP.add(b_menuclose);

		this.add(northFirstP, "North");

	}

	public void jb_color_change(JButton jb) {
		jb.setBackground(Color.black);
		jb.setForeground(Color.white);
	}

	public void l_menu_design(JButton jl) {
		jl.setForeground(Color.white);
		jl.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
	}


}
