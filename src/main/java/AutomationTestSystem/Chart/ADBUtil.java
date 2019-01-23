package AutomationTestSystem.Chart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.MultiLineReceiver;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.SyncException;
import com.android.ddmlib.TimeoutException;

public class ADBUtil {
	static Logger logger = LoggerFactory.getLogger(ADBUtil.class);

	/**
	 * adb pull
	 * 
	 * @param udid
	 * @param source
	 * @param target
	 */
	public static boolean pullfile(String udid, String source, String target) {
		if (LineChartsPaneFXUI.adbBridge.getDevice(udid) != null) {
			try {
				LineChartsPaneFXUI.adbBridge.getDevice(udid).pullFile(source, target);
				return true;
			} catch (SyncException | IOException | AdbCommandRejectedException | TimeoutException e) {
				// TODO Auto-generated catch block
				logger.error("Exception", e);
			}
		}
		return false;
	}

	/**
	 * 执行adb shell
	 * 
	 * @param udid
	 * @param command
	 * @return
	 */
	public static StringBuffer execcmd(String udid, String command) {
		StringBuffer output = new StringBuffer("No device checked!");
		try {
			IDevice iDevice = LineChartsPaneFXUI.adbBridge.getDevice(udid);
//			System.out.println("iDevice:"+iDevice);
			if (iDevice != null) {
				output.setLength(0);
				iDevice.executeShellCommand(command, new MultiLineReceiver() {
					@Override
					public boolean isCancelled() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void processNewLines(String[] lines) {
						// TODO Auto-generated method stub
						for (String line : lines) { // 将输出的数据缓存起来
							// if(!line.startsWith("* daemon")||!line.startsWith("adb server is out of")){
							output.append(line).append("\n");
							// }
						}
					}
				}, 20, TimeUnit.SECONDS);
			}
		} catch (TimeoutException | AdbCommandRejectedException | ShellCommandUnresponsiveException | IOException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		return output;
	}

	/**
	 * 执行adb shell
	 * 
	 * @param udid
	 * @param command
	 * @return
	 */
	public static List<String> returnlist(String udid, String command) {
		List<String> list = new ArrayList<String>();
		for (String str : execcmd(udid, command).toString().split("\n")) {
			if (!str.equals("")) {
				list.add(str);
			}
		}
		return list;
	}

}
