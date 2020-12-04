package wx;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
/**
* 类名: MyX509TrustManager </br>
* 描述:信任管理器 </br>
* 开发人员： howin </br>
* 创建时间： 2016-08-19 </br>
* 发布版本：V1.0 </br>
 */
/*
 * 证书管理器的作用是让它新人我们指定的证书，
 * 此类中的代码意味着信任所有的证书，不管是不是权威机构颁发的。
 */
public class MyX509TrustManager implements X509TrustManager {
  // 检查客户端证书
  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
  }
  // 检查服务器端证书
  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
  }
  // 返回受信任的X509证书数组
  public X509Certificate[] getAcceptedIssuers() {
    return null;
  }
}

