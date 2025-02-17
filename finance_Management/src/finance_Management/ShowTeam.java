package finance_Management;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ShowTeam extends JPanel {
	
	JPanel p_north, p_north_titlebox, p_subtitlerow, p_center;
	JTable t_table;
	JTableHeader tableHeader;
	JScrollPane t_scrollPane;
	DefaultTableModel fs_tableModel;
	JButton b_check;
	JLabel l_titlename, l_positionname, l_smcode, l_cumamount;
	
	public ShowTeam() {
		super();
		
		BorderLayout bl_fs = new BorderLayout(10, 10);
		this.setLayout(bl_fs);
		this.setBackground(Color.WHITE);
		
		p_north = new JPanel();
		BorderLayout fs_title = new BorderLayout();
		p_north.setLayout(fs_title);
		p_north.setBackground(Color.WHITE);
		
		p_north_titlebox = new JPanel();
		BorderLayout fs_titlebox = new BorderLayout();
		p_north_titlebox.setLayout(fs_titlebox);
		p_north_titlebox.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		p_north_titlebox.setBackground(Color.WHITE);
		
		l_titlename = new JLabel("�μ����� ��ȸ", SwingConstants.CENTER);
		l_titlename.setFont(new Font("���� ����",Font.BOLD,20));
		p_north_titlebox.add(l_titlename, BorderLayout.CENTER);
	
		p_north.add(p_north_titlebox,BorderLayout.CENTER);
		
		p_center = new JPanel();
        p_center.setLayout(new BorderLayout());
        p_center.setBackground(Color.WHITE);

        p_subtitlerow = new JPanel();
        p_subtitlerow.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p_subtitlerow.setBackground(Color.WHITE);

        l_positionname = new JLabel("�μ����ǥ", SwingConstants.LEFT);
        l_positionname.setFont(new Font("���� ����", Font.BOLD, 15));
        //p_subtitlerow.add(l_positionname, BorderLayout.WEST);

        b_check = new JButton("��ȸ");
        b_check.setFont(new Font("���� ����", Font.BOLD, 12));
        b_check.setBackground(Color.WHITE);
        p_subtitlerow.add(b_check);

        p_center.add(p_subtitlerow, BorderLayout.NORTH);
        
        String[] columnNames = {"�μ���ȣ", "�μ��̸�"}; // �� �̸�
        fs_tableModel = new DefaultTableModel(columnNames, 11){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
        t_table = new JTable(fs_tableModel);
        t_table.setFont(new Font("���� ����", Font.PLAIN, 12));
        t_table.setRowHeight(40);
        t_table.setGridColor(Color.BLACK);
        t_table.getTableHeader().setFont(new Font("���� ����", Font.PLAIN, 13));
        t_table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        t_table.getTableHeader().setReorderingAllowed(false);
        t_table.getTableHeader().setResizingAllowed(false);
        
        tableHeader = t_table.getTableHeader();
        tableHeader.setFont(new Font("���� ����", Font.BOLD, 13));
        tableHeader.setBackground(Color.LIGHT_GRAY);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 60));
        
        
        t_scrollPane = new JScrollPane(t_table);
        t_scrollPane.getViewport().setBackground(Color.WHITE);
        t_scrollPane.setBackground(Color.WHITE);
		t_table.setBackground(Color.WHITE);
        p_center.add(t_scrollPane, BorderLayout.CENTER);
		
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center, BorderLayout.CENTER);

	}
}
