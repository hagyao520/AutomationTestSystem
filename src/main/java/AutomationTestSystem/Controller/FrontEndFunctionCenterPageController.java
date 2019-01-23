package AutomationTestSystem.Controller;

import java.time.LocalDate;

import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Util.HttpGetRequestUtil;
import AutomationTestSystem.Util.HttpPostRequestUtil;

/**
 * @author King
 * @date 2018年8月1日 18:18:074
 */
public class FrontEndFunctionCenterPageController {

	static SystemServerConfiguration ssc = (SystemServerConfiguration) ConfigManage.get(SystemServerConfiguration.path, SystemServerConfiguration.class);
	static String Api  =ssc.getSapi();
	static String CreateOrderParam;
	
    public static String getAllScenicSpots() {

    	//指定请求的Api地址
        String ApiUrl = "http://"+Api+"/api/client/spot/idSpot";

        //指定API地址,请求参数,发起请求,获取Josn结果
        String SpotIdSpot = HttpGetRequestUtil.GetJsonDataAllValue(ApiUrl,"data.detail.spot_name");

		return SpotIdSpot;
	}

    public static String getSpotTicket(String Account,String PassWord,String SpotName) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

        int uid = HttpPostRequestUtil.GetJsonDataParamIntValue(LoginApiUrl,Param,"data.uid");

    	//指定请求的Api地址
        String sarchTypeSpotApiUrl = "http://"+Api+"/api/client/spot/sarchTypeSpot?keyword="+SpotName+"&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int spot_id = HttpGetRequestUtil.GetJsonDataIntValue(sarchTypeSpotApiUrl,"data.data[0].id");

        //指定请求的Api地址
        String SpotTicketApiUrl = "http://"+Api+"/api/client/spot/getSpotTicket?token="+token+"&uid="+uid+"&spot_id="+spot_id+"";
        String goods_name = HttpGetRequestUtil.GetJsonDataAllValue(SpotTicketApiUrl,"data.goods_name");

