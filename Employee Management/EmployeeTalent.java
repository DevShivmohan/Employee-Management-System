import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EmployeeTalent
{
    public EmployeeTalent(JPopupMenu menu,JFrame frame)
    {
        JMenuItem menuItem1=new JMenuItem("Employee Performance");
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem1);

        JMenuItem menuItem2=new JMenuItem("Behavioral Tests");
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem2);

        JMenuItem menuItem3=new JMenuItem("Competency Tests");
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem3);

        JMenuItem menuItem4=new JMenuItem("Key Performance Indicators (KPI)");
        menuItem4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem4);

        JMenuItem menuItem5=new JMenuItem("Reviews and Feedback");
        menuItem5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem5);
    }    
}
