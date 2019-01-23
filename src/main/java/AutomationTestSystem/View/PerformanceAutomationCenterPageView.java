package AutomationTestSystem.View;

import javafx.stage.FileChooser;
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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
import javafx.event.EventHandler;
import javafx.geometry.Pos;

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

import AutomationTestSystem.Chart.AdbBridge;
import AutomationTestSystem.Chart.LineChartsPaneFXUI;
import AutomationTestSystem.Chart.TimeUtil;
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
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.DragUtil;
import AutomationTestSystem.Util.JGitUtil;
import AutomationTestSystem.Util.OpenBrowserUtil;
import AutomationTestSystem.Util.SendEmail;
import AutomationTestSystem.Util.StringUtil;

@SuppressWarnings({ "rawtypes", "restriction", "static-access","unused"})
public class PerformanceAutomationCenterPageView extends Application {

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

        static AnchorPane PerformanceAutomationCenterPane = new AnchorPane();
        private static TextField SoftwareEngineeringField = new TextField();
        private static Button SoftwareEngineeringButton = new Button();
        static String SoftwareEngineering=null;
        public static String SoftwareEngineeringPath=null;
        static Task Worker;
        
        private static TextField InstallationDependencyField = new TextField();
        private static Button InstallationDependencyButton = new Button();
        
        private static RadioButton OneOptionRadioButton = new RadioButton();
        static String OneSoftwareIcon=null;
        private static ImageView OneSoftwareIconImageView = new ImageView();
        private static Label OneSoftwareNameLabel = new Label();
        
        private static RadioButton TwoOptionRadioButton = new RadioButton();
        static String TwoSoftwareIcon=null;
        private static ImageView TwoSoftwareIconImageView = new ImageView();
        private static Label TwoSoftwareNameLabel = new Label();
        
        private static RadioButton ThreeOptionRadioButton = new RadioButton();
        static String ThreeSoftwareIcon=null;
        private static ImageView ThreeSoftwareIconImageView = new ImageView();
        private static Label ThreeSoftwareNameLabel = new Label();
        
        static int id=1;
        
        public static TextField AppPackageNameField = new TextField();
        static String apkPackageName=null;
        
        public static TextField StartTimeField = new TextField();
        public static TextField EndTimeField = new TextField();
        
        public static TextField SendDataField = new TextField();
        public static TextField ReceiveField = new TextField();
        
        public static TextField ApplicationMemoryField_Average = new TextField();
        public static TextField SystemMemoryField_Average = new TextField();
        public static TextField ApplicationCPUField_Average = new TextField();
        public static TextField SystemCPUField_Average = new TextField();
        
        public static TextField ApplicationMemoryField_Minimum = new TextField();
        public static TextField SystemMemoryField_Minimum = new TextField();
        public static TextField ApplicationCPUField_Minimum = new TextField();
        public static TextField SystemCPUField_Minimum = new TextField();
        
        public static TextField ApplicationMemoryField_Maximum = new TextField();
        public static TextField SystemMemoryField_Maximum = new TextField();
        public static TextField ApplicationCPUField_Maximum = new TextField();
        public static TextField SystemCPUField_Maximum = new TextField();

        private static AnchorPane ChartCenterPane = new AnchorPane();
        static LineChartsPaneFXUI lineChartFX =new LineChartsPaneFXUI();

        private static Button  AccountLoginButton = new Button();
        static AnchorPane  AccountLoginCenterPane = new AnchorPane();
        private static TextField AccountField = new TextField();
        private static PasswordField PasswrodField = new PasswordField();
        private static Button  RegisteredAccountButton = new Button();
        private static Button  ConfirmLoginButton = new Button();
        
        private static FileChooser fileChooser = new FileChooser();
        private static Button ApkPileButton = new Button();
        
        private static Button GetDevicesButton = new Button();
        static AnchorPane DevicesCenterPane = new AnchorPane();
        public static TextField MobileDevicesField = new TextField();
        static ObservableList<String> data = FXCollections.observableArrayList();
		static ListView<String> MobileDevicesListView = new ListView<String>(data);
        private static Button MobileDevicesButton = new Button();
        private static TextField MobilePhoneManufacturerField = new TextField();
        private static TextField MobilePhoneModelField = new TextField();
        private static TextField SyetemVersionField = new TextField();
        private static TextField ResolvingPowerField = new TextField();
        public static TextField SystemMemoryField = new TextField();
        public static int SystemMemory =0;
        
        private static Button InstallApkButton = new Button();
        static String apkSavePath=null;
        
        private static Button StartMonitoringButton = new Button();
        static boolean isstart = false;
        
        private static Button StopMonitoringButton = new Button();
        
        private static Button UploadAnalysisButton = new Button();
        static Thread UploadAnalysisThread = new Thread();
        
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
        	   PerformanceAutomationCenter();
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
//	    		    	APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPane.setVisible(false);
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
//	    		    	FrontEndFunctionCenter.setVisible(ture);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPane.setVisible(false);
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
//	    		    	APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPane.setVisible(false);
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
//	    		    	APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPane.setVisible(false);
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
//	    		    	APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		APPAutomationCenterButton.setLayoutX(0);
	    		APPAutomationCenterButton.setLayoutY(115+250);
	    		APPAutomationCenterButton.setPrefWidth(219);
	    		APPAutomationCenterButton.setPrefHeight(50);
	    		APPAutomationCenterButton.setId("APPAutomationCenterButton");
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
//	    		    	APPAutomationCenterPane.setVisible(true);
	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		PerformanceAutomationCenterButton.setLayoutX(0);
	    		PerformanceAutomationCenterButton.setLayoutY(115+300);
	    		PerformanceAutomationCenterButton.setPrefWidth(219);
	    		PerformanceAutomationCenterButton.setPrefHeight(50);
	    		PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButtonDown");
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
	    		    	PerformanceAutomationCenterPane.setVisible(true);
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
	    		PerformanceAutomationCenterPane.setVisible(true);
	    		
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

