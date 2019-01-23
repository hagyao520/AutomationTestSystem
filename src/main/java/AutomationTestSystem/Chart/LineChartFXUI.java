package AutomationTestSystem.Chart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

@SuppressWarnings("restriction")
public class LineChartFXUI extends AnchorPane {
	
	public static String Logfile = ""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+"/Logs";
	Logger logger = LoggerFactory.getLogger(LineChartFXUI.class);
	int width;
	int height;
	int max_xAxis = 80;// 默认值
	int size_ThresholdLabel = 3;
	Label lbl_info = new Label();
	boolean flag_threshold = false;
	Label lbl_threshold = new Label();
	LineChart<String, Number> lineChart;
	NumberAxis yAxis;
	List<String> invalidList = new ArrayList<>();// 无效数据集
	Label lbl_menu = new Label();

	LineChartMenuFXUI lineChartMenuFXUI;
	boolean need_save_data = false;// 保存数据到文本
	File save_data_file = null;
	StringBuffer saveDataBuf = new StringBuffer();

	boolean ispause = false;// 是否暂停
	// 截取
	boolean intercept_flag = false;
	List<String[]> interceptList = new ArrayList<>();

	public LineChartFXUI(int width, int height) {
		setSize(width, height);
		lineChart = createLineChart();
		lbl_menu.setPrefSize(0, 0);
		VBox vBox = new VBox();
		AnchorPane anchorPane_lineChart = new AnchorPane();
		anchorPane_lineChart.getChildren().addAll(lineChart, lbl_threshold, lbl_menu);
		lbl_info.setAlignment(Pos.CENTER_RIGHT);
		lbl_threshold.setAlignment(Pos.CENTER_RIGHT);
		vBox.getChildren().addAll(anchorPane_lineChart, lbl_threshold, lbl_info);
		getChildren().add(vBox);
		setMax_xAxis(max_xAxis);
		lineChartMenuFXUI = new LineChartMenuFXUI(this);
	}

