package com.xAwesom3.ranking.UI.views;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.w3c.dom.Element;

import com.xAwesom3.ranking.UI.View;
import com.xAwesom3.ranking.UI.components.Keyword;
import com.xAwesom3.ranking.util.XMLHandler;
import com.xAwesom3.ranking.util.xLogger;

public class KeywordView extends View {
	private static final long	serialVersionUID	= 1L;

	private List<Keyword>		keyWords			= new ArrayList<Keyword>();

	private int					currentX			= 10,
										currentY = 10;

	public KeywordView(int width, int height) {
		super(width - 15, height);
	}

	public List<Element> getResults(XMLHandler handler) {
		xLogger.log("Getting results...");
		List<Element> resultList = new ArrayList<Element>();
		for (Keyword key : keyWords) {
			if (key.getContent() != null)
				resultList.add(handler.createElement(key.getName(), key.getContent()));
			else
				resultList.add(handler.createElement(key.getName(), ""));
		}
		xLogger.log("Finished getting results.");
		return resultList;
	}

	public void addKeyword(String name) {
		int keyWordWidth = width / 2 - 15;
		int keyWordHeight = height / 2;
		Keyword keyWord = new Keyword(name, keyWordWidth, keyWordHeight);
		keyWord.setBounds(currentX, currentY, keyWordWidth, keyWordHeight);
		keyWords.add(keyWord);
		add(keyWord);
		currentX += keyWordWidth + 20;
		if (currentX > width) {
			currentX = 10;
			currentY += keyWordHeight + 10;
		}
		setPreferredSize(new Dimension(width, currentY));
	}

	public void remove(String name) {
		for (Keyword key : keyWords) {
			if (key.getName() == name)
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						keyWords.remove(key);
					}
				});
		}
	}

	public boolean isFilledIn() {
		boolean ready = true;
		for (Keyword keyWord : keyWords) {
			if (!keyWord.isAnswered())
				ready = false;
		}
		return ready;
	}

	public void loadResults(XMLHandler handler) {
		xLogger.log("Starting to load results...");
		for (int i = 0; i < keyWords.size(); i++) {
			String answer = handler.getUniqueElementText(toValidString(keyWords.get(i).getName()));
			xLogger.log("Answer for " + keyWords.get(i).getName() + " = " + answer);
			keyWords.get(i).setContent(answer);
		}

		xLogger.log("Finished loading results for KeywordView");
	}

	private String toValidString(String string) {
		String newString = string;
		if (string != null)
			newString = string.replace(' ', '_');
		xLogger.log("Converted " + string + " to " + newString);
		return newString;
	}
}
