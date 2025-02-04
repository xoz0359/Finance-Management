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
		cn_code.put("���ݼ� �ڻ�", 1);cn_code.put("�����ڻ�", 2);	cn_code.put("��Ÿ�����ڻ�", 3);
		cn_code.put("����ä��", 4);cn_code.put("���Ա�", 5);cn_code.put("��������(����)", 6);
		cn_code.put("��������", 7);cn_code.put("�������", 8);cn_code.put("��Ÿ���", 9);
		cn_code.put("�������", 10);cn_code.put("�����ڻ� ���", 11);	cn_code.put("�����ڻ� ó��", 12);
		cn_code.put("��ä��ȯ", 13);
	}
	
	public State() {
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//��ü ���̾ƿ��� center + south�� borderlayout
		this.setLayout(new BorderLayout(10,10));
		//�����ߴ��ǳ� ��ü ����� ���� �����ߴ��ǳ� ��ü�� �߰�
		p_main_center=new Panel();
		p_main_south=new Panel();
		p_main_center.add(new Label("��� �Է� ���α׷�v1.0"));
		this.add(p_main_center,"Center"); //����ȭ�鿡 �����ߴ��ǳ� ����
		this.add(p_main_south,"South"); //�Ʒ�ȭ�鿡 �����ϴ��ǳ� ����
		bt_state_inputView = new Button("��� �Է��Ϸ� ����");
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
		bt_income_input = new Button("���� �Է�");
		p_state_center.add(new Label("��¥"));
		p_state_center.add(p_dateInput);
		tf_y = new TextField();tf_m=new TextField();tf_d=new TextField();
		p_dateInput.add(new Label("����:"));p_dateInput.add(tf_y);
		p_dateInput.add(new Label("��:"));p_dateInput.add(tf_m);
		p_dateInput.add(new Label("��:"));p_dateInput.add(tf_d);
		p_state_center.add(new Label("�з�����"));
		this.make_codeChoice();
		p_state_center.add(cho_teamcode);
		p_state_center.add(new Label("�� ��ȣ"));
		p_state_center.add(tf_teamcode);
		p_state_center.add(new Label("�ݾ�"));
		p_state_center.add(tf_amount);
		p_state_center.add(bt_income_input);
		bt_income_input.addActionListener(this);
	}
	public void make_codeChoice() {
		cho_teamcode = new Choice();
		cho_teamcode.addItem("��������(����)");
		cho_teamcode.addItem("��������");
		cho_teamcode.addItem("�������");
		cho_teamcode.addItem("��Ÿ���");
		cho_teamcode.addItem("�������");
		cho_teamcode.addItem("�����ڻ� ���");
		cho_teamcode.addItem("�����ڻ� ó��");
	}
	
	public void toMain() {
		p_toMain = new Panel();
		bt_toMain = new Button("���� ȭ������");
		p_toMain.add(bt_toMain);
		this.remove(p_main_south);
		this.add(p_toMain,"South");
		
	}
	
	public void input_state(Connection con) {
		String sql = "insert into state values (sq_state_lecno_kor.nextval,?,?,?,?)";
		System.out.println("sql�ۼ�");
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			date = tf_y.getText() +"-" + tf_m.getText()+"-" + tf_d.getText();
			ps.setString(1, date);
			System.out.println(date);
			code = cn_code.get(cho_teamcode.getSelectedItem()); //��ųʸ� ������ �ؽ��ʿ��� �ڵ忡 �ش��ϴ� ���ڸ� �̾� code�� ����
			ps.setInt(2, code); //2��° ����ǥ�� code�� ���Ե� ���ڸ� �Է�(=���������ȣ)
			dept=Integer.parseInt(tf_teamcode.getText());
			ps.setInt(3, dept);
			input_amount = Integer.parseInt(tf_amount.getText());
			ps.setInt(4,input_amount);
			int count = ps.executeUpdate();
			System.out.println(count+"���� ������ �ԷµǾ����ϴ�.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//10���� ���� 100�� �߻� -> fin_st���̺� �ݿ��� �޼���
	public void update_fin_st(int code) {
		String sql_select = "select amount from fin_st where smcode=?";
		PreparedStatement ps,ps2;
		try {
			ps = con.prepareStatement(sql_select);
			System.out.println("ps�� ��ü ���");
			ps.setInt(1, code);
			System.out.println("code:"+code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				fin_amount = rs.getInt(1);
			}
			System.out.println(fin_amount);
			String sql_update = "update fin_st set amount =? where smcode=?";
			ps2 = con.prepareStatement(sql_update);
			System.out.println("ps�� ��ü ���");
			ps2.setInt(1, fin_amount+input_amount);
			System.out.println(fin_amount+input_amount);
			ps2.setInt(2, code);
			System.out.println("code:"+code);
			
			int count = ps2.executeUpdate();
			System.out.println(count+"���� ������ ������Ʈ��");
			
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
		System.out.println(cn_code.get("��������(����)"));
		
		s.setSize(400,400);
		s.setVisible(true);

	}

}
