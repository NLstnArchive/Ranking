package com.xAwesom3.ranking.UI.components;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class FloatTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	public FloatTextField() {
		((AbstractDocument) getDocument()).setDocumentFilter(new FloatFilter());
	}

}
