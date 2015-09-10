package level1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Beer {
	private int _x;
	private int _y;
	private JLabel _bottle;

	public Beer(int x, int y) {
		_x = x;
		_y = y;
		_bottle = new JLabel(new ImageIcon("img/beer_small.png"));
	}
	int get_x_coordinate() {
		return _x;
	}
	int get_y_coordinate() {
		return _y;
	}
	JLabel get_jlabel() {
		return _bottle;
	}
}