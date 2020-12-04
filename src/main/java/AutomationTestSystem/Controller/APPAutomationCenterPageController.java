package AutomationTestSystem.Controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author King
 * @date 2018年8月1日 18:18:074
 */
public class APPAutomationCenterPageController {
	/**
     * 从网络Url中下载文件
     * @param urlStr
     * @param savePath
     * @param fileName
     * @throws IOException
     */  
    public static String DownLoadFromUrl(String DownloadUrl,String SavePath,String FileName) throws IOException{  
    	
    	System.out.println("开始下载文件:"+DownloadUrl);   
        URL url = new URL(DownloadUrl);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
        //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
 
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      

        //文件保存位置  
        File saveDir = new File(SavePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
//        System.out.println(getData.toString()+"--");
        File file = new File(saveDir+"\\"+FileName); 
//        System.out.println(File.separator);
        FileOutputStream fos = new FileOutputStream(file);
       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
 
        System.out.println("文件下载成功，保存路径为:"+file.getPath()+"");   

        String FilePath =file.getPath();
		return FilePath;
    }  
 
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */  
    public static byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();  
        return bos.toByteArray();    
    }    
 
	public static String DownLoadFromUrlParam(String DownloadUrl,String SavePath,String Param) {
		String FilePath=null;
        URL url = null;
        FileOutputStream fileOutputStream  =null;
        try {
            url = new URL(DownloadUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
            //设置超时间为3秒  
            conn.setConnectTimeout(3*1000);  
            //防止屏蔽程序抓取而返回403错误  
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
            
            //得到输入流  
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String fileName = DownloadUrl.substring(DownloadUrl.lastIndexOf(Param)).replace(Param, "");
//            String fileName = URLDecoder.getParamByUrl(DownloadUrl, Param);
            if("LekeU.apk".equals(fileName)){
            	fileName="LekeU.apk";
            	fileOutputStream = new FileOutputStream(new File(SavePath+fileName));
            }else if("LekeW.apk".equals(fileName)){
            	fileName="LekeW.apk";
            	fileOutputStream = new FileOutputStream(new File(SavePath+fileName));
            }else{
            	//蒲公英保存地址
                fileOutputStream = new FileOutputStream(new File(SavePath+fileName));
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream();
 
            byte[] buffer = new byte[1024];
            int length;
 
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
//            FilePath =SavePath+fileName.replace("/", "")+".apk";
            FilePath =SavePath+fileName;
            System.out.println("文件下载成功，保存路径为："+FilePath+""); 
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FilePath;
    }
    
    public static void main(String[] args) {  
        try{  
//        	String str = "2018-08-27";  
//    		String newStr = str.replaceAll("-", "_");  
//    		System.out.println(newStr);   
//            DownLoadFromUrl("http://oih492dqz.bkt.clouddn.com/lkkm_2.1.0_31_"+newStr+"_android.apk",  
//                    "src/main/resources/app/","LekeU.apk"); 
//            DownLoadFromUrl("http://img.bimg.126.net/photo/DCi7Q__VN3NJ_63cq7sosA==/3439905690381537164.jpg",  
//                    "D:\\","百度.png"); 
            
//            String DownloadUrl = "http://app-global.pgyer.com/4868b7f9f9b341049771596534cc64bd.apk?attname=LekeW.apk&sign=b45e80059a769a7141ca78ae2f4227ae&t=5be26ec0";
//            String SavePath="D:\\";
//            DownLoadFromUrlParam(DownloadUrl,SavePath,"attname");
            
            String DownloadUrl = "http://oss.pgyer.com/1b68452cf6bab37a5752f5d39a713fa1.apk?auth_key=1560501174-add470b09f5701aed4990f603f784c9d-0-7a451b56e41034567d2226154e2d9d11&response-content-disposition=attachment%3B+filename%3Dflutter_wanandroid.apk";
            String SavePath="D:\\";
            DownLoadFromUrlParam(DownloadUrl,SavePath,"%3D");
        }catch (Exception e) {  
        	e.printStackTrace();
            System.out.println("失败");
        }  
    }
}