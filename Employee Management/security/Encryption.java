package security;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption extends SRandom
{
    public byte[] getEncryptedData(byte[] data,byte[] key)
    {
        try
        {
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            return cipher.doFinal(data);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public byte[] getDecryptedData(byte[] data,byte[] key)
    {
        try
        {
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            return cipher.doFinal(data);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public byte[] getEncryptedSSL(byte[] data)
    {
        try
        {
            byte[] key=new SRandom().getInitKey().getBytes();
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            return cipher.doFinal(data);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public byte[] getDecryptedSSL(byte[] data)
    {
        try
        {
            byte[] key=new SRandom().getInitKey().getBytes();
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            return cipher.doFinal(data);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public byte[] getAdminEncryptedData(String data)
    {
        try
        {
            byte[] key=new SRandom().getInitAdminKey().getBytes();
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            return cipher.doFinal(data.getBytes());
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public String getAdminDecryptedData(byte[] data)
    {
        try
        {
            byte[] key=new SRandom().getInitAdminKey().getBytes();
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            return new String(cipher.doFinal(data));
        }
        catch(Exception e)
        {
            return null;
        }
    }


    public String getDeveloperEncryptedData(String data)
    {
        try
        {
            byte[] key=new SRandom().getInitAdminKey().getBytes();
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            return new String(cipher.doFinal(data.getBytes()),"ISO-8859-1");
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public String getDeveloperDecryptedData(String data)
    {
        try
        {
            byte[] key=new SRandom().getInitAdminKey().getBytes();
            Cipher cipher=Cipher.getInstance("AES");
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            key=messageDigest.digest(key);
            key=Arrays.copyOf(key,16);
            SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            return new String(cipher.doFinal(data.getBytes("ISO-8859-1")));
        }
        catch(Exception e)
        {
            return null;
        }
    }

}
