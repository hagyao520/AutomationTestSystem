package AutomationTestSystem.Tray;

import javax.swing.JSeparator;

public class OnlySeparator extends JSeparator {

	private static final long serialVersionUID = -2404686601191054374L;

	public OnlySeparator() {
		this(HORIZONTAL);
	}

	public OnlySeparator(int orientation) {
		super(orientation);
		setUI(new OnlySeparatorUI());
		setOpaque(false);
	}

	@Deprecated
	public void updateUI() {
	}
}
