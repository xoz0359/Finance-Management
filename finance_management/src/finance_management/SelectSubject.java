package finance_management;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectSubject extends Finance_Class implements Finance_Interface{

	public SelectSubject() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getInfo() throws SQLException {
		String sql = "select * from title";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}
}
