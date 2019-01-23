package AutomationTestSystem.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class IntUtil {

	private static final Logger LOG = LoggerFactory.getLogger(IntUtil.class);

	public static boolean isEmpty(int str) {
		if (0 == str || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(int str) {
		if (0 == str || "".equals(str)) {
			return false;
		}else if(0 != str){
			return true;
		}
		return false;
	}

	public static boolean isEqual(int str1, int str2) {
		if (str1 == str2) {
			return true;
		}
		return false;
	}
}
