package AutomationTestSystem.View;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemDatabaseConfiguration;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Config.UserInformationConfiguration;
import AutomationTestSystem.Config.UserSaveData;
import AutomationTestSystem.Config.UserSaveDataBox;
import AutomationTestSystem.Controller.LoginController;
import AutomationTestSystem.Util.DragUtil;

@SuppressWarnings({"restriction", "static-access","unused" })
public class HomePageView extends Application {

		public  static Stage HomePageInterfaceStage;
//    	HomePageView.HomePageInterfaceStage.close();

		static AnchorPane HomePagePane = new AnchorPane();

		private DropShadow dropShadow = new DropShadow();

        private ImageView HeadImageView = new ImageView();
        private Label UserNameLabel = new Label();
        private Label UserTypeLabel = new Label();
        private Button SwitchButton = new Button();
        private Button SystemMasterInterfaceButton = new Button();
        private Button FrontEndFunctionCenterButton = new Button();
        private Button BackendFunctionCenterButton = new Button();
        private Button WEBAutomationCenterButton = new Button();
        private Button APIAutomationCenterButton = new Button();
        private Button APPAutomationCenterButton = new Button();
        private Button PerformanceAutomationCenterButton = new Button();
        
        private WebView VideoPlayerWebView = new WebView();
        private Button VideoPlayerButton =new Button();
        private AnchorPane HomePageRightPane = new AnchorPane();

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
                HomePageInterface("18688888888","qq123456");
//				FrontEndFunctionCenter("18688888888","qq123456");
//             FrontEndFunctionCenterPageView.FrontEndFunctionCenter("18688888888","qq123456");
        }

        public void HomePageInterface(String Account,String PassWord) throws Exception {
//        	   FrontEndFunctionCenter(Account, PassWord);
        	   FrontEndFunctionCenterPageView.FrontEndFunctionCenter();
        	   WEBAutomationCenterPageView.WEBAutomationCenter();
        	   APIAutomationCenterPageView.APIAutomationCenter();
        	   APPAutomationCenterPageView.APPAutomationCenter();
        	   PerformanceAutomationCenterPageView.PerformanceAutomationCenter();
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
	    		VersionNumberLabel.setText("Version 1.1.2");
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

	    		UserSaveData usd = usdb.get(Account);
	    		Image HeadIamge = new Image(usd.getHead(), 50,50,true, false);
	    		HeadImageView.setLayoutX(20);
	    		HeadImageView.setLayoutY(10);
//	    		String avatar = this.getClass().getResource("/image/LoginPane/HeadPortraitImage/HeadPortrait1.gif").toExternalForm();
//	    		Image HeadIamge1 = new Image(avatar, 50,50,true, false);
	    		HeadImageView.setImage(HeadIamge);
	    		HeadImageView.setClip(HeadRectangle);

	    		UserNameLabel.setLayoutX(79);
	    		UserNameLabel.setLayoutY(12);
	    		UserNameLabel.setId("UserNameLabel");
//	    		Object[] UserLoginInfo =LoginController.WebUserLoginInfo(Account, PassWord);
//      		    String UserName = (String) UserLoginInfo[0];
//	    		UserNameLabel.setText(UserName);
	    		UserSaveData usd1 = usdb.get(Account);
	    		String UserName = usd1.getPassWord();
	    		UserNameLabel.setText(UserName);
//	    		UserNameLabel.setText("小    智");//无验证

	    		UserTypeLabel.setLayoutX(80);
	    		UserTypeLabel.setLayoutY(38);
	    		UserTypeLabel.setId("UserTypeLabel");
//      		    String UserType =String.valueOf((int) UserLoginInfo[2]) ;
//      		    if("1".equals(UserType)){
//    		    	UserTypeLabel.setText("游   客");
//    		    }if("2".equals(UserType)){
//    		    	UserTypeLabel.setText("旅 行 社");
//    		    }
      		    UserTypeLabel.setText("体 验 者");//无验证
	    		
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
	    		SystemMasterInterfaceButton.setId("SystemMasterInterfaceButtonDown");
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
	    		    	FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane.setVisible(false);
//	    		    	FrontEndFunctionCenterPageView.getFrontEndFunctionCenterPane(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
	    		    	WEBAutomationCenterPageView.WEBAutomationCenterPane.setVisible(false);
	    		    	APIAutomationCenterPageView.APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPageView.APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane.setVisible(false);
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
	    		    	FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane.setVisible(true);
//	    		    	FrontEndFunctionCenterPageView.getFrontEndFunctionCenterPane(true);
//	    		    	BackendFunctionCenterPane.setVisible(false);
	    		    	WEBAutomationCenterPageView.WEBAutomationCenterPane.setVisible(false);
	    		    	APIAutomationCenterPageView.APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPageView.APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane.setVisible(false);
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
	    		    	FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(true);
	    		    	WEBAutomationCenterPageView.WEBAutomationCenterPane.setVisible(false);
	    		    	APIAutomationCenterPageView.APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPageView.APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane.setVisible(false);
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
	    		    	FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
	    		    	WEBAutomationCenterPageView.WEBAutomationCenterPane.setVisible(true);
	    		    	APIAutomationCenterPageView.APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPageView.APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane.setVisible(false);
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
	    		    	FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
	    		    	WEBAutomationCenterPageView.WEBAutomationCenterPane.setVisible(false);
	    		    	APIAutomationCenterPageView.APIAutomationCenterPane.setVisible(true);
	    		    	APPAutomationCenterPageView.APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane.setVisible(false);
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
	    		    	FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
	    		    	WEBAutomationCenterPageView.WEBAutomationCenterPane.setVisible(false);
	    		    	APIAutomationCenterPageView.APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPageView.APPAutomationCenterPane.setVisible(true);
	    		    	PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane.setVisible(false);
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
	    		    	FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
	    		    	WEBAutomationCenterPageView.WEBAutomationCenterPane.setVisible(false);
	    		    	APIAutomationCenterPageView.APIAutomationCenterPane.setVisible(false);
	    		    	APPAutomationCenterPageView.APPAutomationCenterPane.setVisible(false);
	    		    	PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane.setVisible(true);
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
	    		HomePagePane.getChildren().add(FrontEndFunctionCenterPageView.FrontEndFunctionCenterPane);
	    		HomePagePane.getChildren().add(WEBAutomationCenterPageView.WEBAutomationCenterPane);
	    		HomePagePane.getChildren().add(APIAutomationCenterPageView.APIAutomationCenterPane);
	    		HomePagePane.getChildren().add(APPAutomationCenterPageView.APPAutomationCenterPane);
	    		HomePagePane.getChildren().add(PerformanceAutomationCenterPageView.PerformanceAutomationCenterPane);
	    		
	        	Rectangle HomePageRectangle = new Rectangle();
	            HomePageRectangle.setWidth(1600);
	        	HomePageRectangle.setHeight(1000);
	        	HomePageRectangle.setArcHeight(10);
	            HomePageRectangle.setArcWidth(10);

	        	HomePageStack.getChildren().addAll(HomePagePane);
	        	HomePageStack.setClip(HomePageRectangle);

	    		//拖动监听器
	    		DragUtil.addDragListener(HomePageInterfaceStage,HomePageStack);
//	    		DragUtil.addDragListener(HomePageInterfaceStage,APPAutomationCenterPageView.DevicesCenterPane);
	    		
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

    	public void AgainLogin(String Account,String PassWord) throws Exception {
    		UserSaveData usd = usdb.get(Account);
    		Image HeadIamge = new Image(usd.getHead(), 50,50,true, false);
    		HeadImageView.setImage(HeadIamge);
//    		Object[] UserLoginInfo =LoginController.WebUserLoginInfo(Account, PassWord);
//    		String UserName = (String) UserLoginInfo[0];
//    		UserNameLabel.setText(UserName);
    		UserNameLabel.setText("小    智");//无验证
    		if(UserNameLabel.getText().length()>10){
    			SwitchButton.setLayoutX(165);
	    		SwitchButton.setLayoutY(39);
    		}else{
    		SwitchButton.setLayoutX(165);
    		SwitchButton.setLayoutY(22);
    		}
//    		String UserType =String.valueOf((int) UserLoginInfo[2]) ;
//    		if("1".equals(UserType)){
//    		   UserTypeLabel.setText("游 客 组");
//    		}
    		UserTypeLabel.setText("体 验 者");//无验证
    		FrontEndFunctionCenterPageView.Account = Account;
    		FrontEndFunctionCenterPageView.PassWord = PassWord;
    		HomePageInterfaceStage.show();
    	}
    	
        public static void main(String[] args) {
        	Locale.setDefault(Locale.US);
	        launch(args);
    }
}