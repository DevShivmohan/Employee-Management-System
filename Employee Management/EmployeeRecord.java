import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import icons.AllIcons;

public class EmployeeRecord extends AllIcons
{
    public EmployeeRecord(JPopupMenu empMenu,JFrame frame)
    {
        // All features of Employee menu
        JMenuItem empRegMenuItem=new JMenuItem("Registration",new ImageIcon(new ImageIcon(getAddEmpIcon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        empRegMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        empMenu.add(empRegMenuItem);
        empRegMenuItem.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new EmployeeRegistration();
            }
        });

        JMenuItem empUpdateMenuItem=new JMenuItem("Update",new ImageIcon(new ImageIcon(getEmpEditIcon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        empUpdateMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        empMenu.add(empUpdateMenuItem);
        empUpdateMenuItem.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new EmployeeSearchUpdate();
            }
        });

        JMenuItem empDeleteMenuItem=new JMenuItem("Delete",new ImageIcon(new ImageIcon(getEmpDeleteIcon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        empDeleteMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        empMenu.add(empDeleteMenuItem);
        empDeleteMenuItem.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.setVisible(false);
                frame.removeAll();
                new EmployeeSearchDelete();
            }
        });

        JMenuItem empSearchMenuItem=new JMenuItem("Search",new ImageIcon(new ImageIcon(getEmpSearchIcon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        empSearchMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        empMenu.add(empSearchMenuItem);
        empSearchMenuItem.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.setVisible(false);
                frame.removeAll();
                new EmployeeSearch();
            }
        });

        JMenuItem empJobHistoryMenuItem=new JMenuItem("Job History",new ImageIcon(new ImageIcon(getEmpJobExeperienceIcon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        empJobHistoryMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        empMenu.add(empJobHistoryMenuItem);
        empJobHistoryMenuItem.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new EmployeeJobHistorySearch();
            }
        });
    }    
}
