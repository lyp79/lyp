package lyp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {
	
	public static String formatDate(){
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf.format(dt);
		return date;
	}

	public static String formatDate(String dateFormat){
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		String date=sdf.format(dt);
		return date;
	}
	public synchronized String getOrderId(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date dt = new Date();
		return sdf.format(dt);
	}
	
}
