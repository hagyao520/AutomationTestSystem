package Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 通过ApkInfo 里的applicationIcon从APK里解压出icon图片并存放到磁盘上
 */
public class ApkIconUtil {

    /**
     * 从指定的apk文件里获取指定file的流
     *
     * @param apkPath
     * @param fileName
     * @return
     */
    @SuppressWarnings("resource")
	public static InputStream extractFileFromApk(String apkPath, String fileName) {
        try {
            ZipFile zFile = new ZipFile(apkPath);
            ZipEntry entry = zFile.getEntry(fileName);
            entry.getComment();
            entry.getCompressedSize();
            entry.getCrc();
            entry.isDirectory();
            entry.getSize();
            entry.getMethod();
            InputStream stream = zFile.getInputStream(entry);
            return stream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从指定的apk文件里解压出icon图片并存放到指定的磁盘上
     *
     * @param apkPath    apk文件路径
     * @param fileName   apk的icon
     * @param outputPath 指定的磁盘路径
     * @throws Exception
     */
    public static void extractFileFromApk(String apkPath, String fileName, String outputPath) throws Exception {
        InputStream is = extractFileFromApk(apkPath, fileName);

        File file = new File(outputPath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file), 1024);
        byte[] b = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        while (bis.read(b) != -1) {
            bos.write(b);
        }
        bos.flush();
        is.close();
        bis.close();
        bos.close();
    }

    /**
     * demo 获取apk文件的icon并写入磁盘指定位置
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String apkPath = "C:\\Users\\King-liu\\Desktop\\LekeU.apk";
           /* if (args.length > 0) {
                apkPath = args[0];
            }*/
            ApkInfo apkInfo = new ApkUtil().getApkInfo(apkPath);
            System.out.println(apkInfo);
            String now = apkInfo.getApplicationLable();
            extractFileFromApk(apkPath, apkInfo.getApplicationIcon(), "C:\\Users\\King-liu\\Desktop\\" + now + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}