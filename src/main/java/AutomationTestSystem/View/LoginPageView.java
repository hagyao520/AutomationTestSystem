package AutomationTestSystem.View;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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

import java.awt.AWTException;
import java.awt.SystemTray;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import org.comtel2000.keyboard.control.KeyBoardPopup;
import org.comtel2000.keyboard.control.KeyBoardPopupBuilder;

import AutomationTestSystem.Config.ConfigManage;
import AutomationTestSystem.Config.SystemDatabaseConfiguration;
import AutomationTestSystem.Config.SystemServerConfiguration;
import AutomationTestSystem.Config.UserInformationConfiguration;
import AutomationTestSystem.Config.UserSaveData;
import AutomationTestSystem.Config.UserSaveDataBox;
import AutomationTestSystem.Controller.LoginController;
import AutomationTestSystem.Tray.OnlyTrayIcon;
import AutomationTestSystem.Tray.Tray;
import AutomationTestSystem.Tray.TrayPopupMenu;
import AutomationTestSystem.Util.DatabaseUtil;
import AutomationTestSystem.Util.DialogUtil;
import AutomationTestSystem.Util.DragUtil;
import AutomationTestSystem.Util.OpenBrowserUtil;
import AutomationTestSystem.Util.StringUtil;

@SuppressWarnings({ "static-access", "restriction","unchecked", "rawtypes", "unused"})
public class LoginPageView extends Application {

		public  static Stage LoginInterfaceStage;
//    	LoginView.LoginInterfaceStage.close();
		public  static Stage SystemSettingStage;

		private DropShadow dropShadow = new DropShadow();
        private DialogUtil Dialog= new DialogUtil();
        private HomePageView HomePageView= new HomePageView();

        private ImageView HeadImageView = new ImageView();
        static TextField AccountField;
        static PasswordField PassWordField;
//        private TextField AccountField = new TextField();
//        private PasswordField PassWordField = new PasswordField();

        private Button AccountOnButton = new Button();
        private Button AccountOffButton = new Button();

        private AnchorPane AccountPane = new AnchorPane();
        private AnchorPane PassWordPane = new AnchorPane();
        private Label RegisteredLabel = new Label();
        private Label ForgetPassWordLabel = new Label();
        private Label NameLabel = new Label();
        private Button LoginButton = new Button();

        private CheckBox RememberPassword = new CheckBox();
        private CheckBox AutomaticLogon = new CheckBox();

        private TextField DJDBCDriverField = new TextField();
        private TextField DHostAddressField = new TextField();
        private TextField DUserNameField = new TextField();
        private TextField DPassWordField = new TextField();
        private TextField DPortField = new TextField();
        private TextField DDataBaseField = new TextField();
        private TextField SHostAddressField = new TextField();
        private TextField SPortField = new TextField();
        private TextField SUserNameField = new TextField();
        private TextField SPassWordField = new TextField();
        private TextField SKeyField = new TextField();
        private TextField SApiField = new TextField();

    	private OnlyTrayIcon trayIcon;
    	private TrayPopupMenu trayPopupMenu = new TrayPopupMenu();
        private java.awt.Image image = new ImageIcon("src/main/resources/image/LoginPane/Logo/Logo_ico16x16.png").getImage();

        ObservableList<String> data = FXCollections.observableArrayList();
	    ListView<String> AccountListView = new ListView<String>(data);

        CopyOnWriteArraySet<EventHandler<ActionEvent>> closeActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();
        CopyOnWriteArraySet<EventHandler<ActionEvent>> iconifiedActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();

        SystemDatabaseConfiguration sdcd = (SystemDatabaseConfiguration) ConfigManage.get(SystemDatabaseConfiguration.path, SystemDatabaseConfiguration.class);
        SystemServerConfiguration ssc = (SystemServerConfiguration) ConfigManage.get(SystemServerConfiguration.path, SystemServerConfiguration.class);

        UserInformationConfiguration uic = (UserInformationConfiguration) ConfigManage.get(UserInformationConfiguration.path, UserInformationConfiguration.class);
        UserSaveDataBox usdb = (UserSaveDataBox) ConfigManage.get(UserSaveDataBox.path, UserSaveDataBox.class);

    	int SystemSettingStageStatus = 0;
    	int LoginInterfaceStageStatus = 1;
    	static int LoginStatus = 0;

		@Override
        public void start(Stage LoginInterfaceStage) throws Exception  {
                this.LoginInterfaceStage = LoginInterfaceStage;
                //设置主窗口标题
                this.LoginInterfaceStage.setTitle("LoginInterface");
                //设置主窗口图标
//                this.LoginInterfaceStage.getIcons().add(new Image(getClass().getResourceAsStream("images/address_book_32.png")));//获取images包中的图片
                this.LoginInterfaceStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//获取resource下images中的图片
                //去除主窗口外边框
                this.LoginInterfaceStage.initStyle(StageStyle.TRANSPARENT);

                //加载启动类
                LoginInterface();
                //加载托盘
                TaskbarIcon();

//              SystemSettingInterface();
//              LoadingInterface();
//        		HomePageView.HomePageInterface();
        }

        public void LoginInterface() throws Exception {
        	    StackPane LoginStack = new StackPane();
      	        LoginStack.getStylesheets().add(this.getClass().getResource("/css/login.css").toString());

//	    		VBox LoginBox = new VBox();
//	    		LoginBox.setBackground(Background.EMPTY);

	        	AnchorPane LoginPane = new AnchorPane();
	        	LoginPane.setPrefWidth(435);
	        	LoginPane.setPrefHeight(330);
	        	LoginPane.setBackground(Background.EMPTY);
	        	LoginPane.setId("LoginPane");

	        	Button SettingButton = new Button();
	        	SettingButton.setId("SettingButton");
	        	SettingButton.setPrefWidth(30);
	        	SettingButton.setPrefHeight(27);
        		SettingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    		    public void handle(MouseEvent event) {
	    		    	LoginInterfaceStage.close();
	    		    	SystemSettingInterface();
	    		    	SystemSettingStageStatus=1;
	    		    }
	    		});

	        	Button MinimizationButton = new Button();
	        	MinimizationButton.setId("MinimizationButton");
	        	MinimizationButton.setPrefWidth(30);
	        	MinimizationButton.setPrefHeight(27);
	        	MinimizationButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    		    public void handle(MouseEvent event) {
	    		    	LoginInterfaceStage.hide();
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
	    		MinCloseHox.setLayoutX(345);
	    		MinCloseHox.setLayoutY(0);
	    		MinCloseHox.setBackground(Background.EMPTY);
	    		MinCloseHox.setPickOnBounds(false);//面板不参与计算边界，透明区域无鼠标事件
	    		MinCloseHox.getChildren().addAll(SettingButton,MinimizationButton,CloseButton);

	    		Image LogoIamge = new Image(this.getClass().getResource("/image/LoginPane/Logo/Logo.png").toExternalForm(), true);
	    		ImageView LogoImageView = new ImageView();
	    		LogoImageView.setLayoutX(65);
	    		LogoImageView.setLayoutY(20);
	    		LogoImageView.setImage(LogoIamge);
//	    		LogoImageView.setEffect(dropShadow);//增加阴影效果

	    		WebView LogoWebView = new WebView();
	    		LogoWebView.setPrefSize(435, 180);

	    		WebEngine WebEngine = LogoWebView.getEngine();
//	    		webEngine.load(this.getClass().getClassLoader().getResource("html/index.html").toString());
	    		WebEngine.load(this.getClass().getResource("/html/Login/js/index.html").toString());

	    		Rectangle HeadRectangle = new Rectangle();
	    		HeadRectangle.setArcHeight(10);
	    		HeadRectangle.setArcWidth(10);
	    		HeadRectangle.setWidth(80);
	    		HeadRectangle.setHeight(80);

//	    		String avatar = LoginController.WebUserLogin("13510454271", "123456aa");
//	    		String avatar = uic.getHeadPortrait();
//	    		Image HeadIamge = new Image("https://xiyuyou-dev-public.oss-cn-shanghai.aliyuncs.com/images/2018-06-23/5b2e002b942b5.jpg", 80,80,true, false);
//	    		Image HeadIamge = new Image(this.getClass().getResource("/image/LoginPane/HeadPortraitImage/HeadPortrait.png").toExternalForm(), true);
	    		String avatar = this.getClass().getResource("/image/LoginPane/HeadPortraitImage/HeadPortrait1.gif").toExternalForm();
	    		Image HeadIamge = new Image(avatar, 80,80,true, false);
	    		HeadImageView.setLayoutX(40);
	    		HeadImageView.setLayoutY(194);
	    		HeadImageView.setImage(HeadIamge);
	    		HeadImageView.setClip(HeadRectangle);

	    		AccountField= new TextField();
//	    		AccountField.setLayoutX(135);
//	    		AccountField.setLayoutY(195);
	    		AccountField.setPrefWidth(170);
	    		AccountField.setPrefHeight(30);
//	    		AccountField.setId("AccountField");
	    		AccountField.setPromptText("账号");
	    		AccountField.setPrefColumnCount(11);//设置文本最大显示内容长度
	    		AccountField.getStyleClass().remove("text-field");
	    		AccountField.setBackground(Background.EMPTY);
	    		AccountField.setBorder(Border.EMPTY);
	    		AccountField.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	    		     addAccountChangeListener(new ChangeListener<String>() {
	    	    			@Override
	    	    			public void changed(ObservableValue<? extends String> observable, String oldValue, String accountValue) {
	    	    				if (null != accountValue) {
	    	    					UserSaveData usd = usdb.get(accountValue);
	    	    					if (null != usd) {
	    	    						Image image = new Image(usd.getHead(), 80,80,true, false);
	    	    						setHeadImage(image);
	    	    						PassWordField.setText(usd.getPassWord());
	    	    						RememberPassword.setSelected(usd.getRememberPasswordStatus());
	    	    						AutomaticLogon.setSelected(usd.getAutomaticLogonStatus());
	    	    						Timer timer = new Timer();
	    	    		    			timer.schedule(new TimerTask() {
	    	    		    				@Override
	    	    		    				public void run() {
	    	    		    			    	 String Account = AccountField.getText();
    	    		    		    		     String PassWord = PassWordField.getText();
    	    		    			    	     if(StringUtil.isEmpty(Account)||StringUtil.isEmpty(PassWord)){
    	    		    		    	 	     }else if(AutomaticLogon.isSelected()){
    	    		    		    	 	    	Platform.runLater(new Runnable() {
    	   				    	    			       @Override
    	   				    	    			        public void run() {
    	   				    	    			    	    HeadImageView.setLayoutX(175);
    	   		 		    	 		    			    HeadImageView.setLayoutY(185);
    	   		 		    	 		    		 	    AccountPane.setVisible(false);
    	   		 		    	 		    			    PassWordPane.setVisible(false);
    	   		 		    	 		    			    RegisteredLabel.setVisible(false);
    	   		 		    	 		    			    ForgetPassWordLabel.setVisible(false);
    	   		 		    	 		    			    RememberPassword.setVisible(false);
    	   		 		    	 		    			    AutomaticLogon.setVisible(false);
    	   		 		    	 		    		        NameLabel.setText("登录中...");
    	   		 		    	 		    			    NameLabel.setVisible(true);
    	   		 		    	 		    			    LoginButton.setLayoutX(120);
    	   		 		    	 		    			    LoginButton.setLayoutY(290);
    	   		 		    	 		    			    LoginButton.setText("取 消");
    	   				    	    			        }
    	   				    	    			    });
//    	    		    		    	 	    	Timer timer = new Timer();
//    	    		    		    	 	    	timer.schedule(new TimerTask() {
//    	    		    		    	 	    		@Override
//    	    	    	    		    				public void run() {
//    	    		    		    	 	    			try {
//    	    		    		    	 	    				ALogin();
//    	    		    		    	 	    			}catch (Exception e) {
//    	    	    	   								 		Platform.runLater(new Runnable() {
//    	    	    	   				    	    			    @Override
//    	    	    	   				    	    			    public void run() {
//    	    	    	   						    	    			Dialog.SetMessageDialog("Warning","服务器连接失败，请稍后重试！");
//    	    	    	   				    	    			    }
//    	    	    	   				    	    			});
//    	    	    	   									 }
//    	    		    		    	 	    		 }
//    	    		    		    	 	         }, 2000);
    	    		    		    	 	     }
	    	   								  }
	    	    		    			}, 200);
	    	    					}else {
	    	    						HeadImageView.setImage(HeadIamge);
	    	    						PassWordField.setText(null);
	    	    						RememberPassword.setSelected(false);
	    	    						AutomaticLogon.setSelected(false);
	    	    					}
	    	    				}
	    	    			}
	    	    		});
	    	  	    }
	    	  	});
	    		AccountField.textProperty().addListener(new ChangeListener<String>() {
	                @Override
	                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	                	if(AccountField.getText().length() ==11){
	                		PassWordField.requestFocus();
	                	}
	                }
	            });
	    		AccountField.textProperty().addListener(new ChangeListener<String>() {
	                @Override
	                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	                	if(AccountField.getText().length() >11){
    	          			 Dialog.SetMessageDialog("Warning","账号最大11位！");
    	          		     return;
	                	}
	                }
	            });
