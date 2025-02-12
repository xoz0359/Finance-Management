package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI; //�޺��ڽ� ȭ��ǥ ���������� ����Ʈ

public class IncomeAnalysis extends JPanel{
	
	JPanel p_main_center,p_incomeAnalysis,p_iaNorth,p_eiInput,p_infoShow;
	Container cont;
	JLabel l_menu1,l_y,l_m,l_d,jl_dname,jl_period;
	JComboBox jcb_order; //�Ⱓ
	JTextField jtf_d,jtf_dname;
	JTable jt_s;
	DefaultTableModel dtm;
	JScrollPane jsp_jt;
	JButton jb_eiInput,jb_infoShow;
	
	
	
	
	public IncomeAnalysis() {
		this.setLayout(new BorderLayout());
		//gui ���ʿ� �� �����ǳ� �� ���Ϳ� �� �Է��ǳ� ����
		p_incomeAnalysis = new JPanel(new BorderLayout());
		
		//��ǥ�Է��ǳ� ���ʿ� �� �ǳ� ����
		p_iaNorth = new JPanel(new BorderLayout());
		//�Է��ǳ� �� ���� �� ��¥�Է��ǳ� ����
		p_infoShow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p_eiInput = new JPanel(new FlowLayout(FlowLayout.LEFT));


		


		//��¥�Է��ǳڿ� �� ����,�� �޺��ڽ� ���� + �� �ؽ�Ʈ �ʵ�
//		String[] y={"2020", "2021", "2022", "2023","2024"};
//		String[] m= {"��ü","1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��"};
		String[] columnName = {"�μ��̸�","���� �����","���������","�޼���"};
		String[] order = {"��������","��������"};
		
		//���̺�� ��ü ���� & ����(�÷����, �� ����)
		dtm = new DefaultTableModel(columnName,30) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return column!=0 ;
			}
		};
		
		//���̺� ��ü�� ������ ���� ���̺� ���� �̿��� ����
		jt_s = new JTable(dtm);
		
		
		//�������� �Է� ���̾�α� ��ư ����
		jb_eiInput=new JButton("�������� �Է�");jb_eiInput.setBackground(Color.white);
		jb_infoShow = new JButton("��ȸ");jb_infoShow.setBackground(Color.white);
		jcb_order = new JComboBox(order);this.jcb_design(jcb_order);
		
		
		p_iaNorth.add(p_eiInput,"West");
		p_iaNorth.add(p_infoShow,"East");
	
		
		//���� ����� �Է��ǳڿ� ���̾�α� ��ư �߰�
		//p_eiInput.add(jb_eiInput);
		//p_infoShow.add(jcb_order);
		p_infoShow.add(jb_infoShow);
		
		
	
		
		

		
		//jt_s���̺� ��ũ���� �߰��ϰ� �� ��ũ���� ��ǥ�Է��ǳڿ� �߰� + jt_s ���̺��� �Է��ǳڿ� �߰�
		jsp_jt =new JScrollPane(jt_s);
		jsp_jt.setOpaque(true);
		jsp_jt.getViewport().setOpaque(true);
		jsp_jt.setBackground(Color.white);
		jsp_jt.getViewport().setBackground(Color.white);
		jt_s.setOpaque(true);
		jt_s.setBackground(Color.white);
		
		p_incomeAnalysis.add(jsp_jt);
		//�Է��ǳڿ� ��¥�Է��ǳ� �߰�
		p_incomeAnalysis.add(p_iaNorth,"North");
		p_iaNorth.add(p_eiInput);
		
		//������ �����ߴ� ����Ʈ���� ��� ��ü cont�� �����ǳ��̶� �Է��ǳ� �߰�
		this.add(p_incomeAnalysis,"Center");
		TitleSet ts = new TitleSet("���� �м�");
		this.add(ts.jp_title,"North");
		
		//�����ǳ� ��� ������
		p_incomeAnalysis.setOpaque(true);
		p_iaNorth.setOpaque(true);
		
		//�����ǳ� ũ�� ���߱� ���� �߰��� ������ �� ���� 
		l_menu1 = new JLabel("ȸ�����");
		l_menu1.setForeground(Color.white);
		l_menu1.setFont(new Font("���� ���",Font.BOLD,20));
		l_menu1.setHorizontalAlignment(JLabel.CENTER);
		
		// p_siNorth �г� ���� ����
		p_iaNorth.setOpaque(true);
		p_iaNorth.setBackground(Color.decode("#FFFFFF"));

		// ���� �гε鵵 ���� ����
		p_eiInput.setOpaque(true);
		p_eiInput.setBackground(Color.decode("#FFFFFF"));
		
		p_infoShow.setOpaque(true);
		p_infoShow.setBackground(Color.WHITE);

		
		
	}
	//�޺��ڽ� ȭ��ǥ ������
	public void jcb_design(JComboBox jcb) {
		jcb.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				// TODO Auto-generated method stub
				JButton button =  super.createArrowButton();
				button.setBackground(Color.white);
				button.setForeground(Color.black);
				return button;
			}
		});
	}


}
