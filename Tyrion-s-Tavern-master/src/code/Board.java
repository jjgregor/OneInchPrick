package code;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import level1.Beer;
import level1.StartGnome;
import level1.TyrionClass;

public class Board implements Runnable {

	private JFrame _f;
	private JLabel _background;
	private ArrayList<Beer> _beerList;
	private TyrionClass _tyrionClass;
	private StartGnome _startGnome;
	private int _tab;
	private int _screwUps;
	private JLabel _screwUpsLabel;
	private JLabel _timerLabel;
	private JLabel _tabLabel;
	private Timer _timer;
	private int _time;
	
	public void run() {
		_tab = 0;
		_screwUps = 5;
		System.out.println("Tab: "+ _tab);
		System.out.println("ScrewUps: "+ _screwUps);
		_f = new JFrame("Tyrion's Tavern");
		_f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_background = new JLabel(new ImageIcon("img/bar_1280_600.png"));		
		_f.add(_background);
		_f.setVisible(true);
		_f.setExtendedState(Frame.MAXIMIZED_BOTH);
		_f.setResizable(false);
		_f.pack();
		
		JMenuBar menuBar = new JMenuBar();
		_f.setJMenuBar(menuBar);
		
		JMenu Menu = new JMenu("MENU");
		menuBar.add(Menu);
		JMenuItem start = new JMenuItem("Start Game");
		Menu.add(start);
		JMenu help = new JMenu("Help");
		Menu.add(help);
		JMenuItem about = new JMenuItem("Help Tyrion catch the beer by using the arrow keys. Don't Knome Chompski break the boose!!!");
		JMenuItem exit = new JMenuItem("Exit");
		Menu.add(exit);
		help.add(about);
		
		class exitaction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		exit.addActionListener(new exitaction());
		
	    class startaction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel1();
			}
	    }
	    start.addActionListener(new startaction());
	}
		
	public void startLevel1(){
		_background.removeAll();
		
		_startGnome = new StartGnome(this);
		new Thread(_startGnome).start();
		_tyrionClass = new TyrionClass(this);
		new Thread(_tyrionClass).start();
		_f.addKeyListener(_tyrionClass);
		
		_tab = 0;
		_screwUps = 5;
		_screwUpsLabel = new JLabel("Screw Ups Left:  " + _screwUps);
		_screwUpsLabel.setForeground(Color.WHITE);
		_screwUpsLabel.setFont(_screwUpsLabel.getFont().deriveFont(20.0f));
		_screwUpsLabel.setBounds(975, -140, 300, 300);
		_background.add(_screwUpsLabel);
		
		_time = 0;
		_timerLabel = new JLabel("Timer:  " + _time);
		_timerLabel.setForeground(Color.WHITE);
		_timerLabel.setFont(_timerLabel.getFont().deriveFont(20.0f));
		_timerLabel.setBounds(975,-115,300 ,300);
		_background.add(_timerLabel);
		
		_tabLabel = new JLabel("Tab:  " + _tab);
		_tabLabel.setForeground(Color.WHITE);
		_tabLabel.setFont(_tabLabel.getFont().deriveFont(20.0f));
		_tabLabel.setBounds(975, -90, 300, 300);
		_background.add(_tabLabel);
		
		_background.repaint();
		
		_timer = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				_time++;
				if(_time%15==0){
					int i = _startGnome.getDelay();
					if(i>100){
						_startGnome.setDelay(i-100);
					}
					
				}
				_timerLabel.setText("Timer:  " + _time);
			}
		});
		_timer.start();
	}
	public JLabel getBackground(){
		return _background;
	}
	public TyrionClass getTyrionClass(){
		return _tyrionClass;
	}
	public ArrayList<Beer> getBeerList(){
		return _beerList;
	}
	public void endGame(){
		_timer.stop();
		_startGnome.breakLoop();
		JLabel go = new JLabel("GAME OVER");
		JLabel go2 = new JLabel("Click start to play again!");
		go.setForeground(Color.RED);
		go.setFont(go.getFont().deriveFont(50.0f));
		go.setBounds(500, 000, 500, 500);
		go2.setForeground(Color.BLUE);
		go2.setFont(go2.getFont().deriveFont(50.0f));
		go2.setBounds(400,25,750,750);
		_background.add(go);
		_background.add(go2);
		_background.repaint();
	}
	public void incrementTab(){
		_tab++;
		System.out.println("Tab: "+ _tab);
		_tabLabel.setText("Tab: "+_tab);
	}
	public void decrementScrewUps(){
		if(_screwUps>0){
			_screwUps--;
		}
		if(_screwUps == 0){
			endGame();
		}
		System.out.println("ScrewUps: "+ _screwUps);
		_screwUpsLabel.setText("Screw Ups Left:  " + _screwUps);
	}

}
