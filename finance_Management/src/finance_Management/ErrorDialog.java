package gitStart;

import javax.swing.*;
import java.awt.*;

// ���̾�α� Ŭ����
public class ErrorDialog extends JDialog {
    JButton jbClose;
    JPanel jpSouth;
    JLabel jlMessage;
    
    public ErrorDialog(Frame parent) {
        super(parent, "�˸�", true);
        
        jlMessage = new JLabel("�߸� �Ǿ����ϴ�.", SwingConstants.CENTER);
        add(jlMessage, BorderLayout.CENTER);
        
        jbClose = new JButton("�ݱ�");
        jbClose.addActionListener(e -> dispose());
        
        jpSouth = new JPanel();
        jpSouth.add(jbClose);
        add(jpSouth, BorderLayout.SOUTH);
        
        setSize(400, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}