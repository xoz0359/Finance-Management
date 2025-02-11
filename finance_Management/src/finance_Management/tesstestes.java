   public CodeUserRegistration2() {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        p_center = new JPanel();
        p_center.setLayout(new BoxLayout(p_center, BoxLayout.Y_AXIS));

        // 버튼 생성
        btn_userRegi = new JButton("사용자 등록");
        btn_codeRegi = new JButton("계정코드 등록");
        btn_deptRegi = new JButton("부서 등록");
        btn_adminMode = new JButton(); // 관리자 모드 버튼 (내용 따로 설정)

        btn_userRegi.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        btn_codeRegi.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        btn_deptRegi.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        btn_adminMode.setFont(new Font("맑은 고딕", Font.BOLD, 12));

        // 버튼 크기 설정
        Dimension buttonSize = new Dimension(300, 50);
        btn_userRegi.setPreferredSize(buttonSize);
        btn_codeRegi.setPreferredSize(buttonSize);
        btn_deptRegi.setPreferredSize(buttonSize);
        btn_adminMode.setPreferredSize(buttonSize);

        btn_userRegi.setMaximumSize(buttonSize);
        btn_codeRegi.setMaximumSize(buttonSize);
        btn_deptRegi.setMaximumSize(buttonSize);
        btn_adminMode.setMaximumSize(buttonSize);

        btn_userRegi.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_codeRegi.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_deptRegi.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_adminMode.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 관리자 모드 버튼 내부에 체크박스 배치
        cb_adminMode = new JCheckBox();
        cb_adminMode.setPreferredSize(new Dimension(20, 20));
        cb_adminMode.setOpaque(false); // 체크박스 배경 투명화

        JPanel adminContent = new JPanel(new BorderLayout());
        adminContent.setOpaque(false);
        adminContent.add(new JLabel("관리자 모드", JLabel.CENTER), BorderLayout.CENTER);
        adminContent.add(cb_adminMode, BorderLayout.EAST);

        btn_adminMode.setLayout(new BorderLayout());
        btn_adminMode.add(adminContent, BorderLayout.CENTER);

        // 패널에 요소 추가
        add(Box.createVerticalStrut(30));
        add(btn_userRegi);
        add(Box.createVerticalStrut(15));
        add(btn_codeRegi);
        add(Box.createVerticalStrut(15));
        add(btn_deptRegi);
        add(Box.createVerticalStrut(15));
        add(btn_adminMode);
        add(Box.createVerticalStrut(30));

        // 버튼 클릭 시 체크박스 토글 기능 추가
        btn_adminMode.addActionListener(e -> cb_adminMode.setSelected(!cb_adminMode.isSelected()));
    }