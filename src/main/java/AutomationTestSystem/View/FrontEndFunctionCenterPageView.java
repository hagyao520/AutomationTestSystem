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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
import AutomationTestSystem.Controller.FrontEndFunctionCenterPageController;
import AutomationTestSystem.Controller.LoginController;
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.DragUtil;
import AutomationTestSystem.Util.StringUtil;

@SuppressWarnings({ "rawtypes", "restriction", "static-access","unused","unchecked"})
public class FrontEndFunctionCenterPageView extends Application {

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

        private static WebView VideoPlayerWebView = new WebView();
        private static Button VideoPlayerButton =new Button();
        private static AnchorPane HomePageRightPane = new AnchorPane();

        static AnchorPane FrontEndFunctionCenterPane = new AnchorPane();
        private static TextField TouristAttractionsField = new TextField();
        private static Button TouristAttractionsButton = new Button();
        private static TextField SightsTicketingField = new TextField();
        private static Button SightsTicketingButton = new Button();
        static int SightsTicketingSerialNumber = 1;
        private static TextField TicketTypeField = new TextField();
        private static Button TicketTypeButton = new Button();
        static int TicketTypeNumber = 0;
        private static TextField TrialCrowdField = new TextField();
        private static Button TrialCrowdButton = new Button();
        static int TrialCrowdNumber = 0;
//        private static TextField TravelDateField = new TextField();
//        private static Button TravelDateButton = new Button();
        private static DatePicker checkInDatePicker = new DatePicker();
        private static DatePicker checkOutDatePicker = new DatePicker();
        private static final String pattern = "yyyy-MM-dd";
        static LocalDate TravelDate;
        private static TextField StockDayField = new TextField();
        private static Button StockDayButton = new Button();
        private static TextField PurchaseQuantityField = new TextField();
//        private static Button PurchaseQuantityButton = new Button();

        private static TextField GoingOneNameField = new TextField();
        private static Button GoingOneNameButton = new Button();
        private static TextField GoingOnePhoneField = new TextField();
        private static TextField GoingOneIDCardField = new TextField();
        private static TextField GoingOneEmailField = new TextField();

        private static TextField GoingTwoNameField = new TextField();
        private static Button GoingTwoNameButton = new Button();
        private static TextField GoingTwoPhoneField = new TextField();
        private static TextField GoingTwoIDCardField = new TextField();
        private static TextField GoingTwoEmailField = new TextField();

        private static TextField GoingThreeNameField = new TextField();
        private static Button GoingThreeNameButton = new Button();
        private static TextField GoingThreePhoneField = new TextField();
        private static TextField GoingThreeIDCardField = new TextField();
        private static TextField GoingThreeEmailField = new TextField();

        private static TextField GoingFourNameField = new TextField();
        private static Button GoingFourNameButton = new Button();
        private static TextField GoingFourPhoneField = new TextField();
        private static TextField GoingFourIDCardField = new TextField();
        private static TextField GoingFourEmailField = new TextField();

        private static TextField GoingFiveNameField = new TextField();
        private static Button GoingFiveNameButton = new Button();
        private static TextField GoingFivePhoneField = new TextField();
        private static TextField GoingFiveIDCardField = new TextField();
        private static TextField GoingFiveEmailField = new TextField();

        private static TextField TicketHolderNameField = new TextField();
        private static Button TicketHolderNameButton = new Button();
        static int TicketHolderNameNumber;
        private static TextField TicketHolderPhoneField = new TextField();
        private static TextField TicketHolderEmailField = new TextField();

        private static TextField OrderNumberField = new TextField();

        private static Button OneCreateOrderButton = new Button();
        private static Button OnePaymentOrderButton = new Button();
        private static Button OneWriteOffOrderButton = new Button();
        private static Button OneEvaluateOrderButton = new Button();

        CopyOnWriteArraySet<EventHandler<ActionEvent>> closeActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();
        CopyOnWriteArraySet<EventHandler<ActionEvent>> iconifiedActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();

        SystemDatabaseConfiguration sdcd = (SystemDatabaseConfiguration) ConfigManage.get(SystemDatabaseConfiguration.path, SystemDatabaseConfiguration.class);
        SystemServerConfiguration ssc = (SystemServerConfiguration) ConfigManage.get(SystemServerConfiguration.path, SystemServerConfiguration.class);

        UserInformationConfiguration uic = (UserInformationConfiguration) ConfigManage.get(UserInformationConfiguration.path, UserInformationConfiguration.class);
        UserSaveDataBox usdb = (UserSaveDataBox) ConfigManage.get(UserSaveDataBox.path, UserSaveDataBox.class);

    	int SystemSettingStageStatus = 0;
//    	static String Account = LoginPageView.AccountField.getText();
//    	static String PassWord = LoginPageView.PassWordField.getText();
    	static String Account = "18688888888";
    	static String PassWord = "qq123456";
    	
		@Override
        public void start(Stage HomePageInterfaceStage) throws Exception {
                this.HomePageInterfaceStage = HomePageInterfaceStage;
                //?????????????????????
                this.HomePageInterfaceStage.setTitle("HomePageInterface");
                //?????????????????????
//                this.HomePageInterfaceStage.getIcons().add(new Image(getClass().getResourceAsStream("images/address_book_32.png")));//??????images???????????????
                this.HomePageInterfaceStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//??????resource???images????????????
                //????????????????????????
                this.HomePageInterfaceStage.initStyle(StageStyle.TRANSPARENT);

                //???????????????
                HomePageInterface();
//                HomePageInterface("18688888888","qq123456");
//				FrontEndFunctionCenter("18688888888","qq123456");
        }

        public void HomePageInterface() throws Exception {
        	   FrontEndFunctionCenter();
//        	   Stage HomePageInterfaceStage = new Stage();
        	   HomePageInterfaceStage = new Stage();
        	   HomePageInterfaceStage.initStyle(StageStyle.TRANSPARENT);
        	   HomePageInterfaceStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//??????resource???images????????????

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
	    		MinCloseHox.setPickOnBounds(false);//?????????????????????????????????????????????????????????
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
	    		Object[] UserLoginInfo =LoginController.WebUserLoginInfo(Account, PassWord);
      		    String UserName = (String) UserLoginInfo[0];
	    		UserNameLabel.setText(UserName);
//	    		UserNameLabel.setText("???  ???");
	    		
	    		UserTypeLabel.setLayoutX(80);
	    		UserTypeLabel.setLayoutY(38);
	    		UserTypeLabel.setId("UserTypeLabel");
      		    String UserType =String.valueOf((int) UserLoginInfo[2]) ;
      		    if("1".equals(UserType)){
      		    	UserTypeLabel.setText("???   ???");
      		    }if("2".equals(UserType)){
      		    	UserTypeLabel.setText("??? ??? ???");
      		    }
//      		    UserTypeLabel.setText("??? ??? ???");
	    		
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
	    		    	HomePageRightPane.setVisible(true);
	    		    	FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
	    		    }
	    		});

	    		FrontEndFunctionCenterButton.setLayoutX(0);
	    		FrontEndFunctionCenterButton.setLayoutY(115+50);
	    		FrontEndFunctionCenterButton.setPrefWidth(219);
	    		FrontEndFunctionCenterButton.setPrefHeight(50);
	    		FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButtonDown");
	    		FrontEndFunctionCenterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    		    	SystemMasterInterfaceButton.setId("SystemMasterInterfaceButton");
	    		    	FrontEndFunctionCenterButton.setId("FrontEndFunctionCenterButtonDown");
	    		    	BackendFunctionCenterButton.setId("BackendFunctionCenterButton");
	    		    	WEBAutomationCenterButton.setId("WEBAutomationCenterButton");
	    		    	APIAutomationCenterButton.setId("APIAutomationCenterButton");
	    		    	APPAutomationCenterButton.setId("APPAutomationCenterButton");
	    		    	HomePageRightPane.setVisible(false);
	    		    	FrontEndFunctionCenterPane.setVisible(true);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
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
//	    		    	HomePageRightPane.setVisible(false);
	    		    	FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(true);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
	    		    	
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
	    		    	HomePageRightPane.setVisible(false);
	    		    	FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(true);
//	    		    	APIAutomationCenterPane.setVisible(false);
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
	    		    	HomePageRightPane.setVisible(false);
	    		    	FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(true);
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
	    		    	HomePageRightPane.setVisible(false);
	    		    	FrontEndFunctionCenterPane.setVisible(false);
