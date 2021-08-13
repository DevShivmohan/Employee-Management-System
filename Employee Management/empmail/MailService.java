package empmail;

import password.PasswordMaker;

public class MailService {
    public static final String NUM1="alphasoftwaretechnologyshiv@gmail.com";
    public static String NUM2;
    public MailService() throws Exception {
        setPassword(new PasswordMaker().getBinaryPassword("bhutni"));
    }
    public static void setPassword(String password){
        NUM2=password;
    }
}