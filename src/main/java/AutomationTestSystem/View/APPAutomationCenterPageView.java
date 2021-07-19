package AutomationTestSystem.View;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;

import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemDatabaseConfiguration;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Config.UserInformationConfiguration;
import AutomationTestSystem.Config.UserSaveData;
import AutomationTestSystem.Config.UserSaveDataBox;
import AutomationTestSystem.Controller.APPAutomationCenterPageController;
import AutomationTestSystem.Controller.FrontEndFunctionCenterPageController;
import AutomationTestSystem.Controller.HomePageController;
import AutomationTestSystem.Controller.LoginController;
import AutomationTestSystem.Util.ApkIconUtil;
import AutomationTestSystem.Util.ApkInfo;
import AutomationTestSystem.Util.ApkUtil;
import AutomationTestSystem.Util.DatabaseUtil;
import AutomationTestSystem.Util.DateUtil;
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.DragUtil;
import AutomationTestSystem.Util.HttpPostRequestUtil;
import AutomationTestSystem.Util.JGitUtil;
import AutomationTestSystem.Util.OpenBrowserUtil;
import AutomationTestSystem.Util.SendEmail;
import AutomationTestSystem.Util.StringUtil;

@SuppressWarnings({ "rawtypes", "unchecked", "restriction", "static-access","unused","deprecation"})
public class APPAutomationCenterPageView extends Application {

		public  static Stage HomePageInterfaceStage;
//    	HomePageView.HomePageInterfaceStage.close();

		private static AnchorPane HomePagePane = new AnchorPane();

        private static DropShadow dropShadow = new DropShadow();
        private static DialogUtil Dialog= new DialogUtil();

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
        private static Button PerformanceAutomationCenterButton = new Button();
        
        private static WebView VideoPlayerWebView = new WebView();
        private static Button VideoPlayerButton =new Button();
        private static AnchorPane HomePageRightPane = new AnchorPane();

        static AnchorPane APPAutomationCenterPane = new AnchorPane();
        private static TextField SoftwareEngineeringField = new TextField();
        private static Button SoftwareEngineeringButton = new Button();
        static String SoftwareEngineering=null;
        private static RadioButton WeChatOptionRadioButton = new RadioButton();
        private static RadioButton QQOptionRadioButton = new RadioButton();
        private static RadioButton OtherOptionRadioButton = new RadioButton();
        private static ImageView SoftwareIconImageView = new ImageView();
        static String SoftwareIcon=null;
        private static Label SoftwareNameLabel = new Label();
        
        private static DatePicker checkInDatePicker = new DatePicker();
        private static DatePicker checkOutDatePicker = new DatePicker();
        private static final String pattern = "yyyy-MM-dd";
        static LocalDate SoftwareDate;
        
        private static TextField SoftwareDownloadsField = new TextField();
        private static Button SoftwareDownloadsButton = new Button();
        static Task Worker;
        
        private static TextField TestObjectField = new TextField();
        private static Button TestObjectButton = new Button();
        private static TextField TestScriptsField = new TextField();
        private static Button TestScriptsButton = new Button();
        private static TextField TestScriptsAggregateField = new TextField();
        private static Button TestScriptsAggregateButton = new Button();
        private static TextArea CommitMessageTextArea = new TextArea();
        private static TextField MailModeField = new TextField();
        private static Button MailModeButton = new Button();
        static String Smtp=null;
        
        private static TextField SendPeopleNumberField = new TextField();
        private static TextField AddresseeOneField = new TextField();
        private static TextField AddresseeTwoField = new TextField();
        private static TextField AddresseeThreeField = new TextField();
        private static TextField AddresseeFourField = new TextField();
        
        private static TextArea AppiumLogTextArea = new TextArea();
        private static Button AppiumInspecrotButton = new Button();
        
        private static Button GetDevicesButton = new Button();
        static AnchorPane DevicesCenterPane = new AnchorPane();
        private static TextField MobileDevicesField = new TextField();
        static ObservableList<String> data = FXCollections.observableArrayList();
		static ListView<String> MobileDevicesListView = new ListView<String>(data);
        private static Button MobileDevicesButton = new Button();
        private static TextField MobilePhoneManufacturerField = new TextField();
        private static TextField MobilePhoneModelField = new TextField();
        private static TextField SyetemVersionField = new TextField();
        private static TextField ResolvingPowerField = new TextField();
        
        private static Button AppiumRunButton = new Button();
        static Thread AppiumInspectorThread = new Thread();
        private static Button AppiumStopButton = new Button();
        private static TextArea ScriptRunLogTextArea = new TextArea();
        private static FileChooser fileChooser = new FileChooser();
        private static Desktop desktop = Desktop.getDesktop();

        private static Button ScriptUploadButton = new Button();
        private static Button ScriptDownloadButton = new Button();
        private static Button ScriptRunButton = new Button();
        static Thread AppiumThread = new Thread();
		static Thread ScriptsThread = new Thread();
        private static Button GenerationReportButton = new Button();
        static int FirstOpen =0;
        private static Button SendMailButton = new Button();
        
        CopyOnWriteArraySet<EventHandler<ActionEvent>> closeActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();
        CopyOnWriteArraySet<EventHandler<ActionEvent>> iconifiedActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();

        SystemDatabaseConfiguration sdcd = (SystemDatabaseConfiguration) ConfigManage.get(SystemDatabaseConfiguration.path, SystemDatabaseConfiguration.class);
        SystemServerConfiguration ssc = (SystemServerConfiguration) ConfigManage.get(SystemServerConfiguration.path, SystemServerConfiguration.class);

        UserInformationConfiguration uic = (UserInformationConfiguration) ConfigManage.get(UserInformationConfiguration.path, UserInformationConfiguration.class);
        UserSaveDataBox usdb = (UserSaveDataBox) ConfigManage.get(UserSaveDataBox.path, UserSaveDataBox.class);
 
    	int SystemSettingStageStatus = 0;

		@Override
        public void start(Stage HomePageInterfaceStage) throws Exception {
                this.HomePageInterfaceStage = HomePageInterfaceStage;
                //设置主窗口标题
                this.HomePageInterfaceStage.setTitle("HomePageInterface");
                //设置主窗口图标
//                this.HomePageInterfaceStage.getIcons().add(new Image(getClass().getResourceAsStream("images/address_book_32.png")));//获取images包中的图片
                this.HomePageInterfaceStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//获取resource下images中的图片
                //去除主窗口外边框
                this.HomePageInterfaceStage.initStyle(StageStyle.TRANSPARENT);

                //加载启动类
                HomePageInterface();
        }

        public void HomePageInterface() throws Exception {
        	   APPAutomationCenter();
//        	   Stage HomePageInterfaceStage = new Stage();
        	   HomePageInterfaceStage = new Stage();
        	   HomePageInterfaceStage.initStyle(StageStyle.TRANSPARENT);
        	   HomePageInterfaceStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//获取resource下images中的图片

        	    StackPane HomePageStack = new StackPane();
//        	    HomePageStack.setLayoutX(100);
//	        	HomePageStack.setLayoutY(20);
      	        HomePageStack.getStylesheets().add(this.getClass().getResource("/css/HomePage.css").toString());

	        	HomePagePane.setPrefWidth(1600);
	        	HomePagePane.setPrefHeight(1000);
	        	HomePagePane.setId("HomePagePane");

//	        	WebView HomePageBackgroundWebView = new WebView();
//	        	HomePageBackgroundWebView.setLayoutX(0);
//	        	HomePageBackgroundWebView.setLayoutY(0);
//	        	HomePageBackgroundWebView.setPrefSize(1600, 1000);
//
//	    		WebEngine HomePageBackgroundWebEngine = HomePageBackgroundWebView.getEngine();
//	    		HomePageBackgroundWebEngine.load(this.getClass().getResource("/html/HomePage/Background/index.html").toString());

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

//	        	Button SettingButton = new Button();
//	        	SettingButton.setId("SettingButton");
//	        	SettingButton.setPrefWidth(30);
//	        	SettingButton.setPrefHeight(27);
//        		SettingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//	    		    @Override
//	    		    public void handle(MouseEvent event) {
//	    		    	HomePageInterfaceStage.close();
//	    		    	SystemSettingInterface();
//	    		    	SystemSettingStageStatus=1;
//	    		    }
//	    		});

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
	    		MinCloseHox.setPickOnBounds(false);//面板不参与计算边界，透明区域无鼠标事件
	    		MinCloseHox.getChildren().addAll(MinimizationButton,CloseButton);

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

//	    		UserSaveData usd = usdb.get(Account);
//	    		Image HeadIamge = new Image(usd.getHead(), 50,50,true, false);
	    		String avatar = this.getClass().getResource("/image/LoginPane/HeadPortraitImage/HeadPortrait1.gif").toExternalForm();
	    		Image HeadIamge = new Image(avatar, 50,50,true, false);
	    		HeadImageView.setLayoutX(20);
	    		HeadImageView.setLayoutY(10);
	    		HeadImageView.setImage(HeadIamge);
	    		HeadImageView.setClip(HeadRectangle);

	    		UserNameLabel.setLayoutX(79);
	    		UserNameLabel.setLayoutY(12);
	    		UserNameLabel.setId("UserNameLabel");
//	    		Object[] UserLoginInfo =LoginController.WebUserLoginInfo(Account, PassWord);
//      		    String UserName = (String) UserLoginInfo[0];
//	    		UserNameLabel.setText(UserName);
	    		UserNameLabel.setText("小  智");
	    		
	    		UserTypeLabel.setLayoutX(80);
	    		UserTypeLabel.setLayoutY(38);
	    		UserTypeLabel.setId("UserTypeLabel");
//      		    String UserType =String.valueOf((int) UserLoginInfo[2]) ;
//      		    if("1".equals(UserType)){
//      		    	UserTypeLabel.setText("游   客");
//      		    }if("2".equals(UserType)){
//      		    	UserTypeLabel.setText("旅 行 社");
//      		    }
      		    UserTypeLabel.setText("游 客 组");
	    		
//      		    System.out.println(UserNameLabel.getText().length());
	    		if(UserNameLabel.getText().length()>10){
	    			SwitchButton.setLayoutX(165);
		    		SwitchButton.setLayoutY(39);
	    		}else{
	    		SwitchButton.setLayoutX(165);
	    		SwitchButton.setLayoutY(22);
	    		}
	    		SwitchButton.setPrefWidth(20);
	    		SwitchButton.setPrefHeight(20);
	    		SwitchButton.setId("SwitchButton");
	    		SwitchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	HomePageInterfaceStage.close();
	    		    	LoginPageView.LoginStatus=1;
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
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButtonDown");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		    	HomePageRightPane.setVisible(true);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		FrontEndFunctionCenterButton.setLayoutX(0);
	    		FrontEndFunctionCenterButton.setLayoutY(115+50);
	    		FrontEndFunctionCenterButton.setPrefWidth(219);
	    		FrontEndFunctionCenterButton.setPrefHeight(50);
	    		FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		FrontEndFunctionCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButtonDown");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(true);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		BackendFunctionCenterButton.setLayoutX(0);
	    		BackendFunctionCenterButton.setLayoutY(115+100);
	    		BackendFunctionCenterButton.setPrefWidth(219);
	    		BackendFunctionCenterButton.setPrefHeight(50);
	    		BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		BackendFunctionCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButtonDown");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(true);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		WEBAutomationCenterButton.setLayoutX(0);
	    		WEBAutomationCenterButton.setLayoutY(115+150);
	    		WEBAutomationCenterButton.setPrefWidth(219);
	    		WEBAutomationCenterButton.setPrefHeight(50);
	    		WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		WEBAutomationCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButtonDown");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(true);
//	    		    	APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		APIAutomationCenterButton.setLayoutX(0);
	    		APIAutomationCenterButton.setLayoutY(115+200);
	    		APIAutomationCenterButton.setPrefWidth(219);
	    		APIAutomationCenterButton.setPrefHeight(50);
	    		APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		APIAutomationCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButtonDown");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(true);
	    		    	APPAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		APPAutomationCenterButton.setLayoutX(0);
	    		APPAutomationCenterButton.setLayoutY(115+250);
	    		APPAutomationCenterButton.setPrefWidth(219);
	    		APPAutomationCenterButton.setPrefHeight(50);
	    		APPAutomationCenterButton.setId("APPAutomationCenterButtonDown");
	    		APPAutomationCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButtonDown");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPane.setVisible(true);
	    		    }
	    		});

	    		PerformanceAutomationCenterButton.setLayoutX(0);
	    		PerformanceAutomationCenterButton.setLayoutY(115+300);
	    		PerformanceAutomationCenterButton.setPrefWidth(219);
	    		PerformanceAutomationCenterButton.setPrefHeight(50);
	    		PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		PerformanceAutomationCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButtonDown");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPane.setVisible(false);
	    		    }
	    		});
	    		
	    		LeftPane.getChildren().add(HeadImageView);
	    		LeftPane.getChildren().add(UserNameLabel);
	    		LeftPane.getChildren().add(UserTypeLabel);
	    		LeftPane.getChildren().add(SwitchButton);
	    		LeftPane.getChildren().add(SystemMasterInterfaceButton);
	    		LeftPane.getChildren().add(FrontEndFunctionCenterButton);
	    		LeftPane.getChildren().add(BackendFunctionCenterButton);
	    		LeftPane.getChildren().add(WEBAutomationCenterButton);
	    		LeftPane.getChildren().add(APIAutomationCenterButton);
	    		LeftPane.getChildren().add(APPAutomationCenterButton);
	    		LeftPane.getChildren().add(PerformanceAutomationCenterButton);
	    		
	    		HomePageRightPane.setLayoutX(222);
	    		HomePageRightPane.setLayoutY(115);
	    		HomePageRightPane.setPrefWidth(1381);
	    		HomePageRightPane.setPrefHeight(886);
	    		HomePageRightPane.setId("HomePageRightPane");
	    		HomePageRightPane.setVisible(false);
	    		
	    		WebView BackgroundWebView = new WebView();
	    		BackgroundWebView.setLayoutX(0);
	    		BackgroundWebView.setLayoutY(0);
	    		BackgroundWebView.setPrefSize(1381, 886);

	    		WebEngine BackgroundWebEngine = BackgroundWebView.getEngine();
	    		BackgroundWebEngine.load(this.getClass().getResource("/html/HomePage/Animation/index.html").toString());

