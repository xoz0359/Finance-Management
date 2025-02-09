package inserrDept;

import java.awt.*;
import javax.swing.*;

public class LoginForm extends JPanel {
    JButton jbttnCancle, jbttnSave;
    JLabel jlId, l2, jlPw, l4, jlAut, l6, l7;
    JTextField jtId;
    JPasswordField jpPw;
    GridBagConstraints constraint;
    GridBagLayout gridbag;
    String[] authority = {"관리자", "사용자"};
    JComboBox<String> jcAut;

    public LoginForm() {
        gridbag = new GridBagLayout();
        setLayout(gridbag);

        constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(0, 0, 10, 10);

        constraint.gridx = 0;
        constraint.gridy = 0;
        jlId = new JLabel("ID");
        gridbag.setConstraints(jlId, constraint);
        add(jlId);

        constraint.gridx = 1;
        constraint.gridy = 0;
        jtId = new JTextField(10);
        gridbag.setConstraints(jtId, constraint);
        add(jtId);

        constraint.gridx = 2;
        constraint.gridy = 0;
        l2 = new JLabel();
        gridbag.setConstraints(l2, constraint);
        add(l2);

        constraint.gridx = 0;
        constraint.gridy = 1;
        jlPw = new JLabel("PASSWORD");
        gridbag.setConstraints(jlPw, constraint);
        add(jlPw);

        constraint.gridx = 1;
        constraint.gridy = 1;
        jpPw = new JPasswordField(10);
        gridbag.setConstraints(jpPw, constraint);
        add(jpPw);

        constraint.gridx = 2;
        constraint.gridy = 1;
        l4 = new JLabel();
        gridbag.setConstraints(l4, constraint);
        add(l4);

        constraint.gridx = 0;
        constraint.gridy = 2;
        jlAut = new JLabel("권한"); // 중복된 "PASSWORD"를 수정
        gridbag.setConstraints(jlAut, constraint);
        add(jlAut);

        constraint.gridx = 1;
        constraint.gridy = 2;
        jcAut = new JComboBox<>(authority);
        gridbag.setConstraints(jcAut, constraint);
        add(jcAut);

        constraint.gridx = 2;
        constraint.gridy = 2;
        l6 = new JLabel();
        gridbag.setConstraints(l6, constraint);
        add(l6);

        constraint.gridx = 0;
        constraint.gridy = 3;
        l7 = new JLabel();
        gridbag.setConstraints(l7, constraint);
        add(l7);

        constraint.fill = GridBagConstraints.NONE;
        constraint.anchor = GridBagConstraints.EAST;
        constraint.gridx = 1;
        constraint.gridy = 3;
        jbttnCancle = new JButton("취소");
        jbttnCancle.setPreferredSize(new Dimension(60, 23));
        gridbag.setConstraints(jbttnCancle, constraint);
        add(jbttnCancle);

        constraint.gridx = 2;
        constraint.gridy = 3;
        jbttnSave = new JButton("저장");
        jbttnSave.setPreferredSize(new Dimension(60, 23));
        gridbag.setConstraints(jbttnSave, constraint);
        add(jbttnSave);
        
        setSize(450, 270);
        setVisible(true);
    }
}
