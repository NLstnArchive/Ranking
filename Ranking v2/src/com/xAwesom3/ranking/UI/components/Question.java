package com.xAwesom3.ranking.UI.components;

import java.awt.Color;
import java.awt.Font;

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

import com.xAwesom3.ranking.Human;

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

	public Question(String text, int reply, int width, int height) {
		this.reply = reply;
		this.text = text;

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

		DefaultListModel<String> list = new DefaultListModel<String>();
		for (int i = 0; i < Human.getWomen().size(); i++)
			list.addElement(Human.getWomen().get(i).getName());
		femaleList = new JList<String>(list);
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

		list = new DefaultListModel<String>();
		for (int i = 0; i < Human.getMen().size(); i++)
			list.addElement(Human.getMen().get(i).getName());
		maleList = new JList<String>(list);
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
		maleList.removeListSelectionListener(maleListListener);
		txtMaleInputArea.getDocument().removeDocumentListener(maleDocListener);
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < Human.getMen().size(); i++) {
			if (Human.getMen().get(i).getName().toLowerCase().startsWith(txtMaleInputArea.getText().toLowerCase()))
				model.addElement(Human.getMen().get(i).getName());
		}
		maleList.setModel(model);
		maleList.addListSelectionListener(maleListListener);
		txtMaleInputArea.getDocument().addDocumentListener(maleDocListener);
	}

	private void changeFemaleContent() {
		setAnswered(false);
		femaleList.removeListSelectionListener(maleListListener);
		txtFemaleInputArea.getDocument().removeDocumentListener(femaleDocListener);
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < Human.getWomen().size(); i++) {
			if (Human.getWomen().get(i).getName().toLowerCase().startsWith(txtFemaleInputArea.getText().toLowerCase()))
				model.addElement(Human.getWomen().get(i).getName());
		}
		femaleList.setModel(model);
		femaleList.addListSelectionListener(maleListListener);
		txtFemaleInputArea.getDocument().addDocumentListener(femaleDocListener);
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

	public void setAnswerUnchangeable() {
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
		return new StringBuilder().append(maleList.getSelectedValue()).append("/").append(femaleList.getSelectedValue()).toString();
	}

	public void setSelectedValue(String name) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < maleList.getModel().getSize(); i++) {
			model.addElement(maleList.getModel().getElementAt(i));
		}
		for (int i = 0; i < model.getSize(); i++) {
			if (model.getElementAt(i).equalsIgnoreCase(name))
				maleList.setSelectedIndex(i);
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