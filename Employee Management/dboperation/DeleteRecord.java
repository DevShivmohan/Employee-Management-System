package dboperation;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoIterable;

import org.bson.Document;

import empmail.EmpMailOperation;

public class DeleteRecord extends DBConnection
{
    private MongoClient mongoClient;

    public DeleteRecord()
    {
        mongoClient=getConnection();
    }

    public boolean deleteRecord(String id,String recipient,String empName)
    {
        try
        {
            ClientSession clientSession=mongoClient.startSession();
            clientSession.startTransaction();

            BasicDBObject filterB=new BasicDBObject("id",id);
            Document document=new Document("id",id);

            // deleted document from main emp+year
            MongoIterable<String> tables=mongoClient.getDatabase("emprecord").listCollectionNames();
            for (String table : tables)
            {
                DBCursor cursor=mongoClient.getDB("emprecord").getCollection(table).find(filterB);
                while(cursor.hasNext())
                {
                    cursor.next();
                    mongoClient.getDatabase("emprecord").getCollection(table).deleteOne(clientSession,document);
                }
            }

            // delete basic info
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empbasicinfo",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(filterB);
                    if(cursor.hasNext())
                    {
                        mongoClient.getDatabase("empinfo").getCollection(string).deleteOne(clientSession,document);
                        break;
                    }
                }
            }

            // delete contact info
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empcontact",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(filterB);
                    if(cursor.hasNext())
                    {
                        mongoClient.getDatabase("empinfo").getCollection(string).deleteOne(clientSession,document);
                        break;
                    }
                }
            }

            // delete address info
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empaddress",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(filterB);
                    if(cursor.hasNext())
                    {
                        mongoClient.getDatabase("empinfo").getCollection(string).deleteOne(clientSession,document);
                        break;
                    }
                }
            }

            // delete bank info
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empbank",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(filterB);
                    if(cursor.hasNext())
                    {
                        mongoClient.getDatabase("empinfo").getCollection(string).deleteOne(clientSession,document);
                        break;
                    }
                }
            }

            // delete jobprofile info
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empjobprofile",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(filterB);
                    if(cursor.hasNext())
                    {
                        mongoClient.getDatabase("empinfo").getCollection(string).deleteOne(clientSession,document);
                        break;
                    }
                }
            }

            // delete bio info
            tables=mongoClient.getDatabase("empinfo").listCollectionNames();
            for (String string : tables)
            {
                if(string.indexOf("empfile",0)!=-1)
                {
                    DBCursor cursor=mongoClient.getDB("empinfo").getCollection(string).find(filterB);
                    if(cursor.hasNext())
                    {
                        mongoClient.getDatabase("empinfo").getCollection(string).deleteOne(clientSession,document);
                        break;
                    }
                }
            }

            clientSession.commitTransaction(); // all record deleted successfully
            clientSession.close();
            String message="Dear <b>"+empName+"</b><br> Employee id <b>"+id+"</b> record will be deleted successfully from our database. <br> You are better luck for next time in an Industry.<br> Thank You";
            new EmpMailOperation().sendMail(message,recipient);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
