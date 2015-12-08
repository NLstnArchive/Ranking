package com.xAwesom3.ranking.UI;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;

import org.w3c.dom.Element;

import com.xAwesom3.ranking.util.XMLHandler;

public abstract class View extends JPanel {
	private static final long	serialVersionUID	= 1L;

	protected int				width, height;
	protected static Font		txtFont;
	protected static Font		lblFont;
	protected static Font		boxFont;
	protected static Font		headingFont;
	protected static Font		lblUnitFont;

	static {
		lblFont = new Font("Arial Black", Font.PLAIN, 23);
		txtFont = new Font("Comic Sans MS", Font.BOLD, 22);
		boxFont = new Font(txtFont.getFontName(), txtFont.getStyle(), 23);
		headingFont = new Font(lblFont.getFontName(), txtFont.getStyle(), txtFont.getSize() + 20);
		lblUnitFont = new Font(lblFont.getFontName(), lblFont.getStyle(), 15);
	}

	public View(int width, int height) {
		this.width = width;
		this.height = height;
		setBackground(new Color(235, 198, 140));
		setLayout(null);
	}

	public abstract List<Element> getResults(XMLHandler handler);

	public abstract boolean isFilledIn();

	public abstract void loadResults(XMLHandler handler);
}
