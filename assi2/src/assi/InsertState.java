package assi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertState extends Finance_Class implements Finance_Interface {
	
	public InsertState() throws SQLException {
		super();
	}
	
	@Override
	public Integer getDML() throws SQLException {
	String sql = "INSERT INTO state(lecno, lecdate, smcode, dept, amount) "
			+ "values(sq_state_lecno, substr(sysdate,1,10), ?, ?, ?)";
	
	pstmt = conn.prepareStatement(sql);
	
	// 사용자로부터 입력 받기
	System.out.print("분류코드를 입력하세요: ");
	pstmt.setInt(1, sc.nextInt());
	sc.nextLine();
	
	System.out.print("부서번호를 입력하세요: ");
	pstmt.setInt(2, sc.nextInt());
	sc.nextLine();
	
	System.out.print("금액을 입력하세요: ");
	pstmt.setInt(3, sc.nextInt());
	sc.nextLine();
	
	int count = pstmt.executeUpdate();
	System.out.println("================"+count );

	return count;
	}
	
	public static void main(String[] args) throws SQLException{
	}
}

