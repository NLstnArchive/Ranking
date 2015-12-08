package com.xAwesom3.ranking.UI.views;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.w3c.dom.Element;

import com.xAwesom3.ranking.UI.components.BorderedTextField;
import com.xAwesom3.ranking.UI.components.BorderedTextField.Type;
import com.xAwesom3.ranking.UI.components.xCheckBox;
import com.xAwesom3.ranking.util.XMLHandler;
import com.xAwesom3.ranking.util.xLogger;

public class AveragePupilView4 extends AbstractAveragePupilView {
	private static final long	serialVersionUID	= 1L;

	private JLabel				lblMine, lblHandy, lblSwitchOn, lblDrivingLicense, lblOwnCar, lblShoes, lblInstrument, lblJob, lblIncome, lblPocketMoney, lblRock, lblMusic, lblConcerts, lblRaR, lblBeer, lblSchnapps, lblCoffee, lblDrugs, lblSingle, lblJungfrau;
	private JLabel				lblEuro1, lblEuro2, lblL, lblL2, lblTasse;
	private xCheckBox			checkHandy, checkSwitchOn, checkDrivingLicense, checkOwnCar, checkInstrument, checkJob, checkRaR, checkDrugs, checkSingle, checkJungfrau;
	private BorderedTextField	txtShoes, txtIncome, txtPocketMoney, txtConcerts, txtBeer, txtSchnapps, txtCoffee;
	private JComboBox<String>	musicBox;

	private int					mineStartY			= 100;
	private int					rockStartY			= 6 * (componentHeight + 10) + 100 + 100;

	private String[]			musicValues			= { "", "Rock", "Charts", "Elektro", "Anderes", "Hip Hop" };

	public AveragePupilView4(int width, int height) {
		super(width, height);

		/*
		 * MINE
		 */

		lblMine = new JLabel("Meins");
		lblMine.setFont(headingFont);
		lblMine.setHorizontalAlignment(0);
		lblMine.setBounds(10, 10, width - 20, headingFont.getSize() + 5);
		add(lblMine);

		/*
		 * LEFT
		 */

		lblShoes = new JLabel("Anzahl Schuhe");
		lblShoes.setFont(lblFont);
		lblShoes.setBounds(leftX, mineStartY, lblWidth, lblHeight);
		add(lblShoes);

		txtShoes = processTextField(lblShoes, Type.INT);
		add(txtShoes);

		lblIncome = processLabel("Einkommen", leftX, lblShoes);
		add(lblIncome);

		txtIncome = processTextField(lblIncome, Type.INT);
		add(txtIncome);

		lblEuro1 = createUnitLabel("€", txtIncome);
		add(lblEuro1);

		lblPocketMoney = processLabel("...davon Taschengeld?", leftX, lblIncome);
		add(lblPocketMoney);

		txtPocketMoney = processTextField(lblPocketMoney, Type.INT);
		add(txtPocketMoney);

		lblEuro2 = createUnitLabel("€", txtPocketMoney);
		add(lblEuro2);

		/*
		 * MID
		 */

		lblHandy = new JLabel("Handy?");
		lblHandy.setFont(lblFont);
		lblHandy.setBounds(midX, mineStartY, lblWidth, lblHeight);
		add(lblHandy);

		checkHandy = new xCheckBox(getRightSideX(lblHandy), centerBoxY(lblHandy));
		add(checkHandy);

		lblSwitchOn = processLabel("...angeschltet?", midX, lblHandy);
		add(lblSwitchOn);

		checkSwitchOn = new xCheckBox(getRightSideX(lblSwitchOn), centerBoxY(lblSwitchOn));
		add(checkSwitchOn);

		lblDrivingLicense = processLabel("Führerschein?", midX, lblSwitchOn);
		add(lblDrivingLicense);

		checkDrivingLicense = new xCheckBox(getRightSideX(lblDrivingLicense), centerBoxY(lblDrivingLicense));
		add(checkDrivingLicense);

		lblOwnCar = processLabel("eigenes Auto", midX, lblDrivingLicense);
		add(lblOwnCar);

		checkOwnCar = new xCheckBox(getRightSideX(lblOwnCar), centerBoxY(lblOwnCar));
		add(checkOwnCar);

		lblInstrument = processLabel("Spielt ein Instrument?", midX, lblOwnCar);
		add(lblInstrument);

		checkInstrument = new xCheckBox(getRightSideX(lblInstrument), centerBoxY(lblInstrument));
		add(checkInstrument);

		lblJob = processLabel("Nebenjob?", midX, lblInstrument);
		add(lblJob);

		checkJob = new xCheckBox(getRightSideX(lblJob), centerBoxY(lblJob));
		add(checkJob);

		/*
		 * RIGHT
		 */

		/*
		 * SEX, DRUGS, ROCK AND ROLL
		 */

		lblRock = new JLabel("Sex, Drugs, Rock 'n' Roll");
		lblRock.setFont(headingFont);
		lblRock.setHorizontalAlignment(0);
		lblRock.setBounds(10, rockStartY - 100, width - 20, headingFont.getSize() + 5);
		add(lblRock);

		/*
		 * LEFT
		 */

		lblConcerts = new JLabel("Anzahl Konzertbesuche");
		lblConcerts.setFont(lblFont);
		lblConcerts.setBounds(leftX, rockStartY, lblWidth, lblHeight);
		add(lblConcerts);

		txtConcerts = processTextField(lblConcerts, Type.INT);
		add(txtConcerts);

		lblBeer = processLabel("Bier pro Woche", leftX, lblConcerts);
		add(lblBeer);

		txtBeer = processTextField(lblBeer, Type.FLOAT);
		add(txtBeer);

		lblL = createUnitLabel("L", txtBeer);
		add(lblL);

		lblSchnapps = processLabel("Schnaps pro Woche", leftX, lblBeer);
		add(lblSchnapps);

		txtSchnapps = processTextField(lblSchnapps, Type.FLOAT);
		add(txtSchnapps);

		lblL2 = createUnitLabel("L", txtSchnapps);
		add(lblL2);

		lblCoffee = processLabel("Kaffee pro Woche", leftX, lblSchnapps);
		add(lblCoffee);

		txtCoffee = processTextField(lblCoffee, Type.INT);
		add(txtCoffee);

		lblTasse = createUnitLabel("T.", txtCoffee);
		add(lblTasse);

		/*
		 * MID
		 */

		lblRaR = new JLabel("Rock am Ring?");
		lblRaR.setFont(lblFont);
		lblRaR.setBounds(midX, rockStartY, lblWidth, lblHeight);
		add(lblRaR);

		checkRaR = new xCheckBox(getRightSideX(lblRaR), centerBoxY(lblRaR));
		add(checkRaR);

		lblDrugs = processLabel("Schonmal Drogen genommen?", midX, lblRaR);
		lblDrugs.setFont(new Font(lblFont.getName(), lblFont.getStyle(), 18));
		add(lblDrugs);

		checkDrugs = new xCheckBox(getRightSideX(lblDrugs), centerBoxY(lblDrugs));
		add(checkDrugs);

		lblSingle = processLabel("Single?", midX, lblDrugs);
		add(lblSingle);

		checkSingle = new xCheckBox(getRightSideX(lblSingle), centerBoxY(lblSingle));
		add(checkSingle);

		lblJungfrau = processLabel("Jungfrau?", midX, lblSingle);
		add(lblJungfrau);

		checkJungfrau = new xCheckBox(getRightSideX(lblJungfrau), centerBoxY(lblJungfrau));
		add(checkJungfrau);

		/*
		 * RIGHT
		 */

		lblMusic = new JLabel("Musikrichtung:");
		lblMusic.setFont(lblFont);
		lblMusic.setBounds(rightX, rockStartY, lblWidth, lblHeight);
		add(lblMusic);

		musicBox = processComboBox(musicValues, lblMusic);
		add(musicBox);
	}

