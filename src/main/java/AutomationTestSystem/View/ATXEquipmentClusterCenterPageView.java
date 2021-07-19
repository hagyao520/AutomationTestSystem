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
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;

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
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.DragUtil;
import AutomationTestSystem.Util.JGitUtil;
import AutomationTestSystem.Util.OpenBrowserUtil;
import AutomationTestSystem.Util.SendEmail;
import AutomationTestSystem.Util.StringUtil;

@SuppressWarnings({"restriction", "static-access","unused"})
public class ATXEquipmentClusterCenterPageView extends Application {

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
        private static Button ATXEquipmentClusterButton = new Button();
        
        private static WebView VideoPlayerWebView = new WebView();
        private static Button VideoPlayerButton =new Button();
        private static AnchorPane HomePageRightPane = new AnchorPane();

        static AnchorPane ATXEquipmentClusterCenterPane = new AnchorPane();
        static WebEngine BackgroundWebEngine = new WebEngine();

        private static Button StartServiceButton = new Button();
        static Thread StopServiceThread = new Thread();
        static Thread WEditorThread = new Thread();
        static Thread RethinkdbThread = new Thread();
		static Thread ATXServerThread = new Thread();
		static Thread ATXThread = new Thread();
		
        private static Button StopServiceButton = new Button();
        private static Button GetDevicesButton = new Button();
        static String ip =GetIP();
        
        static AnchorPane DevicesCenterPane = new AnchorPane();
        private static FileChooser fileChooser = new FileChooser();
        public static TextField GooglePathField = new TextField();
        private static Button GooglePathButton = new Button();
        public static TextField MobileIPField = new TextField();
        public static TextField MobileUDIDField = new TextField();
        public static TextField ComputerIPField = new TextField();
        private static Button StartRemoteButton = new Button();
        private static Button CloseWindowButton = new Button();
        
        private static Button RemoteControlButton = new Button();
        private static Button RemoteMonitoringButton = new Button();
        private static Button GetElementsButton = new Button();
        static int ID=0;
        
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
        	   ATXEquipmentClusterCenter();
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
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(false);
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
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(false);
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
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(false);
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
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(false);
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
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(false);
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
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(false);
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
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButton");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(false);
	    		    }
	    		});
	    		
	    		ATXEquipmentClusterButton.setLayoutX(0);
	    		ATXEquipmentClusterButton.setLayoutY(115+350);
	    		ATXEquipmentClusterButton.setPrefWidth(219);
	    		ATXEquipmentClusterButton.setPrefHeight(50);
	    		ATXEquipmentClusterButton.setId("ATXEquipmentClusterButtonDown");
	    		ATXEquipmentClusterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButton");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	PerformanceAutomationCenterButton.setId("PerformanceAutomationCenterButton");
	    		    	ATXEquipmentClusterButton.setId("ATXEquipmentClusterButtonDown");
	    		    	HomePageRightPane.setVisible(false);
