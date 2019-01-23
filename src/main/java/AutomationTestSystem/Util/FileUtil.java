/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomationTestSystem.Util;

import java.io.File;
import java.io.IOException;

/**
 * date 2012-9-24 18:58:42
 *
 * @author King
 */
public class FileUtil {

	public static boolean isFolderExists(String path) {
		File file = new File(path);
		return (file.exists() && file.isDirectory());
	}

	/**
	 * 创建文件夹（全路径都为文件夹）
	 */
	public static void createFullFolder(String folderPath) {
		File folder = new File(folderPath);
		File parent = folder.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	/**
	 * 创建文件夹（只创建父文件夹）
	 */
	public static void createParentFolder(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}

	/**
	 * 创建文件夹（全路径都为文件夹）
	 */
	public static void checkOrCreateFolder(String folderPath) {
		File folder = new File(folderPath);
		File parent = folder.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	public static boolean isFileExists(String path) {
		File file = new File(path);
		return (file.exists() && !file.isDirectory());
	}

	public static void createFile(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void checkOrCreateFile(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// checkOrCreateFile("db/");
		createParentFolder("db/createPathFolder/uuu");
		System.err.println(isFolderExists("db/isFolderExists/yy"));
	}
}
