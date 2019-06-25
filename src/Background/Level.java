package Background;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Graphics.TextureAtlas;
import Helper.MapLevel;
import Main.Game;

public class Level implements Serializable{
	//FONE CAN BE REGARDED AS INDIVIDUAL METAL...
	public static final int			FONE_SCALE=2;
	public static final int			FONE_SIZE=8;
	public static final int			FONE_SIZE_SCALE=FONE_SIZE*FONE_SCALE;
	public static final int			FONE_AMOUNT_W=(Game.WIDTH/FONE_SIZE_SCALE)+1;
	public static final int			FONE_AMOUNT_H=(Game.HEIGHT/FONE_SIZE_SCALE)+1;

	private Integer[][] foneType;
	private Map<FoneType, Fone> foneMap;
	
	public Level(TextureAtlas atlas){
		foneType=new Integer[FONE_AMOUNT_W][FONE_AMOUNT_H];
		foneMap=new HashMap<FoneType, Fone>();
		foneMap.put(FoneType.EMPTY, new Fone(atlas.cut(2*FONE_SIZE, 4*FONE_SIZE, 
				FONE_SIZE, FONE_SIZE), FONE_SCALE, FoneType.EMPTY));
		foneMap.put(FoneType.METAL, new Fone(atlas.cut(4*FONE_SIZE, 4*FONE_SIZE, 
				FONE_SIZE, FONE_SIZE), FONE_SCALE, FoneType.METAL));
		foneType=MapLevel.mapLvl("resource/map.lvl");
	}
	public void render(Graphics2D g){
		for(int i=0; i<foneType.length; i++){
			for(int j=0 ; j<foneType[i].length;j++){
				foneMap.get(FoneType.getFone(foneType[i][j])).
				render(g, j*FONE_SIZE_SCALE, i*FONE_SIZE_SCALE);
			}
		}	
	}
		
}
