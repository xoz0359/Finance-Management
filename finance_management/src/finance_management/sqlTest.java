package finance_management;

import java.sql.*;

class SqlConnecter {
	Connection conn;
	String url;
	String user;
	String pwd;

	public SqlConnecter() {
		// 생성할 때 String으로 정보 입력받으면 로그인 세트 구현
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "scott";
		pwd = "1234";
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			System.out.println("DB연결에 성공했지만 잘못된 SQL을 입력했어요");
			e.printStackTrace();
		}
	}
	
	public void sqlConnection() {
		try {
			Class.forName("oracle.jbdc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB연결에 성공했지만 잘못된 SQL을 입력했어요");
			e.printStackTrace();
		}

	}
	PreparedStatement pstmt = conn.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	if (!rs.next()) {
		System.out.println("조회되는 데이터가 없습니다!");
		rs.close();
		pstmt.close();
	} else {
		do {//student.cname, kscr, mscr, escr, tname from student, score
			String objno = rs.getString("objno");
		}while(rs.next());
		
		int cnt = pstmt.executeUpdate();
}	
public class sqlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
