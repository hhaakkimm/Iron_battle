package Background;

import java.io.Serializable;

public enum FoneType implements Serializable{
		EMPTY(0), 
		METAL(1);
	private int index;
    FoneType(int index){
    	this.index=index;
    }	
    
    public int getIndex(){
    	return this.index;
    }
    public static FoneType getFone(int index){
    	if(index==1)return FoneType.METAL;
    	else return FoneType.EMPTY;
    }
}
