package semiProject2;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI; //콤보박스 화살표 디자인위해 임포트

public class Income_infoShow extends JFrame{
	
	JPanel p_main_west,p_main_center,p_stateInput,p_siNorth,p_search,p_save;
	Container cont;
	JLabel l_menu1,l_y,l_m,l_d,jl_dname,jl_period;
	JComboBox jcb_period; //기간
	JTextField jtf_d,jtf_dname;
	JTable jt_s;
	DefaultTableModel dtm;
	JScrollPane jsp_jt;
	JButton jb_infoShow;
	
	
	
	public Income_infoShow() {
		//gui 서쪽에 들어갈 서쪽판넬 및 센터에 들어갈 입력판넬 생성
		p_main_west = new JPanel(new GridLayout(10,1));
		p_stateInput = new JPanel(new BorderLayout());
		
		//전표입력판넬 위쪽에 들어갈 판넬 생성
		p_siNorth = new JPanel(new GridLayout(1,2));
		//입력판넬 맨 위에 들어갈 날짜입력판넬 생성
		p_search = new JPanel(new FlowLayout(FlowLayout.LEFT));


		


		//날짜입력판넬에 들어갈 연도,월 콤보박스 생성 + 일 텍스트 필드
//		String[] y={"2020", "2021", "2022", "2023","2024"};
		String[] m= {"전체","1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"};
		String[] columnName = {"부서번호","1월","2월","3월","4월","5월","6월","7월","8월","9월",
				"10월","11월","12월","예상 매출액"};
		
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
		
		
		//기간 입력하는 콤보박스랑 텍스트필드(길이:3) 생성 + 콤보박스 디자인
		jtf_d = new JTextField(3);
		jtf_dname = new JTextField(3);
		jcb_period = new JComboBox(m);this.jcb_design(jcb_period);
		jb_infoShow = new JButton("조회");jb_infoShow.setBackground(Color.white);
		
		//날짜 관련 라벨 생성
		jl_dname = new JLabel("부서 이름:");
		jl_period = new JLabel("기간:");
		
		//날짜입력판넬에 콤보박스&라벨 추가
		

		p_search.add(jl_dname);
		p_search.add(jtf_dname);
		
		p_search.add(jl_period);
		p_search.add(jcb_period);
		
		p_search.add(jb_infoShow);
		
		

		
		//jt_s테이블에 스크롤을 추가하고 그 스크롤을 전표입력판넬에 추가 + jt_s 테이블을 입력판넬에 추가
		jsp_jt =new JScrollPane(jt_s);
		jsp_jt.setOpaque(true);
		jsp_jt.getViewport().setOpaque(true);
		jsp_jt.setBackground(Color.white);
		jsp_jt.getViewport().setBackground(Color.white);
		jt_s.setOpaque(true);
		jt_s.setBackground(Color.white);
		
		p_stateInput.add(jsp_jt);
		//입력판넬에 날짜입력판넬 추가
		p_stateInput.add(p_siNorth,"North");
		p_siNorth.add(p_search);

		
		//JFrame가장 바깥 pane인 컨텐트페인을 담을 객체 선언하고 대입
		cont =getContentPane();
		
		//위에서 생성했던 컨텐트페인 담긴 객체 cont에 서쪽판넬이랑 입력판넬 추가
		cont.add(p_main_west,"West");
		cont.add(p_stateInput,"Center");
		
		//서쪽판넬 배경 검은색
		p_main_west.setBackground(Color.decode("#262627"));
		p_stateInput.setOpaque(true);
		p_siNorth.setOpaque(true);
		
		//서쪽판넬 크기 맞추기 위해 추가할 임의의 라벨 생성 
		l_menu1 = new JLabel("회계관리");
		l_menu1.setForeground(Color.white);
		l_menu1.setFont(new Font("맑은 고딕",Font.BOLD,20));
		l_menu1.setHorizontalAlignment(JLabel.CENTER);
		
		//서쪽판넬에 라벨 추가
		p_main_west.add(l_menu1);

		
		// p_siNorth 패널 배경색 적용
		p_siNorth.setOpaque(true);
		p_siNorth.setBackground(Color.decode("#FFFFFF"));

		// 내부 패널들도 배경색 적용
		p_search.setOpaque(true);
		p_search.setBackground(Color.decode("#FFFFFF"));
		
		
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
	

	
	public static void main(String[] args) {
		Income_infoShow iis=new Income_infoShow();
		iis.setSize(900,600);
		iis.setVisible(true);
		iis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

}
