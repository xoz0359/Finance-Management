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

		// 버튼 생성
		btn_userRegi = new JButton("사용자 등록");
		btn_codeRegi = new JButton("계정코드 등록");
		btn_deptRegi = new JButton("부서 등록");
		btn_adminMode = new JButton(); // 관리자 모드 버튼 (내용 따로 설정)

		btn_userRegi.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btn_codeRegi.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btn_deptRegi.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btn_adminMode.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		// 버튼 크기 설정
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

		// 관리자 모드 버튼 내부에 체크박스 배치
		cb_adminMode = new JCheckBox();
		cb_adminMode.setPreferredSize(new Dimension(20, 20));
		cb_adminMode.setOpaque(false); // 체크박스 배경 투명화

		JPanel adminContent = new JPanel(new BorderLayout());
		adminContent.setOpaque(false);
		adminContent.add(new JLabel("관리자 모드", JLabel.CENTER), BorderLayout.CENTER);
		// adminContent.add(cb_adminMode, BorderLayout.EAST);

		btn_adminMode.setLayout(new BorderLayout());
		btn_adminMode.add(adminContent, BorderLayout.CENTER);

		// 패널에 요소 추가
		add(Box.createVerticalStrut(30));
		add(btn_userRegi);
		add(Box.createVerticalStrut(15));
		add(btn_codeRegi);
		add(Box.createVerticalStrut(15));
		add(btn_deptRegi);
		add(Box.createVerticalStrut(15));
		//add(btn_adminMode);
		//add(Box.createVerticalStrut(30));

		// 버튼 클릭 시 체크박스 토글 기능 추가
		// btn_adminMode.addActionListener(e ->
		// cb_adminMode.setSelected(!cb_adminMode.isSelected()));
	}

	public JDialog openUserRegistrationDialog(Frame f) {



		userDialog = new JDialog(f, "사용자 등록", true);
		userDialog.setSize(300, 200);
		userDialog.setLayout(new GridLayout(4, 2, 10, 10));
		userDialog.setLocationRelativeTo(null);

		lbl_id = new JLabel("ID:");
		lbl_id.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_idInput = new JTextField();
		tf_idInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		lbl_pwd = new JLabel("PASSWORD:");
		lbl_pwd.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_pwInput = new JTextField();
		tf_pwInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		lbl_accesslv = new JLabel("권한구분:");
		lbl_accesslv.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cb_accesslvInput = new JComboBox<>(new String[] { "조회전용계정", "사원", "관리자" });
		cb_accesslvInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		btn_save = new JButton("저장");
		btn_close = new JButton("닫기");

		userDialog.add(lbl_id);
		userDialog.add(tf_idInput);
		userDialog.add(lbl_pwd);
		userDialog.add(tf_pwInput);
		userDialog.add(lbl_accesslv);
		userDialog.add(cb_accesslvInput);
		userDialog.add(btn_save);
		userDialog.add(btn_close);

		btn_save.addActionListener(e -> {
			if (tf_idInput.getText().equals("") || tf_pwInput.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "입력창에 빈 값이 있습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			ArrayList<String> al = new <String>ArrayList();
			al.add(tf_idInput.getText());
			al.add(tf_pwInput.getText());
			al.add(cb_accesslvInput.getSelectedIndex()+"");
			try {
				InsertUserInfo iu = new InsertUserInfo();

				int cnt = iu.getDML(al);

				JOptionPane.showMessageDialog(null,
						"새로운 " + cb_accesslvInput.getSelectedItem().toString() + " 등록 완료!", "알림",
						JOptionPane.INFORMATION_MESSAGE);

				userDialog.dispose();
			} catch (SQLIntegrityConstraintViolationException e2) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 사용자입니다", "알림", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		btn_close.addActionListener(e -> userDialog.dispose());

		return userDialog;
	}

	public JDialog openCodeRegistrationDialog(Frame f, ArrayList<String> l) {
		codeDialog = new JDialog(f, "계정코드 등록", true);
		codeDialog.setSize(300, 200);
		codeDialog.setLayout(new GridLayout(3, 2, 10, 10));
		codeDialog.setLocationRelativeTo(null);

		lbl_laCodeChoose = new JLabel("대분류코드:");
		lbl_laCodeChoose.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cb_laCodeChoose = new JComboBox<>(new String[] { "100. 자산", "200. 부채", "300. 수익", "400. 비용" });
		cb_laCodeChoose.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		lbl_smCodeName = new JLabel("계정코드명:");
		lbl_smCodeName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_smCodeNameInput = new JTextField(20);
		tf_smCodeNameInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		btn_save = new JButton("저장");
		btn_close = new JButton("닫기");

		codeDialog.add(lbl_laCodeChoose);
		codeDialog.add(cb_laCodeChoose);
		codeDialog.add(lbl_smCodeName);
		codeDialog.add(tf_smCodeNameInput);
		codeDialog.add(btn_save);
		codeDialog.add(btn_close);

		btn_save.addActionListener(e -> {
			
			if (tf_smCodeNameInput.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "입력창에 빈 값이 있습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			ArrayList<String> al = new <String>ArrayList();
			if (cb_laCodeChoose.getSelectedItem().toString().equals("100. 자산")) {
				al.add("100");
			} else if (cb_laCodeChoose.getSelectedItem().toString().equals("200. 부채")) {
				al.add("200");
			} else if (cb_laCodeChoose.getSelectedItem().toString().equals("300. 수익")) {
				al.add("300");
			} else if (cb_laCodeChoose.getSelectedItem().toString().equals("400. 부채")) {
				al.add("400");
			}
			al.add(tf_smCodeNameInput.getText());

			try {
				InsertTitle it = new InsertTitle();
				int cnt = it.getDML(al);

				System.out.println(cnt + "계정코드 등록 되었습니다");
				JOptionPane.showMessageDialog(this, "계정과목 등록: " + al.get(1));
				
				
				InsertFin iif = new InsertFin();
				al.clear();
				al.add((l.size()-1)+"");
				iif.getDML(al);
				codeDialog.dispose();
				System.out.println(cnt+"행 입력");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "이미 존재하는 계정과목입니다", "알림", JOptionPane.INFORMATION_MESSAGE);

			}

		});
		btn_close.addActionListener(e -> codeDialog.dispose());

		return codeDialog;
	}

	public JDialog insertDeptDialog(Frame f) {

		deptDialog = new JDialog(f, "부서등록", true);
		deptDialog.setSize(300, 200);
		deptDialog.setLayout(new GridLayout(4, 2, 10, 10));
		deptDialog.setLocationRelativeTo(null);

		lbl_id = new JLabel("부서명:");
		lbl_id.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_idInput = new JTextField();
		tf_idInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		btn_save = new JButton("저장");
		btn_close = new JButton("닫기");

		deptDialog.add(lbl_id);
		deptDialog.add(tf_idInput);
		deptDialog.add(btn_save);
		deptDialog.add(btn_close);

		btn_save.addActionListener(e -> {
			if (tf_idInput.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "입력창에 빈 값이 있습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			ArrayList<String> al = new <String>ArrayList();
			al.add(tf_idInput.getText());
			try {
				InsertTeam it = new InsertTeam();
				int cnt = it.getDML(al);
				System.out.println(cnt + "행 입력완료");

				System.out.println(cnt + " 부서 등록 되었습니다");
				JOptionPane.showMessageDialog(this, "부서 등록: " + al.get(0));
				deptDialog.dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 부서입니다", "경고", JOptionPane.WARNING_MESSAGE);		
			}
		});
		btn_close.addActionListener(e -> deptDialog.dispose());

		return deptDialog;
	}

	public JDialog openAdminModeDialog(Frame f) {

		adminDialog = new JDialog(f, "관리자 모드", true);
		adminDialog.setSize(300, 200);
		adminDialog.setLayout(new FlowLayout());
		adminDialog.setLocationRelativeTo(null);
		
		btn_confirm = new JButton("확인");
		btn_close = new JButton("닫기");

		adminDialog.add(cb_adminMode);
		adminDialog.add(btn_confirm);
		adminDialog.add(btn_close);

		btn_confirm.addActionListener(e -> {
			Finance_Management fr = new Finance_Management();
			if (cb_adminMode.isSelected()) {
				fr.accesslv = 2;
				JOptionPane.showMessageDialog(this, "관리자 모드 활성화");
				adminDialog.dispose();
			} else {
				fr.accesslv = 1;
				JOptionPane.showMessageDialog(this, "관리자 모드 비활성화");
				adminDialog.dispose();
			}
			
		});
		btn_close.addActionListener(e -> adminDialog.dispose());

		return adminDialog;
	}

	public JDialog insertincomeDialog(Frame f) {

		incomeDialog = new JDialog(f, "예상매출 설정", true);
		incomeDialog.setSize(300, 200);
		incomeDialog.setLayout(new GridLayout(4, 2, 10, 10));
		incomeDialog.setLocationRelativeTo(null);

		lbl_laCodeChoose = new JLabel("부서 선택:");
		lbl_laCodeChoose.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		Finance_Management fm = new Finance_Management();
		String dpte[] = new String[fm.tnamelist.size()];
		for (int i = 0; i < dpte.length; i++) {
			dpte[i] = fm.tnamelist.get(i);
		}
		cb_laCodeChoose = new JComboBox<>(dpte);
		cb_laCodeChoose.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		lbl_smCodeName = new JLabel("예상 매출(백만):");
		lbl_smCodeName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_smCodeNameInput = new JTextField(20);
		tf_smCodeNameInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		btn_save = new JButton("저장");
		btn_close = new JButton("닫기");

		incomeDialog.add(lbl_laCodeChoose);
		incomeDialog.add(cb_laCodeChoose);
		incomeDialog.add(lbl_smCodeName);
		incomeDialog.add(tf_smCodeNameInput);
		incomeDialog.add(btn_save);
		incomeDialog.add(btn_close);

		btn_save.addActionListener(e -> {
			HashMap<Integer, String> im = new HashMap<Integer, String>();
			im.put(0, "EXPECTINCOME");
			ArrayList<String> il = new <String>ArrayList();
			il.add(tf_smCodeNameInput.getText());
			il.add(((cb_laCodeChoose.getSelectedIndex() + 1) * 10) + "");
			try {
				UpdateIncome ui = new UpdateIncome();
				int cnt = ui.getDML(im, il);
				System.out.println(cnt + " 예상매출 등록 되었습니다");
				incomeDialog.dispose();
				JOptionPane.showMessageDialog(this, "예상매출: " + il.get(0));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btn_close.addActionListener(e -> incomeDialog.dispose());

		return incomeDialog;
	}

}