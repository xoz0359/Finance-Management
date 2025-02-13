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

import javax.swing.plaf.basic.BasicComboBoxUI; //�޺��ڽ� ȭ��ǥ ���������� ����Ʈ

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
		//gui ���ʿ� �� �����ǳ� �� ���Ϳ� �� �Է��ǳ� ����
		p_stateInput = new JPanel(new BorderLayout());
		//��ǥ�Է��ǳ� ���ʿ� �� �ǳ� ����
		p_siNorth = new JPanel(new GridLayout(1,2));
		//�Է��ǳ� �� ���� �� ��¥�Է��ǳ� ����
		p_dateInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p_dateInput.setBackground(Color.WHITE);
		//���� �ǳ� & ��ư ����
		p_save = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p_save.setBackground(Color.WHITE);
		jb_save = new JButton("��ȸ"); 
		//���� ��ư ������
//		jb_save.setBackground(Color.black);
		jb_save.setBackground(Color.white);
		
		// ��¥�Է��ǳڿ� �� String �迭 ����
		String[] date1 = new String [32];
		String[] date2 = new String [31];
		
		date1[0] = "��ü";
		for (int i = 0; i < date2.length; i++) {
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, (i-(i*2))); // ���� �ʹٸ� ���� �Է�
		Date now = new Date(cal1.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date1 [i+1] = sdf.format(now);
		date2 [i] = sdf.format(now);
		}
		
		
		// ��¥�Է��ǳڿ� �� ����,�� �޺��ڽ� ���� + �� �ؽ�Ʈ �ʵ�
		String[] columnName = {"��ǥ��ȣ","��ǥ����", "��������", "�μ���ȣ", "�ݾ�"};
		String[] cn_array = {"","��������(����)","��������","�������","��Ÿ���","�������",
				"�����ڻ� ���","�����ڻ� ó��","��ä��ȯ"};
		String[] tc_array = {"","10","20","30","40"};
		
		//���̺�� ��ü ���� & ����(�÷����, �� ����)
		dtm = new DefaultTableModel(columnName,30) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return column < 0 || column > 1;
			}
		};
		
		//���̺� ��ü�� ������ ���� ���̺� ���� �̿��� ����
		jt_s = new JTable(dtm);
		

        // jt_s�� �� ������ ���� (���� �Ұ����� ���� ȸ������ ǥ��)
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jt_s, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component cell = super.getTableCellRendererComponent(jt_s, value, isSelected, hasFocus, row, column);
                
                // ���� �Ұ����� ���� ȸ������ ����
                if (!jt_s.isCellEditable(row, column)) {
                    cell.setBackground(Color.LIGHT_GRAY); // ������ �� ���� �÷��� ����
                } else {
                    cell.setBackground(Color.WHITE); // �⺻ ����
                }

                return cell;
            }
        };

        // ��� ���� ���� ������ ����
        for (int i = 0; i < jt_s.getColumnCount(); i++) {
            jt_s.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

		
		
		//�μ� ��ȣ�� �������� �迭�� �޺��ڽ� ��ü ���� + ������
		//jc_cn = new JComboBox(cn_array);this.jcb_design(jc_cn);
		//jc_tc = new JComboBox(tc_array);this.jcb_design(jc_tc);
		
		//���̺��� ��� ���� ���� -> �ϳ��� ���� ���� -> �����ϴ� �޼��� ���� -> �޺��ڽ��� ����
		//jt_s.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jc_cn));
		//jt_s.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc_tc));
		
		
		//jtable�� getColumnModel�޼���:�� ���̺��� <<��� ������>>�� ���� �����ϴ� TableColumnModel �� �����ݴϴ�
		//��ȯ�� TableColumnModel�� getColumn�޼���:<<���� �ε���(1)>>�� �ش��ϴ� TableColumn��ü�� ������ 
		//��ȯ�� TableColumn��ü�� setCellEditor �޼���:�ش� <<���� ���� �����Ҽ� �ֵ��� ������>>�� ����
		//setCellEditor (TableCellEditor  cellEditor)
		//TableCellEditor: �� �������̽��� JListBox,JComboBox,JTree, �Ǵ� JTable ���� <<���۳�Ʈ�� ���� �����Ͱ� �� �� �ִ� ��ü�� ������ �ʿ䰡 �ִ� �޼ҵ�>>�� ����
		//DefaultCellEditor (JComboBox  comboBox): combobox�� ����ϴ� DefaultCellEditor ��ü�� ����
		
		
		//��¥ �Է��ϴ� �޺��ڽ��� �ؽ�Ʈ�ʵ�(����:3) ���� + �޺��ڽ� ������
		jc_date1 = new JComboBox(date1);this.jcb_design(jc_date1);
		jc_date2 = new JComboBox(date2);this.jcb_design(jc_date2);
		
		//��¥ ���� �� ����
		l_date = new JLabel("����: ");

		//��¥�Է��ǳڿ� �޺��ڽ�&�� �߰�
		
		p_dateInput.add(l_date);
		p_dateInput.add(jc_date1);
		p_dateInput.add(new JLabel("~"));
		p_dateInput.add(jc_date2);
		
		//�����ư�� ���� �ǳڿ� �߰�
		p_save.add(jb_save);
		
		//jt_s���̺� ��ũ���� �߰��ϰ� �� ��ũ���� ��ǥ�Է��ǳڿ� �߰� + jt_s ���̺��� �Է��ǳڿ� �߰�
		JScrollPane jsp = new JScrollPane(jt_s);
		jsp.setBackground(Color.WHITE);
		jsp.getViewport().setBackground(Color.WHITE); 
		jsp.setBackground(Color.WHITE); 
		jt_s.setBackground(Color.WHITE);
		p_stateInput.add(jsp);
		//�Է��ǳڿ� ��¥�Է��ǳ� �߰�
		p_stateInput.add(p_siNorth,"North");
		p_siNorth.add(p_dateInput);
		p_siNorth.add(p_save);
		
		//JFrame���� �ٱ� pane�� ����Ʈ������ ���� ��ü �����ϰ� ����
		
		p_stateInput.setBackground(Color.WHITE);
		//������ �����ߴ� ����Ʈ���� ��� ��ü cont�� �����ǳ��̶� �Է��ǳ� �߰�
		this.add(p_stateInput,"Center");
		TitleSet ts = new TitleSet("��ǥ ��ȸ");
		this.add(ts.jp_title,"North");
		

		
		
		
	}
	//�޺��ڽ� ȭ��ǥ ������
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