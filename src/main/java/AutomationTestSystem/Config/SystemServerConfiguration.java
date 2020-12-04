package AutomationTestSystem.Config;

public class SystemServerConfiguration {

	    public static final String path = "src/main/resources/data/SystemServerConfigurationData.xml";
//		private String shostAddress;
//		private String sport;
//		private String suserName;
//		private String spassWord;
//		private String skey;
//		private String sapi;

		private String shostAddress = "yhtsit.sunline.cn";
		private String sPort = "38822";
		private String suserName = "yhttest";
		private String spassWord = "yhttest_300348";
		private String skey = "src/main/resources/Keya/id_rsa";
		private String sapi = "yhtsit.sunline.cn";
        
		public String getShostAddress() {
			return shostAddress;
		}

		public void setShostAddress(String shostAddress) {
			this.shostAddress = shostAddress;
		}

		public String getSport() {
			return sPort;
		}

		public void setSport(String sPort) {
			this.sPort = sPort;
		}

		public String getSuserName() {
			return suserName;
		}

		public void setSuserName(String suserName) {
			this.suserName = suserName;
		}

		public String getSpassWord() {
			return spassWord;
		}

		public void setSpassWord(String spassWord) {
			this.spassWord = spassWord;
		}

		public String getSkey() {
			return skey;
		}

		public void setSkey(String skey) {
			this.skey = skey;
		}

		public String getSapi() {
			return sapi;
		}

		public void setSapi(String sapi) {
			this.sapi = sapi;
		}
}
