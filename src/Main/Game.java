package Main;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

import Background.Level;
import Graphics.TextureAtlas;
import Helper.Input;
import Helper.Time;
import Network.Client;
import Network.MainServer;
import Show.GameWindow;


public class Game implements Runnable, Serializable{
	//---------------------------------
	public static final int 		WIDTH=913;
	public static final int			HEIGHT=607;
	public static final String		TITLE="IRON WAR";
	public static final int			STRATEGY_NUM=3;
	public static final String		IMAGE_NAME="atlas.png";
	//public static final int 		X=0,Y=0;
	public static final int			SPEED=5;
	public static final int			MASHTAB=2;
	//---------------------------------
	
	private static final float		UPDATE_FREQUENCY=60.0f;
	private static final float 		UPDATE_INTERVAL=Time.SECOND/UPDATE_FREQUENCY;
	private static final long		REST_RENDER=1;
	
	//---------------------------------
	private boolean			play;
	public Thread			mainThread;
	private Graphics2D		graphic;
	private Input			input;		
	private	TextureAtlas	atlas;
	private Level			lvl;
	
	//---------------------------------
	private int 			tankNum;
	private  Tank			tank;
	private boolean			tankServer;
	private long 			prevTime;
	
	private boolean			toStart=true; //to hanldle bag start a geme after pause
	//--------------------------------
	private String			port;
	private String			ip;
	private MainServer		server;
	private Client			client;
	//--------------------------------
	private Tank 			rival;
	
	public Game(boolean thisServer){
		tankServer=thisServer;
		
		play=false;
		GameWindow.create(WIDTH, HEIGHT, TITLE, STRATEGY_NUM, this.tankServer);
		graphic=GameWindow.getGraphics();
		input=new Input();
		GameWindow.addInputListener(input);
		atlas =new TextureAtlas(IMAGE_NAME);
		
		String temName;
		if(tankServer){temName=Main.menuFrame.server.getNameUser();
		}else {	temName=Main.menuFrame.client.getNameUser();}
		
		int xInitial=MASHTAB*16*2, yInitial=MASHTAB*16*2;
		if(!tankServer){xInitial=WIDTH-MASHTAB*16*3; yInitial=HEIGHT-MASHTAB*16*3;}
		
		
		tank=new Tank(xInitial, yInitial, SPEED, MASHTAB, atlas,temName,tankServer);
		rival=new Tank(300, 300, SPEED, MASHTAB, atlas,temName, !tankServer);
		
		lvl= new Level(atlas);
		if(tankServer){
		port=Main.menuFrame.server.getPortTank();
		ip=Main.menuFrame.server.getIpTank();
		}else {
			port=Main.menuFrame.client.getPortTank();
			ip=Main.menuFrame.client.getIpTank();
			}
		
		if(tankServer)
		server=new MainServer(port);
		else client=new Client(ip, port);
	}
	
	
	
	
	//---------------------------------	
	public void setToStart(boolean toStart){
		this.toStart=toStart;
	}
	public boolean getToStart(){
		return this.toStart;
	}
	
	public void setPrevTime(long time){
		this.prevTime=time;
	}
	public long getPrevTime(){
		return this.prevTime;
	}
	
	
	public void setRival(Tank tem){
		this.rival=tem;
	}
	public Tank  getRival(){
		return this.rival;
	}
	
	public String getIp(){
		return this.ip;
	}
	public void setIp(String ip){
		this.ip=ip;
	}
	
	public String getPort(){
		return this.port;
	}
	public void setPort(String port){
		this.port=port;
	}
	
	public boolean getTankServer(){
		return this.tankServer;
	}
	public void setTankServer(boolean server){
		this.tankServer=server;
	}
	
	public Tank getTank(){
		return this.tank;
	}
	public void setTank(Tank tank){
		this.tank=tank;
	}
	//---------------------------------
	
	
	
	public synchronized void start(){
		if(play)return;
		play=true;
		mainThread=new Thread(this);
		mainThread.start();
	}
	
	
	
			public  void run() {
						 float updateNeed=0;
						 prevTime=Time.get();
						 int fps=0, ups=0, upsl=0;
						 float timeSecond=0;

				 
				while(play){
					long currentTime=Time.get();
					long elapsedTime=currentTime-prevTime;
					prevTime=currentTime;
					
					
					timeSecond+=elapsedTime;
					
					boolean renderOptimize=false;
					updateNeed+=(elapsedTime/UPDATE_INTERVAL);
						while(updateNeed>1){
							updateMovement();
							updateNeed--;
							ups++;
						if(renderOptimize)upsl++;
						else  renderOptimize=true;					
						}
								if(renderOptimize){
									render();
									fps++;
								}else{	 
									     try{
									            mainThread.sleep(REST_RENDER);
									     }catch(InterruptedException e){}
								}
							if(timeSecond>=Time.SECOND){
								String users=" || USERS :";
									users+=tank.getName()+" = "+tank.getPoint()+" | "+rival.getName()+" = "+rival.getPoint()+" | ";
								
								GameWindow.setTitle("  |  fps:"+fps+"  ups:"+ups+"  upsl:"+upsl+"  |  "+TITLE+users);
								ups=0; fps=0; upsl=0; timeSecond=0;
							}
				}
			}
	
	private void render(){
		lvl.render(graphic);
		tank.render(graphic);
		rival.render(graphic);
		GameWindow.bufferSwap();
	}
	public void pause(){
		if(rival.getPause())tank.setPause(true);
		else  tank.setPause(false);
	}	
	
	
	
	
		
private void updateMovement(){	
	pause();
		if(input.getKey(27) || !toStart){
			toStart=false;
			tank.setPause(true);
			rival.setPause(true);
		}
		
		if(toStart){
			tank.setPause(false);
		    rival.setPause(false);
		}
		
		toStart=!rival.getPause();
		
	 if(tank.getPause()){
	    }else{
		    GameWindow.window.repaint();
			tank.updateMovement(input);
			if(tank.getKillTem()){
				int xInitial=MASHTAB*16*2, yInitial=MASHTAB*16*2;
				if(!tankServer){xInitial=WIDTH-MASHTAB*16*3; yInitial=HEIGHT-MASHTAB*16*3;}
				tank.x=xInitial; tank.y=yInitial;
			}
		
			
			if(rival.getKillTem()){
				int xInitial=MASHTAB*16*2, yInitial=MASHTAB*16*2;
				if(!tankServer){xInitial=WIDTH-MASHTAB*16*3; yInitial=HEIGHT-MASHTAB*16*3;}
				tank.x=xInitial; tank.y=yInitial;
				rival.setKillTem(false);
			}
	    }		
		if(tankServer)
			server.serverRun.run();
		else client.clientConnect();
		
		if(tank.getIsStore()){
			tank.setIsStore(false);
			showResult();
		}
		
		if(rival.getIsStore()){
			rival.setIsStore(false);
			showResult();
		}
	}
	
public void showResult(){
	Main.menuFrame.createResult();
	
	GameWindow.window.setVisible(false);
	Main.menuFrame.setVisible(true);
	Main.menuFrame.win.setVisible(true);
}

public void showStore(){
	if(tank.getIsServer()){
	Main.menuFrame.win.setVisible(false);
	Main.menuFrame.exit.setVisible(true);
	}else{
		Main.menuFrame.dispose();
		stop();
		clear();
	}
}
	
	public synchronized void stop(){
		if(!play) return;
		play=false;
		try{ mainThread.join();
			}catch(InterruptedException e){}
		clear();
	}


	public void clear(){
		GameWindow.destroy();
	}


}
