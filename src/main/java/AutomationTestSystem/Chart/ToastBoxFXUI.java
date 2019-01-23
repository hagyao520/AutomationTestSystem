package AutomationTestSystem.Chart;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings({"restriction"})
public class ToastBoxFXUI {// 单例模式
	Logger logger = LoggerFactory.getLogger(ToastBoxFXUI.class);
	private static ToastBoxFXUI toastBoxFXUI;
	Stage window = new Stage();// http://hjk685.iteye.com/blog/2202279 stage API介绍
	Timer timer;
	Label lbl_message = new Label();
	boolean isshow = false;

	final public static String WARN = "WARN";
	final public static String INFO = "INFO";
	final public static String ERROR = "ERROR";
	int width = 600;
	int height = 200;

	public ToastBoxFXUI() {
		// TODO Auto-generated constructor stub
		window.setResizable(false);// 不允许变化窗口大小
		// window.initModality(Modality.APPLICATION_MODAL);// 不允许最小化
		window.setWidth(width);// 默认大小?
		window.setHeight(height);
		// window.centerOnScreen();// 屏幕居中
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		double minX = bounds.getMinX();
		double minY = bounds.getMinY();
		double maxX = bounds.getMaxX();
		double maxY = bounds.getMaxY();
		// System.out.println(minX + "," + minY + "," + maxX + "," + maxY);
		window.setX((maxX - minX - width) / 2);
		window.setY((maxY - minY) / 5 * 4);
		window.initStyle(StageStyle.TRANSPARENT);
		window.setAlwaysOnTop(true);
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		lbl_message.setFont(new Font(25));
		vBox.getChildren().add(lbl_message);
		vBox.setStyle("-fx-background:transparent;");
		Scene scene = new Scene(vBox);
		scene.setFill(null);
		window.setScene(scene);
	}

	public void changeToast(String message, String type, int countdown) {
		lbl_message.setText(message);
		switch (type) {
		case INFO:
			lbl_message.setTextFill(Color.BLUE);
			break;
		case WARN:
			lbl_message.setTextFill(Color.YELLOW);
			break;
		case ERROR:
			lbl_message.setTextFill(Color.RED);
			break;
		default:
			break;
		}
		if (window.isShowing()) {
			if (timer != null) {
				timer.cancel();
				window.close();
			}
		}
		window.show();
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (window.isShowing()) {
							window.close();
						}
					}
				});
			}
		}, countdown);
	}

	public static void showToast(String message, String type, int countdown) {
		if (toastBoxFXUI == null) {
			toastBoxFXUI = new ToastBoxFXUI();
		}
		toastBoxFXUI.changeToast(message, type, countdown);
	}
}
