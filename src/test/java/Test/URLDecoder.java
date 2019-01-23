package Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class URLDecoder {
	/**
	 * 在指定url后追加参数
	 * @param url
	 * @param data 参数集合 key = value
	 * @return newUrl
	 */
	private static String appendUrl(String url, Map<String, Object> data) {
	    String newUrl = url;
	    StringBuffer param = new StringBuffer();
	    for (String key : data.keySet()) {
	    	param.append(key + "=" + data.get(key).toString() + "&");
	    }
	    String paramStr = param.toString();
	    paramStr = paramStr.substring(0, paramStr.length() - 1);
	    if (newUrl.indexOf("?") >= 0) {
	    	newUrl += "&" + paramStr;
	    } else {
	        newUrl += "?" + paramStr;
	    }
	    return newUrl;
	}

	/**
	 * 获取指定url中的某个参数
	 * @param url
	 * @param name
	 * @return value
	 */
	public static String getParamByUrl(String url, String name) {
		url += "&";
	    String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9.]*(&{1})";

	    Pattern r = Pattern.compile(pattern);
	    
	    Matcher m = r.matcher(url);
	    if (m.find( )) {
	        System.out.println(m.group(0));
	        return m.group(0).split("=")[1].replace("&", "");
	    } else {
	        return null;
	    }
	}

	public static void main(String[] args) throws Exception {
		String url = "http://app-global.pgyer.com/460c59cf72a58cfa3ce269420b6eaf17.apk?attname=LekeW.apk&sign=262271ae3a84dc9b25bf602543589eea&t=5be0f7dc";
	    System.out.println(getParamByUrl(url, "attname"));
	    System.out.println(getParamByUrl(url, "sign"));
	    System.out.println(getParamByUrl(url, "t"));
	}

    public static void main1(String[] args) throws Exception {
    	String str = "http://app-global.pgyer.com/460c59cf72a58cfa3ce269420b6eaf17.apk?attname=LekeW.apk&sign=262271ae3a84dc9b25bf602543589eea&t=5be0f7dc";
    	String key1 = str.split("&")[0].split("=")[1];
    	String key2 = str.split("&")[1].split("=")[0];
    	System.out.println(key1 + "--" + key2);
    }
}