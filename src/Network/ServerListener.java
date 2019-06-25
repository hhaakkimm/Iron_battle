package Network;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import Main.Main;
import Main.Tank;

public class ServerListener implements Serializable{
	private Socket socket;
	private ObjectOutputStream outStream=null;
	private ObjectInputStream inStream=null;
	public static Tank netTank;
	
	public ServerListener(Socket socket){
		this.socket = socket;
				try{
					outStream = new ObjectOutputStream(socket.getOutputStream());
					inStream = new ObjectInputStream(socket.getInputStream());
				}catch (Exception e) {}
		}
	public void run(){
		try{	
			Tank temTank=Main.game.getRival();
			
			TankToSend tts=(TankToSend) inStream.readObject();
			temTank.x=tts.x; temTank.y=tts.y; temTank.setName(tts.name); temTank.setDirect(tts.direct);
		    temTank.getBullet().x=tts.xb; temTank.getBullet().y=tts.yb;	temTank.getBullet().setFly(tts.fly); 
		    temTank.setPoint(tts.point); temTank.setKillTem(tts.killTem); temTank.setPause(tts.stop);
		    temTank.setIsStore(tts.store);
			    Main.game.setRival((Tank)temTank);
			    System.out.println("server--");
			    
			    
			temTank=Main.game.getTank();
			tts=new TankToSend(temTank.x, temTank.y, temTank.getName(),temTank.getDirect(),
						 temTank.getBullet().x, temTank.getBullet().y,temTank.getBullet().getFly(),
						 temTank.getPoint(),temTank.getKillTem(), temTank.getPause(), temTank.getIsStore());
			
			
						Main.game.getTank().setKillTem(false);
						Main.game.getTank().setPause(false);
				   outStream.writeObject(tts);

			 
		  }catch (Exception e) {}
	}	
}


