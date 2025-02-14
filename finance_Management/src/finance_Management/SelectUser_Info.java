package finance_Management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectUser_Info extends Finance_Class implements Finance_Interface{
	
	HashMap <Integer, String> inmap;
	ArrayList <String> inlist;
	public SelectUser_Info() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection(ArrayList <String> l) throws SQLException {
		inlist = l;
		sql = "select * from user_info where user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inlist.get(0));
		rs = pstmt.executeQuery();
		return rs;
	}
}