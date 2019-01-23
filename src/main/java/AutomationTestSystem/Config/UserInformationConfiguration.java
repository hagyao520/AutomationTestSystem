package AutomationTestSystem.Config;

public class UserInformationConfiguration {

	    public static final String path = "src/main/resources/data/UserInformationConfigurationData.xml";
		private String headPortrait = "src/main/resources/image/LoginPane/HeadPortraitImage/HeadPortrait1.gif";
		private String nickName = "rm-wz9930zn03n90h47v.mysql.rds.aliyuncs.com";
		private String userName = "jqbdev";
		private String dpassWord = "JQBdev1qaz";
		private String dport = "3306";
		private String ddataBase = "travel_test";

		public String getHeadPortrait() {
			return headPortrait;
		}

		public void setHeadPortrait(String headPortrait) {
			this.headPortrait = headPortrait;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getUserName() {
			return userName;
		}

		public void setDuserName(String userName) {
			this.userName = userName;
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
