package AutomationTestSystem.View;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.text.NumberFormat;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import AutomationTestSystem.StartClient;
import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemDatabaseConfiguration;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Config.UserInformationConfiguration;
import AutomationTestSystem.Config.UserSaveData;
import AutomationTestSystem.Config.UserSaveDataBox;
import AutomationTestSystem.Controller.BackendFunctionCenterPageController;
import AutomationTestSystem.Controller.LoginController;
import AutomationTestSystem.Util.Base64Encoder;
import AutomationTestSystem.Util.Constants;
import AutomationTestSystem.Util.DateUtil;
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.DragUtil;
import AutomationTestSystem.Util.HttpGetRequestUtil;
import AutomationTestSystem.Util.HttpPostRequestUtil;
import AutomationTestSystem.Util.JSONUtil;
import AutomationTestSystem.Util.JsonFormatUtil;
import AutomationTestSystem.Util.MyScrollBarUI;
import AutomationTestSystem.Util.StringUtil;
import io.restassured.path.json.JsonPath;

@SuppressWarnings({"restriction", "static-access", "unused","rawtypes"})
public class BackendFunctionCenterPageView extends Application {

    public static Stage HomePageInterfaceStage;
    // HomePageView.HomePageInterfaceStage.close();

    private static AnchorPane HomePagePane = new AnchorPane();

    private static DropShadow dropShadow = new DropShadow();
    private static DialogUtil Dialog = new DialogUtil();

    private static ImageView HeadImageView = new ImageView();
    private static Label UserNameLabel = new Label();
    private static Label UserTypeLabel = new Label();
    private static Button SwitchButton = new Button();
    private static Button SystemMasterInterfaceButton = new Button();
    private static Button FrontEndFunctionCenterButton = new Button();
    private static Button BackendFunctionCenterButton = new Button();
    private static Button WEBAutomationCenterButton = new Button();
    private static Button APIAutomationCenterButton = new Button();
    private static Button APPAutomationCenterButton = new Button();

    private static WebView VideoPlayerWebView = new WebView();
    private static Button VideoPlayerButton = new Button();
    private static AnchorPane HomePageRightPane = new AnchorPane();

    static AnchorPane BackendFunctionCenterPane = new AnchorPane();

    /** 主面板 */
    private static JPanel MainPanel;

    /** 数据展示列表 */
    public static JScrollPane DataDisplayList;

    /** 功能分类面板 */
    public static JTabbedPane jTabbedpane;

    public static JTextArea textArea = new JTextArea();
    static JScrollPane TextAreaScrollPane = new JScrollPane();

    /** 用户信息查询按钮 */
    private static JButton UserInfoButton;
    /** 用户信息查询输入框 */
    private static JTextField UserInfoTextField1;
    private static JTextField UserInfoTextField2;
    private static JTextField UserInfoTextField3;
    
    /** 短信验证码查询按钮 */
    private static JButton SmsInquiryButton;
    /** 短信验证码查询输入框 */
    private static JTextField SmsInquiryFieldTextField1;
    private static JTextField SmsInquiryFieldTextField2;
    private static JTextField SmsInquiryFieldTextField3;
    private static JTextField SmsInquiryFieldTextField4;

    /** 工商四要素验证按钮 */
    private static JButton Business_Button;
    /** 工商四要素验证输入框 */
    private static JTextField Business_FieldTextField1;
    private static JTextField Business_FieldTextField2;
    private static JTextField Business_FieldTextField3;
    private static JTextField Business_FieldTextField4;

    /** 宝生企业白名单按钮 */
    private static JButton BS_WhiteList_Button;
    /** 宝生企业白名单输入框 */
    private static JTextField BS_WhiteList_FieldTextField1;
    private static JTextField BS_WhiteList_FieldTextField2;

    /** 宝生客户清户按钮 */
    private static JButton BS_EnterpriseCredit_Button;
    /** 宝生客户清户输入框 */
    private static JTextField BS_EnterpriseCredit_FieldTextField1;
    private static JTextField BS_EnterpriseCredit_FieldTextField2;
    
    /** 宝生客户清户按钮 */
    private static JButton BS_UserClear_Button;
    /** 宝生客户清户输入框 */
    private static JTextField BS_UserClear_FieldTextField1;
    private static JTextField BS_UserClear_FieldTextField2;
    
    /** 宝生系统日志查询按钮 */
    private static JButton BS_SystemLog_Button;
    /** 宝生系统日志查询输入框 */
    private static JTextField BS_SystemLog_FieldTextField1;
    private static JTextField BS_SystemLog_FieldTextField2;

    /** 梅州企业白名单按钮 */
    private static JButton MZ_WhiteList_Button;
    /** 梅州企业白名单输入框 */
    private static JTextField MZ_WhiteList_FieldTextField1;
    private static JTextField MZ_WhiteList_FieldTextField2;

    /** 梅州客户清户按钮 */
    private static JButton MZ_EnterpriseCredit_Button;
    /** 梅州客户清户输入框 */
    private static JTextField MZ_EnterpriseCredit_FieldTextField1;
    private static JTextField MZ_EnterpriseCredit_FieldTextField2;
    
    /** 梅州客户清户按钮 */
    private static JButton MZ_UserClear_Button;
    /** 梅州客户清户输入框 */
    private static JTextField MZ_UserClear_FieldTextField1;
    private static JTextField MZ_UserClear_FieldTextField2;

    /** 梅州系统日志查询按钮 */
    private static JButton MZ_SystemLog_Button;
    /** 梅州系统日志查询输入框 */
    private static JTextField MZ_SystemLog_FieldTextField1;
    private static JTextField MZ_SystemLog_FieldTextField2;
    
    /** 云霄用户信息查询按钮 */
    private static JButton YX_UserInfoButton;
    /** 云霄用户信息查询输入框 */
    private static JTextField YX_UserInfoTextField1;
    private static JTextField YX_UserInfoTextField2;
    JComboBox<String> YX_UserInfo_JComboBox = new JComboBox<String>();
    
    /** 云霄客户清户按钮 */
    private static JButton YX_UserInfoButton1;
    /** 云霄客户清户输入框 */
    private static JTextField YX_UserClear_FieldTextField1;
    private static JTextField YX_UserClear_FieldTextField2;
    
    
    
    
    
    
    
    
    
    
    /** YAIP登录Cookie */
    private static String Cookie="";
    private static Map<String, Object> Headers = new LinkedHashMap<>();
    private static JsonPath group_list;
    private static JsonPath project_list;
    private static JsonPath process_list;
    private static JsonPath api_list;
    private static JsonPath mock_list;
    
    /** 新增 */
    static JRadioButton select = new JRadioButton("查询");
    static JRadioButton update = new JRadioButton("修改");
    static JRadioButton insert = new JRadioButton("新增");
    static JRadioButton delete = new JRadioButton("删除");
    static ButtonGroup operation = new ButtonGroup();

    /** SQLTxt */
    private static JLabel SQLLabel;
    /** SQL提示Txt */
    private static JLabel SQLPromptLabel;
    /** SQL输入框 */
    private static JTextArea SQLArea;
    /** SQL输入框滑轮 */
    private static JScrollPane SQLScroll;
    /** 查询SQL详情按钮 */
    private static JButton SQLButton;

    CopyOnWriteArraySet<EventHandler<ActionEvent>> closeActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();
    CopyOnWriteArraySet<EventHandler<ActionEvent>> iconifiedActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();

    SystemDatabaseConfiguration sdcd = (SystemDatabaseConfiguration)ConfigManage.get(SystemDatabaseConfiguration.path, SystemDatabaseConfiguration.class);
    SystemServerConfiguration ssc = (SystemServerConfiguration)ConfigManage.get(SystemServerConfiguration.path, SystemServerConfiguration.class);

    UserInformationConfiguration uic = (UserInformationConfiguration)ConfigManage.get(UserInformationConfiguration.path, UserInformationConfiguration.class);
    UserSaveDataBox usdb = (UserSaveDataBox)ConfigManage.get(UserSaveDataBox.path, UserSaveDataBox.class);

    int SystemSettingStageStatus = 0;
    // static String Account = LoginPageView.AccountField.getText();
    // static String PassWord = LoginPageView.PassWordField.getText();
    static String Account = "18688888888";
    static String PassWord = "qq123456";

