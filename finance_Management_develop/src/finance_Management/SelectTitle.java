package finance_Management;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTitle extends Finance_Class implements Finance_Interface{

	public SelectTitle() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection() throws SQLException {
		sql = "select * from title";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}
}