		public static void PerformanceAutomationCenter() throws Exception{
        	    PerformanceAutomationCenterPane.setLayoutX(222);
        	    PerformanceAutomationCenterPane.setLayoutY(115);
        	    PerformanceAutomationCenterPane.setPrefWidth(1381);
        	    PerformanceAutomationCenterPane.setPrefHeight(886);
        	    PerformanceAutomationCenterPane.setId("PerformanceAutomationCenterPane");
        	    PerformanceAutomationCenterPane.setVisible(false);

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
        	    SoftwareEngineeringField.setText("https://github.com/appetizerio/insights.py.git");
        	    
        	    SoftwareEngineeringButton.setLayoutX(415);
        	    SoftwareEngineeringButton.setLayoutY(10);
        	    SoftwareEngineeringButton.setPrefWidth(31);
        	    SoftwareEngineeringButton.setPrefHeight(30);
        	    SoftwareEngineeringButton.setId("ListViewButton");
        	    SoftwareEngineeringButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
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
//    	                	DownloadProgressBar.progressProperty().bind(Worker.progressProperty());
    	                	DownloadProgressLabel.textProperty().bind(Worker.messageProperty());
    	                	new Thread(Worker).start();
    	                	Timer timer = new Timer();
    		    			timer.schedule(new TimerTask() {
    		    				@Override
    		    				public void run() {
    		    					SoftwareEngineering = SoftwareEngineeringurl.substring(SoftwareEngineeringurl.lastIndexOf("/")+1);
    		    					String NewSoftwareEngineering = ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+SoftwareEngineering+"";
    		    					try {
    		    						String message =JGitUtil.CloneRepository(SoftwareEngineeringurl,NewSoftwareEngineering);
    			                		if(message=="Success"){
    			                			Platform.runLater(new Runnable() {
        			    	    			    @Override
        			    	    			    public void run() {
        			    	    			    	Dialog.SetMessageDialog("Success","工程下载成功！");
            			                			SoftwareEngineeringField.setText(SoftwareEngineering.replace(".git", ""));
                			                		DownloadProgressBar.setVisible(false);
                						    	    DownloadProgressLabel.setVisible(false);
                						    	    SoftwareEngineeringButton.setDisable(false);
                						    	    SoftwareEngineeringPath = NewSoftwareEngineering;
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
        						                	OpenDir(NewSoftwareEngineering);
//        						                	OpenFile(SoftwareEngineeringPath);
        						                	SoftwareEngineeringPath = NewSoftwareEngineering;
        			    	    			    }
        			    	    			});
    			                		}else{
    			                			Platform.runLater(new Runnable() {
        			    	    			    @Override
        			    	    			    public void run() {
        			    	    			    	DownloadProgressLabel.textProperty().unbind();
        			    	    			    	Dialog.SetMessageDialog("Warning","工程地址错误或网络连接失败，请检查后重试！");
        			    	    			    	DownloadProgressBar.setVisible(false);
        			    	    			    	DownloadProgressLabel.setVisible(false);
        			    	    			    	SoftwareEngineeringButton.setDisable(false);
        			    	    			    	SoftwareEngineeringPath = null;
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
	            });
        	    
        	    InstallationDependencyField.setLayoutX(115);
        	    InstallationDependencyField.setLayoutY(53);
        	    InstallationDependencyField.setPrefWidth(300);
        	    InstallationDependencyField.setPrefHeight(30);
        	    InstallationDependencyField.setId("OptionEntryBoxLL");
        	    InstallationDependencyField.setPromptText("请输入安装命令...");
        	    InstallationDependencyField.setPrefColumnCount(20);//设置文本最大显示内容长度
        	    InstallationDependencyField.setText("python -m pip install -r requirements.txt");
        	    InstallationDependencyButton.setLayoutX(415);
        	    InstallationDependencyButton.setLayoutY(53);
        	    InstallationDependencyButton.setPrefWidth(31);
        	    InstallationDependencyButton.setPrefHeight(30);
        	    InstallationDependencyButton.setId("ListViewButton");
        	    InstallationDependencyButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	String InstallationDependencyCommand=InstallationDependencyField.getText();
	                	if (StringUtil.isEmpty(SoftwareEngineeringPath)) {
		    	        	Dialog.SetMessageDialog("Warning","请下载正确的工程！");
		                }else if(StringUtil.isEmpty(InstallationDependencyCommand)){
                			Dialog.SetMessageDialog("Warning","请输入安装命令！");
	                	}else{
	                		InstallationDependencyButton.setDisable(true);
                			DownloadProgressBar.setLayoutX(450);
    	      	    	    DownloadProgressBar.setLayoutY(8+43);
    	      	    	    DownloadProgressLabel.setLayoutX(457);
    	      	    	    DownloadProgressLabel.setLayoutY(17+43);
    	                	DownloadProgressBar.setVisible(true);
    		    	    	DownloadProgressLabel.setVisible(true);
    	                	Worker = CreateWorker("InstallationDependency");
//    	                	DownloadProgressBar.progressProperty().bind(Worker.progressProperty());
    	                	DownloadProgressLabel.textProperty().bind(Worker.messageProperty());
    	                	new Thread(Worker).start();
	                		Timer timer = new Timer();
		        			timer.schedule(new TimerTask() {
								@Override
		        				public void run() {
		        				   Platform.runLater(new Runnable() {
		        					   @Override
		       		    			    public void run() {
		       		    			    	String InstallationDependency = "cmd /c c: && cd "+SoftwareEngineeringPath+" && "+InstallationDependencyCommand+"";
		       		    			    	try {
		       									int code = Command("InstallationDependency",InstallationDependency);
		       									if(code==0){
		       										Platform.runLater(new Runnable() {
		        			    	    			    @Override
		        			    	    			    public void run() {
		        			    	    			    	Dialog.SetMessageDialog("Success","安装依赖成功！");
		                			                		DownloadProgressBar.setVisible(false);
		                						    	    DownloadProgressLabel.setVisible(false);
		                						    	    InstallationDependencyButton.setDisable(false);
		        			    	    			    }
		        			    	    			});
		       									}else{
		       										Platform.runLater(new Runnable() {
					    	    	    			    @Override
					    	    	    			    public void run() {
					    	    	    			    	Dialog.SetMessageDialog("Warning","安装依赖失败，请检查工程路径或命令是否正确！");
					    	    	    			    	DownloadProgressBar.setVisible(false);
		                						    	    DownloadProgressLabel.setVisible(false);
		                						    	    InstallationDependencyButton.setDisable(false);
					    	    	    			    }
					    	    	    			});
		       									}
		       								} catch (IOException | InterruptedException e) {
		       									e.printStackTrace();
		       							    }
		       		    			    }
	       		    			    });
		        				}
		        			}, 200);
	                	}
	                }
	    	  	});
        	    
        	    final ToggleGroup SoftwareNameToggleGroup = new ToggleGroup();
        	    OneOptionRadioButton.setLayoutX(116);
        	    OneOptionRadioButton.setLayoutY(126);
        	    OneOptionRadioButton.setPrefWidth(5);
        	    OneOptionRadioButton.setPrefHeight(5);
        	    OneOptionRadioButton.setToggleGroup(SoftwareNameToggleGroup);
        	    OneOptionRadioButton.setUserData("其它");
        	    OneOptionRadioButton.setSelected(true);
        	    
        	    OneSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
	    		Image OneSoftwareIconIamge = new Image(OneSoftwareIcon, 65,65,true, false);
	    		OneSoftwareIconImageView.setImage(OneSoftwareIconIamge);
	    		
	    		OneSoftwareNameLabel.setId("SoftwareNameLabel");
	    		OneSoftwareNameLabel.setText("其它");
	    		
	    		VBox OneSoftwareVBox = new VBox();
	    		OneSoftwareVBox.setLayoutX(138);
	    		OneSoftwareVBox.setLayoutY(100);
	    		OneSoftwareVBox.setSpacing(2);
	    		OneSoftwareVBox.setAlignment(Pos.CENTER);
	    		OneSoftwareVBox.getChildren().add(OneSoftwareIconImageView);
	    		OneSoftwareVBox.getChildren().add(OneSoftwareNameLabel);
	    		
        	    TwoOptionRadioButton.setLayoutX(215);
        	    TwoOptionRadioButton.setLayoutY(126);
        	    TwoOptionRadioButton.setPrefWidth(5);
        	    TwoOptionRadioButton.setPrefHeight(5);
        	    TwoOptionRadioButton.setToggleGroup(SoftwareNameToggleGroup);
        	    TwoOptionRadioButton.setUserData("其它");
        	    TwoOptionRadioButton.setVisible(false);
        	    
        	    TwoSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
	    		Image TwoSoftwareIconIamge = new Image(TwoSoftwareIcon, 65,65,true, false);
	    		TwoSoftwareIconImageView.setImage(TwoSoftwareIconIamge);
	    		
	    		TwoSoftwareNameLabel.setId("SoftwareNameLabel");
	    		TwoSoftwareNameLabel.setText("其它");
	    		
	    		VBox TwoSoftwareVBox = new VBox();
	    		TwoSoftwareVBox.setLayoutX(236);
	    		TwoSoftwareVBox.setLayoutY(100);
	    		TwoSoftwareVBox.setSpacing(2);
	    		TwoSoftwareVBox.setAlignment(Pos.CENTER);
	    		TwoSoftwareVBox.getChildren().add(TwoSoftwareIconImageView);
	    		TwoSoftwareVBox.getChildren().add(TwoSoftwareNameLabel);
	    		TwoSoftwareVBox.setVisible(false);
	    		
        	    ThreeOptionRadioButton.setLayoutX(314);
        	    ThreeOptionRadioButton.setLayoutY(126);
        	    ThreeOptionRadioButton.setPrefWidth(5);
        	    ThreeOptionRadioButton.setPrefHeight(5);
        	    ThreeOptionRadioButton.setToggleGroup(SoftwareNameToggleGroup);
        	    ThreeOptionRadioButton.setUserData("其它");
        	    ThreeOptionRadioButton.setVisible(false);
        	    
        	    ThreeSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
	    		Image ThreeSoftwareIconIamge = new Image(ThreeSoftwareIcon, 65,65,true, false);
	    		ThreeSoftwareIconImageView.setImage(ThreeSoftwareIconIamge);
	    		
	    		ThreeSoftwareNameLabel.setId("SoftwareNameLabel");
	    		ThreeSoftwareNameLabel.setText("其它");
	    		
	    		VBox ThreeSoftwareVBox = new VBox();
	    		ThreeSoftwareVBox.setLayoutX(335);
	    		ThreeSoftwareVBox.setLayoutY(100);
	    		ThreeSoftwareVBox.setSpacing(2);
	    		ThreeSoftwareVBox.setAlignment(Pos.CENTER);
	    		ThreeSoftwareVBox.getChildren().add(ThreeSoftwareIconImageView);
	    		ThreeSoftwareVBox.getChildren().add(ThreeSoftwareNameLabel);
	    		ThreeSoftwareVBox.setVisible(false);
	    		
        	    SoftwareNameToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        	        public void changed(ObservableValue<? extends Toggle> ov,
        	            Toggle old_toggle, Toggle new_toggle) {
        	          if (SoftwareNameToggleGroup.getSelectedToggle() != null) {
        	        	  System.out.println(SoftwareNameToggleGroup.getSelectedToggle().getUserData().toString());
        	        	  String SoftwareName = SoftwareNameToggleGroup.getSelectedToggle().getUserData().toString();
        	        	  AppPackageNameField.setText(SoftwareName);
        	          }
        	        }
        	    });

	    		AppPackageNameField.setLayoutX(110);
	    		AppPackageNameField.setLayoutY(200);
	    		AppPackageNameField.setPrefWidth(200);
	    		AppPackageNameField.setPrefHeight(30);
	    		AppPackageNameField.setId("InputBoxSL");
	    		AppPackageNameField.setPromptText("请安装需要监控的APP...");
	    		AppPackageNameField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		AppPackageNameField.setBackground(Background.EMPTY);
	    		AppPackageNameField.setBorder(Border.EMPTY);

	    		StartTimeField.setLayoutX(114);
	    		StartTimeField.setLayoutY(290);
	    		StartTimeField.setPrefWidth(100);
	    		StartTimeField.setPrefHeight(30);
	    		StartTimeField.setId("InputBoxM");
	    		StartTimeField.setPromptText("00：00：00");
	    		StartTimeField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		StartTimeField.setBackground(Background.EMPTY);
	    		StartTimeField.setBorder(Border.EMPTY);
	    		StartTimeField.setEditable(false);
	    		
	    		EndTimeField.setLayoutX(348);
	    		EndTimeField.setLayoutY(290);
	    		EndTimeField.setPrefWidth(100);
	    		EndTimeField.setPrefHeight(30);
	    		EndTimeField.setId("InputBoxM");
	    		EndTimeField.setPromptText("00：00：00");
	    		EndTimeField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		EndTimeField.setBackground(Background.EMPTY);
	    		EndTimeField.setBorder(Border.EMPTY);
	    		EndTimeField.setEditable(false);
	    		
	    		SendDataField.setLayoutX(114);
	    		SendDataField.setLayoutY(328);
	    		SendDataField.setPrefWidth(100);
	    		SendDataField.setPrefHeight(30);
	    		SendDataField.setId("InputBoxM");
	    		SendDataField.setPromptText("0.00 MB");
	    		SendDataField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		SendDataField.setBackground(Background.EMPTY);
	    		SendDataField.setBorder(Border.EMPTY);
	    		SendDataField.setEditable(false);
	    		
	    		ReceiveField.setLayoutX(348);
	    		ReceiveField.setLayoutY(328);
	    		ReceiveField.setPrefWidth(100);
	    		ReceiveField.setPrefHeight(30);
	    		ReceiveField.setId("InputBoxM");
	    		ReceiveField.setPromptText("0.00 MB");
	    		ReceiveField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ReceiveField.setBackground(Background.EMPTY);
	    		ReceiveField.setBorder(Border.EMPTY);
	    		ReceiveField.setEditable(false);
	    		
	    		ApplicationMemoryField_Average.setLayoutX(114);
	    		ApplicationMemoryField_Average.setLayoutY(409);
	    		ApplicationMemoryField_Average.setPrefWidth(100);
	    		ApplicationMemoryField_Average.setPrefHeight(30);
	    		ApplicationMemoryField_Average.setId("InputBoxM");
	    		ApplicationMemoryField_Average.setPromptText("0.0 MB");
	    		ApplicationMemoryField_Average.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ApplicationMemoryField_Average.setBackground(Background.EMPTY);
	    		ApplicationMemoryField_Average.setBorder(Border.EMPTY);
	    		ApplicationMemoryField_Average.setEditable(false);
	    		
	    		SystemMemoryField_Average.setLayoutX(348);
	    		SystemMemoryField_Average.setLayoutY(409);
	    		SystemMemoryField_Average.setPrefWidth(100);
	    		SystemMemoryField_Average.setPrefHeight(30);
	    		SystemMemoryField_Average.setId("InputBoxM");
	    		SystemMemoryField_Average.setPromptText("0.0 MB");
	    		SystemMemoryField_Average.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		SystemMemoryField_Average.setBackground(Background.EMPTY);
	    		SystemMemoryField_Average.setBorder(Border.EMPTY);
	    		SystemMemoryField_Average.setEditable(false);
	    		
	    		ApplicationCPUField_Average.setLayoutX(114);
	    		ApplicationCPUField_Average.setLayoutY(447);
	    		ApplicationCPUField_Average.setPrefWidth(100);
	    		ApplicationCPUField_Average.setPrefHeight(30);
	    		ApplicationCPUField_Average.setId("InputBoxM");
	    		ApplicationCPUField_Average.setPromptText("0.0 %");
	    		ApplicationCPUField_Average.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ApplicationCPUField_Average.setBackground(Background.EMPTY);
	    		ApplicationCPUField_Average.setBorder(Border.EMPTY);
	    		ApplicationCPUField_Average.setEditable(false);
	    		
	    		SystemCPUField_Average.setLayoutX(348);
	    		SystemCPUField_Average.setLayoutY(447);
	    		SystemCPUField_Average.setPrefWidth(100);
	    		SystemCPUField_Average.setPrefHeight(30);
	    		SystemCPUField_Average.setId("InputBoxM");
	    		SystemCPUField_Average.setPromptText("0.0 %");
	    		SystemCPUField_Average.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		SystemCPUField_Average.setBackground(Background.EMPTY);
	    		SystemCPUField_Average.setBorder(Border.EMPTY);
	    		SystemCPUField_Average.setEditable(false);
	    		
	    		ApplicationMemoryField_Minimum.setLayoutX(114);
	    		ApplicationMemoryField_Minimum.setLayoutY(533);
	    		ApplicationMemoryField_Minimum.setPrefWidth(100);
	    		ApplicationMemoryField_Minimum.setPrefHeight(30);
	    		ApplicationMemoryField_Minimum.setId("InputBoxM");
	    		ApplicationMemoryField_Minimum.setPromptText("0.0 MB");
	    		ApplicationMemoryField_Minimum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ApplicationMemoryField_Minimum.setBackground(Background.EMPTY);
	    		ApplicationMemoryField_Minimum.setBorder(Border.EMPTY);
	    		ApplicationMemoryField_Minimum.setEditable(false);
	    		
	    		SystemMemoryField_Minimum.setLayoutX(348);
	    		SystemMemoryField_Minimum.setLayoutY(533);
	    		SystemMemoryField_Minimum.setPrefWidth(100);
	    		SystemMemoryField_Minimum.setPrefHeight(30);
	    		SystemMemoryField_Minimum.setId("InputBoxM");
	    		SystemMemoryField_Minimum.setPromptText("0.0 MB");
	    		SystemMemoryField_Minimum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		SystemMemoryField_Minimum.setBackground(Background.EMPTY);
	    		SystemMemoryField_Minimum.setBorder(Border.EMPTY);
	    		SystemMemoryField_Minimum.setEditable(false);
	    		
	    		ApplicationCPUField_Minimum.setLayoutX(114);
	    		ApplicationCPUField_Minimum.setLayoutY(574);
	    		ApplicationCPUField_Minimum.setPrefWidth(100);
	    		ApplicationCPUField_Minimum.setPrefHeight(30);
	    		ApplicationCPUField_Minimum.setId("InputBoxM");
	    		ApplicationCPUField_Minimum.setPromptText("0.0 %");
	    		ApplicationCPUField_Minimum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ApplicationCPUField_Minimum.setBackground(Background.EMPTY);
	    		ApplicationCPUField_Minimum.setBorder(Border.EMPTY);
	    		ApplicationCPUField_Minimum.setEditable(false);
	    		
	    		SystemCPUField_Minimum.setLayoutX(348);
	    		SystemCPUField_Minimum.setLayoutY(574);
	    		SystemCPUField_Minimum.setPrefWidth(100);
	    		SystemCPUField_Minimum.setPrefHeight(30);
	    		SystemCPUField_Minimum.setId("InputBoxM");
	    		SystemCPUField_Minimum.setPromptText("0.0 %");
	    		SystemCPUField_Minimum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		SystemCPUField_Minimum.setBackground(Background.EMPTY);
	    		SystemCPUField_Minimum.setBorder(Border.EMPTY);
	    		SystemCPUField_Minimum.setEditable(false);
	    		
	    		ApplicationMemoryField_Maximum.setLayoutX(114);
	    		ApplicationMemoryField_Maximum.setLayoutY(665);
	    		ApplicationMemoryField_Maximum.setPrefWidth(100);
	    		ApplicationMemoryField_Maximum.setPrefHeight(30);
	    		ApplicationMemoryField_Maximum.setId("InputBoxM");
	    		ApplicationMemoryField_Maximum.setPromptText("0.0 MB");
	    		ApplicationMemoryField_Maximum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ApplicationMemoryField_Maximum.setBackground(Background.EMPTY);
	    		ApplicationMemoryField_Maximum.setBorder(Border.EMPTY);
	    		ApplicationMemoryField_Maximum.setEditable(false);
	    		
	    		SystemMemoryField_Maximum.setLayoutX(348);
	    		SystemMemoryField_Maximum.setLayoutY(665);
	    		SystemMemoryField_Maximum.setPrefWidth(100);
	    		SystemMemoryField_Maximum.setPrefHeight(30);
	    		SystemMemoryField_Maximum.setId("InputBoxM");
	    		SystemMemoryField_Maximum.setPromptText("0.0 MB");
	    		SystemMemoryField_Maximum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		SystemMemoryField_Maximum.setBackground(Background.EMPTY);
	    		SystemMemoryField_Maximum.setBorder(Border.EMPTY);
	    		SystemMemoryField_Maximum.setEditable(false);
	    		
	    		ApplicationCPUField_Maximum.setLayoutX(114);
	    		ApplicationCPUField_Maximum.setLayoutY(706);
	    		ApplicationCPUField_Maximum.setPrefWidth(100);
	    		ApplicationCPUField_Maximum.setPrefHeight(30);
	    		ApplicationCPUField_Maximum.setId("InputBoxM");
	    		ApplicationCPUField_Maximum.setPromptText("0.0 %");
	    		ApplicationCPUField_Maximum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ApplicationCPUField_Maximum.setBackground(Background.EMPTY);
	    		ApplicationCPUField_Maximum.setBorder(Border.EMPTY);
	    		ApplicationCPUField_Maximum.setEditable(false);
	    		
	    		SystemCPUField_Maximum.setLayoutX(348);
	    		SystemCPUField_Maximum.setLayoutY(706);
	    		SystemCPUField_Maximum.setPrefWidth(100);
	    		SystemCPUField_Maximum.setPrefHeight(30);
	    		SystemCPUField_Maximum.setId("InputBoxM");
	    		SystemCPUField_Maximum.setPromptText("0.0 %");
	    		SystemCPUField_Maximum.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		SystemCPUField_Maximum.setBackground(Background.EMPTY);
	    		SystemCPUField_Maximum.setBorder(Border.EMPTY);
	    		SystemCPUField_Maximum.setEditable(false);
	    		
	    		ChartCenterPane.setLayoutX(490);
	    		ChartCenterPane.setLayoutY(-8);
	    		ChartCenterPane.setPrefWidth(900);
	    		ChartCenterPane.setPrefHeight(700);
	    		ChartCenterPane.getChildren().addAll(lineChartFX.createLineChartBox());
	    		
	    		AccountLoginButton.setLayoutX(219);
	    		AccountLoginButton.setLayoutY(782);
	    		AccountLoginButton.setPrefWidth(137);
	    		AccountLoginButton.setPrefHeight(42);
	    		AccountLoginButton.setId("AccountLoginButton");
	    		AccountLoginButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	AccountLoginCenterPane.setVisible(true);
	                }
	    	    });
	    		
	    		AccountLoginCenterPane.setLayoutX(410);
	    		AccountLoginCenterPane.setLayoutY(160);
	    		AccountLoginCenterPane.setPrefWidth(360);
	    		AccountLoginCenterPane.setPrefHeight(330);
	    		AccountLoginCenterPane.setId("PerformanceAccountLoginCenterPane");
	    		AccountLoginCenterPane.setBackground(Background.EMPTY);
	    		AccountLoginCenterPane.setVisible(false);
				
		        Button AccountLoginCloseButton = new Button();
		        AccountLoginCloseButton.setId("CloseButton");
		        AccountLoginCloseButton.setPrefWidth(30);
		        AccountLoginCloseButton.setPrefHeight(27);
		        AccountLoginCloseButton.setOnAction(new EventHandler<ActionEvent>() {
	    			public void handle(ActionEvent event) {
	    				AccountLoginCenterPane.setVisible(false);
	    			}
	            });

	    		HBox AccountLoginMinCloseHox = new HBox();
	    		AccountLoginMinCloseHox.setLayoutX(330);
	    		AccountLoginMinCloseHox.setLayoutY(0);
	    		AccountLoginMinCloseHox.setBackground(Background.EMPTY);
	    		AccountLoginMinCloseHox.setPickOnBounds(false);//面板不参与计算边界，透明区域无鼠标事件
	    		AccountLoginMinCloseHox.getChildren().addAll(AccountLoginCloseButton);

	    		AccountField.setLayoutX(97);
	    		AccountField.setLayoutY(161);
	    		AccountField.setPrefWidth(230);
	    		AccountField.setPrefHeight(30);
	    		AccountField.setId("InputBoxL");
	    		AccountField.setPromptText("请输入账号...");
