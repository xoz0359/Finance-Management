package gitStart;

import javax.swing.*;
import java.awt.*;

// 다이얼로그 클래스
public class ErrorDialog extends JDialog {
    JButton jbClose;
    JPanel jpSouth;
    JLabel jlMessage;
    
    public ErrorDialog(Frame parent) {
        super(parent, "알림", true);
        
        jlMessage = new JLabel("잘못 되었습니다.", SwingConstants.CENTER);
        add(jlMessage, BorderLayout.CENTER);
        
        jbClose = new JButton("닫기");
        jbClose.addActionListener(e -> dispose());
        
        jpSouth = new JPanel();
        jpSouth.add(jbClose);
        add(jpSouth, BorderLayout.SOUTH);
        
        setSize(400, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}