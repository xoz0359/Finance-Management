package finance_Management;

import javax.swing.*;
import java.awt.*;

public class TransparentLogo extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // ������ ǥ�� �ð� ���� (�и��� ����)
    private static final int FINAL_DISPLAY_TIME = 3900;
    private static final int EXIT_TIME = 6000;

    public TransparentLogo() {
        initUI();
    }

    private void initUI() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));  // JFrame ��� ���� ����
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // CardLayout ����
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);  // cardPanel ��� ���� ����
        this.add(cardPanel);

        // ScaledImage �ν��Ͻ� ���
        ScaledImage si = new ScaledImage();

        // 4���� ���� �̹��� �߰� (200x200)
        JLabel logoLabel1 = new JLabel(si.givemeImage("images/mincat.png", 200, 200));
        logoLabel1.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel1.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel1a = new JLabel(si.givemeImage("images/mincat.png", 200, 200));
        logoLabel1a.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel1a.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel1b = new JLabel(si.givemeImage("images/mincat.png", 200, 200));
        logoLabel1b.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel1b.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel1c = new JLabel(si.givemeImage("images/mincat.png", 200, 200));
        logoLabel1c.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel1c.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel2 = new JLabel(si.givemeImage("images/namblueguy.png", 200, 200));
        logoLabel2.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel2.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel2a = new JLabel(si.givemeImage("images/namblueguy.png", 200, 200));
        logoLabel2a.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel2a.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel2b = new JLabel(si.givemeImage("images/namblueguy.png", 200, 200));
        logoLabel2b.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel2b.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel3 = new JLabel(si.givemeImage("images/jonggithub.png", 200, 200));
        logoLabel3.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel3.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel3a = new JLabel(si.givemeImage("images/jonggithub.png", 200, 200));
        logoLabel3a.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel3a.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        JLabel logoLabel4 = new JLabel(si.givemeImage("images/soomong.png", 200, 200));
        logoLabel4.setOpaque(false);  // JLabel ��� ���� ����
        logoLabel4.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        
        // ū �̹��� (400x300)
        JLabel bigLogoLabel = new JLabel(si.givemeImage("images/logo.png", 500, 350));
        bigLogoLabel.setOpaque(false);  // JLabel ��� ���� ����
        bigLogoLabel.setPreferredSize(new Dimension(500, 350));  // ũ�� ����

        // �� �г� ���� (���� ������ �߰�)
        JPanel panel1 = createPanel(logoLabel1);
        panel1.setOpaque(false);  // �г� ��� ���� ����
        panel1.add(createTransparentPanel());
        panel1.add(createTransparentPanel());

        JPanel panel2 = createPanel(logoLabel1a, logoLabel2);
        panel2.setOpaque(false);  // �г� ��� ���� ����
        panel2.add(createTransparentPanel());

        JPanel panel3 = createPanel(logoLabel1b, logoLabel2a, logoLabel3);
        panel3.setOpaque(false);  // �г� ��� ���� ����
        panel3.add(createTransparentPanel());

        JPanel panel4 = createPanel(logoLabel1c, logoLabel2b, logoLabel3a, logoLabel4);
        panel4.setOpaque(false);  // �г� ��� ���� ����

        JPanel bigLogoPanel = new JPanel(new BorderLayout());
        bigLogoPanel.add(bigLogoLabel, "Center");
        bigLogoPanel.setOpaque(false);  // �г� ��� ���� ����

        // CardLayout�� �߰�
        cardPanel.add(panel1, "1");
        cardPanel.add(panel2, "2");
        cardPanel.add(panel3, "3");
        cardPanel.add(panel4, "4");
        cardPanel.add(bigLogoPanel, "big");

        // �г� ��ȯ Ÿ�̸� ����
        Timer t1 = new Timer(700, e -> cardLayout.show(cardPanel, "2"));
        t1.setRepeats(false);
        t1.start();

        Timer t2 = new Timer(1600, e -> cardLayout.show(cardPanel, "3"));
        t2.setRepeats(false);
        t2.start();

        Timer t3 = new Timer(2700, e -> cardLayout.show(cardPanel, "4"));
        t3.setRepeats(false);
        t3.start();

        Timer t4 = new Timer(FINAL_DISPLAY_TIME, e -> cardLayout.show(cardPanel, "big"));
        t4.setRepeats(false);
        t4.start();

        // �ڵ� ����
        Timer exitTimer = new Timer(EXIT_TIME, e -> this.dispose());
        exitTimer.setRepeats(false);
        exitTimer.start();

        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel createPanel(JComponent... comps) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setOpaque(false);  // �г� ��� ���� ����
        for (JComponent comp : comps) {
            panel.add(comp);
        }
        return panel;
    }

    private JPanel createTransparentPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);  // ��� ���� ����
        emptyPanel.setPreferredSize(new Dimension(200, 200));  // ũ�� ����
        return emptyPanel;
    }
}
