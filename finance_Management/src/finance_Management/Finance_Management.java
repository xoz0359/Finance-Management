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
		// ���� ������ ����
				
				CardLayout card = new CardLayout();
				this.setLayout(card);
				// ȭ�� ��ȯ�� ī�� ���̾ƿ� ����
				
				// Ÿ��Ʋ ȭ�� �г� ����
				JPanel titlep = new JPanel(new BorderLayout(20, 50));
				titlep.setBackground(Color.BLUE);
				
				// �α��� �г� ����
				LoginForm loginp = new LoginForm();
				titlep.add(loginp, "Center");
				this.add(titlep);
				
				
				// �޴��� ����
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
		// gui����
		fm.setSize(800, 800);
		fm.setVisible(true);
		//fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
