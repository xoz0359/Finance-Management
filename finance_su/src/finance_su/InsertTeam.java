package finance_su;

import java.sql.*;
import java.util.*;

public class InsertTeam extends Finance_Class implements Finance_Interface {

	public InsertTeam() throws SQLException {
		super();
	}

	@Override
	public Integer getDML() throws SQLException {

		String sql = "insert into team values((sq_team_dept.nextval),?,?,null,0)";
		pstmt = conn.prepareStatement(sql);
		String tname = sc.nextLine();
		int tnumber = sc.nextInt();
		pstmt.setString(1, tname);
		pstmt.setInt(2, tnumber);
		int cnt = pstmt.executeUpdate();

		return cnt;
	}

}


