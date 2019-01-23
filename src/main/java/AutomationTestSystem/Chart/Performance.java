package AutomationTestSystem.Chart;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class Performance {
	Logger logger = LoggerFactory.getLogger(Performance.class);
	String udid;
	String packagename;
	String timeformat = "HH:mm:ss";
	boolean runnable = false;

	float sTraficRx = -1, sTraficTx = -1, sTraficRx_wlan = -1, sTraficTx_wlan = -1, sTraficRx_rmnet = -1,
			sTraficTx_rmnet = -1;// 流量初始标记
	float tempTraficRx = -1, tempTraficTx = -1, tempTraficRx_wlan = -1, tempTraficTx_wlan = -1, tempTraficRx_rmnet = -1,
			tempTraficTx_rmnet = -1;// 流量临时标记

	boolean error_app_mem = false;
	boolean error_sys_mem = false;
	boolean error_cpu = false;
	boolean error_net = false;

	public Performance() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置设备udid,并得到命令版本信息
	 * 
	 * @param udid
	 */
	public void setUdid(String udid) {
		this.udid = udid;
		AndroidInfo.setTOPversion(udid);
		AndroidInfo.setPSversion(udid);
		AndroidInfo.setPMversion(udid);
	}

	/**
	 * 设置包名,并重置流量统计
	 * 
	 * @param packagename
	 */
	public void setPackagename(String packagename) {
		this.packagename = packagename;
		error_net = openTraficStatistics();
	}

	/**
	 * 判断是否正在运行
	 * 
	 * @return
	 */
	public boolean getRunnable() {
		return runnable;
	}

	/**
	 * 获取性能数据
	 * 
	 * @return stime,etime,appmem,sysmem,appcpu,syscpu,sendnet,receivenet
	 */
	public Map<String, Object> getData() {
		Map<String, Object> map = new HashMap<>();
		CountDownLatch countDownLatch = new CountDownLatch(4);
		long stime = TimeUtil.getTime();

		AppMemRunnable appMemRunnable = new AppMemRunnable(countDownLatch);
		Thread appMemThread = new Thread(appMemRunnable);
		appMemThread.start();

		SysMemRunnable sysMemRunnable = new SysMemRunnable(countDownLatch);
		Thread sysMemThread = new Thread(sysMemRunnable);
		sysMemThread.start();

		CPURunnable cpuRunnable = new CPURunnable(countDownLatch);
		Thread CPUThread = new Thread(cpuRunnable);
		CPUThread.start();

		NetRunnable netRunnable = new NetRunnable(countDownLatch);
		Thread NetThread = new Thread(netRunnable);
		NetThread.start();

		runnable = true;
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("EXCEPTION", e);
		}
		runnable = false;
		long etime = TimeUtil.getTime();
		// logger.info(TimeUtil.getUseTime(stime, etime));

		map.put(Cconfig.PERFORMANCE_START_TIME, TimeUtil.getTime(timeformat, stime));
		map.put(Cconfig.PERFORMANCE_END_TIME, TimeUtil.getTime(timeformat, etime));
		map.put(Cconfig.PERFORMANCE_APP_MEM, appMemRunnable.getdata());
		map.put(Cconfig.PERFORMANCE_SYS_MEM, sysMemRunnable.getdata());
		map.put(Cconfig.PERFORMANCE_APP_CPU, cpuRunnable.getdata()[0]);
		map.put(Cconfig.PERFORMANCE_SYS_CPU, cpuRunnable.getdata()[1]);
		map.put(Cconfig.PERFORMANCE_SEND_NET, netRunnable.getdata()[1]);
		map.put(Cconfig.PERFORMANCE_RECEIVE_NET, netRunnable.getdata()[0]);
		return map;
	}

	class AppMemRunnable implements Runnable {
		int data;
		CountDownLatch countDownLatch;

		public AppMemRunnable(CountDownLatch countDownLatch) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			data = AndroidInfo.getAppMemSize(udid, packagename);
			countDownLatch.countDown();
		}

		public int getdata() {
			return data;
		}
	}

	class SysMemRunnable implements Runnable {
		float[] data;
		CountDownLatch countDownLatch;

		public SysMemRunnable(CountDownLatch countDownLatch) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			data = AndroidInfo.getSysMemSize(udid);
			countDownLatch.countDown();
		}

		public int getdata() {
			return (int) data[0];
		}
	}

	class CPURunnable implements Runnable {
		float[] data;
		CountDownLatch countDownLatch;

		public CPURunnable(CountDownLatch countDownLatch) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			data = AndroidInfo.getCpuRate(udid, packagename);
			countDownLatch.countDown();
		}

		public float[] getdata() {
			return data;
		}
	}

	class NetRunnable implements Runnable {
		float[] data;
		CountDownLatch countDownLatch;

		public NetRunnable(CountDownLatch countDownLatch) {
			// TODO Auto-generated constructor stub
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			data = showTraficStatistics();
			countDownLatch.countDown();
		}

		public float[] getdata() {
			return data;
		}
	}

	/**
	 * 开启流量统计
	 */
	public boolean openTraficStatistics() {
		float[] trafic = AndroidInfo.getAppTraffic(udid, packagename);
		sTraficRx = trafic[0];
		sTraficTx = trafic[1];
		sTraficRx_wlan = trafic[2];
		sTraficTx_wlan = trafic[3];
		sTraficRx_rmnet = trafic[4];
		sTraficTx_rmnet = trafic[5];
		tempTraficRx = sTraficRx;
		tempTraficTx = sTraficTx;
		tempTraficRx_wlan = sTraficRx_wlan;
		tempTraficTx_wlan = sTraficTx_wlan;
		tempTraficRx_rmnet = sTraficRx_rmnet;
		tempTraficTx_rmnet = sTraficTx_rmnet;
		if (sTraficRx == -1 || sTraficTx == -1 || sTraficRx_wlan == -1 || sTraficTx_wlan == -1 || sTraficRx_rmnet == -1
				|| sTraficTx_rmnet == -1) {
			logger.info("开启流量查询异常");
		} else {
			logger.info("开启流量统计...");
			return false;
		}
		return true;
	}

	/**
	 * 显示从开始流量统计或上一次显示流量统计后的流量计数(需提前开启openTraficStatistics方法)
	 * 
	 * @return 0总接收rx_MB,1总发送tx_MB,2wlan接收rx_MB_wlan,3wlan发送tx_MB_wlan,4移动接收rx_MB_rmnet,5移动发送tx_MB_rmnet
	 *         (出错则返回-1D)
	 */
	public float[] showTraficStatistics() {
		String str = null;
		float[] result = new float[] { -1f, -1f, -1f, -1f, -1f, -1f };
		float[] trafic = AndroidInfo.getAppTraffic(udid, packagename);
		if (tempTraficRx != -1 && tempTraficTx != -1) {
			// DecimalFormat df = new DecimalFormat("0.00");
			float TraficRx = trafic[0];
			float TraficTx = trafic[1];
			float TraficRx_wlan = trafic[2];
			float TraficTx_wlan = trafic[3];
			float TraficRx_rmnet = trafic[4];
			float TraficTx_rmnet = trafic[5];
			float difTraficRx = TraficRx - tempTraficRx;
			float difTraficTx = TraficTx - tempTraficTx;
			float difTraficRx_wlan = TraficRx_wlan - tempTraficRx_wlan;
			float difTraficTx_wlan = TraficTx_wlan - tempTraficTx_wlan;
			float difTraficRx_rmnet = TraficRx_rmnet - tempTraficRx_rmnet;
			float difTraficTx_rmnet = TraficTx_rmnet - tempTraficTx_rmnet;
			str = "下载流量:" + HelperUtil.getFloatDecimal(difTraficRx, 2) + "MB,上传流量:"
					+ HelperUtil.getFloatDecimal(difTraficTx, 2) + "MB" + "(wlan:"
					+ HelperUtil.getFloatDecimal(difTraficRx_wlan, 2) + "↓/"
					+ HelperUtil.getFloatDecimal(difTraficTx_wlan, 2) + "↑,rmnet:"
					+ HelperUtil.getFloatDecimal(difTraficRx_rmnet, 2) + "↓/"
					+ HelperUtil.getFloatDecimal(difTraficTx_rmnet, 2) + "↑)";
			tempTraficRx = TraficRx;
			tempTraficTx = TraficTx;
			tempTraficRx_wlan = TraficRx_wlan;
			tempTraficTx_wlan = TraficTx_wlan;
			tempTraficRx_rmnet = TraficRx_rmnet;
			tempTraficTx_rmnet = TraficTx_rmnet;
			result = new float[] { HelperUtil.getFloatDecimal(difTraficRx, 2),
					HelperUtil.getFloatDecimal(difTraficTx, 2), HelperUtil.getFloatDecimal(difTraficRx_wlan, 2),
					HelperUtil.getFloatDecimal(difTraficTx_wlan, 2), HelperUtil.getFloatDecimal(difTraficRx_rmnet, 2),
					HelperUtil.getFloatDecimal(difTraficTx_rmnet, 2) };
		} else {
//			str = "查询流量异常";
//			logger.info(str);
		}
		return result;
	}

	/**
	 * 显示从开始流量统计到现在的累计流量计数(需提前开启openTraficStatistics方法)
	 * 
	 * @return 0总接收rx_MB,1总发送tx_MB,2wlan接收rx_MB_wlan,3wlan发送tx_MB_wlan,4移动接收rx_MB_rmnet,5移动发送tx_MB_rmnet
	 *         (出错则返回-1D)
	 */
	public float[] showTotalTraficStatistics() {
		String str = null;
		float[] result = new float[] { -1f, -1f, -1f, -1f, -1f, -1f };
		float[] trafic = AndroidInfo.getAppTraffic(udid, packagename);
		if (sTraficRx != -1 && sTraficTx != -1) {
			DecimalFormat df = new DecimalFormat("0.00");
			float TraficRx = trafic[0];
			float TraficTx = trafic[1];
			float TraficRx_wlan = trafic[2];
			float TraficTx_wlan = trafic[3];
			float TraficRx_rmnet = trafic[4];
			float TraficTx_rmnet = trafic[5];
			float difTraficRx = TraficRx - sTraficRx;
			float difTraficTx = TraficTx - sTraficTx;
			float difTraficRx_wlan = TraficRx_wlan - sTraficRx_wlan;
			float difTraficTx_wlan = TraficTx_wlan - sTraficTx_wlan;
			float difTraficRx_rmnet = TraficRx_rmnet - sTraficRx_rmnet;
			float difTraficTx_rmnet = TraficTx_rmnet - sTraficTx_rmnet;
			str = "下载流量:" + HelperUtil.getFloatDecimal(difTraficRx, 2) + "MB,上传流量:"
					+ HelperUtil.getFloatDecimal(difTraficTx, 2) + "MB" + "(wlan:"
					+ HelperUtil.getFloatDecimal(difTraficRx_wlan, 2) + "↓/"
					+ HelperUtil.getFloatDecimal(difTraficTx_wlan, 2) + "↑,rmnet:"
					+ HelperUtil.getFloatDecimal(difTraficRx_rmnet, 2) + "↓/"
					+ HelperUtil.getFloatDecimal(difTraficTx_rmnet, 2) + "↑)";
			result = new float[] { HelperUtil.getFloatDecimal(difTraficRx, 2),
					HelperUtil.getFloatDecimal(difTraficTx, 2), HelperUtil.getFloatDecimal(difTraficRx_wlan, 2),
					HelperUtil.getFloatDecimal(difTraficTx_wlan, 2), HelperUtil.getFloatDecimal(difTraficRx_rmnet, 2),
					HelperUtil.getFloatDecimal(difTraficTx_rmnet, 2) };
		} else {
			str = "查询总流量异常";
			logger.info(str);
		}
		return result;
	}

}
