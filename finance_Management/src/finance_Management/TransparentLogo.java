package finance_Management;

import javax.swing.*;
import java.awt.*;

public class TransparentLogo extends JFrame {
    public TransparentLogo() {
        initUI();
    }

    private void initUI() { 
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setAlwaysOnTop(true);
        this.setLayout(new GridLayout(2, 2, 0, 0)); 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // ScaledImage �ν��Ͻ� ���
        ScaledImage si = new ScaledImage();

        // 4���� ���� �̹��� �߰� (200x200)
        JLabel logoLabel1 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel2 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel3 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel4 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));

        // ū �̹��� (400x300)
        JLabel bigLogoLabel = new JLabel(si.givemeImage("images//logo.png", 400, 300));

        // ��� ����ȭ
        logoLabel1.setOpaque(false);
        logoLabel2.setOpaque(false);
        logoLabel3.setOpaque(false);
        logoLabel4.setOpaque(false);
        bigLogoLabel.setOpaque(false);

        // �ʱ⿡�� �� �г��� ��ġ
        JLabel empty1 = new JLabel();
        JLabel empty2 = new JLabel();
        JLabel empty3 = new JLabel();
        JLabel empty4 = new JLabel();

        this.add(empty1);
        this.add(empty2);
        this.add(empty3);
        this.add(empty4);

        // 1�� �������� �̹��� �߰�
        new Timer(100, e -> {
            this.remove(empty4);
            this.add(logoLabel1);
            this.revalidate();
            this.repaint();
        }).start();

        new Timer(800, e -> {
            this.remove(empty3);
            this.add(logoLabel2);
            this.revalidate();
            this.repaint();
        }).start();

        new Timer(1700, e -> {
            this.remove(empty2);
            this.add(logoLabel3);
            this.revalidate();
            this.repaint();
        }).start();

        new Timer(2700, e -> {
            this.remove(empty1);
            this.add(logoLabel4);
            this.revalidate();
            this.repaint();
        }).start();

        // 4�� �� ��� �̹����� �����ϰ� ū �̹��� ǥ�� (SwingUtilities.invokeLater ���)
        new Timer(3800, e -> {
            SwingUtilities.invokeLater(() -> {
                this.getContentPane().removeAll(); 
                this.setLayout(new BorderLayout()); 
                this.add(bigLogoLabel, BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            });
        }).start();

        // 7�� �� �ڵ� ����
        new Timer(7000, e -> this.dispose()).start();

        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
