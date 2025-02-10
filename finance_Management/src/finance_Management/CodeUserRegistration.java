package finance_Management;

import java.awt.*;
import javax.swing.*;

public class CodeUserRegistration extends JPanel {

   JPanel p_center, p_bottom;
   JTextField tf_smCodeNameInput, tf_idInput, tf_accesslvInput;
   JComboBox<String> cb_laCodeChoose, cb_accesslvInput;
   JButton btn_save, btn_close;
   JLabel lbl_substitle, lbl_laCodeChoose, lbl_smCodeName,
         lbl_id, lbl_pwd, lbl_accesslv;

   public CodeUserRegistration() {
           super();
           
           p_center = new JPanel();
           p_center.setLayout(new GridLayout(5, 2, 10, 10));
           p_center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

           lbl_laCodeChoose = new JLabel("´ëºÐ·ùÄÚµå:");
           lbl_laCodeChoose.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
           cb_laCodeChoose = new JComboBox<>(new String[] { "100. ÀÚ»ê", "200. ºÎÃ¤", "300. ¼öÀÍ", "400. ºñ¿ë" });
           cb_laCodeChoose.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

           lbl_smCodeName = new JLabel("°èÁ¤ÄÚµå¸í:");
           lbl_smCodeName.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
           tf_smCodeNameInput = new JTextField(20);
           tf_smCodeNameInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
           
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

           p_center.add(lbl_laCodeChoose);
           p_center.add(cb_laCodeChoose);
           p_center.add(lbl_smCodeName);
           p_center.add(tf_smCodeNameInput);
          p_center.add(lbl_id);
         p_center.add(tf_idInput);
         p_center.add(lbl_pwd);
         p_center.add(tf_accesslvInput);
         p_center.add(lbl_accesslv);
         p_center.add(cb_accesslvInput);

           p_bottom = new JPanel();
           p_bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

           btn_save = new JButton("ÀúÀå");
           btn_save.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

           btn_close = new JButton("´Ý±â");
           btn_close.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

           p_bottom.add(btn_save);
           p_bottom.add(btn_close);

           add(p_center, BorderLayout.CENTER);
           add(p_bottom, BorderLayout.SOUTH);
       }
   }