import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EmployeeFinancial
{
    public EmployeeFinancial(JPopupMenu menu,JFrame frame)
    {
        JMenuItem menuItem1=new JMenuItem("Payroll");
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem1);

        JMenuItem menuItem2=new JMenuItem("Compensation");
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem2);

        JMenuItem menuItem3=new JMenuItem("Rewards");
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem3);

        JMenuItem menuItem4=new JMenuItem("Salary Administration");
        menuItem4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem4);

        JMenuItem menuItem5=new JMenuItem("Employee Recognition");
        menuItem5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem5);
    }    
}
