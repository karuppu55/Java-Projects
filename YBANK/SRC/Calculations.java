package YBank.utils;
import java.util.regex.*;
import java.time.*;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;  
import java.text.SimpleDateFormat; 
public class Calculations
{
	public static int agecalculation(String date)
	{
		String[] datas=date.split("\\-");
		int year=Integer.parseInt(datas[2]);
		Date cur_date = Calendar.getInstance().getTime();  
                DateFormat dateFormat = new SimpleDateFormat("yyyy");  
                int cur_year= Integer.parseInt(dateFormat.format(cur_date)); 
		int age=cur_year-year;
		return age;
	}
}
