package finance_Management;

import javax.swing.*;
import java.awt.*;

public class TransparentLogo extends JFrame {
    public TransparentLogo() {
        // UI �ʱ�ȭ �޼��� ����
        initUI();
    }

    private void initUI() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setAlwaysOnTop(true);
        this.setLayout(new GridLayout(2, 2, 0, 0)); 

        // ScaledImage �ν��Ͻ� ���
        ScaledImage si = new ScaledImage();

        // 4���� ���� �̹��� �߰� (200x200)
        JLabel logoLabel1 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel2 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel3 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel4 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));

        // ū �̹��� (800x800)
        JLabel bigLogoLabel = new JLabel(si.givemeImage("images//logo.png", 400, 300));

        // �ʱ⿡�� �� �г��� ��ġ (���� Ÿ�̸ӷ� ����)
        JLabel empty1 = new JLabel();
        JLabel empty2 = new JLabel();
        JLabel empty3 = new JLabel();
        JLabel empty4 = new JLabel();

		this.add(empty1);
		this.add(empty2);
		this.add(empty3);
		this.add(empty4);

		// 1�� �������� �̹��� �߰�

		this.remove(empty1);
		this.add(logoLabel1, 0);
		this.revalidate();
		this.repaint();

        new Timer(800, e -> {
            this.remove(empty2);
            this.add(logoLabel2, 1);
            this.revalidate();
            this.repaint();
        }).start();

        new Timer(1700, e -> {
            this.remove(empty3);
            this.add(logoLabel3, 2);
            this.revalidate();
            this.repaint();
        }).start();

        new Timer(2700, e -> {
            this.remove(empty4);
            this.add(logoLabel4, 3);
            this.revalidate();
            this.repaint();
        }).start();

        // 4�� �� ��� �̹����� �����ϰ� ū �̹��� ǥ��
        new Timer(3800, e -> {
            this.getContentPane().removeAll(); 
            this.setLayout(new BorderLayout()); 
            this.add(bigLogoLabel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }).start();

        // 7�� �� �ڵ� ����
        new Timer(7000, e -> this.dispose()).start();

        this.pack(); // ũ�� ����
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TransparentLogo::new); // GUI ����
    }
}
