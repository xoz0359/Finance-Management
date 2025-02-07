package finance_Management;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Finance_Management extends Frame implements ActionListener{

	public Finance_Management() {
		// 메인 프레임 생성
				
				CardLayout card = new CardLayout();
				this.setLayout(card);
				// 화면 전환용 카드 레이아웃 생성
				
				// 타이틀 화면 패널 생성
				JPanel titlep = new JPanel(new BorderLayout(20, 50));
				titlep.setBackground(Color.BLUE);
				
				// 로그인 패널 생성
				LoginForm loginp = new LoginForm();
				titlep.add(loginp, "Center");
				this.add(titlep);
				
				
				// 메뉴바 생성
				Main_west westbar = new Main_west();
				JPanel mainp = new JPanel(new BorderLayout());
				mainp.add(westbar);
				this.add(mainp);
				
				card.next(this);
				Frame mainf = this;
				loginp.login_btt.addMouseListener(new MouseAdapter() { 
					public void mouseClicked(MouseEvent e) {
						card.first(mainf);
					} 
				});
				 
				
	}
	
	public static void main(String[] args) {
		
		
		Finance_Management fm = new Finance_Management();
		// gui생성
		fm.setSize(800, 800);
		fm.setVisible(true);
		//fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
