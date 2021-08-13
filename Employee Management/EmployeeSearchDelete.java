import javax.lang.model.util.ElementScanner14;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import dboperation.AddAdminData;
import dboperation.DeleteRecord;
import dboperation.RetrieveInfo;
import icons.AllColors;
import icons.AllIcons;
import icons.PreLoader;
import icons.ScreenSize;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EmployeeSearchDelete extends ScreenSize {
    private JFrame frame;
    private boolean isTask=false;

    public EmployeeSearchDelete() {
        AllColors allColors = new AllColors();
        frame = new JFrame("Employee Record deletion");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(getFrameWidth(), getFrameHeight());
        panel.setBackground(allColors.getPanelBackground());
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setSize(getFrameWidth(), getFrameHeight());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);

        // front Heading in Employee Registration
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel frontJLabel = new JLabel("Deletion Employee Record");
        frontJLabel.setFont(new Font("verdana", Font.PLAIN, 40));
        frontJLabel.setForeground(ColorUIResource.MAGENTA);
        panel.add(frontJLabel);

        // spaces
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel l1 = new JLabel("   ");
        l1.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(l1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel l3 = new JLabel(new ImageIcon(new ImageIcon(new AllIcons().getEmpDeleteRecordIcon().getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
        l3.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(l3, gbc);

        // spaces
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel l2 = new JLabel("   ");
        l2.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(l2, gbc);

        // Employee id or aadhaar
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel nameLabel = new JLabel("Enter employee ID");
        nameLabel.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        panel.add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JTextField nameTextField = new JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameTextField.setTransferHandler(null);
        nameTextField.setToolTipText("Enter employee ID");
        nameTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent key) {
                if (Character.isLowerCase(key.getKeyChar()))
                    key.setKeyChar(Character.toUpperCase(key.getKeyChar()));

                if (nameTextField.getText().length() > 14)
                    key.consume();
            }
        });
        nameTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(nameTextField, gbc);

        // spaces
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel label1 = new JLabel("   ");
        label1.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(label1, gbc);

        // spaces
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel label2 = new JLabel("   ");
        label2.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(label2, gbc);

        // submit button
        gbc.gridx = 0;
        gbc.gridy = 8;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        submitButton.setBackground(ColorUIResource.PINK);
        submitButton.setForeground(ColorUIResource.BLUE);
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setToolTipText("Submit");
        panel.add(submitButton, gbc);
        submitButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (nameTextField.getText().isBlank())
                {
                    nameTextField.setBackground(ColorUIResource.RED);
                    JOptionPane.showMessageDialog(frame, "Please enter Employee ID", "Failure", 0);
                }
                else
                {
                    if (nameTextField.getText().length() == 15)
                    {
                        // Accepting value
                        nameTextField.setBackground(allColors.getTextFieldBackground());
                        new Thread(new Runnable() {
                            public void run()
                            {
                                isTask=true;
                                submitButton.setVisible(false);
                                PreLoader preLoader=new PreLoader();
                                JLabel preLoaderLabel=preLoader.createPreloader(panel, gbc); // created preloader animation

                                // checking user role
                                AddAdminData addAdminData=new AddAdminData();
                                int permission=addAdminData.getAdminRole(); // get logged user Role
                                if(permission==0)
                                {
                                    RetrieveInfo retrieveInfo=new RetrieveInfo();
                                    int response=retrieveInfo.checkEmpUnique(nameTextField.getText());
                                    if(response==0)
                                    {
                                        retrieveInfo.setEmpDeleteData(); // seting information
                                        String message="Employee ID - "+nameTextField.getText()+"\n";
                                        message=message+"Employee name - "+retrieveInfo.getEmpName()+"\n";
                                        message=message+"Father name - "+retrieveInfo.getEmpFather()+"\n";
                                        message=message+"Date of Birth - "+retrieveInfo.getEmpDob().charAt(0)+retrieveInfo.getEmpDob().charAt(1)+"/"+retrieveInfo.getEmpDob().charAt(2)+retrieveInfo.getEmpDob().charAt(3)+"/"+retrieveInfo.getEmpDob().charAt(4)+retrieveInfo.getEmpDob().charAt(5)+retrieveInfo.getEmpDob().charAt(6)+retrieveInfo.getEmpDob().charAt(7)+"\n";
                                        message=message+"Aadhaar number - "+retrieveInfo.getEmpAadhaar()+"\n";
                                        message=message+"Email - "+retrieveInfo.getEmpEmail()+"\n\n\n\n";
                                        message=message+"Are you sure to confirm delete this record from database ?";
                                        if((JOptionPane.showConfirmDialog(frame, message))==0)
                                        {
                                            if(new DeleteRecord().deleteRecord(nameTextField.getText(),retrieveInfo.getEmpEmail(),retrieveInfo.getEmpName()))
                                            JOptionPane.showMessageDialog(frame,"Employee record deleted Successfully from database","Success",JOptionPane.INFORMATION_MESSAGE);
                                            else
                                            JOptionPane.showMessageDialog(frame,"Employee record deletion error","Error",0);
                                        }
                                    }
                                    else if(response==1)
                                    JOptionPane.showMessageDialog(frame,"Employee record not found","Error",0);
                                    else
                                    JOptionPane.showMessageDialog(frame,"Server error or interrupted connection","Error",0);
                                }
                                else if(permission==1)
                                JOptionPane.showMessageDialog(frame,"Logged user is not allowed to do action","error",0);
                                else
                                JOptionPane.showMessageDialog(frame,"An error occured please try after some time","error",0);

                                preLoader.dismisPreloader(panel,preLoaderLabel);
                                submitButton.setVisible(true);
                                isTask=false;
                            }
                        }).start();
                    }
                    else
                    {
                        nameTextField.setBackground(ColorUIResource.RED);
                        JOptionPane.showMessageDialog(frame,"Please enter valid Employee ID","Failure",0);
                    }
                }
            }
        });

        // back to home
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                if(isTask)
                JOptionPane.showMessageDialog(frame,"Please wait for Task finshing","Warning",JOptionPane.WARNING_MESSAGE);
                else
                {
                    frame.removeAll();
                    frame.setVisible(false);
                    new MainGUI();
                } 
            }
        });

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
