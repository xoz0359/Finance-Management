package semiProject2;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class State extends Frame implements ActionListener{
	
	static Connection con;
	Panel p_main_center,p_main_south,p_state_center,p_toMain,p_dateInput;
	Button bt_state_inputView,bt_toMain,bt_income_input;
	TextField tf_date,tf_smcode,tf_teamcode,tf_amount,tf_y,tf_m, tf_d;
	String date;
	int dept,code,fin_amount,input_amount;
	Choice cho_teamcode;
	static Map<String, Integer> cn_code= new HashMap<String, Integer>();
	public void make_cn_code() {
		cn_code.put("현금성 자산", 1);cn_code.put("유형자산", 2);	cn_code.put("기타금융자산", 3);
		cn_code.put("매입채무", 4);cn_code.put("차입금", 5);cn_code.put("영업수익(매출)", 6);
		cn_code.put("금융수익", 7);cn_code.put("영업비용", 8);cn_code.put("기타비용", 9);
		cn_code.put("금융비용", 10);cn_code.put("유형자산 취득", 11);	cn_code.put("유형자산 처분", 12);
		cn_code.put("부채상환", 13);
	}
	
	public State() {
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//전체 레이아웃은 center + south의 borderlayout
		this.setLayout(new BorderLayout(10,10));
		//메인중단판넬 객체 만들고 라벨을 메인중단판넬 객체에 추가
		p_main_center=new Panel();
		p_main_south=new Panel();
		p_main_center.add(new Label("장부 입력 프로그램v1.0"));
		this.add(p_main_center,"Center"); //센터화면에 메인중단판넬 띄우기
		this.add(p_main_south,"South"); //아래화면에 메인하단판넬 띄우기
		bt_state_inputView = new Button("장부 입력하러 가기");
		p_main_south.add(bt_state_inputView);
		
		bt_state_inputView.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj ==bt_state_inputView) {
			this.remove(p_main_center);
			this.state_inputView();
			this.add(p_state_center,"Center");
			
			this.toMain();
			
			this.validate();
		}else if(obj==bt_income_input) {
			this.input_state(con);
			this.update_fin_st(code);
		}
		
	}
	public void state_inputView() {
		p_state_center = new Panel(new GridLayout(6,2));
		p_dateInput = new Panel(new GridLayout(3,2));
		tf_smcode = new TextField();
		tf_teamcode = new TextField();
		tf_amount = new TextField();
		bt_income_input = new Button("정보 입력");
		p_state_center.add(new Label("날짜"));
		p_state_center.add(p_dateInput);
		tf_y = new TextField();tf_m=new TextField();tf_d=new TextField();
		p_dateInput.add(new Label("연도:"));p_dateInput.add(tf_y);
		p_dateInput.add(new Label("월:"));p_dateInput.add(tf_m);
		p_dateInput.add(new Label("일:"));p_dateInput.add(tf_d);
		p_state_center.add(new Label("분류계정"));
		this.make_codeChoice();
		p_state_center.add(cho_teamcode);
		p_state_center.add(new Label("팀 번호"));
		p_state_center.add(tf_teamcode);
		p_state_center.add(new Label("금액"));
		p_state_center.add(tf_amount);
		p_state_center.add(bt_income_input);
		bt_income_input.addActionListener(this);
	}
	public void make_codeChoice() {
		cho_teamcode = new Choice();
		cho_teamcode.addItem("영업수익(매출)");
		cho_teamcode.addItem("금융수익");
		cho_teamcode.addItem("영업비용");
		cho_teamcode.addItem("기타비용");
		cho_teamcode.addItem("금융비용");
		cho_teamcode.addItem("유형자산 취득");
		cho_teamcode.addItem("유형자산 처분");
	}
	
	public void toMain() {
		p_toMain = new Panel();
		bt_toMain = new Button("메인 화면으로");
		p_toMain.add(bt_toMain);
		this.remove(p_main_south);
		this.add(p_toMain,"South");
		
	}
	
	public void input_state(Connection con) {
		String sql = "insert into state values (sq_state_lecno_kor.nextval,?,?,?,?)";
		System.out.println("sql작성");
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			date = tf_y.getText() +"-" + tf_m.getText()+"-" + tf_d.getText();
			ps.setString(1, date);
			System.out.println(date);
			code = cn_code.get(cho_teamcode.getSelectedItem()); //딕셔너리 구조의 해쉬맵에서 코드에 해당하는 숫자를 뽑아 code에 대입
			ps.setInt(2, code); //2번째 물음표에 code에 대입된 숫자를 입력(=계정과목번호)
			dept=Integer.parseInt(tf_teamcode.getText());
			ps.setInt(3, dept);
			input_amount = Integer.parseInt(tf_amount.getText());
			ps.setInt(4,input_amount);
			int count = ps.executeUpdate();
			System.out.println(count+"개의 정보가 입력되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//10팀의 매출 100원 발생 -> fin_st테이블에 반영할 메서드
	public void update_fin_st(int code) {
		String sql_select = "select amount from fin_st where smcode=?";
		PreparedStatement ps,ps2;
		try {
			ps = con.prepareStatement(sql_select);
			System.out.println("ps에 객체 담기");
			ps.setInt(1, code);
			System.out.println("code:"+code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				fin_amount = rs.getInt(1);
			}
			System.out.println(fin_amount);
			String sql_update = "update fin_st set amount =? where smcode=?";
			ps2 = con.prepareStatement(sql_update);
			System.out.println("ps에 객체 담기");
			ps2.setInt(1, fin_amount+input_amount);
			System.out.println(fin_amount+input_amount);
			ps2.setInt(2, code);
			System.out.println("code:"+code);
			
			int count = ps2.executeUpdate();
			System.out.println(count+"개의 정보가 업데이트됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521";
			String user = "semi";
			String pwd = "1234";
			con = DriverManager.getConnection(url,user,pwd);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		State s = new State();
		s.make_cn_code();
		System.out.println(cn_code.get("영업수익(매출)"));
		
		s.setSize(400,400);
		s.setVisible(true);

	}

}
