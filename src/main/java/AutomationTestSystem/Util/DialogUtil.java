package AutomationTestSystem.Util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArraySet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings("restriction")
public class DialogUtil {

		CopyOnWriteArraySet<EventHandler<ActionEvent>> closeActionSet = new CopyOnWriteArraySet<EventHandler<ActionEvent>>();

		public static void SetInformation(String ContentText){
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("『By：小智出品，必属精品』");
    		alert.setHeaderText("Look, an Information Dialog");
    		alert.setContentText(ContentText);
    		alert.showAndWait();
    	}

    	public static void SetInformationNull(String ContentText){
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("『By：小智出品，必属精品』");
    		alert.setHeaderText(null);
    		alert.setContentText(ContentText);
    		alert.showAndWait();
    	}

    	public static void SetWarning(String ContentText){
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("『By：小智出品，必属精品』");
    		alert.setHeaderText("Look, a Warning Dialog");
    		alert.setContentText(ContentText);
    		alert.showAndWait();
    	}

    	public static void SetError(String ContentText){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("『By：小智出品，必属精品』");
    		alert.setHeaderText("Look, an Error Dialog");
    		alert.setContentText(ContentText);
    		alert.showAndWait();
    	}

    	public static void SetException(String ContentText){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("『By：小智出品，必属精品』");
    		alert.setHeaderText("Look, an Exception Dialog");
    		alert.setContentText(ContentText);

    		Exception ex = new FileNotFoundException(ContentText);

    		// Create expandable Exception.
    		StringWriter sw = new StringWriter();
    		PrintWriter pw = new PrintWriter(sw);
    		ex.printStackTrace(pw);
    		String exceptionText = sw.toString();

    		Label label = new Label("The exception stacktrace was:");

    		TextArea textArea = new TextArea(exceptionText);
    		textArea.setEditable(false);
    		textArea.setWrapText(true);

    		textArea.setMaxWidth(Double.MAX_VALUE);
    		textArea.setMaxHeight(Double.MAX_VALUE);
    		GridPane.setVgrow(textArea, Priority.ALWAYS);
    		GridPane.setHgrow(textArea, Priority.ALWAYS);

    		GridPane expContent = new GridPane();
    		expContent.setMaxWidth(Double.MAX_VALUE);
    		expContent.add(label, 0, 0);
    		expContent.add(textArea, 0, 1);

    		// Set expandable Exception into the dialog pane.
    		alert.getDialogPane().setExpandableContent(expContent);

    		alert.showAndWait();
    	}

