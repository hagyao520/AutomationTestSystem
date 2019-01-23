package AutomationTestSystem.Config;

/**
 * 描述：
 *
 * @author King
 * @date 2016年1月23日 上午12:15:21
 * @version 0.0.1
 */
public class UserSaveData {

	private String head;
	private String account;
	private String passWord;
	private boolean rememberPasswordStatus;
	private boolean automaticLogonStatus;

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean getRememberPasswordStatus() {
		return rememberPasswordStatus;
	}

	public void setRememberPasswordStatus(boolean rememberPasswordStatus) {
		this.rememberPasswordStatus = rememberPasswordStatus;
	}

	public boolean getAutomaticLogonStatus() {
		return automaticLogonStatus;
	}

	public void setAutomaticLogonStatus(boolean automaticLogonStatus) {
		this.automaticLogonStatus = automaticLogonStatus;
	}
}
