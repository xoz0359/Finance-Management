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
	public ResultSet getSelection(HashMap <Integer, String> m, ArrayList <String> l) throws SQLException {
		inmap = m;
		inlist = l;
		sql = "select * from team where ";
		StringBuilder sb = new StringBuilder(sql);
		for(int i = 0; i < inmap.size(); i++) {
			sb.append(inmap.get(i)+" = ");
			i++;
			sb.append("? ");
			
			if (!(i==inmap.size()-1)) {
				sb.append(", ");
			}
		}	
		sql = sb.toString();
		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < inlist.size(); i++) {
			System.out.println(inlist.get(i));
			pstmt.setString(i+1, inlist.get(i+1));
		}
		rs = pstmt.executeQuery();
		return rs;
	}

}
