import dboperation.UpdateJobHistory;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import dboperation.RetrieveInfo;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import icons.AllColors;
import icons.AllIcons;
import icons.PreLoader;
import icons.ScreenSize;

public class EmployeeJobHistorySearch extends ScreenSize
{
    private JFrame frame;
    private JPanel panel;
    private boolean isTask=false;
    public EmployeeJobHistorySearch()
    {

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(60,5,5,40);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        // new InsetsUIResource(top, left, bottom, right)
        gbc.gridx=0;
        gbc.gridy=0;

        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();

        frame=new JFrame("Search Job History");
        frame.setSize(getFrameWidth(),getFrameHeight());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

        panel=new JPanel(new GridBagLayout());
        panel.setBounds(0,0,getFrameWidth(),getFrameHeight());
        panel.setBackground(new AllColors().getPanelBackground());

        JScrollPane scrollPane=new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(getFrameWidth(),getFrameHeight());
        scrollPane.getVerticalScrollBar().getUnitIncrement(20);
        frame.add(scrollPane);

        JLabel frontLabel=new JLabel(new ImageIcon(new ImageIcon(allIcons.getEmpJobExeperienceIcon().getAbsolutePath()).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
        frontLabel.setFont(new Font("Arial",Font.HANGING_BASELINE,35));
        frontLabel.setForeground(ColorUIResource.BLUE);
        panel.add(frontLabel,gbc);


        //label employee id
        gbc.gridx=0;
        gbc.gridy=3;
        JLabel empIdLabel=new JLabel("Employee ID");
        empIdLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,25));
        empIdLabel.setForeground(ColorUIResource.GREEN);
        panel.add(empIdLabel,gbc);

        //empid textField
        gbc.gridx=1;
        gbc.gridy=3;
        JTextField empIdTextField=new JTextField();
        empIdTextField.setPreferredSize(new Dimension(250,30));
        empIdTextField.setFont(new Font("arial",Font.CENTER_BASELINE,20));
        empIdTextField.setTransferHandler(null);
        empIdTextField.setToolTipText("Enter Employee ID");
        empIdTextField.setBackground(allColors.getTextFieldBackground());
        empIdTextField.setForeground(allColors.getTextFieldForeground());
        empIdTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if (Character.isLowerCase(key.getKeyChar()))
                key.setKeyChar(Character.toUpperCase(key.getKeyChar()));

                if (empIdTextField.getText().length() > 14)
                key.consume();
            }
        });
        panel.add(empIdTextField,gbc);

        //submit button
        gbc.gridx=0;
        gbc.gridy=4;
        JButton submitButton=new JButton("Next");
        submitButton.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        submitButton.setBackground(ColorUIResource.PINK);
        submitButton.setForeground(ColorUIResource.BLUE);
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setToolTipText("Submit");
        submitButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                if(empIdTextField.getText().length()==15)
                {
                    empIdTextField.setBackground(allColors.getTextFieldBackground());
                    new Thread(new Runnable()
                    {
                        public void run()
                        {
                            isTask=true;
                            submitButton.setVisible(false);
                            PreLoader preLoader=new PreLoader();
                            JLabel loaderLabel=preLoader.createPreloader(panel, gbc);

                            String id=empIdTextField.getText();
                            int response=new RetrieveInfo().checkEmpUnique(id);
                            if(response==0)
                            new EmployeeJobHistory(id,frame);
                            else if(response==1)
                            JOptionPane.showMessageDialog(frame,"Employee record not found","error",0);
                            else
                            JOptionPane.showMessageDialog(frame,"Server error or interrupted connection","Server error",0);
                            
                            submitButton.setVisible(true);
                            preLoader.dismisPreloader(panel,loaderLabel);
                            isTask=false;
                        }
                    }).start();
                }
                else
                {
                    empIdTextField.setBackground(ColorUIResource.RED);
                    JOptionPane.showMessageDialog(frame,"Please enter valid employee id","Validation Failed",0);
                }
            }
        });
        panel.add(submitButton, gbc);


        //back to home
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