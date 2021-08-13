import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import dboperation.AddAdminData;
import dboperation.RemoveAdmin;

import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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
import security.LoggedUserData;
import validation.AllFieldValidation;

public class DeleteAdmin extends ScreenSize
{
    private JFrame frame;
    private JPanel panel;
    private boolean isTask=false;
    public DeleteAdmin()
    {
        
        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();

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
        JLabel infoSys=new JLabel(new ImageIcon(new ImageIcon(allIcons.getEmpDeleteIcon().getAbsolutePath()).getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        infoSys.setBounds(350,5,100,100);
        panel.add(infoSys);

        // front label
        JLabel frontLoginLabel=new JLabel("Delete ALPHA Admin Users");
        frontLoginLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,30));
        frontLoginLabel.setForeground(ColorUIResource.MAGENTA);
        frontLoginLabel.setBounds(180,115,500,35);
        panel.add(frontLoginLabel);


        // name
        JLabel adminNameLabel=new JLabel("Enter Username");
        adminNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        adminNameLabel.setForeground(ColorUIResource.BLUE);
        adminNameLabel.setBounds(50,200,200,35);
        panel.add(adminNameLabel);

        JTextField adminNameTextField=new JTextField();
        adminNameTextField.setBounds(300,200,300,30);
        adminNameTextField.setToolTipText("Enter Admin username");
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

        // delete user button
        JButton loginButton=new JButton("Delete");
        loginButton.setBounds(360,300,80,26);
        loginButton.setToolTipText("Prees and delete user");
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
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        AllFieldValidation validation=new AllFieldValidation();
                        if(validation.isValidUsername(adminNameTextField))
                        {
                            isTask=true;
                            loginButton.setVisible(false);
                            PreLoader preLoader=new PreLoader();
                            JLabel preLoaderLabel=preLoader.createPreloader(panel,360,300);

                            
                            AddAdminData addAdminData=new AddAdminData();

                            String userName=adminNameTextField.getText();
                            int response=new AddAdminData().getAdminRole();
                            LoggedUserData loggedUserData=new LoggedUserData();
                            if(loggedUserData.getLoggedUserName().equalsIgnoreCase(userName))
                            {
                                if((JOptionPane.showConfirmDialog(frame,"Are you sure to delete  "+userName+" this admin \nNote ! This is main Account after deletion you have not able to perform any operation ?"))==0)
                                {
                                    response=new RemoveAdmin().deleteAdminUser(userName);
                                    if(response==0)
                                    {
                                        JOptionPane.showMessageDialog(frame,"User "+userName+" is deleted successfully from database","error",1);
                                        LoginSession loginSession=new LoginSession();
                                        loginSession.destroySession();
                                        frame.removeAll();
                                        frame.setVisible(false);
                                        new InfosysLogin(); // go to login page
                                    }
                                    else
                                    JOptionPane.showMessageDialog(frame,"Some error in deletion please try after some time","error",0);
                                }
                            }
                            else if(response==0)
                            {
                                response=addAdminData.checkUser(userName);
                                if(response==0)
                                {
                                    if((JOptionPane.showConfirmDialog(frame,"Are you sure to delete  "+userName+" this admin"))==0)
                                    {
                                        response=new RemoveAdmin().deleteAdminUser(userName);
                                        if(response==0)
                                        JOptionPane.showMessageDialog(frame,"User "+userName+" is deleted successfully from database","error",1);
                                        else
                                        JOptionPane.showMessageDialog(frame,"Some error in deletion please try after some time","error",0);
                                    }
                                }
                                else if(response==1)
                                JOptionPane.showMessageDialog(frame,"Incorrect Admin Username","error",0);
                                else
                                JOptionPane.showMessageDialog(frame,"Server error please try after some time","error",0);
                            }
                            else if(response==1)
                            JOptionPane.showMessageDialog(frame,"Logged user is not allowed to do action","error",0);
                            else
                            JOptionPane.showMessageDialog(frame,"Server error please try after some time","error",0);

                            // dismis preloader
                            preLoader.dismisPreloader(panel,preLoaderLabel);
                            loginButton.setVisible(true);
                            isTask=false;
                        }
                        else
                        JOptionPane.showMessageDialog(frame,"Please enter valid username","validation",0);
                    }
                }).start();
            }
        });

        frame.add(panel,gbc);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
    }
    
}
