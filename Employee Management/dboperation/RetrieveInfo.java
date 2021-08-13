package dboperation;
import java.io.File;
import java.io.FileOutputStream;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;

import security.Encryption;
public class RetrieveInfo extends DBConnection
{
    private byte[] photoByte;
    private byte[] sigByte;
    private String empUnique;
    private String empName;
    private String empFather;
    private String empDob;
    private String empPost;
    private String empGender;
    private String empMobile;
    private String empAadhaar;
    private String empEmail;
    private String empAddress;
    private String empAccount;
    private String empIfsc;
    private byte[] SSL;
    private String extenp;
    private String extens;
    private long empSalary;
    DBConnection dbConnection;
    Encryption encryption=new Encryption();
    public int checkEmpUnique(String unique)
    {
        try
        {
            // db connection
            MongoClient mongoClient=getConnection();
            empUnique=unique;
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String table : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(table).find(new BasicDBObject("id",unique)); // checking via employee id
                while(cursor.hasNext())
                {
                    cursor.next();
                    empUnique=unique;
                    return 0;
                }   
            }
            return 1;
        }
        catch(Exception e){
            System.out.println("Error "+e);
            return 2;
        }
    }

    public String checkEmployeeIdAadhaar(String id)
    {
        try
        {
            // db connection
            MongoClient mongoClient=getConnection();
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String table : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(table).find(); // checking via employee id and aadhaar
                while(cursor.hasNext())
                {
                    cursor.next();
                    String tempId=(String)cursor.curr().get("id");
                    SSL=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                    String tempAadhaar=new String(encryption.getDecryptedData((byte[])cursor.curr().get("aadhaar"),SSL));
                    if(id.equalsIgnoreCase(tempId) || id.equals(tempAadhaar))
                    {
                        return tempId;
                    }
                }   
            }
            return "1";
        }
        catch(Exception e){
            return "error";
        }
    }

    public void getEmpJobHistory(String empUnique)
    {
        try
        {
            //emprecord
            MongoClient mongoClient=getConnection();
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String table : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(table).find(new BasicDBObject("id",empUnique));
                while(cursor.hasNext())
                {
                    cursor.next();
                    byte[] key=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                    SSL=key;
                }
            }

            //basicInfo
            DBCursor cursor=getConnection().getDB("empinfo").getCollection("empbasicinfo"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empName=new String(encryption.getDecryptedData((byte[])cursor.curr().get("name"),SSL));
            }

            //jobprofile
            cursor=getConnection().getDB("empinfo").getCollection("empjobprofile"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empPost=new String(encryption.getDecryptedData((byte[])cursor.curr().get("post"),SSL));
                empSalary=(long)cursor.curr().get("salary");
            }


        }catch(Exception e){}
    }

    public void setEmpInfo(String empUnique){
        try
        {
            // db connection
            MongoClient mongoClient=getConnection();
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String table : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(table).find(new BasicDBObject("id",empUnique));
                while(cursor.hasNext())
                {
                    cursor.next();
                    byte[] key=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                    SSL=key;
                }
            }

            DBCursor cursor=getConnection().getDB("empinfo").getCollection("empbasicinfo"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empName=new String(encryption.getDecryptedData((byte[])cursor.curr().get("name"),SSL));
                empFather=new String(encryption.getDecryptedData((byte[])cursor.curr().get("father"),SSL));
                empDob=new String(encryption.getDecryptedData((byte[])cursor.curr().get("dob"),SSL));
                empGender=new String(encryption.getDecryptedData((byte[])cursor.curr().get("gender"),SSL));
            }
            

            cursor=getConnection().getDB("empinfo").getCollection("empcontact"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empMobile=new String(encryption.getDecryptedData((byte[])cursor.curr().get("mobile"),SSL));
                empAadhaar=new String(encryption.getDecryptedData((byte[])cursor.curr().get("aadhaar"),SSL));
                empEmail=new String(encryption.getDecryptedData((byte[])cursor.curr().get("email"),SSL));
            }

            cursor=getConnection().getDB("empinfo").getCollection("empaddress"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empAddress=new String(encryption.getDecryptedData((byte[])cursor.curr().get("address"),SSL));
            }

            cursor=getConnection().getDB("empinfo").getCollection("empbank"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empAccount=new String(encryption.getDecryptedData((byte[])cursor.curr().get("account"),SSL));
                empIfsc=new String(encryption.getDecryptedData((byte[])cursor.curr().get("ifsc"),SSL));
            }

            cursor=getConnection().getDB("empinfo").getCollection("empjobprofile"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empPost=new String(encryption.getDecryptedData((byte[])cursor.curr().get("post"),SSL));
                empSalary=(long)cursor.curr().get("salary");
            }

            cursor=getConnection().getDB("empinfo").getCollection("empfile"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                photoByte=encryption.getDecryptedData((byte[])cursor.curr().get("photo"),SSL);
                sigByte=encryption.getDecryptedData((byte[])cursor.curr().get("sig"),SSL);
                extenp=(String) cursor.curr().get("extenp");
                extens=(String) cursor.curr().get("extens");
            }
        }catch(Exception e){}
    }

    public String getEmpName(){
        return empName;
    }

    public String getEmpFather(){
        return empFather;
    }

    public String getEmpDob(){
        return empDob;
    }

    public String getEmpGender(){
        return empGender;
    }

    public String getEmpMobile(){
        return empMobile;
    }

    public String getEmpAadhaar(){
        return empAadhaar;
    }

    public String getEmpEmail(){
        return empEmail;
    }

    public String getEmpAddress(){
        return empAddress;
    }

    public String getEmpAccount(){
        return empAccount;
    }

    public String getEmpIfsc(){
        return empIfsc;
    }

    public String getEmpPost(){
        return empPost;
    }

    public long getEmpSalary(){
        return empSalary;
    }

    public byte[] getSSL(){
        return SSL;
    }

    private String setUpEmpFileStructure()
    {
        File file=new File(System.getProperty("user.home")+"//EmployeeManagementSystem");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//EmployeeRequire");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//EmpFile");
        file.mkdir();
        return file.getAbsolutePath();
    }

    public File getPhotoBytes()throws Exception{
        String path=setUpEmpFileStructure(); // creating folder
        File file=new File(path+"//"+extenp);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(photoByte);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file;
    }

    public File getSigBytes()throws Exception{
        String path=setUpEmpFileStructure(); // creating folder
        File file=new File(path+"//"+extens);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(sigByte);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file;
    }

    public void setEmpDeleteData()
    {
        try
        {
            // db connection
            MongoClient mongoClient=getConnection();
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String table : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(table).find(new BasicDBObject("id",empUnique));
                while(cursor.hasNext())
                {
                    cursor.next();
                    byte[] key=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                    SSL=key;
                    empAadhaar=new String(encryption.getDecryptedData((byte[])cursor.curr().get("aadhaar"),SSL));
                    empMobile=new String(encryption.getDecryptedData((byte[])cursor.curr().get("mobile"),SSL));
                }
            }

            // fetching basic info
            DBCursor cursor=getConnection().getDB("empinfo").getCollection("empbasicinfo"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empName=new String(encryption.getDecryptedData((byte[])cursor.curr().get("name"),SSL));
                empFather=new String(encryption.getDecryptedData((byte[])cursor.curr().get("father"),SSL));
                empDob=new String(encryption.getDecryptedData((byte[])cursor.curr().get("dob"),SSL));
            }

            // fetching emial
            cursor=getConnection().getDB("empinfo").getCollection("empcontact"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empEmail=new String(encryption.getDecryptedData((byte[])cursor.curr().get("email"),SSL));
            }
            
            // fetching address
            cursor=getConnection().getDB("empinfo").getCollection("empaddress"+empUnique.charAt(3)+empUnique.charAt(4)+empUnique.charAt(5)+empUnique.charAt(6)).find(new BasicDBObject("id",empUnique));
            while(cursor.hasNext()){
                cursor.next();
                empAddress=new String(encryption.getDecryptedData((byte[])cursor.curr().get("address"),SSL));
            }
        }
        catch(Exception e){}
    }
}