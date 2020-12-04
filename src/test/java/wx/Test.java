package wx;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access
    public static final String GET_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get"; // url
    public static final String APP_ID = "wx0151dac554502201";
    public static final String SECRET = "c1b467fd1863197f06a1762c2c970d99";

    // 获取token
    public static String getToken(String apiurl, String appid, String secret) {
        String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", apiurl, appid, secret);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (json.get("errcode") != null) {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                } else {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                    result = json.get("access_token").getAsString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    /**
     * 获取用户组信息
     *
     * @param url 访问url
     * @param token access_token
     * @return id字符串，每个id以,分割
     */
    public static String getGroups(String url, String token) {
        String groupurl = String.format("%s?access_token=%s", url, token);
        System.out.println(groupurl);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(groupurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
            JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();// 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)// 成功返回消息
            {
                if (json.get("errcode") == null)// 不存在错误消息，成功返回
                {
                    JsonArray groups = json.getAsJsonArray("groups"); // 返回对象数组
                    StringBuffer buffer = new StringBuffer();
                    for (int i = 0; i < groups.size(); i++) {
                        buffer.append(groups.get(i).getAsJsonObject().get("id").getAsString() + ",");
                    }
                    result = buffer.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=========1获取token=========");
        String accessToken = getToken(GET_TOKEN_URL, APP_ID, SECRET);// 获取token在微信之一中的方法获取token
        if (accessToken != null)// token成功获取
        {
            String ids = getGroups(GET_USER_GROUP, accessToken);
            if (ids != null) {
                String[] idarray = ids.split(",");// 用户组id数组
                System.out.println(ids);
            }
        }
    }

}