package AutomationTestSystem.View;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.text.NumberFormat;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
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

import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemDatabaseConfiguration;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Config.UserInformationConfiguration;
import AutomationTestSystem.Config.UserSaveData;
import AutomationTestSystem.Config.UserSaveDataBox;
import AutomationTestSystem.Controller.BackendFunctionCenterPageController;
import AutomationTestSystem.Controller.LoginController;
import AutomationTestSystem.Util.Constants;
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.DragUtil;
import AutomationTestSystem.Util.MyScrollBarUI;
import AutomationTestSystem.Util.StringUtil;

@SuppressWarnings({"restriction", "static-access", "unused"})
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
                LoginPageView.DDataBaseField.setText("YHT_USER_SIT");
                jTabbedpane.addTab("用户", null, YHT_USER_Panel(), "YHT_USER");
                jTabbedpane.addTab("APP", null, YHT_APP_Panel(), "YHT_APP");
                jTabbedpane.addTab("前置", null, YHT_EDGE_Panel(), "YHT_EDGE");
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
        BackendFunctionCenterPane.setVisible(true);

        BackendFunctionCenterPane.getChildren().add(swingNode);

        HomePagePane.getChildren().add(BackendFunctionCenterPane);
        // HomePageView.HomePagePane.getChildren().add(BackendFunctionCenterPane);
    }

    private static JPanel YHT_USER_Panel() {

        JPanel JTabbedPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        UserInfoButton = new JButton("用户开户信息");
        UserInfoButton.setFont(Constants.BASIC_BOLD);
        UserInfoButton.setBounds(10, 10, 140, 25);
        UserInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                try {
                    String option = operation.getSelection().getActionCommand();

                    List<JTextField> list = new ArrayList<JTextField>();
                    List<String> tips = new ArrayList<String>();
                    list.add(UserInfoTextField1);
                    tips.add("请输入查询的手机号...");
                    list.add(UserInfoTextField2);
                    tips.add("请输入查询的法人号...");

                    boolean state = getaddActionListener(UserInfoButton, option, list, tips);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            if (StringUtil.isEqual(list.get(i).getText(), tips.get(i))) {
                                list.get(i).setText("");
                            }
                        }
                        String phone = list.get(0).getText();
                        String corpno = list.get(1).getText();
                        boolean SqlState = BackendFunctionCenterPageController.UserInfoButton(option, phone,corpno);
                        getSelectState(SqlState, option);
                    }
                    textAreaToBottom(textArea);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        UserInfoTextField1 = addJTextField(160, 10, 180, 25);
        UserInfoTextField2 = addJTextField(350, 10, 180, 25);
        UserInfoTextField3 = addJTextField(540, 10, 180, 25);

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
                    List<String> tips = new ArrayList<String>();
                    list.add(SmsInquiryFieldTextField1);
                    tips.add("请输入查询的手机号...");

                    boolean state = getaddActionListener(SmsInquiryButton, option, list, tips);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            if (StringUtil.isEqual(list.get(i).getText(), tips.get(i))) {
                                list.get(i).setText("");
                            }
                        }
                        String phone = list.get(0).getText();
                        boolean SqlState = BackendFunctionCenterPageController.SmsInquiryButton(option, phone);
                        getSelectState(SqlState, option);
                    }
                    textAreaToBottom(textArea);
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
                    List<String> tips = new ArrayList<String>();
                    list.add(Business_FieldTextField1);
                    list.add(Business_FieldTextField2);
                    tips.add("请输入身份证号码...");
                    tips.add("请输入企业工商注册号...");

                    boolean state = getaddActionListener(Business_Button, option, list, tips);
                    if (state) {
                        for (int i = 0; i < list.size(); i++) {
                            if (StringUtil.isEqual(list.get(i).getText(), tips.get(i))) {
                                list.get(i).setText("");
                            }
                        }
                        String idtfno = list.get(0).getText();
                        String regnum = list.get(1).getText();
                        boolean SqlState = BackendFunctionCenterPageController.Business_Button(option, idtfno, regnum);
                        getSelectState(SqlState, option);
                    }
                    textAreaToBottom(textArea);
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

    public static boolean getaddActionListener(JButton button, String option, List<JTextField> list, List<String> tips) {
        textArea.append("环境：" + LoginPageView.EnvironmentModeBox.getSelectionModel().getSelectedItem().toString() + "\n");
        textArea.append("功能：" + button.getText() + option + "\n");
        List<String> key = new ArrayList<String>();
        List<String> value = new ArrayList<String>();
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isEditable()) {
                getJTextFieldState(list.get(i), tips.get(i));
            }
            key.add(tips.get(i));
            value.add(list.get(i).getText());
        }
        for (int i = 0; i < value.size(); i++) {
            String param = StringUtil.getStringBetween(key.get(i), "请输入", "...");
            params.put(param, value.get(i));
        }
        textArea.append("参数：" + params.toString() + "\n");
        switch(option){
            case "查询" :
                break;
            case "修改" :
                    if (StringUtil.isEmpty(list.get(0).getText())&&StringUtil.isEqual(list.get(0).getText(), tips.get(0))) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog.SetMessageDialog("Warning", "" + tips.get(0) + "！");
                            }
                        });
                        list.get(0).grabFocus();//获取输入框光标焦点
                        return false;
                    }
                break;
            case "新增" :
                break;
            case "删除" :
                break;
        }
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

    public static void getJTextFieldState(JTextField JTextField, String name) {
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
                    DataDisplayList.getViewport().add(null, "");
                    Dialog.SetMessageDialog("Warning", "" + tip + "数据失败，请检查SQL信息！");
                    textArea.append("状态：" + tip + "数据失败，请检查SQL信息！" + "\n");
                    textArea.append("\n");
                }
            });
        }
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

    public static void getBackendFunctionCenterPane(boolean show) {
        BackendFunctionCenterPane.setVisible(show);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }
}