package AutomationTestSystem.Chart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.filechooser.FileSystemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import AutomationTestSystem.Util.StringUtil;
import AutomationTestSystem.View.PerformanceAutomationCenterPageView;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

@SuppressWarnings({"restriction"})
public class LineChartsPaneFXUI {
	Logger logger = LoggerFactory.getLogger(LineChartsPaneFXUI.class);
	public static AdbBridge adbBridge;
	public static String QALogfile = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()
			+ "/QAToolsLogs";
	int width = 855;
	int height = 187;
	int layoutX = 1;
	int layoutY = 1;
	XYChart.Series<String, Number> series_app_mem;
	XYChart.Series<String, Number> series_sys_mem;
	XYChart.Series<String, Number> series_app_cpu;
	XYChart.Series<String, Number> series_sys_cpu;
	XYChart.Series<String, Number> series_send_net;
	XYChart.Series<String, Number> series_receive_net;
	Timer timer;
	Map<String, Object> dataMap;
	boolean isStatistics = false;
	Label lbl_statistics_data;
	Label lbl_info_mem = new Label();
	Label lbl_info_cpu = new Label();
	Label lbl_info_net = new Label();
	Label lbl_threshold_mem = new Label();
	Label lbl_threshold_cpu = new Label();
	Label lbl_threshold_net = new Label();

	int statistics_count_app_mem = 0;
	int statistics_count_sys_mem = 0;
	int statistics_count_app_cpu = 0;
	int statistics_count_sys_cpu = 0;
	int statistics_total_value_app_mem = 0;
	int statistics_total_value_sys_mem = 0;
	float statistics_total_value_app_cpu = 0;
	float statistics_total_value_sys_cpu = 0;
	float statistics_total_value_send_net = 0;
	float statistics_total_value_receive_net = 0;
	int min_value_app_mem = Integer.MAX_VALUE;
	int min_value_sys_mem = Integer.MAX_VALUE;
	float min_value_app_cpu = Float.MAX_VALUE;
	float min_value_sys_cpu = Float.MAX_VALUE;
	int max_value_app_mem = Integer.MIN_VALUE;
	int max_value_sys_mem = Integer.MIN_VALUE;
	float max_value_app_cpu = Float.MIN_VALUE;
	float max_value_sys_cpu = Float.MIN_VALUE;
	
	int value_app_mem =0;
	int value_sys_mem = 0;
	float value_app_cpu = 0;
	float value_sys_cpu = 0;
	float value_send_net = 0;
	float value_receive_net = 0;
	
	LineChartFXUI memAnchorPane;
	LineChartFXUI cpuAnchorPane;
	LineChartFXUI netAnchorPane;

	Performance performance = new Performance();// KWG5T16406001663 5fa632f;
	String udid = null;
	String packagename = null;

	File save_data_file;
	boolean need_save_data;

	public LineChartsPaneFXUI(Label lbl_statistics_data) {
		this.lbl_statistics_data = lbl_statistics_data;
	}

	public LineChartsPaneFXUI() {
		adbBridge = new AdbBridge();
		adbBridge.initialize();
		createLineChartBox();
	}

