package com.xAwesom3.ranking.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.xAwesom3.ranking.UI.views.AveragePupilView;
import com.xAwesom3.ranking.UI.views.AveragePupilView2;
import com.xAwesom3.ranking.UI.views.AveragePupilView3;
import com.xAwesom3.ranking.UI.views.AveragePupilView4;
import com.xAwesom3.ranking.UI.views.PersonalView;
import com.xAwesom3.ranking.UI.views.QuestionView;

public class MainFrame {

	private JFrame		f;

	private JButton		btnLeft, btnReady, btnRight;
	private JScrollPane	scrollPane;

	private List<View>	views	= new ArrayList<View>();
	private int			index;

	public MainFrame() {
		/*
		 * init variables
		 */

		int width = (int) (1920 * 0.75);
		int height = width / 16 * 9;
		int buttonWidth = (int) (width * 0.17);
		int buttonHeight = (int) (buttonWidth * 0.15);

		/*
		 * init frame
		 */

		f = new JFrame();
		f.setSize(width, height);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setResizable(false);

		/*
		 * init components
		 */

		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(133, 135, 140));

		btnLeft = new JButton("<--");
		btnLeft.setBounds(10, 10, buttonWidth, buttonHeight);
		btnLeft.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				index--;
				btnRight.setEnabled(true);
				if (index == 0)
					btnLeft.setEnabled(false);
				setView(index);
			}

		});
		contentPane.add(btnLeft);

		btnRight = new JButton("-->");
		btnRight.setBounds(width - buttonWidth - 15, 10, buttonWidth, buttonHeight);
		btnRight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				index++;
				btnLeft.setEnabled(true);
				if (index == views.size() - 1)
					btnRight.setEnabled(false);
				setView(index);

			}

		});
		contentPane.add(btnRight);

		btnReady = new JButton("Abstimmen");
		btnReady.setBounds(width / 2 - buttonWidth / 2, 10, buttonWidth, buttonHeight);
		btnReady.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}

		});
		contentPane.add(btnReady);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20 + buttonHeight, width - 25, height - buttonHeight - 55);
		scrollPane.setBorder(LineBorder.createBlackLineBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(12);
		contentPane.add(scrollPane);

		/*
		 * post init
		 */

		initViews();
		setView(0);
	}

	private void setView(int view) {
		index = view;
		scrollPane.setViewportView(views.get(view));
		f.revalidate();
	}

	private void initViews() {
		int viewPortWidth = scrollPane.getWidth() - 10;
		int viewPortHeight = scrollPane.getHeight();
		View personalView = new PersonalView(viewPortWidth, viewPortHeight);
		views.add(personalView);
		View averagePupilView = new AveragePupilView(viewPortWidth, viewPortHeight);
		views.add(averagePupilView);
		View averagePupilView2 = new AveragePupilView2(viewPortWidth, viewPortHeight);
		views.add(averagePupilView2);
		View averagePupilView3 = new AveragePupilView3(viewPortWidth, viewPortHeight);
		views.add(averagePupilView3);
		View averagePupilView4 = new AveragePupilView4(viewPortWidth, viewPortHeight);
		views.add(averagePupilView4);
		QuestionView questionView1 = new QuestionView(viewPortWidth, viewPortHeight, 0);
		views.add(questionView1);
		QuestionView questionView2 = new QuestionView(viewPortWidth, viewPortHeight, 1);
		views.add(questionView2);
	}

	public void show(String name) {
		f.setTitle("Ranking - " + name);
		f.setVisible(true);
	}

}
