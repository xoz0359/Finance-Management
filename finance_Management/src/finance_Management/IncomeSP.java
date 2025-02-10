package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI; //�޺��ڽ� ȭ��ǥ ���������� ����Ʈ

public class IncomeSP extends JPanel {

	JPanel p_main_center, p_stateInput, p_siNorth, p_search, p_save;
	JLabel l_menu1, l_y, l_m, l_d, jl_dname, jl_period;
	JComboBox jcb_period; // �Ⱓ
	JTextField jtf_d, jtf_dname;
	JTable jt_s;
	DefaultTableModel dtm;
	JScrollPane jsp_jt;
	JButton jb_infoShow;

	public IncomeSP() {
		this.setLayout(new BorderLayout());
		// gui ���ʿ� �� �����ǳ� �� ���Ϳ� �� �Է��ǳ� ����
		p_stateInput = new JPanel(new BorderLayout());

		// ��ǥ�Է��ǳ� ���ʿ� �� �ǳ� ����
		p_siNorth = new JPanel(new GridLayout(1, 2));
		// �Է��ǳ� �� ���� �� ��¥�Է��ǳ� ����
		p_search = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// ��¥�Է��ǳڿ� �� ����,�� �޺��ڽ� ���� + �� �ؽ�Ʈ �ʵ�
//			String[] y={"2020", "2021", "2022", "2023","2024"};
		String[] m = { "��ü", "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
		String[] columnName = { "�μ���ȣ", "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��",
				"���� �����" };

		// ���̺�� ��ü ���� & ����(�÷����, �� ����)
		dtm = new DefaultTableModel(columnName, 30) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return column != 0;
			}
		};

		// ���̺� ��ü�� ������ ���� ���̺� ���� �̿��� ����
		jt_s = new JTable(dtm);

		// �Ⱓ �Է��ϴ� �޺��ڽ��� �ؽ�Ʈ�ʵ�(����:3) ���� + �޺��ڽ� ������
		jtf_d = new JTextField(3);
		jtf_dname = new JTextField(3);
		jcb_period = new JComboBox(m);
		this.jcb_design(jcb_period);
		jb_infoShow = new JButton("��ȸ");
		jb_infoShow.setBackground(Color.white);

		// ��¥ ���� �� ����
		jl_dname = new JLabel("�μ� �̸�:");
		jl_period = new JLabel("�Ⱓ:");

		// ��¥�Է��ǳڿ� �޺��ڽ�&�� �߰�

		p_search.add(jl_dname);
		p_search.add(jtf_dname);

		p_search.add(jl_period);
		p_search.add(jcb_period);

		p_search.add(jb_infoShow);

		// jt_s���̺� ��ũ���� �߰��ϰ� �� ��ũ���� ��ǥ�Է��ǳڿ� �߰� + jt_s ���̺��� �Է��ǳڿ� �߰�
		jsp_jt = new JScrollPane(jt_s);
		jsp_jt.setOpaque(true);
		jsp_jt.getViewport().setOpaque(true);
		jsp_jt.setBackground(Color.white);
		jsp_jt.getViewport().setBackground(Color.white);
		jt_s.setOpaque(true);
		jt_s.setBackground(Color.white);

		p_stateInput.add(jsp_jt);
		// �Է��ǳڿ� ��¥�Է��ǳ� �߰�
		p_stateInput.add(p_siNorth, "North");
		p_siNorth.add(p_search);

		

		// ������ �����ߴ� ����Ʈ���� ��� ��ü cont�� �����ǳ��̶� �Է��ǳ� �߰�
		this.add(p_stateInput, "Center");
		TitleSet ts = new TitleSet("�������� ��ȸ");
		this.add(ts.jp_title,"North");

		// �����ǳ� ��� ������
		p_stateInput.setOpaque(true);
		p_siNorth.setOpaque(true);

		// p_siNorth �г� ���� ����
		p_siNorth.setOpaque(true);
		p_siNorth.setBackground(Color.decode("#FFFFFF"));

		// ���� �гε鵵 ���� ����
		p_search.setOpaque(true);
		p_search.setBackground(Color.decode("#FFFFFF"));

	}

	// �޺��ڽ� ȭ��ǥ ������
	public void jcb_design(JComboBox jcb) {
		jcb.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				// TODO Auto-generated method stub
				JButton button = super.createArrowButton();
				button.setBackground(Color.white);
				button.setForeground(Color.black);
				return button;
			}
		});
	}
}
