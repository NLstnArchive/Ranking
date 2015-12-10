package com.xAwesom3.ranking.UI.components;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import org.w3c.dom.Element;

import com.xAwesom3.ranking.Human;
import com.xAwesom3.ranking.util.xLogger;

public class Question extends JPanel {
	private static final long		serialVersionUID	= -4079364967299961090L;

	private String					text;

	private JTextField				txtMaleInputArea, txtFemaleInputArea;
	private JList<String>			maleList, femaleList;
	private JTextField				txtQuestionArea;
	private JScrollPane				scrollMalePane, scrollFemalePane;
	private int						reply;
	private DocumentListener		maleDocListener, femaleDocListener;
	private ListSelectionListener	maleListListener, femaleListListener;

	private boolean					answered;

	private Color					bg_false			= new Color(255, 169, 69);
	private Color					bg_true				= new Color(40, 242, 50);
	private Color					txt_false			= new Color(255, 122, 26);
	private Color					txt_true			= new Color(22, 168, 30);

	protected Font					font				= new Font("Ludicida Sans Unicode", Font.BOLD, 16);

	private List<Human>				menList, womenList;

	public Question(String text, int reply, int width, int height) {
		this.reply = reply;
		this.text = text;

		if (reply == 0) {
			menList = Human.getMen();
			womenList = Human.getWomen();
		}
		else {
			menList = Human.getMenTeachers();
			womenList = Human.getWomenTeachers();
		}

		setLayout(null);
		setBackground(bg_false);
		setBorder(LineBorder.createBlackLineBorder());

		txtQuestionArea = new JTextField(text);
		txtQuestionArea.setEditable(false);
		txtQuestionArea.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuestionArea.setFont(font);
		txtQuestionArea.setBorder(LineBorder.createBlackLineBorder());
		txtQuestionArea.setBackground(txt_false);
		txtQuestionArea.setBounds(10, 10, width - 20, 50);
		add(txtQuestionArea);

		txtMaleInputArea = new JTextField();
		txtMaleInputArea.setFont(font);
		txtMaleInputArea.setBounds(10, txtQuestionArea.getY() + txtQuestionArea.getHeight() + 10, width / 2 - 20, font.getSize() + 10);
		add(txtMaleInputArea);

		txtFemaleInputArea = new JTextField();
		txtFemaleInputArea.setFont(font);
		txtFemaleInputArea.setBounds(width / 2 + 10, txtQuestionArea.getY() + txtQuestionArea.getHeight() + 10, width / 2 - 20, font.getSize() + 10);
		add(txtFemaleInputArea);

		Document maleDoc = txtMaleInputArea.getDocument();
		Document femaleDoc = txtFemaleInputArea.getDocument();

		maleDocListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> changeMaleContents());
			}

			public void insertUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> changeMaleContents());
			}

			public void removeUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> changeMaleContents());
			}
		};
		maleDoc.addDocumentListener(maleDocListener);

		femaleDocListener = new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> changeFemaleContent());
			}

			public void removeUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> changeFemaleContent());
			}

			public void changedUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(() -> changeFemaleContent());
			}

		};
		femaleDoc.addDocumentListener(femaleDocListener);

		femaleList = new JList<String>(toNamesArray(womenList));
		femaleList.setVisibleRowCount(5);
		femaleList.setSelectionBackground(new Color(74, 238, 98));
		femaleList.setSelectionForeground(Color.BLACK);
		femaleList.setFont(new Font("Ludicida Sans Unicode", Font.PLAIN, 14));
		femaleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		femaleListListener = new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setAnswered(maleList.getSelectedIndex() != -1);
						txtFemaleInputArea.getDocument().removeDocumentListener(femaleDocListener);
						txtFemaleInputArea.setText(femaleList.getSelectedValue());
						txtFemaleInputArea.getDocument().addDocumentListener(femaleDocListener);
					}
				});
			}
		};
		femaleList.addListSelectionListener(femaleListListener);

		maleList = new JList<String>(toNamesArray(menList));
		maleList.setVisibleRowCount(5);
		maleList.setSelectionBackground(new Color(74, 238, 98));
		maleList.setSelectionForeground(Color.BLACK);
		maleList.setFont(new Font("Ludicida Sans Unicode", Font.PLAIN, 14));
		maleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		maleListListener = new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setAnswered(femaleList.getSelectedIndex() != -1);
						txtMaleInputArea.getDocument().removeDocumentListener(maleDocListener);
						txtMaleInputArea.setText(maleList.getSelectedValue());
						txtMaleInputArea.getDocument().addDocumentListener(maleDocListener);
					}
				});
			}
		};
		maleList.addListSelectionListener(maleListListener);

		scrollMalePane = new JScrollPane(maleList);
		scrollMalePane.setBounds(txtMaleInputArea.getX(), txtMaleInputArea.getY() + txtMaleInputArea.getHeight(), txtMaleInputArea.getWidth(), height - txtMaleInputArea.getY() + txtMaleInputArea.getHeight() - 61);
		add(scrollMalePane);

		scrollFemalePane = new JScrollPane(femaleList);
		scrollFemalePane.setBounds(txtFemaleInputArea.getX(), txtFemaleInputArea.getY() + txtFemaleInputArea.getHeight(), txtFemaleInputArea.getWidth(), height - txtFemaleInputArea.getY() + txtFemaleInputArea.getHeight() - 61);
		add(scrollFemalePane);

	}

	private void changeMaleContents() {
		setAnswered(false);
		txtMaleInputArea.getDocument().removeDocumentListener(maleDocListener);
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < menList.size(); i++) {
			if (toNamesArray(menList)[i].toLowerCase().startsWith(txtMaleInputArea.getText().toLowerCase()))
				model.addElement(toNamesArray(menList)[i]);
		}
		txtMaleInputArea.getDocument().addDocumentListener(maleDocListener);
		maleList.setModel(model);
	}

	private void changeFemaleContent() {
		setAnswered(false);
		txtFemaleInputArea.getDocument().removeDocumentListener(femaleDocListener);
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < toNamesArray(womenList).length; i++) {
			if (toNamesArray(womenList)[i].toLowerCase().startsWith(txtFemaleInputArea.getText().toLowerCase()))
				model.addElement(toNamesArray(womenList)[i]);
		}
		txtFemaleInputArea.getDocument().addDocumentListener(femaleDocListener);
		femaleList.setModel(model);
	}

	public void setAnswered(boolean answered) {
		if (answered) {
			setBackground(bg_true);
			txtQuestionArea.setBackground(txt_true);
		}
		else {
			setBackground(bg_false);
			txtQuestionArea.setBackground(txt_false);
		}
		this.answered = answered;
	}

	private void setFemaleAnswerUnchangeable() {
		femaleList.setSelectionModel(new DisabledItemSelectionModel());
		txtFemaleInputArea.setEditable(false);
	}

	private void setMaleAnswerUnchangeable() {
		maleList.setSelectionModel(new DisabledItemSelectionModel());
		txtMaleInputArea.setEditable(false);
	}

	public boolean isAnswered() {
		return answered;
	}

	public String getText() {
		return text;
	}

	public Integer getReply() {
		return reply;
	}

	public String getAnswer() {
		StringBuilder answer = new StringBuilder();
		if (txtMaleInputArea.getText() != "")
			answer.append(txtMaleInputArea.getText());
		answer.append(";");
		if (txtFemaleInputArea.getText() != "")
			answer.append(txtFemaleInputArea.getText());
		return answer.toString();
	}

	public void setAnswer(Element element) {
		if (element != null) {
			String answer = element.getTextContent();
			xLogger.log("Setting " + answer + " to Question " + text);
			applyAnswer(answer);
		}
	}

	public void setSelectedMaleValue(String name) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < maleList.getModel().getSize(); i++) {
			model.addElement(maleList.getModel().getElementAt(i));
		}
		for (int i = 0; i < model.getSize(); i++) {
			if (model.getElementAt(i).equalsIgnoreCase(name))
				maleList.setSelectedIndex(i);
		}
	}

	public void setSelectedFemaleValue(String name) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < femaleList.getModel().getSize(); i++) {
			model.addElement(femaleList.getModel().getElementAt(i));
		}
		for (int i = 0; i < model.getSize(); i++) {
			if (model.getElementAt(i).equalsIgnoreCase(name))
				femaleList.setSelectedIndex(i);
		}
	}

	private String[] toNamesArray(List<Human> list) {
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i).getName();
		}
		return result;
	}

	private void applyAnswer(String answer) {
		String[] strings = answer.split(";");

		if (strings.length == 2) {
			txtMaleInputArea.setText(strings[0]);
			setMaleAnswerUnchangeable();
			xLogger.log("Setting txtMaleInputArea to " + strings[0]);

			txtFemaleInputArea.setText(strings[1]);
			setFemaleAnswerUnchangeable();
			xLogger.log("Setting txtFemaleInputArea to " + strings[1]);
		}

		if (strings.length == 1) {
			String string = strings[0];
			if (menList.contains(Human.getByName(string))) {
				txtMaleInputArea.setText(string);
				setMaleAnswerUnchangeable();
				xLogger.log("Setting txtMaleInputArea to " + string);
			}
			if (womenList.contains(string)) {
				txtFemaleInputArea.setText(string);
				setFemaleAnswerUnchangeable();
				xLogger.log("Setting txtFemaleInputArea to " + string);
			}
			else {
				xLogger.log("[CRITICAL ERROR] Trying to load answer: " + string + " to " + text);
			}
		}
	}
}

class DisabledItemSelectionModel extends DefaultListSelectionModel {

	private static final long serialVersionUID = 1L;

	@Override
	public void setSelectionInterval(int index0, int index1) {
		super.setSelectionInterval(-1, -1);
	}

}