// import java.awt.Font;
// import java.awt.Image;

// import javax.swing.ImageIcon;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JPasswordField;
// import javax.swing.JTextField;
// import javax.swing.plaf.ColorUIResource;
// import java.awt.GridBagConstraints;
// import java.awt.GridBagLayout;
// import java.awt.Insets;

// import icons.AllColors;
// import icons.AllIcons;
// import icons.ScreenSize;

// import java.awt.event.WindowAdapter;
// import java.awt.event.WindowEvent;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import dboperation.AdminInfo;
import empmail.EmpMailOperation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import icons.AllColors;
import icons.AllIcons;
import icons.FileStructure;
import icons.PreLoader;
import icons.ScreenSize;
import security.SRandom;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
public class AdminUserAccount extends ScreenSize
{
    private JFrame frame;
    private JPanel panel;
    private boolean isTask=false;
    private String name,userName,role,datetime;
    public AdminUserAccount()
    {
        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();
        PreLoader preLoader=new PreLoader();
        setAllValue(); // setting all value of admin

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(25,5,5,5);

        frame=new JFrame("Admin Account Holder Information");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        gbc.gridx=0;
        gbc.gridy=0;
        panel=new JPanel(null);
        panel.setPreferredSize(new Dimension(800,600));
        panel.setBackground(allColors.getPanelBackground());

        //front user icon
        JLabel infoSys=new JLabel(new ImageIcon(new ImageIcon(allIcons.getLoginUserIcon().getAbsolutePath()).getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        infoSys.setBounds(350,5,100,100);
        panel.add(infoSys);

        // front label
        JLabel frontLoginLabel=new JLabel("System Admin Profile Updation");
        frontLoginLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,30));
        frontLoginLabel.setForeground(ColorUIResource.MAGENTA);
        frontLoginLabel.setBounds(180,115,500,35);
        panel.add(frontLoginLabel);

        // name
        JLabel adminNameLabel=new JLabel("Admin Name");
        adminNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        adminNameLabel.setForeground(ColorUIResource.BLUE);
        adminNameLabel.setBounds(50,200,200,35);
        panel.add(adminNameLabel);

        JTextField adminNameTextField=new JTextField();
        adminNameTextField.setBounds(300,200,300,30);
        adminNameTextField.setText(name);
        adminNameTextField.setToolTipText("Enter your name");
        adminNameTextField.setTransferHandler(null);
        adminNameTextField.setBackground(ColorUIResource.CYAN);
        adminNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        adminNameTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        adminNameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                if(adminNameTextField.getText().length()>40)
                ke.consume();
            }
        });
        panel.add(adminNameTextField);

        // username
        JLabel userNameLabel=new JLabel("Username");
        userNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        userNameLabel.setForeground(ColorUIResource.BLUE);
        userNameLabel.setBounds(50,250,200,35);
        panel.add(userNameLabel);

        JTextField userNameTextField=new JTextField();
        userNameTextField.setBounds(300,250,300,30);
        userNameTextField.setToolTipText("Enter your Username and username length greater then 10");
        userNameTextField.setTransferHandler(null);
        userNameTextField.setText(userName);
        userNameTextField.setBackground(ColorUIResource.CYAN);
        userNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        userNameTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        userNameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                if(userNameTextField.getText().length()>40)
                ke.consume();
            }
        });
        panel.add(userNameTextField);


        // password
        JLabel passwordNameLabel=new JLabel("Password");
        passwordNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        passwordNameLabel.setForeground(ColorUIResource.BLUE);
        passwordNameLabel.setBounds(50,300,200,35);
        panel.add(passwordNameLabel);

        JPasswordField passwordNameTextField=new JPasswordField();
        passwordNameTextField.setBounds(300,300,300,30);
        passwordNameTextField.setToolTipText("Enter your Password and length greater then 8");
        passwordNameTextField.setTransferHandler(null);
        passwordNameTextField.setBackground(ColorUIResource.CYAN);
        passwordNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        passwordNameTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        passwordNameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                if(passwordNameTextField.getText().length()>40)
                ke.consume();
            }
        });
        panel.add(passwordNameTextField);

        // confirm password
        JLabel confirmPasswordNameLabel=new JLabel("Confirm Password");
        confirmPasswordNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        confirmPasswordNameLabel.setForeground(ColorUIResource.BLUE);
        confirmPasswordNameLabel.setBounds(50,350,220,35);
        panel.add(confirmPasswordNameLabel);
 
        JTextField confirmPasswordNameTextField=new JTextField();
        confirmPasswordNameTextField.setBounds(300,350,300,30);
        confirmPasswordNameTextField.setToolTipText("Confirm Password and length greater then 8");
        confirmPasswordNameTextField.setTransferHandler(null);
        confirmPasswordNameTextField.setBackground(ColorUIResource.CYAN);
        confirmPasswordNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        confirmPasswordNameTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        confirmPasswordNameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                if(confirmPasswordNameTextField.getText().length()>40)
                ke.consume();
            }
        });
        panel.add(confirmPasswordNameTextField);

        //admin role
        JLabel adminRoleLabel=new JLabel("Admin Role");
        adminRoleLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        adminRoleLabel.setForeground(ColorUIResource.BLUE);
        adminRoleLabel.setBounds(50,400,220,35);
        panel.add(adminRoleLabel);
 
        JTextField adminRoleTextField=new JTextField();
        adminRoleTextField.setBounds(300,400,300,30);
        adminRoleTextField.setToolTipText("Admin Role");
        adminRoleTextField.setEditable(false);
        adminRoleTextField.setText(role);
        adminRoleTextField.setTransferHandler(null);
        adminRoleTextField.setBackground(ColorUIResource.CYAN);
        adminRoleTextField.setForeground(ColorUIResource.DARK_GRAY);
        adminRoleTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        panel.add(adminRoleTextField);

        //inserted timestamp
        JLabel timeStampRoleLabel=new JLabel("Timestamp");
        timeStampRoleLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        timeStampRoleLabel.setForeground(ColorUIResource.BLUE);
        timeStampRoleLabel.setBounds(50,450,220,35);
        panel.add(timeStampRoleLabel);
 
        JTextField timeStampTextField=new JTextField();
        timeStampTextField.setBounds(300,450,300,30);
        timeStampTextField.setToolTipText("Inserted Timestamp");
        timeStampTextField.setEditable(false);
        timeStampTextField.setText(datetime);
        timeStampTextField.setBackground(ColorUIResource.CYAN);
        timeStampTextField.setForeground(ColorUIResource.DARK_GRAY);
        timeStampTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        panel.add(timeStampTextField);


        // update button
        JButton loginButton=new JButton("Update");
        loginButton.setBounds(360,530,80,26);
        loginButton.setToolTipText("Prees the Update Button then Update");
        loginButton.setFont(new Font("verdana",Font.CENTER_BASELINE,18));
        loginButton.setBackground(ColorUIResource.CYAN);
        loginButton.setForeground(ColorUIResource.BLUE);
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(loginButton);
        loginButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                if(isValid(adminNameTextField,userNameTextField, passwordNameTextField, confirmPasswordNameTextField))
                {
                    if(isValidPassword(passwordNameTextField, confirmPasswordNameTextField))
                    {
                        // initiate task variable
                        isTask=true;
                        AdminInfo adminInfo=new AdminInfo();
                        PreLoader preLoader1=new PreLoader();

                        loginButton.setVisible(false);
                        JLabel preLoader=preLoader1.createPreloader(panel,360,510);

                        new Thread(new Runnable()
                        {   
                            public void run()
                            {
                                //set all variable
                                adminInfo.setName(adminNameTextField.getText());
                                adminInfo.setOldUserName(userName);
                                adminInfo.setUserName(userNameTextField.getText());
                                adminInfo.setPassword(passwordNameTextField.getText());
                                adminInfo.setRole(adminRoleTextField.getText());
                                adminInfo.setDateTime(timeStampTextField.getText());

                                if(userName.equalsIgnoreCase(userNameTextField.getText()))
                                {
                                    if(adminInfo.updateAdminData())
                                    JOptionPane.showMessageDialog(frame,"Admin user Data Updated successfully");
                                    else
                                    JOptionPane.showMessageDialog(frame,"Some technical problem please try after some time","error",0);
                                }
                                else
                                {
                                    int response=adminInfo.checkUserAlready(userNameTextField.getText());
                                    if(response==0)
                                    JOptionPane.showMessageDialog(frame,"Username already have in database","error",0);
                                    else if(response==1)
                                    {
                                        String OTP=new SRandom().getOTP();
                                        try{new EmpMailOperation().sendMail("<h3>Confirmation for updation in Infosys Admin Users</h3><br>Your generated OTP is <b>"+OTP+"</b> so that it can not share anywhere<br>Thank You", userNameTextField.getText());
                                        }catch(Exception e){}
                                        String userOTP=JOptionPane.showInputDialog(frame,"One Time Password sent to his email enter OTP");
                                        if(userOTP!=null)
                                        {
                                            if(userOTP.equals(OTP))
                                            {
                                                // accepted
                                                if(adminInfo.updateAdminData())
                                                JOptionPane.showMessageDialog(frame,"Admin user Data Updated successfully");
                                                else
                                                JOptionPane.showMessageDialog(frame,"Some technical problem please try after some time","error",0);
                                            }
                                            else
                                            JOptionPane.showMessageDialog(frame,"Incorrect OTP","error",0);
                                        }
                                    }
                                    else
                                    JOptionPane.showMessageDialog(frame,"Server Error please try after some time","error",0);
                                    
                                }
                                isTask=false;
                                preLoader1.dismisPreloader(panel,preLoader);
                                loginButton.setVisible(true);
                            }                            
                        }).start();
                    }
                    else
                    JOptionPane.showMessageDialog(frame,"Mismatch password and confirm password","error",0);
                }
                else
                JOptionPane.showMessageDialog(frame,"Please enter valid details","error",0);
            }
        });

        // preLoader.createPreloader(panel, 360, 510);


        frame.add(panel,gbc);
        // back to home page
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                // System.exit(0);
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

    private boolean isValidPassword(JPasswordField pass,JTextField cpass)
    {
        pass.setBackground(ColorUIResource.CYAN);
        cpass.setBackground(ColorUIResource.CYAN);
        if(pass.getText().equals(cpass.getText()))
        {
            return true;
        }
        else
        {
            pass.setBackground(ColorUIResource.RED);
            cpass.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    private boolean isValid(JTextField name,JTextField username,JPasswordField pass,JTextField cpass)
    {
        boolean b1=false,b2=false,b3=false,b4=false;

        name.setBackground(ColorUIResource.CYAN);
        username.setBackground(ColorUIResource.CYAN);
        pass.setBackground(ColorUIResource.CYAN);
        cpass.setBackground(ColorUIResource.CYAN);

        //name
        if(name.getText().isBlank())
        name.setBackground(ColorUIResource.RED);
        else
        b1=true;

        //username
        if(username.getText().isBlank())
        username.setBackground(ColorUIResource.RED);
        else
        {
            if(username.getText().length()>10)
            b2=true;
            else
            username.setBackground(ColorUIResource.RED);
        }

        // password
        if(pass.getText().isBlank())
        pass.setBackground(Color.RED);
        else
        {
            if(pass.getText().length()>8)
            b3=true;
            else
            pass.setBackground(ColorUIResource.RED);
        }

        // confirm password
        if(cpass.getText().isBlank())
        cpass.setBackground(Color.RED);
        else
        {
            if(cpass.getText().length()>8)
            b4=true;
            else
            cpass.setBackground(ColorUIResource.RED);
        }

        if(b1 && b2 && b3 && b4)
        return true;
        else
        return false;
    }

    private void setAllValue()
    {
        try
        {
            FileStructure fileStructure=new FileStructure();
            File file=new File(fileStructure.getSessionFileStructure()+"//AdminInfo.binary");
            FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            HashMap<String,String> map=(HashMap<String,String>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            for (Map.Entry<String,String> element : map.entrySet())
            {
                if(element.getKey().equals("name"))
                {
                    name=element.getValue();
                }

                if(element.getKey().equals("username"))
                {
                    userName=element.getValue();
                }

                if(element.getKey().equals("role"))
                {
                    role=element.getValue();
                }

                if(element.getKey().equals("datetime"))
                {
                    datetime=element.getValue();
                }
            }
            map.clear();
        }
        catch(Exception e)
        {
            setAllValue();
        }
    }
}
