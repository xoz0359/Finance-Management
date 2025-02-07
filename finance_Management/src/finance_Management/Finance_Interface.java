package finance_Management;
import java.sql.*;
import java.util.*;

public interface Finance_Interface {

	default ResultSet getSelection() throws SQLException {
		return null;
	}
	
	default Integer getDML() throws SQLException {
		return null;
	}
	
	default Integer getDML(HashMap <Integer, String> m) throws SQLException {
		return null;
	}
	
	default Integer getDML(ArrayList <String> l) throws SQLException {
		return null;
	}
}
