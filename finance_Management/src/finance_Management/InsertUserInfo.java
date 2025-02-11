package finance_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.text.*;

public class InsertUserInfo extends Finance_Class implements Finance_Interface {
	
	ArrayList <String> inlist;
	public InsertUserInfo() throws SQLException {
		super();
	}
	
	@Override
	public Integer getDML(ArrayList <String> l) throws SQLException {
	inlist = l;
	sql = "insert into user_info values(?, ?, ?)";
	System.out.println(sql);
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < inlist.size(); i++) {
		System.out.println("inlist:"+inlist.get(i));
		pstmt.setString(i+1, inlist.get(i));
	}
	int cnt = pstmt.executeUpdate();

	return cnt;
	}
	
}
