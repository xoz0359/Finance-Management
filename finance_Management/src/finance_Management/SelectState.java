package finance_Management;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectState extends Finance_Class implements Finance_Interface {

	public SelectState() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection() throws SQLException {
		sql = "select * from state";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}
	
}
