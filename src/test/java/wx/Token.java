package wx;

/**
* 类名: TOKEN </br>
* 描述: 凭证 </br>
* 开发人员： howin </br>
* 创建时间： 2016-08-19 </br>
* 发布版本：V1.0 </br>
 */
public class Token {
  // 接口访问凭证֤
  private String accessToken;
  // 凭证有效期单位：second
  private int expiresIn;
  public String getAccessToken() {
    return accessToken;
  }
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  public int getExpiresIn() {
    return expiresIn;
  }
  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }
}

