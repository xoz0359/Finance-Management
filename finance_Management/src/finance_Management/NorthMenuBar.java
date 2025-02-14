package finance_Management;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NorthMenuBar extends JPanel implements ActionListener{

    JPanel northFirstP;
    JButton b_menufavorites, b_forward, b_backward, b_menuclose;
    JComboBox<String> favoritesComboBox;  // �޺��ڽ��� ���� ���� �߰�
    boolean isComboBoxVisible = false;  // �޺��ڽ��� ǥ�� ���θ� ����

    public NorthMenuBar() {
        super();

        BorderLayout mainLayout = new BorderLayout(10, 10);
        this.setLayout(mainLayout);

        northFirstP = new JPanel();
        FlowLayout northLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
        northFirstP.setLayout(northLayout);
        northFirstP.setBackground(Color.decode("#1879C9"));
        
        // �̹��� �����ϸ�, ��Ƽ������� Ŭ����
        ScaledImage si = new ScaledImage();
        
        b_backward = new JButton(si.givemeImage("images/backward.png", 30, 30));
        this.l_menu_design(b_backward);
        this.jb_color_change(b_backward);
        
        b_forward = new JButton(si.givemeImage("images/forward.png", 30, 30));
        this.l_menu_design(b_forward);
        this.jb_color_change(b_forward);
        
        b_menufavorites = new JButton("���ã��", si.givemeImage("images/star.png", 20, 20));
        this.l_menu_design(b_menufavorites);
        this.jb_color_change(b_menufavorites);
        b_menufavorites.addActionListener(this);
        
        // ���ã�� ��ư Ŭ�� �̺�Ʈ ó��
        b_menufavorites.addActionListener(e -> toggleComboBox());

        // �޺��ڽ��� �ʱ�ȭ
        favoritesComboBox = new JComboBox<>(new String[] { "���ã�� 1", "���ã�� 2", "���ã�� 3" });
        favoritesComboBox.setPreferredSize(new Dimension(b_menufavorites.getPreferredSize().width, 30));
        favoritesComboBox.setVisible(false);  // ó������ ����
        
        // �޺��ڽ� ������ ���� �̺�Ʈ ������
        favoritesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // �޺��ڽ����� �������� ���õǾ��� ��, ���ã�� ��ư�� �ٽ� ���̵��� ����
                    b_menufavorites.setVisible(true);
                    favoritesComboBox.setVisible(false);  // �޺��ڽ��� �����
                    isComboBoxVisible = false;  // ���� �ʱ�ȭ
                }
            }
        });

        // ���� ��ư�� �߰�
        northFirstP.add(b_backward);
        northFirstP.add(b_forward);
        //northFirstP.add(b_menufavorites);
        
        // �޺��ڽ��� �гο� �߰�������, ��ư�� ��ġ�� �ٷ� �Ʒ��� ��ġ�ϵ��� ����
        //northFirstP.add(favoritesComboBox);  // �޺��ڽ��� �гο� �߰�

        this.add(northFirstP, "Center");
        
        b_menuclose = new JButton(si.givemeImage("images/logout.png", 50, 50));
        this.add(b_menuclose, "East");
        b_menuclose.setBackground(Color.decode("#1879C9"));
        this.setBackground(Color.decode("#1879C9"));
    }

    // ���ã�� �޺��ڽ��� ����ϴ� �޼���
    private void toggleComboBox() {
        // ��ư �Ʒ��� �޺��ڽ��� �����ǵ��� ����
        if (isComboBoxVisible) {
            favoritesComboBox.setVisible(false);
            b_menufavorites.setVisible(true);
        } else {
            // ���ã�� ��ư�� ��ġ�� �������� �޺��ڽ��� �Ʒ��� ���̵��� ����
            Point buttonLocation = b_menufavorites.getLocationOnScreen();
            favoritesComboBox.setLocation(buttonLocation.x, buttonLocation.y + b_menufavorites.getHeight());
            b_menufavorites.setVisible(false);
            favoritesComboBox.setVisible(true);
        }
        isComboBoxVisible = !isComboBoxVisible;
    }

    public void jb_color_change(JButton jb) {
        jb.setBackground(Color.gray);
        jb.setForeground(Color.white);
    }

    public void l_menu_design(JButton jl) {
        jl.setForeground(Color.white);
        jl.setFont(new Font("���� ���", Font.BOLD, 20));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == favoritesComboBox) {
    		favoritesComboBox.getSelectedItem().toString();
    	}
    	
    }
    
}