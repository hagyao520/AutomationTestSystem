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

import AutomationTestSystem.View.LoginPageView;
import javafx.application.Platform;

@SuppressWarnings({"unused", "restriction"})
public class DatabaseUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseUtil.class);
    private static DialogUtil Dialog = new DialogUtil();
    private static ResultSet rs;
    public static Statement sm;
    private static Connection con;
    private static CallableStatement cs;
    
    /**
     * 连接数据库
     * 
     * @throws Exception
     */
    public static void Connect() throws Exception {
        try {
            String DatabaseConnectionMode = LoginPageView.DatabaseConnectionModeBox.getSelectionModel().getSelectedItem().toString();
            
            String DJDBCDriver = LoginPageView.DJDBCDriverField.getText();
            String DHostAddress = LoginPageView.DHostAddressField.getText();
            int DPort = Integer.parseInt(LoginPageView.DPortField.getText());
            String DDataBase = LoginPageView.DDataBaseField.getText();
            String DUserName = LoginPageView.DUserNameField.getText();
            String DPassWord = LoginPageView.DPassWordField.getText();
            
            String SKey = LoginPageView.SKeyField.getText();
            String SUserName = LoginPageView.SUserNameField.getText();
            String SPassWord = LoginPageView.SPassWordField.getText();
            String SHostAddress = LoginPageView.SHostAddressField.getText();
            int SPort = Integer.parseInt(LoginPageView.SPortField.getText());
            
            Boolean state = false;
            switch(DatabaseConnectionMode){
                case "Oracle" :
                    state = Connect_Oracle(DJDBCDriver, DHostAddress, DPort, DDataBase, DUserName, DPassWord);
                    break;
                case "MySql" :
                    state = Connect_MySql(DJDBCDriver, DHostAddress, DPort, DDataBase, DUserName, DPassWord);
                    break;
                case "SHHKeyMySql" :
                    state = Connect_SSHKeyMySql(SKey, SUserName, SHostAddress, SPort, DHostAddress, DPort, DJDBCDriver, DDataBase, DUserName, DPassWord);
                    break;
                case "SHHPassWordMySql" :
                    state = Connect_SSHPassWordMySql(SUserName, SHostAddress, SPort, SPassWord, DHostAddress, DPort, DJDBCDriver, DDataBase, DUserName, DPassWord);
                    break;
            }
            if(!state){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Dialog.SetMessageDialog("Error", "数据库连接失败，请检查后重试！");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接Oracle数据库
     * @return 
     * 
     * @throws Exception
     */
    public static boolean Connect_Oracle(String DJDBCDriver, String DHostAddress, int DPort, String DDataBase, String DUserName, String DPassWord) throws Exception {
        try {
            Class.forName(DJDBCDriver);
            con = DriverManager.getConnection("jdbc:oracle:thin:@" + DHostAddress + ":" + DPort + "/" + DDataBase + "", DUserName, DPassWord);
            sm = con.createStatement();
            LOG.info("数据库连接成功");
            return true;
        } catch (Exception e) {
            LOG.info("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 连接MySql数据库
     * 
     * @throws Exception
     */
    public static boolean Connect_MySql(String DJDBCDriver, String DHostAddress, int DPort, String DDataBase, String DUserName, String DPassWord) throws Exception {
        try {
            Class.forName(DJDBCDriver);
            con = DriverManager.getConnection("jdbc:mysql://" + DHostAddress + ":" + DPort + "/" + DDataBase + "?useUnicode=true&characterEncoding=utf-8&useSSL=false", DUserName, DPassWord);
            sm = con.createStatement();
            LOG.info("数据库连接成功");
            return true;
        } catch (Exception e) {
            LOG.info("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 连接SHHMySql数据库
     * 
     * @throws Exception
     */
    public static boolean Connect_SSHKeyMySql(String SKey, String SUserName, String SHostAddress, int SPort, String DHostAddress, int DPort, String DJDBCDriver, String DDataBase, String DUserName,
        String DPassWord) throws Exception {
        SSHKey(SKey, SUserName, SHostAddress, SPort, DHostAddress, DPort);
        // SSHPassWord(SKey,SUserName,SHostAddress,SPort,DHostAddress,DPort,SPassWord);
        try {
            Class.forName(DJDBCDriver);
            con = DriverManager.getConnection("jdbc:mysql://localhost:33100/" + DDataBase + "?useUnicode=true&characterEncoding=utf-8&useSSL=false", DUserName, DPassWord);
            sm = con.createStatement();
            LOG.info("数据库连接成功");
            return true;
        } catch (Exception e) {
            LOG.info("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 连接SHHMySql数据库
     * 
     * @throws Exception
     */
    public static boolean Connect_SSHPassWordMySql(String SUserName, String SHostAddress, int SPort, String SPassWord, String DHostAddress, int DPort, String DJDBCDriver, String DDataBase,
        String DUserName, String DPassWord) throws Exception {
        SSHPassWord(SUserName, SHostAddress, SPort, SPassWord, DHostAddress, DPort);
        try {
            Class.forName(DJDBCDriver);
            con = DriverManager.getConnection("jdbc:mysql://localhost:33101/" + DDataBase + "?useUnicode=true&characterEncoding=utf-8&useSSL=false", DUserName, DPassWord);
            sm = con.createStatement();
            LOG.info("数据库连接成功");
            return true;
        } catch (Exception e) {
            LOG.info("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }

    public static void SSHKey(String SKey, String SUserName, String SHostAddress, int SPort, String DHostAddress, int DPort) throws Exception {
        String passphrase = "11";
        try {
            JSch jsch = new JSch();
            jsch.addIdentity(SKey);
            Session session = jsch.getSession(SUserName, SHostAddress, SPort);
            UserInfo ui = new MyUserInfo(passphrase);
            session.setUserInfo(ui);
            session.connect();
            System.out.println("SSH服务器连接成功，版本信息为：" + session.getServerVersion());// 这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(33100, DHostAddress, DPort);
            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + DHostAddress + ":" + DPort);
        } catch (Exception e) {
            // e.printStackTrace();
            // DialogUtil.SetException("服务器连接失败！");
        }
    }

    public static void SSHPassWord(String SUserName, String SHostAddress, int SPort, String SPassWord, String DHostAddress, int DPort) throws Exception {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SUserName, SHostAddress, SPort);
            session.setPassword(SPassWord);
            // System.out.println(SSH_TUser+SSH_THost+SSH_TPort+SSH_TPassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("SSH服务器连接成功，版本信息为：" + session.getServerVersion());// 这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(33101, DHostAddress, DPort);
            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + DHostAddress + ":" + DPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源并关闭数据库
     */
    public static void close() {
        try {
            if (sm != null) {
                sm.close();
                sm = null;
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
            LOG.info("数据库资源释放成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("数据库资源释放失败！");
        }
        try {
            if (con != null && !con.isClosed())
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
