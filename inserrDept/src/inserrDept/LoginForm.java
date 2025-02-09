package inserrDept;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginForm extends JPanel {
    JButton jbttnCancle,jbttnSave;
    // l2,l4,l6,l7 레이아웃을 위한 공백으로 사용.
    JLabel jlId,l2,jlPw,l4,jlAut,l6,l7;
    GridBagConstraints constraint;
    GridBagLayout gridbag;
    String[] authority = {"관리자", "사용자"};
    JComboBox<String> jcAut;

    public LoginForm() {
    	gridbag = new GridBagLayout();
    	getContentPane().setLayout(gridbag);
    	setTitle("사용자 등록");
    	
    	constraint = new GridBagConstraints();
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(0, 0, 10, 10);


        constraint.gridx = 0;
        constraint.gridy = 0;
        JLabel jlId = new JLabel("ID");
        gridbag.setConstraints(jlId, constraint);
        getContentPane().add(jlId);
        
        constraint.gridx = 1;
        constraint.gridy = 0;
        JTextField jtId = new JTextField(10);
        gridbag.setConstraints(jtId, constraint);
        getContentPane().add(jtId);
        

        constraint.gridx = 2;
        constraint.gridy = 0;
        JLabel l2 = new JLabel();
        gridbag.setConstraints(l2, constraint);
        getContentPane().add(l2);
           
        constraint.gridx = 0;
        constraint.gridy = 1;
        JLabel jlPw = new JLabel("PASSWORD");
        gridbag.setConstraints(jlPw, constraint);
        getContentPane().add(jlPw);
        
        constraint.gridx = 1;
        constraint.gridy = 1;
        JPasswordField jpPw = new JPasswordField(10);
        gridbag.setConstraints(jpPw, constraint);
        getContentPane().add(jpPw);
        
        constraint.gridx = 2;
        constraint.gridy = 1;
        JLabel l4 = new JLabel();
        gridbag.setConstraints(l4, constraint);
        getContentPane().add(l4);

        constraint.gridx = 0;
        constraint.gridy = 2;
        JLabel jlAut = new JLabel("PASSWORD");
        gridbag.setConstraints(jlAut, constraint);
        getContentPane().add(jlAut);
        
        constraint.gridx = 1;
        constraint.gridy = 2;
        jcAut = new JComboBox<String>(authority);
        gridbag.setConstraints(jcAut, constraint);
        getContentPane().add(jcAut);
        
        constraint.gridx = 2;
        constraint.gridy = 2;
        JLabel l6 = new JLabel();
        gridbag.setConstraints(l6, constraint);
        getContentPane().add(l6);

        constraint.gridx = 0;
        constraint.gridy = 3;
        JLabel l7 = new JLabel();
        gridbag.setConstraints(l7, constraint);
        getContentPane().add(l7);
        
        constraint.fill = GridBagConstraints.NONE;
        constraint.anchor = GridBagConstraints.EAST;
        constraint.gridx = 1;
        constraint.gridy = 3;
        jbttnCancle = new JButton("취소");
        jbttnCancle.setPreferredSize(new Dimension(60, 23));
        gridbag.setConstraints(jbttnCancle, constraint);
        getContentPane().add(jbttnCancle);
        

        constraint.gridx = 2;
        constraint.gridy = 3;
        jbttnSave = new JButton("저장");
        jbttnSave.setPreferredSize(new Dimension(60, 23));
        gridbag.setConstraints(jbttnSave, constraint);
        getContentPane().add(jbttnSave);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBounds(1000, 200, 450, 270);
    }
    public static void main(String[] args) {
    	new LoginForm();
    }

}
