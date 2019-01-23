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
		private String dhostAddress = "rm-wz99301zn03n90h47v.mysql.rds.aliyuncs.com";
		private String duserName = "jqbdaev";
		private String dpassWord = "JQBdaev1qaz";
		private String dport = "3306";
		private String ddataBase = "travele_test";

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