//	    		    	BackendFunctionCenterPane.setVisible(false);
//	    		    	WEBAutomationCenterPane.setVisible(false);
//	    		    	APIAutomationCenterPane.setVisible(false);
//	    		    	APPAutomationCenterPane.setVisible(true);
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
//	    		VideoPlayerIamgeView.setEffect(dropShadow);//??????????????????
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
//	    		HomePagePane.getChildren().add(HomePageRightPane);
	    		FrontEndFunctionCenterPane.setVisible(true);
	    		
	        	Rectangle HomePageRectangle = new Rectangle();
	            HomePageRectangle.setWidth(1600);
	        	HomePageRectangle.setHeight(1000);
	        	HomePageRectangle.setArcHeight(10);
	            HomePageRectangle.setArcWidth(10);

	        	HomePageStack.getChildren().addAll(HomePagePane);
	        	HomePageStack.setClip(HomePageRectangle);

	    		//???????????????
	    		DragUtil.addDragListener(HomePageInterfaceStage,HomePageStack);

	    		//??????????????????Scene,??????????????????????????????
	    		Scene scene = new Scene(HomePageStack);
	    		//Scene??????
	    		scene.setFill(null);

	    		//scene???????????????Stage???
	            HomePageInterfaceStage.setScene(scene);
	            //????????????????????????????????????
	            HomePageInterfaceStage.centerOnScreen();
	            //????????????
	            HomePageInterfaceStage.show();
        }

		public static void FrontEndFunctionCenter() throws Exception{
        	    FrontEndFunctionCenterPane.setLayoutX(222);
        	    FrontEndFunctionCenterPane.setLayoutY(115);
        	    FrontEndFunctionCenterPane.setPrefWidth(1381);
        	    FrontEndFunctionCenterPane.setPrefHeight(886);
        	    FrontEndFunctionCenterPane.setId("FrontEndFunctionCenterPane");
        	    FrontEndFunctionCenterPane.setVisible(false);

        	    TouristAttractionsField.setLayoutX(105);
        	    TouristAttractionsField.setLayoutY(12);
        	    TouristAttractionsField.setPrefWidth(200);
        	    TouristAttractionsField.setPrefHeight(30);
        	    TouristAttractionsField.setId("OptionEntryBoxL");
        	    TouristAttractionsField.setPromptText("?????????????????????...");
        	    TouristAttractionsField.setPrefColumnCount(10);//????????????????????????????????????
//        	    TouristAttractionsField.getStyleClass().remove("text-field");
        	    TouristAttractionsField.setBackground(Background.EMPTY);
        	    TouristAttractionsField.setBorder(Border.EMPTY);
        	    TouristAttractionsField.setText("???????????????");
        	    
//        	    String ScenicSpotsAll =FrontEndFunctionCenterPageController.getAllScenicSpots();
//        		String[] ScenicSpots = ScenicSpotsAll.split(",");
//        	    for (int i = 0; i < ScenicSpots.length; i++) {
////        			System.out.print(ScenicSpots[i]+"   ");
//        			data.addAll(ScenicSpots[i]);
//        		}
//        	    String[] SpotIdSpot ={"????????????????????????","??????????????????","???????????????","????????????","????????????","???????????????","???????????????"};
//        	    data.addAll(SpotIdSpot);
//	    	    TouristAttractionsListView.setLayoutX(105);
//        	    TouristAttractionsListView.setLayoutY(12+30);
//        	    TouristAttractionsListView.setPrefSize(230, 150);
//	    	    TouristAttractionsListView.setItems(data);
//	    	    TouristAttractionsListView.setEditable(true);
//	    	    TouristAttractionsListView.setVisible(false);
//	    	    TouristAttractionsListView.setId("ListView");
//	    	    TouristAttractionsListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
//        	    	TouristAttractionsField.setText(new_val);
//        	    	TouristAttractionsListView.setVisible(false);
//	  	        });

        	    TouristAttractionsButton.setLayoutX(305);
        	    TouristAttractionsButton.setLayoutY(12);
        	    TouristAttractionsButton.setPrefWidth(31);
        	    TouristAttractionsButton.setPrefHeight(30);
        	    TouristAttractionsButton.setId("ListViewButton");
        	    TouristAttractionsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		ObservableList<String> data = FXCollections.observableArrayList();
	  	    	  		    ListView<String> TouristAttractionsListView = new ListView<String>(data);
	  	    	  		    String ScenicSpotsAll =FrontEndFunctionCenterPageController.getAllScenicSpots();
	  	            		String[] ScenicSpots = ScenicSpotsAll.split(",");
	  	            	    for (int i = 0; i < ScenicSpots.length; i++) {
//	  	            			System.out.print(ScenicSpots[i]+"   ");
	  	            			data.addAll(ScenicSpots[i]);
	  	            		}
//	  	    	  	    	String[] SpotIdSpot ={"????????????????????????","??????????????????","???????????????","????????????","????????????","???????????????","???????????????"};
//	  	            	    data.addAll(SpotIdSpot);
	  	            	    TouristAttractionsListView.setItems(data);
	  	    	    	    TouristAttractionsListView.setLayoutX(105);
	  	    	    	    TouristAttractionsListView.setLayoutY(12+30);
	  	    	    	    TouristAttractionsListView.setPrefSize(230, 150);
	  	    	    	    TouristAttractionsListView.setEditable(true);
	  	    	    	    TouristAttractionsListView.setId("ListView");
	  	    	    	    TouristAttractionsListView.toFront();//??????????????????????????????
	  	    	    	    TouristAttractionsListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
	  	            	    	TouristAttractionsField.setText(new_val);
	  	            	    	TouristAttractionsListView.setVisible(false);
	  	    	  	        });
	  	    	    	    FrontEndFunctionCenterPane.getChildren().add(TouristAttractionsListView);
	    	    	    } catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    SightsTicketingField.setLayoutX(449);
	    	    SightsTicketingField.setLayoutY(12);
	    	    SightsTicketingField.setPrefWidth(200);
	    	    SightsTicketingField.setPrefHeight(30);
	    	    SightsTicketingField.setId("OptionEntryBoxL");
	    	    SightsTicketingField.setPromptText("?????????????????????...");
	    	    SightsTicketingField.setPrefColumnCount(10);//????????????????????????????????????
	    	    SightsTicketingField.setBackground(Background.EMPTY);
	    	    SightsTicketingField.setBorder(Border.EMPTY);
	    	    SightsTicketingField.setText("???????????????????????????");
	    	    
	    	    SightsTicketingButton.setLayoutX(649);
	    	    SightsTicketingButton.setLayoutY(12);
	    	    SightsTicketingButton.setPrefWidth(31);
	    	    SightsTicketingButton.setPrefHeight(30);
	    	    SightsTicketingButton.setId("ListViewButton");
	    	    SightsTicketingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		if(StringUtil.isNotEmpty(TouristAttractionsField.getText())){
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> SightsTicketingListView = new ListView<String>(data);
		  	    	  	    	String ScenicSpots_NameAll =FrontEndFunctionCenterPageController.getSpotTicket(Account,PassWord,TouristAttractionsField.getText());
		  	            		String[] ScenicSpotsName = ScenicSpots_NameAll.split(",");
		  	            	    for (int i = 0; i < ScenicSpotsName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(ScenicSpotsName[i]);
		  	            		}
//		  	            	    String[] ScenicSpotsName ={"???????????????????????????","????????????????????????+????????????"};
//		  	            	    data.addAll(ScenicSpotsName);
		  	            	    SightsTicketingListView.setItems(data);
		  	            	    SightsTicketingListView.setLayoutX(449);
		  	            	    SightsTicketingListView.setLayoutY(12+30);
		  	            	    SightsTicketingListView.setPrefSize(230, 150);
		  	            	    SightsTicketingListView.setEditable(true);
		  	            	    SightsTicketingListView.setId("ListView");
		  	            	    SightsTicketingListView.toFront();//??????????????????????????????
		  	            	    SightsTicketingListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		      	  	        		SightsTicketingField.setText(new_val);
		      	  	        		SightsTicketingListView.setVisible(false);
		      	  	        	    SightsTicketingSerialNumber = SightsTicketingListView.getSelectionModel().getSelectedIndex();
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(SightsTicketingListView);
	      	  	        	}else{
	      	  	        	       Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
	  	    	  		    }
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    TicketTypeField.setLayoutX(105);
	    	    TicketTypeField.setLayoutY(58);
	    	    TicketTypeField.setPrefWidth(200);
	    	    TicketTypeField.setPrefHeight(30);
	    	    TicketTypeField.setId("OptionEntryBoxL");
	    	    TicketTypeField.setPromptText("?????????????????????...");
	    	    TicketTypeField.setPrefColumnCount(10);//????????????????????????????????????
	    	    TicketTypeField.setBackground(Background.EMPTY);
	    	    TicketTypeField.setBorder(Border.EMPTY);
	    	    TicketTypeField.setText("?????????");
	    	    
	    	    TicketTypeButton.setLayoutX(305);
	    	    TicketTypeButton.setLayoutY(58);
	    	    TicketTypeButton.setPrefWidth(31);
	    	    TicketTypeButton.setPrefHeight(30);
	    	    TicketTypeButton.setId("ListViewButton");
	    	    TicketTypeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		if(StringUtil.isNotEmpty(SightsTicketingField.getText())){
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> TicketTypeListView = new ListView<String>(data);
		  	    	  		    String SpotName = TouristAttractionsField.getText();
		  	    	  	    	String TicketType_NameAll =FrontEndFunctionCenterPageController.getTicketType(Account,PassWord,SpotName,SightsTicketingSerialNumber,1);
		  	            		String[] TicketTypeName = TicketType_NameAll.split(",");
		  	            	    for (int i = 0; i < TicketTypeName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(TicketTypeName[i]);
		  	            		}
//		  	            	    String[] TicketTypeName ={"?????????","?????????"};
//		  	            	    data.addAll(TicketTypeName);
		  	            	    TicketTypeListView.setItems(data);
		  	            	    TicketTypeListView.setLayoutX(105);
		  	            	    TicketTypeListView.setLayoutY(58+30);
		  	            	    TicketTypeListView.setPrefSize(230, 150);
		  	            	    TicketTypeListView.setEditable(true);
		  	            	    TicketTypeListView.setId("ListView");
		  	            	    TicketTypeListView.toFront();//??????????????????????????????
		  	            	    TicketTypeListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	            	    	TicketTypeField.setText(new_val);
		      	  	        	    TicketTypeListView.setVisible(false);
		      	  	        	    TicketTypeNumber = TicketTypeListView.getSelectionModel().getSelectedIndex();
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(TicketTypeListView);
	      	  	        	}else{
	      	  	        	       Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
	  	    	  		    }
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    TrialCrowdField.setLayoutX(448);
	    	    TrialCrowdField.setLayoutY(58);
	    	    TrialCrowdField.setPrefWidth(200);
	    	    TrialCrowdField.setPrefHeight(30);
	    	    TrialCrowdField.setId("OptionEntryBoxL");
	    	    TrialCrowdField.setPromptText("?????????????????????...");
	    	    TrialCrowdField.setPrefColumnCount(10);//????????????????????????????????????
	    	    TrialCrowdField.setBackground(Background.EMPTY);
	    	    TrialCrowdField.setBorder(Border.EMPTY);
	    	    TrialCrowdField.setText("??????");
	    	    
	    	    TrialCrowdButton.setLayoutX(648);
	    	    TrialCrowdButton.setLayoutY(58);
	    	    TrialCrowdButton.setPrefWidth(31);
	    	    TrialCrowdButton.setPrefHeight(30);
	    	    TrialCrowdButton.setId("ListViewButton");
	    	    TrialCrowdButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		if(StringUtil.isNotEmpty(SightsTicketingField.getText())){
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> TrialCrowdListView = new ListView<String>(data);
		  	    	  		    String SpotName = TouristAttractionsField.getText();
		  	    	  	    	String TrialCrowd_NameAll =FrontEndFunctionCenterPageController.getTicketType(Account,PassWord,SpotName,SightsTicketingSerialNumber,2);
		  	            		String[] TrialCrowdName = TrialCrowd_NameAll.split(",");
		  	            	    for (int i = 0; i < TrialCrowdName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(TrialCrowdName[i]);
		  	            		}
//		  	            	    String[] TrialCrowdName ={"??????","??????"};
//		  	            	    data.addAll(TrialCrowdName);
		  	            	    TrialCrowdListView.setItems(data);
		  	            	    TrialCrowdListView.setLayoutX(448);
		  	            	    TrialCrowdListView.setLayoutY(58+30);
		  	                	TrialCrowdListView.setPrefSize(230, 150);
		  	                	TrialCrowdListView.setEditable(true);
		  	                	TrialCrowdListView.setId("ListView");
		  	                	TrialCrowdListView.toFront();//??????????????????????????????
		  	                	TrialCrowdListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	            	    	TrialCrowdField.setText(new_val);
		  	            	    	TrialCrowdListView.setVisible(false);
		  	            	    	TrialCrowdNumber = TrialCrowdListView.getSelectionModel().getSelectedIndex();
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(TrialCrowdListView);
	      	  	        	}else{
	      	  	        	       Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
	  	    	  		    }
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

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
	    	    checkOutDatePicker.setLayoutX(105);
	    	    checkOutDatePicker.setLayoutY(100);
	    	    checkOutDatePicker.setPrefWidth(150);
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
	    	                                checkInDatePicker.getValue().plusDays(1))
	    	                            ) {
	    	                                setDisable(true);
	    	                                setStyle("-fx-background-color: #ffc0cb;");
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
	    	    checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));//?????????????????????

	    	    StockDayField.setLayoutX(368);
	    	    StockDayField.setLayoutY(101);
	    	    StockDayField.setPrefWidth(70);
	    	    StockDayField.setPrefHeight(30);
	    	    StockDayField.setId("InputBoxM");
	    	    StockDayField.setPromptText("?????????...");
	    	    StockDayField.setPrefColumnCount(10);//????????????????????????????????????
	    	    StockDayField.setBackground(Background.EMPTY);
	    	    StockDayField.setBorder(Border.EMPTY);
	    	    
	    	    StockDayButton.setLayoutX(438);
	    	    StockDayButton.setLayoutY(101);
	    	    StockDayButton.setPrefWidth(31);
	    	    StockDayButton.setPrefHeight(30);
	    	    StockDayButton.setId("ListViewButton");
	    	    StockDayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		String SpotName = TouristAttractionsField.getText();
  	    	  		        TravelDate = checkOutDatePicker.getValue();
	    	  	    		if(StringUtil.isEmpty(TouristAttractionsField.getText())){
		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
		    	  	    	}else if(StringUtil.isEmpty(SightsTicketingField.getText())){
		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
		    	  	    	}else if(StringUtil.isEmpty(TicketTypeField.getText())){
		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
		    	  	    	}else if(StringUtil.isEmpty(TrialCrowdField.getText())){
		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
		    	  	    	}else if(TravelDate==null){
		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
	  	    	  		    }else{
		  	    	  	    	int goods_stock =FrontEndFunctionCenterPageController.getStockDay(Account,PassWord,SpotName,SightsTicketingSerialNumber,TicketTypeNumber,TrialCrowdNumber,TravelDate);
		  	    	  	        StockDayField.setText(String.valueOf(goods_stock));
	  	    	  		    }
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});
	    	    
	    	    PurchaseQuantityField.setLayoutX(579);
	    	    PurchaseQuantityField.setLayoutY(101);
	    	    PurchaseQuantityField.setPrefWidth(100);
	    	    PurchaseQuantityField.setPrefHeight(30);
	    	    PurchaseQuantityField.setId("InputBoxM");
	    	    PurchaseQuantityField.setPromptText("???????????????...");
	    	    PurchaseQuantityField.setPrefColumnCount(10);//????????????????????????????????????
	    	    PurchaseQuantityField.setBackground(Background.EMPTY);
	    	    PurchaseQuantityField.setBorder(Border.EMPTY);
	    	    PurchaseQuantityField.setText("1");
	    	    PurchaseQuantityField.textProperty().addListener(new ChangeListener<String>() {
	                @Override
	                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	                	if("1".equals(PurchaseQuantityField.getText())){
	                		GoingOneNameField.setText("??????");
	                		GoingOnePhoneField.setText("13333333333");
	                		GoingOneIDCardField.setText("110101199003074856");
	                		GoingOneEmailField.setText("333@qq.com");
	                	}else if("2".equals(PurchaseQuantityField.getText())){
	                		GoingOneNameField.setText("??????");
	                		GoingOnePhoneField.setText("13333333333");
	                		GoingOneIDCardField.setText("110101199003074856");
	                		GoingOneEmailField.setText("333@qq.com");
	                		GoingTwoNameField.setText("??????");
	                		GoingTwoPhoneField.setText("13444444444");
	                		GoingTwoIDCardField.setText("513427197309217969");
	                		GoingTwoEmailField.setText("4444@qq.com");
	                	}else if("3".equals(PurchaseQuantityField.getText())){
	                		GoingOneNameField.setText("??????");
	                		GoingOnePhoneField.setText("13333333333");
	                		GoingOneIDCardField.setText("110101199003074856");
	                		GoingOneEmailField.setText("333@qq.com");
	                		GoingTwoNameField.setText("??????");
	                		GoingTwoPhoneField.setText("13444444444");
	                		GoingTwoIDCardField.setText("513427197309217969");
	                		GoingTwoEmailField.setText("4444@qq.com");
	                		GoingThreeNameField.setText("??????");
	                		GoingThreePhoneField.setText("13555555555");
	                		GoingThreeIDCardField.setText("522636200012291968");
	                		GoingThreeEmailField.setText("55555@qq.com");
	                	}else if("4".equals(PurchaseQuantityField.getText())){
	                		GoingOneNameField.setText("??????");
	                		GoingOnePhoneField.setText("13333333333");
	                		GoingOneIDCardField.setText("110101199003074856");
	                		GoingOneEmailField.setText("333@qq.com");
	                		GoingTwoNameField.setText("??????");
	                		GoingTwoPhoneField.setText("13444444444");
	                		GoingTwoIDCardField.setText("513427197309217969");
	                		GoingTwoEmailField.setText("4444@qq.com");
	                		GoingThreeNameField.setText("??????");
	                		GoingThreePhoneField.setText("13555555555");
	                		GoingThreeIDCardField.setText("522636200012291968");
	                		GoingThreeEmailField.setText("55555@qq.com");
	                		GoingFourNameField.setText("?????????");
	                		GoingFourPhoneField.setText("13888888888");
	                		GoingFourIDCardField.setText("131121199504126511");
	                		GoingFourEmailField.setText("8888@qq.com");
	                	}else if("5".equals(PurchaseQuantityField.getText())){
	                		GoingOneNameField.setText("??????");
	                		GoingOnePhoneField.setText("13333333333");
	                		GoingOneIDCardField.setText("110101199003074856");
	                		GoingOneEmailField.setText("333@qq.com");
	                		GoingTwoNameField.setText("??????");
	                		GoingTwoPhoneField.setText("13444444444");
	                		GoingTwoIDCardField.setText("513427197309217969");
	                		GoingTwoEmailField.setText("4444@qq.com");
	                		GoingThreeNameField.setText("??????");
	                		GoingThreePhoneField.setText("13555555555");
	                		GoingThreeIDCardField.setText("522636200012291968");
	                		GoingThreeEmailField.setText("55555@qq.com");
	                		GoingFourNameField.setText("?????????");
	                		GoingFourPhoneField.setText("13888888888");
	                		GoingFourIDCardField.setText("131121199504126511");
	                		GoingFourEmailField.setText("8888@qq.com");
	                		GoingFiveNameField.setText("?????????");
	                		GoingFivePhoneField.setText("13999999999");
	                		GoingFiveIDCardField.setText("130525199111029426");
	                		GoingFiveEmailField.setText("999@qq.com");
	                	}else{
	                		TouristAttractionsField.setText("");
	                		SightsTicketingField.setText("");
	                		TicketTypeField.setText("");
	                		TrialCrowdField.setText("");
	                		GoingOneNameField.setText("");
	                		GoingOnePhoneField.setText("");
	                		GoingOneIDCardField.setText("");
	                		GoingOneEmailField.setText("");
	                		GoingTwoNameField.setText("");
	                		GoingTwoPhoneField.setText("");
	                		GoingTwoIDCardField.setText("");
	                		GoingTwoEmailField.setText("");
	                		GoingThreeNameField.setText("");
	                		GoingThreePhoneField.setText("");
	                		GoingThreeIDCardField.setText("");
	                		GoingThreeEmailField.setText("");
	                		GoingFourNameField.setText("");
	                		GoingFourPhoneField.setText("");
	                		GoingFourIDCardField.setText("");
	                		GoingFourEmailField.setText("");
	                		GoingFiveNameField.setText("");
	                		GoingFivePhoneField.setText("");
	                		GoingFiveIDCardField.setText("");
	                		GoingFiveEmailField.setText("");
	                		TicketHolderNameField.setText("");
	                		TicketHolderPhoneField.setText("");
	                		TicketHolderEmailField.setText("");
	                	}
	                }
	            });
	    	    
	    	    GoingOneNameField.setLayoutX(162);
	    	    GoingOneNameField.setLayoutY(145);
	    	    GoingOneNameField.setPrefWidth(144);
	    	    GoingOneNameField.setPrefHeight(30);
	    	    GoingOneNameField.setId("OptionEntryBoxS");
	    	    GoingOneNameField.setPromptText("???????????????...");
	    	    GoingOneNameField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingOneNameField.setBackground(Background.EMPTY);
	    	    GoingOneNameField.setBorder(Border.EMPTY);
	    	    GoingOneNameField.setText("??????");
	    	    
	    	    GoingOneNameButton.setLayoutX(162+144);
	    	    GoingOneNameButton.setLayoutY(145);
	    	    GoingOneNameButton.setPrefWidth(31);
	    	    GoingOneNameButton.setPrefHeight(30);
	    	    GoingOneNameButton.setId("ListViewButton");
	    	    GoingOneNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	 try{
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> GoingOneNameListView = new ListView<String>(data);
		  	    	  	    	String UserGetTing_NameAll =FrontEndFunctionCenterPageController.getUserGetTingAll(Account,PassWord);
		  	            		String[] UserGetTingName = UserGetTing_NameAll.split(",");
		  	            	    for (int i = 0; i < UserGetTingName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(UserGetTingName[i]);
		  	            		}
//		  	            	    String[] UserGetTingName ={"??????","??????","??????","????????? "};
//		  	            	    data.addAll(UserGetTingName);
		  	            	    GoingOneNameListView.setItems(data);
		  	                	GoingOneNameListView.setLayoutX(162);
		  	                	GoingOneNameListView.setLayoutY(145+30);
		  	               	    GoingOneNameListView.setPrefSize(174, 150);
		  	               	    GoingOneNameListView.setEditable(true);
		  	                	GoingOneNameListView.setId("ListView");
		  	            	    GoingOneNameListView.toFront();//??????????????????????????????
		  	            	    GoingOneNameListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	                	    GoingOneNameField.setText(new_val);
		  	                	    int GoingOneNameNumber = GoingOneNameListView.getSelectionModel().getSelectedIndex();
			  	            		Object[] UserGetTingInfo =FrontEndFunctionCenterPageController.getUserGetTing(Account,PassWord,GoingOneNameNumber);
			  	            		String mobile = (String) UserGetTingInfo[0];
			  	            		String sfz_code = (String) UserGetTingInfo[1];
			  	            		String email = (String) UserGetTingInfo[2];
		  	                	    GoingOnePhoneField.setText(mobile);
		  	                	    GoingOneIDCardField.setText(sfz_code);
		  	                	    GoingOneEmailField.setText(email);
		  	                	    GoingOneNameListView.setVisible(false);
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(GoingOneNameListView);
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    GoingOnePhoneField.setLayoutX(506);
	    	    GoingOnePhoneField.setLayoutY(145);
	    	    GoingOnePhoneField.setPrefWidth(174);
	    	    GoingOnePhoneField.setPrefHeight(30);
	    	    GoingOnePhoneField.setId("InputBoxS");
	    	    GoingOnePhoneField.setPromptText("??????????????????...");
	    	    GoingOnePhoneField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingOnePhoneField.setBackground(Background.EMPTY);
	    	    GoingOnePhoneField.setBorder(Border.EMPTY);
	    	    GoingOnePhoneField.setText("13333333333");
        		
	    	    GoingOneIDCardField.setLayoutX(162);
	    	    GoingOneIDCardField.setLayoutY(190);
	    	    GoingOneIDCardField.setPrefWidth(174);
	    	    GoingOneIDCardField.setPrefHeight(30);
	    	    GoingOneIDCardField.setId("InputBoxS");
	    	    GoingOneIDCardField.setPromptText("??????????????????...");
	    	    GoingOneIDCardField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingOneIDCardField.setBackground(Background.EMPTY);
	    	    GoingOneIDCardField.setBorder(Border.EMPTY);
	    	    GoingOneIDCardField.setText("110101199003074856");

	    	    GoingOneEmailField.setLayoutX(506);
	    	    GoingOneEmailField.setLayoutY(190);
	    	    GoingOneEmailField.setPrefWidth(174);
	    	    GoingOneEmailField.setPrefHeight(30);
	    	    GoingOneEmailField.setId("InputBoxS");
	    	    GoingOneEmailField.setPromptText("??????????????????...");
	    	    GoingOneEmailField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingOneEmailField.setBackground(Background.EMPTY);
	    	    GoingOneEmailField.setBorder(Border.EMPTY);
	    	    GoingOneEmailField.setText("333@qq.com");
	    	    
	    	    GoingTwoNameField.setLayoutX(162);
	    	    GoingTwoNameField.setLayoutY(234);
	    	    GoingTwoNameField.setPrefWidth(144);
	    	    GoingTwoNameField.setPrefHeight(30);
	    	    GoingTwoNameField.setId("OptionEntryBoxS");
	    	    GoingTwoNameField.setPromptText("???????????????...");
	    	    GoingTwoNameField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingTwoNameField.setBackground(Background.EMPTY);
	    	    GoingTwoNameField.setBorder(Border.EMPTY);

	    	    GoingTwoNameButton.setLayoutX(162+144);
	    	    GoingTwoNameButton.setLayoutY(234);
	    	    GoingTwoNameButton.setPrefWidth(31);
	    	    GoingTwoNameButton.setPrefHeight(30);
	    	    GoingTwoNameButton.setId("ListViewButton");
	    	    GoingTwoNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> GoingTwoNameListView = new ListView<String>(data);
		  	    	  	    	String UserGetTing_NameAll =FrontEndFunctionCenterPageController.getUserGetTingAll(Account,PassWord);
		  	            		String[] UserGetTingName = UserGetTing_NameAll.split(",");
		  	            	    for (int i = 0; i < UserGetTingName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(UserGetTingName[i]);
		  	            		}
//		  	            	    String[] UserGetTingName ={"??????","??????","??????","????????? "};
//		  	            	    data.addAll(UserGetTingName);
		  	            	    GoingTwoNameListView.setItems(data);
		  	                	GoingTwoNameListView.setLayoutX(162);
		  	                	GoingTwoNameListView.setLayoutY(234+30);
		  	               	    GoingTwoNameListView.setPrefSize(174, 150);
		  	               	    GoingTwoNameListView.setEditable(true);
		  	                	GoingTwoNameListView.setId("ListView");
		  	            	    GoingTwoNameListView.toFront();//??????????????????????????????
		  	            	    GoingTwoNameListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	                	    GoingTwoNameField.setText(new_val);
		  	                	    int GoingTwoNameNumber = GoingTwoNameListView.getSelectionModel().getSelectedIndex();
			  	            		Object[] UserGetTingInfo =FrontEndFunctionCenterPageController.getUserGetTing(Account,PassWord,GoingTwoNameNumber);
			  	            		String mobile = (String) UserGetTingInfo[0];
			  	            		String sfz_code = (String) UserGetTingInfo[1];
			  	            		String email = (String) UserGetTingInfo[2];
		  	                	    GoingTwoPhoneField.setText(mobile);
		  	                	    GoingTwoIDCardField.setText(sfz_code);
		  	                	    GoingTwoEmailField.setText(email);
		  	                	    GoingTwoNameListView.setVisible(false);
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(GoingTwoNameListView);
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    GoingTwoPhoneField.setLayoutX(506);
	    	    GoingTwoPhoneField.setLayoutY(232);
	    	    GoingTwoPhoneField.setPrefWidth(174);
	    	    GoingTwoPhoneField.setPrefHeight(30);
	    	    GoingTwoPhoneField.setId("InputBoxS");
	    	    GoingTwoPhoneField.setPromptText("??????????????????...");
	    	    GoingTwoPhoneField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingTwoPhoneField.setBackground(Background.EMPTY);
	    	    GoingTwoPhoneField.setBorder(Border.EMPTY);

	    	    GoingTwoIDCardField.setLayoutX(162);
	    	    GoingTwoIDCardField.setLayoutY(279);
	    	    GoingTwoIDCardField.setPrefWidth(174);
	    	    GoingTwoIDCardField.setPrefHeight(30);
	    	    GoingTwoIDCardField.setId("InputBoxS");
	    	    GoingTwoIDCardField.setPromptText("??????????????????...");
	    	    GoingTwoIDCardField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingTwoIDCardField.setBackground(Background.EMPTY);
	    	    GoingTwoIDCardField.setBorder(Border.EMPTY);

	    	    GoingTwoEmailField.setLayoutX(506);
	    	    GoingTwoEmailField.setLayoutY(279);
	    	    GoingTwoEmailField.setPrefWidth(174);
	    	    GoingTwoEmailField.setPrefHeight(30);
	    	    GoingTwoEmailField.setId("InputBoxS");
	    	    GoingTwoEmailField.setPromptText("??????????????????...");
	    	    GoingTwoEmailField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingTwoEmailField.setBackground(Background.EMPTY);
	    	    GoingTwoEmailField.setBorder(Border.EMPTY);

	    	    GoingThreeNameField.setLayoutX(162);
	    	    GoingThreeNameField.setLayoutY(324);
	    	    GoingThreeNameField.setPrefWidth(144);
	    	    GoingThreeNameField.setPrefHeight(30);
	    	    GoingThreeNameField.setId("OptionEntryBoxS");
	    	    GoingThreeNameField.setPromptText("???????????????...");
	    	    GoingThreeNameField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingThreeNameField.setBackground(Background.EMPTY);
	    	    GoingThreeNameField.setBorder(Border.EMPTY);

	    	    GoingThreeNameButton.setLayoutX(162+144);
	    	    GoingThreeNameButton.setLayoutY(324);
	    	    GoingThreeNameButton.setPrefWidth(31);
	    	    GoingThreeNameButton.setPrefHeight(30);
	    	    GoingThreeNameButton.setId("ListViewButton");
	    	    GoingThreeNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	  try{
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> GoingThreeNameListView = new ListView<String>(data);
		  	    	  	    	String UserGetTing_NameAll =FrontEndFunctionCenterPageController.getUserGetTingAll(Account,PassWord);
		  	            		String[] UserGetTingName = UserGetTing_NameAll.split(",");
		  	            	    for (int i = 0; i < UserGetTingName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(UserGetTingName[i]);
		  	            		}
//		  	            	    String[] UserGetTingName ={"??????","??????","??????","????????? "};
//		  	            	    data.addAll(UserGetTingName);
		  	            	    GoingThreeNameListView.setItems(data);
		  	                	GoingThreeNameListView.setLayoutX(162);
		  	                	GoingThreeNameListView.setLayoutY(324+30);
		  	               	    GoingThreeNameListView.setPrefSize(174, 150);
		  	               	    GoingThreeNameListView.setEditable(true);
		  	                	GoingThreeNameListView.setId("ListView");
		  	            	    GoingThreeNameListView.toFront();//??????????????????????????????
		  	            	    GoingThreeNameListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	                	    GoingThreeNameField.setText(new_val);
		  	                	    int GoingThreeNameNumber = GoingThreeNameListView.getSelectionModel().getSelectedIndex();
			  	            		Object[] UserGetTingInfo =FrontEndFunctionCenterPageController.getUserGetTing(Account,PassWord,GoingThreeNameNumber);
			  	            		String mobile = (String) UserGetTingInfo[0];
			  	            		String sfz_code = (String) UserGetTingInfo[1];
			  	            		String email = (String) UserGetTingInfo[2];
		  	                	    GoingThreePhoneField.setText(mobile);
		  	                	    GoingThreeIDCardField.setText(sfz_code);
		  	                	    GoingThreeEmailField.setText(email);
		  	                	    GoingThreeNameListView.setVisible(false);
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(GoingThreeNameListView);
    	  	        	  } catch (Exception e) {
	    	  		 		  Platform.runLater(new Runnable() {
	    	  	   			      @Override
	    	  	   			      public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			      }
	    	  	   			  });
	    	  			  }
	    	  	    }
	    	  	});

	    	    GoingThreePhoneField.setLayoutX(506);
	    	    GoingThreePhoneField.setLayoutY(324);
	    	    GoingThreePhoneField.setPrefWidth(174);
	    	    GoingThreePhoneField.setPrefHeight(30);
	    	    GoingThreePhoneField.setId("InputBoxS");
	    	    GoingThreePhoneField.setPromptText("??????????????????...");
	    	    GoingThreePhoneField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingThreePhoneField.setBackground(Background.EMPTY);
	    	    GoingThreePhoneField.setBorder(Border.EMPTY);

	    	    GoingThreeIDCardField.setLayoutX(161);
	    	    GoingThreeIDCardField.setLayoutY(368);
	    	    GoingThreeIDCardField.setPrefWidth(174);
	    	    GoingThreeIDCardField.setPrefHeight(30);
	    	    GoingThreeIDCardField.setId("InputBoxS");
	    	    GoingThreeIDCardField.setPromptText("??????????????????...");
	    	    GoingThreeIDCardField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingThreeIDCardField.setBackground(Background.EMPTY);
	    	    GoingThreeIDCardField.setBorder(Border.EMPTY);

	    	    GoingThreeEmailField.setLayoutX(506);
	    	    GoingThreeEmailField.setLayoutY(368);
	    	    GoingThreeEmailField.setPrefWidth(174);
	    	    GoingThreeEmailField.setPrefHeight(30);
	    	    GoingThreeEmailField.setId("InputBoxS");
	    	    GoingThreeEmailField.setPromptText("??????????????????...");
	    	    GoingThreeEmailField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingThreeEmailField.setBackground(Background.EMPTY);
	    	    GoingThreeEmailField.setBorder(Border.EMPTY);

	    	    GoingFourNameField.setLayoutX(162);
	    	    GoingFourNameField.setLayoutY(411);
	    	    GoingFourNameField.setPrefWidth(144);
	    	    GoingFourNameField.setPrefHeight(30);
	    	    GoingFourNameField.setId("OptionEntryBoxS");
	    	    GoingFourNameField.setPromptText("???????????????...");
	    	    GoingFourNameField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFourNameField.setBackground(Background.EMPTY);
	    	    GoingFourNameField.setBorder(Border.EMPTY);

	    	    GoingFourNameButton.setLayoutX(162+144);
	    	    GoingFourNameButton.setLayoutY(411);
	    	    GoingFourNameButton.setPrefWidth(31);
	    	    GoingFourNameButton.setPrefHeight(30);
	    	    GoingFourNameButton.setId("ListViewButton");
	    	    GoingFourNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> GoingFourNameListView = new ListView<String>(data);
		  	    	  	    	String UserGetTing_NameAll =FrontEndFunctionCenterPageController.getUserGetTingAll(Account,PassWord);
		  	            		String[] UserGetTingName = UserGetTing_NameAll.split(",");
		  	            	    for (int i = 0; i < UserGetTingName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(UserGetTingName[i]);
		  	            		}
