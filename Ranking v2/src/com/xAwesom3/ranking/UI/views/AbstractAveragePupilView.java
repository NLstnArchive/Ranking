package com.xAwesom3.ranking.UI.views;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.xAwesom3.ranking.UI.View;
import com.xAwesom3.ranking.UI.components.BorderedTextField;

public abstract class AbstractAveragePupilView extends View {
	private static final long	serialVersionUID	= 1L;

	protected int				componentHeight		= txtFont.getSize() + 14;
	protected int				leftX				= 10;
	protected int				componentWidth		= 140;
	protected int				checkBoxSize		= 30;
	protected int				lblWidth			= (width) / 3 - 130 - 20;
	protected int				lblHeight			= lblFont.getSize() + 5;
	protected int				checkComboWidth		= lblWidth + 10 + checkBoxSize;
	protected int				midX				= width / 2 - checkComboWidth / 2;
	protected int				rightX				= width - lblWidth - 10 - componentWidth - 10;
	protected int				lblUnitHeight		= lblUnitFont.getSize() + 2;

	public AbstractAveragePupilView(int width, int height) {
		super(width, height);
	}

	protected JLabel processLabel(String text, int x, JLabel above) {
		JLabel l = new JLabel(text);
		l.setFont(lblFont);
		l.setBounds(x, getDownSideY(above), lblWidth, lblHeight);
		return l;
	}

	protected BorderedTextField processTextField(JLabel label) {
		BorderedTextField field = new BorderedTextField(getRightSideX(label), centerY(label), componentWidth, componentHeight);
		field.setFont(txtFont);
		field.setTextAlignment(SwingConstants.RIGHT);
		return field;
	}

	protected JComboBox<String> processComboBox(String[] values, JLabel l) {
		JComboBox<String> comboBox = new JComboBox<String>(values);
		comboBox.setFont(boxFont);
		comboBox.setBounds(getRightSideX(l), centerY(l), componentWidth, componentHeight);
		return comboBox;
	}

	protected JLabel createUnitLabel(String text, BorderedTextField field) {
		JLabel l = new JLabel(text);
		l.setFont(lblUnitFont);
		l.setBounds(getRightSideX(field) - 8, centerY(field), 30, componentHeight);
		return l;
	}

	protected int getRightSideX(JComponent comp) {
		return comp.getX() + comp.getWidth() + 10;
	}

	protected int getDownSideY(JComponent comp) {
		return comp.getY() + comp.getHeight() + 10;
	}

	protected int centerY(JComponent comp) {
		return comp.getY() + comp.getHeight() / 2 - componentHeight / 2;
	}

	protected int centerBoxY(JComponent comp) {
		return comp.getY() + comp.getHeight() / 2 - 15;
	}
}
