package finance_Management;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UpdateIncome extends Finance_Class implements Finance_Interface{

	HashMap <Integer, String> inmap;
	ArrayList <String> inlist;
	public UpdateIncome() throws SQLException {
		super();
	}
	
	@Override
	public Integer getDML(HashMap<Integer, String> m, ArrayList<String> l) throws SQLException {
		inmap = m;
		inlist = l;
		sql = "update income set ";
		StringBuilder sb = new StringBuilder(sql);
		sb.append(inmap.get(0) + " = "+inmap.get(0)+"+? where dept = ?");
		//System.out.println(sb.toString());
		pstmt = conn.prepareStatement(sb.toString());
		for (int i = 0; i < inlist.size(); i++) {
			//System.out.println(inlist.get(i));
			pstmt.setString(i + 1, inlist.get(i));
		}
		int cnt = pstmt.executeUpdate();
		return cnt;
	}
}
