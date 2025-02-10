package finance_Management;

import java.sql.ResultSet;
import java.sql.Date;
import java.util.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectState extends Finance_Class implements Finance_Interface {

	HashMap <Integer, String> inmap;
	ArrayList <String> inlist;
	public SelectState() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection() throws SQLException {
		sql = "select * from state";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getSelection(ArrayList <String> l) throws SQLException {
		inlist = l;
		Date date1 = Date.valueOf(inlist.get(0));
		Date date2 = Date.valueOf(inlist.get(1));
		sql = "select * from state where lecdate between ? and ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setDate(1, date1);
		pstmt.setDate(2, date2);
		rs = pstmt.executeQuery();
		return rs;
	}
	
}
