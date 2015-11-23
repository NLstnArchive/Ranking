package com.xAwesom3.ranking.UI.views;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.xAwesom3.ranking.UI.components.BorderedTextField;
import com.xAwesom3.ranking.UI.components.xCheckBox;

public class AveragePupilView2 extends AbstractAveragePupilView {
	private static final long	serialVersionUID	= 1L;

	private JLabel				lblMornings, lblAlarmClock, lblTimeInBath, lblBreakfast, lblSchoolWay, lblSchoolWayTime, lblEverything, lblConfession, lblBelieve, lblSchoolMess, lblTV, lblPC, lblMusic, lblHandy, lblGaming, lblBooks, lblSports, lblSleep, lblVote;
	private JLabel				lblMins, lblColon, lblMins2, lblH, lblH2, lblH3, lblH4, lblH5, lblH6, lblH7;
	private JComboBox<String>	alarmClockHBox, alarmClockMinBox, schoolWayBox, confessionBox, schoolMessBox, voteBox;
	private BorderedTextField	txtTimeInBath, txtSchoolWayTime, txtTV, txtPC, txtMusic, txtHandy, txtGaming, txtBooks, txtSports, txtSleep;
	private xCheckBox			checkBreakfast, checkBelieve;

	private static String[]		hourValues			= { "", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
	private static String[]		minuteValues		= { "", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };
	private static String[]		schoolWayValues		= { "", "Bus", "Zug", "Auto", "Fahrrad", "Zu Fuß" };
	private static String[]		confessionValues	= { "", "katholisch", "evangelisch" };
	private static String[]		schoolMessValues	= { "", "immer", "selten", "oft", "nie" };
	private static String[]		voteValues			= { "", "CDU", "SPD", "Linke", "Grüne", "FDP" };

	private int					everythingStartY	= 100 + 2 * (componentHeight) + 10 + 150;

	public AveragePupilView2(int width, int height) {
		super(width, height);

		/*
		 * init components
		 */

		/*
		 * MORNINGS
		 */

		lblMornings = new JLabel("Morgens");
		lblMornings.setFont(headingFont);
		lblMornings.setBounds(10, 10, width - 20, headingFont.getSize() + 10);
		lblMornings.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMornings);

		/*
		 * LEFT
		 */

		lblTimeInBath = new JLabel("Zeit im Bad");
		lblTimeInBath.setFont(lblFont);
		lblTimeInBath.setBounds(leftX, 100, lblWidth, lblHeight);
		add(lblTimeInBath);

		txtTimeInBath = processTextField(lblTimeInBath);
		add(txtTimeInBath);

		lblMins = createUnitLabel("min", txtTimeInBath);
		add(lblMins);

		lblSchoolWayTime = processLabel("Dauer des Schulwegs", leftX, lblTimeInBath);
		add(lblSchoolWayTime);

		txtSchoolWayTime = processTextField(lblSchoolWayTime);
		add(txtSchoolWayTime);

		lblMins2 = createUnitLabel("min", txtSchoolWayTime);
		add(lblMins2);

		/*
		 * MID
		 */

		lblBreakfast = new JLabel("Frühstück?");
		lblBreakfast.setFont(lblFont);
		lblBreakfast.setBounds(midX, 100, lblWidth, lblHeight);
		add(lblBreakfast);

		checkBreakfast = new xCheckBox(getRightSideX(lblBreakfast), centerBoxY(lblBreakfast));
		add(checkBreakfast);

		/*
		 * RIGHT
		 */

		lblAlarmClock = new JLabel("Wecker klingelt um?");
		lblAlarmClock.setFont(lblFont);
		lblAlarmClock.setBounds(rightX, 100, lblWidth, lblHeight);
		add(lblAlarmClock);

		alarmClockHBox = new JComboBox<String>(hourValues);
		alarmClockHBox.setFont(boxFont);
		alarmClockHBox.setBounds(getRightSideX(lblAlarmClock), centerY(lblAlarmClock), componentWidth / 2 - 5, componentHeight);
		add(alarmClockHBox);

		lblColon = new JLabel(":");
		lblColon.setFont(lblFont);
		lblColon.setBounds(alarmClockHBox.getX() + alarmClockHBox.getWidth() + 1, alarmClockHBox.getY() + 5, 10, lblHeight);
		add(lblColon);

		alarmClockMinBox = new JComboBox<String>(minuteValues);
		alarmClockMinBox.setFont(boxFont);
		alarmClockMinBox.setBounds(getRightSideX(alarmClockHBox), centerY(lblAlarmClock), componentWidth / 2 - 5, componentHeight);
		add(alarmClockMinBox);

		lblSchoolWay = processLabel("Schulweg mit?", rightX, lblAlarmClock);
		add(lblSchoolWay);

		schoolWayBox = processComboBox(schoolWayValues, lblSchoolWay);
		add(schoolWayBox);

		/*
		 * GOTT UND DIE WELT
		 */

		lblEverything = new JLabel("Gott und die Welt");
		lblEverything.setFont(headingFont);
		lblEverything.setHorizontalAlignment(0);
		lblEverything.setBounds(10, everythingStartY - 100, width - 20, headingFont.getSize() + 5);
		add(lblEverything);

		/*
		 * LEFT
		 */

		lblTV = new JLabel("TV am Tag");
		lblTV.setFont(lblFont);
		lblTV.setBounds(leftX, everythingStartY, lblWidth, lblHeight);
		add(lblTV);

		txtTV = processTextField(lblTV);
		add(txtTV);

		lblH = createUnitLabel("h", txtTV);
		add(lblH);

		// lblMusic, lblHandy, lblGaming, lblBooks, lblSports, lblSleep

		lblPC = processLabel("PC pro Tag", leftX, lblTV);
		add(lblPC);

		txtPC = processTextField(lblPC);
		add(txtPC);

		lblH2 = createUnitLabel("h", txtPC);
		add(lblH2);

		lblMusic = processLabel("Musik am Tag", leftX, lblPC);
		add(lblMusic);

		txtMusic = processTextField(lblMusic);
		add(txtMusic);

		lblH3 = createUnitLabel("h", txtMusic);
		add(lblH3);

		lblHandy = processLabel("Handy am Tag", leftX, lblMusic);
		add(lblHandy);

		txtHandy = processTextField(lblHandy);
		add(txtHandy);

		lblH4 = createUnitLabel("h", txtHandy);
		add(lblH4);

		lblGaming = processLabel("Computer/Konsole pro Tag", leftX, lblHandy);
		add(lblGaming);

		txtGaming = processTextField(lblGaming);
		add(txtGaming);

		lblH5 = createUnitLabel("h", txtGaming);
		add(lblH5);

		lblBooks = processLabel("Bücher pro Jahr", leftX, lblGaming);
		add(lblBooks);

		txtBooks = processTextField(lblBooks);
		add(txtBooks);

		lblSports = processLabel("Sport pro Woche", leftX, lblBooks);
		add(lblSports);

		txtSports = processTextField(lblSports);
		add(txtSports);

		lblH6 = createUnitLabel("h", txtSports);
		add(lblH6);

		lblSleep = processLabel("Schlaf pro Nacht", leftX, lblSports);
		add(lblSleep);

		txtSleep = processTextField(lblSleep);
		add(txtSleep);

		lblH7 = createUnitLabel("h", txtSleep);
		add(lblH7);

		/*
		 * MID
		 */

		lblBelieve = new JLabel("Glaube an Gott?");
		lblBelieve.setFont(lblFont);
		lblBelieve.setBounds(midX, everythingStartY, lblWidth, lblHeight);
		add(lblBelieve);

		checkBelieve = new xCheckBox(getRightSideX(lblBelieve), centerBoxY(lblBelieve));
		add(checkBelieve);

		/*
		 * RIGHT
		 */

		lblConfession = new JLabel("Konfession:");
		lblConfession.setFont(lblFont);
		lblConfession.setBounds(rightX, everythingStartY, lblWidth, lblHeight);
		add(lblConfession);

		confessionBox = processComboBox(confessionValues, lblConfession);
		add(confessionBox);

		lblSchoolMess = processLabel("Besuch der Schulgottesdienste", rightX, lblConfession);
		lblSchoolMess.setFont(new Font(lblFont.getName(), lblFont.getStyle(), 18));
		add(lblSchoolMess);

		schoolMessBox = processComboBox(schoolMessValues, lblSchoolMess);
		add(schoolMessBox);

		lblVote = processLabel("Wenn am Sonntag Wahl wäre...", rightX, lblSchoolMess);
		lblVote.setFont(new Font(lblFont.getName(), lblFont.getStyle(), 18));
		add(lblVote);

		voteBox = processComboBox(voteValues, lblVote);
		add(voteBox);
	}

	public void handleResults() {
	}
}
