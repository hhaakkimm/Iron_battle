package Show;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import Main.Game;
import Main.Main;

public class Menu extends Container implements Serializable{
	public static final int BUTTON_SIZEX=100;
	public static final int BUTTON_SIZEY=30;
	
	private JButton server;
	private JButton	client;
	
	public Menu(){
		setSize(Game.WIDTH,Game.HEIGHT);
		setLayout(null);
		
		server=new JButton("SERVER");
		server.setBounds(MenuFrame.w/2-BUTTON_SIZEX/2,MenuFrame.h/2-BUTTON_SIZEY*2,BUTTON_SIZEX,BUTTON_SIZEY);
		add(server);
		server.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 Main.menuFrame.menu.setVisible(false);
					Main.menuFrame.server.setVisible(true);
			}
		});
		
		client=new JButton("CLIENT");
		client.setBounds(MenuFrame.w/2-BUTTON_SIZEX/2,(MenuFrame.h/2),BUTTON_SIZEX,BUTTON_SIZEY);
		add(client);
		client.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 Main.menuFrame.menu.setVisible(false);
					Main.menuFrame.client.setVisible(true);
			}
		});
		
	}
	
}
