package finance_su;

import java.awt.*;
import javax.swing.*;

public class UserRegistration extends JFrame {

	JPanel p_center, p_bottom;
	JTextField tf_idInput, tf_pwdInput;
	JComboBox<String> cb_powerInput;
	JButton btn_save, btn_close;
	JLabel lbl_id, lbl_pwd, lbl_power;

	public UserRegistration() {
		super("사용자등록창");

		p_center = new JPanel();
		p_center.setLayout(new GridLayout(3, 2, 10, 10));
		p_center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		lbl_id = new JLabel("ID:");
		lbl_id.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_idInput = new JTextField();
		tf_idInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		lbl_pwd = new JLabel("PASSWORD:");
		lbl_pwd.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_pwdInput = new JTextField();
		tf_pwdInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		lbl_power = new JLabel("권한구분:");
		lbl_power.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cb_powerInput = new JComboBox<>(new String[] { "관리자", "사용자" });
		cb_powerInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		p_center.add(lbl_id);
		p_center.add(tf_idInput);
		p_center.add(lbl_pwd);
		p_center.add(tf_pwdInput);
		p_center.add(lbl_power);
		p_center.add(cb_powerInput);

		p_bottom = new JPanel();
		p_bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 55, 35));

		btn_save = new JButton("저장");
		btn_save.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		btn_close = new JButton("닫기");
		btn_close.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		p_bottom.add(btn_save);
		p_bottom.add(btn_close);

		add(p_center, BorderLayout.CENTER);
		add(p_bottom, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
