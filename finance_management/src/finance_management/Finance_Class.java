package finance_management;

import java.sql.*;
import java.util.*;

public class Finance_Class {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "scott";
	String pwd = "1234";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	Scanner sc;
	
	public Finance_Class() throws SQLException {
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "scott";
		pwd = "1234";
		sc = new Scanner(System.in);
		conn = DriverManager.getConnection(url, user, pwd);
		pstmt = conn.prepareStatement("");
		ResultSet rs = pstmt.executeQuery();
	}
}
