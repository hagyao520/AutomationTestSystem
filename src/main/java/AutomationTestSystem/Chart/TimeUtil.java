package AutomationTestSystem.Chart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {
	static Logger logger = LoggerFactory.getLogger(TimeUtil.class);
	static SimpleDateFormat Time4File = new SimpleDateFormat("yyyy_MMdd_HHmm_ss");
	static SimpleDateFormat Time4Log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	static SimpleDateFormat Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 返回当前时间的毫秒值
	 * 
	 * @return
	 */
	public static long getTime() {
		return new Date().getTime();
	}

	/**
	 * 返回当前时间的纳秒值,精确依据计算机系统
	 * 
	 * @return
	 */
	public static long getNanoTime() {
		return System.nanoTime();
	}

	/**
	 * 返回yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time 毫秒
	 * @return
	 */
	public static String getTime(long time) {
		return Time.format(time);
	}

	/**
	 * 返回yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param time 毫秒
	 * @return
	 */
	public static String getTime_Millisecond(long time) {
		return Time4Log.format(time);
	}

	/**
	 * 用于生成适用于文件名称的时间轴yyyy_MMdd_HHmm_ss
	 * 
	 * @return
	 */
	public static String getTime4File() {
		return Time4File.format(new Date());
	}

	/**
	 * 用于生成适用于日记的时间轴 yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @return
	 */
	public static String getTime4Log() {
		return Time4Log.format(new Date());
	}

	/**
	 * 自定义时间轴 yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @return
	 */
	public static String getTime(String dataformat) {
		SimpleDateFormat df = new SimpleDateFormat(dataformat);
		return df.format(new Date());
	}

	/**
	 * 根据格式转换时间字符串
	 * 
	 * @param dataformat
	 * @param time
	 * @return
	 */
	public static long getTime(String dataformat, String time) {
		SimpleDateFormat df = new SimpleDateFormat(dataformat);
		long timestamp = -1;
		try {
			timestamp = df.parse(time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		return timestamp;
	}

	/**
	 * 根据格式转换时间戳
	 * 
	 * @param dataformat
	 * @param timestamp
	 * @return
	 */
	public static String getTime(String dataformat, long timestamp) {
		SimpleDateFormat df = new SimpleDateFormat(dataformat);
		return df.format(timestamp);
	}

	/**
	 * 计算时间差 返回X天X小时X分X秒
	 * 
	 * @param start 毫秒
	 * @param end   毫秒
	 * @return
	 */
	public static String getUseTime(long start, long end) {
		StringBuffer stringBuffer = new StringBuffer();
		long diff = (end - start) / 1000;
		if (diff <= 0)
			return "0秒";
		long days = (diff / (60 * 60 * 24));
		long hours = (diff - days * 60 * 60 * 24) / (60 * 60);
		long mins = (diff - days * 60 * 60 * 24 - hours * 60 * 60) / (60);
		long seconds = diff - days * 60 * 60 * 24 - hours * 60 * 60 - mins * 60;
		if (days > 0)
			stringBuffer.append(days + "天");
		if (hours > 0)
			stringBuffer.append(hours + "小时");
		if (mins > 0)
			stringBuffer.append(mins + "分钟");
		if (seconds > 0)
			stringBuffer.append(seconds + "秒");
		return stringBuffer.toString();
	}
}