//	    		AccountField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//	    		    @Override
//	    		    public void handle(KeyEvent event) {
//	    		        if(event.getCode() == KeyCode.TAB){
//	    		    		PassWordField.requestFocus();
//	    		        }
//	    		    }
//	    		 });

    			Map<String, UserSaveData> map = usdb.getMap();
    			List<UserSaveData> list = new ArrayList<UserSaveData>(map.values());
//    			UserSaveData ud = list.get(0);

	    		int size = usdb.getSize();// 获取登录保存的用户账号数量
    			for(int i=0;i<size;i++){
//	    		data.addAll(list.get(0).getAccount(), list.get(1).getAccount(), list.get(2).getAccount(), list.get(3).getAccount(), list.get(4).getAccount());
    		    data.addAll(list.get(i).getAccount());}

	    		AccountListView.setLayoutX(135);
	    		AccountListView.setLayoutY(195+30);
	    		AccountListView.setPrefSize(194, 85);
	    		AccountListView.setItems(data);
	  	        AccountListView.setEditable(true);
	  	        AccountListView.setVisible(false);
	  	        AccountListView.setCellFactory((ListView<String>l) -> new ColorRectCell());
	  	        AccountListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
	  	        	AccountField.setText(new_val);
	  	        	AccountOnButton.setVisible(false);
    	  	    	AccountOffButton.setVisible(true);
	  	        	AccountListView.setVisible(false);
    				if (null != new_val) {
    					UserSaveData usd = usdb.get(new_val);
    					if (null != usd) {
    						Image image = new Image(usd.getHead(), 80,80,true, false);
    						setHeadImage(image);
    						PassWordField.setText(usd.getPassWord());
    						RememberPassword.setSelected(usd.getRememberPasswordStatus());
    						AutomaticLogon.setSelected(usd.getAutomaticLogonStatus());
    						Timer timer = new Timer();
    		    			timer.schedule(new TimerTask() {
    		    				@Override
    		    				public void run() {
    		    			    	 String Account = AccountField.getText();
		    		    		     String PassWord = PassWordField.getText();
		    			    	     if(StringUtil.isEmpty(Account)||StringUtil.isEmpty(PassWord)){
		    		    	 	     }else if(AutomaticLogon.isSelected()){
		    		    	 	    	Platform.runLater(new Runnable() {
				    	    			       @Override
				    	    			        public void run() {
				    	    			    	    HeadImageView.setLayoutX(175);
		 		    	 		    			    HeadImageView.setLayoutY(185);
		 		    	 		    		 	    AccountPane.setVisible(false);
		 		    	 		    			    PassWordPane.setVisible(false);
		 		    	 		    			    RegisteredLabel.setVisible(false);
		 		    	 		    			    ForgetPassWordLabel.setVisible(false);
		 		    	 		    			    RememberPassword.setVisible(false);
		 		    	 		    			    AutomaticLogon.setVisible(false);
		 		    	 		    		        NameLabel.setText("登录中...");
		 		    	 		    			    NameLabel.setVisible(true);
		 		    	 		    			    LoginButton.setLayoutX(120);
		 		    	 		    			    LoginButton.setLayoutY(290);
		 		    	 		    			    LoginButton.setText("取 消");
				    	    			        }
				    	    			    });
		    		    	 	    	Timer timer = new Timer();
		    		    	 	    	timer.schedule(new TimerTask() {
		    		    	 	    		@Override
	    	    		    				public void run() {
		    		    	 	    			try {
		    		    	 	    				ALogin();
		    		    	 	    			}catch (Exception e) {
	    	   								 		Platform.runLater(new Runnable() {
	    	   				    	    			    @Override
	    	   				    	    			    public void run() {
	    	   						    	    			Dialog.SetMessageDialog("Warning","服务器连接失败，请稍后重试！");
	    	   				    	    			    }
	    	   				    	    			});
	    	   									 }
		    		    	 	    		 }
		    		    	 	         }, 2000);
		    		    	 	     }
   								  }
    		    			}, 200);
    					}else {
    						HeadImageView.setImage(HeadIamge);
    						PassWordField.setText(null);
    						RememberPassword.setSelected(false);
    						AutomaticLogon.setSelected(false);
    					}
    				}
	  	        });

//	  	        AccountListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//		  	          @Override
//		  	          public void handle(MouseEvent event)
//		  	          {
//		  	              if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1)
//		  	              {
//		  	                  System.out.println("单击了条目");
//		  	                  int index = AccountListView.getSelectionModel().getSelectedIndex();
//		  	                  System.out.println(index);
//		  	                  System.out.println(event.getX());
//		  	                  System.out.println(event.getSceneX());
//		  	              }
//		  	              else if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2)
//		  	              {
//		  	                  System.out.println("双击了条目");
//		  	              }
//		  	         }
//		  	    });

	    		AccountOnButton.setLayoutX(170);
	    		AccountOnButton.setLayoutY(5);
	    		AccountOnButton.setPrefHeight(20);
	    		AccountOnButton.setPrefWidth(20);
	    		AccountOnButton.setId("AccountField-Combobox-OnButton");
	    		AccountOnButton.setVisible(false);
	    		AccountOnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	AccountOnButton.setVisible(false);
	    	  	    	AccountOffButton.setVisible(true);
//    	  	            AccountListView.setItems(null);
    	  	        	AccountListView.setVisible(false);
	    	  	    }
	    	  	});

	    		AccountOffButton.setLayoutX(170);
	    		AccountOffButton.setLayoutY(5);
	    		AccountOffButton.setPrefHeight(20);
	    		AccountOffButton.setPrefWidth(20);
	    		AccountOffButton.setId("AccountField-Combobox-OffButton");
	    		AccountOffButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
    	  	        	AccountOffButton.setVisible(false);
    	  	        	AccountOnButton.setVisible(true);
