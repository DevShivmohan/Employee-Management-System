package dboperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import empmail.EmpMailOperation;
import icons.PrepareReciept;
import security.Encryption;

public class AccountingData extends DBConnection {
    private MongoClient mongoClient;
    private byte[] SSL;
    private String id;
    private Encryption encryption=new Encryption();
    public AccountingData(){
        this.mongoClient=getConnection();
    }

    public int getValidEmpIdResponse(String id){
        try{
            String year=""+id.charAt(3)+id.charAt(4)+id.charAt(5)+id.charAt(6);
            DBCursor cursor=mongoClient.getDB("emprecord").getCollection("emp"+year).find(new BasicDBObject("id",id));
            if(cursor.hasNext()){
                cursor.next();
                SSL=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                this.id=id;
                return 0;
            }
            else
            return 1;
        }
        catch(Exception e){
            return 2;
        }
    }

    public String getEmployeeSalary(){
        try{
            String year=""+id.charAt(3)+id.charAt(4)+id.charAt(5)+id.charAt(6);
            DBCursor cursor=mongoClient.getDB("empinfo").getCollection("empjobprofile"+year).find(new BasicDBObject("id",id));
            if(cursor.hasNext()){
                cursor.next();
                long ammount=(long)cursor.curr().get("salary");
                return ""+ammount;
            }
            else
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int setAccountingTable(DefaultTableModel defaultTableModel){
        try{
            String[] header={"Employee ID","Reciept No.","Ammount","Date","Time"};
            defaultTableModel.setColumnIdentifiers(header);
            DBCursor cursor=mongoClient.getDB("empaccounting").getCollection("accounting"+id).find();
            while(cursor.hasNext()){
                cursor.next();
                Vector<Object> data=new Vector<>();
                data.add((String)cursor.curr().get("id"));
                data.add((String)cursor.curr().get("receipt_id"));
                data.add(new String(encryption.getDecryptedData((byte[])cursor.curr().get("ammount"),SSL)));
                data.add(new String(encryption.getDecryptedData((byte[])cursor.curr().get("date"),SSL)));
                data.add(new String(encryption.getDecryptedData((byte[])cursor.curr().get("time"),SSL)));
                defaultTableModel.addRow(data);
            }
            return 0;
        }catch(Exception e){
            return 2;
        }
    }

    public int deleteAccountingRecord(String id,String receiptId){
        try{
            this.id=id;
            DBCursor cursor=mongoClient.getDB("empaccounting").getCollection("accounting"+id).find(new BasicDBObject("receipt_id",receiptId));
            if(cursor.hasNext()){
                cursor.next();
                mongoClient.getDB("empaccounting").getCollection("accounting"+id).remove(new BasicDBObject("receipt_id",receiptId));
                String message="Dear Employee<br>Employee ID <b>"+id+"</b> Deleted a record from Accounting History which Receipt Id <b>"+receiptId+"</b><br>Thank You";
                sendMail(message);
                return 0;
            }
            else
            return 1;
        }catch(Exception e){
            return 2;
        }
    }
    private String getReceiptId(){
        try
        {
            String filterId=null;
            DBCursor cursor=mongoClient.getDB("empaccounting").getCollection("accounting"+id).find();
            while(cursor.hasNext()){
                cursor.next();
                filterId=(String)cursor.curr().get("receipt_id");
            }
            if(filterId==null)
            return genReceiptId();
            else
            {
                long fId=Long.parseLong(filterId.substring(12,filterId.length()))+1;
                filterId=filterId.substring(0,12)+fId;
                return filterId;
            }
        }catch(Exception e){
            return null;
        }
    }

    private String genReceiptId(){
        String filterId=""+id.substring(3,id.length())+"10000000";
        return filterId;
    }

    public int trfAmmount(String id,String ammount){
        try
        {
            getValidEmpIdResponse(id);
            String date=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String time=new SimpleDateFormat("HH:mm:ss").format(new Date());
            this.id=id;
            try{mongoClient.getDatabase("empaccounting").createCollection("accounting"+id);}catch(Exception e){}
            BasicDBObject data=new BasicDBObject();
            String receiptId=getReceiptId();
            data.put("id",id);
            data.put("receipt_id",receiptId);
            data.put("ammount",encryption.getEncryptedData(ammount.getBytes(),SSL));
            data.put("date",encryption.getEncryptedData(date.getBytes(),SSL));
            data.put("time",encryption.getEncryptedData(time.getBytes(),SSL));
            mongoClient.getDB("empaccounting").getCollection("accounting"+id).insert(data);
            String message="Dear Employee<br>Your Employee ID <b>"+id+"</b> has been Transferred Ammount Rs. <b>"+ammount+"</b> to your registered Account.<br>Receipt No. <b>"+receiptId+"</b> on <b>"+date+"</b> at <b>"+time+"</b><br>Thank you";
            sendMail(message);
            return 0;
        }catch(Exception e){
            return 2;
        }
    }

    private void sendMail(String message){
        try
        {
            String year=""+id.charAt(3)+id.charAt(4)+id.charAt(5)+id.charAt(6);
            DBCursor cursor=mongoClient.getDB("empinfo").getCollection("empcontact"+year).find(new BasicDBObject("id",id));
            if(cursor.hasNext()){
                cursor.next();
                String email=new String(encryption.getDecryptedData((byte[])cursor.curr().get("email"),SSL));
                new EmpMailOperation().sendMail(message,email);
            }
        }catch(Exception e){

        }
    }

    public boolean isPrintAccountingReceipt(String id)
    {
        try
        {
            String[] infos=new String[3];
            byte[] key=null;
            String year=""+id.charAt(3)+id.charAt(4)+id.charAt(5)+id.charAt(6);
            DBCursor accountingCursor=mongoClient.getDB("empaccounting").getCollection("accounting"+id).find();

            // getting decryption key
            DBCursor cursor=mongoClient.getDB("emprecord").getCollection("emp"+year).find(new BasicDBObject("id",id));
            if(cursor.hasNext()){
                cursor.next();
                key=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
            }

            // getting empName and fatherName
            cursor=mongoClient.getDB("empinfo").getCollection("empbasicinfo"+year).find(new BasicDBObject("id",id));
            if(cursor.hasNext()){
                cursor.next();
                infos[0]=new String(encryption.getDecryptedData((byte[])cursor.curr().get("name"), key));
                infos[1]=new String(encryption.getDecryptedData((byte[])cursor.curr().get("father"), key));
            }

            // getting email
            cursor=mongoClient.getDB("empinfo").getCollection("empcontact"+year).find(new BasicDBObject("id",id));
            if(cursor.hasNext()){
                cursor.next();
                infos[2]=new String(encryption.getDecryptedData((byte[])cursor.curr().get("email"), key));
            }

            // creating receipt
            String filePath=new PrepareReciept().createAccountingHistory(accountingCursor, infos, key);
            if(filePath==null)
            return false;
            else
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
