package finance_Management;

import java.sql.SQLException;
import java.util.*;

public class UpdateState extends Finance_Class implements Finance_Interface {

	public UpdateState() throws SQLException {
		super();
	}
	
	@Override
	public Integer getDML(HashMap <Integer, String> m, ArrayList <String> l) throws SQLException {
		HashMap input = m;
		sql = "update state set ";
		StringBuilder sb = new StringBuilder(sql);
		for(int i = 0; i < input.size(); i++) {
			sb.append(input.get(i)+" = ");
			i++;
			sb.append("? ");
			
			if (!(i==input.size()-1)) {
				sb.append(", ");
			}
		}
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < input.size(); i++) {
			pstmt.setString(i+1, (String) input.get(i+1));
			i++;
		}
		int cnt = pstmt.executeUpdate();
		return cnt;
	}
}
