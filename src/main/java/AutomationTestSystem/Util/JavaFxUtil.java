package AutomationTestSystem.Util;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

@SuppressWarnings("restriction")
public class JavaFxUtil {

    	public static void SetLabel(Label Label,int X,int Y,String Text,double FontSize) throws Exception {
    		Label = new Label();
    		Label.setTranslateX(X);
    		Label.setTranslateY(Y);
    		Label.setText(Text);
    		Label.setFont(new Font("微软雅黑", FontSize));
    	}
}