		return goods_name;
	}

    public static String getTicketType(String Account,String PassWord,String SpotName,int SightsTicketingSerialNumber,int NatureID) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

        int uid = HttpPostRequestUtil.GetJsonDataParamIntValue(LoginApiUrl,Param,"data.uid");

    	//指定请求的Api地址
        String SrchTypeSpotApiUrl = "http://"+Api+"/api/client/spot/sarchTypeSpot?keyword="+SpotName+"&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int spot_id = HttpGetRequestUtil.GetJsonDataIntValue(SrchTypeSpotApiUrl,"data.data[0].id");

        //指定请求的Api地址
        String SpotTicketApiUrl = "http://"+Api+"/api/client/spot/getSpotTicket?token="+token+"&uid="+uid+"&spot_id="+spot_id+"";
        int goods_id = HttpGetRequestUtil.GetJsonDataIntValue(SpotTicketApiUrl,"data["+SightsTicketingSerialNumber+"].goods_id");

        //指定请求的Api地址
        String TicketTypeApiUrl = "http://"+Api+"/api/client/goods/getTicket?goods_id="+goods_id+"&token="+token+"";
        String TicketType_Name = HttpGetRequestUtil.GetJsonDataAllValue(TicketTypeApiUrl,"data.nature."+NatureID+".children.name");

		return TicketType_Name;
	}

    public static int getStockDay(String Account,String PassWord,String SpotName,int SightsTicketingSerialNumber,int TicketTypeNumber,int TrialCrowdNumber,LocalDate TravelDate) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

        int uid = HttpPostRequestUtil.GetJsonDataParamIntValue(LoginApiUrl,Param,"data.uid");

    	//指定请求的Api地址
        String SrchTypeSpotApiUrl = "http://"+Api+"/api/client/spot/sarchTypeSpot?keyword="+SpotName+"&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int spot_id = HttpGetRequestUtil.GetJsonDataIntValue(SrchTypeSpotApiUrl,"data.data[0].id");

        //指定请求的Api地址
        String SpotTicketApiUrl = "http://"+Api+"/api/client/spot/getSpotTicket?token="+token+"&uid="+uid+"&spot_id="+spot_id+"";
        int goods_id = HttpGetRequestUtil.GetJsonDataIntValue(SpotTicketApiUrl,"data["+SightsTicketingSerialNumber+"].goods_id");

        //指定请求的Api地址
        String TicketTypeApiUrl = "http://"+Api+"/api/client/goods/getTicket?goods_id="+goods_id+"&token="+token+"";
        int TicketType_ID = HttpGetRequestUtil.GetJsonDataIntValue(TicketTypeApiUrl,"data.nature.1.children["+TicketTypeNumber+"].id");
        int TrialCrowd_ID = HttpGetRequestUtil.GetJsonDataIntValue(TicketTypeApiUrl,"data.nature.2.children["+TrialCrowdNumber+"].id");
        String attr = ""+TicketType_ID+","+TrialCrowd_ID+"";

        //指定请求的Api地址
        String StockDayApiUrl = "http://"+Api+"/api/client/goods/ticketMen?token="+token+"&attr="+attr+"&goods_id="+goods_id+"&date="+TravelDate+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int goods_stock = HttpGetRequestUtil.GetJsonDataIntValue(StockDayApiUrl,"data.goods_stock");

		return goods_stock;
	}
    
    public static String getUserGetTingAll(String Account,String PassWord) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

    	//指定请求的Api地址
        String UserGetTingApiUrl = "http://"+Api+"/api/client/user/getUserGetTing?keyword=&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        String UserGetTing_Name = HttpGetRequestUtil.GetJsonDataAllValue1(UserGetTingApiUrl,"data.data.name");

		return UserGetTing_Name;
	}

    public static Object[] getUserGetTing(String Account,String PassWord,int NatureID) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

    	//指定请求的Api地址
        String UserGetTingApiUrl = "http://"+Api+"/api/client/user/getUserGetTing?keyword=&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        String UserGetTing_mobile = HttpGetRequestUtil.GetJsonDataStringValue(UserGetTingApiUrl,"data.data["+NatureID+"].mobile");
        String UserGetTing_sfz_code = HttpGetRequestUtil.GetJsonDataStringValue(UserGetTingApiUrl,"data.data["+NatureID+"].sfz_code");
        String UserGetTing_email = HttpGetRequestUtil.GetJsonDataStringValue(UserGetTingApiUrl,"data.data["+NatureID+"].email");

        Object[] UserGetTingInfo = {UserGetTing_mobile,UserGetTing_sfz_code,UserGetTing_email};

		return UserGetTingInfo;
	}

    public static String getUserFetchWritAll(String Account,String PassWord) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

    	//指定请求的Api地址
        String UserFetchWritApiUrl = "http://"+Api+"/api/client/user/getFetchWrit?token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        String UserFetchWrit_name = HttpGetRequestUtil.GetJsonDataAllValue1(UserFetchWritApiUrl,"data.name");

		return UserFetchWrit_name;
	}

    public static Object[] getUserFetchWrit(String Account,String PassWord,int NatureID) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

    	//指定请求的Api地址
        String UserFetchWritApiUrl = "http://"+Api+"/api/client/user/getFetchWrit?token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        String UserFetchWrit_phone = HttpGetRequestUtil.GetJsonDataStringValue(UserFetchWritApiUrl,"data["+NatureID+"].phone");
        String UserFetchWrit_email = HttpGetRequestUtil.GetJsonDataStringValue(UserFetchWritApiUrl,"data["+NatureID+"].email");

        Object[] UserFetchWritInfo = {UserFetchWrit_phone,UserFetchWrit_email};

		return UserFetchWritInfo;
	}

    public static String getOrderNumber(String Account,String PassWord,String SpotName,int SightsTicketingSerialNumber,int TicketTypeNumber,int TrialCrowdNumber,String PurchaseQuantity,LocalDate TravelDate,
    		String GoingOneName,String GoingOneIDCard,String GoingOneMobile,String GoingOneEmail,
    		String GoingTwoName,String GoingTwoIDCard,String GoingTwoMobile,String GoingTwoEmail,
    		String GoingThreeName,String GoingThreeIDCard,String GoingThreeMobile,String GoingThreeEmail,
    		String GoingFourName,String GoingFourIDCard,String GoingFourMobile,String GoingFourEmail,
    		String GoingFiveName,String GoingFiveIDCard,String GoingFiveMobile,String GoingFiveEmail,
    		String TicketHolderName,String TicketHolderPhone,String TicketHolderEmail,String FetchPhone,int NatureID) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

        int uid = HttpPostRequestUtil.GetJsonDataParamIntValue(LoginApiUrl,Param,"data.uid");

    	//指定请求的Api地址
        String SarchTypeSpotApiUrl = "http://"+Api+"/api/client/spot/sarchTypeSpot?keyword="+SpotName+"&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int spot_id = HttpGetRequestUtil.GetJsonDataIntValue(SarchTypeSpotApiUrl,"data.data[0].id");

        //指定请求的Api地址
        String SpotTicketApiUrl = "http://"+Api+"/api/client/spot/getSpotTicket?token="+token+"&uid="+uid+"&spot_id="+spot_id+"";
        int goods_id = HttpGetRequestUtil.GetJsonDataIntValue(SpotTicketApiUrl,"data["+SightsTicketingSerialNumber+"].goods_id");

        //指定请求的Api地址
        String TicketTypeApiUrl = "http://"+Api+"/api/client/goods/getTicket?goods_id="+goods_id+"&token="+token+"";
        int TicketType_ID = HttpGetRequestUtil.GetJsonDataIntValue(TicketTypeApiUrl,"data.nature.1.children["+TicketTypeNumber+"].id");
        int TrialCrowd_ID = HttpGetRequestUtil.GetJsonDataIntValue(TicketTypeApiUrl,"data.nature.2.children["+TrialCrowdNumber+"].id");
        String spec_ids = ""+TicketType_ID+","+TrialCrowd_ID+"";

        //指定请求的Api地址
        String UserFetchWritApiUrl = "http://"+Api+"/api/client/user/getFetchWrit?token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int FetchId = HttpGetRequestUtil.GetJsonDataIntValue(UserFetchWritApiUrl,"data["+NatureID+"].id");

        String CreateOrderApiUrl = "http://"+Api+"/api/client/order/createOrder";

