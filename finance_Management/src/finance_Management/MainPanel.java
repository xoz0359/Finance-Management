package finance_Management;

import javax.swing.JPanel;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPanel extends JPanel{
	
	JPanel jp_outline,jp_organization, jp_serviceList,jp_etc;
	JLabel jl_outline_title,jl_outline_content;
	JButton jb_outline, jb_organization, jb_serviceList,jb_onlinecenter,jb_info,jb_mail;
	
	public MainPanel() {
		this.setLayout(new GridLayout(2,2,10,10));
		jp_outline = new JPanel(new BorderLayout());
		jp_organization = new JPanel(new BorderLayout());
		jp_serviceList = new JPanel(new BorderLayout());
		jp_etc = new JPanel(new GridLayout(2,1,10,10));
		
		ImageIconAdd icon_outline = new ImageIconAdd("images\\outline_icon.png", 120, 120);
		ImageIconAdd icon_organization = new ImageIconAdd("images\\organization_icon.png", 120, 120);
		ImageIconAdd icon_servicelist = new ImageIconAdd("images\\ser_list_icon.png", 120, 120);
		ImageIconAdd icon_service_center = new ImageIconAdd("images\\service_center.png", 90, 90);
		ImageIconAdd icon_email = new ImageIconAdd("images\\email.png", 90, 90);
		
		
		jb_outline = new JButton("사업 개요",icon_outline.icon);this.jb_design(jb_outline);
		jb_organization = new JButton("사업 조직도",icon_organization.icon);this.jb_design(jb_organization);
		jb_serviceList = new JButton("서비스 현황",icon_servicelist.icon);this.jb_design(jb_serviceList);
		//사업 개요란
		this.add(jb_outline);
		this.add(jp_etc);
		this.add(jb_organization);
		this.add(jb_serviceList);
		
		
		this.setBackground(Color.white);
		jp_etc.setBackground(Color.white);
		
		jb_onlinecenter = new JButton("온라인 고객센터",icon_service_center.icon);this.jb_design(jb_onlinecenter);
//		jb_info
		jb_mail = new JButton("이메일 주소",icon_email.icon);this.jb_design(jb_mail);
		jp_etc.add(jb_onlinecenter);
		jp_etc.add(jb_mail);

		ImageFileOpen ifo_outline = new ImageFileOpen(jb_outline, "images\\outline.png");
		ImageFileOpen ifo_organization = new ImageFileOpen(jb_organization, "images\\organization.png");
		ImageFileOpen ifo_servicelist = new ImageFileOpen(jb_serviceList, "images\\servicelist.png");
//		ImageFileOpen ifo_service_center = new ImageFileOpen(jb_outline, "");
		jb_onlinecenter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://localhost:8080/"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
//		ImageFileOpen ifo_email = new ImageFileOpen(jb_outline, "");
		jb_mail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String textToCopy = "fourTeam@gmail.com";
				StringSelection selection = new StringSelection(textToCopy);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selection, null);
			}
		});
	}
	
	@Override
	public Insets getInsets() {
		
		return new Insets(10, 10, 10, 10);
	}
	public void jb_design(JButton jb) {
		jb.setFont(new Font("맑은 고딕",Font.BOLD,30));
		jb.setForeground(Color.white);
		jb.setBackground(Color.decode("#A9C1E7"));
		jb.setVerticalTextPosition(JButton.TOP);
		jb.setHorizontalTextPosition(JButton.CENTER);
	}

}
