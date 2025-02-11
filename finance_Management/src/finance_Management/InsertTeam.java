package finance_Management;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.*;
	import java.text.*;

public class InsertTeam extends Finance_Class implements Finance_Interface {
		
		ArrayList <String> inlist;
		public InsertTeam() throws SQLException {
			super();
		}
		
		@Override
		public Integer getDML(ArrayList <String> l) throws SQLException {
		inlist = l;
		sql = "insert into team values(sq_team_dept.nextval, ?)";
		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inlist.get(0));
		int cnt = pstmt.executeUpdate();

		return cnt;
		
	}

}

