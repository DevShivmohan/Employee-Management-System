package icons;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class AllHomePageIcon extends AllIcons
{
    private ImageIcon[] imageIcons=new ImageIcon[9];
    public AllHomePageIcon()
    {
        try
        {
            String[] photoNames={"home_wallpaper.jpeg","home_employee.png","home_talent.png","home_financial.png","home_benefits.png","home_about.png","home_setting.png","home_accounting.png","home_attendance.png"};
            FileInputStream fileInputStream=new FileInputStream(setUpEmpFileStructure()+"//allhomeicons.binary");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            HashMap<String,ImageIcon> map=(HashMap<String,ImageIcon>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            for (Map.Entry<String,ImageIcon> ele : map.entrySet())
            {
                // home wallpaper
                if(photoNames[0].equals(ele.getKey()))
                imageIcons[0]=ele.getValue();

                // employee
                if(photoNames[1].equals(ele.getKey()))
                imageIcons[1]=ele.getValue();
                
                // talent
                if(photoNames[2].equals(ele.getKey()))
                imageIcons[2]=ele.getValue();

                //financial
                if(photoNames[3].equals(ele.getKey()))
                imageIcons[3]=ele.getValue();

                // benefits
                if(photoNames[4].equals(ele.getKey()))
                imageIcons[4]=ele.getValue();

                // about
                if(photoNames[5].equals(ele.getKey()))
                imageIcons[5]=ele.getValue();

                //setting
                if(photoNames[6].equals(ele.getKey()))
                imageIcons[6]=ele.getValue();

                // accounting
                if(photoNames[7].equals(ele.getKey()))
                imageIcons[7]=ele.getValue();

                //attendance
                if(photoNames[8].equals(ele.getKey()))
                imageIcons[8]=ele.getValue();
            }
        }catch(Exception e){
            System.out.println("Error in home icon "+e);
        }
    }    

    public ImageIcon getHomeWallpaper(){
        return imageIcons[0];
    }

    public ImageIcon getHomeEmployee(){
        return imageIcons[1];
    }

    public ImageIcon getHomeTalent(){
        return imageIcons[2];
    }

    public ImageIcon getHomeFinancial(){
        return imageIcons[3];
    }

    public ImageIcon getHomeBenefits(){
        return imageIcons[4];
    }

    public ImageIcon getHomeAbout(){
        return imageIcons[5];
    }

    public ImageIcon getHomeSetting(){
        return imageIcons[6];
    }

    public ImageIcon getHomeAccounting(){
        return imageIcons[7];
    }

    public ImageIcon getHomeAttendance(){
        return imageIcons[8];
    }
}