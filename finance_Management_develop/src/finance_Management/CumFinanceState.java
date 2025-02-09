package finance_Management;

import java.sql.*;
import java.util.*;


public class CumFinanceState {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Scanner in = new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String pwd = "1234";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		String selectsql = "select codename, amount from fin_st";
		PreparedStatement pstmt = conn.prepareStatement(selectsql);
		ResultSet rs = pstmt.executeQuery();
		HashMap <String, Integer> valmap = null;
		if (!rs.next()) {
			System.out.println("등록된 데이터가 없습니다");
			rs.close();
			pstmt.close();
			conn.close();
			return;	
		}else {
		do {
			valmap.put(rs.getString("codename"), rs.getInt("smcode"));
		}while(rs.next());
		}
		

	}

}