    	public static void SetConfirmation(String ContentText){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("『By：小智出品，必属精品』");
    		alert.setHeaderText("Look, a Confirmation Dialog");
    		alert.setContentText(ContentText);

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    // ... user chose OK
    		} else {
    		    // ... user chose CANCEL or closed the dialog
    		}
    	}

    	public static void SetTextInput(String ContentText){
    		TextInputDialog dialog = new TextInputDialog();
    		dialog.setTitle("『By：小智出品，必属精品』");
    		dialog.setHeaderText("Look, a Text Input Dialog");
    		dialog.setContentText("Please enter your name:");

    		// Traditional way to get the response value.
    		Optional<String> result = dialog.showAndWait();
    		if (result.isPresent()){
    		    System.out.println("Your name: " + result.get());
    		}

    		// The Java 8 way to get the response value (with lambda expression).
    		result.ifPresent(name -> System.out.println("Your name: " + name));
    	}

    	public void SetMessageDialog(String MessageType,String ContentText){
    		Stage MessageDialogStage = new Stage();
    		MessageDialogStage.initStyle(StageStyle.TRANSPARENT);
    		MessageDialogStage.getIcons().add(new Image("image/LoginPane/Logo/Logo_ico.png"));//获取resource下images中的图片

    		StackPane MessageDialogStack = new StackPane();
    		MessageDialogStack.getStylesheets().add(this.getClass().getResource("/css/dialog.css").toString());

    		AnchorPane MessageDialogTopPane = new AnchorPane();
    		MessageDialogTopPane.setLayoutX(0);
    		MessageDialogTopPane.setLayoutY(0);
    		MessageDialogTopPane.setPrefWidth(335);
    		MessageDialogTopPane.setPrefHeight(30);
    		MessageDialogTopPane.setBackground(Background.EMPTY);
    		MessageDialogTopPane.setId("MessageDialogTopPane");

    		Image MessageDialogIamge = new Image(this.getClass().getResource("/image/MessageDialogPane/Ico/Tips.png").toExternalForm(), true);
    		ImageView MessageDialogView = new ImageView();
    		MessageDialogView.setLayoutX(8);
    		MessageDialogView.setLayoutY(5);
    		MessageDialogView.setImage(MessageDialogIamge);

    		Label TipsLabel = new Label();
    		TipsLabel.setLayoutX(33);
    		TipsLabel.setLayoutY(8);
    		TipsLabel.setText("提示");
    		TipsLabel.setId("TipsText");
    		TipsLabel.setCursor(Cursor.HAND);

    		Button CloseButton = new Button();
    		CloseButton.setLayoutX(305);
    		CloseButton.setLayoutY(0);
    		CloseButton.setPrefWidth(30);
    		CloseButton.setPrefHeight(30);
    		CloseButton.setId("CloseButton");
    		CloseButton.setOnAction((ActionEvent actionEvent) -> {
                if (!closeActionSet.isEmpty()) {
                    for (EventHandler<ActionEvent> e : closeActionSet) {
                        e.handle(actionEvent);
                    }
                } else {
                	MessageDialogStage.close();
                }
            });

    		MessageDialogTopPane.getChildren().add(MessageDialogView);
    		MessageDialogTopPane.getChildren().add(TipsLabel);
    		MessageDialogTopPane.getChildren().add(CloseButton);

            Image Iamge = new Image(this.getClass().getResource("/image/MessageDialogPane/Ico/"+MessageType+".png").toExternalForm(), true);
        	ImageView ImageView = new ImageView();
        	ImageView.setLayoutX(30);
        	ImageView.setLayoutY(50);
        	ImageView.setImage(Iamge);

    		Label ContentTextLabel = new Label();
//    		System.out.println(ContentText.length());
    		if(ContentText.length()<18){
    			ContentTextLabel.setLayoutX(80);
        		ContentTextLabel.setLayoutY(62);
    		}else{
    			ContentTextLabel.setLayoutX(80);
        		ContentTextLabel.setLayoutY(55);
    		}
    		
    		ContentTextLabel.setText(ContentText);
    		ContentTextLabel.setPrefWidth(220);
    		ContentTextLabel.setWrapText(true);//设置自动换行
    		ContentTextLabel.setId("ContentText");

    		AnchorPane MessageDialogBottomPane = new AnchorPane();
    		MessageDialogBottomPane.setLayoutX(0);
    		MessageDialogBottomPane.setLayoutY(115);
    		MessageDialogBottomPane.setPrefWidth(335);
    		MessageDialogBottomPane.setPrefHeight(35);
    		MessageDialogBottomPane.setBackground(Background.EMPTY);
    		MessageDialogBottomPane.setId("MessageDialogBottomPane");

    		Button OKButton = new Button();
    		OKButton.setLayoutX(250);
    		OKButton.setLayoutY(120);
    		OKButton.setPrefWidth(70);
    		OKButton.setPrefHeight(10);
    		OKButton.setText("确定");
//    		SureButton.setId("SureButton");
    		OKButton.setOnAction((ActionEvent actionEvent) -> {
                if (!closeActionSet.isEmpty()) {
                    for (EventHandler<ActionEvent> e : closeActionSet) {
                        e.handle(actionEvent);
                    }
                } else {
                	MessageDialogStage.close();
                }
            });

//    		Button CancelButton = new Button();
//    		CancelButton.setLayoutX(250);
//    		CancelButton.setLayoutY(120);
//    		CancelButton.setPrefWidth(70);
//    		CancelButton.setPrefHeight(10);
//    		CancelButton.setText("取消");
//    		CancelButton.setVisible(false);
////    		CancelButton.setId("CancelButton");
//    		CancelButton.setOnAction((ActionEvent actionEvent) -> {
//                if (!closeActionSet.isEmpty()) {
//                    for (EventHandler<ActionEvent> e : closeActionSet) {
//                        e.handle(actionEvent);
//                    }
//                } else {
//                	MessageDialogStage.close();
//                }
//            });

    		AnchorPane MessageDialogPane = new AnchorPane();
    		MessageDialogPane.setPrefWidth(335);
    		MessageDialogPane.setPrefHeight(150);
    		MessageDialogPane.setBackground(Background.EMPTY);
    		MessageDialogPane.getChildren().addAll(MessageDialogTopPane);
    		MessageDialogPane.getChildren().add(ImageView);
    		MessageDialogPane.getChildren().add(ContentTextLabel);
    		MessageDialogPane.getChildren().addAll(MessageDialogBottomPane);
    		MessageDialogPane.getChildren().addAll(OKButton);
    		MessageDialogPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
    		    @Override
    		    public void handle(KeyEvent event) {
    		        if(event.getCode() == KeyCode.ENTER){
    		        	MessageDialogStage.close();
    		        }
    		    }
    		 });

        	Rectangle MessageDialogRectangle = new Rectangle();
        	MessageDialogRectangle.setWidth(335);
        	MessageDialogRectangle.setHeight(150);
        	MessageDialogRectangle.setArcHeight(5);
        	MessageDialogRectangle.setArcWidth(5);

            MessageDialogStack.getChildren().addAll(MessageDialogPane);
            MessageDialogStack.setClip(MessageDialogRectangle);

            //拖动监听器
    		DragUtil.addDragListener(MessageDialogStage,MessageDialogStack);

    		//构建一个场景Scene,加载包含根布局的场景
    		Scene scene = new Scene(MessageDialogStack);
    		//Scene透明
    		scene.setFill(null);

    		//scene加载到新的Stage中
    		MessageDialogStage.setScene(scene);
            //设置让对话框处于屏幕中间
    		MessageDialogStage.centerOnScreen();
            //显示布局
    		MessageDialogStage.show();
    	}
}
