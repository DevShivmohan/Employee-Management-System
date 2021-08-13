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
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class EmployeeSearchUpdate extends ScreenSize {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameTextField;
    private AllColors allColors = new AllColors();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton submitButton;
    private boolean isTask=false;

    public EmployeeSearchUpdate() {
        frame = new JFrame("Employee record updation");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel = new JPanel(new GridBagLayout());
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
        JLabel frontJLabel = new JLabel("Updation Employee Record");
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
        JLabel l3 = new JLabel(new ImageIcon(new ImageIcon(new AllIcons().getEmpWorkersIcon().getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
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
        nameTextField = new JTextField();
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
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        submitButton.setBackground(ColorUIResource.PINK);
        submitButton.setForeground(ColorUIResource.BLUE);
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setToolTipText("Submit");
        panel.add(submitButton, gbc);
        submitButton.addKeyListener(new KeyAdapter()
        {
            // by pressing enter button
            public void keyPressed(KeyEvent key){
                if(key.getKeyCode()==KeyEvent.VK_ENTER)
                handleEmployeeSearchUpdate();
            }
        });
        submitButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                handleEmployeeSearchUpdate();
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

    private void handleEmployeeSearchUpdate(){

        if (nameTextField.getText().isBlank())
        {
            nameTextField.setBackground(ColorUIResource.RED);
            JOptionPane.showMessageDialog(frame, "Please enter Employee ID or Aadhaar", "Failure", 0);
        } else
        {
            if (nameTextField.getText().length() == 15)
            {
                // Accepting value
                nameTextField.setBackground(allColors.getTextFieldBackground());
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        isTask=true;
                        gbc.gridx=0;
                        gbc.gridy=8;
                        PreLoader preLoader=new PreLoader();
                        submitButton.setVisible(false);
                        JLabel preLoaderLabel=preLoader.createPreloader(panel, gbc); // created animation
                        
                        int response=new RetrieveInfo().checkEmpUnique(nameTextField.getText());
                        if (response==0)
                        {
                            try
                            {
                                new EmployeeUpdateField(nameTextField.getText(),frame);
                            }
                            catch (Exception e){}
                       }
                       else if(response==1)
                        {
                           JOptionPane.showMessageDialog(frame,"Employee record not found","Error",0);
                            submitButton.setVisible(true);
                            preLoader.dismisPreloader(panel,preLoaderLabel);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame,"Server error or interrupted connection","Error",0);
                            submitButton.setVisible(true);
                            preLoader.dismisPreloader(panel,preLoaderLabel);
                        }
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
}
