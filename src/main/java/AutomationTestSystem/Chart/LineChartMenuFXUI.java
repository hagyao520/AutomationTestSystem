package AutomationTestSystem.Chart;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings({"restriction"})
public class LineChartMenuFXUI extends ContextMenu {
	Logger logger = LoggerFactory.getLogger(LineChartMenuFXUI.class);
	boolean issavedata = false;
	boolean issavedataall = false;
	boolean ispause = false;
	LineChartFXUI lineChartFXUI;
	Menu line_menus;
	ObservableList<CheckMenuItem> checkMenuItemList = FXCollections.observableArrayList();
	MenuItem intercept_menu;
	MenuItem xAxis_menu;
	MenuItem width_menu;
	MenuItem height_menu;
	MenuItem y_tickcount_menu;
	MenuItem size_thresholdlabel_menu;
	MenuItem clear_menu;

	public LineChartMenuFXUI(LineChartFXUI lineChartFXUI) {
		// TODO Auto-generated constructor stub
		this.lineChartFXUI = lineChartFXUI;
		MenuItem pause_menu = new MenuItem("暂停");
		line_menus = new Menu("显示数据");
		Menu data_menus = new Menu("数据");
		MenuItem save_data_menu = new MenuItem("保存数据(新增)");
		MenuItem save_data_all_menu = new MenuItem("保存数据(所有)");
		MenuItem save_pic_menu = new MenuItem("截图");
		intercept_menu = new MenuItem();
		clear_menu = new MenuItem();
		data_menus.getItems().addAll(save_data_menu, save_data_all_menu, save_pic_menu, intercept_menu, clear_menu);
		// adv
		Menu adv_menus = new Menu("高级设置");
		xAxis_menu = new MenuItem();
		width_menu = new MenuItem();
		height_menu = new MenuItem();
		y_tickcount_menu = new MenuItem();
		size_thresholdlabel_menu = new MenuItem();
		updateItemShow();
		CheckMenuItem vgridline_menus = new CheckMenuItem("垂直网格线");
		CheckMenuItem hgridline_menus = new CheckMenuItem("水平网格线");
		adv_menus.getItems().addAll(xAxis_menu, width_menu, height_menu, y_tickcount_menu, size_thresholdlabel_menu,
				vgridline_menus, hgridline_menus);
		MenuItem cancel_menu = new MenuItem("取消");
		getItems().add(pause_menu);
		getItems().add(line_menus);
		getItems().add(data_menus);
		getItems().add(adv_menus);
		getItems().add(cancel_menu);
		pause_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press pause_menu");
				if (!ispause) {
					ispause = true;
					pause_menu.setText("继续");
					lineChartFXUI.setPause(true);
				} else {
					ispause = false;
					lineChartFXUI.setPause(false);
					pause_menu.setText("暂停");
				}
			}
		});
		save_data_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press save_data_menu");
				if (!issavedata) {
					if (lineChartFXUI.startSaveData(false) != null) {
						issavedata = true;
						save_data_menu.setText("停止保存(新增)");
						save_data_all_menu.setDisable(true);
					}
				} else {
					issavedata = false;
					save_data_menu.setText("保存数据(新增)");
					lineChartFXUI.stopSaveData();
					save_data_all_menu.setDisable(false);
				}
			}
		});
		save_data_all_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press save_data_all_menu");
				if (!issavedataall) {
					if (lineChartFXUI.startSaveData(true) != null) {
						issavedataall = true;
						save_data_all_menu.setText("停止保存(所有)");
						save_data_menu.setDisable(true);
					}
				} else {
					issavedataall = false;
					save_data_all_menu.setText("保存数据(所有)");
					save_data_menu.setDisable(false);
					lineChartFXUI.stopSaveData();
				}
			}
		});
		save_pic_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press save_pic_menu");
				lineChartFXUI.savePicture();
			}
		});
		// adv
		xAxis_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press xAxis_menu");
				String max_xAxis = AlertBoxFXUI.showInputDialog("请输入", "X轴最大节点数量,默认80",
						AlertBoxFXUI.showInputDialog_Integer_Positive);
				if (max_xAxis != null) {
					lineChartFXUI.setMax_xAxis(Integer.parseInt(max_xAxis));
				}
			}
		});
		width_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press width_menu");
				String size = AlertBoxFXUI.showInputDialog("请输入折线图宽", "折线图宽,且大于50,例如:600",
						AlertBoxFXUI.showInputDialog_Integer_Positive);
				if (size != null) {
					int width = Integer.parseInt(size);
					if (width <= 50) {
						AlertBoxFXUI.showMessageDialogWarn("宽需要大于50", "折线图宽,且大于50,例如:600");
					} else {
						lineChartFXUI.setSize(width, -1);
					}
				}
			}
		});
		height_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press height_menu");
				String size = AlertBoxFXUI.showInputDialog("请输入折线图高", "折线图高,且大于50,例如:400",
						AlertBoxFXUI.showInputDialog_Integer_Positive);
				if (size != null) {
					int height = Integer.parseInt(size);
					if (height <= 50) {
						AlertBoxFXUI.showMessageDialogWarn("高需要大于50", "折线图高,大于50,例如:400");
					} else {
						lineChartFXUI.setSize(-1, height);
					}
				}
			}
		});
		y_tickcount_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press y_tickcount_menu");
				String tickcount = AlertBoxFXUI.showInputDialog("请输入", "Y轴刻度线数量,默认5",
						AlertBoxFXUI.showInputDialog_Integer_Positive);
				if (tickcount != null) {
					lineChartFXUI.getYAxis().setMinorTickCount(Integer.parseInt(tickcount));
				}
			}
		});
		size_thresholdlabel_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press size_thresholdlabel_menu");
				String size_thresholdlabel = AlertBoxFXUI.showInputDialog("请输入", "节点大小(每隔节点的圆圈直径大小),默认3",
						AlertBoxFXUI.showInputDialog_Integer_Positive);
				if (size_thresholdlabel != null) {
					lineChartFXUI.setSize_ThresholdLabel(Integer.parseInt(size_thresholdlabel));
				}
			}
		});
		vgridline_menus.setSelected(true);
		vgridline_menus.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press vgridline_menus checkmenuitem");
				if (!vgridline_menus.isSelected()) {
					lineChartFXUI.getLineChart().setVerticalGridLinesVisible(false);
				} else {
					lineChartFXUI.getLineChart().setVerticalGridLinesVisible(true);
				}
			}
		});
		hgridline_menus.setSelected(true);
		hgridline_menus.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press hgridline_menus checkmenuitem");
				if (!hgridline_menus.isSelected()) {
					lineChartFXUI.getLineChart().setHorizontalGridLinesVisible(false);
				} else {
					lineChartFXUI.getLineChart().setHorizontalGridLinesVisible(true);
				}
			}
		});
		intercept_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press intercept_menu");
				Stage stage = new Stage();
				stage.setWidth(800);
				stage.setHeight(400);
				stage.centerOnScreen();
				LineChartFXUI minilineChartFXUI = new LineChartFXUI(800, 350);
				List<Data<String, Number>> list = lineChartFXUI.getInterceptData();
				if (list != null) {
					logger.info("intercept list size=" + list.size());
					stage.setTitle("截取数据:" + list.size() + "组");
					Series<String, Number> series = new XYChart.Series<String, Number>();
					minilineChartFXUI.addSeries(series, "数据");
					for (Data<String, Number> data : list) {
						minilineChartFXUI.addData(series, data.getXValue(), data.getYValue());
					}
					stage.setScene(new Scene(minilineChartFXUI));
				} else {
					VBox vBox = new VBox();
					vBox.setAlignment(Pos.CENTER);
					Label lbl_note = new Label();
					lbl_note.setText("错误:请先选择同一折线上的两个点!");
					vBox.getChildren().add(lbl_note);
					stage.setScene(new Scene(vBox));
					stage.setTitle("截取数据");
				}
				stage.show();
			}
		});
		clear_menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				logger.info("press clear_menu");
				lineChartFXUI.clearData();
			}
		});
	}

	/**
	 * 条目更新
	 */
	public void updateItemShow() {
		intercept_menu.setText("截取数据:"
				+ (lineChartFXUI.interceptList.size() > 0 ? lineChartFXUI.interceptList.get(0)[1] : "null") + "/"
				+ (lineChartFXUI.interceptList.size() > 1 ? lineChartFXUI.interceptList.get(1)[1] : "null"));
		xAxis_menu.setText(
				"x轴节点最大数量:" + (lineChartFXUI.max_xAxis == Integer.MAX_VALUE ? "MAX" : lineChartFXUI.max_xAxis));
		width_menu.setText("折线图宽:" + lineChartFXUI.width);
		height_menu.setText("折线图高:" + lineChartFXUI.height);
		y_tickcount_menu.setText("y轴刻度线数量:" + lineChartFXUI.getYAxis().getMinorTickCount());
		size_thresholdlabel_menu.setText("节点大小:" + lineChartFXUI.size_ThresholdLabel);
		clear_menu.setText("清空数据:" + (lineChartFXUI.getLineChart().getData().size() > 0
				? lineChartFXUI.getLineChart().getData().get(0).getData().size()
				: "0") + "组");
	}

	/**
	 * 更新当前折线
	 */
	public void updateLineMenu() {
		line_menus.getItems().clear();
		checkMenuItemList.clear();
		for (Series<String, Number> series : lineChartFXUI.getLineChart().getData()) {
			CheckMenuItem checkMenuItem = new CheckMenuItem(series.getName());
			checkMenuItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					logger.info("press linechatmenu:" + series.getName() + " checkmenuitem");
					if (!checkMenuItem.isSelected()) {
						lineChartFXUI.getLineChart().getData().remove(series);
					} else {
						lineChartFXUI.getLineChart().getData().add(series);
					}
				}
			});
			checkMenuItemList.add(checkMenuItem);
			checkMenuItem.setSelected(true);
		}
		line_menus.getItems().addAll(checkMenuItemList);
	}

	/**
	 * 获取checkmenuitem是否被选中
	 * 
	 * @param name 折线名称
	 * @return
	 */
	public boolean getCheckMenuItemSelected(String name) {
		for (CheckMenuItem item : checkMenuItemList) {
			if (item.getText().equals(name)) {
				return item.isSelected();
			}
		}
		return false;
	}
}
