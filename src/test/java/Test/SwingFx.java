package Test;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

@SuppressWarnings({"restriction", "static-access","unused"})
public class SwingFx extends Application {
 
    /** 主面板 */
    private JPanel MainPanel; 
    
    /** 数据展示列表 */
    public static JScrollPane DataDisplayList;
    
    /** 用户信息查询按钮 */
    private JButton UserInfoButton;
    
    @Override
    public void start (Stage stage) {
        final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
 
        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
 
        stage.setTitle("Swing in JavaFX");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();
 
    }
 
 
    private void createSwingContent(final SwingNode swingNode) {
 
        SwingUtilities.invokeLater(() -> {
            MainPanel = new JPanel();
            MainPanel.setLayout(null);
            MainPanel.setSize(600, 600);
            
            DataDisplayList = new JScrollPane();
            DataDisplayList.setBounds(100,10,1180,807);
            //设置背景是否透明
            DataDisplayList.setOpaque(false);  
            DataDisplayList.getViewport().setOpaque(false);  
            MainPanel.add(DataDisplayList);
            
            UserInfoButton = new JButton("用户信息查询");
            UserInfoButton.setBounds(10, 10, 140, 25);
            
            MainPanel.add(UserInfoButton);
            
            JScrollPane sp=new JScrollPane(MainPanel);
            swingNode.setContent(sp); 
        });                                 
 
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}