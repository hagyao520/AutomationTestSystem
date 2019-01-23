package AutomationTestSystem.Util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
@SuppressWarnings("unused")
public class DatabaseUtil {
        private static final Logger LOG = LoggerFactory.getLogger(DatabaseUtil.class);
        private static DialogUtil Dialog= new DialogUtil();
		private static ResultSet rs;
    	private static Statement sm;
    	private static Connection con;
    	private static CallableStatement cs;
	    /**
	     * 连接Oracle数据库
	     * @throws Exception
	     */
//	    public static void Connect_Oracle(String DataVersion) throws Exception{
//	        try {
//	        	if("正式环境".equals(DataVersion)){
//		    	    Class.forName(Oracle_FDRIVER);
//		    	    con = DriverManager.getConnection(Oracle_FURL, Oracle_FUSERNAME, Oracle_FPASSWORD);
//		    	    sm = con.createStatement();
//		    	}
//		    	if("测试环境".equals(DataVersion)){
//		    		Class.forName(Oracle_TDRIVER);
//		    	    con = DriverManager.getConnection(Oracle_TURL, Oracle_TUSERNAME, Oracle_TPASSWORD);
//		    	    sm = con.createStatement();
//			    }
//		    	if("开发环境".equals(DataVersion)){
//		    		Class.forName(Oracle_DDRIVER);
//		    	    con = DriverManager.getConnection(Oracle_DURL, Oracle_DUSERNAME, Oracle_DPASSWORD);
//		    	    sm = con.createStatement();
//			    }
//		    	LOG.info("数据库连接成功");
//	        } catch (Exception e) {
//	            String message = "数据库连接失败";
//	            if(e instanceof ClassNotFoundException)
//	                message = "数据库驱动类未找到";
//	            throw new Exception(message, e.fillInStackTrace());
//	        }
//	    }

	    /**
	     * 连接Oracle数据库
	     * @throws Exception
	     */
	    public static void Connect_Oracle(String DJDBCDriver,String DHostAddress,int DPort,String DDataBase,String DUserName,String DPassWord) throws Exception{
	    	 try {
	    		      Class.forName(DJDBCDriver);
	    	          con = DriverManager.getConnection("jdbc:oracle:thin:@"+DHostAddress+":"+DPort+"/"+DDataBase+"?useUnicode=true&characterEncoding=utf-8&useSSL=false",DUserName,DPassWord);
	    	          sm = con.createStatement();
	    	          Dialog.SetMessageDialog("Success","数据库连接成功！");
	    	  }catch (Exception e) {
			          e.printStackTrace();
			          Dialog.SetMessageDialog("Error","数据库连接失败，请检查后重试！");
			  }
	    }

	    /**
	     * 连接MySql数据库
	     * @throws Exception
	     */
	    public static void Connect_MySql(String DJDBCDriver,String DHostAddress,int DPort,String DDataBase,String DUserName,String DPassWord) throws Exception{
	    	try {
	    		   Class.forName(DJDBCDriver);
	    	       con = DriverManager.getConnection("jdbc:mysql://"+DHostAddress+":"+DPort+"/"+DDataBase+"?useUnicode=true&characterEncoding=utf-8&useSSL=false",DUserName,DPassWord);
	    	       sm = con.createStatement();
	    	       Dialog.SetMessageDialog("Success","数据库连接成功！");
	    	 }catch (Exception e) {
			       e.printStackTrace();
			       Dialog.SetMessageDialog("Error","数据库连接失败，请检查后重试！");
			 }
	    }

	    /**
	     * 连接SHHMySql数据库
	     * @throws Exception
	     */
	    public static void Connect_SSHKeyMySql(String SKey,String SUserName,String SHostAddress,int SPort,String DHostAddress,int DPort,String DJDBCDriver,String DDataBase,String DUserName,String DPassWord) throws Exception{
	    	SSHKey(SKey,SUserName,SHostAddress,SPort,DHostAddress,DPort);
//		    SSHPassWord(SKey,SUserName,SHostAddress,SPort,DHostAddress,DPort,SPassWord);
		    try {
		    		Class.forName(DJDBCDriver);
		    	    con = DriverManager.getConnection("jdbc:mysql://localhost:33100/"+DDataBase+"?useUnicode=true&characterEncoding=utf-8&useSSL=false",DUserName,DPassWord);
		    	    sm = con.createStatement();
		    	    Dialog.SetMessageDialog("Success","数据库连接成功！");
		     }catch (Exception e) {
				    e.printStackTrace();
				    Dialog.SetMessageDialog("Error","数据库连接失败，请检查后重试！");
			 }
	    }