//	    		AccountField.setText("");
	    		AccountField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		AccountField.setBackground(Background.EMPTY);
	    		AccountField.setBorder(Border.EMPTY);
	    		
	    		PasswrodField.setLayoutX(97);
	    		PasswrodField.setLayoutY(201);
	    		PasswrodField.setPrefWidth(230);
	    		PasswrodField.setPrefHeight(30);
	    		PasswrodField.setId("InputBoxL");
	    		PasswrodField.setPromptText("请输入密码...");
//	    		PasswrodField.setText("");
	    		PasswrodField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		PasswrodField.setBackground(Background.EMPTY);
	    		PasswrodField.setBorder(Border.EMPTY);
	    		
	    		RegisteredAccountButton.setLayoutX(50);
	    		RegisteredAccountButton.setLayoutY(260);
	    		RegisteredAccountButton.setPrefWidth(108);
	    		RegisteredAccountButton.setPrefHeight(33);
	    		RegisteredAccountButton.setId("RegisteredAccountButton");
	    		RegisteredAccountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try {
	    			    	   OpenBrowserUtil.OpenBrowser("https://user.appetizer.io/user/register");
						} catch (Exception e) {
							   e.printStackTrace();
					    }
	    	  	    }
	    	  	});
	    		
	    		ConfirmLoginButton.setLayoutX(201);
	    		ConfirmLoginButton.setLayoutY(260);
	    		ConfirmLoginButton.setPrefWidth(108);
	    		ConfirmLoginButton.setPrefHeight(33);
	    		ConfirmLoginButton.setId("ConfirmLoginButton");
	    		ConfirmLoginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	String Account = AccountField.getText();
					    String PassWord = PasswrodField.getText();
	                	if (StringUtil.isEmpty(SoftwareEngineeringPath)) {
		    	        	Dialog.SetMessageDialog("Warning","请下载正确的工程！");
		                }else if("请输入账号..".equals(Account)|StringUtil.isEmpty(Account)){
	                		Dialog.SetMessageDialog("Warning","请输入账号！");
	          		        return;
    	          	    }else if("请输入密码...".equals(PassWord)|StringUtil.isEmpty(PassWord)){
    	          	    	Dialog.SetMessageDialog("Warning","请输入密码！");
	          		        return;
    	          	    }else{
    	          	    	Timer timer = new Timer();
    	        			timer.schedule(new TimerTask() {
    							@Override
    	        				public void run() {
    								Platform.runLater(new Runnable() {
    		        					   @Override
    		       		    			    public void run() {
    		       		    			    	String AccountLogin = "cmd /c c: && cd "+SoftwareEngineeringPath+" && python insights.py login "+Account+" "+PassWord+"";
    		       		    			    	try {
    		       									int code = Command("AccountLogin",AccountLogin);
    		       									if(code==0){
    		       										Platform.runLater(new Runnable() {
    		        			    	    			    @Override
    		        			    	    			    public void run() {
    		        			    	    			    	Dialog.SetMessageDialog("Success","登录成功！");
    		        			    	    			    }
    		        			    	    			});
    		       									}else{
    		       										Platform.runLater(new Runnable() {
    					    	    	    			    @Override
    					    	    	    			    public void run() {
    					    	    	    			    	Dialog.SetMessageDialog("Warning","登录失败，请检查工程路径或账号密码是否正确！");
    					    	    	    			    }
    					    	    	    			});
    		       									}
    		       								} catch (IOException | InterruptedException e) {
    		       									e.printStackTrace();
    		       							    }
    		       		    			    }
    	       		    			});
    	        				}
    	        			}, 200);
    	          	    }    
	    	  	    }
	    	  	});
	    		
	    	    Rectangle AccountLoginRectangle = new Rectangle();
	    	    AccountLoginRectangle.setWidth(360);
	    	    AccountLoginRectangle.setHeight(330);
	    	    AccountLoginRectangle.setArcHeight(10);
	    	    AccountLoginRectangle.setArcWidth(10);
	    	    AccountLoginCenterPane.setClip(AccountLoginRectangle);
	    	    
	    	    AccountLoginCenterPane.getChildren().add(AccountLoginMinCloseHox);
	    	    AccountLoginCenterPane.getChildren().add(AccountField);
	    	    AccountLoginCenterPane.getChildren().add(PasswrodField);	
	    	    AccountLoginCenterPane.getChildren().add(RegisteredAccountButton);
	    	    AccountLoginCenterPane.getChildren().add(ConfirmLoginButton);	
	    		
	    		
	    	    fileChooser.getExtensionFilters().addAll(
	    	    		new FileChooser.ExtensionFilter("apk", "*.apk"),
		    	    	new FileChooser.ExtensionFilter("All Images", "*.*")
		         );
	    		
	    	    ApkPileButton.setLayoutX(380);
	    		ApkPileButton.setLayoutY(782);
	    		ApkPileButton.setPrefWidth(137);
	    		ApkPileButton.setPrefHeight(42);
	    		ApkPileButton.setId("ApkPileButton");
	    		ApkPileButton.setOnAction(new EventHandler<ActionEvent>() {
	    			public void handle(ActionEvent event) {
	                	if (StringUtil.isEmpty(SoftwareEngineeringPath)) {
		    	        	Dialog.SetMessageDialog("Warning","请下载正确的工程！");
		                }else{
		                	ApkPileButton.setDisable(true);
		                	fileChooser.setTitle("请选择需要插桩的Apk文件");
		                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\Desktop"));                 
		                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
		                	if (file != null) {
		                		String apkPath = file.getPath();
		                		String NewapkPath = apkPath.replace(".apk", "process.apk");
     						    Timer timer = new Timer();
				        			timer.schedule(new TimerTask() {
										@Override
				        				public void run() {
											String ApkPile = "cmd /c c: && cd "+SoftwareEngineeringPath+" && python insights.py process "+apkPath+" "+NewapkPath+"";
		       		    			    	try {
		       									int code = Command("ApkPile",ApkPile);
		       									if(code==0){
		       										Platform.runLater(new Runnable() {
		        			    	    			    @Override
		        			    	    			    public void run() {
		        			    	    			    	Dialog.SetMessageDialog("Success","插桩成功！");
		        			    	    			    	ApkPileButton.setDisable(false);
		        			    	    			    	apkSavePath = NewapkPath;
		        			    	    			    }
		        			    	    			});
		       									}else{
		       										Platform.runLater(new Runnable() {
 					    	    	    			    @Override
 					    	    	    			    public void run() {
 					    	    	    			    	Dialog.SetMessageDialog("Warning","插桩失败，请检查工程路径或apk包是否正确！");
 					    	    	    			    	ApkPileButton.setDisable(false);
 					    	    	    			    }
 					    	    	    			});
		       									}
		       		    			    	} catch (IOException | InterruptedException e) {
	 		       									e.printStackTrace();
	 		       							}
					        			}
				        			}, 200);	  
		                	}else{
		                		ApkPileButton.setDisable(false);
		                	}
		                }
	                }
	            });