//        	  	    	AccountListView.setItems(null);
//    	  	        	AccountListView.setItems(name);
    	  	        	AccountListView.setVisible(true);
    	  	        	AccountListView.toFront();//激活当前图层到最顶层
	    	  	    }
	    	  	});

	    		AccountPane.setLayoutX(135);
	    		AccountPane.setLayoutY(195);
	    		AccountPane.setPrefWidth(194);
	    		AccountPane.setPrefHeight(30);
	    		AccountPane.setId("AccountField");
	    		AccountPane.getChildren().add(AccountField);
	    		AccountPane.getChildren().add(AccountOnButton);
	    		AccountPane.getChildren().add(AccountOffButton);

	    		AnchorPane AccountListViewPane = new AnchorPane();
	    		AccountListViewPane.setPrefWidth(194);
	    		AccountListViewPane.setPrefHeight(200);
	    		AccountListViewPane.getChildren().add(AccountListView);

	    		PassWordField = new PasswordField();
//	    		PassWordField.setLayoutX(135);
//	    		PassWordField.setLayoutY(195+30);
	    		PassWordField.setPrefWidth(170);
	    		PassWordField.setPrefHeight(30);
	    		PassWordField.setPromptText("密码");
//	    		PassWordField.setId("PassWordField");
	    		PassWordField.getStyleClass().remove("text-field");
	    		PassWordField.setBackground(Background.EMPTY);
	    		PassWordField.setBorder(Border.EMPTY);
//	    		PassWordField.setOnMouseExited(new EventHandler<MouseEvent>() {
//	    		     @Override
//	    			 public void handle(MouseEvent event){
//	    			     try {
//	    			    	    String Account = AccountField.getText();
//	    		    		    String PassWord = PassWordField.getText();
//	    			    	if(StringUtil.isEmpty(Account)||StringUtil.isEmpty(PassWord)){
//	    		    	 	 }else if(AutomaticLogon.isSelected()){
//	    		    	 		ALogin();
//	    		    	 	 }
//						 }catch (Exception e) {
//							    e.printStackTrace();
//					     }
//	    		     }
//	    		});

	    		AnchorPane PassWordKeyButton = new AnchorPane();
	    		PassWordKeyButton.setLayoutX(173);
	    		PassWordKeyButton.setLayoutY(6);
	    		PassWordKeyButton.setPrefHeight(16);
	    		PassWordKeyButton.setPrefWidth(15);
	    		PassWordKeyButton.setPrefSize(15, 15);
	    		PassWordKeyButton.setId("PassWordField-KeyButton");
	    		KeyBoardPopup KeyPopup = KeyBoardPopupBuilder.create().initLocale(Locale.ENGLISH).build();
	    		KeyPopup.setAutoHide(true);
	    		KeyPopup.setOnKeyboardCloseButton(e -> KeyPopup.hide());
	    		PassWordKeyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    		    public void handle(MouseEvent event) {
	    				    PassWordField.requestFocus();
	    				    KeyPopup.show(PassWordKeyButton, LoginInterfaceStage.getX() + 135, LoginInterfaceStage.getY() + 260);
	    		    }
	    		});

	    		PassWordPane.setLayoutX(135);
	    		PassWordPane.setLayoutY(194+30);
	    		PassWordPane.setPrefWidth(194);
	    		PassWordPane.setPrefHeight(30);
	    		PassWordPane.setId("PassWordField");
	    		PassWordPane.getChildren().add(PassWordField);
	    		PassWordPane.getChildren().add(PassWordKeyButton);

	    		AccountPane.setOnMouseEntered((Event event) -> {
	    			AccountPane.toFront();//激活当前图层到最上层
	    		});

	    		PassWordPane.setOnMouseEntered((Event event) -> {
	    			PassWordPane.toFront();//激活当前图层到最上层
	    		});

	    		RegisteredLabel.setLayoutX(345);
	    		RegisteredLabel.setLayoutY(203);
	    		RegisteredLabel.setText("注册账号");
	    		RegisteredLabel.setId("RegisteredLabel");
	    		RegisteredLabel.setCursor(Cursor.HAND);//鼠标手势效果
	    		RegisteredLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    			    try {
//	    			    	   OpenBrowserUtil.OpenBrowser("http://"+ssc.getSapi()+"/register/visitor-register");
	    			    	   OpenBrowserUtil.OpenBrowser("https://testerhome.com/opensource_projects/automationtestsystem");
						} catch (Exception e) {
							   e.printStackTrace();
					    }
	    		    }
	    		});

	    		ForgetPassWordLabel.setLayoutX(345);
	    		ForgetPassWordLabel.setLayoutY(231);
	    		ForgetPassWordLabel.setText("找回密码");
	    		ForgetPassWordLabel.setId("ForgetPassWordLabel");
	    		ForgetPassWordLabel.setCursor(Cursor.HAND);//鼠标手势效果
	    		ForgetPassWordLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    			    try {
//	    			    	   OpenBrowserUtil.OpenBrowser("http://"+ssc.getSapi()+"/forget/0");
	    			    	   OpenBrowserUtil.OpenBrowser("https://testerhome.com/topics/16372");
						} catch (Exception e) {
							   e.printStackTrace();
					    }
	    		    }
	    		});

	    		RememberPassword.setLayoutX(136);
	    		RememberPassword.setLayoutY(260);
	    		RememberPassword.setText("记住密码");

	    		AutomaticLogon.setLayoutX(260);
	    		AutomaticLogon.setLayoutY(260);
	    		AutomaticLogon.setText("自动登录");

    			NameLabel.setText("登录中...");
    			NameLabel.setId("NameLabel");
    			NameLabel.setCursor(Cursor.HAND);
    			NameLabel.setVisible(false);
    			NameLabel.setOnMouseEntered((MouseEvent e) -> {
    				NameLabel.setScaleX(1.2);
    				NameLabel.setScaleY(1.2);
    			});
    			NameLabel.setOnMouseExited((MouseEvent e) -> {
    				NameLabel.setScaleX(1);
    				NameLabel.setScaleY(1);
    			});

    			BorderPane NamePane = new BorderPane();
	    		NamePane.setPrefWidth(430);
	    		NamePane.setPrefHeight(555);
	    		NamePane.setCenter(NameLabel);

	    		LoginButton.setLayoutX(135);
	    		LoginButton.setLayoutY(287);
	    		LoginButton.setPrefHeight(30);
	    		LoginButton.setPrefWidth(194);
	    		LoginButton.setText("登  录");
	    		LoginButton.setId("LoginButton");
	    		LoginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    			public void handle(MouseEvent event){
	    			    if("登  录".equals(LoginButton.getText())) {
		    	                  String Account = AccountField.getText();
		    	          		  String PassWord = PassWordField.getText();
		    	          		  if("账号".equals(Account)|StringUtil.isEmpty(Account)){
		    	          			    Dialog.SetMessageDialog("Warning","请输入账号！");
		    	          		        return;
		    	          	      }else if("密码".equals(PassWord)|StringUtil.isEmpty(PassWord)){
		    	          			    Dialog.SetMessageDialog("Warning","请输入密码！");
		    	          		        return;
		    	          	      }else{
			    	          	    	HeadImageView.setLayoutX(175);
			    		    			HeadImageView.setLayoutY(185);
			    		    			AccountPane.setVisible(false);
			    		    			PassWordPane.setVisible(false);
			    		    			RegisteredLabel.setVisible(false);
			    		    			ForgetPassWordLabel.setVisible(false);
			    		    			RememberPassword.setVisible(false);
			    		    			AutomaticLogon.setVisible(false);
			    		    			NameLabel.setText("登录中...");
			    		    			NameLabel.setVisible(true);
			    		    			LoginButton.setLayoutX(120);
			    		    			LoginButton.setLayoutY(290);
			    		    			LoginButton.setText("取 消");
			    		    			Timer timer = new Timer();
			    		    			timer.schedule(new TimerTask() {
			    		    				@Override
			    		    				public void run() {
			    		    					 try {
												         ALogin();
											 	 } catch (Exception e) {
											 		Platform.runLater(new Runnable() {
							    	    			    @Override
							    	    			    public void run() {
									    	    			Dialog.SetMessageDialog("Warning","服务器连接失败，请稍后重试！");
							    	    			    }
							    	    			});
												 }
			    		    				  }
			    		    			}, 2000);
		    	          	    }
					    }else if("取 消".equals(LoginButton.getText())) {
						    NameLabel.setText("取消登录");
	    	    			HeadImageView.setLayoutX(40);
	    	    			HeadImageView.setLayoutY(194);
	    	    			AccountPane.setVisible(true);
	    	    			PassWordPane.setVisible(true);
	    	    			RegisteredLabel.setVisible(true);
	    	    			ForgetPassWordLabel.setVisible(true);
	    	    			RememberPassword.setVisible(true);
	    	    			AutomaticLogon.setVisible(true);
	    	    			NameLabel.setVisible(false);
	    	    			LoginButton.setLayoutX(135);
	    	    			LoginButton.setText("登  录");
					    }
	    		    }
	    		});

	    		LoginPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    		    @Override
	    		    public void handle(KeyEvent event) {
	    		        if(event.getCode() == KeyCode.ENTER){
	    		        	if("登  录".equals(LoginButton.getText())) {
			    	                  String Account = AccountField.getText();
			    	          		  String PassWord = PassWordField.getText();
			    	          		  if("账号".equals(Account)|StringUtil.isEmpty(Account)){
			    	          			    Dialog.SetMessageDialog("Warning","请输入账号！");
			    	          		        return;
			    	          	      }else if("密码".equals(PassWord)|StringUtil.isEmpty(PassWord)){
			    	          			    Dialog.SetMessageDialog("Warning","请输入密码！");
			    	          		        return;
			    	          	      }else{
				    	          	    	HeadImageView.setLayoutX(175);
				    		    			HeadImageView.setLayoutY(185);
				    		    			AccountPane.setVisible(false);
				    		    			PassWordPane.setVisible(false);
				    		    			RegisteredLabel.setVisible(false);
				    		    			ForgetPassWordLabel.setVisible(false);
				    		    			RememberPassword.setVisible(false);
				    		    			AutomaticLogon.setVisible(false);
				    		    			NameLabel.setText("登录中...");
				    		    			NameLabel.setVisible(true);
				    		    			LoginButton.setLayoutX(120);
				    		    			LoginButton.setLayoutY(290);
				    		    			LoginButton.setText("取 消");
				    		    			Timer timer = new Timer();
				    		    			timer.schedule(new TimerTask() {
				    		    				@Override
				    		    				public void run() {
				    		    					 try {
													         ALogin();
												 	 } catch (Exception e) {
												 		Platform.runLater(new Runnable() {
								    	    			    @Override
								    	    			    public void run() {
										    	    			Dialog.SetMessageDialog("Warning","服务器连接失败，请稍后重试！");
								    	    			    }
								    	    			});
													 }
				    		    				  }
				    		    			}, 2000);
			    	          	    }
						    }else if("取 消".equals(LoginButton.getText())) {
						    	NameLabel.setText("取消登录");
		    	    			HeadImageView.setLayoutX(40);
		    	    			HeadImageView.setLayoutY(194);
		    	    			AccountPane.setVisible(true);
		    	    			PassWordPane.setVisible(true);
		    	    			RegisteredLabel.setVisible(true);
		    	    			ForgetPassWordLabel.setVisible(true);
		    	    			RememberPassword.setVisible(true);
		    	    			AutomaticLogon.setVisible(true);
		    	    			NameLabel.setVisible(false);
		    	    			LoginButton.setLayoutX(135);
		    	    			LoginButton.setText("登  录");
						    }
		    		    }
	    		    }
	    		});
