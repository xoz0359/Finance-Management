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
	JComboBox jc_date,jc_cn,jc_tc;
	JTextField jtf_d;
	JTable jt_s;
	DefaultTableModel dtm;
	JButton jb_save, jb_dateinsert;
	ArrayList<String> list;
	
	
	
	public ShowState() {
		//gui ���ʿ� �� �����ǳ� �� ���Ϳ� �� �Է��ǳ� ����
		p_stateInput = new JPanel(new BorderLayout());
		//��ǥ�Է��ǳ� ���ʿ� �� �ǳ� ����
		p_siNorth = new JPanel(new GridLayout(1,2));
		//�Է��ǳ� �� ���� �� ��¥�Է��ǳ� ����
		p_dateInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//���� �ǳ� & ��ư ����
		p_save = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jb_save = new JButton("����"); 
		jb_dateinsert = new JButton("����");
		//���� ��ư ������
//		jb_save.setBackground(Color.black);
		jb_save.setBackground(Color.white);
		jb_dateinsert.setBackground(Color.white);
		
		// ��¥�Է��ǳڿ� �� String �迭 ����
		String[] date = new String [31];
		
		for (int i = 0; i < 31; i++) {
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, (i-(i*2))); // ���� �ʹٸ� ���� �Է�
		Date now = new Date(cal1.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date [i] = sdf.format(now);
		}
		
		
		// ��¥�Է��ǳڿ� �� ����,�� �޺��ڽ� ���� + �� �ؽ�Ʈ �ʵ�
		String[] columnName = {"","��ǥ����", "��������", "�μ���ȣ", "�ݾ�"};
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
		jc_date = new JComboBox(date);this.jcb_design(jc_date);
		
		//��¥ ���� �� ����
		l_date = new JLabel("����: ");

		//��¥�Է��ǳڿ� �޺��ڽ�&�� �߰�
		
		p_dateInput.add(l_date);
		p_dateInput.add(jc_date);
		p_dateInput.add(jb_dateinsert);
		
		//�����ư�� ���� �ǳڿ� �߰�
		p_save.add(jb_save);
		
		//jt_s���̺� ��ũ���� �߰��ϰ� �� ��ũ���� ��ǥ�Է��ǳڿ� �߰� + jt_s ���̺��� �Է��ǳڿ� �߰�
		p_stateInput.add(new JScrollPane(jt_s));
		//�Է��ǳڿ� ��¥�Է��ǳ� �߰�
		p_stateInput.add(p_siNorth,"North");
		p_siNorth.add(p_dateInput);
		p_siNorth.add(p_save);
		
		//JFrame���� �ٱ� pane�� ����Ʈ������ ���� ��ü �����ϰ� ����
		
		
		//������ �����ߴ� ����Ʈ���� ��� ��ü cont�� �����ǳ��̶� �Է��ǳ� �߰�
		this.add(p_stateInput,"Center");
		
		//�����ǳ� ũ�� ���߱� ���� �߰��� ������ �� ���� 
		l_menu1 = new JLabel("ȸ�����");
		l_menu1.setForeground(Color.white);
		l_menu1.setFont(new Font("���� ���",Font.BOLD,20));
		l_menu1.setHorizontalAlignment(JLabel.CENTER);
		
//		p_stateInput.setBackground(Color.white);
		
		
		
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
	}

}