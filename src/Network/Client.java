package Network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import Main.Game;
import Main.Main;
import Main.Tank;
import Show.GameWindow;

public class Client implements Serializable{
	 private Socket socket=null;
	 private ObjectOutputStream outStream=null;
	 private ObjectInputStream inStream=null;
	 private Tank				netTank;
	 
	 public Client(String ip, String port){
			try{
				socket = new Socket(ip, Integer.parseInt(port));			
				outStream= new ObjectOutputStream(socket.getOutputStream());
				inStream= new ObjectInputStream(socket.getInputStream());
			}catch (Exception e) {}
			GameWindow.contentCanvas.setVisible(true);
	 }
	 
	
	public void clientConnect(){
		try{
			Tank temTank=Main.game.getTank();
			TankToSend tts=new TankToSend(temTank.x, temTank.y, temTank.getName(),temTank.getDirect(),
					temTank.getBullet().x, temTank.getBullet().y, temTank.getBullet().
					getFly(), temTank.getPoint(),temTank.getKillTem(), temTank.getPause(), temTank.getIsStore());
					
					
					Main.game.getTank().setKillTem(false);
					Main.game.getTank().setPause(false);
			   outStream.writeObject(tts);
			   
			   
			temTank=Main.game.getRival();
		    tts=(TankToSend) inStream.readObject();
				temTank.x=tts.x; temTank.y=tts.y;  temTank.setName(tts.name); temTank.setDirect(tts.direct);
			    temTank.getBullet().x=tts.xb; temTank.getBullet().y=tts.yb;
				temTank.setPoint(tts.point);temTank.getBullet().setFly(tts.fly); temTank.setKillTem(tts.killTem);
				temTank.setPause(tts.stop); temTank.setIsStore(tts.store);
				
				    Main.game.setRival((Tank)temTank);
		    

		}catch (Exception e) {}
	}
}