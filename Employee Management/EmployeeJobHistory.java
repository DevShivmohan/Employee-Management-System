import java.awt.Font;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import dboperation.RetrieveInfo;
import dboperation.UpdateJobHistory;
import icons.AllColors;
import icons.AllIcons;
import icons.PreLoader;
import icons.ScreenSize;
import validation.AllFieldValidation;
public final class EmployeeJobHistory extends ScreenSize
{
    private JFrame frame;
    private JPanel panel;
    private String jobProfile;
    private long empSalary=0;
    private String empName;
    private String empId;
    private boolean isTask=false;

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public void setEmpName(String empName){
        this.empName=empName;
    }

    public void setEmpSalary(long empSalary){
        this.empSalary=empSalary;
    }

    public void setEmpPost(String jobProfile){
        this.jobProfile=jobProfile;
    }

    public int getIndex(String jobProfile)
    {
        int index=0;
        if(jobProfile.equalsIgnoreCase("Software Developer"))
        index=0;

        if(jobProfile.equalsIgnoreCase("Web Developer"))
        index=1;

        if(jobProfile.equalsIgnoreCase("PHP Developer"))
        index=2;

        if(jobProfile.equalsIgnoreCase("Android Developer"))
        index=3;

        if(jobProfile.equalsIgnoreCase("Software Tester"))
        index=4;

        if(jobProfile.equalsIgnoreCase("Flowchart Designer"))
        index=5;

        if(jobProfile.equalsIgnoreCase("Peon"))
        index=6;

        return index;
    }

