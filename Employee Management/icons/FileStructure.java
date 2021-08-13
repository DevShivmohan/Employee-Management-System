package icons;

import java.io.File;

public class FileStructure {
    public String getSessionFileStructure()
    {
        File file=new File(System.getProperty("user.home")+"//EmployeeManagementSystem");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//EmployeeRequire");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//Session");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//LoginSession");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//UserAccess");
        file.mkdir();
        return file.getAbsolutePath();
    }

    public String getDownloadsPath(){
        String path=System.getProperty("user.home")+"//Downloads";
        return path;
    }
}