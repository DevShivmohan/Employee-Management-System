import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import icons.AllColors;
public class RegisterValidation
{
    AllColors allColors=new AllColors();

    public boolean isName(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()>=3 && textField.getText().length()<=50)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isFatherName(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()>=3 && textField.getText().length()<=50)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isDOB(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()==8)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isMobile(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()==10)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isAadhaar(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()==12)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isEmail(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()>=12 && textField.getText().length()<=60 && textField.getText().indexOf("@",0)!=-1 && textField.getText().indexOf("mail.com",0)!=-1)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isAddress(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()>=5 && textField.getText().length()<=100)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isAccount(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()<=25 && textField.getText().length()>=10)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isIFSC(JTextField textField)
    {
        if(!textField.getText().isBlank() && textField.getText().length()<=25 && textField.getText().length()>=8)
        {
            textField.setBackground(allColors.getTextFieldBackground());
            return true;
        }
        else
        {
            textField.setBackground(ColorUIResource.RED);
            return false;
        }
    }

    public boolean isJobProfile(JComboBox comboBox)
    {
        if(comboBox.getSelectedIndex()!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getJobProfile(JComboBox comboBox)
    {
        String jobProfile=null;
        if(comboBox.getSelectedIndex()==1)
            jobProfile="Software Developer";
        if(comboBox.getSelectedIndex()==2)
            jobProfile="Web Developer";
        if(comboBox.getSelectedIndex()==3)
            jobProfile="PHP Developer";
        if(comboBox.getSelectedIndex()==4)
            jobProfile="Android Developer";
        if(comboBox.getSelectedIndex()==5)
            jobProfile="Software Tester";
        if(comboBox.getSelectedIndex()==6)
            jobProfile="Flowchart Designer";
        if(comboBox.getSelectedIndex()==7)
            jobProfile="Peon";
        return jobProfile;
    }

}