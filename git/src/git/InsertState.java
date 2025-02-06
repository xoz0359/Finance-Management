package git;

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
	pstmt.setInt(1, sc.nextInt());
	pstmt.setInt(2, sc.nextInt());
	pstmt.setInt(3, sc.nextInt());
	int count = pstmt.executeUpdate();

	return count;
	}
	
	public static void main(String[] args) throws SQLException{
	}
}

