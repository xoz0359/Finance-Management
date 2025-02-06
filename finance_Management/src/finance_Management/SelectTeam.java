package finance_Management;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTeam extends Finance_Class implements Finance_Interface{
	
	public SelectTeam() throws SQLException {
		super();
	}
	
	@Override
	public ResultSet getSelection() throws SQLException {
		sql = "select * from team";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}

}
