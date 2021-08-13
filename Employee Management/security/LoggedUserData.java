package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import icons.FileStructure;

public class LoggedUserData
{
    public String getLoggedUserName()
    {
        try
        {
            FileStructure fileStructure=new FileStructure();
            File file=new File(fileStructure.getSessionFileStructure()+"//AdminInfo.binary");
            FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            HashMap<String,String> map=(HashMap<String,String>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            for (Map.Entry<String,String> element : map.entrySet())
            {
                if(element.getKey().equals("username"))
                {
                    String userName=element.getValue();
                    return userName;
                }
            }
            map.clear();
            return "";
        }
        catch(Exception e)
        {
            return "";
        }
    }    
}
