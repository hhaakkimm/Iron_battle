package Show;

import java.io.Serializable;

import javax.swing.JFrame;

import Main.Main;
import Store.StoreExit;
import Store.StoreLoad;
import Store.WhoWin;

public class MenuFrame extends JFrame implements Serializable{
	public static final int w=300;
	public static final int h=300;
	
	public Menu menu;
	public ClientSetting client;
	public ServerSetting server;
	public ServerList	 list;
	public StoreExit exit;
	public WhoWin win;
	public StoreLoad load;
	
	public MenuFrame(){
		setSize(w,h);
		setTitle("Iron War");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		menu=new Menu();
		menu.setLocation(0,0);
		add(menu);
		menu.setVisible(true);
		
		client=new ClientSetting();
		client.setLocation(0,0);
		add(client);
		client.setVisible(false);
		
		server=new ServerSetting();
		server.setLocation(0,0);
		add(server);
		server.setVisible(false);
		
		list=new ServerList();
		list.setLocation(0,0);
		add(list);
		list.setVisible(false);
		
		exit=new StoreExit();
		exit.setLocation(0,0);
		add(exit);
		exit.setVisible(false);
		
		load=new StoreLoad();
		load.setLocation(0,0);
		add(load);
		load.setVisible(false);
		
//		win=new WhoWin("server", "client", 1, 3);
//		win.setLocation(0,0);
//		add(win);
//		win.setVisible(false);
	}
	
	public void createResult(){
		win=new WhoWin(Main.game.getTank().getName(),  Main.game.getRival().getName(), 
				Main.game.getTank().getPoint(), Main.game.getRival().getPoint());
		win.setLocation(0,0);
		add(win);
		win.setVisible(false);
	}
	
}
