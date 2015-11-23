package com.xAwesom3.ranking.UI.components;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class BorderedTextArea extends JPanel{
	private static final long	serialVersionUID	= 1L;

	private JTextArea			field;

	public BorderedTextArea(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		setBorder(LineBorder.createBlackLineBorder());
		setLayout(null);

		field = new JTextArea();
		field.setLineWrap(true);
		field.setWrapStyleWord(true);
		field.setMargin(new Insets(5, 5, 5, 5));
		field.setBounds(1, 1, width - 2, height - 2);
		add(field);
	}

	public String getText() {
		return field.getText();
	}

	public void setFont(Font font) {
		if(field != null)
		field.setFont(font);
	}

}
