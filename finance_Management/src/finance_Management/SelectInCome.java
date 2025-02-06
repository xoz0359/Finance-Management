package finance_Management;

import java.sql.*;

public class SelectInCome extends Finance_Class implements Finance_Interface{

	public SelectInCome() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection() throws SQLException {
		sql = "Select * from income";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}
}
