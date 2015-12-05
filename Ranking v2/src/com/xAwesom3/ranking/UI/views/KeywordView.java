package com.xAwesom3.ranking.UI.views;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.xAwesom3.ranking.UI.View;
import com.xAwesom3.ranking.UI.components.Keyword;
import com.xAwesom3.ranking.util.XMLHandler;

public class KeywordView extends View {
	private static final long	serialVersionUID	= 1L;

	private List<Keyword>		keyWords			= new ArrayList<Keyword>();

	private int					currentX			= 10,
										currentY = 10;

	public KeywordView(int width, int height) {
		super(width - 15, height);
	}

	public List<Element> getResults(XMLHandler handler) {
		List<Element> resultList = new ArrayList<Element>();
		for (int i = 0; i < keyWords.size(); i++) {
			resultList.add(handler.createElement(keyWords.get(i).getName(), keyWords.get(i).getContent()));
		}
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

	public boolean isFilledIn() {
		boolean ready = true;
		for (Keyword keyWord : keyWords) {
			if (!keyWord.isAnswered())
				ready = false;
		}
		return ready;
	}

}
