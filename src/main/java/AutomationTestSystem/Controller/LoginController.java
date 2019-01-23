package AutomationTestSystem.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import org.json.JSONObject;

import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Util.DecodeUnicodeUtil;
import AutomationTestSystem.Util.HttpGetRequestUtil;
import AutomationTestSystem.Util.HttpPostRequestUtil;
import javafx.event.ActionEvent;

/**
 * @author King
 * @date 2018年8月1日 18:18:074
 */
@SuppressWarnings("restriction")
public class LoginController {

	static SystemServerConfiguration ssc = (SystemServerConfiguration) ConfigManage.get(SystemServerConfiguration.path, SystemServerConfiguration.class);
	static String Api  =ssc.getSapi();

	@FXML
    public void handlerBtnClick(ActionEvent event) {
        Button btnSource = (Button) event.getSource();
        btnSource.setText("I am clicked!");
    }

    public static String WebUserLogin(String Account,String PassWord){

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Josn结果
        String Message = HttpPostRequestUtil.GetMessag(LoginApiUrl,Param);

		return Message;
	}

    public static Object[] WebUserLoginInfo(String Account,String PassWord) throws Exception {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取token值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);
        
        //指定请求的Api地址
        String UserMsgApiUrl = "http://"+Api+"/api/client/user/userMsg?token="+token+"";

        //指定API地址,请求参数,发起请求,获取Josn结果
        String name = HttpGetRequestUtil.GetJsonDataStringValue(UserMsgApiUrl,"data.username");
        String avatar = HttpGetRequestUtil.GetJsonDataStringValue(UserMsgApiUrl,"data.avatar");
        int user_type = HttpGetRequestUtil.GetJsonDataIntValue(UserMsgApiUrl,"data.user_type");

        Object[] UserLoginInfo = {name,avatar,user_type};

		return UserLoginInfo;
	}

    public static String WebUserLogin1(String Account,String PassWord) throws Exception {

    	//指定请求的Api地址
        String ApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取token值
        String Token = HttpPostRequestUtil.GetToKen(ApiUrl,Param);

        //指定请求的Api地址
        String ApiUrl1 = "http://"+Api+"/api/client/user/userMsg";

        //指定API地址,请求参数,发起请求,获取Josn结果
        String avatar = HttpGetRequestUtil.GetJsonDataParamValueValue(ApiUrl1,Token,"data","avatar");

        //将Json数据中的Unicode编码转换为中文
        DecodeUnicodeUtil.decodeUnicode(avatar);

		return avatar;
	}

    public static String WebUserLogin2(String Account,String PassWord) throws Exception {

    	//指定请求的Api地址
        String ApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取token值
        String Token = HttpPostRequestUtil.GetToKen(ApiUrl,Param);

        //指定请求的Api地址
        String ApiUrl1 = "http://"+Api+"/api/client/user/userMsg?token=";

        //指定API地址,请求参数,发起请求,获取Josn结果
        String JsonResult = HttpGetRequestUtil.GetJsonResult(ApiUrl1,Token);

        JSONObject object_result = new JSONObject(JsonResult);
        String message = object_result.getString("message");

        //将Json数据中的Unicode编码转换为中文
        DecodeUnicodeUtil.decodeUnicode(JsonResult);

		return message;
	}
}