package finance_Management;

import java.sql.*;
import java.util.*;

public class Finance_Class {
	String url ;
	String user ;
	String pwd ;
	String sql;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	Scanner sc;
	public static final String DB_IP = "192.168.0.109:1521";
	
	public Finance_Class() throws SQLException {
		url = "jdbc:oracle:thin:@"+DB_IP+":xe";
		user = "xoz0359";
		pwd = "1234";
		sc = new Scanner(System.in);
		conn = DriverManager.getConnection(url, user, pwd);
	}
}
