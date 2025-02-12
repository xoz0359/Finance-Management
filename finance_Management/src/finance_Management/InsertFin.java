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

public class InsertFin extends Finance_Class implements Finance_Interface {
		
		ArrayList <String> inlist;
		public InsertFin() throws SQLException {
			super();
		}
		
		@Override
		public Integer getDML(ArrayList <String> l) throws SQLException {
		inlist = l;
		sql = "insert into fin_st values(?, 0)";
		//System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < l.size(); i++) {
			//System.out.println(l.get(i));
			pstmt.setString(i+1, l.get(i));
		}
		pstmt.setString(1, inlist.get(0));
		int cnt = pstmt.executeUpdate();

		return cnt;
		
	}

}

