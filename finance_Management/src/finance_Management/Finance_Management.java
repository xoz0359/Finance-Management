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
				// 화면 전환용 카드 레이아웃 생성
				incard = new CardLayout();
				this.setLayout(incard);
				outcard = new CardLayout();
				cardpanel = new JPanel(outcard);
				spanel1 = new JPanel(new BorderLayout());
				spanel2 = new JPanel(new BorderLayout());
				spanel3 = new JPanel(new BorderLayout());
				
				// 로그인 패널 생성
				LoginForm tp = new LoginForm();
				
				// 메뉴바 생성
				Main_west mp = new Main_west();
				westbar = mp.p_main_west;
				// 장부 입력 메뉴화면 호출
				State_Input isp = new State_Input();
				//isp.add(mp.p_main_west, "West");
				
				// J테이블의 칼럼이 변경될 때 감지하는 이벤트 리스너
				list = new ArrayList<String>();
				DefaultTableModel ispdtm = isp.dtm;
				ispdtm.addTableModelListener(new TableModelListener() {
		            @Override
		            public void tableChanged(TableModelEvent e) {
		                int row = e.getFirstRow();
		                int column = e.getColumn();
		                
		                if (column >= 0) { // 컬럼이 -1이면 구조 변경이므로 무시
		                	list.add(isp.jt_s.getValueAt(row, column).toString());
		                }
		            }
		        });
				
				// 매출 조회 메뉴화면 호출
				IncomeSP sip = new IncomeSP();
				
				// 패널 조립
				this.add(tp, "TitlePanel");
				this.add(cardpanel, "menu");
				cardpanel.add(spanel1, "MainPanel");
				cardpanel.add(spanel2, "InsertStatePanel");
				cardpanel.add(spanel3, "SelectIncomPanel");
				
				spanel1.add(mp, "Center");
				spanel2.add(isp, "Center");
				spanel3.add(sip, "Center");
				
				
				
				// 자녀 클래스와 인스턴스 주소 연결
				jb_save = isp.jb_save;
				login_btt = tp.login_btt;
				jb_stateInput = mp.jb_stateInput;
				jb_incomeSelect = mp.jb_incomeSelect;
				
				
				// 화면 전환과 이벤트 핸들러 연결
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
		if (e.getSource() == jb_save) {
			try {
				InsertState is = new InsertState();
				int cnt = is.getDML(list);
				System.out.println(cnt+"행 등록 완료!");
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
		// gui생성
		fm.setSize(800, 800);
		fm.setVisible(true);
		//fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	
}
