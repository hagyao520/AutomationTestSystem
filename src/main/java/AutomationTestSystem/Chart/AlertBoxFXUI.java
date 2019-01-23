package AutomationTestSystem.Chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings({"restriction","unused"})
public class AlertBoxFXUI {
	private static Logger logger = LoggerFactory.getLogger(AlertBoxFXUI.class);
	private static String showInputDialog_input = null;
	private static String showOptionDialog_selected = null;
	private static int width = 360;
	private static int height = 176;
	public static int showInputDialog_Integer = 1;
	public static int showInputDialog_Number = 2;
	public static int showInputDialog_Integer_Positive = 3;
	public static int showInputDialog_Number_Positive = 4;
	public static int showInputDialog_String = 0;

	public AlertBoxFXUI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 错误信息提示框
	 * 
	 * @param title
	 * @param message
	 */
	public static void showMessageDialogError(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR, message);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.setResizable(false);
		alert.setTitle("消息"); // 设置标题，不设置默认标题为本地语言的alert
		alert.setHeaderText(title); // 设置头标题，默认标题为本地语言的alert
		logger.error("Alter showMessageDialogError,title=" + title + ",message=" + message);
		alert.showAndWait();
		alert.close();
	}

	/**
	 * 警告信息提示框
	 * 
	 * @param title
	 * @param message
	 */
	public static void showMessageDialogWarn(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING, message);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.setResizable(false);
		alert.setTitle("消息"); // 设置标题，不设置默认标题为本地语言的alert
		alert.setHeaderText(title); // 设置头标题，默认标题为本地语言的alert
		logger.warn("Alter showMessageDialogWarn,title=" + title + ",message=" + message);
		alert.showAndWait();
		alert.close();
	}

	/**
	 * 信息提示框
	 * 
	 * @param title
	 * @param message
	 */
	public static void showMessageDialog(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.setResizable(false);
		alert.setTitle("消息"); // 设置标题，不设置默认标题为本地语言的alert
		alert.setHeaderText(title); // 设置头标题，默认标题为本地语言的alert
		logger.info("Alter showMessageDialog,title=" + title + ",message=" + message);
		alert.showAndWait();
		alert.close();
	}

	/**
	 * 确认提示框
	 * 
	 * @param title
	 * @param message
	 * @return
	 */
	public static boolean showConfirmDialog(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.setResizable(false);
		alert.setTitle("确认框");
		alert.setHeaderText(title);
		Optional<ButtonType> result = alert.showAndWait();
		alert.close();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			logger.info("Alter showConfirmDialog ok,title=" + title + ",message=" + message);
			return true;
		} else {
			logger.info("Alter showConfirmDialog cancel,title=" + title + ",message=" + message);
			return false;
		}
	}

	/**
	 * 输入框
	 * 
	 * @param message
	 * @return
	 */
	public static String showInputDialog(String title, String message, int type) {
		showInputDialog_input = null;
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.setResizable(false);
		alert.setTitle("输入框");
		alert.setHeaderText(title);
		TextField textField = new TextField();
		if (type != showInputDialog_String) {
			textField.textProperty().addListener((observable, oldValue, newValue) -> {
				String regex = "[0-9]*";
				switch (type) {
//				public static int showInputDialog_Integer = 1;
//				public static int showInputDialog_Number = 2;
//				public static int showInputDialog_Integer_Positive = 3;
//				public static int showInputDialog_Number_Positive = 4;
				case 1:
					regex = "-?[0-9]+";
					break;
				case 2:
					regex = "^-?[0-9]+([.][0-9]+){0,1}$";
					break;
				case 3:
					regex = "[0-9]+";
					break;
				case 4:
					regex = "^[0-9]+([.][0-9]+){0,1}$";
					break;
				default:
					break;
				}
				if (!newValue.equals("") && !newValue.matches(regex)) {
					textField.setText(oldValue);
				}
			});
		}
		VBox vbox = new VBox();
		vbox.setSpacing(10);// 上下间距
		vbox.getChildren().addAll(textField);
		vbox.setAlignment(Pos.CENTER);
		alert.getDialogPane().setExpandableContent(vbox);
		alert.getDialogPane().setExpanded(true);
		Optional<ButtonType> result = alert.showAndWait();
		alert.close();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			showInputDialog_input = textField.getText().equals("") ? null : textField.getText();
			logger.info("Alter showInputDialog ok,input=" + showInputDialog_input + ",title=" + title + ",message="
					+ message);
		} else {
			logger.info("Alter showInputDialog cancel,title=" + title + ",message=" + message);
		}
		return showInputDialog_input;
	}

	/**
	 * 选项框
	 * 
	 * @param message
	 * @return
	 */
	public static String showOptionDialog(String title, String message, List<String> optionList) {
		showOptionDialog_selected = null;
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.setResizable(false);
		alert.setTitle("选项框");
		alert.setHeaderText(title);
		ToggleGroup group = new ToggleGroup();
		ScrollPane scrollPane = new ScrollPane();
		VBox vbox_radiobtns = new VBox();
		vbox_radiobtns.setSpacing(10);
		scrollPane.setContent(vbox_radiobtns);
		scrollPane.setPannable(true);
		scrollPane.setPrefHeight(150);
		scrollPane.setPadding(new Insets(5, 5, 5, 35));
		List<RadioButton> radioBtnList = new ArrayList<>();
		for (String option : optionList) {
			RadioButton radioButton = new RadioButton(option);
			radioButton.setToggleGroup(group);
			radioButton.setUserData(option);
			radioBtnList.add(radioButton);
			vbox_radiobtns.getChildren().add(radioButton);
		}
		if (group.getToggles().size() > 0) {
			group.getToggles().get(0).setSelected(true);// 默认第一个选中
		}
		alert.getDialogPane().setExpandableContent(scrollPane);
		alert.getDialogPane().setExpanded(true);
		Optional<ButtonType> result = alert.showAndWait();
		alert.close();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if (group.getSelectedToggle() != null) {
				showOptionDialog_selected = group.getSelectedToggle().getUserData().toString();
			}
			logger.info("Alter showOptionDialog ok,input=" + showOptionDialog_selected + ",title=" + title + ",message="
					+ message);
		} else {
			logger.info("Alter showOptionDialog cancel,title=" + title + ",message=" + message);
		}
		return showOptionDialog_selected;
	}

	/**
	 * 选项框
	 * 
	 * @param optionList
	 * @param message
	 * @return
	 */
	@Deprecated
	private static String showOptionDialog(List<String> optionList, String message) {
		showOptionDialog_selected = null;
		Stage window = new Stage();
		window.setTitle("选择框");
		window.setResizable(false);// 不允许变化窗口大小
		window.initModality(Modality.APPLICATION_MODAL);// 不允许最小化
		window.setWidth(width);
		window.setHeight(height + 100);
		window.centerOnScreen();// 屏幕居中,设置大小后生效
		ToggleGroup group = new ToggleGroup();
//		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//				// TODO Auto-generated method stub
//				 if (group.getSelectedToggle() != null) {
//					 showOptionDialog_selected=group.getSelectedToggle().getUserData().toString();
//				 }
//			}
//		});
		Button btn_ok = new Button("确认");
		btn_ok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (group.getSelectedToggle() != null) {
					showOptionDialog_selected = group.getSelectedToggle().getUserData().toString();
				}
				logger.info("Alter showInputDialog ok,input=" + showInputDialog_input + ",message=" + message);
				window.close();
			}
		});
		Button btn_cancel = new Button("取消");
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("Alter showInputDialog cancle,input=" + showInputDialog_input + ",message=" + message);
				window.close();
			}
		});
		Label lbl_input = new Label("请选择");
		Label lbl_message = new Label(message);
		HBox hbox_confirm = new HBox(2);
		hbox_confirm.getChildren().addAll(btn_ok, btn_cancel);
		VBox vbox = new VBox(4);
		vbox.setSpacing(10);// 上下间距
		ScrollPane scrollPane = new ScrollPane();
		VBox vbox_radiobtns = new VBox();
		vbox_radiobtns.setSpacing(10);
		scrollPane.setContent(vbox_radiobtns);
		scrollPane.setPannable(true);
		scrollPane.setPrefHeight(150);
		List<RadioButton> radioBtnList = new ArrayList<>();
		for (String option : optionList) {
			RadioButton radioButton = new RadioButton(option);
			radioButton.setToggleGroup(group);
			radioButton.setUserData(option);
			radioButton.setPadding(new Insets(0, 0, 0, 35));
			radioBtnList.add(radioButton);
			vbox_radiobtns.getChildren().add(radioButton);
		}
		if (group.getToggles().size() > 0) {
			group.getToggles().get(0).setSelected(true);// 默认第一个选中
		}
		vbox.getChildren().addAll(lbl_input, scrollPane, lbl_message, hbox_confirm);
		// vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.setAlwaysOnTop(true);
		hbox_confirm.setPadding(new Insets(0, 0, 0, 240));
		window.showAndWait(); // 使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
		return showOptionDialog_selected;
	}

	/**
	 * 输入框
	 * 
	 * @param message
	 * @return
	 */
	@Deprecated
	private static String showInputDialog(String message) {
		showInputDialog_input = null;
		Stage window = new Stage();
		window.setTitle("输入框");
		window.setResizable(false);// 不允许变化窗口大小
		window.initModality(Modality.APPLICATION_MODAL);// 不允许最小化
		window.setWidth(width);// 默认大小?
		window.setHeight(height);
		window.centerOnScreen();// 屏幕居中
		TextField textField = new TextField();
		Button btn_ok = new Button("确认");
		btn_ok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showInputDialog_input = textField.getText();
				logger.info("Alter showInputDialog ok,input=" + showInputDialog_input + ",message=" + message);
				window.close();
			}
		});
		Button btn_cancel = new Button("取消");
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("Alter showInputDialog cancle,input=" + showInputDialog_input + ",message=" + message);
				window.close();
			}
		});
		Label lbl_input = new Label("请输入");
		Label lbl_message = new Label(message);
		HBox hbox_confirm = new HBox(2);
		hbox_confirm.getChildren().addAll(btn_ok, btn_cancel);
		VBox vbox = new VBox(4);
		vbox.setSpacing(10);// 上下间距
		vbox.getChildren().addAll(lbl_input, textField, lbl_message, hbox_confirm);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.setAlwaysOnTop(true);
		lbl_message.setPrefWidth(window.getWidth());
		lbl_input.setPrefWidth(window.getWidth());
		hbox_confirm.setPadding(new Insets(0, 0, 0, 240));
		window.showAndWait(); // 使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
		return showInputDialog_input;
	}

}
