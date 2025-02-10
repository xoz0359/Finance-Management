package finance_Management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitleSet {
	
	JPanel jp_title;
	JLabel jl_titleName;
	
	public TitleSet(String titleName) {
		jp_title = new JPanel();
		BorderLayout fs_titlebox = new BorderLayout();
		jp_title.setLayout(fs_titlebox);
		jp_title.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		jp_title.setBackground(Color.WHITE);
		
		jl_titleName = new JLabel(titleName, SwingConstants.CENTER);
		jl_titleName.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,20));
		jp_title.add(jl_titleName, BorderLayout.CENTER);
	}
	

}
