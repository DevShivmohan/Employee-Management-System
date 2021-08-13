import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import dboperation.AddAdminData;
import empmail.EmpMailOperation;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import icons.AllColors;
import icons.AllIcons;
import icons.PreLoader;
import icons.ScreenSize;
import security.SRandom;
import validation.AllFieldValidation;

public class AddAdminUser extends ScreenSize {
    private JFrame frame;
    private JPanel panel;
    private final String[] adminRoles = { "Admin", "AtlasAdmin" };
    private boolean isTask = false;
    private JTextField adminNameTextField, userNameTextField, confirmPasswordNameTextField;
    private JButton loginButton;
    private JComboBox roleComboBox;

    public AddAdminUser() {
        AllColors allColors = new AllColors();
        AllIcons allIcons = new AllIcons();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 5, 5, 5);

        frame = new JFrame("Admin Account Holder Information");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setBackground(allColors.getPanelBackground());

        // front user icon
        JLabel infoSys = new JLabel(new ImageIcon(new ImageIcon(allIcons.getLoginUserIcon().getAbsolutePath())
                .getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING)));
        infoSys.setBounds(350, 5, 100, 100);
        panel.add(infoSys);

        // front label
        JLabel frontLoginLabel = new JLabel("Add ALPHA Admin Users");
        frontLoginLabel.setFont(new Font("verdana", Font.HANGING_BASELINE, 30));
        frontLoginLabel.setForeground(ColorUIResource.MAGENTA);
        frontLoginLabel.setBounds(180, 115, 500, 35);
        panel.add(frontLoginLabel);

        // name
        JLabel adminNameLabel = new JLabel("Admin Name");
        adminNameLabel.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        adminNameLabel.setForeground(ColorUIResource.BLUE);
        adminNameLabel.setBounds(50, 200, 200, 35);
        panel.add(adminNameLabel);

        adminNameTextField = new JTextField();
        adminNameTextField.setBounds(300, 200, 300, 30);
        adminNameTextField.setToolTipText("Enter your name");
        adminNameTextField.setTransferHandler(null);
        adminNameTextField.setBackground(ColorUIResource.CYAN);
        adminNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        adminNameTextField.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        adminNameTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                if (adminNameTextField.getText().length() > 40)
                    ke.consume();
            }
        });
        panel.add(adminNameTextField);

        // username
        JLabel userNameLabel = new JLabel("Email");
        userNameLabel.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        userNameLabel.setForeground(ColorUIResource.BLUE);
        userNameLabel.setBounds(50, 250, 200, 35);
        panel.add(userNameLabel);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(300, 250, 300, 30);
        userNameTextField.setToolTipText("Enter your Username and username length greater then 10");
        userNameTextField.setTransferHandler(null);
        userNameTextField.setBackground(ColorUIResource.CYAN);
        userNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        userNameTextField.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        userNameTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                if (userNameTextField.getText().length() > 40)
                    ke.consume();
            }
        });
        panel.add(userNameTextField);

        // create password
        JLabel confirmPasswordNameLabel = new JLabel("Create Password");
        confirmPasswordNameLabel.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        confirmPasswordNameLabel.setForeground(ColorUIResource.BLUE);
        confirmPasswordNameLabel.setBounds(50, 300, 220, 35);
        panel.add(confirmPasswordNameLabel);

        confirmPasswordNameTextField = new JTextField();
        confirmPasswordNameTextField.setBounds(300, 300, 300, 30);
        confirmPasswordNameTextField.setToolTipText("Password length greater then 8 characters");
        confirmPasswordNameTextField.setTransferHandler(null);
        confirmPasswordNameTextField.setBackground(ColorUIResource.CYAN);
        confirmPasswordNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        confirmPasswordNameTextField.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        confirmPasswordNameTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                if (confirmPasswordNameTextField.getText().length() > 40)
                    ke.consume();
            }
        });
        panel.add(confirmPasswordNameTextField);

        // role
        JLabel userRoleLabel = new JLabel("Admin Role");
        userRoleLabel.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        userRoleLabel.setForeground(ColorUIResource.BLUE);
        userRoleLabel.setBounds(50, 350, 200, 35);
        panel.add(userRoleLabel);

        roleComboBox = new JComboBox(adminRoles);
        roleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        roleComboBox.setSize(300, 30);
        roleComboBox.setBounds(300, 350, 300, 30);
        roleComboBox.setTransferHandler(null);
        roleComboBox.setForeground(ColorUIResource.DARK_GRAY);
        panel.add(roleComboBox);

        // add user button
        loginButton = new JButton("Add");
        loginButton.setBounds(360, 450, 80, 26);
        loginButton.setToolTipText("Prees and Add user");
        loginButton.setFont(new Font("verdana", Font.CENTER_BASELINE, 18));
        loginButton.setBackground(ColorUIResource.CYAN);
        loginButton.setForeground(ColorUIResource.BLUE);
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(loginButton);
        loginButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleAddUserEvent();
                }
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                handleAddUserEvent();
            }
        });

        // back to home page
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                if (isTask)
                    JOptionPane.showMessageDialog(frame, "Please wait for Task finshing", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                else {
                    frame.removeAll();
                    frame.setVisible(false);
                    new MainGUI();
                }
            }
        });
        frame.add(panel, gbc);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void handleAddUserEvent() {
        AllFieldValidation validation = new AllFieldValidation();
        if (validation.isValidateAddUserFields(adminNameTextField, userNameTextField, confirmPasswordNameTextField)) {
            new Thread(new Runnable() {
                public void run() {
                    isTask = true;
                    PreLoader preLoader = new PreLoader(); // creating preloader object
                    JLabel preLoaderLabel = preLoader.createPreloader(panel, 360, 450);
                    loginButton.setVisible(false);

                    // getting all value
                    String name = adminNameTextField.getText();
                    String userName = userNameTextField.getText();
                    String password = confirmPasswordNameTextField.getText();
                    String role = adminRoles[roleComboBox.getSelectedIndex()];

                    AddAdminData addAdminData = new AddAdminData(); // creating object of his class
                    int userResponse = addAdminData.getAdminRole();
                    if (userResponse == 0) // logged user role are atlasadmin
                    {
                        userResponse = addAdminData.checkUser(userName);
                        if (userResponse == 1) {
                            String sentOTP = new SRandom().getOTP();
                            String message = "Dear User<br> Your Registration in ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED for Admin OTP is <b>"
                            + sentOTP + "</b><br>Thank You";
                            try {
                                new EmpMailOperation().sendMail(message, userName);
                            } catch (Exception e) {
                            }
                            String userOTP=JOptionPane.showInputDialog(frame,"OTP is sent to his email enter OTP","OTP sent",1);
                            if(userOTP!=null)
                            {
                                // ready for insertion
                                if(addAdminData.uploadAdminData(name, userName, password, role))
                                JOptionPane.showMessageDialog(frame,"User Added Successfully");
                                else
                                JOptionPane.showMessageDialog(frame,"Server error or interrupted connection","error",0);
                            }
                        }
                        else if(userResponse==0)
                        JOptionPane.showMessageDialog(frame,"Username "+userName+" already registered in database","error",0);
                        else
                        JOptionPane.showMessageDialog(frame,"Some technical problem please try after some time","error",0);

                    }
                    else if(userResponse==1)
                    JOptionPane.showMessageDialog(frame,"Logged user is not allowed to do action","error",0);
                    else
                    JOptionPane.showMessageDialog(frame,"An error occured please try after some time","error",0);
                    
                    // visible fields
                    isTask=false;
                    preLoader.dismisPreloader(panel,preLoaderLabel);
                    loginButton.setVisible(true);
                }
            }).start();
        }   
        else
        JOptionPane.showMessageDialog(frame, "Please enter valid details","validation",0);
        }
}