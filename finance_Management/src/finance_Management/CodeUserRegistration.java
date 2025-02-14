package finance_Management;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class CodeUserRegistration extends JPanel {

	JPanel p_center, p_bottom, adminPanel;
	JTextField tf_smCodeNameInput, tf_idInput, tf_pwInput, tf_accesslvInput;
	JComboBox<String> cb_laCodeChoose, cb_accesslvInput;
	JButton btn_save, btn_close, btn_codeRegi, btn_userRegi, btn_adminMode, btn_confirm, btn_deptRegi;
	JLabel lbl_substitle, lbl_laCodeChoose, lbl_smCodeName, lbl_id, lbl_pwd, lbl_accesslv;
	JDialog userDialog, deptDialog, codeDialog, adminDialog, incomeDialog;
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
		// adminContent.add(cb_adminMode, BorderLayout.EAST);

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
		//add(btn_adminMode);
		//add(Box.createVerticalStrut(30));

		// ��ư Ŭ�� �� üũ�ڽ� ��� ��� �߰�
		// btn_adminMode.addActionListener(e ->
		// cb_adminMode.setSelected(!cb_adminMode.isSelected()));
	}

	public JDialog openUserRegistrationDialog(Frame f) {

	    userDialog = new JDialog(f, "����� ���", true);
	    userDialog.setSize(300, 200);
	    userDialog.setLocationRelativeTo(null);

	    // JPanel�� ����Ͽ� �ܰ� ������ �߰�
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4, 2, 10, 10));  // ���� ������Ʈ ���� ����
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // ���̾�α� �ܰ��� ���� �߰�

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
	    cb_accesslvInput = new JComboBox<>(new String[] { "��ȸ�������", "���", "������" });
	    cb_accesslvInput.setFont(new Font("���� ���", Font.PLAIN, 12));

	    btn_save = new JButton("����");
	    btn_close = new JButton("�ݱ�");

	    // �гο� ������Ʈ �߰�
	    panel.add(lbl_id);
	    panel.add(tf_idInput);
	    panel.add(lbl_pwd);
	    panel.add(tf_pwInput);
	    panel.add(lbl_accesslv);
	    panel.add(cb_accesslvInput);
	    panel.add(btn_save);
	    panel.add(btn_close);

	    // ���̾�α׿� �г� �߰�
	    userDialog.getContentPane().add(panel);

	    btn_save.addActionListener(e -> {
	        if (tf_idInput.getText().equals("") || tf_pwInput.getText().equals("")) {
	            JOptionPane.showMessageDialog(null, "�Է�â�� �� ���� �ֽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        ArrayList<String> al = new <String>ArrayList();
	        al.add(tf_idInput.getText());
	        al.add(tf_pwInput.getText());
	        al.add(cb_accesslvInput.getSelectedIndex() + "");
	        try {
	            InsertUserInfo iu = new InsertUserInfo();

	            int cnt = iu.getDML(al);

	            JOptionPane.showMessageDialog(null,
	                    "���ο� " + cb_accesslvInput.getSelectedItem().toString() + " ��� �Ϸ�!", "�˸�",
	                    JOptionPane.INFORMATION_MESSAGE);

	            userDialog.dispose();
	        } catch (SQLIntegrityConstraintViolationException e2) {
	            JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ������Դϴ�", "�˸�", JOptionPane.INFORMATION_MESSAGE);
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    });

	    btn_close.addActionListener(e -> userDialog.dispose());

	    return userDialog;
	}

	public JDialog openCodeRegistrationDialog(Frame f, ArrayList<String> l) {
	    codeDialog = new JDialog(f, "�����ڵ� ���", true);
	    codeDialog.setSize(300, 200);
	    codeDialog.setLocationRelativeTo(null);

	    // �ܰ� ������ �߰��� JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(3, 2, 10, 10));
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // ���� �߰�

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

	    // �гο� ������Ʈ �߰�
	    panel.add(lbl_laCodeChoose);
	    panel.add(cb_laCodeChoose);
	    panel.add(lbl_smCodeName);
	    panel.add(tf_smCodeNameInput);
	    panel.add(btn_save);
	    panel.add(btn_close);

	    // ���̾�α׿� �г� �߰�
	    codeDialog.getContentPane().add(panel);
		btn_save.addActionListener(e -> {
			
			if (tf_smCodeNameInput.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�Է�â�� �� ���� �ֽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE);
				return;
			}
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

				JOptionPane.showMessageDialog(this, "�������� ���: " + al.get(1));
				
				
				InsertFin iif = new InsertFin();
				al.clear();
				al.add((l.size()-1)+"");
				iif.getDML(al);
				codeDialog.dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���������Դϴ�", "�˸�", JOptionPane.INFORMATION_MESSAGE);

			}

		});
		btn_close.addActionListener(e -> codeDialog.dispose());

		return codeDialog;
	}

	public JDialog insertDeptDialog(Frame f) {
	    deptDialog = new JDialog(f, "�μ����", true);
	    deptDialog.setSize(300, 200);
	    deptDialog.setLocationRelativeTo(null);

	    // �ܰ� ������ �߰��� JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4, 2, 10, 10));
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // ���� �߰�

	    lbl_id = new JLabel("�μ���:");
	    lbl_id.setFont(new Font("���� ���", Font.BOLD, 12));
	    tf_idInput = new JTextField();
	    tf_idInput.setFont(new Font("���� ���", Font.PLAIN, 12));

	    btn_save = new JButton("����");
	    btn_close = new JButton("�ݱ�");

	    // �гο� ������Ʈ �߰�
	    panel.add(lbl_id);
	    panel.add(tf_idInput);
	    panel.add(btn_save);
	    panel.add(btn_close);

	    // ���̾�α׿� �г� �߰�
	    deptDialog.getContentPane().add(panel);

		btn_save.addActionListener(e -> {
			if (tf_idInput.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�Է�â�� �� ���� �ֽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE);
				return;
			}
			ArrayList<String> al = new <String>ArrayList();
			al.add(tf_idInput.getText());
			try {
				InsertTeam it = new InsertTeam();
				int cnt = it.getDML(al);


				JOptionPane.showMessageDialog(this, "�μ� ���: " + al.get(0));
				deptDialog.dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "�̹� �����ϴ� �μ��Դϴ�", "���", JOptionPane.WARNING_MESSAGE);		
			}
		});
		btn_close.addActionListener(e -> deptDialog.dispose());

		return deptDialog;
	}

	public JDialog openAdminModeDialog(Frame f) {

		adminDialog = new JDialog(f, "������ ���", true);
		adminDialog.setSize(300, 200);
		adminDialog.setLayout(new FlowLayout());
		adminDialog.setLocationRelativeTo(null);
		
		btn_confirm = new JButton("Ȯ��");
		btn_close = new JButton("�ݱ�");

		adminDialog.add(cb_adminMode);
		adminDialog.add(btn_confirm);
		adminDialog.add(btn_close);

		btn_confirm.addActionListener(e -> {
			Finance_Management fr = new Finance_Management();
			if (cb_adminMode.isSelected()) {
				fr.accesslv = 2;
				JOptionPane.showMessageDialog(this, "������ ��� Ȱ��ȭ");
				adminDialog.dispose();
			} else {
				fr.accesslv = 1;
				JOptionPane.showMessageDialog(this, "������ ��� ��Ȱ��ȭ");
				adminDialog.dispose();
			}
			
		});
		btn_close.addActionListener(e -> adminDialog.dispose());

		return adminDialog;
	}

	public JDialog insertincomeDialog(Frame f) {
	    incomeDialog = new JDialog(f, "������� ����", true);
	    incomeDialog.setSize(300, 200);
	    incomeDialog.setLocationRelativeTo(null);

	    // �ܰ� ������ �߰��� JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4, 2, 10, 10));
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // ���� �߰�

	    lbl_laCodeChoose = new JLabel("�μ� ����:");
	    lbl_laCodeChoose.setFont(new Font("���� ���", Font.BOLD, 12));

		Finance_Management fm = new Finance_Management();
		String dpte[] = new String[fm.tnamelist.size()];
		for (int i = 0; i < dpte.length; i++) {
			dpte[i] = fm.tnamelist.get(i);
		}
		 cb_laCodeChoose = new JComboBox<>(dpte);
		    cb_laCodeChoose.setFont(new Font("���� ���", Font.PLAIN, 12));

		    lbl_smCodeName = new JLabel("���� ����(�鸸):");
		    lbl_smCodeName.setFont(new Font("���� ���", Font.BOLD, 12));
		    tf_smCodeNameInput = new JTextField(20);
		    tf_smCodeNameInput.setFont(new Font("���� ���", Font.PLAIN, 12));

		    btn_save = new JButton("����");
		    btn_close = new JButton("�ݱ�");

		    // �гο� ������Ʈ �߰�
		    panel.add(lbl_laCodeChoose);
		    panel.add(cb_laCodeChoose);
		    panel.add(lbl_smCodeName);
		    panel.add(tf_smCodeNameInput);
		    panel.add(btn_save);
		    panel.add(btn_close);

		    // ���̾�α׿� �г� �߰�
		    incomeDialog.getContentPane().add(panel);


		btn_save.addActionListener(e -> {
			HashMap<Integer, String> im = new HashMap<Integer, String>();
			im.put(0, "EXPECTINCOME");
			ArrayList<String> il = new <String>ArrayList();
			il.add(tf_smCodeNameInput.getText());
			il.add(((cb_laCodeChoose.getSelectedIndex() + 1) * 10) + "");
			try {
				UpdateIncome ui = new UpdateIncome();
				int cnt = ui.getDML(im, il);
				incomeDialog.dispose();
				JOptionPane.showMessageDialog(this, "�������: " + il.get(0));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btn_close.addActionListener(e -> incomeDialog.dispose());

		return incomeDialog;
	}

}