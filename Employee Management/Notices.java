import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import icons.AllColors;
import icons.AllIcons;
import icons.PreLoader;
import icons.PrepareReciept;
import icons.ScreenSize;
import validation.AllFieldValidation;

public class Notices extends ScreenSize
{
    private JFrame frame;
    private JPanel panel;
    private boolean isTask=false;
    public Notices()
    {
        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(25,5,5,5);

        frame=new JFrame("Sending notice to All Organization Members");
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
        JLabel infoSys=new JLabel(new ImageIcon(new ImageIcon(allIcons.getNoticeIcon().getAbsolutePath()).getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        infoSys.setBounds(350,5,100,100);
        panel.add(infoSys);

        // front label
        JLabel frontLoginLabel=new JLabel("Send Notice to all organization members");
        frontLoginLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,30));
        frontLoginLabel.setForeground(ColorUIResource.MAGENTA);
        frontLoginLabel.setBounds(80,115,700,35);
        panel.add(frontLoginLabel);

        // notice subject
        JLabel adminNameLabel=new JLabel("Notice Subject");
        adminNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        adminNameLabel.setForeground(ColorUIResource.BLUE);
        adminNameLabel.setBounds(50,200,200,35);
        panel.add(adminNameLabel);

        JTextField adminNameTextField=new JTextField();
        adminNameTextField.setBounds(300,200,450,30);
        adminNameTextField.setToolTipText("Subject length should be greater then 10 & less then 100 charaters");
        // adminNameTextField.setTransferHandler(null);
        adminNameTextField.setBackground(ColorUIResource.CYAN);
        adminNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        adminNameTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        adminNameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                if(adminNameTextField.getText().length()>100)
                ke.consume();
            }
        });
        panel.add(adminNameTextField);

        // notice
        JLabel userNameLabel=new JLabel("Notice");
        userNameLabel.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        userNameLabel.setForeground(ColorUIResource.BLUE);
        userNameLabel.setBounds(50,250,200,35);
        panel.add(userNameLabel);

        JTextArea userNameTextField=new JTextArea();
        userNameTextField.setToolTipText("Notice length should be greater then 20 and less then 1000 characters");
        // userNameTextField.setTransferHandler(null);
        userNameTextField.setBackground(ColorUIResource.CYAN);
        userNameTextField.setForeground(ColorUIResource.DARK_GRAY);
        userNameTextField.setWrapStyleWord(true);
        userNameTextField.setLineWrap(true);
        userNameTextField.setFont(new Font("verdana",Font.CENTER_BASELINE,20));
        userNameTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                if(userNameTextField.getText().length()>1000)
                ke.consume();
            }
        });
        JScrollPane scrollPane=new JScrollPane(userNameTextField);
        scrollPane.setBounds(300,250,450,200);
        panel.add(scrollPane);

        // send button
        JButton loginButton=new JButton("Send");
        loginButton.setBounds(360,500,80,26);
        loginButton.setToolTipText("Prees and Add user");
        loginButton.setFont(new Font("verdana",Font.CENTER_BASELINE,18));
        loginButton.setBackground(ColorUIResource.CYAN);
        loginButton.setForeground(ColorUIResource.BLUE);
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(loginButton);
        loginButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                if(new AllFieldValidation().isValidNoticeField(adminNameTextField,userNameTextField))
                {
                    new Thread(new Runnable()
                    {
                        public void run()
                        {
                            isTask=true;
                            loginButton.setVisible(false);
                            PreLoader preLoader=new PreLoader();
                            JLabel loaderLabel=preLoader.createPreloader(panel,360,500);

                            PrepareReciept prepareReciept=new PrepareReciept();
                            boolean response= prepareReciept.prepareNotice(adminNameTextField.getText(),userNameTextField.getText());
                            if(response)
                            JOptionPane.showMessageDialog(frame,"Notice sent to all Organization members");
                            else
                            JOptionPane.showMessageDialog(frame,"Some technical problem in sending notice please try again","error",0);

                            preLoader.dismisPreloader(panel,loaderLabel);
                            loginButton.setVisible(true);
                            isTask=false;
                        }
                    }).start();
                }
                else
                JOptionPane.showMessageDialog(frame,"Please enter all valid data with in required","validation error",0);
            }
        });

        // back to home page
        frame.add(panel,gbc);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                if(isTask)
                JOptionPane.showMessageDialog(frame,"Please wait for Task finshing","Warning",JOptionPane.WARNING_MESSAGE);
                else
                {
                    frame.removeAll();
                    frame.setVisible(false);
                    new MainGUI();
                }
            }
        });
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}