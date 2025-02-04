package assi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Deposit {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String pwd="1234";
		Connection conn= DriverManager.getConnection(url,user,pwd);
		
		Scanner sc = new Scanner(System.in);
		
		String sql="select * from korstate where lecno_kor = (select max(lecno_kor) from korstate)";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		int balance=0;
		
		if(rs.next()) {
			do {
				balance = rs.getInt("korbal");
				System.out.println(balance);
			}
			while(rs.next());
		}

		System.out.print("입금할 금액: ");
		int amount = sc.nextInt();
		sc.nextLine();
		
		String sql1 = "insert into korstate(lecno_kor,lecdate_kor,kordepo,korwith,korbal)"+
				"values(sq_korstate_lecno_kor.nextval,"
				+ "substr(sysdate,1,10),"
				+ "?,"
				+ "?,"
				+ "?)";
		PreparedStatement pss = conn.prepareStatement(sql1);
		pss.setInt(1, amount);
		pss.setInt(2,0);
		pss.setInt(3,amount + balance);
		int count=pss.executeUpdate();
		
		ps.close();
		rs.close();
		pss.close();
	}
}