//	    		    	FrontEndFunctionCenter.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(false);
//	    		    	PerformanceAutomationCenterPane.setVisible(false);
	    		    	ATXEquipmentClusterCenterPane.setVisible(true);
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
	    		LeftPane.getChildren().add(ATXEquipmentClusterButton);
	    		
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
	    		ATXEquipmentClusterCenterPane.setVisible(true);
	    		
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

		public static void ATXEquipmentClusterCenter() throws Exception{
        	    ATXEquipmentClusterCenterPane.setLayoutX(222);
        	    ATXEquipmentClusterCenterPane.setLayoutY(115);
        	    ATXEquipmentClusterCenterPane.setPrefWidth(1381);
        	    ATXEquipmentClusterCenterPane.setPrefHeight(886);
        	    ATXEquipmentClusterCenterPane.setId("ATXEquipmentClusterCenterPane");
        	    ATXEquipmentClusterCenterPane.setVisible(false);

        	    WebView BackgroundWebView = new WebView();
        		BackgroundWebView.setLayoutX(7);
        		BackgroundWebView.setLayoutY(6);
        		BackgroundWebView.setPrefSize(1365, 730);

        	    BackgroundWebEngine = BackgroundWebView.getEngine();
        	    BackgroundWebEngine.load("https://gitee.com/hagyao520/ATX");
//        		BackgroundWebEngine.load("https://www.baidu.com/s?wd=https%3A%2F%2Fgitee.com%2Fhagyao520%2FATX&rsv_spt=1&rsv_iqid=0xbb1fa6b7000bef88&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_dl=tb&rsv_enter=0&oq=ATX%253A%2520ATX%25E6%2598%25AF%25E4%25B8%2580%25E4%25B8%25AA%25E5%25AE%2589%25E5%258D%2593%25E8%25AE%25BE%25E5%25A4%2587%25E9%259B%2586%25E7%25BE%25A4%25E7%25AE%25A1%25E7%2590%2586%25E5%25B9%25B3%25E5%258F%25B0%252C%25E5%258F%25AF%25E5%259C%25A8%25E7%25BA%25BF%25E7%25AE%25A1%25E7%2590%2586%25E5%25A4%259A%25E5%258F%25B0%25E8%25AE%25BE%25E5%25A4%2587%252C&inputT=346&rsv_t=40d5acCWm3%2F4dcSAucmpVOMFB9txsxYlJtHuEKNBs%2BDWG5LE8fPzrEq6vXvIW%2FtwrCkb&rsv_pq=d86c78660011da73&rsv_n=2&rsv_sug3=54&rsv_sug2=0&rsv_sug4=346");
        		
        		StartServiceButton.setLayoutX(219);
        		StartServiceButton.setLayoutY(782);
        		StartServiceButton.setPrefWidth(137);
        		StartServiceButton.setPrefHeight(42);
        		StartServiceButton.setId("StartServiceButton");
        		StartServiceButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	StartServiceButton.setDisable(true);
	    	  	        //创建五个任务
	        			Runnable StopService = new Runnable() {
	            			@Override
	            			public void run() {
	            				Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
									@Override
			        				public void run() {
										String StopService = "cmd /c start wmic process where name='rethinkdb.exe' call terminate && start wmic process where name='atx-server.exe' call terminate && start wmic process where name='python.exe' call terminate";
		   		    			    	try {
		   									Command("StartService",StopService);
		   		    			    	} catch (IOException | InterruptedException e) {
			       							e.printStackTrace();
			       						}
			        				}
			        			}, 200);
	            			}
	            		};
	            		Runnable WEditorServer = new Runnable() {
	            			@Override
	            			public void run() {
	            				Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
									@Override
			        				public void run() {
										String WEditor = "cmd /c python -m weditor";
			        					try {
			        						Thread.sleep(1000);
			        						Command("StartService",WEditor);
			    						} catch (IOException | InterruptedException e) {
			    							e.printStackTrace();
			    					    }
			        				}
			        			}, 300);
	            			}
	            		};
	                	Runnable RethinkdbServer = new Runnable() {
	            			@Override
	            			public void run() {
	            				Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
									@Override
			        				public void run() {
										String rethinkdb = "cmd /c c: && cd "+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\ATX\\rethinkdb && rethinkdb --http-port 8090";
			        					try {
			        						Command("StartService",rethinkdb);
			    						} catch (IOException | InterruptedException e) {
			    							e.printStackTrace();
			    					    }
			        				}
			        			}, 2000);
	            			}
	            		};
	            		Runnable ATXServer = new Runnable() {
	            			@Override
	            			public void run() {
	            				Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
									@Override
			        				public void run() {
										String atx_server = "cmd /c c: && cd "+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\ATX\\atx-server && atx-server --port 8000";
			        					try {
			        						Command("StartService",atx_server);
			    						} catch (IOException | InterruptedException e) {
			    							e.printStackTrace();
			    					    }
			        				}
			        			}, 4000);
	            			}
	            		};
	            		Runnable ATX = new Runnable() {
	            			@Override
	            			public void run() {
	            				Timer timer = new Timer();
			        			timer.schedule(new TimerTask() {
									@Override
			        				public void run() {
										Platform.runLater(new Runnable() {
				        					   @Override
				       		    			    public void run() {
				        						   System.out.println("跳转至：http://"+ip+":8000/");
				        						   BackgroundWebEngine.load("http://"+ip+":8000/");
				       		    			    }
			       		    			});
			        				}
			        			}, 500);
	            			}
	            		};
	            		//创建五个线程
	            		StopServiceThread = new Thread(StopService);
	            		WEditorThread = new Thread(WEditorServer);
	            		RethinkdbThread = new Thread(RethinkdbServer);
	            		ATXServerThread = new Thread(ATXServer);
	            		ATXThread = new Thread(ATX);
	            		//启动五个线程，将同时执行创建的五个任务
	            		StopServiceThread.start();
	            		WEditorThread.start();
	            		RethinkdbThread.start();
	            		ATXServerThread.start();
	            		ATXThread.start();
	    	  	    }
	    	  	});
	    		
        		StopServiceButton.setLayoutX(380);
        		StopServiceButton.setLayoutY(782);
        		StopServiceButton.setPrefWidth(137);
        		StopServiceButton.setPrefHeight(42);
        		StopServiceButton.setId("StopServiceButton");
        		StopServiceButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
							@Override
	        				public void run() {
								String StopService = "cmd /c start wmic process where name='rethinkdb.exe' call terminate && start wmic process where name='atx-server.exe' call terminate && start wmic process where name='python.exe' call terminate";
   		    			    	try {
   									Command("StartService",StopService);
   									StartServiceButton.setDisable(false);
   		    			    	} catch (IOException | InterruptedException e) {
	       							e.printStackTrace();
	       						}
	        				}
	        			}, 200);
	    	  	    }
	    	  	});
	    		
        		GetDevicesButton.setLayoutX(541);
        		GetDevicesButton.setLayoutY(782);
        		GetDevicesButton.setPrefWidth(137);
        		GetDevicesButton.setPrefHeight(42);
        		GetDevicesButton.setId("ATXGetDevicesButton");
        		GetDevicesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	Platform.runLater(new Runnable() {
							@Override
   		    			    public void run() {
    						   Dialog.SetMessageDialog("Warning","请保持手机和电脑的正常连接，并开启开发者USB调试模式！");
   		    			    }   
   		    			});
	    	  	    	Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
							@Override
	        				public void run() {
								String init = "python -m uiautomator2 init --server "+ip+":8000";
   		    			    	try {
   									int code = Command("StartService",init);
   									Platform.runLater(new Runnable() {
   										@Override
		       		    			    public void run() {
			   							    BackgroundWebEngine.load("http://"+ip+":8000/");
		       		    			    }	   
		       		    			});	
   		    			    	} catch (IOException | InterruptedException e) {
	       							e.printStackTrace();
	       						}
	        				}
	        			}, 2000);
	    	  	    }
	    	  	});
        		
        		DevicesCenterPane.setLayoutX(461);
	    	    DevicesCenterPane.setLayoutY(201);
	    	    DevicesCenterPane.setPrefWidth(360);
				DevicesCenterPane.setPrefHeight(330);
				DevicesCenterPane.setId("ATXGetDevicesCenterPane");
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

	    		GooglePathField.setLayoutX(110);
	    		GooglePathField.setLayoutY(62);
	    		GooglePathField.setPrefWidth(200);
	    		GooglePathField.setPrefHeight(30);
	    		GooglePathField.setId("OptionEntryBoxL");
	    		GooglePathField.setPromptText("请获取谷歌安装路径...");
	    		GooglePathField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		GooglePathField.setBackground(Background.EMPTY);
	    		GooglePathField.setBorder(Border.EMPTY);
	    		GooglePathField.setEditable(false);
	    		
	    		fileChooser.getExtensionFilters().addAll(
		    	    	new FileChooser.ExtensionFilter("All Images", "*.*"),
		    	    	new FileChooser.ExtensionFilter("EXE", "*.exe") 
		             );

	    		GooglePathButton.setLayoutX(310);
	    		GooglePathButton.setLayoutY(62);
	    	    GooglePathButton.setPrefWidth(31);
	    	    GooglePathButton.setPrefHeight(30);
	    	    GooglePathButton.setId("ListViewButton");
	    		GooglePathButton.setOnAction(new EventHandler<ActionEvent>() {
	    			public void handle(ActionEvent event) {
	    				try{
	                		fileChooser.setTitle("请选择谷歌浏览器安装路径的EXE启动程序");
		                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\AppData\\Local\\Google\\Chrome\\Application\\"));                 
		                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
		                	if (file != null) {
		                		String GooglePath = file.getPath();
		                		GooglePathField.setText(GooglePath);
		                    }
		                } catch (Exception e) {
		                	fileChooser.setTitle("请选择谷歌浏览器安装路径的EXE启动程序");
		                	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"\\AppData\\Local\\"));                 
		                	File file = fileChooser.showOpenDialog(HomePageInterfaceStage);
		                	if (file != null) {
		                		String GooglePath = file.getPath();
		                		GooglePathField.setText(GooglePath);
		                    }
	                	}
                    }   	
	    	    });

	    		MobileIPField.setLayoutX(110);
	    		MobileIPField.setLayoutY(104);
	    		MobileIPField.setPrefWidth(230);
	    		MobileIPField.setPrefHeight(30);
	    		MobileIPField.setId("InputBoxL");
	    		MobileIPField.setPromptText("请填写手机IP地址...");
	    		MobileIPField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		MobileIPField.setBackground(Background.EMPTY);
	    		MobileIPField.setBorder(Border.EMPTY);
	    		
	    		MobileUDIDField.setLayoutX(110);
	    		MobileUDIDField.setLayoutY(148);
	    		MobileUDIDField.setPrefWidth(230);
	    		MobileUDIDField.setPrefHeight(30);
	    		MobileUDIDField.setId("InputBoxL");
	    		MobileUDIDField.setPromptText("请填写手机UDID...");
	    		MobileUDIDField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		MobileUDIDField.setBackground(Background.EMPTY);
	    		MobileUDIDField.setBorder(Border.EMPTY);

	    		ComputerIPField.setLayoutX(110);
	    		ComputerIPField.setLayoutY(192);
	    		ComputerIPField.setPrefWidth(230);
	    		ComputerIPField.setPrefHeight(30);
	    		ComputerIPField.setId("InputBoxL");
	    		ComputerIPField.setPromptText("请填写电脑IP地址...");
	    		ComputerIPField.setPrefColumnCount(10);//设置文本最大显示内容长度
	    		ComputerIPField.setBackground(Background.EMPTY);
	    		ComputerIPField.setBorder(Border.EMPTY);
	    		ComputerIPField.setText(ip);
	    		
	    		StartRemoteButton.setLayoutX(50);
	    		StartRemoteButton.setLayoutY(260);
	    		StartRemoteButton.setPrefWidth(108);
	    		StartRemoteButton.setPrefHeight(33);
	    		StartRemoteButton.setId("StartRemoteButton");
	    		StartRemoteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	String GooglePath =GooglePathField.getText();
	    	  	    	String MobileIP =MobileIPField.getText();
	    	  	    	String MobileUDID =MobileUDIDField.getText();
	    	  	    	String ComputerIP =ComputerIPField.getText();
	    			    if(StringUtil.isEmpty(GooglePath)){
							Dialog.SetMessageDialog("Warning","请获取谷歌安装路径！");
						}else if(StringUtil.isEmpty(MobileIP)){
							Dialog.SetMessageDialog("Warning","请填写手机IP地址！");
						}else if(StringUtil.isEmpty(MobileUDID)){
							Dialog.SetMessageDialog("Warning","请填写手机UDID！");
						}else if(StringUtil.isEmpty(ComputerIP)){
							Dialog.SetMessageDialog("Warning","请填写电脑IP地址！");
						}else{
							if(ID==1){
								Timer timer = new Timer();
								timer.schedule(new TimerTask() {
									@Override
									public void run() {
										String StartRemoteURL = "cmd /c start "+GooglePath+" http://"+ComputerIP+":8000/devices/"+MobileUDID+"/remote";
						    			try {
						    				DevicesCenterPane.setVisible(false);
											Command("StartService",StartRemoteURL);
										} catch (IOException | InterruptedException e) {
											e.printStackTrace();
										} 
									}
								}, 300);
							}else if(ID==2){
								Timer timer = new Timer();
								timer.schedule(new TimerTask() {
									@Override
									public void run() {
										String RemoteMonitoringURL = "cmd /c start "+GooglePath+" http://"+MobileIP+":7912/remote";
						    			try {
						    				DevicesCenterPane.setVisible(false);
						    				Command("StartService",RemoteMonitoringURL); 
										} catch (IOException | InterruptedException e) {
											e.printStackTrace();
										} 
									}
								}, 300);
							}else if(ID==3){
								Timer timer = new Timer();
								timer.schedule(new TimerTask() {
									@Override
									public void run() {
										String GetElementsURL = "cmd /c start "+GooglePath+" http://"+ComputerIP+":17310/";
						    			try {
						    				DevicesCenterPane.setVisible(false);
						    				Command("StartService",GetElementsURL); 
										} catch (IOException | InterruptedException e) {
											e.printStackTrace();
										} 
									}
								}, 300);
							}
						}
	    	  	    }
	    	  	});
	    		
	    		CloseWindowButton.setLayoutX(201);
	    		CloseWindowButton.setLayoutY(260);
	    		CloseWindowButton.setPrefWidth(108);
	    		CloseWindowButton.setPrefHeight(33);
	    		CloseWindowButton.setId("CloseWindowButton");
	    		CloseWindowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	DevicesCenterPane.setVisible(false);
	    	  	    }
	    	  	});
	    		
	    	    Rectangle DevicesRectangle = new Rectangle();
	    	    DevicesRectangle.setWidth(360);
	    	    DevicesRectangle.setHeight(330);
	    	    DevicesRectangle.setArcHeight(10);
	    	    DevicesRectangle.setArcWidth(10);
	    	    DevicesCenterPane.setClip(DevicesRectangle);
	    	    
	    	    DevicesCenterPane.getChildren().add(DevicesMinCloseHox);
	    	    DevicesCenterPane.getChildren().add(GooglePathField);
	    	    DevicesCenterPane.getChildren().add(GooglePathButton);
	    	    DevicesCenterPane.getChildren().add(MobileIPField);	
	    	    DevicesCenterPane.getChildren().add(MobileUDIDField);	
	    	    DevicesCenterPane.getChildren().add(ComputerIPField);	
	    	    DevicesCenterPane.getChildren().add(StartRemoteButton);	
	    	    DevicesCenterPane.getChildren().add(CloseWindowButton);	

        		RemoteControlButton.setLayoutX(702);
        		RemoteControlButton.setLayoutY(782);
        		RemoteControlButton.setPrefWidth(137);
        		RemoteControlButton.setPrefHeight(42);
        		RemoteControlButton.setId("RemoteControlButton");
        		RemoteControlButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	DevicesCenterPane.setVisible(true);
	    	  	    	ID=1;
	    	  	    }
	    	  	});
        		
        		RemoteMonitoringButton.setLayoutX(863);
        		RemoteMonitoringButton.setLayoutY(782);
        		RemoteMonitoringButton.setPrefWidth(137);
        		RemoteMonitoringButton.setPrefHeight(42);
        		RemoteMonitoringButton.setId("RemoteMonitoringButton");
        		RemoteMonitoringButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	DevicesCenterPane.setVisible(true);
	    	  	    	ID=2;
	    	  	    }
	    	  	});
        		
        		GetElementsButton.setLayoutX(1025);
        		GetElementsButton.setLayoutY(782);
        		GetElementsButton.setPrefWidth(137);
        		GetElementsButton.setPrefHeight(42);
        		GetElementsButton.setId("GetElementsButton");
        		GetElementsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	Platform.runLater(new Runnable() {
							@Override
   		    			    public void run() {
    						   Dialog.SetMessageDialog("Warning","请安装Python，并pip install --pre weditor！");
   		    			    }   
   		    			});
	    	  	    	Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
							@Override
	        				public void run() {
								DevicesCenterPane.setVisible(true);
			    	  	    	ID=3;
	        				}
	        			}, 3000);
	    	  	    }
	    	  	});
        		
	    	    ATXEquipmentClusterCenterPane.getChildren().add(BackgroundWebView);
	    	    ATXEquipmentClusterCenterPane.getChildren().add(StartServiceButton);
	    	    ATXEquipmentClusterCenterPane.getChildren().add(StopServiceButton);
	    	    ATXEquipmentClusterCenterPane.getChildren().add(GetDevicesButton);
	    	    ATXEquipmentClusterCenterPane.getChildren().add(DevicesCenterPane);
	    	    ATXEquipmentClusterCenterPane.getChildren().add(RemoteControlButton);
	    	    ATXEquipmentClusterCenterPane.getChildren().add(RemoteMonitoringButton);
	    	    ATXEquipmentClusterCenterPane.getChildren().add(GetElementsButton);
	    	    
        	    HomePagePane.getChildren().add(ATXEquipmentClusterCenterPane);
