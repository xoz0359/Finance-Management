package finance_Management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main_west extends JPanel{
	
	Container cont1;
	JPanel p_main_west,p_main_center;
	JLabel title,l_menu1,l_menu2,l_menu3,l_menu4,l_menu5;
	JMenuBar jmb;
	JButton jb_stateInput,
			jb_stateSelect,jb_teamSelect,jb_teamExpectIncomeSet,
			jb_incomeSelect,jb_incomeAnalysis,jb_fin_st,
			jb_userRegiser,jb_setting;
	
	
	public Main_west() {
		String path = "images\\finIcon.png";
		ImageIconAdd iconAdd = new ImageIconAdd(path, 20,20);

		//�����ǳڰ� �����ǳ� ����
		p_main_west = new JPanel(new GridLayout(12,1));
		p_main_center = new JPanel(new GridLayout(1,1));
		
		//���� ȭ�鿡 �����ǳڿ� �� �� ����
		title = new JLabel("�繫 ���� ���α׷�v1.0",iconAdd.icon,JLabel.CENTER);
		
//		title.setText("�繫");
//		title.setIcon(icon);
		
		//�����ǳڿ� �� �߰�
		p_main_center.add(title);
		
		//������ �����ߴ� ����Ʈ���� ��� ��ü cont�� �����ǳ��̶� �߽��ǳ� �߰�
		//this.add(p_main_west,"West");
		this.add(p_main_center,"Center");
		
		//�����ǳ� ��� ������
		p_main_west.setBackground(Color.decode("#3F91D5"));
		
		String pathState = "images\\wirte.png";
		String pathTeam = "images\\department.png";
		String pathIncome = "images\\income2.png";
		String pathSetting = "images\\setting4.png";
		String pathFS = "images\\financial_statement.png";
		
		ImageIconAdd IconState = new ImageIconAdd(pathState, 20, 20);
		ImageIconAdd IconTeam = new ImageIconAdd(pathTeam, 20, 20);
		ImageIconAdd IconIncome = new ImageIconAdd(pathIncome, 20, 20);
		ImageIconAdd IconSetting = new ImageIconAdd(pathSetting, 20, 20);
		ImageIconAdd IconFS = new ImageIconAdd(pathFS, 20, 20);
		
		//ū �޴� ��ü ���� & ������ �޼��� ���
		l_menu1 = new JLabel("ȸ�����",IconState.icon,JLabel.CENTER);this.l_menu_design(l_menu1);
		l_menu2 = new JLabel("�μ�����",IconTeam.icon,JLabel.CENTER);this.l_menu_design(l_menu2);
		l_menu3 = new JLabel("��������",IconIncome.icon,JLabel.CENTER);this.l_menu_design(l_menu3);
//		l_menu4 = new JLabel("�繫��ǥ");this.l_menu_design(l_menu4);
//		l_menu5 = new JLabel("ȯ�漳��",IconSetting.icon,JLabel.CENTER);this.l_menu_design(l_menu5);
		
		//ȸ�� ���� �����޴���ư ���� & ������
		jb_stateInput=new JButton("��ǥ �Է�");jb_stateSelect=new JButton("��ǥ ��ȸ");
		this.jb_color_change(jb_stateInput);this.jb_color_change(jb_stateSelect);
		
		//�μ� ���� �����޴���ư ���� & ������
		jb_teamSelect=new JButton("�μ����� ��ȸ");
		jb_teamExpectIncomeSet = new JButton("������� ����");
		this.jb_color_change(jb_teamSelect);
		this.jb_color_change(jb_teamExpectIncomeSet);
		
		//���� ���� �����޴���ư ���� & ������
		jb_incomeSelect = new JButton("�������� ��ȸ"); jb_incomeAnalysis=new JButton("���� �м�");
		this.jb_color_change(jb_incomeSelect);this.jb_color_change(jb_incomeAnalysis);
		
		//ȯ�漳�� ���� �޴���ư ���� & ������
		jb_setting=new JButton("ȯ�漳��",IconSetting.icon);jb_userRegiser = new JButton("����� ���");
		this.jb_color_change(jb_setting);this.jb_color_change(jb_userRegiser);
		jb_setting.setFont(new Font("���� ���",Font.BOLD,20));
		
		
		//�繫��ǥ ��ư ����
		jb_fin_st = new JButton("�繫��ǥ",IconFS.icon);
		jb_fin_st.setFont(new Font("���� ���",Font.BOLD,20));
		this.jb_color_change(jb_fin_st);
		
		//ū �޴��� �����޴���ư �����ǳڿ� �߰�
		p_main_west.add(l_menu1);//ȸ����� �޴� + ��ư
		p_main_west.add(jb_stateInput);p_main_west.add(jb_stateSelect);
		
		p_main_west.add(l_menu2);//�μ����� �޴� + ��ư
		p_main_west.add(jb_teamSelect);p_main_west.add(jb_teamExpectIncomeSet);
		
		p_main_west.add(l_menu3);//�������� �޴� + ��ư
		p_main_west.add(jb_incomeSelect); p_main_west.add(jb_incomeAnalysis);
		
		//p_main_west.add(l_menu4);//�繫��ǥ �޴� �� �߰�
		p_main_west.add(jb_fin_st); //�繫��ǥ �޴� ��ư �߰�
		
		
		p_main_west.add(jb_setting);
		
		
		//�� Ŭ�� �� �̺�Ʈ �߻�
//		l_menu4.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				JFrame newWindow = new JFrame("�� â");
//                newWindow.setSize(200, 150);
//                newWindow.setVisible(true);
//			}
//		});
	}
	
	//��ư �� �ٲٱ�
	public void jb_color_change(JButton jb) {
		jb.setBackground(Color.decode("#3F91D5"));jb.setForeground(Color.white);
	}
	//ū �޴� ��Ʈ �ٲٱ�
	public void l_menu_design(JLabel jl) {
		jl.setForeground(Color.white);
		jl.setFont(new Font("���� ���",Font.BOLD,20));
		jl.setHorizontalAlignment(JLabel.CENTER);
	}
	
	
}