package finance_Management;

import java.awt.*;
import javax.swing.*;

public class CodeUserRegistration extends JPanel {

   JPanel p_center, p_bottom, adminPanel;
   JTextField tf_smCodeNameInput, tf_idInput, tf_accesslvInput;
   JComboBox<String> cb_laCodeChoose, cb_accesslvInput;
   JButton btn_save, btn_close, btn_codeRegi, btn_userRegi, btn_adminMode, btn_confirm;
   JLabel lbl_substitle, lbl_laCodeChoose, lbl_smCodeName, lbl_id, lbl_pwd, lbl_accesslv;
   JDialog userDialog, codeDialog, adminDialog;
   JCheckBox cb_adminMode;

   public CodeUserRegistration() {

      p_center = new JPanel();
      p_center.setLayout(new BoxLayout(p_center, BoxLayout.Y_AXIS));

      btn_userRegi = new JButton("»ç¿ëÀÚ µî·Ï");
      btn_userRegi.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
      btn_userRegi.setPreferredSize(getPreferredSize());

      btn_codeRegi = new JButton("°èÁ¤ÄÚµå µî·Ï");
      btn_codeRegi.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

      adminPanel = new JPanel();
      adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.X_AXIS));
      adminPanel.setMaximumSize(new Dimension(300, 50));
      adminPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

      btn_adminMode = new JButton("°ü¸®ÀÚ ¸ðµå");
      btn_adminMode.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
      cb_adminMode = new JCheckBox();

      btn_adminMode.setPreferredSize(new Dimension(200, 40));
      cb_adminMode.setPreferredSize(new Dimension(20, 20));

      adminPanel.add(Box.createHorizontalGlue());
      adminPanel.add(btn_adminMode);
      adminPanel.add(cb_adminMode);
      adminPanel.add(Box.createHorizontalGlue());

      Dimension buttonSize = new Dimension(300, 50);
      btn_userRegi.setPreferredSize(buttonSize);
      btn_codeRegi.setPreferredSize(buttonSize);
      btn_adminMode.setPreferredSize(buttonSize);

      btn_userRegi.setMaximumSize(buttonSize);
      btn_codeRegi.setMaximumSize(buttonSize);
      btn_adminMode.setMaximumSize(buttonSize);

      btn_userRegi.setAlignmentX(Component.CENTER_ALIGNMENT);
      btn_codeRegi.setAlignmentX(Component.CENTER_ALIGNMENT);

      add(Box.createVerticalStrut(20));
      add(btn_userRegi);
      add(Box.createVerticalStrut(10));
      add(btn_codeRegi);
      add(Box.createVerticalStrut(10));
      add(adminPanel);
      add(Box.createVerticalStrut(20));
   }

   public JDialog openUserRegistrationDialog(Frame f) {
	      userDialog = new JDialog(f, "»ç¿ëÀÚ µî·Ï", true); 
	      userDialog.setSize(300, 200);
	      userDialog.setLayout(new GridLayout(4, 2, 10, 10));

	      lbl_id = new JLabel("ID:");
	      lbl_id.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
	      tf_idInput = new JTextField();
	      tf_idInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

	      lbl_pwd = new JLabel("PASSWORD:");
	      lbl_pwd.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
	      tf_accesslvInput = new JTextField();
	      tf_accesslvInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

	      lbl_accesslv = new JLabel("±ÇÇÑ±¸ºÐ:");
	      lbl_accesslv.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
	      cb_accesslvInput = new JComboBox<>(new String[] { "°ü¸®ÀÚ", "»ç¿ëÀÚ" });
	      cb_accesslvInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

	      btn_save = new JButton("ÀúÀå");
	      btn_close = new JButton("´Ý±â");

	      userDialog.add(lbl_id);
	      userDialog.add(tf_idInput);
	      userDialog.add(lbl_pwd);
	      userDialog.add(tf_accesslvInput);
	      userDialog.add(lbl_accesslv);
	      userDialog.add(cb_accesslvInput);
	      userDialog.add(btn_save);
	      userDialog.add(btn_close);
	      
	      return userDialog;
	   }

   public JDialog openCodeRegistrationDialog(Frame f) {
	      codeDialog = new JDialog(f, "°èÁ¤ÄÚµå µî·Ï", true);
	      codeDialog.setSize(300, 200);
	      codeDialog.setLayout(new GridLayout(3, 2, 10, 10));

	      lbl_laCodeChoose = new JLabel("´ëºÐ·ùÄÚµå:");
	      lbl_laCodeChoose.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
	      cb_laCodeChoose = new JComboBox<>(new String[] { "100. ÀÚ»ê", "200. ºÎÃ¤", "300. ¼öÀÍ", "400. ºñ¿ë" });
	      cb_laCodeChoose.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

	      lbl_smCodeName = new JLabel("°èÁ¤ÄÚµå¸í:");
	      lbl_smCodeName.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
	      tf_smCodeNameInput = new JTextField(20);
	      tf_smCodeNameInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

	      btn_save = new JButton("ÀúÀå");
	      btn_close = new JButton("´Ý±â");

	      codeDialog.add(lbl_laCodeChoose);
	      codeDialog.add(cb_laCodeChoose);
	      codeDialog.add(lbl_smCodeName);
	      codeDialog.add(tf_smCodeNameInput);
	      codeDialog.add(btn_save);
	      codeDialog.add(btn_close);

	      return codeDialog;
	   }

   public JDialog openAdminModeDialog(Frame f) {

	      adminDialog = new JDialog(f, "°èÁ¤ÄÚµå µî·Ï", true);
	      adminDialog.setSize(300, 200);
	      adminDialog.setLayout(new FlowLayout());

	      
	      btn_confirm = new JButton("È®ÀÎ");
	      btn_close = new JButton("´Ý±â");

	      adminDialog.add(cb_adminMode);
	      adminDialog.add(btn_confirm);
	      adminDialog.add(btn_close);

	      btn_close.addActionListener(e -> adminDialog.dispose());

	      adminDialog.setLocationRelativeTo(this);
	      adminDialog.setVisible(true);
	      
	      return adminDialog;
	   }

 
}