//        	    HomePageView.HomePagePane.getChildren().add(ATXEquipmentClusterCenterPane);
        }

    	public static void getATXEquipmentClusterCenterPane(boolean show) {
         	ATXEquipmentClusterCenterPane.setVisible(show);
     	}
    
		private static  int Command(String name,String cmd) throws IOException, InterruptedException{
			int i=0;
		    try {  
		    	System.out.println("开始执行Cmd命令："+cmd);
	        	Process pr = Runtime.getRuntime().exec(cmd);
	            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "utf-8"));
	            String line = input.readLine();
	            if(name=="StartService"){
	            	if(line!=null){
	            		while(line!=null){
	            			System.out.println(line);
	            			line = input.readLine();
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
		
		public static String GetIP(){  
			String IP =null;
			Enumeration<NetworkInterface> netInterfaces;
			try {
				// 拿到所有网卡
				netInterfaces = NetworkInterface.getNetworkInterfaces();
				InetAddress ip;
				// 遍历每个网卡，拿到ip
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = netInterfaces.nextElement();
					Enumeration<InetAddress> addresses = ni.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = addresses.nextElement();
//						if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(':') == -1) {
////							System.out.println(ni.getName() + " " + ip.getHostAddress());
//							if(ni.getName().equals("eth1")){
//								IP =ip.getHostAddress();
////								System.out.println(IP);
//								break;
//							}
//						}
						if (ip != null && ip instanceof Inet4Address){  
		                    if(ip.getHostAddress().equals("127.0.0.1")){
		                        continue;
		                    }
		                    IP=ip.getHostAddress();
//		                    System.out.println("IP = " + IP);
		                    return IP;
		                } 
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
					    Dialog.SetMessageDialog("Warning","请检测本地网络连接是否正常！");
	    			}      
	    		});
			}
			return IP;		
		} 
		
        public static void main(String[] args) {
        	Locale.setDefault(Locale.US);
	        launch(args);
        }
}