//	    		Image VideoPlayerIamge = new Image(this.getClass().getResource("/image/HomePagePane/HomePageRightPane/VideoPlayer_Background_1381x886.png").toExternalForm(), true);
//	    		ImageView VideoPlayerIamgeView = new ImageView();
//	    		VideoPlayerIamgeView.setLayoutX(0);
//	    		VideoPlayerIamgeView.setLayoutY(0);
//	    		VideoPlayerIamgeView.setImage(VideoPlayerIamge);
//	    		VideoPlayerIamgeView.setEffect(dropShadow);//增加阴影效果
//
//	    		Rectangle VideoPlayerRectangle = new Rectangle();
//	    		VideoPlayerRectangle.setArcHeight(10);
//	    		VideoPlayerRectangle.setArcWidth(10);
//	    		VideoPlayerRectangle.setWidth(1381);
//	    		VideoPlayerRectangle.setHeight(886);

	    		VideoPlayerWebView.setLayoutX(355);
	    		VideoPlayerWebView.setLayoutY(313);
	    		VideoPlayerWebView.setPrefSize(700, 488);
	    		VideoPlayerWebView.setVisible(false);

	    		WebEngine VideoPlayerWebEngine = VideoPlayerWebView.getEngine();
	    		VideoPlayerWebEngine.load(this.getClass().getResource("/html/HomePage/VideoPlayer/index.html").toString());

	    		Button VideoPlayerCloseButton = new Button();
	    		VideoPlayerCloseButton.setLayoutX(370+700-45);
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
	    		VideoPlayerButton.setLayoutY(650+73);
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
   		    			public void handle(MouseEvent event){
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
	    		HomePagePane.getChildren().add(HomePageRightPane);
	    		APPAutomationCenterPane.setVisible(true);
	    		
	        	Rectangle HomePageRectangle = new Rectangle();
	            HomePageRectangle.setWidth(1600);
	        	HomePageRectangle.setHeight(1000);
	        	HomePageRectangle.setArcHeight(10);
	            HomePageRectangle.setArcWidth(10);

	        	HomePageStack.getChildren().addAll(HomePagePane);
	        	HomePageStack.setClip(HomePageRectangle);

	    		//拖动监听器
	    		DragUtil.addDragListener(HomePageInterfaceStage,HomePageStack);

	    		//构建一个场景Scene,加载包含根布局的场景
	    		Scene scene = new Scene(HomePageStack);
	    		//Scene透明
	    		scene.setFill(null);

	    		//scene加载到新的Stage中
	            HomePageInterfaceStage.setScene(scene);
	            //设置让对话框处于屏幕中间
	            HomePageInterfaceStage.centerOnScreen();
	            //显示布局
	            HomePageInterfaceStage.show();
        }

		public static void APPAutomationCenter() throws Exception{
        	    APPAutomationCenterPane.setLayoutX(222);
        	    APPAutomationCenterPane.setLayoutY(115);
        	    APPAutomationCenterPane.setPrefWidth(1381);
        	    APPAutomationCenterPane.setPrefHeight(886);
        	    APPAutomationCenterPane.setId("APPAutomationCenterPane");
        	    APPAutomationCenterPane.setVisible(false);

	    	    ProgressIndicator DownloadProgressBar = new ProgressIndicator();
	    	    DownloadProgressBar.setMaxSize(35, 35);
	    	    DownloadProgressBar.setVisible(false);
  
	    	    Label DownloadProgressLabel = new Label();
	    	    DownloadProgressLabel.setFont(new Font(11));
	    	    DownloadProgressLabel.setText("0%");
	    	    DownloadProgressLabel.setStyle("-fx-text-fill: white");
	    	    DownloadProgressLabel.setVisible(false);
	    	    
        	    SoftwareEngineeringField.setLayoutX(115);
        	    SoftwareEngineeringField.setLayoutY(10);
        	    SoftwareEngineeringField.setPrefWidth(300);
        	    SoftwareEngineeringField.setPrefHeight(30);
        	    SoftwareEngineeringField.setId("OptionEntryBoxLL");
        	    SoftwareEngineeringField.setPromptText("请输入工程地址...");
        	    SoftwareEngineeringField.setPrefColumnCount(20);//设置文本最大显示内容长度

        	    SoftwareEngineeringButton.setLayoutX(415);
        	    SoftwareEngineeringButton.setLayoutY(10);
        	    SoftwareEngineeringButton.setPrefWidth(31);
        	    SoftwareEngineeringButton.setPrefHeight(30);
        	    SoftwareEngineeringButton.setId("ListViewButton");
        	    SoftwareEngineeringButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	if(HomePageView.AccountLoginOpen==0){
	                		HomePageView.AccountLoginCenterPane.setVisible(true);
	                	}else if(SendPeopleNumberField.getText().equals("5")){
	                		HomePageView.AccountLoginCenterPane.setVisible(true);
	                	}else{
	                		String SoftwareEngineeringurl=SoftwareEngineeringField.getText().replace(".git", "");
		                	if(StringUtil.isEmpty(SoftwareEngineeringurl)){
	                			Dialog.SetMessageDialog("Warning","请输入工程地址！");
		                	}else{
		                		SoftwareEngineeringButton.setDisable(true);
	                			DownloadProgressBar.setLayoutX(450);
	    	      	    	    DownloadProgressBar.setLayoutY(8);
	    	      	    	    DownloadProgressLabel.setLayoutX(457);
	    	      	    	    DownloadProgressLabel.setLayoutY(17);
	    	                	DownloadProgressBar.setVisible(true);
	    		    	    	DownloadProgressLabel.setVisible(true);
	    	                	Worker = CreateWorker("SoftwareEngineering");
//	    	                	DownloadProgressBar.progressProperty().bind(Worker.progressProperty());
	    	                	DownloadProgressLabel.textProperty().bind(Worker.messageProperty());
	    	                	new Thread(Worker).start();
	    	                	Timer timer = new Timer();
	    		    			timer.schedule(new TimerTask() {
	    		    				@Override
	    		    				public void run() {
//	    		    					SoftwareEngineering ="Appium";
	    		    					SoftwareEngineering = SoftwareEngineeringurl.substring(SoftwareEngineeringurl.lastIndexOf("/")+1);
	    		    					String SoftwareEngineeringPath = ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"";
	    		    					String Account = HomePageView.AccountField.getText();
			    					    String PassWord = HomePageView.PasswrodField.getText();
	    		    					try {
	    		    						String message =JGitUtil.CloneRepository(SoftwareEngineeringurl,SoftwareEngineeringPath,Account,PassWord);
	    			                		if(message=="Success"){
	    			                			Platform.runLater(new Runnable() {
	        			    	    			    @Override
	        			    	    			    public void run() {
	        			    	    			    	Dialog.SetMessageDialog("Success","工程下载成功！");
	            			                			SoftwareEngineeringField.setText(SoftwareEngineering.replace(".git", ""));
	                			                		DownloadProgressBar.setVisible(false);
	                						    	    DownloadProgressLabel.setVisible(false);
	                						    	    SoftwareEngineeringButton.setDisable(false);
	        			    	    			    }
	        			    	    			});
	    			                		}else if(message=="AlreadyExisted"){
	    			                			Platform.runLater(new Runnable() {
	        			    	    			    @Override
	        			    	    			    public void run() {
	        			    	    			    	DownloadProgressLabel.textProperty().unbind();
	        			    	    			    	Dialog.SetMessageDialog("Warning","工程已存在，请勿重复下载！");
	        			    	    			    	SoftwareEngineeringField.setText(SoftwareEngineering.replace(".git", ""));
	        			    	    			    	DownloadProgressBar.setVisible(false);
	        			    	    			    	DownloadProgressLabel.setVisible(false);
	        			    	    			    	SoftwareEngineeringButton.setDisable(false);
	        						                	String Dir = System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"";
	        						                	OpenDir(Dir);
	        						                	OpenFile(Dir);
	        			    	    			    }
	        			    	    			});
	    			                		}else{
	    			                			Platform.runLater(new Runnable() {
	        			    	    			    @Override
	        			    	    			    public void run() {
	        			    	    			    	DownloadProgressLabel.textProperty().unbind();
	        			    	    			    	Dialog.SetMessageDialog("Warning","工程地址错误丨账号密码错误丨网络连接失败，请检查后重试！");
	        			    	    			    	DownloadProgressBar.setVisible(false);
	        			    	    			    	DownloadProgressLabel.setVisible(false);
	        			    	    			    	SoftwareEngineeringButton.setDisable(false);
	        			    	    			    	HomePageView.AccountLoginCenterPane.setVisible(true);
	        			    	    			    }
	        			    	    			});
	    			                		}
	    								} catch (Exception e) {
	    									e.printStackTrace();
	    							    }
	    		    				}
	    		    			}, 200);
	                		}
	                	}
	                }
	            });
        	    
	    	    final ToggleGroup SoftwareNameToggleGroup = new ToggleGroup();
        	    WeChatOptionRadioButton.setLayoutX(115);
        	    WeChatOptionRadioButton.setLayoutY(79);
        	    WeChatOptionRadioButton.setPrefWidth(5);
        	    WeChatOptionRadioButton.setPrefHeight(5);
        	    WeChatOptionRadioButton.setToggleGroup(SoftwareNameToggleGroup);
        	    WeChatOptionRadioButton.setUserData("微信");
        	    WeChatOptionRadioButton.setSelected(true);
        	    
        	    QQOptionRadioButton.setLayoutX(214);
        	    QQOptionRadioButton.setLayoutY(79);
        	    QQOptionRadioButton.setPrefWidth(5);
        	    QQOptionRadioButton.setPrefHeight(5);
        	    QQOptionRadioButton.setToggleGroup(SoftwareNameToggleGroup);
        	    QQOptionRadioButton.setUserData("QQ");

        	    OtherOptionRadioButton.setLayoutX(313);
        	    OtherOptionRadioButton.setLayoutY(79);
        	    OtherOptionRadioButton.setPrefWidth(5);
        	    OtherOptionRadioButton.setPrefHeight(5);
        	    OtherOptionRadioButton.setToggleGroup(SoftwareNameToggleGroup);
        	    OtherOptionRadioButton.setUserData("其他");
        	    
        	    SoftwareNameToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        	        public void changed(ObservableValue<? extends Toggle> ov,
        	            Toggle old_toggle, Toggle new_toggle) {
        	          if (SoftwareNameToggleGroup.getSelectedToggle() != null) {
//        	        	  System.out.println(SoftwareNameGroup.getSelectedToggle().getUserData().toString());
        	        	  String SoftwareName = SoftwareNameToggleGroup.getSelectedToggle().getUserData().toString();
        	        	  if("微信".equals(SoftwareName)||"QQ".equals(SoftwareName)){
        	        		  SoftwareDownloadsField.clear();
        	  	    	      SoftwareDownloadsField.setEditable(false);
        	        	  }else{
        	        		  SoftwareDownloadsField.requestFocus();
        	        		  SoftwareDownloadsField.setEditable(true);
        	        	  }
        	          }
        	        }
        	    });

	    	    SoftwareIcon = "/image/HomePagePane/RightPane/APPAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
	    		Image SoftwareIconIamge = new Image(SoftwareIcon, 65,65,true, false);
	    		SoftwareIconImageView.setImage(SoftwareIconIamge);
	    		
	    		SoftwareNameLabel.setId("SoftwareNameLabel");
	    		SoftwareNameLabel.setText("其它");
	    		
	    		VBox SoftwareVBox = new VBox();
	    		SoftwareVBox.setLayoutX(337);
	    		SoftwareVBox.setLayoutY(53);
	    		SoftwareVBox.setSpacing(2);
	    		SoftwareVBox.setAlignment(Pos.CENTER);
	    		SoftwareVBox.getChildren().add(SoftwareIconImageView);
	    		SoftwareVBox.getChildren().add(SoftwareNameLabel);

        	    StringConverter converter = new StringConverter<LocalDate>() {
	    	        DateTimeFormatter dateFormatter =
	    	            DateTimeFormatter.ofPattern(pattern);
	    	        @Override
	    	        public String toString(LocalDate date) {
	    	            if (date != null) {
	    	                return dateFormatter.format(date);
	    	            } else {
	    	                return "";
	    	            }
	    	        }
	    	        @Override
	    	        public LocalDate fromString(String string) {
	    	            if (string != null && !string.isEmpty()) {
	    	                return LocalDate.parse(string, dateFormatter);
	    	            } else {
	    	                return null;
	    	            }
	    	        }
	    	    };
	    	    checkOutDatePicker.setLayoutX(115);
	    	    checkOutDatePicker.setLayoutY(147);
	    	    checkOutDatePicker.setPrefWidth(183);
	    	    checkOutDatePicker.setPrefHeight(30);
	    	    checkInDatePicker.setValue(LocalDate.now());
	    	    final Callback<DatePicker, DateCell> dayCellFactory =
	    	        new Callback<DatePicker, DateCell>() {
	    	            @Override
	    	            public DateCell call(final DatePicker datePicker) {
	    	                return new DateCell() {
	    	                    @Override
	    	                    public void updateItem(LocalDate item, boolean empty) {
	    	                        super.updateItem(item, empty);
	    	                        if (item.isBefore(
	    	                                checkInDatePicker.getValue().plusDays(0))
	    	                            ) {
//	    	                                setDisable(true);
//	    	                                setStyle("-fx-background-color: #ffc0cb;");
	    	                        }
	    	                        long p = ChronoUnit.DAYS.between(
	    	                                checkInDatePicker.getValue(), item
	    	                        );
	    	                        setTooltip(new Tooltip(
	    	                            "You're about to stay for " + p + " days")
	    	                        );
	    	                }
	    	            };
	    	        }
	    	    };
	    	    checkOutDatePicker.setConverter(converter);
	    	    checkOutDatePicker.setDayCellFactory(dayCellFactory);
	    	    checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(0));//当前时间加一天

	    	    SoftwareDownloadsField.setLayoutX(115);
	    	    SoftwareDownloadsField.setLayoutY(191);
	    	    SoftwareDownloadsField.setPrefWidth(300);
	    	    SoftwareDownloadsField.setPrefHeight(30);
	    	    SoftwareDownloadsField.setId("OptionEntryBoxLL");
	    	    SoftwareDownloadsField.setPromptText("请输入软件下载地址，推荐使用蒲公英...");
	    	    SoftwareDownloadsField.setPrefColumnCount(20);//设置文本最大显示内容长度
	    	    SoftwareDownloadsField.setEditable(false);//禁止输入
	    	    
	    	    SoftwareDownloadsButton.setLayoutX(415);
	    	    SoftwareDownloadsButton.setLayoutY(191);
	    	    SoftwareDownloadsButton.setPrefWidth(31);
	    	    SoftwareDownloadsButton.setPrefHeight(30);
	    	    SoftwareDownloadsButton.setId("ListViewButton");
	    	    SoftwareDownloadsButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                    if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if (StringUtil.isEmpty(SoftwareEngineering)) {
                            Dialog.SetMessageDialog("Warning","请先下载工程！");
                        }else if (StringUtil.isEmpty(String.valueOf(SoftwareDate))) {
		    	        	Dialog.SetMessageDialog("Warning","请输入软件日期！");
		                }else{
		                	DownloadProgressBar.setLayoutX(450);
		      	    	    DownloadProgressBar.setLayoutY(189);
		      	    	    DownloadProgressLabel.setLayoutX(457);
		      	    	    DownloadProgressLabel.setLayoutY(198);
		                	DownloadProgressBar.setVisible(true);
			    	    	DownloadProgressLabel.setVisible(true);
		                	Worker = CreateWorker("SoftwareDownloads");
//		                	DownloadProgressBar.progressProperty().bind(Worker.progressProperty());
		                	DownloadProgressLabel.textProperty().bind(Worker.messageProperty());
		                	new Thread(Worker).start();
		                	Timer timer = new Timer();
			    			timer.schedule(new TimerTask() {
			    				@Override
			    				public void run() {
				                    	String AppName = SoftwareNameToggleGroup.getSelectedToggle().getUserData().toString();
						    	        SoftwareDate = checkOutDatePicker.getValue();
						    	        String Date = String.valueOf(SoftwareDate);
						    	        String NewDate = Date.replaceAll("-", "_");  
						    	        if ("微信".equals(AppName)) {
						    	        	if("".equals(SoftwareDownloadsField.getText())){
						    	        		try {
						    	        			SoftwareDownloadsButton.setDisable(true);
//							    	        		String DownloadUrl ="http://oih492dqz.bkt.clouddn.com/lkkm_2.1.0_31_"+NewDate+"_android.apk";
								                	String DownloadUrl ="https://dldir1.qq.com/weixin/android/weixin800android1840_arm64.apk";
									    	    	String SavePath = ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\TestData\\App\\";
//									    	    	String FileName = "LekeU.apk";
									    	    	String FileName = "WeChat.apk";
												    String AppPath =APPAutomationCenterPageController.DownLoadFromUrl(DownloadUrl, SavePath, FileName).replace(""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\", "");
							    	        		Platform.runLater(new Runnable() {
		    			                				@Override
		        			    	    			    public void run() {
		    			                					Dialog.SetMessageDialog("Success","软件下载成功！");
	    												    SoftwareDownloadsField.clear();
	    												    SoftwareDownloadsField.setText(AppPath);	
		    			                				}
	        			    	    			    }); 
						    	        		}catch (Exception e) {
							                    	Platform.runLater(new Runnable() {
							    	    			    @Override
							    	    			    public void run() {
							    	    			    	e.printStackTrace();
							    	    			    	DownloadProgressLabel.textProperty().unbind();
							    	    			    	Dialog.SetMessageDialog("Warning","网络连接失败，请检查后重试！");
							    	    			    	DownloadProgressBar.setVisible(false);
											    	    	DownloadProgressLabel.setVisible(false);
											    	    	SoftwareDownloadsButton.setDisable(false);
											    	    	SoftwareDownloadsField.setEditable(true);
							    	    			    }
							    	    			});
												}
	    			                		}else if("TestData\\App\\WeChat.apk".equals(SoftwareDownloadsField.getText())){
	    			                			Platform.runLater(new Runnable() {
	    			                				@Override
	        			    	    			    public void run() {
	        			    	    			    	DownloadProgressLabel.textProperty().unbind();
	        			    	    			    	Dialog.SetMessageDialog("Warning","软件已存在，请勿重复下载！");
	        			    	    			    	DownloadProgressBar.setVisible(false);
	        			    	    			    	DownloadProgressLabel.setVisible(false);
	        						                	String Dir = System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\TestData\\App";
	        						                	OpenDir(Dir);
	        			    	    			    }
        			    	    			    });
	    			                		}
						                }else if ("QQ".equals(AppName)) {
						                	if("".equals(SoftwareDownloadsField.getText())){
						                		try {
						                			SoftwareDownloadsButton.setDisable(true);
//							    	        		String DownloadUrl ="http://oih492dqz.bkt.clouddn.com/lkkmwy_1.0.5_11_"+NewDate+"_android.apk";
								                	String DownloadUrl ="https://down.qq.com/qqweb/QQ_1/android_apk/Android_8.5.5.5105_537067016.apk";
								                	String SavePath = ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\TestData\\App\\";
//									    	    	String FileName = "LekeW.apk";
									    	    	String FileName = "QQ.apk";
													String AppPath =APPAutomationCenterPageController.DownLoadFromUrl(DownloadUrl, SavePath, FileName).replace(""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\", "");
													Platform.runLater(new Runnable() {
		    			                				@Override
		        			    	    			    public void run() {
		    			                					Dialog.SetMessageDialog("Success","软件下载成功！");
	    													SoftwareDownloadsField.clear();
	    													SoftwareDownloadsField.setText(AppPath);	
		    			                				}
	        			    	    			    });
						                		}catch (Exception e) {
							                    	Platform.runLater(new Runnable() {
							    	    			    @Override
							    	    			    public void run() {
							    	    			    	e.printStackTrace();
							    	    			    	DownloadProgressLabel.textProperty().unbind();
							    	    			    	Dialog.SetMessageDialog("Warning","网络连接失败，请检查后重试！");
							    	    			    	DownloadProgressBar.setVisible(false);
											    	    	DownloadProgressLabel.setVisible(false);
											    	    	SoftwareDownloadsButton.setDisable(false);
											    	    	SoftwareDownloadsField.setEditable(true);
							    	    			    }
							    	    			});
												}	   
	    			                		}else if("TestData\\App\\QQ.apk".equals(SoftwareDownloadsField.getText())){
	    			                			Platform.runLater(new Runnable() {
	    			                				@Override
	        			    	    			    public void run() {
	        			    	    			    	DownloadProgressLabel.textProperty().unbind();
	        			    	    			    	Dialog.SetMessageDialog("Warning","软件已存在，请勿重复下载！");
	        			    	    			    	DownloadProgressBar.setVisible(false);
	        			    	    			    	DownloadProgressLabel.setVisible(false);
	        						                	String Dir = System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\TestData\\App";
	        						                	OpenDir(Dir);
	        			    	    			    }
        			    	    			    });
	    			                	    }     	
						              }else{
						            	  if("".equals(SoftwareDownloadsField.getText())){
						            		  Platform.runLater(new Runnable() {
	    			                				@Override
	        			    	    			    public void run() {
	    			                					Dialog.SetMessageDialog("Warning","请输入软件下载地址！");
	    			                					try {
															OpenBrowserUtil.OpenBrowser("https://www.pgyer.com/app/publish");
														} catch (Exception e) {
															e.printStackTrace();
														}
	    			                				}
  			    	    			          });
						            	  }else{
						            		  try{ 
						            			  SoftwareDownloadsButton.setDisable(true);
								                   String DownloadUrl =SoftwareDownloadsField.getText();
								                   String SavePath = ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\TestData\\App\\";
								                   String ApkPath =APPAutomationCenterPageController.DownLoadFromUrlParam(DownloadUrl, SavePath,"%3D");
								                   String NewApkPath =ApkPath.replace(""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\TestData\\App\\", "");
								                   if("null".equals(NewApkPath)){
								                	   Platform.runLater(new Runnable() {
								    	    			    @Override
								    	    			    public void run() {
								    	    			    	DownloadProgressLabel.textProperty().unbind();
								    	    			    	Dialog.SetMessageDialog("Warning","下载地址错误或网络连接失败，请检查后重试！");
								    	    			    	DownloadProgressBar.setVisible(false);
												    	    	DownloadProgressLabel.setVisible(false);
												    	    	SoftwareDownloadsButton.setDisable(false);
								    	    			    }
								    	    		  });
								                   }else{
								                	   try{
									                	   ApkInfo apkInfo = new ApkUtil().getApkInfo(ApkPath);
												           System.out.println(apkInfo);
												           String apkName = apkInfo.getApplicationLable(); 
												           String apkPackageName = apkInfo.getPackageName(); 
												           String apkActivity = apkInfo.getLaunchableActivity();
//												           SoftwareIcon = ""+ApkIconUtil.extractFileFromApk(ApkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\src\\main\\resources\\image\\HomePagePane\\RightPane\\APPAutomationCenterPane\\SoftwareIcon\\" + apkName + ".png");
														   SoftwareIcon = ApkIconUtil.extractFileFromApk(ApkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.dir")+"/src/main/resources/image/HomePagePane/RightPane/APPAutomationCenterPane/SoftwareIcon/" + apkName + ".png");
												    	   Platform.runLater(new Runnable() {
									    	    			    @Override
									    	    			    public void run() {
									    	    			    	SoftwareNameLabel.setText(apkName);
									    	    			    	Image SoftwareIconIamge = new Image("file:///"+SoftwareIcon, 65,65,true, false);
									    	    			    	SoftwareIconImageView.setImage(SoftwareIconIamge);
									    	    			    	SoftwareDownloadsField.clear();
															    	SoftwareDownloadsField.setText(apkPackageName);   
															    	SoftwareDownloadsField.appendText("丨"+apkActivity);
															    	Dialog.SetMessageDialog("Success","软件下载并解析成功！");
									    	    			    }
									    	    		  });
									                   }catch (Exception e) {
									                	   e.printStackTrace();
									                	   Platform.runLater(new Runnable() {
									    	    			    @Override
									    	    			    public void run() {
									    	    			    	Dialog.SetMessageDialog("Warning","无法正常解析APK包，请重新下载！");
									    	    			    }
									    	    		  });  
													   }
								                   }
						            		  }catch (Exception e) {
							                	   e.printStackTrace();
							                	   Platform.runLater(new Runnable() {
							    	    			    @Override
							    	    			    public void run() {
							    	    			    	DownloadProgressLabel.textProperty().unbind();
							    	    			    	Dialog.SetMessageDialog("Warning","下载地址错误或网络连接失败，请检查后重试！");
							    	    			    	DownloadProgressBar.setVisible(false);
											    	    	DownloadProgressLabel.setVisible(false);
											    	    	SoftwareDownloadsButton.setDisable(false);
							    	    			    }
							    	    		  });
											   } 
						            	  }
						              }
						             DownloadProgressBar.setVisible(false);
						    	     DownloadProgressLabel.setVisible(false);
						    	    SoftwareDownloadsButton.setDisable(false);
				                }
			    			}, 200);
		                }
	                }
	            });

	    	    TestObjectField.setLayoutX(115);
	    	    TestObjectField.setLayoutY(235);
	    	    TestObjectField.setPrefWidth(300);
	    	    TestObjectField.setPrefHeight(30);
	    	    TestObjectField.setId("OptionEntryBoxLL");
	    	    TestObjectField.setPromptText("请选择测试对象文件...");
	    	    TestObjectField.setPrefColumnCount(20);//设置文本最大显示内容长度
	    	    
	    	    fileChooser.getExtensionFilters().addAll(
	    	    	new FileChooser.ExtensionFilter("All Images", "*.*"),
	    	    	new FileChooser.ExtensionFilter("properties", "*.properties"),
                    new FileChooser.ExtensionFilter("JAVA", "*.java"),
                    new FileChooser.ExtensionFilter("XML", "*.xml")
//                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                    new FileChooser.ExtensionFilter("GIF", "*.gif"),
//                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
//                    new FileChooser.ExtensionFilter("PNG", "*.png")     
	             );

	    	    TestObjectButton.setLayoutX(415);
	    	    TestObjectButton.setLayoutY(235);
	    	    TestObjectButton.setPrefWidth(31);
	    	    TestObjectButton.setPrefHeight(30);
	    	    TestObjectButton.setId("ListViewButton");
	    	    TestObjectButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                    if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if (StringUtil.isEmpty(SoftwareEngineering)) {
                            Dialog.SetMessageDialog("Warning","请先下载工程！");
                        }else{
		                	try{
		                		fileChooser.setTitle("请选择需要运行的测试对象文件");
			                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\TestCases"));                 
			                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
			                	if (file != null) {
			                		String TestObjectPath = file.getPath();
			                		if("请选择测试对象文件...".equals(TestObjectField.getText())|StringUtil.isEmpty(TestObjectField.getText())){
				                        TestObjectField.setText(TestObjectPath.replace(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\", ""));
			                	    }else{
			                            OpenFile(TestObjectPath);
			                            TestObjectField.setText(TestObjectPath.replace(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\", ""));
//			                            System.out.println(file.getPath());
			                	    }
			                	}
			                } catch (Exception e) {
								e.printStackTrace();
								Dialog.SetMessageDialog("Error","未找到工程目录，请下载正确的工程！");
		                	}
	                    }
	                }
	    	    });
	    	    
	    	    TestScriptsField.setLayoutX(115);
	    	    TestScriptsField.setLayoutY(279);
	    	    TestScriptsField.setPrefWidth(300);
	    	    TestScriptsField.setPrefHeight(30);
	    	    TestScriptsField.setId("OptionEntryBoxLL");
	    	    TestScriptsField.setPromptText("请选择测试脚本文件...");
	    	    TestScriptsField.setPrefColumnCount(20);//设置文本最大显示内容长度
	    	    
	    	    TestScriptsButton.setLayoutX(415);
	    	    TestScriptsButton.setLayoutY(279);
	    	    TestScriptsButton.setPrefWidth(31);
	    	    TestScriptsButton.setPrefHeight(30);
	    	    TestScriptsButton.setId("ListViewButton");
	    	    TestScriptsButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                    if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if (StringUtil.isEmpty(SoftwareEngineering)) {
                            Dialog.SetMessageDialog("Warning","请先下载工程！");
                        }else{
		                	try{
		                		fileChooser.setTitle("请选择需要运行的测试脚本文件");
			                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\TestCaseXml"));                 
			                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
			                	if (file != null) {
			                		String TestObjectPath = file.getPath();
			                		if("请选择测试脚本文件...".equals(TestScriptsField.getText())|StringUtil.isEmpty(TestScriptsField.getText())){
			                			TestScriptsField.setText(TestObjectPath.replace(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\", ""));
			                	    }else{
			                            OpenFile(TestObjectPath);
			                            TestScriptsField.setText(TestObjectPath.replace(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\", ""));
//			                            System.out.println(file.getPath());
			                	    }
			                	}
			                } catch (Exception e) {
								e.printStackTrace();
								Dialog.SetMessageDialog("Error","未找到工程目录，请下载正确的工程！");
		                	}
		                }
	                }
	    	    });

	    	    TestScriptsAggregateField.setLayoutX(115);
	    	    TestScriptsAggregateField.setLayoutY(323);
	    	    TestScriptsAggregateField.setPrefWidth(300);
	    	    TestScriptsAggregateField.setPrefHeight(30);
	    	    TestScriptsAggregateField.setId("OptionEntryBoxLL");
	    	    TestScriptsAggregateField.setPromptText("请选择测试脚本集合文件...");
	    	    TestScriptsAggregateField.setPrefColumnCount(20);//设置文本最大显示内容长度
	    	    
	    	    TestScriptsAggregateButton.setLayoutX(415);
	    	    TestScriptsAggregateButton.setLayoutY(323);
	    	    TestScriptsAggregateButton.setPrefWidth(31);
	    	    TestScriptsAggregateButton.setPrefHeight(30);
	    	    TestScriptsAggregateButton.setId("ListViewButton");
	    	    TestScriptsAggregateButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                    if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if (StringUtil.isEmpty(SoftwareEngineering)) {
                            Dialog.SetMessageDialog("Warning","请先下载工程！");
                        }else{
		                	try{
		                		fileChooser.setTitle("请选择需要运行的测试脚本集合文件");
			                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\TestReportXml"));                 
			                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
			                	if (file != null) {
			                		String TestObjectPath = file.getPath();
			                		if("请选择测试脚本集合文件...".equals(TestScriptsAggregateField.getText())|StringUtil.isEmpty(TestScriptsAggregateField.getText())){
			                			TestScriptsAggregateField.setText(TestObjectPath.replace(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\", ""));
			                	    }else{
			                            OpenFile(TestObjectPath);
			                            TestScriptsAggregateField.setText(TestObjectPath.replace(System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"\\src\\test\\java\\", ""));
//			                            System.out.println(file.getPath());
			                	    }
			                	}
			                } catch (Exception e) {
								e.printStackTrace();
								Dialog.SetMessageDialog("Error","未找到工程目录，请下载正确的工程！");
		                	}
		                }
	                }
	    	    });
	    	    
	    	    CommitMessageTextArea.setLayoutX(115);
	    	    CommitMessageTextArea.setLayoutY(367);
	    	    CommitMessageTextArea.setPrefSize(330, 200); 
	    	    CommitMessageTextArea.setPromptText("请输入提交备注...");
	    	    CommitMessageTextArea.setPrefRowCount(22);  //设置行宽
	    	    CommitMessageTextArea.setPrefColumnCount(22);  //设置列宽  
	    	    CommitMessageTextArea.setWrapText(true);
	    	    CommitMessageTextArea.setId("CommitMessageTextArea");
	    	    
	    	    MailModeField.setLayoutX(114);
	    	    MailModeField.setLayoutY(578);
	    	    MailModeField.setPrefWidth(70);
	    	    MailModeField.setPrefHeight(30);
	    	    MailModeField.setId("InputBoxM");
	    	    MailModeField.setPromptText("请选择...");
	    	    MailModeField.setText("QQ");
	    	    MailModeField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    MailModeField.setBackground(Background.EMPTY);
	    	    MailModeField.setBorder(Border.EMPTY);
	    	    
	    	    MailModeButton.setLayoutX(184);
	    	    MailModeButton.setLayoutY(578);
	    	    MailModeButton.setPrefWidth(31);
	    	    MailModeButton.setPrefHeight(30);
	    	    MailModeButton.setId("ListViewButton");
	    	    MailModeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	ObservableList<String> data = FXCollections.observableArrayList();
  	    	  		    ListView<String> MailModeListView = new ListView<String>(data);
  	            	    String[] TrialCrowdName ={"smtp.qq.com","smtp.163.com","smtp.sina.com"};
  	            	    data.addAll(TrialCrowdName);
  	            	    MailModeListView.setItems(data);
  	            	    MailModeListView.setLayoutX(114);
  	            	    MailModeListView.setLayoutY(578+30);
  	            	    MailModeListView.setPrefSize(100, 80);
  	            	    MailModeListView.setEditable(true);
  	            	    MailModeListView.setId("ListView");
  	            	    MailModeListView.toFront();//激活当前图层到最上层
  	            	    MailModeListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
  	            	    	if(new_val=="smtp.qq.com"){
  	            	    		MailModeField.setText("QQ");
  	            	    	}else if(new_val=="smtp.163.com"){
  	            	    		MailModeField.setText("网易");
  	            	    	}else if(new_val=="smtp.sina.com"){
  	            	    		MailModeField.setText("新浪");
  	            	    	}
  	            	    	MailModeListView.setVisible(false);
      		  	        });
  	            	  APPAutomationCenterPane.getChildren().add(MailModeListView);	
	    	  	    }
	    	  	});
	    	    
	    	    SendPeopleNumberField.setLayoutX(345);
	    	    SendPeopleNumberField.setLayoutY(578);
	    	    SendPeopleNumberField.setPrefWidth(100);
	    	    SendPeopleNumberField.setPrefHeight(30);
	    	    SendPeopleNumberField.setId("InputBoxM");
	    	    SendPeopleNumberField.setPromptText("请输入人数...");
	    	    SendPeopleNumberField.setText("1");
	    	    SendPeopleNumberField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    SendPeopleNumberField.setBackground(Background.EMPTY);
	    	    SendPeopleNumberField.setBorder(Border.EMPTY);
	    	    SendPeopleNumberField.textProperty().addListener(new ChangeListener<String>() {
	                @Override
	                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	                	String SendPeopleNumber=SendPeopleNumberField.getText();
	                	 if("1".equals(SendPeopleNumber)){
	                		AddresseeOneField.setText("1306086303@qq.com");
	                		AddresseeTwoField.setText("");
	                		AddresseeThreeField.setText("");
	                		AddresseeFourField.setText("");
	                	}else if("2".equals(SendPeopleNumber)){
	                		AddresseeOneField.setText("liuzhi1@sunline.cn");
	                		AddresseeTwoField.setText("jinliu@sunline.cn");
	                		AddresseeThreeField.setText("");
	                		AddresseeFourField.setText("");
	                	}else if("3".equals(SendPeopleNumber)){
	                	    AddresseeOneField.setText("liuzhi1@sunline.cn");
                            AddresseeTwoField.setText("jinliu@sunline.cn");
	                		AddresseeThreeField.setText("yinhongcheng@sunline.cn");
	                		AddresseeFourField.setText("");
	                	}else if("4".equals(SendPeopleNumber)){
	                	    AddresseeOneField.setText("liuzhi1@sunline.cn");
                            AddresseeTwoField.setText("jinliu@sunline.cn");
                            AddresseeThreeField.setText("yinhongcheng@sunline.cn");
	                		AddresseeFourField.setText("zhangcheng2@sunline.cn");
	                	}else{
//	                		SendPeopleNumberField.setText("");
	                		AddresseeOneField.setText("");
	                		AddresseeTwoField.setText("");
	                		AddresseeThreeField.setText("");
	                		AddresseeFourField.setText("");	
	                	}
	                }
	            });

	    	    AddresseeOneField.setLayoutX(114);
	    	    AddresseeOneField.setLayoutY(619);
	    	    AddresseeOneField.setPrefWidth(200);
	    	    AddresseeOneField.setPrefHeight(30);
	    	    AddresseeOneField.setId("InputBoxSL");
	    	    AddresseeOneField.setPromptText("请输入收件人一邮箱号...");
	    	    AddresseeOneField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    AddresseeOneField.setBackground(Background.EMPTY);
	    	    AddresseeOneField.setBorder(Border.EMPTY);
	    	    AddresseeOneField.setText("1306086303@qq.com");
                	
	    	    AddresseeTwoField.setLayoutX(415);
	    	    AddresseeTwoField.setLayoutY(619);
	    	    AddresseeTwoField.setPrefWidth(200);
	    	    AddresseeTwoField.setPrefHeight(30);
	    	    AddresseeTwoField.setId("InputBoxSL");
	    	    AddresseeTwoField.setPromptText("请输入收件人二邮箱号...");
	    	    AddresseeTwoField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    AddresseeTwoField.setBackground(Background.EMPTY);
	    	    AddresseeTwoField.setBorder(Border.EMPTY);
	    	    
	    	    AddresseeThreeField.setLayoutX(114);
	    	    AddresseeThreeField.setLayoutY(661);
	    	    AddresseeThreeField.setPrefWidth(200);
	    	    AddresseeThreeField.setPrefHeight(30);
	    	    AddresseeThreeField.setId("InputBoxSL");
	    	    AddresseeThreeField.setPromptText("请输入收件人三邮箱号...");
	    	    AddresseeThreeField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    AddresseeThreeField.setBackground(Background.EMPTY);
	    	    AddresseeThreeField.setBorder(Border.EMPTY);
	    	    
	    	    AddresseeFourField.setLayoutX(415);
	    	    AddresseeFourField.setLayoutY(661);
	    	    AddresseeFourField.setPrefWidth(200);
	    	    AddresseeFourField.setPrefHeight(30);
	    	    AddresseeFourField.setId("InputBoxSL");
	    	    AddresseeFourField.setPromptText("请输入收件人四邮箱号...");
	    	    AddresseeFourField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    AddresseeFourField.setBackground(Background.EMPTY);
	    	    AddresseeFourField.setBorder(Border.EMPTY);
	    	    
	    	    AppiumLogTextArea.setLayoutX(724);
	    	    AppiumLogTextArea.setLayoutY(56);
	    	    AppiumLogTextArea.setPrefSize(635, 280); 
	    	    AppiumLogTextArea.setPromptText("请运行Appium服务...");
	    	    AppiumLogTextArea.setPrefRowCount(22);  //设置行宽
	    	    AppiumLogTextArea.setPrefColumnCount(22);  //设置列宽  
	    	    AppiumLogTextArea.setWrapText(true);
	    	    AppiumLogTextArea.setId("AppiumLogTextArea");
	    	    AppiumLogTextArea.setEditable(false);
