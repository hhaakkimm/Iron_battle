package Main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Background.Level;
import Graphics.Sprite;
import Graphics.TextureAtlas;
import Helper.Input;
import Helper.MapLevel;
import Helper.Time;

public class Tank extends Entity implements Serializable{
	public static final int SPRITE_SIZE=16;
	public static final int CORECTION=5;
	public static final int BULLET_SIZE=7;
	public static final int BULLET_SPEED=10;
	
	private boolean 		aLIVE;
	private String 			nameTank;
	private int 			point;
	private Bullet 			bullet;
	private Direction 		direct;
	private Direction 		bulletDirect;
	private Map<Direction, Sprite> mapDirect=new HashMap<Direction, Sprite>();
	private int 			speed;
	private int 			mashtab;
	private boolean 	    killTem;
	private int 			tankTypeY;
	private TextureAtlas	atlas;
	
	//---------------------------
	private boolean			isServer;
	private boolean			isPause;
	private boolean			isStore;
	
	private Integer[][]		obstecle;
	
	//---------------------------
	
	
	public Tank(int x, int y, int speed, int mashtab, TextureAtlas atlas, String name, boolean isServer ){
		super(x, y);
		nameTank=name;
		aLIVE=true;
		point=0;
		direct=Direction.UP;
		this.speed=speed;
		this.mashtab=mashtab;
		this.isServer=isServer;
		this.isPause=false;
		this.isStore=false;
		
		this.atlas=atlas;
		tankTypeY=(int) Math.round(Math.random()*3); //
		if(isServer)	tankTypeY=0;
		else 			tankTypeY=1;
		BufferedImage temImage=atlas.cut(0 , tankTypeY*SPRITE_SIZE, 4*SPRITE_SIZE, SPRITE_SIZE);
		for(Direction i: Direction.values()){
			Sprite sprite=new Sprite(i.atlasCut(temImage), mashtab);
			mapDirect.put(i, sprite);
		}
		
		bullet=new Bullet(x+(SPRITE_SIZE*mashtab)/2-BULLET_SIZE/2, y, BULLET_SIZE,BULLET_SIZE, atlas, BULLET_SPEED);
		bulletDirect=direct;
		obstecle=MapLevel.mapLvl("resource/map.lvl");
	}

	//---------------------------
	public enum Direction implements Serializable{
		LEFT	(1*SPRITE_SIZE,0,SPRITE_SIZE,SPRITE_SIZE),
		UP		(0*SPRITE_SIZE,0,SPRITE_SIZE,SPRITE_SIZE),
		RIGHT	(3*SPRITE_SIZE,0,SPRITE_SIZE,SPRITE_SIZE),
		DOWN	(2*SPRITE_SIZE,0,SPRITE_SIZE,SPRITE_SIZE);
		private int x,y,w,h;
		Direction(int x, int y,int w, int h){
			this.x=x;	this.y=y;	 this.w=w;	 this.h=h;
		}
		public BufferedImage atlasCut(BufferedImage image){
			return image.getSubimage(x, y, w, h);
		}
	}
	//---------------------------
	public boolean getIsServer(){
		return this.isServer;
	}
	
	public boolean getIsStore(){
		return this.isStore;
	}
	public void setIsStore(boolean isStore){
		this.isStore=isStore;
	}
	
	public boolean getPause(){
		return this.isPause;
	}
	public void setPause(boolean pause){
		this.isPause=pause;
	}
	
	public boolean getKillTem(){
		return this.killTem;
	}
	public void setKillTem(boolean kill){
		this.killTem=kill;
	}
	
	
	public Direction getDirect(){
		return this.direct;
	}
	public void setDirect(Direction direct){
		this.direct=direct;
	}	
	
	
	public boolean getAlive(){
		return this.aLIVE;
	}
	
	public void setAlive(boolean aLIVE){
		this.aLIVE=aLIVE;
	}
	
	public String getName(){
		return this.nameTank;
	}
	public void setName(String name){
		this.nameTank=name;
	}
	
	public int getPoint(){
		return point;
	}
	public void setPoint(int point){
		this.point=point;
	}
		
	public Bullet getBullet(){
		return this.bullet;
	}
	public void setBullet(Bullet bullet){
		this.bullet=bullet;
	}
	
//-----------------------------------------

