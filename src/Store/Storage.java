package Store;
import java.io.Serializable;

import Main.Tank;
import Network.TankToSend;

public class Storage implements Serializable{
	private String name;
	private TankToSend[] tanks;
	
	Storage(String name, TankToSend[] tanks){
		this.name=name;
		this.tanks=tanks;
	}
	public String getName(){
		return name;
	}
	public TankToSend getTankServer(){
		return tanks[0];
	}
	public TankToSend getTankClient(){
		return tanks[1];
	}
}