//				AppiumLogTextArea.appendText("\r\n"); 
//				AppiumLogTextArea.setText(AppiumLogTextArea.getText()+"222");
	    	    
	    	    ScriptRunLogTextArea.setLayoutX(724);
	    	    ScriptRunLogTextArea.setLayoutY(397+14);
	    	    ScriptRunLogTextArea.setPrefSize(635, 280); 
	    	    ScriptRunLogTextArea.setPromptText("请运行脚本程序...");
	    	    ScriptRunLogTextArea.setPrefRowCount(22);  //设置行宽
	    	    ScriptRunLogTextArea.setPrefColumnCount(22);  //设置列宽  
	    	    ScriptRunLogTextArea.setWrapText(true);
	    	    ScriptRunLogTextArea.setId("AppiumLogTextArea");
	    	    ScriptRunLogTextArea.setEditable(false);
	    	    
	    	    AppiumInspecrotButton.setLayoutX(1245);
	    	    AppiumInspecrotButton.setLayoutY(17);
	    	    AppiumInspecrotButton.setPrefWidth(30);
	    	    AppiumInspecrotButton.setPrefHeight(30);
	    	    AppiumInspecrotButton.setId("AppiumInspecrotButton");
	    	    AppiumInspecrotButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	if("".equals(MobileDevicesField.getText())){
//	                		Dialog.SetMessageDialog("Warning","请先获取设备！");
	                		DevicesCenterPane.setVisible(true);
	                	}else{
	                		AppiumRunButton.setVisible(false);
		                	AppiumStopButton.setVisible(true);
		        			//创建二个任务
		                	Runnable AppiumInspector = new Runnable() {
		            			@Override
		            			public void run() {
		            				Timer timer = new Timer();
				        			timer.schedule(new TimerTask() {
										@Override
				        				public void run() {
					        				String AppiumInspector = "cmd /c app-inspector -u "+MobileDevicesField.getText()+" --verbose";
				        					try {
				        						Command("AppiumStop","cmd /c taskkill /f /IM node.exe");
				        						AppiumLogTextArea.clear();
			            		                AppiumLogTextArea.appendText("[Appium-Inspector] 服务正在启动中...");
			            		                Command("AppiumStart",AppiumInspector);
				    						} catch (IOException | InterruptedException e) {
				    							e.printStackTrace();
				    					    }
				        				}
				        			}, 200);
		            			}
		            		};
//		                	Runnable StartGoogle = new Runnable() {
//		            			@Override
//		            			public void run() {
//		            				Timer timer = new Timer();
//				        			timer.schedule(new TimerTask() {
//										@Override
//				        				public void run() {
//					        				   String StartGoogle = "cmd /c start "+System.getProperty("user.home")+"\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe http://localhost:5678/";
//				        					try {
//			            		                Command("AppiumStop",StartGoogle);
//				    						} catch (IOException | InterruptedException e) {
//				    							e.printStackTrace();
//				    					    }
//				        				}
//				        			}, 5000);
//		            			}
//		            		};
		            		AppiumInspectorThread = new Thread(AppiumInspector);
//		            		Thread StartGoogleThread = new Thread(StartGoogle);
		            		//启动二个线程，将同时执行创建的二个任务
		            		AppiumInspectorThread.start();
//		            		StartGoogleThread.start();
	                	}	
	                }
	    	    });
	    	    
	    	    GetDevicesButton.setLayoutX(1284);
	    	    GetDevicesButton.setLayoutY(17);
	    	    GetDevicesButton.setPrefWidth(30);
	    	    GetDevicesButton.setPrefHeight(30);
	    	    GetDevicesButton.setId("APPGetDevicesButton");
	    	    GetDevicesButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