    @Override
    public void start(Stage HomePageInterfaceStage) throws Exception {
        this.HomePageInterfaceStage = HomePageInterfaceStage;
        // 设置主窗口标题
        this.HomePageInterfaceStage.setTitle("HomePageInterface");
        // 设置主窗口图标
        // this.HomePageInterfaceStage.getIcons().add(new
        // Image(getClass().getResourceAsStream("images/address_book_32.png")));//获取images包中的图片
        this.HomePageInterfaceStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));// 获取resource下images中的图片
        // 去除主窗口外边框
        this.HomePageInterfaceStage.initStyle(StageStyle.TRANSPARENT);

        // 加载启动类
        HomePageInterface();
        
        // HomePageInterface("18688888888","qq123456");
        // BackendFunctionCenter("18688888888","qq123456");
    }

    public void HomePageInterface() throws Exception {
        BackendFunctionCenter();
        // Stage HomePageInterfaceStage = new Stage();
        HomePageInterfaceStage = new Stage();
        HomePageInterfaceStage.initStyle(StageStyle.TRANSPARENT);
        HomePageInterfaceStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));// 获取resource下images中的图片

        StackPane HomePageStack = new StackPane();
        // HomePageStack.setLayoutX(100);
        // HomePageStack.setLayoutY(20);
        HomePageStack.getStylesheets().add(this.getClass().getResource("/css/HomePage.css").toString());

        HomePagePane.setPrefWidth(1600);
        HomePagePane.setPrefHeight(1000);
        HomePagePane.setId("HomePagePane");

        // WebView HomePageBackgroundWebView = new WebView();
        // HomePageBackgroundWebView.setLayoutX(0);
        // HomePageBackgroundWebView.setLayoutY(0);
        // HomePageBackgroundWebView.setPrefSize(1600, 1000);
        //
        // WebEngine HomePageBackgroundWebEngine = HomePageBackgroundWebView.getEngine();
        // HomePageBackgroundWebEngine.load(this.getClass().getResource("/html/HomePage/Background/index.html").toString());

        AnchorPane TopPane = new AnchorPane();
        TopPane.setLayoutX(0);
        TopPane.setLayoutY(0);
        TopPane.setPrefWidth(1600);
        TopPane.setPrefHeight(112);
        TopPane.setId("TopPane");

        Label VersionNumberLabel = new Label();
        VersionNumberLabel.setLayoutX(792);
        VersionNumberLabel.setLayoutY(68);
        VersionNumberLabel.setText("Version 1.0.4");
        VersionNumberLabel.setId("VersionNumberText");

        // Button SettingButton = new Button();
        // SettingButton.setId("SettingButton");
        // SettingButton.setPrefWidth(30);
        // SettingButton.setPrefHeight(27);
        // SettingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent event) {
        // HomePageInterfaceStage.close();
        // SystemSettingInterface();
        // SystemSettingStageStatus=1;
        // }
        // });

        Button MinimizationButton = new Button();
        MinimizationButton.setId("MinimizationButton");
        MinimizationButton.setPrefWidth(30);
        MinimizationButton.setPrefHeight(27);
        MinimizationButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HomePageInterfaceStage.hide();
                Platform.setImplicitExit(false);
            }
        });

        Button CloseButton = new Button();
        CloseButton.setId("CloseButton");
        CloseButton.setPrefWidth(30);
        CloseButton.setPrefHeight(27);
        CloseButton.setOnAction((ActionEvent actionEvent) -> {
            if (!closeActionSet.isEmpty()) {
                for (EventHandler<ActionEvent> e : closeActionSet) {
                    e.handle(actionEvent);
                }
            } else {
                System.exit(0);
            }
        });

        HBox MinCloseHox = new HBox();
        MinCloseHox.setLayoutX(1540);
        MinCloseHox.setLayoutY(0);
        MinCloseHox.setBackground(Background.EMPTY);
        MinCloseHox.setPickOnBounds(false);// 面板不参与计算边界，透明区域无鼠标事件
        MinCloseHox.getChildren().addAll(MinimizationButton, CloseButton);

        WebView TimeControlWebView = new WebView();
        TimeControlWebView.setLayoutX(1200);
        TimeControlWebView.setLayoutY(20);
        TimeControlWebView.setPrefSize(320, 75);

        WebEngine TimeControlWebEngine = TimeControlWebView.getEngine();
        TimeControlWebEngine.load(this.getClass().getResource("/html/HomePage/TimeControl/index.html").toString());

        TopPane.getChildren().add(VersionNumberLabel);
        TopPane.getChildren().add(TimeControlWebView);
        TopPane.getChildren().add(MinCloseHox);

        AnchorPane LeftPane = new AnchorPane();
        LeftPane.setLayoutX(0);
        LeftPane.setLayoutY(115);
        LeftPane.setPrefWidth(216);
        LeftPane.setPrefHeight(886);
        LeftPane.setId("LeftPane");

        Rectangle HeadRectangle = new Rectangle();
        HeadRectangle.setArcHeight(5);
        HeadRectangle.setArcWidth(5);
        HeadRectangle.setWidth(50);
        HeadRectangle.setHeight(50);

        UserSaveData usd = usdb.get(Account);
        Image HeadIamge = new Image(usd.getHead(), 50, 50, true, false);
        HeadImageView.setLayoutX(20);
        HeadImageView.setLayoutY(10);
        // String avatar =
        // this.getClass().getResource("/image/LoginPane/HeadPortraitImage/HeadPortrait1.gif").toExternalForm();
        // Image HeadIamge1 = new Image(avatar, 50,50,true, false);
        HeadImageView.setImage(HeadIamge);
        HeadImageView.setClip(HeadRectangle);

        UserNameLabel.setLayoutX(79);
        UserNameLabel.setLayoutY(12);
        UserNameLabel.setId("UserNameLabel");
        // Object[] UserLoginInfo =LoginController.WebUserLoginInfo(Account, PassWord);
        // String UserName = (String) UserLoginInfo[0];
        // UserNameLabel.setText(UserName);
        UserSaveData usd1 = usdb.get(Account);
        String UserName = usd1.getPassWord();
        UserNameLabel.setText(UserName);
        // UserNameLabel.setText("小 智");//无验证

        UserTypeLabel.setLayoutX(80);
        UserTypeLabel.setLayoutY(38);
        UserTypeLabel.setId("UserTypeLabel");
        // String UserType =String.valueOf((int) UserLoginInfo[2]) ;
        // if("1".equals(UserType)){
        // UserTypeLabel.setText("游 客");
        // }if("2".equals(UserType)){
        // UserTypeLabel.setText("旅 行 社");
        // }
        UserTypeLabel.setText("体 验 者");// 无验证

        // System.out.println(UserNameLabel.getText().length());
        if (UserNameLabel.getText().length() > 10) {
            SwitchButton.setLayoutX(165);
            SwitchButton.setLayoutY(39);
        } else {
            SwitchButton.setLayoutX(165);
            SwitchButton.setLayoutY(22);
        }
        SwitchButton.setPrefWidth(20);
        SwitchButton.setPrefHeight(20);
        SwitchButton.setId("SwitchButton");
        SwitchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HomePageInterfaceStage.close();
                LoginPageView.LoginStatus = 1;
                LoginPageView.LoginInterfaceStage.show();
            }
        });

        SystemMasterInterfaceButton.setLayoutX(0);
        SystemMasterInterfaceButton.setLayoutY(115);
        SystemMasterInterfaceButton.setPrefWidth(219);
        SystemMasterInterfaceButton.setPrefHeight(50);
        SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
        SystemMasterInterfaceButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SystemMasterInterfaceButton.setId("SystemMasterInterfaceButtonDown");
                BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
                HomePageRightPane.setVisible(true);
                BackendFunctionCenterPane.setVisible(false);
            }
        });

        BackendFunctionCenterButton.setLayoutX(0);
        BackendFunctionCenterButton.setLayoutY(115 + 50);
        BackendFunctionCenterButton.setPrefWidth(219);
        BackendFunctionCenterButton.setPrefHeight(50);
        BackendFunctionCenterButton.setId("BackendFunctionCenterButtonDown");
        BackendFunctionCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
                BackendFunctionCenterButton.setId("BackendFunctionCenterButtonDown");
                HomePageRightPane.setVisible(false);
                BackendFunctionCenterPane.setVisible(true);
            }
        });

        LeftPane.getChildren().add(HeadImageView);
        LeftPane.getChildren().add(UserNameLabel);
        LeftPane.getChildren().add(UserTypeLabel);
        LeftPane.getChildren().add(SwitchButton);
        LeftPane.getChildren().add(SystemMasterInterfaceButton);
        LeftPane.getChildren().add(BackendFunctionCenterButton);

        HomePageRightPane.setLayoutX(222);
        HomePageRightPane.setLayoutY(115);
        HomePageRightPane.setPrefWidth(1381);
        HomePageRightPane.setPrefHeight(886);
        HomePageRightPane.setId("HomePageRightPane");

        WebView BackgroundWebView = new WebView();
        BackgroundWebView.setLayoutX(0);
        BackgroundWebView.setLayoutY(0);
        BackgroundWebView.setPrefSize(1381, 886);

        WebEngine BackgroundWebEngine = BackgroundWebView.getEngine();
        BackgroundWebEngine.load(this.getClass().getResource("/html/HomePage/Animation/index.html").toString());

        // Image VideoPlayerIamge = new
        // Image(this.getClass().getResource("/image/HomePagePane/HomePageRightPane/VideoPlayer_Background_1381x886.png").toExternalForm(),
        // true);
        // ImageView VideoPlayerIamgeView = new ImageView();
        // VideoPlayerIamgeView.setLayoutX(0);
        // VideoPlayerIamgeView.setLayoutY(0);
        // VideoPlayerIamgeView.setImage(VideoPlayerIamge);
        // VideoPlayerIamgeView.setEffect(dropShadow);//增加阴影效果
        //
        // Rectangle VideoPlayerRectangle = new Rectangle();
        // VideoPlayerRectangle.setArcHeight(10);
        // VideoPlayerRectangle.setArcWidth(10);
        // VideoPlayerRectangle.setWidth(1381);
        // VideoPlayerRectangle.setHeight(886);

        VideoPlayerWebView.setLayoutX(355);
        VideoPlayerWebView.setLayoutY(313);
        VideoPlayerWebView.setPrefSize(700, 488);
        VideoPlayerWebView.setVisible(false);

        WebEngine VideoPlayerWebEngine = VideoPlayerWebView.getEngine();
        VideoPlayerWebEngine.load(this.getClass().getResource("/html/HomePage/VideoPlayer/index.html").toString());

        Button VideoPlayerCloseButton = new Button();
        VideoPlayerCloseButton.setLayoutX(370 + 700 - 45);
        VideoPlayerCloseButton.setLayoutY(313);
        VideoPlayerCloseButton.setPrefWidth(30);
        VideoPlayerCloseButton.setPrefHeight(27);
        VideoPlayerCloseButton.setId("VideoPlayerCloseButton");
        VideoPlayerCloseButton.setVisible(false);
        VideoPlayerCloseButton.setOnAction((ActionEvent actionEvent) -> {
            VideoPlayerWebView.setVisible(false);
            VideoPlayerCloseButton.setVisible(false);
        });

        VideoPlayerButton.setLayoutX(270);
        VideoPlayerButton.setLayoutY(650 + 73);
        VideoPlayerButton.setPrefWidth(80);
        VideoPlayerButton.setPrefHeight(79);
        VideoPlayerButton.setId("VideoPlayerButton");
        VideoPlayerButton.setVisible(false);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                VideoPlayerButton.setVisible(true);
                VideoPlayerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        VideoPlayerWebView.setVisible(true);
                        VideoPlayerCloseButton.setVisible(true);
                    }
                });
            }
        }, 19000);

        WebView PublisherWebView = new WebView();
        PublisherWebView.setLayoutX(0);
        PublisherWebView.setLayoutY(833);
        PublisherWebView.setPrefSize(1381, 50);

        WebEngine PublisherWebEngine = PublisherWebView.getEngine();
        PublisherWebEngine.load(this.getClass().getResource("/html/HomePage/Background/index.html").toString());

        HomePageRightPane.getChildren().add(BackgroundWebView);
        HomePageRightPane.getChildren().add(VideoPlayerWebView);
        HomePageRightPane.getChildren().add(VideoPlayerButton);
        HomePageRightPane.getChildren().add(VideoPlayerCloseButton);
        HomePageRightPane.getChildren().add(PublisherWebView);

        HomePagePane.getChildren().add(TopPane);
        HomePagePane.getChildren().add(LeftPane);
        // HomePagePane.getChildren().add(HomePageRightPane);

        Rectangle HomePageRectangle = new Rectangle();
        HomePageRectangle.setWidth(1600);
        HomePageRectangle.setHeight(1000);
        HomePageRectangle.setArcHeight(10);
        HomePageRectangle.setArcWidth(10);

        HomePageStack.getChildren().addAll(HomePagePane);
        HomePageStack.setClip(HomePageRectangle);

        // 拖动监听器
        DragUtil.addDragListener(HomePageInterfaceStage, HomePageStack);

        // 构建一个场景Scene,加载包含根布局的场景
        Scene scene = new Scene(HomePageStack);
        // Scene透明
        scene.setFill(null);

        // scene加载到新的Stage中
        HomePageInterfaceStage.setScene(scene);
        // 设置让对话框处于屏幕中间
        HomePageInterfaceStage.centerOnScreen();
        // 显示布局
        HomePageInterfaceStage.show();
    }

    public static void BackendFunctionCenter() throws Exception {
        SwingNode swingNode = new SwingNode();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // try {
                // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                // } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                // UnsupportedLookAndFeelException e) {
                // e.printStackTrace();
                // }
                JPanel MainPanel = new JPanel();
                MainPanel.setSize(1381, 886);
                MainPanel.setLayout(null);
                MainPanel.setBorder(Constants.GRAY_BORDER);

                DataDisplayList = new JScrollPane();
                DataDisplayList.setBounds(5, 5, 1368, 500);
                DataDisplayList.setOpaque(false);// 设置背景是否透明
                DataDisplayList.getViewport().setOpaque(false);
                // DataDisplayList.getVerticalScrollBar().setUI(new MyScrollBarUI());//设置UI

                jTabbedpane = new JTabbedPane();
                jTabbedpane.setBounds(5, 510, 755, 375);
                jTabbedpane.setFont(Constants.BASIC_BOLD);
                if(StartClient.state){
                    LoginPageView.DDataBaseField.setText("YHT_USER_"+StartClient.Environment+"");
                }
                jTabbedpane.addTab("用户", null, YHT_USER_Panel(), "YHT_USER");
                jTabbedpane.addTab("APP", null, YHT_APP_Panel(), "YHT_APP");
                jTabbedpane.addTab("前置", null, YHT_EDGE_Panel(), "YHT_EDGE");
                jTabbedpane.addTab("宝生", null, BS_LOAN_Panel(), "BS_LOAN");
                jTabbedpane.addTab("梅州", null, MZKJ_LOAN_Panel(), "MZKJ_LOAN");
                jTabbedpane.addTab("云霄", null, YHT_XYUN_Panel(), "YHT_XYUN");
                jTabbedpane.addTab("AES加解密", null, MZKJ_AES_Panel(), "MZKJ_LOAN");
                jTabbedpane.addTab("Mock数据", null, YAPI_Mock_Panel(), "YAPI_MOCK");
                if(StartClient.state){
                    jTabbedpane.addChangeListener(new javax.swing.event.ChangeListener() {
                        @Override
                        public void stateChanged(javax.swing.event.ChangeEvent e) {
                            int index = jTabbedpane.getSelectedIndex();
                            String title = jTabbedpane.getTitleAt(index);
                            String tip = jTabbedpane.getToolTipTextAt(index);
                            String environment = LoginPageView.EnvironmentModeBox.getSelectionModel().getSelectedItem().toString();
                            switch (environment) {
                                case "正式环境":
                                    LoginPageView.DDataBaseField.setText(tip + "_PRO");
                                    break;
                                case "开发环境":
                                    LoginPageView.DDataBaseField.setText(tip + "_DEV");
                                    break;
                                case "测试环境":
                                    LoginPageView.DDataBaseField.setText(tip + "_SIT");
                                    break;
                                case "验收环境":
                                    LoginPageView.DDataBaseField.setText(tip + "_UAT");
                                    break;
                            }
                        }
                    });
                }

                JPanel OperationPanel = new JPanel(new GridLayout(0, 1));
                // OperationPanel.setBorder(Constants.GRAY_BORDER);
                OperationPanel.setBounds(760, 515, 60, 370);

                // operation=new ButtonGroup();

                select.setFont(Constants.BASIC_BOLD);
                select.setActionCommand(select.getText());
                select.setSelected(true);

                update.setFont(Constants.BASIC_BOLD);
                update.setActionCommand(update.getText());

                insert.setFont(Constants.BASIC_BOLD);
                insert.setActionCommand(insert.getText());

                delete.setFont(Constants.BASIC_BOLD);
                delete.setActionCommand(delete.getText());

                operation.add(select);
                operation.add(update);
                operation.add(insert);
                operation.add(delete);
                OperationPanel.add(select);
                OperationPanel.add(update);
                OperationPanel.add(insert);
                OperationPanel.add(delete);

                class SexActionListener implements ActionListener {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        String option = operation.getSelection().getActionCommand();
//                        System.out.println("选择了****-----: " + option);
                    }
                }

                ActionListener alisten = new SexActionListener();
                select.addActionListener(alisten);
                update.addActionListener(alisten);
                insert.addActionListener(alisten);
                delete.addActionListener(alisten);

                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setSelectedTextColor(Color.RED);
                textArea.setFont(Constants.BASIC_BOLD);

                TextAreaScrollPane = new JScrollPane(textArea);
                TextAreaScrollPane.setBounds(825, 515, 549, 370);

                MainPanel.add(DataDisplayList);
                MainPanel.add(jTabbedpane);
                MainPanel.add(OperationPanel);
                MainPanel.add(TextAreaScrollPane);

                swingNode.setContent(MainPanel);
            }
        });

        BackendFunctionCenterPane.setLayoutX(222);
        BackendFunctionCenterPane.setLayoutY(115);
        BackendFunctionCenterPane.setPrefWidth(1381);
        BackendFunctionCenterPane.setPrefHeight(886);
        // BackendFunctionCenterPane.setId("BackendFunctionCenterPane");
        BackendFunctionCenterPane.setVisible(false);

        BackendFunctionCenterPane.getChildren().add(swingNode);

        HomePagePane.getChildren().add(BackendFunctionCenterPane);
        // HomePageView.HomePagePane.getChildren().add(BackendFunctionCenterPane);
    }

    private static JPanel YHT_USER_Panel() {

        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JComboBox<String> UserInfoType_JComboBox = new JComboBox<String>();
        
        UserInfoButton = new JButton("用户开户信息");
        UserInfoButton.setFont(Constants.BASIC_BOLD);
        UserInfoButton.setBounds(10, 10, 140, 25);
        UserInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();
                    String UserInfoType = UserInfoType_JComboBox.getSelectedItem().toString();
                    
                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(UserInfoTextField1);
                    list.add(UserInfoTextField2);
                    list.add(UserInfoTextField3);
                    tips.put("请输入法人号...","false");
                    tips.put("请输入手机号...","true");
                    tips.put("请输入变更手机号...","true");
                    
                    listpar.add("corpno");
                    listpar.add("phone");
                    listpar.add("tphone");
                    
                    List<JComboBox> JComboBox_List = new ArrayList<JComboBox>();
                    LinkedHashMap<String,String> JComboBox_MapValue = new LinkedHashMap<>();
                    
                    JComboBox_List.add(UserInfoType_JComboBox);
                    JComboBox_MapValue.put("请选择用户修改类型...","true");
                    
                    boolean state = getaddActionListener(UserInfoButton, option, list, tips,JComboBox_List,JComboBox_MapValue);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.UserInfoButton(option, listmap.get("corpno"),listmap.get("phone"),listmap.get("tphone"),UserInfoType);
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        UserInfoTextField1 = addJTextField(160, 10, 130, 25);
        UserInfoTextField2 = addJTextField(300, 10, 130, 25);
        UserInfoTextField3 = addJTextField(440, 10, 130, 25);
        
        UserInfoType_JComboBox.addItem("请选择用户修改类型...");
        UserInfoType_JComboBox.addItem("用户替换");
        UserInfoType_JComboBox.addItem("用户还原");
        UserInfoType_JComboBox.setBounds(580, 10, 150, 25);
        UserInfoType_JComboBox.setFont(Constants.BASIC_BOLD);
        UserInfoType_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if(StartClient.state){
                    String DDataBaseField = LoginPageView.DDataBaseField.getText();
                    switch(item.getItem().toString()){
                        case "用户替换" :
                            LoginPageView.DDataBaseField.setText(StringUtil.ReplaceTopFew(DDataBaseField, 4, "YHT_ORDER"));
                            break;
                        case "用户还原" :
                            LoginPageView.DDataBaseField.setText(StringUtil.ReplaceTopFew(DDataBaseField, 4, "YHT_ORDER"));
                            break;
                    }
                }
            }
        });
        
        SmsInquiryButton = new JButton("短信查询");
        SmsInquiryButton.setFont(Constants.BASIC_BOLD);
        SmsInquiryButton.setBounds(10, 45, 140, 25);
        SmsInquiryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {

            }
        });

        SmsInquiryFieldTextField1 = addJTextField(160, 45, 130, 25);
        SmsInquiryFieldTextField2 = addJTextField(300, 45, 130, 25);
        SmsInquiryFieldTextField3 = addJTextField(440, 45, 130, 25);
        SmsInquiryFieldTextField4 = addJTextField(580, 45, 140, 25);

        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(UserInfoButton);
        ButtonPanel.add(UserInfoTextField1);
        ButtonPanel.add(UserInfoTextField2);
        ButtonPanel.add(UserInfoTextField3);
        ButtonPanel.add(UserInfoType_JComboBox);
        
