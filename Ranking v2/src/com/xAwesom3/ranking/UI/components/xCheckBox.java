package com.xAwesom3.ranking.UI.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.xAwesom3.ranking.util.FileHandler;

public class xCheckBox extends JComponent {
	private static final long		serialVersionUID	= 1L;

	private static BufferedImage	img_checked, img_unchecked;

	private boolean					checked				= false;

	static {
		try {
			img_checked = ImageIO.read(new File(FileHandler.getPath() + "images/checkBox-checked.png"));
			img_unchecked = ImageIO.read(new File(FileHandler.getPath() + "images/checkBox-unchecked.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public xCheckBox(int x, int y) {
		setBounds(x, y, 30, 30);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checked = !checked;
				repaint();
			}
		});
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		if (checked)
			g.drawImage(img_checked, 0, 0, getWidth(), getHeight(), null);
		else
			g.drawImage(img_unchecked, 0, 0, getWidth(), getHeight(), null);
	}

}
