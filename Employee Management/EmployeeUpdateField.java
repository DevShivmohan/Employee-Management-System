import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Insets;


import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.plaf.ColorUIResource;

import dboperation.RetrieveInfo;
import dboperation.UpdateData;
import icons.AllIcons;
import icons.PreLoader;
import icons.ScreenSize;
import icons.AllColors;
public class EmployeeUpdateField extends ScreenSize
{
    private JFrame frame;
    private File photoFile=null;
    private File sigFile=null;
    private String jobProfileString;
    private long AMMOUNT;
    private boolean isTask=false;

    
    public EmployeeUpdateField(String id,JFrame tempFrame) throws Exception
    {
        RetrieveInfo retrieveInfo=new RetrieveInfo();
        
        RegisterValidation validation=new RegisterValidation();
        AllColors allColors=new AllColors();
        frame=new JFrame("Updation Employee Record");
        frame.setSize(getFrameWidth(),getFrameHeight());
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());

        
        retrieveInfo.setEmpInfo(id); //seting all data

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(5,15,15,20);

        JPanel panel=new JPanel(new GridBagLayout());
        panel.setSize(getFrameWidth(),getFrameHeight());
        panel.setBackground(allColors.getPanelBackground());
        JScrollPane scrollPane=new JScrollPane(panel);
        scrollPane.setSize(getFrameWidth(),getFrameHeight());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);
        // front Heading in Employee Registration
        gbc.gridx=0;
        gbc.gridy=0;
        JLabel frontJLabel=new JLabel("Updation Employee Record");
        frontJLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,40));
        frontJLabel.setForeground(ColorUIResource.CYAN);
        panel.add(frontJLabel);

        //spaces 
        gbc.gridx=0;gbc.gridy=1;
        JLabel l1=new JLabel("   ");
        l1.setFont(new Font("verdana",Font.PLAIN,25));
        panel.add(l1,gbc);

        gbc.gridx=0;gbc.gridy=2;
        JLabel l2=new JLabel(new ImageIcon(new ImageIcon(new AllIcons().getEmpEditIcon().getAbsolutePath()).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
        l2.setFont(new Font("verdana",Font.PLAIN,25));
        panel.add(l2,gbc);

        // Employee Name id
        gbc.gridx=0;
        gbc.gridy=3;
        JLabel idLabel=new JLabel("Employee ID");
        idLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        idLabel.setSize(300,30);
        panel.add(idLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=4;
        JTextField idTextField=new JTextField();
        idTextField.setText(id);
        idTextField.setEditable(false);
        idTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        idTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(idTextField,gbc);

        // Employee Name field
        gbc.gridx=0;
        gbc.gridy=5;
        JLabel nameLabel=new JLabel("Employee Name");
        nameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        nameLabel.setSize(300,30);
        panel.add(nameLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        JTextField nameTextField=new JTextField();
        nameTextField.setText(retrieveInfo.getEmpName());
        nameTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        // nameTextField.setTransferHandler(null);
        nameTextField.setToolTipText("Employee name length Max 50 and Min 3 character");
        nameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(Character.isDigit(key.getKeyChar()))
                    key.consume();

                if(Character.isLowerCase(key.getKeyChar()))
                    key.setKeyChar(Character.toUpperCase(key.getKeyChar()));
                    
                if(nameTextField.getText().length()>50)
                    key.consume();
            }
        });
        nameTextField.setSize(400,30);
        nameTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(nameTextField,gbc);

        // Employee Father name Field
        gbc.gridx=0;
        gbc.gridy=7;
        JLabel fNameLabel=new JLabel("Father Name");
        fNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        fNameLabel.setSize(300,30);
        panel.add(fNameLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=8;
        JTextField fNameTextField=new JTextField();
        fNameTextField.setText(retrieveInfo.getEmpFather());
        fNameTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        fNameTextField.setSize(300,30);
        fNameTextField.setTransferHandler(null);
        fNameTextField.setToolTipText("Employee father name length Max 50 and Min 3 character");
        fNameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(Character.isDigit(key.getKeyChar()))
                    key.consume();

                if(Character.isLowerCase(key.getKeyChar()))
                    key.setKeyChar(Character.toUpperCase(key.getKeyChar()));
                    
                if(fNameTextField.getText().length()>50)
                    key.consume();
            }
        });
        fNameTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(fNameTextField,gbc);

        // Employee dob field
        gbc.gridx=0;
        gbc.gridy=9;
        JLabel dobLabel=new JLabel("Date of Birth");
        dobLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        dobLabel.setSize(300,30);
        panel.add(dobLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=10;
        JTextField dobTextField=new JTextField();
        dobTextField.setText(retrieveInfo.getEmpDob());
        dobTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        dobTextField.setSize(300,30);
        dobTextField.setTransferHandler(null);
        dobTextField.setToolTipText("DOB format DDMMYYYY");
        dobTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(!Character.isDigit(key.getKeyChar()))
                    key.consume();
                    
                if(dobTextField.getText().length()>7)
                    key.consume();
            }
        });
        dobTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(dobTextField,gbc);

        // Employee Gender field
        gbc.gridx=0;
        gbc.gridy=11;
        JLabel genderLabel=new JLabel("Employee Gender");
        genderLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        genderLabel.setSize(300,30);
        panel.add(genderLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=12;
        String[] empGenderArray={"Male","Female","Transgender"};
        JComboBox genderComboBox=new JComboBox(empGenderArray);
        genderComboBox.setFont(new Font("Tahoma",Font.PLAIN,20));
        genderComboBox.setSize(300,30);
        genderComboBox.setTransferHandler(null);
        genderComboBox.setForeground(allColors.getTextFieldBackground());
        genderComboBox.setBackground(allColors.getTextFieldBackground());
        String gender=retrieveInfo.getEmpGender();

        if(gender.equalsIgnoreCase("Male"))
        genderComboBox.setSelectedIndex(0);
        if(gender.equalsIgnoreCase("Female"))
        genderComboBox.setSelectedIndex(1);
        if(gender.equalsIgnoreCase("Transgender"))
        genderComboBox.setSelectedIndex(2);


        panel.add(genderComboBox,gbc);

        // Employee Mobile field
        gbc.gridx=0;
        gbc.gridy=13;
        JLabel mobileLabel=new JLabel("Mobile");
        mobileLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        mobileLabel.setSize(300,30);
        panel.add(mobileLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=14;
        JTextField mobileTextField=new JTextField();
        mobileTextField.setText(retrieveInfo.getEmpMobile());
        mobileTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        mobileTextField.setSize(300,30);
        mobileTextField.setTransferHandler(null);
        mobileTextField.setToolTipText("Mobile Number only 10 digits");
        mobileTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(!Character.isDigit(key.getKeyChar()))
                    key.consume();
                    
                if(mobileTextField.getText().length()>9)
                    key.consume();
            }
        });
        mobileTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(mobileTextField,gbc);

        // Employee Aadhaar field
        gbc.gridx=0;
        gbc.gridy=15;
        JLabel aadhaarLabel=new JLabel("Aadhaar");
        aadhaarLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        aadhaarLabel.setSize(300,30);
        panel.add(aadhaarLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=16;
        JTextField aadhaarTextField=new JTextField();
        aadhaarTextField.setText(retrieveInfo.getEmpAadhaar());
        aadhaarTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        aadhaarTextField.setSize(300,30);
        aadhaarTextField.setTransferHandler(null);
        aadhaarTextField.setToolTipText("Aadhaar Number only 12 digits");
        aadhaarTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(!Character.isDigit(key.getKeyChar()))
                    key.consume();
                    
                if(aadhaarTextField.getText().length()>11)
                    key.consume();
            }
        });
        aadhaarTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(aadhaarTextField,gbc);

        // Employee Email field
        gbc.gridx=0;
        gbc.gridy=17;
        JLabel emailLabel=new JLabel("Email");
        emailLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        emailLabel.setSize(300,30);
        panel.add(emailLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=18;
        JTextField emailTextField=new JTextField();
        emailTextField.setText(retrieveInfo.getEmpEmail());
        emailTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        emailTextField.setSize(300,30);
        emailTextField.setTransferHandler(null);
        emailTextField.setToolTipText("Email must be @,mail.com and length Max 60 and Min 12 character needed");
        emailTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(emailTextField.getText().length()>59)
                    key.consume();
            }
        });
        emailTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(emailTextField,gbc);

        // Employee Address field
        gbc.gridx=0;
        gbc.gridy=19;
        JLabel addressLabel=new JLabel("Permanent Address");
        addressLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        addressLabel.setSize(300,30);
        panel.add(addressLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=20;
        JTextField addressTextField=new JTextField();
        addressTextField.setText(retrieveInfo.getEmpAddress());
        addressTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        addressTextField.setSize(300,30);
        addressTextField.setTransferHandler(null);
        addressTextField.setToolTipText("Address must be Max 100 and Min 5 character");
        addressTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(addressTextField.getText().length()>99)
                    key.consume();

                if(Character.isLowerCase(key.getKeyChar()))
                    key.setKeyChar(Character.toUpperCase(key.getKeyChar()));
            }
        });
        addressTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(addressTextField,gbc);

        // Employee Account number field
        gbc.gridx=0;
        gbc.gridy=21;
        JLabel accountLabel=new JLabel("Account Number");
        accountLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        accountLabel.setSize(300,30);
        panel.add(accountLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=22;
        JTextField accountTextField=new JTextField();
        accountTextField.setText(retrieveInfo.getEmpAccount());
        accountTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        accountTextField.setSize(300,30);
        accountTextField.setTransferHandler(null);
        accountTextField.setToolTipText("Your Bank Details Account Number length Max 25 and Min 10 digit");
        accountTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(!Character.isDigit(key.getKeyChar()))
                    key.consume();

                if(accountTextField.getText().length()>25)
                    key.consume();
            }
        });
        accountTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(accountTextField,gbc);

        // Employee Bank IFSC code field
        gbc.gridx=0;
        gbc.gridy=23;
        JLabel ifscLabel=new JLabel("Bank IFSC code");
        ifscLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        ifscLabel.setSize(300,30);
        panel.add(ifscLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=24;
        JTextField ifscTextField=new JTextField();
        ifscTextField.setText(retrieveInfo.getEmpIfsc());
        ifscTextField.setFont(new Font("Tahoma",Font.PLAIN,20));
        ifscTextField.setSize(300,30);
        ifscTextField.setTransferHandler(null);
        ifscTextField.setToolTipText("Your Bank IFSC code length Max 25 and Min 8 Character");
        ifscTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(Character.isLowerCase(key.getKeyChar()))
                    key.setKeyChar(Character.toUpperCase(key.getKeyChar()));

                if(ifscTextField.getText().length()>25)
                    key.consume();
            }
        });
        ifscTextField.setBackground(allColors.getTextFieldBackground());
        panel.add(ifscTextField,gbc);
        
        // Employee select photo field
        gbc.gridx=0;
        gbc.gridy=25;
        JLabel photoLabel=new JLabel();
        photoLabel.setIcon(new ImageIcon(new ImageIcon(retrieveInfo.getPhotoBytes().getAbsolutePath()).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
        panel.add(photoLabel,gbc);

        gbc.gridx=0;
        gbc.gridy=26;
        JButton selectImgButton=new JButton("Choose Photo");
        selectImgButton.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        selectImgButton.setSize(300,30);
        selectImgButton.setBackground(allColors.getImgButtonBackground());
        selectImgButton.setForeground(allColors.getImgButtonForeground());
        selectImgButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectImgButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        selectImgButton.setToolTipText("Photo Size accepted only 10KB to 30 KB and File format JPG,JPEG,PNG only");
        selectImgButton.addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent me)
                {
                    FileNameExtensionFilter fileNameExtensionFilter=new FileNameExtensionFilter("Select Your Photo","jpg","jpeg","png");
                    JFileChooser fileChooser=new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.setMultiSelectionEnabled(false);
                    fileChooser.setFileFilter(fileNameExtensionFilter);
                    fileChooser.setSize(800,500);
                    int result=fileChooser.showOpenDialog(frame);
                    if(result==JFileChooser.APPROVE_OPTION)
                    {
                        File file=fileChooser.getSelectedFile();
                        if(file.length()>=10240 && file.length()<=30720)
                        {
                            photoFile=file;
                            photoLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
                        }
                        else JOptionPane.showMessageDialog(frame,"File size must be Min 10 and Max 30KB","Validation Error",0);
                    }
                }
            }
        );
        panel.add(selectImgButton,gbc);

        // Employee select photo field
        gbc.gridx=0;
        gbc.gridy=27;
        JLabel sigLabel=new JLabel();
        sigLabel.setIcon(new ImageIcon(new ImageIcon(retrieveInfo.getSigBytes().getAbsolutePath()).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
        panel.add(sigLabel,gbc);

        retrieveInfo.getPhotoBytes().delete();
        retrieveInfo.getSigBytes().delete();

        gbc.gridx=0;
        gbc.gridy=28;
        JButton selectSigButton=new JButton("Choose Signature");
        selectSigButton.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        selectSigButton.setSize(300,30);
        selectSigButton.setBackground(allColors.getImgButtonBackground());
        selectSigButton.setForeground(allColors.getImgButtonForeground());
        selectSigButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        selectSigButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectSigButton.setToolTipText("Signature Size accepted only 10KB to 30 KB and File format JPG,JPEG,PNG only");
        selectSigButton.addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent me)
                {
                    FileNameExtensionFilter fileNameExtensionFilter=new FileNameExtensionFilter("Select Your Photo","jpg","jpeg","png");
                    JFileChooser fileChooser=new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.setMultiSelectionEnabled(false);
                    fileChooser.setFileFilter(fileNameExtensionFilter);
                    fileChooser.setSize(800,500);
                    int result=fileChooser.showOpenDialog(frame);
                    if(result==JFileChooser.APPROVE_OPTION)
                    {
                        File file=fileChooser.getSelectedFile();
                        if(file.length()>=10240 && file.length()<=30720)
                        {
                            sigFile=file;
                            sigLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
                        }
                        else JOptionPane.showMessageDialog(frame,"Signature File size must be Min 10 and Max 30KB","Validation Error",0);
                    }
                }
            }
        );
        panel.add(selectSigButton,gbc);

        //spaces
        gbc.gridx=0;gbc.gridy=29;
        JLabel spaceLabel1=new JLabel("     ");
        spaceLabel1.setFont(new Font("verdana",Font.PLAIN,30));
        panel.add(spaceLabel1,gbc);

        //spaces
        gbc.gridx=0;gbc.gridy=30;
        JLabel spaceLabel2=new JLabel("     ");
        spaceLabel2.setFont(new Font("verdana",Font.PLAIN,30));
        panel.add(spaceLabel2,gbc);

        gbc.gridx=0;gbc.gridy=31;
        JButton submitButton=new JButton("Update");
        submitButton.setFont(new Font("tahoma",Font.CENTER_BASELINE,22));
        submitButton.setBackground(new ColorUIResource(22,11,22));
        submitButton.setForeground(ColorUIResource.BLUE);
        submitButton.setToolTipText("When you have press Submit then you are not eligible for any changes");
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                boolean a1=validation.isName(nameTextField);
                boolean a2=validation.isFatherName(fNameTextField);
                boolean a3=validation.isDOB(dobTextField);
                boolean a4=validation.isMobile(mobileTextField);
                boolean a5=validation.isAadhaar(aadhaarTextField);
                boolean a6=validation.isEmail(emailTextField);
                boolean a7=validation.isAddress(addressTextField);
                boolean a8=validation.isAccount(accountTextField);
                boolean a9=validation.isIFSC(ifscTextField);


                if(a1 && a2 && a3 && a4 && a5 && a6 && a7 && a8 && a9)
                {
                    // all data acceptable
                    new Thread(new Runnable(){
                        public void run()
                        {
                            isTask=true;
                            gbc.gridx=0;
                            gbc.gridy=31;
                            submitButton.setVisible(false);
                            PreLoader preLoader=new PreLoader();
                            JLabel preLoderLabel=preLoader.createPreloader(panel,gbc); // created preloader

                            UpdateData uploadData=new UpdateData();
                            uploadData.setEmpId(id);
                            uploadData.setEmpAadhaar(aadhaarTextField.getText()); //aadhaar
                            int checkResult=uploadData.checkUpdateEmployee(id,aadhaarTextField.getText());
                            if(checkResult==1)
                            {
                                JOptionPane.showMessageDialog(frame,"Employee Aadhaar will be matched from another employee record\nPlease provide another Aadhaar number","error",0);
                                submitButton.setVisible(true);
                                preLoader.dismisPreloader(panel,preLoderLabel);
                            }
                            else
                            {
                                uploadData.setEmpName(nameTextField.getText()); //name
                                uploadData.setEmpFatherName(fNameTextField.getText()); //father
                                uploadData.setEmpDob(dobTextField.getText()); // dob
                                uploadData.setEmpGender(empGenderArray[genderComboBox.getSelectedIndex()]); // gender
                                uploadData.setEmpMobile(mobileTextField.getText()); //mobile
                                uploadData.setEmpEmail(emailTextField.getText()); //email
                                uploadData.setEmpAddress(addressTextField.getText()); //address
                                uploadData.setEmpAccount(accountTextField.getText()); //account
                                uploadData.setEmpIfsc(ifscTextField.getText()); //ifsc
                                uploadData.setEmpPhotoFile(photoFile);
                                uploadData.setEmpSigFile(sigFile);

                                if(uploadData.updateEmpData())
                                {
                                    JOptionPane.showMessageDialog(tempFrame,"Employee Record will be updated successfully");
                                    submitButton.setVisible(true);
                                    preLoader.dismisPreloader(panel,preLoderLabel);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(tempFrame,"Server error or internet connection interrupted","error",0);
                                    submitButton.setVisible(true);
                                    preLoader.dismisPreloader(panel,preLoderLabel);
                                }
                            }
                            isTask=false;

                        }
                    }).start();
                }
                else JOptionPane.showMessageDialog(frame,"Please enter valid details where are indecates res colors","Validation",0);
            }
        });
        panel.add(submitButton,gbc);

        // back to Search update
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
                    new EmployeeSearchUpdate();
                } // calling Search update
            }
        });

        //spaces
        gbc.gridx=0;gbc.gridy=33;
        JLabel spaceLabel3=new JLabel("     ");
        spaceLabel3.setFont(new Font("verdana",Font.PLAIN,30));
        panel.add(spaceLabel3,gbc);

        //spaces
        gbc.gridx=0;gbc.gridy=34;
        JLabel spaceLabel4=new JLabel("     ");
        spaceLabel4.setFont(new Font("verdana",Font.PLAIN,30));
        panel.add(spaceLabel4,gbc);

        tempFrame.removeAll();
        tempFrame.setVisible(false); // previous frame
        frame.setVisible(true); // Update field frame
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }    
}