//		  	            	    String[] UserGetTingName ={"??????","??????","??????","????????? "};
//		  	            	    data.addAll(UserGetTingName);
		  	            	    GoingFourNameListView.setItems(data);
		  	                	GoingFourNameListView.setLayoutX(162);
		  	                	GoingFourNameListView.setLayoutY(411+30);
		  	               	    GoingFourNameListView.setPrefSize(174, 150);
		  	               	    GoingFourNameListView.setEditable(true);
		  	                	GoingFourNameListView.setId("ListView");
		  	            	    GoingFourNameListView.toFront();//??????????????????????????????
		  	            	    GoingFourNameListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	                	    GoingFourNameField.setText(new_val);
		  	                	    int GoingFourNameNumber = GoingFourNameListView.getSelectionModel().getSelectedIndex();
			  	            		Object[] UserGetTingInfo =FrontEndFunctionCenterPageController.getUserGetTing(Account,PassWord,GoingFourNameNumber);
			  	            		String mobile = (String) UserGetTingInfo[0];
			  	            		String sfz_code = (String) UserGetTingInfo[1];
			  	            		String email = (String) UserGetTingInfo[2];
		  	                	    GoingFourPhoneField.setText(mobile);
		  	                	    GoingFourIDCardField.setText(sfz_code);
		  	                	    GoingFourEmailField.setText(email);
		  	                	    GoingFourNameListView.setVisible(false);
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(GoingFourNameListView);
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    GoingFourPhoneField.setLayoutX(506);
	    	    GoingFourPhoneField.setLayoutY(411);
	    	    GoingFourPhoneField.setPrefWidth(174);
	    	    GoingFourPhoneField.setPrefHeight(30);
	    	    GoingFourPhoneField.setId("InputBoxS");
	    	    GoingFourPhoneField.setPromptText("??????????????????...");
	    	    GoingFourPhoneField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFourPhoneField.setBackground(Background.EMPTY);
	    	    GoingFourPhoneField.setBorder(Border.EMPTY);

	    	    GoingFourIDCardField.setLayoutX(161);
	    	    GoingFourIDCardField.setLayoutY(454);
	    	    GoingFourIDCardField.setPrefWidth(174);
	    	    GoingFourIDCardField.setPrefHeight(30);
	    	    GoingFourIDCardField.setId("InputBoxS");
	    	    GoingFourIDCardField.setPromptText("??????????????????...");
	    	    GoingFourIDCardField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFourIDCardField.setBackground(Background.EMPTY);
	    	    GoingFourIDCardField.setBorder(Border.EMPTY);

	    	    GoingFourEmailField.setLayoutX(506);
	    	    GoingFourEmailField.setLayoutY(454);
	    	    GoingFourEmailField.setPrefWidth(174);
	    	    GoingFourEmailField.setPrefHeight(30);
	    	    GoingFourEmailField.setId("InputBoxS");
	    	    GoingFourEmailField.setPromptText("??????????????????...");
	    	    GoingFourEmailField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFourEmailField.setBackground(Background.EMPTY);
	    	    GoingFourEmailField.setBorder(Border.EMPTY);

	    	    GoingFiveNameField.setLayoutX(162);
	    	    GoingFiveNameField.setLayoutY(497);
	    	    GoingFiveNameField.setPrefWidth(144);
	    	    GoingFiveNameField.setPrefHeight(30);
	    	    GoingFiveNameField.setId("OptionEntryBoxS");
	    	    GoingFiveNameField.setPromptText("???????????????...");
	    	    GoingFiveNameField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFiveNameField.setBackground(Background.EMPTY);
	    	    GoingFiveNameField.setBorder(Border.EMPTY);

	    	    GoingFiveNameButton.setLayoutX(162+144);
	    	    GoingFiveNameButton.setLayoutY(497);
	    	    GoingFiveNameButton.setPrefWidth(31);
	    	    GoingFiveNameButton.setPrefHeight(30);
	    	    GoingFiveNameButton.setId("ListViewButton");
	    	    GoingFiveNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> GoingFiveNameListView = new ListView<String>(data);
		  	    	  	    	String UserGetTing_NameAll =FrontEndFunctionCenterPageController.getUserGetTingAll(Account,PassWord);
		  	            		String[] UserGetTingName = UserGetTing_NameAll.split(",");
		  	            	    for (int i = 0; i < UserGetTingName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(UserGetTingName[i]);
		  	            		}
