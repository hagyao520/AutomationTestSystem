package AutomationTestSystem.Chart;

import java.awt.Color;

import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public interface Cconfig {
	String picContrast_UImain = "图片对比";
	String logs_UImain = "Android日志";
	String getscreen_UImain = "Android屏幕获取";
	String autoscript_UImain = "自动化录制";
	String monkey_UImain = "Monkey";
	String iosGetScreenUImain = "iOS屏幕获取";
	String iosLogsUImain = "iOS日志";

	String ANDROID = "Android";
	String IOS = "iOS";
	/**
	 * 正则格式:xxx=xxx;xxx=xxx;
	 */
	String REGEX_FORMAT = "^([^;=]+=[^;=]+;?){1,}$";// 格式:xxx=xxx;xxx=xxx;

	int WINDOWS = 0;
	int MAC = 1;
	int LINUX = 2;

	String BLUE = "#00EEEE";
	String GREEN = "#00CD66";
	String RED = "#FF3030";
	String YELLOW = "#FFC125";
	Highlighter.HighlightPainter highlightPainter_RED = new DefaultHighlighter.DefaultHighlightPainter(
			Color.decode(Cconfig.RED));
	Highlighter.HighlightPainter highlightPainter_GREEN = new DefaultHighlighter.DefaultHighlightPainter(
			Color.decode(Cconfig.GREEN));
	Highlighter.HighlightPainter highlightPainter_BLUE = new DefaultHighlighter.DefaultHighlightPainter(
			Color.decode(Cconfig.BLUE));
	Highlighter.HighlightPainter highlightPainter_YELLO = new DefaultHighlighter.DefaultHighlightPainter(
			Color.decode(Cconfig.YELLOW));

	String PERFORMANCE_START_TIME = "PERFORMANCE_START_TIME";
	String PERFORMANCE_END_TIME = "PERFORMANCE_END_TIEM";
	String PERFORMANCE_APP_MEM = "PERFORMANCE_APP_MEM";
	String PERFORMANCE_SYS_MEM = "PERFORMANCE_SYS_MEM";
	String PERFORMANCE_APP_CPU = "PERFORMANCE_APP_CPU";
	String PERFORMANCE_SYS_CPU = "PERFORMANCE_SYS_CPU";
	String PERFORMANCE_SEND_NET = "PERFORMANCE_SEND_NET";
	String PERFORMANCE_RECEIVE_NET = "PERFORMANCE_RECEIVE_NET";

	String DATAHANDEL_SETTINGS_EFFECTIVE = "effective";
	String DATAHANDEL_SETTINGS_SIMPLE = "simple";
	String DATAHANDEL_SETTINGS_FLAG = "flag";
	String DATAHANDEL_SETTINGS_REGEX = "regex";
	String DATAHANDEL_SETTINGS_NAME = "name";
	String DATAHANDEL_SETTINGS_RETAIN_DECIMAL = "DATAHANDEL_SETTINGS_RETAIN_DECIMAL";
	String DATAHANDEL_SETTINGS_GROUP_NUM = "group_num";
	String DATAHANDEL_SETTINGS_GROUP_TYPE = "group_type";
	String DATAHANDEL_SETTINGS_GROUP_INTERVAL = "group_interval";
	String DATAHANDEL_SETTINGS_TYPE = "DATAHANDEL_SETTINGS_TYPE";
	String DATAHANDEL_SETTINGS_TYPE_MONITOR = "DATAHANDEL_SETTINGS_TYPE_MONITOR";
	String DATAHANDEL_SETTINGS_TYPE_DRAWING = "DATAHANDEL_SETTINGS_TYPE_DRAWING";
	String DATAHANDEL_SETTINGS_DRAWING_TIMEPERIOD = "drawing_timeperiod";
	String DATAHANDEL_SETTINGS_NAME_TIME = "time";
	String DATAHANDEL_SETTINGS_NAME_INVALID = "invalid";
	String DATAHANDEL_SETTINGS_NAME_FLAG = "flag";

	String PICINSPECT_STAGE_NUM = "PICINSPECT_STAGE_NUM";
	String PICINSPECT_STAGE_FOLDER = "PICINSPECT_STAGE_FOLDER";
	String PICINSPECT_STAGE_ZOOM = "PICINSPECT_STAGE_ZOOM";
	String PICINSPECT_STAGE_WIDTH = "PICINSPECT_STAGE_WIDTH";
	String PICINSPECT_STAGE_HEIGHT = "PICINSPECT_STAGE_HEIGHT";
}