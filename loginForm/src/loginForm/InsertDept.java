package loginForm;

import java.awt.*;
import javax.swing.*;

public class InsertDept extends JFrame{
	GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
	public InsertDept() {	
		setTitle("부서 입력");
		
		JPanel pWindow = new JPanel(new GridLayout(1,3));
		
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp1.setBorder(BorderFactory.createEmptyBorder(70 , 0 , 0 , 0));
		JLabel lDeptName = new JLabel("부서명  : ");
		jp1.add(lDeptName);
		
		
		JPanel jp2 = new JPanel();
		JPanel jp21 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp21.setBorder(BorderFactory.createEmptyBorder(64 , 0 , 0 , 30));
		JTextField jDeptName = new JTextField(11);	
		JPanel jp22 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp22.setBorder(BorderFactory.createEmptyBorder(0 , 100 , 0 , 58));
		JButton save = new JButton("저장");
		save.setMargin(new Insets(0, 10, 0, 10));
		jp21.add(jDeptName);
		jp22.add(save);
		
		jp2.add(jp21);
		jp2.add(jp22);
		
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp3.setBorder(BorderFactory.createEmptyBorder(106 , 0 , 0 , 100));
		JButton cancle = new JButton("닫기");
		cancle.setMargin(new Insets(0, 10, 0, 10));
		jp3.add(cancle);
		
		
		
		pWindow.add(jp1);
		pWindow.add(jp2);
		pWindow.add(jp3);
		this.add(pWindow);
		
		setBounds(1000, 200, 500, 300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		InsertDept name = new InsertDept();
	}
}
