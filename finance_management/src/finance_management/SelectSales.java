package finance_management;

import java.sql.*;

public abstract class SelectSales extends Finance_Class implements Finance_Interface{

	public SelectSales() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getInfo() throws SQLException {
		String sql = "Select * from income";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
}
