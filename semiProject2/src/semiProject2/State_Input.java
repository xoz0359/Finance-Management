package semiProject2;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI; //콤보박스 화살표 디자인위해 임포트

public class State_Input extends JFrame{
	
	JPanel p_main_west,p_main_center,p_stateInput,p_siNorth,p_dateInput,p_save;
	Container cont;
	JLabel l_menu1,l_y,l_m,l_d;
	JComboBox jc_y,jc_m,jc_cn,jc_tc;
	JTextField jtf_d;
	JTable jt_s;
	DefaultTableModel dtm;
	JButton jb_save;
	
	
	
	public State_Input() {
		//gui 서쪽에 들어갈 서쪽판넬 및 센터에 들어갈 입력판넬 생성
		p_main_west = new JPanel(new GridLayout(10,1));
		p_stateInput = new JPanel(new BorderLayout());
		//전표입력판넬 위쪽에 들어갈 판넬 생성
		p_siNorth = new JPanel(new GridLayout(1,2));
		//입력판넬 맨 위에 들어갈 날짜입력판넬 생성
		p_dateInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//저장 판넬 & 버튼 생성
		p_save = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jb_save = new JButton("저장"); 
		//저장 버튼 디자인
//		jb_save.setBackground(Color.black);
		jb_save.setBackground(Color.white);
		//날짜입력판넬에 들어갈 연도,월 콤보박스 생성 + 일 텍스트 필드
		String[] y={"2020", "2021", "2022", "2023","2024"};
		String[] m= {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String[] columnName = {"전표일자","계정과목","부서 번호","금액"};
		String[] cn_array = {"영업수익(매출)","금융수익","영업비용","기타비용","금융비용",
				"유형자산 취득","유형자산 처분","부채상환"};
		String[] tc_array = {"10","20","30","40"};
		
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
		
		//부서 번호랑 계정과목 배열로 콤보박스 객체 생성 + 디자인
		jc_cn = new JComboBox(cn_array);this.jcb_design(jc_cn);
		jc_tc = new JComboBox(tc_array);this.jcb_design(jc_tc);
		
		//테이블의 모든 열을 선택 -> 하나의 열을 선택 -> 수정하는 메서드 실행 -> 콤보박스로 수정
		jt_s.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jc_cn));;
		jt_s.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc_tc));
		//jtable의 getColumnModel메서드:이 테이블의 <<모든 열정보>>를 보관 유지하는 TableColumnModel 를 돌려줍니다
		//반환된 TableColumnModel의 getColumn메서드:<<열의 인덱스(1)>>에 해당하는 TableColumn객체를 돌려줌 
		//반환된 TableColumn객체의 setCellEditor 메서드:해당 <<열의 셀을 편집할수 있도록 에디터>>를 설정
		//setCellEditor (TableCellEditor  cellEditor)
		//TableCellEditor: 이 인터페이스는 JListBox,JComboBox,JTree, 또는 JTable 등의 <<컴퍼넌트의 값의 에디터가 될 수 있는 객체가 구현할 필요가 있는 메소드>>를 정의
		//DefaultCellEditor (JComboBox  comboBox): combobox를 사용하는 DefaultCellEditor 객체를 구축
		
		
		//날짜 입력하는 콤보박스랑 텍스트필드(길이:3) 생성 + 콤보박스 디자인
		jc_y = new JComboBox(y);this.jcb_design(jc_y);
		jc_m = new JComboBox(m);this.jcb_design(jc_m);
		jtf_d = new JTextField(3);
		
		//날짜 관련 라벨 생성
		l_y = new JLabel("년");
		l_m = new JLabel("월");
		l_d = new JLabel("일");
		
		//날짜입력판넬에 콤보박스&라벨 추가
		p_dateInput.add(jc_y);
		p_dateInput.add(l_y);
		p_dateInput.add(jc_m);
		p_dateInput.add(l_m);
		p_dateInput.add(jtf_d);
		p_dateInput.add(l_d);
		
		//저장버튼을 저장 판넬에 추가
		p_save.add(jb_save);
		
		//jt_s테이블에 스크롤을 추가하고 그 스크롤을 전표입력판넬에 추가 + jt_s 테이블을 입력판넬에 추가
		p_stateInput.add(new JScrollPane(jt_s));
		//입력판넬에 날짜입력판넬 추가
		p_stateInput.add(p_siNorth,"North");
		p_siNorth.add(p_dateInput);
		p_siNorth.add(p_save);
		
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
		
	}
	//콤보박스 화살표 디자인
	public void jcb_design(JComboBox jcb) {
		jcb.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				// TODO Auto-generated method stub
				JButton button =  super.createArrowButton();
				button.setBackground(Color.black);
				button.setForeground(Color.white);
				return button;
			}
		});
	}
	

	
	public static void main(String[] args) {
		State_Input si=new State_Input();
		si.setSize(900,600);
		si.setVisible(true);
		si.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

}
