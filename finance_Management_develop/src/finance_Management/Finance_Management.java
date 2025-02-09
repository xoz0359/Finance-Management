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
	
	HashMap <Integer, String> isp_map;
	ArrayList <String> isplist, siplist, uilist, aiplist, tnamelist, p_backwardlog, p_forwardlog;
	ResultSet rs, sirs;
	JButton login_btt, isp_save, sip_show, aip_show, jb_stateInput, jb_incomeSelect, jb_incomeanalysis, jb_backward, jb_forward;
	CardLayout incard, outcard;
	JPanel cardpanel, totalpanel, spanel0, spanel1, spanel2, spanel3, spanel4, westbar, northbar;
	DefaultTableModel ispdtm, sipdtm, aipdtm;
	Integer backcnt, forcnt;
	
	// 할일 명사 동사 순서로 객체 명명규칙 바꾸기...
	
	public Finance_Management() {
				// backward, forward 정보 저장용 리스트 생성
				p_backwardlog = new ArrayList <String>();
				p_forwardlog = new ArrayList <String>();
				
		
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
				spanel4 = new JPanel(new BorderLayout());
				
				// 로그인 패널 생성
				LoginForm tp = new LoginForm();
				
				// westbar 생성
				Main_west mp = new Main_west();
				westbar = mp.p_main_west;
				
				// northbar 생성
				NorthMenuBar nb  = new NorthMenuBar();
				northbar = nb;
				
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
				
				// 매출 분석 메뉴화면 호출
				IncomeAnalysis aip = new IncomeAnalysis(); 
				
				// 매출 조회화면 테이블에 대한 이벤트 리스너
				aiplist = new ArrayList<String>();
				aipdtm = aip.dtm;
				aipdtm.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						int row = e.getFirstRow();
						int column = e.getColumn();

						if (column >= 0) { // 컬럼이 음수면 구조변경이므로 무시
							aiplist.add(aip.jt_s.getValueAt(row, column).toString());
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
				cardpanel.add(spanel4, "AnalysisIncomPanel");
				spanel1.add(mp, "Center");
				spanel2.add(isp, "Center");
				spanel3.add(sip, "Center");
				spanel4.add(aip, "Center");
				
				
				
				// 자녀 클래스와 인스턴스 주소 연결
				login_btt = tp.login_btt;
				sip_show = sip.jb_infoShow;
				isp_save = isp.jb_save;
				aip_show = aip.jb_infoShow;
				jb_stateInput = mp.jb_stateInput;
				jb_incomeSelect = mp.jb_incomeSelect;
				jb_incomeanalysis = mp.jb_incomeAnalysis;
				jb_backward = nb.b_backward;
				jb_forward = nb.b_forward;
				
				// 화면 전환과 이벤트 핸들러 연결
				outcard.show(this, "TitlePanel");
				login_btt.addMouseListener(this);
				jb_stateInput.addMouseListener(this);
				jb_incomeSelect.addMouseListener(this);
				jb_incomeanalysis.addMouseListener(this);
				isp_save.addMouseListener(this);
				sip_show.addMouseListener(this);
				aip_show.addMouseListener(this);
				jb_backward.addMouseListener(this);
				jb_forward.addMouseListener(this);
				
				
				
				this.addWindowListener(new WindowAdapter() {
		            public void windowClosing(WindowEvent we) {
		                System.exit(0);
		            }
		        });

		 
		        	
		 
		        
				
				//si.jt_s.addAncestorListener(e -> );
				//InsertState is = new InsertState();
				
				// 자주 쓰는 정보 db에서 받아와서 저장해두기
				try {
					SelectTeam at = new SelectTeam();
					tnamelist = new ArrayList<String>();
					rs = at.getSelection();
					if (!rs.next()) {
						System.out.println("가져올 row가 없자너 ㅠㅠ");
						at.rs.close();
						at.pstmt.close();
						at.conn.close();
					} else {
						tnamelist.add(rs.getString("teamname"));
					}
					while (rs.next());
					at.rs.close();
					at.pstmt.close();
					at.conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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
					isp_map = new HashMap<Integer, String>();
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
					
					isp_map.put(0, "JAN");
					uilist.add(isplist.get(3-1));
					uilist.add(isplist.get(2-1));
					UpdateIncome ui = new UpdateIncome();
					cnt = ui.getDML(isp_map, uilist);
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
				//System.out.println("조회 이벤트 발생!");
				sirs = si.getSelection();
				if (!sirs.next()) {
					//System.out.println("가져올 row가 없자너 ㅠㅠ");
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
					sipdtm.fireTableDataChanged();
					//System.out.println("아마 출력 잘 된 것 같지롱~");
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == aip_show) {
			// 필요한 정보들 부서이름, 예상매출액, 누적매출액, 달성률
			// team, income table에서 각각 select 받은 정보로 테이블 구성해서 보여주기
			try {
				SelectIncome ai = new SelectIncome();
				System.out.println("조회 이벤트 발생!");
				rs = ai.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					System.out.println("가져올 row가 없자너 ㅠㅠ");
					ai.rs.close();
					ai.pstmt.close();
					ai.conn.close();
				}else {
					do {
						int sum = rs.getInt("JAN")+rs.getInt("FEB")+rs.getInt("MAR")+rs.getInt("APR")+rs.getInt("MAY")+rs.getInt("JUN")+
						rs.getInt("JUL")+rs.getInt("AUG")+rs.getInt("SEP")+rs.getInt("OCT")+rs.getInt("NOV")+rs.getInt("DEC");
						int rate = Math.round((rs.getInt("EXPECTINCOME")/sum)/10)*10;
						// 기존의 row를 삭제 후 새 row 삽입
						aipdtm.removeRow(rowcnt);
						aipdtm.insertRow(rowcnt, new Object[] {tnamelist.get(rowcnt), rs.getString("EXPECTINCOME").toString(), ""+sum, ""+rate});
						rowcnt++;
					}while(rs.next());
					ai.rs.close();
					ai.pstmt.close();
					ai.conn.close();
					aipdtm.fireTableDataChanged();
					System.out.println("아마 출력 잘 된 것 같지롱~");
					}
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == login_btt) {
			if (p_forwardlog.size() > 0 && !p_forwardlog.getLast().equals("MainPanel") ) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("MainPanel");
			outcard.show(this, "menu");
			incard.show(cardpanel, "MainPanel");
		} else if (e.getSource() == jb_stateInput) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("InsertStatePanel");
			incard.show(cardpanel, "InsertStatePanel");
		} else if (e.getSource() == jb_incomeSelect) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("SelectIncomPanel");
			incard.show(cardpanel, "SelectIncomPanel");
		} else if (e.getSource() == jb_incomeanalysis) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("AnalysisIncomPanel");
			incard.show(cardpanel, "AnalysisIncomPanel");
		} else if (e.getSource() == jb_backward) {
			if(p_backwardlog.size() > 1) {
			p_forwardlog.add(p_backwardlog.getLast());
			incard.show(cardpanel, p_backwardlog.get(p_backwardlog.size()-2));
			p_backwardlog.removeLast();
			}else {// 뒤로가거나 앞으로 갈 페이지 없으면 버튼 비활성화
				
			}
		} else if (e.getSource() == jb_forward) {
			if(p_forwardlog.size() > 1) {
			p_backwardlog.add(p_forwardlog.getLast());
			incard.show(cardpanel, p_forwardlog.get(p_forwardlog.size()-2));
			p_forwardlog.removeLast();
			}else {
				
			}
		} else{
			
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
