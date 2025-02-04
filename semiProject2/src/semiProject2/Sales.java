package semiProject2;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.*;
import java.awt.*;
import java.awt.event.*;

public class Sales extends Frame implements ActionListener{
	
	static Connection con;
	Panel p_center,p_south,p_salesInput,p_salesDelete,p_salesCheck,p_salesInfo;
	Button bt1,bt2,bt3,bt4,bt_salesInput,bt_salesDelete,bt_mainView,bt_salesCheck;
	TextField tf_sidate, tf_sidept, tf_expectincome,tf_actualincome,tf_teamcost,tf_sddate,tf_sddept,tf_scdept;
	String date_str,msg,info_date,info_dept,info_expectincome,info_actualincome,info_teamcost,info_achiev;
	Choice sidate_c,sddate_c,scdate_c;
	Label msg_label;
	int dept,cumincome,actualIncome;
	
	public Sales() {
		BorderLayout bl = new BorderLayout(20,20);
		this.setLayout(bl);
		//중단
		p_center = new Panel(new BorderLayout(20,20));
		this.add(p_center,"Center");
		p_center.add(new Label("재무관리 프로그램v1.0",Label.CENTER));
		
		//하단
		p_south = new Panel();
		bt1 = new Button("매출 입력");
		bt2 = new Button("매출 내역 삭제");
		bt3 = new Button("매출 내역 조회");
//		bt4 = new Button("??");
		this.add(p_south,"South");
		p_south.add(bt1);
		p_south.add(bt2);
		p_south.add(bt3);
//		p_south.add(bt4);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt1) {
			this.remove(p_center);
			this.southViewDeletion();
			this.salesInputView();
			this.add(p_salesInput,"Center");
			this.validate();
			System.out.println("hi");
		}else if(obj==bt_salesInput) {
			date_str = sidate_c.getSelectedItem();
			this.salesInput(con);
			this.cumincomeCheck(con);
			this.cumincomeUpdate(con);
			this.validate();
		}else if(obj==bt2) {
			this.remove(p_center);
			this.southViewDeletion();
			System.out.println("hi");
			this.salesDeleteView();
			System.out.println("hi");
			this.add(p_salesDelete,"Center");
			System.out.println("hi");
			this.validate();	
		}else if(obj==bt_mainView) {
			dispose();
			Sales s = new Sales();
			s.setSize(300,400);
			s.setVisible(true);
		}else if(obj==bt_salesDelete) {
			date_str = sddate_c.getSelectedItem();
			this.salesDelete(con);
			this.validate();
			
		}else if(obj==bt3) {
			this.remove(p_center);
			this.southViewDeletion();
			this.salesCheckView();
			System.out.println("hi hello");
			this.add(p_salesCheck,"Center");
			this.validate();
		}else if(obj==bt_salesCheck) {
			date_str = scdate_c.getSelectedItem();
			this.salesView(con);
			System.out.println("hi hello");
			this.remove(p_salesCheck);
			this.salesInfoView();
			this.add(p_salesInfo,"Center");
			this.validate();
			
		}
	}
	
	public void southViewDeletion() {
		this.remove(p_south);
		bt_mainView = new Button("메인 화면으로");
		this.add(bt_mainView,"South");
//		this.validate();
		bt_mainView.addActionListener(this);
	}
	
	public void salesInputView() {
		p_salesInput = new Panel(new GridLayout(10,1,10,10));
		tf_sidate = new TextField();
		tf_sidept = new TextField();
		tf_expectincome = new TextField();
		tf_actualincome = new TextField();
		tf_teamcost = new TextField();
//		tf_date = new TextField();
		p_salesInput.add(new Label("날짜 입력"));
		sidate_c = new Choice();
		sidate_c.addItem("2024-01");
		sidate_c.addItem("2024-02");
		sidate_c.addItem("2024-03");
		sidate_c.addItem("2024-04");
		p_salesInput.add(sidate_c);
		
		p_salesInput.add(new Label("부서번호 입력"));
		p_salesInput.add(tf_sidept);
		p_salesInput.add(new Label("실제 매출 입력"));
		p_salesInput.add(tf_actualincome);
		p_salesInput.add(new Label("비용 입력"));
		p_salesInput.add(tf_teamcost);
		bt_salesInput = new Button("정보 입력");
		p_salesInput.add(bt_salesInput);
		bt_salesInput.addActionListener(this);

	}
	public void salesDeleteView() {
		p_salesDelete = new Panel(new GridLayout(6,1));
		tf_sddept = new TextField();
		sddate_c = new Choice();
		System.out.println("hi");
		sddate_c.addItem("2024-01");
		sddate_c.addItem("2024-02");
		sddate_c.addItem("2024-03");
		sddate_c.addItem("2024-04");
		System.out.println("hi");
		p_salesDelete.add(new Label("날짜 입력"));
		p_salesDelete.add(sddate_c);
		System.out.println("hi");
		p_salesDelete.add(new Label("부서번호 입력"));
		p_salesDelete.add(tf_sddept);
		System.out.println("hi5");
		bt_salesDelete = new Button("삭제 정보 입력");
		p_salesDelete.add(bt_salesDelete);
		bt_salesDelete.addActionListener(this);
	}
	public void salesCheckView() {
		p_salesCheck= new Panel(new GridLayout(5,1));
		scdate_c = new Choice();
		tf_scdept = new TextField();
		bt_salesCheck = new Button("조회 정보 입력");
		System.out.println("hi");
		scdate_c.addItem("2024-01");
		scdate_c.addItem("2024-02");
		scdate_c.addItem("2024-03");
		scdate_c.addItem("2024-04");
		p_salesCheck.add(new Label("날짜:"));
		p_salesCheck.add(scdate_c);
		p_salesCheck.add(new Label("부서번호:"));
		p_salesCheck.add(tf_scdept);
		p_salesCheck.add(bt_salesCheck);
		bt_salesCheck.addActionListener(this);
		
//		p_salesCheck.add(new Label("예상매출:"));
//		p_salesCheck.add(new Label("실제매출:"));
//		p_salesCheck.add(new Label("비용:"));
//		p_salesCheck.add(new Label("달성도:"));
	}
	public void salesInfoView() {
		p_salesInfo = new Panel(new GridLayout(8,1));
		
		p_salesInfo.add(new Label("날짜:"));
		p_salesInfo.add(new Label(info_date));
		p_salesInfo.add(new Label("부서번호:"));
		p_salesInfo.add(new Label(info_dept));
		p_salesInfo.add(new Label("실제매출:"));
		p_salesInfo.add(new Label(info_actualincome));
		p_salesInfo.add(new Label("비용:"));
		p_salesInfo.add(new Label(info_teamcost));
	}
	
	public void salesInput(Connection con) {
			String sql = "insert into income values('"+date_str+"',?,?,?)";
			String sql_teamCumincomeCheck = "select cumincome from team where dept=?";
			String sql_teamCumincomeUpdate = "update team set cumincome=? where dept=?";
			PreparedStatement ps;
			PreparedStatement ps2;
			PreparedStatement ps3;
			try {
				ps = con.prepareStatement(sql);
				dept=Integer.parseInt(tf_sidept.getText());
				ps.setInt(1, dept );
				actualIncome=Integer.parseInt(tf_actualincome.getText());
				ps.setInt(2, actualIncome);
				System.out.println("actualIncome:"+actualIncome);
				int teamcost=Integer.parseInt(tf_teamcost.getText());
				ps.setInt(3, teamcost);
				int count = ps.executeUpdate();
				p_salesInput.add(new Label(count + "개의 정보가 입력됨"));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void cumincomeCheck(Connection con) {
		String sql_teamCumincomeCheck = "select cumincome from team where dept=?";
		PreparedStatement ps2;
		try {
			ps2 = con.prepareStatement(sql_teamCumincomeCheck);
			ps2.setInt(1, dept);
			ResultSet rs = ps2.executeQuery();
			while(rs.next()) {
				cumincome = rs.getInt(1);
				System.out.println("rs.getInt(1):"+rs.getInt(1));
				System.out.println("cumincome:"+cumincome);
			}
			System.out.println("cumincome:"+cumincome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void cumincomeUpdate(Connection con) {
		String sql_teamCumincomeUpdate = "update team set cumincome=? where dept=?";
		PreparedStatement ps3;
		try {
			ps3 = con.prepareStatement(sql_teamCumincomeUpdate);
			ps3.setInt(1, cumincome+actualIncome);
			ps3.setInt(2, dept);
			System.out.println("업데이트정보:"+(cumincome+actualIncome));
			int count2 = ps3.executeUpdate();
			System.out.println(count2+"개의 정보 수정");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salesDelete(Connection con) {
		String sql = "delete from income where mon_income='"+date_str+"' and dept =?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			int dept=Integer.parseInt(tf_sddept.getText());
			ps.setInt(1, dept);
			int count = ps.executeUpdate();
			p_salesDelete.add(new Label(count + "개의 정보가 삭제됨"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void salesView(Connection con) {
		String sql="select * from income where mon_income=? and dept =?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			int dept=Integer.parseInt(tf_scdept.getText());
			ps.setString(1, date_str);
			ps.setInt(2, dept);
			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				info_date = rs.getString(1);
				info_dept = Integer.toString(rs.getInt(2));
				info_actualincome = Integer.toString(rs.getInt(3));
				info_teamcost = Integer.toString(rs.getInt(4));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
		Sales s =new Sales();
		s.setSize(300,400);
		s.setVisible(true);
		
		

	}

}