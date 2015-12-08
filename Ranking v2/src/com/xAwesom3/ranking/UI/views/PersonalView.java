package com.xAwesom3.ranking.UI.views;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.w3c.dom.Element;

import com.xAwesom3.ranking.Human;
import com.xAwesom3.ranking.UI.View;
import com.xAwesom3.ranking.UI.components.BorderedTextArea;
import com.xAwesom3.ranking.util.XMLHandler;
import com.xAwesom3.ranking.util.xLogger;

public class PersonalView extends View {
	private static final long	serialVersionUID	= 1L;

	private Font				lblFlagFont			= new Font(lblFont.getFontName(), lblFont.getStyle(), lblFont.getSize() - 6);

	private JLabel				lblBirthday, lblDay, lblMonth, lblYear, lblLeistungsKurse, lblFavoriteTeacher, lblFuture, lblThank;
	private JComboBox<String>	dayBox, monthBox, yearBox, lk1Box, lk2Box, lk3Box, favoriteTeacherBox;
	private BorderedTextArea	txtFuture, txtThank;

	private String[]			days				= { "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String[]			months				= { "", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember" };
	private String[]			years				= { "", "1998", "1997", "1996", "1995" };
	private String[]			classes				= { "", "Deutsch", "Mathematik", "Physik", "Englisch", "Sozialkunde", "Erdkunde", "Geschichte", "Latein", "Französisch", "Religion" };

	public PersonalView(int width, int height) {
		super(width, height);

		/*
		 * init variables
		 */

		int boxWidth = 90;
		int boxHeight = 30;

		/*
		 * init components
		 */
		lblBirthday = new JLabel("Geburtstag: ");
		lblBirthday.setFont(lblFont);
		lblBirthday.setBounds(180, 30, 200, lblFont.getSize() + 5);
		add(lblBirthday);

		dayBox = new JComboBox<String>(days);
		dayBox.setBounds(lblBirthday.getX() + lblBirthday.getWidth() + 30, lblBirthday.getY() + lblBirthday.getHeight() / 2 - boxHeight / 2, boxWidth - 40, boxHeight);
		add(dayBox);

		lblDay = new JLabel("Tag:");
		lblDay.setFont(lblFlagFont);
		lblDay.setBounds(dayBox.getX() + dayBox.getWidth() / 2 - 50 / 2, 5, 50, lblDay.getFont().getSize() + 5);
		add(lblDay);

		monthBox = new JComboBox<String>(months);
		monthBox.setBounds(dayBox.getX() + dayBox.getWidth() + 10, lblBirthday.getY() + lblBirthday.getHeight() / 2 - boxHeight / 2, boxWidth, boxHeight);
		add(monthBox);

		lblMonth = new JLabel("Monat:");
		lblMonth.setFont(lblFlagFont);
		lblMonth.setBounds(monthBox.getX() + monthBox.getWidth() / 2 - 74 / 2, 5, 74, lblMonth.getFont().getSize() + 5);
		add(lblMonth);

		yearBox = new JComboBox<String>(years);
		yearBox.setBounds(dayBox.getX() + dayBox.getWidth() + monthBox.getWidth() + 20, lblBirthday.getY() + lblBirthday.getHeight() / 2 - boxHeight / 2, boxWidth - 30, boxHeight);
		add(yearBox);

		lblYear = new JLabel("Jahr:");
		lblYear.setFont(lblFlagFont);
		lblYear.setBounds(yearBox.getX() + yearBox.getWidth() / 2 - 65 / 2, 5, 65, lblYear.getFont().getSize() + 5);
		add(lblYear);

		lblLeistungsKurse = new JLabel("Leistungskurse:");
		lblLeistungsKurse.setFont(lblFont);
		lblLeistungsKurse.setBounds(yearBox.getX() + yearBox.getWidth() + 50, 30, 230, lblFont.getSize() + 5);
		add(lblLeistungsKurse);

		lk1Box = new JComboBox<String>(classes);
		lk1Box.setBounds(lblLeistungsKurse.getX() + lblLeistungsKurse.getWidth() + 15, lblLeistungsKurse.getY() + lblLeistungsKurse.getHeight() / 2 - boxHeight / 2, boxWidth, boxHeight);
		add(lk1Box);

		lk2Box = new JComboBox<String>(classes);
		lk2Box.setBounds(lk1Box.getX() + lk1Box.getWidth() + 15, lk1Box.getY(), boxWidth, boxHeight);
		add(lk2Box);

		lk3Box = new JComboBox<String>(classes);
		lk3Box.setBounds(lk2Box.getX() + lk2Box.getWidth() + 15, lk2Box.getY(), boxWidth, boxHeight);
		add(lk3Box);

		lblFavoriteTeacher = new JLabel("Lieblingslehrer:");
		lblFavoriteTeacher.setFont(lblFont);
		lblFavoriteTeacher.setBounds(width / 2 - 200, 80, 230, lblFont.getSize() + 5);
		add(lblFavoriteTeacher);

		String[] teachers = new String[Human.getMenTeachers().size() + Human.getWomenTeachers().size()];
		for (int i = 0; i < Human.getMenTeachers().size(); i++) {
			teachers[i] = Human.getMenTeachers().get(i).getName();
		}
		for (int i = 0; i < Human.getWomenTeachers().size(); i++) {
			teachers[i + Human.getMenTeachers().size()] = Human.getWomenTeachers().get(i).getName();
		}
		favoriteTeacherBox = new JComboBox<String>(teachers);
		favoriteTeacherBox.setBounds(lblFavoriteTeacher.getX() + lblFavoriteTeacher.getWidth() + 30, lblFavoriteTeacher.getY() + lblFavoriteTeacher.getHeight() / 2 - boxHeight / 2, boxWidth + 30, boxHeight);
		add(favoriteTeacherBox);

		lblFuture = new JLabel("Das will ich nach dem Abi machen:");
		lblFuture.setFont(lblFont);
		lblFuture.setBounds(50 / 2, 140, width - 50, lblFont.getSize() + 5);
		lblFuture.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFuture);