	public void render(Graphics2D g) {
		bullet.render(g);
		mapDirect.get(direct).render(g, x, y);
	}
	
	
	public void updateMovement(Input input) {
//-----------bullet----------------------
		if(!bullet.getFly()){
			bullet.x=(x+SPRITE_SIZE/2)+BULLET_SIZE/2;	bullet.y=(y+SPRITE_SIZE/2)+BULLET_SIZE/2;
		}
		if(input.getKey(32)){
			if(!bullet.getFly()){
					bullet.setFly(true);
				if(direct==Direction.UP) bulletDirect=Direction.UP;
				else if(direct==Direction.DOWN)  bulletDirect=Direction.DOWN;
				else if(direct==Direction.RIGHT)  bulletDirect=Direction.RIGHT;
				else if(direct==Direction.LEFT)  bulletDirect=Direction.LEFT;
			}
		}
		
		
		int[] posBullet=new int[2];
		posBullet[0]=bullet.x;  posBullet[1]=bullet.y; 
		if(bullet.getFly()){
			if(bulletDirect==Direction.UP){
				if(0!=obstecle(posBullet,BULLET_SIZE-4))
				bullet.y-=bullet.getSpeed();
				else bullet.setFly(false);
			}
			else if(bulletDirect==Direction.DOWN){
				if(0!=obstecle(posBullet,BULLET_SIZE-4))
					bullet.y+=bullet.getSpeed();
					else bullet.setFly(false);
			}
			else if(bulletDirect==Direction.RIGHT){
				if(0!=obstecle(posBullet,BULLET_SIZE-4))
					bullet.x+=bullet.getSpeed();
					else bullet.setFly(false);
			}
			else if(bulletDirect==Direction.LEFT){
				if(0!=obstecle(posBullet,BULLET_SIZE-4))
					bullet.x-=bullet.getSpeed();
					else bullet.setFly(false);
			}
		}
		

		if(bullet.getFly()){
				live();
		}
		
//-----------bullet---END-------------------	

	
//-----------TANK----------------------

		
					//		left arrow: 37  
					//		up arrow: 38
					//		right arrow: 39
					//		down arrow: 40
							int xTem=x,  yTem=y;
							int[] pos=new int[2]; pos[0]=x; pos[1]=y;
							int[] pos2=new int[2]; pos2[0]=Main.game.getRival().x; pos2[1]=Main.game.getRival().y;
							if(input.getKey(37)){
								pos[0]-=speed;
								int tem1=obstecle(pos,SPRITE_SIZE);
								int tem2=tanksInteraction(pos,pos2,SPRITE_SIZE);
								if(tem1!=0 && tem2!=0)
								xTem-=speed;
								direct=Direction.LEFT;	
																
								
							}else if(input.getKey(38)){
								pos[1]-=speed;
								int tem1=obstecle(pos,SPRITE_SIZE);
								int tem2=tanksInteraction(pos,pos2,SPRITE_SIZE);
								if(tem1!=0 && tem2!=0)
								yTem-=speed;
								direct=Direction.UP;
							}else if(input.getKey(39)){
								pos[0]+=speed;
								int tem1=obstecle(pos,SPRITE_SIZE);
								int tem2=tanksInteraction(pos,pos2,SPRITE_SIZE);
								if(tem1!=0 && tem2!=0)
								xTem+=speed;
								direct=Direction.RIGHT;
							}else if(input.getKey(40)){
								pos[1]+=speed;
								int tem1=obstecle(pos,SPRITE_SIZE);
								int tem2=tanksInteraction(pos,pos2,SPRITE_SIZE);
								if(tem1!=0 && tem2!=0)
								yTem+=speed;
								direct=Direction.DOWN;
							}			
							x=xTem; y=yTem;
//-----------TANK---END-------------------				
  }
	
	
	private void live() {
		Rectangle tankPos=new Rectangle(Main.game.getRival().x, Main.game.getRival().y, SPRITE_SIZE+CORECTION*2,SPRITE_SIZE+CORECTION*2),
				  bulletPos=new Rectangle(bullet.x,bullet.y,BULLET_SIZE+CORECTION,BULLET_SIZE+CORECTION);
	    if(tankPos.intersects(bulletPos)){
	    	bullet.setFly(false);
	    	killTem=true;
	    	point++;
	    }
	}
	
	

	public int tanksInteraction(int[] xy,int[] Exy, int  SIZE){
		Rectangle tankPos1=new Rectangle(xy[0]+SIZE/2,xy[1]+SIZE/2,SIZE+CORECTION*2,SIZE+CORECTION*2);
		Rectangle tankPos2=new Rectangle(Exy[0]+SIZE/2,Exy[1]+SIZE/2,SIZE+CORECTION*2,SIZE+CORECTION*2);
		for(int i=0; i<obstecle.length;i++)
			for(int j=0; j<obstecle[i].length;j++){
				if(obstecle[i][j]!=0)
					if(tankPos1.intersects(tankPos2)){
						return 0;
					}
			}
		return speed;
	}
	
	public int obstecle(int[] xy,int  SIZE){
		Rectangle tankPos=new Rectangle(xy[0]+SIZE/2,xy[1]+SIZE/2,SIZE+CORECTION,SIZE+CORECTION);
		for(int i=0; i<obstecle.length;i++)
			for(int j=0; j<obstecle[i].length;j++){
				if(obstecle[i][j]!=0)
					if(tankPos.intersects(new Rectangle(j*Level.FONE_SIZE_SCALE, i*Level.FONE_SIZE_SCALE,
							Level.FONE_SIZE_SCALE+CORECTION,Level.FONE_SIZE_SCALE+CORECTION))){
						return 0;
					}
			}
		return speed;
	}

	
	
}