	// private xCheckBox checkHandy, checkSwitchOn, checkDrivingLicense, checkOwnCar, checkInstrument, checkJob, checkRaR, checkDrugs, checkSingle, checkJungfrau;
	// private BorderedTextField txtShoes, txtIncome, txtPocketMoney, txtConcerts, txtBeer, txtSchnapps, txtCoffee;
	// private JComboBox<String> musicBox;

	public List<Element> getResults(XMLHandler handler) {
		List<Element> resultList = new ArrayList<Element>();
		resultList.add(handler.createElement("checkHandy", checkHandy.isChecked().toString()));
		resultList.add(handler.createElement("checkSwitchOn", checkSwitchOn.isChecked().toString()));
		resultList.add(handler.createElement("checkDrivingLicense", checkDrivingLicense.isChecked().toString()));
		resultList.add(handler.createElement("checkOwnCar", checkOwnCar.isChecked().toString()));
		resultList.add(handler.createElement("checkInstrument", checkInstrument.isChecked().toString()));
		resultList.add(handler.createElement("checkJob", checkJob.isChecked().toString()));
		resultList.add(handler.createElement("checkRaR", checkRaR.isChecked().toString()));
		resultList.add(handler.createElement("checkDrugs", checkDrugs.isChecked().toString()));
		resultList.add(handler.createElement("checkSingle", checkSingle.isChecked().toString()));
		resultList.add(handler.createElement("checkJungfrau", checkJungfrau.isChecked().toString()));

		resultList.add(handler.createElement("txtShoes", txtShoes.getText()));
		resultList.add(handler.createElement("txtIncome", txtIncome.getText()));
		resultList.add(handler.createElement("txtPocketMoney", txtPocketMoney.getText()));
		resultList.add(handler.createElement("txtConcerts", txtConcerts.getText()));
		resultList.add(handler.createElement("txtBeer", txtBeer.getText()));
		resultList.add(handler.createElement("txtSchnapps", txtSchnapps.getText()));
		resultList.add(handler.createElement("txtCoffee", txtCoffee.getText()));

		resultList.add(handler.createElement("musicBox", (String) musicBox.getSelectedItem()));

		return resultList;
	}

	public boolean isFilledIn() {
		return false;
	}

	public void loadResults(XMLHandler handler) {
		txtShoes.setText(handler.getUniqueElementText("txtShoes"));
		txtIncome.setText(handler.getUniqueElementText("txtIncome"));
		txtPocketMoney.setText(handler.getUniqueElementText("txtPocketMoney"));
		txtConcerts.setText(handler.getUniqueElementText("txtConcerts"));
		txtBeer.setText(handler.getUniqueElementText("txtBeer"));
		txtSchnapps.setText(handler.getUniqueElementText("txtSchnapps"));
		txtCoffee.setText(handler.getUniqueElementText("txtCoffee"));

		checkHandy.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkHandy")));
		checkSwitchOn.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkSwitchOn")));
		checkDrivingLicense.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkDrivingLicense")));
		checkOwnCar.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkOwnCar")));
		checkInstrument.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkInstrument")));
		checkJob.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkJob")));
		checkRaR.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkRaR")));
		checkDrugs.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkDrugs")));
		checkSingle.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkSingle")));
		checkJungfrau.setChecked(Boolean.valueOf(handler.getUniqueElementText("checkJungfrau")));

		musicBox.setSelectedItem(handler.getUniqueElementText("musicBox"));
		
		xLogger.log("Finished loading results for AveragePupilView4");
	}

}
