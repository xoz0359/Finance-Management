package finance_Management;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class ImageFileOpen {

    public ImageFileOpen(JButton jb, String resourcePath) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // InputStream으로 JAR 내부 이미지 불러오기
                InputStream stream = getClass().getClassLoader().getResourceAsStream(resourcePath);

                if (stream != null) {
                    try {
                        Image image = ImageIO.read(stream);

                        // 이미지 크기 조정 (예: 가로 400px, 세로 300px)
                        int width = 600;
                        int height = 500;
                        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                        JLabel label = new JLabel(new ImageIcon(scaledImage));

                        // 새 창에서 이미지 표시
                        JFrame frame = new JFrame("이미지 보기");
                        frame.setSize(width + 50, height + 50); // 프레임 크기 조정
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.add(label);
                        frame.setVisible(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("이미지를 찾을 수 없습니다: " + resourcePath);
                }
            }
        });
    }
}
