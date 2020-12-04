package AutomationTestSystem.Util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Batch {

    /** 判断数据库是否支持批处理 */
    public static boolean supportBatch(Connection con) {
        try {
            // 得到数据库的元数据
            DatabaseMetaData md = con.getMetaData();
            return md.supportsBatchUpdates();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** 执行一批SQL更新语句 */
    public static int[] goBatch(Connection con, String[] sqls) throws Exception {
        if (sqls == null) {
            return null;
        }
        Statement sm = null;
        try {
            sm = con.createStatement();
            for (int i = 0; i < sqls.length; i++) {
                sm.addBatch(sqls[i]);// 将所有的SQL语句添加到Statement中
            }
            // 一次执行多条SQL语句
            return sm.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sm.close();
        }
        return null;
    }

    /** 执行一批SQL查询语句 */
    public static void getQuery(Connection con, String[] sqls) throws Exception {
        if (sqls == null) {
            return;
        }
        Statement sm = null;
        try {
            sm = con.createStatement();
            for (int i = 0; i < sqls.length; i++) {
                ResultSet rs = sm.executeQuery(sqls[i]);
                while (rs.next()) {
                    String name = rs.getString("phonno");
                    System.out.println(name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sm.close();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("没有执行批处理时的数据为：");
        query();
        String[] sqls = new String[2];
        // sqls[0] = "UPDATE user_base_info set phonno='17378403910' WHERE phonno='17378403911'";
        // sqls[1] = "UPDATE user_base_info set phonno='17378403911' WHERE phonno='17378403910'";
        sqls[0] = "SELECT * FROM user_base_info WHERE phonno='17378403911'";
        sqls[1] = "SELECT * FROM user_base_info WHERE phonno='17378403911'";

        Connection con = null;
        try {
            con = getConnection();// 获得数据库连接
            boolean supportBatch = supportBatch(con); // 判断是否支持批处理
            System.out.println("支持批处理？ " + supportBatch);
            if (supportBatch) {
                // int[] results = goBatch(con, sqls);// 执行一批SQL语句
                // // 分析执行的结果
                // for (int i = 0; i < sqls.length; i++) {
                // if (results[i] >= 0) {
                // System.out.println("语句: " + sqls[i] + " 执行成功，影响了"
                // + results[i] + "行数据");
                // } else if (results[i] == Statement.SUCCESS_NO_INFO) {
                // System.out.println("语句: " + sqls[i] + " 执行成功，影响的行数未知");
                // } else if (results[i] == Statement.EXECUTE_FAILED) {
                // System.out.println("语句: " + sqls[i] + " 执行失败");
                // }
                // }
                getQuery(con, sqls);
                for (int i = 0; i < sqls.length; i++) {
                    System.out.println("语句: " + sqls[i] + " 执行成功");
                }
            }
        } catch (ClassNotFoundException e1) {
            throw e1;
        } catch (SQLException e2) {
            throw e2;
        } finally {
            con.close();// 关闭数据库连接
        }
        System.out.println("执行批处理后的数据为：");
        query();
    }

    public static Connection getConnection() {// 数据库连接
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
            con = DriverManager.getConnection("jdbc:mysql://10.22.83.70:3323/YHT_USER_SIT?useUnicode=true&characterEncoding=utf-8&useSSL=false", "yhtsit", "SitYht@300348");// 创建数据连接
        } catch (Exception e) {
            System.out.println("数据库连接失败");
        }
        return con;
    }

    public static void query() throws Exception {// 查询所有的数据
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM user_base_info WHERE phonno='17378403911'");
        while (rs.next()) {
            String name = rs.getString("phonno");
            System.out.println(name);
        }
    }

}
