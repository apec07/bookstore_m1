package idv.utli;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IdGen {
	
	private static String now() {
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdfTime.format(new Date()).replaceAll("[[\\s-:punct:]]", "");
	}
	
	public static String getID() {
		
		Random r = new Random();
		Integer getRandomNum = r.nextInt(900000)+100000; //(int)(Math.random()*999999)
		String myID = now() + "" + getRandomNum; 
        return myID; 
		
	}
	
}