//	    		LoginPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
//	    		     @Override
//	    			 public void handle(MouseEvent event){
////	    			     try {
////	    			    	    String Account = AccountField.getText();
////	    		    		    String PassWord = PassWordField.getText();
////	    			    	if(StringUtil.isEmpty(Account)||StringUtil.isEmpty(PassWord)){
////	    		    	 	 }else if(AutomaticLogon.isSelected()){
////	    		    	 		ALogin();
////	    		    	 	 }
////						 }catch (Exception e) {
////							    e.printStackTrace();
////					     }
//	    			     Timer timer = new Timer();
// 		    			 timer.schedule(new TimerTask() {
// 		    				@Override
// 		    				public void run() {
// 		    					 try {
// 		    			    	        String Account = AccountField.getText();
// 		    		    		        String PassWord = PassWordField.getText();
// 		    			    	     if(StringUtil.isEmpty(Account)||StringUtil.isEmpty(PassWord)){
// 		    		    	 	     }else if(AutomaticLogon.isSelected()){
// 		    		    	 	    	Platform.runLater(new Runnable() {
//				    	    			    @Override
//				    	    			    public void run() {
//				    	    			    	HeadImageView.setLayoutX(175);
//		 		    	 		    			 HeadImageView.setLayoutY(185);
//		 		    	 		    		 	 AccountPane.setVisible(false);
//		 		    	 		    			 PassWordPane.setVisible(false);
//		 		    	 		    			 RegisteredLabel.setVisible(false);
//		 		    	 		    			 ForgetPassWordLabel.setVisible(false);
//		 		    	 		    			 RememberPassword.setVisible(false);
//		 		    	 		    			 AutomaticLogon.setVisible(false);
//		 		    	 		    			 RegisteredLabel.setVisible(false);
//		 		    	 		    			 NameLabel.setVisible(true);
//		 		    	 		    			 LoginButton.setLayoutX(120);
//		 		    	 		    			 LoginButton.setLayoutY(290);
//		 		    	 		    			 LoginButton.setText("取 消");
//				    	    			    }
//				    	    			});
// 		    		    	 	    	ALogin();
// 		    		    	 	     }
//								  } catch (Exception e) {
//								 		Platform.runLater(new Runnable() {
//				    	    			    @Override
//				    	    			    public void run() {
//						    	    			Dialog.SetMessageDialog("Warning","服务器连接失败，请稍后重试！");
//				    	    			    }
//				    	    			});
//									 }
// 		    				  }
// 		    			}, 300);
//	    		     }
//	    		});

	    		LoginPane.getChildren().add(NamePane);
	    		LoginPane.getChildren().add(LogoWebView);
	    		LoginPane.getChildren().add(LogoImageView);
	    		LoginPane.getChildren().add(MinCloseHox);
	    		LoginPane.getChildren().add(HeadImageView);
	    		LoginPane.getChildren().add(AccountPane);
	    		LoginPane.getChildren().add(AccountListViewPane);
	    		LoginPane.getChildren().add(PassWordPane);
	    		LoginPane.getChildren().add(RegisteredLabel);
	    		LoginPane.getChildren().add(ForgetPassWordLabel);
	    		LoginPane.getChildren().add(RememberPassword);
	    		LoginPane.getChildren().add(AutomaticLogon);
	    		LoginPane.getChildren().add(LoginButton);
	    		LoginPane.getChildren().add(AccountListView);
