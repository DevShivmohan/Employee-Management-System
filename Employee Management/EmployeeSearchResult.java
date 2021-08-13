import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.ColorUIResource;

import dboperation.RetrieveInfo;
import icons.AllColors;
import icons.AllIcons;
import icons.ScreenSize;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EmployeeSearchResult extends ScreenSize
{
    private JFrame frame;

    public EmployeeSearchResult(String id, JFrame animationFrame)
    {
        RetrieveInfo retrieveInfo = new RetrieveInfo();
        retrieveInfo.setEmpInfo(id); // set all data

        AllColors allColors = new AllColors();

        frame = new JFrame("Employee Information");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(null);
        frame.getContentPane().setBackground(allColors.getFrameBackground());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 15, 15);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(getFrameWidth(), getFrameHeight());
        panel.setBackground(allColors.getPanelBackground());
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setSize(getFrameWidth(), getFrameHeight());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);

        // front Heading in Employee Registration
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel frontJLabel = new JLabel("Employee Searched Information");
        frontJLabel.setFont(new Font("verdana", Font.HANGING_BASELINE, 40));
        frontJLabel.setForeground(ColorUIResource.CYAN);
        panel.add(frontJLabel);

        // spaces
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel l1 = new JLabel("   ");
        l1.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(l1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel l2 = new JLabel(new ImageIcon(new ImageIcon(new AllIcons().getEmpSearchIcon().getAbsolutePath())
                .getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
        l2.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(l2, gbc);

        // spaces
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel spaceLabel = new JLabel("   ");
        spaceLabel.setFont(new Font("verdana", Font.PLAIN, 25));
        panel.add(spaceLabel, gbc);

        // Employee Photo
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel photoLabel1 = new JLabel("Employee Photo");
        photoLabel1.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        photoLabel1.setForeground(ColorUIResource.BLUE);
        panel.add(photoLabel1, gbc);

        try {
            gbc.gridx = 1;
            gbc.gridy = 4;
            JLabel photoLabel = new JLabel(new ImageIcon(new ImageIcon(retrieveInfo.getPhotoBytes().getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            panel.add(photoLabel, gbc);
        } catch (Exception e) {
        }

        // Employee Signature

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel sigLabel1 = new JLabel("Employee Signature");
        sigLabel1.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        sigLabel1.setForeground(ColorUIResource.BLUE);
        panel.add(sigLabel1, gbc);

        try {
            gbc.gridx = 1;
            gbc.gridy = 5;
            JLabel sigLabel = new JLabel(new ImageIcon(new ImageIcon(retrieveInfo.getSigBytes().getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            panel.add(sigLabel, gbc);
        } catch (Exception e) {
        }

        try
        {
            retrieveInfo.getPhotoBytes().delete();
            retrieveInfo.getSigBytes().delete();
        } catch (Exception e) {}
        

        // Employee id
        gbc.gridx=0;
        gbc.gridy=6;
        JLabel idLabel=new JLabel("Employee ID");
        idLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        idLabel.setForeground(ColorUIResource.BLUE);
        panel.add(idLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=6;
        JLabel idTextField=new JLabel();
        idTextField.setText(id);
        idTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        idTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(idTextField,gbc);

        // Employee Name
        gbc.gridx=0;
        gbc.gridy=7;
        JLabel nameLabel=new JLabel("Employee Name");
        nameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        nameLabel.setForeground(ColorUIResource.BLUE);
        panel.add(nameLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=7;
        JLabel nameTextField=new JLabel();
        nameTextField.setText(retrieveInfo.getEmpName());
        nameTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        nameTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(nameTextField,gbc);

        // Employee father Name
        gbc.gridx=0;
        gbc.gridy=8;
        JLabel fNameLabel=new JLabel("Employee Father Name");
        fNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        fNameLabel.setForeground(ColorUIResource.BLUE);
        panel.add(fNameLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=8;
        JLabel fNameTextField=new JLabel();
        fNameTextField.setText(retrieveInfo.getEmpFather());
        fNameTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        fNameTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(fNameTextField,gbc);

        // Employee dob
        gbc.gridx=0;
        gbc.gridy=9;
        JLabel dobLabel=new JLabel("Date of Birth");
        dobLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        dobLabel.setForeground(ColorUIResource.BLUE);
        panel.add(dobLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=9;
        String empDob=retrieveInfo.getEmpDob();
        empDob=""+empDob.charAt(0)+empDob.charAt(1)+"/"+empDob.charAt(2)+empDob.charAt(3)+"/"+empDob.charAt(4)+empDob.charAt(5)+empDob.charAt(6)+empDob.charAt(7);
        JLabel dobTextField=new JLabel();
        dobTextField.setText(empDob);
        dobTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        dobTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(dobTextField,gbc);

        // Employee gender
        gbc.gridx=0;
        gbc.gridy=10;
        JLabel genderLabel=new JLabel("Gender");
        genderLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        genderLabel.setForeground(ColorUIResource.BLUE);
        panel.add(genderLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=10;
        JLabel genderTextField=new JLabel();
        genderTextField.setText(retrieveInfo.getEmpGender());
        genderTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        genderTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(genderTextField,gbc);

        // Employee mobile
        gbc.gridx=0;
        gbc.gridy=11;
        JLabel mobileLabel=new JLabel("Mobile");
        mobileLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        mobileLabel.setForeground(ColorUIResource.BLUE);
        panel.add(mobileLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=11;
        JLabel mobileTextField=new JLabel();
        mobileTextField.setText(retrieveInfo.getEmpMobile());
        mobileTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        mobileTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(mobileTextField,gbc);

        // Employee aadhaar
        gbc.gridx=0;
        gbc.gridy=12;
        JLabel aadhaarLabel=new JLabel("Aadhaar Number");
        aadhaarLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        aadhaarLabel.setForeground(ColorUIResource.BLUE);
        panel.add(aadhaarLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=12;
        JLabel aadhaarTextField=new JLabel();
        aadhaarTextField.setText(retrieveInfo.getEmpAadhaar());
        aadhaarTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        aadhaarTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(aadhaarTextField,gbc);

        // Employee email
        gbc.gridx=0;
        gbc.gridy=13;
        JLabel emailLabel=new JLabel("Email");
        emailLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        emailLabel.setForeground(ColorUIResource.BLUE);
        panel.add(emailLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=13;
        JLabel emailTextField=new JLabel();
        emailTextField.setText(retrieveInfo.getEmpEmail());
        emailTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        emailTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(emailTextField,gbc);

        // Employee address
        gbc.gridx=0;
        gbc.gridy=14;
        JLabel addressLabel=new JLabel("Address");
        addressLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        addressLabel.setForeground(ColorUIResource.BLUE);
        panel.add(addressLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=14;
        JLabel addressTextField=new JLabel();
        addressTextField.setText(retrieveInfo.getEmpAddress());
        addressTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        addressTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(addressTextField,gbc);

        // Employee account
        gbc.gridx=0;
        gbc.gridy=15;
        JLabel accountLabel=new JLabel("Account Number");
        accountLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        accountLabel.setForeground(ColorUIResource.BLUE);
        panel.add(accountLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=15;
        JLabel accountTextField=new JLabel();
        accountTextField.setText(retrieveInfo.getEmpAccount());
        accountTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        accountTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(accountTextField,gbc);

        // Employee ifsc code
        gbc.gridx=0;
        gbc.gridy=16;
        JLabel ifscLabel=new JLabel("IFSC code");
        ifscLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        ifscLabel.setForeground(ColorUIResource.BLUE);
        panel.add(ifscLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=16;
        JLabel ifscTextField=new JLabel();
        ifscTextField.setText(retrieveInfo.getEmpIfsc());
        ifscTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        ifscTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(ifscTextField,gbc);
        
        // Employee post
        gbc.gridx=0;
        gbc.gridy=17;
        JLabel postLabel=new JLabel("Job Profile");
        postLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        postLabel.setForeground(ColorUIResource.BLUE);
        panel.add(postLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=17;
        JLabel postTextField=new JLabel();
        postTextField.setText(retrieveInfo.getEmpPost());
        postTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        postTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(postTextField,gbc);

        // Employee init salary
        gbc.gridx=0;
        gbc.gridy=18;
        JLabel salaryLabel=new JLabel("Initiate Salary");
        salaryLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        salaryLabel.setForeground(ColorUIResource.BLUE);
        panel.add(salaryLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=18;
        JLabel salaryTextField=new JLabel();
        salaryTextField.setText("Rs. "+retrieveInfo.getEmpSalary()+"/");
        salaryTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        salaryTextField.setForeground(ColorUIResource.MAGENTA);
        panel.add(salaryTextField,gbc);

        //space
        gbc.gridx=0;
        gbc.gridy=19;
        JLabel spaceLabel6=new JLabel("");
        spaceLabel6.setFont(new Font("arial",Font.CENTER_BASELINE,25));
        panel.add(spaceLabel6,gbc);

        // Back to search another page
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                frame.removeAll();
                frame.setVisible(false);
                new EmployeeSearch();
            }
        });

        //space
        gbc.gridx=0;
        gbc.gridy=21;
        JLabel spaceLabel2=new JLabel("");
        spaceLabel2.setFont(new Font("arial",Font.CENTER_BASELINE,25));
        panel.add(spaceLabel2,gbc);

        //space
        gbc.gridx=0;
        gbc.gridy=22;
        JLabel spaceLabel3=new JLabel("");
        spaceLabel3.setFont(new Font("arial",Font.CENTER_BASELINE,25));
        panel.add(spaceLabel3,gbc);

        //space
        gbc.gridx=0;
        gbc.gridy=23;
        JLabel spaceLabel4=new JLabel("");
        spaceLabel4.setFont(new Font("arial",Font.CENTER_BASELINE,25));
        panel.add(spaceLabel4,gbc);

        //space
        gbc.gridx=0;
        gbc.gridy=24;
        JLabel spaceLabel5=new JLabel("");
        spaceLabel5.setFont(new Font("arial",Font.CENTER_BASELINE,25));
        panel.add(spaceLabel5,gbc);

        animationFrame.removeAll();
        animationFrame.setVisible(false);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}