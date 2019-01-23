package AutomationTestSystem.Chart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.android.ddmlib.IDevice;


public class AndroidInfo {
	static Logger logger = LoggerFactory.getLogger(AndroidInfo.class);
	static boolean debug = false;
	static boolean isWaitForNewWindow = true;

	final static String INFO = "info";
	final static String WARN = "warn";
	final static String ERROR = "error";
	final static String VERSION_PS = "VERSION_PS";
	final static String VERSION_TOP = "VERSION_TOP";
	final static String VERSION_PM = "VERSION_PM";
	static Map<String, Map<String, Integer>> deviceInfoMap = new HashMap<>();

	/**
	 * 打印日志
	 * 
	 * @param flag
	 * @param info
	 */
	private static void print(String flag, String info) {
		if (debug) {
			if (flag.equals(INFO)) {
				logger.info(info);
			} else if (flag.equals(WARN)) {
				logger.warn(info);
			} else if (flag.equals(ERROR)) {
				logger.error(info);
			}
		}
	}

	/**
	 * 打印日志
	 * 
	 * @param flag
	 * @param info
	 * @param e
	 */
	private static void print(String flag, String info, Exception e) {
		if (debug) {
			if (flag.equals(INFO)) {
				logger.info(info, e);
			} else if (flag.equals(WARN)) {
				logger.warn(info, e);
			} else if (flag.equals(ERROR)) {
				logger.error(info, e);
			}
		}
	}

	/**
	 * 设置设备信息
	 * 
	 * @param udid
	 * @param type
	 * @param version
	 */
	private static void setDeviceInfo(String udid, String type, int version) {
		if (deviceInfoMap.get(udid) == null) {// 新设备
			Map<String, Integer> map = new HashMap<>();
			deviceInfoMap.put(udid, map);
		}
		deviceInfoMap.get(udid).put(type, version);
	}

	/**
	 * 获取设备信息
	 * 
	 * @param udid
	 * @param type
	 * @return
	 */
	private static int getDeviceInfo(String udid, String type) {
		int result = 1;
		if (deviceInfoMap.get(udid) == null) {// 新设备,未设置过.
			Map<String, Integer> map = new HashMap<>();
			deviceInfoMap.put(udid, map);
		}
		// 设置默认值
		if (deviceInfoMap.get(udid).get(VERSION_PM) == null) {
			deviceInfoMap.get(udid).put(VERSION_PM, 1);
		}
		if (deviceInfoMap.get(udid).get(VERSION_PS) == null) {
			deviceInfoMap.get(udid).put(VERSION_PS, 1);
		}
		if (deviceInfoMap.get(udid).get(VERSION_TOP) == null) {
			deviceInfoMap.get(udid).put(VERSION_TOP, 1);
		}
		result = deviceInfoMap.get(udid).get(type);
		return result;
	}

	/**
	 * 判断ps版本<br>
	 * 1=旧版本ps,2=新版本ps -A
	 * 
	 * @param udid
	 * @return
	 */
	public static int setPSversion(String udid) {
		// List<String> result_ps = ADBUtil.returnlist(udid, "ps");
		int adbshell_ps = 1;
		List<String> result_psa = ADBUtil.returnlist(udid, "ps -A");
		if (result_psa.size() > 1) {
			adbshell_ps = 2;
		}
		logger.info("setPSversion:" + adbshell_ps);
		setDeviceInfo(udid, VERSION_PS, adbshell_ps);
		return adbshell_ps;
	}

	/**
	 * 判断pm版本<br>
	 * 1=旧版本pm,2=新版本pm
	 * 
	 * @param udid
	 * @return
	 */
	public static int setPMversion(String udid) {
		int adbshell_pm = 1;
		String result = ADBUtil.execcmd(udid, "pm").toString();
		if (result.contains("'pm list' commands have moved! Run 'adb shell cmd package'")) {
			// Error: didn't specifytype of data to list
			adbshell_pm = 2;
		}
		logger.info("setPMversion:" + adbshell_pm);
		setDeviceInfo(udid, VERSION_PM, adbshell_pm);
		return adbshell_pm;
	}

	/**
	 * 判断top版本 1=旧版本top,2=新版本top
	 * 
	 * @param udid
	 * @return
	 */
	public static int setTOPversion(String udid) {
		int adbshell_top = 1;
		String result = ADBUtil.execcmd(udid, "top -n 1").toString();
//		System.out.println(result);
		if (result.contains("PID USER         PR  NI VIRT  RES  SHR S[%CPU] %MEM     TIME+ ARGS")) {
			adbshell_top = 3;
		} else if (result.contains("PID USER     PR  NI CPU% S  #THR     VSS     RSS PCY Name")) {
			adbshell_top = 2;
		}
		logger.info("setTOPversion:" + adbshell_top);
		setDeviceInfo(udid, VERSION_TOP, adbshell_top);
		return adbshell_top;
	}

	/**
	 * 得到设备信息
	 * 
	 * @return
	 */
	public static Map<String, String> getDeviceInfo(String udid) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("厂商", getManufacturer(udid));
		map.put("机型", getModel(udid));
		map.put("平台", getProduct(udid));
		map.put("API", getSDKVersion(udid));
		map.put("版本", getVersion(udid));
		map.put("语言", getLanguage(udid));
		int[] i = getDeviceResolution(udid);
		map.put("分辨率", i[0] + "x" + i[1]);
		map.put("内存", getDeviceMemroy(udid) + "MB");
		return map;
	}

	/**
	 * 得到内存大小 MB
	 * 
	 * @param udid
	 * @return 失败返回-1
	 */
	public static int getDeviceMemroy(String udid) {
		// MemTotal: 5850384 kB
		String result = ADBUtil.execcmd(udid, CAndroidCMD.GET_MEM).toString();
		if (result.contains("MemTotal")) {
			String[] strings = result.split("\\s+");
			if (strings.length > 2) {
				return Integer.parseInt(strings[1]) / 1024;
			}
		}
		return -1;
	}

	/**
	 * 得到屏幕分辨率
	 * 
	 * @param udid
	 * @return 0=宽,1=高,失败返回{0,0}
	 */
	public static int[] getDeviceResolution(String udid) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.WINDOWS_SIZE).toString();
		int[] i = new int[] { 0, 0 };
		if (result.contains("size")) {
			Matcher matcher = Pattern.compile(".*?(\\d+)x(\\d+)").matcher(result);
			if (matcher.find()) {
				i[0] = Integer.parseInt(matcher.group(1));
				i[1] = Integer.parseInt(matcher.group(2));
			}
		}
		return i;
	}

