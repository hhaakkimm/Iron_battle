package Store;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Main.Main;
import Show.InTo;
import Show.MenuFrame;

public class WhoWin extends Container{
	private JLabel res;
	private JLabel win;
	private JButton next;
	
	public WhoWin(String tank, String rival, int t, int r){
		setSize(MenuFrame.w,MenuFrame.h);
		setLayout(null);
		
		String result=tank+" "+t+" - "+r+" "+rival;
		res=new JLabel(result);
		res.setBounds(InTo.PADDING*4, InTo.PADDING, 200, InTo.GENERAL_H);
		add(res);
		
		
		String winer= t < r ? rival :tank;
		String chempion="The chempion is "+ winer.toUpperCase()+" !!!!!!!!";
		win=new JLabel(chempion);
		win.setBounds(InTo.PADDING*4, InTo.PADDING*2+InTo.GENERAL_H, 200, InTo.GENERAL_H);
		add(win);
		
		next=new JButton("Next");
		next.setBounds(InTo.PADDING*4, InTo.PADDING*3+2*InTo.GENERAL_H, 100, InTo.GENERAL_H);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Main.game.showStore();
			}});
		add(next);
		
	}

}
