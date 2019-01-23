package AutomationTestSystem.Tray;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author King
 * @date 2018年8月6日 10:34:22
 */
@SuppressWarnings("restriction")
public class OnlyFeelUtil {
	/**
	 * IPV4地址块的正则表达式
	 */
	public static final String IPV4_BLOCK_REGEX = "(2[0-4]\\d|25[0-5]|[01]?\\d?\\d)";

	/**
	 * IPV4地址的正则表达式
	 */
	public static final String IPV4_REGEX = "(" + IPV4_BLOCK_REGEX + "\\.){3}" + IPV4_BLOCK_REGEX;

	/**
	 * Mac地址的正则表达式，以:分隔
	 */
	public static final String MAC_REGEX = "([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}";

	/**
	 * CSV文件中需要转义的字符集正则表达式
	 */
	public static final String CSV_REGEX = ".*([\"|\\,|\\n|\\r]|(\\r\\n)|(\\n\\r)).*";

	/**
	 * 从目标数组中查找指定的元素 由于Arrays.binarySearch只比较了==，对equals的仍然认为是不同，所以添加此方法
	 *
	 * @param objects
	 *            目标数组
	 * @param object
	 *            指定元素
	 * @return 指定元素的序号
	 */
	public static int searchFromArray(Object[] objects, Object object) {

		int pos = -1;
		int index = 0;

		for (Object o : objects) {
			if ((o == null && object == null) || (o == object) || (o != null && object != null && o.equals(object))) {
				pos = index;
				break;
			}
			index++;
		}

		return pos;
	}

	/**
	 * 判断相应字符串是否为空，不允许出现空字符
	 *
	 * @param object
	 *            目标字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(Object object) {
		return isEmpty(object, false);
	}

	/**
	 * 判断相应字符串是否为空
	 *
	 * @param object
	 *            目标字符串
	 * @param spaceAllowed
	 *            是否允许出现空字符
	 * @return 是否为空
	 */
	public static boolean isEmpty(Object object, boolean spaceAllowed) {
		if (object == null) {
			return true;
		} else {
			String string = spaceAllowed ? object.toString() : object.toString().trim();
			return string.isEmpty() || string.equalsIgnoreCase("null");
		}
	}

	/**
	 * 校验IP地址格式
	 *
	 * @param ip
	 *            IP地址
	 * @return IP是否合法
	 */
	public static boolean checkIPV4(String ip) {
		return Pattern.matches(IPV4_REGEX, ip);
	}

	/**
	 * 检验Mac地址格式
	 *
	 * @param mac
	 *            Mac地址
	 * @return Mac是否合法
	 */
	public static boolean checkMac(String mac) {
		return Pattern.matches(MAC_REGEX, mac);
	}

	/**
	 * 检验Mac地址格式
	 *
	 * @param mac
	 *            Mac地址
	 * @param split
	 *            分隔字符
	 * @return Mac是否合法
	 */
	public static boolean checkMac(String mac, char split) {
		return Pattern.matches(MAC_REGEX.replace(':', split), mac);
	}

	/**
	 * 将16进制字节组解码成字符串，字节之间无分隔符，默认字符集，适用于所有字符（包括中文）
	 *
	 * @param hexCode
	 *            16进制字节组
	 * @return 解码后的字符串
	 */
	public static String hexToString(String hexCode) {
		return hexToString(hexCode, null);
	}

	/**
	 * 将16进制字节组解码成字符串，字节之间无分隔符，适用于所有字符（包括中文）
	 *
	 * @param hexCode
	 *            16进制字节组
	 * @param charsetName
	 *            字符集名称
	 * @return 解码后的字符串
	 */
	public static String hexToString(String hexCode, String charsetName) {
		return hexToString(hexCode, null, charsetName);
	}