//        ButtonPanel.add(SmsInquiryButton);
//        ButtonPanel.add(SmsInquiryFieldTextField1);
//        ButtonPanel.add(SmsInquiryFieldTextField2);
//        ButtonPanel.add(SmsInquiryFieldTextField3);
//        ButtonPanel.add(SmsInquiryFieldTextField4);

        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }

    private static JPanel YHT_APP_Panel() {
        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        SmsInquiryButton = new JButton("短信验证码");
        SmsInquiryButton.setFont(Constants.BASIC_BOLD);
        SmsInquiryButton.setBounds(10, 10, 140, 25);
        SmsInquiryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    
                    list.add(SmsInquiryFieldTextField1);
                    tips.put("请输入手机号...","true");
                    listpar.add("phone");
                    
                    boolean state = getaddActionListener(SmsInquiryButton, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        String phone = list.get(0).getText();
                        boolean SqlState = BackendFunctionCenterPageController.SmsInquiryButton(option, listmap.get("phone"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        SmsInquiryFieldTextField1 = addJTextField(160, 10, 130, 25);
        SmsInquiryFieldTextField2 = addJTextField(300, 10, 130, 25);
        SmsInquiryFieldTextField3 = addJTextField(440, 10, 130, 25);
        SmsInquiryFieldTextField4 = addJTextField(580, 10, 140, 25);

        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(SmsInquiryButton);
        ButtonPanel.add(SmsInquiryFieldTextField1);
        ButtonPanel.add(SmsInquiryFieldTextField2);
        ButtonPanel.add(SmsInquiryFieldTextField3);
        ButtonPanel.add(SmsInquiryFieldTextField4);

        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }

    private static JPanel YHT_EDGE_Panel() {
        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        Business_Button = new JButton("工商四要素验证");
        Business_Button.setFont(Constants.BASIC_BOLD);
        Business_Button.setBounds(10, 10, 140, 25);
        Business_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(Business_FieldTextField1);
                    list.add(Business_FieldTextField2);
                    tips.put("请输入身份证号码...","true");
                    tips.put("请输入企业工商注册号...","true");
                    listpar.add("idtfno");
                    listpar.add("regnum");
                    
                    boolean state = getaddActionListener(Business_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.Business_Button(option, listmap.get("idtfno"), listmap.get("regnum"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Business_FieldTextField1 = addJTextField(160, 10, 150, 25);
        Business_FieldTextField2 = addJTextField(320, 10, 150, 25);
        Business_FieldTextField3 = addJTextField(480, 10, 120, 25);
        Business_FieldTextField4 = addJTextField(610, 10, 120, 25);

        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(Business_Button);
        ButtonPanel.add(Business_FieldTextField1);
        ButtonPanel.add(Business_FieldTextField2);
        ButtonPanel.add(Business_FieldTextField3);
        ButtonPanel.add(Business_FieldTextField4);

        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }

    private static JPanel BS_LOAN_Panel() {
        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        BS_WhiteList_Button = new JButton("企业白名单");
        BS_WhiteList_Button.setFont(Constants.BASIC_BOLD);
        BS_WhiteList_Button.setBounds(10, 10, 140, 25);
        BS_WhiteList_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(BS_WhiteList_FieldTextField1);
                    list.add(BS_WhiteList_FieldTextField2);
                    tips.put("请输入企业名称...","true");
                    tips.put("请输入企业信用代码...","true");
                    listpar.add("firmna");
                    listpar.add("nsrsbh");
                    
                    boolean state = getaddActionListener(BS_WhiteList_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.WhiteList_Button(option, listmap.get("firmna"), listmap.get("nsrsbh"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        BS_WhiteList_FieldTextField1 = addJTextField(160, 10, 150, 25);
        BS_WhiteList_FieldTextField2 = addJTextField(320, 10, 150, 25);

        BS_EnterpriseCredit_Button = new JButton("企业授信");
        BS_EnterpriseCredit_Button.setFont(Constants.BASIC_BOLD);
        BS_EnterpriseCredit_Button.setBounds(10, 45, 140, 25);
        BS_EnterpriseCredit_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(BS_EnterpriseCredit_FieldTextField1);
                    list.add(BS_EnterpriseCredit_FieldTextField2);
                    tips.put("请输入客户姓名...","true");
                    tips.put("请输入企业信用代码...","true");
                    listpar.add("cobjna");
                    listpar.add("nsrsbh");
                    
                    boolean state = getaddActionListener(BS_EnterpriseCredit_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.EnterpriseCredit_Button(option, listmap.get("cobjna"), listmap.get("nsrsbh"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        BS_EnterpriseCredit_FieldTextField1 = addJTextField(160, 45, 150, 25);
        BS_EnterpriseCredit_FieldTextField2 = addJTextField(320, 45, 150, 25);
        
        BS_UserClear_Button = new JButton("客户清户");
        BS_UserClear_Button.setFont(Constants.BASIC_BOLD);
        BS_UserClear_Button.setBounds(10, 80, 140, 25);
        BS_UserClear_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(BS_UserClear_FieldTextField1);
                    list.add(BS_UserClear_FieldTextField2);
                    tips.put("请输入客户身份证号...","true");
                    tips.put("请输入企业信用代码...","true");;
                    listpar.add("id_card");
                    listpar.add("nsrsbh");
                    
                    boolean state = getaddActionListener(BS_UserClear_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.UserClear_Button(option, listmap.get("id_card"), listmap.get("nsrsbh"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        BS_UserClear_FieldTextField1 = addJTextField(160, 80, 150, 25);
        BS_UserClear_FieldTextField2 = addJTextField(320, 80, 150, 25);
        
        BS_SystemLog_Button = new JButton("系统日志");
        BS_SystemLog_Button.setFont(Constants.BASIC_BOLD);
        BS_SystemLog_Button.setBounds(10, 115, 140, 25);
        BS_SystemLog_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(BS_SystemLog_FieldTextField1);
                    list.add(BS_SystemLog_FieldTextField2);
                    tips.put("请输入开始时间...","true");
                    tips.put("请输入结束时间...","true");
                    listpar.add("start");
                    listpar.add("end");
                    
                    boolean state = getaddActionListener(MZ_SystemLog_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.SystemLog_Button(option, listmap.get("start"), listmap.get("end"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        BS_SystemLog_FieldTextField1 = addJTextField(160, 115, 150, 25);
        BS_SystemLog_FieldTextField2 = addJTextField(320, 115, 150, 25);
        
        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(BS_WhiteList_Button);
        ButtonPanel.add(BS_WhiteList_FieldTextField1);
        ButtonPanel.add(BS_WhiteList_FieldTextField2);

        ButtonPanel.add(BS_EnterpriseCredit_Button);
        ButtonPanel.add(BS_EnterpriseCredit_FieldTextField1);
        ButtonPanel.add(BS_EnterpriseCredit_FieldTextField2);
        
        ButtonPanel.add(BS_UserClear_Button);
        ButtonPanel.add(BS_UserClear_FieldTextField1);
        ButtonPanel.add(BS_UserClear_FieldTextField2);
        
        ButtonPanel.add(BS_SystemLog_Button);
        ButtonPanel.add(BS_SystemLog_FieldTextField1);
        ButtonPanel.add(BS_SystemLog_FieldTextField2);
        
        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }
    
    private static JPanel YHT_XYUN_Panel() {
        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JComboBox<String> UserType_JComboBox = new JComboBox<String>();
        LinkedHashMap<String,String> JComboBox_ListMap = new LinkedHashMap<>();
        
        YX_UserInfoButton = new JButton("用户信息");
        YX_UserInfoButton.setFont(Constants.BASIC_BOLD);
        YX_UserInfoButton.setBounds(10, 10, 140, 25);
        YX_UserInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();
                    
                    String UserType = UserType_JComboBox.getSelectedItem().toString();
   
                    List<JTextField> JTextField_List = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> JTextField_MapValue = new LinkedHashMap<>();
                    List<String> JTextField_ListMapKey = new ArrayList<String>();
                    LinkedHashMap<String,String> JTextField_ListMap = new LinkedHashMap<>();
                    List<String> JTextField_ListKey = new ArrayList<String>();
                    
                    JTextField_List.add(YX_UserInfoTextField1);
                    JTextField_MapValue.put("请输入手机号...","true");
                    JTextField_ListMapKey.add("phone");
                    
                    List<JComboBox> JComboBox_List = new ArrayList<JComboBox>();
                    LinkedHashMap<String,String> JComboBox_MapValue = new LinkedHashMap<>();
                    List<String> JComboBox_ListKey = new ArrayList<String>();
                    
                    JComboBox_List.add(UserType_JComboBox);
                    JComboBox_MapValue.put("请选择用户类型...","true");
                    
                    boolean state = getaddActionListener(YX_UserInfoButton, option, JTextField_List, JTextField_MapValue,JComboBox_List,JComboBox_MapValue);
                    if (state) {
                        for (int i = 0; i < JTextField_List.size(); i++) {
                            for (String key : JTextField_MapValue.keySet()) {
                                JTextField_ListKey.add(key);
                            } 
                            if (StringUtil.isEqual(JTextField_List.get(i).getText(), JTextField_ListKey.get(i))) {
                                JTextField_ListMap.put(JTextField_ListMapKey.get(i), "");
                            }else{
                                JTextField_ListMap.put(JTextField_ListMapKey.get(i), JTextField_List.get(i).getText());
                            }
                        }
                        for (int i = 0; i < JComboBox_List.size(); i++) {
                            for (String key : JComboBox_MapValue.keySet()) {
                                JComboBox_ListKey.add(key);
                            } 
                            if (StringUtil.isEqual(UserType, JComboBox_ListKey.get(i))) {
                                for (String key : JComboBox_ListMap.keySet()) {
                                    JComboBox_ListMap.put(key, "");
                                } 
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.YX_UserInfoButton(option, JTextField_ListMap.get("phone"),JComboBox_ListMap.get("user_type"),JComboBox_ListMap.get("personnel_type"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        YX_UserInfoTextField1 = addJTextField(160, 10, 150, 25);

        UserType_JComboBox.addItem("请选择用户类型...");
        UserType_JComboBox.addItem("外部用户-->游客");
        UserType_JComboBox.addItem("内部用户-->客户经理");
//        UserType_JComboBox.addItem("普通用户-->普通用户");
        UserType_JComboBox.setBounds(320, 10, 150, 25);
        UserType_JComboBox.setFont(Constants.BASIC_BOLD);
        UserType_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                switch(item.getItem().toString()){
                    case "外部用户-->游客" :
                        JComboBox_ListMap.put("user_type","02");
                        JComboBox_ListMap.put("personnel_type","20");
                        break;
                    case "内部用户-->客户经理" :
                        JComboBox_ListMap.put("user_type","01");
                        JComboBox_ListMap.put("personnel_type","10");
                        break;
                    case "普通用户-->普通用户" :
                        JComboBox_ListMap.put("user_type","03");
                        JComboBox_ListMap.put("personnel_type","20");
                        break;
                }
            }
        });
        
        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(YX_UserInfoButton);
        ButtonPanel.add(YX_UserInfoTextField1);
        ButtonPanel.add(UserType_JComboBox);
        
//        ButtonPanel.add(YX_UserClear_Button);
//        ButtonPanel.add(YX_UserClear_FieldTextField1);
//        
//        ButtonPanel.add(MZ_UserClear_Button);
//        ButtonPanel.add(MZ_UserClear_FieldTextField1);
//        ButtonPanel.add(MZ_UserClear_FieldTextField2);
//        
//        ButtonPanel.add(MZ_SystemLog_Button);
//        ButtonPanel.add(MZ_SystemLog_FieldTextField1);
//        ButtonPanel.add(MZ_SystemLog_FieldTextField2);
        
        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }
    
    private static JPanel MZKJ_LOAN_Panel() {
        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        MZ_WhiteList_Button = new JButton("企业白名单");
        MZ_WhiteList_Button.setFont(Constants.BASIC_BOLD);
        MZ_WhiteList_Button.setBounds(10, 10, 140, 25);
        MZ_WhiteList_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(MZ_WhiteList_FieldTextField1);
                    list.add(MZ_WhiteList_FieldTextField2);
                    tips.put("请输入企业名称...","true");
                    tips.put("请输入企业信用代码...","true");
                    listpar.add("firmna");
                    listpar.add("nsrsbh");
                    
                    boolean state = getaddActionListener(MZ_WhiteList_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.WhiteList_Button(option, listmap.get("firmna"), listmap.get("nsrsbh"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        MZ_WhiteList_FieldTextField1 = addJTextField(160, 10, 150, 25);
        MZ_WhiteList_FieldTextField2 = addJTextField(320, 10, 150, 25);

        MZ_EnterpriseCredit_Button = new JButton("企业授信");
        MZ_EnterpriseCredit_Button.setFont(Constants.BASIC_BOLD);
        MZ_EnterpriseCredit_Button.setBounds(10, 45, 140, 25);
        MZ_EnterpriseCredit_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(MZ_EnterpriseCredit_FieldTextField1);
                    list.add(MZ_EnterpriseCredit_FieldTextField2);
                    tips.put("请输入客户姓名...","true");
                    tips.put("请输入企业信用代码...","true");
                    listpar.add("cobjna");
                    listpar.add("nsrsbh");
                    
                    boolean state = getaddActionListener(MZ_EnterpriseCredit_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.EnterpriseCredit_Button(option, listmap.get("cobjna"), listmap.get("nsrsbh"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        MZ_EnterpriseCredit_FieldTextField1 = addJTextField(160, 45, 150, 25);
        MZ_EnterpriseCredit_FieldTextField2 = addJTextField(320, 45, 150, 25);
        
        MZ_UserClear_Button = new JButton("客户清户");
        MZ_UserClear_Button.setFont(Constants.BASIC_BOLD);
        MZ_UserClear_Button.setBounds(10, 80, 140, 25);
        MZ_UserClear_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(MZ_UserClear_FieldTextField1);
                    list.add(MZ_UserClear_FieldTextField2);
                    tips.put("请输入客户身份证号...","true");
                    tips.put("请输入企业信用代码...","true");
                    listpar.add("id_card");
                    listpar.add("nsrsbh");
                    
                    boolean state = getaddActionListener(MZ_UserClear_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.UserClear_Button(option, listmap.get("id_card"), listmap.get("nsrsbh"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        MZ_UserClear_FieldTextField1 = addJTextField(160, 80, 150, 25);
        MZ_UserClear_FieldTextField2 = addJTextField(320, 80, 150, 25);
        
        MZ_SystemLog_Button = new JButton("系统日志");
        MZ_SystemLog_Button.setFont(Constants.BASIC_BOLD);
        MZ_SystemLog_Button.setBounds(10, 115, 140, 25);
        MZ_SystemLog_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    LinkedHashMap<String,String> tips = new LinkedHashMap<>();
                    List<String> tip = new ArrayList<String>();
                    List<String> listpar = new ArrayList<String>();
                    LinkedHashMap<String,String> listmap = new LinkedHashMap<>();
                    list.add(MZ_SystemLog_FieldTextField1);
                    list.add(MZ_SystemLog_FieldTextField2);
                    tips.put("请输入开始时间...","true");
                    tips.put("请输入结束时间...","true");
                    listpar.add("start");
                    listpar.add("end");
                    
                    boolean state = getaddActionListener(MZ_SystemLog_Button, option, list, tips,null,null);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            for (String key : tips.keySet()) {
                                tip.add(key);
                            } 
                            if (StringUtil.isEqual(list.get(i).getText(), tip.get(i))) {
                                listmap.put(listpar.get(i), "");
                            }else{
                                listmap.put(listpar.get(i), list.get(i).getText());
                            }
                        }
                        boolean SqlState = BackendFunctionCenterPageController.SystemLog_Button(option, listmap.get("start"), listmap.get("end"));
                        getSelectState(SqlState, option);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        MZ_SystemLog_FieldTextField1 = addJTextField(160, 115, 150, 25);
        MZ_SystemLog_FieldTextField2 = addJTextField(320, 115, 150, 25);
        
        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(MZ_WhiteList_Button);
        ButtonPanel.add(MZ_WhiteList_FieldTextField1);
        ButtonPanel.add(MZ_WhiteList_FieldTextField2);

        ButtonPanel.add(MZ_EnterpriseCredit_Button);
        ButtonPanel.add(MZ_EnterpriseCredit_FieldTextField1);
        ButtonPanel.add(MZ_EnterpriseCredit_FieldTextField2);
        
        ButtonPanel.add(MZ_UserClear_Button);
        ButtonPanel.add(MZ_UserClear_FieldTextField1);
        ButtonPanel.add(MZ_UserClear_FieldTextField2);
        
        ButtonPanel.add(MZ_SystemLog_Button);
        ButtonPanel.add(MZ_SystemLog_FieldTextField1);
        ButtonPanel.add(MZ_SystemLog_FieldTextField2);
        
        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }
    
    private static JPanel MZKJ_AES_Panel() {
        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        JTextField AES_JTextField = new JTextField();
        AES_JTextField.setBounds(160, 10, 150, 25);
        AES_JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);
        AES_JTextField.setFont(Constants.BASIC_BOLD);
        AES_JTextField.setForeground(Color.GRAY);
        getJTextFieldState(AES_JTextField,"请输入密码...");
        
        JButton AES_KEY = new JButton("AES密码");
        AES_KEY.setFont(Constants.BASIC_BOLD);
        AES_KEY.setBounds(10, 10, 140, 25);
        AES_KEY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                AES_JTextField.setText("1020304050607080");
            }
        });
        
        JTextArea Encryption_JTextArea = new JTextArea();
        Encryption_JTextArea.setLineWrap(true);
        Encryption_JTextArea.setWrapStyleWord(true);
        Encryption_JTextArea.setSelectedTextColor(Color.RED);
        Encryption_JTextArea.setFont(Constants.BASIC_BOLD);
        Encryption_JTextArea.setText("请输入加密内容...");
        Encryption_JTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {// 失去鼠标焦点事件
                Encryption_JTextArea.setBorder(Constants.LIGHT_GRAY_BORDER);
                String value = String.valueOf(Encryption_JTextArea.getText());
                if (StringUtil.isEmpty(value)) {
                    Encryption_JTextArea.setText("请输入加密内容...");
                }
            }
            @Override
            public void focusGained(FocusEvent e) {// 获得鼠标焦点事件
                Encryption_JTextArea.setBorder(Constants.DARKGRAY_BORDER);
                String value = String.valueOf(Encryption_JTextArea.getText());
                if (StringUtil.isEqual(value, "请输入加密内容...")) {
                    Encryption_JTextArea.setText("");
                }
            }
        });
        
        JTextArea Decrypt_JTextArea = new JTextArea();
        Decrypt_JTextArea.setLineWrap(true);
        Decrypt_JTextArea.setWrapStyleWord(true);
        Decrypt_JTextArea.setSelectedTextColor(Color.RED);
        Decrypt_JTextArea.setFont(Constants.BASIC_BOLD);
        Decrypt_JTextArea.setText("请输入解密内容...");
        Decrypt_JTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {// 失去鼠标焦点事件
                Decrypt_JTextArea.setBorder(Constants.LIGHT_GRAY_BORDER);
                String value = String.valueOf(Decrypt_JTextArea.getText());
                if (StringUtil.isEmpty(value)) {
                    Decrypt_JTextArea.setText("请输入解密内容...");
                }
            }
            @Override
            public void focusGained(FocusEvent e) {// 获得鼠标焦点事件
                Decrypt_JTextArea.setBorder(Constants.DARKGRAY_BORDER);
                String value = String.valueOf(Decrypt_JTextArea.getText());
                if (StringUtil.isEqual(value, "请输入解密内容...")) {
                    Decrypt_JTextArea.setText("");
                }
            }
        });
        
        JScrollPane EncryptionScrollPane = new JScrollPane(Encryption_JTextArea);
        EncryptionScrollPane.setBounds(160, 45, 550, 200);
        
        JScrollPane DecryptScrollPane = new JScrollPane(Decrypt_JTextArea);
        DecryptScrollPane.setBounds(160, 253, 550, 200);
        
        JButton Encryption_Button = new JButton("AES加密");
        Encryption_Button.setFont(Constants.BASIC_BOLD);
        Encryption_Button.setBounds(10, 45, 140, 25);
        Encryption_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                String password = AES_JTextField.getText();
                String content = Encryption_JTextArea.getText();
                try {
                    if(StringUtil.isEqual(password, "请输入密码...")){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog.SetMessageDialog("Warning", "请输入AES密码！");
                            }
                        });
                    }else if(StringUtil.isEqual(content, "请输入加密内容...")){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog.SetMessageDialog("Warning", "请输入加密内容！");
                            }
                        });
                    }else{
                        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
                        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                        cipher.init(Cipher.ENCRYPT_MODE, key);
                        byte[] encryptedData = cipher.doFinal(content.getBytes("UTF-8"));
                        Decrypt_JTextArea.setText(Base64Encoder.encode(encryptedData));
                        textArea.append("功能：" + Encryption_Button.getText() + "\n");
                        textArea.append("密码：" + AES_JTextField.getText() + "\n");
                        textArea.append("内容：\n" + JsonFormatUtil.formatJson(Encryption_JTextArea.getText()) + "\n");
                        textArea.append("结果：\n" + Decrypt_JTextArea.getText() + "\n");
                        textArea.append("\n");
                    }
                    textAreaToBottom(textArea);
              } catch (Exception e) {
                  Platform.runLater(new Runnable() {
                      @Override
                      public void run() {
                          Dialog.SetMessageDialog("Error",e.getMessage());
                      }
                  });
                  e.printStackTrace();
              }
            }
        });

        JButton Decrypt_Button = new JButton("AES解密");
        Decrypt_Button.setFont(Constants.BASIC_BOLD);
        Decrypt_Button.setBounds(10, 253, 140, 25);
        Decrypt_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                String password = AES_JTextField.getText();
                String content = Decrypt_JTextArea.getText();
                try {
                    if(StringUtil.isEqual(password, "请输入密码...")){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog.SetMessageDialog("Warning", "请输入AES密码！");
                            }
                        });
                    }else if(StringUtil.isEqual(content, "请输入解密内容...")){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog.SetMessageDialog("Warning", "请输入解密内容！");
                            }
                        });
                    }else{
                        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
                        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                        cipher.init(Cipher.DECRYPT_MODE, key);
                        byte[] decryptedData = cipher.doFinal(Base64Encoder.decodeString(content));
                        Encryption_JTextArea.setText(new String(decryptedData, "UTF-8"));
                        textArea.append("功能：" + Decrypt_Button.getText() + "\n");
                        textArea.append("密码：" + AES_JTextField.getText() + "\n");
                        textArea.append("内容：\n" + Decrypt_JTextArea.getText() + "\n");
                        textArea.append("结果：\n" + JsonFormatUtil.formatJson(new String(decryptedData, "UTF-8")) + "\n");
                        textArea.append("\n");
                    }
                    textAreaToBottom(textArea);
              } catch (Exception e) {
                  e.printStackTrace();
              }
            }
        });

        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(AES_KEY);
        ButtonPanel.add(AES_JTextField);
        ButtonPanel.add(EncryptionScrollPane);
        ButtonPanel.add(DecryptScrollPane);
        ButtonPanel.add(Encryption_Button);
        ButtonPanel.add(Decrypt_Button);
        
        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }
    
    private static JPanel YAPI_Mock_Panel() {
        
        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        
        JTextField YAPI_URL_JTextField = new JTextField();
        JTextField Project_URL_JTextField = new JTextField();
        JTextField API_URL_JTextField = new JTextField();
        JTextField Email_JTextField = new JTextField();
        JTextField PassWord_JTextField = new JTextField();
        
        JComboBox<String> Group_JComboBox = new JComboBox<String>();
        JComboBox<String> Project_JComboBox = new JComboBox<String>();
        JComboBox<String> Process_JComboBox = new JComboBox<String>();
        JComboBox<String> API_JComboBox = new JComboBox<String>();
        JComboBox<String> Mock_JComboBox = new JComboBox<String>();
        JComboBox<String> State_JComboBox = new JComboBox<String>();
        
        YAPI_URL_JTextField.setBounds(160, 10, 150, 25);
        YAPI_URL_JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);
        YAPI_URL_JTextField.setFont(Constants.BASIC_BOLD);
        YAPI_URL_JTextField.setForeground(Color.GRAY);
        getJTextFieldState(YAPI_URL_JTextField,"请输入YAPI地址...");
        
        Project_URL_JTextField.setBounds(320, 10, 70, 25);
        Project_URL_JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);
        Project_URL_JTextField.setFont(Constants.BASIC_BOLD);
        Project_URL_JTextField.setForeground(Color.GRAY);
        getJTextFieldState(Project_URL_JTextField,"项目地址...");
        
        API_URL_JTextField.setBounds(400, 10, 190, 25);
        API_URL_JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);
        API_URL_JTextField.setFont(Constants.BASIC_BOLD);
        API_URL_JTextField.setForeground(Color.GRAY);
        getJTextFieldState(API_URL_JTextField,"请获取接口地址...");
        
        JButton YAPI_URL_Button = new JButton("YAPI地址");
        YAPI_URL_Button.setBounds(10, 10, 140, 25);
        YAPI_URL_Button.setFont(Constants.BASIC_BOLD);
        YAPI_URL_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getButton() == e.BUTTON1) {//左键单击
                    YAPI_URL_JTextField.setText("http://10.22.83.65:3000");
                }
                if (e.getButton() == e.BUTTON3) {//右键单击
                }
                if (e.getClickCount() == 2) {//左右键双击
                    Email_JTextField.setText("yht@sunline.cn");
                    PassWord_JTextField.setText("300348");
                } 
            }
        });
        
        Email_JTextField.setBounds(160, 45, 140, 25);
        Email_JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);
        Email_JTextField.setFont(Constants.BASIC_BOLD);
        Email_JTextField.setForeground(Color.GRAY);
        getJTextFieldState(Email_JTextField,"请输入邮箱...");
        
        PassWord_JTextField.setBounds(310, 45, 140, 25);
        PassWord_JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);
        PassWord_JTextField.setFont(Constants.BASIC_BOLD);
        PassWord_JTextField.setForeground(Color.GRAY);
        getJTextFieldState(PassWord_JTextField,"请输入密码...");
        
        JButton YAPI_Login_Button = new JButton("YAPI登录");
        YAPI_Login_Button.setBounds(10, 45, 140, 25);
        YAPI_Login_Button.setFont(Constants.BASIC_BOLD);
        YAPI_Login_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                String Login_URL = YAPI_URL_JTextField.getText()+"/api/user/login";
                String Group_URL = YAPI_URL_JTextField.getText()+"/api/group/list";
                String Email = Email_JTextField.getText();
                String PassWord = PassWord_JTextField.getText();
                String Body = "{\"email\":\""+Email+"\",\"password\":\""+PassWord+"\"}";
                if(StringUtil.isEqual(Login_URL, "请输入YAPI地址...")){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Dialog.SetMessageDialog("Warning", "请输入YAPI地址！");
                        }
                    });
                }else if(StringUtil.isEqual(Email, "请输入邮箱...")||StringUtil.isEqual(PassWord,"请输入密码...")){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Dialog.SetMessageDialog("Warning", "请输入邮箱或密码！");
                        }
                    });
                }else{
                    try {
                        textArea.append("功能：" + YAPI_Login_Button.getText() + "\n");
                        Cookie = HttpPostRequestUtil.GetCookie(Login_URL, Body);
                        if(StringUtil.isNotEmpty(Cookie)){
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Dialog.SetMessageDialog("Success", "登录成功！");
                                }
                            });
                            Headers.put("Cookie", Cookie);
                            group_list =HttpGetRequestUtil.GetJsonResult(Group_URL,Headers);
                            ArrayList<String> data = group_list.get("data");
                            ArrayList<String> group_name = group_list.get("data.group_name");
                            Group_JComboBox.removeAllItems();
                            Group_JComboBox.addItem("--请选择空间--");
                            for (int i =0;i<data.size();i++) {
                                Group_JComboBox.addItem(group_name.get(i));
                            }
                        }else{
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Dialog.SetMessageDialog("Error", "登录失败，请检查邮箱密码！");
                                }
                            });
                        }
                    } catch (Exception e) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog.SetMessageDialog("Warning", "登录失败，请检查网络环境！");
                            }
                        });
                    }
                }
            }
        });
       
        JButton User_Credit_Button = new JButton("用户授信流程");
        User_Credit_Button.setFont(Constants.BASIC_BOLD);
        User_Credit_Button.setBounds(10, 80, 140, 25);
        User_Credit_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {

            }
        });
        
        Group_JComboBox.addItem("--请选择空间--");
        Group_JComboBox.setBounds(460, 45, 130, 25);
        Group_JComboBox.setFont(Constants.BASIC_BOLD);
        Group_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if (item.getStateChange() == ItemEvent.SELECTED&&StringUtil.isNotEqual(item.getItem().toString(), "--请选择空间--")) {
                    String URL = YAPI_URL_JTextField.getText()+"/api/project/list";
//                    String URL1 = "http://10.22.83.65:3000/api/project/list?group_id=47&page=1&limit=10";
                    if(StringUtil.isEmpty(Cookie)){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog.SetMessageDialog("Warning", "请先登录YAPI！");
                            }
                        });
                    }else{
                        try {
                            textArea.append("功能：" + Group_JComboBox.getSelectedItem() + "\n");
                            String group_id = group_list.get("data.find{it.group_name==\""+item.getItem()+"\"}._id").toString();
                            project_list =HttpGetRequestUtil.GetJsonResult(URL+"?group_id="+group_id+"&page=1&limit=10",Headers);
                            ArrayList<String> data_list = project_list.get("data.list");
                            ArrayList<String> name = project_list.get("data.list.name");
                            Project_JComboBox.removeAllItems();
                            Project_JComboBox.addItem("--请选择项目--");
                            Process_JComboBox.removeAllItems();
                            Process_JComboBox.addItem("--请选择流程--");
                            API_JComboBox.removeAllItems();
                            API_JComboBox.addItem("--请选择接口--");
                            Mock_JComboBox.removeAllItems();
                            Mock_JComboBox.addItem("--请选择期望--");
                            for (int i =0;i<data_list.size();i++) {
                                Project_JComboBox.addItem(name.get(i));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } 
                    }
                }else{
                    Project_JComboBox.removeAllItems();
                    Project_JComboBox.addItem("--请选择项目--");
                    Process_JComboBox.removeAllItems();
                    Process_JComboBox.addItem("--请选择流程--");
                    API_JComboBox.removeAllItems();
                    API_JComboBox.addItem("--请选择接口--");
                    Mock_JComboBox.removeAllItems();
                    Mock_JComboBox.addItem("--请选择期望--");
                }
            }
        });
        
        Project_JComboBox.addItem("--请选择项目--");
        Project_JComboBox.setBounds(10, 80, 140, 25);
        Project_JComboBox.setFont(Constants.BASIC_BOLD);
        Project_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if (item.getStateChange() == ItemEvent.SELECTED&&StringUtil.isNotEqual(item.getItem().toString(), "--请选择项目--")) {
                    String URL = YAPI_URL_JTextField.getText()+"/api/col/list";
//                    String URL1 = "http://10.22.83.65:3000/api/col/list?project_id=78";
                    try {
                        textArea.append("功能：" + Project_JComboBox.getSelectedItem() + "\n");
                        String project_id = project_list.get("data.list.find{it.name==\""+item.getItem()+"\"}._id").toString();
                        process_list =HttpGetRequestUtil.GetJsonResult(URL+"?project_id="+project_id+"",Headers);
                        ArrayList<String> data = process_list.get("data");
                        ArrayList<String> name = process_list.get("data.name");
                        Project_URL_JTextField.setText("/mock/"+project_id);
                        API_URL_JTextField.setText("");
                        Process_JComboBox.removeAllItems();
                        Process_JComboBox.addItem("--请选择流程--");
                        API_JComboBox.removeAllItems();
                        API_JComboBox.addItem("--请选择接口--");
                        Mock_JComboBox.removeAllItems();
                        Mock_JComboBox.addItem("--请选择期望--");
                        for (int i =0;i<data.size();i++) {
                            Process_JComboBox.addItem(name.get(i));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                }else{
                    Process_JComboBox.removeAllItems();
                    Process_JComboBox.addItem("--请选择流程--");
                    API_JComboBox.removeAllItems();
                    API_JComboBox.addItem("--请选择接口--");
                    Mock_JComboBox.removeAllItems();
                    Mock_JComboBox.addItem("--请选择期望--");
                }
            }
        });
        
        Process_JComboBox.addItem("--请选择流程--");
        Process_JComboBox.setBounds(160, 80, 140, 25);
        Process_JComboBox.setFont(Constants.BASIC_BOLD);
        Process_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if (item.getStateChange() == ItemEvent.SELECTED&&StringUtil.isNotEqual(item.getItem().toString(), "--请选择流程--")) {
                    String URL = YAPI_URL_JTextField.getText()+"/api/col/case_list";
//                    String URL1 = "http://10.22.83.65:3000/api/col/case_list/?col_id=208";
                    try {
                        textArea.append("功能：" + Process_JComboBox.getSelectedItem() + "\n");
                        String col_id = process_list.get("data.find{it.name==\""+item.getItem()+"\"}._id").toString();
                        api_list =HttpGetRequestUtil.GetJsonResult(URL+"?col_id="+col_id+"",Headers);
                        ArrayList<String> data = api_list.get("data");
                        ArrayList<String> casename = api_list.get("data.casename");
                        API_JComboBox.removeAllItems();
                        API_JComboBox.addItem("--请选择接口--");
                        Mock_JComboBox.removeAllItems();
                        Mock_JComboBox.addItem("--请选择期望--");
                        for (int i =0;i<data.size();i++) {
                            API_JComboBox.addItem(casename.get(i));
                            API_JComboBox.setSelectedItem(casename.get(i)); 
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    API_JComboBox.removeAllItems();
                    API_JComboBox.addItem("--请选择接口--");
                    Mock_JComboBox.removeAllItems();
                    Mock_JComboBox.addItem("--请选择期望--");
                }
            }
        });
        
        API_JComboBox.addItem("--请选择接口--");
        API_JComboBox.setBounds(310, 80, 140, 25);
        API_JComboBox.setFont(Constants.BASIC_BOLD);
        API_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if (item.getStateChange() == ItemEvent.SELECTED&&StringUtil.isNotEqual(item.getItem().toString(), "--请选择接口--")) {
                    String URL = YAPI_URL_JTextField.getText()+"/api/plugin/advmock/case/list";
//                    String URL1 = "http://10.22.83.65:3000/api/plugin/advmock/case/list?interface_id=1008";
                    try {
                        textArea.append("功能：" + API_JComboBox.getSelectedItem() + "\n");
                        String interface_id = api_list.get("data.find{it.casename==\""+item.getItem()+"\"}.interface_id").toString();
                        String path = api_list.get("data.find{it.casename==\""+item.getItem()+"\"}.path").toString();
                        mock_list =HttpGetRequestUtil.GetJsonResult(URL+"?interface_id="+interface_id+"",Headers);
                        ArrayList<String> data = mock_list.get("data");
                        ArrayList<String> name = mock_list.get("data.name");
                        Mock_JComboBox.removeAllItems();
                        Mock_JComboBox.addItem("--请选择期望--");
                        for (int i =0;i<data.size();i++) {
                            Mock_JComboBox.addItem(name.get(i));
                            Mock_JComboBox.setSelectedItem(name.get(i));
                            switch(Process_JComboBox.getSelectedItem().toString()){
                                case "用户授信流程_Mock":
                                    switch(item.getItem().toString()){
                                        case "微信登录":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已授信":
                                                    State_JComboBox.setSelectedItem("未开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已授信");
                                            break;
                                        case "查询服务协议":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "授信协议":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("授信协议");
                                            break;
                                    }
                                    break;
                                case "用户开户流程_Mock":
                                    switch(item.getItem().toString()){
                                        case "微信登录":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已授信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已授信");
                                            break;
                                        case "查询用户开户信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已开户":
                                                    State_JComboBox.setSelectedItem("未开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已开户");
                                            break;
                                        case "查询银行卡信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "建设银行":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("建设银行");
                                            break;
                                        case "查询服务协议":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "开户协议":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("开户协议");
                                            break;
                                        case "发送验证码":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "开户短信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("开户短信");
                                            break;
                                        case "lastPutout":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已借款":
                                                    State_JComboBox.setSelectedItem("未开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已借款");
                                            break;
                                        case "nextRepay":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已借款":
                                                    State_JComboBox.setSelectedItem("未开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已借款");
                                            break;
                                    }
                                    break;
                                case "用户借款流程_Mock":
                                    switch(item.getItem().toString()){
                                        case "微信登录":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已授信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已授信");
                                            break;
                                        case "查询用户开户信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已开户":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已开户");
                                            break;
                                        case "查询服务协议":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "借款协议":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("借款协议");
                                            break;
                                        case "lastPutout":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已借款":
                                                    State_JComboBox.setSelectedItem("未开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已借款");
                                            break;
                                        case "nextRepay":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已借款":
                                                    State_JComboBox.setSelectedItem("未开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已借款");
                                            break;
                                    }
                                    break;
                                case "用户还款流程_Mock":
                                    switch(item.getItem().toString()){
                                        case "微信登录":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已授信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已授信");
                                            break;
                                        case "查询用户开户信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已开户":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已开户");
                                            break;
                                        case "lastPutout":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已借款":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已借款");
                                            break;
                                        case "nextRepay":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已借款":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已借款");
                                            break;
                                        case "homeAmtInfo":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已借款":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已借款");
                                            break;
                                        case "查询借据信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "有借据":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("有借据");
                                            break;
                                        case "查询借款记录信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "有借款记录":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("有借款记录");
                                            break;
                                        case "查询还款记录信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "有还款记录":
                                                    State_JComboBox.setSelectedItem("未开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("有还款记录");
                                            break;
                                    }
                                    break;
                                case "用户充值流程_Mock":
                                    switch(item.getItem().toString()){
                                        case "微信登录":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已授信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已授信");
                                            break;
                                        case "查询用户开户信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已开户":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已开户");
                                            break;
                                        case "发送验证码":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "充值短信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("充值短信");
                                            break;
                                        case "充值提现":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "充值成功":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("充值成功");
                                            break;
                                    }
                                    break;
                                case "用户提现流程_Mock":
                                    switch(item.getItem().toString()){
                                        case "微信登录":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已授信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已授信");
                                            break;
                                        case "查询用户开户信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已开户":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已开户");
                                            break;
                                        case "发送验证码":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "提现短信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("提现短信");
                                            break;
                                        case "充值提现":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "提现成功":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("提现成功");
                                            break;
                                    }
                                    break;
                                case "个人中心流程_Mock":
                                    switch(item.getItem().toString()){
                                        case "微信登录":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已授信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已授信");
                                            break;
                                        case "查询用户开户信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "已开户":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("已开户");
                                            break;
                                        case "查询银行卡信息":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "工商银行":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("工商银行");
                                            break;
                                        case "发送验证码":
                                            switch(Mock_JComboBox.getSelectedItem().toString()){
                                                case "更换银行卡短信":
                                                    State_JComboBox.setSelectedItem("已开启");
                                                    break;
                                            }
                                            Mock_JComboBox.setSelectedItem("更换银行卡短信");
                                            break;
                                    }
                                    break;
                            }
                        }
                        API_URL_JTextField.setText(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                }else{
                    Mock_JComboBox.removeAllItems();
                    Mock_JComboBox.addItem("--请选择期望--");
                }
            }
        });

        Mock_JComboBox.addItem("--请选择期望--");
        Mock_JComboBox.setBounds(460, 80, 130, 25);
        Mock_JComboBox.setFont(Constants.BASIC_BOLD);
        Mock_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if (item.getStateChange() == ItemEvent.SELECTED&&StringUtil.isNotEqual(item.getItem().toString(), "--请选择期望--")) {
                    String URL = YAPI_URL_JTextField.getText()+"/api/plugin/advmock/case/list";
                    try {
                        textArea.append("功能：" + Mock_JComboBox.getSelectedItem() + "\n");
                        String interface_id = mock_list.get("data.find{it.name==\""+item.getItem()+"\"}.interface_id").toString();
                        mock_list = HttpGetRequestUtil.GetJsonResult(URL+"?interface_id="+interface_id+"",Headers);
                        String case_enable = mock_list.get("data.find{it.name==\""+item.getItem()+"\"}.case_enable").toString();
                        State_JComboBox.removeAllItems();
                        State_JComboBox.addItem("--请选择状态--");
                        State_JComboBox.addItem("已开启");
                        State_JComboBox.addItem("未开启");
                        switch(case_enable){
                            case "true" :
                                State_JComboBox.setSelectedItem("已开启");
                                break;
                            case "false" :
                                State_JComboBox.setSelectedItem("未开启");
                                break;
                            default :
                                State_JComboBox.setSelectedItem("--请选择状态--");
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                }else{
                    State_JComboBox.removeAllItems();
                    State_JComboBox.addItem("--请选择状态--");
                }
            }
        });
        
        State_JComboBox.addItem("--请选择状态--");
        State_JComboBox.setBounds(600, 80, 125, 26);
        State_JComboBox.setFont(Constants.BASIC_BOLD);
        State_JComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if (item.getStateChange() == ItemEvent.SELECTED&&StringUtil.isNotEqual(item.getItem().toString(), "--请选择状态--")) {
                    String URL = YAPI_URL_JTextField.getText()+"/api/plugin/advmock/case/hide";
                    try {
                        String _id = mock_list.get("data.find{it.name==\""+Mock_JComboBox.getSelectedItem()+"\"}._id").toString();
                        String case_enable = mock_list.get("data.find{it.name==\""+Mock_JComboBox.getSelectedItem()+"\"}.case_enable").toString();
                        switch(item.getItem().toString()){
                            case "已开启":
                                String Body = "{\"id\":"+_id+",\"enable\":true}";
                                if(StringUtil.isNotEqual(case_enable, "true")){
                                    Mock(Mock_JComboBox,State_JComboBox,URL,Headers,Body);
                                }
                                break;
                            case "未开启":
                                Body = "{\"id\":"+_id+",\"enable\":false}";
                                if(StringUtil.isNotEqual(case_enable, "false")){
                                    Mock(Mock_JComboBox,State_JComboBox,URL,Headers,Body);
                                }
                                break;
                        }
                        String URL1 = YAPI_URL_JTextField.getText()+"/api/plugin/advmock/case/list";
                        String interface_id = mock_list.get("data.find{it.name==\""+Mock_JComboBox.getSelectedItem()+"\"}.interface_id").toString();
                        mock_list = HttpGetRequestUtil.GetJsonResult(URL1+"?interface_id="+interface_id+"",Headers);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                }
            }
        });
        
        ButtonPanel.setLayout(null);
        ButtonPanel.setPreferredSize(new Dimension(730, 1550));
        ButtonPanel.add(YAPI_URL_JTextField);
        ButtonPanel.add(Project_URL_JTextField);
        ButtonPanel.add(API_URL_JTextField);
        ButtonPanel.add(YAPI_URL_Button);
        ButtonPanel.add(Email_JTextField);
        ButtonPanel.add(PassWord_JTextField);
        ButtonPanel.add(YAPI_Login_Button);
        ButtonPanel.add(Group_JComboBox);
        ButtonPanel.add(Project_JComboBox);
        ButtonPanel.add(Process_JComboBox);
        ButtonPanel.add(API_JComboBox);
        ButtonPanel.add(Mock_JComboBox);
        ButtonPanel.add(State_JComboBox);
        
        JScrollPane ButtonScrollPane = new JScrollPane(ButtonPanel);
        ButtonScrollPane.setBounds(0, 0, 750, 345);
        // ButtonScrollPane.setBorder(null);//无边框
        JTabbedPanel.setLayout(null);
        JTabbedPanel.add(ButtonScrollPane);

        return JTabbedPanel;
    }
    
    public static boolean getaddActionListener(JButton button, String option, List<JTextField> JTextField_List, LinkedHashMap<String,String> JTextField_ListValue, List<JComboBox> JComboBox_List, LinkedHashMap<String,String> JComboBox_ListValue) {   
        
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        List<String> key = new ArrayList<String>();
        List<String> state = new ArrayList<String>();
        List<String> value = new ArrayList<String>();
        
        outer:for (int i = 0; i < JTextField_List.size(); i++) {
            for(Map.Entry<String, String> ListValue : JTextField_ListValue.entrySet()){
                key.add(ListValue.getKey()) ;
                state.add(ListValue.getValue()) ;
            }
            if (!JTextField_List.get(i).isEditable()) {
                getJTextFieldState(JTextField_List.get(i), key.get(i));
            }
            String message = key.get(i);
            if(StringUtil.isNotEqual(option, "查询")&&StringUtil.isEqual(JTextField_List.get(i).getText(), key.get(i))&&StringUtil.isEqual(state.get(i), "true")){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Dialog.SetMessageDialog("Warning", "" + message + "！");
                    }
                });
                JTextField_List.get(i).grabFocus();//获取输入框光标焦点
