import icons.AllColors;
import icons.AllIcons;
import icons.PreLoader;
import icons.ScreenSize;
import validation.AllFieldValidation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import dboperation.AccountingData;
public class AccountingShowAndCommit extends ScreenSize {
    private JFrame frame;
    private JPanel panel;
    private JButton trfButton,deleteButton,printButton;
    private AccountingData accountingData;
    private Thread trfThread,deleteThread,printThread;
    private JTable table;
    private DefaultTableModel defaultTableModel;

    public AccountingShowAndCommit(String ammount,String empId,DefaultTableModel defaultTableModel,AccountingData accountingData){
        this.accountingData=accountingData;
        this.defaultTableModel=defaultTableModel;
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
        JButton frontLabel=new JButton("Employee Accounting Management",new ImageIcon(new ImageIcon(allIcons.getAccountingIcon().getAbsolutePath()).getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
        frontLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,20));
        frontLabel.setForeground(ColorUIResource.MAGENTA);
        frontLabel.setBackground(allColors.getPanelBackground());
        frontLabel.setBorderPainted(false);
        frontLabel.setVerticalTextPosition(AbstractButton.BOTTOM);
        frontLabel.setHorizontalTextPosition(AbstractButton.CENTER);
        frontLabel.setBounds(200,0,400,80);
        panel.add(frontLabel);

        // Employee id field
        JLabel empIdLabel=new JLabel("Employee ID");
        empIdLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
        empIdLabel.setBounds(5,100,150,20);
        panel.add(empIdLabel);

        JTextField empIdTextField=new JTextField();
        empIdTextField.setText(empId);
        empIdTextField.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
        empIdTextField.setBackground(ColorUIResource.CYAN);
        empIdTextField.setForeground(ColorUIResource.BLACK);
        empIdTextField.setBounds(170,100,200,25);
        empIdTextField.setTransferHandler(null);
        empIdTextField.setEditable(false);
        panel.add(empIdTextField);

        // Employee Salary field
        JLabel empSalaryLabel=new JLabel("Employee Ammount");
        empSalaryLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
        empSalaryLabel.setBounds(5,150,150,20);
        panel.add(empSalaryLabel);

        JTextField empSalaryTextField=new JTextField();
        empSalaryTextField.setText(ammount);
        empSalaryTextField.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
        empSalaryTextField.setBackground(ColorUIResource.CYAN);
        empSalaryTextField.setForeground(ColorUIResource.BLACK);
        empSalaryTextField.setBounds(170,150,150,25);
        empSalaryTextField.setTransferHandler(null);
        empSalaryTextField.setEditable(false);
        empSalaryTextField.setToolTipText("Please enter Ammount Max. 1000000000 and Min. 1000");
        panel.add(empSalaryTextField);

        // Transfer button
        trfButton=new JButton("Transfer");
        trfButton.setBounds(170,200,80,25);
        trfButton.setFont(new Font("verdana",Font.CENTER_BASELINE,14));
        trfButton.setBackground(ColorUIResource.CYAN);
        trfButton.setForeground(ColorUIResource.BLUE);
        trfButton.setBorder(BorderFactory.createEtchedBorder());
        trfButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        trfButton.setToolTipText("Add Ammount to employee accounting history");
        panel.add(trfButton);
        trfButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                if(empSalaryTextField.getText().length()>1 && empIdTextField.getText().length()>1)
                {
                    if((JOptionPane.showConfirmDialog(frame,"Are you sure to Transfer Ammount Given by User","Confirmation",JOptionPane.WARNING_MESSAGE))==JOptionPane.YES_OPTION)
                    {
                        trfThread=new Thread(new Runnable()
                        {
                            public void run()
                            {
                                JLabel label=createPreloader(trfButton);
                                int response=accountingData.trfAmmount(empIdTextField.getText(),empSalaryTextField.getText());
                                if(response==0){
                                    refresh();
                                    JOptionPane.showMessageDialog(frame,"Data Transferred successfully");
                                }
                                else
                                JOptionPane.showMessageDialog(frame,"Some technical issues please try after some time","error",0);
                                dismisPreloader(trfButton, label);
                            }
                        });
                        trfThread.start();
                    }
                }
                else
                JOptionPane.showMessageDialog(frame,"Some technical issues please try after some time","error",0);
            }
        });

        // Receipt field
        JLabel empReceiptNoLabel=new JLabel("Receipt No.");
        empReceiptNoLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
        empReceiptNoLabel.setBounds(430,150,120,20);
        panel.add(empReceiptNoLabel);

        JTextField empReceiptTextField=new JTextField();
        empReceiptTextField.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
        empReceiptTextField.setBackground(ColorUIResource.CYAN);
        empReceiptTextField.setForeground(ColorUIResource.BLACK);
        empReceiptTextField.setBounds(560,150,225,25);
        empReceiptTextField.setTransferHandler(null);
        empReceiptTextField.setToolTipText("Please enter Receipt no. of Transaction ammount");
        empReceiptTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if((key.getKeyChar()=='0' && empReceiptTextField.getText().length()==0))
                key.consume();

                if(!Character.isDigit(key.getKeyChar()))
                key.consume();

                if(empReceiptTextField.getText().length()>19)
                key.consume();
            }
        });
        panel.add(empReceiptTextField);

        // Print Button
        printButton=new JButton("Print");
        printButton.setBounds(705,100,80,25);
        printButton.setFont(new Font("verdana",Font.CENTER_BASELINE,14));
        printButton.setBackground(ColorUIResource.CYAN);
        printButton.setForeground(ColorUIResource.BLUE);
        printButton.setToolTipText("Print Receipt");
        printButton.setBorder(BorderFactory.createEtchedBorder());
        printButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        printButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                if((JOptionPane.showConfirmDialog(frame,"Are you sure to Print or Download Accounting History","Confirmation",JOptionPane.WARNING_MESSAGE))==JOptionPane.YES_OPTION)
                {
                    printThread=new Thread(new Runnable()
                    {
                        public void run()
                        {
                            JLabel label= createPreloader(printButton);
                            boolean feedback=accountingData.isPrintAccountingReceipt(empIdTextField.getText());
                            if(feedback)
                            JOptionPane.showMessageDialog(frame,"Accounting history file of employee id "+empIdTextField.getText()+" is downloaded successfully");
                            else
                            JOptionPane.showMessageDialog(frame,"Some technical issues please try after some time","error",0);
                            dismisPreloader(printButton, label);
                        }
                    });
                    printThread.start();
                }
            }
        });
        panel.add(printButton);

        // Delete button
        deleteButton=new JButton("Delete");
        deleteButton.setBounds(560,200,80,25);
        deleteButton.setFont(new Font("verdana",Font.CENTER_BASELINE,14));
        deleteButton.setBackground(ColorUIResource.CYAN);
        deleteButton.setForeground(ColorUIResource.BLUE);
        deleteButton.setToolTipText("Delete specific record which are matched receipt no.");
        deleteButton.setBorder(BorderFactory.createEtchedBorder());
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                if(new AllFieldValidation().isValidReceiptId(empReceiptTextField))
                {
                    deleteThread=new Thread(new Runnable()
                    {
                        public void run()
                        {
                            JLabel label=createPreloader(deleteButton);
                            String receiptId=empReceiptTextField.getText();
                            int response=accountingData.deleteAccountingRecord(empIdTextField.getText(),receiptId);
                            if(response==0){
                                refresh();
                                JOptionPane.showMessageDialog(frame,"Record of Receipt ID "+receiptId+" is deleted successfully");
                            }
                            else if(response==1)
                            JOptionPane.showMessageDialog(frame,"Invalid Receipt ID "+receiptId,"Invalid detail",0);
                            else
                            JOptionPane.showMessageDialog(frame,"Some technical issues please try after some time","error",0);
                            dismisPreloader(deleteButton, label);
                        }
                    });
                    deleteThread.start();
                }
                else
                JOptionPane.showMessageDialog(frame,"Please enter valid Receipt ID","Validation",0);
            }
        });
        panel.add(deleteButton);

        //table
        table=new JTable();
        table.setModel(defaultTableModel);
        JScrollPane scrollPane2=new JScrollPane(table);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(5,300,790,295);
        panel.add(scrollPane2);
        table.setEnabled(false);
        table.setCellSelectionEnabled(false);

        // back
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        panel.repaint();
        panel.revalidate();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                if(printThread!=null && printThread.isAlive())
                printThread.interrupt();
                if(deleteThread!=null && deleteThread.isAlive())
                deleteThread.interrupt();
                if(trfThread!=null && trfThread.isAlive())
                trfThread.interrupt();
                frame.removeAll();
                frame.setVisible(false);
                new Accounting();
            }
        });
    }
    private JLabel createPreloader(JButton button){
        button.setVisible(false);
        PreLoader preLoader=new PreLoader();
        JLabel label=preLoader.createPreloader(panel, button.getX(), button.getY());
        return label;
    }

    private void dismisPreloader(JButton button,JLabel label){
        PreLoader preLoader=new PreLoader();
        preLoader.dismisPreloader(panel,label);
        button.setVisible(true);
    }

    synchronized private void refresh(){
        defaultTableModel=(DefaultTableModel)table.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel=new DefaultTableModel();
        accountingData.setAccountingTable(defaultTableModel);
        table.revalidate();
        table.repaint();
    }
}