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


 
}