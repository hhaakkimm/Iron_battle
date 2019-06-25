package Main;

import java.io.Serializable;

import Show.GameWindow;
import Show.MenuFrame;

public class Main implements Serializable{
	public static MenuFrame menuFrame=null;
	public static Game game;
	public static void main(String[] args) {	
		menuFrame=new MenuFrame();
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setResizable(false);
		menuFrame.setVisible(true);
	}
	public static void runGame(){
		game=new Game(menuFrame.server.getThisServer());
		game.start();
	}

}
