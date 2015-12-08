package com.xAwesom3.ranking.UI.components;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class BorderedTextField extends JPanel {
	private static final long	serialVersionUID	= 1L;

	private JTextField			field;

	public enum Type {
		FLOAT, TEXT, INT
	}

	public BorderedTextField(int x, int y, int width, int height, Type type) {
		setBounds(x, y, width, height);
		setBorder(LineBorder.createBlackLineBorder());
		setLayout(null);

		if (type == Type.FLOAT)
			field = new FloatTextField();
		if (type == Type.INT)
			field = new IntegerTextField();
		else
			field = new JTextField();
		field.setBounds(1, 1, width - 2, height - 2);
		field.setMargin(new Insets(5, 5, 5, 5));
		add(field);
	}

	public String getText() {
		return field.getText();
	}

	public void setFont(Font font) {
		if (field != null)
			field.setFont(font);
	}

	public void setTextAlignment(int textAlignment) {
		field.setHorizontalAlignment(textAlignment);
	}

	public void setText(String text) {
		field.setText(text);
	}
}
