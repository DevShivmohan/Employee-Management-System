package icons;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
public class AllColors extends AllIcons
{
    Color textFieldBackground=ColorUIResource.BLUE;
    Color textFieldForeground=new ColorUIResource(0,0,0);
    Color frameBackground=new ColorUIResource(22,11,33);
    Color panelBackground=ColorUIResource.DARK_GRAY;
    Color imgButtonBackground=new ColorUIResource(11,20,11);
    Color imgButtonForeground=ColorUIResource.MAGENTA;
    Color homePanelBackground=new ColorUIResource(15,40,20);
    Color animationPanelBackground=new ColorUIResource(25,255,255);

    public Color getTextFieldBackground(){
        return textFieldBackground;
    }

    public Color getAnimationPanelBackground(){
        if(isSetDarkMode())
        return Color.BLACK;
        else
        return animationPanelBackground;
    }

    public Color getHomePanelBackground(){
        if(isSetDarkMode())
        return ColorUIResource.BLACK;
        else
        return homePanelBackground;
    }

    public Color getTextFieldForeground(){
        return textFieldForeground;
    }

    public Color getFrameBackground(){
        if(isSetDarkMode())
        return ColorUIResource.BLACK;
        else
        return frameBackground;
    }

    public Color getPanelBackground(){

        if(isSetDarkMode())
        return Color.BLACK;
        else
        return panelBackground;
    }

    public Color getImgButtonBackground(){
        return imgButtonBackground;
    }

    public Color getImgButtonForeground(){
        return imgButtonForeground;
    }

    public void setDarkMode()
    {
        try
        {
            byte[] color="BLACK".getBytes();
            setUpEmpFileStructure();
            File file=new File(System.getProperty("user.home")+"//EmployeeManagementSystem//EmployeeRequire//Color");
            file.mkdir();
            FileOutputStream fileOutputStream=new FileOutputStream(file.getAbsolutePath()+"//dark.shiv");
            fileOutputStream.write(color);
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch(Exception e){}
    }

    private boolean isSetDarkMode()
    {
        try
        {
            File file=new File(System.getProperty("user.home")+"//EmployeeManagementSystem//EmployeeRequire//Color//dark.shiv");
            FileInputStream fileInputStream=new FileInputStream(file);
            String color=new String(fileInputStream.readAllBytes());
            fileInputStream.close();
            if("BLACK".equals(color))
            return true;
            else
            return false;
        }
        catch(Exception e){
            return false;
        }
    }

    public void setLightMode()
    {
        try
        {
            File file=new File(System.getProperty("user.home")+"//EmployeeManagementSystem//EmployeeRequire//Color//dark.shiv");
            file.delete();
        }catch(Exception e){}
    }
}
