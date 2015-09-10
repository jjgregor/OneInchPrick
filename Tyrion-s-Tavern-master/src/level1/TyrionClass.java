package level1;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.Board;

public class TyrionClass implements Runnable, KeyListener {
	private JLabel _tyrion;
	private int _x;
	private JLabel _background;

	public TyrionClass(Board board) {
		_tyrion = new JLabel(new ImageIcon("img/tyrion.png"));
		_x = 100;
		_tyrion.setBounds(_x, 370, 159, 238);
		_background = board.getBackground();
		_background.add(_tyrion);
		_background.repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if (_x > 100) {
				_x = _x - 125;
				_tyrion.setBounds(_x, 370, 159, 238);
				_background.repaint();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (_x < 1100) {
				_x = _x + 125;
				_tyrion.setBounds(_x, 370, 159, 238);
				_background.repaint();
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public int getXCoordinate(){
		return _x;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