//	                	AppiumStopButton.setVisible(false);
//	                	AppiumRunButton.setVisible(true);
	                	MobileDevicesListView.toFront();
	                	DevicesCenterPane.setVisible(true);
//	                	Timer timer = new Timer();
//	        			timer.schedule(new TimerTask() {
//							@Override
//	        				public void run() {
//	        				   String AppiumStop = "cmd /c taskkill /f /IM node.exe";
//	        				   try {
//	        					    AppiumLogTextArea.clear();
//	    							Command("AppiumStop",AppiumStop);
//	    							AppiumInspectorThread.stop();
//	    							AppiumThread.stop();
//	    							ScriptsThread.stop();
//	    		                	DevicesCenterPane.setVisible(true);
//	        				    } catch (IOException | InterruptedException e) {
//	    							e.printStackTrace();
//	    						}
//	        				}
//	        			}, 200);
	                }
	    	    });
	    	    
	    	    DevicesCenterPane.setLayoutX(410);
	    	    DevicesCenterPane.setLayoutY(160);
	    	    DevicesCenterPane.setPrefWidth(360);
				DevicesCenterPane.setPrefHeight(330);
				DevicesCenterPane.setId("DevicesCenterPane");
				DevicesCenterPane.setBackground(Background.EMPTY);
				DevicesCenterPane.setVisible(false);
				
		        Button CloseButton = new Button();
	    		CloseButton.setId("CloseButton");
	    		CloseButton.setPrefWidth(30);
	    		CloseButton.setPrefHeight(27);
	    		CloseButton.setOnAction(new EventHandler<ActionEvent>() {
	    			public void handle(ActionEvent event) {
	    				DevicesCenterPane.setVisible(false);
	    			}
	            });

	    		HBox MinCloseHox = new HBox();
	    		MinCloseHox.setLayoutX(330);
	    		MinCloseHox.setLayoutY(0);
	    		MinCloseHox.setBackground(Background.EMPTY);
	    		MinCloseHox.setPickOnBounds(false);//面板不参与计算边界，透明区域无鼠标事件
	    		MinCloseHox.getChildren().addAll(CloseButton);

	    		MobileDevicesField.setLayoutX(110);
	    		MobileDevicesField.setLayoutY(55);
	    		MobileDevicesField.setPrefWidth(200);
	    		MobileDevicesField.setPrefHeight(30);
	    		MobileDevicesField.setId("OptionEntryBoxL");
	    		MobileDevicesField.setPromptText("请获取设备...");
