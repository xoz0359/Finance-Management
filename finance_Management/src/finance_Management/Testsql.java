package finance_Management;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Testsql {

	public static void main(String[] args) throws SQLException {
		
		SelectFin_st ss = new SelectFin_st();
		
		ResultSet rs = ss.getSelection();
		if(!rs.next()) {
			
		}else {
			do {
				String teamname = rs.getString("lacode");
				int b = rs.getInt("smcode");
				String c = rs.getString("codename");
				int d = rs.getInt("amount");
				System.out.println("lacode:"+teamname+"\tsmcode:"+b+"\tcodename:"+c+"\tamount:"+d);
				
			}while(!rs.next());
		}
		

	}

}
