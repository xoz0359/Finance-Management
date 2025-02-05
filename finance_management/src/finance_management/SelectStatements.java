package finance_management;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectStatements extends Finance_Class implements Finance_Interface {
	
	public SelectStatements() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getInfo() throws SQLException {
		sql = "select * from fin_st";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}
}
