package Show;
import java.awt.Container;
import java.awt.TextField;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;

import Main.Game;

public abstract class InTo extends Container implements Serializable{
	public static final int PADDING=20;
	public static final int LEBEL_W=100;
	public static final int FIELD_W=150;
	public static final int GENERAL_H=30;
	
	
	protected JButton 	next, back;
	public TextField 	ip, name, port;
	private String 		ipTank, portTank, nameUser;
	private boolean     thisServer;
	private JLabel		ipL, nameL, portL;
	protected JLabel 	errors;
	public String 		 ipTransfer;
	public String		 portTransfer;

	
	
	public InTo(){
		setSize(MenuFrame.w,MenuFrame.h);
		setLayout(null);
		
		nameL=new JLabel("User Name: ");
		nameL.setBounds(PADDING,PADDING,LEBEL_W,GENERAL_H);
		add(nameL);
		name=new TextField();
		name.setBounds(PADDING+LEBEL_W, PADDING, FIELD_W, GENERAL_H);
		add(name);
		
		ipL=new JLabel("IP ADRESS: ");
		ipL.setBounds(PADDING,PADDING*2+GENERAL_H,LEBEL_W,GENERAL_H);
		add(ipL);
		ip=new TextField();
		ip.setBounds(PADDING+LEBEL_W, PADDING*2+GENERAL_H, FIELD_W, GENERAL_H);
		add(ip);
		
		portL=new JLabel("PORT: ");
		portL.setBounds(PADDING,PADDING*3+2*GENERAL_H,LEBEL_W,GENERAL_H);
		add(portL);
		port=new TextField();
		port.setBounds(PADDING+LEBEL_W, PADDING*3+2*GENERAL_H, FIELD_W, GENERAL_H);
		add(port);		
	}
	
	public String getIpTank() {
		return ipTank;
	}

	public void setIpTank(String ipTank) {
		this.ipTank = ipTank;
	}

	public String getPortTank() {
		return portTank;
	}

	public void setPortTank(String portTank) {
		this.portTank = portTank;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	public void setThisServer(boolean server){
		this.thisServer=server;
	}
	public boolean getThisServer(){
		return this.thisServer;
	}
	
}