//	    		MobileDevicesField.setText("CYSBBBE741129913");
	    		MobileDevicesField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		MobileDevicesField.setBackground(Background.EMPTY);
	    		MobileDevicesField.setBorder(Border.EMPTY);
	    		MobileDevicesField.setEditable(false);
	    		
	    		MobileDevicesListView.setItems(data);
        	    MobileDevicesListView.setLayoutX(108);
        	    MobileDevicesListView.setLayoutY(54+30);
        	    MobileDevicesListView.setPrefSize(230, 80);
        	    MobileDevicesListView.setEditable(true);
        	    MobileDevicesListView.setId("ListView");
        	    MobileDevicesListView.toFront();//激活当前图层到最上层
        	    MobileDevicesListView.setVisible(false);
        	    MobileDevicesListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
        	    	MobileDevicesField.setText(new_val);
        	    	try {
						String MobilePhoneManufacturer = "cmd /c adb -s "+new_val+" -d shell getprop ro.product.brand";
						CommandAdb("MobilePhoneManufacturer",MobilePhoneManufacturer);
        	    		
        	    		String MobilePhoneModel = "cmd /c adb -s "+new_val+" -d shell getprop ro.product.model";
						CommandAdb("MobilePhoneModel",MobilePhoneModel);
						
						String SystemVersion = "cmd /c adb -s "+new_val+" shell getprop ro.build.version.release";
						CommandAdb("SystemVersion",SystemVersion);
						
						String ResolvingPower = "cmd /c adb -s "+new_val+" shell \"dumpsys window | grep mUnrestrictedScreen\"";
						CommandAdb("ResolvingPower",ResolvingPower);
					} catch (Exception e) {
						e.printStackTrace();
					}
        	    	MobileDevicesListView.setVisible(false);
	  	        });
        	    
	    	    MobileDevicesButton.setLayoutX(310);
	    	    MobileDevicesButton.setLayoutY(55);
	    	    MobileDevicesButton.setPrefWidth(31);
	    	    MobileDevicesButton.setPrefHeight(30);
	    	    MobileDevicesButton.setId("ListViewButton");
	    	    MobileDevicesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	AppiumStopButton.setVisible(false);
	                	AppiumRunButton.setVisible(true);
	                	Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
							@Override
	        				public void run() {
	        				   Platform.runLater(new Runnable() {
	        					   @Override
	       		    			    public void run() {
	       		    			    	String MobileDevices = "cmd /c adb devices";
	       		    			    	try {
	       				        	    	data.clear();
	       									CommandAdb("MobileDevices",MobileDevices);
	       									MobileDevicesListView.setVisible(true);
	       				                	MobileDevicesListView.toFront();
	       								} catch (IOException e) {
	       									e.printStackTrace();
	       							    }
	       		    			    }
       		    			    });
	        				}
	        			}, 200);
	    	  	    }
	    	  	});

	    	    MobilePhoneManufacturerField.setLayoutX(110);
	    	    MobilePhoneManufacturerField.setLayoutY(95);
	    	    MobilePhoneManufacturerField.setPrefWidth(230);
	    	    MobilePhoneManufacturerField.setPrefHeight(30);
	    	    MobilePhoneManufacturerField.setId("InputBoxL");
	    	    MobilePhoneManufacturerField.setPromptText("请获取设备...");
