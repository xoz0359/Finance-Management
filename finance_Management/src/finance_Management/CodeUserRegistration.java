package finance_Management;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class CodeUserRegistration extends JPanel {

   JPanel p_center, p_bottom, adminPanel;
   JTextField tf_smCodeNameInput, tf_idInput, tf_pwInput, tf_accesslvInput;
   JComboBox<String> cb_laCodeChoose, cb_accesslvInput;
   JButton btn_save, btn_close, btn_codeRegi, btn_userRegi, btn_adminMode, btn_confirm, btn_deptRegi;
   JLabel lbl_substitle, lbl_laCodeChoose, lbl_smCodeName, lbl_id, lbl_pwd, lbl_accesslv;
	JDialog userDialog, codeDialog, adminDialog;
	JCheckBox cb_adminMode;

	public CodeUserRegistration() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		p_center = new JPanel();
		p_center.setLayout(new BoxLayout(p_center, BoxLayout.Y_AXIS));

		// ��ư ����
		btn_userRegi = new JButton("����� ���");
		btn_codeRegi = new JButton("�����ڵ� ���");
		btn_deptRegi = new JButton("�μ� ���");
		btn_adminMode = new JButton(); // ������ ��� ��ư (���� ���� ����)

		btn_userRegi.setFont(new Font("���� ���", Font.BOLD, 12));
		btn_codeRegi.setFont(new Font("���� ���", Font.BOLD, 12));
		btn_deptRegi.setFont(new Font("���� ���", Font.BOLD, 12));
		btn_adminMode.setFont(new Font("���� ���", Font.BOLD, 12));

		// ��ư ũ�� ����
		Dimension buttonSize = new Dimension(300, 50);
		btn_userRegi.setPreferredSize(buttonSize);
		btn_codeRegi.setPreferredSize(buttonSize);
		btn_deptRegi.setPreferredSize(buttonSize);
		btn_adminMode.setPreferredSize(buttonSize);

		btn_userRegi.setMaximumSize(buttonSize);
		btn_codeRegi.setMaximumSize(buttonSize);
		btn_deptRegi.setMaximumSize(buttonSize);
		btn_adminMode.setMaximumSize(buttonSize);

		btn_userRegi.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_codeRegi.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_deptRegi.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_adminMode.setAlignmentX(Component.CENTER_ALIGNMENT);

		// ������ ��� ��ư ���ο� üũ�ڽ� ��ġ
		cb_adminMode = new JCheckBox();
		cb_adminMode.setPreferredSize(new Dimension(20, 20));
		cb_adminMode.setOpaque(false); // üũ�ڽ� ��� ����ȭ

		JPanel adminContent = new JPanel(new BorderLayout());
		adminContent.setOpaque(false);
		adminContent.add(new JLabel("������ ���", JLabel.CENTER), BorderLayout.CENTER);
		//adminContent.add(cb_adminMode, BorderLayout.EAST);

		btn_adminMode.setLayout(new BorderLayout());
		btn_adminMode.add(adminContent, BorderLayout.CENTER);

		// �гο� ��� �߰�
		add(Box.createVerticalStrut(30));
		add(btn_userRegi);
		add(Box.createVerticalStrut(15));
		add(btn_codeRegi);
		add(Box.createVerticalStrut(15));
		add(btn_deptRegi);
		add(Box.createVerticalStrut(15));
		add(btn_adminMode);
		add(Box.createVerticalStrut(30));

		// ��ư Ŭ�� �� üũ�ڽ� ��� ��� �߰�
		//btn_adminMode.addActionListener(e -> cb_adminMode.setSelected(!cb_adminMode.isSelected()));
	}

	public JDialog openUserRegistrationDialog(Frame f) {
	   
	      userDialog = new JDialog(f, "����� ���", true); 
	      userDialog.setSize(300, 200);
	      userDialog.setLayout(new GridLayout(4, 2, 10, 10));

	      lbl_id = new JLabel("ID:");
	      lbl_id.setFont(new Font("���� ���", Font.BOLD, 12));
	      tf_idInput = new JTextField();
	      tf_idInput.setFont(new Font("���� ���", Font.PLAIN, 12));

	      lbl_pwd = new JLabel("PASSWORD:");
	      lbl_pwd.setFont(new Font("���� ���", Font.BOLD, 12));
	      tf_pwInput = new JTextField();
	      tf_pwInput.setFont(new Font("���� ���", Font.PLAIN, 12));

	      lbl_accesslv = new JLabel("���ѱ���:");
	      lbl_accesslv.setFont(new Font("���� ���", Font.BOLD, 12));
	      cb_accesslvInput = new JComboBox<>(new String[] { "������", "�����" });
	      cb_accesslvInput.setFont(new Font("���� ���", Font.PLAIN, 12));

	      btn_save = new JButton("����");
	      btn_close = new JButton("�ݱ�");

	      userDialog.add(lbl_id);
	      userDialog.add(tf_idInput);
	      userDialog.add(lbl_pwd);
	      userDialog.add(tf_pwInput);
	      userDialog.add(lbl_accesslv);
	      userDialog.add(btn_save);
	      userDialog.add(btn_close);

	      btn_save.addActionListener(e -> {
	    	  ArrayList <String> al = new <String> ArrayList();
	          al.add(tf_idInput.getText());
	          al.add(tf_pwInput.getText());
	          if (cb_accesslvInput.getSelectedItem().toString().equals("������")) {
	        	  al.add("2");
	          }else {
	        	  al.add("1");
	          }
	          
	          try {
				InsertUserInfo iu = new InsertUserInfo();
				
				int cnt = iu.getDML(al);
				System.out.println(cnt+"����� ��� �Ǿ����ϴ�");
				JOptionPane.showMessageDialog(this, "����� ���: " + al.get(0));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        });
	      btn_close.addActionListener(e -> userDialog.dispose());
	      
	      return userDialog;
	   }

   public JDialog openCodeRegistrationDialog(Frame f) {
	      codeDialog = new JDialog(f, "�����ڵ� ���", true);
	      codeDialog.setSize(300, 200);
	      codeDialog.setLayout(new GridLayout(3, 2, 10, 10));

	      lbl_laCodeChoose = new JLabel("��з��ڵ�:");
	      lbl_laCodeChoose.setFont(new Font("���� ���", Font.BOLD, 12));
	      cb_laCodeChoose = new JComboBox<>(new String[] { "100. �ڻ�", "200. ��ä", "300. ����", "400. ���" });
	      cb_laCodeChoose.setFont(new Font("���� ���", Font.PLAIN, 12));

	      lbl_smCodeName = new JLabel("�����ڵ��:");
	      lbl_smCodeName.setFont(new Font("���� ���", Font.BOLD, 12));
	      tf_smCodeNameInput = new JTextField(20);
	      tf_smCodeNameInput.setFont(new Font("���� ���", Font.PLAIN, 12));

	      btn_save = new JButton("����");
	      btn_close = new JButton("�ݱ�");

	      codeDialog.add(lbl_laCodeChoose);
	      codeDialog.add(cb_laCodeChoose);
	      codeDialog.add(lbl_smCodeName);
	      codeDialog.add(tf_smCodeNameInput);
	      codeDialog.add(btn_save);
	      codeDialog.add(btn_close);

			btn_save.addActionListener(e -> {
				ArrayList<String> al = new <String>ArrayList();	
				if (cb_laCodeChoose.getSelectedItem().toString().equals("100. �ڻ�")) {
					al.add("100");
				} else if (cb_laCodeChoose.getSelectedItem().toString().equals("200. ��ä")) {
					al.add("200");
				} else if (cb_laCodeChoose.getSelectedItem().toString().equals("300. ����")) {
					al.add("300");
				} else if (cb_laCodeChoose.getSelectedItem().toString().equals("400. ��ä")) {
					al.add("400");
				}
				al.add(tf_smCodeNameInput.getText());
				
				try {
					InsertTitle it = new InsertTitle();
					int cnt = it.getDML(al);
					System.out.println(cnt + "�����ڵ� ��� �Ǿ����ϴ�");
					JOptionPane.showMessageDialog(this, "�������� ���: " + al.get(1));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
	      btn_close.addActionListener(e -> codeDialog.dispose());

	      return codeDialog;
	   }

	public JDialog insertDeptDialog(Frame f) {
		   
	      userDialog = new JDialog(f, "�μ����", true); 
	      userDialog.setSize(300, 200);
	      userDialog.setLayout(new GridLayout(4, 2, 10, 10));

	      lbl_id = new JLabel("�μ���:");
	      lbl_id.setFont(new Font("���� ���", Font.BOLD, 12));
	      tf_idInput = new JTextField();
	      tf_idInput.setFont(new Font("���� ���", Font.PLAIN, 12));

	      btn_save = new JButton("����");
	      btn_close = new JButton("�ݱ�");

	      userDialog.add(lbl_id);
	      userDialog.add(tf_idInput);
	      userDialog.add(btn_save);
	      userDialog.add(btn_close);

	      btn_save.addActionListener(e -> {
	    	  ArrayList <String> al = new <String> ArrayList();
	          al.add(tf_idInput.getText());
	          try {
				InsertUserInfo iu = new InsertUserInfo();
				
				int cnt = iu.getDML(al);
				System.out.println(cnt+"����� ��� �Ǿ����ϴ�");
				JOptionPane.showMessageDialog(this, "����� ���: " + al.get(0));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        });
	      btn_close.addActionListener(e -> userDialog.dispose());
	      
	      return userDialog;
	   }
   
   public JDialog openAdminModeDialog(Frame f) {

	      adminDialog = new JDialog(f, "������ ���", true);
	      adminDialog.setSize(300, 200);
	      adminDialog.setLayout(new FlowLayout());

	      
	      btn_confirm = new JButton("Ȯ��");
	      btn_close = new JButton("�ݱ�");

	      adminDialog.add(cb_adminMode);
	      adminDialog.add(btn_confirm);
	      adminDialog.add(btn_close);
	      
	      btn_confirm.addActionListener(e -> {
	    	 
	    	 Finance_Management fr = new Finance_Management();
	    	 if (cb_adminMode.isSelected()) {
	    		 fr.accesslv = 2;
	    	 }else {
	    		 fr.accesslv = 1;
	    		 JOptionPane.showMessageDialog(this, "������ ��� ��Ȱ��ȭ");
	    		 adminDialog.dispose();
	    	 }
	    	adminDialog.dispose();
			JOptionPane.showMessageDialog(this, "������ ��� Ȱ��ȭ");
			});
	      btn_close.addActionListener(e -> adminDialog.dispose());

	      return adminDialog;
	   }

 
}