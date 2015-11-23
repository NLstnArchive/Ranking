package com.xAwesom3.ranking.UI.views;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.xAwesom3.ranking.UI.components.BorderedTextField;
import com.xAwesom3.ranking.UI.components.xCheckBox;

public class AveragePupilView extends AbstractAveragePupilView {
	private static final long	serialVersionUID		= 1L;

	private JLabel				lblAboutMe, lblSize, lblWeight, lblShoeSize, lblHairLength, lblHairColor, lblEyeColor, lblPiercing, lblTattoo, lblSmoker, lblLivingLocation, lblShower, lblGlasses, lblVegetarian, lblSiblings, lblStarSign, lblSchoolCareer, lblRegretLK, lblRepeated, lblSkipped, lblWorkSubmitted, lblWorkCanceled, lblAbiPreparations, lblTimeLearning, lblHoursMissed;
	private JLabel				lblM, lblKG, lblWo, lblH;																																																																																	// wochen/monate
	private BorderedTextField	txtHeight, txtWeight, txtShoeSize, txtShower, txtSiblings, txtAbiPreparations, txtTimeLearning, txtHoursMissed;
	private JComboBox<String>	hairLengthBox, hairColorBox, eyeColorBox, livingLocationBox, starSignBox;
	private xCheckBox			checkPiercing, checkTattoo, checkSmoker, checkGlasses, checkVegetarian, checkRegretLK, checkRepeated, checkSkipped, checkWorkSubmitted, checkWorkCanceled;

	private static String[]		hairLengthValues		= { "", "kurz", "mittel", "lang" };
	private static String[]		hairColorValues			= { "", "Blond", "Braun", "Schwarz" };
	private static String[]		eyeColorValues			= { "", "Blau", "Gr�n", "Braun" };
	private static String[]		livingLocationValues	= { "", "Stadt", "Land" };
	private static String[]		starSignValues			= { "", "Widder", "Stier", "Zwillinge", "Krebs", "L�we", "Jungfrau", "Waage", "Skorpion", "Sch�tze", "Steinbock", "Wassermann", "Fische" };

	/*
	 * variables
	 */

	private int					aboutMeStartY			= 100;
	private int					schoolCareerStartY		= aboutMeStartY + 5 * (lblHeight + 10) + 20 + 100;

