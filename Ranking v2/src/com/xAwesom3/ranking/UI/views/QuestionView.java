package com.xAwesom3.ranking.UI.views;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xAwesom3.ranking.UI.View;
import com.xAwesom3.ranking.UI.components.Question;
import com.xAwesom3.ranking.util.FileHandler;
import com.xAwesom3.ranking.util.XMLHandler;

public class QuestionView extends View {
	private static final long	serialVersionUID	= 1L;

	private List<Question>		questions			= new ArrayList<Question>();
	private int					sort;

	public QuestionView(int width, int height, int sort) {
		super(width, height);
		width = width - 20;
		this.sort = sort;
		/*
		 * load questions
		 */

		XMLHandler handler = FileHandler.getXMLFile("questions");
		NodeList questionNodes = handler.getNodeList("question");
		int questionWidth = (width - 20) / 2;
		int questionHeight = height / 2;
		for (int i = 0; i < questionNodes.getLength(); i++) {
			Element e = (Element) questionNodes.item(i);
			int qSort = Integer.parseInt(e.getAttribute("sort"));
			if (qSort == this.sort) {
				Question q = new Question(e.getTextContent(), Integer.parseInt(e.getAttribute("sort")), questionWidth, questionHeight);
				int x = i % 2;
				int y = i / 2;
				q.setBounds(10 + x * (questionWidth + 10), 10 + y * (questionHeight + 10), questionWidth, questionHeight);
				if (i == questionNodes.getLength() - 1 && i % 2 == 1)
					q.setBounds(width / 2 - questionWidth / 2, 10 + y * (questionHeight + 10), questionWidth, questionHeight);
				questions.add(q);
				add(q);
			}
		}
		setPreferredSize(new Dimension(width, questions.size() / 2 * (questionHeight + 20) + (questions.size() % 2) * (questionHeight + 10)));
	}

	public void handleResults() {

	}
}
