package finance_management;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.lang.*;

public class CostCalculate {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// 예
		Scanner in = new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String pwd = "1234";
		Connection conn = DriverManager.getConnection(url, user, pwd);

		String selectsql = "select codename from title";
		PreparedStatement pstmt = conn.prepareStatement(selectsql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<String> strlist = new <String> ArrayList();
		if (!rs.next()) {
			System.out.println("저장된 데이터가 없습니다");
			rs.close();
			pstmt.close();
		}
		do {
			strlist.add(rs.getString("codename"));
		} while (rs.next());
		rs.close();
		pstmt.close();
		
		System.out.println("비용 등록 화면입니다");
		
		selectsql = "select teamname from team";
		pstmt = conn.prepareStatement(selectsql);
		rs = pstmt.executeQuery();
		ArrayList<String> strlist2 = new <String> ArrayList();
		if (!rs.next()) {
			System.out.println("저장된 데이터가 없습니다");
			rs.close();
			pstmt.close();
		}
		do {
			strlist2.add(rs.getString("teamname"));
		} while (rs.next());
		rs.close();
		pstmt.close();
		
		
		System.out.println("비용 등록할 팀을 선택해주세요");
		for (int i = 0; i<strlist2.size(); i++) {
			System.out.println(i+1+". "+strlist2.get(i));
		}
		int input3 = in.nextInt();
		
		System.out.println("등록할 비용의 종류를 입력해주세요");
		for (int i = 0; i<strlist.size(); i++) {
			System.out.println(i+1+". "+strlist.get(i));
		}
		int input1 = in.nextInt();
		
		System.out.println("발생한 비용을 입력해주세요");
		System.out.println(">");
		int input2 = in.nextInt();
		
		//출금 처리 부분
		selectsql = "select korwith, korbal from korstate where lecno_kor in (select MAX(lecno_kor) from korstate)";
		pstmt = conn.prepareStatement(selectsql);
		rs = pstmt.executeQuery();
		int korbal;
		if (!rs.next()) {
			System.out.println("저장된 데이터가 없습니다");
			rs.close();
			pstmt.close();
		}
		do {
			korbal = rs.getInt("korbal");
		} while (rs.next());

		if (korbal - input2 < 0) {
			System.out.println("입력된 비용이 잔고보다 많습니다");
			return;
		}
		rs.close();
		pstmt.close();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(new Date());

		String insertsql = "insert into korstate values (sq_korstate_lecno_kor.nextval, to_date('" + today
				+ "', 'YYYY-MM-DD'), 0, ?, ?)";// 첫 파라미터는 전표번호
		pstmt = conn.prepareStatement(insertsql);
		pstmt.setInt(1, input2);
		pstmt.setInt(2, korbal - input2);
		int cnt = pstmt.executeUpdate();
		System.out.println(cnt + "행 입력 성공");
		// 비용 등록 부분
		pstmt.close();
		
		
		String updatesql = "update cost set "+strlist.get(input1-1)+" = ";
		StringBuilder sb = new StringBuilder(updatesql);
		sb.append(strlist.get(input1-1)+" + "+input2+" where code = ?");
		pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, input1);
		
		
		int cnt2 = pstmt.executeUpdate();
		System.out.println(cnt2 + "행 입력 성공");
	}

}
