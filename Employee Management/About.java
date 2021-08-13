import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import icons.AllIcons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.awt.Desktop;
import java.awt.Image;
public class About
{
    public About(JPopupMenu menu,JFrame frame)
    {

        AllIcons allIcons=new AllIcons();

        JMenuItem menuItem1=new JMenuItem("About Softwere",new ImageIcon(new ImageIcon(allIcons.getAboutSoftwareIcon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem1);
        menuItem1.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSoftware();
            }
        });

        JMenuItem menuItem2=new JMenuItem("About Developer",new ImageIcon(new ImageIcon(allIcons.getDevelopericon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem2);
        menuItem2.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutDevelopers();
            }
        });

        JMenuItem menuItem3=new JMenuItem("About Security",new ImageIcon(new ImageIcon(allIcons.getEmpSecurityIcon().getAbsolutePath()).getImage().getScaledInstance(30,30,Image.SCALE_AREA_AVERAGING)));
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.add(menuItem3);
        menuItem3.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            Desktop desktop=Desktop.getDesktop();
                            desktop.browse(new URI("https://www.tutorialspoint.com/cryptography/advanced_encryption_standard.htm#:~:text=%20Advanced%20Encryption%20Standard%20%201%20Operation%20of,both%20hardware%20and%20software.%20Till%20date%2C.x..%20More%20"));
                        }
                        catch(Exception e){}
                    }
                }).start();
            }
        });
    }    
}
