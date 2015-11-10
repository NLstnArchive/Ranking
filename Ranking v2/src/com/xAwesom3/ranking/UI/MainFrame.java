package com.xAwesom3.ranking.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xAwesom3.ranking.UI.components.UIQuestion;
import com.xAwesom3.ranking.UI.components.UIView;
import com.xAwesom3.ranking.util.FileHandler;

public class MainFrame {

	public static int		WIDTH, HEIGHT;
	private int				buttonWidth, buttonHeight;

	private JFrame			f;
	private JPanel			contentPane;
	private JButton			btnLeft, btnRight, btnReady;
	private JScrollPane		scrollPane;

	private List<UIView>	views	= new ArrayList<UIView>();

	static {
		DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		WIDTH = (int) (mode.getWidth() * 0.7);
		HEIGHT = (int) (mode.getHeight() * 0.7);
	}

	public MainFrame() {
		f = new JFrame();
		f.setSize(WIDTH, HEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setMinimumSize(new Dimension(800, 600));
		f.setResizable(true);

		contentPane = (JPanel) f.getContentPane();
		contentPane.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				resize();
			}
		});
		contentPane.setLayout(null);

		btnLeft = new JButton("<--");
		contentPane.add(btnLeft);

		btnRight = new JButton("-->");
		contentPane.add(btnRight);

		btnReady = new JButton("Abstimmen");
		contentPane.add(btnReady);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(scrollPane);
		initViews();

		setView(1);
	}

	private void initViews() {
		UIView personalDataView = new UIView();
		UIView questionPupilView = new UIView();
		UIView questionTeacherView = new UIView();
		NodeList questions = FileHandler.getFile("questions").getNodeList("question");
		for (int i = 0; i < questions.getLength(); i++) {
			Element e = (Element) questions.item(i);
			if (e.getAttribute("sort") == "0")
				questionPupilView.add(new UIQuestion(e.getTextContent(), 0));
			if (e.getAttribute("sort") == "1")
				questionTeacherView.add(new UIQuestion(e.getTextContent(), 1));
		}
		views.add(0, personalDataView);
		views.add(1, questionPupilView);
		views.add(2, questionTeacherView);
	}

	public void setView(int view) {
		scrollPane.setViewportView(views.get(view));
	}

	private void resize() {
		WIDTH = contentPane.getWidth();
		HEIGHT = contentPane.getHeight();
		buttonWidth = (int) (WIDTH * 0.12);
		buttonHeight = (int) (HEIGHT * 0.04);
		btnLeft.setBounds(10, 10, buttonWidth, buttonHeight);
		btnRight.setBounds(WIDTH - buttonWidth - 10, 10, buttonWidth, buttonHeight);
		btnReady.setBounds(WIDTH / 2 - buttonWidth / 2, 10, buttonWidth, buttonHeight);
		scrollPane.setBounds(10, buttonHeight + 40, WIDTH - 20, HEIGHT - buttonHeight - 50);
	}

	public void show(String name) {
		f.setTitle("Ranking - " + name);
		resize();
		f.setVisible(true);
	}
}