//		  	            	    String[] UserGetTingName ={"??????","??????","??????","????????? "};
//		  	            	    data.addAll(UserGetTingName);
		  	            	    GoingFiveNameListView.setItems(data);
		  	                	GoingFiveNameListView.setLayoutX(162);
		  	                	GoingFiveNameListView.setLayoutY(497+30);
		  	               	    GoingFiveNameListView.setPrefSize(174, 150);
		  	               	    GoingFiveNameListView.setEditable(true);
		  	                	GoingFiveNameListView.setId("ListView");
		  	            	    GoingFiveNameListView.toFront();//??????????????????????????????
		  	            	    GoingFiveNameListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	                	    GoingFiveNameField.setText(new_val);
		  	                	    int GoingFiveNameNumber = GoingFiveNameListView.getSelectionModel().getSelectedIndex();
			  	            		Object[] UserGetTingInfo =FrontEndFunctionCenterPageController.getUserGetTing(Account,PassWord,GoingFiveNameNumber);
			  	            		String mobile = (String) UserGetTingInfo[0];
			  	            		String sfz_code = (String) UserGetTingInfo[1];
			  	            		String email = (String) UserGetTingInfo[2];
		  	                	    GoingFivePhoneField.setText(mobile);
		  	                	    GoingFiveIDCardField.setText(sfz_code);
		  	                	    GoingFiveEmailField.setText(email);
		  	                	    GoingFiveNameListView.setVisible(false);
		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(GoingFiveNameListView);
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    GoingFivePhoneField.setLayoutX(506);
	    	    GoingFivePhoneField.setLayoutY(497);
	    	    GoingFivePhoneField.setPrefWidth(174);
	    	    GoingFivePhoneField.setPrefHeight(30);
	    	    GoingFivePhoneField.setId("InputBoxS");
	    	    GoingFivePhoneField.setPromptText("??????????????????...");
	    	    GoingFivePhoneField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFivePhoneField.setBackground(Background.EMPTY);
	    	    GoingFivePhoneField.setBorder(Border.EMPTY);

	    	    GoingFiveIDCardField.setLayoutX(161);
	    	    GoingFiveIDCardField.setLayoutY(542);
	    	    GoingFiveIDCardField.setPrefWidth(174);
	    	    GoingFiveIDCardField.setPrefHeight(30);
	    	    GoingFiveIDCardField.setId("InputBoxS");
	    	    GoingFiveIDCardField.setPromptText("??????????????????...");
	    	    GoingFiveIDCardField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFiveIDCardField.setBackground(Background.EMPTY);
	    	    GoingFiveIDCardField.setBorder(Border.EMPTY);

	    	    GoingFiveEmailField.setLayoutX(506);
	    	    GoingFiveEmailField.setLayoutY(542);
	    	    GoingFiveEmailField.setPrefWidth(174);
	    	    GoingFiveEmailField.setPrefHeight(30);
	    	    GoingFiveEmailField.setId("InputBoxS");
	    	    GoingFiveEmailField.setPromptText("??????????????????...");
	    	    GoingFiveEmailField.setPrefColumnCount(10);//????????????????????????????????????
	    	    GoingFiveEmailField.setBackground(Background.EMPTY);
	    	    GoingFiveEmailField.setBorder(Border.EMPTY);

	    	    TicketHolderNameField.setLayoutX(162);
	    	    TicketHolderNameField.setLayoutY(587);
	    	    TicketHolderNameField.setPrefWidth(144);
	    	    TicketHolderNameField.setPrefHeight(30);
	    	    TicketHolderNameField.setId("OptionEntryBoxS");
	    	    TicketHolderNameField.setPromptText("???????????????...");
	    	    TicketHolderNameField.setPrefColumnCount(10);//????????????????????????????????????
	    	    TicketHolderNameField.setBackground(Background.EMPTY);
	    	    TicketHolderNameField.setBorder(Border.EMPTY);
	    	    TicketHolderNameField.setText("??????");
        		
	    	    TicketHolderNameButton.setLayoutX(162+144);
	    	    TicketHolderNameButton.setLayoutY(587);
	    	    TicketHolderNameButton.setPrefWidth(31);
	    	    TicketHolderNameButton.setPrefHeight(30);
	    	    TicketHolderNameButton.setId("ListViewButton");
	    	    TicketHolderNameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	try{
	    	  	    		    ObservableList<String> data = FXCollections.observableArrayList();
		  	    	  		    ListView<String> TicketHolderNameListView = new ListView<String>(data);
		  	    	  	    	String UserFetchWrit_NameAll =FrontEndFunctionCenterPageController.getUserFetchWritAll(Account,PassWord);
		  	            		String[] UserFetchWritName = UserFetchWrit_NameAll.split(",");
		  	            	    for (int i = 0; i < UserFetchWritName.length; i++) {
//		  	            			System.out.print(ScenicSpotsName[i]+"   ");
		  	            			data.addAll(UserFetchWritName[i]);
		  	            		}
//		  	            	    String[] UserGetTingName ={"??????","??????","??????","????????? "};
//		  	            	    data.addAll(UserGetTingName);
		  	            	    TicketHolderNameListView.setItems(data);
		  	            	    TicketHolderNameListView.setLayoutX(162);
		  	            	    TicketHolderNameListView.setLayoutY(587+30);
		  	            	    TicketHolderNameListView.setPrefSize(174, 150);
		  	            	    TicketHolderNameListView.setEditable(true);
		  	            	    TicketHolderNameListView.setId("ListView");
		  	            	    TicketHolderNameListView.toFront();//??????????????????????????????
		  	            	    TicketHolderNameListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
		  	            	    	TicketHolderNameField.setText(new_val);
			  	            		TicketHolderNameNumber = TicketHolderNameListView.getSelectionModel().getSelectedIndex();
			  	            		Object[] UserFetchWritInfo =FrontEndFunctionCenterPageController.getUserFetchWrit(Account,PassWord,TicketHolderNameNumber);
			  	            		String mobile = (String) UserFetchWritInfo[0];
			  	            		String email = (String) UserFetchWritInfo[1];
			  	            		TicketHolderPhoneField.setText(mobile);
			  	            		TicketHolderEmailField.setText(email);
			  	            		TicketHolderNameListView.setVisible(false);

		      		  	        });
		      	  	        	FrontEndFunctionCenterPane.getChildren().add(TicketHolderNameListView);
    	  	        	} catch (Exception e) {
	    	  		 		Platform.runLater(new Runnable() {
	    	  	   			    @Override
	    	  	   			    public void run() {
	    	  	   	    			Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
	    	  	   			    }
	    	  	   			});
	    	  			}
	    	  	    }
	    	  	});

	    	    TicketHolderPhoneField.setLayoutX(506);
	    	    TicketHolderPhoneField.setLayoutY(587);
	    	    TicketHolderPhoneField.setPrefWidth(174);
	    	    TicketHolderPhoneField.setPrefHeight(30);
	    	    TicketHolderPhoneField.setId("InputBoxS");
	    	    TicketHolderPhoneField.setPromptText("??????????????????...");
	    	    TicketHolderPhoneField.setPrefColumnCount(10);//????????????????????????????????????
	    	    TicketHolderPhoneField.setBackground(Background.EMPTY);
	    	    TicketHolderPhoneField.setBorder(Border.EMPTY);
	    	    TicketHolderPhoneField.setText("13333333333");
        		
        		
	    	    TicketHolderEmailField.setLayoutX(161);
	    	    TicketHolderEmailField.setLayoutY(631);
	    	    TicketHolderEmailField.setPrefWidth(174);
	    	    TicketHolderEmailField.setPrefHeight(30);
	    	    TicketHolderEmailField.setId("InputBoxS");
	    	    TicketHolderEmailField.setPromptText("??????????????????...");
	    	    TicketHolderEmailField.setPrefColumnCount(10);//????????????????????????????????????
	    	    TicketHolderEmailField.setBackground(Background.EMPTY);
	    	    TicketHolderEmailField.setBorder(Border.EMPTY);
	    	    TicketHolderEmailField.setText("333@qq.com");
	    	    
	    	    OrderNumberField.setLayoutX(506);
	    	    OrderNumberField.setLayoutY(631);
	    	    OrderNumberField.setPrefWidth(174);
	    	    OrderNumberField.setPrefHeight(30);
	    	    OrderNumberField.setId("InputBoxS");
	    	    OrderNumberField.setPromptText("??????????????????...");
	    	    OrderNumberField.setPrefColumnCount(10);//????????????????????????????????????
	    	    OrderNumberField.setBackground(Background.EMPTY);
	    	    OrderNumberField.setBorder(Border.EMPTY);

	    	    OneCreateOrderButton.setLayoutX(17);
	    	    OneCreateOrderButton.setLayoutY(748);
	    	    OneCreateOrderButton.setPrefWidth(137);
	    	    OneCreateOrderButton.setPrefHeight(42);
	    	    OneCreateOrderButton.setId("OneCreateOrderButton");
	    	    OneCreateOrderButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	Dialog.SetMessageDialog("Success","?????????????????????");
