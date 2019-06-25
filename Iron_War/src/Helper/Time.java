package Helper;

import java.io.Serializable;

public class Time implements Serializable{
	public static final long SECOND=1000000000L;
	
	public static Long get(){
		return System.nanoTime();
	}
}
