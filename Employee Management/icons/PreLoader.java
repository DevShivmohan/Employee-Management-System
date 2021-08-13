package icons;

import java.awt.Image;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PreLoader {
    private AllIcons allIcons=new AllIcons();
    public JLabel createPreloader(JPanel panel,int x,int y)
    {
        // creating preloader
        JLabel loginPreloader=new JLabel(new ImageIcon(new ImageIcon(allIcons.getLoginPreloader().getAbsolutePath()).getImage().getScaledInstance(40, 40, Image.SCALE_FAST)));
        loginPreloader.setBounds(x,y,40,40);
        panel.add(loginPreloader);
        panel.revalidate();
        panel.repaint();
        return loginPreloader;
    }

    public void dismisPreloader(JPanel panel,JLabel loginPreloader)
    {
        // dismis preloader
        panel.remove(loginPreloader);
        panel.revalidate();
        panel.repaint();
    }

    public JLabel createPreloader(JPanel panel,GridBagConstraints gbc){
        // creating preloader
        JLabel loginPreloader=new JLabel(new ImageIcon(new ImageIcon(allIcons.getLoginPreloader().getAbsolutePath()).getImage().getScaledInstance(40, 40, Image.SCALE_FAST)));
        panel.add(loginPreloader,gbc);
        panel.revalidate();
        panel.repaint();
        return loginPreloader;
    }
}