//	    	  	    	try{
//	    	  	    		String SpotName = TouristAttractionsField.getText();
//		    	  	    	String PurchaseQuantity =PurchaseQuantityField.getText();
//		    	  	        TravelDate = checkOutDatePicker.getValue();
//		    	  	    	String GoingOneName = GoingOneNameField.getText();
//		    	  	    	String GoingOnePhone = GoingOnePhoneField.getText();
//		    	  	    	String GoingOneIDCard = GoingOneIDCardField.getText();
//		    	  	    	String GoingOneEmail = GoingOneEmailField.getText();
//		    	  	    	String GoingTwoName = GoingTwoNameField.getText();
//		    	  	    	String GoingTwoPhone = GoingTwoPhoneField.getText();
//		    	  	    	String GoingTwoIDCard = GoingTwoIDCardField.getText();
//		    	  	    	String GoingTwoEmail = GoingTwoEmailField.getText();
//		    	  	    	String GoingThreeName = GoingThreeNameField.getText();
//		    	  	    	String GoingThreePhone = GoingThreePhoneField.getText();
//		    	  	    	String GoingThreeIDCard = GoingThreeIDCardField.getText();
//		    	  	    	String GoingThreeEmail = GoingThreeEmailField.getText();
//		    	  	    	String GoingFourName = GoingFourNameField.getText();
//		    	  	    	String GoingFourPhone = GoingFourPhoneField.getText();
//		    	  	    	String GoingFourIDCard = GoingFourIDCardField.getText();
//		    	  	    	String GoingFourEmail = GoingFourEmailField.getText();
//		    	  	    	String GoingFiveName = GoingFiveNameField.getText();
//		    	  	    	String GoingFivePhone = GoingFivePhoneField.getText();
//		    	  	    	String GoingFiveIDCard = GoingFiveIDCardField.getText();
//		    	  	    	String GoingFiveEmail = GoingFiveEmailField.getText();
//		    	  	    	String TicketHolderName = TicketHolderNameField.getText();
//		    	  	    	String TicketHolderPhone = TicketHolderPhoneField.getText();
//		    	  	    	String TicketHolderEmail = TicketHolderEmailField.getText();
//		    	  	    	String phone = TicketHolderPhoneField.getText();
//		    	  	    	if(StringUtil.isEmpty(TouristAttractionsField.getText())){
//		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
//		    	  	    	}else if(StringUtil.isEmpty(SightsTicketingField.getText())){
//		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
//		    	  	    	}else if(StringUtil.isEmpty(TicketTypeField.getText())){
//		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
//		    	  	    	}else if(StringUtil.isEmpty(TrialCrowdField.getText())){
//		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
//		    	  	    	}else if(TravelDate==null){
//		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????");
//		    	  	    	}else if(TravelDate!=null){
//		    	  	    		int goods_stock =FrontEndFunctionCenterPageController.getStockDay(Account,PassWord,SpotName,SightsTicketingSerialNumber,TicketTypeNumber,TrialCrowdNumber,TravelDate);
//		  	    	  	        StockDayField.setText(String.valueOf(goods_stock));
//		    	  	    	}if(StringUtil.isEmpty(StockDayField.getText())||Integer.parseInt(StockDayField.getText())<1){
//		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????1????????????????????????");;
//		    	  	    	}else if(StringUtil.isEmpty(PurchaseQuantityField.getText())||Integer.parseInt(PurchaseQuantityField.getText())<1){
//		    	  	    		Dialog.SetMessageDialog("Warning","????????????????????????1????????????????????????");;
//		    	  	    	}else if("1".equals(PurchaseQuantity)){
//		    	  	    		if(StringUtil.isEmpty(GoingOneNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOnePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else{
//			    	  	    		String OrderNumber =FrontEndFunctionCenterPageController.getOrderNumber(Account,PassWord,SpotName,SightsTicketingSerialNumber,TicketTypeNumber,TrialCrowdNumber,PurchaseQuantity,TravelDate,
//							    	  	    GoingOneName,GoingOneIDCard,GoingOnePhone,GoingOneEmail,
//					    	  	    		 GoingTwoName,GoingTwoIDCard,GoingTwoPhone,GoingTwoEmail,
//					    	  	    		 GoingThreeName,GoingThreeIDCard,GoingThreePhone,GoingThreeEmail,
//					    	  	    		 GoingFourName,GoingFourIDCard,GoingFourPhone,GoingFourEmail,
//					    	  	    		 GoingFiveName,GoingFiveIDCard,GoingFivePhone,GoingFiveEmail,
//					    	  	    	     TicketHolderName,TicketHolderPhone,TicketHolderEmail,phone,TicketHolderNameNumber);
//				    	  	    		     if(OrderNumber!=null){
//							    	  	    	   OrderNumberField.setText(OrderNumber);
//							    	  	    	   Dialog.SetMessageDialog("Success","????????????????????????????????????????????????");
//						    	  	         }else{
//						    	  	               Dialog.SetMessageDialog("Warning","???????????????????????????????????????");
//						    	  	         }
//					    	  	     }
//		    	  	    	}else if("2".equals(PurchaseQuantity)){
//		    	  	    		if(StringUtil.isEmpty(GoingOneNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOnePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else{
//			    	  	    		String OrderNumber =FrontEndFunctionCenterPageController.getOrderNumber(Account,PassWord,SpotName,SightsTicketingSerialNumber,TicketTypeNumber,TrialCrowdNumber,PurchaseQuantity,TravelDate,
//							    	  	    GoingOneName,GoingOneIDCard,GoingOnePhone,GoingOneEmail,
//					    	  	    		 GoingTwoName,GoingTwoIDCard,GoingTwoPhone,GoingTwoEmail,
//					    	  	    		 GoingThreeName,GoingThreeIDCard,GoingThreePhone,GoingThreeEmail,
//					    	  	    		 GoingFourName,GoingFourIDCard,GoingFourPhone,GoingFourEmail,
//					    	  	    		 GoingFiveName,GoingFiveIDCard,GoingFivePhone,GoingFiveEmail,
//					    	  	    	     TicketHolderName,TicketHolderPhone,TicketHolderEmail,phone,TicketHolderNameNumber);
//				    	  	    		     if(OrderNumber!=null){
//							    	  	    	   OrderNumberField.setText(OrderNumber);
//							    	  	    	   Dialog.SetMessageDialog("Success","????????????????????????????????????????????????");
//						    	  	         }else{
//						    	  	               Dialog.SetMessageDialog("Warning","???????????????????????????????????????");
//						    	  	         }
//					    	  	     }
//		    	  	    	}else if("3".equals(PurchaseQuantity)){
//		    	  	    		if(StringUtil.isEmpty(GoingOneNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOnePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else{
//			    	  	    		String OrderNumber =FrontEndFunctionCenterPageController.getOrderNumber(Account,PassWord,SpotName,SightsTicketingSerialNumber,TicketTypeNumber,TrialCrowdNumber,PurchaseQuantity,TravelDate,
//							    	  	    GoingOneName,GoingOneIDCard,GoingOnePhone,GoingOneEmail,
//					    	  	    		 GoingTwoName,GoingTwoIDCard,GoingTwoPhone,GoingTwoEmail,
//					    	  	    		 GoingThreeName,GoingThreeIDCard,GoingThreePhone,GoingThreeEmail,
//					    	  	    		 GoingFourName,GoingFourIDCard,GoingFourPhone,GoingFourEmail,
//					    	  	    		 GoingFiveName,GoingFiveIDCard,GoingFivePhone,GoingFiveEmail,
//					    	  	    	     TicketHolderName,TicketHolderPhone,TicketHolderEmail,phone,TicketHolderNameNumber);
//				    	  	    		     if(OrderNumber!=null){
//							    	  	    	   OrderNumberField.setText(OrderNumber);
//							    	  	    	   Dialog.SetMessageDialog("Success","????????????????????????????????????????????????");
//						    	  	         }else{
//						    	  	               Dialog.SetMessageDialog("Warning","???????????????????????????????????????");
//						    	  	         }
//					    	  	     }
//			    	  	    }else if("4".equals(PurchaseQuantity)){
//		    	  	    		if(StringUtil.isEmpty(GoingOneNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOnePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else{
//			    	  	    		String OrderNumber =FrontEndFunctionCenterPageController.getOrderNumber(Account,PassWord,SpotName,SightsTicketingSerialNumber,TicketTypeNumber,TrialCrowdNumber,PurchaseQuantity,TravelDate,
//							    	  	    GoingOneName,GoingOneIDCard,GoingOnePhone,GoingOneEmail,
//					    	  	    		 GoingTwoName,GoingTwoIDCard,GoingTwoPhone,GoingTwoEmail,
//					    	  	    		 GoingThreeName,GoingThreeIDCard,GoingThreePhone,GoingThreeEmail,
//					    	  	    		 GoingFourName,GoingFourIDCard,GoingFourPhone,GoingFourEmail,
//					    	  	    		 GoingFiveName,GoingFiveIDCard,GoingFivePhone,GoingFiveEmail,
//					    	  	    	     TicketHolderName,TicketHolderPhone,TicketHolderEmail,phone,TicketHolderNameNumber);
//				    	  	    		     if(OrderNumber!=null){
//							    	  	    	   OrderNumberField.setText(OrderNumber);
//							    	  	    	   Dialog.SetMessageDialog("Success","????????????????????????????????????????????????");
//						    	  	         }else{
//						    	  	               Dialog.SetMessageDialog("Warning","???????????????????????????????????????");
//						    	  	         }
//					    	  	     }
//			    	  	    }else if("5".equals(PurchaseQuantity)){
//		    	  	    		if(StringUtil.isEmpty(GoingOneNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOnePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingOneEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingTwoEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingThreeEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFourEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFiveNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFivePhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFiveIDCardField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(GoingFiveEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderNameField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderPhoneField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????");
//			    	  	    	}else if(StringUtil.isEmpty(TicketHolderEmailField.getText())){
//			    	  	    		Dialog.SetMessageDialog("Warning","???????????????????????????????????????????????????");
//			    	  	    	}else{
//			    	  	    		String OrderNumber =FrontEndFunctionCenterPageController.getOrderNumber(Account,PassWord,SpotName,SightsTicketingSerialNumber,TicketTypeNumber,TrialCrowdNumber,PurchaseQuantity,TravelDate,
//						    	  	    GoingOneName,GoingOneIDCard,GoingOnePhone,GoingOneEmail,
//				    	  	    		 GoingTwoName,GoingTwoIDCard,GoingTwoPhone,GoingTwoEmail,
//				    	  	    		 GoingThreeName,GoingThreeIDCard,GoingThreePhone,GoingThreeEmail,
//				    	  	    		 GoingFourName,GoingFourIDCard,GoingFourPhone,GoingFourEmail,
//				    	  	    		 GoingFiveName,GoingFiveIDCard,GoingFivePhone,GoingFiveEmail,
//				    	  	    	     TicketHolderName,TicketHolderPhone,TicketHolderEmail,phone,TicketHolderNameNumber);
//			    	  	    		     if(OrderNumber!=null){
//						    	  	    	   OrderNumberField.setText(OrderNumber);
//						    	  	    	   Dialog.SetMessageDialog("Success","????????????????????????????????????????????????");
//					    	  	         }else{
//					    	  	               Dialog.SetMessageDialog("Warning","???????????????????????????????????????");
//					    	  	         }
//				    	  	        }
//			    	  	    }else{
//			    	  	    	Dialog.SetMessageDialog("Warning","????????????????????????????????????????????????????????????");;
//			    	  	    }
//	    	  	    	}catch (Exception e) {
//	    	  		 		Platform.runLater(new Runnable() {
//	    	  	   			     @Override
//	    	  	   			     public void run() {
//	    	  	   			         Dialog.SetMessageDialog("Warning","??????????????????????????????????????????????????????????????????");
//	    	  	   			     }
//	    	  	   			});
//	    	  			}
	    	  	    }
	    	  	});

	    	    OnePaymentOrderButton.setLayoutX(184);
	    	    OnePaymentOrderButton.setLayoutY(748);
	    	    OnePaymentOrderButton.setPrefWidth(137);
	    	    OnePaymentOrderButton.setPrefHeight(42);
	    	    OnePaymentOrderButton.setId("OnePaymentOrderButton");
	    	    OnePaymentOrderButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	Dialog.SetMessageDialog("Success","?????????????????????");