//	    	    MobilePhoneManufacturerField.setText("HWCUN-L6735");
	    	    MobilePhoneManufacturerField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    MobilePhoneManufacturerField.setBackground(Background.EMPTY);
	    	    MobilePhoneManufacturerField.setBorder(Border.EMPTY);
	    	    MobilePhoneManufacturerField.setEditable(false);
	    	    
	    	    MobilePhoneModelField.setLayoutX(110);
	    	    MobilePhoneModelField.setLayoutY(135);
	    	    MobilePhoneModelField.setPrefWidth(230);
	    	    MobilePhoneModelField.setPrefHeight(30);
	    	    MobilePhoneModelField.setId("InputBoxL");
	    	    MobilePhoneModelField.setPromptText("请获取设备...");
//	    	    MobilePhoneModelField.setText("CUN-AL00");
	    	    MobilePhoneModelField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    MobilePhoneModelField.setBackground(Background.EMPTY);
	    	    MobilePhoneModelField.setBorder(Border.EMPTY);
	    	    MobilePhoneModelField.setEditable(false);
	    	    
	    	    SyetemVersionField.setLayoutX(110);
	    	    SyetemVersionField.setLayoutY(175);
	    	    SyetemVersionField.setPrefWidth(230);
	    	    SyetemVersionField.setPrefHeight(30);
	    	    SyetemVersionField.setId("InputBoxL");
	    	    SyetemVersionField.setPromptText("请获取设备...");
//	    	    SyetemVersionField.setText("Android 5.1");
	    	    SyetemVersionField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    SyetemVersionField.setBackground(Background.EMPTY);
	    	    SyetemVersionField.setBorder(Border.EMPTY);
	    	    SyetemVersionField.setEditable(false);
	    	    
	    	    ResolvingPowerField.setLayoutX(110);
	    	    ResolvingPowerField.setLayoutY(216);
	    	    ResolvingPowerField.setPrefWidth(230);
	    	    ResolvingPowerField.setPrefHeight(30);
	    	    ResolvingPowerField.setId("InputBoxL");
	    	    ResolvingPowerField.setPromptText("请获取设备...");
//	    	    ResolvingPowerField.setText("720x1280");
	    	    ResolvingPowerField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    ResolvingPowerField.setBackground(Background.EMPTY);
	    	    ResolvingPowerField.setBorder(Border.EMPTY);
	    	    ResolvingPowerField.setEditable(false);
	    	    
	    	    Rectangle DevicesRectangle = new Rectangle();
	    	    DevicesRectangle.setWidth(360);
	    	    DevicesRectangle.setHeight(330);
	    	    DevicesRectangle.setArcHeight(10);
	    	    DevicesRectangle.setArcWidth(10);
	    	    DevicesCenterPane.setClip(DevicesRectangle);
	    	    
	    	    DevicesCenterPane.getChildren().add(MinCloseHox);
	    	    DevicesCenterPane.getChildren().add(MobileDevicesField);
	    	    DevicesCenterPane.getChildren().add(MobileDevicesListView);	
	    	    DevicesCenterPane.getChildren().add(MobileDevicesButton);
	    	    DevicesCenterPane.getChildren().add(MobilePhoneManufacturerField);	
	    	    DevicesCenterPane.getChildren().add(MobilePhoneModelField);	
	    	    DevicesCenterPane.getChildren().add(SyetemVersionField);	
	    	    DevicesCenterPane.getChildren().add(ResolvingPowerField);	
	    	    
	    	    AppiumRunButton.setLayoutX(1323);
	    	    AppiumRunButton.setLayoutY(17);
	    	    AppiumRunButton.setPrefWidth(30);
	    	    AppiumRunButton.setPrefHeight(30);
	    	    AppiumRunButton.setId("AppiumRunButton");
	    	    AppiumRunButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	AppiumRunButton.setVisible(false);
	                	AppiumStopButton.setVisible(true);
	                	Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
	        				@Override
	        				public void run() {
	                	        String AppiumStart = "cmd /c node "+System.getProperty("user.home")+"\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js --address 127.0.0.1 --port 4723";
	        				    try {
	        				    	Command("AppiumStop","cmd /c taskkill /f /IM node.exe");
	        				    	AppiumLogTextArea.clear();
	        		                AppiumLogTextArea.appendText("[Appium] 服务正在启动中...");
	        		                Command("AppiumStart",AppiumStart);
	    						} catch (IOException | InterruptedException e) {
	    							e.printStackTrace();
	    						}
	        				}
	        			}, 200);
