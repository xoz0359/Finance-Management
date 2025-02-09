package semiProject2;

import java.util.function.Predicate;
import javax.swing.table.DefaultTableModel;

public class Celleditable{
	
	DefaultTableModel dtm; //멤버변수로 dtm 선언
	
	//생성자 매개변수로 DefaultTableModel의 매개 변수인 columname이랑 rownumber 설정, 그리고 지정 못하게할 컬럼범위 설정
	Celleditable(String[] columnName,int rownumber,int startColumn,int endColumn){
		
		dtm = new DefaultTableModel(columnName,rownumber) {
			public boolean isCellEditable(int row, int column) {
				return column <startColumn || column >endColumn;
			}
		};
		
	}
	

}
