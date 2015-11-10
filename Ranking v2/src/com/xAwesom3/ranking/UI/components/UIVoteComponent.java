package com.xAwesom3.ranking.UI.components;

import javax.swing.JPanel;

public abstract class UIVoteComponent extends JPanel {
	private static final long	serialVersionUID	= 1L;

	public int					width, height;

	public abstract String getAnswer();

	public abstract void setAnswered(boolean answered);

	public abstract boolean isAnswered();

	protected abstract void resize();

	public void resize(int width, int height) {
		this.width = width / 2 - 10;
		this.height = height / 2 - 10;
		setSize(this.width, this.height);
		resize();
	}
}
