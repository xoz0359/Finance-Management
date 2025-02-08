package loginForm;

import javax.swing.*;
import java.awt.*;

public class ErrorDialog extends JDialog {
	JLabel jlAlarm;
	JButton jbClose;
	JPanel jpCenter, jpSouth;

    public ErrorDialog(JFrame parent, String message) {
        super(parent, "Error", true); 
        setLayout(new BorderLayout(0, 0));

        jlAlarm = new JLabel(message, SwingConstants.CENTER);
        jlAlarm.setHorizontalAlignment(SwingConstants.CENTER);

        jbClose = new JButton("´Ý±â");
        jbClose.addActionListener(e -> dispose());

        jpCenter = new JPanel(new BorderLayout());
        jpCenter.add(jlAlarm, BorderLayout.CENTER);
        jpCenter.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));


        jpSouth = new JPanel();
        jpSouth.add(jbClose);

        add(jpCenter, BorderLayout.CENTER);
        add(jpSouth, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);

        setVisible(true);
    }
}
