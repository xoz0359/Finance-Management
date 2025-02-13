package finance_Management;

import java.awt.*;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Finance_Management extends Frame
		implements ActionListener, MouseListener, MouseMotionListener {

	HashMap<Integer, String> isp_map, sfp_map, ssp_map;
	ArrayList<String> uiflist, isplist, siplist, uilist, aiplist, sfp_list, tnamelist, smcodelist, sfplist, ssplist, sspdatelist,
			tiplist, p_backwardlog, p_forwardlog;
	ResultSet rs, sirs;
	JButton login_btt, isp_save, urp_save, urp_close, isp_dateinsert, sip_show, aip_show, ti_input, sfp_show, ssp_show,
			stp_show, fmenu, jb_teaminput, jb_stateinput, jb_stateselect, jb_incomeselect, jb_incomeanalysis,
			jb_fin_stselect, jb_teamselect, jb_backward, jb_logout, jb_exinconinsert, jb_forward, jb_userregist,
			btn_codeRegi, btn_userRegi, btn_adminMode, btn_deptRegi, b_menufavorites;
	JTextField id_jtext, pwd_jtext, ti_jtext, si_jtext;
	CardLayout incard, outcard;
	JPanel cardpanel, totalpanel, spanel0, spanel1, spanel2, spanel3, spanel4, spanel5, spanel6, spanel7, spanel8,
			spanel9, westbar, northbar, sip;
	DefaultTableModel ispdtm, sipdtm, aipdtm, sfpdtm, sspdtm, stpdtm;
	Integer backcnt, forcnt, accesslv;
	JTable ispjt, aipjt, sfpjt, sspjt, sipjt;
	JComboBox is_date, ai_sort, si_tname, si_date, sfp_statetype, ssp_date1, ssp_date2, sip_tname, favoritesComboBox;
	String editingdata;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	CodeUserRegistration urp;
	JCheckBox rememberIdCheckBox;
    DefaultTableCellRenderer numberRenderer;
		
	
	// 할일 동사 명사 순서로 객체 명명규칙 바꾸기...

	public Finance_Management() { //무한 재귀호출 해결 해야 해

		
		// db에서 자주 쓰는 정보 받아와서 저장
		this.dbtodata();
		
		// backward, forward 정보 저장용 리스트 생성
		p_backwardlog = new ArrayList<String>();
		p_forwardlog = new ArrayList<String>();

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
		spanel9 = new JPanel(new BorderLayout());

		// 로그인 패널 생성
		LoginForm tp = new LoginForm();
		rememberIdCheckBox = tp.rememberIdCheckBox;

		// 메인 패널 생성
		MainPanel nmp = new MainPanel();

		// westbar 생성
		Main_west mp = new Main_west();
		westbar = mp.p_main_west;
		jb_exinconinsert = mp.jb_teamExpectIncomeSet;

		// northbar 생성
		NorthMenuBar nb = new NorthMenuBar();
		northbar = nb;
		fmenu = nb.b_menufavorites;
		jb_logout = nb.b_menuclose;
		b_menufavorites = nb.b_menufavorites;
		favoritesComboBox = nb.favoritesComboBox;
		
		favoritesComboBox.addActionListener(this);

		// 장부 입력 메뉴화면 호출
		State_Input isp = new State_Input();
		ispdtm = isp.dtm;
		ispjt = isp.jt_s;
		isp_dateinsert = isp.jb_dateinsert;
		isp_dateinsert.addMouseListener(this);
		// 장부 입력화면 테이블에 대한 이벤트 리스너
		/*
		 * isplist = new ArrayList<String>(); ispdtm = isp.dtm;
		 * ispdtm.addTableModelListener(new TableModelListener() {
		 * 
		 * @Override public void tableChanged(TableModelEvent e) { int row =
		 * e.getFirstRow(); int column = e.getColumn();
		 * 
		 * if (column >= 0) { // 컬럼이 -1이면 구조 변경이므로 무시
		 * isplist.add(isp.jt_s.getValueAt(row, column).toString()); } } });
		 */
		// 장부 조회 화면 호출
		ShowState ssp = new ShowState();
		sspdtm = ssp.dtm;
		sspjt = ssp.jt_s;

		// 부서 조회 화면 호출
		ShowTeam stp = new ShowTeam();
		stpdtm = stp.fs_tableModel;

		// 매출 조회 메뉴화면 호출
		IncomeSP sip = new IncomeSP();
		sip.inputdata(tnamelist);
		sip_tname = sip.jcb_dname;
		sipjt = sip.jt_s;

		// 매출 조회화면 테이블에 대한 이벤트 리스너
		siplist = new ArrayList<String>();
		sipdtm = sip.dtm;


		// 매출 분석 메뉴화면 호출
		IncomeAnalysis aip = new IncomeAnalysis();

		// 매출 분석 화면 테이블에 대한 이벤트 리스너
		aiplist = new ArrayList<String>();
		aipjt = aip.jt_s;
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
		sfpjt = sfp.t_table;
		
		
		
		// 숫자 표현 테이블에 , 구분자 표시 ex) 1,000
        // 숫자 포맷터 생성
        numberRenderer = new DefaultTableCellRenderer() {
            private final NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);

            @Override
            public void setValue(Object value) {
                if (value instanceof Number) {
                    setText(formatter.format(value));
                } else if (value != null) {  // Null이 아닐 때만 문자열로 변환
                    setText(value.toString());
                } else {
                    setText(null); // Null일 경우 빈 문자열 설정
                }
            }
        };

        

		// 환경설정 화면 호출
		urp = new CodeUserRegistration();
		urp_save = urp.btn_save;
		urp_close = urp.btn_close;
		btn_userRegi = urp.btn_userRegi;
		btn_codeRegi = urp.btn_codeRegi;
		btn_adminMode = urp.btn_adminMode;
		btn_deptRegi = urp.btn_deptRegi;

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
		cardpanel.add(spanel9, "RegistUserPanel");

		spanel1.add(nmp, "Center");
		spanel2.add(isp, "Center");
		spanel3.add(ssp, "Center");
		spanel4.add(stp, "Center");
		spanel5.add(sip, "Center");
		spanel6.add(aip, "Center");
		spanel7.add(sfp, "Center");
		spanel9.add(urp, "Center");

		// 호출된 인스턴스의 주소 연결
		login_btt = tp.jbSave;
		id_jtext = tp.jtId;
		pwd_jtext = tp.jpPw;
		sip_show = sip.jb_infoShow;
		si_date = sip.jcb_period;
		si_tname = sip.jcb_dname;
		is_date = isp.jc_date;
		stp_show = stp.b_check;
		isp_save = isp.jb_save;
		aip_show = aip.jb_infoShow;
		ai_sort = aip.jcb_order;
		sfp_show = sfp.b_check;
		sfp_statetype = sfp.cb_stateType;
		ssp_show = ssp.jb_save;
		ssp_date1 = ssp.jc_date1;
		ssp_date2 = ssp.jc_date2;
		jb_stateinput = mp.jb_stateInput;
		jb_stateselect = mp.jb_stateSelect;
		jb_teamselect = mp.jb_teamSelect;
		jb_incomeselect = mp.jb_incomeSelect;
		jb_incomeanalysis = mp.jb_incomeAnalysis;
		jb_fin_stselect = mp.jb_fin_st;
		jb_userregist = mp.jb_setting;
		jb_backward = nb.b_backward;
		jb_forward = nb.b_forward;
		ispjt = isp.jt_s;

		// 호출한 인스턴스와 이벤트 핸들러 연결
		outcard.show(this, "TitlePanel");
		login_btt.addActionListener(this);
		jb_stateinput.addActionListener(this);
		jb_stateselect.addActionListener(this);
		jb_exinconinsert.addActionListener(this);
		jb_teamselect.addActionListener(this);
		jb_incomeselect.addActionListener(this);
		jb_incomeanalysis.addActionListener(this);
		jb_fin_stselect.addActionListener(this);
		jb_userregist.addActionListener(this);
		jb_backward.addActionListener(this);
		jb_forward.addActionListener(this);
		jb_logout.addActionListener(this);
		fmenu.addActionListener(this);
		isp_save.addMouseListener(this);
		stp_show.addMouseListener(this);
		ssp_show.addMouseListener(this);
		ssp_date2.addActionListener(this);
		sip_show.addMouseListener(this);
		aip_show.addMouseListener(this);
		ai_sort.addActionListener(this);
		si_date.addActionListener(this);
		si_tname.addActionListener(this);
		sfp_show.addMouseListener(this);

		btn_deptRegi.addActionListener(this);
		btn_codeRegi.addActionListener(this);
		btn_userRegi.addActionListener(this);
		btn_adminMode.addActionListener(this);
		
		
		loadUserData();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

	}

	@Override // 이벤트 처리를 위한 기본적인 엑션 핸들러
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login_btt) {
			boolean grant = false;
			if (id_jtext.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if ( pwd_jtext.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			uiflist = new ArrayList<String>();
			uiflist.add(id_jtext.getText());
			uiflist.add(pwd_jtext.getText());
			try {
				SelectUser_Info su = new SelectUser_Info();
				rs = su.getSelection(uiflist);
				if (!rs.next()) {
					JOptionPane.showMessageDialog(this, "등록되지 않은 아이디입니다", "알림", JOptionPane.INFORMATION_MESSAGE);
					su.rs.close();
					su.pstmt.close();
					su.conn.close();
					return;
				} else {
					do {
						// db에 저장된 id와 pwd 모두 매치하지 않으면 grant는 false
						grant = uiflist.get(1).equals(rs.getString("pwd"));
						accesslv = rs.getInt("accesslv");
					} while (rs.next());
					su.rs.close();
					su.pstmt.close();
					su.conn.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			if (grant) {// 로그인 기능 활성화
				saveUserData();
				p_backwardlog.add("MainPanel");
				outcard.show(this, "menu");
				incard.show(cardpanel, "MainPanel");
				id_jtext.setText(null);
				pwd_jtext.setText(null);
			} else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
			uiflist.clear();
		} else if (e.getSource() == jb_stateinput && accesslv > 0) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("InsertStatePanel");
			incard.show(cardpanel, "InsertStatePanel");
		} else if (e.getSource() == jb_stateselect) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("SelectStatePanel");
			incard.show(cardpanel, "SelectStatePanel");
		} else if (e.getSource() == jb_exinconinsert) {
			CodeUserRegistration cur = new CodeUserRegistration();
			cur.insertincomeDialog(this).show();
		} else if (e.getSource() == jb_teamselect) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("SelectTeamPanel");
			incard.show(cardpanel, "SelectTeamPanel");
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
		} else if (e.getSource() == jb_userregist && accesslv > 1) {
			System.out.println(accesslv);
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("RegistUserPanel");
			incard.show(cardpanel, "RegistUserPanel");
		} else if (e.getSource() == jb_backward) {
			if (p_backwardlog.size() > 1) {
				p_forwardlog.add(p_backwardlog.getLast());
				incard.show(cardpanel, p_backwardlog.get(p_backwardlog.size() - 2));
				p_backwardlog.removeLast();
			} else {// 뒤로가거나 앞으로 갈 페이지 없으면 버튼 비활성화

			}
		} else if (e.getSource() == jb_forward) {
			if (p_forwardlog.size() > 0) {
				p_backwardlog.add(p_forwardlog.getLast());
				incard.show(cardpanel, p_forwardlog.getLast());
				p_forwardlog.removeLast();
			} else {

			}
		} else if (e.getSource() == jb_logout) {
			int logout = JOptionPane.showConfirmDialog(
				    this, 
				    "정말 로그아웃하시겠습니까?", 
				    "질문", 
				    JOptionPane.YES_NO_OPTION, 
				    JOptionPane.QUESTION_MESSAGE
				);
			switch(logout){
				case 1 : return;
			}
			
			accesslv = 0;
			p_forwardlog.clear();
			p_backwardlog.clear();
			loadUserData();
			outcard.show(this, "TitlePanel");
		} else if (e.getSource() == ssp_date2) {
			if (!ssp_date2.getSelectedItem().toString().equals("전체")) {
				ssp_date2.getSelectedItem().toString();
				Date date = Date.valueOf(ssp_date2.getSelectedItem().toString());
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String[] newdate1 = new String[30];
				for (int i = 0; i < newdate1.length; i++) {
					cal.add(Calendar.DATE, -1);
					Date ndate = new Date(cal.getTimeInMillis());
					newdate1[i] = sdf.format(ndate);
				}
				for (int i = 0; i < newdate1.length; i++) {
					// System.out.println(newdate1[i]);
				}
				ssp_date1.setModel(new DefaultComboBoxModel<>(newdate1));
			}
		} else if (e.getSource() == btn_userRegi) {
			urp.openUserRegistrationDialog(this).show(true);

		} else if (e.getSource() == btn_codeRegi) {
			urp.openCodeRegistrationDialog(this, smcodelist).show(true);
			dbtodata();
			

		} else if (e.getSource() == btn_adminMode) {
			urp.openAdminModeDialog(this).show(true);
		} else if (e.getSource() == btn_deptRegi) {
			urp.insertDeptDialog(this).show(true);
			this.dbtodata();
			String [] tnlist = new String [tnamelist.size()+1]; 
			 tnlist[0] = "전체";
			 for (int i = 1; i < tnlist.length; i++) {
				 tnlist[i] = tnamelist.get(i-1); 
			}
			 sip_tname.setModel(new DefaultComboBoxModel<>(tnlist));
		} else if (e.getSource() == favoritesComboBox) {
			favoritesComboBox.getSelectedItem().toString();
		}
	}
	
		
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == isp_save) {
			try {
				isplist = new ArrayList<String>();
				// 사용자가 수정을 완료하지 않고 저장 버튼을 누르면 수정하던 내용을 포함시켜서 처리하는 부분
				int row = ispjt.getEditingRow();
				int col = ispjt.getEditingColumn();
				if (row != -1 && col != -1) {
					// getEditingRow와 getEditingColumn은 api에서 찾았는데
					// 수정중인 텍스트를 불러오는 방법은 검색만으로 도저히 알아낼 수 없어서 ai의 힘을 빌렸습니다 ㅜㅜ
					Component editorComponent = ispjt.getEditorComponent();
					if (editorComponent instanceof JTextField) {
						JTextField textField = (JTextField) editorComponent;
						editingdata = textField.getText();
						textField.setText(null);
					}
					ispdtm.setValueAt(null, row, col);
				}

				for (int i = 1; i < ispjt.getColumnCount(); i++) {
					if (ispjt.getValueAt(0, i) == null) {
						isplist.add(editingdata);
					} else {
						isplist.add(ispjt.getValueAt(0, i).toString());
					}
				}

				InsertState is = new InsertState();
				int cnt = is.getDML(isplist);
				is.pstmt.close();
				is.conn.close();
				System.out.println(cnt + "행 state에 insert 성공!");

				// 주비교자는 map에 보조 비교자는 list에 담아서 전달
				// 주비교자는 ?를 통해서 세팅할 수 없기 때문
				if (isplist.get(1).equals("6")) {
					isp_map = new HashMap<Integer, String>();
					uilist = new ArrayList<String>();

					int mon = Integer.parseInt(isplist.get(0).substring(5, 7));
					switch (mon) {
					case 1:
						isp_map.put(0, "JAN");
						break;
					case 2:
						isp_map.put(0, "FEB");
						break;
					case 3:
						isp_map.put(0, "MAR");
						break;
					case 4:
						isp_map.put(0, "APR");
						break;
					case 5:
						isp_map.put(0, "MAY");
						break;
					case 6:
						isp_map.put(0, "JUN");
						break;
					case 7:
						isp_map.put(0, "JUL");
						break;
					case 8:
						isp_map.put(0, "AUG");
						break;
					case 9:
						isp_map.put(0, "SEP");
						break;
					case 10:
						isp_map.put(0, "OCT");
						break;
					case 11:
						isp_map.put(0, "NOV");
						break;
					case 12:
						isp_map.put(0, "DEC");
						break;
					default:
					}
					uilist.add(isplist.get(3));
					uilist.add(isplist.get(2));
					UpdateIncome ui = new UpdateIncome();
					cnt = ui.getDML(isp_map, uilist);
					System.out.println(cnt + "income에 update 완료!");
					
					isplist.clear();
					row = ispdtm.getRowCount();
					ispdtm.setRowCount(0);
					ispdtm.setRowCount(row);
					JOptionPane.showMessageDialog(null, "정보가 저장 되었습니다", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLRecoverableException e1) {
				JOptionPane.showMessageDialog(this, "조회할 데이터가 없습니다", "정보", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(this, "입력한 값이 형식과 맞지 않습니다!", "경고", JOptionPane.WARNING_MESSAGE);
			} catch (IllegalArgumentException e3 ) {
				JOptionPane.showMessageDialog(this, "컬럼에 공백이 입력되었습니다!", "경고", JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource() == sip_show) { 
			int rows = sipdtm.getRowCount();
			sipdtm.setRowCount(0);
			sipdtm.setRowCount(rows);

			try {
				String tname = si_tname.getSelectedItem().toString();
				int rowcnt = 0;
				SelectIncome si = new SelectIncome();
				rs = si.getSelection();
				if (!rs.next()) {
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
				} else {
					do {
						// 기존의 row를 삭제 후 새 row 삽입
						if (tname.equals("전체")) {
							
							sipdtm.removeRow(rowcnt);
							sipdtm.insertRow(rowcnt,
									new Object[] { tnamelist.get((rs.getInt("DEPT") / 10) - 1), rs.getInt("JAN")/1000000, rs.getInt("FEB")/1000000,
											rs.getInt("MAR")/1000000, rs.getInt("APR")/1000000, rs.getInt("MAY")/1000000,
											rs.getInt("JUN")/1000000, rs.getInt("JUL")/1000000, rs.getInt("AUG")/1000000,
											rs.getInt("SEP")/1000000, rs.getInt("OCT")/1000000, rs.getInt("NOV")/1000000,
											rs.getInt("DEC")/1000000, rs.getInt("EXPECTINCOME") });
						} else {
							if (tname.equals(tnamelist.get((rs.getInt("DEPT") / 10) - 1))) { // 현재 부서 넘버 20번이 건너 뛰어졌음
								rowcnt = 0;
								sipdtm.removeRow(rowcnt);
								sipdtm.insertRow(rowcnt,
										new Object[] { tnamelist.get((rs.getInt("DEPT") / 10) - 1), rs.getInt("JAN")/1000000, rs.getInt("FEB")/1000000,
												rs.getInt("MAR")/1000000, rs.getInt("APR")/1000000, rs.getInt("MAY")/1000000,
												rs.getInt("JUN")/1000000, rs.getInt("JUL")/1000000, rs.getInt("AUG")/1000000,
												rs.getInt("SEP")/1000000, rs.getInt("OCT")/1000000, rs.getInt("NOV")/1000000,
												rs.getInt("DEC")/1000000, rs.getInt("EXPECTINCOME") });
							}
						}
						rowcnt++;
					} while (rs.next());
					for (int i = 1; i < sipdtm.getColumnCount(); i++) {
						sipjt.getColumnModel().getColumn(i).setCellRenderer(numberRenderer);
					}
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
					sipdtm.fireTableDataChanged(); // 패널 안 바뀔 때 새로고침
				}

			} catch (SQLRecoverableException e1) {
				JOptionPane.showMessageDialog(this, "조회할 데이터가 없습니다", "데이터 조회 실패", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e2) {
				e2.printStackTrace();
			} 
		} else if (e.getSource() == stp_show) {
			try {
				SelectTeam st = new SelectTeam();
				rs = st.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					st.rs.close();
					st.pstmt.close();
					st.conn.close();
				} else {
					do {
						stpdtm.removeRow(rowcnt);
						stpdtm.insertRow(rowcnt, new Object[] { rs.getString("DEPT"), rs.getString("Teamname") });
						// 기존의 row를 삭제 후 새 row 삽입

						rowcnt++;
					} while (rs.next());
					st.rs.close();
					st.pstmt.close();
					st.conn.close();
				}
			} catch (SQLRecoverableException e1) {
				JOptionPane.showMessageDialog(this, "조회할 데이터가 없습니다", "데이터 조회 실패", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} else if (e.getSource() == aip_show) {
			// 필요한 정보들 부서이름, 예상매출액, 누적매출액, 달성률
			// team, income table에서 각각 select 받은 정보로 테이블 구성해서 보여주기
			try {
				SelectIncome ai = new SelectIncome();
				rs = ai.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					for (int i=0; i<tnamelist.size(); i++) {
						aipdtm.removeRow(i);
						aipdtm.insertRow(i, new Object[] { tnamelist.get(i), "0", "0", "0%" });
					}
					ai.rs.close();
					ai.pstmt.close();
					ai.conn.close();
				} else {
					do {
						double rate;
						double sum = rs.getInt("JAN") + rs.getInt("FEB") + rs.getInt("MAR") + rs.getInt("APR")
								+ rs.getInt("MAY") + rs.getInt("JUN") + rs.getInt("JUL") + rs.getInt("AUG")
								+ rs.getInt("SEP") + rs.getInt("OCT") + rs.getInt("NOV") + rs.getInt("DEC");
						sum /= 1000000;
						if (sum == 0) {
							rate = 0;
						}else {
							//rate = Math.round((rs.getInt("EXPECTINCOME") / sum) / 10) * 10;
							rate = sum/rs.getInt("EXPECTINCOME")*100;
						}
						// 기존의 row를 삭제 후 새 row 삽입
						aipdtm.removeRow(rowcnt);
						aipdtm.insertRow(rowcnt, new Object[] { tnamelist.get(rowcnt),
								rs.getInt("EXPECTINCOME"), sum, rate+"%" });
						rowcnt++;
					} while (rs.next());
					for (int i = 1; i < aipdtm.getColumnCount()-1; i++) {
						aipjt.getColumnModel().getColumn(i).setCellRenderer(numberRenderer);
					}
					ai.rs.close();
					ai.pstmt.close();
					ai.conn.close();
				}
			}catch (SQLRecoverableException e1) {
				JOptionPane.showMessageDialog(this, "조회할 데이터가 없습니다", "데이터 조회 실패", JOptionPane.INFORMATION_MESSAGE);
			}catch (SQLException e2) {
				e2.printStackTrace();	
			}
		} else if (e.getSource() == isp_dateinsert) {
			for (int i = 0; i < ispdtm.getRowCount(); i++) {
				if (ispdtm.getValueAt(i, 1) == null) {
					if (i != 0) {
						ispdtm.removeRow(i);
						ispdtm.insertRow(i, new Object[] { null, is_date.getSelectedItem().toString(),
								ispdtm.getValueAt(i - 1, 2), ispdtm.getValueAt(i - 1, 3), null });
					} else {
						ispdtm.removeRow(i);
						ispdtm.insertRow(i,
								new Object[] { null, is_date.getSelectedItem().toString(), null, null, null });
					}

				} else {
					continue;
				}
				break;
			}

		} else if (e.getSource() == sfp_show) {
			SelectFin_st sf;
			String stype = sfp_statetype.getSelectedItem().toString();
			int rnum = sfpdtm.getRowCount();
			sfpdtm.setRowCount(0);
			sfpdtm.setRowCount(rnum);
			try {
				sf = new SelectFin_st();
				rs = sf.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					System.out.println("가져올 데이터가 없어요");
					rs.close();
					sf.pstmt.close();
					sf.conn.close();
				} else {
					do {
						if (stype.equals("재무상태표")) {
							if (rs.getInt("smcode") < 7) {
								sfpdtm.removeRow(rowcnt);
								sfpdtm.insertRow(rowcnt,
										new Object[] { smcodelist.get(rs.getInt("smcode")-1), rs.getInt("AMOUNT") });
								rowcnt++;
							}
						} else {
							if (rs.getInt("smcode") >= 7) {// 계정과목 목록 수정 고려
								sfpdtm.removeRow(rowcnt);
								sfpdtm.insertRow(rowcnt,
										new Object[] { smcodelist.get(rs.getInt("smcode")-1), "(" + rs.getInt("AMOUNT") + ")" });
								rowcnt++;
							}
						}
						
					} while (rs.next());
					sfpjt.getColumnModel().getColumn(1).setCellRenderer(numberRenderer);
					rs.close();
					sf.pstmt.close();
					sf.conn.close();
				}
			} catch (SQLRecoverableException e1) {
				JOptionPane.showMessageDialog(this, "조회할 데이터가 없습니다", "데이터 조회 실패", JOptionPane.INFORMATION_MESSAGE);
			}catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		} else if (e.getSource() == ssp_show) {
			int rows = sspdtm.getRowCount();
			sspdtm.setRowCount(0);
			sspdtm.setRowCount(rows);
			try {
				sspdatelist = new ArrayList<String>();
				if (!ssp_date1.getSelectedItem().toString().equals("전체")) {
					sspdatelist.add(ssp_date1.getSelectedItem().toString());
					sspdatelist.add(ssp_date2.getSelectedItem().toString());
				}
				SelectState ss = new SelectState();
				if (sspdatelist.isEmpty()) {
					rs = ss.getSelection();
				} else {
					rs = ss.getSelection(sspdatelist);
				}
				int rowcnt = 0;
				if (!rs.next()) {
					System.out.println("가져올 데이터가 없어요");
					rs.close();
					ss.pstmt.close();
					ss.conn.close();
					// 비어있는 rs
				}
				do {
					sspdtm.removeRow(rowcnt);
					sspdtm.insertRow(rowcnt, new Object[] { rs.getInt("LECNO"), rs.getString("LECDATE"),
							rs.getString("SMCODE"), rs.getInt("DEPT"), rs.getInt("AMOUNT") });
					rowcnt++;
				} while (rs.next());
				sspjt.getColumnModel().getColumn(4).setCellRenderer(numberRenderer);
				rs.close();
				ss.pstmt.close();
				ss.conn.close();
			} catch (SQLRecoverableException e1) {
				JOptionPane.showMessageDialog(this, "조회할 데이터가 없습니다", "데이터 조회 실패", JOptionPane.INFORMATION_MESSAGE); 
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
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
	
	public void dbtodata() {
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
				do {
					tnamelist.add(rs.getString("teamname"));
				} while (rs.next());
			}

			at.rs.close();
			at.pstmt.close();
			at.conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			SelectTitle sf = new SelectTitle();
			smcodelist = new ArrayList<String>();
			rs = sf.getSelection();
			if (!rs.next()) {
				System.out.println("가져올 row가 없자너 ㅠㅠ");
				sf.rs.close();
				sf.pstmt.close();
				sf.conn.close();
			} else {
				do {
					smcodelist.add(rs.getString("CODENAME"));
				} while (rs.next());
			}

			sf.rs.close();
			sf.pstmt.close();
			sf.conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	    
	    public void saveUserData() {
	        File file = new File("text//userinfo");
	        System.out.println(id_jtext.getText());
	        try {
	            if (!file.exists()) {
	                // 부모 디렉터리 생성
	                file.getParentFile().mkdirs();
	                file.createNewFile(); // 파일 생성
	            }

	            // MS949 인코딩을 사용하여 파일 작성
	            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "MS949"))) {
	                if (rememberIdCheckBox.isSelected()) {
	                    writer.write(id_jtext.getText() + "\n"); // ID 저장
	                    writer.write("true"); // 체크박스 상태 저장
	                } else {
	                    // 체크 해제 시 파일 삭제 (정보 초기화)
	                    file.delete();
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        rememberIdCheckBox.setSelected(false);
	    }

	    public void loadUserData() {
	        File file = new File("text//userinfo");
	        if (!file.exists()) return;

	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "MS949"))) {
	            String savedId = reader.readLine();
	            String isChecked = reader.readLine();

	            if (savedId != null) id_jtext.setText(savedId); // 저장된 아이디 불러오기
	            rememberIdCheckBox.setSelected("true".equals(isChecked)); // 체크박스 상태 적용

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	

	    public static void main(String[] args) {
	        // TransparentLogo 실행
	        TransparentLogo tl = new TransparentLogo();
	        
	        // TransparentLogo가 닫힐 때 Finance_Management 실행
	        tl.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosed(java.awt.event.WindowEvent e) {
	                SwingUtilities.invokeLater(() -> {
	                    // Finance_Management 실행
	                    Finance_Management fm = new Finance_Management();
	                    fm.setSize(800, 800);
	                    fm.setLocationRelativeTo(null);
	                    fm.setVisible(true);
	                });
	            }
	        });
	    }
}