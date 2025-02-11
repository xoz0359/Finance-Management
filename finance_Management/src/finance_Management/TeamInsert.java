package finance_Management;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TeamInsert extends JPanel {
	JButton jbttnSave,jbttnClose;
	JLabel deptCode,l1,l2,l3,l4,l5,l6;
	JTextField codeNumber;
    GridBagConstraints constraint;
    GridBagLayout gridbag;

    public TeamInsert() {
    	gridbag = new GridBagLayout();
	setLayout(gridbag);
    	
    	constraint = new GridBagConstraints();
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(0, 0, 20, 10);

    	constraint.gridx = 0;
        constraint.gridy = 0;
        deptCode = new JLabel("부서명 :", JLabel.RIGHT);
        gridbag.setConstraints(deptCode, constraint);
        add(deptCode);
        
        constraint.gridx = 1;
        constraint.gridy = 0;
        codeNumber = new JTextField(10);
        gridbag.setConstraints(codeNumber, constraint);
        add(codeNumber);
        
        constraint.gridx = 2;
        constraint.gridy = 0;
        l1 = new JLabel();
        gridbag.setConstraints(l1, constraint);
        add(l1);
        
        constraint.gridx = 0;
        constraint.gridy = 1;
        l2 = new JLabel();
        gridbag.setConstraints(l2, constraint);
        add(l2);
        
        constraint.fill = GridBagConstraints.NONE;
        constraint.anchor = GridBagConstraints.EAST;
        constraint.gridx = 1;
        constraint.gridy = 1;
        jbttnSave = new JButton("저장");
        jbttnSave.setPreferredSize(new Dimension(60, 23));
        gridbag.setConstraints(jbttnSave, constraint);
        add(jbttnSave);
        
        constraint.gridx = 2;
        constraint.gridy = 1;
        jbttnClose = new JButton("닫기");
        jbttnClose.setPreferredSize(new Dimension(60, 23));
        gridbag.setConstraints(jbttnClose, constraint);
        add(jbttnClose);

    }
}