    public EmployeeJobHistory(String empId,JFrame tempFrame)
    {

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(60,5,5,40);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        // new InsetsUIResource(top, left, bottom, right)
        gbc.gridx=0;
        gbc.gridy=0;

        AllColors allColors=new AllColors();
        AllIcons allIcons=new AllIcons();

        frame=new JFrame("Employee Job History");
        frame.setSize(getFrameWidth(),getFrameHeight());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(null);

        // retreiving information
        RetrieveInfo retrieveInfo=new RetrieveInfo();
        retrieveInfo.getEmpJobHistory(empId);
        setEmpName(retrieveInfo.getEmpName());
        setEmpPost(retrieveInfo.getEmpPost());
        setEmpSalary(retrieveInfo.getEmpSalary());

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

        // employee id
        gbc.gridx=0;
        gbc.gridy=1;
        JLabel empIdLabel=new JLabel("Employee ID");
        empIdLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,20));
        empIdLabel.setForeground(ColorUIResource.BLUE);
        panel.add(empIdLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        JTextField empIdTextField=new JTextField();
        empIdTextField.setText(empId);
        empIdTextField.setPreferredSize(new Dimension(250,30));
        empIdTextField.setFont(new Font("arial",Font.CENTER_BASELINE,20));
        empIdTextField.setTransferHandler(null);
        empIdTextField.setToolTipText("Your Employee ID");
        empIdTextField.setBackground(allColors.getTextFieldBackground());
        empIdTextField.setForeground(allColors.getTextFieldForeground());
        empIdTextField.setEditable(false);
        panel.add(empIdTextField,gbc);

        // employee name
        gbc.gridx=0;
        gbc.gridy=2;
        JLabel empNameLabel=new JLabel("Employee Name");
        empNameLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,20));
        empNameLabel.setForeground(ColorUIResource.BLUE);
        panel.add(empNameLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        JTextField empNameTextField=new JTextField();  
        empNameTextField.setText(empName);
        empNameTextField.setPreferredSize(new Dimension(250,30));
        empNameTextField.setFont(new Font("arial",Font.CENTER_BASELINE,20));
        empNameTextField.setTransferHandler(null);
        empNameTextField.setToolTipText("Employee Name");
        empNameTextField.setBackground(allColors.getTextFieldBackground());
        empNameTextField.setForeground(allColors.getTextFieldForeground());
        empNameTextField.setEditable(false);
        panel.add(empNameTextField,gbc);

        // employee post
        gbc.gridx=0;
        gbc.gridy=3;
        JLabel empPostLabel=new JLabel("Employee Post");
        empPostLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,20));
        empPostLabel.setForeground(ColorUIResource.BLUE);
        panel.add(empPostLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        String[] jobFacilities={"Software Developer","Web Developer","PHP Developer","Android Developer","Software Tester","Flowchart Designer","Peon"};
        JComboBox jobComboBox=new JComboBox(jobFacilities);
        jobComboBox.setFont(new Font("Tahoma",Font.PLAIN,20));
        jobComboBox.setSize(300,30);
        jobComboBox.setTransferHandler(null);
        jobComboBox.setSelectedIndex(getIndex(jobProfile));
        jobComboBox.setForeground(allColors.getTextFieldBackground());
        panel.add(jobComboBox,gbc);

        // employee salary
        gbc.gridx=0;
        gbc.gridy=4;
        JLabel empSalaryLabel=new JLabel("Employee Salary per Month");
        empSalaryLabel.setFont(new Font("verdana",Font.HANGING_BASELINE,20));
        empSalaryLabel.setForeground(ColorUIResource.BLUE);
        panel.add(empSalaryLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        JTextField empSalaryTextField=new JTextField();  
        empSalaryTextField.setText(""+empSalary);
        empSalaryTextField.setPreferredSize(new Dimension(250,30));
        empSalaryTextField.setFont(new Font("arial",Font.CENTER_BASELINE,20));
        empSalaryTextField.setTransferHandler(null);
        empSalaryTextField.setToolTipText("Please enter Ammount Max. 1000000000 and Min. 1000");
        empSalaryTextField.setBackground(allColors.getTextFieldBackground());
        empSalaryTextField.setForeground(ColorUIResource.WHITE);
        empSalaryTextField.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent key)
            {
                if((key.getKeyChar()=='0' && empSalaryTextField.getText().length()==0))
                key.consume();

                if(!Character.isDigit(key.getKeyChar()))
                key.consume();

                if(empSalaryTextField.getText().length()>10)
                key.consume();
            }
        });
        panel.add(empSalaryTextField,gbc);

        //submit button
        gbc.gridx=1;
        gbc.gridy=5;
        JButton submitButton=new JButton("Update");
        submitButton.setFont(new Font("verdana", Font.CENTER_BASELINE, 20));
        submitButton.setBackground(ColorUIResource.PINK);
        submitButton.setForeground(ColorUIResource.BLUE);
        submitButton.setPreferredSize(new Dimension(100,30));
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setToolTipText("Update ");
        panel.add(submitButton,gbc);
        submitButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                boolean checkSalary=false;

                // employee Job Profile
                jobProfile=jobFacilities[jobComboBox.getSelectedIndex()];

                // final checking 
                if(new AllFieldValidation().isValidAmmount(empSalaryTextField))
                {
                    new Thread(new Runnable(){
                        public void run()
                        {
                            isTask=true;
                            submitButton.setVisible(false);
                            PreLoader preLoader=new PreLoader();
                            JLabel loaderLabel=preLoader.createPreloader(panel, gbc);

                            UpdateJobHistory updateJobHistory=new UpdateJobHistory();
                            updateJobHistory.setEmpId(empIdTextField.getText());
                            updateJobHistory.setEmpSalary(Long.parseLong(empSalaryTextField.getText()));
                            updateJobHistory.setEmpName(empName);
                            updateJobHistory.setJobProfile(jobProfile);

                            int response=updateJobHistory.updatejobHistory();
                            if(response==0)
                            JOptionPane.showMessageDialog(frame,"Employee Job History Updated successfully");
                            else
                            JOptionPane.showMessageDialog(frame,"Server Error or interrupted connection","error",0);

                            submitButton.setVisible(true);
                            preLoader.dismisPreloader(panel,loaderLabel);
                            isTask=false;
                        }
                    }).start();
                }
                else
                JOptionPane.showMessageDialog(frame,"Invalid details entered whoose are indicate red color","validation",0);
            }
        });
       

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
                    new EmployeeJobHistorySearch();
                } 
            }
        });

        tempFrame.removeAll();
        tempFrame.setVisible(false);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
