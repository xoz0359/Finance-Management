package finance_Management;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectFin_st extends Finance_Class implements Finance_Interface {
	
	public SelectFin_st() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection() throws SQLException {
		sql = "select * from fin_st";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}
}
