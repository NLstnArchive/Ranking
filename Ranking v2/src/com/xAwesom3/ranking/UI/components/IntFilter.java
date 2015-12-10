package com.xAwesom3.ranking.UI.components;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class IntFilter extends DocumentFilter {

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
			boolean valid = checkInput(newValue);
			if(valid)
			fb.insertString(offset, newValue, attr);
		}
	}

	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
		fb.remove(offset, length);
	}

	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

		Document doc = fb.getDocument();
		int currentLength = doc.getLength();
		String currentContent = doc.getText(0, currentLength);
		String before = currentContent.substring(0, offset);
		String newValue = before + (text == null ? "" : text);
		boolean valid = checkInput(newValue);
		if(valid)
		fb.replace(offset, length, text, attrs);
	}

	private boolean checkInput(String proposedValue) throws BadLocationException {
		if (proposedValue.length() > 0) {
			try {
				Integer.parseInt(proposedValue);
			}
			catch (NumberFormatException e) {
				Toolkit.getDefaultToolkit().beep();
				return false;
			}
			return true;
		}
		return false;
	}
}
