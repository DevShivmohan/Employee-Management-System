package dboperation;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import security.Encryption;

public class RemoveAdmin extends DBConnection
{
    private MongoClient mongoClient;

    public RemoveAdmin(){
        this.mongoClient=getConnection();
    }

    public int deleteAdminUser(String userName)
    {
        try
        {
            Encryption encryption=new Encryption();
            DBCursor cursor=mongoClient.getDB("empadmin").getCollection("empusers").find();
            while(cursor.hasNext())
            {
                cursor.next();
                if(userName.equalsIgnoreCase(encryption.getAdminDecryptedData((byte[])cursor.curr().get("username"))))
                {
                    mongoClient.getDB("empadmin").getCollection("empusers").remove(new BasicDBObject("_id",cursor.curr().get("_id")));
                    return 0;
                }
            }
            return 1;
        }catch(Exception e){
            return 2;
        }
    }
}
