package AutomationTestSystem.Util;

import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * 工具类
 * @author King
 */
@SuppressWarnings("restriction")
public class DragUtil {
     public static void addDragListener(Stage stage,Node root) {
        new DragListener(stage).enableDrag(root);
    }
}

