package Helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
public class MapLevel implements Serializable {
	public static Integer[][] mapLvl(String file){
		Integer[][] res = null;
		try(BufferedReader read=new BufferedReader(new FileReader(new File(file)))){
			ArrayList<Integer[]> lineInt=new ArrayList<Integer[]>();
			String line;
			while((line=read.readLine())!=null){
				String tem[]=line.split(" ");
				lineInt.add(strToInt(tem));
			}
			res=new Integer[lineInt.size()][lineInt.get(0).length];
			for(int i=0; i<lineInt.size();i++)
				res[i]=lineInt.get(i);
			
		} catch (IOException e) {}
		return res;
	}
	public static Integer[] strToInt(String[] aStr){
		Integer[] aInt=new Integer[aStr.length];
		for(int i=0; i<aStr.length; i++)
			aInt[i]=Integer.parseInt(aStr[i]);
		return aInt;
	}
}