//	    	  	    	if(StringUtil.isEmpty(OrderNumberField.getText())){
//	    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????");
//	    	  	    	}else {
//	    	  	    	     String order_sn =  OrderNumberField.getText();
//		    	  	    	 String OrderMessage = FrontEndFunctionCenterPageController.getOrderPaymentInfo(order_sn);
//		  	    		     if("OK".equals(OrderMessage)){
//			    	  	    	   Dialog.SetMessageDialog("Success","?????????????????????");
//		    	  	         }else{
//		    	  	               Dialog.SetMessageDialog("Warning",""+OrderMessage+"???");
//		    	  	         }
//	    	  	    	}
	    	  	     }
	    	  	});

	    	    OneWriteOffOrderButton.setLayoutX(353);
	    	    OneWriteOffOrderButton.setLayoutY(748);
	    	    OneWriteOffOrderButton.setPrefWidth(137);
	    	    OneWriteOffOrderButton.setPrefHeight(42);
	    	    OneWriteOffOrderButton.setId("OneWriteOffOrderButton");
	    	    OneWriteOffOrderButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	Dialog.SetMessageDialog("Success","?????????????????????");
//	    	  	    	if(StringUtil.isEmpty(OrderNumberField.getText())){
//	    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????");
//	    	  	    	}else {
//	    	  	    	    String SpotName = TouristAttractionsField.getText();
//		    	  	    	String order_sn =  OrderNumberField.getText();
//		    	  	    	TravelDate = checkInDatePicker.getValue();
//		    	  	    	 String WriteOffMessage = FrontEndFunctionCenterPageController.getOrderWriteOffInfo(Account,PassWord,SpotName,order_sn,TravelDate);
//		  	    		     if("????????????".equals(WriteOffMessage)){
//			    	  	    	   Dialog.SetMessageDialog("Success","?????????????????????");
//		    	  	         }else{
//		    	  	               Dialog.SetMessageDialog("Warning",""+WriteOffMessage+"???");
//		    	  	         }
//	    	  	    	}
	    	  	     }
	    	  	});

	    	    OneEvaluateOrderButton.setLayoutX(524);
	    	    OneEvaluateOrderButton.setLayoutY(748);
	    	    OneEvaluateOrderButton.setPrefWidth(137);
	    	    OneEvaluateOrderButton.setPrefHeight(42);
	    	    OneEvaluateOrderButton.setId("OneEvaluateOrderButton");
	    	    OneEvaluateOrderButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	Dialog.SetMessageDialog("Success","?????????????????????");
