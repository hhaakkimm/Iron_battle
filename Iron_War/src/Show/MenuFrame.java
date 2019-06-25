package Show;

import java.io.Serializable;

import javax.swing.JFrame;

import Main.Game;

public class MenuFrame extends JFrame implements Serializable{
	public static final int w=300;
	public static final int h=300;
	
	public Menu menu;
	public ClientSetting client;
	public ServerSetting server;
	public ServerList	 list;
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
	}
	
}
