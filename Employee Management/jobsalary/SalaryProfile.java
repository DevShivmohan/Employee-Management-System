package jobsalary;

public class SalaryProfile
{
    private final long SOFTWARE_DEV=16000;
    private final long WEB_DEV=14000;
    private final long PHP_DEV=14500;
    private final long ANDROID_DEV=15000;
    private final long SOFTWARE_TESTER=12000;
    private final long FLOWCHART_DESIGNER=13000;
    private final long PEON=10000;

    public long getSoftwareDevSalary(){
        return SOFTWARE_DEV;
    }

    public long getWebDevSalary(){
        return WEB_DEV;
    }

    public long getPhpDevSalary(){
        return PHP_DEV;
    }

    public long getAndroidDevSalary(){
        return ANDROID_DEV;
    }

    public long getSoftwareTesterSalary(){
        return SOFTWARE_TESTER;
    }

    public long getFlowchartDesignerSalary(){
        return FLOWCHART_DESIGNER;
    }

    public long getPeonSalary(){
        return PEON;
    }
}
