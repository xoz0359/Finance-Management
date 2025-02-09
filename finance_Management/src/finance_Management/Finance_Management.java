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
	
	HashMap <Integer, String> isp_map, sfp_map, ssp_map;
	ArrayList <String> isplist, siplist, uilist, aiplist, sfp_list, tnamelist, sfplist, ssplist, p_backwardlog, p_forwardlog;
	ResultSet rs, sirs;
	JButton login_btt, isp_save, isp_dateinsert, sip_show, aip_show, sfp_show, ssp_show, stp_show, jb_stateinput, jb_stateselect, jb_incomeselect, jb_incomeanalysis, jb_fin_stselect, jb_teamselect, jb_backward, jb_forward;
	CardLayout incard, outcard;
	JPanel cardpanel, totalpanel, spanel0, spanel1, spanel2, spanel3, spanel4, spanel5, spanel6, spanel7, westbar, northbar;
	DefaultTableModel ispdtm, sipdtm, aipdtm, sfpdtm, sspdtm, stpdtm;
	Integer backcnt, forcnt;
	JTable ispjt;
	JComboBox jc_date;
	
	// 할일 동사 명사 순서로 객체 명명규칙 바꾸기...
	
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
				spanel5 = new JPanel(new BorderLayout());
				spanel6 = new JPanel(new BorderLayout());
				spanel7 = new JPanel(new BorderLayout());
				
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
				
				// 장부 조회 화면 호출
				ShowState ssp = new ShowState();
				sspdtm = ssp.dtm;
				
				// 부서 조회 화면 호출
				ShowTeam stp = new ShowTeam();
				stpdtm = stp.fs_tableModel;
				
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
				
				// 매출 분석 화면 테이블에 대한 이벤트 리스너
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
				
				// 재무제표 조회 화면 호출
				FinancialStatements sfp = new FinancialStatements();
				sfpdtm = sfp.fs_tableModel;
				
				
				
				// 패널 조립
				this.add(tp, "TitlePanel");
				this.add(totalpanel, "menu");
				totalpanel.add(cardpanel, "Center");
				totalpanel.add(westbar, "West");
				totalpanel.add(northbar, "North");
				cardpanel.add(spanel1, "MainPanel");
				cardpanel.add(spanel2, "InsertStatePanel");
				cardpanel.add(spanel3, "SelectStatePanel");
				cardpanel.add(spanel4, "SelectTeamPanel");
				cardpanel.add(spanel5, "SelectIncomPanel");
				cardpanel.add(spanel6, "AnalysisIncomPanel");
				cardpanel.add(spanel7, "SelectFin_stPanel");
				
				spanel1.add(mp, "Center");
				spanel2.add(isp, "Center");
				spanel3.add(ssp, "Center");
				spanel4.add(stp, "Center");
				spanel5.add(sip, "Center");
				spanel6.add(aip, "Center");
				spanel7.add(sfp, "Center");
				
				
				
				
				
				
				// 호출된 인스턴스의 주소 연결
				login_btt = tp.check;
				sip_show = sip.jb_infoShow;
				jc_date = isp.jc_date;
				stp_show = stp.b_check;
				isp_save = isp.jb_save;
				aip_show = aip.jb_infoShow;
				sfp_show = sfp.b_check;
				ssp_show = ssp.jb_save;
				jb_stateinput = mp.jb_stateInput;
				jb_stateselect = mp.jb_stateSelect;
				jb_incomeselect = mp.jb_incomeSelect;
				jb_incomeanalysis = mp.jb_incomeAnalysis;
				jb_fin_stselect = mp.jb_fin_st;
				jb_teamselect = mp.jb_teamSelect;
				jb_backward = nb.b_backward;
				jb_forward = nb.b_forward;
				ispjt = isp.jt_s;
				isp_dateinsert = isp.jb_dateinsert;
				
				
				
				// 호출한 인스턴스와 이벤트 핸들러 연결
				outcard.show(this, "TitlePanel");
				login_btt.addActionListener(this);
				jb_stateinput.addActionListener(this);
				jb_stateselect.addActionListener(this);
				jb_teamselect.addActionListener(this);
				jb_incomeselect.addActionListener(this);
				jb_incomeanalysis.addActionListener(this);
				jb_fin_stselect.addActionListener(this);
				jb_backward.addActionListener(this);
				jb_forward.addActionListener(this);
				isp_save.addMouseListener(this);
				stp_show.addMouseListener(this);
				ssp_show.addMouseListener(this);
				sip_show.addMouseListener(this);
				aip_show.addMouseListener(this);
				isp_dateinsert.addMouseListener(this);
				sfp_show.addMouseListener(this);
				
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
				
				this.addWindowListener(new WindowAdapter() {
		            public void windowClosing(WindowEvent we) {
		                System.exit(0);
		            }
		        });

		 

	}
	
	
	@Override // 이벤트 처리를 위한 기본적인 엑션 핸들러
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == login_btt) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("MainPanel");
			outcard.show(this, "menu");
			incard.show(cardpanel, "MainPanel");
		} else if (e.getSource() == jb_stateinput) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("InsertStatePanel");
			incard.show(cardpanel, "InsertStatePanel");
		} else if (e.getSource() == jb_incomeselect) {
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
		} else if (e.getSource() == jb_fin_stselect) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("SelectFin_stPanel");
			incard.show(cardpanel, "SelectFin_stPanel");
		} else if (e.getSource() == jb_stateselect) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("SelectStatePanel");
			incard.show(cardpanel, "SelectStatePanel");
		}else if (e.getSource() == jb_teamselect) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("SelectTeamPanel");
			incard.show(cardpanel, "SelectTeamPanel");
		}else if (e.getSource() == jb_backward) {
			if(p_backwardlog.size() > 1) {
			p_forwardlog.add(p_backwardlog.getLast());
			incard.show(cardpanel, p_backwardlog.get(p_backwardlog.size()-2));
			p_backwardlog.removeLast();
			}else {// 뒤로가거나 앞으로 갈 페이지 없으면 버튼 비활성화
				
			}
		} else if (e.getSource() == jb_forward) {
			if(p_forwardlog.size() > 0) {
			p_backwardlog.add(p_forwardlog.getLast());
			incard.show(cardpanel, p_forwardlog.getLast());
			p_forwardlog.removeLast();
			}else {
				
			}
		} 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == isp_save) {
			try {
				// 사용자가 수정을 완료하지 않고 저장 버튼을 누르면 수정하던 내용을 포함시켜서 처리하는 부분
				int row = ispjt.getEditingRow();
				int col = ispjt.getEditingColumn();
				if (row != -1 && col != -1) {
				    // getEditingRow와 getEditingColumn은 api에서 찾았는데 
					// 수정중인 텍스트를 불러오는 방법은 검색만으로 도저히 알아낼 수 없어서 ai의 힘을 빌렸습니다 ㅜㅜ
				    Component editorComponent = ispjt.getEditorComponent();
				    if (editorComponent instanceof JTextField) {
				        JTextField textField = (JTextField) editorComponent;
				        String editingdata = textField.getText();
				        isplist.add(editingdata);
				    }
				}
				
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
				rs = si.getSelection();
				if (!rs.next()) {
					//System.out.println("가져올 row가 없자너 ㅠㅠ");
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
				}else {
					do {
						// 기존의 row를 삭제 후 새 row 삽입
						int rowcnt = 0;
						sipdtm.removeRow(rowcnt);
						sipdtm.insertRow(rowcnt, new Object[] {rs.getString("DEPT"), rs.getString("JAN"), rs.getString("FEB"),
								rs.getString("MAR"), rs.getString("APR"), rs.getString("MAY"), rs.getString("JUN"),
								rs.getString("JUL"), rs.getString("AUG"), rs.getString("SEP"), rs.getString("OCT"),
								rs.getString("NOV"), rs.getString("DEC"), rs.getString("EXPECTINCOME")});
					}while(rs.next());
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
					sipdtm.fireTableDataChanged(); // 패널 안 바뀔 때 새로고침
					//System.out.println("아마 출력 잘 된 것 같지롱~");
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource() == stp_show) {
			try {
				SelectTeam st = new SelectTeam();
				rs = st.getSelection();
				if (!rs.next()) {
					//System.out.println("가져올 row가 없자너 ㅠㅠ");
					st.rs.close();
					st.pstmt.close();
					st.conn.close();
				}else {
					do {
						// 기존의 row를 삭제 후 새 row 삽입
						int rowcnt = 0;
						stpdtm.removeRow(rowcnt);
						stpdtm.insertRow(rowcnt, new Object[] {rs.getString("DEPT"), rs.getString("Teamname")});
					}while(rs.next());
					st.rs.close();
					st.pstmt.close();
					st.conn.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == aip_show) {
			// 필요한 정보들 부서이름, 예상매출액, 누적매출액, 달성률
			// team, income table에서 각각 select 받은 정보로 테이블 구성해서 보여주기
			try {
				SelectIncome ai = new SelectIncome();
				//System.out.println("조회 이벤트 발생!");
				rs = ai.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					//System.out.println("가져올 row가 없자너 ㅠㅠ");
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
					//System.out.println("아마 출력 잘 된 것 같지롱~");
					}
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == isp_dateinsert) {
			int rowcnt = 0;
			ispdtm.insertRow(rowcnt, new Object[] {"", jc_date.getSelectedItem().toString(), "", "", ""});
		} else if (e.getSource() == sfp_show) {
			try {
				SelectFin_st sf = new SelectFin_st();
				rs = sf.getSelection();
				int rowcnt = 0;
				if(!rs.next()) {
					System.out.println("가져올 데이터가 없어요");
					rs.close();
					sf.pstmt.close();
					sf.conn.close();
				}do {
					sfpdtm.removeRow(rowcnt);
					sfpdtm.insertRow(rowcnt, new Object[] {tnamelist.get(rowcnt), rs.getString("amount")});
					rowcnt++;
				}while(rs.next());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == ssp_show) {
			try {
				SelectState ss = new SelectState();
				rs = ss.getSelection();
				int rowcnt = 0;
				if(!rs.next()) {
					System.out.println("가져올 데이터가 없어요");
					rs.close();
					ss.pstmt.close();
					ss.conn.close();
				}do {
					sspdtm.removeRow(rowcnt);
					sspdtm.insertRow(rowcnt, new Object[] {rs.getString("LECNO"), rs.getString("LECDATE"), rs.getString("SMCODE"), rs.getString("DEPT"), rs.getString("AMOUNT")});
					rowcnt++;
				}while(rs.next());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
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
