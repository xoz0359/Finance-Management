package finance_Management;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageFileOpen {
	
	public ImageFileOpen(JButton jb,String path) {
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File imageFile = new File(path);
				try {
					Desktop.getDesktop().open(imageFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

}
