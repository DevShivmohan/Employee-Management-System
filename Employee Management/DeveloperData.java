import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import security.Encryption;

public class DeveloperData
{
  private ImageIcon[] photoIcons=new ImageIcon[8];
  private String[] intros=new String[8];
  public DeveloperData()
  {
    try
    {
        String[] bioKeys={"5muhiimg","alokmaniimg","ayushimg","mhaimg","prinkaimg","shivimg","shivaniimg","vipulimg"};
        String[] introKeys={"5muhiintro","alokmaniintro","ayushintro","mhaintro","prinkaintro","shivintro","shivaniintro","vipulintro"};
        String path=System.getProperty("user.dir")+"//EmployeeManagementSystem//EmployeeRequire//icons//";
        FileInputStream fileInputStream=new FileInputStream(path+"DeveloperBioInfo.binary");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        HashMap<String,ImageIcon> bioMap=(HashMap<String,ImageIcon>)objectInputStream.readObject();
        fileInputStream=new FileInputStream(path+"DeveloperDataInfo.binary");
        objectInputStream=new ObjectInputStream(fileInputStream);
        HashMap<String,String> introMap=(HashMap<String,String>)objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        for(Map.Entry<String,ImageIcon> ele:bioMap.entrySet())
        {
            if(bioKeys[0].equals(ele.getKey()))
            photoIcons[0]=ele.getValue();

            if(bioKeys[1].equals(ele.getKey()))
            photoIcons[1]=ele.getValue();

            if(bioKeys[2].equals(ele.getKey()))
            photoIcons[2]=ele.getValue();

            if(bioKeys[3].equals(ele.getKey()))
            photoIcons[3]=ele.getValue();

            if(bioKeys[4].equals(ele.getKey()))
            photoIcons[4]=ele.getValue();

            if(bioKeys[5].equals(ele.getKey()))
            photoIcons[5]=ele.getValue();

            if(bioKeys[6].equals(ele.getKey()))
            photoIcons[6]=ele.getValue();

            if(bioKeys[7].equals(ele.getKey()))
            photoIcons[7]=ele.getValue();
        }
        for(Map.Entry<String,String> ele:introMap.entrySet())
        {
            Encryption encryption=new Encryption();
            if(introKeys[0].equals(ele.getKey()))
            intros[0]=encryption.getDeveloperDecryptedData(ele.getValue());

            if(introKeys[1].equals(ele.getKey()))
            intros[1]=encryption.getDeveloperDecryptedData(ele.getValue());

            if(introKeys[2].equals(ele.getKey()))
            intros[2]=encryption.getDeveloperDecryptedData(ele.getValue());

            if(introKeys[3].equals(ele.getKey()))
            intros[3]=encryption.getDeveloperDecryptedData(ele.getValue());

            if(introKeys[4].equals(ele.getKey()))
            intros[4]=encryption.getDeveloperDecryptedData(ele.getValue());

            if(introKeys[5].equals(ele.getKey()))
            intros[5]=encryption.getDeveloperDecryptedData(ele.getValue());

            if(introKeys[6].equals(ele.getKey()))
            intros[6]=encryption.getDeveloperDecryptedData(ele.getValue());

            if(introKeys[7].equals(ele.getKey()))
            intros[7]=encryption.getDeveloperDecryptedData(ele.getValue());
        }
        introMap.clear();
        bioMap.clear();
    }
    catch(Exception e)
    {
        new LoginSession().destroySession();
    }
  }
  

  public String getSunitaIntro(){
    return intros[0];  
  }

  public ImageIcon getSunitaImage(){
    return photoIcons[0];
  }

  
  public String getAlokIntro(){
    return intros[1];  
  }

  public ImageIcon getAlokImage(){
    return photoIcons[1];
  }

  
  public String getAyushIntro(){
    return intros[2];  
  }

  public ImageIcon getAyushImage(){
    return photoIcons[2];
  }

  
  public String getMahimaIntro(){
    return intros[3];  
  }

  public ImageIcon getMahimaImage(){
    return photoIcons[3];
  }

  
  public String getPrinkaIntro(){
    return intros[4];  
  }

  public ImageIcon getPrinkaImage(){
    return photoIcons[4];
  }

  
  public String getShivIntro(){
    return intros[5];  
  }

  public ImageIcon getShivImage(){
    return photoIcons[5];
  }

  
  public String getShivaniIntro(){
    return intros[6];  
  }

  public ImageIcon getShivaniImage(){
    return photoIcons[6];
  }

  
  public String getVipulIntro(){
    return intros[7];  
  }

  public ImageIcon getVipulImage(){
    return photoIcons[7];
  }
  
}
