package AutomationTestSystem.Util;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HttpGetRequestUtil {
   /**
    * 指定API接口URL,POST请求参数,获取JsonResult
    * @param ApiUrl
    * @param Param
    * @return JsonResult
    */
   public static String GetJsonResult(String ApiUrl, String Param){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .params("token", Param)
               .when()
               .get(ApiUrl);

       String JsonResult = response.asString();
       System.out.println("返回的值JsonResult:"+JsonResult);

       return JsonResult;
   }

   /**
    * 指定API接口URL,POST请求参数,获取JsonDataParamValueValue
    * @param ApiUrl
    * @param Param
    * @param Value
    * @param Value1
    * @return JsonDataParamValueValue
    */
   public static String GetJsonDataStringValue(String ApiUrl, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(ApiUrl);

       String JsonData = response.jsonPath().get(Value);//获取单个值
       System.out.println(JsonData);

       return JsonData;
   }

   /**
    * 指定API接口URL,POST请求参数,获取JsonDataParamValueValue
    * @param ApiUrl
    * @param Param
    * @param Value
    * @param Value1
    * @return JsonDataParamValueValue
    */
   public static int GetJsonDataIntValue(String ApiUrl, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(ApiUrl);

       int JsonData = response.jsonPath().getInt(Value);//获取单个值
       System.out.println(JsonData);

       return JsonData;
   }

   /**
    * 指定API接口URL,POST请求参数,获取JsonData
    * @param ApiUrl
    * @param Value
    * @return JsonData
    */
   //双中括号
   public static String GetJsonDataAllValue(String ApiUrl, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(ApiUrl);

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
   public static String GetJsonDataAllValue1(String ApiUrl, String Value){

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .when()
               .get(ApiUrl);

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
    * @param ApiUrl
    * @param Param
    * @param Value
    * @param Value1
    * @return JsonDataParamValueValue
    */
   public static String GetJsonDataParamValueValue(String ApiUrl, String Param,  String Value,  String Value1) throws Exception{

       Response response = given()
               .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
               .contentType("application/json")
               .log().all()
               .request()
               .params("token", Param)
               .when()
               .get(ApiUrl);

       String result = response.asString();
       JsonPath jsonPath = new JsonPath(result).setRoot(Value);
       String JsonDataParamValueValue = jsonPath.getString(Value1);
       System.out.println(JsonDataParamValueValue);

       return JsonDataParamValueValue;
   }


}