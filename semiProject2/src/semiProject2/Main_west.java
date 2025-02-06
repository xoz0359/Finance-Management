package semiProject2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main_west extends JFrame{
	
	Container cont1;
	JPanel p_main_west,p_main_center;
	JLabel title,l_menu1,l_menu2,l_menu3,l_menu4;
	JMenuBar jmb;
	JButton jb_stateInput,jb_stateSelect,jb_teamInput,jb_teamSelect,jb_incomeSelect,jb_incomeAnalysis,jb_fin_st;
	
	
	public Main_west() {
		//센터판넬과 서쪽판넬 생성
		p_main_west = new JPanel(new GridLayout(10,1));
		p_main_center = new JPanel(new GridLayout(1,1));
		
		//메인 화면에 센터판넬에 들어갈 라벨 생성
		title = new JLabel("재무 관리 프로그램v1.0",JLabel.CENTER);
		
		//센터판넬에 라벨 추가
		p_main_center.add(title);
		
		//JFrame가장 바깥 pane인 컨텐트페인을 담을 객체 선언하고 대입
		Container cont=getContentPane();
		
		//위에서 생성했던 컨텐트페인 담긴 객체 cont에 서쪽판넬이랑 중심판넬 추가
		cont.add(p_main_west,"West");
		cont.add(p_main_center,"Center");
		
		//서쪽판넬 배경 검은색
		p_main_west.setBackground(Color.black);

		//큰 메뉴 객체 생성 & 디자인 메서드 사용
		l_menu1 = new JLabel("회계 관리");this.l_menu_design(l_menu1);
		l_menu2 = new JLabel("부서 관리");this.l_menu_design(l_menu2);
		l_menu3 = new JLabel("매출 정보");this.l_menu_design(l_menu3);
//		l_menu4 = new JLabel("재무제표");this.l_menu_design(l_menu4);
		
		//회계 관련 작은메뉴버튼 생성 & 디자인
		jb_stateInput=new JButton("회계 정보 입력");jb_stateSelect=new JButton("회계 정보 조회");
		this.jb_color_change(jb_stateInput);this.jb_color_change(jb_stateSelect);
		
		//부서 관련 작은메뉴버튼 생성 & 디자인
		jb_teamInput = new JButton("부서 정보 입력"); jb_teamSelect=new JButton("부서 정보 조회");
		this.jb_color_change(jb_teamInput);this.jb_color_change(jb_teamSelect);
		
		//매출 관련 작은메뉴버튼 생성 & 디자인
		jb_incomeSelect = new JButton("매출 정보 입력"); jb_incomeAnalysis=new JButton("매출 분석");
		this.jb_color_change(jb_incomeSelect);this.jb_color_change(jb_incomeAnalysis);
		
		//재무제표 버튼 생성
		jb_fin_st = new JButton("재무제표");
		jb_fin_st.setFont(new Font("맑은 고딕",Font.BOLD,20));
		this.jb_color_change(jb_fin_st);
		
		//큰 메뉴와 작은메뉴버튼 서쪽판넬에 추가
		p_main_west.add(l_menu1);//회계관리 메뉴 + 버튼
		p_main_west.add(jb_stateInput);p_main_west.add(jb_stateSelect);
		p_main_west.add(l_menu2);//부서관리 메뉴 + 버튼
		p_main_west.add(jb_teamInput); p_main_west.add(jb_teamSelect);
		p_main_west.add(l_menu3);//매출정보 메뉴 + 버튼
		p_main_west.add(jb_incomeSelect); p_main_west.add(jb_incomeAnalysis);
		//p_main_west.add(l_menu4);//재무제표 메뉴 라벨 추가
		p_main_west.add(jb_fin_st); //재무제표 메뉴 버튼 추가
		
		
		
		//라벨 클릭 시 이벤트 발생
//		l_menu4.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				JFrame newWindow = new JFrame("새 창");
//                newWindow.setSize(200, 150);
//                newWindow.setVisible(true);
//			}
//		});
	}
	
	//버튼 색 바꾸기
	public void jb_color_change(JButton jb) {
		jb.setBackground(Color.black);jb.setForeground(Color.white);
	}
	//큰 메뉴 폰트 바꾸기
	public void l_menu_design(JLabel jl) {
		jl.setForeground(Color.white);
		jl.setFont(new Font("맑은 고딕",Font.BOLD,20));
		jl.setHorizontalAlignment(JLabel.CENTER);
	}
	

	public static void main(String[] args) {
		Main_west mw =new Main_west();
		mw.setSize(500,600);
		mw.setVisible(true);
		mw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