		txtFuture = new BorderedTextArea(50 / 2, lblFuture.getY() + lblFuture.getHeight() + 10, width - 50, 230);
		txtFuture.setFont(txtFont);
		add(txtFuture);

		lblThank = new JLabel("Diesen Menschen möchte ich danken!");
		lblThank.setFont(lblFont);
		lblThank.setHorizontalAlignment(SwingConstants.CENTER);
		lblThank.setBounds(10, txtFuture.getY() + txtFuture.getHeight() + 20, width - 20, lblFont.getSize() + 5);
		add(lblThank);

		txtThank = new BorderedTextArea(50 / 2, lblThank.getY() + lblThank.getHeight() + 10, width - 50, 230);
		txtThank.setFont(txtFont);
		add(txtThank);

	}

	// private JComboBox<String> dayBox, monthBox, yearBox, lk1Box, lk2Box, lk3Box, favoriteTeacherBox;
	// private BorderedTextArea txtFuture, txtThank;

	public List<Element> getResults(XMLHandler handler) {
		List<Element> resultList = new ArrayList<Element>();

		resultList.add(handler.createElement("dayBox", (String) dayBox.getSelectedItem()));
		resultList.add(handler.createElement("monthBox", (String) monthBox.getSelectedItem()));
		resultList.add(handler.createElement("yearBox", (String) yearBox.getSelectedItem()));
		resultList.add(handler.createElement("lk1Box", (String) lk1Box.getSelectedItem()));
		resultList.add(handler.createElement("lk2Box", (String) lk2Box.getSelectedItem()));
		resultList.add(handler.createElement("lk3Box", (String) lk3Box.getSelectedItem()));
		resultList.add(handler.createElement("favoriteTeacherBox", (String) favoriteTeacherBox.getSelectedItem()));

		//TODO: doesnt work
		resultList.add(handler.createElement("txtFuture", txtFuture.getText()));
		resultList.add(handler.createElement("txtThank", txtThank.getText()));

		return resultList;
	}

	public boolean isFilledIn() {
		return dayBox.getSelectedIndex() != 0 && monthBox.getSelectedIndex() != 0 && yearBox.getSelectedIndex() != 0;
	}

	public void loadResults(XMLHandler handler) {
		txtFuture.setText(handler.getUniqueElementText("txtFuture"));
		txtThank.setText(handler.getUniqueElementText("txtThank"));
		
		dayBox.setSelectedItem(handler.getUniqueElementText("dayBox"));
		monthBox.setSelectedItem(handler.getUniqueElementText("monthBox"));
		yearBox.setSelectedItem(handler.getUniqueElementText("yearBox"));
		lk1Box.setSelectedItem(handler.getUniqueElementText("lk1Box"));
		lk2Box.setSelectedItem(handler.getUniqueElementText("lk2Box"));
		lk3Box.setSelectedItem(handler.getUniqueElementText("lk3Box"));
		
		favoriteTeacherBox.setSelectedItem(handler.getUniqueElementText("favoriteTeacherBox"));
		
		xLogger.log("Finished loading results for PersonalView");
	}

}