//	/**
//	 * AAPT得到APK信息
//	 * 
//	 * @param apkpath
//	 * @param filter
//	 * @return
//	 */
//	private static String getAppInfos(String apkpath) {
//		apkpath = apkpath.replaceAll("\\\\", "/");
//		if (!new File(apkpath + "").exists())
//			return apkpath + "文件不存在";
//		String appPackage = CMDUtil.execcmd(CAndroidCMD.INFO_PACKAGE_ANDROID.replace("#apk#", apkpath + ""),
//				CAndroidCMD.CUSCMD, true)[0];
//		return appPackage.trim();
//	}
//
//	/**
//	 * AAPT:得到应用包名
//	 * 
//	 * @param apkpath
//	 * @return
//	 */
//	public static String getAppPackage(String apkpath) {
//		String info = getAppInfos(apkpath);
//		Matcher m = Pattern.compile("package: name='(\\S+)'").matcher(info);
//		if (m.find())
//			return m.group(1);
//		return "";
//	}
//
//	/**
//	 * AAPT:得到应用启动Activity名
//	 * 
//	 * @param apkpath
//	 * @return
//	 */
//	public static String getAppActivity(String apkpath) {
//		String info = getAppInfos(apkpath);
//		Matcher m = Pattern.compile("launchable-activity: name='(\\S+)'").matcher(info);
//		if (m.find())
//			return m.group(1);
//		return "";
//	}
//
//	/**
//	 * AAPT:得到应用权限列表
//	 * 
//	 * @param apkpath
//	 * @return
//	 */
//	public static Map<String, String> getAppPermission(String apkpath) {
//		String info = getAppInfos(apkpath);
//		Matcher m = Pattern.compile("uses-permission:(.*)").matcher(info);
//		Map<String, String> map = new LinkedHashMap<>();
//		int count = 0;
//		while (m.find()) {
//			count++;
//			map.put(count + "", m.group(1));
//		}
//		return map;
//	}
//
//	/**
//	 * AAPT:得到应用信息<br>
//	 * 
//	 * @param apkpath
//	 * @return versionCode,versionName,platformBuildVersionName,sdkVersion,targetSdkVersion,未找到则返回-1
//	 */
//	public static Map<String, String> getAPKInfo(String apkpath) {
//		Map<String, String> map = new LinkedHashMap<>();
//		String info = getAppInfos(apkpath);
//		Matcher m = Pattern.compile(
//				"versionCode='(\\S+)'\\s+versionName='(\\S+)'\\s+platformBuildVersionName='(\\S*?)'\\s+sdkVersion:'(\\S+)'\\s+targetSdkVersion:'(\\S+)'")
//				.matcher(info);
//		String versionCode = "-1";
//		String versionName = "-1";
//		String platformBuildVersionName = "-1";
//		String sdkVersion = "-1";
//		String targetSdkVersion = "-1";
//		if (m.find()) {
//			versionCode = m.group(1);
//			versionName = m.group(2);
//			platformBuildVersionName = m.group(3);
//			sdkVersion = m.group(4);
//			targetSdkVersion = m.group(5);
//		}
//		map.put("versionCode", versionCode);
//		map.put("versionName", versionName);
//		map.put("platformBuildVersionName", platformBuildVersionName);
//		map.put("sdkVersion", sdkVersion);
//		map.put("targetSdkVersion", targetSdkVersion);
//		return map;
//	}

	/**
	 * 获取手机系统时间,精确到毫秒
	 * 
	 * @param udid
	 * @return
	 */
	public static String getDeviceTime(String udid) {
		String nanotime = ADBUtil.execcmd(udid, "echo $EPOCHREALTIME").toString().trim().replace(".", "");
		if (nanotime.length() != 16)
			return "";
		String time = nanotime.substring(0, nanotime.length() - 3);
		return TimeUtil.getTime_Millisecond(Long.parseLong(time));
	}

	/**
	 * 判断应用是否安装
	 * 
	 * @param udid
	 * @param packagename
	 * @return
	 */
	public static boolean isAppInstall(String udid, String packagename) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.IS_INSTALLED.replace("#packagename#", packagename + ""))
				.toString().trim();
		if (result.contains(packagename)) {
			print(INFO, "app is installed:" + packagename);
			return true;
		} else {
			print(INFO, "app is not installed=" + packagename + ":" + result);
			return false;
		}
	}

	/**
	 * 得到应用当前的PID
	 */
	public static String getAppPID(String udid, String packagename) {
		String pid = "";
		String result = "";
		result = ADBUtil.execcmd(udid,
				(getDeviceInfo(udid, VERSION_PS) == 1 ? CAndroidCMD.PS_INFO_ANDROID : CAndroidCMD.PS_INFO_ANDROID_2)
						.replace("#packagename#", packagename + ""))
				.toString();
		if (result.contains(packagename))
			pid = result.split("\\s+")[1];
		return pid;
	}

	/**
	 * 杀掉应用(某些机型无法杀死某些应用,杀掉monkey专用)
	 * 
	 * @param udid
	 * @param packagename
	 * @return
	 */
	@Deprecated
	public static boolean killApp(String udid, String packagename) {
		String pid = AndroidInfo.getAppPID(udid, packagename);
		if (!pid.equals(""))
			ADBUtil.execcmd(udid, CAndroidCMD.KILL_PID.replace("#pid#", pid));
		return !checkIsAlive(udid, packagename);
	}

	/**
	 * 停止应用
	 * 
	 * @param udid
	 * @param packagename
	 * @return
	 */
	public static boolean stopApp(String udid, String packagename) {
		String pid = AndroidInfo.getAppPID(udid, packagename);
		if (!pid.equals(""))
			ADBUtil.execcmd(udid, CAndroidCMD.KILL_PID.replace("#pid#", pid));
		ADBUtil.execcmd(udid, CAndroidCMD.CLOSE_APP.replace("#packagename#", packagename));
		return !checkIsAlive(udid, packagename);
	}

	/**
	 * 启动应用
	 * 
	 * @param udid
	 * @param packagename
	 * @param activity
	 * @return
	 */
	public static boolean launchApp(String udid, String packagename, String activity) {
		String result = ADBUtil.execcmd(udid,
				CAndroidCMD.LAUNCH_APP.replaceAll("#packagename#", packagename).replaceAll("#appActivity#", activity))
				.toString();
		if (!result.contains("Error")) {
			print(INFO, "start app successful:" + packagename + "/" + activity);
			return true;
		} else {
			print(INFO, "start app failed:" + packagename + "/" + activity);
			return false;
		}
	}

	/**
	 * 清除应用数据
	 * 
	 * @param udid
	 * @param packagename
	 * @return
	 */
	public static boolean clearApp(String udid, String packagename) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.CLEAR_APP.replace("#packagename#", packagename)).toString()
				.trim();
		if (result.equals("Success")) {
			print(INFO, "clear app successful:" + packagename);
			return true;
		} else {
			print(INFO, "clear app failed=" + packagename + ":" + result);
			return false;
		}
	}

	/**
	 * 卸载应用(会先判断是否存在该应用)
	 * 
	 * @param udid
	 * @param packagename
	 * @return
	 */
	public static boolean uninstallApp(String udid, String packagename) {
		if (!isAppInstall(udid, packagename)) {
			print(INFO, "not exist,uninstall app failed=" + packagename);
			return false;
		}
		String result = ADBUtil.execcmd(udid, CAndroidCMD.UNINSTALL_APP_PM.replace("#packagename#", packagename))
				.toString();
		if (result.contains("Success")) {
			print(INFO, "uninstall app successful:" + packagename);
			return true;
		} else {
			print(INFO, "uninstall app failed=" + packagename + ":" + result);
			return false;
		}
	}

	/**
	 * 获取应用UID
	 * 
	 * @param udid
	 * @param packagename
	 * @return
	 */
	public static String getAppUID(String udid, String packagename) {
		String uid = "";
		String result = ADBUtil.execcmd(udid, CAndroidCMD.GET_UID.replace("#pid#", getAppPID(udid, packagename)))
				.toString();
		if (result.contains("Uid:"))
			uid = result.split("\\s+")[1];
		return uid;
	}

	/**
	 * 检查应用是否在运行
	 */
	public static boolean checkOnSurface(String udid, String packagename) {
		// TODO Auto-generated method stub
		List<String> list = ADBUtil.returnlist(udid, CAndroidCMD.IS_SURFACE_ANDROID);
		// List<String>
		// list=CMDUtil.returnlist(Ccmd.IS_SURFACE_ANDROID.replaceAll("#udid#",
		// udid+""), Ccmd.SYSCMD, true);
		for (String str : list) {
			if (str.contains(packagename)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查应用是否存活
	 * 
	 * @return
	 */
	public static boolean checkIsAlive(String udid, String packagename) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print(ERROR, "EXCEPTION", e);
		}
		if (!ADBUtil
				.execcmd(udid,
						(getDeviceInfo(udid, VERSION_PS) == 1 ? CAndroidCMD.PS_INFO_ANDROID
								: CAndroidCMD.PS_INFO_ANDROID_2).replace("#packagename#", packagename))
				.toString().trim().equals(""))
			return true;
		// String[] strings=CMDUtil.execcmd(Ccmd.IS_ALIVE_ANDROID.replaceAll("#udid#",
		// udid+"").replaceAll("#package#", packagename), Ccmd.SYSCMD, true);
		// if(strings[0].equals("")) return true;
		return false;
	}

	/**
	 * 获取应用包信息
	 * 
	 * @param udid
	 * @param packagename
	 * @return versionCode, minSdk, targetSdk, versionName,未找到则返回-1
	 */
	public static Map<String, String> getPackageInfo(String udid, String packagename) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.DUMPSYS_PACKAGE.replace("#packagename#", packagename))
				.toString();
		Matcher matcher = Pattern
				.compile("versionCode=(\\S+)\\s+minSdk=(\\S+)\\s+targetSdk=(\\S+)\\s+versionName=(\\S+)")
				.matcher(result);
		String versionCode = "-1";
		String minSdk = "-1";
		String targetSdk = "-1";
		String versionName = "-1";
		if (matcher.find()) {
			versionCode = matcher.group(1);
			minSdk = matcher.group(2);
			targetSdk = matcher.group(3);
			versionName = matcher.group(4);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("versionCode", versionCode);
		map.put("minSdk", minSdk);
		map.put("targetSdk", targetSdk);
		map.put("versionName", versionName);
		return map;
	}

	/**
	 * 返回ro.build.product信息
	 * 
	 * @param udid
	 * @return
	 */
	public static String getProduct(String udid) {
		IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
		if (iDevice != null)
			return iDevice.getProperty("ro.build.product");
		return "";
		// return ADBUtil.execcmd(udid, Ccmd.INFO_PRODUCT_ANDROID).toString().trim();
		// return CMDUtil.execcmd(Ccmd.INFO_PRODUCT_ANDROID.replaceAll("#udid#",
		// udid+""), Ccmd.SYSCMD, true)[0].trim();
	}

	/**
	 * 返回ro.product.manufacturer信息,厂商
	 * 
	 * @param udid
	 * @return
	 */
	public static String getManufacturer(String udid) {
		IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
		if (iDevice != null)
			return iDevice.getProperty("ro.product.manufacturer");
		return "";
		// return ADBUtil.execcmd(udid, Ccmd.INFO_MODEL_ANDROID).toString().trim();
		// return CMDUtil.execcmd(Ccmd.INFO_MODEL_ANDROID.replaceAll("#udid#", udid+""),
		// Ccmd.SYSCMD, true)[0].trim();
	}

	/**
	 * 返回ro.product.model信息
	 * 
	 * @param udid
	 * @return
	 */
	public static String getModel(String udid) {
		IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
		if (iDevice != null)
			return iDevice.getProperty("ro.product.model");
		return "";
		// return ADBUtil.execcmd(udid, Ccmd.INFO_MODEL_ANDROID).toString().trim();
		// return CMDUtil.execcmd(Ccmd.INFO_MODEL_ANDROID.replaceAll("#udid#", udid+""),
		// Ccmd.SYSCMD, true)[0].trim();
	}

	/**
	 * 返回ro.build.version.sdk信息
	 * 
	 * @param udid
	 * @return
	 */
	public static String getSDKVersion(String udid) {
		IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
		if (iDevice != null)
			return iDevice.getProperty("ro.build.version.sdk");
		return "";
		// return ADBUtil.execcmd(udid, Ccmd.INFO_SDKVERSION_ANDROID).toString().trim();
		// return CMDUtil.execcmd(Ccmd.INFO_SDKVERSION_ANDROID.replaceAll("#udid#",
		// udid+""), Ccmd.SYSCMD, true)[0].trim();
	}

	/**
	 * 返回ro.build.version.release信息
	 * 
	 * @param udid
	 * @return
	 */
	public static String getVersion(String udid) {
		IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
		if (iDevice != null)
			return iDevice.getProperty("ro.build.version.release");
		return "";
		// return ADBUtil.execcmd(udid, Ccmd.INFO_VERSION_ANDROID).toString().trim();
		// return CMDUtil.execcmd(Ccmd.INFO_VERSION_ANDROID.replaceAll("#udid#",
		// udid+""), Ccmd.SYSCMD, true)[0].trim();
	}

	/**
	 * 得到设备列表 (str+"
	 * -----"+AndroidInfo.getmanufacturer(str)+","+AndroidInfo.getVersion(str)+","+Cconfig.ANDROID)
	 * 
	 * @return
	 */
	public static List<String> getDevices() {
		List<String> list = new ArrayList<>();
		// for(String str:CMDUtil.returnlist(Ccmd.DEVICELIST_ANDROID, Ccmd.SYSCMD,
		// true)){
		// if(str.contains("List of devices attached"))continue;
		// list.add(str.trim().split("\\s+")[0]);
		// }
		if (LineChartsPaneFXUI.adbBridge.getDevices() != null) {
			for (IDevice device : LineChartsPaneFXUI.adbBridge.getDevices()) {
				list.add(device.toString() + "  -----" + device.getProperty("ro.product.manufacturer") + ","
						+ device.getProperty("ro.build.version.release") + "," + Cconfig.ANDROID);
			}
		}
		return list;
	}

	/**
	 * 获取电池电量
	 * 
	 * @param udid
	 * @return
	 */
	public static int getBatteryLevel(String udid) {
		IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
		Integer result = -1;
		if (iDevice != null) {
			Future<Integer> future = iDevice.getBattery(5, TimeUnit.SECONDS);
			try {
				result = future.get();//// 只有当future的状态是已完成时(future.isDone() = true),get()方法才会返回
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				print(ERROR, "EXCEPTION", e);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				print(ERROR, "EXCEPTION", e);
			}
		}
		return result;
	}

	/**
	 * 获取系统语言
	 * 
	 * @param udid
	 * @return
	 */
	public static String getLanguage(String udid) {
		IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
		String result = "Unknown";
		if (iDevice != null) {
			result = iDevice.getLanguage();
		}
		return result;
	}

	/**
	 * 获取当前系统CPU使用率(top -n 1 -m 1)<br>
	 * 
	 * @return 单位: %
	 */
	@Deprecated
	public static float getSysCpuRate(String udid) {
		// top version=1
		// User 3%, System 7%, IOW 0%, IRQ 0%
		// User 11 + Nice 0 + Sys 23 + Idle 286 + IOW 0 + IRQ 0 + SIRQ 0 = 320
		//
		// PID PR CPU% S #THR VSS RSS PCY UID Name
		// 8090 0 6% R 1 3680K 1236K shell top
//		  PID USER     PR  NI CPU% S  #THR     VSS     RSS PCY Name
//		  32259 shell    20   0  10% R     1   9132K   1912K  fg top

		// top version=2
		// Tasks: 512 total, 2 running, 487 sleeping, 0 stopped, 1 zombie
		// Mem: 5850384k total, 4760732k used, 1089652k free, 166968k buffers
		// Swap: 1048572k total, 61492k used, 987080k free, 2499888k cached
		// 800%cpu 15%user 0%nice 17%sys 768%idle 0%iow 0%irq 0%sirq 0%host
		// PID USER PR NI VIRT RES SHR S[%CPU] %MEM TIME+ ARGS
		// 14924 system 20 0 4.8G 287M 159M S 10.3 5.0 10:10.63 com.android.systemui
		// 413 system -2 -8 450M 37M 33M S 7.6 0.6 19:31.28 surfaceflinger
		// 30988 shell 20 0 10M 4.4M 3.1M R 5.3 0.0 0:00.61 top
		int adbshell_top = getDeviceInfo(udid, VERSION_TOP);
		try {
			if (adbshell_top == 1 || adbshell_top == 2) {
				Pattern pattern = Pattern.compile("User (\\S+)%, System (\\S+)%, IOW (\\S+)%, IRQ (\\S+)%");
				Matcher m = pattern.matcher(ADBUtil.execcmd(udid, "top -n 1 -m 1"));
				if (m.find()) {
					float User = Float.parseFloat(m.group(1));
					float System = Float.parseFloat(m.group(2));
					float IOW = Float.parseFloat(m.group(3));
					float IRQ = Float.parseFloat(m.group(4));
					print(INFO, "getSysCpuRate: " + m.group());
					print(INFO, "系统CPU使用率: " + (User + System + IOW + IRQ));
					return User + System + IOW + IRQ;
				}
			} else if (adbshell_top == 3) {
				Pattern pattern = Pattern
						.compile("(\\S+)%user\\s+\\S+%nice\\s+(\\S+)%sys\\s+\\S+%idle\\s+(\\S+)%iow\\s+(\\S+)%irq");
				Matcher m = pattern.matcher(ADBUtil.execcmd(udid, "top -n 1"));
				if (m.find()) {
					float User = Float.parseFloat(m.group(1));
					float System = Float.parseFloat(m.group(2));
					float IOW = Float.parseFloat(m.group(3));
					float IRQ = Float.parseFloat(m.group(4));
					print(INFO, "getSysCpuRate: " + m.group());
					print(INFO, "系统CPU使用率: " + (User + System + IOW + IRQ));
					return User + System + IOW + IRQ;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取系统CPU使用率出错");
		}
		return -1f;
	}

	/**
	 * 获取当前应用主程序CPU使用率(top -n 1|grep pid)
	 * 
	 * @return 单位: %
	 */
	@Deprecated
	public static float getAppCpuRate(String udid, String packagename) {
		// 31136 4 11% R 96 1728016K 130636K fg u0_a217 vStudio.Android.Camera

		// PID USER PR NI VIRT RES SHR S[%CPU] %MEM TIME+ ARGS
		// 31201 u0_a299 10 -10 2.0G 367M 249M S 11.1 6.4 18:02.76
		// vStudio.Android.Camera
		int adbshell_top = getDeviceInfo(udid, VERSION_TOP);
		try {
			if (adbshell_top == 1 || adbshell_top == 2) {
				String info = ADBUtil.execcmd(udid, "top -n 1|grep " + getAppPID(udid, packagename)).toString();
				print(INFO, "getAppCpuRate: " + info);
				String[] strings = info.split("\\s+");
				int cpu_postion = adbshell_top == 1 ? 2 : 4;
				if (strings.length > 7) {
					print(INFO, "应用CPU使用率: " + strings[cpu_postion]);
					return Float.parseFloat(strings[cpu_postion].substring(0, strings[cpu_postion].length() - 1));
				}
			} else if (adbshell_top == 3) {
				String info = ADBUtil.execcmd(udid, "top -n 1 -p " + getAppPID(udid, packagename)).toString();
				print(INFO, "getAppCpuRate: " + info);
				String[] strings = info.split("\\s+");
				if (strings.length > 10) {
					print(INFO, "应用CPU使用率: " + strings[8]);
					return Float.parseFloat(strings[8]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取应用CPU使用率出错");
		}
		return -1f;
	}

	/**
	 * 获取CPU使用率
	 * 
	 * @param udid
	 * @param packagename null则只获取系统CPU
	 * @return 0=应用,1=系统,值为-1f=出错或无效
	 */
	public static float[] getCpuRate(String udid, String packagename) {
		float sysrate = -1f;
		float apprate = 0;
		int adbshell_top = getDeviceInfo(udid, VERSION_TOP);
		try {
			if (adbshell_top == 1 || adbshell_top == 2) {
				String info = ADBUtil.execcmd(udid, "top -n 1").toString();
				Matcher sysm = Pattern.compile("User (\\S+)%, System (\\S+)%, IOW (\\S+)%, IRQ (\\S+)%").matcher(info);
				if (sysm.find()) {
					float User = Float.parseFloat(sysm.group(1));
					float System = Float.parseFloat(sysm.group(2));
					float IOW = Float.parseFloat(sysm.group(3));
					float IRQ = Float.parseFloat(sysm.group(4));
					print(INFO, "getSysCpuRate: " + sysm.group());
					sysrate = User + System + IOW + IRQ;
				}
				if (packagename != null) {
					int cpu_postion = adbshell_top == 1 ? 2 : 4;
					for (String line : info.split("\n")) {
						if (line.contains(packagename)) {
							String[] strings = line.split("\\s+");
							if (strings.length > 7) {
								apprate += Float.parseFloat(
										strings[cpu_postion].substring(0, strings[cpu_postion].length() - 1));
							}
						}
					}
				}
			} else if (adbshell_top == 3) {
				String info = ADBUtil.execcmd(udid, "top -n 1").toString();
				Matcher m = Pattern
						.compile("(\\S+)%user\\s+\\S+%nice\\s+(\\S+)%sys\\s+\\S+%idle\\s+(\\S+)%iow\\s+(\\S+)%irq")
						.matcher(info);
				if (m.find()) {
					float User = Float.parseFloat(m.group(1));
					float System = Float.parseFloat(m.group(2));
					float IOW = Float.parseFloat(m.group(3));
					float IRQ = Float.parseFloat(m.group(4));
					print(INFO, "getSysCpuRate: " + m.group());
					sysrate = User + System + IOW + IRQ;
				}
				if (packagename != null) {
					for (String line : info.split("\n")) {
						if (line.contains(packagename.substring(0, 14))) {
							String[] strings = line.split("\\s+");
							if (strings.length > 7) {
								apprate += Float.parseFloat(strings[8]);
							}
						}
					}
				}
			}
//			System.out.println("应用CPU使用率: " + apprate);
//			System.out.println("系统CPU使用率: " + sysrate);
			print(INFO, "应用CPU使用率: " + apprate);
			print(INFO, "系统CPU使用率: " + sysrate);
			if (sysrate != -1) {
				return new float[] { apprate, sysrate };
			}
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取CPU使用率出错");
		}
		return new float[] { -1f, -1f };
	}

	/**
	 * 获取系统内存使用率(dumpsys meminfo|grep RAM:)
	 * 
	 * @return 单位: 0=实用内存MB,1=空闲内存MB,2=总共,3=使用率%
	 */
	public static float[] getSysMemSize(String udid) {
		// Total RAM: 2963288 kB (status moderate)
		// Free RAM: 1618487 kB (328551 cached pss + 1139888 cached kernel + 26676 ion
		// cached + 123372 free)
		// Used RAM: 1340750 kB (1041350 used pss + 299400 kernel)
		// Lost RAM: 4051 kB \S*\s+RAM:\s+([-?\d+,?]+)
		try {
			float Total = 1, Free = 1, Used = 1, Lost = 1;
			String infos = ADBUtil.execcmd(udid, "dumpsys meminfo|grep RAM:").toString();
			Matcher m = Pattern.compile("\\S*\\s+RAM:\\s+([-\\d,]+)").matcher(infos);
			while (m.find()) {
				if (m.group().trim().startsWith("Total RAM:")) {
					Total = Float.parseFloat(m.group(1).replace(",", ""));
				} else if (m.group().trim().startsWith("Free RAM:")) {
					Free = Float.parseFloat(m.group(1).replace(",", ""));
				} else if (m.group().trim().startsWith("Used RAM:")) {
					Used = Float.parseFloat(m.group(1).replace(",", ""));
				} else if (m.group().trim().startsWith("Lost RAM:")) {
					Lost = Float.parseFloat(m.group(1).replace(",", ""));
				}
			}
			print(INFO, "系统内存Total=" + Total / 1024 + "MB,Free=" + Free / 1024 + "MB,Used=" + Used / 1024 + "MB,"
					+ (Used + Lost) / Total + "%,Lost=" + Lost / 1024 + "MB");
			if (Free != 1 && Used != 1 && Total != 1) {
				// Lost RAM = Total RAM - Free RAM - Used RAM
				return new float[] { (Used + Lost) / 1024, Free / 1024, Total / 1024, ((Used + Lost) / Total * 100) };
			}
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取系统内存使用率出错");
		}
		return new float[] { -1f, -1f, -1f, -1f };
	}

	/**
	 * 获取应用占用内存大小(dumpsys meminfo package|grep TOTAL)
	 * 
	 * @return 单位:MB
	 */
	public static int getAppMemSize(String udid, String packagename) {
		// TOTAL 130752 98440 22196 0 93784 74150 19633
		try {
			String info = ADBUtil.execcmd(udid, "dumpsys meminfo " + packagename + "|grep TOTAL").toString();
			// print(INFO, "getAppMemSize: " + info);
			String[] strings = info.split("\\s+");
			if (strings.length > 1) {
				int size = Integer.parseInt(strings[1]) / 1024;
				print(INFO, "应用内存占用:" + size + "MB");
				return size;
			}
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取应用内存占用大小出错");
		}
		return -1;
	}

	// 流量统计,方式一,部分机型不兼容
	/**
	 * 获取应用TCP流量累计接收大小(cat /proc/uid_stat/uid/tcp_rcv)
	 * 
	 * @return MB
	 */
	@Deprecated
	public static Double getAppTrafficRcv_tcp(String udid, String packagename) {
		try {
			String info = ADBUtil.execcmd(udid, "cat /proc/uid_stat/" + getAppUID(udid, packagename) + "/tcp_rcv")
					.toString();
			if (!info.contains("No such file or directory")) {
				DecimalFormat df = new DecimalFormat("0.00");
				double size = Float.parseFloat(df.format(Float.parseFloat(info) / 1024 / 1024));
				print(INFO, "应用流量tcp累计接收:" + size + "MB");
				return size;
			}
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取应用流量累计接收大小出错");
		}
		return -1D;
	}

	/**
	 * 获取应用TCP流量累计发送大小(cat /proc/uid_stat/uid/tcp_snd)
	 * 
	 * @return MB
	 */
	@Deprecated
	public static Double getAppTrafficSnd_tcp(String udid, String packagename) {
		try {
			String info = ADBUtil.execcmd(udid, "cat /proc/uid_stat/" + getAppUID(udid, packagename) + "/tcp_snd")
					.toString();
			if (!info.contains("No such file or directory")) {
				DecimalFormat df = new DecimalFormat("0.00");
				double size = Float.parseFloat(df.format(Float.parseFloat(info) / 1024 / 1024));
				print(INFO, "应用流量tcp累计发送:" + size + "MB");
				return size;
			}
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取应用流量累计发送大小出错");
		}
		return -1D;
	}

	// 流量统计,方式二,兼容所有机型
	/**
	 * 获取应用累计流量统计(cat /proc/net/xt_qtaguid/stats |grep uid )<br>
	 * 
	 * @param udid
	 * @param packagename
	 * @return 0总接收rx_MB,1总发送tx_MB,2wlan接收rx_MB_wlan,3wlan发送tx_MB_wlan,4移动接收rx_MB_rmnet,5移动发送tx_MB_rmnet
	 *         (出错则返回-1D)
	 */
	public static float[] getAppTraffic(String udid, String packagename) {
		// 54 wlan0 0x0 13251 0 2604051 2801 314243 3076 2604051 2801 0 0 0 0 314243
		// 3076 0 0 0 0
		// 55 wlan0 0x0 13251 1 128032096 94935 3982656 58269 128032096 94935 0 0 0 0
		// 3982656 58269 0 0 0 0
		// 第四列为UID,其中第6和8列为
		// rx_bytes（接收数据）和tx_bytes（传输数据）包含tcp，udp等所有网络流量传输的统计。iface：网络性质［vlan－wifi流量
		// lo－本地流量 rmnet0－3g/2g流量 ...］
		// idx iface acct_tag_hex uid_tag_int cnt_set rx_bytes rx_packets tx_bytes
		// tx_packets rx_tcp_bytes rx_tcp_packets rx_udp_bytes rx_udp_packets
		// rx_other_bytes rx_other_packets tx_tcp_bytes tx_tcp_packets tx_udp_bytes
		// tx_udp_packets tx_other_bytes tx_other_packets
		try {
			String uid = getAppUID(udid, packagename);
			List<String> list = ADBUtil.returnlist(udid, "cat /proc/net/xt_qtaguid/stats");
			float rx_MB = 0, tx_MB = 0, rx_MB_wlan = 0, tx_MB_wlan = 0, rx_MB_rmnet = 0, tx_MB_rmnet = 0;
			if (list.get(0).contains("uid_tag_int")) {
				list.remove(0);
			} else {
				return new float[] { -1f, -1f, -1f, -1f, -1f, -1f };
			}
			for (String line : list) {
				String[] strings = line.split("\\s+");
				if (strings.length > 3 && strings[3].equals(uid)) {
					if (line.contains("wlan")) {
						rx_MB_wlan += Float.parseFloat(strings[5]) / 1024 / 1024;
						tx_MB_wlan += Float.parseFloat(strings[7]) / 1024 / 1024;
					} else if (line.contains("rmnet")) {
						rx_MB_rmnet += Float.parseFloat(strings[5]) / 1024 / 1024;
						tx_MB_rmnet += Float.parseFloat(strings[7]) / 1024 / 1024;
					}
				}
			}
			// DecimalFormat df = new DecimalFormat("0.00");
			// rx_MB_wlan=Float.parseFloat(df.format(rx_MB_wlan));
			// tx_MB_wlan=Float.parseFloat(df.format(tx_MB_wlan));
			// rx_MB_rmnet=Float.parseFloat(df.format(rx_MB_rmnet));
			// tx_MB_rmnet=Float.parseFloat(df.format(tx_MB_rmnet));
			rx_MB = rx_MB_rmnet + rx_MB_wlan;
			tx_MB = tx_MB_rmnet + tx_MB_wlan;
			print(INFO, "应用流量累计接收:" + rx_MB + "MB,发送:" + tx_MB + "MB");
			print(INFO, "应用流量wlan累计接收:" + rx_MB_wlan + "MB,发送:" + tx_MB_wlan + "MB,rmnet累计接收:" + rx_MB_rmnet + "MB,发送:"
					+ tx_MB_rmnet + "MB");
			// getAppTrafficRcv_tcp(udid,packagename);
			// getAppTrafficSnd_tcp(udid, packagename);
			return new float[] { rx_MB, tx_MB, rx_MB_wlan, tx_MB_wlan, rx_MB_rmnet, tx_MB_rmnet };
		} catch (Exception e) {
			// TODO: handle exception
			print(WARN, "Exception", e);
			print(WARN, "获取应用流量累计发送大小出错");
		}
		return new float[] { -1f, -1f, -1f, -1f, -1f, -1f };
	}

	// settings类
	/**
	 * 获取当前输入法
	 * 
	 * @param udid
	 * @return
	 */
	public static String getDefaultInputMethod(String udid) {
		return ADBUtil.execcmd(udid, CAndroidCMD.GET_DEFAULT_INPUT_METHOD).toString().trim();
	}

	/**
	 * 设置输入法
	 * 
	 * @param udid
	 * @param input_method
	 * @return
	 */
	public static boolean setDefaultInputMethod(String udid, String input_method) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.SET_INPUT_METHOD.replace("#input#", input_method)).toString()
				.trim();
		if (result.matches("Input method.*?selected\\n{0,}")) {
			print(INFO, "set default input method successful");
			return true;
		} else {
			print(INFO, "set default input method failed:" + result);
			return false;
		}
	}

	/**
	 * 得到手机输入法列表
	 * 
	 * @param udid
	 * @return
	 */
	public static List<String> getInputMethodList(String udid) {
		List<String> list = ADBUtil.returnlist(udid, CAndroidCMD.GET_INPUT_METHOD_LIST);
		return list;
	}

	/**
	 * 获取屏幕亮度模式
	 * 
	 * @param udid
	 * @return true=自动 false=手动
	 */
	public static boolean getScreenBrightnessMode(String udid) {
		if (ADBUtil.execcmd(udid, CAndroidCMD.GET_SCREEN_BRIGHTNESS_MODE).toString().trim().equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置屏幕亮度模式是否为自动
	 * 
	 * @param udid
	 * @param on   自动=true,手动=false
	 * @return
	 */
	public static boolean setScreenBrightnessMode(String udid, boolean on) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.SET_SCREEN_BRIGHTNESS_MODE.replace("#value#", on ? "1" : "0"))
				.toString();
		if (result.equals("")) {
			print(INFO, "set screen brightness mode successful");
			return true;
		} else {
			print(INFO, "set screen brightness mode failed:" + result);
			return false;
		}
	}

	/**
	 * 获取屏幕亮度值
	 * 
	 * @param udid
	 * @return -1=获取错误
	 */
	public static int getScreenBrightness(String udid) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.GET_SCREEN_BRIGHTNESS).toString().trim();
		if (HelperUtil.isNumber(result)) {
			return Integer.parseInt(result);
		} else {
			return -1;
		}
	}

	/**
	 * 设置屏幕亮度值
	 * 
	 * @param udid
	 * @param value
	 * @return
	 */
	public static boolean setScreenBrightness(String udid, int value) {
		if (setScreenBrightnessMode(udid, false)
				&& ADBUtil.execcmd(udid, CAndroidCMD.SET_SCREEN_BRIGHTNESS.replace("#value#", value + "")).toString()
						.equals("")) {
			print(INFO, "set screen brightness successful");
			return true;
		} else {
			print(INFO, "set screen brightness failed");
			return false;
		}
	}

	/**
	 * 获取屏幕休眠时间,单位秒
	 * 
	 * @param udid
	 * @return -1为获取错误
	 */
	public static int getScreenOffTimeout(String udid) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.GET_SCREEN_OFF_TIMEOUT).toString().trim();
		if (HelperUtil.isNumber(result)) {
			return Integer.parseInt(result) / 1000;
		} else {
			return -1;
		}
	}

	/**
	 * 设置屏幕休眠时间
	 * 
	 * @param udid
	 * @param value 单位秒
	 * @return
	 */
	public static boolean setScreenOffTimeout(String udid, int value) {
		String result = ADBUtil
				.execcmd(udid, CAndroidCMD.SET_SCREEN_OFF_TIMEOUT.replace("#value_ms#", (value * 1000) + ""))
				.toString();
		if (result.equals("")) {
			print(INFO, "set screen off time out successful");
			return true;
		} else {
			print(INFO, "set screen off time out failed:" + result);
			return false;
		}
	}

	/**
	 * 是否为自动获取时间
	 * 
	 * @param udid
	 * @return
	 */
	public static boolean getAutoTime(String udid) {
		if (ADBUtil.execcmd(udid, CAndroidCMD.GET_AUTO_TIME).toString().trim().equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置自动获取时间
	 * 
	 * @param udid
	 * @param on
	 * @return
	 */
	public static boolean setAutoTime(String udid, boolean on) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.SET_AUTO_TIME.replace("#value#", on ? "1" : "0")).toString();
		if (result.equals("")) {
			print(INFO, "set auto time successful");
			return true;
		} else {
			print(INFO, "set auto time failed:" + result);
			return false;
		}
	}

	/**
	 * 是否为自动获取时区
	 * 
	 * @param udid
	 * @return
	 */
	public static boolean getAutoTimeZone(String udid) {
		if (ADBUtil.execcmd(udid, CAndroidCMD.GET_AUTO_TIME_ZONE).toString().trim().equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置自动获取时区
	 * 
	 * @param udid
	 * @param on
	 * @return
	 */
	public static boolean setAutoTimeZone(String udid, boolean on) {
		String result = ADBUtil.execcmd(udid, CAndroidCMD.SET_AUTO_TIME_ZONE.replace("#value#", on ? "1" : "0"))
				.toString();
		if (result.equals("")) {
			print(INFO, "set auto time zone successful");
			return true;
		} else {
			print(INFO, "set auto time zone failed:" + result);
			return false;
		}
	}

	/**
	 * 获取wifi是否为开
	 * 
	 * @param udid
	 * @return
	 */
	public static boolean getWifiOn(String udid) {
		if (ADBUtil.execcmd(udid, CAndroidCMD.GET_WIFI_ON).toString().trim().equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置wifi开关,部分机型无效
	 * 
	 * @param udid
	 * @param on
	 * @return
	 */
	public static boolean setWifiOn(String udid, boolean on) {
		String result = ADBUtil.execcmd(udid, on ? CAndroidCMD.SET_WIFI_ENABLE : CAndroidCMD.SET_WIFI_DISABLE)
				.toString();
		if (result.equals("")) {
			print(INFO, "set wifi on successful");
			return true;
		} else {
			print(INFO, "set wifi on failed:" + result);
			return false;
		}
	}
//
//	/**
//	 * 等待新窗口(原理: 界面元素不再变化)
//	 * 
//	 * @param driver
//	 * @param oplog
//	 */
//	public static String waitForNewWindow(AndroidDriver<WebElement> driver, SceneLogUtil oplog) {
//		String[] temp = getPageSource(driver);
//		if (!isWaitForNewWindow) {
//			return temp[0];
//		}
//		String[] pagesource = { "", "" };
//		int count = 0;
//		for (int j = 0; j < 10; j++) {
//			count++;
//			// try {Thread.sleep(500);} catch (InterruptedException e) {}
//			pagesource = getPageSource(driver);
//			if (temp[1].equals(pagesource[1])) {
//				break;
//			} else {
//				temp = pagesource;
//			}
//		}
//		if (count > 3)
//			print(INFO, "OP waitForNewWindow: " + count);
//		if (count > 8)
//			oplog.logWarn("等待新窗口次数过多: " + count);
//		return pagesource[0];
//	}
//
//	/**
//	 * 是否等待界面不再变化
//	 * 
//	 * @param iswait
//	 */
//	public static void setWaitForNewWindow(boolean iswait) {
//		isWaitForNewWindow = iswait;
//	}
//
//	/**
//	 * 简化页面元素
//	 * 
//	 * @param driver 驱动
//	 * @return 0=详细页面,1=简化页面
//	 */
//	public static String[] getPageSource(AndroidDriver<WebElement> driver) {
//		String page = "";
//		int count = 0;
//		while (count < 5 && page.equals("")) {
//			count++;
//			try {
//				page = driver.getPageSource();
//			} catch (Exception e) {
//				// TODO: handle exception
//				print(ERROR, "get page source failed:" + count);
//				print(ERROR, "EXCEPTION", e);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//				}
//			}
//		}
//		return new String[] { page, page.replaceAll("\\d+", "") };
//	}
}
