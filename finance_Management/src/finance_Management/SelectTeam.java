package finance_Management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectTeam extends Finance_Class implements Finance_Interface{
	
	HashMap <Integer, String> inmap;
	ArrayList <String> inlist;
	public SelectTeam() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection() throws SQLException {
		sql = "select * from team";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet getSelection(ArrayList <String> l) throws SQLException {
		sql = "select * from team where TEAMNAME = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, l.get(0));
		rs = pstmt.executeQuery();
		return rs;
	}
}
