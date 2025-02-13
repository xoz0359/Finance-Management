package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicComboBoxUI; //콤보박스 화살표 디자인위해 임포트

public class ShowState extends JPanel{
	
	JPanel p_main_center,p_stateInput,p_siNorth,p_dateInput,p_save;
	Container cont;
	JLabel l_menu1,l_date;
	JComboBox jc_date1,jc_date2,jc_cn,jc_tc;
	JTextField jtf_d;
	JTable jt_s;
	DefaultTableModel dtm;
	JButton jb_save;
	ArrayList<String> list;
	
	
	
	public ShowState() {
		this.setLayout(new BorderLayout());
		//gui 서쪽에 들어갈 서쪽판넬 및 센터에 들어갈 입력판넬 생성
		p_stateInput = new JPanel(new BorderLayout());
		//전표입력판넬 위쪽에 들어갈 판넬 생성
		p_siNorth = new JPanel(new GridLayout(1,2));
		//입력판넬 맨 위에 들어갈 날짜입력판넬 생성
		p_dateInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p_dateInput.setBackground(Color.WHITE);
		//저장 판넬 & 버튼 생성
		p_save = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p_save.setBackground(Color.WHITE);
		jb_save = new JButton("조회"); 
		//저장 버튼 디자인
//		jb_save.setBackground(Color.black);
		jb_save.setBackground(Color.white);
		
		// 날짜입력판넬에 들어갈 String 배열 생성
		String[] date1 = new String [32];
		String[] date2 = new String [31];
		
		date1[0] = "전체";
		for (int i = 0; i < date2.length; i++) {
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, (i-(i*2))); // 빼고 싶다면 음수 입력
		Date now = new Date(cal1.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date1 [i+1] = sdf.format(now);
		date2 [i] = sdf.format(now);
		}
		
		
		// 날짜입력판넬에 들어갈 연도,월 콤보박스 생성 + 일 텍스트 필드
		String[] columnName = {"전표번호","전표일자", "계정과목", "부서번호", "금액"};
		String[] cn_array = {"","영업수익(매출)","금융수익","영업비용","기타비용","금융비용",
				"유형자산 취득","유형자산 처분","부채상환"};
		String[] tc_array = {"","10","20","30","40"};
		
		//테이블모델 객체 생성 & 설정(컬럼명들, 행 숫자)
		dtm = new DefaultTableModel(columnName,30) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return column < 0 || column > 1;
			}
		};
		
		//테이블 객체를 생성해 놓은 테이블 모델을 이용해 생성
		jt_s = new JTable(dtm);
		

        // jt_s의 셀 렌더러 설정 (편집 불가능한 행을 회색으로 표시)
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jt_s, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component cell = super.getTableCellRendererComponent(jt_s, value, isSelected, hasFocus, row, column);
                
                // 편집 불가능한 행을 회색으로 설정
                if (!jt_s.isCellEditable(row, column)) {
                    cell.setBackground(Color.LIGHT_GRAY); // 편집할 수 없는 컬럼의 색상
                } else {
                    cell.setBackground(Color.WHITE); // 기본 색상
                }

                return cell;
            }
        };

        // 모든 열에 대해 렌더러 적용
        for (int i = 0; i < jt_s.getColumnCount(); i++) {
            jt_s.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

		
		
		//부서 번호랑 계정과목 배열로 콤보박스 객체 생성 + 디자인
		//jc_cn = new JComboBox(cn_array);this.jcb_design(jc_cn);
		//jc_tc = new JComboBox(tc_array);this.jcb_design(jc_tc);
		
		//테이블의 모든 열을 선택 -> 하나의 열을 선택 -> 수정하는 메서드 실행 -> 콤보박스로 수정
		//jt_s.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jc_cn));
		//jt_s.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc_tc));
		
		
		//jtable의 getColumnModel메서드:이 테이블의 <<모든 열정보>>를 보관 유지하는 TableColumnModel 를 돌려줍니다
		//반환된 TableColumnModel의 getColumn메서드:<<열의 인덱스(1)>>에 해당하는 TableColumn객체를 돌려줌 
		//반환된 TableColumn객체의 setCellEditor 메서드:해당 <<열의 셀을 편집할수 있도록 에디터>>를 설정
		//setCellEditor (TableCellEditor  cellEditor)
		//TableCellEditor: 이 인터페이스는 JListBox,JComboBox,JTree, 또는 JTable 등의 <<컴퍼넌트의 값의 에디터가 될 수 있는 객체가 구현할 필요가 있는 메소드>>를 정의
		//DefaultCellEditor (JComboBox  comboBox): combobox를 사용하는 DefaultCellEditor 객체를 구축
		
		
		//날짜 입력하는 콤보박스랑 텍스트필드(길이:3) 생성 + 콤보박스 디자인
		jc_date1 = new JComboBox(date1);this.jcb_design(jc_date1);
		jc_date2 = new JComboBox(date2);this.jcb_design(jc_date2);
		
		//날짜 관련 라벨 생성
		l_date = new JLabel("일자: ");

		//날짜입력판넬에 콤보박스&라벨 추가
		
		p_dateInput.add(l_date);
		p_dateInput.add(jc_date1);
		p_dateInput.add(new JLabel("~"));
		p_dateInput.add(jc_date2);
		
		//저장버튼을 저장 판넬에 추가
		p_save.add(jb_save);
		
		//jt_s테이블에 스크롤을 추가하고 그 스크롤을 전표입력판넬에 추가 + jt_s 테이블을 입력판넬에 추가
		JScrollPane jsp = new JScrollPane(jt_s);
		jsp.setBackground(Color.WHITE);
		jsp.getViewport().setBackground(Color.WHITE); 
		jsp.setBackground(Color.WHITE); 
		jt_s.setBackground(Color.WHITE);
		p_stateInput.add(jsp);
		//입력판넬에 날짜입력판넬 추가
		p_stateInput.add(p_siNorth,"North");
		p_siNorth.add(p_dateInput);
		p_siNorth.add(p_save);
		
		//JFrame가장 바깥 pane인 컨텐트페인을 담을 객체 선언하고 대입
		
		p_stateInput.setBackground(Color.WHITE);
		//위에서 생성했던 컨텐트페인 담긴 객체 cont에 서쪽판넬이랑 입력판넬 추가
		this.add(p_stateInput,"Center");
		TitleSet ts = new TitleSet("전표 조회");
		this.add(ts.jp_title,"North");
		

		
		
		
	}
	//콤보박스 화살표 디자인
	public void jcb_design(JComboBox jcb) {
		jcb.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				// TODO Auto-generated method stub
				JButton button =  super.createArrowButton();
				button.setBackground(Color.black);
				button.setForeground(Color.white);
				return button;
			}
		});
		jcb.setPreferredSize(new Dimension(90, 23));
	}

}