//	    		ApkPileButton.setLayoutX(380);
//	    		ApkPileButton.setLayoutY(767);
//	    		ApkPileButton.setPrefWidth(137);
//	    		ApkPileButton.setPrefHeight(42);
//	    		ApkPileButton.setId("ApkPileButton");
//	    		ApkPileButton.setOnAction(new EventHandler<ActionEvent>() {
//	                public void handle(ActionEvent event) {
//	                	ApkPileButton.setDisable(true);
//	                	if (StringUtil.isEmpty(SoftwareEngineering)) {
//		    	        	Dialog.SetMessageDialog("Warning","请下载正确的工程！");
//		    	        	ApkPileButton.setDisable(false);
//		                }else if (ThreeSoftwareNameLabel.getText()!="其它") {
//		    	        	Dialog.SetMessageDialog("Warning","目前最大只支持三个APP！");
//		    	        	ThreeOptionRadioButton.setUserData("其它");
//		    	        	ThreeSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
//					    	Image SoftwareIconIamge = new Image(ThreeSoftwareIcon, 65,65,true, false);
//					    	ThreeSoftwareIconImageView.setImage(SoftwareIconIamge);
//					    	ThreeSoftwareNameLabel.setText("其它");
//		    	        	ApkPileButton.setDisable(false);
//		                }else{
//		                	try{
//		                		fileChooser.setTitle("请选择需要插桩的Apk文件");
//			                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\Desktop"));                 
//			                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
//			                	if (file != null) {
//			                		String apkPath = file.getPath();
//			                	    apkSavePath = apkPath.replace(".apk", "process.apk");
////			                		System.out.println(apkPath);
////			                		System.out.println(apkSavePath);
//			                		Platform.runLater(new Runnable() {
// 		        					   @Override
// 		       		    			    public void run() {
// 		        						   if(OneSoftwareNameLabel.getText()=="其它"){
// 		        							    ApkPileButton.setDisable(true);
// 		        							    DownloadProgressBar.setLayoutX(211);
// 	 		        						    DownloadProgressBar.setLayoutY(118);
// 	 	 		         	      	    	    DownloadProgressLabel.setLayoutX(218);
// 	 	 		         	      	    	    DownloadProgressLabel.setLayoutY(127);
// 	 	 		         	      	    	    id =1;
// 		        						   }else{
// 		        							    TwoOptionRadioButton.setVisible(true);
// 		        							    TwoSoftwareVBox.setVisible(true);
// 		        							    TwoOptionRadioButton.setSelected(true);
// 		        							   if(TwoSoftwareNameLabel.getText()=="其它"){
// 		        								    ApkPileButton.setDisable(true);
// 	 		        							    DownloadProgressBar.setLayoutX(311);
// 	 	 		        						    DownloadProgressBar.setLayoutY(118);
// 	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutX(318);
// 	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutY(127);	
// 	 	 	 		         	      	    	    id =2;
// 	 		        						   }else{
// 	 		        							    ThreeOptionRadioButton.setVisible(true);
// 	 		        							    ThreeSoftwareVBox.setVisible(true);
// 	 		        							    ThreeOptionRadioButton.setSelected(true);
// 	 		        							  if(ThreeSoftwareNameLabel.getText()=="其它"){
// 	 		        								    ApkPileButton.setDisable(true);
// 	 	 		        							    DownloadProgressBar.setLayoutX(410);
// 	 	 	 		        						    DownloadProgressBar.setLayoutY(118);
// 	 	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutX(417);
// 	 	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutY(127);
// 	 	 	 	 		         	      	    	    id =3;
// 	 	 		        						   }
// 	 		        						   }  
// 		        						   } 
// 		        						    DownloadProgressBar.setVisible(true);
//	 		         		    	    	DownloadProgressLabel.setVisible(true);
//	 		         	                	Worker = CreateWorker("ApkPile");
////	 		         	                	DownloadProgressBar.progressProperty().bind(Worker.progressProperty());
//	 		         	                	DownloadProgressLabel.textProperty().bind(Worker.messageProperty());
//	 		         	                	new Thread(Worker).start();
// 		        						    Timer timer = new Timer();
// 						        			timer.schedule(new TimerTask() {
// 												@Override
// 						        				public void run() {
// 													String ApkPile = "cmd /c c: && cd "+SoftwareEngineeringPath+" && python insights.py process "+apkPath+" "+apkSavePath+"";
// 		 		       		    			    	try {
// 		 		       									int code = Command("ApkPile",ApkPile);
// 		 		       									if(code==0){
// 		 		       										Platform.runLater(new Runnable() {
// 		 		        			    	    			    @Override
// 		 		        			    	    			    public void run() {
// 		 		        			    	    			    	Dialog.SetMessageDialog("Success","插桩成功！");
// 		 		        			    	    			    	DownloadProgressBar.setVisible(false);
// 													    	    	DownloadProgressLabel.setVisible(false);
// 		 		        			    	    			    	ApkPileButton.setDisable(false);
// 		 		        			    	    			    }
// 		 		        			    	    			});
// 		 		       									    try{
//												               if(id==1){
//												            	   ApkInfo apkInfo = new ApkUtil().getApkInfo(apkSavePath);
//													               System.out.println(apkInfo);
//													               String apkName = apkInfo.getApplicationLable(); 
//													               apkPackageName = apkInfo.getPackageName(); 
////												            	   OneSoftwareIcon = ""+ApkIconUtil.extractFileFromApk(apkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\src\\main\\resources\\image\\HomePagePane\\RightPane\\APPAutomationCenterPane\\SoftwareIcon\\" + apkName + ".png");
//															       OneSoftwareIcon = ApkIconUtil.extractFileFromApk(apkSavePath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.dir")+"/src/main/resources/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/" + apkName + ".png");
//													    	       Platform.runLater(new Runnable() {
//										    	    			       @Override
//										    	    			        public void run() {
//										    	    			    	   OneOptionRadioButton.setUserData(apkPackageName);
//										    	    			        	Image SoftwareIconIamge = new Image("file:///"+OneSoftwareIcon, 65,65,true, false);
//																    	   OneSoftwareIconImageView.setImage(SoftwareIconIamge);
//																    	   OneSoftwareNameLabel.setText(apkName);   
//																    	   AppPackageNameField.setText(apkPackageName);
//										    	    			        }
//										    	    		        });
//												               }else if(id==2){
//												            	   ApkInfo apkInfo = new ApkUtil().getApkInfo(apkSavePath);
//													               System.out.println(apkInfo);
//													               String apkName = apkInfo.getApplicationLable(); 
//													               apkPackageName = apkInfo.getPackageName(); 
////												            	   OneSoftwareIcon = ""+ApkIconUtil.extractFileFromApk(apkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\src\\main\\resources\\image\\HomePagePane\\RightPane\\APPAutomationCenterPane\\SoftwareIcon\\" + apkName + ".png");
//															       TwoSoftwareIcon = ApkIconUtil.extractFileFromApk(apkSavePath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.dir")+"/src/main/resources/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/" + apkName + ".png");
//													    	       Platform.runLater(new Runnable() {
//										    	    			       @Override
//										    	    			        public void run() {
//										    	    			    	   TwoOptionRadioButton.setUserData(apkPackageName);
//										    	    			        	Image SoftwareIconIamge = new Image("file:///"+TwoSoftwareIcon, 65,65,true, false);
//																    	   TwoSoftwareIconImageView.setImage(SoftwareIconIamge);
//																    	   TwoSoftwareNameLabel.setText(apkName);   
//																    	   AppPackageNameField.setText(apkPackageName);
//										    	    			        }
//										    	    		        });
//												               }else if(id==3){
//												            	   ApkInfo apkInfo = new ApkUtil().getApkInfo(apkSavePath);
//													               System.out.println(apkInfo);
//													               String apkName = apkInfo.getApplicationLable(); 
//													               apkPackageName = apkInfo.getPackageName(); 
////												            	   OneSoftwareIcon = ""+ApkIconUtil.extractFileFromApk(apkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\src\\main\\resources\\image\\HomePagePane\\RightPane\\APPAutomationCenterPane\\SoftwareIcon\\" + apkName + ".png");
//															       ThreeSoftwareIcon = ApkIconUtil.extractFileFromApk(apkSavePath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.dir")+"/src/main/resources/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/" + apkName + ".png");
//													    	       Platform.runLater(new Runnable() {
//										    	    			       @Override
//										    	    			        public void run() {
//										    	    			    	   ThreeOptionRadioButton.setUserData(apkPackageName);
//										    	    			        	Image SoftwareIconIamge = new Image("file:///"+ThreeSoftwareIcon, 65,65,true, false);
//										    	    			           ThreeSoftwareIconImageView.setImage(SoftwareIconIamge);
//																    	   ThreeSoftwareNameLabel.setText(apkName);   
//																    	   AppPackageNameField.setText(apkPackageName);
//										    	    			        }
//										    	    		        });
//												               }
//  									                         }catch (Exception e) {
//  									                	           Platform.runLater(new Runnable() {
//  									    	    			           @Override
//  									    	    			            public void run() {
//  									    	    			    	       Dialog.SetMessageDialog("Warning","无法正常解析APK包，请重新下载！");
//  									    	    			            }
//  									    	    		          });  
//  													         }	
// 		 		       									}else{
// 		 		       										Platform.runLater(new Runnable() {
// 		 					    	    	    			    @Override
// 		 					    	    	    			    public void run() {
// 		 					    	    	    			    	Dialog.SetMessageDialog("Warning","插桩失败，请检查工程路径或apk包是否正确！");
// 		 					    	    	    			    	DownloadProgressBar.setVisible(false);
// 													    	    	DownloadProgressLabel.setVisible(false);
// 													    	    	if(id==1){
// 													    	    		OneSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
// 	 														    		Image SoftwareIconIamge = new Image(OneSoftwareIcon, 65,65,true, false);
// 	 														    		OneSoftwareIconImageView.setImage(SoftwareIconIamge);
// 	 														    		OneSoftwareNameLabel.setText("其它");
// 													    	    	}else if(id==2){
// 													    	    		TwoSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
// 	 														    		Image SoftwareIconIamge = new Image(TwoSoftwareIcon, 65,65,true, false);
// 	 														    		TwoSoftwareIconImageView.setImage(SoftwareIconIamge);
// 	 														    		TwoSoftwareNameLabel.setText("其它");
// 													    	    	}else if(id==3){
// 													    	    		ThreeSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
// 	 														    		Image SoftwareIconIamge = new Image(ThreeSoftwareIcon, 65,65,true, false);
// 	 														    		ThreeSoftwareIconImageView.setImage(SoftwareIconIamge);
// 	 														    		ThreeSoftwareNameLabel.setText("其它");
// 													    	    	}
// 		 					    	    	    			    	ApkPileButton.setDisable(false);
// 		 					    	    	    			    }
// 		 					    	    	    			});
// 		 		       									}
// 		 		       								} catch (IOException | InterruptedException e) {
// 		 		       									e.printStackTrace();
// 		 		       							    }
// 						        				}
// 						        			}, 200);	    			    	
// 		       		    			    }
// 	       		    			    });
//			                	}else{
//			                		ApkPileButton.setDisable(false);
//			                	}
//			                } catch (Exception e) {
//								e.printStackTrace();
//								Dialog.SetMessageDialog("Error","未找到工程目录，请下载正确的工程！");
//		                	}
//	                    }
//	                }
//	    	    });
	    		
	    		GetDevicesButton.setLayoutX(541);
	    	    GetDevicesButton.setLayoutY(782);
	    	    GetDevicesButton.setPrefWidth(137);
	    	    GetDevicesButton.setPrefHeight(42);
	    	    GetDevicesButton.setId("PerGetDevicesButton");
	    	    GetDevicesButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	MobileDevicesListView.toFront();
	                	DevicesCenterPane.setVisible(true);
	                }
	    	    });
	    	    
	    	    DevicesCenterPane.setLayoutX(410);
	    	    DevicesCenterPane.setLayoutY(160);
	    	    DevicesCenterPane.setPrefWidth(360);
				DevicesCenterPane.setPrefHeight(330);
				DevicesCenterPane.setId("PerformanceDevicesCenterPane");
				DevicesCenterPane.setBackground(Background.EMPTY);
				DevicesCenterPane.setVisible(false);
				
		        Button DevicesCloseButton = new Button();
		        DevicesCloseButton.setId("CloseButton");
		        DevicesCloseButton.setPrefWidth(30);
		        DevicesCloseButton.setPrefHeight(27);
		        DevicesCloseButton.setOnAction(new EventHandler<ActionEvent>() {
	    			public void handle(ActionEvent event) {
	    				DevicesCenterPane.setVisible(false);
	    			}
	            });

	    		HBox DevicesMinCloseHox = new HBox();
	    		DevicesMinCloseHox.setLayoutX(330);
	    		DevicesMinCloseHox.setLayoutY(0);
	    		DevicesMinCloseHox.setBackground(Background.EMPTY);
	    		DevicesMinCloseHox.setPickOnBounds(false);//面板不参与计算边界，透明区域无鼠标事件
	    		DevicesMinCloseHox.getChildren().addAll(DevicesCloseButton);

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
						
						String SystemMemory = "cmd /c adb -s "+new_val+" shell cat proc/meminfo| findstr MemTotal";
						CommandAdb("SystemMemory",SystemMemory);
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
	    	    ResolvingPowerField.setLayoutY(215);
	    	    ResolvingPowerField.setPrefWidth(230);
	    	    ResolvingPowerField.setPrefHeight(30);
	    	    ResolvingPowerField.setId("InputBoxL");
	    	    ResolvingPowerField.setPromptText("请获取设备...");
