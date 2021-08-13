import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EmployeeTimeAttendance
{
    public EmployeeTimeAttendance(JPopupMenu menu,JFrame frame)
    {
        JMenuItem menuItem1=new JMenuItem("Clock In/Out");
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem1);

        JMenuItem menuItem2=new JMenuItem("Time Reporting");
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem2);

        JMenuItem menuItem3=new JMenuItem("Project Billing");
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem3);

        JMenuItem menuItem4=new JMenuItem("Workforce Scheduling");
        menuItem4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem4);

        JMenuItem menuItem5=new JMenuItem("Absence Management");
        menuItem5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem5);

        JMenuItem menuItem6=new JMenuItem("Holidays Calendar");
        menuItem6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem6);
    }    
}
