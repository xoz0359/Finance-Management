package finance_management;

import java.sql.SQLException;
import java.util.HashMap;

public class UpdateInCome extends Finance_Class implements Finance_Interface{

	public UpdateInCome() throws SQLException {
		super();
	}
	
	@Override
	public Integer getDML(HashMap<String, Integer> m) throws SQLException {
		HashMap input = m;
		sql = "update income set ";
		StringBuilder sb = new StringBuilder(sql);
		for (int i = 0; i < input.size(); i++) {
			sb.append(input.get(i) + " = ");
			i++;
			sb.append("? ");

			if (!(i == input.size() - 1)) {
				sb.append(", ");
			}
		}
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < input.size(); i++) {
			pstmt.setString(i + 1, (String) input.get(i + 1));
			i++;
		}
		int cnt = pstmt.executeUpdate();
		return cnt;
	}
}
