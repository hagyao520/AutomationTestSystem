package AutomationTestSystem.Chart;

import java.awt.Image;

import javax.swing.filechooser.FileSystemView;

public class MainRun {
	public static String Version = "V2.1115";
	public static Image imagelogo;
	public static String selectedID = null;
	public static String selectedOS = "";
	public static AdbBridge adbBridge;
	public static String extraBinlocation = System.getProperty("user.dir") + "/extraBin";
	public static String datalocation = System.getProperty("user.dir") + "/Data";
	public static String QALogfile = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()
			+ "/QAToolsLogs";
	public static int OStype = 0;// 0=win 1=mac 2=linux

	public static void main(String[] args) {
		
		adbBridge = new AdbBridge();
		adbBridge.initialize();

//		PerformanceUI performanceUI = new PerformanceUI("1ed814f6");
//		performanceUI.setVisible(true);
	}
}
