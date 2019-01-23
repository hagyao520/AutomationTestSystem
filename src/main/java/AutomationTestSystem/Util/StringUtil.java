package AutomationTestSystem.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class StringUtil {

	private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}else if(null != str){
			return true;
		}
		return false;
	}

	public static boolean isEqual(String str1, String str2) {
		if (str1 == str2 || str1.equals(str2)) {
			return true;
		}
		return false;
	}
}
