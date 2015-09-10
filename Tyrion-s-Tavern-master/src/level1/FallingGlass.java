package level1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import code.Board;

public class FallingGlass implements Runnable {
	private Board _board;
	private JLabel _beer;
	private int _x, _y;
	private JLabel _fallingbeer;
	private Timer _timer;
	private TyrionClass _tyrionClass;

	public FallingGlass(Board board, Beer beer) {
		_board = board;
		_x = beer.get_x_coordinate();
		_y = 90;
		_beer = beer.get_jlabel();
		_tyrionClass = board.getTyrionClass();
	}

	@Override
	public void run() {
		_fallingbeer = new JLabel(new ImageIcon("img/fallingbeer.png"));

		_timer = new Timer(75, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (_y > 300 && _y < 450 && _tyrionClass.getXCoordinate() == _x) {
					end();
					_board.incrementTab();
				} else {
					if (_y < 480) {
						gravity();
					}
					if (_y >= 480) {
						breakGlass();
					}
					if (_y >= 500) {
						end();
					}
					_y += 10;
				}
			}
		});

		_board.getBackground().remove(_beer);
		_board.getBackground().repaint();
		_fallingbeer.setBounds(_x, _y, 128, 151);
		_board.getBackground().add(_fallingbeer);
		_board.getBackground().repaint();
		_timer.start();

	}

	public void gravity() {
		_board.getBackground().remove(_fallingbeer);
		_board.getBackground().repaint();
		_fallingbeer.setBounds(_x, _y, 128, 151);
		_board.getBackground().add(_fallingbeer);
		_board.getBackground().repaint();
	}

	public void breakGlass() {
		if (_y == 480) {
			_board.getBackground().remove(_fallingbeer);
			_board.getBackground().repaint();
			_fallingbeer = new JLabel(new ImageIcon("img/brokenglass.png"));
			_board.decrementScrewUps();
		} else {
			_board.getBackground().remove(_fallingbeer);
			_board.getBackground().repaint();
		}
		_fallingbeer.setBounds(_x, _y, 241, 133);
		_board.getBackground().add(_fallingbeer);
	}

	public void end() {
		_timer.stop();
		_board.getBackground().remove(_fallingbeer);
		_board.getBackground().repaint();
	}
}