//	    		LoginPane.getChildren().addAll(LogoWebView,LogoImageView,MinCloseHox);

	        	Rectangle LoginRectangle = new Rectangle();
	            LoginRectangle.setWidth(435);
	        	LoginRectangle.setHeight(330);
	        	LoginRectangle.setArcHeight(10);
	            LoginRectangle.setArcWidth(10);

	        	LoginStack.getChildren().addAll(LoginPane);
	        	LoginStack.setClip(LoginRectangle);

	    		//拖动监听器
	    		DragUtil.addDragListener(LoginInterfaceStage,LogoWebView);
	    		DragUtil.addDragListener(LoginInterfaceStage,LoginStack);

	    		//构建一个场景Scene,加载包含根布局的场景
	    		Scene scene = new Scene(LoginStack);
	    		//Scene透明
	    		scene.setFill(null);

	    		//scene加载到新的Stage中
	            LoginInterfaceStage.setScene(scene);
	            //设置让对话框处于屏幕中间
	            LoginInterfaceStage.centerOnScreen();
	            //显示布局
	            LoginInterfaceStage.show();
        }

		public void SystemSettingInterface() {
//    		    Stage SystemSettingStage = new Stage();
    		    SystemSettingStage = new Stage();
    	        SystemSettingStage.initStyle(StageStyle.TRANSPARENT);
    	        SystemSettingStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//获取resource下images中的图片

        		DJDBCDriverField.setText(sdcd.getDjdbcDriver());
        		DHostAddressField.setText(sdcd.getDhostAddress());
        		DUserNameField.setText(sdcd.getDuserName());
        		DPassWordField.setText(sdcd.getDpassWord());
        		DPortField.setText(sdcd.getDport());
        		DDataBaseField.setText(sdcd.getDdataBase());

        		SHostAddressField.setText(ssc.getShostAddress());
        		SPortField.setText(ssc.getSport());
        		SUserNameField.setText(ssc.getSuserName());
        		SPassWordField.setText(ssc.getSpassWord());
        		SKeyField.setText(ssc.getSkey());
        		SApiField.setText(ssc.getSapi());

        	    StackPane LoginStack = new StackPane();
        	    LoginStack.getStylesheets().add(this.getClass().getResource("/css/login.css").toString());

	        	AnchorPane SystemSettingPane = new AnchorPane();
	        	SystemSettingPane.setPrefWidth(500);
	        	SystemSettingPane.setPrefHeight(500);
	        	SystemSettingPane.setId("SystemSettingPane");
	        	SystemSettingPane.setBackground(Background.EMPTY);

	    		Button CloseButton = new Button();
	    		CloseButton.setId("CloseButton");
	    		CloseButton.setPrefWidth(30);
	    		CloseButton.setPrefHeight(30);
	    		CloseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
	    		    public void handle(MouseEvent event) {
	    		    	String djdbcdriver = DJDBCDriverField.getText();
	    	    		String dhostaddress = DHostAddressField.getText();
	    	    		String dusername = DUserNameField.getText();
	    	    		String dpassword = DPassWordField.getText();
	    	    		String dport = DPortField.getText();
	    	    		String ddatabase = DDataBaseField.getText();

	    	    		String shostaddress = SHostAddressField.getText();
	            		String sport = SPortField.getText();
	            		String susername = SUserNameField.getText();
	            		String spassword = SPassWordField.getText();
	            		String skey = SKeyField.getText();
	            		String sapi = SApiField.getText();

	    	    		if (djdbcdriver == null || "".endsWith(djdbcdriver)) {
	    	                Dialog.SetMessageDialog("Warning","请输入数据库驱动！");
	    	    		}else if (dhostaddress == null || "".endsWith(dhostaddress)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库主机地址！");
	    	    		}else if (dusername == null || "".endsWith(dusername)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库用户名！");
	    	    		}else if (dpassword == null || "".endsWith(dpassword)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库密码！");
	    	    		}else if (dport == null || "".endsWith(dport)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库端口！");
	    	    		}else if (ddatabase == null || "".endsWith(ddatabase)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入要连接的数据库名！");
	    	    		}else if (shostaddress == null || "".endsWith(shostaddress)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器主机地址！");
	            		}else if (sport == null || "".endsWith(sport)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器端口！");
	            		}else if (susername == null || "".endsWith(susername)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器用户名！");
	            		}else if (spassword == null || "".endsWith(spassword)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器密码！");
	            		}else if (skey == null || "".endsWith(skey)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器私钥路径！");
	            		}else if (sapi == null || "".endsWith(sapi)) {
	            			Dialog.SetMessageDialog("Warning","请输入接口地址！");
	            		}else{
	    	    			SystemDatabaseConfiguration sdcd = new SystemDatabaseConfiguration();
	    	    			sdcd.setDjdbcDriver(djdbcdriver);
	    	    			sdcd.setDhostAddress(dhostaddress);
	    	    			sdcd.setDuserName(dusername);
	    	    			sdcd.setDpassWord(dpassword);
	    	    			sdcd.setDport(dport);
	    	    			sdcd.setDdataBase(ddatabase);
	    	        		ConfigManage.addOrUpdate(SystemDatabaseConfiguration.path, sdcd);

	            			SystemServerConfiguration ssc = new SystemServerConfiguration();
	            			ssc.setShostAddress(shostaddress);
	            			ssc.setSport(sport);
	            			ssc.setSuserName(susername);
	            			ssc.setSpassWord(spassword);
	            			ssc.setSkey(skey);
	            			ssc.setSapi(sapi);
	                		ConfigManage.addOrUpdate(SystemServerConfiguration.path, ssc);
	                    	SystemSettingStage.close();
	                    	if(LoginInterfaceStageStatus==1){
	  		    				   LoginInterfaceStage.show();
	  				             }else{
	  				            	 HomePageView.HomePageInterfaceStage.show();
	  				             }
	                		Dialog.SetMessageDialog("Success","修改成功，重启后生效！");
	                		SystemSettingStageStatus=0;
	            		}
	    		    }
	    		});

	    		HBox MinCloseHox = new HBox();
	    		MinCloseHox.setLayoutX(470);
	    		MinCloseHox.setLayoutY(0);
	    		MinCloseHox.setBackground(Background.EMPTY);
	    		MinCloseHox.setPickOnBounds(false);//面板不参与计算边界，透明区域无鼠标事件
	    		MinCloseHox.getChildren().addAll(CloseButton);

	    		Image SystemSettingIamge = new Image(this.getClass().getResource("/image/LoginPane/SystemSettingPane/白Settings20X20.png").toExternalForm(), true);
	    		ImageView SystemSettingView = new ImageView();
	    		SystemSettingView.setLayoutX(8);
	    		SystemSettingView.setLayoutY(5);
	    		SystemSettingView.setImage(SystemSettingIamge);

	    		Label SystemSettingLabel = new Label();
	    		SystemSettingLabel.setLayoutX(33);
	    		SystemSettingLabel.setLayoutY(6);
	    		SystemSettingLabel.setText("系统设置");
	    		SystemSettingLabel.setId("SystemSettingLabel");

	    		TabPane SystemSettingTabPane = new TabPane();
	    		SystemSettingTabPane.setPrefWidth(500);
	    		SystemSettingTabPane.setPrefHeight(470);
	    		SystemSettingTabPane.setLayoutX(0);
	    		SystemSettingTabPane.setLayoutY(30);

	    		Tab DatabaseTab = new Tab();
	    		DatabaseTab.setText("数据库配置");
	    		DatabaseTab.setId("DatabaseTab");
	    		DatabaseTab.setClosable(false);
	    		DatabaseTab.setGraphic(new ImageView("/image/LoginPane/SystemSettingPane/Database20x20.png"));

	    		Label DJDBCDriverLabel = new Label();
	    		DJDBCDriverLabel.setTranslateX(20);
	    		DJDBCDriverLabel.setTranslateY(12);
	    		DJDBCDriverLabel.setText("数据库驱动：");
	    		DJDBCDriverLabel.setFont(new Font("微软雅黑", 13));

	    		DJDBCDriverField.setTranslateX(120);
	    		DJDBCDriverField.setTranslateY(10);
	    		DJDBCDriverField.setPrefWidth(360);
	    		DJDBCDriverField.setPrefHeight(20);
	    		DJDBCDriverField.setPromptText("请输入数据库驱动名称...");

	    		Label DHostAddressLabel = new Label();
	    		DHostAddressLabel.setTranslateX(20);
	    		DHostAddressLabel.setTranslateY(15+13+13);
	    		DHostAddressLabel.setText("主机地址(IP)：");
	    		DHostAddressLabel.setFont(new Font("微软雅黑", 13));

	    		DHostAddressField.setTranslateX(120);
	    		DHostAddressField.setTranslateY(20+10*2);
	    		DHostAddressField.setPrefWidth(360);
	    		DHostAddressField.setPrefHeight(20);
	    		DHostAddressField.setPromptText("请输入数据库主机地址...");

	    		Label DUserNameLabel = new Label();
	    		DUserNameLabel.setTranslateX(20);
	    		DUserNameLabel.setTranslateY(15+(13+15)*2);
	    		DUserNameLabel.setText("用户名：");
	    		DUserNameLabel.setFont(new Font("微软雅黑", 13));

	    		DUserNameField.setTranslateX(120);
	    		DUserNameField.setTranslateY(20+10*5);
	    		DUserNameField.setPrefWidth(360);
	    		DUserNameField.setPrefHeight(20);
	    		DUserNameField.setPromptText("请输入数据库用户名...");

	    		Label DPassWordLabel = new Label();
	    		DPassWordLabel.setTranslateX(20);
	    		DPassWordLabel.setTranslateY(18+(13+15)*3);
	    		DPassWordLabel.setText("密码：");
	    		DPassWordLabel.setFont(new Font("微软雅黑", 13));

	    		DPassWordField.setTranslateX(120);
	    		DPassWordField.setTranslateY(20+10*8);
	    		DPassWordField.setPrefWidth(360);
	    		DPassWordField.setPrefHeight(20);
	    		DPassWordField.setPromptText("请输入数据库密码...");

	    		Label DPortLabel = new Label();
	    		DPortLabel.setTranslateX(20);
	    		DPortLabel.setTranslateY(21+(13+15)*4);
	    		DPortLabel.setText("端口：");
	    		DPortLabel.setFont(new Font("微软雅黑", 13));

	    		DPortField.setTranslateX(120);
	    		DPortField.setTranslateY(20+10*11);
	    		DPortField.setPrefWidth(360);
	    		DPortField.setPrefHeight(20);
	    		DPortField.setPromptText("请输入数据库端口...");

	    		Label DDataBaseLabel = new Label();
	    		DDataBaseLabel.setTranslateX(20);
	    		DDataBaseLabel.setTranslateY(23+(13+15)*5);
	    		DDataBaseLabel.setText("数据库：");
	    		DDataBaseLabel.setFont(new Font("微软雅黑", 13));

	    		DDataBaseField.setTranslateX(120);
	    		DDataBaseField.setTranslateY(20+10*14);
	    		DDataBaseField.setPrefWidth(360);
	    		DDataBaseField.setPrefHeight(20);
	    		DDataBaseField.setPromptText("请输入要连接的数据库名...");

	    		AnchorPane DDatabasePane = new AnchorPane();
	    		DDatabasePane.getChildren().addAll(DJDBCDriverLabel,DJDBCDriverField);
	    		DDatabasePane.getChildren().addAll(DHostAddressLabel,DHostAddressField);
	    		DDatabasePane.getChildren().addAll(DUserNameLabel,DUserNameField);
	    		DDatabasePane.getChildren().addAll(DPassWordLabel,DPassWordField);
	    		DDatabasePane.getChildren().addAll(DPortLabel,DPortField);
	    		DDatabasePane.getChildren().addAll(DDataBaseLabel,DDataBaseField);

	    		DatabaseTab.setContent(DDatabasePane);

	    		Tab ServerTab = new Tab();
	    		ServerTab.setText("服务器配置");
	    		ServerTab.setId("ServerTab");
	    		ServerTab.setClosable(false);
	    		ServerTab.setGraphic(new ImageView("/image/LoginPane/SystemSettingPane/Server20x20.png"));

	    		Label SHostAddressLabel = new Label();
	    		SHostAddressLabel.setTranslateX(20);
	    		SHostAddressLabel.setTranslateY(12);
	    		SHostAddressLabel.setText("主机地址(IP)");
	    		SHostAddressLabel.setFont(new Font("微软雅黑", 13));

	    		SHostAddressField.setTranslateX(120);
	    		SHostAddressField.setTranslateY(10);
	    		SHostAddressField.setPrefWidth(360);
	    		SHostAddressField.setPrefHeight(20);
	    		SHostAddressField.setPromptText("请输入服务器主机地址...");

	    		Label SPortLabel = new Label();
	    		SPortLabel.setTranslateX(20);
	    		SPortLabel.setTranslateY(15+13+13);
	    		SPortLabel.setText("端口");
	    		SPortLabel.setFont(new Font("微软雅黑", 13));

	    		SPortField.setTranslateX(120);
	    		SPortField.setTranslateY(20+10*2);
	    		SPortField.setPrefWidth(360);
	    		SPortField.setPrefHeight(20);
	    		SPortField.setPromptText("请输入服务器端口...");

	    		Label SUserNameLabel = new Label();
	    		SUserNameLabel.setTranslateX(20);
	    		SUserNameLabel.setTranslateY(15+(13+15)*2);
	    		SUserNameLabel.setText("用户名：");
	    		SUserNameLabel.setFont(new Font("微软雅黑", 13));

	    		SUserNameField.setTranslateX(120);
	    		SUserNameField.setTranslateY(20+10*5);
	    		SUserNameField.setPrefWidth(360);
	    		SUserNameField.setPrefHeight(20);
	    		SUserNameField.setPromptText("请输入服务器用户名...");

	    		Label SPassWordLabel = new Label();
	    		SPassWordLabel.setTranslateX(20);
	    		SPassWordLabel.setTranslateY(18+(13+15)*3);
	    		SPassWordLabel.setText("密码：");
	    		SPassWordLabel.setFont(new Font("微软雅黑", 13));

	    		SPassWordField.setTranslateX(120);
	    		SPassWordField.setTranslateY(20+10*8);
	    		SPassWordField.setPrefWidth(360);
	    		SPassWordField.setPrefHeight(20);
	    		SPassWordField.setPromptText("请输入服务器密码...");

	    		Label SKeyLabel = new Label();
	    		SKeyLabel.setTranslateX(20);
	    		SKeyLabel.setTranslateY(21+(13+15)*4);
	    		SKeyLabel.setText("私钥：");
	    		SKeyLabel.setFont(new Font("微软雅黑", 13));

	    		SKeyField.setTranslateX(120);
	    		SKeyField.setTranslateY(20+10*11);
	    		SKeyField.setPrefWidth(360);
	    		SKeyField.setPrefHeight(20);
	    		SKeyField.setPromptText("请输入服务器私钥路径...");

	    		Label SApiLabel = new Label();
	    		SApiLabel.setTranslateX(20);
	    		SApiLabel.setTranslateY(21+(13+15)*5);
	    		SApiLabel.setText("接口地址：");
	    		SApiLabel.setFont(new Font("微软雅黑", 13));

	    		SApiField.setTranslateX(120);
	    		SApiField.setTranslateY(20+10*14);
	    		SApiField.setPrefWidth(360);
	    		SApiField.setPrefHeight(20);
	    		SApiField.setPromptText("请输入接口地址...");

	    		AnchorPane  ServerPane = new AnchorPane();
	    		ServerPane.getChildren().addAll(SHostAddressLabel,SHostAddressField);
	    		ServerPane.getChildren().addAll(SPortLabel,SPortField);
	    		ServerPane.getChildren().addAll(SUserNameLabel,SUserNameField);
	    		ServerPane.getChildren().addAll(SPassWordLabel,SPassWordField);
	    		ServerPane.getChildren().addAll(SKeyLabel,SKeyField);
	    		ServerPane.getChildren().addAll(SApiLabel,SApiField);

	    		ServerTab.setContent(ServerPane);

	    		SystemSettingTabPane.getTabs().addAll(DatabaseTab,ServerTab);

	    		ComboBox DatabaseTypeBox = new ComboBox();
	    		DatabaseTypeBox.setLayoutX(198);
	    		DatabaseTypeBox.setLayoutY(35);
	    		DatabaseTypeBox.setPrefWidth(100);
	    		DatabaseTypeBox.setPrefHeight(26);
	    		DatabaseTypeBox.getItems().addAll("Oracle","MySql","Server");
	    		DatabaseTypeBox.setValue("MySql");
	    		DatabaseTypeBox.setTooltip(new Tooltip("请选择数据库类型..."));
	    		DatabaseTypeBox.setEditable(false);
	    		DatabaseTypeBox.setOnAction((Event ev) -> {
	    			String DatabaseType =  DatabaseTypeBox.getSelectionModel().getSelectedItem().toString();
	    			if("Oracle".equals(DatabaseType)){
						String djdbcDriver = "oracle.jdbc.driver.OracleDriver";
		    			String dhostAddress = "idcdbtest.dafycredit.com";
		    			String duserName = "dafy_sales";
		    			String dpassWord = "Ju$2017";
		    			String dport = "3306";
		    			String ddataBase = "dbtest01";
						DJDBCDriverField.setText(djdbcDriver);
		        		DHostAddressField.setText(dhostAddress);
		        		DUserNameField.setText(duserName);
		        		DPassWordField.setText(dpassWord);
		        		DPortField.setText(dport);
		        		DDataBaseField.setText(ddataBase);
					}else if("MySql".equals(DatabaseType)){
						String djdbcDriver = "com.mysql.jdbc.Driver";
		    			String dhostAddress = "rm-wz99130zn032n90h47v.mysql.rds.aliyuncs.com";
		    			String duserName = "jqebdaev";
		    			String dpassWord = "JQeBdebv1qaez";
		    			String dport = "3306";
		    			String ddataBase = "travaele_test";
						DJDBCDriverField.setText(djdbcDriver);
		        		DHostAddressField.setText(dhostAddress);
		        		DUserNameField.setText(duserName);
		        		DPassWordField.setText(dpassWord);
		        		DPortField.setText(dport);
		        		DDataBaseField.setText(ddataBase);
					}else if("Server".equals(DatabaseType)){
						String shostaddress = "45.126.139.225";
		    			String sport = "22";
		    			String susername = "liuzhi";
		    			String spassword = "JQBdevea@13qaz24wsx";
		    			String skey = "src/main/resources/Keya/id_rsa";
		    			String sapi = "www.travele.szjqnb.net";
		    			SHostAddressField.setText(shostaddress);
		    			SPortField.setText(sport);
		    			SUserNameField.setText(susername);
		    			SPassWordField.setText(spassword);
		    			SKeyField.setText(skey);
		    			SApiField.setText(sapi);
					}
	            });

	    		ComboBox DatabaseConnectionModeBox = new ComboBox();
	    		DatabaseConnectionModeBox.setLayoutX(300);
	    		DatabaseConnectionModeBox.setLayoutY(35);
	    		DatabaseConnectionModeBox.setPrefWidth(130);
	    		DatabaseConnectionModeBox.setPrefHeight(26);
	    		DatabaseConnectionModeBox.getItems().addAll("Oracle","MySql","SHHKeyMySql","SHHPassWordMySql");
	    		DatabaseConnectionModeBox.setValue("SHHKeyMySql");
	    		DatabaseConnectionModeBox.setTooltip(new Tooltip("请选择数据库连接方式..."));
	    		DatabaseConnectionModeBox.setEditable(false);
	    		DatabaseConnectionModeBox.setOnAction((Event ev) -> {

	            });

	    		Button ConnectionTestButton = new Button();
	    		ConnectionTestButton.setLayoutX(0);
	    		ConnectionTestButton.setLayoutY(500);
	    		ConnectionTestButton.setPrefHeight(30);
	    		ConnectionTestButton.setPrefWidth(500);
	    		ConnectionTestButton.setText("连 接 测 试");
	    		ConnectionTestButton.setId("ConnectionTestButton");
	    		ConnectionTestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    		    @Override
	    		    public void handle(MouseEvent event) {
	    		    	String DatabaseConnectionMode =  DatabaseConnectionModeBox.getSelectionModel().getSelectedItem().toString();

	    		    	String djdbcdriver = DJDBCDriverField.getText();
	    	    		String dhostaddress = DHostAddressField.getText();
	    	    		String dusername = DUserNameField.getText();
	    	    		String dpassword = DPassWordField.getText();
	    	    		String dport1 = DPortField.getText();
	    	    		String ddatabase = DDataBaseField.getText();

	    	    		String shostaddress = SHostAddressField.getText();
	            		String sport = SPortField.getText();
	            		String susername = SUserNameField.getText();
	            		String spassword = SPassWordField.getText();
	            		String skey = SKeyField.getText();

	    	    		if (djdbcdriver == null || "".endsWith(djdbcdriver)) {
	    	                Dialog.SetMessageDialog("Warning","请输入数据库驱动！");
	    	    		}else if (dhostaddress == null || "".endsWith(dhostaddress)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库主机地址！");
	    	    		}else if (dusername == null || "".endsWith(dusername)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库用户名！");
	    	    		}else if (dpassword == null || "".endsWith(dpassword)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库密码！");
	    	    		}else if (dport1 == null || "".endsWith(dport1)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入数据库端口！");
	    	    		}else if (ddatabase == null || "".endsWith(ddatabase)) {
	    	    			Dialog.SetMessageDialog("Warning","请输入要连接的数据库名！");
	    	    		}else if (shostaddress == null || "".endsWith(shostaddress)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器主机地址！");
	            		}else if (sport == null || "".endsWith(sport)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器端口！");
	            		}else if (susername == null || "".endsWith(susername)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器用户名！");
	            		}else if (spassword == null || "".endsWith(spassword)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器密码！");
	            		}else if (skey == null || "".endsWith(skey)) {
	            			Dialog.SetMessageDialog("Warning","请输入服务器私钥路径！");
	            		}else if("Oracle".equals(DatabaseConnectionMode)){
		    				String DJDBCDriver = DJDBCDriverField.getText();
		    	    		String DHostAddress = DHostAddressField.getText();
		    	    		int DPort = Integer.parseInt(DPortField.getText());
		    	    		String DDataBase = DDataBaseField.getText();
		    	    		String DUserName = DUserNameField.getText();
		    	    		String DPassWord = DPassWordField.getText();
		    		    	try {
									DatabaseUtil.Connect_Oracle(DJDBCDriver, DHostAddress, DPort, DDataBase,DUserName, DPassWord);
							} catch (Exception e) {
									e.printStackTrace();
							}
						}else if("MySql".equals(DatabaseConnectionMode)){
							String DJDBCDriver = DJDBCDriverField.getText();
		    	    		String DHostAddress = DHostAddressField.getText();
		    	    		int DPort = Integer.parseInt(DPortField.getText());
		    	    		String DDataBase = DDataBaseField.getText();
		    	    		String DUserName = DUserNameField.getText();
		    	    		String DPassWord = DPassWordField.getText();
		    		    	try {
									DatabaseUtil.Connect_MySql(DJDBCDriver, DHostAddress, DPort, DDataBase,DUserName, DPassWord);
							} catch (Exception e) {
									e.printStackTrace();
							}
						}else if("SHHKeyMySql".equals(DatabaseConnectionMode)){
							String SKey = SKeyField.getText();
				    		String SUserName = SUserNameField.getText();
				    		String SHostAddress = SHostAddressField.getText();
				    		int SPort = Integer.parseInt(SPortField.getText());
				    		String DHostAddress = DHostAddressField.getText();
				    		int DPort = Integer.parseInt(DPortField.getText());
				    		String DJDBCDriver = DJDBCDriverField.getText();
				    		String DUserName = DUserNameField.getText();
				    		String DPassWord = DPassWordField.getText();
				    		String DDataBase = DDataBaseField.getText();
		    		    	try {
		    		    		    DatabaseUtil.Connect_SSHKeyMySql(SKey, SUserName, SHostAddress, SPort, DHostAddress, DPort, DJDBCDriver, DDataBase,DUserName, DPassWord);
							} catch (Exception e) {
									e.printStackTrace();
							}
						}else if("SHHPassWordMySql".equals(DatabaseConnectionMode)){
				    		String SUserName = SUserNameField.getText();
				    		String SHostAddress = SHostAddressField.getText();
				    		int SPort = Integer.parseInt(SPortField.getText());
				    		String SPassWord = SPassWordField.getText();
				    		String DHostAddress = DHostAddressField.getText();
				    		int DPort = Integer.parseInt(DPortField.getText());
				    		String DJDBCDriver = DJDBCDriverField.getText();
				    		String DDataBase = DDataBaseField.getText();
				    		String DUserName = DUserNameField.getText();
				    		String DPassWord = DPassWordField.getText();
		    		    	try {
		    		    		    DatabaseUtil.Connect_SSHPassWordMySql(SUserName, SHostAddress, SPort, SPassWord,DHostAddress, DPort, DJDBCDriver, DDataBase,DUserName, DPassWord);
							} catch (Exception e) {
									e.printStackTrace();
							}
						}
	    		    }
	    		});

	    		SystemSettingPane.getChildren().add(MinCloseHox);
	    		SystemSettingPane.getChildren().add(SystemSettingView);
	    		SystemSettingPane.getChildren().add(SystemSettingLabel);
	    		SystemSettingPane.getChildren().add(SystemSettingTabPane);
	    		SystemSettingPane.getChildren().add(DatabaseTypeBox);
	    		SystemSettingPane.getChildren().add(ConnectionTestButton);
	    		SystemSettingPane.getChildren().add(DatabaseConnectionModeBox);

	        	LoginStack.getChildren().addAll(SystemSettingPane);

	    		//拖动监听器
	        	DragUtil.addDragListener(SystemSettingStage,SystemSettingTabPane);
	        	DragUtil.addDragListener(SystemSettingStage,SystemSettingPane);

	    		//构建一个场景Scene,加载包含根布局的场景
	    		Scene scene = new Scene(LoginStack);
	    		//Scene透明
	    		scene.setFill(null);

	    		//scene加载到新的Stage中
	    		SystemSettingStage.setScene(scene);
	            //设置让对话框处于屏幕中间
	    		SystemSettingStage.centerOnScreen();
	            //显示布局
	    		SystemSettingStage.show();
        }

        public void LoadingInterface() {
	            Stage LoadingStage = new Stage();
	            LoadingStage.initStyle(StageStyle.TRANSPARENT);
	            LoadingStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//获取resource下images中的图片

	            StackPane LoadingStack = new StackPane();
	            LoadingStack.getStylesheets().add(this.getClass().getResource("/css/login.css").toString());

       	        AnchorPane LoadingPane = new AnchorPane();
//       	        LoadingPane.setPrefWidth(1500);
//       	        LoadingPane.setPrefHeight(844);
       	        LoadingPane.setId("LoadingBackground");

       	        Region LoadingBackground = new Region();
       	        LoadingBackground.setId("LoadingBackground");

	    		String Loading = this.getClass().getResource("/image/LoginPane/Loading/DogLoding1920x1080.gif").toExternalForm();
	    		Image LoadingIamge = new Image(Loading, 1920,1080,true, false);
	    		ImageView LoadingView = new ImageView();
	    		LoadingView.setLayoutX(0);
	    		LoadingView.setLayoutY(0);
	    		LoadingView.setImage(LoadingIamge);

	    		String LoadingSucceeded = this.getClass().getResource("/image/LoginPane/Loading/Succeeded.png").toExternalForm();
	    		Image LoadingSucceededIamge = new Image(LoadingSucceeded, 30,30,true, false);
	    		ImageView LoadingSucceededIamgeView = new ImageView();
	    		LoadingSucceededIamgeView.setLayoutX(690);
	    		LoadingSucceededIamgeView.setLayoutY(675);
	    		LoadingSucceededIamgeView.setImage(LoadingSucceededIamge);
	    		LoadingSucceededIamgeView.setVisible(false);

	    		Label LoadingLabel = new Label("0%");
//	    		LoadingLabel.setLayoutX(1200);
//	    		LoadingLabel.setLayoutY(500);
	    		LoadingLabel.setLayoutX(1560);
	    		LoadingLabel.setLayoutY(650);
	    		LoadingLabel.setId("LoadingLabel");
	    		LoadingLabel.setTextFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.REPEAT, new
	    	            Stop[]{new Stop(0, Color.AQUAMARINE), new Stop(0.5f, Color.BLANCHEDALMOND)}));

	    		Button EnterTheSystemButton = new Button();
