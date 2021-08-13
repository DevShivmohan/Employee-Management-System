package validation;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

public class AllFieldValidation
{
    // delete admin user
    public boolean isValidUsername(JTextField userNameTextField)
    {
        boolean b1=false;
        // username
        userNameTextField.setBackground(ColorUIResource.CYAN);
        if(userNameTextField.getText().isBlank())
        userNameTextField.setBackground(ColorUIResource.RED);
        else if(userNameTextField.getText().length()>9)
        b1=true;
        else
        userNameTextField.setBackground(ColorUIResource.RED);

        return b1;
    }
    
    // add admin user
    public boolean isValidateAddUserFields(JTextField name,JTextField email,JTextField pass)
    {
        boolean b1=false,b2=false,b3=false;

        name.setBackground(ColorUIResource.CYAN);
        email.setBackground(ColorUIResource.CYAN);
        pass.setBackground(ColorUIResource.CYAN);

        //name
        if(name.getText().isBlank())
        name.setBackground(ColorUIResource.RED);
        else
        b1=true;

        //email
        if(email.getText().isBlank())
        email.setBackground(ColorUIResource.RED);
        else
        {
            if(email.getText().length()>10)
            b2=true;
            else
            email.setBackground(ColorUIResource.RED);
        }

        //pass
        if(pass.getText().isBlank())
        pass.setBackground(ColorUIResource.RED);
        else
        {
            if(pass.getText().length()>8)
            b3=true;
            else
            pass.setBackground(ColorUIResource.RED);
        }

        if(b1 && b2 && b3)
        return true;
        else
        return false;
    }

    // notice class
    public boolean isValidNoticeField(JTextField subject,JTextArea content)
    {
        boolean b1=false,b2=false;

        subject.setBackground(ColorUIResource.CYAN);
        content.setBackground(ColorUIResource.CYAN);
        // subject
        if(!subject.getText().isBlank())
        {
            if(subject.getText().length()>9 && subject.getText().length()<=100)
            b1=true;
            else
            subject.setBackground(ColorUIResource.RED);
        }
        else
        subject.setBackground(ColorUIResource.RED);

        //content
        if(!content.getText().isBlank())
        {
            if(content.getText().length()>=20 && content.getText().length()<1000)
            b2=true;
            else
            content.setBackground(ColorUIResource.RED);
        }
        else
        content.setBackground(ColorUIResource.RED);

        if(b1 && b2)
        return true;
        else
        return false;
    }

    //employee id validation
    public boolean isValidEmpId(JTextField textField){
        boolean value=false;
        textField.setBackground(ColorUIResource.CYAN);
        if(!textField.getText().isBlank() && textField.getText().length()==15)
        value=true;
        else
        textField.setBackground(ColorUIResource.RED);

        return value;
    }

    // ammount validation
    public boolean isValidAmmount(JTextField textField){
        boolean value=false;
        textField.setBackground(ColorUIResource.PINK);
        if(!textField.getText().isBlank() && textField.getText().length()>4 && textField.getText().length()<10)
        value=true;
        else
        textField.setBackground(ColorUIResource.RED);
        return value;
    }

    // receipt no validation
    public boolean isValidReceiptId(JTextField textField){
        boolean value=false;
        textField.setBackground(ColorUIResource.CYAN);
        if(!textField.getText().isBlank() && textField.getText().length()==20)
        value=true;
        else
        textField.setBackground(ColorUIResource.RED);
        return value;
    }
}