	/**
	 * 获取注释信息文本
	 * 
	 * @return
	 */
	public void setNoteInfo(String text) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				lbl_info.setText(text);
			}
		});
	}

	/**
	 * 获取折线图
	 * 
	 * @return
	 */
	public LineChart<String, Number> getLineChart() {
		return lineChart;
	}

	/**
	 * 添加折线
	 * 
	 * @param series
	 * @param name
	 */
	public void addSeries(XYChart.Series<String, Number> series, String name) {
		if (series.getData().size() > 0) {

		}
		series.setName(name);
		lineChart.getData().add(series);
		lineChartMenuFXUI.updateLineMenu();
	}

	/**
	 * 根据折线添加数据
	 * 
	 * @param series
	 * @param x
	 * @param y
	 */
	public void addData(XYChart.Series<String, Number> series, String x, Number y) {
		if (!ispause) {// 是否已暂停
			XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(x, y);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String[] values = { series.getName(), x, y.toString() };
					data.setNode(new ThresholdLabel(values));
					series.getData().add(data);
					if (series.getData().size() > max_xAxis) {
						series.getData().remove(0, series.getData().size() - max_xAxis);// 移除掉币max多余的节点
					}
				}
			});
			if (need_save_data && save_data_file != null && save_data_file.exists()) {
				saveDataBuf.append(series.getName() + "," + x + "," + y + ",");
			}
		}
	}

	/**
	 * 根据折线添加数据
	 * 
	 * @param series
	 * @param x
	 * @param y
	 */
	public void addData(String name, String x, Number y) {
		if (!ispause) {// 是否已暂停
			XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(x, y);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					XYChart.Series<String, Number> series = null;
					for (XYChart.Series<String, Number> chart : lineChart.getData()) {
						if (chart.getName().equals(name)) {
							series = chart;
							break;
						}
					}
					if (series != null) {
						String[] values = { series.getName(), x, y.toString() };
						data.setNode(new ThresholdLabel(values));
						series.getData().add(data);
						if (series.getData().size() > max_xAxis) {
							series.getData().remove(0, series.getData().size() - max_xAxis);// 移除掉币max多余的节点
						}
					}
				}
			});
			if (need_save_data && save_data_file != null && save_data_file.exists()) {
				saveDataBuf.append(name + "," + x + "," + y + ",");
			}
		}
	}

	/**
	 * 创建网络折线图
	 * 
	 * @return
	 */
	private LineChart<String, Number> createLineChart() {
		CategoryAxis xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		yAxis.setLabel("单位");
		yAxis.setSide(Side.LEFT);// 指定坐标轴位置
//		yAxis.setUpperBound(500);// 上限数字
//		yAxis.setLowerBound(0);// 下限数字
		yAxis.setMinorTickCount(5);

		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setCreateSymbols(false);// 禁用折线图的符号化
		lineChart.setAnimated(false);// 值改变时的动画
		// lineChart.setLegendVisible(false);
		// lineChart.setBlendMode(BlendMode.GREEN);
		// lineChart.setTitle("");
//		lineChart.setLayoutX(layoutX);// 位置X
//		lineChart.setLayoutY(layoutY);// 位置Y
		lineChart.setPrefWidth(width);// 宽
		lineChart.setPrefHeight(height);// 高

		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					double mouse_x = event.getX();// 当前控件中的坐标系
					double mouse_y = event.getY();
					logger.info("line chart panel mouse click,x=" + mouse_x + ",y=" + mouse_y);
					lbl_menu.setLayoutX(mouse_x >= width - 25 ? width - 25 : mouse_x);// 避免扩大原面板
					lbl_menu.setLayoutY(mouse_y >= height - 25 ? height - 25 : mouse_y);
					lineChartMenuFXUI.updateItemShow();
					lineChartMenuFXUI.show(lbl_menu, Side.BOTTOM, 0, 0);
				}
			}
		});
		return lineChart;
	}

	/**
	 * 更改X轴最大节点数
	 * 
	 * @param max_xAxis
	 */
	public void setMax_xAxis(int max_xAxis) {
		this.max_xAxis = max_xAxis;
	}

	/**
	 * 得到Y轴对象
	 * 
	 * @return
	 */
	public NumberAxis getYAxis() {
		return yAxis;
	}

	/**
	 * 设置窗体大小
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		if (width > 0) {
			this.width = width;
			lbl_threshold.setPrefWidth(width);
			lbl_info.setPrefWidth(width);
		}
		if (height > 0) {
			this.height = height;
		}
		if (lineChart != null) {
			lineChart.setPrefSize(this.width, this.height);
		}
		setMaxSize(this.width, this.height);
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		lineChart.setTitle(title);
	}

	/**
	 * 暂停
	 * 
	 * @param ispause
	 */
	public void setPause(boolean ispause) {
		this.ispause = ispause;
	}

	/**
	 * 提示大小
	 * 
	 * @param size_ThresholdLabel
	 */
	public void setSize_ThresholdLabel(int size_ThresholdLabel) {
		this.size_ThresholdLabel = size_ThresholdLabel;
		ObservableList<Series<String, Number>> seriesList = lineChart.getData();
		for (int i = 0; i < seriesList.size(); i++) {// 采用固定数组大小获取,避免改变大小遍历时,添加折线造成java.util.ConcurrentModificationException
			Series<String, Number> series = seriesList.get(i);
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < series.getData().size(); j++) {
						Data<String, Number> data = series.getData().get(j);
						((ThresholdLabel) data.getNode()).setPrefSize(size_ThresholdLabel, size_ThresholdLabel);
					}
				}
			});
		}
	}

	/**
	 * 写数据,需在添加数据后执行
	 * 
	 * @param list
	 * @return 返回null则为不写入
	 */
	public String writeSaveData() {
		String line = null;
		if (!ispause) {
			if (need_save_data && save_data_file != null && save_data_file.exists()) {
				line = saveDataBuf.toString() + "\r\n";
				HelperUtil.file_write_line(save_data_file.getAbsolutePath(), line, true);
				saveDataBuf.setLength(0);
			}
		}
		return line;
	}

	/**
	 * 开始记录数据
	 */
	public File startSaveData(boolean hasall) {
		save_data_file = selectFileByTXT();
		if (save_data_file != null) {
			if (!save_data_file.exists()) {
				try {
					save_data_file.createNewFile();
				} catch (IOException e) {
					logger.error("EXCEPTION", e);
				}
			}
			need_save_data = true;// 开始标志位
			logger.info("start to save linechart data:" + save_data_file.getAbsolutePath());
			if (hasall && lineChart.getData().size() > 0) {
				StringBuffer dataBuf = new StringBuffer();
				for (int j = 0; j < lineChart.getData().get(0).getData().size(); j++) {
					dataBuf.setLength(0);
					for (int i = 0; i < lineChart.getData().size(); i++) {
						Data<String, Number> data = lineChart.getData().get(i).getData().get(j);
						dataBuf.append(lineChart.getData().get(i).getName() + "," + data.getXValue() + ","
								+ data.getYValue() + ",");
					}
					HelperUtil.file_write_line(save_data_file.getAbsolutePath(), dataBuf.toString() + "\r\n", true);
				}
			}
		} else {
			need_save_data = false;
		}
		return save_data_file;
	}

	/**
	 * 停止记录数据
	 */
	public void stopSaveData() {
		need_save_data = false;
		logger.info("stop to save linechart data");
	}

	/**
	 * 选择文本保存位置
	 * 
	 * @return
	 */
	private File selectFileByTXT() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择数据文本保存位置");
		File initfile = new File(Logfile + "/DataRecord");
		if (!initfile.exists()) {
			initfile.mkdirs();
		}
		fileChooser.setInitialDirectory(initfile);
		fileChooser.setInitialFileName("linechart_" + (lineChart.getTitle() != null ? lineChart.getTitle() + "_" : "")
				+ TimeUtil.getTime4File() + ".txt");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
		File file = fileChooser.showSaveDialog(null);
		return file;
	}

	/**
	 * 清空数据
	 */
	public void clearData() {
		ObservableList<Series<String, Number>> seriesList = lineChart.getData();
		for (Series<String, Number> series : seriesList) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					series.getData().clear();
				}
			});
		}
	}

	/**
	 * 清除折线
	 */
	public void clearSeries() {
		lineChart.getData().removeAll(lineChart.getData());
	}

	/**
	 * 保存图片
	 */
	public void savePicture() {
		WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
		try {
			File file = selectFileWithPNG();
			if (file != null) {
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
				AlertBoxFXUI.showMessageDialog("保存图片", "图片保存成功,位置:" + file != null ? file.getAbsolutePath() : "无");
			}
		} catch (IOException e) {
			AlertBoxFXUI.showMessageDialogError("保存图片", "图片保存失败");
		}
	}

	/**
	 * 选择图片保存位置
	 * 
	 * @return
	 */
	private File selectFileWithPNG() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择保存图片位置");
		File initfile = new File(Logfile + "/ScreenCap");
		if (!initfile.exists()) {
			initfile.mkdirs();
		}
		fileChooser.setInitialDirectory(initfile);
		fileChooser.setInitialFileName("linechart_" + (lineChart.getTitle() != null ? lineChart.getTitle() + "_" : "")
				+ TimeUtil.getTime4File() + ".png");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showSaveDialog(null);
		return file;
	}

	/**
	 * 获取截取的数据列表
	 * 
	 * @return
	 */
	public List<Data<String, Number>> getInterceptData() {
		// series.getName(), x, y.toString()
		if (interceptList.size() == 2) {
			String[] pointA = interceptList.get(0);
			String[] pointB = interceptList.get(1);
			logger.info("intercept from \"" + pointA[0] + "," + pointA[1] + "," + pointA[2] + "\" to \"" + pointB[0]
					+ "," + pointB[1] + "," + pointB[2] + "\"");
			if (!pointA[0].equals(pointB[0])) {
				return null;
			}
			Series<String, Number> series = null;
			for (Series<String, Number> temp_series : lineChart.getData()) {
				if (temp_series.getName().equals(pointA[0])) {
					series = temp_series;
					break;
				}
			}
			if (series != null) {
				int totalcount = series.getData().size();
				int start_index = 0;
				int end_index = totalcount - 1;
				for (int i = 0; i < totalcount; i++) {
					Data<String, Number> data = series.getData().get(i);
					if (data.getXValue().equals(pointA[1])) {
						start_index = i;
					} else if (data.getXValue().equals(pointB[1])) {
						end_index = i;
					}
				}
				if (start_index > end_index) {
					int temp = start_index;
					start_index = end_index;
					end_index = temp;
				}
				if (end_index < totalcount - 1) {// 包含最后一个数据
					end_index += 1;
				}
				return series.getData().subList(start_index, end_index);// 未包括最后一个
			}
		}
		logger.info("intercept return null");
		return null;
	}

	/**
	 * 节点显示数值
	 * 
	 * @author auto
	 *
	 */
	class ThresholdLabel extends StackPane {
		ThresholdLabel(String[] values) {// name,x,y
			Label lbl_show = createDataThresholdLabel(values[2]);
			setPrefSize(size_ThresholdLabel, size_ThresholdLabel);
			setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (!intercept_flag) {
						intercept_flag = true;
						if (interceptList.size() == 0) {
							interceptList.add(values);
						} else {
							interceptList.set(0, values);
						}
					} else {
						intercept_flag = false;
						if (interceptList.size() == 1) {
							interceptList.add(values);
						} else {
							interceptList.set(1, values);
						}
					}
					ToastBoxFXUI.showToast(
							"截取数据:" + interceptList.get(0)[1] + "/"
									+ (interceptList.size() == 2 ? interceptList.get(1)[1] : ""),
							ToastBoxFXUI.INFO, 3000);
					logger.info("intercept click " + interceptList.get(0)[1] + "/"
							+ (interceptList.size() == 2 ? interceptList.get(1)[1] : ""));
				}
			});
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getChildren().setAll(lbl_show);
					setCursor(Cursor.NONE);
					if (flag_threshold) {
						flag_threshold = false;
						lbl_threshold.setTextFill(Color.GREEN);
					} else {
						flag_threshold = true;
						lbl_threshold.setTextFill(Color.BLUE);
					}
					lbl_threshold.setText(values[0] + "=" + values[1] + "/" + values[2]);
					// toFront(); ????
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getChildren().clear();
					setCursor(Cursor.CROSSHAIR);
					// lbl_threshold.setText("");
				}
			});
		}
	}

	/**
	 * 创建节点数值显示label
	 * 
	 * @param value
	 * @return
	 */
	private Label createDataThresholdLabel(String value) {
		Label label = new Label(invalidList.contains(value) ? "invalid" : value);
		label.getStyleClass().addAll("default-color3", "chart-line-symbol", "chart-series-line");
		label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
		label.setTextFill(Color.FIREBRICK);
		label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		return label;
	}

	/**
	 * 添加无效数据,显示为invalid
	 * 
	 * @param invalidvalue
	 */
	public void addInvalidData(List<String> invalidList) {
		for (String str : invalidList) {
			this.invalidList.add(str);
		}
	}
}
