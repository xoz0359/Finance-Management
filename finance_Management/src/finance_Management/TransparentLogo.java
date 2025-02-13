package finance_Management;

import javax.swing.*;
import java.awt.*;

public class TransparentLogo extends JFrame {
    public TransparentLogo() {
        // UI 초기화 메서드 실행
        initUI();
    }

    private void initUI() {
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setAlwaysOnTop(true);
        this.setLayout(new GridLayout(2, 2, 0, 0)); 

        // ScaledImage 인스턴스 사용
        ScaledImage si = new ScaledImage();

        // 4개의 작은 이미지 추가 (200x200)
        JLabel logoLabel1 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel2 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel3 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));
        JLabel logoLabel4 = new JLabel(si.givemeImage("images//mincat.png", 200, 200));

        // 큰 이미지 (800x800)
        JLabel bigLogoLabel = new JLabel(si.givemeImage("images//logo.png", 400, 300));

        // 초기에는 빈 패널을 배치 (이후 타이머로 변경)
        JLabel empty1 = new JLabel();
        JLabel empty2 = new JLabel();
        JLabel empty3 = new JLabel();
        JLabel empty4 = new JLabel();

		this.add(empty1);
		this.add(empty2);
		this.add(empty3);
		this.add(empty4);

		// 1초 간격으로 이미지 추가

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

        // 4초 후 모든 이미지를 제거하고 큰 이미지 표시
        new Timer(3800, e -> {
            this.getContentPane().removeAll(); 
            this.setLayout(new BorderLayout()); 
            this.add(bigLogoLabel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }).start();

        // 7초 후 자동 종료
        new Timer(7000, e -> this.dispose()).start();

        this.pack(); // 크기 조정
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TransparentLogo::new); // GUI 실행
    }
}
