package wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



/**
* 类名: CommonUtil </br>
* 描述: 通用工具类 </br>
* 开发人员： howin </br>
* 创建时间： 2016-08-19 </br>
* 发布版本：V1.0 </br>
 */
public class CommonUtil {
  private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
  private static long tokenTime = 0;
  private static long jsTicketTime = 0;
  private static Token token = null;
  private static String ticket = null;

  // 凭证获取（GET）
  public final static String token_url  = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
  
  /**
   * 发送https请求
   * 
   * @param requestUrl 请求地址
   * @param requestMethod 请求方式（GET、POST）
   * @param outputStr 提交的数据
   * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
   */
  public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
    JSONObject jsonObject = null;
    try {
      // 创建SSLContext对象，并使用我们指定的信任管理器初始化
      TrustManager[] tm = { new MyX509TrustManager() };
      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
      sslContext.init(null, tm, new java.security.SecureRandom());
      // 从上述SSLContext对象中得到SSLSocketFactory对象
      SSLSocketFactory ssf = sslContext.getSocketFactory();
      URL url = new URL(requestUrl);
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      conn.setSSLSocketFactory(ssf);
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setUseCaches(false);
      // 设置请求方式（GET/POST）
      conn.setRequestMethod(requestMethod);
      // 当outputStr不为null时向输出流写数据
      if (null != outputStr) {
        OutputStream outputStream = conn.getOutputStream();
        // 注意编码格式
        outputStream.write(outputStr.getBytes("UTF-8"));
        outputStream.close();
      }
      // 从输入流读取返回内容
      InputStream inputStream = conn.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String str = null;
      StringBuffer buffer = new StringBuffer();
      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str);
      }
      // 释放资源
      bufferedReader.close();
      inputStreamReader.close();
      inputStream.close();
      inputStream = null;
      conn.disconnect();
      jsonObject = JSONObject.fromObject(buffer.toString());
    } catch (ConnectException ce) {
      log.error("连接超时：{}", ce);
    } catch (Exception e) {
      log.error("https请求异常：{}", e);
    }
    return jsonObject;
  }
  /**
   * 获取接口访问凭证
   * 
   * @param appid 凭证
   * @param appsecret 密钥
   * @return
   */
  public static Token getToken(String appid, String appsecret) {
    long now = new Date().getTime();
    if(tokenTime!=0&&now-tokenTime<7000000){//token有效时间 7e6 毫秒
         return token;
    }
    String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
    // 发起GET请求获取凭证
    JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
    if (null != jsonObject) {
      try {
        token = new Token();
        token.setAccessToken(jsonObject.getString("access_token"));
        token.setExpiresIn(jsonObject.getInt("expires_in"));
        tokenTime = now;
      } catch (JSONException e) {
        token = null;
        // 获取token失败
        log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
      }
    }
    return token;
  }
  /**
   * 获取jsapi_ticket访问凭证
   * */
  public static String getJsTicket(){
      long now = new Date().getTime();
      if(jsTicketTime!=0&&now-jsTicketTime<7000000){//token有效时间 7e6 毫秒
             return ticket;
        }
      //得到token
      String accessToken = getToken(null,null).getAccessToken();

      //GET方法获得jsapi_ticket
      String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi"
          .replace("ACCESS_TOKEN",accessToken);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        if(jsonObject!=null){
            ticket = jsonObject.getString("ticket");
            jsTicketTime = now;
        }
      return ticket;
  }
  /**
   * URL编码（utf-8）
   * 
   * @param source
   * @return
   */
  public static String urlEncodeUTF8(String source) {
    String result = source;
    try {
      result = java.net.URLEncoder.encode(source, "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
  }
  /**
   * 根据内容类型判断文件扩展名
   * 
   * @param contentType 内容类型
   * @return
   */
  public static String getFileExt(String contentType) {
    String fileExt = "";
    if ("image/jpeg".equals(contentType))
      fileExt = ".jpg";
    else if ("audio/mpeg".equals(contentType))
      fileExt = ".mp3";
    else if ("audio/amr".equals(contentType))
      fileExt = ".amr";
    else if ("video/mp4".equals(contentType))
      fileExt = ".mp4";
    else if ("video/mpeg4".equals(contentType))
      fileExt = ".mp4";
    return fileExt;
  }





  // 菜单创建（POST） 限100（次/天）  
  public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  

  /** 
   * 创建菜单 
   *  
   * @param menu 菜单实例 
   * @param accessToken 有效的access_token 
   * @return 0表示成功，其他值表示失败 
   */  
//  public static int createMenu(Menu menu, String accessToken) {  
//      int result = 0;  
//
//      // 拼装创建菜单的url  
//      String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
//      // 将菜单对象转换成json字符串  
//      String jsonMenu = JSONObject.fromObject(menu).toString();  
//      // 调用接口创建菜单  
//      JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);  
//    System.out.println(jsonMenu);
//      if (null != jsonObject) {  
//          if (0 != jsonObject.getInt("errcode")) {  
//              result = jsonObject.getInt("errcode");  
//              log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
//          }  
//      }  
//
//      return result;  
//  }  
  
  /**
   * 对Map数组进行排序  
   * */
    public static String FormatQueryParaMap(HashMap<String, String> parameters) throws UnsupportedEncodingException {
        String buff = "";

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                parameters.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                    Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> item = infoIds.get(i);
            if (item.getKey() != "") {
//              buff += item.getKey() + "="+ URLEncoder.encode(item.getValue(), "utf-8") + "&";
                buff += item.getKey() + "="+ item.getValue() + "&";
            }
        }
        if (buff.isEmpty() == false) {
            buff = buff.substring(0, buff.length() - 1);
        }
        return buff;
    }
    /**
     * 生成32位随机字符串
     * */
    public static String CreateNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i <16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }
    /**
     * SHA1加密
     * */
    public final static String Sha1(String s) {
        char hexDigits[]={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("sha-1");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
       }
    }
}

