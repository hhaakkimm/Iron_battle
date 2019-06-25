package Show;

import java.awt.Container;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;

import Main.Main;

public class ServerList extends Container implements Serializable{
		private ArrayList<JLabel> tanksName=new ArrayList<JLabel>();
		
		public ServerList(){
			setSize(MenuFrame.w,MenuFrame.h);
			setLayout(null);
			
			
		}
	
}
