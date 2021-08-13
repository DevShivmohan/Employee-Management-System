import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import security.Encryption;
import security.SRandom;

public class LoginSession extends Encryption
{
    SRandom sRandom=new SRandom();
    public boolean createSession(String userName)
    {
        try
        {
            // main file
            File file=new File(getSessionFileStructure()+"//LoginSession.shiv");
            byte[] k=sRandom.getInitAdminKey().getBytes(); // key decryption key
            byte[] key=sRandom.getRandom().getBytes(); // file decryption key
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(getEncryptedData(userName.getBytes(), key));
            fileOutputStream.flush();
            fileOutputStream.close();

            // key file
            file=new File(getSessionFileStructure()+"//LoginGrant.sta");
            fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(getEncryptedData(key,k));
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    private String getSessionFileStructure()
    {
        File file=new File(System.getProperty("user.home")+"//EmployeeManagementSystem");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//EmployeeRequire");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//Session");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//LoginSession");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//UserAccess");
        file.mkdir();
        return file.getAbsolutePath();
    }

    public boolean isSetSession()
    {
        try
        {
            byte[] key=sRandom.getInitAdminKey().getBytes();
            FileInputStream fileInputStream=new FileInputStream(getSessionFileStructure()+"//LoginSession.shiv");
            FileInputStream fileInputStream2=new FileInputStream(getSessionFileStructure()+"//LoginGrant.sta");
            byte[] data=getDecryptedData(fileInputStream.readAllBytes(),getDecryptedData(fileInputStream2.readAllBytes(),key));
            fileInputStream2.close();
            fileInputStream.close();
            if(data!=null)
            return true;
            else
            return false;
        }
        catch(Exception e){
            return false;
        }
    }

    public void destroySession()
    {
        try
        {
            new File(getSessionFileStructure()+"//LoginSession.shiv").delete();
            new File(getSessionFileStructure()+"//LoginGrant.sta").delete();
            new File(getSessionFileStructure()+"//Random.shiv").delete();
            new File(getSessionFileStructure()+"//AdminInfo.binary").delete();
        }catch(Exception e){}
    }

    public String getUserName()
    {
        try
        {
            byte[] key=sRandom.getInitAdminKey().getBytes();
            FileInputStream fileInputStream=new FileInputStream(getSessionFileStructure()+"//LoginSession.shiv");
            FileInputStream fileInputStream2=new FileInputStream(getSessionFileStructure()+"//LoginGrant.sta");
            byte[] data=getDecryptedData(fileInputStream.readAllBytes(),getDecryptedData(fileInputStream2.readAllBytes(),key));
            fileInputStream2.close();
            fileInputStream.close();
            String userName=new String(data);
            if(userName==null)
            return null;
            else
            return userName;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}