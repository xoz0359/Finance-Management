package loginForm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginForm extends JPanel {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel pWindow;
    JPanel jp1;
    JPanel jp2;
    JPanel jp3;
    JPanel jp4;
    JPanel jp5;
    JPanel jp6;
    JLabel jlId;
    JTextField jtId;
    JLabel jlPw;
    JPasswordField jtPw;
    JLabel jlAut;
    String[] authority = {"관리자", "사용자"};
    JButton cancle;
    JButton check;
    JComboBox<String> jcAut;

    public LoginForm() {

        pWindow = new JPanel(new GridBagLayout());

        jp1 = new JPanel(new BorderLayout());
        jlId = new JLabel("ID : ", JLabel.RIGHT);
        gbadd(jp1, 0, 0, 1, 1, pWindow);

        jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jtId = new JTextField(12);
        gbadd(jp2, 1, 0, 2, 2, pWindow);

        jp3 = new JPanel(new BorderLayout());
        jlPw = new JLabel("PASSWORD : ", JLabel.RIGHT);
        gbadd(jp3, 0, 1, 1, 1, pWindow);

        jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jtPw = new JPasswordField(12);
        gbadd(jp4, 1, 1, 2, 1, pWindow);

        jp5 = new JPanel(new BorderLayout());
        jlAut = new JLabel("권한 : ", JLabel.RIGHT);
        gbadd(jp5, 0, 2, 1, 1, pWindow);

        jp6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jcAut = new JComboBox<String>(authority);
        jcAut.setPreferredSize(new Dimension(135, 25));
        gbadd(jp6, 1, 2, 2, 1, pWindow);

        
        cancle = new JButton("취소");
        check = new JButton("저장");

        cancle.setBounds(235, 185, 70, 25);
        check.setBounds(320, 185, 70, 25);
        

        jp1.add(jlId);
        jp2.add(jtId);
        jp3.add(jlPw);
        jp4.add(jtPw);
        jp5.add(jlAut);
        jp6.add(jcAut);
        this.getContentPane().add(cancle);
        this.getContentPane().add(check);
        this.add(pWindow);

        // 창 위치 이동과 사이즈 설정(int x, int y, 500, 300)
        setBounds(1000, 200, 500, 300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void gbadd(Component c, int x, int y, int tx, int w, JPanel p_window) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = tx;
        gbc.gridwidth = w;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        p_window.add(c, gbc);
    }
}
