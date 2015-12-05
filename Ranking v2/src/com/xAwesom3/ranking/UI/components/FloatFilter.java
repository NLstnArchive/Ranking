package com.xAwesom3.ranking.UI.components;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class FloatFilter extends DocumentFilter {

	float currentValue = 0;

	public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

		if (string == null) {
			return;
		}
		else {
			String newValue;
			Document doc = fb.getDocument();
			int length = doc.getLength();
			if (length == 0) {
				newValue = string;
			}
			else {
				String currentContent = doc.getText(0, length);
				StringBuffer currentBuffer = new StringBuffer(currentContent);
				currentBuffer.insert(offset, string);
				newValue = currentBuffer.toString();
			}
			currentValue = checkInput(newValue);
			if (currentValue != 0)
				fb.insertString(offset, newValue, attr);
		}
	}

	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {

		Document doc = fb.getDocument();
		int currentLength = doc.getLength();
		String currentContent = doc.getText(0, currentLength);
		String after = currentContent.substring(length + offset, currentLength);
		String newValue = after;
		currentValue = checkInput(newValue);
		fb.remove(offset, length);
	}

	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

		Document doc = fb.getDocument();
		int currentLength = doc.getLength();
		String currentContent = doc.getText(0, currentLength);
		String before = currentContent.substring(0, offset);
		String newValue = before + (text == null ? "" : text);
		currentValue = checkInput(newValue);
		if (currentValue != 0)
			fb.replace(offset, length, text, attrs);
	}

	private float checkInput(String proposedValue) throws BadLocationException {
		float newValue = 0;
		if (proposedValue.length() > 0) {
			try {
				newValue = Float.parseFloat(validate(proposedValue));
			}
			catch (NumberFormatException e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		return newValue;
	}

	private String validate(String string) {
		char[] chars = string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ',')
				chars[i] = '.';
		}
		return new String(chars);
	}
}
