package Network;

import java.io.Serializable;

import Main.Bullet;
import Main.Tank;
import Main.Tank.Direction;

public class TankToSend implements Serializable{
     public 		int x, y;
     public			String name;
     public			Tank.Direction direct;
     public			int xb, yb;
     public  		boolean fly;
     public 		int point;
     public			boolean killTem;
     public			boolean stop;

     

	public TankToSend(int x, int y, String name, Direction direct,int xb,
			int yb, boolean fly, int point, boolean killTem, boolean stop) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.direct=direct;
		this.xb=xb;
		this.yb=yb;
		this.fly=fly;
		this.point=point;
		this.killTem=killTem;
		this.stop=stop;
	}	
}
