package gitStart;

import javax.swing.*;
import java.awt.*;

public class ASbectDialog extends JDialog {
    JButton jbClose, jbSave;
    JLabel deptCode, l1, l2;
    JPasswordField codeNumber;
    JPanel pWindow;
    GridBagConstraints gbc;
    GridBagLayout gridB;

    public ASbectDialog(Frame parent) {
        super(parent, "계정코드 입력", true);

        pWindow = new JPanel();
        gridB = new GridBagLayout();
        pWindow.setLayout(gridB);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 20, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        deptCode = new JLabel("계정코드 :", JLabel.RIGHT);
        gridB.setConstraints(deptCode, gbc);
        pWindow.add(deptCode);

        gbc.gridx = 1;
        gbc.gridy = 0;
        codeNumber = new JPasswordField(10);
        gridB.setConstraints(codeNumber, gbc);
        pWindow.add(codeNumber);

        gbc.gridx = 2;
        gbc.gridy = 0;
        l1 = new JLabel();
        gridB.setConstraints(l1, gbc);
        pWindow.add(l1);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        l2 = new JLabel();
        gridB.setConstraints(l2, gbc);
        pWindow.add(l2);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        jbSave = new JButton("저장");
        jbSave.setPreferredSize(new Dimension(60, 23));
        gridB.setConstraints(jbSave, gbc);
        pWindow.add(jbSave);

        gbc.gridx = 2;
        gbc.gridy = 1;
        jbClose = new JButton("닫기");
        jbClose.setPreferredSize(new Dimension(60, 23));
        gridB.setConstraints(jbClose, gbc);
        pWindow.add(jbClose);

        add(pWindow);

        setSize(450, 250);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
