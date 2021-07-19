package AutomationTestSystem.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author 刘智King
 * @date 2020年8月14日 上午11:18:42
 */
public class DateUtil {
    
    static Logger log = Logger.getLogger(DateUtil.class);
    
    static Calendar cale = Calendar.getInstance();;
    static Map<String,String> date = new HashMap<>();
    
    /**
     * 获取当前系统时间
     * 
     * @return
     */
    public static String getDate() {
        SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        log.info(Date.format(new Date()));
        return Date.format(new Date());
    }

    public static String getDateFormat(String DateFormat) {
        SimpleDateFormat Date = new SimpleDateFormat(DateFormat);
        return Date.format(new Date());
    }
    
    /**
     * 获取当前月的第一天和最后一天
     * 
     * @return
     */
    public static Map<String,String> getDateCalendar(String DateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(DateFormat);
        String firstday,lastday;
        
        // 获取当前月的第一天
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        
        // 获取当前月的最后一天
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        
        date.put("firstday", firstday);
        date.put("lastday", lastday);
        return date;
    }

    /**
     * 获取当前系统时间戳
     * 
     * @return
     */
    public static String getTime() {
        String Time = String.valueOf(new Date().getTime());
        log.info(Time);
        return Time;
    }
    
    public static void main(String[] args) {
        getDate();
        log.info(System.currentTimeMillis());
        
    }
    
}
