package finance_su;

import java.awt.*;
import javax.swing.*;

public class CodeRegistration extends JFrame {

	JPanel p_center, p_bottom;
	JTextField tf_smCodeNameInput;
	JComboBox<String> cb_laCodeChoose;
	JButton btn_save, btn_close;
	JLabel lbl_laCodeChoose, lbl_smCodeName;

	public CodeRegistration() {
		super("계정코드등록창");

		p_center = new JPanel();
		p_center.setLayout(new GridLayout(3, 2, 10, 10));
		p_center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		lbl_laCodeChoose = new JLabel("대분류코드:");
		lbl_laCodeChoose.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cb_laCodeChoose = new JComboBox<>(new String[] { "100. 자산", "200. 부채", "300. 수익", "400. 비용" });
		cb_laCodeChoose.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_smCodeName = new JLabel("계정코드명:");
		lbl_smCodeName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_smCodeNameInput = new JTextField(20); // 입력 필드
		tf_smCodeNameInput.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		p_center.add(lbl_laCodeChoose);
		p_center.add(cb_laCodeChoose);
		p_center.add(lbl_smCodeName);
		p_center.add(tf_smCodeNameInput);

		p_bottom = new JPanel();
		p_bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

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