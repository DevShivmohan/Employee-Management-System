import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.DefaultCaret;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import icons.AllColors;
import icons.AllIcons;
import icons.ScreenSize;

public class AboutSoftware extends ScreenSize
{
    private JFrame frame;
    private JPanel panel;
    public AboutSoftware()
    {
        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(25,5,5,5);

        frame=new JFrame("About Software Information");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        gbc.gridx=0;
        gbc.gridy=0;
        panel=new JPanel(null);
        panel.setPreferredSize(new Dimension(800,600));
        panel.setBackground(allColors.getPanelBackground());

        //front user icon
        JLabel infoSys=new JLabel(new ImageIcon(new ImageIcon(allIcons.getAboutSoftwareIcon().getAbsolutePath()).getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        infoSys.setBounds(350,5,100,100);
        panel.add(infoSys);

        // front label
        JLabel frontLoginLabel=new JLabel("About Software Information");
        frontLoginLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,30));
        frontLoginLabel.setForeground(ColorUIResource.MAGENTA);
        frontLoginLabel.setBounds(180,115,500,35);
        panel.add(frontLoginLabel);

        // about info textarea with scrolling
        JTextArea aboutTextArea=new JTextArea();
        // aboutTextArea.setRows(5);
        aboutTextArea.setWrapStyleWord(false);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setBackground(ColorUIResource.GRAY);
        aboutTextArea.setForeground(ColorUIResource.BLACK);
        aboutTextArea.setFont(new Font("arial",Font.TRUETYPE_FONT,18));
        aboutTextArea.setPreferredSize(new Dimension(600,450));
        aboutTextArea.setAutoscrolls(true);
        aboutTextArea.setEditable(false);
        JScrollPane scrollPane=new JScrollPane(aboutTextArea);
        scrollPane.setBounds(0,180,800,420);
        scrollPane.setAutoscrolls(true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        DefaultCaret defaultCaret=(DefaultCaret)aboutTextArea.getCaret();
        defaultCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        aboutTextArea.setText(getAboutSoftwareInfo().toString()); // adding data
        panel.add(scrollPane);
        // back to home page
        frame.addWindowListener(
            new WindowAdapter()
            {
                public void windowClosing(WindowEvent we)
                {
                    // System.exit(0);
                    frame.removeAll();
                    frame.setVisible(false);
                    new MainGUI();
                }
            }
        );
        frame.add(panel,gbc);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private StringBuilder getAboutSoftwareInfo()
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Dear Users ,\n\n");
        stringBuilder.append("This Software made only Employee Management System of an Industry ");
        stringBuilder.append("That how to manage data of employees diffrent types of data ");
        stringBuilder.append("And All data which are used in this software back-end level these all data are fully encrypted");
        stringBuilder.append(" means many difficulties in hacking of any data\n\n So Thank You");
        return stringBuilder;
    }
}