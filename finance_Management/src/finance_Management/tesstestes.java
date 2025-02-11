   public CodeUserRegistration2() {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        p_center = new JPanel();
        p_center.setLayout(new BoxLayout(p_center, BoxLayout.Y_AXIS));

        // ��ư ����
        btn_userRegi = new JButton("����� ���");
        btn_codeRegi = new JButton("�����ڵ� ���");
        btn_deptRegi = new JButton("�μ� ���");
        btn_adminMode = new JButton(); // ������ ��� ��ư (���� ���� ����)

        btn_userRegi.setFont(new Font("���� ���", Font.BOLD, 12));
        btn_codeRegi.setFont(new Font("���� ���", Font.BOLD, 12));
        btn_deptRegi.setFont(new Font("���� ���", Font.BOLD, 12));
        btn_adminMode.setFont(new Font("���� ���", Font.BOLD, 12));

        // ��ư ũ�� ����
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

        // ������ ��� ��ư ���ο� üũ�ڽ� ��ġ
        cb_adminMode = new JCheckBox();
        cb_adminMode.setPreferredSize(new Dimension(20, 20));
        cb_adminMode.setOpaque(false); // üũ�ڽ� ��� ����ȭ

        JPanel adminContent = new JPanel(new BorderLayout());
        adminContent.setOpaque(false);
        adminContent.add(new JLabel("������ ���", JLabel.CENTER), BorderLayout.CENTER);
        adminContent.add(cb_adminMode, BorderLayout.EAST);

        btn_adminMode.setLayout(new BorderLayout());
        btn_adminMode.add(adminContent, BorderLayout.CENTER);

        // �гο� ��� �߰�
        add(Box.createVerticalStrut(30));
        add(btn_userRegi);
        add(Box.createVerticalStrut(15));
        add(btn_codeRegi);
        add(Box.createVerticalStrut(15));
        add(btn_deptRegi);
        add(Box.createVerticalStrut(15));
        add(btn_adminMode);
        add(Box.createVerticalStrut(30));

        // ��ư Ŭ�� �� üũ�ڽ� ��� ��� �߰�
        btn_adminMode.addActionListener(e -> cb_adminMode.setSelected(!cb_adminMode.isSelected()));
    }