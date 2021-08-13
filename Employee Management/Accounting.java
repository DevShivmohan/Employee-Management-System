import java.awt.Font;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
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
import javax.swing.table.DefaultTableModel;

import dboperation.AccountingData;
import icons.AllColors;
import icons.AllIcons;
import icons.PreLoader;
import icons.ScreenSize;
import validation.AllFieldValidation;

public class Accounting extends ScreenSize
{
    private JPanel panel;
    private JFrame frame;
    private boolean isTask=false;
    public Accounting()
    {
        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(25,5,5,5);

        frame=new JFrame("Employee Accounting Management");
        frame.setSize(getFrameWidth(), getFrameHeight());
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(allColors.getFrameBackground());
        frame.setVisible(true);

        // main panel
        gbc.gridx=0;
        gbc.gridy=0;
        panel=new JPanel(null);
        panel.setBackground(allColors.getPanelBackground());
        panel.setPreferredSize(new Dimension(800,600));
        JScrollPane scrollPane=new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        frame.add(scrollPane,gbc);

        
        // Accounting heading
        JButton frontLabel=new JButton("Employee Accounting Management",new ImageIcon(new ImageIcon(allIcons.getAccountingIcon().getAbsolutePath()).getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        frontLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,22));
        frontLabel.setForeground(ColorUIResource.MAGENTA);
        frontLabel.setBackground(allColors.getPanelBackground());
        frontLabel.setBorderPainted(false);
        frontLabel.setVerticalTextPosition(AbstractButton.BOTTOM);
        frontLabel.setHorizontalTextPosition(AbstractButton.CENTER);
        frontLabel.setBounds(200,40,400,150);
        panel.add(frontLabel);

        // Employee id field
        JLabel empIdLabel=new JLabel("Employee ID");
        empIdLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        empIdLabel.setBounds(125,270,200,35);
        panel.add(empIdLabel);
        
        JTextField empIdTextField=new JTextField();
        empIdTextField.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        empIdTextField.setBackground(ColorUIResource.CYAN);
        empIdTextField.setForeground(ColorUIResource.BLACK);
        empIdTextField.setBounds(375,270,300,30);
        empIdTextField.setTransferHandler(null);
        empIdTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if(!(Character.isLetterOrDigit(key.getKeyChar())))
                key.consume();

                if(Character.isLowerCase(key.getKeyChar()))
                key.setKeyChar(Character.toUpperCase(key.getKeyChar()));

                if(empIdTextField.getText().length()>14)
                key.consume();
            }
        });
        panel.add(empIdTextField);

        // add user button
        JButton loginButton=new JButton("Submit");
        loginButton.setBounds(360,450,90,30);
        loginButton.setToolTipText("Prees and Goto next Step");
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
                if(new AllFieldValidation().isValidEmpId(empIdTextField))
                {
                    new Thread(new Runnable()
                    {
                        public void run()
                        {
                            isTask=true;
                            loginButton.setVisible(false);
                            PreLoader preLoader=new PreLoader();
                            JLabel loder=preLoader.createPreloader(panel,360,450);

                            String id=empIdTextField.getText();
                            AccountingData accountingData=new AccountingData();
                            int response=accountingData.getValidEmpIdResponse(id);
                            if(response==0)
                            {
                                frame.removeAll();
                                frame.setVisible(false);
                                DefaultTableModel defaultTableModel=new DefaultTableModel();
                                accountingData.setAccountingTable(defaultTableModel);
                                String ammount=accountingData.getEmployeeSalary();
                                new AccountingShowAndCommit(ammount,id,defaultTableModel,accountingData);
                            }
                            else if(response==1)
                            JOptionPane.showMessageDialog(frame,"Incorrect Employee ID","error",0);
                            else
                            JOptionPane.showMessageDialog(frame,"Server error please try after some time","error",0);

                            preLoader.dismisPreloader(panel,loder);
                            loginButton.setVisible(true);
                            isTask=false;
                        }
                    }).start();
                }
                else
                JOptionPane.showMessageDialog(frame,"Please enter valid data","validation",0);
            }
        });

        // back
        panel.repaint();
        panel.revalidate();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                // System.exit(0);
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
    }
}