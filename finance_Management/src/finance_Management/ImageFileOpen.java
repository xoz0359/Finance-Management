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
                // InputStream���� JAR ���� �̹��� �ҷ�����
                InputStream stream = getClass().getClassLoader().getResourceAsStream(resourcePath);

                if (stream != null) {
                    try {
                        Image image = ImageIO.read(stream);

                        // �̹��� ũ�� ���� (��: ���� 400px, ���� 300px)
                        int width = 600;
                        int height = 500;
                        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                        JLabel label = new JLabel(new ImageIcon(scaledImage));

                        // �� â���� �̹��� ǥ��
                        JFrame frame = new JFrame("�̹��� ����");
                        frame.setSize(width + 50, height + 50); // ������ ũ�� ����
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.add(label);
                        frame.setVisible(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("�̹����� ã�� �� �����ϴ�: " + resourcePath);
                }
            }
        });
    }
}
