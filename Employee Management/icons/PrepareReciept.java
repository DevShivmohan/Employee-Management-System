package icons;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;

import dboperation.DBConnection;
import empmail.EmpMailOperation;
import security.Encryption;

public class PrepareReciept extends AllIcons
{
    private String userAdmin;
    private String empId;
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
    private String empPost;
    private final String regStatus="Success";
    private File photoFile;
    private File signFile;

    private void setUserAdmin(String admin){
        userAdmin=admin;
    }

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public void setEmpName(String empName){
        this.empName=empName;
    }

    public void setEmpFather(String empId){
        this.empFather=empId;
    }

    public void setEmpDob(String empId){
        this.empDob=empId;
    }

    public void setEmpGender(String empId){
        this.empGender=empId;
    }

    public void setEmpMobile(String empId){
        this.empMobile=empId;
    }

    public void setEmpAadhaar(String empId){
        this.empAadhaar=empId;
    }

    public void setEmpEmail(String empId){
        this.empEmail=empId;
    }

    public void setEmpAddress(String empId){
        this.empAddress=empId;
    }

    public void setEmpAccount(String empId){
        this.empAccount=empId;
    }

    public void setEmpIfsc(String empId){
        this.empIfsc=empId;
    }

    public void setEmpPost(String empId){
        this.empPost=empId;
    }

    public void setEmpPhotoFile(File empId){
        this.photoFile=empId;
    }

    public void setEmpSignFile(File empId){
        this.signFile=empId;
    }

