package dboperation;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;

public class AttendanceData extends DBConnection {
    private MongoClient mongoClient;
    public AttendanceData(){
        this.mongoClient=getConnection();
    }

    public void fetchDBs(){
        try{
            MongoIterable<String> dbs=mongoClient.listDatabaseNames();
            for (String string : dbs) {
                System.out.println(string);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
