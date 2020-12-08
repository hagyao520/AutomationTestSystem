package AutomationTestSystem.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Enumeration;
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

import org.apache.log4j.Logger;

import javafx.application.Platform;
import AutomationTestSystem.Util.Constants;
import AutomationTestSystem.Util.DatabaseUtil;
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.StringUtil;
import AutomationTestSystem.View.BackendFunctionCenterPageView;

/**
 * @author King
 * @date 2018年8月1日 18:18:074
 */
@SuppressWarnings({ "unused", "unchecked", "rawtypes", "restriction" })
public class BackendFunctionCenterPageController {

	static Logger log = Logger.getLogger(BackendFunctionCenterPageController.class);

	private static DialogUtil Dialog = new DialogUtil();
	private static JTableHeader jth;
	private static JTable tabDemo;
	
	private static String djdbcDriver = "com.mysql.jdbc.Driver";
	private static String dhostAddress = "10.22.83.70";
	private static String duserName = "yhtsit";
	private static String dpassWord = "SitYht@300348";
	private static int dport = 3323;
	private static String ddataBase = "YHT_USER_SIT";

	public static void main(String[] args) throws Exception {
		DatabaseUtil.Connect_MySql(djdbcDriver, dhostAddress, dport, ddataBase, duserName, dpassWord);
		UserInfoButton("查询", "18694925429", "000025");
	}

	// 根据用户手机号，查询用户信息
	public static boolean UserInfoButton(String option, String phone, String corpno) throws Exception {
		boolean state = false;
		String sql = "SELECT " + "a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
				+ "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM user_base_info a,cust_base_info b,cust_bind_acct c,cust_acct_rela d "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct "
				+ "ORDER BY c.gmt_create DESC;";
		String sql0 = "SELECT " + "a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
				+ "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM user_base_info a,cust_base_info b,cust_bind_acct c,cust_acct_rela d "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.phonno='" + phone
				+ "' and c.corpno='" + corpno + "' " + "ORDER BY c.gmt_create DESC;";
		String sql1 = "SELECT " + "a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
				+ "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM user_base_info a,cust_base_info b,cust_bind_acct c,cust_acct_rela d "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and a.phonno='" + phone + "' "
				+ "ORDER BY c.gmt_create DESC;";
		String sql2 = "SELECT " + "a.usercd as 用户ID,a.custno as 客户号,a.phonno as 手机号,"
				+ "b.custna as 姓名,b.bhdate as 出生日期,b.idtfno as 身份证号码,b.idefdt as 开始日期,b.idexdt as 结束日期,b.issuat as 签发机关,b.idsite as 身份证住址,"
				+ "c.bdacct as 二类户卡号,c.bkstnm as 二类户所属银行,c.corpno as 二类户法人号,"
				+ "d.reacct as 二类户绑定银行卡,d.bkstnm  as 二类户绑定卡所属银行 "
				+ "FROM user_base_info a,cust_base_info b,cust_bind_acct c,cust_acct_rela d "
				+ "WHERE a.custno=b.custno and b.custno=c.custno and c.bdacct=d.bdacct and c.corpno='" + corpno + "' "
				+ "ORDER BY c.gmt_create DESC;";
		try {
			switch (option) {
			case "查询":
				if (StringUtil.isEmpty(phone) && StringUtil.isEmpty(corpno)) {
					state = Select(option, sql);
				} else if (StringUtil.isNotEmpty(phone) && StringUtil.isNotEmpty(corpno)) {
					state = Select(option, sql0);
				} else if (StringUtil.isNotEmpty(phone)) {
					state = Select(option, sql1);
				} else if (StringUtil.isNotEmpty(corpno)) {
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

	public static boolean Select(String option, String sql) throws Exception {
		try {
			DatabaseUtil.Connect();
//			DatabaseUtil.Connect_MySql(djdbcDriver, dhostAddress, dport, ddataBase, duserName, dpassWord);
			ResultSet res = DatabaseUtil.sm.executeQuery(sql);
			BackendFunctionCenterPageView.textArea.append("SQL：" + sql + "\n");
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
				tabDemo = new JTable(rows, columnHeads);
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
			DatabaseUtil.Connect();
//			DatabaseUtil.Connect_MySql(djdbcDriver, dhostAddress, dport, ddataBase, duserName, dpassWord);
			int results = DatabaseUtil.sm.executeUpdate(sql);
			BackendFunctionCenterPageView.textArea.append("SQL：" + sql + "\n");
			if (results >= 0) {
				log.info("语句: " + sql + " 执行成功，影响了" + results + "行数据");
				BackendFunctionCenterPageView.textArea.append("状态：" + option + "成功，影响了" + results + "行数据！" + "\n");
				BackendFunctionCenterPageView.textArea.append("\n");
			} else if (results == Statement.SUCCESS_NO_INFO) {
				log.info("语句: " + sql + " 执行成功，影响的行数未知");
				BackendFunctionCenterPageView.textArea.append("状态：" + option + "成功，影响的行数未知！" + "\n");
				BackendFunctionCenterPageView.textArea.append("\n");
			} else if (results == Statement.EXECUTE_FAILED) {
				log.info("语句: " + sql + " 执行失败");
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
				tabDemo = new JTable(rows, columnHeads); // 将获得的行列数据信息作为参数重新构造表格视图
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
		// 列的宽度
		// TableColumn column = tabDemo.getColumnModel().getColumn(0);
		// column.setPreferredWidth(300);
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
		tabDemo.setRowHeight(30);
		// 固定表格的列宽
		// setFixColumnWidth(tabDemo);
		// 根据单元内的数据内容自动调整列宽resize column width accordng to content of cell
		// automatically
		fitTableColumns(tabDemo);
		// 将JTable加入到带纵向滚动条的面板中
		BackendFunctionCenterPageView.DataDisplayList.getViewport().add(tabDemo);

		// 如果JTable的一个列或者JTable窗口自身的大小被重新确定，那么其他列会被相应的缩小或者放大，以适应新的窗口。使用setAutoResizeMode()方法就能够控制这种行为：
		tabDemo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 横向滚动条
		tabDemo.setColumnSelectionAllowed(true);
	}
}