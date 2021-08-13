import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import icons.AllColors;
import icons.AllIcons;

public class Setting
{
    public Setting(JPopupMenu menu,JFrame frame)
    {
        AllIcons allIcons=new AllIcons();

        JMenuItem menuItem1=new JMenuItem("Account",new ImageIcon(new ImageIcon(allIcons.getLoginUserIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem1);
        menuItem1.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AdminUserAccount();
            }
        });

        JMenuItem menuItem2=new JMenuItem("Add User",new ImageIcon(new ImageIcon(allIcons.getAddEmpIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem2);
        menuItem2.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AddAdminUser();
            }
        });

        JMenuItem menuItem3=new JMenuItem("Delete User",new ImageIcon(new ImageIcon(allIcons.getEmpDeleteIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem3);
        menuItem3.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new DeleteAdmin();
            }
        });

        JMenuItem menuItem4=new JMenuItem("Eye Care",new ImageIcon(new ImageIcon(allIcons.getEyeCareIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        menuItem4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem4);
        JPopupMenu popupMenu=new JPopupMenu();
        JMenuItem darkMode=new JMenuItem("Switch to Dark Mode",new ImageIcon(new ImageIcon(allIcons.getDarkModeIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        darkMode.setCursor(new Cursor(Cursor.HAND_CURSOR));
        darkMode.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                new AllColors().setDarkMode();
                frame.removeAll();
                frame.setVisible(false);
                new MainGUI();
            }
        });

        JMenuItem lightMode=new JMenuItem("Switch to Light Mode",new ImageIcon(new ImageIcon(allIcons.getLightModeIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        lightMode.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lightMode.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                new AllColors().setLightMode();
                frame.removeAll();
                frame.setVisible(false);
                new MainGUI();
            }
        });
        
        popupMenu.add(darkMode);
        popupMenu.add(lightMode);
        menuItem4.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                popupMenu.show(frame,500,200);
            }
        });

        JMenuItem menuItem5=new JMenuItem("Logout",new ImageIcon(new ImageIcon(allIcons.getLogoutIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        menuItem5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem5);        
        menuItem5.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                new LoginSession().destroySession();
                frame.removeAll();
                frame.setVisible(false);
                new InfosysLogin();
            }
        });
    }    
}