//	                	Runnable Appium = new Runnable() {
//	                		@Override
//	        				public void run() {
//	        					String AppiumStart = "cmd /c node C:\\Users\\King-liu\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js --address 127.0.0.1 --port 4723";
//	        					try {
//	        						Command("AppiumStop","cmd /c taskkill /f /IM node.exe");
//	        		                AppiumLogTextArea.appendText("[Appium] 服务正在启动中...");
//	        		                Command("AppiumStart",AppiumStart);
//	    						} catch (IOException | InterruptedException e) {
//	    							e.printStackTrace();
//	    					    }
//	        				}		
//	            		};
//	            		Thread AppiumThread = new Thread(Appium);
//	            		AppiumThread.start();
	                }
	    	    });
	    	    
	    	    AppiumStopButton.setLayoutX(1323);
	    	    AppiumStopButton.setLayoutY(17);
	    	    AppiumStopButton.setPrefWidth(30);
	    	    AppiumStopButton.setPrefHeight(30);
	    	    AppiumStopButton.setId("AppiumStopButton");
	    	    AppiumStopButton.setVisible(false);
	    	    AppiumStopButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	AppiumStopButton.setVisible(false);
	                	AppiumRunButton.setVisible(true);
	                	Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
							@Override
	        				public void run() {
	        				   String AppiumStop = "cmd /c taskkill /f /IM node.exe";
	        				   String ScriptsStop = "cmd /c start wmic process where name='cmd.exe' call terminate";
	        				   try {
	        					    Command("AppiumStop",AppiumStop);
	        					    AppiumLogTextArea.clear();
	        					    Command("ScriptsStop",ScriptsStop);
	    							AppiumInspectorThread.stop();
	    							AppiumThread.stop();
	    							ScriptsThread.stop();
	    							ScriptRunButton.setDisable(false);
	        				    } catch (IOException | InterruptedException e) {
	    							e.printStackTrace();
	    						}
	        				}
	        			}, 200);
	                }
	    	    });
	    	    
	    	    ScriptUploadButton.setLayoutX(300);
	    	    ScriptUploadButton.setLayoutY(751+16);
	    	    ScriptUploadButton.setPrefWidth(137);
	    	    ScriptUploadButton.setPrefHeight(42);
	    	    ScriptUploadButton.setId("ScriptUploadButton");
	    	    ScriptUploadButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	ScriptUploadButton.setDisable(true);
	                	if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if (StringUtil.isEmpty(SoftwareEngineering)) {
                            Dialog.SetMessageDialog("Warning","请先下载工程！");
                            ScriptUploadButton.setDisable(false);
                        }else{
	                		Timer timer = new Timer();
		        			timer.schedule(new TimerTask() {
		        				@Override
		        				public void run() {
		        					String gitlocalPath =""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"";
//		    	                	String TestObject = TestObjectField.getText().replace("TestCases\\", "");
//		    	                	String TestScripts = TestScriptsField.getText().replace("TestCaseXml\\", "");
//		    	                	String TestScriptsAggregate = TestScriptsAggregateField.getText().replace("TestReportXml\\", "");
		    	                	String CommitMessage = CommitMessageTextArea.getText();
		    	                	String Account = HomePageView.AccountField.getText();
		    					    String PassWord = HomePageView.PasswrodField.getText();
		    	                	try {
		    	                		if (StringUtil.isEmpty(CommitMessage)) {
		    	                			Platform.runLater(new Runnable() {
	    	    	    	    			    @Override
	    	    	    	    			    public void run() {
	    	    	    	    			    	Dialog.SetMessageDialog("Warning","请输入提交备注！");
	    	    	    	    			    }
	    	    	    	    			});
		    			                }else{
		    			                	String message= JGitUtil.GitPush(gitlocalPath,CommitMessage,Account,PassWord);
		    			                	if(message=="无已修改脚本！"){
		    			                		Platform.runLater(new Runnable() {
		    	    	    	    			    @Override
		    	    	    	    			    public void run() {
		    	    	    	    			    	Dialog.SetMessageDialog("Warning",message);
		    	    	    	    			    }
		    	    	    	    			});
		    			                	}else if(message=="提交失败！"){
		    			                		Platform.runLater(new Runnable() {
		    	    	    	    			    @Override
		    	    	    	    			    public void run() {
//		    	    	    	    			    	Dialog.SetMessageDialog("Error",message);
		    	    	    	    			        HomePageView.AccountLoginCenterPane.setVisible(true);
		    	    	    	    			        Dialog.SetMessageDialog("Error","请检查GIT环境，确保账号密码正确！");
		    	    	    	    			    }
		    	    	    	    			});
		    			                	}else if(message=="提交成功！"){
		    			                		Platform.runLater(new Runnable() {
		    	    	    	    			    @Override
		    	    	    	    			    public void run() {
		    	    	    	    			    	Dialog.SetMessageDialog("Success",message);
		    	    	    	    			    }
		    	    	    	    			});
		    			                	}
		    			                }
		    	                		ScriptUploadButton.setDisable(false);
		    						} catch (Exception e) {
		    							Platform.runLater(new Runnable() {
		    	    	    			    @Override
		    	    	    			    public void run() {
		    	    	    			    	Dialog.SetMessageDialog("Warning","网络连接失败，请稍后重试！");
		    									e.printStackTrace();
		    	    	    			    }
		    	    	    			});
		    					    }
		        				}
		        			}, 200);
	                	}
	                }
	    	    });
	    	    
	    	    ScriptDownloadButton.setLayoutX(461);
	    	    ScriptDownloadButton.setLayoutY(751+16);
	    	    ScriptDownloadButton.setPrefWidth(137);
	    	    ScriptDownloadButton.setPrefHeight(42);
	    	    ScriptDownloadButton.setId("ScriptDownloadButton");
	    	    ScriptDownloadButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
    					ScriptDownloadButton.setDisable(true);
    					if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if(StringUtil.isEmpty(SoftwareEngineering)){
                			Dialog.SetMessageDialog("Warning","请先下载工程！");
                			ScriptDownloadButton.setDisable(false);
	                	}else{
	                		Timer timer = new Timer();
		        			timer.schedule(new TimerTask() {
		        				@Override
		        				public void run() {
		        					String gitlocalPath =""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"";
		        					String Account = HomePageView.AccountField.getText();
		    					    String PassWord = HomePageView.PasswrodField.getText();
		    	                	try {
		    	                		String message= JGitUtil.GitPull(gitlocalPath,Account,PassWord);
		    		                	if(message=="拉取失败！"){
		    		                		Platform.runLater(new Runnable() {
			    	    	    			    @Override
			    	    	    			    public void run() {
//			    	    	    			    	Dialog.SetMessageDialog("Error",message);
			    	    	    			    	HomePageView.AccountLoginCenterPane.setVisible(true);
			    	    	    			    	Dialog.SetMessageDialog("Error","请检查GIT环境，确保账号密码正确！");
			    	    	    			    }
			    	    	    			});
		    		                	}else if(message=="拉取成功！"){
		    		                		Platform.runLater(new Runnable() {
			    	    	    			    @Override
			    	    	    			    public void run() {
			    	    	    			    	Dialog.SetMessageDialog("Success",message);
			    	    	    			    }
			    	    	    			});
		    		                	}	
		    		                	ScriptDownloadButton.setDisable(false);
		    						} catch (Exception e) {
		    							Platform.runLater(new Runnable() {
		    	    	    			    @Override
		    	    	    			    public void run() {
		    	    	    			    	Dialog.SetMessageDialog("Warning","网络连接失败，请稍后重试！");
		    									e.printStackTrace();
		    	    	    			    }
		    	    	    			});
		    					    }
		        				}
		        			}, 200);
	                	}
	                }
	    	    });
	    	    
	    	    ScriptRunButton.setLayoutX(622);
	    	    ScriptRunButton.setLayoutY(751+16);
	    	    ScriptRunButton.setPrefWidth(137);
	    	    ScriptRunButton.setPrefHeight(42);
	    	    ScriptRunButton.setId("ScriptRunButton");
	    	    ScriptRunButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	String SoftwareDownloads = SoftwareDownloadsField.getText();
	                	String TestObject = TestObjectField.getText();
	                	String TestScripts = TestScriptsField.getText();
	                	String TestScriptsAggregate = TestScriptsAggregateField.getText();
	                	String MobileDevices = MobileDevicesField.getText();
	                	if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if (StringUtil.isEmpty(SoftwareEngineering)) {
		    	        	Dialog.SetMessageDialog("Warning","请先下载工程！");
                		}else if (StringUtil.isEmpty(SoftwareDownloads)) {
		    	        	Dialog.SetMessageDialog("Warning","请先下载软件！");
		                }else if (StringUtil.isEmpty(TestObject)) {
		    	        	Dialog.SetMessageDialog("Warning","请选择测试对象！");
		                }else if (StringUtil.isEmpty(TestScripts)) {
		    	        	Dialog.SetMessageDialog("Warning","请选择测试脚本！");
		                }else if (StringUtil.isEmpty(TestScriptsAggregate)) {
		    	        	Dialog.SetMessageDialog("Warning","请选择测试脚本集合！");
		                }else if(StringUtil.isEmpty(MobileDevices)){
//	                		Dialog.SetMessageDialog("Warning","请先获取设备！");
		                	DevicesCenterPane.setVisible(true);
	                	}else{
		                	ScriptRunButton.setDisable(true);
		                	AppiumRunButton.setVisible(false);
		                	AppiumStopButton.setVisible(true);
		                	//创建二个任务
		                	Runnable Appium = new Runnable() {
		            			@Override
		            			public void run() {
		            				Timer timer = new Timer();
				        			timer.schedule(new TimerTask() {
										@Override
				        				public void run() {
				        					String AppiumStart = "cmd /c node "+System.getProperty("user.home")+"\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js --address 127.0.0.1 --port 4723";
				        					try {
				        						Command("AppiumStop","cmd /c taskkill /f /IM node.exe");
				        						AppiumLogTextArea.clear();
			            		                AppiumLogTextArea.appendText("[Appium] 服务正在启动中...");
			            		                Command("AppiumStart",AppiumStart);
				    						} catch (IOException | InterruptedException e) {
				    							System.out.println("报错一");
				    							e.printStackTrace();
				    							AppiumThread.stop();
				    					    }
				        				}
				        			}, 200);
		            			}
		            		};
		            		Runnable Scripts = new Runnable() {
		            			@Override
		            			public void run() {
		            				Timer timer = new Timer();
				                	timer.schedule(new TimerTask() {
				        				@Override
				        				public void run() {
				    	                	String mvn = "cmd /c c: && cd "+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+" && mvn clean package";
				    	                    String ant = "cmd /c c: && cd "+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+" && ant";
				        					try {
				        						Command("ScriptsStop","cmd /c start wmic process where name='cmd.exe' call terminate");
				        						//执行Mvn命令
				        						ScriptRunLogTextArea.appendText("[Appium项目] 脚本打包编译中...");
			    			                	int mvncode=Command("ScriptsStart",mvn);
			    			                	if(mvncode==0){
			    			                		ScriptRunLogTextArea.appendText("\n");
			    									ScriptRunLogTextArea.appendText("[Appium项目] 脚本打包编译成功...");
			    									ScriptRunLogTextArea.appendText("\n");
			    									ScriptRunLogTextArea.appendText("-------------------------------------------------------------------------------------------------------------------");
			    									//执行Ant命令
				    								ScriptRunLogTextArea.appendText("\n");
				    			                	ScriptRunLogTextArea.appendText("[Appium项目] Ant脚本运行中...");
				    			                	int antcode=Command("ScriptsStart",ant);
				    			                	if(antcode==0){
				    			                		Thread.sleep(5000);
				    			                		ScriptRunLogTextArea.appendText("\n");
				    									ScriptRunLogTextArea.appendText("[Appium项目] Ant脚本运行成功...");
				    									ScriptRunLogTextArea.appendText("\n");
				    									ScriptRunLogTextArea.appendText("-------------------------------------------------------------------------------------------------------------------");
				    			                	}else{
				    			                		ScriptRunLogTextArea.appendText("\n");
				    									ScriptRunLogTextArea.appendText("[Appium项目] Ant脚本运行失败...");
				    									ScriptRunLogTextArea.appendText("\n");
				    									ScriptRunLogTextArea.appendText("-------------------------------------------------------------------------------------------------------------------");
				    			                	}
			    			                	}else{
			    			                		ScriptRunLogTextArea.appendText("\n");
			    									ScriptRunLogTextArea.appendText("[Appium项目] 脚本打包编译失败...");
			    									ScriptRunLogTextArea.appendText("\n");
			    									ScriptRunLogTextArea.appendText("-------------------------------------------------------------------------------------------------------------------");
			    			                	}
			    								Timer timer = new Timer();
			    			        			timer.schedule(new TimerTask() {
			    			        				@Override
			    			        				public void run() {
			    			        					try {
															Command("AppiumStop","cmd /c taskkill /f /IM node.exe");
															AppiumLogTextArea.appendText("\n");
			    			        						AppiumLogTextArea.appendText("[Appium] 服务关闭成功...");
			    			    							AppiumLogTextArea.appendText("\n");
			    			    							AppiumLogTextArea.appendText("-------------------------------------------------------------------------------------------------------------------");
			    			    							AppiumStopButton.setVisible(false);
			    			    		                	AppiumRunButton.setVisible(true);    
														} catch (IOException | InterruptedException e) {
															e.printStackTrace();
														}
			    			        				}
			    			        			}, 200);
			    								AppiumThread.stop();
				    							ScriptsThread.stop();
				    							ScriptRunButton.setDisable(false);
				    						} catch (IOException | InterruptedException e) {
				    							System.out.println("报错二");
				    							e.printStackTrace();
				    							AppiumThread.stop();
				    							ScriptsThread.stop();
				    					    }
				        				}
				        			}, 8000);
		            			}
		            		};
		            		//创建二个线程
		            		AppiumThread = new Thread(Appium);
		            		ScriptsThread = new Thread(Scripts);
		            		//启动二个线程，将同时执行创建的二个任务
		            		AppiumThread.start();
		            		ScriptsThread.start();
		                }
	                }
	    	    });
	    	    
	    	    GenerationReportButton.setLayoutX(783);
	    	    GenerationReportButton.setLayoutY(751+16);
	    	    GenerationReportButton.setPrefWidth(137);
	    	    GenerationReportButton.setPrefHeight(42);
	    	    GenerationReportButton.setId("GenerationReportButton");
	    	    GenerationReportButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                    String date = DateUtil.getDateFormat("yyyy-MM-dd");
	                	try {
	                	    if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
	                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
	                        }else if(StringUtil.isEmpty(SoftwareEngineering)) {
	                			Dialog.SetMessageDialog("Warning","请先下载工程！");
			                }else if(SoftwareEngineering!=null){
			                	if(FirstOpen==0){
			                		Dialog.SetMessageDialog("Warning","请添加151.139.237.11 cdn.rawgit.com,到本地hosts文件末尾，或者翻墙即可正常打开测试报告，否则会显示乱码！");
			                		FirstOpen =1;
		                		}else{
		                		    OpenBrowserUtil.OpenBrowser("file:///"+System.getProperty("user.home")+"//AppData//Local//AutomationTestSystem//app//"+SoftwareEngineering+"//TestOutput//ExtentReport//"+date+"//index.html");
		                		}
			                }
						 }catch (Exception e) {
							e.printStackTrace();
						}
	                }
	    	    });
	    	    
	    	    SendMailButton.setLayoutX(944);
	    	    SendMailButton.setLayoutY(751+16);
	    	    SendMailButton.setPrefWidth(137);
	    	    SendMailButton.setPrefHeight(42);
	    	    SendMailButton.setId("SendMailButton");
	    	    SendMailButton.setOnAction(new EventHandler<ActionEvent>() {
	    	        String MailboxAccount ="";
                    String MailboxAuthorizationPassword ="";
	                public void handle(ActionEvent event) {
	                	Smtp =MailModeField.getText();
	                	if("QQ".equals(Smtp)){
    						Smtp="smtp.qq.com";
    						MailboxAccount="1306086303@qq.com";
    						MailboxAuthorizationPassword="ticmipgebuznhdcd";
    					}else if("网易".equals(Smtp)){
    						Smtp="smtp.163.com";
    						MailboxAccount="hagyao520@163.com";
                            MailboxAuthorizationPassword="YPFCHNRRCIQWFUBK";
    					}else if("新浪".equals(Smtp)){
    						Smtp="smtp.sina.com";
    						MailboxAccount="hagyao520@sina.com";
                            MailboxAuthorizationPassword="61a185f24d2f6d1f";
    					}
    					String SendPeopleNumber =SendPeopleNumberField.getText();
    					String AddresseeOne =AddresseeOneField.getText();
    					String AddresseeTwo =AddresseeTwoField.getText();
    					String AddresseeThree =AddresseeThreeField.getText();
    					String AddresseeFour =AddresseeFourField.getText();
    					String EntryName = SoftwareEngineeringField.getText();
//    					String EntryName = "Appium";
    					if(StringUtil.isEmpty(SoftwareEngineeringField.getText())){
                            Dialog.SetMessageDialog("Warning","请输入工程地址！");
                        }else if(StringUtil.isEmpty(SoftwareEngineering)) {
		    	        	Dialog.SetMessageDialog("Warning","请先下载工程！");
		                }else if (StringUtil.isEmpty(Smtp)) {
		    	        	Dialog.SetMessageDialog("Warning","请先选择邮件发送方式！");
		                }else if (StringUtil.isEmpty(SendPeopleNumber)) {
		    	        	Dialog.SetMessageDialog("Warning","请输入发送人数！");
		                }else if ("0".equals(SendPeopleNumber)) {
		                	Dialog.SetMessageDialog("Warning","发送人数不能小于1，请重新输入！");
		                }else if ("1".equals(SendPeopleNumber)) {
		                	if (StringUtil.isEmpty(AddresseeOne)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人一邮箱号！");
		                	}else{
			                	SendMailButton.setDisable(true);
			                	Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
			        				@Override
			        				public void run() {
			    	                	try {
			    	                        int message = SendEmail.sendEmail(""+Smtp+"", MailboxAccount, MailboxAuthorizationPassword,SendPeopleNumber,AddresseeOne,null,null,null,EntryName);
			    						    if(message==0){
			    						    	Platform.runLater(new Runnable() {
			    		    	    			    @Override
			    		    	    			    public void run() {
			    		    	    			    	Dialog.SetMessageDialog("Success","邮件发送成功，请查收！");
			    		    	    			    }
			    						    	});
			    						    }
			    						    SendMailButton.setDisable(false);
			    	                	} catch (Exception e) {
			    	                		Platform.runLater(new Runnable() {
		    		    	    			    @Override
		    		    	    			    public void run() {
		    		    	    			    	Dialog.SetMessageDialog("Warning","邮件格式错误或网络连接失败，请检查后重试！");
		    		    	    			    	SendMailButton.setDisable(false);
		    		    	    			    	e.printStackTrace();
		    		    	    			    }
		    		    	    			});
			    						}
			        				}
			        			}, 200);
		                	}
		                }else if ("2".equals(SendPeopleNumber)) {
		                	if (StringUtil.isEmpty(AddresseeOne)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人一邮箱号！");
		                	}else if (StringUtil.isEmpty(AddresseeTwo)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人二邮箱号！");
		                	}else{
			                	SendMailButton.setDisable(true);
			                	Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
			        				@Override
			        				public void run() {
			    	                	try {
			    	                        int message = SendEmail.sendEmail(""+Smtp+"", MailboxAccount, MailboxAuthorizationPassword,SendPeopleNumber,AddresseeOne,AddresseeTwo,null,null,EntryName);
			    						    if(message==0){
			    						    	Platform.runLater(new Runnable() {
			    		    	    			    @Override
			    		    	    			    public void run() {
			    		    	    			    	Dialog.SetMessageDialog("Success","邮件发送成功，请查收！");
			    		    	    			    }
			    		    	    			});
			    						    }
			    						    SendMailButton.setDisable(false);
			    	                	} catch (Exception e) {
			    	                		Platform.runLater(new Runnable() {
		    		    	    			    @Override
		    		    	    			    public void run() {
		    		    	    			    	Dialog.SetMessageDialog("Warning","工程地址或邮件格式错误或网络连接失败，请检查后重试！");
		    		    	    			    	SendMailButton.setDisable(false);
		    		    	    			    	e.printStackTrace();
		    		    	    			    }
		    		    	    			});
			    						}
			        				}
			        			}, 200);
		                	}
		                }else if ("3".equals(SendPeopleNumber)) {
		                	if (StringUtil.isEmpty(AddresseeOne)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人一邮箱号！");
		                	}else if (StringUtil.isEmpty(AddresseeTwo)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人二邮箱号！");
		                	}else if (StringUtil.isEmpty(AddresseeThree)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人三邮箱号！");
		                	}else{
			                	SendMailButton.setDisable(true);
			                	Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
			        				@Override
			        				public void run() {
			    	                	try {
			    	                        int message = SendEmail.sendEmail(""+Smtp+"", MailboxAccount, MailboxAuthorizationPassword,SendPeopleNumber,AddresseeOne,AddresseeTwo,AddresseeThree,null,EntryName);
			    						    if(message==0){
			    						    	Platform.runLater(new Runnable() {
			    		    	    			    @Override
			    		    	    			    public void run() {
			    		    	    			    	Dialog.SetMessageDialog("Success","邮件发送成功，请查收！");
			    		    	    			    }
			    						    	});
			    						    }
			    						    SendMailButton.setDisable(false);
			    	                	} catch (Exception e) {
			    	                		Platform.runLater(new Runnable() {
		    		    	    			    @Override
		    		    	    			    public void run() {
		    		    	    			    	Dialog.SetMessageDialog("Warning","工程地址或邮件格式错误或网络连接失败，请检查后重试！");
		    		    	    			    	SendMailButton.setDisable(false);
		    		    	    			    	e.printStackTrace();
		    		    	    			    }
		    		    	    			});
			    						}
			        				}
			        			}, 200);
		                	}
		                }else if ("4".equals(SendPeopleNumber)) {
		                	if (StringUtil.isEmpty(AddresseeOne)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人一邮箱号！");
		                	}else if (StringUtil.isEmpty(AddresseeTwo)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人二邮箱号！");
		                	}else if (StringUtil.isEmpty(AddresseeThree)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人三邮箱号！");
		                	}else if (StringUtil.isEmpty(AddresseeFour)) {
		                		Dialog.SetMessageDialog("Warning","请输入收件人四邮箱号！");
		                	}else{
			                	SendMailButton.setDisable(true);
			                	Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
			        				@Override
			        				public void run() {
			    	                	try {
			    	                        int message = SendEmail.sendEmail(""+Smtp+"", MailboxAccount, MailboxAuthorizationPassword,SendPeopleNumber,AddresseeOne,AddresseeTwo,AddresseeThree,AddresseeFour,EntryName);
			    						    if(message==0){
			    						    	Platform.runLater(new Runnable() {
			    		    	    			    @Override
			    		    	    			    public void run() {
			    		    	    			    	Dialog.SetMessageDialog("Success","邮件发送成功，请查收！");
			    		    	    			    }
			    						    	});
			    						    }
			    						    SendMailButton.setDisable(false);
			    	                	} catch (Exception e) {
			    	                		Platform.runLater(new Runnable() {
		    		    	    			    @Override
		    		    	    			    public void run() {
		    		    	    			    	Dialog.SetMessageDialog("Warning","工程地址或邮件格式错误或网络连接失败，请检查后重试！");
		    		    	    			    	SendMailButton.setDisable(false);
		    		    	    			    	e.printStackTrace();
		    		    	    			    }
		    		    	    			});
			    						}
			        				}
			        			}, 200);
		                	}
		                }else{
		                	Dialog.SetMessageDialog("Warning","发送人数最大四个，请重新输入！");
		                }
	                }
	    	    });

	    	    APPAutomationCenterPane.getChildren().add(DownloadProgressBar);
	    	    APPAutomationCenterPane.getChildren().add(DownloadProgressLabel);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(SoftwareEngineeringField);
	    	    APPAutomationCenterPane.getChildren().add(SoftwareEngineeringButton);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(WeChatOptionRadioButton);
	    	    APPAutomationCenterPane.getChildren().add(QQOptionRadioButton);
	    	    APPAutomationCenterPane.getChildren().add(OtherOptionRadioButton);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(SoftwareVBox);
	    	    
        	    APPAutomationCenterPane.getChildren().add(checkOutDatePicker);
        	    
        	    APPAutomationCenterPane.getChildren().add(SoftwareDownloadsField);
	    	    APPAutomationCenterPane.getChildren().add(SoftwareDownloadsButton);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(TestObjectField);
	    	    APPAutomationCenterPane.getChildren().add(TestObjectButton);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(TestScriptsField);
	    	    APPAutomationCenterPane.getChildren().add(TestScriptsButton);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(TestScriptsAggregateField);
	    	    APPAutomationCenterPane.getChildren().add(TestScriptsAggregateButton);
	    	    		
	    	    APPAutomationCenterPane.getChildren().add(CommitMessageTextArea);
	    	    APPAutomationCenterPane.getChildren().add(MailModeField);
	    	    APPAutomationCenterPane.getChildren().add(MailModeButton);
	    	    APPAutomationCenterPane.getChildren().add( SendPeopleNumberField);
	    	    APPAutomationCenterPane.getChildren().add( AddresseeOneField);
	    	    APPAutomationCenterPane.getChildren().add(AddresseeTwoField);
	    	    APPAutomationCenterPane.getChildren().add(AddresseeThreeField);
	    	    APPAutomationCenterPane.getChildren().add(AddresseeFourField);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(AppiumLogTextArea);
	    	    APPAutomationCenterPane.getChildren().add(AppiumInspecrotButton);
	    	    APPAutomationCenterPane.getChildren().add(GetDevicesButton);
	    	    APPAutomationCenterPane.getChildren().add(AppiumRunButton);
	    	    APPAutomationCenterPane.getChildren().add(AppiumStopButton);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(ScriptRunLogTextArea);
	    	    
	    	    APPAutomationCenterPane.getChildren().add(HomePageView.AccountLoginCenterPane); 
	    	    APPAutomationCenterPane.getChildren().add(ScriptUploadButton);
	    	    APPAutomationCenterPane.getChildren().add(ScriptDownloadButton);
	    	    APPAutomationCenterPane.getChildren().add(ScriptRunButton);
	    	    APPAutomationCenterPane.getChildren().add(GenerationReportButton);
	    	    APPAutomationCenterPane.getChildren().add(SendMailButton);
	    	    
	    	    APPAutomationCenterPane.getChildren().addAll(DevicesCenterPane);
	    	    
        	    HomePagePane.getChildren().add(APPAutomationCenterPane);
