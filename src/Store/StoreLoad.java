package Store;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Show.GameWindow;
import Show.MenuFrame;

public class StoreLoad extends Container{
	private JLabel info;
	private JTextArea gameRes;
	private JButton back, delete;
	private JTextField delID;
	

	
	public StoreLoad(){
		setSize(MenuFrame.w,MenuFrame.h);
		setLayout(null);		
		
		info=new JLabel("The Result of Previous Games");
		info.setBounds(Show.InTo.PADDING,Show.InTo.PADDING,
				Show.MenuFrame.w-2*Show.InTo.PADDING, Show.InTo.GENERAL_H );
		add(info);
		
		
					
		gameRes=new JTextArea();
		gameRes.setBounds(10,Show.InTo.PADDING*2+Show.InTo.GENERAL_H,
							Show.MenuFrame.w-20, Show.InTo.GENERAL_H*5 );
		gameRes.setEditable(false);
					history();
					add(gameRes);
					
					
					back=new JButton("BACK");
					back.setBounds(Show.InTo.PADDING,Show.InTo.PADDING*2+10+6*Show.InTo.GENERAL_H,
							70, Show.InTo.GENERAL_H );
					add(back);			
					back.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
								Main.Main.menuFrame.load.setVisible(false);		
								Main.Main.menuFrame.setVisible(false);	
								GameWindow.window.setVisible(true);
						}
					});
					
					
					delID=new JTextField();
					delID.setBounds(Show.MenuFrame.w-Show.InTo.PADDING-100-60,Show.InTo.PADDING*2+10+6*Show.InTo.GENERAL_H,
							50, Show.InTo.GENERAL_H );
					add(delID);
					
					
					delete=new JButton("DELATE");
					delete.setBounds(Show.MenuFrame.w-Show.InTo.PADDING-100,Show.InTo.PADDING*2+10+6*Show.InTo.GENERAL_H,
							100, Show.InTo.GENERAL_H );
					add(delete);			
					delete.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							String temId=delID.getText();
					if(temId!=""){
						gameRes.setText("");
						history();
						
							try { ObjectInputStream ois=new ObjectInputStream(new FileInputStream("game.dat"));
										    ArrayList<Storage> storage=(ArrayList<Storage>)ois.readObject();
						
											int id=Integer.parseInt(temId)-1;
											storage.remove(id);
						
											try {	ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("game.dat"));
											oos.writeObject(storage);			
										oos.close();} catch (IOException e1) {}
							ois.close();} catch (IOException e1) {} catch (ClassNotFoundException e1) {}
							}
						}
					});
		
		
//		getId=new JTextField();
//		getId.setBounds(Show.InTo.PADDING,Show.InTo.PADDING*3+2*Show.InTo.GENERAL_H,
//				70, Show.InTo.GENERAL_H );
//		add(getId);
//		
//		load=new JButton("PLAY");
//		load.setBounds(Show.InTo.PADDING+100,Show.InTo.PADDING*3+2*Show.InTo.GENERAL_H,
//				100, Show.InTo.GENERAL_H );
//		add(load);
//		
//		load.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				ObjectInputStream ois=null;
//				ArrayList<Storage> storage=null;
//                                                                                 
//				      try { ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("game.dat"));    
//			          ArrayList<Storage> storage2=(ArrayList<Storage>)ois2.readObject();
//			          TankToSend temTTS=null;
//			          if(Main.Main.game.getTank().getIsServer()){
//			                Main.Main.game.setTank(changeToTank(storage2.get(0).getTankServer()));
//			                Main.Main.game.setRival(changeToTank(storage2.get(0).getTankClient()));
//			                temTTS=storage2.get(0).getTankClient();
//			                } 
//			          else { Main.Main.game.setTank(changeToTank(storage2.get(0).getTankClient()));
//			          		 Main.Main.game.setRival(changeToTank(storage2.get(0).getTankServer()));
//			          		temTTS=storage2.get(0).getTankServer();
//			          }
//			          
//			          
//                       ois.close();} catch (IOException e1) {} catch (ClassNotFoundException e1) {}           
//			}
//		});
//		
//	}
//	public static Tank changeToTank(TankToSend tts){
//		Tank temTank=null;
//		
//		temTank.x=tts.x; temTank.y=tts.y; temTank.setName(tts.name); temTank.setDirect(tts.direct);
//	    temTank.getBullet().x=tts.xb; temTank.getBullet().y=tts.yb;	temTank.getBullet().setFly(tts.fly); 
//	    temTank.setPoint(tts.point); temTank.setKillTem(tts.killTem); temTank.setPause(false);
//	    temTank.setIsStore(false);
//		
//		
//		return temTank;
					
	}
	
	public void history(){
		try { ObjectInputStream ois=new ObjectInputStream(new FileInputStream("game.dat"));
	    ArrayList<Storage> storage=(ArrayList<Storage>)ois.readObject();
	
	    	for(int i=0; i<storage.size(); i++){
			String tem="id= "+(i+1)+" "+storage.get(i).getName()+"    "+storage.get(i).getTankServer().name+" "+
	    	storage.get(i).getTankServer().point+" - "+ storage.get(i).getTankClient().point+" "
					+storage.get(i).getTankClient().name  +""+'\n';
			gameRes.append(tem);
		}
		ois.close();} catch (IOException e1) {} catch (ClassNotFoundException e1) {}
	}
}