//        String CreateOrderOneParam = "goods_id=72&spec_ids=286,287&num=1&usetimes=2018-08-22&token="+token+"&"
//        		+"going=[{\"name\":\"张三\",\"sfz_code\":\"35072119910131246X\",\"mobile\":\"13333333333\",\"email\":\"5444@qq.com\"}]&"
//        		+"user_address={\"name\":\"刘智\",\"phone\":\"18694925429\",\"email\":\"18688888888@qq.com\"}&"
//                +"phone=18694925429&fetch_id=39&code=123456";

        //指定请求的Api地址
        String CreateOrderOneParam = "goods_id="+goods_id+"&spec_ids="+spec_ids+"&num="+PurchaseQuantity+"&usetimes="+TravelDate+"&token="+token+"&"
        		+"going=[{\"name\":\""+GoingOneName+"\",\"sfz_code\":\""+GoingOneIDCard+"\",\"mobile\":\""+GoingOneMobile+"\",\"email\":\""+GoingOneEmail+"\"}]&"
        		+"user_address={\"name\":\""+TicketHolderName+"\",\"phone\":\""+TicketHolderPhone+"\",\"email\":\""+TicketHolderEmail+"\"}&"
                +"phone="+FetchPhone+"&fetch_id="+FetchId+"&code=123456";

        //指定请求的Api地址
        String CreateOrderTwoParam = "goods_id="+goods_id+"&spec_ids="+spec_ids+"&num="+PurchaseQuantity+"&usetimes="+TravelDate+"&token="+token+"&"
        		+"going=[{\"name\":\""+GoingOneName+"\",\"sfz_code\":\""+GoingOneIDCard+"\",\"mobile\":\""+GoingOneMobile+"\",\"email\":\""+GoingOneEmail+"\"},"
			                + "{\"name\":\""+GoingTwoName+"\",\"sfz_code\":\""+GoingTwoIDCard+"\",\"mobile\":\""+GoingTwoMobile+"\",\"email\":\""+GoingTwoEmail+"\"}]&"
        		+"user_address={\"name\":\""+TicketHolderName+"\",\"phone\":\""+TicketHolderPhone+"\",\"email\":\""+TicketHolderEmail+"\"}&"
                +"phone="+FetchPhone+"&fetch_id="+FetchId+"&code=123456";

        //指定请求的Api地址
        String CreateOrderThreeParam = "goods_id="+goods_id+"&spec_ids="+spec_ids+"&num="+PurchaseQuantity+"&usetimes="+TravelDate+"&token="+token+"&"
        		+"going=[{\"name\":\""+GoingOneName+"\",\"sfz_code\":\""+GoingOneIDCard+"\",\"mobile\":\""+GoingOneMobile+"\",\"email\":\""+GoingOneEmail+"\"},"
                            + "{\"name\":\""+GoingTwoName+"\",\"sfz_code\":\""+GoingTwoIDCard+"\",\"mobile\":\""+GoingTwoMobile+"\",\"email\":\""+GoingTwoEmail+"\"},"
			                + "{\"name\":\""+GoingThreeName+"\",\"sfz_code\":\""+GoingThreeIDCard+"\",\"mobile\":\""+GoingThreeMobile+"\",\"email\":\""+GoingThreeEmail+"\"}]&"
        		+"user_address={\"name\":\""+TicketHolderName+"\",\"phone\":\""+TicketHolderPhone+"\",\"email\":\""+TicketHolderEmail+"\"}&"
                +"phone="+FetchPhone+"&fetch_id="+FetchId+"&code=123456";

        //指定请求的Api地址
        String CreateOrderFourParam = "goods_id="+goods_id+"&spec_ids="+spec_ids+"&num="+PurchaseQuantity+"&usetimes="+TravelDate+"&token="+token+"&"
        		+"going=[{\"name\":\""+GoingOneName+"\",\"sfz_code\":\""+GoingOneIDCard+"\",\"mobile\":\""+GoingOneMobile+"\",\"email\":\""+GoingOneEmail+"\"},"
                            + "{\"name\":\""+GoingTwoName+"\",\"sfz_code\":\""+GoingTwoIDCard+"\",\"mobile\":\""+GoingTwoMobile+"\",\"email\":\""+GoingTwoEmail+"\"},"
                            + "{\"name\":\""+GoingThreeName+"\",\"sfz_code\":\""+GoingThreeIDCard+"\",\"mobile\":\""+GoingThreeMobile+"\",\"email\":\""+GoingThreeEmail+"\"},"
				            + "{\"name\":\""+GoingFourName+"\",\"sfz_code\":\""+GoingFourIDCard+"\",\"mobile\":\""+GoingFourMobile+"\",\"email\":\""+GoingFourEmail+"\"}]&"
        		+"user_address={\"name\":\""+TicketHolderName+"\",\"phone\":\""+TicketHolderPhone+"\",\"email\":\""+TicketHolderEmail+"\"}&"
                +"phone="+FetchPhone+"&fetch_id="+FetchId+"&code=123456";

        //指定请求的Api地址
        String CreateOrderFiveParam = "goods_id="+goods_id+"&spec_ids="+spec_ids+"&num="+PurchaseQuantity+"&usetimes="+TravelDate+"&token="+token+"&"
        		+"going=[{\"name\":\""+GoingOneName+"\",\"sfz_code\":\""+GoingOneIDCard+"\",\"mobile\":\""+GoingOneMobile+"\",\"email\":\""+GoingOneEmail+"\"},"
                            + "{\"name\":\""+GoingTwoName+"\",\"sfz_code\":\""+GoingTwoIDCard+"\",\"mobile\":\""+GoingTwoMobile+"\",\"email\":\""+GoingTwoEmail+"\"},"
                            + "{\"name\":\""+GoingThreeName+"\",\"sfz_code\":\""+GoingThreeIDCard+"\",\"mobile\":\""+GoingThreeMobile+"\",\"email\":\""+GoingThreeEmail+"\"},"
	                        + "{\"name\":\""+GoingFourName+"\",\"sfz_code\":\""+GoingFourIDCard+"\",\"mobile\":\""+GoingFourMobile+"\",\"email\":\""+GoingFourEmail+"\"},"
				            + "{\"name\":\""+GoingFiveName+"\",\"sfz_code\":\""+GoingFiveIDCard+"\",\"mobile\":\""+GoingFiveMobile+"\",\"email\":\""+GoingFiveEmail+"\"}]&"
        		+"user_address={\"name\":\""+TicketHolderName+"\",\"phone\":\""+TicketHolderPhone+"\",\"email\":\""+TicketHolderEmail+"\"}&"
                +"phone="+FetchPhone+"&fetch_id="+FetchId+"&code=123456";

        String num = PurchaseQuantity;
        switch (num) {
		case "1":
			CreateOrderParam=CreateOrderOneParam;
			break;
		case "2":
			CreateOrderParam=CreateOrderTwoParam;
			break;
		case "3":
			CreateOrderParam=CreateOrderThreeParam;
			break;
		case "4":
			CreateOrderParam=CreateOrderFourParam;
			break;
		case "5":
			CreateOrderParam=CreateOrderFiveParam;
			break;
		}
        String OrderNumber = HttpPostRequestUtil.GetJsonResultX(CreateOrderApiUrl,CreateOrderParam,"data.order_sn");

		return OrderNumber;
	}

    public static String getOrderPaymentInfo(String order_sn) {

    	//指定请求的Api地址
        String OrderPaymentApiUrl = "http://"+Api+"/api/completes?order_sn="+order_sn+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        String PaymentMessage = HttpPostRequestUtil.GetMessag(OrderPaymentApiUrl,"message");

		return PaymentMessage;
	}

    public static String getOrderWriteOffInfo(String Account,String PassWord,String SpotName,String order_sn,LocalDate travelDate) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

    	//指定请求的Api地址
        String SarchTypeSpotApiUrl = "http://"+Api+"/api/client/spot/sarchTypeSpot?keyword="+SpotName+"&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int spot_id = HttpGetRequestUtil.GetJsonDataIntValue(SarchTypeSpotApiUrl,"data.data[0].id");

        //指定请求的Api地址
        String OrderInfoApiUrl = "http://"+Api+"/api/client/order/getOrder?token="+token+"&order_sn="+order_sn+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        String old_goods_time = HttpGetRequestUtil.GetJsonDataStringValue(OrderInfoApiUrl,"data.order_goods[0].name.goods_time");

        //指定请求的Api地址
        String UpdateGoodsTimeApiUrl = "http://"+Api+"/api/client/order/updateGoodsTime?token="+token+"&spot_id="+spot_id+"&old_goods_time="+old_goods_time+"&goods_time="+travelDate+"&order_sn="+order_sn+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        HttpPostRequestUtil.GetMessag(UpdateGoodsTimeApiUrl,"message");

    	//指定请求的Api地址
        String order_goods_sn = HttpGetRequestUtil.GetJsonDataStringValue(OrderInfoApiUrl,"data.order_goods[0].goods[0].order_goods_sn");

        //指定请求的Api地址
        String WriteOffLoginApiUrl = "http://check.travel.szjqb.net/api/verify/loginVerify";
        String WriteOffToken = HttpPostRequestUtil.GetToKen(WriteOffLoginApiUrl,Param);

        String OrderWriteOffConfirmApiUrl = "http://check.travel.szjqb.net/api/verify/checkCodeDetails?order_goods_sn="+order_goods_sn+"&token="+WriteOffToken+"";
        String OrderWriteOffInfoApiUrl = "http://check.travel.szjqb.net/api/verify/appear?order_goods_sn="+order_goods_sn+"&token="+WriteOffToken+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        HttpGetRequestUtil.GetJsonDataStringValue(OrderWriteOffConfirmApiUrl,"message");
        String WriteOffMessage = HttpPostRequestUtil.GetMessag(OrderWriteOffInfoApiUrl,"message");

		return WriteOffMessage;
	}
    
    public static String getOrderEvaluateInfo(String Account,String PassWord,String SpotName,int SightsTicketingSerialNumber,String order_sn,LocalDate travelDate) {

    	//指定请求的Api地址
        String LoginApiUrl = "http://"+Api+"/api/client/user/login";

        final String Param = "{" +
                "\"username\": \""+Account+"\",\"password\": \""+PassWord+"\"}";

        //指定API地址,请求参数,发起请求,获取Cookie值
        String token = HttpPostRequestUtil.GetToKen(LoginApiUrl,Param);

        int uid = HttpPostRequestUtil.GetJsonDataParamIntValue(LoginApiUrl,Param,"data.uid");

    	//指定请求的Api地址
        String SarchTypeSpotApiUrl = "http://"+Api+"/api/client/spot/sarchTypeSpot?keyword="+SpotName+"&token="+token+"";
        //指定API地址,请求参数,发起请求,获取Josn结果
        int spot_id = HttpGetRequestUtil.GetJsonDataIntValue(SarchTypeSpotApiUrl,"data.data[0].id");

        //指定请求的Api地址
        String SpotTicketApiUrl = "http://"+Api+"/api/client/spot/getSpotTicket?token="+token+"&uid="+uid+"&spot_id="+spot_id+"";
        int goods_id = HttpGetRequestUtil.GetJsonDataIntValue(SpotTicketApiUrl,"data["+SightsTicketingSerialNumber+"].goods_id");

        String OrderEvaluateApiUrl = "http://"+Api+"/api/client/goods/userApprise";
        String OrderEvaluateParam ="data[0]={\"detail\":\"玩的很开心，下次还会再来!!!\",\"goods_id\":\""+goods_id+"\",\"order_sn\":\""+order_sn+"\",\"images\":\"https://xiyuyou-dev-public.oss-cn-shanghai.aliyuncs.com/images/user/apprise/"+travelDate+"/5b7cd3b72b7e3.jpg\",\"score\":\"5\"}" +
                "&token="+token+"&order_sn="+order_sn+"";
        
        //指定API地址,请求参数,发起请求
        String EvaluateMessage = HttpPostRequestUtil.GetJsonResultX(OrderEvaluateApiUrl,OrderEvaluateParam,"message");

		return EvaluateMessage;
	} 
}