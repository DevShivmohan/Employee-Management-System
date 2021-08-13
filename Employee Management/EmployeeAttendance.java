import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractButton;
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

import dboperation.AttendanceData;
import icons.AllColors;
import icons.AllIcons;
import icons.ScreenSize;
import validation.AllFieldValidation;
public class EmployeeAttendance extends ScreenSize{
    private JFrame frame;
    private JPanel panel;
    private boolean isTask=false;
    private AttendanceData attendanceData;
    public EmployeeAttendance(){
        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(25,5,5,5);

        frame=new JFrame("Employee Accounting Management");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        // main panel
        gbc.gridx=0;
        gbc.gridy=0;
        panel=new JPanel(null);
        panel.setBackground(allColors.getPanelBackground());
        panel.setPreferredSize(new Dimension(800,600));
        JScrollPane scrollPane=new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        frame.add(scrollPane,gbc);

        
        // Accounting heading
        JButton frontLabel=new JButton("Employee Attendance Management",new ImageIcon(new ImageIcon(allIcons.getAccountingIcon().getAbsolutePath()).getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        frontLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,22));
        frontLabel.setForeground(ColorUIResource.MAGENTA);
        frontLabel.setBackground(allColors.getPanelBackground());
        frontLabel.setBorderPainted(false);
        frontLabel.setVerticalTextPosition(AbstractButton.BOTTOM);
        frontLabel.setHorizontalTextPosition(AbstractButton.CENTER);
        frontLabel.setBounds(200,10,400,150);
        panel.add(frontLabel);

        // Employee id field
        JLabel empIdLabel=new JLabel("Employee ID");
        empIdLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        empIdLabel.setBounds(125,270,200,35);
        panel.add(empIdLabel);
        
        JTextField empIdTextField=new JTextField();
        empIdTextField.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        empIdTextField.setBackground(ColorUIResource.CYAN);
        empIdTextField.setForeground(ColorUIResource.BLACK);
        empIdTextField.setBounds(375,270,300,30);
        empIdTextField.setTransferHandler(null);
        empIdTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(!(Character.isLetterOrDigit(key.getKeyChar())))
                key.consume();

                if(Character.isLowerCase(key.getKeyChar()))
                key.setKeyChar(Character.toUpperCase(key.getKeyChar()));

                if(empIdTextField.getText().length()>14)
                key.consume();
            }
        });
        panel.add(empIdTextField);

        // add user button
        JButton loginButton=new JButton("Submit");
        loginButton.setBounds(300,450,90,30);
        loginButton.setToolTipText("Prees and Present");
        loginButton.setFont(new Font("verdana",Font.CENTER_BASELINE,18));
        loginButton.setBackground(ColorUIResource.CYAN);
        loginButton.setForeground(ColorUIResource.BLUE);
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(loginButton);
        loginButton.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me){
                AllFieldValidation allFieldValidation=new AllFieldValidation();
                if(allFieldValidation.isValidEmpId(empIdTextField)){
                    if(attendanceData==null){
                        AttendanceData attendanceDataCopy=new AttendanceData();
                        attendanceData=attendanceDataCopy;
                    }
                }else
                JOptionPane.showMessageDialog(null ,"Please enter valid Employee ID","validation",0);
            }
        });

        // delete button
        JButton deleteButton=new JButton("Delete");
        deleteButton.setBounds(400,450,90,30);
        deleteButton.setToolTipText("Prees and Delete Present");
        deleteButton.setFont(new Font("verdana",Font.CENTER_BASELINE,18));
        deleteButton.setBackground(ColorUIResource.CYAN);
        deleteButton.setForeground(ColorUIResource.BLUE);
        deleteButton.setBorder(BorderFactory.createEtchedBorder());
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(deleteButton);
        deleteButton.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me){
                AllFieldValidation allFieldValidation=new AllFieldValidation();
                if(allFieldValidation.isValidEmpId(empIdTextField)){
                    if(attendanceData==null){
                        AttendanceData attendanceDataCopy=new AttendanceData();
                        attendanceData=attendanceDataCopy;
                    }
                }else
                JOptionPane.showMessageDialog(null ,"Please enter valid Employee ID","validation",0);
            }
        });

        // Print Button
        JButton printButton=new JButton("Print");
        printButton.setBounds(705,100,80,25);
        printButton.setFont(new Font("verdana",Font.CENTER_BASELINE,14));
        printButton.setBackground(ColorUIResource.CYAN);
        printButton.setForeground(ColorUIResource.BLUE);
        printButton.setToolTipText("Print Attendance List");
        printButton.setBorder(BorderFactory.createEtchedBorder());
        printButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(printButton);
        printButton.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me){
                AllFieldValidation allFieldValidation=new AllFieldValidation();
                if(allFieldValidation.isValidEmpId(empIdTextField)){

                }else
                JOptionPane.showMessageDialog(null ,"Please enter valid Employee ID","validation",0);
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
                    // frame.removeAll();
                    // frame.setVisible(false);
                    // new MainGUI();
                    System.exit(0);
                } 
            }
        });

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }  
}
