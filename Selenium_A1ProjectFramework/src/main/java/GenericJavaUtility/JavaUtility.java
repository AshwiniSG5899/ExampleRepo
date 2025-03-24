package GenericJavaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNum(int limit)
	{
		Random ran=new Random();
		int randomNum = ran.nextInt(limit);
		return randomNum;
		
	}
	public String getSystemDate()
	{
		Date dateObj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String date = sim.format(dateObj);
		return date;
	}
	public String getRequiredDate(int days)
	{
		Date dateObj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String date = sim.format(dateObj);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requiredDate = sim.format(cal.getTime());
		return requiredDate;
	}
}
