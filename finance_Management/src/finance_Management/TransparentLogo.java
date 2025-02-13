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
        
        // ScaledImage 인스턴스 사용
        ScaledImage si = new ScaledImage();

        // 4개의 작은 이미지 추가 (200x200)
        JLabel logoLabel1 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel2 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel3 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel4 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));

        // 큰 이미지 (400x300)
        JLabel bigLogoLabel = new JLabel(si.givemeImage("images//logo.png", 400, 300));

        // 배경 투명화
        logoLabel1.setOpaque(false);
        logoLabel2.setOpaque(false);
        logoLabel3.setOpaque(false);
        logoLabel4.setOpaque(false);
        bigLogoLabel.setOpaque(false);

        // 초기에는 빈 패널을 배치
        JLabel empty1 = new JLabel();
        JLabel empty2 = new JLabel();
        JLabel empty3 = new JLabel();
        JLabel empty4 = new JLabel();

        this.add(empty1);
        this.add(empty2);
        this.add(empty3);
        this.add(empty4);

        // 1초 간격으로 이미지 추가
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

        // 4초 후 모든 이미지를 제거하고 큰 이미지 표시 (SwingUtilities.invokeLater 사용)
        new Timer(3800, e -> {
            SwingUtilities.invokeLater(() -> {
                this.getContentPane().removeAll(); 
                this.setLayout(new BorderLayout()); 
                this.add(bigLogoLabel, BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            });
        }).start();

        // 7초 후 자동 종료
        new Timer(7000, e -> this.dispose()).start();

        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
