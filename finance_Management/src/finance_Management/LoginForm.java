package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class LoginForm extends JPanel {
    JButton jbSave;
    JLabel jlId, jlPw;
    JTextField jtId;
    JPasswordField jpPw;
    GridBagConstraints gbc;
    GridBagLayout gridB;

    public LoginForm() {
        gridB = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(new BorderLayout());

        JPanel outerPl = new JPanel(gridB );
        outerPl.setBackground(Color.decode("#3F91D5"));
        JPanel pWindow = new JPanel(gridB);
        
        pWindow.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY, 2)),
                new EmptyBorder(40, 40,40, 40)
        ));

        Font font = new Font("¸¼Àº °íµñ",Font.BOLD, 15);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
 
        gbc.gridx = 0;
        gbc.gridy = 0;
        jlId = new JLabel("ID :", JLabel.RIGHT);
        jlId.setBorder(BorderFactory.createEmptyBorder(0 , 0, 6 , 0));
        jlId.setFont(font);
        gridB.setConstraints(jlId, gbc);
        pWindow.add(jlId);
 
        gbc.gridx = 1;
        gbc.gridy = 0;
        jtId = createFocusChangingTextField(15);
        jtId.setPreferredSize(new Dimension(200, 25));
        gridB.setConstraints(jtId, gbc);
        pWindow.add(jtId);
 
        gbc.gridx = 0;
        gbc.gridy = 1;
        jlPw = new JLabel("PASSWORD :", JLabel.RIGHT);
        jlPw.setBorder(BorderFactory.createEmptyBorder(0 , 0, 6 , 0));
        jlPw.setFont(font);
        gridB.setConstraints(jlPw, gbc);
        pWindow.add(jlPw);
 
        gbc.gridx = 1;
        jpPw = createFocusChangingJPasswordField(15);
        jpPw.setPreferredSize(new Dimension(200, 25));
        gridB.setConstraints(jpPw, gbc);
        pWindow.add(jpPw);
 
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        jbSave = new JButton("·Î±×ÀÎ");
        jbSave.setFont(font);
        gridB.setConstraints(jbSave, gbc);
        pWindow.add(jbSave);

        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 0;
        frameConstraints.weightx = 1;
        frameConstraints.weighty = 1;
        frameConstraints.anchor = GridBagConstraints.CENTER;
        

        outerPl.add(pWindow, frameConstraints);
        add(outerPl);
        setVisible(true);
    }

    public JTextField createFocusChangingTextField(int columns) {
        JTextField textField = new JTextField(columns);
        Color defaultColor = Color.WHITE;
        Color focusColor = new Color(200, 230, 255);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBackground(focusColor);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBackground(defaultColor);
            }
        });

        return textField;
    }

    private JPasswordField createFocusChangingJPasswordField(int columns) {
        JPasswordField passwordField = new JPasswordField(columns);
        Color defaultColor = Color.WHITE;
        Color focusColor = new Color(200, 230, 255);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setBackground(focusColor);
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setBackground(defaultColor);
            }
        });

        return passwordField;
    }
}
