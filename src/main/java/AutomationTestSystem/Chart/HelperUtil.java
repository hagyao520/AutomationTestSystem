package AutomationTestSystem.Chart;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperUtil {
	static Logger logger = LoggerFactory.getLogger(HelperUtil.class);

	/**
	 * 保留小数
	 * 
	 * @param value
	 * @param num   保留几位
	 * @return
	 */
	public static double getDoubleDecimal(double value, int num) {
		String decimal = "";
		for (int i = 0; i < num; i++) {
			decimal += "0";
		}
		DecimalFormat df = new DecimalFormat("0." + decimal);
		return Double.parseDouble(df.format(value));
	}

	/**
	 * 保留小数
	 * 
	 * @param value
	 * @param num   保留几位
	 * @return
	 */
	public static float getFloatDecimal(float value, int num) {
		String decimal = "";
		for (int i = 0; i < num; i++) {
			decimal += "0";
		}
		DecimalFormat df = new DecimalFormat("0." + decimal);
		return Float.parseFloat(df.format(value));
	}

	/**
	 * 设置剪贴板内容为图片
	 * 
	 * @param image
	 */
	public static void setSysClipboardPic(Image image) {
		ImageSelection imgSel = new ImageSelection(image); // 创建能传输指定 String 的 Transferable
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel, null);
	}

	private static class ImageSelection implements Transferable {
		private Image image;

		public ImageSelection(Image image) {
			this.image = image;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { DataFlavor.imageFlavor };
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return DataFlavor.imageFlavor.equals(flavor);
		}

		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
			if (!DataFlavor.imageFlavor.equals(flavor)) {
				throw new UnsupportedFlavorException(flavor);
			}
			return image;
		}
	}

	/**
	 * 设置剪贴板内容为文字
	 * 
	 * @param text
	 */
	public static void setSysClipboardText(String text) {
		StringSelection ss = new StringSelection(text);// 创建能传输指定 String 的 Transferable
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}

	/**
	 * 只能输入数字
	 * 
	 * @return
	 */
	public static KeyListener Listener_inputNumbers() {
		return new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if ((e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9)
						|| e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB
						|| e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE
						|| e.getKeyChar() == KeyEvent.VK_LEFT || e.getKeyChar() == KeyEvent.VK_RIGHT
						|| e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					return;
				} else {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		};
	}

	/**
	 * 字符串相似度比较,编辑距离算法
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static float StringSimilarity(String str1, String str2) {
		// 计算两个字符串的长度。
		int len1 = str1.length();
		int len2 = str2.length();
		// 建立上面说的数组，比字符长度大一个空间
		int[][] dif = new int[len1 + 1][len2 + 1];
		// 赋初值，步骤B。
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		// 计算两个字符是否一样，计算左上的值
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 取三个值中最小的
				dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1);
			}
		}
		// System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");
		// 取数组右下角的值，同样不同位置代表不同字符串的比较
		// System.out.println("差异步骤："+dif[len1][len2]);
		// 计算相似度
		float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
		// System.out.println("相似度："+similarity);
		return similarity;
	}

	// 得到最小值
	private static int min(int... is) {
		int min = Integer.MAX_VALUE;
		for (int i : is) {
			if (min > i) {
				min = i;
			}
		}
		return min;
	}

	/**
	 * 验证邮件是否正确
	 * 
	 * @param emailstr emailA;emailB;emailC格式
	 * @return
	 */
	public static boolean checkEmail(String emailstr) {
		if (emailstr.equals(""))
			return true;
		// 验证邮箱的正则表达式
		String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		// p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
		// w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
		// [a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
		// [.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
		// p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
		emailstr = emailstr.replace(",", ";");
		String[] emails = emailstr.split(";");
		for (String email : emails) {
			if (!email.trim().matches(format)) {
				logger.info("email format is not correct: " + email);
				return false;
			}
		}
		return true;
	}

	/**
	 * 保留最近多少份文件
	 * 
	 * @param dir       需要清除的路径
	 * @param namestart 需要清除文件名的开头
	 * @param counts    需要保留的份数
	 * @return
	 */
	public static int clearFiles(File dir, String namestart, int counts) {
		int count = 0;
		if (dir.exists() && dir.isDirectory()) {
			List<File> filelists = new ArrayList<>(Arrays.asList(dir.listFiles()));
			Iterator<File> iter = filelists.iterator();
			while (iter.hasNext()) {
				if (!iter.next().getName().startsWith(namestart))
					iter.remove();
			}
			if (filelists.size() > counts) {
				for (int i = 0; i < filelists.size() - counts; i++) {
					if (filelists.get(i).isFile()) {
						filelists.get(i).delete();
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * 比较两个List是否相等
	 * 
	 * @param listA
	 * @param listB
	 * @return
	 */
	public static boolean equals(List<?> listA, List<?> listB) {
		if (listA == listB)
			return true;
		if (listA == null || listB == null)
			return false;
		if (listA.containsAll(listB) && listB.containsAll(listA))
			return true;
		return false;
	}

	/**
	 * 判断格式是否符合 key,value;key,value;
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean check_format(String txt) {
		Pattern TXT_format = Pattern.compile("^.+,.+$");
		String[] strings = txt.replaceAll("\n", "").split(";");
		for (String str : strings) {
			if (!TXT_format.matcher(str).matches()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String strNum) {
		if (strNum == null || strNum.endsWith("."))
			return false;
		return strNum.matches("^-?[0-9]+([.][0-9]+){0,1}$");
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String strNum) {
		if (strNum == null)
			return false;
		return strNum.matches("^(0|[1-9][0-9]*|-[1-9][0-9]*)$");
	}

	/**
	 * 判断是否为小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String strNum) {
		if (strNum == null)
			return false;
		return strNum.matches("^-?[0-9]+.[0-9]+$");
	}

	/**
	 * 判断是否含有中文
	 * 
	 * @param strName
	 * @return
	 */
	public static final boolean hasChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 功能：检测当前URL是否可连接或是否有效, 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
	 * 
	 * @param urlStr 指定URL网络地址
	 * @return URL
	 */
	public static boolean URLisConnect(String urlStr) {
		URL url = null;
		HttpURLConnection con;
		int state = -1;
		int counts = 0;
		if (urlStr == null || urlStr.length() <= 0) {
			return false;
		}
		while (counts < 5) {
			try {
				url = new URL(urlStr);
				con = (HttpURLConnection) url.openConnection();
				state = con.getResponseCode();
				if (state == 200) {
					logger.info("URL=" + urlStr + " is available!");
					return true;
				}
				logger.info(counts + "= " + state);
			} catch (Exception e) {
				counts++;
				logger.error("URL is unavailable,counts=" + counts, e);
				continue;
			}
		}
		return false;
	}

	/**
	 * MD5生成
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toString().toLowerCase();
		} catch (Exception e) {
			logger.error("Exception", e);
			return null;
		}
	}

	/**
	 * 生成随机数
	 * 
	 * @param min
	 * @param max
	 * @param n
	 * @return
	 */
	public static int[] random(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		List<Integer> list = new ArrayList<>();// int[]默认0,会导致随机出0被丢弃
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int oldnum : list) {
				if (oldnum == num) {
					flag = false;
					break;
				}
			}
			if (flag) {
				list.add(num);
				count++;
			}
		}
		int[] result = new int[n];
		for (int j = 0; j < n; j++) {
			result[j] = list.get(j);
		}
		return result;
	}

	/**
	 * AES加密
	 * 
	 * @param sSrc
	 * @param sKey
	 * @return
	 */
	public static String AESEncrypt(String sSrc, String sKey) {
		if (sKey == null) {
			System.out.println("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.println("Key长度不是16位");
			return null;
		}
		String result = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] raw = md.digest(sKey.getBytes("utf-8"));
			// byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			result = Base64.getEncoder().encodeToString(encrypted);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 为了和服务器nodejs加密结果一直 必须将密钥先进行md5校验
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String result=bytesToHexString(encrypted);
		logger.info("Str=" + sSrc + ",key=" + sKey + ",AESEncrypt=" + result);
		return result;// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param filepath
	 * @param content
	 * @param isappend
	 * @param split    true则以\n为分隔符,每行以/r/n结尾
	 */
	public static void file_write_all(String filepath, String content, boolean isappend, boolean split) {
		FileOutputStream fileOutputStream = null;
		try {
			byte[] initline;
			if (split) {
				String[] spiltstr = content.split("\n");
				fileOutputStream = new FileOutputStream(filepath, isappend);
				for (String line : spiltstr) {
					line = line + "\r\n";
					initline = line.getBytes("UTF-8");
					fileOutputStream.write(initline);
				}
			} else {
				fileOutputStream = new FileOutputStream(filepath, isappend);
				initline = content.getBytes("UTF-8");
				fileOutputStream.write(initline);
			}
			fileOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Exception", e);
			}
		}
	}

	/**
	 * 将字符串写入指定文件,一行,不换行
	 * 
	 * @param filepath
	 * @param content
	 * @param isappend
	 */
	public static void file_write_line(String filepath, String content, boolean isappend) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filepath, isappend);
			// content=content+"\r\n";
			byte[] initContent = content.getBytes("UTF-8");
			fileOutputStream.write(initContent);
			fileOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Exception", e);
			}
		}
	}

	/**
	 * 替换文件中内容
	 * 
	 * @param file
	 * @param source
	 * @param content
	 */
	public static void file_replace_content(String filepath, String source, String content) {
		File file = new File(filepath);
		String text = file_read_all(file.getAbsolutePath()).toString();
		text = text.replace(source, content);
		file_write_all(file.getAbsolutePath(), text, false, false);
	}

	/**
	 * 读取特定字符串之间的内容
	 * 
	 * @param file
	 * @param startstr 开始字符串
	 * @param endstr   结束字符串
	 * @param which    第几组,如果无则返回null
	 * @return
	 */
	public static String file_read_content(String filepath, String startstr, String endstr, int which) {
		File file = new File(filepath);
		String str = null;
		ArrayList<String> list = new ArrayList<>();
		String text = file_read_all(file.getAbsolutePath()).toString();
		int pos = 0;
		while ((pos = text.indexOf(startstr, pos)) >= 0) {
			int temp = text.indexOf(endstr, pos + startstr.length());
			String target = text.substring(pos + startstr.length(), temp);
			pos = temp + endstr.length();
			list.add(target);
		}
		if (list.size() > 0 && list.size() >= which) {
			if (which < 0) {
				str = list.get(list.size() - 1);
			} else {
				str = list.get(which);
			}
		}
		return str;
	}

	/**
	 * 读取文件所有内容
	 * 
	 * @param filepath
	 * @return
	 */
	public static StringBuffer file_read_all(String filepath) {
		BufferedReader reader = null;
		StringBuffer strbuf = new StringBuffer();
		try {
			File file = new File(filepath);
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				strbuf.append(tempString + "\n");
			}
		} catch (IOException e) {
			logger.error("Exception", e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Exception", e);
			}
		}
		return strbuf;
	}

	/**
	 * 将\r\n,\n转化为<br>
	 * 
	 * @param s
	 * @return
	 */
	public static String linebreak2br(String s) {
		s = s.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>").replaceAll("\r", "<br>");
		return s;
	}

	/**
	 * 得到字符串出现次数
	 * 
	 * @param str
	 * @param sub
	 * @return
	 */
	public static int getStringShowCount(String str, String sub) {
		int index = 0;
		int count = 0;
		while ((index = str.indexOf(sub)) != -1) {
			str = str.substring(index + sub.length());
			count++;
		}
		return count;
	}

	/**
	 * 格式化json
	 * 
	 * @param content
	 * @return
	 */
	public static String formatJson(String content) {
		if (content != null) {
			StringBuffer sb = new StringBuffer();
			int index = 0;
			int count = 0;
			while (index < content.length()) {
				char ch = content.charAt(index);
				if (ch == '{' || ch == '[') {
					sb.append(ch);
					sb.append('\n');
					count++;
					for (int i = 0; i < count; i++) {
						// sb.append('\t');
					}
				} else if (ch == '}' || ch == ']') {
					sb.append('\n');
					count--;
					for (int i = 0; i < count; i++) {
						// sb.append('\t');
					}
					sb.append(ch);
				} else if (ch == ',') {
					sb.append(ch);
					sb.append('\n');
					for (int i = 0; i < count; i++) {
						// sb.append('\t');
					}
				} else {
					sb.append(ch);
				}
				index++;
			}
			return sb.toString();
		}
		return "formatJson content is null...";
	}

	/**
	 * 把格式化的json紧凑
	 * 
	 * @param content
	 * @return
	 */
	public static String compactJson(String content) {
		String regEx = "[\t\n]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		return m.replaceAll("").trim();
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath 文件夹完整绝对路径
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			logger.error("Excepiton", e);
		}
	}

	/**
	 * 删除指定文件夹下所有文件
	 * 
	 * @param path文件夹完整绝对路径
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

}
