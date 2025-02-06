package finance_management;

import java.sql.*;
import java.util.*;

public class Finance_Class {
	
<<<<<<< HEAD
	String url;
	String user;
	String pwd;
=======
	String url ;
	String user ;
	String pwd ;
>>>>>>> refs/remotes/origin/feature_민석
	String sql;
	Connection conn;
	PreparedStatement pstmt;
	Object oj;
	Scanner sc;
	
	public Finance_Class() throws SQLException {
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "xoz0359";
		pwd = "1234";
		sc = new Scanner(System.in);
		conn = DriverManager.getConnection(url, user, pwd);
	}
}
