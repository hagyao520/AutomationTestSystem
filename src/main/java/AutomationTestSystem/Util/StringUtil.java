package AutomationTestSystem.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import AutomationTestSystem.View.LoginPageView;

@SuppressWarnings("unused")
public class StringUtil {

    private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

    public static boolean isEmpty(Object str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean isEqual(Object str1, Object str2) {
        if (str1 == str2 || str1.equals(str2)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEqual(Object str1, Object str2) {
        if (str1 == str2 || str1.equals(str2)) {
            return false;
        }
        return true;
    }

    /**
     * String结果对比，得到OK，NG
     * 
     * @param expectedResult
     * @param actualResult
     * @return
     */
    public static String assertResult(String expectedResult, String actualResult) {
        String result;
        if (expectedResult.equals(actualResult))
            result = "OK";
        else
            result = "NG";
        return result;
    }

    /**
     * 获取指定字符后的内容，例如.
     * 
     * @param s
     * @return
     */
    public static String getSubString(String str) {
        int one = str.lastIndexOf(".");
        String Suffix = str.substring((one + 1), str.length());
        System.out.println(Suffix);
        return Suffix;
    }

    /**
     * 循环获取中文字符串，
     * 例如"{'info':[{'menuId':'193','menuN124ame1':'装饰建材'},{'menuId':'194','menuName':'家纺'},{'menuId':'176','menuName':'手机通讯'},{'menuId':'178','menuName':'手机配件'}]}"
     * 
     * @param str
     * @return
     */
    public static String getChinese(String str) {
        String substrZyhy = "";
        Matcher matcher = Pattern.compile("([\u4e00-\u9fa5]+)").matcher(str);// 只获取中文字符
        while (matcher.find()) {
            substrZyhy += matcher.group(0) + ",";
        }
        System.out.println(substrZyhy);
        return substrZyhy;

    }

    /**
     * 循环获取指定字符之间的内容，例如『123』
     * 
     * @param str,begin,end
     * @return
     */
    public static String[] getBetween(String str, String begin, String end) {
        List<String> results = new ArrayList<String>();
        // Pattern p = Pattern.compile("\\『([\\w]*)\\』"); //获取『』之间的内容，不包含中文
        Pattern p = Pattern.compile("\\" + begin + "([\\w\u4e00-\u9fa5]+)\\" + end + "");// 获取『』之间的内容，包含中文
        Matcher m = p.matcher(str);
        while (m.find()) {
            results.add(m.group(1));
        }
        String[] title = new String[results.size()];
        int i = 0;
        for (String strs : results) {
            System.out.println(strs);
            title[i] = begin + strs + end;
            i++;
        }
        return title;
    }

    /**
     * 获取指定字符之间的内容，例如『123』
     * 
     * @param str,begin,end
     * @return
     */
    public static String getStringBetween(String str, String begin, String end) {
        String newstr = "";
        // Pattern p = Pattern.compile("\\『([\\w]*)\\』"); //获取『』之间的内容，不包含中文
        Pattern p = Pattern.compile("\\" + begin + "([\\w\u4e00-\u9fa5]+)\\" + end + "");// 获取『』之间的内容，包含中文
        Matcher m = p.matcher(str);
        while (m.find()) {
            newstr = m.group(1);
            // System.out.println(newstr);
        }
        return newstr;
    }

    /**
     * 循环获取数组之间的内容，例如[用户ID,客户号,手机号]
     * 
     * @param str,begin,middle,end
     * @return
     */
    public static void getListBetween(String str, String begin, String middle, String end) {
        String[] array = str.split("\\" + middle + "");
        for (String s : array) {
            if (s.indexOf("" + begin + "") != -1) {
                System.out.println(s.substring(s.indexOf("" + begin + "") + 1, s.length()) + " ");
            } else if (s.indexOf("" + end + "") != -1) {
                System.out.println(s.substring(0, s.indexOf("" + end + "")) + " ");
            } else {
                System.out.println(s + " ");
            }
        }
    }

    /**
     * 使用正则表达式去掉多余的.与0 100.00 => 100
     * 
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            // 去掉多余的 0
            s = s.replaceAll("0+?$", "");
            // 如果最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    /**
     * 替换前几位字符
     * 
     * @param str,begin,end,newstr
     * @return
     */
    public static String ReplaceTopFew(String str, int num, String newstr) {
        StringBuilder sb = new StringBuilder(str);
        int end = sb.length() - num;
        sb.replace(0, end, newstr);
//         System.out.println("替换后的字符串：" + sb.toString());
        return sb.toString();
    }
    
    /**
     * 替换后几位字符
     * 
     * @param str,begin,end,newstr
     * @return
     */
    public static String ReplaceLastFew(String str, int num, String newstr) {
        StringBuilder sb = new StringBuilder(str);
        int begin = sb.length() - num;
        int end = sb.length();
        sb.replace(begin, end, newstr);
//         System.out.println("替换后的字符串：" + sb.toString());
        return sb.toString();
    }
    
    /**
     * 替换开始到结束指定位置的字符
     * 
     * @param str,begin,end,newstr
     * @return
     */
    public static void ReplaceBetween(String str, int begin, int end, String newstr) {
        if (isNotEmpty(str)) {
            StringBuilder sb = new StringBuilder(str);
            sb.replace(begin, end, newstr);
            System.out.println("替换后的字符串：" + sb.toString());
        }
    }

    /**
     * 截取开始到结束指定位置的字符
     * 
     * @param str,begin,end,newstr
     * @return
     */
    public static String Substring(String str, int begin, int end) {
        String newstr = str.substring(begin, end);
        // System.out.println(newstr);
        // String newstr1 = str.substring(str.length() - 6);
        // System.out.println(newstr1);
        return newstr;
    }

    public static boolean Substring(String str, int begin, int end, String str1) {
        String newstr = str.substring(begin, end);
        // System.out.println(newstr);
        if (isEqual(newstr, str1)) {
            return false;
        }
        return true;
    }

    /**
     * Java中String类型转换成Map
     * 
     * @param 
     * @return
     */
    public static Map<String, Object> JsonToMap(String str_json) {
        Map<String, Object> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return res;
}
    
    public static void main(String[] args) {
         String str1 = "SELECT " + "a.usercd as 『用户ID』,a.custno as 客户号,a.phonno as 手机号,";
          str1 = str1.replace("as ", "{").replace(",", "}").replace(" FROM", "}");
          System.out.println(str1);
          getChinese(str1);
          System.out.println(getBetween(str1,"『","』")[0]);
        
         ReplaceLastFew("YHT_USER_SIT", 3, "DEV");
         ReplaceTopFew("YHT_USER_UAT", 4, "YHT_ORDER");
//         getBetween("请输入身份证号码...","请输入","...");
//         ReplaceBetween("YHT_USER_UAT", 0,9, "YHT_ORDER");
         
    }
}
