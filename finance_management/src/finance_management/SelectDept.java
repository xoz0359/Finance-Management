package finance_management;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectDept extends Finance_Class implements Finance_Interface{
	
	public SelectDept() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getInfo() throws SQLException {
		sql = "select * from team";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}

}
