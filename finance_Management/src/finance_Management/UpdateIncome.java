package finance_Management;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UpdateIncome extends Finance_Class implements Finance_Interface{

	public UpdateIncome() throws SQLException {
		super();
	}
	
	@Override
	public Integer getDML(HashMap<Integer, String> m, ArrayList<String> l) throws SQLException {
		
		
		HashMap <Integer, String> inmap = m;
		ArrayList <String> inlist = l;
		sql = "update income set ";
		StringBuilder sb = new StringBuilder(sql);
		for (int i = 0; i < inmap.size(); i++) {
			sb.append(inmap.get(i) + " = ");
			i++;
			sb.append("? ");

			if (!(i == inmap.size() - 1)) {
				sb.append(", ");
			}
		}
		pstmt = conn.prepareStatement(sql); 
		for (int i = 0; i < inmap.size(); i++) {
			pstmt.setString(i + 1, inlist.get(i));
			i++;
		}
		int cnt = pstmt.executeUpdate();
		return cnt;
	}
}
