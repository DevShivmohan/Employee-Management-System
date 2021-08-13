import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import dboperation.AdminInfo;
import icons.AllColors;
import icons.AllIcons;
import icons.ScreenSize;
import security.SRandom;

public class InfosysLogin extends ScreenSize {

	AllColors allColors = new AllColors();
	AllIcons allIcons = new AllIcons();

	JFrame frame;
	JPanel panel;
	JButton forgotButton;
	JButton loginButton;

	public InfosysLogin() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(25, 5, 5, 5);
		// new InsetsUIResource(top, left, bottom, right)

		frame = new JFrame("ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED");
		frame.setSize(getFrameWidth(), getFrameHeight());
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setBackground(ColorUIResource.GRAY);
		frame.setVisible(true);

		new LoginSession().destroySession(); // destryoe all sessions

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel = new JPanel(null);
		panel.setPreferredSize(new Dimension(650, 500));
		panel.setBackground(new ColorUIResource(11, 22, 30));

		// infosys icon
		JLabel infoSys = new JLabel(new ImageIcon(new ImageIcon(allIcons.getEmpWorkersIcon().getAbsolutePath())
				.getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING)));
		infoSys.setBounds(275, 5, 100, 100);
		panel.add(infoSys);

		// front label
		JLabel frontLoginLabel = new JLabel("Employee Management System Login");
		frontLoginLabel.setFont(new Font("verdana", Font.HANGING_BASELINE, 30));
		frontLoginLabel.setForeground(ColorUIResource.MAGENTA);
		frontLoginLabel.setBounds(50, 115, 600, 35);
		panel.add(frontLoginLabel);

		// username
		ImageIcon imageUserIcon = new ImageIcon(new ImageIcon(allIcons.getLoginUserIcon().getAbsolutePath()).getImage()
				.getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING));
		JLabel userNameLabel = new JLabel("Username", imageUserIcon, JLabel.HORIZONTAL);
		userNameLabel.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
		userNameLabel.setForeground(ColorUIResource.BLUE);
		userNameLabel.setBounds(50, 200, 200, 35);
		panel.add(userNameLabel);

		JTextField userNameTextField = new JTextField();
		userNameTextField.setBounds(300, 200, 250, 30);
		userNameTextField.setToolTipText("Enter your Username");
		// userNameTextField.setTransferHandler(null);
		userNameTextField.setBackground(ColorUIResource.CYAN);
		userNameTextField.setForeground(ColorUIResource.BLUE);
		userNameTextField.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
		userNameTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				if (userNameTextField.getText().length() > 40)
					ke.consume();
			}
		});
		panel.add(userNameTextField);

		// password
		ImageIcon imagePasswordIcon = new ImageIcon(new ImageIcon(allIcons.getLoginPasswordIcon().getAbsolutePath())
				.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING));
		JLabel userPassLabel = new JLabel("Password", imagePasswordIcon, JLabel.HORIZONTAL);
		userPassLabel.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
		userPassLabel.setForeground(ColorUIResource.BLUE);
		userPassLabel.setBounds(50, 250, 200, 35);
		panel.add(userPassLabel);

		JPasswordField userPasswordField = new JPasswordField();
		userPasswordField.setBounds(300, 250, 250, 30);
		userPasswordField.setToolTipText("Enter your Password");
		userPasswordField.setTransferHandler(null);
		userPasswordField.setBackground(ColorUIResource.CYAN);
		userPasswordField.setForeground(ColorUIResource.BLUE);
		userPasswordField.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
		userPasswordField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				if (userPasswordField.getText().length() > 50)
					ke.consume();
			}
		});
		panel.add(userPasswordField);

		// login button
		loginButton = new JButton("Login");
		loginButton.setBounds(220, 380, 80, 26);
		loginButton.setToolTipText("Prees the Login after that logging");
		loginButton.setFont(new Font("verdana", Font.CENTER_BASELINE, 18));
		loginButton.setBackground(ColorUIResource.CYAN);
		loginButton.setForeground(ColorUIResource.BLUE);
		loginButton.setBorder(BorderFactory.createEtchedBorder());
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (userNameTextField.getText().isBlank() && userPasswordField.getText().isBlank()) {
					userNameTextField.setBackground(ColorUIResource.RED);
					userPasswordField.setBackground(ColorUIResource.RED);
					JOptionPane.showMessageDialog(frame, "Please enter Username and Password", "error", 0);
				} else {
					if (!userNameTextField.getText().isBlank()) {
						userNameTextField.setBackground(ColorUIResource.CYAN);
						if (!userPasswordField.getText().isBlank()) {
							userPasswordField.setBackground(ColorUIResource.CYAN);
							new Thread(new Runnable() {
								public void run() {
									loginButton.setVisible(false);
									forgotButton.setVisible(false);
									JLabel loader = createPreloader(panel);
									String userInput = userNameTextField.getText();
									String userPass = userPasswordField.getText();
									userNameTextField.setText("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
									userPasswordField.setText("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
									String OTP = new SRandom().getOTP(); // generating an OTP
									AdminInfo adminInfo = new AdminInfo();
									int response = adminInfo.checkUser(userInput, userPass, OTP);
									if (response == 0) {
										String userOTP = JOptionPane.showInputDialog(frame,
												"OTP will be sent to your registered email enter OTP");
										if (userOTP != null) {
											if (userOTP.equals(OTP)) {
												LoginSession loginSession = new LoginSession();
												loginSession.createSession(userInput); // creating session
												adminInfo.setAdminInfoInHashTable(); // setting all data into hashtable
												if (adminInfo.updateSession(userInput)) {
													frame.removeAll();
													frame.setVisible(false);
													new MainGUI();
												} else
													JOptionPane.showMessageDialog(frame,
															"Some Technical Problem in creating Sessions ! So that we are again return to Login Page",
															"error", 0);
											} else
												JOptionPane.showMessageDialog(frame, "Incorrect OTP", "error", 0);
										}
									} else if (response == 1)
										JOptionPane.showMessageDialog(frame, "Incorrect Username or Password", "error",
												0);
									else
										JOptionPane.showMessageDialog(frame, "Server error or interrupted connection",
												"error", 0);

									userNameTextField.setText("");
									userPasswordField.setText("");// there
									dismisPreloader(panel, loader); // dismissing animation
									loginButton.setVisible(true);
									forgotButton.setVisible(true);
								}

							}).start();
						} else {
							userPasswordField.setBackground(ColorUIResource.RED);
							JOptionPane.showMessageDialog(frame, "Please enter Password", "error", 0);
						}
					} else {
						userNameTextField.setBackground(ColorUIResource.RED);
						JOptionPane.showMessageDialog(frame, "Please enter Username", "error", 0);
					}
				}
			}
		});
		panel.add(loginButton);

		// forgot button
		forgotButton = new JButton("Forgot");
		forgotButton.setBounds(350, 380, 80, 26);
		forgotButton.setToolTipText("Enter username after that click Forgot button");
		forgotButton.setFont(new Font("verdana", Font.CENTER_BASELINE, 18));
		forgotButton.setBackground(ColorUIResource.CYAN);
		forgotButton.setForeground(ColorUIResource.BLUE);
		forgotButton.setBorder(BorderFactory.createEtchedBorder());
		forgotButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		forgotButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (!userNameTextField.getText().isBlank()) {
					new Thread(new Runnable() {
						public void run() {
							String userName = userNameTextField.getText();
							userNameTextField.setBackground(ColorUIResource.CYAN);
							userPasswordField.setBackground(ColorUIResource.CYAN);
							userNameTextField.setText("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
							userPasswordField.setText("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
							loginButton.setVisible(false);
							forgotButton.setVisible(false);
							JLabel loader = createPreloader(panel);
							int response = new AdminInfo().checkUser(userName);
							if (response == 0) {
								dismisPreloader(panel, loader);
								loginButton.setVisible(true);
								forgotButton.setVisible(true);
								JOptionPane.showMessageDialog(frame,
										"Your Account Username and Password will be sent to his registered email",
										"success", JOptionPane.INFORMATION_MESSAGE);
								userNameTextField.setText("");
								userPasswordField.setText("");

							} else if (response == 1) {
								dismisPreloader(panel, loader);
								loginButton.setVisible(true);
								forgotButton.setVisible(true);
								JOptionPane.showMessageDialog(frame, "Incorrect Username", "error", 0);
								userNameTextField.setText("");
								userPasswordField.setText("");
							} else {
								dismisPreloader(panel, loader);
								loginButton.setVisible(true);
								forgotButton.setVisible(true);
								JOptionPane.showMessageDialog(frame, "Server error or interrupted connection", "error",
										0);
								userNameTextField.setText("");
								userPasswordField.setText("");
							}
						}
					}).start();
				} else {
					userNameTextField.setBackground(ColorUIResource.RED);
					JOptionPane.showMessageDialog(frame, "Please enter Username", "error", 0);
					userNameTextField.setText("");
					userPasswordField.setText("");
				}
			}
		});
		panel.add(forgotButton);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				if ((JOptionPane.showConfirmDialog(frame, "Are you sure to exit in this Application")) == 0)
					System.exit(0);
			}
		});
		frame.add(panel, gbc);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private JLabel createPreloader(JPanel panel) {
		// login preloader
		JLabel loginPreloader = new JLabel(new ImageIcon(new ImageIcon(allIcons.getLoginPreloader().getAbsolutePath())
				.getImage().getScaledInstance(40, 40, Image.SCALE_FAST)));
		loginPreloader.setBounds(305, 315, 40, 40);
		panel.add(loginPreloader);
		panel.revalidate();
		panel.repaint();
		return loginPreloader;
	}

	private void dismisPreloader(JPanel panel, JLabel loginPreloader) {
		// login preloader
		panel.remove(loginPreloader);
		panel.revalidate();
		panel.repaint();
	}
}