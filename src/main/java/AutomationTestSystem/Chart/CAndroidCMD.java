package AutomationTestSystem.Chart;

public interface CAndroidCMD {
	int SYSCMD = 1;
	int CUSCMD = 2;

	/**
	 * Android命令
	 */
	// 常用命令
	// String DEVICELIST_ANDROID="#adb# devices";
	//
	// // screen capture
	// String SCREEN_CAP_ANDROID= "#adb# -s #udid# shell \"screencap -p
	// /data/local/tmp/qascreenshot.png\"";
	//
	// // pull screenshot
	// String PULL_SCREENSHOT_ANDROID = "#adb# -s #udid# pull
	// /data/local/tmp/qascreenshot.png \"#savepath#\"";
	//
	// //adb logcat
	// String LOGCAT="#adb# -s #udid# logcat -v threadtime";
	//
	// String LOGCAT_PID="#adb# -s #udid# shell \"ps logcat|grep logcat\"";
	//
	// String LOGCAT_STOP="#adb# -s #udid# shell kill -9 #pid#";
	//
	// String LOGCAT_CLEAR="#adb# -s #udid# logcat -c";
	//
	// //info命令
	// String IS_SURFACE_ANDROID="#adb# -s #udid# shell \"dumpsys window w |grep -i
	// 'name=.*\\/.*'\"";
	//
	// String IS_ALIVE_ANDROID="#adb# -s #udid# shell \"ps |grep '#package#$'\"";
	//
	// String INFO_PACKAGE_ANDROID="#aapt# dump badging \"#apk#\"";
	//
	// String INFO_PRODUCT_ANDROID="#adb# -s #udid# shell getprop ro.build.product";
	//
	// String INFO_VERSION_ANDROID="#adb# -s #udid# shell getprop
	// ro.build.version.release";
	//
	// String INFO_SDKVERSION_ANDROID="#adb# -s #udid# shell getprop
	// ro.build.version.sdk";
	//
	// String INFO_MODEL_ANDROID="#adb# -s #udid# shell getprop ro.product.model";
	// 操作命令
	String INPUT_TAP = "input tap #x# #y#";

	String INPUT_SWIPE = "input swipe #x1# #y1# #x2# #y2# #duration#";
	String INPUT_TEXT = "input text #msm#";
	// 常用命令
	String DEVICELIST_ANDROID = "#adb# devices";

	String GET_UID = "cat /proc/#pid#/status |grep Uid: ";
	String DUMPSYS_PACKAGE = "dumpsys package #packagename#";
	String GET_MEM = "cat proc/meminfo| grep MemTotal";
	// uiautomator dump
	String DUMP_PAGE = "uiautomator dump -file /sdcard/window_dump.xml";
	String CAT_DUMP_PAGE = "cat /sdcard/window_dump.xml";
	// monkey
	// settings
	String GET_DEFAULT_INPUT_METHOD = "settings get secure default_input_method";
	String SET_INPUT_METHOD = "ime set #input#";
	String GET_INPUT_METHOD_LIST = "ime list -s";
	String GET_SCREEN_BRIGHTNESS_MODE = "settings get system screen_brightness_mode";// 1为自动
	String SET_SCREEN_BRIGHTNESS_MODE = "settings put system screen_brightness_mode #value#";// 1为自动,0为关闭
	String GET_SCREEN_BRIGHTNESS = "settings get system screen_brightness";
	String SET_SCREEN_BRIGHTNESS = "settings put system screen_brightness #value#";
	String GET_SCREEN_OFF_TIMEOUT = "settings get system screen_off_timeout";
	String SET_SCREEN_OFF_TIMEOUT = "settings put system screen_off_timeout #value_ms#";// 毫秒,通常15S/30S/1M/2M/5M/10M
	String GET_AUTO_TIME = "settings get global auto_time";// 获取日期时间选项中通过网络获取时间的状态，1为允许、0为不允许
	String SET_AUTO_TIME = "settings put global auto_time #value#";// 1为允许、0为不允许
	String GET_AUTO_TIME_ZONE = "settings get global auto_time_zone";// 获取日期时间选项中通过网络获取时区的状态，1为允许、0为不允许
	String SET_AUTO_TIME_ZONE = "settings put global auto_time_zone #value#";// 1为允许、0为不允许
	String GET_WIFI_ON = "settings get global wifi_on";// 1开,0关
	String SET_WIFI_ENABLE = "svc wifi enable";// 部分机型不适用
	String SET_WIFI_DISABLE = "svc wifi disable";// 部分机型不适用
	// install
	String PACKAGE_LIST = "pm list packages";
	String PACKAGE_LIST_2 = "cmd package list packages";
	String IS_INSTALLED = "pm list packages #packagename#";
	String IS_INSTALLED_2 = "cmd package list packages -3 #packagename#";
	String UNINSTALL_APP = "#adb# uninstall #packagename#";
	String INSTALL_APP = "#adb# install -r \"#appPath#\"";// install-multiple
	String UNINSTALL_APP_PM = "pm uninstall #packagename#";
	String INSTALL_APP_PM = "pm install \"#appPath#\"";

	String WINDOWS_SIZE = "wm size";
	// screen record
	String SCREEN_RECORD = "screenrecord --help";

	// screen capture
	String SCREEN_CAP_ANDROID = "screencap -p /data/local/tmp/qascreenshot.png";
	// start close app
	String LAUNCH_APP = "am start #packagename#/#appActivity#";
	String CLOSE_APP = "am force-stop #packagename#";
	String CLEAR_APP = "pm clear #packagename#";
	// pull screenshot
	String PULL_SCREENSHOT_ANDROID = "#adb# -s #udid# pull /data/local/tmp/qascreenshot.png \"#savepath#\"";

	// adb logcat
	String LOGCAT = "logcat -v threadtime";

	String KILL_PID = "kill -9 #pid#";

	String LOGCAT_CLEAR = "logcat -c";

	// info命令
	String IS_SURFACE_ANDROID = "dumpsys window w |grep -i 'name=.*\\/.*'";

	String TOP_ACTIVITY_ANDROID = "dumpsys activity top|grep ACTIVITY";

	String PS_INFO_ANDROID = "ps |grep '#packagename#$'";

	String PS_INFO_ANDROID_2 = "ps -A|grep '#packagename#$'";

	String INFO_PACKAGE_ANDROID = "#aapt# dump badging \"#apk#\"";

	String INFO_PRODUCT_ANDROID = "getprop ro.build.product";

	String INFO_VERSION_ANDROID = "getprop ro.build.version.release";

	String INFO_SDKVERSION_ANDROID = "getprop ro.build.version.sdk";

	String INFO_MODEL_ANDROID = "getprop ro.product.model";

}
