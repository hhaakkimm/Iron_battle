package Network;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import Show.GameWindow;
public class MainServer implements Serializable{
	private	ServerSocket server=null;
	public ServerListener serverRun=null;
	public MainServer(String port){
		int intPort=Integer.parseInt(port);
		try{
			server = new ServerSocket(intPort);
			}catch (Exception e) {}
	    try{
			System.out.println("server");
		Socket socket = server.accept();
		GameWindow.contentCanvas.setVisible(true);
		System.out.println("CLIENT CONNECTED");
		serverRun = new ServerListener(socket);
		System.out.println("server");
		}catch (Exception e) {}
	}
}