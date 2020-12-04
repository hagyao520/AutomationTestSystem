package Test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * @see https://stackoverflow.com/a/32396344/230513
 * @see https://stackoverflow.com/a/31576647/230513
 */
public class WebViewTable {

    private JTable table;
    private WebView webView;

    private void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("WebViewTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JFXPanel fxPanel = new JFXPanel(){

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 400);
            }
        };
        frame.add(fxPanel, BorderLayout.CENTER);
        table = new JTable(){

            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(800, 100);
            }
        };
        frame.add(new JScrollPane(table), BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Platform.runLater(() -> {
            initFX(fxPanel);
        });
    }

    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        Worker worker = webEngine.getLoadWorker();
        worker.stateProperty().addListener((Observable o) -> {
            if (worker.getState() == Worker.State.SUCCEEDED) {
                EventQueue.invokeLater(() -> {
                    table.setModel(new AbstractTableModel() {
                        @Override
                        public String getColumnName(int col) {
                            return "Document URI";
                        }

                        @Override
                        public int getColumnCount() {
                            return 1;
                        }

                        @Override
                        public int getRowCount() {
                            return 1;
                        }

                        @Override
                        public Object getValueAt(int row, int col) {
                            return webEngine.getDocument().getDocumentURI();
                        }
                    });
                });
            }
        });
        webEngine.load("https://example.com");
        root.getChildren().add(webView);
        return scene;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new WebViewTable()::initAndShowGUI);
    }
}

