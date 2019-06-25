package Show;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Helper.Input;
import Main.Main;

public abstract class GameWindow{
	private static boolean created;
	public  static JFrame window;
	public static Canvas contentCanvas;
	
	//-------------------------------------
	private static BufferedImage 	buffer;
	private static int[] 			bufferData;
	private static Graphics 		bufferGraphics;
	private static BufferStrategy 	bufferStrategy;
	private static JMenuBar			menuBar;
	private static JMenu			menu;
	private static JMenuItem		pause, start, store, stored;
	//------------------------------------
	private static int backGroundColor;
	private static int strategyNumber;
	
	public static void create(int width, int height, String title, int backColor, int numberOfBufferStrategy){
		if(created) return;
		window=new JFrame(title);	
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentCanvas=new Canvas();
		Dimension sizeOfCanvas=new Dimension(width, height);
		contentCanvas.setSize(sizeOfCanvas);
		
		Dimension sizeOfWindow=new Dimension(width+5, height+55);
		window.setPreferredSize(sizeOfWindow);
		window.getContentPane().add(contentCanvas);
		window.pack();
		window.setResizable(false);
		window.setLocationRelativeTo(null);	
		window.setVisible(true);
		
		menuBar=new JMenuBar();
		window.setJMenuBar(menuBar);
		
		menu=new JMenu("Game");
		menuBar.add(menu);
		
		pause=new JMenuItem(new AbstractAction("Pause") {
		    public void actionPerformed(ActionEvent e) {
		    	Main.game.setToStart(false);
		    	Main.game.getTank().setPause(true);
		    	Main.game.getRival().setPause(true);
		    }
		});
		menu.add(pause);
		
		start=new JMenuItem(new AbstractAction("Start") {
		    public void actionPerformed(ActionEvent e) {
				Main.game.setToStart(true);
		    	Main.game.getTank().setPause(false);
		    	Main.game.getRival().setPause(false);
		    }
		});
		menu.add(start);
		
		store=new JMenuItem(new AbstractAction("Store and Exit") {
		    public void actionPerformed(ActionEvent e) {
				System.out.print("MenuBar");

		    }
		});
		menu.add(store);
		
		
		stored=new JMenuItem(new AbstractAction("Stored games") {
		    public void actionPerformed(ActionEvent e) {
				System.out.print("MenuBar");

		    }
		});
		menu.add(stored);
		

		
		strategyNumber=numberOfBufferStrategy;
		
		buffer=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		bufferData =((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
		bufferGraphics=buffer.getGraphics();	
		contentCanvas.createBufferStrategy(strategyNumber);
		bufferStrategy=contentCanvas.getBufferStrategy();
		
		
		created=true;
	}
	
	public static void clear(){
		for(int i=0; i<bufferData.length; i++)
			bufferData[i]=backGroundColor;
	}
	
	
	public static void bufferSwap(){
		Graphics tem=bufferStrategy.getDrawGraphics();
		tem.setColor(new Color(0xff00ffff));
		tem.drawImage(buffer, 0, 0, null);
		bufferStrategy.show();
	}
	
	public static Graphics2D getGraphics(){
		return (Graphics2D)bufferGraphics;
	}
	
	public static void destroy(){
		if(!created)return;
		window.dispose();
	}
	
	public static void setTitle(String title){
		window.setTitle(title);
	}
	public static void addInputListener(Input input){
		window.add(input);
	}
	
	
//	public static void startCheker(){
//		start.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				System.out.print("MenuBar");
//				Main.game.getTank().setPause(false);
//				Main.game.getRival().setPause(false);
//			}
//		});
//	}
//	
//	public static void pauseCheker(){
//		pause.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				System.out.print("MenuBar");
//				Main.game.getTank().setPause(true);
//				Main.game.getRival().setPause(true);
//			}
//		});
//	}

}




















