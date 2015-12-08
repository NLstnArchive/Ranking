package com.xAwesom3.ranking.UI.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Keyword extends JPanel {
	private static final long	serialVersionUID	= 1L;

	private String				name;

	private boolean				answered			= false;

	private Color				bg_false			= new Color(255, 169, 69);
	private Color				bg_true				= new Color(40, 242, 50);
	private Color				txt_false			= new Color(255, 122, 26);
	private Color				txt_true			= new Color(22, 168, 30);

	protected Font				font				= new Font("Ludicida Sans Unicode", Font.BOLD, 16);

	private JLabel				label;
	private BorderedTextArea	textField;

	public Keyword(String name, int width, int height) {
		this.name = name;
		setLayout(null);
		setBackground(bg_false);
		setBorder(LineBorder.createBlackLineBorder());

		label = new JLabel(name);
		label.setHorizontalAlignment(JTextField.CENTER);
		label.setFont(font);
		label.setBounds(10, 10, width - 20, font.getSize() + 5);
		add(label);

		textField = new BorderedTextArea(10, label.getY() + label.getHeight() + 10, width - 20, height - 10 - (label.getY() + label.getHeight() + 10));
		textField.setFont(new Font("Ludicida Sans Unicode", Font.BOLD, 14));
		textField.setBackground(txt_false);
		textField.setSelectedTextColor(txt_false.darker());
		textField.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				setAnswered(!(textField.getText().equals("")));
			}

			public void removeUpdate(DocumentEvent e) {
				setAnswered(!(textField.getText().equals("")));
			}

			public void changedUpdate(DocumentEvent e) {
				setAnswered(!(textField.getText().equals("")));
			}

		});
		add(textField);
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
		if (answered) {
			textField.setBackground(txt_true);
			setBackground(bg_true);
		}
		else {
			textField.setBackground(txt_false);
			setBackground(bg_false);
		}
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		if (textField.getText() == null)
			return "";
		else
			return textField.getText();
	}

	public void setContent(String content) {
		textField.setText(content);
	}

	public boolean isAnswered() {
		return answered;
	}
}
