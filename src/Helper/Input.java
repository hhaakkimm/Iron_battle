package Helper;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Input extends JComponent implements Serializable{
	private boolean[] map;
	
	public Input(){
		map=new boolean[50];
		for(int i=27; i<map.length; i++){
			final int KEY=i;			
				getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i,0,false),i*2);
				getActionMap().put(i*2, new AbstractAction(){
					public void actionPerformed(ActionEvent e) {
							map[KEY]=true;
					}	
				});
			
				getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i,0,true), i*2+1);
				getActionMap().put(i*2+1, new AbstractAction(){
					public void actionPerformed(ActionEvent e) {
						map[KEY]=false;
					}
				});	
		}
	}
	
	
	public boolean[] getMap(){
		return Arrays.copyOf(map, map.length);
	}
	public boolean getKey(int idKey){
		return map[idKey];
	}
	
}











