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
    JComboBox<String> favoritesComboBox;  // 콤보박스를 위한 변수 추가
    boolean isComboBoxVisible = false;  // 콤보박스의 표시 여부를 추적

    public NorthMenuBar() {
        super();

        BorderLayout mainLayout = new BorderLayout(10, 10);
        this.setLayout(mainLayout);

        northFirstP = new JPanel();
        FlowLayout northLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
        northFirstP.setLayout(northLayout);
        northFirstP.setBackground(Color.decode("#1879C9"));
        
        // 이미지 스케일링, 안티엘리어싱 클래스
        ScaledImage si = new ScaledImage();
        
        b_backward = new JButton(si.givemeImage("images/backward.png", 30, 30));
        this.l_menu_design(b_backward);
        this.jb_color_change(b_backward);
        
        b_forward = new JButton(si.givemeImage("images/forward.png", 30, 30));
        this.l_menu_design(b_forward);
        this.jb_color_change(b_forward);
        
        b_menufavorites = new JButton("즐겨찾기", si.givemeImage("images/star.png", 20, 20));
        this.l_menu_design(b_menufavorites);
        this.jb_color_change(b_menufavorites);
        b_menufavorites.addActionListener(this);
        
        // 즐겨찾기 버튼 클릭 이벤트 처리
        b_menufavorites.addActionListener(e -> toggleComboBox());

        // 콤보박스를 초기화
        favoritesComboBox = new JComboBox<>(new String[] { "즐겨찾기 1", "즐겨찾기 2", "즐겨찾기 3" });
        favoritesComboBox.setPreferredSize(new Dimension(b_menufavorites.getPreferredSize().width, 30));
        favoritesComboBox.setVisible(false);  // 처음에는 숨김
        
        // 콤보박스 아이템 선택 이벤트 리스너
        favoritesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // 콤보박스에서 아이템이 선택되었을 때, 즐겨찾기 버튼을 다시 보이도록 설정
                    b_menufavorites.setVisible(true);
                    favoritesComboBox.setVisible(false);  // 콤보박스는 숨기기
                    isComboBoxVisible = false;  // 상태 초기화
                }
            }
        });

        // 기존 버튼들 추가
        northFirstP.add(b_backward);
        northFirstP.add(b_forward);
        //northFirstP.add(b_menufavorites);
        
        // 콤보박스를 패널에 추가하지만, 버튼이 위치한 바로 아래로 위치하도록 설정
        //northFirstP.add(favoritesComboBox);  // 콤보박스를 패널에 추가

        this.add(northFirstP, "Center");
        
        b_menuclose = new JButton(si.givemeImage("images/logout.png", 50, 50));
        this.add(b_menuclose, "East");
        b_menuclose.setBackground(Color.decode("#1879C9"));
        this.setBackground(Color.decode("#1879C9"));
    }

    // 즐겨찾기 콤보박스를 토글하는 메서드
    private void toggleComboBox() {
        // 버튼 아래에 콤보박스가 생성되도록 설정
        if (isComboBoxVisible) {
            favoritesComboBox.setVisible(false);
            b_menufavorites.setVisible(true);
        } else {
            // 즐겨찾기 버튼의 위치를 기준으로 콤보박스를 아래에 보이도록 설정
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
        jl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == favoritesComboBox) {
    		favoritesComboBox.getSelectedItem().toString();
    	}
    	
    }
    
}