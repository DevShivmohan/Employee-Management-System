package security;

public class SRandom
{
    public String getRandom()
    {
        String alphaString="qwertyuiopasdfghjklzxcvbnm";
        alphaString=alphaString+alphaString.toUpperCase();
        alphaString=alphaString+"1234567890"+"~!@#$%^&*()_+";
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<100;i++)
        {
            int index=(int)(alphaString.length() * Math.random());
            stringBuilder.append(alphaString.charAt(index));
        }
        return stringBuilder.toString();
    }

    public String getOTP()
    {
        String alphaString="0123456789";
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<8;i++)
        {
            int index=(int)(alphaString.length() * Math.random());
            stringBuilder.append(alphaString.charAt(index));
        }
        return stringBuilder.toString();
    }

    public String getInitKey(){
        String alphaString="qwertyuiopasdfghjklzxcvbnmshivsta";
        alphaString=alphaString+alphaString.toUpperCase();
        alphaString=alphaString+"1234567890"+"~!@#$%^&*()_+";
        return alphaString;
    }

    public String getInitAdminKey(){
        String alphaString="qwertyuiopasdfghjklzxcvbnmhfdgfh4376473643746372122254545SHIVSTA####";
        alphaString=alphaString+alphaString.toUpperCase();
        alphaString=alphaString+"1234567890gfjghfjghfjhg1010101101010110"+"~!@#$%^&*()_+";
        return alphaString;
    }
}