//	    	    ResolvingPowerField.setText("720x1280");
	    	    ResolvingPowerField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    ResolvingPowerField.setBackground(Background.EMPTY);
	    	    ResolvingPowerField.setBorder(Border.EMPTY);
	    	    ResolvingPowerField.setEditable(false);
	    	    
	    	    SystemMemoryField.setLayoutX(110);
	    	    SystemMemoryField.setLayoutY(257);
	    	    SystemMemoryField.setPrefWidth(230);
	    	    SystemMemoryField.setPrefHeight(30);
	    	    SystemMemoryField.setId("InputBoxL");
	    	    SystemMemoryField.setPromptText("请获取设备...");
//	    	    SystemMemoryField.setText("3567MB");
	    	    SystemMemoryField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    	    SystemMemoryField.setBackground(Background.EMPTY);
	    	    SystemMemoryField.setBorder(Border.EMPTY);
	    	    SystemMemoryField.setEditable(false);
	    	    
	    	    Rectangle DevicesRectangle = new Rectangle();
	    	    DevicesRectangle.setWidth(360);
	    	    DevicesRectangle.setHeight(330);
	    	    DevicesRectangle.setArcHeight(10);
	    	    DevicesRectangle.setArcWidth(10);
	    	    DevicesCenterPane.setClip(DevicesRectangle);
	    	    
	    	    DevicesCenterPane.getChildren().add(DevicesMinCloseHox);
	    	    DevicesCenterPane.getChildren().add(MobileDevicesField);
	    	    DevicesCenterPane.getChildren().add(MobileDevicesListView);	
	    	    DevicesCenterPane.getChildren().add(MobileDevicesButton);
	    	    DevicesCenterPane.getChildren().add(MobilePhoneManufacturerField);	
	    	    DevicesCenterPane.getChildren().add(MobilePhoneModelField);	
	    	    DevicesCenterPane.getChildren().add(SyetemVersionField);	
	    	    DevicesCenterPane.getChildren().add(ResolvingPowerField);	
	    	    DevicesCenterPane.getChildren().add(SystemMemoryField);	
	    		
	    	    InstallApkButton.setLayoutX(702);
	    	    InstallApkButton.setLayoutY(782);
	    	    InstallApkButton.setPrefWidth(137);
	    	    InstallApkButton.setPrefHeight(42);
	    	    InstallApkButton.setId("InstallApkButton");
	    	    InstallApkButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	if (StringUtil.isEmpty(MobileDevicesField.getText())) {
	                		Dialog.SetMessageDialog("Warning","请先获取设备！");
		                }else if (ThreeSoftwareNameLabel.getText()!="其它") {
		    	        	Dialog.SetMessageDialog("Warning","目前最大只支持三个APP！");
		    	        	ThreeOptionRadioButton.setUserData("其它");
		    	        	ThreeSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
					    	Image SoftwareIconIamge = new Image(ThreeSoftwareIcon, 65,65,true, false);
					    	ThreeSoftwareIconImageView.setImage(SoftwareIconIamge);
					    	ThreeSoftwareNameLabel.setText("其它");
		                }else{
		                	InstallApkButton.setDisable(true);
		                	fileChooser.setTitle("请选择已插桩的Apk文件进行安装");
		                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\Desktop"));                 
		                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
		                	if (file != null) {
		                		String ApkPath = file.getPath();
		                		Platform.runLater(new Runnable() {
		        					   @Override
		       		    			    public void run() {
		        						   if(OneSoftwareNameLabel.getText()=="其它"){
		        							    InstallApkButton.setDisable(true);
		        							    DownloadProgressBar.setLayoutX(211);
	 		        						    DownloadProgressBar.setLayoutY(118);
	 	 		         	      	    	    DownloadProgressLabel.setLayoutX(218);
	 	 		         	      	    	    DownloadProgressLabel.setLayoutY(127);
	 	 		         	      	    	    id =1;
		        						   }else{
		        							    TwoOptionRadioButton.setVisible(true);
		        							    TwoSoftwareVBox.setVisible(true);
		        							    TwoOptionRadioButton.setSelected(true);
		        							   if(TwoSoftwareNameLabel.getText()=="其它"){
		        								    InstallApkButton.setDisable(true);
	 		        							    DownloadProgressBar.setLayoutX(311);
	 	 		        						    DownloadProgressBar.setLayoutY(118);
	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutX(318);
	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutY(127);	
	 	 	 		         	      	    	    id =2;
	 		        						   }else{
	 		        							    ThreeOptionRadioButton.setVisible(true);
	 		        							    ThreeSoftwareVBox.setVisible(true);
	 		        							    ThreeOptionRadioButton.setSelected(true);
	 		        							  if(ThreeSoftwareNameLabel.getText()=="其它"){
	 		        								    InstallApkButton.setDisable(true);
	 	 		        							    DownloadProgressBar.setLayoutX(410);
	 	 	 		        						    DownloadProgressBar.setLayoutY(118);
	 	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutX(417);
	 	 	 	 		         	      	    	    DownloadProgressLabel.setLayoutY(127);
	 	 	 	 		         	      	    	    id =3;
	 	 		        						   }
	 		        						   }  
		        						   } 
		        						DownloadProgressBar.setVisible(true);
 		         		    	    	DownloadProgressLabel.setVisible(true);
 		         	                	Worker = CreateWorker("InstallApk");
// 		         	                	DownloadProgressBar.progressProperty().bind(Worker.progressProperty());
 		         	                	DownloadProgressLabel.textProperty().bind(Worker.messageProperty());
 		         	                	new Thread(Worker).start();
		        						    Timer timer = new Timer();
						        			timer.schedule(new TimerTask() {
												@Override
						        				public void run() {
													String InstallApk = "cmd /c adb -s "+MobileDevicesField.getText()+" install -r "+ApkPath+"";
		 		       		    			    	try {
		 		       									int code = Command("InstallApk",InstallApk);
		 		       									if(code==0){
		 		       										Platform.runLater(new Runnable() {
		 		        			    	    			    @Override
		 		        			    	    			    public void run() {
		 		        			    	    			    	Dialog.SetMessageDialog("Success","安装成功！");
		 		        			    	    			    	DownloadProgressBar.setVisible(false);
													    	    	DownloadProgressLabel.setVisible(false);
													    	    	InstallApkButton.setDisable(false);
													    	    	apkSavePath = ApkPath;
		 		        			    	    			    }
		 		        			    	    			});
											               if(id==1){
											            	   ApkInfo apkInfo = new ApkUtil().getApkInfo(ApkPath);
												               System.out.println(apkInfo);
												               String apkName = apkInfo.getApplicationLable(); 
												               apkPackageName = apkInfo.getPackageName(); 
//											            	   OneSoftwareIcon = ""+ApkIconUtil.extractFileFromApk(apkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\src\\main\\resources\\image\\HomePagePane\\RightPane\\APPAutomationCenterPane\\SoftwareIcon\\" + apkName + ".png");
														       OneSoftwareIcon = ApkIconUtil.extractFileFromApk(ApkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.dir")+"/src/main/resources/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/" + apkName + ".png");
												    	       Platform.runLater(new Runnable() {
									    	    			       @Override
									    	    			        public void run() {
									    	    			    	   OneOptionRadioButton.setUserData(apkPackageName);
									    	    			        	Image SoftwareIconIamge = new Image("file:///"+OneSoftwareIcon, 65,65,true, false);
															    	   OneSoftwareIconImageView.setImage(SoftwareIconIamge);
															    	   OneSoftwareNameLabel.setText(apkName);   
															    	   AppPackageNameField.setText(apkPackageName);
									    	    			        }
									    	    		        });
											               }else if(id==2){
											            	   ApkInfo apkInfo = new ApkUtil().getApkInfo(ApkPath);
												               System.out.println(apkInfo);
												               String apkName = apkInfo.getApplicationLable(); 
												               apkPackageName = apkInfo.getPackageName(); 
//											            	   OneSoftwareIcon = ""+ApkIconUtil.extractFileFromApk(apkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\src\\main\\resources\\image\\HomePagePane\\RightPane\\APPAutomationCenterPane\\SoftwareIcon\\" + apkName + ".png");
														       TwoSoftwareIcon = ApkIconUtil.extractFileFromApk(ApkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.dir")+"/src/main/resources/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/" + apkName + ".png");
												    	       Platform.runLater(new Runnable() {
									    	    			       @Override
									    	    			        public void run() {
									    	    			    	   TwoOptionRadioButton.setUserData(apkPackageName);
									    	    			        	Image SoftwareIconIamge = new Image("file:///"+TwoSoftwareIcon, 65,65,true, false);
															    	   TwoSoftwareIconImageView.setImage(SoftwareIconIamge);
															    	   TwoSoftwareNameLabel.setText(apkName);   
															    	   AppPackageNameField.setText(apkPackageName);
									    	    			        }
									    	    		        });
											               }else if(id==3){
											            	   ApkInfo apkInfo = new ApkUtil().getApkInfo(ApkPath);
												               System.out.println(apkInfo);
												               String apkName = apkInfo.getApplicationLable(); 
												               apkPackageName = apkInfo.getPackageName(); 
//											            	   OneSoftwareIcon = ""+ApkIconUtil.extractFileFromApk(apkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\src\\main\\resources\\image\\HomePagePane\\RightPane\\APPAutomationCenterPane\\SoftwareIcon\\" + apkName + ".png");
														       ThreeSoftwareIcon = ApkIconUtil.extractFileFromApk(ApkPath, apkInfo.getApplicationIcon(), ""+System.getProperty("user.dir")+"/src/main/resources/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/" + apkName + ".png");
												    	       Platform.runLater(new Runnable() {
									    	    			       @Override
									    	    			        public void run() {
									    	    			    	   ThreeOptionRadioButton.setUserData(apkPackageName);
									    	    			        	Image SoftwareIconIamge = new Image("file:///"+ThreeSoftwareIcon, 65,65,true, false);
									    	    			           ThreeSoftwareIconImageView.setImage(SoftwareIconIamge);
															    	   ThreeSoftwareNameLabel.setText(apkName);   
															    	   AppPackageNameField.setText(apkPackageName);
									    	    			        }
									    	    		        });
											               }
		 		       									}else{
		 		       										Platform.runLater(new Runnable() {
		 					    	    	    			    @Override
		 					    	    	    			    public void run() {
		 					    	    	    			    	Dialog.SetMessageDialog("Warning","安装失败，请检查是否已安装或apk包是否正确！");
		 					    	    	    			    	DownloadProgressBar.setVisible(false);
													    	    	DownloadProgressLabel.setVisible(false);
													    	    	if(id==1){
													    	    		OneSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
	 														    		Image SoftwareIconIamge = new Image(OneSoftwareIcon, 65,65,true, false);
	 														    		OneSoftwareIconImageView.setImage(SoftwareIconIamge);
	 														    		OneSoftwareNameLabel.setText("其它");
													    	    	}else if(id==2){
													    	    		TwoSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
	 														    		Image SoftwareIconIamge = new Image(TwoSoftwareIcon, 65,65,true, false);
	 														    		TwoSoftwareIconImageView.setImage(SoftwareIconIamge);
	 														    		TwoSoftwareNameLabel.setText("其它");
													    	    	}else if(id==3){
													    	    		ThreeSoftwareIcon = "/image/HomePagePane/RightPane/PerformanceAutomationCenterPane/SoftwareIcon/SoftwareIcon.png";
	 														    		Image SoftwareIconIamge = new Image(ThreeSoftwareIcon, 65,65,true, false);
	 														    		ThreeSoftwareIconImageView.setImage(SoftwareIconIamge);
	 														    		ThreeSoftwareNameLabel.setText("其它");
													    	    	}
													    	    	InstallApkButton.setDisable(false);
		 					    	    	    			    }
		 					    	    	    			});
		 		       									}
		 		       								} catch (Exception e) {
		 		       									e.printStackTrace();
		 		       							    }
						        				}
						        			}, 200);	    			    	
		       		    			    }
	       		    			    });
		                	}else{
		                		InstallApkButton.setDisable(false);
		                	}	
	                    }
	                }
	    	    });

	    	    StartMonitoringButton.setLayoutX(863);
	    	    StartMonitoringButton.setLayoutY(782);
	    	    StartMonitoringButton.setPrefWidth(137);
	    	    StartMonitoringButton.setPrefHeight(42);
	    	    StartMonitoringButton.setId("StartMonitoringButton");
	    	    StartMonitoringButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	String udid =MobileDevicesField.getText();
	                	String packagename =AppPackageNameField.getText();
	                	if (StringUtil.isEmpty(udid)) {
	                		Dialog.SetMessageDialog("Warning","请先获取设备！");
		                }else if (StringUtil.isEmpty(packagename)) {
	                		Dialog.SetMessageDialog("Warning","请先安装APP，获取包名！");
		                }else{
		                	StartMonitoringButton.setDisable(true);
		                	Timer timer = new Timer();
		        			timer.schedule(new TimerTask() {
								@Override
		        				public void run() {
		        				   Platform.runLater(new Runnable() {
		        					   @Override
		       		    			    public void run() {
		        						   StartMonitoringButton.setVisible(false);
		        						   StopMonitoringButton.setVisible(true);
		        						   StopMonitoringButton.setDisable(false);
		       		                	   lineChartFX.setUdid(udid);
		       		                	   lineChartFX.setPackagename(packagename);
		       		                	   lineChartFX.clearStatisticsData();
		       		                	   lineChartFX.startMonitor();
		       		                	   lineChartFX.setIsStatistics(true);
		       		                	   StartTimeField.setText(TimeUtil.getTime("HH：mm：ss"));
		       		                	   EndTimeField.clear();
		       		    			    }
	       		    			    });
		        				}
		        			}, 200);
		                }
	                }
	    	    });
	    	    
	    	    StopMonitoringButton.setLayoutX(863);
	    	    StopMonitoringButton.setLayoutY(782);
	    	    StopMonitoringButton.setPrefWidth(137);
	    	    StopMonitoringButton.setPrefHeight(42);
	    	    StopMonitoringButton.setId("StopMonitoringButton");
	    	    StopMonitoringButton.setVisible(false);
	    	    StopMonitoringButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	StopMonitoringButton.setDisable(true);
	                	Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
							@Override
	        				public void run() {
	        				   Platform.runLater(new Runnable() {
	        					   @Override
	       		    			    public void run() {
	        						   StopMonitoringButton.setVisible(false);
	        						   StartMonitoringButton.setVisible(true);
	        						   StartMonitoringButton.setDisable(false);
	        		                   lineChartFX.stopMonitor();
	        		                   lineChartFX.setIsStatistics(false);
	        		                   EndTimeField.setText(TimeUtil.getTime("HH：mm：ss"));
	       		    			    }
       		    			    });
	        				}
	        			}, 500);
	                }
	    	    });
	    	    
	    	    UploadAnalysisButton.setLayoutX(1025);
	    	    UploadAnalysisButton.setLayoutY(782);
	    	    UploadAnalysisButton.setPrefWidth(137);
	    	    UploadAnalysisButton.setPrefHeight(42);
	    	    UploadAnalysisButton.setId("UploadAnalysisButton");
	    	    UploadAnalysisButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                	String Devices = MobileDevicesField.getText();
	                	if (StringUtil.isEmpty(SoftwareEngineeringPath)) {
		    	        	Dialog.SetMessageDialog("Warning","请下载正确的工程！");
		                }else if (StringUtil.isEmpty(apkSavePath)) {
	                		Dialog.SetMessageDialog("Warning","请安装已插桩的Apk！");
		                }else if (StringUtil.isEmpty(Devices)) {
	                		Dialog.SetMessageDialog("Warning","请先获取设备！");
		                }else{
	                		UploadAnalysisButton.setDisable(true);
	                		Runnable Analyze = new Runnable() {
		            			@Override
		            			public void run() {
		            				Timer timer = new Timer();
				        			timer.schedule(new TimerTask() {
										@Override
				        				public void run() {
											String Analyze  = "cmd /c c: && cd "+SoftwareEngineeringPath+" && python insights.py analyze "+apkSavePath+" -s "+Devices+" --clear";
		       		    			    	try {
		       									int code = Command("Analyze",Analyze);
		       									if(code==0){
		       										Platform.runLater(new Runnable() {
		        			    	    			    @Override
		        			    	    			    public void run() {
		        			    	    			    	Dialog.SetMessageDialog("Success","上传成功！");
		        			    	    			    	UploadAnalysisButton.setDisable(false);
		        			    	    			    }
		        			    	    			});
		       									}else{
		       										Platform.runLater(new Runnable() {
					    	    	    			    @Override
					    	    	    			    public void run() {
					    	    	    			    	Dialog.SetMessageDialog("Warning","上传失败，请检查Apk已安装或设备ID是否正确！");
					    	    	    			    	UploadAnalysisButton.setDisable(false);
					    	    	    			    }
					    	    	    			});
		       									}
		       								} catch (IOException | InterruptedException e) {
		       									e.printStackTrace();
		       							    }
				        				}
				        			}, 200);
		            			}
		            		};
		            		UploadAnalysisThread = new Thread(Analyze);
		            		UploadAnalysisThread.start();
    	          	    }	
	                }
	    	    });
	    		
	    	    PerformanceAutomationCenterPane.getChildren().add(DownloadProgressBar);
	    	    PerformanceAutomationCenterPane.getChildren().add(DownloadProgressLabel);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(SoftwareEngineeringField);
	    	    PerformanceAutomationCenterPane.getChildren().add(SoftwareEngineeringButton);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(InstallationDependencyField);
	    	    PerformanceAutomationCenterPane.getChildren().add(InstallationDependencyButton);

	    	    PerformanceAutomationCenterPane.getChildren().add(OneOptionRadioButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(OneSoftwareVBox);
	    	    PerformanceAutomationCenterPane.getChildren().add(TwoOptionRadioButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(TwoSoftwareVBox);
	    	    PerformanceAutomationCenterPane.getChildren().add(ThreeOptionRadioButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(ThreeSoftwareVBox);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(AppPackageNameField);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(StartTimeField);
	    	    PerformanceAutomationCenterPane.getChildren().add(EndTimeField);
	    	    PerformanceAutomationCenterPane.getChildren().add(SendDataField);
	    	    PerformanceAutomationCenterPane.getChildren().add(ReceiveField);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(ApplicationMemoryField_Average);
	    	    PerformanceAutomationCenterPane.getChildren().add(SystemMemoryField_Average);
	    	    PerformanceAutomationCenterPane.getChildren().add(ApplicationCPUField_Average);
	    	    PerformanceAutomationCenterPane.getChildren().add(SystemCPUField_Average);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(ApplicationMemoryField_Minimum);
	    	    PerformanceAutomationCenterPane.getChildren().add(SystemMemoryField_Minimum);
	    	    PerformanceAutomationCenterPane.getChildren().add(ApplicationCPUField_Minimum);
	    	    PerformanceAutomationCenterPane.getChildren().add(SystemCPUField_Minimum);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(ApplicationMemoryField_Maximum);
	    	    PerformanceAutomationCenterPane.getChildren().add(SystemMemoryField_Maximum);
	    	    PerformanceAutomationCenterPane.getChildren().add(ApplicationCPUField_Maximum);
	    	    PerformanceAutomationCenterPane.getChildren().add(SystemCPUField_Maximum);
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(ChartCenterPane); 
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(AccountLoginButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(AccountLoginCenterPane); 
	    	    
	    	    PerformanceAutomationCenterPane.getChildren().add(ApkPileButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(GetDevicesButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(DevicesCenterPane);
	    	    PerformanceAutomationCenterPane.getChildren().add(InstallApkButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(StartMonitoringButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(StopMonitoringButton);
	    	    PerformanceAutomationCenterPane.getChildren().add(UploadAnalysisButton);
	    	    
        	    HomePagePane.getChildren().add(PerformanceAutomationCenterPane);
//        	    HomePageView.HomePagePane.getChildren().add(PerformanceAutomationCenterPane);
        }

    	public static void getPerformanceAutomationCenterPane(boolean show) {
         	PerformanceAutomationCenterPane.setVisible(show);
     	}
    	
    	public static Task CreateWorker(String Name) {
            return new Task() {
                @Override
                protected Object call() throws Exception {
                	for(int i = 0; i < 100; i++){
                		if(Name=="SoftwareEngineering"){
                			Thread.sleep(275);
                		}else if(Name=="SoftwareDownloads"){
                			Thread.sleep(95);
                		}else if(Name=="InstallationDependency"){
                			Thread.sleep(2);
                		}else if(Name=="ApkPile"){
                			Thread.sleep(700);
                		}else if(Name=="InstallApk"){
                			Thread.sleep(60);
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
	            String line = input.readLine();
	            if(name=="InstallationDependency"){
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
	            			line = input.readLine();
	            		}
		            }else{
	 	            	i=1;
	 	            }
	            }else if(name=="AccountLogin"){
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
	            			if(line.equals("Failed to login. User does not exist or bad password")){
		            			i=1;
		            			break;
		            		}
	            			line=input.readLine();
	            		}
	            	}else{
	            		i=1;
	            	}
	            }else if(name=="ApkPile"){
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
	            			if(line.equals("not a valid APK")){
		            			i=1;
		            			break;
		            		}
	            			line=input.readLine();
	            		}
	            	}else{
	            		i=1;
	            	}
	            }else if(name=="InstallApk"){
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
	            			line=input.readLine();
	            		}
	            	}else{
	            		i=1;
	            	}
	            }else if(name=="Analyze"){
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
	            			line=input.readLine();
	            		}
	            	}else{
	            		i=1;
	            	}
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
	                }else if(name=="SystemMemory"){
	                	if("".equals(line)){	
 	                	}else{
 	                		SystemMemory=Math.round(Integer.parseInt(line.replace("MemTotal:        ", "").replace(" kB", ""))/1024);
 	                		SystemMemoryField.setText(SystemMemory+" MB");
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
        	Locale.setDefault(Locale.US);
	        launch(args);
        }
}