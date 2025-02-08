package finance_Management;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class Finance_Management extends Frame implements ActionListener, MouseListener, MouseMotionListener, KeyListener{
	
	HashMap <Integer, String> map;
	ArrayList <String> isplist, siplist, uilist;
	ResultSet sirs;
	JButton isp_save, sip_show, login_btt, jb_stateInput, jb_incomeSelect;
	CardLayout incard, outcard;
	JPanel cardpanel, totalpanel, spanel1, spanel2, spanel3, westbar, northbar;
	DefaultTableModel ispdtm, sipdtm;
	
	
	
	public Finance_Management() {
				// ȭ�� ��ȯ�� ī�� ���̾ƿ� ����
				outcard = new CardLayout();
				this.setLayout(outcard);
				
				// ���� �ٱ��� ��ġ�� �г�
				totalpanel = new JPanel(new BorderLayout());
				
				// ��üȭ�鿡�� ȭ�� ��ȯ�� ����ϴ� �г�
				incard = new CardLayout();
				cardpanel = new JPanel(incard);
				
				// cardpanel���� ��ȯ�� �гε�
				spanel1 = new JPanel(new BorderLayout());
				spanel2 = new JPanel(new BorderLayout());
				spanel3 = new JPanel(new BorderLayout());
				
				// �α��� �г� ����
				LoginForm tp = new LoginForm();
				
				// westbar ����
				Main_west mp = new Main_west();
				westbar = mp.p_main_west;
				
				// northbar ����
				northbar = new NorthMenuBar();
				
				// ��� �Է� �޴�ȭ�� ȣ��
				State_Input isp = new State_Input();
				
				// ��� �Է�ȭ�� ���̺� ���� �̺�Ʈ ������
				isplist = new ArrayList<String>();
				ispdtm = isp.dtm;
				ispdtm.addTableModelListener(new TableModelListener() {
		            @Override
		            public void tableChanged(TableModelEvent e) {
		                int row = e.getFirstRow();
		                int column = e.getColumn();
		                
		                if (column >= 0) { // �÷��� -1�̸� ���� �����̹Ƿ� ����
		                	isplist.add(isp.jt_s.getValueAt(row, column).toString());
		                }
		            }
		        });
				
				// ���� ��ȸ �޴�ȭ�� ȣ��
				IncomeSP sip = new IncomeSP();
				
				// ���� ��ȸȭ�� ���̺� ���� �̺�Ʈ ������
				siplist = new ArrayList<String>();
				sipdtm = sip.dtm;
				sipdtm.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						int row = e.getFirstRow();
						int column = e.getColumn();

						if (column >= 0) { // �÷��� ������ ���������̹Ƿ� ����
							siplist.add(sip.jt_s.getValueAt(row, column).toString());
						}
					}
				});
				
				// �г� ����
				this.add(tp, "TitlePanel");
				this.add(totalpanel, "menu");
				totalpanel.add(cardpanel, "Center");
				totalpanel.add(westbar, "West");
				totalpanel.add(northbar, "North");
				cardpanel.add(spanel1, "MainPanel");
				cardpanel.add(spanel2, "InsertStatePanel");
				cardpanel.add(spanel3, "SelectIncomPanel");
				spanel1.add(mp, "Center");
				spanel2.add(isp, "Center");
				spanel3.add(sip, "Center");
				
				
				
				// �ڳ� Ŭ������ �ν��Ͻ� �ּ� ����
				isp_save = isp.jb_save;
				sip_show = sip.jb_infoShow;
				login_btt = tp.login_btt;
				jb_stateInput = mp.jb_stateInput;
				jb_incomeSelect = mp.jb_incomeSelect;
				
				
				// ȭ�� ��ȯ�� �̺�Ʈ �ڵ鷯 ����
				outcard.show(this, "TitlePanel");
				login_btt.addMouseListener(this);
				jb_stateInput.addMouseListener(this);
				jb_incomeSelect.addMouseListener(this);
				isp_save.addMouseListener(this);
				sip_show.addMouseListener(this);
				
				
				
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
		if (e.getSource() == isp_save) {
			try {
				InsertState is = new InsertState();
				int cnt = is.getDML(isplist);
				is.pstmt.close();
				is.conn.close();
				System.out.println(cnt+"�� state�� insert ����!");
				
				// �ֺ��ڴ� map�� ���� ���ڴ� list�� ��Ƽ� ����
				// �ֺ��ڴ� ?�� ���ؼ� ������ �� ���� ����
				if(isplist.get(0).equals("6")) {
					map = new HashMap<Integer, String>();
					uilist = new ArrayList<String>();
					
					//int mon = Integer.parseInt(isplist.get(0).substring(5, 7));
					//switch (mon) {
					//case 1 : map.put(0, "JAN"); break;
					//case 2 : map.put(0, "FEB"); break;
					//case 3 : map.put(0, "MAR"); break;
					//case 4 : map.put(0, "APR"); break;
					//case 5 : map.put(0, "MAY"); break;
					//case 6 : map.put(0, "JUN"); break;
					//case 7 : map.put(0, "JUL"); break;
					//case 8 : map.put(0, "AUG"); break;
					//case 9 : map.put(0, "SEP"); break;
					//case 10 : map.put(0, "OCT"); break;
					//case 11 : map.put(0, "NOV"); break;
					//case 12 : map.put(0, "DEC"); break;
					//default :
					//}
					map.put(0, "JAN");
					uilist.add(isplist.get(3-1));
					uilist.add(isplist.get(2-1));
					UpdateIncome ui = new UpdateIncome();
					cnt = ui.getDML(map, uilist);
					System.out.println(cnt+"income�� update �Ϸ�!");
				}
				isplist.clear();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == sip_show) {
			try {
				SelectIncome si = new SelectIncome();
				System.out.println("��ȸ �̺�Ʈ �߻�!");
				sirs = si.getSelection();
				if (!sirs.next()) {
					System.out.println("������ row�� ���ڳ� �Ф�");
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
				}else {
					do {
						// ������ row�� ���� �� �� row ����
						int rowcnt = 0;
						sipdtm.removeRow(rowcnt);
						sipdtm.insertRow(rowcnt, new Object[] {sirs.getString("DEPT"), sirs.getString("JAN"), sirs.getString("FEB"),
								sirs.getString("MAR"), sirs.getString("APR"), sirs.getString("MAY"), sirs.getString("JUN"),
								sirs.getString("JUL"), sirs.getString("AUG"), sirs.getString("SEP"), sirs.getString("OCT"),
								sirs.getString("NOV"), sirs.getString("DEC"), sirs.getString("EXPECTINCOME")});
					}while(sirs.next());
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
					System.out.println("�Ƹ� ��� �� �� �� ������~");
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == login_btt) {
			outcard.show(this, "menu");
		} else if (e.getSource() == jb_stateInput) {
			incard.show(cardpanel, "InsertStatePanel");
		} else if (e.getSource() == jb_incomeSelect) {
			incard.show(cardpanel, "SelectIncomPanel");
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
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