//        	    HomePageView.HomePagePane.getChildren().add(APPAutomationCenterPane);
        }

    	public static void getAPPAutomationCenterPane(boolean show) {
         	APPAutomationCenterPane.setVisible(show);
     	}
    	
    	public static Task CreateWorker(String Name) {
            return new Task() {
                @Override
                protected Object call() throws Exception {
                	for(int i = 0; i < 100; i++){
                		if(Name=="SoftwareEngineering"){
                			Thread.sleep(275);
                		}else if(Name=="SoftwareDownloads"){
//                			Thread.sleep(95);
                			Thread.sleep(500);
                		}
                        updateMessage((i + 1) + "%");
                        updateProgress(i + 1, 100);
                    }
                	updateMessage(" ok");
                    return true;
                } 
            };
        }
    	
		private static void OpenFile(String FilePath) {
            EventQueue.invokeLater(() -> {
                try {
//                    desktop.open(file);
                    String cmd = "cmd /c atom " +FilePath ;
                    Runtime.getRuntime().exec(cmd); 
                } catch (IOException e) {
                	e.printStackTrace();
                }
            });
        }
    	
		private static void OpenDir(String file) {
            EventQueue.invokeLater(() -> {
                try {
                  java.awt.Desktop.getDesktop().open(new File(file));
                } catch (IOException e) {
                	e.printStackTrace();
                }
            });
        }

		private static  int Command(String name,String cmd) throws IOException, InterruptedException{
			int i=0;
		    try {  
		    	System.out.println("开始执行Cmd命令："+cmd);
	        	Process pr = Runtime.getRuntime().exec(cmd);
	            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "utf-8")); 
	            if(name=="AppiumStart"){
        			String line = input.readLine();
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
	            			if(line!=null){
	            				String newline = line;
	            				Platform.runLater(new Runnable() {
		    	    			    @Override
		    	    			    public void run() {
		    	                		AppiumLogTextArea.appendText("\n");
		    		                	AppiumLogTextArea.appendText(newline.replaceAll("\\[35m", "").replaceAll("\\[39m", ""));
		    		                	AppiumLogTextArea.selectEnd();
		    		                	AppiumLogTextArea.deselect();
		    		                	AppiumLogTextArea.setScrollTop(Double.MAX_VALUE);
		    	    			    }
		    	    			});
	            			}else{
	            				AppiumThread.stop();
	            				break;
	            			}
	            			line = input.readLine();
	            		}
		            }else{
		            	i=1;
	 	            }
	            }else if(name=="AppiumStop"){
	            	String line = input.readLine();
	            	System.out.println(line);
	            }else if(name=="ScriptsStart"){
	            	String line = input.readLine();
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
			                if(line!=null){
			                	String newlog = line;
			                	Platform.runLater(new Runnable() {
		    	    			    @Override
		    	    			    public void run() {
		    	    			    	ScriptRunLogTextArea.appendText("\n");
	        	    			    	ScriptRunLogTextArea.appendText(newlog);
	        	    			    	ScriptRunLogTextArea.selectEnd();
	        	    			    	ScriptRunLogTextArea.deselect();
	        	    			    	ScriptRunLogTextArea.setScrollTop(Double.MAX_VALUE);
		    	    			    }
		    	    			}); 
		                	}else{
		                		AppiumThread.stop();
								ScriptsThread.stop();
								 break;
		                	}
	            			line=input.readLine();
	            		}
	            	}else{
	            		i=1;
	            	}
	            }else if(name=="ScriptsStop"){
	            	String line = input.readLine();
	            	System.out.println(line);
	            	ScriptRunLogTextArea.clear();
	            }
	         } catch (IOException e) {  
	              e.printStackTrace();  
	         }
			return i;
        }
		
		private static  void CommandAdb(String name,String cmd) throws IOException{
		    try {  
	        	Process pr = Runtime.getRuntime().exec(cmd);
	            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "utf-8"));
	            String line = null;  
	            while ((line = input.readLine()) != null) {  
	                System.out.println(line);
	                if(name=="MobileDevices"){
	                	if("List of devices attached".equals(line)){
	 	                }else{
	 	                	data.addAll(line.replace("device", ""));
	 	                }
	                }else if(name=="MobilePhoneManufacturer"){
	                	if("".equals(line)){	
 	                	}else{
 	                		MobilePhoneManufacturerField.setText(line);
 	                	}
	                }else if(name=="MobilePhoneModel"){
	                	if("".equals(line)){	
 	                	}else{
 	 	                	MobilePhoneModelField.setText(line);
 	                	}
	                }else if(name=="SystemVersion"){
	                	if("".equals(line)){	
 	                	}else{
 	 	                	SyetemVersionField.setText(line);
 	                	}
	                }else if(name=="ResolvingPower"){
	                	if("".equals(line)){	
 	                	}else{
 	 	                	ResolvingPowerField.setText(line.replace("    mUnrestrictedScreen=(0,0) ", ""));
 	                	}
	                }
	            } 
	         } catch (IOException e) {  
	              e.printStackTrace();  
	         }
        }
		
		public void appendText(String p){
//		    txtarea.appendText(p);
//		    txtarea.appendText("text\n");
		}
		
        public static void main(String[] args) {
//        	Locale.setDefault(Locale.US);
//	        launch(args);
	        String AppiumStart = "cmd /c node "+System.getProperty("user.home")+"\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js --address 127.0.0.1 --port 4723";
            try {
//                Command("AppiumStop","cmd /c taskkill /f /IM node.exe");
//                Command("AppiumStart",AppiumStart);
                System.out.println(AppiumStart); 
                Process pr =  Runtime.getRuntime().exec(AppiumStart);
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "utf-8"));
                String line = input.readLine();
                if(line!=null){
                    while(line!=null){
                        System.out.println(line);
                        line = input.readLine();
                    }
                }
                
            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
}