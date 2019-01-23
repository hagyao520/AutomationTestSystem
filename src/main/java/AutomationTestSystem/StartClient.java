package AutomationTestSystem;

import AutomationTestSystem.View.LoginPageView;

public class StartClient {
    	public static void main(String[] args) {
     	       try {
     			      LoginPageView.main(args);
     		   } catch (Exception e) {
     			      e.printStackTrace();
     		   }
     	}
}