	/**
	 * 将16进制字节组解码成字符串，适用于所有字符（包括中文）
	 *
	 * @param hexCode
	 *            16进制字节组
	 * @param split
	 *            字节之间的分隔符
	 * @param charsetName
	 *            字符集名称
	 * @return 解码后的字符串
	 */
	public static String hexToString(String hexCode, String split, String charsetName) {
		if (hexCode == null || hexCode.isEmpty()) {
			return hexCode;
		}

		if (!isHexString(hexCode, split)) {
			throw new IllegalArgumentException("Invalid hexCode:" + hexCode);
		}

		byte[] bytes = hexToBytes(hexCode, split);
		String ret = null;

		if (charsetName == null) {
			ret = new String(bytes);
		} else {
			try {
				ret = new String(bytes, charsetName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}

	/**
	 * 将字符串解析成16进制字节组，无分隔符，默认字符集，适用于所有字符（包括中文）
	 *
	 * @param text
	 *            字符串
	 * @return 16进制字节组
	 */
	public static String stringToHex(String text) {
		return stringToHex(text, null);
	}

	/**
	 * 将字符串解析成16进制字节组，无分隔符，适用于所有字符（包括中文）
	 *
	 * @param text
	 *            字符串
	 * @param charsetName
	 *            字符集名称
	 * @return 16进制字节组
	 */
	public static String stringToHex(String text, String charsetName) {
		return stringToHex(text, null, charsetName);
	}

	/**
	 * 将字符串解析成16进制字节组，适用于所有字符（包括中文）
	 *
	 * @param text
	 *            字符串
	 * @param split
	 *            字节组中的分隔符
	 * @param charsetName
	 *            字符集名称
	 * @return 16进制字节组
	 */
	public static String stringToHex(String text, String split, String charsetName) {
		byte[] bytes;

		if (text == null || text.isEmpty()) {
			return text;
		}

		if (charsetName == null) {
			bytes = text.getBytes();
		} else {
			try {
				bytes = text.getBytes(charsetName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}

		return bytesToHex(bytes, split);
	}

	/**
	 * 将普通字符串编码为16进制表示的int码字符串，适用于所有字符（包括中文）
	 *
	 * @param text
	 *            普通字符串
	 * @return 16进制表示的int码字符串
	 */
	public static String stringToIntHex(String text) {
		StringBuilder hexes = new StringBuilder(text.length() * 2);
		String hex;

		for (char ch : text.toCharArray()) {
			hex = Integer.toHexString(ch);

			if (hex.length() % 2 != 0) {
				hexes.append(0);
			}

			hexes.append(hex);
		}

		return hexes.toString();
	}

	/**
	 * 目标字符串是否为16进制字节码组，字节码之间无分隔符
	 *
	 * @param str
	 *            目标字符串
	 * @return 是否为16进制字节码组
	 */
	public static boolean isHexString(String str) {
		return isHexString(str, "");
	}

	/**
	 * 目标字符串是否为16进制字节码组
	 *
	 * @param str
	 *            目标字符串
	 * @param split
	 *            字节之间的分隔符
	 * @return 是否为16进制字节码组
	 */
	public static boolean isHexString(String str, String split) {
		if (str == null) {
			return false;
		}

		split = split == null ? "" : split;
		String regex = "([0-9A-Fa-f]{2}" + split + ")*[0-9A-Fa-f]{2}";
		return Pattern.matches(regex, str);
	}

	/**
	 * 将字节数组转换成16进制字符串
	 *
	 * @param bytes
	 *            目标字节数组
	 * @return 转换结果
	 */
	public static String bytesToHex(byte bytes[]) {
		return bytesToHex(bytes, null);
	}

	/**
	 * 将字节数组转换成16进制字符串
	 *
	 * @param bytes
	 *            目标字节数组
	 * @param split
	 *            16进制字符串的分隔符
	 * @return 转换结果
	 */
	public static String bytesToHex(byte bytes[], String split) {
		return bytesToHex(bytes, 0, bytes.length, split);
	}

	/**
	 * 将字节数组中指定区间的字节转换成16进制字符串
	 *
	 * @param bytes
	 *            目标字节数组
	 * @param start
	 *            起始位置（包括该位置）
	 * @param end
	 *            结束位置（不包括该位置）
	 * @param split
	 *            16进制字符串的分隔符
	 * @return 转换结果
	 */
	public static String bytesToHex(byte bytes[], int start, int end, String split) {
		StringBuilder sb = new StringBuilder();
		boolean hasSplit = split != null && !split.isEmpty();
		end = Math.min(end, bytes.length);

		for (int i = start; i < end; i++) {
			if (hasSplit && i > start) {
				sb.append(split);
			}

			sb.append(byteToHex(bytes[i]));
		}

		return sb.toString();
	}

	/**
	 * 将单个字节码转换成16进制字符串
	 *
	 * @param b
	 *            目标字节
	 * @return 转换结果
	 */
	public static String byteToHex(byte b) {
		String hex = Integer.toHexString(0xFF & b | 0x00);
		return b >= 0 && b <= 15 ? '0' + hex : hex;
	}

	/**
	 * 将16进制字节码转换成字节数组
	 *
	 * @param hex
	 *            16进制字节码
	 * @return 字节数组
	 */
	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex, null);
	}

	/**
	 * 将16进制字节码转换成字节数组
	 *
	 * @param hex
	 *            16进制字节码
	 * @param split
	 *            16进制字节码的分隔符
	 * @return 字节数组
	 */
	public static byte[] hexToBytes(String hex, String split) {
		if (hex == null) {
			return null;
		} else if (hex.isEmpty()) {
			return new byte[0];
		} else if (!isHexString(hex, split)) {
			throw new IllegalArgumentException("Invalid hex:" + hex);
		} else {
			if (split != null && !split.isEmpty()) {
				hex = hex.replaceAll(split, "");
			}

			int length = hex.length();
			ByteArrayOutputStream baos = new ByteArrayOutputStream(length / 2);

			for (int i = 0; i < length; i += 2) {
				baos.write(Integer.parseInt(hex.substring(i, i + 2), 16));
			}

			return baos.toByteArray();
		}
	}

	/**
	 * 将可序列化的Java对象转化为16进制字符串
	 *
	 * @param obj
	 *            可序列化的Java对象
	 * @return 16进制字符串
	 * @throws IOException
	 */
	public static String objectToHexString(Object obj) throws IOException {
		String str = null;

		if (obj != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream zipOut = new BufferedOutputStream(baos);
			ObjectOutputStream os = new ObjectOutputStream(zipOut);
			os.writeObject(obj);
			os.flush();
			os.close();
			str = bytesToHex(baos.toByteArray());
		}

		return str;
	}

	/**
	 * 将16进制字符串转化为可序列化的Java对象
	 *
	 * @param text
	 *            16进制字符串
	 * @return 可序列化的Java对象
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object hexStringToObject(String text) throws IOException, ClassNotFoundException {
		Object obj = null;

		if (text != null) {
			byte byteArr[] = hexToBytes(text);
			ByteArrayInputStream baIn = new ByteArrayInputStream(byteArr);
			BufferedInputStream bIn = new BufferedInputStream(baIn);
			ObjectInputStream objIn = new ObjectInputStream(bIn);
			obj = objIn.readObject();
			objIn.close();
		}

		return obj;
	}

	/**
	 * 写入文本到系统剪切板
	 *
	 * @param text
	 *            待写入的文本
	 */
	public static void writeTextToSystemClipboard(String text) {
		Transferable transferable = new StringSelection(text);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transferable, null);
	}

	/**
	 * 写入文件到系统剪切板
	 *
	 * @param fileList
	 *            待写入的文件列表
	 */
	public static void writeFilesToSystemClipboard(final List<File> fileList) {
		writeDataToSystemClipboard(fileList, DataFlavor.javaFileListFlavor);
	}

	/**
	 * 写入图像到系统剪切板
	 *
	 * @param image
	 *            待写入的图像
	 */
	public static void writeImageToSystemClipboard(final Image image) {
		writeDataToSystemClipboard(image, DataFlavor.imageFlavor);
	}

	/**
	 * 写入数据到系统剪切板
	 *
	 * @param data
	 *            待写入的数据
	 * @param dataFlavor
	 *            数据元信息
	 */
	public static void writeDataToSystemClipboard(final Object data, final DataFlavor dataFlavor) {
		Transferable transferable = new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { dataFlavor };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return dataFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor)) {
					return data;
				} else {
					throw new UnsupportedFlavorException(flavor);
				}
			}
		};

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transferable, null);
	}

	/**
	 * 获取系统剪切板中的文本
	 *
	 * @return 系统剪切板中的文本
	 */
	public static String getSystemClipboardText() {
		Object data = getSystemClipboardData(DataFlavor.stringFlavor);
		return data == null ? null : data.toString();
	}

	/**
	 * 获取系统剪切板中的文件
	 *
	 * @return 系统剪切板中的文件
	 */
	@SuppressWarnings("unchecked")
	public static List<File> getSystemClipboardFileList() {
		return (List<File>) getSystemClipboardData(DataFlavor.javaFileListFlavor);
	}

	/**
	 * 获取系统剪切板中的图像
	 *
	 * @return 系统剪切板中的图像
	 */
	public static Image getSystemClipboardImage() {
		return (Image) getSystemClipboardData(DataFlavor.imageFlavor);
	}

	/**
	 * 获取系统剪切板中的数据
	 *
	 * @param flavor
	 *            数据元信息
	 * @return 系统剪切板中的数据
	 */
	public static Object getSystemClipboardData(DataFlavor flavor) {
		Object ret = null;
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = sysClip.getContents(null);

		if (transferable != null && transferable.isDataFlavorSupported(flavor)) {
			try {
				ret = transferable.getTransferData(flavor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ret;
	}

	/**
	 * 获取鼠标的位置
	 *
	 * @return 鼠标位置
	 */
	public static Point getMouseLocation() {
		return MouseInfo.getPointerInfo().getLocation();
	}

	/**
	 * 判断字符是否为中文
	 *
	 * @param ch
	 *            目标字符
	 * @return 是否为中文
	 */
	public static boolean isChinese(char ch) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);

		if (block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || block == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || block == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| block == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || block == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符是否为中文，String类型
	 *
	 * @param text
	 *            目标字符，只取该第一个字符进行判断
	 * @return 是否为中文
	 */
	public static boolean isChinese(String text) {
		return isChinese(text.charAt(0));
	}

	/**
	 * 将基本类型转化为对应的非基本类型
	 *
	 * @param classes
	 *            可能含有基本类型的Class数组
	 * @return 不含有基本类型的Class数组
	 */
	public static Class<?>[] createUnprimitiveClasses(Class<?>[] classes) {
		Class<?>[] unPrimitiveClass = new Class<?>[classes.length];
		Class<?> clazz;

		for (int i = 0; i < unPrimitiveClass.length; i++) {
			clazz = classes[i];

			if (clazz.isAssignableFrom(int.class)) {
				unPrimitiveClass[i] = Integer.class;
			} else if (clazz.isAssignableFrom(long.class)) {
				unPrimitiveClass[i] = Long.class;
			} else if (clazz.isAssignableFrom(short.class)) {
				unPrimitiveClass[i] = Short.class;
			} else if (clazz.isAssignableFrom(byte.class)) {
				unPrimitiveClass[i] = Byte.class;
			} else if (clazz.isAssignableFrom(char.class)) {
				unPrimitiveClass[i] = Character.class;
			} else if (clazz.isAssignableFrom(double.class)) {
				unPrimitiveClass[i] = Double.class;
			} else if (clazz.isAssignableFrom(float.class)) {
				unPrimitiveClass[i] = Float.class;
			} else if (clazz.isAssignableFrom(boolean.class)) {
				unPrimitiveClass[i] = Boolean.class;
			} else {
				unPrimitiveClass[i] = clazz;
			}
		}

		return unPrimitiveClass;
	}

	/**
	 * 将普通字符串转换为符合CSV格式的字符串
	 *
	 * @param text
	 *            普通字符串
	 * @return 符合CSV格式的字符串
	 */
	public static String toCSVString(String text) {
		text = text.replaceAll("\"", "\"\"");
		Pattern p = Pattern.compile(CSV_REGEX, Pattern.DOTALL);
		Matcher m = p.matcher(text);
		if (m.matches()) {
			text = "\"" + text + "\"";
		}
		return text;
	}

	/**
	 * 按字节数获取子字符串
	 *
	 * @param text
	 *            原字符串
	 * @param byteBeginIndex
	 *            开始位置
	 * @param byteEndIndex
	 *            结束位置
	 * @param halfChinese
	 *            是否包括取到一半的汉字
	 * @return 子字符串
	 */
	public static String substringByByte(String text, int byteBeginIndex, int byteEndIndex, boolean halfChinese) {
		String result = "";
		int charLength = 0;
		int tempCharBeginIndex = 0;
		int tempCharEndIndex = 0;
		int charBeginIndex = -1;
		int charEndIndex = -1;

		if (byteEndIndex > byteBeginIndex && byteBeginIndex >= 0) {
			for (int i = 0; i < text.length(); i++) {
				charLength = text.substring(i, i + 1).getBytes().length;
				tempCharBeginIndex = tempCharEndIndex;
				tempCharEndIndex += charLength;

				if (byteBeginIndex >= tempCharBeginIndex && byteBeginIndex < tempCharEndIndex) {
					charBeginIndex = (byteBeginIndex > tempCharBeginIndex && !halfChinese) ? i + 1 : i;
				}

				if (byteEndIndex >= tempCharBeginIndex && byteEndIndex < tempCharEndIndex) {
					charEndIndex = (byteEndIndex > tempCharBeginIndex && halfChinese) ? i + 1 : i;
					break;
				}
			}

			charEndIndex = charEndIndex == -1 ? (charBeginIndex == -1 ? 0 : text.length()) : charEndIndex;
			charBeginIndex = charBeginIndex == -1 ? 0 : charBeginIndex;

			if (charEndIndex > charBeginIndex) {
				result = text.substring(charBeginIndex, charEndIndex);
			}
		}

		return result;
	}

	/**
	 * 压缩字符串
	 *
	 * @param text
	 *            目标字符串
	 * @return 压缩后的字符串
	 * @throws IOException
	 */
	public static String compress(String text) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
		GZIPOutputStream gzip = new GZIPOutputStream(output);
		gzip.write(text.getBytes("UTF-8"));
		gzip.close();
		String result = new BASE64Encoder().encode(output.toByteArray());
		output.close();
		return result;
	}

	/**
	 * 解压字符串
	 *
	 * @param text
	 *            目标字符串
	 * @return 解压后的字符串
	 * @throws IOException
	 */
	public static String decompress(String text) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
		ByteArrayInputStream input = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(text));
		GZIPInputStream gzip = new GZIPInputStream(input);
		byte[] buffer = new byte[1024];
		int number;
		while ((number = gzip.read(buffer)) != -1) {
			output.write(buffer, 0, number);
		}
		gzip.close();
		input.close();
		String result = new String(output.toString("UTF-8"));
		output.close();
		return result;
	}

	/**
	 * 判断当前操作系统是否为Windows
	 *
	 * @return 是否为Windows
	 */
	public static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().startsWith("windows");
	}
}