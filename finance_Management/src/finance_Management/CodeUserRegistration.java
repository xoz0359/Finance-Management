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

           lbl_laCodeChoose = new JLabel("��з��ڵ�:");
           lbl_laCodeChoose.setFont(new Font("���� ���", Font.BOLD, 12));
           cb_laCodeChoose = new JComboBox<>(new String[] { "100. �ڻ�", "200. ��ä", "300. ����", "400. ���" });
           cb_laCodeChoose.setFont(new Font("���� ���", Font.PLAIN, 12));

           lbl_smCodeName = new JLabel("�����ڵ��:");
           lbl_smCodeName.setFont(new Font("���� ���", Font.BOLD, 12));
           tf_smCodeNameInput = new JTextField(20);
           tf_smCodeNameInput.setFont(new Font("���� ���", Font.PLAIN, 12));
           
           lbl_id = new JLabel("ID:");
         lbl_id.setFont(new Font("���� ���", Font.BOLD, 12));
         tf_idInput = new JTextField();
         tf_idInput.setFont(new Font("���� ���", Font.PLAIN, 12));

         lbl_pwd = new JLabel("PASSWORD:");
         lbl_pwd.setFont(new Font("���� ���", Font.BOLD, 12));
         tf_accesslvInput = new JTextField();
         tf_accesslvInput.setFont(new Font("���� ���", Font.PLAIN, 12));

         lbl_accesslv = new JLabel("���ѱ���:");
         lbl_accesslv.setFont(new Font("���� ���", Font.BOLD, 12));
         cb_accesslvInput = new JComboBox<>(new String[] { "������", "�����" });
         cb_accesslvInput.setFont(new Font("���� ���", Font.PLAIN, 12));

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

           btn_save = new JButton("����");
           btn_save.setFont(new Font("���� ���", Font.BOLD, 12));

           btn_close = new JButton("�ݱ�");
           btn_close.setFont(new Font("���� ���", Font.BOLD, 12));

           p_bottom.add(btn_save);
           p_bottom.add(btn_close);

           add(p_center, BorderLayout.CENTER);
           add(p_bottom, BorderLayout.SOUTH);
       }
   }