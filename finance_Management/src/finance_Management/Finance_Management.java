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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Finance_Management extends Frame implements ActionListener, MouseListener, MouseMotionListener, KeyListener{
	
	HashMap <Integer, String> isp_map, sfp_map, ssp_map;
	ArrayList <String> uiflist, isplist, siplist, uilist, aiplist, sfp_list, tnamelist, sfplist, ssplist, sspdatelist, tiplist, p_backwardlog, p_forwardlog;
	ResultSet rs, sirs;
	JButton login_btt, isp_save, isp_dateinsert, sip_show, aip_show, ti_input, sfp_show, ssp_show, stp_show, jb_teaminput, jb_stateinput, jb_stateselect, 
	jb_incomeselect, jb_incomeanalysis, jb_fin_stselect, jb_teamselect, jb_backward, jb_forward, jb_userregist;
	JTextField id_jtext, pwd_jtext, ti_jtext, si_jtext;
	CardLayout incard, outcard;
	JPanel cardpanel, totalpanel, spanel0, spanel1, spanel2, spanel3, spanel4, spanel5, spanel6, spanel7, spanel8, spanel9, westbar, northbar;
	DefaultTableModel ispdtm, sipdtm, aipdtm, sfpdtm, sspdtm, stpdtm;
	Integer backcnt, forcnt, accesslv;
	JTable ispjt, aipjt;
	JComboBox is_date, ai_sort, si_tname, si_date, sfp_statetype, ssp_date1, ssp_date2;
	String editingdata;
	// ���� ���� ��� ������ ��ü ����Ģ �ٲٱ�...
	
	public Finance_Management() {
		
		
		// ���� ���� ���� db���� �޾ƿͼ� �����صα�
		try {
			SelectTeam at = new SelectTeam();
			tnamelist = new ArrayList<String>();
			rs = at.getSelection();
			if (!rs.next()) {
				System.out.println("������ row�� ���ڳ� �Ф�");
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
		
				// backward, forward ���� ����� ����Ʈ ����
				p_backwardlog = new ArrayList <String>();
				p_forwardlog = new ArrayList <String>();
				
		
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
				spanel4 = new JPanel(new BorderLayout());
				spanel5 = new JPanel(new BorderLayout());
				spanel6 = new JPanel(new BorderLayout());
				spanel7 = new JPanel(new BorderLayout());
				spanel8 = new JPanel(new BorderLayout());
				spanel9 = new JPanel(new BorderLayout());
				
				
				// �α��� �г� ����
				LoginForm tp = new LoginForm();
				
				// westbar ����
				Main_west mp = new Main_west();
				westbar = mp.p_main_west;
				
				// northbar ����
				NorthMenuBar nb  = new NorthMenuBar();
				northbar = nb;
				
				// ��� �Է� �޴�ȭ�� ȣ��
				State_Input isp = new State_Input();
				ispdtm = isp.dtm;
				// ��� �Է�ȭ�� ���̺� ���� �̺�Ʈ ������
				/*
				 * isplist = new ArrayList<String>(); ispdtm = isp.dtm;
				 * ispdtm.addTableModelListener(new TableModelListener() {
				 * 
				 * @Override public void tableChanged(TableModelEvent e) { int row =
				 * e.getFirstRow(); int column = e.getColumn();
				 * 
				 * if (column >= 0) { // �÷��� -1�̸� ���� �����̹Ƿ� ����
				 * isplist.add(isp.jt_s.getValueAt(row, column).toString()); } } });
				 */
				// ��� ��ȸ ȭ�� ȣ��
				ShowState ssp = new ShowState();
				sspdtm = ssp.dtm;
				
				// �μ� �Է� ȭ�� ȣ��
				TeamInsert tip = new TeamInsert();
				
				
				// �μ� ��ȸ ȭ�� ȣ��
				ShowTeam stp = new ShowTeam();
				stpdtm = stp.fs_tableModel;
				
				// ���� ��ȸ �޴�ȭ�� ȣ��
				IncomeSP sip = new IncomeSP();
				sip.jcb_dname.addItem("��ü");
				sip.jcb_dname.addItem(tnamelist);
				
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
				
				// ���� �м� �޴�ȭ�� ȣ��
				IncomeAnalysis aip = new IncomeAnalysis();
				
				// ���� �м� ȭ�� ���̺� ���� �̺�Ʈ ������
				aiplist = new ArrayList<String>();
				aipjt = aip.jt_s; 
				aipdtm = aip.dtm;
				aipdtm.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						int row = e.getFirstRow();
						int column = e.getColumn();

						if (column >= 0) { // �÷��� ������ ���������̹Ƿ� ����
							aiplist.add(aip.jt_s.getValueAt(row, column).toString());
						}
					}
				});
				
				// �繫��ǥ ��ȸ ȭ�� ȣ��
				FinancialStatements sfp = new FinancialStatements();
				sfpdtm = sfp.fs_tableModel;
				
				
				CodeUserRegistration urp = new CodeUserRegistration();
				
				
				
				// �г� ����
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
				cardpanel.add(spanel8, "InsertTeamPanel");
				cardpanel.add(spanel9, "RegistUserPanel");
				
				
				spanel1.add(mp, "Center");
				spanel2.add(isp, "Center");
				spanel3.add(ssp, "Center");
				spanel4.add(stp, "Center");
				spanel5.add(sip, "Center");
				spanel6.add(aip, "Center");
				spanel7.add(sfp, "Center");
				spanel8.add(tip, "Center");
				spanel9.add(urp, "Center");
				
				
				
				
				
				
				// ȣ��� �ν��Ͻ��� �ּ� ����
				login_btt = tp.check;
				id_jtext = tp.jtId;
				pwd_jtext = tp.jtPw;
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
				ssp_date2 = ssp.jc_date1;
				jb_stateinput = mp.jb_stateInput;
				jb_stateselect = mp.jb_stateSelect;
				jb_teaminput = mp.jb_teamInput;
				jb_teamselect = mp.jb_teamSelect;
				jb_incomeselect = mp.jb_incomeSelect;
				jb_incomeanalysis = mp.jb_incomeAnalysis;
				jb_fin_stselect = mp.jb_fin_st;
				jb_userregist = mp.jb_userRegiser;
				jb_backward = nb.b_backward;
				jb_forward = nb.b_forward;
				ispjt = isp.jt_s;
				isp_dateinsert = isp.jb_dateinsert;
				
				
				
				// ȣ���� �ν��Ͻ��� �̺�Ʈ �ڵ鷯 ����
				outcard.show(this, "TitlePanel");
				login_btt.addActionListener(this);
				jb_stateinput.addActionListener(this);
				jb_stateselect.addActionListener(this);
				jb_teaminput.addActionListener(this);
				jb_teamselect.addActionListener(this);
				jb_incomeselect.addActionListener(this);
				jb_incomeanalysis.addActionListener(this);
				jb_fin_stselect.addActionListener(this);
				jb_userregist.addActionListener(this);
				jb_backward.addActionListener(this);
				jb_forward.addActionListener(this);
				isp_save.addMouseListener(this);
				stp_show.addMouseListener(this);
				ssp_show.addMouseListener(this);
				ssp_date2.addActionListener(this);
				sip_show.addMouseListener(this);
				aip_show.addMouseListener(this);
				ai_sort.addActionListener(this);
				si_date.addActionListener(this);
				si_tname.addActionListener(this);
				isp_dateinsert.addMouseListener(this);
				sfp_show.addMouseListener(this);
				
				
				
				this.addWindowListener(new WindowAdapter() {
		            public void windowClosing(WindowEvent we) {
		                System.exit(0);
		            }
		        });

		 

	}
	
	
	@Override // �̺�Ʈ ó���� ���� �⺻���� ���� �ڵ鷯
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == login_btt) {
			 boolean grant = false;
			 uiflist = new ArrayList<String>();
			 uiflist.add(id_jtext.getText());
			 uiflist.add(pwd_jtext.getText());
			 try {
				SelectUser_Info su = new SelectUser_Info();
				rs = su.getSelection(uiflist);
				if (!rs.next()) {
					System.out.println("�߸��� ID�Դϴ�");
					su.rs.close();
					su.pstmt.close();
					su.conn.close();
				}else {
					do {
						// db�� ����� id�� pwd ��� ��ġ���� ������ grant�� false
						grant = uiflist.get(1).equals(rs.getString("pwd"));
						accesslv = rs.getInt("accesslv");
					}while(rs.next());
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
			if (true) {
				p_backwardlog.add("MainPanel");
				outcard.show(this, "menu");
				incard.show(cardpanel, "MainPanel");
			}else {
				System.out.println("�߸��� ��й�ȣ�Դϴ�");
			}
			uiflist.clear();
		} else if (e.getSource() == jb_stateinput) {
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
		}else if (e.getSource() == jb_teaminput) { // �г� ���� �������
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("InsertTeamPanel");
			incard.show(cardpanel, "InsertTeamPanel");
		} else if (e.getSource() == jb_teamselect) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("SelectTeamPanel");
			incard.show(cardpanel, "SelectTeamPanel");
		}else if (e.getSource() == jb_incomeselect) {
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
		} else if (e.getSource() == jb_userregist) {
			if (p_forwardlog.size() > 0) {
				p_forwardlog.clear();
			}
			p_backwardlog.add("RegistUserPanel");
			incard.show(cardpanel, "RegistUserPanel");
		} else if (e.getSource() == jb_backward) {
			if(p_backwardlog.size() > 1) {
			p_forwardlog.add(p_backwardlog.getLast());
			incard.show(cardpanel, p_backwardlog.get(p_backwardlog.size()-2));
			p_backwardlog.removeLast();
			}else {// �ڷΰ��ų� ������ �� ������ ������ ��ư ��Ȱ��ȭ
				
			}
		} else if (e.getSource() == jb_forward) {
			if(p_forwardlog.size() > 0) {
			p_backwardlog.add(p_forwardlog.getLast());
			incard.show(cardpanel, p_forwardlog.getLast());
			p_forwardlog.removeLast();
			}else {
				
			}
		} // ������� �޴���ư
		else if (e.getSource() == ai_sort) {
			aipjt.setAutoCreateRowSorter(true);
			TableRowSorter sorter = new TableRowSorter<TableModel>(aipjt.getModel());
			if (ai_sort.getSelectedItem().toString().equals("��������")) {
				System.out.println("��������������");
				sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
			}else {
				System.out.println("��������������");
				sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
			}
		} else if (true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == isp_save) {
			try {
				isplist = new ArrayList<String>();
				// ����ڰ� ������ �Ϸ����� �ʰ� ���� ��ư�� ������ �����ϴ� ������ ���Խ��Ѽ� ó���ϴ� �κ�
				int row = ispjt.getEditingRow();
				int col = ispjt.getEditingColumn();
				if (row != -1 && col != -1) {
				    // getEditingRow�� getEditingColumn�� api���� ã�Ҵµ� 
					// �������� �ؽ�Ʈ�� �ҷ����� ����� �˻������� ������ �˾Ƴ� �� ��� ai�� ���� ���Ƚ��ϴ� �̤�
				    Component editorComponent = ispjt.getEditorComponent();
				    if (editorComponent instanceof JTextField) {
				        JTextField textField = (JTextField) editorComponent;
				        editingdata = textField.getText();
				    }
				}
				
					for (int i = 1; i < ispjt.getColumnCount(); i++) {
						if (ispjt.getValueAt(0, i) == "") {
							isplist.add(editingdata);
						}else {
						 isplist.add(ispjt.getValueAt(0, i).toString());
						}
					}
					
					InsertState is = new InsertState();
					int cnt = is.getDML(isplist);
					is.pstmt.close();
					is.conn.close();
					System.out.println(cnt+"�� state�� insert ����!");
					
					// �ֺ��ڴ� map�� ���� ���ڴ� list�� ��Ƽ� ����
					// �ֺ��ڴ� ?�� ���ؼ� ������ �� ���� ����
					if(isplist.get(1).equals("6")) {
						isp_map = new HashMap<Integer, String>();
						uilist = new ArrayList<String>();
						
						int mon = Integer.parseInt(isplist.get(0).substring(5, 7));
						switch (mon) {
						case 1 : isp_map.put(0, "JAN"); break;
						case 2 : isp_map.put(0, "FEB"); break;
						case 3 : isp_map.put(0, "MAR"); break;
						case 4 : isp_map.put(0, "APR"); break;
						case 5 : isp_map.put(0, "MAY"); break;
						case 6 : isp_map.put(0, "JUN"); break;
						case 7 : isp_map.put(0, "JUL"); break;
						case 8 : isp_map.put(0, "AUG"); break;
						case 9 : isp_map.put(0, "SEP"); break;
						case 10 : isp_map.put(0, "OCT"); break;
						case 11 : isp_map.put(0, "NOV"); break;
						case 12 : isp_map.put(0, "DEC"); break;
						default :
						}
						uilist.add(isplist.get(3));
						uilist.add(isplist.get(2));
						UpdateIncome ui = new UpdateIncome();
						cnt = ui.getDML(isp_map, uilist);
						System.out.println(cnt+"income�� update �Ϸ�!");
						isplist.clear();
					}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == sip_show) {
			try {
				String tname = si_tname.getSelectedItem().toString();
				boolean a = true;
				int rowcnt = 0;
				SelectIncome si = new SelectIncome();
				//System.out.println("��ȸ �̺�Ʈ �߻�!");
				rs = si.getSelection();
				if (!rs.next()) {
					//System.out.println("������ row�� ���ڳ� �Ф�");
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
				} else {
					do {
						if (!tname.equals(tnamelist.get((rs.getInt("DEPT")/10)-1))) {
							a = false;
						}
						// ������ row�� ���� �� �� row ����
						if (a || tname.equals("��ü")) {
							sipdtm.removeRow(rowcnt);
							sipdtm.insertRow(rowcnt,
									new Object[] { rs.getString("DEPT"), rs.getString("JAN"), rs.getString("FEB"),
											rs.getString("MAR"), rs.getString("APR"), rs.getString("MAY"),
											rs.getString("JUN"), rs.getString("JUL"), rs.getString("AUG"),
											rs.getString("SEP"), rs.getString("OCT"), rs.getString("NOV"),
											rs.getString("DEC"), rs.getString("EXPECTINCOME") });
						}
					} while (rs.next());
					si.rs.close();
					si.pstmt.close();
					si.conn.close();
					sipdtm.fireTableDataChanged(); // �г� �� �ٲ� �� ���ΰ�ħ
					// System.out.println("�Ƹ� ��� �� �� �� ������~");
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource() == stp_show) {
			try {
				SelectTeam st = new SelectTeam();
				rs = st.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					//System.out.println("������ row�� ���ڳ� �Ф�");
					st.rs.close();
					st.pstmt.close();
					st.conn.close();
				}else {
					do {
						stpdtm.removeRow(rowcnt);
						stpdtm.insertRow(rowcnt, new Object[] {rs.getString("DEPT"), rs.getString("Teamname")});
						// ������ row�� ���� �� �� row ����
						
						rowcnt++;
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
			// �ʿ��� ������ �μ��̸�, ��������, ���������, �޼���
			// team, income table���� ���� select ���� ������ ���̺� �����ؼ� �����ֱ�
			try {
				SelectIncome ai = new SelectIncome();
				//System.out.println("��ȸ �̺�Ʈ �߻�!");
				rs = ai.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					//System.out.println("������ row�� ���ڳ� �Ф�");
					ai.rs.close();
					ai.pstmt.close();
					ai.conn.close();
				}else {
					do {
						int sum = rs.getInt("JAN")+rs.getInt("FEB")+rs.getInt("MAR")+rs.getInt("APR")+rs.getInt("MAY")+rs.getInt("JUN")+
						rs.getInt("JUL")+rs.getInt("AUG")+rs.getInt("SEP")+rs.getInt("OCT")+rs.getInt("NOV")+rs.getInt("DEC");
						if (sum == 0) {
							continue;
						}
						int rate = Math.round((rs.getInt("EXPECTINCOME")/sum)/10)*10;
						// ������ row�� ���� �� �� row ����
						aipdtm.removeRow(rowcnt);
						aipdtm.insertRow(rowcnt, new Object[] {tnamelist.get(rowcnt), rs.getString("EXPECTINCOME").toString(), ""+sum, ""+rate});
						rowcnt++;
					}while(rs.next());
					ai.rs.close();
					ai.pstmt.close();
					ai.conn.close();
					aipdtm.fireTableDataChanged();
					//System.out.println("�Ƹ� ��� �� �� �� ������~");
					}
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == isp_dateinsert) {
			int rowcnt = 0;
			ispdtm.insertRow(rowcnt, new Object[] {"", is_date.getSelectedItem().toString(), "", "", ""});
		} else if (e.getSource() == sfp_show) {
			SelectFin_st sf;
			String stype = sfp_statetype.getSelectedItem().toString();
			for (int i = 0; i < sfpdtm.getRowCount(); i++) {// ���̺��� ���븸 �ʱ�ȭ
				for (int j = 0; j < sfpdtm.getColumnCount(); j++) {
					sfpdtm.setValueAt("", i, j);
				}
			}
			
			try {
				sf = new SelectFin_st();
				rs = sf.getSelection();
				int rowcnt = 0;
				if (!rs.next()) {
					System.out.println("������ �����Ͱ� �����");
					rs.close();
					sf.pstmt.close();
					sf.conn.close();
				}
				do {
					if (stype.equals("�繫����ǥ")) {
						if (rs.getInt("smcode") < 7) {
							sfpdtm.removeRow(rowcnt);
							sfpdtm.insertRow(rowcnt, new Object[] { rs.getString("smcode"), rs.getString("AMOUNT") });
						} else {
							if (rs.getInt("smcode") >= 7 && rs.getInt("smcode") < 10) {
								sfpdtm.removeRow(rowcnt);
								sfpdtm.insertRow(rowcnt,
										new Object[] { rs.getString("smcode"), "("+rs.getString("AMOUNT")+")" });
							}
						}
					}
					rowcnt++;
			}while(rs.next());
				rs.close();
				sf.pstmt.close();
				sf.conn.close();
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == ssp_show) {
			try {
				if (!ssp_date1.getSelectedItem().toString().equals("��ü")) {
				sspdatelist.add(ssp_date1.getSelectedItem().toString());
				sspdatelist.add(ssp_date2.getSelectedItem().toString());	
				}
				SelectState ss = new SelectState();
				if (sspdatelist.isEmpty()) {
				rs = ss.getSelection();
				}else {
					rs = ss.getSelection(sspdatelist);
				}
				int rowcnt = 0;
				if(!rs.next()) {
					System.out.println("������ �����Ͱ� �����");
					rs.close();
					ss.pstmt.close();
					ss.conn.close();
				}do {
					sspdtm.removeRow(rowcnt);
					sspdtm.insertRow(rowcnt, new Object[] {rs.getString("LECNO"), rs.getString("LECDATE"), rs.getString("SMCODE"), rs.getString("DEPT"), rs.getString("AMOUNT")});
					rowcnt++;
				}while(rs.next());
				rs.close();
				ss.pstmt.close();
				ss.conn.close();
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
		// gui����
		fm.setSize(800, 800);
		fm.setVisible(true);
		//fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	
}