//                break outer;
                return false;
            }
            value.add(JTextField_List.get(i).getText());
            String param = StringUtil.getStringBetween(key.get(i), "请输入", "...");
            params.put(param, value.get(i));
        }
        
        if(StringUtil.isNotEmpty(JComboBox_List)&&StringUtil.isNotEmpty(JComboBox_ListValue)){
            for (int i = 0; i < JComboBox_List.size(); i++) {
                key = new ArrayList<String>();
                state = new ArrayList<String>();
                value = new ArrayList<String>();
                for(Map.Entry<String, String> ListValue : JComboBox_ListValue.entrySet()){
                    key.add(ListValue.getKey()) ;
                    state.add(ListValue.getValue()) ;
                }
                String message = key.get(i);
                if(StringUtil.isNotEqual(option, "查询")&&StringUtil.isEqual(JComboBox_List.get(i).getSelectedItem().toString(), key.get(i))&&StringUtil.isEqual(state.get(i), "true")){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Dialog.SetMessageDialog("Warning", "" + message + "！");
                        }
                    });
                    return false;
                }
                value.add(JComboBox_List.get(i).getSelectedItem().toString());
                String param = StringUtil.getStringBetween(key.get(i), "请选择", "...");
                params.put(param, value.get(i));
            }
        }
        
        if(StartClient.state){
            textArea.append("环境：" + LoginPageView.EnvironmentModeBox.getSelectionModel().getSelectedItem().toString() + "\n");
        }
        textArea.append("功能：" + button.getText() + option + "\n");
        textArea.append("时间：" + DateUtil.getDate() + "\n");
        textArea.append("参数：" + params.toString() + "\n");
        return true;
    }

    public static JTextField addJTextField(int x, int y, int width, int height) {
        JTextField JTextField = new JTextField();
        JTextField.setBounds(x, y, width, height);
        JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);// 边框颜色
        JTextField.setFont(Constants.BASIC_BOLD);
        JTextField.setForeground(Color.GRAY);

        JTextField.setEditable(false);
        return JTextField;
    }

    public static  void getJTextFieldState(JTextField JTextField, String name) {
        JTextField.setEditable(true);
        JTextField.setText(name);
        JTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {// 失去鼠标焦点事件
                JTextField.setBorder(Constants.LIGHT_GRAY_BORDER);
                String value = String.valueOf(JTextField.getText());
                if (StringUtil.isEmpty(value)) {
                    JTextField.setText(name);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {// 获得鼠标焦点事件
                JTextField.setBorder(Constants.DARKGRAY_BORDER);
                String value = String.valueOf(JTextField.getText());
                if (StringUtil.isEqual(value, name)) {
                    JTextField.setText("");
                }
            }
        });
    }

    public static  void getJTextFieldState1(JTextField JTextField, String name) {
        JTextField.setEditable(true);
        JTextField.setText(name);
        class myFocusListener implements FocusListener{
            @Override
            public void focusGained(FocusEvent arg0){
                String value = String.valueOf(JTextField.getText());
                System.out.println(name);
                if (StringUtil.isEqual(value, name)) {
                    JTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent arg0){
                String value = String.valueOf(JTextField.getText());
                System.out.println(name);
                if (StringUtil.isEmpty(value)) {
                    JTextField.setText(name);
                }
            }
        }
        JTextField.addFocusListener(new myFocusListener());
    }
    
    public static void getSelectState(boolean state, String tip) {
        if (state) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Dialog.SetMessageDialog("Success", "" + tip + "成功！");
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
//                    DataDisplayList.getViewport().add(null, "");
                    Dialog.SetMessageDialog("Warning", "" + tip + "数据失败，请检查SQL信息！");
                    textArea.append("状态：" + tip + "数据失败，请检查SQL信息！" + "\n");
                    textArea.append("\n");
                }
            });
        }
        textAreaToBottom(textArea);
    }

    public static void SexActionListener(ButtonGroup group) {
        class SexActionListener implements ActionListener {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String choice = group.getSelection().getActionCommand();
                System.out.println("选择了****-----: " + choice);
            }
        }
    }

    /***
     * 使JTextArea自动定位到最后一行
     * 
     * @param textArea
     */
    public static void textAreaToBottom(JTextArea textArea) {
        textArea.selectAll();
        if (textArea.getSelectedText() != null) {
            textArea.setCaretPosition(textArea.getSelectedText().length());
            textArea.requestFocus();
        }
    }

    /***
     * 使滚动条自动定位到底部
     * 
     * @param panel_7JS2
     */
    public static void scrollToBottom(JScrollPane panel_7JS2) {
        int maxHeight = panel_7JS2.getVerticalScrollBar().getMaximum();
        System.out.println(maxHeight);
        panel_7JS2.getViewport().setViewPosition(new Point(0, maxHeight));
        panel_7JS2.updateUI();
    }

    public static void Mock(JComboBox<String> Mock_JComboBox,JComboBox<String> State_JComboBox,String YAPI_MockUrl,Map<String, Object> Headers,String Body) {
        try {
            Map<String, Object> JsonResult = StringUtil.JsonToMap(HttpPostRequestUtil.GetJsonResult(YAPI_MockUrl, Headers,Body));
            if(StringUtil.isEqual(JsonResult.get("errmsg").toString(),"成功！")){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Dialog.SetMessageDialog("Success", "修改【"+Mock_JComboBox.getSelectedItem()+"】期望成功！");
                    }
                });
            }else{
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Dialog.SetMessageDialog("Error", "修改【"+Mock_JComboBox.getSelectedItem()+"】期望失败！");
                    }
                });
                State_JComboBox.setSelectedIndex(0);
            }
        } catch (Exception e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Dialog.SetMessageDialog("Warning", "请检查网络环境！");
                }
            });
            State_JComboBox.setSelectedIndex(0);
        }
    }
        
    public static void getBackendFunctionCenterPane(boolean show) {
        BackendFunctionCenterPane.setVisible(show);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }
}