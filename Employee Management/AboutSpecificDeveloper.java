import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;

import icons.AllColors;
import icons.AllHomePageIcon;
import icons.ScreenSize;

public class AboutSpecificDeveloper extends ScreenSize
{
    private JFrame frame;
    private ImageIcon imageIcon;
    private String intro;
    public AboutSpecificDeveloper(ImageIcon imageIcon,String intro)
    {
        this.imageIcon=imageIcon;
        this.intro=intro;

        AllColors allColors=new AllColors();

        frame = new JFrame("Developer Community Information");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        // insets
        GridBagConstraints gbc=new GridBagConstraints();
        GridBagConstraints gbc1=new GridBagConstraints();
        gbc.insets=new Insets(25,25,25,25);

        gbc1=gbc;

        // mainPanel
        gbc.gridx=0;
        gbc.gridy=0;
        JPanel mainPanel=new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setBackground(allColors.getPanelBackground());
        mainPanel.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Community Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        frame.add(mainPanel,gbc);

        
        AllHomePageIcon allHomePageIcon=new AllHomePageIcon(); // creating object

        // photo
        gbc1.fill=GridBagConstraints.VERTICAL;
        gbc1.gridx=0;gbc1.gridy=0;
        gbc1.insets=new Insets(25,25,25,25);
        JLabel photoLabel=new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        // photoLabel.setPreferredSize(new Dimension(100,100));
        photoLabel.setBounds(350,15,100,100);
        photoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        photoLabel.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JPopupMenu popupMenu=new JPopupMenu();
                popupMenu.setLayout(null);
                popupMenu.setSize(500,500);
                JLabel imageLabel=new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(500,500,Image.SCALE_AREA_AVERAGING)));
                imageLabel.setBounds(0,0,500,500);
                popupMenu.add(imageLabel);
                popupMenu.show(photoLabel,me.getX(),me.getY());
            }
        });
        mainPanel.add(photoLabel);

        // introduction
        gbc1.gridx=0;
        gbc1.gridy=1;
        // gbc1.insets=new Insets(30,100,50,100);
        JTextArea textArea=new JTextArea();
        textArea.setLineWrap(true);
        textArea.setText(intro);
        textArea.setEditable(false);
        textArea.setTransferHandler(null);
        textArea.setBorder(new TitledBorder(new LineBorder(new Color(95,158,160),3,true),"Developer Details",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(72,209,204)));
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial Rounded MT Bold",Font.CENTER_BASELINE,20));
        textArea.setBackground(new Color(152,251,152));
        textArea.setForeground(new Color(0,0,139));
        JScrollPane scrollPane=new JScrollPane(textArea);
        scrollPane.setBounds(2,120,798,383);
        mainPanel.add(scrollPane);

        // back to home page
        frame.addWindowListener(
            new WindowAdapter()
            {
                public void windowClosing(WindowEvent we)
                {
                    // System.exit(0);
                    frame.removeAll();
                    frame.setVisible(false);
                    new AboutDevelopers();
                }
            }
        );
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }    
}
