package com.xAwesom3.ranking.UI.views;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xAwesom3.ranking.UI.Question;
import com.xAwesom3.ranking.UI.View;
import com.xAwesom3.ranking.util.FileHandler;
import com.xAwesom3.ranking.util.XMLHandler;

public class QuestionView extends View {
	private static final long	serialVersionUID	= 1L;

	private List<Question>		questions			= new ArrayList<Question>();
	private int sort;

	public QuestionView(int width, int height, int sort) {
		super(width, height);
		this.sort = sort;
		/*
		 * load questions
		 */

		XMLHandler handler = FileHandler.getXMLFile("questions");
		NodeList questionNodes = handler.getNodeList("question");
		int x = 0;
		int questionWidth = (width - 20) / 2;
		int questionHeight = height / 4;
		for (int i = 0; i < questionNodes.getLength(); i++) {
			Element e = (Element) questionNodes.item(i);
			if (Integer.parseInt(e.getAttribute("sort")) == sort) {
				Question question = new Question(e.getTextContent(), sort);
				questions.add(question);
				question.setBounds(10 + (questionWidth * x), 10 + ((x / 2) * (10 + questionHeight)), questionWidth, questionHeight);
				add(question);
				x = x++ % 2;
			}
		}
		setSize(width, questions.size() * questionHeight);
	}

	public void handleResults() {
		
	}
}
