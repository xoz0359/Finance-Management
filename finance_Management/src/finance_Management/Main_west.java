package finance_Management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main_west extends JPanel{
	
	Container cont1;
	JPanel p_main_west,p_main_center;
	JLabel title,l_menu1,l_menu2,l_menu3,l_menu4,l_menu5;
	JMenuBar jmb;
	JButton jb_stateInput,
			jb_stateSelect,jb_teamSelect,jb_teamExpectIncomeSet,
			jb_incomeSelect,jb_incomeAnalysis,jb_fin_st,
			jb_userRegiser,jb_setting;
	
	
	public Main_west() {
		String path = "images\\finIcon.png";
		ImageIconAdd iconAdd = new ImageIconAdd(path, 20,20);

		//센터판넬과 서쪽판넬 생성
		p_main_west = new JPanel(new GridLayout(12,1));
		p_main_center = new JPanel(new GridLayout(1,1));
		
		//메인 화면에 센터판넬에 들어갈 라벨 생성
		title = new JLabel("재무 관리 프로그램v1.0",iconAdd.icon,JLabel.CENTER);
		
//		title.setText("재무");
//		title.setIcon(icon);
		
		//센터판넬에 라벨 추가
		p_main_center.add(title);
		
		//위에서 생성했던 컨텐트페인 담긴 객체 cont에 서쪽판넬이랑 중심판넬 추가
		//this.add(p_main_west,"West");
		this.add(p_main_center,"Center");
		
		//서쪽판넬 배경 검은색
		p_main_west.setBackground(Color.decode("#3F91D5"));
		
		String pathState = "images\\wirte.png";
		String pathTeam = "images\\department.png";
		String pathIncome = "images\\income2.png";
		String pathSetting = "images\\setting4.png";
		String pathFS = "images\\financial_statement.png";
		
		ImageIconAdd IconState = new ImageIconAdd(pathState, 20, 20);
		ImageIconAdd IconTeam = new ImageIconAdd(pathTeam, 20, 20);
		ImageIconAdd IconIncome = new ImageIconAdd(pathIncome, 20, 20);
		ImageIconAdd IconSetting = new ImageIconAdd(pathSetting, 20, 20);
		ImageIconAdd IconFS = new ImageIconAdd(pathFS, 20, 20);
		
		//큰 메뉴 객체 생성 & 디자인 메서드 사용
		l_menu1 = new JLabel("회계관리",IconState.icon,JLabel.CENTER);this.l_menu_design(l_menu1);
		l_menu2 = new JLabel("부서관리",IconTeam.icon,JLabel.CENTER);this.l_menu_design(l_menu2);
		l_menu3 = new JLabel("매출정보",IconIncome.icon,JLabel.CENTER);this.l_menu_design(l_menu3);
//		l_menu4 = new JLabel("재무제표");this.l_menu_design(l_menu4);
//		l_menu5 = new JLabel("환경설정",IconSetting.icon,JLabel.CENTER);this.l_menu_design(l_menu5);
		
		//회계 관련 작은메뉴버튼 생성 & 디자인
		jb_stateInput=new JButton("전표 입력");jb_stateSelect=new JButton("전표 조회");
		this.jb_color_change(jb_stateInput);this.jb_color_change(jb_stateSelect);
		
		//부서 관련 작은메뉴버튼 생성 & 디자인
		jb_teamSelect=new JButton("부서정보 조회");
		jb_teamExpectIncomeSet = new JButton("예상매출 설정");
		this.jb_color_change(jb_teamSelect);
		this.jb_color_change(jb_teamExpectIncomeSet);
		
		//매출 관련 작은메뉴버튼 생성 & 디자인
		jb_incomeSelect = new JButton("매출정보 조회"); jb_incomeAnalysis=new JButton("매출 분석");
		this.jb_color_change(jb_incomeSelect);this.jb_color_change(jb_incomeAnalysis);
		
		//환경설정 관련 메뉴버튼 생성 & 디자인
		jb_setting=new JButton("환경설정",IconSetting.icon);jb_userRegiser = new JButton("사용자 등록");
		this.jb_color_change(jb_setting);this.jb_color_change(jb_userRegiser);
		jb_setting.setFont(new Font("맑은 고딕",Font.BOLD,20));
		
		
		//재무제표 버튼 생성
		jb_fin_st = new JButton("재무제표",IconFS.icon);
		jb_fin_st.setFont(new Font("맑은 고딕",Font.BOLD,20));
		this.jb_color_change(jb_fin_st);
		
		//큰 메뉴와 작은메뉴버튼 서쪽판넬에 추가
		p_main_west.add(l_menu1);//회계관리 메뉴 + 버튼
		p_main_west.add(jb_stateInput);p_main_west.add(jb_stateSelect);
		
		p_main_west.add(l_menu2);//부서관리 메뉴 + 버튼
		p_main_west.add(jb_teamSelect);p_main_west.add(jb_teamExpectIncomeSet);
		
		p_main_west.add(l_menu3);//매출정보 메뉴 + 버튼
		p_main_west.add(jb_incomeSelect); p_main_west.add(jb_incomeAnalysis);
		
		//p_main_west.add(l_menu4);//재무제표 메뉴 라벨 추가
		p_main_west.add(jb_fin_st); //재무제표 메뉴 버튼 추가
		
		
		p_main_west.add(jb_setting);
		
		
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
		jb.setBackground(Color.decode("#3F91D5"));jb.setForeground(Color.white);
	}
	//큰 메뉴 폰트 바꾸기
	public void l_menu_design(JLabel jl) {
		jl.setForeground(Color.white);
		jl.setFont(new Font("맑은 고딕",Font.BOLD,20));
		jl.setHorizontalAlignment(JLabel.CENTER);
	}
	
	
}