	    /**
	     * 连接SHHMySql数据库
	     * @throws Exception
	     */
	    public static void Connect_SSHPassWordMySql(String SUserName,String SHostAddress,int SPort,String SPassWord,String DHostAddress,int DPort,String DJDBCDriver,String DDataBase,String DUserName,String DPassWord) throws Exception{
		    SSHPassWord(SUserName,SHostAddress,SPort,SPassWord,DHostAddress,DPort);
		    try {
		    		Class.forName(DJDBCDriver);
		    	    con = DriverManager.getConnection("jdbc:mysql://localhost:33101/"+DDataBase+"?useUnicode=true&characterEncoding=utf-8&useSSL=false",DUserName,DPassWord);
		    	    sm = con.createStatement();
		    	    Dialog.SetMessageDialog("Success","数据库连接成功！");
		     }catch (Exception e) {
				    e.printStackTrace();
				    Dialog.SetMessageDialog("Error","数据库连接失败，请检查后重试！");
			 }
	    }

	    public static void SSHKey(String SKey,String SUserName,String SHostAddress,int SPort,String DHostAddress,int DPort) throws Exception{
	        String passphrase = "11";
	    	try {
		            JSch jsch = new JSch();
		            jsch.addIdentity(SKey);
		            Session session = jsch.getSession(SUserName, SHostAddress, SPort);
		            UserInfo ui = new MyUserInfo(passphrase);
		            session.setUserInfo(ui);
		            session.connect();
		            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
		            int assinged_port = session.setPortForwardingL(33100, DHostAddress,DPort);
		            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + DHostAddress + ":" + DPort);
	        } catch (Exception e) {
//	                e.printStackTrace();
//	                DialogUtil.SetException("服务器连接失败！");
	        }
	    }

	    public static void SSHPassWord(String SUserName,String SHostAddress,int SPort,String SPassWord,String DHostAddress,int DPort) throws Exception{
	    	try {
		            JSch jsch = new JSch();
		            Session session = jsch.getSession(SUserName, SHostAddress, SPort);
		            session.setPassword(SPassWord);
//		            System.out.println(SSH_TUser+SSH_THost+SSH_TPort+SSH_TPassword);
		            session.setConfig("StrictHostKeyChecking", "no");
		            session.connect();
		            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
		            int assinged_port = session.setPortForwardingL(33101, DHostAddress,DPort);
		            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + DHostAddress + ":" + DPort);
	        } catch (Exception e) {
	                e.printStackTrace();
	        }
	    }

	    /**
	     * 释放资源并关闭数据库
	     */
	    public static void close(Statement sm, ResultSet rs){
	    	try {
	    		if(sm != null){
	                sm.close();
	                sm = null;
	            }
	            if(rs != null){
	                rs.close();
	                rs = null;
	            }
	            LOG.info("数据库资源释放成功！");
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	LOG.info("数据库资源释放失败！");
	        }
	        try {
	            if(con != null && !con.isClosed())
	            	con.close();
	            con = null;
	            LOG.info("数据库连接关闭成功！");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            LOG.info("数据库连接关闭失败！");
	        }
	    }

	    public static class MyUserInfo implements UserInfo {
	    	   private String passphrase = null;

	    	   public MyUserInfo(String passphrase) {
	    	    this.passphrase = passphrase;
	    	   }

	    	   public String getPassphrase() {
	    	    return passphrase;
	    	   }

	    	   public String getPassword() {
	    	    return null;
	    	   }

	    	   public boolean promptPassphrase(String s) {
	    	    return true;
	    	   }

	    	   public boolean promptPassword(String s) {
	    	    return true;
	    	   }

	    	   public boolean promptYesNo(String s) {
	    	    return true;
	    	   }

	    	   public void showMessage(String s) {
	    	    System.out.println(s);
	    	   }
	    }
}
