import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import icons.AllColors;
import icons.AllHomePageIcon;
import icons.ScreenSize;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
public class MainGUI extends ScreenSize
{
    private JFrame frame;
    public MainGUI()
    {
        AllColors allColors=new AllColors();
        AllHomePageIcon allIcons=new AllHomePageIcon();

        GridBagConstraints gbc1=new GridBagConstraints();
        GridBagConstraints gbc2=new GridBagConstraints();
        GridBagConstraints gbc3=new GridBagConstraints();
        gbc1.insets=new Insets(1,1,1,1);
        gbc2=gbc1;
        
        frame=new JFrame("Employee Management System");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);


        // check session isset or not
        LoginSession loginSession=new LoginSession();
        if(!loginSession.isSetSession() && false)
        {
            JOptionPane.showMessageDialog(frame,"Session expired ! please return to Login Page","Warning",JOptionPane.WARNING_MESSAGE);
            frame.removeAll();
            frame.setVisible(false);
            new InfosysLogin();
        }

        // mainLabel like as panel
        gbc1.gridx=0;
        gbc1.gridy=0;
        JLabel mainLabel=new JLabel(new ImageIcon(allIcons.getHomeWallpaper().getImage().getScaledInstance(getFrameWidth(), getFrameHeight(),Image.SCALE_AREA_AVERAGING)));
        mainLabel.setPreferredSize(new Dimension(getFrameWidth(),getFrameHeight()));
        mainLabel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY,3,true),"Employee Management System Home Page",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLUE));
        mainLabel.setLayout(new GridBagLayout());
        frame.add(mainLabel,gbc1);

        // panel in mainLabel
        gbc2.gridx=0;
        gbc2.gridy=0;
        JPanel homePanel=new JPanel(new GridBagLayout());
        homePanel.setBackground(allColors.getHomePanelBackground());
        homePanel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY,3,true),"Employee Management System Home Page",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
        mainLabel.add(homePanel,gbc2);

        // employee
        gbc3.insets=new Insets(80,80,30,30);
        gbc3.gridx=0;
        gbc3.gridy=0;
        JButton employeeButton=new JButton("Employee",new ImageIcon(allIcons.getHomeEmployee().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        employeeButton.setPreferredSize(new Dimension(130,130));
        employeeButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        employeeButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        employeeButton.setHorizontalTextPosition(AbstractButton.CENTER);
        employeeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        employeeButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"Employee",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(employeeButton,gbc3);
        employeeButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new EmployeeRecord(popupMenu, frame);
                popupMenu.show(employeeButton,me.getX(),me.getY());
            }
        });

        
        // talents
        gbc3.insets=new Insets(80,30,30,30);
        gbc3.gridx=1;
        gbc3.gridy=0;
        JButton talentButton=new JButton("Talent",new ImageIcon(allIcons.getHomeTalent().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        talentButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        talentButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        talentButton.setPreferredSize(new Dimension(130,130));
        talentButton.setHorizontalTextPosition(AbstractButton.CENTER);
        talentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        talentButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"Talents",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(talentButton,gbc3);
        talentButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new EmployeeTalent(popupMenu, frame);
                popupMenu.show(talentButton,me.getX(),me.getY());
            }
        });

        // benefits
        gbc3.gridx=2;
        gbc3.gridy=0;
        JButton benefitsButton=new JButton("Benefits",new ImageIcon(allIcons.getHomeBenefits().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        benefitsButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        benefitsButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        benefitsButton.setPreferredSize(new Dimension(130,130));
        benefitsButton.setHorizontalTextPosition(AbstractButton.CENTER);
        benefitsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        benefitsButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"Benefits",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(benefitsButton,gbc3);
        benefitsButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new EmployeeBenefits(popupMenu, frame);
                popupMenu.show(benefitsButton,me.getX(),me.getY());
            }
        });

        // financial
        gbc3.insets=new Insets(80,30,30,80);
        gbc3.gridx=3;
        gbc3.gridy=0;
        JButton financialButton=new JButton("Financial",new ImageIcon(allIcons.getHomeFinancial().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        financialButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        financialButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        financialButton.setPreferredSize(new Dimension(130,130));
        financialButton.setHorizontalTextPosition(AbstractButton.CENTER);
        financialButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        financialButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"Financial",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(financialButton,gbc3);
        financialButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new EmployeeFinancial(popupMenu, frame);
                popupMenu.show(financialButton,me.getX(),me.getY());
            }
        });

        // time & attendance
        gbc3.insets=new Insets(30,80,80,30);
        gbc3.gridx=0;
        gbc3.gridy=1;
        JButton attendanceButton=new JButton("Attendance",new ImageIcon(allIcons.getHomeAttendance().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        attendanceButton.setPreferredSize(new Dimension(130,130));
        attendanceButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        attendanceButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        attendanceButton.setHorizontalTextPosition(AbstractButton.CENTER);
        attendanceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        attendanceButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"Attendance",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(attendanceButton,gbc3);
        attendanceButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new EmployeeTimeAttendance(popupMenu,frame);
                popupMenu.show(attendanceButton,me.getX(),me.getY());
            }
        });

        // Accounting
        gbc3.insets=new Insets(30,30,80,30);
        gbc3.gridx=1;
        gbc3.gridy=1;
        JButton accountingButton=new JButton("Accounting",new ImageIcon(allIcons.getHomeAccounting().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        accountingButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        accountingButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        accountingButton.setPreferredSize(new Dimension(130,130));
        accountingButton.setHorizontalTextPosition(AbstractButton.CENTER);
        accountingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountingButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"Accounting",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(accountingButton,gbc3);
        accountingButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new EmployeeAccounting(popupMenu,frame);
                popupMenu.show(accountingButton,me.getX(),me.getY());
            }
        });

        // About
        gbc3.gridx=2;
        gbc3.gridy=1;
        JButton aboutButton=new JButton("About",new ImageIcon(allIcons.getHomeAbout().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        aboutButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        aboutButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        aboutButton.setPreferredSize(new Dimension(130,130));
        aboutButton.setHorizontalTextPosition(AbstractButton.CENTER);
        aboutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"About",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(aboutButton,gbc3);
        aboutButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new About(popupMenu,frame);
                popupMenu.show(aboutButton,me.getX(),me.getY());
            }
        });

        
        // Setting
        gbc3.insets=new Insets(30,30,80,80);
        gbc3.gridx=3;
        gbc3.gridy=1;
        JButton settingButton=new JButton("Setting",new ImageIcon(allIcons.getHomeSetting().getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        settingButton.setFont(new Font("arial",Font.CENTER_BASELINE,18));
        settingButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        settingButton.setPreferredSize(new Dimension(130,130));
        settingButton.setHorizontalTextPosition(AbstractButton.CENTER);
        settingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingButton.setBorder(new TitledBorder(new LineBorder(Color.PINK,3,true),"Setting",TitledBorder.LEADING,TitledBorder.TOP,null,Color.MAGENTA));
        homePanel.add(settingButton,gbc3);
        settingButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                new Setting(popupMenu, frame);
                popupMenu.show(settingButton,me.getX(),me.getY());
            }
        });


        // exit from this application
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                // System.exit(0);
                if((JOptionPane.showConfirmDialog(frame,"Are you sure to exit in this Application"))==0)
                {
                    new LoginSession().destroySession();
                    System.exit(0);
                }
            }
        });
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}