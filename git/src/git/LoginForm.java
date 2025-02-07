package git;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JPanel {
   String dbid = "scott";
   String dbpw = "1234";
   JPanel j_window;

   JLabel titlelb;
   JPanel j_top;
   JPanel j_middle;
   JPanel p_log;
   JLabel j_id;
   JLabel j_pw;
   JTextField idfield;
   JPasswordField pwfield;
   JButton login_btt;

   public LoginForm() {
      j_window = new JPanel(new BorderLayout());
      
      j_top = new JPanel(new BorderLayout());
      titlelb = new JLabel("재무제표", JLabel.CENTER);
      j_top.add(titlelb);
      j_window.add(j_top, BorderLayout.CENTER);

      j_middle = new JPanel(new BorderLayout());
      p_log = new JPanel(new GridLayout(2, 2, 0, 0));
      j_id = new JLabel("ID");
      j_pw = new JLabel("PW");
      idfield = new JTextField();
      pwfield = new JPasswordField();
      j_id.setHorizontalAlignment(JLabel.CENTER);
      j_pw.setHorizontalAlignment(JLabel.CENTER);

      p_log.add(j_id);
      p_log.add(idfield);
      p_log.add(j_pw);
      p_log.add(pwfield);

      j_middle.add(p_log);

      login_btt = new JButton("Login");
      j_middle.add(login_btt, BorderLayout.SOUTH);
      j_window.add(j_middle, BorderLayout.SOUTH);
      
      this.add(j_window);
   }
}
