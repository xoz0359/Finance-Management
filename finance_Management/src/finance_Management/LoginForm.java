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
    JCheckBox rememberIdCheckBox; // 아이디 기억 체크박스 추가
    GridBagConstraints gbc;
    GridBagLayout gridB;

    public LoginForm() {
        gridB = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(new BorderLayout());

        //  바깥 패널: 파란색 유지
        JPanel outerPl = new JPanel(gridB);
        outerPl.setBackground(Color.decode("#3F91D5"));

        //  중앙 패널 (ID/PW 입력창 패널): 흰색으로 변경
        JPanel pWindow = new JPanel(gridB);
        pWindow.setBackground(Color.WHITE); // 중앙 패널 흰색 적용

        pWindow.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY, 2)), 
                new EmptyBorder(40, 40, 40, 40)
        ));

        Font font = new Font("맑은 고딕", Font.BOLD, 15);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // ID 라벨
        gbc.gridx = 0;
        gbc.gridy = 0;
        jlId = new JLabel("ID :", JLabel.RIGHT);
        jlId.setFont(font);
        jlId.setForeground(Color.BLACK);
        gridB.setConstraints(jlId, gbc);
        pWindow.add(jlId);

        // ID 입력 필드
        gbc.gridx = 1;
        jtId = new JTextField(15);
        jtId.setPreferredSize(new Dimension(200, 25));
        gridB.setConstraints(jtId, gbc);
        pWindow.add(jtId);

        // 비밀번호 라벨
        gbc.gridx = 0;
        gbc.gridy = 1;
        jlPw = new JLabel("PASSWORD :", JLabel.RIGHT);
        jlPw.setFont(font);
        jlPw.setForeground(Color.BLACK);
        gridB.setConstraints(jlPw, gbc);
        pWindow.add(jlPw);

        // 비밀번호 입력 필드
        gbc.gridx = 1;
        jpPw = new JPasswordField(15);
        jpPw.setPreferredSize(new Dimension(200, 25));
        gridB.setConstraints(jpPw, gbc);
        pWindow.add(jpPw);

        // "아이디 기억" 체크박스
        gbc.gridx = 1;
        gbc.gridy = 2;
        rememberIdCheckBox = new JCheckBox("아이디 기억");
        rememberIdCheckBox.setFont(font);
        rememberIdCheckBox.setForeground(Color.BLACK);
        rememberIdCheckBox.setBackground(Color.WHITE);
        gridB.setConstraints(rememberIdCheckBox, gbc);
        pWindow.add(rememberIdCheckBox);
        rememberIdCheckBox.setSelected(false);
        
        // 로그인 버튼
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        jbSave = new JButton("로그인");
        jbSave.setFont(font);
        gridB.setConstraints(jbSave, gbc);
        pWindow.add(jbSave);

        jbSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        
        // Enter 키로 로그인 버튼 클릭 가능하도록 설정
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
     // 프로그램 시작 시 저장된 정보 불러오기
    }

    // 아이디 기억 상태를 파일에 저장하는 메서드 (파일이 없으면 생성)
   
   
}