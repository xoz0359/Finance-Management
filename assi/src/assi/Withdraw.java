package assi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Withdraw {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String pwd="1234";
		Connection conn= DriverManager.getConnection(url,user,pwd);
		
		Scanner sc = new Scanner(System.in);
		int balance=0;
		System.out.print("출금할 금액: ");
		int amount = 0;
		
		
		String sql="select * from korstate where lecno_kor = (select max(lecno_kor) from korstate)";	
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
				do {
					amount = sc.nextInt();
					balance = rs.getInt("korbal");
					do {
						if(balance < amount) {
							System.out.println("출금할 잔액이 부족합니다.");
							return;
						}
					}while(balance > amount);
				}
				while(rs.next());
		}
		
		String sql1  = "insert into korstate(lecno_kor,lecdate_kor,kordepo,korwith,korbal)"+
				"values(sq_korstate_lecno_kor.nextval,"
				+ "substr(sysdate,1,10),"
				+ "?,"
				+ "?,"
				+ "?)";
		PreparedStatement pss = conn.prepareStatement(sql1);
		pss.setInt(1,0);
		pss.setInt(2, amount);
		pss.setInt(3, balance - amount);
		
		int count=pss.executeUpdate();
		
		
		ps.close();
		rs.close();
		sc.close();
		
		
		
		
		
	}
}
