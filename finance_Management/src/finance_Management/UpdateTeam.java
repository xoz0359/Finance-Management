package finance_Management;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UpdateTeam extends Finance_Class implements Finance_Interface {
	HashMap <Integer, String> inmap;
	ArrayList <String> inlist;
	
	public UpdateTeam() throws SQLException {
		super();
	}

	@Override
	public Integer getDML(HashMap <Integer, String> m, ArrayList <String> l) throws SQLException {
		inmap = m;
		inlist = l;
		sql = "update team set ";
		StringBuilder sb = new StringBuilder(sql);
		sb.append(inmap.get(0) + " = ? where dept = ?");
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < inlist.size(); i++) {
			pstmt.setString(i + 1, inlist.get(i));
		}
		int cnt = pstmt.executeUpdate();
		return cnt;
	}

}
