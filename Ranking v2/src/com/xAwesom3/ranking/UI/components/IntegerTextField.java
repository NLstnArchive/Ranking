package com.xAwesom3.ranking.UI.components;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class IntegerTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	public IntegerTextField() {
		((AbstractDocument) getDocument()).setDocumentFilter(new IntFilter());
	}
}
