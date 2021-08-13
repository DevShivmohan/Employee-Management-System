package dboperation;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoIterable;

import org.bson.Document;

import empmail.EmpMailOperation;
import icons.PrepareReciept;
import security.Encryption;

public class UploadData extends DBConnection
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
    private String empJobProfile;
    private String key;
    private String empId;
    private long empSalary;
    private File sigFile;
    private File photoFile;

    private MongoClient mongoClient;
    private ClientSession clientSession;

    public UploadData(){
        mongoClient=getConnection();
    }

    public void setEmpId(String id){
        empId=id;
    }

    public String getEmpId(){
        return empId;
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

    public void setEmpJobProfile(String empName){
        this.empJobProfile=empName;
    }

    public void setEmpSigFile(File empName){
        this.sigFile=empName;
    }

    public void setEmpPhotoFile(File empName){
        this.photoFile=empName;
    }

    public void setEmpSalary(long salary){
        this.empSalary=salary;
    }

    public void setKey(String key){
        this.key=key;
    }

    // inserting data
    public boolean uploadEmpInfo()
    {
        Encryption encryption=new Encryption();
        String YEAR=new SimpleDateFormat("yyyy").format(new Date()); // getting current year
        try
        {
            try
            {
                // main collection
                mongoClient.getDatabase("emprecord").createCollection("emp"+YEAR);
            }catch(Exception e){}

            clientSession=mongoClient.startSession();
            clientSession.startTransaction();
            try
            {
                // creating all collection
                mongoClient.getDatabase("empinfo").createCollection("empbasicinfo"+YEAR);
                mongoClient.getDatabase("empinfo").createCollection("empcontact"+YEAR);
                mongoClient.getDatabase("empinfo").createCollection("empaddress"+YEAR);
                mongoClient.getDatabase("empinfo").createCollection("empbank"+YEAR);
                mongoClient.getDatabase("empinfo").createCollection("empjobprofile"+YEAR);
                mongoClient.getDatabase("empinfo").createCollection("empfile"+YEAR);

            }catch(Exception e){}

            // inserting main
            String datetime=new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
            Document document=new Document();
            document.put("id",empId);
            document.put("aadhaar",encryption.getEncryptedData(empAadhaar.getBytes(),key.getBytes()));
            document.put("mobile",encryption.getEncryptedData(empMobile.getBytes(),key.getBytes()));
            document.put("datetime",encryption.getEncryptedData(datetime.getBytes(),key.getBytes()));
            document.put("SSL",encryption.getEncryptedSSL(key.getBytes()));
            mongoClient.getDatabase("emprecord").getCollection("emp"+YEAR).insertOne(clientSession,document); // inserted document

            
            // inserting basic info
            document=new Document();
            document.put("id",empId);
            document.put("name",encryption.getEncryptedData(empName.getBytes(),key.getBytes()));
            document.put("father",encryption.getEncryptedData(empFather.getBytes(),key.getBytes()));
            document.put("dob",encryption.getEncryptedData(empDob.getBytes(),key.getBytes()));
            document.put("gender",encryption.getEncryptedData(empGender.getBytes(),key.getBytes()));
            mongoClient.getDatabase("empinfo").getCollection("empbasicinfo"+YEAR).insertOne(clientSession,document); // inserted document

            // inserting contact info
            document=new Document();
            document.put("id",empId);
            document.put("mobile",encryption.getEncryptedData(empMobile.getBytes(),key.getBytes()));
            document.put("aadhaar",encryption.getEncryptedData(empAadhaar.getBytes(),key.getBytes()));
            document.put("email",encryption.getEncryptedData(empEmail.getBytes(),key.getBytes()));
            mongoClient.getDatabase("empinfo").getCollection("empcontact"+YEAR).insertOne(clientSession,document); // inserted document

            //inserting address info
            document=new Document();
            document.put("id",empId);
            document.put("address",encryption.getEncryptedData(empAddress.getBytes(),key.getBytes()));
            mongoClient.getDatabase("empinfo").getCollection("empaddress"+YEAR).insertOne(clientSession,document); // inserted document

            // inserting bank info
            document=new Document();
            document.put("id",empId);
            document.put("account",encryption.getEncryptedData(empAccount.getBytes(),key.getBytes()));
            document.put("ifsc",encryption.getEncryptedData(empIfsc.getBytes(),key.getBytes()));
            mongoClient.getDatabase("empinfo").getCollection("empbank"+YEAR).insertOne(clientSession,document); // inserted document

            // inserting job profile info
            document=new Document();
            document.put("id",empId);
            document.put("post",encryption.getEncryptedData(empJobProfile.getBytes(),key.getBytes()));
            document.put("salary",empSalary);
            mongoClient.getDatabase("empinfo").getCollection("empjobprofile"+YEAR).insertOne(clientSession,document); // inserted document

            // inserting empFile info
            document=new Document();
            FileInputStream fileInputStream=new FileInputStream(photoFile);
            document.put("id",empId);
            String exten=sigFile.getName();
            exten="sig"+exten.substring(exten.lastIndexOf("."),exten.length());
            document.put("extens",exten);
            exten=photoFile.getName();
            exten="image"+exten.substring(exten.lastIndexOf("."),exten.length());
            document.put("extenp",exten);
            document.put("photo",encryption.getEncryptedData(fileInputStream.readAllBytes(),key.getBytes()));
            fileInputStream=new FileInputStream(sigFile);
            document.put("sig",encryption.getEncryptedData(fileInputStream.readAllBytes(),key.getBytes()));
            mongoClient.getDatabase("empinfo").getCollection("empfile"+YEAR).insertOne(clientSession,document); // inserted document
            // commiting transactions
            clientSession.commitTransaction(); // successfully inserted
            clientSession.close();

            // sending messages
            String messageFinal="Welcome <b>"+empName+"</b><br> Your always welcome in our industry your record will be successfully saved in our database.Your Employee id <b>"+empId+"</b> and Job Profile <b>"+empJobProfile+"</b> registered mobile <b>XXXXXXXXX"+empMobile.charAt(7)+empMobile.charAt(8)+empMobile.charAt(9)+"</b>.<br>Thank You";
            new EmpMailOperation().sendMail(messageFinal,empEmail);

            // sending pdf file
            PrepareReciept prepareReciept=new PrepareReciept();
            prepareReciept.setEmpId(empId);
            prepareReciept.setEmpName(empName);
            prepareReciept.setEmpFather(empFather);
            prepareReciept.setEmpDob(empDob);
            prepareReciept.setEmpGender(empGender);
            prepareReciept.setEmpMobile(empMobile);
            prepareReciept.setEmpAadhaar(empAadhaar);
            prepareReciept.setEmpEmail(empEmail);
            prepareReciept.setEmpAddress(empAddress);
            prepareReciept.setEmpAccount(empAccount);
            prepareReciept.setEmpIfsc(empIfsc);
            prepareReciept.setEmpPost(empJobProfile);
            prepareReciept.setEmpPhotoFile(photoFile);
            prepareReciept.setEmpSignFile(sigFile);
            prepareReciept.createEmployeeRegistrationAcknowledgement();

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    // checking employee record exist or not and also setting employee id 
    public boolean checkEmployee()
    {
        try
        {
            boolean check=false,isTableExist=false;
            String tempId="";
            Encryption encryption=new Encryption();
            try
            {
                // checking already employee exist or not via aadhaar
                MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
                for (String string : tables)
                {
                    DBCursor cursor=mongoClient.getDB("emprecord").getCollection(string).find();
                    while(cursor.hasNext())
                    {
                        cursor.next();
                        isTableExist=true;
                        tempId=(String)cursor.curr().get("id");
                        if(empAadhaar.equals(new String(encryption.getDecryptedData((byte[])cursor.curr().get("aadhaar"),encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"))))))
                        {
                            check=true;
                        }
                    }
                }
            }catch(Exception e){}

            //getting employee id
            try
            {
                String YEAR=new SimpleDateFormat("yyyy").format(new Date());
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection("emp"+YEAR).find();
                while(cursor.hasNext())
                {
                    cursor.next();
                    tempId=(String)cursor.curr().get("id");
                }
            }
            catch(Exception e){}


            String year="";
            if(isTableExist)
            {
                year=""+tempId.charAt(3)+tempId.charAt(4)+tempId.charAt(5)+tempId.charAt(6);
                tempId=""+tempId.charAt(7)+tempId.charAt(8)+tempId.charAt(9)+tempId.charAt(10)+tempId.charAt(11)+tempId.charAt(12)+tempId.charAt(13)+tempId.charAt(14);
                try
                {
                    tempId=""+(Long.parseLong(tempId)+1);
                }catch(Exception e){}
            }

            if(check)
            {
                // if aadhaar matched
                return true;
            }
            else if(isTableExist)
            {
                // if table exist
                if(!new SimpleDateFormat("yyyy").format(new Date()).equals(year))
                genEmpId();
                else
                setEmpId("EMP"+year+tempId);
            }
            else 
            {
                // if does not any tables
                genEmpId();
            }
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void genEmpId()
    {
        String id="EMP"+new SimpleDateFormat("yyyy").format(new Date())+"10000000";
        setEmpId(id);
    }
}
