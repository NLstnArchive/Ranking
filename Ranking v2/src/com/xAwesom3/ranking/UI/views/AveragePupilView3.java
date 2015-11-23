package com.xAwesom3.ranking.UI.views;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.xAwesom3.ranking.UI.components.BorderedTextField;
import com.xAwesom3.ranking.UI.components.xCheckBox;

public class AveragePupilView3 extends AbstractAveragePupilView {
	private static final long	serialVersionUID	= 1L;

	private JLabel				lblFuture, lblWedding, lblChildren, lblFutureHomeTown, lblYouthSins, lblPokemon, lblDiddl, lblYugiOh, lblCardCollecting, lblGameBoy, lblBarbies, lblActionFiguren, lblBoyGroupFan, lblAlcohol, lblCigaretts;
	private JComboBox<String>	futureHomeTownBox;
	private xCheckBox			checkWedding, checkChildren, checkPokemon, checkDiddl, checkYugiOh, checkCardCollecting, checkGameBoy, checkBarbies, checkActionFiguren, checkBoygroupfan;
	private BorderedTextField	txtCigaretts, txtAlcohol;

	private static String[]		futureHomeTown		= { "", "weg", "hier", "Umgebung" };

	private int					startYouthSinY		= 100 + 2 * (componentHeight + 10) + 100;

	public AveragePupilView3(int width, int height) {
		super(width, height);

		/*
		 * FUTURE
		 */

		lblFuture = new JLabel("Zukunft");
		lblFuture.setFont(headingFont);
		lblFuture.setHorizontalAlignment(0);
		lblFuture.setBounds(10, 10, width - 20, headingFont.getSize() + 5);
		add(lblFuture);

		/*
		 * LEFT
		 */

		/*
		 * MID
		 */

		lblWedding = new JLabel("Heiratswunsch?");
		lblWedding.setFont(lblFont);
		lblWedding.setBounds(midX, 100, lblWidth, lblHeight);
		add(lblWedding);

		checkWedding = new xCheckBox(getRightSideX(lblWedding), centerBoxY(lblWedding));
		add(checkWedding);

		lblChildren = processLabel("Kinderwunsch?", midX, lblWedding);
		add(lblChildren);

		checkChildren = new xCheckBox(getRightSideX(lblChildren), centerBoxY(lblChildren));
		add(checkChildren);

		/*
		 * RIGHT
		 */

		lblFutureHomeTown = new JLabel("Zukünftiger Wohnort");
		lblFutureHomeTown.setFont(lblFont);
		lblFutureHomeTown.setBounds(rightX, 100, lblWidth, lblHeight);
		add(lblFutureHomeTown);

		futureHomeTownBox = processComboBox(futureHomeTown, lblFutureHomeTown);
		add(futureHomeTownBox);

		/*
		 * YOUTH SINS
		 */

		lblYouthSins = new JLabel("Jugendsünden");
		lblYouthSins.setHorizontalAlignment(0);
		lblYouthSins.setFont(headingFont);
		lblYouthSins.setBounds(10, startYouthSinY - 100, width - 20, headingFont.getSize() + 5);
		add(lblYouthSins);

		/*
		 * LEFT
		 */

		lblAlcohol = new JLabel("Erstes Mal Alkohol?");
		lblAlcohol.setFont(lblFont);
		lblAlcohol.setBounds(leftX, startYouthSinY, lblWidth, lblHeight);
		add(lblAlcohol);

		txtAlcohol = processTextField(lblAlcohol);
		add(txtAlcohol);

		lblCigaretts = processLabel("Erstes Mal Zigaretten?", leftX, lblAlcohol);
		add(lblCigaretts);

		txtCigaretts = processTextField(lblCigaretts);
		add(txtCigaretts);

		/*
		 * MID
		 */

		lblPokemon = new JLabel("Pokemon?");
		lblPokemon.setFont(lblFont);
		lblPokemon.setBounds(midX, startYouthSinY, lblWidth, lblHeight);
		add(lblPokemon);

		checkPokemon = new xCheckBox(getRightSideX(lblPokemon), centerBoxY(lblPokemon));
		add(checkPokemon);

		lblDiddl = processLabel("Diddl?", midX, lblPokemon);
		add(lblDiddl);

		checkDiddl = new xCheckBox(getRightSideX(lblDiddl), centerBoxY(lblDiddl));
		add(checkDiddl);

		lblYugiOh = processLabel("YugiOh?", midX, lblDiddl);
		add(lblYugiOh);

		checkYugiOh = new xCheckBox(getRightSideX(lblYugiOh), centerBoxY(lblYugiOh));
		add(checkYugiOh);

		lblCardCollecting = processLabel("Kartensammeln?", midX, lblYugiOh);
		add(lblCardCollecting);

		checkCardCollecting = new xCheckBox(getRightSideX(lblCardCollecting), centerBoxY(lblCardCollecting));
		add(checkCardCollecting);

		lblGameBoy = processLabel("GameBoy?", midX, lblCardCollecting);
		add(lblGameBoy);

		checkGameBoy = new xCheckBox(getRightSideX(lblGameBoy), centerBoxY(lblGameBoy));
		add(checkGameBoy);

		// lblPokemon, lblDiddl, lblYugiOh, lblCardCollecting, lblGameBoy, lblBarbies, lblActionFiguren, lblBoyGroupFan, lblAlcohol, lblCigaretts;

		lblBarbies = processLabel("Barbies`?", midX, lblGameBoy);
		add(lblBarbies);

		checkBarbies = new xCheckBox(getRightSideX(lblBarbies), centerBoxY(lblBarbies));
		add(checkBarbies);

		lblActionFiguren = processLabel("ActionFiguren?", midX, lblBarbies);
		add(lblActionFiguren);

		checkActionFiguren = new xCheckBox(getRightSideX(lblActionFiguren), centerBoxY(lblActionFiguren));
		add(checkActionFiguren);

		lblBoyGroupFan = processLabel("Boygroupfan?", midX, lblActionFiguren);
		add(lblBoyGroupFan);

		checkBoygroupfan = new xCheckBox(getRightSideX(lblBoyGroupFan), centerBoxY(lblBoyGroupFan));
		add(checkBoygroupfan);

		/*
		 * RIGHT
		 */
	}

	public void handleResults() {

	}

}