//	    		EnterTheSystemButton.setLayoutX(1123);
//	    		EnterTheSystemButton.setLayoutY(600);
	    		EnterTheSystemButton.setLayoutX(1490);
	    		EnterTheSystemButton.setLayoutY(760);
	    		EnterTheSystemButton.setPrefWidth(185);
	    		EnterTheSystemButton.setPrefHeight(70);
	    		EnterTheSystemButton.setId("EnterTheSystemButton");
	    		EnterTheSystemButton.setVisible(false);
	    		EnterTheSystemButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    	  	    @Override
	    	  	    public void handle(MouseEvent event) {
	    	  	    	LoadingStage.close();
	    	  	    	HeadImageView.setLayoutX(40);
    	    			HeadImageView.setLayoutY(194);
    	    			AccountPane.setVisible(true);
    	    			PassWordPane.setVisible(true);
    	    			RegisteredLabel.setVisible(true);
    	    			ForgetPassWordLabel.setVisible(true);
    	    			RememberPassword.setVisible(true);
    	    			AutomaticLogon.setVisible(true);
    	    			NameLabel.setVisible(false);
    	    			LoginButton.setLayoutX(135);
    	    			LoginButton.setText("登  录");
	    	  	    	try {
	    	  	    		   String Account = AccountField.getText();
	    	      		       String PassWord = PassWordField.getText();
	    	      		       if(LoginStatus==0){
	    	      		    	 HomePageView.HomePageInterface(Account,PassWord);
	    	      		       }else{
								   HomePageView.AgainLogin(Account,PassWord);
	    	      		       }
						} catch (Exception e) {
							e.printStackTrace();
						}
	    	  	    }
	    	  	});

	    		Task<Void> ProgressTask = new Task<Void>(){
	                @Override
	                protected void succeeded(){
	                    super.succeeded();
	                    EnterTheSystemButton.setVisible(true);
	                }

//	                @Override
//	                protected void cancelled() {
//	                    super.cancelled();
//	                    updateMessage("Cancelled");
//	                }
//
//	                @Override
//	                protected void failed() {
//	                    super.failed();
//	                    updateMessage("Failed");
//	                }

	                @Override
	                protected Void call() throws Exception {
	                    for(int i = 0; i < 100; i++){
	                        Thread.sleep(20);
	                        updateMessage((i + 1) + "%");
	                        updateProgress(i + 1, 100);
	                    }
						return null;
	                }
	            };

       	        LoadingPane.getChildren().add(LoadingView);
