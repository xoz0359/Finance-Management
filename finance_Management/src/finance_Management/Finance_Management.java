package finance_Management;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Finance_Management extends Frame implements ActionListener, MouseListener, MouseMotionListener{

	HashMap <Integer, String> map;
	ArrayList <String> list;
	JButton jb_save, login_btt, jb_stateInput, jb_incomeSelect;
	CardLayout incard, outcard;
	JPanel cardpanel, spanel1, spanel2, spanel3, westbar;
	DefaultTableModel ispdmt, sipdmt;
	
	
	public Finance_Management() {
				// ȭ�� ��ȯ�� ī�� ���̾ƿ� ����
				incard = new CardLayout();
				this.setLayout(incard);
				outcard = new CardLayout();
				cardpanel = new JPanel(outcard);
				spanel1 = new JPanel(new BorderLayout());
				spanel2 = new JPanel(new BorderLayout());
				spanel3 = new JPanel(new BorderLayout());
				
				// �α��� �г� ����
				LoginForm tp = new LoginForm();
				
				// �޴��� ����
				Main_west mp = new Main_west();
				westbar = mp.p_main_west;
				// ��� �Է� �޴�ȭ�� ȣ��
				State_Input isp = new State_Input();
				//isp.add(mp.p_main_west, "West");
				
				// J���̺��� Į���� ����� �� �����ϴ� �̺�Ʈ ������
				list = new ArrayList<String>();
				DefaultTableModel ispdtm = isp.dtm;
				ispdtm.addTableModelListener(new TableModelListener() {
		            @Override
		            public void tableChanged(TableModelEvent e) {
		                int row = e.getFirstRow();
		                int column = e.getColumn();
		                
		                if (column >= 0) { // �÷��� -1�̸� ���� �����̹Ƿ� ����
		                	list.add(isp.jt_s.getValueAt(row, column).toString());
		                }
		            }
		        });
				
				// ���� ��ȸ �޴�ȭ�� ȣ��
				IncomeSP sip = new IncomeSP();
				
				// �г� ����
				this.add(tp, "TitlePanel");
				this.add(cardpanel, "menu");
				cardpanel.add(spanel1, "MainPanel");
				cardpanel.add(spanel2, "InsertStatePanel");
				cardpanel.add(spanel3, "SelectIncomPanel");
				
				spanel1.add(mp, "Center");
				spanel2.add(isp, "Center");
				spanel3.add(sip, "Center");
				
				
				
				// �ڳ� Ŭ������ �ν��Ͻ� �ּ� ����
				jb_save = isp.jb_save;
				login_btt = tp.login_btt;
				jb_stateInput = mp.jb_stateInput;
				jb_incomeSelect = mp.jb_incomeSelect;
				
				
				// ȭ�� ��ȯ�� �̺�Ʈ �ڵ鷯 ����
				incard.show(this, "TitlePanel");
				tp.login_btt.addMouseListener(this);
				mp.jb_stateInput.addMouseListener(this);
				mp.jb_incomeSelect.addMouseListener(this);
				isp.jb_save.addMouseListener(this);
				
				
				
				this.addWindowListener(new WindowAdapter() {
		            public void windowClosing(WindowEvent we) {
		                System.exit(0);
		            }
		        });

		 
		        	
		 
		        
				
				//si.jt_s.addAncestorListener(e -> );
				//InsertState is = new InsertState();
				
				
	}
	
	
	@Override // �̺�Ʈ ó���� ���� �⺻���� ���� �ڵ鷯
	public void actionPerformed(ActionEvent e) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb_save) {
			try {
				InsertState is = new InsertState();
				int cnt = is.getDML(list);
				System.out.println(cnt+"�� ��� �Ϸ�!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == login_btt) {
			spanel1.add(westbar, "West");
			incard.show(this, "menu");
		} else if (e.getSource() == jb_stateInput) {
			spanel2.add(westbar, "West");
			outcard.show(cardpanel, "InsertStatePanel");
		} else if (e.getSource() == jb_incomeSelect) {
			spanel3.add(westbar, "West");
			outcard.show(cardpanel, "SelectIncomPanel");
		} else {
			
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
	public static void main(String[] args) {
		
		
		Finance_Management fm = new Finance_Management();
		// gui����
		fm.setSize(800, 800);
		fm.setVisible(true);
		//fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	
}
