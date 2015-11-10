package com.xAwesom3.ranking.UI.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class UIQuestion extends UIVoteComponent {
	private static final long	serialVersionUID	= -2704987084694164236L;

	public static int			TYPE_PUPIL, TYPE_TEACHER;

	private JLabel				lblQuestion;

	public UIQuestion(String question, int type) {
		setSize(WIDTH, HEIGHT);

		lblQuestion = new JLabel(question);
		lblQuestion.setBounds(10, 10, WIDTH - 10, (int) (HEIGHT * 0.1f));
		lblQuestion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(lblQuestion);
	}

	public String getAnswer() {
		return null;
	}

	public void setAnswered(boolean answered) {

	}

	public boolean isAnswered() {
		return false;
	}

	protected void resize() {

	}

}
