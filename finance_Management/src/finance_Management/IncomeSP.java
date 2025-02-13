package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI; //콤보박스 화살표 디자인위해 임포트
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class IncomeSP extends JPanel {

	JPanel p_main_center, p_stateInput, p_siNorth, p_search, p_save;
	JLabel l_menu1, l_y, l_m, l_d, jl_dname, jl_period;
	JComboBox jcb_period, jcb_dname; // 기간
	JTextField jtf_d;
	JTable jt_s;
	DefaultTableModel dtm;
	JScrollPane jsp_jt;
	JButton jb_infoShow;

	public IncomeSP() {
		this.setLayout(new BorderLayout());
		// gui 서쪽에 들어갈 서쪽판넬 및 센터에 들어갈 입력판넬 생성
		p_stateInput = new JPanel(new BorderLayout());

		// 전표입력판넬 위쪽에 들어갈 판넬 생성
		p_siNorth = new JPanel(new GridLayout(1, 2));
		// 입력판넬 맨 위에 들어갈 날짜입력판넬 생성
		p_search = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 조회버튼 판넬
		p_save = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p_save.setBackground(Color.WHITE);
		String[] date = new String [12];	
		date[0] = "전체";
		for (int i = 1; i < date.length; i++) {
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.YEAR, (i-(i*2))); // 빼고 싶다면 음수 입력
			Date now = new Date(cal1.getTimeInMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			date [i] = sdf.format(now);
			}
		String[] m = { "전체", "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
		String[] columnName = { "부서번호", "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월",
				"예상 매출액" };
		 
		
		
		
		// 테이블모델 객체 생성 & 설정(컬럼명들, 행 숫자)
		dtm = new DefaultTableModel(columnName, 30) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		// 테이블 객체를 생성해 놓은 테이블 모델을 이용해 생성
		jt_s = new JTable(dtm);

		// 기간 입력하는 콤보박스랑 텍스트필드(길이:3) 생성 + 콤보박스 디자인
		jtf_d = new JTextField(3);
		jcb_dname = new JComboBox <String> ();
		this.jcb_design(jcb_dname);
		jcb_period = new JComboBox <String> (date);
		this.jcb_design(jcb_period);
		jb_infoShow = new JButton("조회(백만)");
		jb_infoShow.setBackground(Color.white);

		// 날짜 관련 라벨 생성
		jl_dname = new JLabel("부서 이름:");
		jl_period = new JLabel("기간:");

		// 날짜입력판넬에 콤보박스&라벨 추가

		p_search.add(jl_dname);
		p_search.add(jcb_dname);

		p_search.add(jl_period);
		p_search.add(jcb_period);

		

		// jt_s테이블에 스크롤을 추가하고 그 스크롤을 전표입력판넬에 추가 + jt_s 테이블을 입력판넬에 추가
		jsp_jt = new JScrollPane(jt_s);
		jsp_jt.setOpaque(true);
		jsp_jt.getViewport().setOpaque(true);
		jsp_jt.setBackground(Color.white);
		jsp_jt.getViewport().setBackground(Color.white);
		jt_s.setOpaque(true);
		jt_s.setBackground(Color.white);
		
		p_stateInput.add(jsp_jt);
		// 입력판넬에 날짜입력판넬 추가
		p_stateInput.add(p_siNorth, "North");
		p_siNorth.add(p_search);
		p_siNorth.add(p_save);
		p_save.add(jb_infoShow);
		p_stateInput.setBackground(Color.WHITE);

		// 위에서 생성했던 컨텐트페인 담긴 객체 cont에 서쪽판넬이랑 입력판넬 추가
		this.add(p_stateInput, "Center");
		TitleSet ts = new TitleSet("매출정보 조회");
		this.add(ts.jp_title,"North");

		// 서쪽판넬 배경 검은색
		p_stateInput.setOpaque(true);
		p_siNorth.setOpaque(true);

		// p_siNorth 패널 배경색 적용
		p_siNorth.setOpaque(true);
		p_siNorth.setBackground(Color.decode("#FFFFFF"));

		// 내부 패널들도 배경색 적용
		p_search.setOpaque(true);
		p_search.setBackground(Color.decode("#FFFFFF"));
	}

		

	// 콤보박스 화살표 디자인
	public void jcb_design(JComboBox jcb) {
		jcb.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				// TODO Auto-generated method stub
				JButton button = super.createArrowButton();
				button.setBackground(Color.white);
				button.setForeground(Color.black);
				return button;
			}
		});
		jcb.setPreferredSize(new Dimension(80, 23));
	}
	
	public void inputdata(ArrayList <String> l) {
		 String [] tnlist = new String [l.size()+1]; 
		 tnlist[0] = "전체";
		 for (int i = 1; i < tnlist.length; i++) {
			 tnlist[i] = l.get(i-1); 
		}
		 jcb_dname.setModel(new DefaultComboBoxModel<>(tnlist));
	}
}
