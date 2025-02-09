package finance_Management;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;

public class CurrencyExchangeCalculate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner in = new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String pwd = "1234";
		Connection conn = DriverManager.getConnection(url, user, pwd);

		
	}

}
