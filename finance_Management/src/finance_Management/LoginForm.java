package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;

public class LoginForm extends JPanel {
    JButton jbSave;
    JLabel jlId, jlPw;
    JTextField jtId;
    JPasswordField jpPw;
    JCheckBox rememberIdCheckBox; // ���̵� ��� üũ�ڽ� �߰�
    GridBagConstraints gbc;
    GridBagLayout gridB;

    public LoginForm() {
        gridB = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(new BorderLayout());

        //  �ٱ� �г�: �Ķ��� ����
        JPanel outerPl = new JPanel(gridB);
        outerPl.setBackground(Color.decode("#3F91D5"));

        //  �߾� �г� (ID/PW �Է�â �г�): ������� ����
        JPanel pWindow = new JPanel(gridB);
        pWindow.setBackground(Color.WHITE); // �߾� �г� ��� ����

        pWindow.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY, 2)), 
                new EmptyBorder(40, 40, 40, 40)
        ));

        Font font = new Font("���� ���", Font.BOLD, 15);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // ID ��
        gbc.gridx = 0;
        gbc.gridy = 0;
        jlId = new JLabel("ID :", JLabel.RIGHT);
        jlId.setFont(font);
        jlId.setForeground(Color.BLACK);
        gridB.setConstraints(jlId, gbc);
        pWindow.add(jlId);

        // ID �Է� �ʵ�
        gbc.gridx = 1;
        jtId = new JTextField(15);
        jtId.setPreferredSize(new Dimension(200, 25));
        gridB.setConstraints(jtId, gbc);
        pWindow.add(jtId);

        // ��й�ȣ ��
        gbc.gridx = 0;
        gbc.gridy = 1;
        jlPw = new JLabel("PASSWORD :", JLabel.RIGHT);
        jlPw.setFont(font);
        jlPw.setForeground(Color.BLACK);
        gridB.setConstraints(jlPw, gbc);
        pWindow.add(jlPw);

        // ��й�ȣ �Է� �ʵ�
        gbc.gridx = 1;
        jpPw = new JPasswordField(15);
        jpPw.setPreferredSize(new Dimension(200, 25));
        gridB.setConstraints(jpPw, gbc);
        pWindow.add(jpPw);

        // "���̵� ���" üũ�ڽ�
        gbc.gridx = 1;
        gbc.gridy = 2;
        rememberIdCheckBox = new JCheckBox("���̵� ���");
        rememberIdCheckBox.setFont(font);
        rememberIdCheckBox.setForeground(Color.BLACK);
        rememberIdCheckBox.setBackground(Color.WHITE);
        gridB.setConstraints(rememberIdCheckBox, gbc);
        pWindow.add(rememberIdCheckBox);
        rememberIdCheckBox.setSelected(false);
        
        // �α��� ��ư
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        jbSave = new JButton("�α���");
        jbSave.setFont(font);
        gridB.setConstraints(jbSave, gbc);
        pWindow.add(jbSave);

        jbSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        
        // Enter Ű�� �α��� ��ư Ŭ�� �����ϵ��� ����
        jbSave.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jbSave.doClick();
                }
            }
        });

        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 0;
        frameConstraints.weightx = 1;
        frameConstraints.weighty = 1;
        frameConstraints.anchor = GridBagConstraints.CENTER;

        outerPl.add(pWindow, frameConstraints);
        add(outerPl);
        setVisible(true);
     // ���α׷� ���� �� ����� ���� �ҷ�����
    }

    // ���̵� ��� ���¸� ���Ͽ� �����ϴ� �޼��� (������ ������ ����)
   
   
}