//       	        LoadingPane.getChildren().add(LoadingSucceededIamgeView);
       	        LoadingPane.getChildren().add(LoadingLabel);
       	        LoadingPane.getChildren().add(EnterTheSystemButton);
//	    		LoginPane.getChildren().addAll(LogoWebView,LogoImageView,MinCloseHox);

	        	Rectangle LoadingRectangle = new Rectangle();
	        	LoadingRectangle.setWidth(1500);
	        	LoadingRectangle.setHeight(844);
	        	LoadingRectangle.setArcHeight(20);
	        	LoadingRectangle.setArcWidth(20);

	        	LoadingStack.getChildren().addAll(LoadingBackground);
	        	LoadingStack.getChildren().addAll(LoadingPane);
//	        	LoadingStack.setClip(LoadingRectangle);

	        	LoadingBackground.visibleProperty().bind(ProgressTask.runningProperty());
	        	LoadingLabel.textProperty().bind(ProgressTask.messageProperty());
	        	new Thread(ProgressTask).start();

	    		//构建一个场景Scene,加载包含根布局的场景
	    		Scene scene = new Scene(LoadingStack);
	    		//Scene透明
	    		scene.setFill(null);

	    		//scene加载到新的Stage中
	    		LoadingStage.setScene(scene);
	            //设置让对话框处于屏幕中间
	    		LoadingStage.centerOnScreen();
	    		LoadingStage.setMaximized(true);
	            //显示布局
	    		LoadingStage.show();
        }

        private void initTrayIcon() {
    		trayIcon = new OnlyTrayIcon(image, "自动化测试系统");
    		trayIcon.setMenu(trayPopupMenu);
    		trayPopupMenu.showAll(true);
    	}

        private void initTray() {
    		try {
    			if (SystemTray.isSupported()) {
    				SystemTray tray = SystemTray.getSystemTray();
    				tray.add(trayIcon);
    			}
    		} catch (AWTException ex) {
    			Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
    		}
    	}

    	private void initEvent() {
    		//任务栏图标单击事件
    		trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
    			@Override
    			public void mouseClicked(java.awt.event.MouseEvent event) {
    				if (event.getClickCount() == 1 && event.getButton() == 1) {
    	    			Platform.runLater(new Runnable() {
							@Override
    				        public void run() {
    				        	if(LoginInterfaceStageStatus==1){
    		    				   LoginInterfaceStage.show();
    				             }else{
    				            	 HomePageView.HomePageInterfaceStage.show();
    				             }
    				        }
    				   });
    	    		}
    			}
    		});

    		//点击系统设置选项事件
    		trayPopupMenu.addSystemSetupActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				Platform.runLater(new Runnable() {
						@Override
    			        public void run() {
    			        	if(SystemSettingStageStatus==1){
    			        		Dialog.SetMessageDialog("Warning","系统设置窗口已开启，请关闭后重试！");
        			        	 return;
    			        	}else if(SystemSettingStageStatus==0){
    			        		if(LoginInterfaceStageStatus==1){
      		    				   LoginInterfaceStage.close();
      				             }else{
      				            	 HomePageView.HomePageInterfaceStage.close();
      				             }
    			        		SystemSettingInterface();
    			        		SystemSettingStageStatus=1;
    			        	}
    			        }
    			   });
    			}
    		});

    		//点击打开主面板选项事件
    		trayPopupMenu.addOpenMainActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				Platform.runLater(new Runnable() {
						@Override
    			        public void run() {
							if(SystemSettingStageStatus==1){
								 SystemSettingStage.close();
								 SystemSettingStageStatus=0;
	 				        }
							if(LoginInterfaceStageStatus==1){
 		    				     LoginInterfaceStage.show();
 				            }else{
 				            	 HomePageView.HomePageInterfaceStage.show();
 				            }
    			        }
    			   });
    			}
    		});

    		//点击退出选项事件
    		trayPopupMenu.addExitActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				System.exit(0);
    			}
    		});
    	}

        //登录按钮处理事件
    	private void ALogin() throws Exception{
    		  try{
    			  if("登录中...".equals(NameLabel.getText())){
    			      String Account = AccountField.getText();
    	    		  String PassWord = PassWordField.getText();
    	    		  boolean RememberPasswordStatus = RememberPassword.isSelected();
    	    		  boolean AutomaticLogonStatus = AutomaticLogon.isSelected();
//    	    		  String Message = LoginController.WebUserLogin(Account, PassWord);
    	    		  String Message = "OK";//无验证
    	    		  if("OK".equals(Message)){
//    	    			  Dialog.SetMessageDialog("Success","登录成功！");
//    	        		  Object[] UserLoginInfo =LoginController.WebUserLoginInfo(Account, PassWord);
//    	        		  String Avatar = (String) UserLoginInfo[1];
    	    			  UserSaveData usd1 = usdb.get(Account);
    	  	    		  String Avatar = usd1.getHead();
    	        		  Image HeadIamge = new Image(Avatar, 80,80,true, false);
    	        		  setHeadImage(HeadIamge);
    	  	    		  int size = usdb.getSize();// 获取登录保存的用户账号数量
    	  	    		  if (size > 20) {// 默认只保存20个用户的数量，多于20个，则删除多余的。
    	  	    			  usdb.remove((size - 20));
    	  	    	      }
    	        		  UserSaveData usd = new UserSaveData();
    	        		  usd.setHead(Avatar);
    	        		  usd.setAccount(Account);
    	        		  if(RememberPassword.isSelected()){
    	            		  usd.setPassWord(PassWord);
    	        		  }
    	        		  usd.setRememberPasswordStatus(RememberPasswordStatus);
    	        		  usd.setAutomaticLogonStatus(AutomaticLogonStatus);
    	        		  usdb.put(Account, usd);
    	        		  ConfigManage.addOrUpdate(UserSaveDataBox.path, usdb);
      	    			  AccountField.setText(Account);
  						  PassWordField.setText(PassWord);
    	        		  Platform.runLater(new Runnable() {
    	        			    @Override
    	        			    public void run() {
    	        		        	  LoginInterfaceStage.close();
    	      	    	  	    	  LoginInterfaceStageStatus=0;
    	        		        	  LoadingInterface();
    	        			    }
    	        		   });
    	    		  }else{
    	    			  Platform.runLater(new Runnable() {
    	    				    @Override
    	    				    public void run() {
    	    		    			  Dialog.SetMessageDialog("Error",Message+"！");
    	    				    }
    	    				});
    	    		  }
    	    	  }else{
    	    		  System.out.println(NameLabel.getText());
    	    	  }
    		  } catch (Exception e) {
  		 		Platform.runLater(new Runnable() {
  	   			    @Override
  	   			    public void run() {
  	   	    			Dialog.SetMessageDialog("Warning","服务器连接失败，请稍后重试！");
  	   			    }
  	   			});
  			}
    	}

    	private class ColorRectCell extends ListCell<String> {
    	    @Override
    	    protected void updateItem(String item, boolean empty) {
    	        super.updateItem(item, empty);

//    			if (null != usdb && null != usdb.getMap() && !usdb.getMap().isEmpty()) {
//    			Map<String, UserSaveData> map = usdb.getMap();
//    			List<UserSaveData> list = new ArrayList<UserSaveData>(map.values());
//    			UserSaveData ud = list.get(0);
//    			for (UserSaveData u : list) {
//    			System.out.println("Account："+u.getAccount());
//    			System.out.println("Head："+u.getHead());
//    			}}

    			if (item != null) {
    	        UserSaveData ud = usdb.get(item);
    	        Image HeadIamge = new Image(ud.getHead(), 80,80,true, false);
//    	        Image HeadIamge = new Image("https://xiyuyou-dev-public.oss-cn-shanghai.aliyuncs.com/images/2018-06-23/5b2e002b942b5.jpg", 80,80,true, false);
    	        ImageView imageView = new ImageView();
    	        imageView.setFitWidth(30);
  	    	    imageView.setFitHeight(30);
    	        imageView.setImage(HeadIamge);
    	        imageView.preserveRatioProperty().set(true);

  	    	    Button AccountListViewDeleteButton = new Button();
    	        AccountListViewDeleteButton.setLayoutX(140);
    	        AccountListViewDeleteButton.setLayoutY(3);
    	        AccountListViewDeleteButton.setPrefHeight(20);
    	        AccountListViewDeleteButton.setPrefWidth(20);
    	        AccountListViewDeleteButton.setId("AccountListViewDeleteButtonX");
    	        AccountListViewDeleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						usdb.remove(item);
						ConfigManage.addOrUpdate(UserSaveDataBox.path, usdb);
						AccountListView.setVisible(false);
						AccountOnButton.setVisible(false);
	    	  	    	AccountOffButton.setVisible(true);
	    	  	    	Dialog.SetMessageDialog("Success","删除成功，重启后生效！");
					}
	    		});

    	        Label NameLabel = new Label();
    	        NameLabel.setText(ud.getAccount());
