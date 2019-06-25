package Show;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;

import Main.Main;
public class ServerSetting extends InTo implements Serializable{
	public ServerSetting(){
		super();
		
		errors=new JLabel("errors: ");
		errors.setBounds(PADDING,PADDING*5+4*GENERAL_H,300, GENERAL_H);
		add(errors);

		next=new JButton("NEXT");
		next.setBounds(MenuFrame.w/2+PADDING,PADDING*4+3*GENERAL_H,100, GENERAL_H);
		add(next);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean ch=true;
				
				String temError="errors: ";
				String temName=name.getText();
				String temIp=ip.getText();
				String temPort=port.getText();	
				
				//-----------------------
//				temName="server";
//				temIp="127.0.0.1";
//				temPort="1998";
//				
				
				if(temName.equals("")) temError+="(no name) ";
				if(temIp.equals(""))temError+="(no ip) ";
				if(temPort.equals(""))temError+="(no port) ";
				if(portCheker(temPort)){temError+="(incorrect port) "; ch=false; }
				else ch=true;
				
				
				
			if(!temName.equals("") && !temIp.equals("") && !temPort.equals("") && ch){
				Main.menuFrame.setVisible(false);
				ipTransfer=temIp; portTransfer=temPort;
				setIpTank(temIp);
				setPortTank(temPort);
				setNameUser(temName);
				setThisServer(true);
				Main.menuFrame.server.setVisible(false);
				Main.runGame();
			}else 
				errors.setText(temError);
		}
		
	});
		
		back=new JButton("BACK");
		back.setBounds(PADDING,PADDING*4+3*GENERAL_H,100, GENERAL_H);
		add(back);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Main.menuFrame.server.setVisible(false);
				Main.menuFrame.menu.setVisible(true);
			}
			
		});
		
	}
	
	public boolean portCheker(String port){
		if(port.length()==0 || port.length()>5 )return true;
		for(int i=0; i<port.length(); i++)
				if(port.charAt(i)<'0' && port.charAt(i)>'9')
					return true;
		int tem=Integer.parseInt(port);
         if(tem<1 && tem>3000) return true;
		return false;
	}
	
}
