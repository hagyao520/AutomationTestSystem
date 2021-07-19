package AutomationTestSystem.Util;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.LinkedHashMap;
import java.util.Map;

import AutomationTestSystem.View.BackendFunctionCenterPageView;

public class HttpGetRequestUtil {
   /**
    * 指定API接口URL,POST请求参数,获取JsonResult
    * @param url
    * @param headers
    * @return response.jsonPath
    */
   public static JsonPath GetJsonResult(String url, Map<String, Object> headers){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .headers(headers)
               .log().all()
               .request()
               .when()
               .get(url);

       response.print();
       
       BackendFunctionCenterPageView.textArea.append("请求地址：" + url + "\n");
       BackendFunctionCenterPageView.textArea.append("请求表头：\n" + headers + "\n");
       BackendFunctionCenterPageView.textArea.append("返回结果：\n" + JsonFormatUtil.formatJson(response.asString()) + "\n");
       BackendFunctionCenterPageView.textArea.append("<------------------------------------------- 分    割    线 -------------------------------------------->");
       BackendFunctionCenterPageView.textAreaToBottom(BackendFunctionCenterPageView.textArea);
       
       return response.jsonPath();
   }

   public static JsonPath GetJsonResult(String url, Map<String, Object> headers, Map<String, Object> params)throws Exception{

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .headers(headers)
               .log().all()
               .request()
               .params(params)
               .when()
               .get(url);
       
       response.print();
       
       BackendFunctionCenterPageView.textArea.append("请求地址：" + url + "\n");
       BackendFunctionCenterPageView.textArea.append("请求表头：\n" + headers + "\n");
       BackendFunctionCenterPageView.textArea.append("请求参数：\n" + JsonFormatUtil.formatJson(params.toString()) + "\n");
       BackendFunctionCenterPageView.textArea.append("返回结果：\n" + JsonFormatUtil.formatJson(response.asString()) + "\n");
       BackendFunctionCenterPageView.textArea.append("<------------------------------------------- 分    割    线 -------------------------------------------->");
       BackendFunctionCenterPageView.textAreaToBottom(BackendFunctionCenterPageView.textArea);
       
       return response.jsonPath();
   }
   
   public static void main(String[] args) throws Exception {
       Map<String, Object> headers = new LinkedHashMap<String, Object>();
       headers.put("Cookie", "_yapi_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjI2LCJpYXQiOjE2MTE5MTQyOTMsImV4cCI6MTYxMjUxOTA5M30.4D6dgCrlW5nADqNDkKxCv8HrRYE6cEfm_074PYFSctU; _yapi_uid=26");
       Map<String, Object> Param = new LinkedHashMap<String, Object>();
       Param.put("interface_id", "992");
       GetJsonResult("http://10.22.83.65:3000/api/group/list", headers);
   }
   
   /**
    * 指定API接口URL,POST请求参数,获取JsonDataParamValueValue
    * @param url
    * @param Param
    * @param Value
    * @param Value1
    * @return JsonDataParamValueValue
    */
   public static String GetJsonDataStringValue(String url, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(url);

       String JsonData = response.jsonPath().get(Value);//获取单个值
       System.out.println(JsonData);

       return JsonData;
   }

   /**
    * 指定API接口URL,POST请求参数,获取JsonDataParamValueValue
    * @param url
    * @param Param
    * @param Value
    * @param Value1
    * @return JsonDataParamValueValue
    */
   public static int GetJsonDataIntValue(String url, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(url);

       int JsonData = response.jsonPath().getInt(Value);//获取单个值
       System.out.println(JsonData);

       return JsonData;
   }

   /**
    * 指定API接口URL,POST请求参数,获取JsonData
    * @param url
    * @param Value
    * @return JsonData
    */
   //双中括号
   public static String GetJsonDataAllValue(String url, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(url);

//       String JsonData = response.jsonPath().get("data[4].detail[5].spot_name");//获取单个值
//       String JsonData = response.jsonPath().get("data.detail.spot_name").toString();//获取全部值
       String  JsonData = response.jsonPath().get(Value).toString();
       System.out.println(JsonData);
//       String jsonData = JsonData.replaceAll("\\[\\[", "{\"").replaceAll("\\]\\]", "\"}").replaceAll("\\], \\[","\", ").replaceAll(",\"","").replaceAll("\", ",", ").replaceAll(", ","\",\"").replaceAll("\"\",","").replaceAll(",\"\"","");
       String jsonData = JsonData.replaceAll("\\[\\[", "").replaceAll("\\]\\]", "\"").replaceAll("\\], \\[",", ").replaceAll(" ,","").replaceAll(", ",",").replaceAll(",\"","").replaceAll("\\[","").replaceAll("\\]","");
       System.out.println(jsonData);

       return jsonData;
   }

   //单中括号
   public static String GetJsonDataAllValue1(String url, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(url);

//       String JsonData = response.jsonPath().get("data[4].detail[5].spot_name");//获取单个值
//       String JsonData = response.jsonPath().get("data.detail.spot_name").toString();//获取全部值
       String  JsonData = response.jsonPath().get(Value).toString();
//       System.out.println(JsonData);
//       String jsonData = JsonData.replaceAll("\\[\\[", "{\"").replaceAll("\\]\\]", "\"}").replaceAll("\\], \\[","\", ").replaceAll(",\"","").replaceAll("\", ",", ").replaceAll(", ","\",\"").replaceAll("\"\",","").replaceAll(",\"\"","");
       String jsonData = JsonData.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(", ",",");
       System.out.println(jsonData);

       return jsonData;
   }

   /**
    * 指定API接口URL,POST请求参数,获取JsonDataParamValueValue
    * @param url
    * @param Param
    * @param Value
    * @param Value1
    * @return JsonDataParamValueValue
    */
   public static String GetJsonDataParamValueValue(String url, String Param,  String Value,  String Value1) throws Exception{

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .params("token", Param)
               .when()
               .get(url);

       String result = response.asString();
       JsonPath jsonPath = new JsonPath(result).setRoot(Value);
       String JsonDataParamValueValue = jsonPath.getString(Value1);
       System.out.println(JsonDataParamValueValue);

       return JsonDataParamValueValue;
   }


}