package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI; //콤보박스 화살표 디자인위해 임포트

public class IncomeAnalysis extends JPanel{
	
	JPanel p_main_center,p_incomeAnalysis,p_iaNorth,p_eiInput,p_infoShow;
	Container cont;
	JLabel l_menu1,l_y,l_m,l_d,jl_dname,jl_period;
	JComboBox jcb_order; //기간
	JTextField jtf_d,jtf_dname;
	JTable jt_s;
	DefaultTableModel dtm;
	JScrollPane jsp_jt;
	JButton jb_eiInput,jb_infoShow;
	
	
	
	
	public IncomeAnalysis() {
		this.setLayout(new BorderLayout());
		//gui 서쪽에 들어갈 서쪽판넬 및 센터에 들어갈 입력판넬 생성
		p_incomeAnalysis = new JPanel(new BorderLayout());
		
		//전표입력판넬 위쪽에 들어갈 판넬 생성
		p_iaNorth = new JPanel(new BorderLayout());
		//입력판넬 맨 위에 들어갈 날짜입력판넬 생성
		p_infoShow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p_eiInput = new JPanel(new FlowLayout(FlowLayout.LEFT));


		


		//날짜입력판넬에 들어갈 연도,월 콤보박스 생성 + 일 텍스트 필드
//		String[] y={"2020", "2021", "2022", "2023","2024"};
//		String[] m= {"전체","1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"};
		String[] columnName = {"부서이름","예상 매출액","누적매출액","달성률"};
		String[] order = {"내림차순","오름차순"};
		
		//테이블모델 객체 생성 & 설정(컬럼명들, 행 숫자)
		dtm = new DefaultTableModel(columnName,30) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return column!=0 ;
			}
		};
		
		//테이블 객체를 생성해 놓은 테이블 모델을 이용해 생성
		jt_s = new JTable(dtm);
		
		
		//예상매출액 입력 다이얼로그 버튼 생성
		jb_eiInput=new JButton("예상매출액 입력");jb_eiInput.setBackground(Color.white);
		jb_infoShow = new JButton("조회");jb_infoShow.setBackground(Color.white);
		jcb_order = new JComboBox(order);this.jcb_design(jcb_order);
		
		
		p_iaNorth.add(p_eiInput,"West");
		p_iaNorth.add(p_infoShow,"East");
	
		
		//예상 매출액 입력판넬에 다이얼로그 버튼 추가
		//p_eiInput.add(jb_eiInput);
		//p_infoShow.add(jcb_order);
		p_infoShow.add(jb_infoShow);
		
		
	
		
		

		
		//jt_s테이블에 스크롤을 추가하고 그 스크롤을 전표입력판넬에 추가 + jt_s 테이블을 입력판넬에 추가
		jsp_jt =new JScrollPane(jt_s);
		jsp_jt.setOpaque(true);
		jsp_jt.getViewport().setOpaque(true);
		jsp_jt.setBackground(Color.white);
		jsp_jt.getViewport().setBackground(Color.white);
		jt_s.setOpaque(true);
		jt_s.setBackground(Color.white);
		
		p_incomeAnalysis.add(jsp_jt);
		//입력판넬에 날짜입력판넬 추가
		p_incomeAnalysis.add(p_iaNorth,"North");
		p_iaNorth.add(p_eiInput);
		
		//위에서 생성했던 컨텐트페인 담긴 객체 cont에 서쪽판넬이랑 입력판넬 추가
		this.add(p_incomeAnalysis,"Center");
		TitleSet ts = new TitleSet("매출 분석");
		this.add(ts.jp_title,"North");
		
		//서쪽판넬 배경 검은색
		p_incomeAnalysis.setOpaque(true);
		p_iaNorth.setOpaque(true);
		
		//서쪽판넬 크기 맞추기 위해 추가할 임의의 라벨 생성 
		l_menu1 = new JLabel("회계관리");
		l_menu1.setForeground(Color.white);
		l_menu1.setFont(new Font("맑은 고딕",Font.BOLD,20));
		l_menu1.setHorizontalAlignment(JLabel.CENTER);
		
		// p_siNorth 패널 배경색 적용
		p_iaNorth.setOpaque(true);
		p_iaNorth.setBackground(Color.decode("#FFFFFF"));

		// 내부 패널들도 배경색 적용
		p_eiInput.setOpaque(true);
		p_eiInput.setBackground(Color.decode("#FFFFFF"));
		
		p_infoShow.setOpaque(true);
		p_infoShow.setBackground(Color.WHITE);

		
		
	}
	//콤보박스 화살표 디자인
	public void jcb_design(JComboBox jcb) {
		jcb.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				// TODO Auto-generated method stub
				JButton button =  super.createArrowButton();
				button.setBackground(Color.white);
				button.setForeground(Color.black);
				return button;
			}
		});
	}


}
