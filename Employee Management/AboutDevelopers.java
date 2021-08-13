import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import icons.AllColors;
import icons.AllIcons;
import icons.ScreenSize;

public class AboutDevelopers extends ScreenSize
{
    private JFrame frame;
    public AboutDevelopers()
    {
        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();
        DeveloperData developerData=new DeveloperData();
        // new InsetsUIResource(top, left, bottom, right)

        frame = new JFrame("Developer Community Information");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc1=gbc;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,10,10);
        // new InsetsUIResource(top, left, bottom, right)
        
        
        
        // front label
        gbc1.gridx=0;
        gbc1.gridy=0;
        JLabel frontLabel=new JLabel("All Developers Community Information");
        frontLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,25));
        frame.add(frontLabel,gbc1);
        frontLabel.setForeground(ColorUIResource.MAGENTA);
        
        gbc1.gridy=1;
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(500,500);
        panel.setBackground(allColors.getPanelBackground());
        panel.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Community Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setSize(500, 500);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane,gbc1);
        

        // Shivmohan image
        gbc.insets = new Insets(100,100,30,30);
        gbc.gridx=0;
        gbc.gridy=2;
        JLabel label=new JLabel(new ImageIcon(developerData.getShivImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Shivmohan Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label.setPreferredSize(new Dimension(100,100));
        label.setToolTipText("Developer Shivmohan Details");
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label,gbc);
        label.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getShivImage(),developerData.getShivIntro());
            }
        });

        // Vipul Kumar image
        gbc.insets = new Insets(100,30,30,30);
        gbc.gridx=1;
        gbc.gridy=2;
        JLabel label1=new JLabel(new ImageIcon(developerData.getVipulImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label1.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Vipul Kumar Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label1.setPreferredSize(new Dimension(100,100));
        label1.setToolTipText("Developer Vipul Kumar Details");
        label1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label1,gbc);
        label1.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getVipulImage(),developerData.getVipulIntro());
            }
        });

        // Ayush Tripathi image
        gbc.insets = new Insets(100,30,30,30);
        gbc.gridx=2;
        gbc.gridy=2;
        JLabel label2=new JLabel(new ImageIcon(developerData.getAyushImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label2.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Ayush Tripathi Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label2.setPreferredSize(new Dimension(100,100));
        label2.setToolTipText("Developer Ayush Tripathi Details");
        label2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label2,gbc);
        label2.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getAyushImage(),developerData.getAyushIntro());
            }
        });

        // Alok Mani image
        gbc.insets = new Insets(100,30,30,100);
        gbc.gridx=3;
        gbc.gridy=2;
        JLabel label3=new JLabel(new ImageIcon(developerData.getAlokImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label3.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Alok Mani Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label3.setPreferredSize(new Dimension(100,100));
        label3.setToolTipText("Developer Alok Mani Details");
        label3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label3,gbc);
        label3.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getAlokImage(),developerData.getAlokIntro());
            }
        });

        
        // Mahima Devi image
        gbc.insets = new Insets(30,100,100,30);
        gbc.gridx=0;
        gbc.gridy=3;
        JLabel label4=new JLabel(new ImageIcon(developerData.getMahimaImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label4.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Mahima Devi Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label4.setPreferredSize(new Dimension(100,100));
        label4.setToolTipText("Developer Mahima Devi Details");
        panel.add(label4,gbc);
        label4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label4.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getMahimaImage(),developerData.getMahimaIntro());
            }
        });

        // 5muhi Devi image
        gbc.insets = new Insets(30,30,100,30);
        gbc.gridx=1;
        gbc.gridy=3;
        JLabel label5=new JLabel(new ImageIcon(developerData.getSunitaImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label5.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Sunita Devi Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label5.setPreferredSize(new Dimension(100,100));
        label5.setToolTipText("Developer Sunita Devi Details");
        label5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label5,gbc);
        label5.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getSunitaImage(),developerData.getSunitaIntro());
            }
        });

        // Shivani Maurya image
        gbc.gridx=2;
        gbc.gridy=3;
        JLabel label6=new JLabel(new ImageIcon(developerData.getShivaniImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label6.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Shivani Maurya Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label6.setPreferredSize(new Dimension(100,100));
        label6.setToolTipText("Developer Shivani Maurya Details");
        label6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label6,gbc);
        label6.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getShivaniImage(),developerData.getShivaniIntro());
            }
        });

        // Priyanka Gupta image
        gbc.insets = new Insets(30,30,100,100);
        gbc.gridx=3;
        gbc.gridy=3;
        JLabel label7=new JLabel(new ImageIcon(developerData.getPrinkaImage().getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        label7.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Priyanka Gupta Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        label7.setPreferredSize(new Dimension(100,100));
        label7.setToolTipText("Developer Priyanka Gupta Details");
        label7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(label7,gbc);
        label7.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                frame.removeAll();
                frame.setVisible(false);
                new AboutSpecificDeveloper(developerData.getPrinkaImage(),developerData.getPrinkaIntro());
            }
        });

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
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
