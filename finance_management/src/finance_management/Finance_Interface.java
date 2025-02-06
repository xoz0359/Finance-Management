package finance_management;
import java.sql.*;
import java.util.*;

public interface Finance_Interface {

	default ResultSet getSelection() throws SQLException {
		return null;
	}
	
	default Integer getDML() throws SQLException {
		return null;
	}
	
	default Integer getDML(HashMap <String, Integer> m) throws SQLException {
		return null;
	}
}