//    	        NameLabel.setText("1306086303");
  	    	    NameLabel.setGraphic(imageView);
  	    	    NameLabel.setOnMouseMoved(new EventHandler<MouseEvent>() {
    	  	        @Override
    	  	        public void handle(MouseEvent event) {
    	  	      	     imageView.setFitWidth(40);
    	  	    	     imageView.setFitHeight(40);
    	  	    	     AccountListViewDeleteButton.setId("AccountListViewDeleteButtonD");
    	    	         AccountListViewDeleteButton.setLayoutY(8);
    	  	      }
    	  	    });
  	    	    NameLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
    	    	    @Override
    	    	    public void handle(MouseEvent event) {
    	    	     	 imageView.setFitWidth(30);
    	    	    	 imageView.setFitHeight(30);
    	    	    	 AccountListViewDeleteButton.setId("AccountListViewDeleteButtonX");
    	    	    	 AccountListViewDeleteButton.setLayoutY(3);
    	    	    }
    	    	});

    	        AnchorPane AccountListViewPane = new AnchorPane();
  	    	    AccountListViewPane.getChildren().addAll(NameLabel, AccountListViewDeleteButton);
    	        setGraphic(AccountListViewPane);
    		    }
            }
    	}

    	public <T> void addAccountChangeListener(ChangeListener<String> listener) {
			    AccountField.textProperty().addListener(listener);
		}

    	public void setHeadImage(Image image) {
    		HeadImageView.setImage(image);
    	}

    	public String getAccount(String account) {
//    		AccountField.setText(account);
//    		String Account =AccountField.getText();
    		return account;
    	}

    	public void TaskbarIcon() {
            initTrayIcon();
    		initTray();
    		initEvent();
    	}

        public static void main(String[] args) {
    	        launch(args);
        }
}