//	    	  	    	if(StringUtil.isEmpty(OrderNumberField.getText())){
//	    	  	    		Dialog.SetMessageDialog("Warning","?????????????????????????????????????????????");
//	    	  	    	}else {
//	    	  	    	    String SpotName = TouristAttractionsField.getText();
//		    	  	    	String order_sn =  OrderNumberField.getText();
//		    	  	    	TravelDate = checkInDatePicker.getValue();
//		    	  	    	 String EvaluateMessage = FrontEndFunctionCenterPageController.getOrderEvaluateInfo(Account,PassWord,SpotName,SightsTicketingSerialNumber,order_sn,TravelDate);
//		  	    		     if("OK".equals(EvaluateMessage)){
//			    	  	    	   Dialog.SetMessageDialog("Success","?????????????????????");
//		    	  	         }else{
//		    	  	               Dialog.SetMessageDialog("Warning",""+EvaluateMessage+"???");
//		    	  	         }
//	    	  	    	}
	    	  	     }
	    	  	});

        	    FrontEndFunctionCenterPane.getChildren().add(TouristAttractionsField);
        	    FrontEndFunctionCenterPane.getChildren().add(TouristAttractionsButton);

        	    FrontEndFunctionCenterPane.getChildren().add(SightsTicketingField);
        	    FrontEndFunctionCenterPane.getChildren().add(SightsTicketingButton);

        	    FrontEndFunctionCenterPane.getChildren().add(TicketTypeField);
        	    FrontEndFunctionCenterPane.getChildren().add(TicketTypeButton);

        	    FrontEndFunctionCenterPane.getChildren().add(TrialCrowdField);
        	    FrontEndFunctionCenterPane.getChildren().add(TrialCrowdButton);

        	    FrontEndFunctionCenterPane.getChildren().add(checkOutDatePicker);

        	    FrontEndFunctionCenterPane.getChildren().add(StockDayField);
        	    FrontEndFunctionCenterPane.getChildren().add(StockDayButton);

        	    FrontEndFunctionCenterPane.getChildren().add(PurchaseQuantityField);

        	    FrontEndFunctionCenterPane.getChildren().add(GoingOneNameField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingOneNameButton);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingOnePhoneField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingOneIDCardField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingOneEmailField);

        	    FrontEndFunctionCenterPane.getChildren().add(GoingTwoNameField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingTwoNameButton);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingTwoPhoneField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingTwoIDCardField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingTwoEmailField);

        	    FrontEndFunctionCenterPane.getChildren().add(GoingThreeNameField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingThreeNameButton);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingThreePhoneField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingThreeIDCardField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingThreeEmailField);

        	    FrontEndFunctionCenterPane.getChildren().add(GoingFourNameField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFourNameButton);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFourPhoneField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFourIDCardField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFourEmailField);

        	    FrontEndFunctionCenterPane.getChildren().add(GoingFiveNameField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFiveNameButton);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFivePhoneField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFiveIDCardField);
        	    FrontEndFunctionCenterPane.getChildren().add(GoingFiveEmailField);

        	    FrontEndFunctionCenterPane.getChildren().add(TicketHolderNameField);
        	    FrontEndFunctionCenterPane.getChildren().add(TicketHolderNameButton);
        	    FrontEndFunctionCenterPane.getChildren().add(TicketHolderPhoneField);
        	    FrontEndFunctionCenterPane.getChildren().add(TicketHolderEmailField);

        	    FrontEndFunctionCenterPane.getChildren().add(OrderNumberField);

        	    FrontEndFunctionCenterPane.getChildren().add(OneCreateOrderButton);
        	    FrontEndFunctionCenterPane.getChildren().add(OnePaymentOrderButton);
        	    FrontEndFunctionCenterPane.getChildren().add(OneWriteOffOrderButton);
        	    FrontEndFunctionCenterPane.getChildren().add(OneEvaluateOrderButton);

        	    HomePagePane.getChildren().add(FrontEndFunctionCenterPane);
//        	    HomePageView.HomePagePane.getChildren().add(FrontEndFunctionCenterPane);
        }

    	public static void getFrontEndFunctionCenterPane(boolean show) {
         	FrontEndFunctionCenterPane.setVisible(show);
     	}
    	
        public static void main(String[] args) {
        	Locale.setDefault(Locale.US);
	        launch(args);
        }
}