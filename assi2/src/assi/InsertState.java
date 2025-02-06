package assi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertState extends Finance_Class implements Finance_Interface {
	
	public InsertState() throws SQLException {
		super();
	}
	
	@Override
	public Integer getDML() throws SQLException {
	String sql = "INSERT INTO state(lecno, lecdate, smcode, dept, amount) "
			+ "values(sq_state_lecno, substr(sysdate,1,10), ?, ?, ?)";
	
	pstmt = conn.prepareStatement(sql);
	
	// ����ڷκ��� �Է� �ޱ�
	System.out.print("�з��ڵ带 �Է��ϼ���: ");
	pstmt.setInt(1, sc.nextInt());
	sc.nextLine();
	
	System.out.print("�μ���ȣ�� �Է��ϼ���: ");
	pstmt.setInt(2, sc.nextInt());
	sc.nextLine();
	
	System.out.print("�ݾ��� �Է��ϼ���: ");
	pstmt.setInt(3, sc.nextInt());
	sc.nextLine();
	
	int count = pstmt.executeUpdate();
	System.out.println("================"+count );

	return count;
	}
	
	public static void main(String[] args) throws SQLException{
	}
}

