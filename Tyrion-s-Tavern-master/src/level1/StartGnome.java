package level1;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.Board;

public class StartGnome implements Runnable {
	private Board _board;
	private boolean _loop;
	private int _delay;

	public StartGnome(Board board) {
		_board = board;
	}

	@Override
	public void run() {
		_loop = true;
		_delay = 1000;
		JLabel background = _board.getBackground();
		JLabel gnomey = new JLabel(new ImageIcon("img/gnome.png"));
		gnomey.setBounds(550, 90, 103, 119);
		background.add(gnomey);
		background.repaint();
		ArrayList<Beer> beerList = new ArrayList<Beer>();
		int x = 0;
		for (int i = 0; i < 9; i++) {
			Beer b = new Beer(100 + x, 90);
			x += 125;
			beerList.add(b);
			b.get_jlabel().setBounds(b.get_x_coordinate(),
					b.get_y_coordinate(), 110, 150);
			background.add(b.get_jlabel());
			background.repaint();
		}
		Random R = new Random();
		int prev = 10;
		while (_loop) {
			background.remove(gnomey);
			background.repaint();
			// 300ms gap between Gnomey disappearing and reappearing
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int rand = R.nextInt(9);
			while (rand == prev) {
				rand = R.nextInt(9);
			}
			gnomey.setBounds(beerList.get(rand).get_x_coordinate() + 25, 90,
					120, 119);
			background.add(gnomey);
			background.repaint();

			// 1000ms to view Gnomey
			try {
				Thread.sleep(_delay);  // speed of the gnome
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (prev < 10) {
				background.add(beerList.get(prev).get_jlabel());
				background.repaint();
			}
			prev = rand;

			// Gnome pushes the glass
			new Thread(new FallingGlass(_board, beerList.get(rand))).start();

		}
		background.remove(gnomey);
		background.repaint();
	}
	public void breakLoop(){
		_loop = false;
	}
	public int getDelay(){
		return _delay;
	}
	public void setDelay(int delay){
		_delay = delay;
	}

}
