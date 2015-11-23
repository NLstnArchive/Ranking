package com.xAwesom3.ranking.UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.xAwesom3.ranking.Human;
import com.xAwesom3.ranking.util.FileHandler;

public class Login extends JFrame {
	private static final long		serialVersionUID	= 1L;

	private static MainFrame		mainFrame;

	private Font					lblFont;
	private Font					txtFont;

	private JLabel					lblName, lblPassword;
	private static JTextField		txtName;
	private static JPasswordField	txtPassword;
	private static JButton			btnStart;

	static boolean					finishedLoading		= false;

	public Login() {

		/*
		 * init variables
		 */

		DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		int width = (int) (displayMode.getWidth() * 0.35);
		int height = (int) (width / 16 * 9);
		lblFont = new Font("Arial Black", Font.PLAIN, (int) (width * 0.04));
		txtFont = new Font("Comic Sans MS", Font.BOLD, (int) (width * 0.03));
		int txtWidth = (int) (width * 0.7);
		int txtHeight = txtFont.getSize() + 10;
		int btnWidth = (int) (width * 0.2);

		/*
		 * init frame
		 */

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle("Ranking - Login");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		/*
		 * init components
		 */

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setLayout(null);

		lblName = new JLabel("Name:");
		lblName.setFont(lblFont);
		lblName.setForeground(Color.BLACK);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(10, (int) (height * 0.1f), width - 20, lblName.getFont().getSize());
		add(lblName);

		txtName = new JTextField("Niklas Lahnstein");
		txtName.setFont(txtFont);
		txtName.setForeground(Color.BLACK);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setBounds(width / 2 - txtWidth / 2, lblName.getY() + lblName.getHeight() + 15, txtWidth, txtHeight);
		add(txtName);

		lblPassword = new JLabel("Passwort:");
		lblPassword.setFont(lblFont);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(10, (int) (height * 0.4), width - 20, lblPassword.getFont().getSize());
		add(lblPassword);

		txtPassword = new JPasswordField("31Mai1997");
		txtPassword.setFont(txtFont);
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBounds(width / 2 - txtWidth / 2, lblPassword.getY() + lblPassword.getHeight() + 15, txtWidth, txtHeight);
		add(txtPassword);

		btnStart = new JButton("Lädt...");
		btnStart.setFont(lblFont);
		btnStart.setForeground(Color.BLACK);
		btnStart.setBounds(width / 2 - btnWidth / 2, (int) (height * 0.7), btnWidth, txtHeight);
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login();
			}

		});
		add(btnStart);

		/*
		 * postInit
		 */

		KeyAdapter keyAdapter = new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (!btnStart.isEnabled())
					return;
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					login();
			}
		};
		btnStart.setEnabled(false);
		txtName.addKeyListener(keyAdapter);
		txtPassword.addKeyListener(keyAdapter);
		new ButtonStateController(btnStart, txtName, txtPassword);
		setVisible(true);
	}

	private void login() {
		Human human = Human.getManByName(txtName.getText());
		if (human == null)
			human = Human.getWomanByName(txtName.getText());
		if (human != null) {
			if (new String(txtPassword.getPassword()).equals(human.getPassword())) {
				mainFrame.show(human.getName());
				dispose();
			}
			else {
				JOptionPane.showConfirmDialog(this, "Falsches Passwort!");
				txtPassword.setText("");
				return;
			}
		}
		else {
			JOptionPane.showConfirmDialog(this, "Name wurde nicht in der Liste deiner Stufe gefunden!");
			txtName.setText("");
			txtPassword.setText("");
			return;
		}
	}

	private static void finishedLoading() {
		finishedLoading = true;
		btnStart.setText("Start");
		btnStart.setEnabled(!txtName.getText().equalsIgnoreCase("") && !new String(txtPassword.getPassword()).equalsIgnoreCase(""));
	}

	public static void main(String[] args) {
		new Thread("FileLoading") {
			public void run() {
				FileHandler.loadFiles();
				mainFrame = new MainFrame();
				finishedLoading();
			}
		}.start();
		new Login();
	}

}

class ButtonStateController {

	private JButton			button;
	private JTextField		name;
	private JPasswordField	password;

	public ButtonStateController(JButton button, JTextField name, JPasswordField password) {
		this.button = button;
		this.name = name;
		this.password = password;

		name.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				onContentChanged();
			}

			public void removeUpdate(DocumentEvent e) {
				onContentChanged();
			}

			public void changedUpdate(DocumentEvent e) {
				onContentChanged();
			}

		});
		password.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				onContentChanged();
			}

			public void removeUpdate(DocumentEvent e) {
				onContentChanged();
			}

			public void changedUpdate(DocumentEvent e) {
				onContentChanged();
			}

		});
	}

	public void onContentChanged() {
		if (Login.finishedLoading) {
			button.setEnabled(!name.getText().equalsIgnoreCase("") && !new String(password.getPassword()).equalsIgnoreCase(""));
		}
	}

}
