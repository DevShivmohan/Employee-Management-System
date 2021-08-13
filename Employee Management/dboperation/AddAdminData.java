package dboperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import empmail.EmpMailOperation;
import icons.FileStructure;
import security.Encryption;
import security.SRandom;

public class AddAdminData extends DBConnection
{
    private MongoClient mongoClient;

    public AddAdminData()
    {
        this.mongoClient=getConnection();
    }

    // checking admin user already in database or not
    public int checkUser(String userName)
    {
        try
        {
            Encryption encryption=new Encryption();
            com.mongodb.DBCursor cursor = mongoClient.getDB("empadmin").getCollection("empusers").find();
            while(cursor.hasNext())
            {
                cursor.next();
                byte[] uname=(byte[]) cursor.curr().get("username");
                if(encryption.getAdminDecryptedData(uname).equalsIgnoreCase(userName))
                {
                    return 0; // username matchd
                }
            }
            return 1; // not matched
        }
        catch(Exception e)
        {
            return 2; // error
        }
    }

    // checking admin Role
    public int getAdminRole()
    {
        try
        {
            String userName=getUser(); //getting logged username
            Encryption encryption=new Encryption();
            DBCursor cursor=mongoClient.getDB("empadmin").getCollection("empusers").find();
            while(cursor.hasNext())
            {
                cursor.next();
                if(userName.equalsIgnoreCase(encryption.getAdminDecryptedData((byte[])cursor.curr().get("username"))) && "AtlasAdmin".equals(encryption.getAdminDecryptedData((byte[])cursor.curr().get("role"))))
                return 0; // user allowed all access
            }
            return 1; // user is not alloed to do action
        }
        catch(Exception e){
            return 2; // server error
        }
    }

    private String getUser()
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
                    return element.getValue();
                }
            }
            map.clear();
            return null;
        }
        catch(Exception e)
        {
            return null;
        }
    } 
    
    public boolean uploadAdminData(String name,String userName,String pass,String role)
    {
        Encryption encryption=new Encryption();
        try
        {
            String timeStamp=new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
            String status=new SRandom().getRandom();

            BasicDBObject object=new BasicDBObject();
            object.put("name",encryption.getAdminEncryptedData(name));
            object.put("username",encryption.getAdminEncryptedData(userName));
            object.put("password",encryption.getAdminEncryptedData(pass));
            object.put("role",encryption.getAdminEncryptedData(role));
            object.put("datetime",encryption.getAdminEncryptedData(timeStamp));
            object.put("status",status);
            mongoClient.getDB("empadmin").getCollection("empusers").insert(object); // inserted successfully
            String message="Dear <b>"+name+"</b><br>You are successfully registered in ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED for Admin.<br>Your Username <b>"+userName+"</b> and Password <b>"+pass+"</b> Your Admin Role is <b>"+role+"</b>.<br>Registered Timestamp is <b>"+timeStamp+"</b><br>Thank You";
            new EmpMailOperation().sendMail(message,userName);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}