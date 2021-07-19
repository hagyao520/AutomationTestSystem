package AutomationTestSystem.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javafx.application.Platform;

import org.apache.log4j.Logger;

import AutomationTestSystem.StartClient;
import AutomationTestSystem.Util.Constants;
import AutomationTestSystem.Util.DatabaseUtil;
import AutomationTestSystem.Util.DateUtil;
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.SqlFormatUtil;
import AutomationTestSystem.Util.StringUtil;
import AutomationTestSystem.View.BackendFunctionCenterPageView;

/**
 * @author King
 * @date 2020年3月1日 18:18:074
 */
@SuppressWarnings({ "unused", "unchecked", "rawtypes", "restriction","serial" })
public class BackendFunctionCenterPageController {

	static Logger log = Logger.getLogger(BackendFunctionCenterPageController.class);

	private static DialogUtil Dialog = new DialogUtil();
	private static JTableHeader jth;
	private static JTable tabDemo;
	
	private static String djdbcDriver = "com.mysql.jdbc.Driver";
	private static String dhostAddress = "10.22.83.70";
	private static String duserName = "adminall";
	private static String dpassWord = "admin@300348";
	private static int dport = 3323;
	private static String ddataBase = "YHT_ORDER_UAT";

	public static void main(String[] args) throws Exception {
		DatabaseUtil.Connect_MySql(djdbcDriver, dhostAddress, dport, ddataBase, duserName, dpassWord);
		UserInfoButton("查询", "17378403911", "000025","18694925429","用户替换");
	}
	
