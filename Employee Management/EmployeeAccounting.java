import icons.AllIcons;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeAccounting extends AllIcons
{
    public EmployeeAccounting(JPopupMenu menu,JFrame frame)
    {
        JMenuItem menuItem1=new JMenuItem("Accounting",new ImageIcon(new ImageIcon(getAccountingIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem1);
        menuItem1.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new Accounting();
            }
        });

        JMenuItem menuItem2=new JMenuItem("Send Notice",new ImageIcon(new ImageIcon(getNoticeIcon().getAbsolutePath()).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem2);
        menuItem2.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new Notices();
            }
        });

        JMenuItem menuItem3=new JMenuItem("Forecasting");
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem3);

        JMenuItem menuItem4=new JMenuItem("AP/AR Automation");
        menuItem4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem4);
    }    
}
