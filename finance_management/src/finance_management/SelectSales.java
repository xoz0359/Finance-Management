package finance_management;

import java.sql.*;

public abstract class SelectSales extends Finance_Class implements Finance_Interface{

	public SelectSales() throws SQLException {
		super();
	}
	
	public ResultSet getInfo() throws SQLException {
		String sql = "Select * from income";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
