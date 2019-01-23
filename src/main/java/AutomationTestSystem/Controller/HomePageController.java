package AutomationTestSystem.Controller;

import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Util.HttpGetRequestUtil;
import AutomationTestSystem.Util.HttpPostRequestUtil;

/**
 * @author King
 * @date 2018年8月1日 18:18:074
 */
public class HomePageController {

	static SystemServerConfiguration ssc = (SystemServerConfiguration) ConfigManage.get(SystemServerConfiguration.path, SystemServerConfiguration.class);
	static String Api  =ssc.getSapi();
	static String CreateOrderParam;

    public static Object[] getUserLoginInfo(String Account,String PassWord) throws Exception {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定请求的Api地址
        String UserMsgApiUrl = "http://"+Api+"/api/client/user/userMsg";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String Token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

        //指定API地址,请求参数,发起请求,获取Josn结果
        String name = HttpGetRequestUtil.GetJsonDataParamValueValue(UserMsgApiUrl,Token,"data","username");
        String avatar = HttpGetRequestUtil.GetJsonDataParamValueValue(UserMsgApiUrl,Token,"data","avatar");
//        String mobile = HttpGetRequestUtil.GetJsonDataParamValueValue(UserMsgApiUrl,Token,"data","mobile");

        Object[] UserLoginInfo = {name,avatar};

		return UserLoginInfo;
	}
}