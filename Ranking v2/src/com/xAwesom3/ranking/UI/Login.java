package com.xAwesom3.ranking.UI;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.xAwesom3.ranking.Pupil;
import com.xAwesom3.ranking.util.FileHandler;

public class Login {

	private static int		WIDTH;
	private static int		HEIGHT;

	private static float	SIZE_MULTIPLIER	= 0.35f;

	private static float	FONT_SIZE;

	private static int		TXT_WIDTH;
	private static int		BTN_WIDTH, BTN_HEIGHT;

	private JFrame			f;

	private JLabel			lblName;
	private JTextField		txtName;
	private JLabel			lblPassword;
	private JPasswordField	txtPassword;
	private JButton			btnStart;

	private Font			font			= new Font("Arial Black", Font.PLAIN, (int) (FONT_SIZE * 0.9));
	private Font			txtFont			= new Font("Comic Sans MS", Font.BOLD, (int) (FONT_SIZE * 0.9));

	private MainFrame		frame;

	static {
		DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		WIDTH = (int) (mode.getWidth() * SIZE_MULTIPLIER);
		HEIGHT = (int) (mode.getHeight() * SIZE_MULTIPLIER);
		FONT_SIZE = WIDTH * 0.033333333333333f;
		TXT_WIDTH = (int) (WIDTH * 0.6);
		BTN_WIDTH = (int) (WIDTH * 0.2);
		BTN_HEIGHT = (int) (HEIGHT * 0.08);
	}

	public Login() {
		Thread thread = new Thread() {
			public void run() {
				FileHandler.loadFiles();
				frame = new MainFrame();
			}
		};
		thread.start();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		f = new JFrame();

		f.setTitle("Ranking");
		f.setSize(WIDTH, HEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.getContentPane().setBackground(Color.DARK_GRAY);
		f.setVisible(true);

		lblName = new JLabel("Name:");
		lblName.setFont(font);
		lblName.setBounds(0, (int) (HEIGHT * 0.09), WIDTH, lblName.getFont().getSize());
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		f.add(lblName);

		txtName = new JTextField();
		txtName.setFont(txtFont);
		txtName.setBounds(WIDTH / 2 - TXT_WIDTH / 2, lblName.getY() + lblName.getHeight() + 15, TXT_WIDTH, (int) (txtName.getFont().getSize() * 1.6));
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		f.add(txtName);

		lblPassword = new JLabel("Passwort:");
		lblPassword.setFont(font);
		lblPassword.setBounds(0, txtName.getY() + txtName.getHeight() + 30, WIDTH, lblPassword.getFont().getSize());
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		f.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(txtFont);
		txtPassword.setBounds(WIDTH / 2 - TXT_WIDTH / 2, lblPassword.getY() + lblPassword.getHeight() + 15, TXT_WIDTH, (int) (txtPassword.getFont().getSize() * 1.6));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		f.add(txtPassword);

		btnStart = new JButton("Login");
		btnStart.setFont(font);
		btnStart.setBounds(WIDTH / 2 - BTN_WIDTH / 2, txtPassword.getY() + txtPassword.getHeight() + 65, BTN_WIDTH, BTN_HEIGHT);
		btnStart.addActionListener((ActionEvent e) -> login());
		f.add(btnStart);
	}

	private void login() {
		Pupil pupil = Pupil.getByName(txtName.getText());
		if (pupil != null) {
			if (new String(txtPassword.getPassword()).equals(pupil.getPassword())) {
				// TODO: check progress!
				frame.show(txtName.getText());
				f.dispose();
			}
			else {
				JOptionPane.showMessageDialog(f, "Falsches Passwort!");
				txtPassword.setText("");
			}
		}
		else {
			JOptionPane.showMessageDialog(f, "Dieser Name existiert nicht in der Stufenliste!");
			txtName.setText("");
			txtPassword.setText("");
		}
	}

	public static void main(String[] args) {
		new Login();
	}

}
