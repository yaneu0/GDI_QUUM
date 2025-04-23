import java.util.HashMap;

public class logincred
{
    HashMap<String,String> logininfo = new HashMap<String,String>();

    logincred() {
        logininfo.put("Admin","123");
    }
    public HashMap getLoginInfo() {
        return logininfo;
    }
}