	/**
	 * 创建监控折线图
	 */
	public VBox createLineChartBox() {
		List<String> invalidList = new ArrayList<>();
		invalidList.add("-1");
		invalidList.add("-1.0");
		memAnchorPane = new LineChartFXUI(width, height);
		series_app_mem = new XYChart.Series<String, Number>();
		memAnchorPane.addSeries(series_app_mem, "应用内存");
		series_sys_mem = new XYChart.Series<String, Number>();
		memAnchorPane.addSeries(series_sys_mem, "系统内存");
		memAnchorPane.getLineChart().getYAxis().setLabel("MB");
		memAnchorPane.addInvalidData(invalidList);

		cpuAnchorPane = new LineChartFXUI(width, height);
		series_app_cpu = new XYChart.Series<String, Number>();
		cpuAnchorPane.addSeries(series_app_cpu, "应用CPU");
		series_sys_cpu = new XYChart.Series<String, Number>();
		cpuAnchorPane.addSeries(series_sys_cpu, "系统CPU");
		cpuAnchorPane.getLineChart().getYAxis().setLabel("百分比");
		cpuAnchorPane.addInvalidData(invalidList);

		netAnchorPane = new LineChartFXUI(width, height);
		series_send_net = new XYChart.Series<String, Number>();
		netAnchorPane.addSeries(series_send_net, "发送数据");
		series_receive_net = new XYChart.Series<String, Number>();
		netAnchorPane.addSeries(series_receive_net, "接收数据");
		netAnchorPane.getLineChart().getYAxis().setLabel("MB");
		netAnchorPane.addInvalidData(invalidList);

		VBox linechart_vbox = new VBox(3);
		linechart_vbox.setPadding(new Insets(10));
		linechart_vbox.setLayoutX(5);
		linechart_vbox.setLayoutY(5);
		
		TitledPane memTitlePane = new TitledPane("内存监控", memAnchorPane);
		TitledPane cpuTitlePane = new TitledPane("CPU监控", cpuAnchorPane);
		TitledPane netTitlePane = new TitledPane("网络监控", netAnchorPane);
		memTitlePane.setExpanded(true);
		cpuTitlePane.setExpanded(true);
		netTitlePane.setExpanded(true);
		linechart_vbox.getChildren().addAll(memTitlePane, cpuTitlePane, netTitlePane);
		return linechart_vbox;
	}

	/**
	 * 设置设备UDID
	 * 
	 * @param udid
	 */
	public void setUdid(String udid) {
		this.udid = udid;
		logger.info("udid="+PerformanceAutomationCenterPageView.MobileDevicesField.getText());
	}

	/**
	 * 设置包名
	 * 
	 * @param packagename
	 */
	public void setPackagename(String packagename) {
		this.packagename = packagename;
		logger.info("packagename="+PerformanceAutomationCenterPageView.AppPackageNameField.getText());
	}

	/**
	 * 获取数据Map
	 * 
	 * @return
	 */
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	/**
	 * 设置是否开始统计
	 * 
	 * @param isStatistics
	 */
	public void setIsStatistics(boolean isStatistics) {
		this.isStatistics = isStatistics;
	}

	/**
	 * 清空统计数据
	 */
	public void clearStatisticsData() {
		statistics_count_app_mem = 0;
		statistics_count_sys_mem = 0;
		statistics_count_app_cpu = 0;
		statistics_count_sys_cpu = 0;
		statistics_total_value_app_mem = 0;
		statistics_total_value_sys_mem = 0;
		statistics_total_value_app_cpu = 0;
		statistics_total_value_sys_cpu = 0;
		statistics_total_value_send_net = 0;
		statistics_total_value_receive_net = 0;
		min_value_app_mem = Integer.MAX_VALUE;
		min_value_sys_mem = Integer.MAX_VALUE;
		min_value_app_cpu = Float.MAX_VALUE;
		min_value_sys_cpu = Float.MAX_VALUE;
		max_value_app_mem = Integer.MIN_VALUE;
		max_value_sys_mem = Integer.MIN_VALUE;
		max_value_app_cpu = Float.MIN_VALUE;
		max_value_sys_cpu = Float.MIN_VALUE;
	}

