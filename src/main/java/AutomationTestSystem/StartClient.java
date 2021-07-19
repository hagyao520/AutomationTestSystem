package AutomationTestSystem;

import AutomationTestSystem.View.LoginPageView;

public class StartClient {
    public static boolean state = true;
    public static String Environment = "SIT";
    
//    public static boolean state = false;
//    public static String Environment = "UAT";
    
	public static void main(String[] args) {
		try {
			LoginPageView.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