	// 根据用户手机号，查询用户信息
	public static boolean UserInfoButton(String option, String corpno, String phone,String tphone,String type) throws Exception {
		boolean state = false;
		String sql = "SELECT " + "a.rgdate as 注册时间,a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,e.phonno as 原手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
				+ "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM `YHT_USER_"+StartClient.Environment+"`.user_base_info a,`YHT_USER_"+StartClient.Environment+"`.cust_base_info b,`YHT_USER_"+StartClient.Environment+"`.cust_bind_acct c,`YHT_USER_"+StartClient.Environment+"`.cust_acct_rela d,`YHT_ORDER_"+StartClient.Environment+"`.user_info e "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.usercd=e.usercd "
				+ "ORDER BY c.gmt_create DESC;";
		String sql0 = "SELECT " + "a.rgdate as 注册时间,a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,e.phonno as 原手机号,"
            + "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
            + "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
            + "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
            + "FROM `YHT_USER_"+StartClient.Environment+"`.user_base_info a,`YHT_USER_"+StartClient.Environment+"`.cust_base_info b,`YHT_USER_"+StartClient.Environment+"`.cust_bind_acct c,`YHT_USER_"+StartClient.Environment+"`.cust_acct_rela d,`YHT_ORDER_"+StartClient.Environment+"`.user_info e "
            + "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.usercd=e.usercd and a.phonno in('"+phone+"','"+tphone+"') "
            + "and c.corpno='" + corpno + "' " + "ORDER BY c.gmt_create DESC;";
		String sql1 = "SELECT " + "a.rgdate as 注册时间,a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,e.phonno as 原手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
				+ "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM `YHT_USER_"+StartClient.Environment+"`.user_base_info a,`YHT_USER_"+StartClient.Environment+"`.cust_base_info b,`YHT_USER_"+StartClient.Environment+"`.cust_bind_acct c,`YHT_USER_"+StartClient.Environment+"`.cust_acct_rela d,`YHT_ORDER_"+StartClient.Environment+"`.user_info e "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.usercd=e.usercd and a.phonno='" + phone +"' "
				+ "and c.corpno='" + corpno + "' " + "ORDER BY c.gmt_create DESC;";
		String sql2 = "SELECT " + "a.rgdate as 注册时间,a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,e.phonno as 原手机号,"
            + "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
            + "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
            + "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
            + "FROM `YHT_USER_"+StartClient.Environment+"`.user_base_info a,`YHT_USER_"+StartClient.Environment+"`.cust_base_info b,`YHT_USER_"+StartClient.Environment+"`.cust_bind_acct c,`YHT_USER_"+StartClient.Environment+"`.cust_acct_rela d,`YHT_ORDER_"+StartClient.Environment+"`.user_info e "
            + "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.usercd=e.usercd and a.phonno in('"+phone+"','"+tphone+"')"
            + "ORDER BY a.gmt_create DESC;";
		String sql3 = "SELECT " + "a.rgdate as 注册时间,a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,e.phonno as 原手机号,"
            + "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
            + "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
            + "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
            + "FROM `YHT_USER_"+StartClient.Environment+"`.user_base_info a,`YHT_USER_"+StartClient.Environment+"`.cust_base_info b,`YHT_USER_"+StartClient.Environment+"`.cust_bind_acct c,`YHT_USER_"+StartClient.Environment+"`.cust_acct_rela d,`YHT_ORDER_"+StartClient.Environment+"`.user_info e "
            + "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.usercd=e.usercd and c.corpno='" + corpno + "' "
            + "ORDER BY c.gmt_create DESC;";
		String sql4 = "SELECT " + "a.rgdate as 注册时间,a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,e.phonno as 原手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
				+ "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM `YHT_USER_"+StartClient.Environment+"`.user_base_info a,`YHT_USER_"+StartClient.Environment+"`.cust_base_info b,`YHT_USER_"+StartClient.Environment+"`.cust_bind_acct c,`YHT_USER_"+StartClient.Environment+"`.cust_acct_rela d,`YHT_ORDER_"+StartClient.Environment+"`.user_info e "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.usercd=e.usercd and a.phonno='" + phone + "' "
				+ "ORDER BY c.gmt_create DESC;";
		
		List<String> sqllist = new ArrayList<String>();
		//用户信息修改
		String sql5 = "UPDATE `YHT_USER_"+StartClient.Environment+"`.`user_base_info` SET bracct=usercd,usercd=phonno WHERE phonno in('"+phone+"','"+tphone+"');";
		String sql6 = "UPDATE `YHT_USER_"+StartClient.Environment+"`.`user_base_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"');";
        String sql7 = "UPDATE `YHT_USER_"+StartClient.Environment+"`.`user_base_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"');";
		String sql8 = "UPDATE `YHT_MERH_"+StartClient.Environment+"`.`memb_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"');";
        String sql9 = "UPDATE `YHT_MERH_"+StartClient.Environment+"`.`memb_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"');";
        //用户信息还原
        String sql10 = "UPDATE `YHT_USER_"+StartClient.Environment+"`.`user_base_info` SET bracct=usercd,usercd=phonno WHERE phonno in('"+phone+"','"+tphone+"');";
        String sql11 = "UPDATE `YHT_USER_"+StartClient.Environment+"`.`user_base_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"');";
        String sql12 = "UPDATE `YHT_USER_"+StartClient.Environment+"`.`user_base_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"');";
        String sql13 = "UPDATE `YHT_MERH_"+StartClient.Environment+"`.`memb_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+phone+"');";
        String sql14 = "UPDATE `YHT_MERH_"+StartClient.Environment+"`.`memb_info` SET usercd=(SELECT usercd FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"') WHERE custno=(SELECT custno FROM `YHT_ORDER_"+StartClient.Environment+"`.`user_info` WHERE phonno='"+tphone+"');";
        String sql15 = "UPDATE `YHT_USER_"+StartClient.Environment+"`.`user_base_info` SET usercd=bracct WHERE phonno in('"+phone+"','"+tphone+"');";
        
		try {
			switch (option) {
			case "查询":
				if (StringUtil.isEmpty(corpno) && StringUtil.isEmpty(phone)&& StringUtil.isEmpty(tphone)) {
					state = Select(option, sql);
				}else if (StringUtil.isNotEmpty(corpno) && StringUtil.isNotEmpty(phone)&& StringUtil.isNotEmpty(tphone)) {
                    state = Select(option, sql0);
                } else if (StringUtil.isNotEmpty(corpno) && StringUtil.isNotEmpty(phone)) {
                    state = Select(option, sql1);
                } else if (StringUtil.isNotEmpty(phone) && StringUtil.isNotEmpty(tphone)) {
					state = Select(option, sql2);
				} else if (StringUtil.isNotEmpty(corpno)) {
					state = Select(option, sql3);
				} else if (StringUtil.isNotEmpty(phone)) {
					state = Select(option, sql4);
				}
				break;
			case "修改":
			    if (StringUtil.isNotEmpty(phone) && StringUtil.isNotEmpty(tphone)&&StringUtil.isEqual(type, "用户替换")) {
			        sqllist.add(sql5);
                    sqllist.add(sql6);
                    sqllist.add(sql7);
                    sqllist.add(sql8);
                    sqllist.add(sql9);
                    state = Update(option, sql5);
                    if(state){
                        for(int i =1;i<sqllist.size();i++){
                            if(state){
                                state = Update(option, sqllist.get(i));
                            }else{
                                Update(option, sql15);
                                break;
                            }
                        }
                        Select("查询", sql2);
                    }
                }else if(StringUtil.isNotEmpty(phone)&& StringUtil.isNotEmpty(tphone)&&StringUtil.isEqual(type, "用户还原")){
                    sqllist.add(sql10);
                    sqllist.add(sql11);
                    sqllist.add(sql12);
                    sqllist.add(sql13);
                    sqllist.add(sql14);
                    state = Update(option, sql10);
                    if(state){
                        for(int i =1;i<sqllist.size();i++){
                            if(state){
                                state = Update(option, sqllist.get(i));
                            }else{
                                Update(option, sql15);
                                break;
                            }
                        }
                        Select("查询", sql2);
                    }
                }
				break;
			case "新增":
				break;
			case "删除":
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}

	// 根据用户手机号，查询短信验证码信息
	public static boolean SmsInquiryButton(String option, String phone) throws Exception {
		boolean state = false;
		String sql = "SELECT a.phonno as 手机号,a.str1 as 验证码,a.create_date as 时间,a.* from m_getmsg_water a ORDER BY create_date DESC;";
		String sql1 = "SELECT a.phonno as 手机号,a.str1 as 验证码,a.create_date as 时间,a.* from m_getmsg_water a where phonno ='"
				+ phone + "' ORDER BY create_date DESC;";
		try {
			switch (option) {
			case "查询":
				if (StringUtil.isEmpty(phone)) {
					state = Select(option, sql);
				} else {
					state = Select(option, sql1);
				}
				break;
			case "修改":
				break;
			case "新增":
				break;
			case "删除":
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}

	// 根据用户身份证，企业工商注册号等，查询工商四要素验证信息
	public static boolean Business_Button(String option, String idtfno, String regnum) throws Exception {
		boolean state = false;
		String sql = "SELECT a.transq as 交易流水,a.compna as 企业名称,a.regnum as 企业工商注册号,a.person as 企业法人姓名,a.idtfno as 证件号码,a.trandt as 交易日期,a.etnamh as 企业名称是否匹配,a.idnomh as 身份证号码是否匹配,a.renamh as 姓名是否匹配,a.renomh as 工商注册号是否匹配,a.transt as 交易状态,a.rqdata as 请求数据,a.rpdata as 响应数据,a.crtime as 创建时间,a.uptime as 更新时间,a.erorcd as 错误码,a. erortx as 错误描述 from bsap_saic_busi_recd a order by crtime desc;";
		String sql0 = "SELECT a.transq as 交易流水,a.compna as 企业名称,a.regnum as 企业工商注册号,a.person as 企业法人姓名,a.idtfno as 证件号码,a.trandt as 交易日期,a.etnamh as 企业名称是否匹配,a.idnomh as 身份证号码是否匹配,a.renamh as 姓名是否匹配,a.renomh as 工商注册号是否匹配,a.transt as 交易状态,a.rqdata as 请求数据,a.rpdata as 响应数据,a.crtime as 创建时间,a.uptime as 更新时间,a.erorcd as 错误码,a. erortx as 错误描述 from bsap_saic_busi_recd a where "
				+ "idtfno='" + idtfno + "' and regnum='" + regnum + "' order by crtime desc;";
		String sql1 = "SELECT a.transq as 交易流水,a.compna as 企业名称,a.regnum as 企业工商注册号,a.person as 企业法人姓名,a.idtfno as 证件号码,a.trandt as 交易日期,a.etnamh as 企业名称是否匹配,a.idnomh as 身份证号码是否匹配,a.renamh as 姓名是否匹配,a.renomh as 工商注册号是否匹配,a.transt as 交易状态,a.rqdata as 请求数据,a.rpdata as 响应数据,a.crtime as 创建时间,a.uptime as 更新时间,a.erorcd as 错误码,a. erortx as 错误描述 from bsap_saic_busi_recd a where "
				+ "idtfno='" + idtfno + "' order by crtime desc;";
		String sql2 = "SELECT a.transq as 交易流水,a.compna as 企业名称,a.regnum as 企业工商注册号,a.person as 企业法人姓名,a.idtfno as 证件号码,a.trandt as 交易日期,a.etnamh as 企业名称是否匹配,a.idnomh as 身份证号码是否匹配,a.renamh as 姓名是否匹配,a.renomh as 工商注册号是否匹配,a.transt as 交易状态,a.rqdata as 请求数据,a.rpdata as 响应数据,a.crtime as 创建时间,a.uptime as 更新时间,a.erorcd as 错误码,a. erortx as 错误描述 from bsap_saic_busi_recd a where "
				+ "regnum='" + regnum + "' order by crtime desc;";
		String sql3 = "UPDATE bsap_saic_busi_recd a set a.etnamh='1',a.idnomh='1',a.renamh='1',a.renomh='1' where idtfno='"
				+ idtfno + "' and regnum='" + regnum + "';";
		String sql4 = "UPDATE bsap_saic_busi_recd a set a.etnamh='1',a.idnomh='1',a.renamh='1',a.renomh='1' where idtfno='"
				+ idtfno + "';";
		String sql5 = "UPDATE bsap_saic_busi_recd a set a.etnamh='1',a.idnomh='1',a.renamh='1',a.renomh='1' where regnum='"
				+ regnum + "';";
		try {
			switch (option) {
			case "查询":
				if (StringUtil.isEmpty(idtfno) && StringUtil.isEmpty(regnum)) {
					state = Select(option, sql);
				} else if (StringUtil.isNotEmpty(idtfno) && StringUtil.isNotEmpty(regnum)) {
					state = Select(option, sql0);
				} else if (StringUtil.isNotEmpty(idtfno)) {
					state = Select(option, sql1);
				} else if (StringUtil.isNotEmpty(regnum)) {
					state = Select(option, sql2);
				}
				break;
			case "修改":
				if (StringUtil.isNotEmpty(idtfno) && StringUtil.isNotEmpty(regnum)) {
					state = Update(option, sql3);
					Select("查询", sql0);
				} else if (StringUtil.isNotEmpty(idtfno)) {
					state = Update(option, sql4);
					Select("查询", sql1);
				} else if (StringUtil.isNotEmpty(regnum)) {
					state = Update(option, sql5);
					Select("查询", sql2);
				}
				break;
			case "新增":
				break;
			case "删除":
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}

	// 根据企业名称或企业代码，查询企业白名单信息
    public static boolean WhiteList_Button(String option, String firmna, String nsrsbh) throws Exception {
        boolean state = false;
        String crtime = DateUtil.getDate();
        String sql = "SELECT a.firmna as 企业名称,a.nsrsbh as 企业信用代码,a.* from ntrcb_cust_white_list a order by crtime desc;";
        String sql0 = "SELECT a.firmna as 企业名称,a.nsrsbh as 企业信用代码,a.* from ntrcb_cust_white_list a where firmna='"+ firmna +"' and nsrsbh='"+ nsrsbh +"' order by crtime desc;";
        String sql1 = "SELECT a.firmna as 企业名称,a.nsrsbh as 企业信用代码,a.* from ntrcb_cust_white_list a where firmna='"+ firmna +"' order by crtime desc;";
        String sql2 = "SELECT a.firmna as 企业名称,a.nsrsbh as 企业信用代码,a.* from ntrcb_cust_white_list a where nsrsbh='"+ nsrsbh +"' order by crtime desc;";
        String sql3 = "INSERT INTO `ntrcb_cust_white_list`"
            + "(`serino`, `nsrsbh`, `firmna`, `prodcd`, `creamt`, `whitfl`, `apprtm`, `exrate`, `remark`, `cretor`, `crtime`, `updtor`, `uptime`) "
            + "VALUES "
            + "('001', '"+ nsrsbh +"', '" + firmna + "', '', '300000.00', '2', '"+crtime+"', '0.1', '', '', '"+crtime+"', '', '"+crtime+"');";
        String sql4 = "DELETE FROM ntrcb_cust_white_list WHERE firmna='" + firmna + "';";
        String sql5 = "DELETE FROM ntrcb_cust_white_list WHERE nsrsbh='" + nsrsbh + "';";
        try {
            switch (option) {
            case "查询":
                if (StringUtil.isEmpty(firmna) && StringUtil.isEmpty(nsrsbh)) {
                    state = Select(option, sql);
                } else if (StringUtil.isNotEmpty(firmna) && StringUtil.isNotEmpty(nsrsbh)) {
                    state = Select(option, sql0);
                } else if (StringUtil.isNotEmpty(firmna)) {
                    state = Select(option, sql1);
                } else if (StringUtil.isNotEmpty(nsrsbh)) {
                    state = Select(option, sql2);
                }
                break;
            case "修改":
                break;
            case "新增":
                state = Update(option, sql3);
                Select("查询", sql);
                break;
            case "删除":
                if (StringUtil.isNotEmpty(firmna)) {
                    state = Update(option, sql4);
                } else if (StringUtil.isNotEmpty(nsrsbh)) {
                    state = Update(option, sql5);
                }
                Select("查询", sql);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    
    // 根据客户姓名和企业代码，清除客户相关信息
    public static boolean EnterpriseCredit_Button(String option, String cobjna, String nsrsbh) throws Exception {
        boolean state = false;
        String sql = "SELECT * from ntrcb_credit_apply order by crtime desc;";
        String sql0 = "SELECT * from ntrcb_credit_apply where cobjna = '"+cobjna+"' and nsrsbh = '"+nsrsbh+"' order by crtime desc;";
        String sql1 = "SELECT * from ntrcb_credit_apply where cobjna = '"+cobjna+"' order by crtime desc;";
        String sql2 = "SELECT * from ntrcb_credit_apply where nsrsbh = '"+nsrsbh+"' order by crtime desc;";
        try {
            switch (option) {
            case "查询":
                if (StringUtil.isEmpty(cobjna) && StringUtil.isEmpty(nsrsbh)) {
                    state = Select(option, sql);
                } else if (StringUtil.isNotEmpty(cobjna) && StringUtil.isNotEmpty(nsrsbh)) {
                    state = Select(option, sql0);
                } else if (StringUtil.isNotEmpty(cobjna)) {
                    state = Select(option, sql1);
                } else if (StringUtil.isNotEmpty(nsrsbh)) {
                    state = Select(option, sql2);
                }
                break;
            case "修改":
                break;
            case "新增":
                break;
            case "删除":
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    
    // 根据客户姓名和企业代码，清除客户相关信息
    public static boolean UserClear_Button(String option, String id_card, String nsrsbh) throws Exception {
        boolean state = false;
        String sql0 = "SELECT * from ntrcb_credit_apply order by crtime desc;";
        String sql1 = "DELETE FROM user_bind WHERE id_card='"+ id_card +"';";
        String sql2 = "DELETE FROM ntrcb_cust_legal_info WHERE leidno='"+ id_card +"';";
        String sql3 = "DELETE FROM ntrcb_cust_base_info WHERE leidno='"+ id_card +"';";
        String sql4 = "DELETE FROM ntrcb_cust_detail_info WHERE nsrsbh='"+ nsrsbh +"';";
        String sql5 = "DELETE FROM ntrcb_credit_apply WHERE leidno='"+ id_card +"' and nsrsbh='"+ nsrsbh +"';";
        List<String> sql = new ArrayList<String>();
        try {
            switch (option) {
            case "查询":
                break;
            case "修改":
                break;
            case "新增":
                break;
            case "删除":
                if (StringUtil.isNotEmpty(id_card) && StringUtil.isNotEmpty(nsrsbh)) {
                    sql.add(sql1);
                    sql.add(sql2);
                    sql.add(sql3);
                    sql.add(sql4);
                    sql.add(sql5);
                    state = Update(option, sql1);
                    if(state){
                        for(int i =1;i<sql.size();i++){
                            if(state){
                                state = Update(option, sql.get(i));
                            }else{
                                break;
                            }
                        }
                    }
                }
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    
    // 根据查询时间，查询系统日志信息
    public static boolean SystemLog_Button(String option, String start, String end) throws Exception {
        boolean state = false;
        String sql0 = "select pckgdt,startm,endttm,idtfno,prcscd,erorcd,erortx,a.* from ch_tran a where pckgdt = DATE_FORMAT(NOW(),'%Y%m%d') order by startm desc;";
        String sql1 = "select pckgdt,startm,endttm,idtfno,prcscd,erorcd,erortx from ch_tran where pckgdt = DATE_FORMAT(NOW(),'%Y%m%d') and startm < concat(DATE_FORMAT(NOW(),'%Y%m%d%H%i%s')-"+start+",substring(startm, -3))and startm > concat(DATE_FORMAT(NOW(),'%Y%m%d%H%i%s')-"+end+",substring(startm, -3)) order by startm desc;";
        String sql2 = "select pckgdt,startm,endttm,pckgsq,inglsq,rpndsq,tracsq,trantc,idtfno,prcscd,erorcd,erortx from ch_tran where pckgdt = DATE_FORMAT(NOW(),'%Y%m%d') and startm > concat(DATE_FORMAT(NOW(),'%Y%m%d%H%i')-"+start+",substring(startm, -5)) order by startm desc;";
        String sql3 = "select pckgdt,startm,endttm,pckgsq,inglsq,rpndsq,tracsq,trantc,idtfno,prcscd,erorcd,erortx from ch_tran where pckgdt = DATE_FORMAT(NOW(),'%Y%m%d') and startm < concat(DATE_FORMAT(NOW(),'%Y%m%d%H%i')-"+end+",substring(startm, -5)) order by startm desc;";
        try {
            switch (option) {
            case "查询":
                if (StringUtil.isEmpty(start) && StringUtil.isEmpty(end)) {
                    state = Select(option, sql0);
                } else if (StringUtil.isNotEmpty(start) && StringUtil.isNotEmpty(end)) {
                    state = Select(option, sql1);
                } else if (StringUtil.isNotEmpty(start)) {
                    state = Select(option, sql2);
                } else if (StringUtil.isNotEmpty(end)) {
                    state = Select(option, sql3);
                }
                break;
            case "修改":
                break;
            case "新增":
                break;
            case "删除":
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    
    // 根据用户手机号，查询用户信息
    public static boolean YX_UserInfoButton(String option, String phone,String user_type,String personnel_type) throws Exception {
        boolean state = false;
        String sql0 = "SELECT user_type as '用户类型(01-内部用户(客户经理),02-外部用户(游客),03-普通用户)',login_type as '登录类型(01-企业微信,02-普通微信的存款/理财/积分,03-微信,04-第三方)',usercd as '用户ID',user_name as '用户姓名',phone_no as '用户手机号',cust_manager_no as '用户工号',nick_name as '用户微信昵称',user_avatar as '用户头像',user_status as '用户状态',open_id as '用户open_id',org_id as '用户所属法人ID',data_create_time,data_update_time,data_create_user,data_update_user,data_version FROM kusb_user ORDER BY data_create_time DESC;";
        String sql1 = "SELECT user_type as '用户类型(01-内部用户(客户经理),02-外部用户(游客),03-普通用户)',login_type as '登录类型(01-企业微信,02-普通微信的存款/理财/积分,03-微信,04-第三方)',usercd as '用户ID',user_name as '用户姓名',phone_no as '用户手机号',cust_manager_no as '用户工号',nick_name as '用户微信昵称',user_avatar as '用户头像',user_status as '用户状态',open_id as '用户open_id',org_id as '用户所属法人ID',data_create_time,data_update_time,data_create_user,data_update_user,data_version FROM kusb_user WHERE phone_no='"+ phone +"' and user_type='"+ user_type +"' ORDER BY data_create_time DESC;";
        String sql2 = "SELECT user_type as '用户类型(01-内部用户(客户经理),02-外部用户(游客),03-普通用户)',login_type as '登录类型(01-企业微信,02-普通微信的存款/理财/积分,03-微信,04-第三方)',usercd as '用户ID',user_name as '用户姓名',phone_no as '用户手机号',cust_manager_no as '用户工号',nick_name as '用户微信昵称',user_avatar as '用户头像',user_status as '用户状态',open_id as '用户open_id',org_id as '用户所属法人ID',data_create_time,data_update_time,data_create_user,data_update_user,data_version FROM kusb_user WHERE user_name in(SELECT user_name FROM kusb_user WHERE phone_no='"+ phone +"') ORDER BY data_create_time DESC;";
        String sql3 = "SELECT user_type as '用户类型(01-内部用户(客户经理),02-外部用户(游客),03-普通用户)',login_type as '登录类型(01-企业微信,02-普通微信的存款/理财/积分,03-微信,04-第三方)',usercd as '用户ID',user_name as '用户姓名',phone_no as '用户手机号',cust_manager_no as '用户工号',nick_name as '用户微信昵称',user_avatar as '用户头像',user_status as '用户状态',open_id as '用户open_id',org_id as '用户所属法人ID',data_create_time,data_update_time,data_create_user,data_update_user,data_version FROM kusb_user WHERE user_type='"+ user_type +"' ORDER BY data_create_time DESC;";
        String sql4 = "UPDATE kusb_user SET user_type='"+ user_type +"' WHERE phone_no='"+ phone +"';";
        String sql5 = "UPDATE YHT_CMP_SIT.dbm_name_list SET personnel_type='"+ personnel_type +"' WHERE phone='"+ phone +"';";
        String sql6 = "DELETE a FROM kusb_user_manager a INNER JOIN kusb_user b on a.usercd=b.usercd WHERE a.to_cust_phone='"+ phone +"';";
        String sql7 = "DELETE FROM kusb_user WHERE nick_name in(SELECT a.nick_name from (SELECT nick_name FROM kusb_user WHERE phone_no='"+phone+"')a)";
        String sql8 = "DELETE FROM YHT_CMP_SIT.dbm_name_list where phone='"+phone+"';";
        
        List<String> sql = new ArrayList<String>();
        try {
            switch (option) {
            case "查询":
                if (StringUtil.isEmpty(phone)&&StringUtil.isEmpty(user_type)) {
                    state = Select(option, sql0);
                }else if (StringUtil.isNotEmpty(phone)&&StringUtil.isNotEmpty(user_type)){
                    state = Select(option, sql1);
                }else if (StringUtil.isNotEmpty(phone)){
                    state = Select(option, sql2);
                }else if (StringUtil.isNotEmpty(user_type)){
                    state = Select(option, sql3);
                }
                break;
            case "修改":
                if (StringUtil.isNotEmpty(phone)&&StringUtil.isNotEmpty(user_type)&&StringUtil.isNotEmpty(personnel_type)) {
                    sql.add(sql4);
                    sql.add(sql5);
                    state = Update(option, sql4);
                    if(state){
                        for(int i =1;i<sql.size();i++){
                            state = Update(option, sql.get(i));
                        }
                        Select("查询", sql1);
                    }
                }
                break;
            case "新增":
                break;
            case "删除":
                if (StringUtil.isNotEmpty(phone)) {
                    sql.add(sql6);
                    sql.add(sql7);
                    sql.add(sql8);
                    state = Update(option, sql6);
                    if(state){
                        for(int i =1;i<sql.size();i++){
                            if(state){
                                state = Update(option, sql.get(i));
                            }else{
                                break;
                            }
                        }
                        Select("查询", sql0);
                    }
                }
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    
     // 根据客户姓名和企业代码，清除客户相关信息
    public static boolean YX_UserClear_Button(String option, String phone) throws Exception {
        boolean state = false;
        String sql0 = "SELECT usercd as '用户ID',user_name as '用户姓名',phone_no as '用户手机号',cust_manager_no   as '用户工号',nick_name as '用户微信昵称',user_avatar as '用户头像',user_status   as '用户状态',user_type as '用户类型(01-内部用户(客户经理),02-外部用户(游客),03-普通用户)',login_type as '登录类型(01-企业微信,02-普通微信的存款/理财/积分,03-微信,04-第三方)',open_id    as '用户open_id',org_id as '用户所属法人ID',data_create_time,data_update_time,data_create_user,data_update_user,data_version FROM kusb_user ORDER BY data_create_time DESC;";
        String sql1 = "DELETE a FROM kusb_user_manager a INNER JOIN kusb_user b on a.usercd=b.usercd WHERE a.to_cust_phone='"+ phone +"';";
        String sql2 = "DELETE FROM kusb_user WHERE nick_name in(SELECT a.nick_name from (SELECT nick_name FROM kusb_user WHERE phone_no='"+phone+"')a)";
        List<String> sql = new ArrayList<String>();
        try {
            switch (option) {
            case "查询":
                state = Select(option, sql0);
                break;
            case "修改":
                break;
            case "新增":
                break;
            case "删除":
                if (StringUtil.isNotEmpty(phone)) {
                    sql.add(sql1);
                    sql.add(sql2);
                    state = Update(option, sql1);
                    if(state){
                        for(int i =1;i<sql.size();i++){
                            if(state){
                                state = Update(option, sql.get(i));
                            }else{
                                break;
                            }
                        }
                    }
                }
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public static boolean Select(String option, String sql) throws Exception {
		try {
		    if(StartClient.state){
	            DatabaseUtil.Connect();
		    }else{
		        DatabaseUtil.Connect_MySql(djdbcDriver, dhostAddress, dport, ddataBase, duserName, dpassWord);
		    }
		    BackendFunctionCenterPageView.textArea.append("SQL：\n" + SqlFormatUtil.format(sql) + "\n");
			ResultSet res = DatabaseUtil.sm.executeQuery(sql);
			boolean moreRecords = res.next();
			if (moreRecords) {
				Vector rows = new Vector();
				Vector columnHeads = new Vector();
				ResultSetMetaData rsmd = res.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
					columnHeads.addElement(rsmd.getColumnLabel(i));
				}
				do {
					rows.addElement(getNextRow(res, rsmd));
				} while (res.next());
//				tabDemo = new JTable(rows, columnHeads);
				tabDemo = new JTable(rows, columnHeads){ // 将获得的行列数据信息作为参数重新构造表格视图
                    protected JTableHeader createDefaultTableHeader() {   
				        //悬浮显示表头内容  
                        return new JTableHeader(columnModel) {   
                            public String getToolTipText(MouseEvent e) {   
                                String tip = null;   
                                Object[] tmp=columnHeads.toArray(new String[columnHeads.size()]);
                                java.awt.Point p = e.getPoint();   
                                int index = columnModel.getColumnIndexAtX(p.x);   
                                int realIndex =  columnModel.getColumn(index).getModelIndex();   
                                return (String)tmp[realIndex];   
                            }   
                        };   
                    }
				    //悬浮显示单元格内容  
                    public String getToolTipText(MouseEvent e) {   
                        int row=tabDemo.rowAtPoint(e.getPoint());   
                        int col=tabDemo.columnAtPoint(e.getPoint());   
                        String tiptextString=null;   
                        if(row>-1 && col>-1){   
                            Object value=tabDemo.getValueAt(row, col);   
                            if(null!=value && !"".equals(value))   
                                tiptextString=value.toString();
                        }   
                        return tiptextString;   
                    }   
                };
				TableView(tabDemo);
				BackendFunctionCenterPageView.textArea.append("状态：" + option + "成功！" + "\n");
				BackendFunctionCenterPageView.textArea.append("\n");
				DatabaseUtil.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean Update(String option, String sql) throws Exception {
		try {
		    if(StartClient.state){
                DatabaseUtil.Connect();
            }else{
                DatabaseUtil.Connect_MySql(djdbcDriver, dhostAddress, dport, ddataBase, duserName, dpassWord);
            }
		    BackendFunctionCenterPageView.textArea.append("SQL：\n" + SqlFormatUtil.format(sql) + "\n");
			int results = DatabaseUtil.sm.executeUpdate(sql);
			if (results > 0) {
				log.info("语句: " + sql + " 执行成功，影响了" + results + "行数据");
				BackendFunctionCenterPageView.textArea.append("状态：" + option + "成功，影响了" + results + "行数据！" + "\n");
				BackendFunctionCenterPageView.textArea.append("\n");
			} else if (results == Statement.SUCCESS_NO_INFO) {
				log.info("语句: " + sql + " 执行成功，影响的行数未知");
				BackendFunctionCenterPageView.textArea.append("状态：" + option + "成功，影响的行数未知！" + "\n");
				BackendFunctionCenterPageView.textArea.append("\n");
				return false;
			} else if (results == Statement.EXECUTE_FAILED) {
				log.info("语句: " + sql + " 执行失败");
				return false;
			}else {
			    log.info("语句: " + sql + " 执行成功，影响了" + results + "行数据");
                BackendFunctionCenterPageView.textArea.append("状态：" + option + "成功，影响了" + results + "行数据！" + "\n");
                BackendFunctionCenterPageView.textArea.append("\n");
                return false;
            }
			DatabaseUtil.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 根据用户手机号，查询用户信息
	public static boolean UserInfoButton1(String Phone) throws Exception {
		String sql = "SELECT " + "a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM user_base_info a,cust_base_info b,cust_bind_acct c,cust_acct_rela d "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.phonno='" + Phone + "' "
				+ "ORDER BY c.gmt_create DESC";
		try {
			DatabaseUtil.Connect();
			// DatabaseUtil.Connect_MySql(djdbcDriver, dhostAddress, dport,
			// ddataBase, duserName, dpassWord);
			ResultSet res = DatabaseUtil.sm.executeQuery(sql);
			BackendFunctionCenterPageView.textArea.append("SQL：" + sql + "\n");
			boolean moreRecords = res.next(); // 定位到达第一条记录
			if (!moreRecords) {
				// JOptionPane.showMessageDialog(null, "未查询到数据，请检查SQL信息！");
				return false;
			} else {
				Vector rows = new Vector();
				Vector columnHeads = new Vector();
				ResultSetMetaData rsmd = res.getMetaData(); // 获得rs结果集中列属性信息
				for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
					// columnHeads.addElement(rsmd.getColumnName(i)); //
					// 获得原始列名(将列名存放至向量columnHeads)
					columnHeads.addElement(rsmd.getColumnLabel(i)); // 获得自定义列名(将列名存放至向量columnHeads)
				}
				do {
					rows.addElement(getNextRow(res, rsmd));
				} while (res.next()); // 利用循环获得所有记录
				tabDemo = new JTable(rows, columnHeads);
				TableView(tabDemo);
				DatabaseUtil.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return true;
	}

	private static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		Vector currentRow = new Vector(); // 定义一个向量,用于存放记录
		for (int i = 1; i <= rsmd.getColumnCount(); ++i)
			currentRow.addElement(rs.getString(i)); // 获取记录
		return currentRow; // 返回记录
	}

	/**
     * 自动调整表列宽
     * 
     * @param table
     */
	public static void fitTableColumns(JTable myTable) {
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration<?> columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) header.getDefaultRenderer()
					.getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width + myTable.getIntercellSpacing().width);
			// 自定义调整前两列的宽度
	        for(int i=0;i<2;i++){
	            int Width = tabDemo.getColumnModel().getColumn(i).getWidth();
	            if(Width>300){
	                tabDemo.getColumnModel().getColumn(i).setWidth(120);
	            }
	        }
		}
	}

	/**
	 * 表头数据居中
	 * 
	 * @param table
	 */
	public static void setTableHeaderCenter(JTable table) {
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	}

	/**
	 * 表格数据居中
	 * 
	 * @param table
	 */
	public static void setTableColumnCenter(JTable table) {
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
	}

	/**
	 * 表格视图设置
	 * 
	 * @param tabDemo
	 * @param table
	 */
	public static void TableView(JTable tabDemo) {
		jth = tabDemo.getTableHeader();
		// 通过点击表头来排序列中数据resort data by clicking table header
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabDemo.getModel());
		tabDemo.setRowSorter(sorter);
		// 设置JTable的内容的字体大小
		 tabDemo.setFont(Constants.BASIC_FONT);
		// 设置表头的字体
		tabDemo.getTableHeader().setFont(Constants.BASIC_FONT2);
		// 设置表头文字的颜色
		tabDemo.getTableHeader().setForeground(Color.white);
		// 设置表单的表头高度
//        ((DefaultTableCellRenderer) tabDemo.getTableHeader().getDefaultRenderer())
//		.setPreferredSize(new Dimension(200,25));
		// 设置表头的背景色
		tabDemo.getTableHeader().setBackground(new Color(30, 34, 35));
		// 设置表头与单元格边框是否居中等
		setTableHeaderCenter(tabDemo);
		// 标题的重新排序是否被允许
		// tabDemo.getTableHeader().setReorderingAllowed(false);
		// 在列标题之间拖动而改变大小
		// tabDemo.getTableHeader().setResizingAllowed(false);
		// 单元格内方格坐标线的缺省颜色是Color.gray
		// tabDemo.setGridColor(Color.red);
		// 行的高
		// tabDemo.setRowHeight(30);
		// 背景颜色
		tabDemo.setSelectionBackground(Color.black);
		// 前景颜色
		tabDemo.setSelectionForeground(Color.white);
		// 隐藏单元格的方格坐标线
		// tabDemo.setShowHorizontalLines(false);
		// tabDemo.setShowVerticalLines(false);
		// 允许一次选择一行
		// tabDemo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 允许选择相邻的一系列行。
		// tabDemo.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		// 许用户使用[Ctrl]键进行多个互不相邻的选择（即选择不相邻的行）
		// tabDemo.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// 让用户能够同时选择单个单元格或者整个行
		// tabDemo.setCellSelectionEnabled(true);
		// 设置数据与单元格边框的眉边距
		tabDemo.setIntercellSpacing(new Dimension(20, 2));
		// 设置数据与单元格边框是否居中等
		setTableColumnCenter(tabDemo);
		// 设置每一列的高度
		tabDemo.setRowHeight(25);
		// 固定表格的列宽
//		 setFixColumnWidth(tabDemo);
		// 自定义列的宽度
//        TableColumn column = tabDemo.getColumnModel().getColumn(0);
//        column.setPreferredWidth(120);
		// 根据单元内的数据内容自动调整列宽
		fitTableColumns(tabDemo);
		// 将JTable加入到带纵向滚动条的面板中
		BackendFunctionCenterPageView.DataDisplayList.getViewport().add(tabDemo);
		// 如果JTable的一个列或者JTable窗口自身的大小被重新确定，那么其他列会被相应的缩小或者放大，以适应新的窗口。使用setAutoResizeMode()方法就能够控制这种行为：
		tabDemo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 横向滚动条
		tabDemo.setColumnSelectionAllowed(true);
		//悬浮提示单元格的值 
//		tabDemo.addMouseMotionListener(new MouseAdapter(){
//            public void mouseMoved(MouseEvent e) {
//                int row=tabDemo.rowAtPoint(e.getPoint());
//                int col=tabDemo.columnAtPoint(e.getPoint());
//                if(row>-1 && col>-1){
//                    Object value=tabDemo.getValueAt(row, col);
//                    if(null!=value && !"".equals(value))
//                        tabDemo.setToolTipText(value.toString());//悬浮显示单元格内容
//                    else
//                        tabDemo.setToolTipText(null);//关闭提示
//                }
//            }
//        }); 
	}
}