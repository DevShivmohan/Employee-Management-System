package dboperation;

import java.io.File;
import java.io.FileInputStream;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoIterable;

import org.bson.Document;

import security.Encryption;
import security.SRandom;

public class UpdateData extends DBConnection
{
    private String empName;
    private String empFather;
    private String empDob;
    private String empGender;
    private String empMobile;
    private String empAadhaar;
    private String empEmail;
    private String empAddress;
    private String empAccount;
    private String empIfsc;
    private String key;
    private byte[] SSL;
    private String empId;
    private File sigFile;
    private File photoFile;

    private MongoClient mongoClient;

    public UpdateData(){
        mongoClient=getConnection(); //initializing connection
        key=new SRandom().getInitKey();
    }

    public void setEmpId(String id){
        empId=id;
    }

    public void setEmpName(String empName){
        this.empName=empName;
    }

    public void setEmpFatherName(String empName){
        this.empFather=empName;
    }

    public void setEmpDob(String empName){
        this.empDob=empName;
    }

    public void setEmpGender(String empGender){
        this.empGender=empGender;
    }

    public void setEmpMobile(String empName){
        this.empMobile=empName;
    }

    public void setEmpAadhaar(String empName){
        this.empAadhaar=empName;
    }

    public void setEmpEmail(String empName){
        this.empEmail=empName;
    }

    public void setEmpAddress(String empName){
        this.empAddress=empName;
    }

    public void setEmpAccount(String empName){
        this.empAccount=empName;
    }

    public void setEmpIfsc(String empName){
        this.empIfsc=empName;
    }

    public void setEmpSigFile(File empName){
        this.sigFile=empName;
    }

    public void setEmpPhotoFile(File empName){
        this.photoFile=empName;
    }


    Encryption encryption=new Encryption();
    public int checkUpdateEmployee(String empId,String empAadhaar)
    {
        try
        {
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String string : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(string).find();
                while(cursor.hasNext())
                {
                    cursor.next();
                    String tempId=(String)cursor.curr().get("id");
                    byte[] keySSL=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                    byte[] dataDecrypt=encryption.getDecryptedData((byte[])cursor.curr().get("aadhaar"),keySSL);
                    String tempAadhaar=new String(dataDecrypt);
                    if(!empId.equalsIgnoreCase(tempId))
                    {
                        if(empAadhaar.equals(tempAadhaar))
                        {
                            return 1;
                        }
                    }
                }
            }
            return 0;
        }
        catch(Exception e){
            return 2;
        }
    }

    public boolean updateEmpData()
    {
        Encryption encryption=new Encryption();
        try
        {
            ClientSession clientSession=mongoClient.startSession();
            clientSession.startTransaction(); // transactions start

            Document document=new Document();
            Document filter=new Document("id",empId);
            System.out.println("Employee id "+empId);
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String string : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(string).find(new BasicDBObject("id",empId));
                if(cursor.hasNext())
                {
                    cursor.next();
                    SSL=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));

                    document.put("aadhaar",encryption.getEncryptedData(empAadhaar.getBytes(),SSL));
                    document.put("mobile",encryption.getEncryptedData(empMobile.getBytes(),SSL));
                    mongoClient.getDatabase("emprecord").getCollection(string).updateOne(clientSession,filter,new Document("$set",document)); // updated
                    break;
                }
            }
            
            // updating basic info
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empbasicinfo",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(new BasicDBObject("id",empId));
                    if(cursor.hasNext())
                    {
                        document=new Document();
                        document.put("name",encryption.getEncryptedData(empName.getBytes(),SSL));
                        document.put("father",encryption.getEncryptedData(empFather.getBytes(),SSL));
                        document.put("dob",encryption.getEncryptedData(empDob.getBytes(),SSL));
                        document.put("gender",encryption.getEncryptedData(empGender.getBytes(),SSL));
                        mongoClient.getDatabase("empinfo").getCollection(string).updateOne(clientSession,filter,new Document("$set",document)); // updated
                        break;
                    }
                }
            }

            //updating contact
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empcontact",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(new BasicDBObject("id",empId));
                    if(cursor.hasNext())
                    {
                        document=new Document();
                        document.put("mobile",encryption.getEncryptedData(empMobile.getBytes(),SSL));
                        document.put("aadhaar",encryption.getEncryptedData(empAadhaar.getBytes(),SSL));
                        document.put("email",encryption.getEncryptedData(empEmail.getBytes(),SSL));
                        mongoClient.getDatabase("empinfo").getCollection(string).updateOne(clientSession,filter,new Document("$set",document)); // updated
                        break;
                    }
                }
            }

            //updating address
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empaddress",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(new BasicDBObject("id",empId));
                    if(cursor.hasNext())
                    {
                        document=new Document();
                        document.put("address",encryption.getEncryptedData(empAddress.getBytes(),SSL));
                        mongoClient.getDatabase("empinfo").getCollection(string).updateOne(clientSession,filter,new Document("$set",document)); // updated
                        break;
                    }
                }
            }

            //updating bank 
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empbank",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(new BasicDBObject("id",empId));
                    if(cursor.hasNext())
                    {
                        document=new Document();
                        document.put("account",encryption.getEncryptedData(empAccount.getBytes(),SSL));
                        document.put("ifsc",encryption.getEncryptedData(empIfsc.getBytes(),SSL));
                        mongoClient.getDatabase("empinfo").getCollection(string).updateOne(clientSession,filter,new Document("$set",document)); // updated
                        break;
                    }
                }
            }

            //updating bio data
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empfile",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(new BasicDBObject("id",empId));
                    if(cursor.hasNext())
                    {
                        document=new Document();
                        if(photoFile!=null)
                        {
                            FileInputStream fileInputStream=new FileInputStream(photoFile);
                            String exten=photoFile.getName();
                            exten="image"+exten.substring(exten.lastIndexOf("."),exten.length()); // photo extension
                            document.put("photo",encryption.getEncryptedData(fileInputStream.readAllBytes(),SSL));
                            document.put("extenp",exten);
                            fileInputStream.close();
                        }
                        if(sigFile!=null)
                        {
                            FileInputStream fileInputStream=new FileInputStream(sigFile);
                            String exten=sigFile.getName();
                            exten="sig"+exten.substring(exten.lastIndexOf("."),exten.length()); // signature extension
                            document.put("sig",encryption.getEncryptedData(fileInputStream.readAllBytes(),SSL));
                            document.put("extens",exten);
                            fileInputStream.close();
                        }
                        if(sigFile!=null || photoFile!=null)
                        {
                            mongoClient.getDatabase("empinfo").getCollection(string).updateOne(clientSession,filter,new Document("$set",document)); // table updated
                            break;
                        }
                    }
                }
            }

            // commiting transactions
            clientSession.commitTransaction();
            clientSession.close();
            

            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}