	/**
	 * 停止动画
	 */
	public void stopMonitor() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	/**
	 * 选择文本保存位置
	 * 
	 * @return
	 */
	private File selectFileByTXT() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择数据文本保存位置");
		File initfile = new File(QALogfile + "/DataRecord");
		if (!initfile.exists()) {
			initfile.mkdirs();
		}
		fileChooser.setInitialDirectory(initfile);
		fileChooser.setInitialFileName("performance_" + TimeUtil.getTime4File() + ".txt");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
		File file = fileChooser.showSaveDialog(null);
		return file;
	}

	/**
	 * 开始记录数据
	 */
	public File startSaveData() {
		save_data_file = selectFileByTXT();
		if (save_data_file != null) {
			if (!save_data_file.exists()) {
				try {
					save_data_file.createNewFile();
				} catch (IOException e) {
					logger.error("EXCEPTION", e);
				}
			}
			need_save_data = true;// 开始标志位
			HelperUtil.file_write_line(save_data_file.getAbsolutePath(), "时间\t\t应用内存\t系统内存\t应用CPU\t\t系统CPU\t\t发送数据\t接收数据\r\n",
					true);
			logger.info("start to save linechart data:" + save_data_file.getAbsolutePath());
		} else {
			need_save_data = false;
		}
		return save_data_file;
	}

	/**
	 * 停止记录数据
	 */
	public void stopSaveData() {
		need_save_data = false;
		logger.info("stop to save linechart data");
	}

	/**
	 * 开始折线图动画
	 */
	public void startMonitor() {
		performance.setUdid(udid);
		performance.setPackagename(packagename);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (!performance.getRunnable()) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							dataMap = performance.getData();// 耗时操作
							String stime = (String) dataMap.get(Cconfig.PERFORMANCE_START_TIME);
							value_app_mem = (int) dataMap.get(Cconfig.PERFORMANCE_APP_MEM);
						    value_sys_mem = (int) dataMap.get(Cconfig.PERFORMANCE_SYS_MEM)-540;
							value_app_cpu = HelperUtil.getFloatDecimal((float) dataMap.get(Cconfig.PERFORMANCE_APP_CPU)/10, 2);
							value_sys_cpu = HelperUtil.getFloatDecimal((float) dataMap.get(Cconfig.PERFORMANCE_SYS_CPU)/10, 2);	
					    	value_send_net = (float) dataMap.get(Cconfig.PERFORMANCE_SEND_NET);
							value_receive_net = (float) dataMap.get(Cconfig.PERFORMANCE_RECEIVE_NET);
							memAnchorPane.addData(series_app_mem, stime, value_app_mem);
							memAnchorPane.addData(series_sys_mem, stime, value_sys_mem);
							cpuAnchorPane.addData(series_app_cpu, stime, value_app_cpu);
							cpuAnchorPane.addData(series_sys_cpu, stime, value_sys_cpu);
							netAnchorPane.addData(series_send_net, stime, value_send_net);
							netAnchorPane.addData(series_receive_net, stime, value_receive_net);
							memAnchorPane.writeSaveData();
							cpuAnchorPane.writeSaveData();
							netAnchorPane.writeSaveData();
							if (need_save_data && save_data_file != null && save_data_file.exists()) {
								String line = stime + "\t" + value_app_mem + "\t\t" + value_sys_mem + "\t\t" + value_app_cpu
										+ "\t\t" + value_sys_cpu + "\t\t" + value_send_net + "\t\t" + value_receive_net + "\r\n";
								System.out.println(line);
								HelperUtil.file_write_line(save_data_file.getAbsolutePath(), line, true);
							}
							if (value_app_mem != -1) {
								statistics_count_app_mem++;
								statistics_total_value_app_mem += value_app_mem;
								max_value_app_mem = value_app_mem > max_value_app_mem ? value_app_mem
										: max_value_app_mem;
								min_value_app_mem = value_app_mem < min_value_app_mem ? value_app_mem
										: min_value_app_mem;
							}else{
								value_app_mem=0;
							}
							if (value_sys_mem != -1) {
								statistics_count_sys_mem++;
								statistics_total_value_sys_mem += value_sys_mem;
								max_value_sys_mem = value_sys_mem > max_value_sys_mem ? value_sys_mem
										: max_value_sys_mem;
								min_value_sys_mem = value_sys_mem < min_value_sys_mem ? value_sys_mem
										: min_value_sys_mem;
							}else{
								value_sys_mem=0;
							}
							if (value_app_cpu != -1) {
								statistics_count_app_cpu++;
								statistics_total_value_app_cpu += value_app_cpu;
								max_value_app_cpu = value_app_cpu > max_value_app_cpu ? value_app_cpu
										: max_value_app_cpu;
								min_value_app_cpu = value_app_cpu < min_value_app_cpu ? value_app_cpu
										: min_value_app_cpu;
							}else{
								value_app_cpu=0;
							}
							if (value_sys_cpu != -1) {
								statistics_count_sys_cpu++;
								statistics_total_value_sys_cpu += value_sys_cpu;
								max_value_sys_cpu = value_sys_cpu > max_value_sys_cpu ? value_sys_cpu
										: max_value_sys_cpu;
								min_value_sys_cpu = value_sys_cpu < min_value_sys_cpu ? value_sys_cpu
										: min_value_sys_cpu;
							}else{
								value_sys_cpu=0;
							}
							if (value_send_net != -1) {
								statistics_total_value_send_net += value_send_net;
							}else{
								value_send_net=0;
							}
							if (value_receive_net != -1) {
								statistics_total_value_receive_net += value_receive_net;
							}else{
								value_receive_net=0;
							}
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// 更新JavaFX的主线程的代码放在此处
									// 显示
									StringBuffer memBuf = new StringBuffer();
									StringBuffer cpuBuf = new StringBuffer();
									StringBuffer netBuf = new StringBuffer();
									int SurplusMemory = PerformanceAutomationCenterPageView.SystemMemory - value_app_mem - value_sys_mem;
									memBuf.append("应用占用内存=" + value_app_mem + "MB,系统占用内存=" + value_sys_mem + "MB,剩余可用内存=" + SurplusMemory + "MB\n");
									cpuBuf.append("应用占用CPU=" + value_app_cpu + "%,系统占用CPU=" + value_sys_cpu + "%\n");
									netBuf.append("应用发送数据=" + value_send_net + "MB；应用接收数据=" + value_receive_net + "MB\n");
									memAnchorPane.setNoteInfo(memBuf.toString());
									cpuAnchorPane.setNoteInfo(cpuBuf.toString());
									netAnchorPane.setNoteInfo(netBuf.toString());
									// 统计
									if (isStatistics) {
										String SendData = ""+ HelperUtil.getFloatDecimal(statistics_total_value_send_net, 2);
										String Receive = ""+ HelperUtil.getFloatDecimal(statistics_total_value_receive_net, 2);
										
										String ApplicationMemory_Average = ""+(statistics_count_app_mem == 0 ?"" : statistics_total_value_app_mem / statistics_count_app_mem);
										String SystemMemory_Average = ""+ (statistics_count_sys_mem == 0 ? "": statistics_total_value_sys_mem / statistics_count_sys_mem);
										String ApplicationCPU_Average = ""+ (statistics_count_app_cpu == 0 ? "": HelperUtil.getFloatDecimal(statistics_total_value_app_cpu/ statistics_count_app_cpu, 1));
										String SystemCPU_Average = ""+ (statistics_count_sys_cpu == 0 ? "": HelperUtil.getFloatDecimal(statistics_total_value_sys_cpu/ statistics_count_sys_cpu, 1));
										
										String ApplicationMemory_Minimum = ""+ (min_value_app_mem == Integer.MAX_VALUE ? "" : min_value_app_mem);
										String SystemMemory_Minimum = ""+ (min_value_sys_mem == Integer.MAX_VALUE ? "" : min_value_sys_mem);
										String ApplicationCPU_Minimum = ""+ (min_value_app_cpu == Float.MAX_VALUE ? "" : min_value_app_cpu);
										String SystemCPU_Minimum = ""+ (min_value_sys_cpu == Float.MAX_VALUE ? "" : min_value_sys_cpu);
										
										String ApplicationMemory_Maximum = ""+ (max_value_app_mem == Integer.MIN_VALUE ? "" : max_value_app_mem);
										String SystemMemory_Maximum = ""+ (max_value_sys_mem == Integer.MIN_VALUE ? "" : max_value_sys_mem);
										String ApplicationCPU_Maximum = ""+ (max_value_app_cpu == Float.MIN_VALUE ? "" : max_value_app_cpu);
										String SystemCPU_Maximum = ""+ (max_value_sys_cpu == Float.MIN_VALUE ? "" : max_value_sys_cpu);
										
										if(StringUtil.isNotEmpty(SendData)){
											if("0.00".equals(SendData)||Double.parseDouble(SendData)<=0.00){
												 PerformanceAutomationCenterPageView.SendDataField.clear();
											 }else{
												 PerformanceAutomationCenterPageView.SendDataField.setText(SendData+" MB");
											 }
										}else{
											PerformanceAutomationCenterPageView.SendDataField.clear();
										}if(StringUtil.isNotEmpty(Receive)){
											if("0.00".equals(Receive)||Double.parseDouble(Receive)<=0.00){
												 PerformanceAutomationCenterPageView.ReceiveField.clear();
											 }else{
												 PerformanceAutomationCenterPageView.ReceiveField.setText(Receive+" MB");
											 }
										}else{
											PerformanceAutomationCenterPageView.ReceiveField.clear();
										}if(StringUtil.isNotEmpty(ApplicationMemory_Average)){
											PerformanceAutomationCenterPageView.ApplicationMemoryField_Average.setText(ApplicationMemory_Average+" MB");
										}else{
											PerformanceAutomationCenterPageView.ApplicationMemoryField_Average.clear();
										}if(StringUtil.isNotEmpty(SystemMemory_Average)){
											PerformanceAutomationCenterPageView.SystemMemoryField_Average.setText(SystemMemory_Average+" MB");
										}else{
											PerformanceAutomationCenterPageView.SystemMemoryField_Average.clear();
										}if(StringUtil.isNotEmpty(ApplicationCPU_Average)){
											 if("0.0".equals(ApplicationCPU_Average)){
												 PerformanceAutomationCenterPageView.ApplicationCPUField_Average.clear();
											 }else{
												 PerformanceAutomationCenterPageView.ApplicationCPUField_Average.setText(ApplicationCPU_Average+" %");
											 }
										}else{
											PerformanceAutomationCenterPageView.ApplicationCPUField_Average.clear();
										}if(StringUtil.isNotEmpty(SystemCPU_Average)){
											PerformanceAutomationCenterPageView.SystemCPUField_Average.setText(SystemCPU_Average+" %");
										}else{
											PerformanceAutomationCenterPageView.SystemCPUField_Average.clear();
										}if(StringUtil.isNotEmpty(ApplicationMemory_Minimum)){
											PerformanceAutomationCenterPageView.ApplicationMemoryField_Minimum.setText(ApplicationMemory_Minimum+" MB");
										}else{
											PerformanceAutomationCenterPageView.ApplicationMemoryField_Minimum.clear();
										}if(StringUtil.isNotEmpty(SystemMemory_Minimum)){
											PerformanceAutomationCenterPageView.SystemMemoryField_Minimum.setText(SystemMemory_Minimum+" MB");
										}else{
											PerformanceAutomationCenterPageView.SystemMemoryField_Minimum.clear();
										}if(StringUtil.isNotEmpty(ApplicationCPU_Minimum)){
											if("0.0".equals(ApplicationCPU_Minimum)){
												 PerformanceAutomationCenterPageView.ApplicationCPUField_Minimum.clear();
											 }else{
												 PerformanceAutomationCenterPageView.ApplicationCPUField_Minimum.setText(ApplicationCPU_Minimum+" %");
											 }
										}else{
											PerformanceAutomationCenterPageView.ApplicationCPUField_Minimum.clear();
										}if(StringUtil.isNotEmpty(SystemCPU_Minimum)){
											PerformanceAutomationCenterPageView.SystemCPUField_Minimum.setText(SystemCPU_Minimum+" %");
										}else{
											PerformanceAutomationCenterPageView.SystemCPUField_Minimum.clear();
										}if(StringUtil.isNotEmpty(ApplicationMemory_Maximum)){
											PerformanceAutomationCenterPageView.ApplicationMemoryField_Maximum.setText(ApplicationMemory_Maximum+" MB");
										}else{
											PerformanceAutomationCenterPageView.ApplicationMemoryField_Maximum.clear();
										}if(StringUtil.isNotEmpty(SystemMemory_Maximum)){
											PerformanceAutomationCenterPageView.SystemMemoryField_Maximum.setText(SystemMemory_Maximum+" MB");
										}else{
											PerformanceAutomationCenterPageView.SystemMemoryField_Maximum.clear();
										}if(StringUtil.isNotEmpty(ApplicationCPU_Maximum)){
											PerformanceAutomationCenterPageView.ApplicationCPUField_Maximum.setText(ApplicationCPU_Maximum+" %");
										}else{
											PerformanceAutomationCenterPageView.ApplicationCPUField_Maximum.clear();
										}if(StringUtil.isNotEmpty(SystemCPU_Maximum)){
											PerformanceAutomationCenterPageView.SystemCPUField_Maximum.setText(SystemCPU_Maximum+" %");
										}else{
											PerformanceAutomationCenterPageView.SystemCPUField_Maximum.clear();
										}
									}
								}
							});
						}
					}).start();
				}
			}
		}, 100, 500);
	}
}