	public AveragePupilView(int width, int height) {
		super(width, height);
		/*
		 * init components
		 */

		/*
		 * ABOUT ME
		 */

		lblAboutMe = new JLabel("�ber mich");
		lblAboutMe.setFont(headingFont);
		lblAboutMe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutMe.setBounds(10, 10, width - 20, headingFont.getSize() + 5);
		add(lblAboutMe);

		/*
		 * LEFT
		 */
		lblSize = new JLabel("Gr��e:");
		lblSize.setFont(lblFont);
		lblSize.setBounds(leftX, aboutMeStartY, lblWidth, lblHeight);
		add(lblSize);

		txtHeight = processTextField(lblSize);
		add(txtHeight);

		lblM = createUnitLabel("m", txtHeight);
		add(lblM);

		lblWeight = processLabel("Gewicht", leftX, lblSize);
		add(lblWeight);

		txtWeight = processTextField(lblWeight);
		add(txtWeight);

		lblKG = createUnitLabel("kg", txtWeight);
		add(lblKG);

		lblShoeSize = processLabel("Schuhgr��e", leftX, lblWeight);
		add(lblShoeSize);

		txtShoeSize = processTextField(lblShoeSize);
		add(txtShoeSize);

		lblShower = processLabel("Duschen pro Woche", leftX, lblShoeSize);
		add(lblShower);

		txtShower = processTextField(lblShower);
		add(txtShower);

		lblSiblings = processLabel("Geschweisterzahl", leftX, lblShower);
		add(lblSiblings);

		txtSiblings = processTextField(lblSiblings);
		add(txtSiblings);

		/*
		 * MID
		 */

		lblPiercing = new JLabel("Piercings?");
		lblPiercing.setFont(lblFont);
		lblPiercing.setBounds(midX, aboutMeStartY, lblWidth, lblHeight);
		add(lblPiercing);

		checkPiercing = new xCheckBox(getRightSideX(lblPiercing), centerBoxY(lblPiercing));
		add(checkPiercing);

		lblTattoo = processLabel("Tattoo?", midX, lblPiercing);
		add(lblTattoo);

		checkTattoo = new xCheckBox(getRightSideX(lblTattoo), centerBoxY(lblTattoo));
		add(checkTattoo);

		lblSmoker = processLabel("Raucher?", midX, lblTattoo);
		add(lblSmoker);

		checkSmoker = new xCheckBox(getRightSideX(lblSmoker), centerBoxY(lblSmoker));
		add(checkSmoker);

		lblGlasses = processLabel("Brille/Kontaktlinsen?", midX, lblSmoker);
		add(lblGlasses);

		checkGlasses = new xCheckBox(getRightSideX(lblGlasses), centerBoxY(lblGlasses));
		add(checkGlasses);

		lblVegetarian = processLabel("Vegetarier?", midX, lblGlasses);
		add(lblVegetarian);

		checkVegetarian = new xCheckBox(getRightSideX(lblVegetarian), centerBoxY(lblVegetarian));
		add(checkVegetarian);

		/*
		 * RIGHT
		 */

		lblHairLength = new JLabel("Haarl�nge:");
		lblHairLength.setFont(lblFont);
		lblHairLength.setBounds(rightX, aboutMeStartY, lblWidth, lblHeight);
		add(lblHairLength);

		hairLengthBox = processComboBox(hairLengthValues, lblHairLength);
		add(hairLengthBox);

		lblHairColor = processLabel("Haarfarbe?", rightX, lblHairLength);
		add(lblHairColor);

		hairColorBox = processComboBox(hairColorValues, lblHairColor);
		add(hairColorBox);

		lblEyeColor = processLabel("Augenfarbe", rightX, lblHairColor);
		add(lblEyeColor);

		eyeColorBox = processComboBox(eyeColorValues, lblEyeColor);
		add(eyeColorBox);

		lblLivingLocation = processLabel("Wohnlage", rightX, lblEyeColor);
		add(lblLivingLocation);

		livingLocationBox = processComboBox(livingLocationValues, lblLivingLocation);
		add(livingLocationBox);

		lblStarSign = processLabel("Sternzeichen", rightX, lblLivingLocation);
		add(lblStarSign);

		starSignBox = processComboBox(starSignValues, lblStarSign);
		add(starSignBox);

		/*
		 * SCHOOL CAREER
		 */

		lblSchoolCareer = new JLabel("Schulkarriere");
		lblSchoolCareer.setFont(headingFont);
		lblSchoolCareer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoolCareer.setBounds(10, schoolCareerStartY - 100, width - 20, headingFont.getSize() + 5);
		add(lblSchoolCareer);

		/*
		 * LEFT
		 */

		lblAbiPreparations = new JLabel("Abi Vorbereitung?");
		lblAbiPreparations.setFont(lblFont);
		lblAbiPreparations.setBounds(leftX, schoolCareerStartY, lblWidth, lblHeight);
		add(lblAbiPreparations);

		txtAbiPreparations = processTextField(lblAbiPreparations);
		add(txtAbiPreparations);

		lblWo = createUnitLabel("Wo.", txtAbiPreparations);
		add(lblWo);

		lblTimeLearning = processLabel("Lernen pro Tag", leftX, lblAbiPreparations);
		add(lblTimeLearning);

		txtTimeLearning = processTextField(lblTimeLearning);
		add(txtTimeLearning);

		lblH = createUnitLabel("h", txtTimeLearning);
		add(lblH);

		lblHoursMissed = processLabel("Fehlstunden gesamt", leftX, lblTimeLearning);
		add(lblHoursMissed);

		txtHoursMissed = processTextField(lblHoursMissed);
		add(txtHoursMissed);

		/*
		 * MID
		 */

		lblRegretLK = new JLabel("LK-Wahl bereut?");
		lblRegretLK.setFont(lblFont);
		lblRegretLK.setBounds(midX, schoolCareerStartY, lblWidth, lblHeight);
		add(lblRegretLK);

		checkRegretLK = new xCheckBox(getRightSideX(lblRegretLK), centerBoxY(lblRegretLK));
		add(checkRegretLK);

		lblRepeated = processLabel("Wiederholt?", midX, lblRegretLK);
		add(lblRepeated);

		checkRepeated = new xCheckBox(getRightSideX(lblRepeated), centerBoxY(lblRepeated));
		add(checkRepeated);

		lblSkipped = processLabel("�bersprungen?", midX, lblRepeated);
		add(lblSkipped);

		checkSkipped = new xCheckBox(getRightSideX(lblSkipped), centerBoxY(lblSkipped));
		add(checkSkipped);

		lblWorkSubmitted = processLabel("Facharbeit abgegeben?", midX, lblSkipped);
		add(lblWorkSubmitted);

		checkWorkSubmitted = new xCheckBox(getRightSideX(lblWorkSubmitted), centerBoxY(lblWorkSubmitted));
		add(checkWorkSubmitted);

		lblWorkCanceled = processLabel("Facharbeit abgebrochen?", midX, lblWorkSubmitted);
		add(lblWorkCanceled);

		checkWorkCanceled = new xCheckBox(getRightSideX(lblWorkCanceled), centerBoxY(lblWorkCanceled));
		add(checkWorkCanceled);

		/*
		 * RIGHT
		 */
	}

	public void handleResults() {

	}
}
