package semiProject2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class State_Input extends JFrame{
	
	JPanel p_main_west,p_main_center,p_stateInput,p_dateInput;
	Container cont;
	JLabel title,l_menu1,l_menu2,l_menu3,l_menu4,l_menu5;
	JComboBox jc_y,jc_m;
	JTextField jtf_d;
	
	
	
	public State_Input() {
		//센터에 들어갈 입력판넬과 서쪽에 들어갈 서쪽판넬 생성
		p_main_west = new JPanel(new GridLayout(10,1));
//		p_main_center = new JPanel(new GridLayout(1,1));
		p_stateInput = new JPanel(new GridLayout(7,1));
		//입력판넬 맨 위에 들어갈 날짜입력판넬 생성
		p_dateInput = new JPanel(new GridBagLayout());
		//날짜입력판넬에 들어갈 연도,월 콤보박스 생성 + 일 텍스트 필드
		String[] y={"2020", "2021", "2022", "2023","2024"};
		String[] m= {"1","2","3","4","5","6","7","8","9","10","11","12"};
		 
		
		jc_y = new JComboBox(y);jc_y.setSize(100, 10);
		jc_m = new JComboBox(m);
		jtf_d = new JTextField();
		
		//날짜입력판넬에 콤보박스 추가
		p_dateInput.add(jc_y);
		p_dateInput.add(jc_m);
		p_dateInput.add(jtf_d);
		
		//JFrame가장 바깥 pane인 컨텐트페인을 담을 객체 선언하고 대입
		cont =getContentPane();
		
		//위에서 생성했던 컨텐트페인 담긴 객체 cont에 서쪽판넬이랑 입력판넬 추가
		cont.add(p_main_west,"West");
		cont.add(p_stateInput,"Center");
		
		//서쪽판넬 배경 검은색
		p_main_west.setBackground(Color.black);
		
		//서쪽판넬 크기 맞추기 위해 추가할 임의의 라벨 생성 
		l_menu1 = new JLabel("회계관리");
		l_menu1.setForeground(Color.white);
		l_menu1.setFont(new Font("맑은 고딕",Font.BOLD,20));
		l_menu1.setHorizontalAlignment(JLabel.CENTER);
		
		//서쪽판넬에 라벨 추가
		p_main_west.add(l_menu1);
		
//		p_stateInput.setBackground(Color.white);
		p_stateInput.add(p_dateInput);
		
	}

	
	public static void main(String[] args) {
		State_Input si=new State_Input();
		si.setSize(500,600);
		si.setVisible(true);
		si.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

}
