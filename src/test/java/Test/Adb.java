package Test;

import java.io.*;

public class Adb {

	public static void main(String[] args) throws IOException, InterruptedException{
		GetMemory("com.lekelian.lkkm");
	}
	
	public static String GetMemory(String packageName) throws IOException, InterruptedException {

	      String str3=null;
	      Runtime runtime = Runtime.getRuntime();
	      Process proc = runtime.exec("adb shell dumpsys meminfo "+packageName);
	      try {

	          if (proc.waitFor() != 0) {
	              System.err.println("exit value = " + proc.exitValue());
	          }
	          BufferedReader in = new BufferedReader(new InputStreamReader(
	                  proc.getInputStream()));
	          StringBuffer stringBuffer = new StringBuffer();
	          String line = null;
	          while ((line = in.readLine()) != null) {
	              stringBuffer.append(line+" ");
	          }
	          String str1=stringBuffer.toString();
	          System.out.println(str1);
	          String str2=str1.substring(str1.indexOf("Objects")-60,str1.indexOf("Objects"));     
	          System.out.println(str2);
	          str3=str2.substring(0,10);
	          str3.trim();
	          System.out.println("11"+str3);
	      } catch (InterruptedException e) {
	          System.err.println(e);
	      }finally{
	          try {
	              proc.destroy();
	          } catch (Exception e2) {
	          }
	      }
	    return str3 ;
	}
}
