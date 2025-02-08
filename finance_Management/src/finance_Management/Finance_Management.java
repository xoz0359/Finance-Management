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
				// 화면 전환용 카드 레이아웃 생성
				outcard = new CardLayout();
				this.setLayout(outcard);
				
				// 가장 바깥에 위치한 패널
				totalpanel = new JPanel(new BorderLayout());
				
				// 전체화면에서 화면 전환을 담당하는 패널
				incard = new CardLayout();
				cardpanel = new JPanel(incard);
				
				// cardpanel에서 전환될 패널들
				spanel1 = new JPanel(new BorderLayout());
				spanel2 = new JPanel(new BorderLayout());
				spanel3 = new JPanel(new BorderLayout());
				
				// 로그인 패널 생성
				LoginForm tp = new LoginForm();
				
				// westbar 생성
				Main_west mp = new Main_west();
				westbar = mp.p_main_west;
				
				// northbar 생성
				northbar = new NorthMenuBar();
				
				// 장부 입력 메뉴화면 호출
				State_Input isp = new State_Input();
				
				// 장부 입력화면 테이블에 대한 이벤트 리스너
				isplist = new ArrayList<String>();
				ispdtm = isp.dtm;
				ispdtm.addTableModelListener(new TableModelListener() {
		            @Override
		            public void tableChanged(TableModelEvent e) {
		                int row = e.getFirstRow();
		                int column = e.getColumn();
		                
		                if (column >= 0) { // 컬럼이 -1이면 구조 변경이므로 무시
		                	isplist.add(isp.jt_s.getValueAt(row, column).toString());
		                }
		            }
		        });
				
				// 매출 조회 메뉴화면 호출
				IncomeSP sip = new IncomeSP();
				
				// 매출 조회화면 테이블에 대한 이벤트 리스너
				siplist = new ArrayList<String>();
				sipdtm = sip.dtm;
				sipdtm.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						int row = e.getFirstRow();
						int column = e.getColumn();

						if (column >= 0) { // 컬럼이 음수면 구조변경이므로 무시
							siplist.add(sip.jt_s.getValueAt(row, column).toString());
						}
					}
				});
				
				// 패널 조립
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
				
				
				
				// 자녀 클래스와 인스턴스 주소 연결
				isp_save = isp.jb_save;
				sip_show = sip.jb_infoShow;
				login_btt = tp.login_btt;
				jb_stateInput = mp.jb_stateInput;
				jb_incomeSelect = mp.jb_incomeSelect;
				
				
				// 화면 전환과 이벤트 핸들러 연결
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
	
	
	@Override // 이벤트 처리를 위한 기본적인 엑션 핸들러
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
				System.out.println(cnt+"행 state에 insert 성공!");
				
				// 주비교자는 map에 보조 비교자는 list에 담아서 전달
				// 주비교자는 ?를 통해서 세팅할 수 없기 때문
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
					System.out.println(cnt+"income에 update 완료!");
				}
				isplist.clear();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == sip_show) {
			try {
				SelectIncome si = new SelectIncome();
				System.out.println("조회 이벤트 발생!");
				sirs = si.getSelection();
				if (!sirs.next()) {
					System.out.println("가져올 row가 없자너 ㅠㅠ");
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
				}else {
					do {
						// 기존의 row를 삭제 후 새 row 삽입
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
					System.out.println("아마 출력 잘 된 것 같지롱~");
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
		// gui생성
		fm.setSize(800, 800);
		fm.setVisible(true);
		//fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	
}
