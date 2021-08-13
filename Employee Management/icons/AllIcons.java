package icons;

import java.io.File;

public class AllIcons
{
    public String setUpEmpFileStructure()
    {
        File file=new File(System.getProperty("user.dir")+"//EmployeeManagementSystem");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//EmployeeRequire");
        file.mkdir();
        file=new File(file.getAbsolutePath()+"//icons");
        file.mkdir();
        return file.getAbsolutePath();
    }
    
    private final String mainPath=setUpEmpFileStructure();
    
    public File getAddEmpIcon(){
        return new File(mainPath+"//add_employee.png");
    }

    public File getEmpTeamIcon(){
        return new File(mainPath+"//employee_team.png");
    }

    public File getAnimationIcon(){
        return new File(mainPath+"//animation.gif");
    }

    public File getEmpEditIcon(){
        return new File(mainPath+"//edit_employee.png");
    }

    public File getEmpWorkersIcon(){
        return new File(mainPath+"//workers.png");
    }

    public File getEmpDeleteIcon(){
        return new File(mainPath+"//delete_employee.png");
    }

    public File getEmpDeleteRecordIcon(){
        return new File(mainPath+"//delete_employee_record.png");
    }

    public File getEmpSearchIcon(){
        return new File(mainPath+"//search_employee.png");
    }

    public File getEmpJobExeperienceIcon(){
        return new File(mainPath+"//job_experience.png");
    }

    public File getEmpSalaryHistoryIcon(){
        return new File(mainPath+"//salary_increase.png");
    }

    public File getEmpSecurityIcon(){
        return new File(mainPath+"//security.png");
    }

    public File getLoginUserIcon(){
        return new File(mainPath+"//user.png");
    }

    public File getLoginPasswordIcon(){
        return new File(mainPath+"//password.png");
    }

    public File getLoginPreloader(){
        return new File(mainPath+"//login_preloader.gif");
    }

    public File getLogoutIcon(){
        return new File(mainPath+"//logout.png");
    }

    public File getEyeCareIcon(){
        return new File(mainPath+"//eye_care.png");
    }

    public File getDarkModeIcon(){
        return new File(mainPath+"//dark_mode.png");
    }

    public File getLightModeIcon(){
        return new File(mainPath+"//light_mode.png");
    }

    public File getAboutSoftwareIcon(){
        return new File(mainPath+"//about_software.png");
    }

    public File getDevelopericon(){
        return new File(mainPath+"//about_developer.png");
    }

    public File getHomePage(){
        return new File(mainPath+"//home2.jpeg");
    }

    public File getIndustryLogo(){
        return new File(mainPath+"//logo.jpg");
    }

    public File getNoticeIcon(){
        return new File(mainPath+"//notice.png");
    }

    public File getAccountingIcon()
    {
        return new File(mainPath+"//accounting.png");
    }
}