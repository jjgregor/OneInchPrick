package code;


public class Driver {
	public static void main(String[] args){
		(new Thread(new Board())).start();
	}
}
