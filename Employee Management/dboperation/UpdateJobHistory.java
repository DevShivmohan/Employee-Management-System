package dboperation;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoIterable;

import org.bson.Document;

import empmail.EmpMailOperation;
import security.Encryption;

public final class UpdateJobHistory extends DBConnection
{
    private String jobProfile;
    private long empSalary;
    private String empId;
    private String email;
    private byte[] SSL;
    private String empName;
    private MongoClient mongoClient;

    public UpdateJobHistory(){
        mongoClient=getConnection();
    }

    public void setEmpName(String empName){
        this.empName=empName;
    }

    public void setJobProfile(String jobProfile){
        this.jobProfile=jobProfile;
    }

    public void setEmpSalary(long empSalary){
        this.empSalary=empSalary;
    }

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public void setEmail(String email){
        this.email=email;
    }


    public int updatejobHistory()
    {
        try
        {
            Encryption encryption=new Encryption();

            ClientSession clientSession=mongoClient.startSession();
            clientSession.startTransaction(); // transactions start

            Document document=new Document();
            Document filter=new Document("id",empId);
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String string : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(string).find(new BasicDBObject("id",empId));
                if(cursor.hasNext())
                {
                    cursor.next();
                    SSL=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                    break;
                }
            }

            //getting email
            DBCursor cursor=mongoClient.getDB("empinfo").getCollection("empcontact"+empId.charAt(3)+empId.charAt(4)+empId.charAt(5)+empId.charAt(6)).find(new BasicDBObject("id",empId));
            if(cursor.hasNext())
            {
                cursor.next();
                setEmail(new String(encryption.getDecryptedData((byte[])(cursor.curr().get("email")),SSL)));
            }

            //getting name
            cursor=mongoClient.getDB("empinfo").getCollection("empbasicinfo"+empId.charAt(3)+empId.charAt(4)+empId.charAt(5)+empId.charAt(6)).find(new BasicDBObject("id",empId));
            if(cursor.hasNext())
            {
                cursor.next();
                setEmpName(new String(encryption.getDecryptedData((byte[])(cursor.curr().get("name")),SSL)));
            }

            document=new Document();
            document.put("post",encryption.getEncryptedData(jobProfile.getBytes(),SSL));
            document.put("salary",empSalary);

            Document document2=new Document();
            document2.put("$set",document);
            mongoClient.getDatabase("empinfo").getCollection("empjobprofile"+empId.charAt(3)+empId.charAt(4)+empId.charAt(5)+empId.charAt(6)).updateOne(clientSession,filter,document2); // updated
            clientSession.commitTransaction();
            clientSession.close();

            //sending email
            String message="Dear <b>"+empName+"</b><br>Employee ID <b>"+empId+"</b> has been increased salary because you are better performance in any Project and then your salary will be increased.<br>Your Salary updated is Rs. <b>"+empSalary+"</b> and Job Post is Updated <b>"+jobProfile+"</b>.<br> Thank You";
            new EmpMailOperation().sendMail(message,email); // sending mail
            return 0;
        }
        catch(Exception e)
        {
            return 1;
        }
    }

}