    private void setHeader(Document document)
    {
        try
        {
            // header

            Font headerFont=new Font(Font.FontFamily.TIMES_ROMAN,25f,Font.UNDERLINE,BaseColor.BLUE);
            Paragraph paragraph=new Paragraph("ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED",headerFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

    
            // space
            document.add(new Paragraph("\n"));

            

            PdfPTable table=new PdfPTable(2);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);

            // barcode
            BarcodeQRCode barcodeQRCode=new BarcodeQRCode("ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED - #SHIVMOHAN",1,1,null);
            Image qrImage=barcodeQRCode.getImage();
            qrImage.setAlignment(Element.ALIGN_LEFT);
            qrImage.scaleAbsolute(100f,100f);
            // cell1.add
    
            // logo
            String logoPath=getIndustryLogo().getAbsolutePath();
            Image logoImage=Image.getInstance(logoPath);
            logoImage.scaleAbsolute(100f,100f);
            logoImage.setAlignment(Element.ALIGN_RIGHT);

            
            PdfPCell cell1=new PdfPCell(qrImage);
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_BASELINE);
            PdfPCell cell2=new PdfPCell(logoImage);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell2.setVerticalAlignment(Element.ALIGN_BASELINE);

            table.addCell(cell1);
            table.addCell(cell2);

            document.add(table);

        }catch(Exception e){}
    }

    public boolean prepareNotice(String subject,String content)
    {
        try
        {
            FileStructure fileStructure=new FileStructure();
            File path=new File(fileStructure.getSessionFileStructure()+"//Notice.pdf");
            FileOutputStream fileOutputStream=new FileOutputStream(path);
            Document document=new Document(new Rectangle(PageSize.A4));
            PdfWriter.getInstance(document,fileOutputStream);
            document.open();

            // notice header
            Font font=new Font(Font.FontFamily.TIMES_ROMAN,28f,Font.UNDERLINE,BaseColor.MAGENTA);
            Paragraph acknowledgementParagraph=new Paragraph("ORGANIZATION NOTICE",font);
            acknowledgementParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(acknowledgementParagraph);

            // setting header
            setHeader(document);
            document.add(new Paragraph("\n"));

            //subject
            font=new Font(Font.FontFamily.TIMES_ROMAN,20f,Font.UNDERLINE,BaseColor.BLACK);
            Paragraph subjectParagraph=new Paragraph(subject,font);
            subjectParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(subjectParagraph);
            document.add(new Paragraph("\n"));

            // respectation
            font=new Font(Font.FontFamily.TIMES_ROMAN,20f,Font.NORMAL,BaseColor.BLACK);
            Paragraph dear=new Paragraph("Dear Organization members",font);
            dear.setAlignment(Element.ALIGN_LEFT);
            document.add(dear);

            // content
            Paragraph contentParagraph=new Paragraph(content,font);
            contentParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(contentParagraph);

            // date
            document.add(new Paragraph("\n\n\n\n\n\n"));
            String date=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            contentParagraph=new Paragraph("Date : "+date,font);
            contentParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(contentParagraph);

            // by
            contentParagraph=new Paragraph("By Our Organization",font);
            contentParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(contentParagraph);

            document.close();

            // getting all employee email
            String[] recipients=new GettingEmails().getEmails();
            EmpMailOperation mailOperation=new EmpMailOperation();
            if(recipients.length>2)
            return mailOperation.sendNotice(path, recipients,userAdmin);
            else
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private class GettingEmails extends DBConnection
    {
        public String[] getEmails()
        {
            try
            {
                setUserAdmin(getUserName()); // setting logged username
                Encryption encryption=new Encryption();
                MongoClient mongoClient=getConnection();
                long countDocuments=0;
                MongoIterable<String> collections= mongoClient.getDatabase("emprecord").listCollectionNames();
                for (String collection : collections)
                {
                    countDocuments=countDocuments+mongoClient.getDatabase("emprecord").getCollection(collection).countDocuments();
                }

                // creating emails array
                String emails[]=new String[(int)countDocuments];
                int index=0;

                collections= mongoClient.getDatabase("emprecord").listCollectionNames();
                for (String collection : collections)
                {
                    DBCursor cursor=mongoClient.getDB("emprecord").getCollection(collection).find();
                    while(cursor.hasNext())
                    {
                        cursor.next();
                        byte[] key=encryption.getDecryptedSSL((byte[])cursor.curr().get("SSL"));
                        String id=(String)cursor.curr().get("id");

                        // traversing email table
                        String year=""+id.charAt(3)+id.charAt(4)+id.charAt(5)+id.charAt(6);
                        DBCursor cursor1=mongoClient.getDB("empinfo").getCollection("empcontact"+year).find(new BasicDBObject("id",id));
                        if(cursor1.hasNext())
                        {
                            cursor1.next();
                            emails[index]=new String(encryption.getDecryptedData((byte[])cursor1.curr().get("email"), key));
                            index++;
                        }
                    }
                }
                return emails;
            }
            catch(Exception e)
            {
                String[] str={"none"};
                return str;
            }
        }
    }

    public void createEmployeeRegistrationAcknowledgement()
    {
        try
        {
            // file creation
            String outputPath=new FileStructure().getDownloadsPath()+"//"+empId+"Acknowledgement.pdf";
            File file=new File(outputPath);
            outputPath=file.getAbsolutePath();
            Document document=new Document(new Rectangle(PageSize.A4));
            FileOutputStream fileOutputStream=new FileOutputStream(outputPath);
            PdfWriter.getInstance(document,fileOutputStream);
            document.open();
            document.setMarginMirroring(true);

            // seting header of pdf
            setHeader(document); 

            // acknowledgement slip
            Font font=new Font(Font.FontFamily.TIMES_ROMAN,20f,Font.UNDERLINE,BaseColor.BLACK);
            Paragraph acknowledgementParagraph=new Paragraph("Acknowledgement Slip",font);
            acknowledgementParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(acknowledgementParagraph);
            document.add(new Paragraph("\n"));

            // adding data member
            PdfPTable table=new PdfPTable(2);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            // id
            Font font1=new Font(Font.FontFamily.TIMES_ROMAN,18f,Font.BOLD,BaseColor.BLACK);
            Font font2=new Font(Font.FontFamily.TIMES_ROMAN,18f,Font.NORMAL,BaseColor.BLUE);
            Paragraph h1=new Paragraph("Employee ID",font1);
            Paragraph h2=new Paragraph(empId,font2);
            PdfPCell cell1=new PdfPCell(h1);
            PdfPCell cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // name
            h1=new Paragraph("Name",font1);
            h2=new Paragraph(empName,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // father
            h1=new Paragraph("Father Name",font1);
            h2=new Paragraph(empFather,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // dob
            String dob=""+empDob.charAt(0)+empDob.charAt(1)+"/"+empDob.charAt(2)+empDob.charAt(3)+"/"+empDob.charAt(4)+empDob.charAt(5)+empDob.charAt(6)+empDob.charAt(7);
            h1=new Paragraph("Date of birth",font1);
            h2=new Paragraph(dob,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // gender
            h1=new Paragraph("Gender",font1);
            h2=new Paragraph(empGender,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // Mobile
            String mobile="XXXXXXXXX"+empMobile.charAt(8)+empMobile.charAt(9);
            h1=new Paragraph("Mobile",font1);
            h2=new Paragraph(mobile,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // aadhaar
            String aadhaar=""+empAadhaar.charAt(0)+empAadhaar.charAt(1)+"XXXXXXXX"+empAadhaar.charAt(10)+empAadhaar.charAt(11);
            h1=new Paragraph("Aadhaar",font1);
            h2=new Paragraph(aadhaar,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // email
            h1=new Paragraph("Email",font1);
            h2=new Paragraph(empEmail,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // address
            h1=new Paragraph("Address",font1);
            h2=new Paragraph(empAddress,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // account
            int length=empAccount.length();
            String account="XXXXXXXXXX"+empAccount.charAt(length-4)+empAccount.charAt(length-3)+empAccount.charAt(length-2)+empAccount.charAt(length-1);
            h1=new Paragraph("Account number",font1);
            h2=new Paragraph(account,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // ifsc
            h1=new Paragraph("IFSC code",font1);
            h2=new Paragraph(empIfsc,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // profile
            h1=new Paragraph("Job Role",font1);
            h2=new Paragraph(empPost,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // status
            h1=new Paragraph("Registration status",font1);
            h2=new Paragraph(regStatus,font2);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(h2);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // photo
            h1=new Paragraph("Photo",font1);
            Image image=Image.getInstance(photoFile.getAbsolutePath());
            image.scaleAbsolute(150f,150f);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(image);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // signature
            h1=new Paragraph("Signature",font1);
            image=Image.getInstance(signFile.getAbsolutePath());
            image.scaleAbsolute(150f,150f);
            cell1=new PdfPCell(h1);
            cell2=new PdfPCell(image);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);

            // adding table
            document.add(table);

            // space
            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n"));

            // authorised signature
            font=new Font(Font.FontFamily.HELVETICA,15f,Font.BOLD,BaseColor.BLACK);
            Paragraph paragraph=new Paragraph("Authorised Signature..............",font);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraph);

            document.close();
            
            // sending file
            new EmpMailOperation().sendAcknowledgementMail(new File(outputPath),empEmail,empId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }   

    public String createAccountingHistory(DBCursor accountingCursor,String[] infos,byte[] key){
        try
        {
            Encryption encryption=new Encryption();

            DBCursor cursor2=accountingCursor;
            if(cursor2.hasNext()){
                cursor2.next();
                empId=(String)cursor2.curr().get("id");
            }

            // file creation
            String outputPath=new FileStructure().getDownloadsPath()+"//AccountingHistory"+empId+".pdf";
            File file=new File(outputPath);
            outputPath=file.getAbsolutePath();
            Document document=new Document(new Rectangle(PageSize.A4));
            FileOutputStream fileOutputStream=new FileOutputStream(outputPath);
            PdfWriter.getInstance(document,fileOutputStream);
            document.open();
            document.setMarginMirroring(true);

            // seting header of pdf
            setHeader(document); 

            // acknowledgement slip
            Font font=new Font(Font.FontFamily.TIMES_ROMAN,22f,Font.UNDERLINE,BaseColor.BLACK);
            Paragraph acknowledgementParagraph=new Paragraph("Accounting History",font);
            acknowledgementParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(acknowledgementParagraph);
            document.add(new Paragraph("\n"));

            // name and father name
            font=new Font(Font.FontFamily.TIMES_ROMAN,20f,Font.NORMAL,BaseColor.MAGENTA);
            acknowledgementParagraph=new Paragraph(infos[0]+" S/O "+infos[1],font);
            acknowledgementParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(acknowledgementParagraph);

            // emp id
            font=new Font(Font.FontFamily.TIMES_ROMAN,20f,Font.NORMAL,BaseColor.MAGENTA);
            acknowledgementParagraph=new Paragraph("Employee ID : "+empId,font);
            acknowledgementParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(acknowledgementParagraph);

            // email
            font=new Font(Font.FontFamily.TIMES_ROMAN,20f,Font.NORMAL,BaseColor.MAGENTA);
            acknowledgementParagraph=new Paragraph("Email : "+infos[2],font);
            acknowledgementParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(acknowledgementParagraph);

            // current date
            String date=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            font=new Font(Font.FontFamily.TIMES_ROMAN,20f,Font.NORMAL,BaseColor.BLACK);
            acknowledgementParagraph=new Paragraph("Date : "+date,font);
            acknowledgementParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(acknowledgementParagraph);
            document.add(new Paragraph("\n\n"));


            // adding data member
            PdfPTable table=new PdfPTable(5);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);

            // table header
            Font font1=new Font(Font.FontFamily.TIMES_ROMAN,12f,Font.BOLD,BaseColor.BLACK);
            Paragraph h1=new Paragraph("Employee Id",font1);
            Paragraph h2=new Paragraph("Receipt Id",font1);
            Paragraph h3=new Paragraph("Ammount",font1);
            Paragraph h4=new Paragraph("Date",font1);
            Paragraph h5=new Paragraph("Time",font1);
            PdfPCell cell1=new PdfPCell(h1);
            PdfPCell cell2=new PdfPCell(h2);
            PdfPCell cell3=new PdfPCell(h3);
            PdfPCell cell4=new PdfPCell(h4);
            PdfPCell cell5=new PdfPCell(h5);
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell3.setBackgroundColor(BaseColor.GRAY);
            cell4.setBackgroundColor(BaseColor.GRAY);
            cell5.setBackgroundColor(BaseColor.GRAY);
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_CENTER);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);


            // adding data
            while(accountingCursor.hasNext())
            {
                accountingCursor.next();
                String id=(String)accountingCursor.curr().get("id");
                String receiptId=(String)accountingCursor.curr().get("receipt_id");
                String ammount=new String(encryption.getDecryptedData((byte[])accountingCursor.curr().get("ammount"), key));
                String dateC=new String(encryption.getDecryptedData((byte[])accountingCursor.curr().get("date"), key));
                String time=new String(encryption.getDecryptedData((byte[])accountingCursor.curr().get("time"), key));
           
                font1=new Font(Font.FontFamily.TIMES_ROMAN,11f,Font.NORMAL,BaseColor.BLACK);
                h1=new Paragraph(id,font1);
                h2=new Paragraph(receiptId,font1);
                h3=new Paragraph(ammount,font1);
                h4=new Paragraph(dateC,font1);
                h5=new Paragraph(time,font1);
                cell1=new PdfPCell(h1);
                cell2=new PdfPCell(h2);
                cell3=new PdfPCell(h3);
                cell4=new PdfPCell(h4);
                cell5=new PdfPCell(h5);
                cell1.setBackgroundColor(BaseColor.GRAY);
                cell2.setBackgroundColor(BaseColor.GRAY);
                cell3.setBackgroundColor(BaseColor.GRAY);
                cell4.setBackgroundColor(BaseColor.GRAY);
                cell5.setBackgroundColor(BaseColor.GRAY);
                cell1.setVerticalAlignment(Element.ALIGN_CENTER);
                cell2.setVerticalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_CENTER);
                cell4.setVerticalAlignment(Element.ALIGN_CENTER);
                cell5.setVerticalAlignment(Element.ALIGN_CENTER);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
            }

            document.add(table);

            document.close(); // finished
            
            // sending file
            new EmpMailOperation().sendAcknowledgementMail(new File(outputPath),infos[2], empId);
            
            return outputPath;
        }
        catch(Exception e){
            return null;
        }
    }
}