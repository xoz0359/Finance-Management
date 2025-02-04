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

		System.out.println("비용 등록 화면입니다");
		System.out.println("발생한 비용을 입력해주세요");
		System.out.println(">");
		int input = in.nextInt();
		// 그냥 입력된 값만큼 출금
		String selectsql = "select korwith, korbal from korstate where lecno_kor in (select MAX(lecno_kor) from korstate)";

		PreparedStatement pstmt = conn.prepareStatement(selectsql);
		ResultSet rs = pstmt.executeQuery();
		int korbal;
		if (!rs.next()) {
			System.out.println("저장된 데이터가 없습니다");
			rs.close();
			pstmt.close();
		}
		do {
			korbal = rs.getInt("korbal");
		} while (rs.next());

		if (korbal - input < 0) {
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
		pstmt.setInt(1, input);
		pstmt.setInt(2, korbal - input);
		int cnt = pstmt.executeUpdate();
		System.out.println(cnt + "행 입력 성공");
		// 뭔가 비용 칼럼을 저장할 테이블이 확정 되면 작업할 부분(비용이 등록되면 실매출과 매출등급을 실시간으로 조정한다)

	}

}
