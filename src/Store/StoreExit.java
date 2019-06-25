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
import javax.swing.JTextField;

import Main.Main;
import Main.Tank;
import Network.TankToSend;
import Show.MenuFrame;

public class StoreExit extends Container{
	private JLabel nameL;
	private JTextField nameV;
	private JButton store;
	
	public StoreExit(){
		setSize(MenuFrame.w,MenuFrame.h);
		setLayout(null);		
		
		nameL=new JLabel("Name of Game to Store:");
		nameL.setBounds(Show.InTo.PADDING,Show.InTo.PADDING,
				Show.MenuFrame.w-2*Show.InTo.PADDING, Show.InTo.GENERAL_H );
		add(nameL);
		
		nameV=new JTextField();
		nameV.setBounds(Show.InTo.PADDING,Show.InTo.PADDING*2+Show.InTo.GENERAL_H,
				Show.MenuFrame.w-2*Show.InTo.PADDING, Show.InTo.GENERAL_H );
		add(nameV);
		
		store=new JButton("SAVE");
		store.setBounds(Show.InTo.PADDING,Show.InTo.PADDING*3+2*Show.InTo.GENERAL_H,
				100, Show.InTo.GENERAL_H );
		add(store);
		
		store.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ObjectInputStream ois=null;
				ArrayList<Storage> storage=null;
				
					try {	ois=new ObjectInputStream(new FileInputStream("game.dat"));
						storage=(ArrayList<Storage>)ois.readObject();
					ois.close();} catch (IOException e1) {} catch (ClassNotFoundException e1) {}
//			ArrayList<Storage> storage=new ArrayList<Storage>();
				
				TankToSend[] tanks=new TankToSend[2];
				tanks[0]=getTankToSend(Main.game.getTank());
				tanks[1]=getTankToSend(Main.game.getRival());
				Storage s=new Storage(nameV.getText(), tanks);
				storage.add(s);
				
					try {	ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("game.dat"));
						oos.writeObject(storage);			
					oos.close();} catch (IOException e1) {}
					
				Main.game.stop();
				Main.game.clear();
				Main.menuFrame.dispose();
				
			}
		});
	}
	public static TankToSend getTankToSend(Tank temTank){
		TankToSend res = new TankToSend(temTank.x, temTank.y, temTank.getName(),temTank.getDirect(),
				 temTank.getBullet().x, temTank.getBullet().y,temTank.getBullet().getFly(),
				 temTank.getPoint(),temTank.getKillTem(), temTank.getPause(), temTank.getIsStore());
		return res;
	}
	
}






