import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EmployeeBenefits
{
    public EmployeeBenefits(JPopupMenu menu,JFrame frame)
    {
        JMenuItem menuItem1=new JMenuItem("Benefits Administration");
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem1);

        JMenuItem menuItem2=new JMenuItem("Wellness");
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem2);
        
        JMenuItem menuItem3=new JMenuItem("Retirement Plans");
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem3);

        JMenuItem menuItem4=new JMenuItem("Travel compensation");
        menuItem4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem4);

        JMenuItem menuItem5=new JMenuItem("Paid Time Off (PTO)");
        menuItem5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem5);        
    }    
}
