package AutomationTestSystem.Config;

public class SystemDatabaseConfiguration {

	    public static final String path = "src/main/resources/data/SystemDatabaseConfigurationData.xml";
//		private String djdbcDriver;
//		private String dhostAddress;
//		private String duserName;
//		private String dpassWord;
//		private String dport;
//		private String ddataBase;

		private String djdbcDriver = "com.mysql.jdbc.Driver";
		private String dhostAddress = "10.22.83.70";
		private String duserName = "adminall";
		private String dpassWord = "admin@300348";
		private String dport = "3323";
		private String ddataBase = "YHT_USER_SIT";
        
		public String getDjdbcDriver() {
			return djdbcDriver;
		}

		public void setDjdbcDriver(String djdbcDriver) {
			this.djdbcDriver = djdbcDriver;
		}

		public String getDhostAddress() {
			return dhostAddress;
		}

		public void setDhostAddress(String dhostAddress) {
			this.dhostAddress = dhostAddress;
		}

		public String getDuserName() {
			return duserName;
		}

		public void setDuserName(String duserName) {
			this.duserName = duserName;
		}

		public String getDpassWord() {
			return dpassWord;
		}

		public void setDpassWord(String dpassWord) {
			this.dpassWord = dpassWord;
		}

		public String getDport() {
			return dport;
		}

		public void setDport(String dport) {
			this.dport = dport;
		}

		public String getDdataBase() {
			return ddataBase;
		}

		public void setDdataBase(String ddataBase) {
			this.ddataBase = ddataBase;
		}
}
